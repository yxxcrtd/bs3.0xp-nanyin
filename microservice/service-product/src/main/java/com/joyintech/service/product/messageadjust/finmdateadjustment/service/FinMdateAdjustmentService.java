/**
 * 
 */
package com.joyintech.service.product.messageadjust.finmdateadjustment.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.FinCashPayRuleMapper;
import com.joyintech.tams.common.base.dao.FinCashPlanMapper;
import com.joyintech.tams.common.base.dao.FinFloatAccrualScheMapper;
import com.joyintech.tams.common.base.dao.FinFloatCountingRuleMapper;
import com.joyintech.tams.common.base.dao.FinOpenPlanMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;
import com.joyintech.tams.common.base.entity.FinFloatCountingRule;
import com.joyintech.tams.common.base.entity.FinFloatCountingRuleKey;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.PrdMdateAdjust;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.common.dao.FinProductAnapleroticMapper;
import com.joyintech.service.product.common.dto.BussFinProductFeeInfoDto;
import com.joyintech.service.product.common.dto.BussFinProductOpenPlanDto;
import com.joyintech.service.product.common.dto.BussFinProductPayPlanDto;
import com.joyintech.service.product.common.dto.FinProductAnapleroticInfoDto;
import com.joyintech.service.product.common.dto.FinProductAnapleroticPlanDto;
import com.joyintech.service.product.common.dto.FinProductCommonInfoDto;
import com.joyintech.service.product.messageadjust.finmdateadjustment.dao.FinMdateAdjustmentMapper;
import com.joyintech.service.product.messageadjust.finmdateadjustment.dto.FinMdateAdjustmentDto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;

/**
 * @author zhangliang
 */
@Service("backFinMdateAdjustmentService")
public class FinMdateAdjustmentService extends BaseService<FinMdateAdjustmentDto> {

    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private FinMdateAdjustmentMapper finMdateAdjustmentMapper;
    
    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private FinFloatCountingRuleMapper finFloatCountingRuleMapper;
    
    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private FinFloatAccrualScheMapper finFloatAccrualScheMapper;
    /**
     * 标的类金融产品
     */
    @Autowired
    private FinProductMapper finProductMapper;

    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private IPlatformDate platformDate;

    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private FinOpenPlanMapper finOpenPlanMapper;
    
    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private FinCashPlanMapper finCashPlanMapper;
    
    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private FinCashPayRuleMapper finCashPayRuleMapper;
    
    @Autowired(required = false)
    private FinProductAnapleroticMapper  finProductAnapleroticMapper;

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * 
     * @param page page
     * @param length length
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<FinMdateAdjustmentDto> queryAdjustMdateList(int page, int length,
                                                            Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        pageParam.put("ISSUE_STATUS01", DictCodeListExt1.发行状态_发行成功.getValue());
        pageParam.put("platDate", platformDate.getSysDate());
        return super.queryForPage(page, length,
            () -> finMdateAdjustmentMapper.queryAdjustMdateList(pageParam));
    }

    /**
     * 到期日调整新建产品代码下拉框检索逻辑
     * 
     * @param map 入参
     * @return 产品代码下拉框
     */
    public List<SelectOptionDto> queryAdjustProdIdList(Map<String, Object> map) {
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("ISSUE_STATUS01", DictCodeListExt1.发行状态_发行成功.getValue());
        return finMdateAdjustmentMapper.queryAdjustProdIdList(map);
    }

    /**
     * 根据金融产品代码查询到期日调整信息
     * 
     * @param finprodId 金融产品代码
     * @return 到期日调整信息
     */
    public FinMdateAdjustmentDto queryAdjustInfoByFinprodId(String finprodId) {
        return finMdateAdjustmentMapper.queryAdjustInfoByFinprodId(finprodId);
    }

    /**
     * 查询组合到期日
     * 
     * @param finprodId 金融产品代码
     * @return 组合到期日
     */
    public String queryPortFolioMdateByFinprodId(String finprodId) {
        return finMdateAdjustmentMapper.queryPortFolioMdateByFinprodId(finprodId);
    }

    /**
     * 查询是否有后续交易
     * 
     * @param finprodId 金融产品代码
     * @param mdate 调整后到期日
     * @return 交易个数
     */
    public int queryDealCountByFinprodId(String finprodId, String mdate) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
//        map.put("psSG", DictCodeList.交易方向_产品申购.getValue());
//        map.put("psSH", DictCodeList.交易方向_产品赎回.getValue());
//        map.put("psZJFX", DictCodeList.交易方向_中间付息.getValue());
        map.put("finprodId", finprodId);
        map.put("mdate", mdate);
        map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        return finMdateAdjustmentMapper.queryDealCountByFinprodId(map);
    }

    /**
     *  更新计划相关信息
     * @param finProductCommonInfoDto 更新数据
     * @throws InvocationTargetException 
     * @throws Exception 
     */
    public void updateProdPlanList(FinProductCommonInfoDto finProductCommonInfoDto) throws Exception{
        // 开放计划调整
        BussFinProductOpenPlanDto bussFinProductOpenPlanDto = finProductCommonInfoDto.getBussFinProductOpenPlanDto();
        //update by leipeng 开放式产品有首次开放日，提前终止日小于首次开放日，结果开放计划不变。预期只会生成到终止日的开放计划。
        //if (bussFinProductOpenPlanDto != null && !StringUtil.isEmptyList(bussFinProductOpenPlanDto.getFinOpenPlanList())) {
        if (bussFinProductOpenPlanDto != null ) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cashType1", DictCodeListExt1.现金流一级类型_本金.getValue());
            map.put("cashType2", DictCodeListExt1.现金流二级类型_本金.getValue());
            map.put("finprodId", finProductCommonInfoDto.getFinprodId());
            String cashId = finMdateAdjustmentMapper.queryCashIdByCashType(map);

            // 删除开放计划
            if (cashId != null){
                finMdateAdjustmentMapper.deleteOpenPlanByCashId(cashId);
            }
            // 插入新开放计划
            for (FinOpenPlan finOpenPlan : bussFinProductOpenPlanDto.getFinOpenPlanList()) {
                finOpenPlanMapper.insert(finOpenPlan);
            }
        }

        // 付息计划调整
        BussFinProductPayPlanDto bussFinProductPayPlanDto = finProductCommonInfoDto.getBussFinProductPayPlanDto();
        if (bussFinProductPayPlanDto != null && !StringUtil.isEmptyList(bussFinProductPayPlanDto.getCashPlanList())) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
            map.put("cashType2", DictCodeListExt1.现金流一级类型_利息.getValue());
            map.put("finprodId", finProductCommonInfoDto.getFinprodId());
            String cashId = finMdateAdjustmentMapper.queryCashIdByCashType(map);
            
             // 删除付息计划
            if (cashId != null){
                finMdateAdjustmentMapper.deletePayPlanByCashId(cashId);
            }
            // 插入新付息计划
            for (FinCashPlan finCashPlan : bussFinProductPayPlanDto.getCashPlanList()) {
                finCashPlanMapper.insert(finCashPlan);
            }
        }
       //合版本业绩回补相关 start wangcheng 20191028
      //回补信息调整
        List<FinProductAnapleroticInfoDto> dtoList =  finProductCommonInfoDto.getFinProductAnapleroticTabInfoDtoList();
        if(dtoList != null){
        	 for (FinProductAnapleroticInfoDto finProductAnapleroticInfoDto : dtoList) {
        		 List<FinProductAnapleroticPlanDto>  planDto =  finProductAnapleroticInfoDto.getFinProductAnapleroticPlanList();
					if(!StringUtil.isEmptyList(planDto)){
						 for (FinProductAnapleroticPlanDto finProductAnapleroticPlanDto : planDto) {
							 if(StringUtil.isNotEmpty(finProductAnapleroticPlanDto)){
								 String cashId = finProductAnapleroticPlanDto.getCashId();
								  // 删除费用计划
			                        if (cashId != null){
			                        	finProductAnapleroticMapper.deleteScheByCashId(cashId);
			                        }
			                        for (FinProductAnapleroticPlanDto finCashPlan : planDto){
			                        	FinFloatAccrualSche finFloatAccrualSche = new FinFloatAccrualSche();
			                        	BeanUtil.copyProperties(finFloatAccrualSche,finCashPlan);
			                        	finFloatAccrualSche.setAccrDate(finCashPlan.getAnapleroticDate());
			                        	finFloatAccrualSche.setAccrStrDate(finCashPlan.getAnapleroticCycleStartDate());
			                        	finFloatAccrualSche.setAccrEndDate(finCashPlan.getAnapleroticCycleEndDate());
			                        	finFloatAccrualSche.setValDate(finCashPlan.getValDate());
			                        	finFloatAccrualSche.setCashId(finCashPlan.getCashId());
			                        	finFloatAccrualScheMapper.insert(finFloatAccrualSche);
			                        }
							 }
					}
				}
			}
        }
      //合版本业绩回补相关 end wangcheng 20191028
        FinProduct finProduct = finProductMapper.selectByPrimaryKey(finProductCommonInfoDto.getFinprodId());
        // 付费计划调整
        BussFinProductFeeInfoDto bussFinProductFeeInfoDto = finProductCommonInfoDto.getBussFinProductFeeInfoDto();
        if (bussFinProductFeeInfoDto != null) {
            List<BussFinProductFeeInfoDto> bussFinProductFeeInfoDtoList = bussFinProductFeeInfoDto.getBussFinProductFeeInfoDtoList();
            if (!StringUtil.isEmptyList(bussFinProductFeeInfoDtoList)){
                for (BussFinProductFeeInfoDto feeInfoDto : bussFinProductFeeInfoDtoList){
                    if(StringUtil.equals(DictCodeList.频率_到期一次.getValue(), feeInfoDto.getPayFeeRule())){
                        FinCashPayRule finCashPayRule = new FinCashPayRule();
                        finCashPayRule.setCashId(feeInfoDto.getCashId());
                        finCashPayRule.setEffDate(finProduct.getVdate());
                        finCashPayRule.setFirstPayDate(finProduct.getMdate());
                        finCashPayRuleMapper.updateByPrimaryKeySelective(finCashPayRule);
                    }
                    if (!StringUtil.isEmptyList(feeInfoDto.getFinCashPlanList())){
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
                   //     map.put("finprodId", finProductCommonInfoDto.getFinprodId());
                        map.put("finprodId", feeInfoDto.getFinprodId());
                        map.put("feeId", feeInfoDto.getFeeId());
                        String cashId = finMdateAdjustmentMapper.queryCashIdByCashType(map);
                        
                        // 删除费用计划
                        if (cashId != null){
                            finMdateAdjustmentMapper.deletePayPlanByCashId(cashId);
                        }
                        for (FinCashPlan finCashPlan : feeInfoDto.getFinCashPlanList()){
                            finCashPlanMapper.insert(finCashPlan);
                        }
                    }
                }
            }
        }
        
        //计提计划调整
        PrdMdateAdjust dto = finProductCommonInfoDto.getPrdMdateAdjust();
        List<PrdMdateAdjust> prdMdateAdjustList=dto.getSelectedItem();//有现金流代码和费用代码
        //根据现金流代码和费用代码更改计提计划
        for (PrdMdateAdjust prdMdateAdjust : prdMdateAdjustList) {
        	String cashId_accr=prdMdateAdjust.getCashId();
        	Date new_eAccrDate = prdMdateAdjust.geteAccrDate();
			//获取计提计划列表
			List<FinFloatAccrualSche> accrPlanList = finMdateAdjustmentMapper.queryAccrPlanByCashId(cashId_accr);
			
			FinFloatCountingRuleKey accrRuleKey= new FinFloatCountingRuleKey();
			accrRuleKey.setCashId(cashId_accr);
			accrRuleKey.setEffDate(finProduct.getVdate());
			//获取计提规则表
			FinFloatCountingRule countRule = finFloatCountingRuleMapper.selectByPrimaryKey(accrRuleKey);
			
			//新的计提计划
			List<FinFloatAccrualSche> scheList = new ArrayList<FinFloatAccrualSche>();
			
			//1.固定费用（每日计提）
			if(!StringUtil.equals(prdMdateAdjust.getFeeType(), DictCodeList.费用品种类型_浮动管理费.getValue())){
				if(DateUtil.compareTo(new_eAccrDate,countRule.geteAccrDate())<0){//向前调整
					for (FinFloatAccrualSche Sche : accrPlanList) {
                        if (Sche.getAccrDate().compareTo(new_eAccrDate) > 0) {
                        	//删除多余的一部分计提计划
                        	finFloatAccrualScheMapper.deleteByPrimaryKey(Sche);
                        }
                    }
				}else if(DateUtil.compareTo(new_eAccrDate,countRule.geteAccrDate())>0){//向后调整
					Date startDate = countRule.geteAccrDate();//旧的计提结束日
					 //添加一部分新的计提计划
                     while (DateUtil.compareDate(startDate,
                    		 DateUtil.addDay(new_eAccrDate,-1)) < 1) {
                     	FinFloatAccrualSche finFloatAccrualSche = new FinFloatAccrualSche();
                         //BeanUtil.copyProperties(finFloatAccrualSche, finProductCommonInfoDto);
                     	//add by zlp 判断计提结束日不为空 bug 326
                     	if(startDate!=null) {
                     		 startDate = DateUtil.addDay(startDate, 1);
                     		finFloatAccrualSche.setValDate(startDate);
                     	 }
                         finFloatAccrualSche.setCashId(cashId_accr);
                         finFloatAccrualSche.setAccrDate(startDate);
                         finFloatAccrualSche.setAccrStrDate(startDate);
                         finFloatAccrualSche.setAccrEndDate(startDate);
                         
                         finFloatAccrualScheMapper.insert(finFloatAccrualSche);
                     }
				}
			}else{ //2.浮动管理费（区间计提）
				// 封闭式产品
                if (StringUtil.equals(DictCodeList.开放类型_封闭式.getValue(),finProduct.getOperationType())){
                	if(accrPlanList!=null&&accrPlanList.size()>0)//update zhangweiqi 浮动管理费区间计提是否存在 2019-3-20
                	finFloatAccrualScheMapper.deleteByPrimaryKey(accrPlanList.get(0));
                	FinFloatAccrualSche finFloatAccrualSche = new FinFloatAccrualSche();
                    finFloatAccrualSche.setCashId(cashId_accr);
                    finFloatAccrualSche.setAccrDate(prdMdateAdjust.geteAccrDate());
                    finFloatAccrualSche.setAccrStrDate(DateUtil.addDay(finProduct.getVdate(), -1));
                    finFloatAccrualSche.setAccrEndDate(finProduct.getMdate());
                    finFloatAccrualScheMapper.insert(finFloatAccrualSche);
                    countRule.setfAccrDate(new_eAccrDate);
                }
                //开放式产品
                else if(StringUtil.equals(DictCodeList.开放类型_开放式.getValue(),finProduct.getOperationType())
                		&&StringUtil.isNotEmpty(accrPlanList)){
                	if(DateUtil.compareTo(new_eAccrDate,countRule.geteAccrDate())<0){//向前调整
                		int count=0;
                		FinFloatAccrualSche finFloatAccrualSche = new FinFloatAccrualSche();
                		for (FinFloatAccrualSche Sche : accrPlanList) {
                            if (Sche.getAccrDate().compareTo(new_eAccrDate)>= 0) {
                            	count++;
                            	BeanUtil.copyProperties(finFloatAccrualSche,Sche);
                            	finFloatAccrualScheMapper.deleteByPrimaryKey(Sche);
                            	if(count==1){
                            		finFloatAccrualSche.setAccrDate(new_eAccrDate);
                                	finFloatAccrualSche.setAccrEndDate(new_eAccrDate);
                                	finFloatAccrualScheMapper.insert(finFloatAccrualSche);
                            	}
                            }
                        }
                		
                	}else if(DateUtil.compareTo(new_eAccrDate,countRule.geteAccrDate())>0){//向后调整
                		scheList.addAll(accrPlanList);
                        // 获取最后一条计划的计提日
//                		//update zhangweiqi 浮动管理费区间计提是否存在 2019-3-20
//                        Date startDate =accrPlanList!=null&&accrPlanList.size()>0? accrPlanList.get(accrPlanList.size() - 1).getAccrDate():finProduct.getMdate();
//                        int month=1;
//                    	if(StringUtil.equals(DictCodeList.频率_季.getValue(), countRule.getIntervalRule())){
//                    		month=3;
//                    	}else if(StringUtil.equals(DictCodeList.频率_半年.getValue(), countRule.getIntervalRule())){
//                    		month=6;
//                    	}else if(StringUtil.equals(DictCodeList.频率_年.getValue(), countRule.getIntervalRule())){
//                    		month=12;
//                    	}
//                        while (
//                         		(new_eAccrDate.getYear()-startDate.getYear())*12+
//                         		(new_eAccrDate.getMonth()-startDate.getMonth())>=month) {//update zhangweiqi 计提结束日
//                    		 FinFloatAccrualSche finFloatAccrualSche = new FinFloatAccrualSche();
//                             finFloatAccrualSche.setCashId(cashId_accr);
//                             finFloatAccrualSche.setAccrDate(DateUtil.addMonthDate(startDate, month));
//                             finFloatAccrualSche.setAccrStrDate(startDate);
//                             finFloatAccrualSche.setAccrEndDate(DateUtil.addMonthDate(startDate, month));
//                             finFloatAccrualSche.setValDate(DateUtil.addMonthDate(startDate, month));
//                             finFloatAccrualScheMapper.insert(finFloatAccrualSche);
//                             startDate = DateUtil.addMonthDate(startDate, month);
//                         }
//                         //最后一个区间
//                         if(DateUtil.compareDate(new_eAccrDate,startDate)>0){
//                        	 FinFloatAccrualSche finFloatAccrualSche = new FinFloatAccrualSche();
//                             finFloatAccrualSche.setCashId(cashId_accr);
//                             finFloatAccrualSche.setAccrDate(new_eAccrDate);//计提结束日
//                             finFloatAccrualSche.setAccrStrDate(startDate);
//                             finFloatAccrualSche.setAccrEndDate(new_eAccrDate);//计提结束日
//                             finFloatAccrualSche.setValDate(new_eAccrDate);//计提结束日
//                             finFloatAccrualScheMapper.insert(finFloatAccrualSche);
//                         }
                		 List<Date> busiDate = new ArrayList<Date>();

                         BussFinProductOpenPlanDto bussFinProductOpenPlanRedDto = finProductCommonInfoDto.getBussFinProductOpenPlanRedDto();
                         if(StringUtil.equals(DictCodeList.收益类型_净值型.getValue(), finProduct.getProfitType())){
                             if(bussFinProductOpenPlanDto!=null && !StringUtil.isEmptyList(bussFinProductOpenPlanDto.getFinOpenPlanList())){
                                 for(FinOpenPlan plan:bussFinProductOpenPlanDto.getFinOpenPlanList()){
                                     addDateToList(busiDate,plan.getOpenNdate());
                                 }
                             }
                             if(bussFinProductOpenPlanRedDto!=null && !StringUtil.isEmptyList(bussFinProductOpenPlanRedDto.getFinOpenPlanList())){
                                 for(FinOpenPlan plan:bussFinProductOpenPlanRedDto.getFinOpenPlanList()){
                                     addDateToList(busiDate,plan.getOpenNdate());
                                 }
                             }
                         }else if(StringUtil.equals(DictCodeList.收益类型_货币型.getValue(), finProduct.getProfitType())){
                             if(bussFinProductOpenPlanDto!=null && !StringUtil.isEmptyList(bussFinProductOpenPlanDto.getFinOpenPlanList())){
                                 for(FinOpenPlan plan:bussFinProductOpenPlanDto.getFinOpenPlanList()){
                                     
                                     addDateToList(busiDate,plan.getOpenDateEnd());
                                 }
                             }
                             if(bussFinProductOpenPlanRedDto!=null && !StringUtil.isEmptyList(bussFinProductOpenPlanRedDto.getFinOpenPlanList())){
                                 for(FinOpenPlan plan:bussFinProductOpenPlanRedDto.getFinOpenPlanList()){
                                     addDateToList(busiDate,plan.getOpenDateEnd());
                                 }
                             }
                         }
                         
                         addDateToList(busiDate,finProduct.getVdate());
                         addDateToList(busiDate,finProduct.getMdate());
                         addDateToList(busiDate,countRule.getfAccrDate());
                         //modify by wm 由于是展期，故计提结束日为到new_eAccrDate
                         //addDateToList(busiDate,countRule.geteAccrDate());
                         addDateToList(busiDate,new_eAccrDate);
                         Date startDate = busiDate.get(0);
                         boolean flag = true;
                         for(Date endDate: busiDate){
                             if(startDate.compareTo(endDate)==0){
                                 startDate = endDate;
                                 continue;
                             }
                             if(endDate.compareTo(countRule.getfAccrDate())<0){
                                 startDate = endDate;
                                 continue;
                             }
                             if(endDate.compareTo(new_eAccrDate)>0){
                                 startDate = endDate;
                                 continue;
                             }
                             FinFloatAccrualSche finFloatAccrualScheFirst = new FinFloatAccrualSche();
                             BeanUtil.copyProperties(finFloatAccrualScheFirst, finProduct);
                             finFloatAccrualScheFirst.setCashId(cashId_accr);
                             finFloatAccrualScheFirst.setAccrDate(endDate);
                             finFloatAccrualScheFirst.setAccrStrDate(startDate);
                             if(flag){
                            	 finFloatAccrualScheFirst.setAccrStrDate(DateUtil.addDay(startDate, -1));
                            	 flag = false;
                            }
                             finFloatAccrualScheFirst.setAccrEndDate(endDate);
                             finFloatAccrualScheFirst.setValDate(endDate);
                             
                             //update by leipeng 【到期日调整】：产品多次调整数据排序不正确:浮动管理费向后调整主键冲突
                             finFloatAccrualScheMapper.deleteByPrimaryKey(finFloatAccrualScheFirst);
                             
                             finFloatAccrualScheMapper.insert(finFloatAccrualScheFirst);
                             startDate = endDate;
                         }
                		
                	}
                }
			 }
			
			//更新计提规则表
			countRule.seteAccrDate(new_eAccrDate);
			finFloatCountingRuleMapper.updateByPrimaryKeySelective(countRule);
		   }
        }
    /**
     * 
     * 按降序向列表添加 日期 
     * @param list 日期列表
     * @param date 日期
     * @see
     * @since 1.0
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
    /**
     * 查询关联影响列表信息
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public List<FinMdateAdjustmentDto> queryRelateInfoList(String finprodId) {
    	 Map<String, Object> map = new HashMap<String, Object>();
         map.put("finprodId", finprodId);
         map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());
         map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
         map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
         //关联影响的费用列表，应该不展示一次性费用。
         map.put("chargeType", DictCodeList.收费类型_不规则费用.getValue());
        return finMdateAdjustmentMapper.queryRelateInfoList(map);
    }

    /**
     * 查询产品的最大调整批次
     * @param finprodId
     * @return
     */
	public int getMaxAdjustNum(String finprodId) {
		return finMdateAdjustmentMapper.getMaxAdjustNum(finprodId);
	}

	/**
	 * 查询是否是存续产品
	 * @param finprodId
	 * @return
	 */
	public FinMdateAdjustmentDto queryProductByProdId(String finprodId) {
		Map<String, Object> pageParam=new HashMap<String,Object>();
		pageParam.put("finprodId",finprodId);
		pageParam.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        pageParam.put("ISSUE_STATUS01", DictCodeListExt1.发行状态_发行成功.getValue());
        pageParam.put("platDate", platformDate.getSysDate());
		return finMdateAdjustmentMapper.queryProductByProdId(pageParam);
	}
	
	/**
	 * 母产品代码查询分层子产品信息
	 * <功能详细描述>
	 * @param finprodId
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<FinProduct> queryLayeredChildProductByProdid(String finprodId){
	    return finMdateAdjustmentMapper.queryLayeredChildProductByProdid(finprodId);
	}
}
