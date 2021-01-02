package com.joyintech.tams.invest.batch.prdopenplanupdate.writer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.tams.common.base.dao.FinCashPlanMapper;
import com.joyintech.tams.common.base.dao.FinFloatAccrualScheMapper;
import com.joyintech.tams.common.base.dao.FinOpenPlanMapper;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinCashPlanKey;
import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;
import com.joyintech.tams.common.base.entity.FinFloatAccrualScheKey;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.FinOpenPlanKey;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dao.PrdOpenPlanUpdateMapper;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.AccrDateNeedOtherDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.FinOpenPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.PrdOpenPlanUpdateDto;

/**
 *  批处理-市场日历接入后更新产品开放计划 writer
 */
@Service
@Scope("prototype")
public class PrdOpenPlanUpdateWriter extends BaseWriter<NeedToWriteEntity>{
	@Autowired
	private FinOpenPlanMapper finOpenPlanMapper;
	@Autowired
	private PrdOpenPlanUpdateMapper prdOpenPlanUpdateMapper;
	/**
     * 平台日期接口
     */
    @Autowired(required = false)
    private PlatformDate platformDateService;
    //新增浮动管理费计划
  	@Autowired
  	private FinFloatAccrualScheMapper finFloatAccrualScheMapper;
  	@Autowired
  	private FinCashPlanMapper finCashPlanMapper;
    

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void write(List<? extends NeedToWriteEntity> items) throws Exception {

		List<FinOpenPlanDto> finOpenPlanDtoList = (List<FinOpenPlanDto>) items;
		
		List<String> prodidList=new ArrayList<String>();
		
		for (FinOpenPlanDto finOpenPlanDto : finOpenPlanDtoList) {
			//更新开放计划
			if (!StringUtil.isEmptyList(finOpenPlanDto.getFinOpenPlanList())) {
				// 先删后插
				PrdOpenPlanUpdateDto dto = finOpenPlanDto.getPrdOpenPlanUpdateDto();
				FinOpenPlanKey fopKey = new FinOpenPlanKey();
				fopKey.setCashId(dto.getCashId());
				fopKey.setOpenType(dto.getOpenType());
				fopKey.setOpenDateEnd(dto.getOpenDateEnd());
				prdOpenPlanUpdateMapper.deleteOldOpenPlans(fopKey);
				for (FinOpenPlan finOpenPlan : finOpenPlanDto.getFinOpenPlanList()) {
					// 查询无重复
					FinOpenPlanKey key = new FinOpenPlanKey();
					key.setCashId(finOpenPlan.getCashId());
					key.setOpenType(finOpenPlan.getOpenType());
					key.setOpenDateEnd(finOpenPlan.getOpenDateEnd());
					FinOpenPlan fop = finOpenPlanMapper.selectByPrimaryKey(key);
					
					if(!StringUtil.isNotEmpty(fop)) {
						finOpenPlanMapper.insert(finOpenPlan); // 如果为空再插入操作
					}
					//设置List存储唯一的产品代码
					if(StringUtil.isNotEmpty(finOpenPlan.getFinprodId())){
						if(!prodidList.contains(finOpenPlan.getFinprodId())){
							prodidList.add(finOpenPlan.getFinprodId());
						}
					}
				}
				
				//生成最新的浮动管理费计提计划 
				createFinFloatAccrualSche(prodidList);
			}
			
			//更新付息计划
			if (!StringUtil.isEmptyList(finOpenPlanDto.getFinCashPlanList())) {
				// 先删后插
				PrdOpenPlanUpdateDto dto = finOpenPlanDto.getPrdOpenPlanUpdateDto();
				FinCashPlanKey fcpKey = new FinCashPlanKey();
				fcpKey.setCashId(dto.getCashId());
				fcpKey.setMdate(dto.getPayFPMdate());
				prdOpenPlanUpdateMapper.deleteOldPayPlans(fcpKey);
				for (FinCashPlan finCashPlan : finOpenPlanDto.getFinCashPlanList()) {
					// 查询无重复
					FinCashPlanKey key = new FinCashPlanKey();
					key.setCashId(finCashPlan.getCashId());
					key.setMdate(finCashPlan.getMdate());
					FinCashPlan fcp = finCashPlanMapper.selectByPrimaryKey(key);
					
					if(!StringUtil.isNotEmpty(fcp)) {
						finCashPlanMapper.insert(finCashPlan); // 如果为空再插入操作
					}
				}
			}
			
			//更新系统参数-市场日历更新联动更新的开发计划日期（年）
			String platYear = DateUtil.format(platformDateService.getSysDate()).substring(0, 4);
			prdOpenPlanUpdateMapper.updateSysParam(platYear);
		}
	}

	/**
	 * 根据开放计划生成浮动管理费计提计划 
	 */
	public void createFinFloatAccrualSche(List<String> finprodidList){
		
		if (!StringUtil.isEmptyList(finprodidList)) {
			for (String finprodId : finprodidList) {
				// 1.根据产品代码  查询到相应的开放计划净值日
				List<Date> dateOpenPlanList = prdOpenPlanUpdateMapper.findNdateOrEndDateByProdid(finprodId);
				if(StringUtil.isEmptyList(dateOpenPlanList)){//过滤收益型产品
					continue;
				}
				// 1.2 当净值型产品没有挂浮动管理费时，无需更新浮动管理费计划
				Map<String,Object> map = new HashMap<String,Object>();
		        map.put("FINPROD_ID", finprodId);
		        map.put("CASH_TYPE_2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
		        //查询浮动管理费的现金流代码
		        String cashId = prdOpenPlanUpdateMapper.selectfinCashInfo(map);
		        if(StringUtil.isEmpty(cashId)){
		        	continue;
		        }
				// 2.将其他时间点也塞进dateOpenPlanList内
				AccrDateNeedOtherDto accrDateNeedOtherDto = prdOpenPlanUpdateMapper
						.findOtherNeedDateByProdid(finprodId);
				//3.根据浮动管理费的cashid删除产品原计提计划
				FinFloatAccrualScheKey finFloatAccrualScheKey=new FinFloatAccrualScheKey();
				finFloatAccrualScheKey.setCashId(accrDateNeedOtherDto.getCashId());
				prdOpenPlanUpdateMapper.deleteFloatAccrualScheByPrimaryKey(finFloatAccrualScheKey);
				
				if (StringUtil.isNotEmpty(accrDateNeedOtherDto)) {
					addDateToList(dateOpenPlanList, accrDateNeedOtherDto.getVdate());
					addDateToList(dateOpenPlanList, accrDateNeedOtherDto.getMdate());
					addDateToList(dateOpenPlanList, accrDateNeedOtherDto.getProvisionFirstDay());
					addDateToList(dateOpenPlanList, accrDateNeedOtherDto.geteAccrDate());
				}
				// 对dateOpenPlanList进行处理
				Date startDate = dateOpenPlanList.get(0);
				boolean flag = true;
				for (Date endDate : dateOpenPlanList) {
					if (startDate.compareTo(endDate) == 0) {
						startDate = endDate;
						continue;
					}
					if (endDate.compareTo(accrDateNeedOtherDto.getProvisionFirstDay()) < 0) {
						startDate = endDate;
						continue;
					}
					if (endDate.compareTo(accrDateNeedOtherDto.geteAccrDate()) > 0) {
						startDate = endDate;
						continue;
					}
					FinFloatAccrualSche finFloatAccrualScheFirst = new FinFloatAccrualSche();
					finFloatAccrualScheFirst.setCashId(accrDateNeedOtherDto.getCashId());
					finFloatAccrualScheFirst.setAccrDate(endDate);
					finFloatAccrualScheFirst.setAccrStrDate(startDate);
					if (flag) {
						finFloatAccrualScheFirst.setAccrStrDate(DateUtil.addDay(startDate, -1));
						flag = false;
					}

					finFloatAccrualScheFirst.setAccrEndDate(endDate);
					finFloatAccrualScheFirst.setValDate(endDate);
					finFloatAccrualScheFirst.setUpdateTime(new Date());
					//插入更新的浮动管理费计提计划
					finFloatAccrualScheMapper.insert(finFloatAccrualScheFirst);
					startDate = endDate;
				}
			}
		}
	}
	
	/**
	 * 按降序向列表添加日期
	 * @param list
	 * @param date
	 */
	private void  addDateToList(List<Date> list, Date date){
	    if(date == null){
	        return;
	    }
	    for(int i=0;i<list.size();i++){
	        if(list.get(i).compareTo(date)==0){
	            return;
	        } 
	        if(list.get(i).compareTo(date)>0){
	            list.add(i,date);
	            return;
            }
	    }
	    list.add(date);
	}
	
}
