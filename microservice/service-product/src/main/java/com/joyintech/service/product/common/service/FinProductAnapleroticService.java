/*
 * 文件名：FinProductAnapleroticService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：LiJun
 * 修改时间：2019年4月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.common.dao.FinProductAnapleroticMapper;
import com.joyintech.service.product.common.dto.*;
import com.joyintech.service.product.productelement.build.dao.FinProductBuildMapper;
import com.joyintech.tams.common.base.dao.FinBaseRuleInfoMapper;
import com.joyintech.tams.common.base.dao.FinCashInfoMapper;
import com.joyintech.tams.common.base.dao.FinFloatAccrualScheMapper;
import com.joyintech.tams.common.base.dao.FinFloatCalcRuleDetailMapper;
import com.joyintech.tams.common.base.dao.FinFloatCalcRuleMapper;
import com.joyintech.tams.common.base.dao.MstIndexInfoMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.base.core.BaseFinAnapleroticCalcRuleService;
import com.joyintech.tams.component.base.core.BaseFinProductCashCountingRuleService;
import com.joyintech.tams.component.base.core.BaseFinProductCashInfoService;
import com.joyintech.tams.component.base.core.dto.BaseFinProductCashCountingRuleDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


/**
 * 业绩回补
 * 〈功能详细描述〉
 * @author LiJun
 * @version 1.0 2019年4月25日
 * @see FinProductAnapleroticService
 * @since 1.0
 */
@Service("finProductAnapleroticService")
public class FinProductAnapleroticService extends BaseService<FinProductAnapleroticInfoDto>{
    @Autowired
    private FinProductAnapleroticMapper finProductAnapleroticMapper;
    @Autowired
    private BaseFinAnapleroticCalcRuleService baseFinAnapleroticCalcRuleService;
    @Autowired
    private BaseFinProductCashInfoService baseFinProductCashInfoService;
    @Autowired
    private BaseFinProductCashCountingRuleService baseFinProductCashCountingRuleService;
    @Autowired
    private FinFloatCalcRuleDetailMapper finFloatCalcRuleDetailMapper;
    @Autowired
    private FinFloatAccrualScheMapper finFloatAccrualScheMapper;
    @Autowired
    private FinProductBuildMapper finProductBuildMapper;
    @Autowired
    private FinCashInfoMapper finCashInfoMapper;
    @Autowired
    private FinFloatCalcRuleMapper finFloatCalcRuleMapper;
//    @Autowired
//    private  FinProductFloatFeeService finProductFloatFeeService;
    @Autowired
    private FinProductOpenPlanService finProductOpenPlanService; 
    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private MstIndexInfoMapper mstIndexInfoMapper;
    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private FinBaseRuleInfoMapper finBaseRuleInfoMapper;
    
    
    public Page<FinProductAnapleroticInfoDto> selectForPage(int page, int length, Map<String, Object> param){
		// 获取组合代码
		String portfolioId = (String) param.get("portfolioId");
		String[] portfolioIdArr = null;
		if (StringUtil.isNotEmpty(portfolioId)) {
			portfolioIdArr = portfolioId.split(",");
		}
		param.put("finprodIds", portfolioIdArr);
        param.put("cashType1", DictCodeListExt1.现金流一级类型_回补.getValue());
        param.put("cashType2", DictCodeListExt1.现金流二级类型_回补.getValue());
        param.put("finprodType", BussinessDictCodeList.现金流业务类型_金融产品.getValue());
        param.put("isCalcY", BussinessDictCodeList.是否_是.getValue());
        return  super.queryForPage(page, length, () -> finProductAnapleroticMapper.selectForPage(param));
    }
    
    public List<FinProductAnapleroticInfoDto> selectForlist(Map<String, Object> param){
		// 获取组合代码
		String portfolioId = (String) param.get("portfolioId");
		String[] portfolioIdArr = null;
		if (StringUtil.isNotEmpty(portfolioId)) {
			portfolioIdArr = portfolioId.split(",");
		}
		param.put("finprodIds", portfolioIdArr);
        param.put("cashType1", DictCodeListExt1.现金流一级类型_回补.getValue());
        param.put("cashType2", DictCodeListExt1.现金流二级类型_回补.getValue());
        param.put("finprodType", BussinessDictCodeList.现金流业务类型_金融产品.getValue());
        param.put("isCalcY", BussinessDictCodeList.是否_是.getValue());
        
        param.put("baseRuleType03", DictCodeListExt1.业绩基准类型_业绩回补.getValue());
        
        return  finProductAnapleroticMapper.selectForPage(param);
    }
    
    public List<FinProductAnapleroticInfoDto> queryAnapleroticInfoById(Map<String,Object> param){
        param.put("cashType1", DictCodeListExt1.现金流一级类型_回补.getValue());
        param.put("cashType2", DictCodeListExt1.现金流二级类型_回补.getValue());
        param.put("finprodType", BussinessDictCodeList.现金流业务类型_金融产品.getValue());
        param.put("isCalcY", BussinessDictCodeList.是否_是.getValue());
        
        param.put("baseRuleType03", DictCodeListExt1.业绩基准类型_业绩回补.getValue());
        param.put("dealMode11", DictCodeList.处理模式_分档.getValue());
        return  finProductAnapleroticMapper.queryAnapleroticInfoById(param);
    }
    /**
     * 查询分档产品回补信息
     * @param param
     * @return
     */
    public List<FinProductAnapleroticInfoDto> queryAnapleroticInfoByProdStyle(Map<String,Object> param){
        param.put("cashType1", DictCodeListExt1.现金流一级类型_回补.getValue());
        param.put("cashType2", DictCodeListExt1.现金流二级类型_回补.getValue());
        param.put("finprodType", BussinessDictCodeList.现金流业务类型_金融产品.getValue());
        param.put("isCalcY", BussinessDictCodeList.是否_是.getValue());
        
        param.put("baseRuleType03", DictCodeListExt1.业绩基准类型_业绩回补.getValue());
        return  finProductAnapleroticMapper.queryAnapleroticInfoByProdStyle(param);
    }
    /**
     * 现金流id 生效日删除业绩回补信息
     * <功能详细描述>
     * @param finprodId
     * @see
     * @since 1.0
     */
    public void deleteByPrimaryKey(Map<String,Object> param){
        String effDateStr = (String)param.get("effDateStr");
        String anapleroticId = (String)param.get("anapleroticId");
        String finprodId = (String)param.get("finprodId");
        Date effDate = null;
//        String flag =null;
        if(StringUtil.isNotEmpty(effDateStr)){
            effDate = DateUtil.parse(effDateStr);
        }
//        FinAnapleroticCalcRule key = new FinAnapleroticCalcRule();
//        key.setCashId(anapleroticId);
//        key.setEffDate(effDate);
//        List<FinAnapleroticCalcRule> list = baseFinAnapleroticCalcRuleService.selectListByPrimaryKey(key);
//        //如果回补计算规则只有一条 则删除现金流信息
//        if(!StringUtil.isEmptyList(list) && list.size() == 1 ){
//            flag = BussinessDictCodeList.是否_是.getValue();
//        }
        deleteProductAnapleroticInfoById(anapleroticId,effDate,BussinessDictCodeList.是否_是.getValue(),finprodId);
    }
    
    /**
     * 修改业绩现金流信息
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void updateAnapleroticInfo(FinProductAnapleroticInfoDto dto){
        //先删除
        this.deleteProductAnapleroticInfoById(dto.getAnapleroticId(), dto.getOriEffDate(),null,null);
        
        //生成回补计划
        boolean isHighOpen = false;
        genAnapleroticPlanList(dto, isHighOpen);
        //在插入
        if(StringUtil.isNotEmpty(dto.getProdStyle())){
            dto.setFinprodId(dto.getProdStyle());
        }
        //删除业绩基准信息
        finProductAnapleroticMapper.deleteAnapleroticBaseByFinprodId(dto.getFinprodId(), dto.getEffDate());
        //在插入
        this.insertProductAnapleroticInfo(dto, null, isHighOpen);
    }
    /**
     * 修改业绩现金流信息
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void insertAnapleroticInfo(FinProductAnapleroticInfoDto dto){
        //先判断现金流信息
    	 String finprodId = "";
         if(StringUtil.isNotEmpty(dto.getProdStyle())){
        	 finprodId = dto.getProdStyle();
         }
         else{
        	 finprodId = dto.getFinprodId();
         }
        List<FinProductAnapleroticInfoDto>  list = queryAnapleroticInfoByFinprodId(finprodId);
       
        //如果为空得先生生成现金流信息
        if(StringUtil.isEmptyList(list)){
            FinProductCommonInfoDto prod = this.getProdInfoByFinprodId(dto.getFinprodId());
            String anaplerotic_int = CommonUtil.generateCashFlowCode(
            		finprodId,
                DictCodeListExt1.现金流业务类型_金融产品.getValue(), prod.getBranch(),
                DictCodeListExt1.现金流二级类型_回补.getValue());
            FinCashInfo finCashInfo_int = new FinCashInfo();
            finCashInfo_int.setCashId(anaplerotic_int);
            finCashInfo_int.setCashType1(DictCodeListExt1.现金流一级类型_回补.getValue());
            finCashInfo_int.setCashType2(DictCodeListExt1.现金流二级类型_回补.getValue());
            finCashInfo_int.setCcy(prod.getCcy());
            finCashInfo_int.setFinprodId(finprodId);
            finCashInfo_int.setFinprodType(BussinessDictCodeList.现金流业务类型_金融产品.getValue());
            finCashInfo_int.setBranch(prod.getBranch());
            finCashInfo_int.setIsCalc(BussinessDictCodeList.是否_是.getValue());
//            if(StringUtil.equals(dto.getIsAnaplerotic(), DictCodeListExt1.是否_是.getValue())){
//            	 finCashInfoMapper.insert(finCashInfo_int);
//            }
            finCashInfoMapper.insert(finCashInfo_int);
            dto.setAnapleroticId(anaplerotic_int); 
        } else {
            dto.setAnapleroticId(list.get(0).getAnapleroticId()); 
        }
        //生成回补计划
        //是否高频开放
        boolean isHighOpen = false;
        genAnapleroticPlanList(dto, isHighOpen);
        //在插入
        dto.setFinprodId(finprodId);
        this.insertProductAnapleroticInfo(dto, null,isHighOpen);
    }
    private FinProductCommonInfoDto getProdInfoByFinprodId(String finprodId){
        Map<String,Object> map = new HashMap<>();
        map.put("finprodId", finprodId);
        map.put("cashType1", BussinessDictCodeList.现金流一级类型_利息.getValue());
        map.put("cashType2", BussinessDictCodeList.现金流二级类型_利息.getValue());
        map.put("PS_ZJSX", BussinessDictCodeList.交易方向_中间付息.getValue());
        map.put("FINPROD_TYPE_LCCP", BussinessDictCodeList.金融产品类型_投管用_理财产品.getValue());
        map.put("YES_NO_N", BussinessDictCodeList.是否_否.getValue());
        map.put("tradeStatus", BussinessDictCodeList.交易状态_初始.getValue());
        map.put("finprodType2", BussinessDictCodeList.金融产品类型_理财产品.getValue());
        return finProductBuildMapper.queryProdInfoByFinprodId(map);
    }
    /**
     * 回补计划生成
     * @param dto 参数
     */
    private void genAnapleroticPlanList(FinProductAnapleroticInfoDto dto, boolean isHighOpen){

    	List<FinProductAnapleroticPlanDto> anapleroticPlanList = new ArrayList<FinProductAnapleroticPlanDto>();
		
		FinProductCommonInfoDto prod = new FinProductCommonInfoDto();
		if(StringUtil.isNotEmpty(dto.getProdStyle())){
			prod = this.getProdInfoByProdStyle(dto.getProdStyle());
		}
		else{
			prod = this.getProdInfoByFinprodId(dto.getFinprodId());
		}
		List<Date> busiDate = new ArrayList<Date>();
		if(StringUtil.equals(DictCodeList.开放类型_开放式.getValue(), prod.getOperationType())){
			List<FinOpenPlan> openPlanList = finProductOpenPlanService.queryOpenPlanListByFinprodId(dto.getFinprodId());
			if(StringUtil.isEmptyList(openPlanList)){
				//查询不到开放计划，即该产品为高频开放
				isHighOpen = true;
				return;
			}
			if(StringUtil.equals(DictCodeList.收益类型_净值型.getValue(), prod.getProfitType())){
				 for (FinOpenPlan finOpenPlan : openPlanList) {
					 this.addDateToList(busiDate,finOpenPlan.getOpenNdate());
				 }
			}
			else if(StringUtil.equals(DictCodeList.收益类型_货币型.getValue(), prod.getProfitType())){
				for (FinOpenPlan finOpenPlan : openPlanList) {
	                this.addDateToList(busiDate,finOpenPlan.getOpenDateEnd());
				 }
			}
	        addDateToList(busiDate,dto.getFirstAnapleroticDate());
	        addDateToList(busiDate,dto.getAnapleroticEndDate());
	        addDateToList(busiDate,dto.getFirstAnapleroticCycleBegDate());
	        Date startDate = busiDate.get(0);
	        for(Date endDate: busiDate){
	            if(startDate.compareTo(endDate)==0){
	                startDate = endDate;
	                continue;
	            }
	            if(endDate.compareTo(dto.getFirstAnapleroticDate())<0){
	                startDate = endDate;
	                continue;
	            }
	            if(endDate.compareTo(dto.getAnapleroticEndDate())>0){
	                startDate = endDate;
	                continue;
	            }
	            FinProductAnapleroticPlanDto finProductAnapleroticPlanDto = new FinProductAnapleroticPlanDto();
//	            finProductAnapleroticPlanDto.setCashId(cashId_floatFee);
	            finProductAnapleroticPlanDto.setAnapleroticDate(endDate);
	            //现金流计提计划.计提开始日 = 画面回补计划列表.回补周期开始日
	            finProductAnapleroticPlanDto.setAnapleroticCycleStartDate(startDate);
	            //现金流计提计划.计提结束日 = 画面回补计划列表.回补周期结束日
	            finProductAnapleroticPlanDto.setAnapleroticCycleEndDate(endDate);
	            //现金流计提计划.调整前结束日 = 画面回补计划列表.回补周期结束日
	            finProductAnapleroticPlanDto.setAnapleroticCycleEndDate(endDate);
	            anapleroticPlanList.add(finProductAnapleroticPlanDto);
	            startDate = endDate;
	        }
		}
		else if(StringUtil.equals(DictCodeList.开放类型_封闭式.getValue(), prod.getOperationType())){
			FinProductAnapleroticPlanDto finProductAnapleroticPlanDto = new FinProductAnapleroticPlanDto();
            finProductAnapleroticPlanDto.setAnapleroticDate(dto.getFirstAnapleroticDate());
            finProductAnapleroticPlanDto.setAnapleroticCycleStartDate(dto.getFirstAnapleroticCycleBegDate());
            finProductAnapleroticPlanDto.setAnapleroticCycleEndDate(dto.getAnapleroticEndDate());
            finProductAnapleroticPlanDto.setAnapleroticCycleEndDate(dto.getAnapleroticEndDate());
            anapleroticPlanList.add(finProductAnapleroticPlanDto);
		}
		dto.setFinProductAnapleroticPlanList(anapleroticPlanList);
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
     * 产品代码查询业绩现金流信息
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public List<FinProductAnapleroticInfoDto> queryAnapleroticInfoByFinprodId(String finprodId){
        Map<String, Object> param = new HashMap<>();
        param.put("finprodId", finprodId);
        param.put("cashType1", DictCodeListExt1.现金流一级类型_回补.getValue());
        param.put("cashType2", DictCodeListExt1.现金流二级类型_回补.getValue());
        param.put("finprodType", BussinessDictCodeList.现金流业务类型_金融产品.getValue());
        param.put("isCalcY", BussinessDictCodeList.是否_是.getValue());
        return finProductAnapleroticMapper.queryAnapleroticInfoByFinprodId(param);
    }
    public List<SelectOptionDto> queryProdIdInfo(Map<String,Object> param){
        param.put("finprodType1", BussinessDictCodeList.金融产品类型_理财产品.getValue());
        param.put("finprodType2", BussinessDictCodeList.金融产品类型_理财产品.getValue());
        param.put("notProfitType", BussinessDictCodeList.收益类型_预期收益.getValue());
        //平台日
        param.put("currentDate", DateUtil.platDateFromCache());
        return finProductAnapleroticMapper.queryProdIdInfo(param);
    }
    
    public FinProductAnapleroticInfoDto queryAnapAndProdInfo(String finprodId,String dealMode){
    	Map<String,Object> map = new HashMap<String,Object>();
    	List<FinProductAnapleroticInfoDto> lists = new ArrayList<FinProductAnapleroticInfoDto>();
    	map.put("finprodId",finprodId);
    	if(StringUtil.isNotEmpty(dealMode)&&StringUtil.equals(dealMode, DictCodeList.处理模式_分档.getValue())){
    		lists = this.queryAnapleroticInfoByProdStyle(map);
    	}
    	else{
        	lists = this.queryAnapleroticInfoById(map);
    	}
    	if(lists != null && lists.size()>0){
    		if(StringUtil.isEmpty(lists.get(0).getFinProductAnapleroticInfoDto().getCycleTdayRule())&&StringUtil.isEmpty(lists.get(0).getFinProductAnapleroticInfoDto().getAnapleroticFrequency())){
    			lists.get(0).getFinProductAnapleroticInfoDto().setIsList("01");
    			//lists.get(0).getFinProductAnapleroticInfoDto().setIsAnapleroticList("N");
    		}
    		return lists.get(0).getFinProductAnapleroticInfoDto();
    	}else{
    			FinProductCommonInfoDto prod = new FinProductCommonInfoDto();
    			if(StringUtil.isNotEmpty(dealMode)&&StringUtil.equals(dealMode, DictCodeList.处理模式_分档.getValue())){
    				prod = this.getProdInfoByProdStyle(finprodId);
    				finprodId = prod.getpFinprodId();
    			}
    			else{
    				prod = this.getProdInfoByFinprodId(finprodId);
    			}

    	        FinProductAnapleroticInfoDto result = new FinProductAnapleroticInfoDto();
    	        //如果是封闭式产品
    	        if(StringUtil.equals(prod.getOperationType(), DictCodeList.开放类型_封闭式.getValue())){
    	        	Calendar calendar = Calendar.getInstance();
        	        calendar.setTime(prod.getMdate());
        	        calendar.add(Calendar.DATE, -1);
        	        DateUtil.addDay(prod.getMdate(), -1);
        	        //delete by wm 与浮动管理费无关 start 2019-12-11
//        	        FinProductFloatFeeDto   finProductFloatFee   =  finProductFloatFeeService.queryFloatFeeInfoByFinprodId(finprodId,null);
//        	        if(StringUtil.isNotEmpty(finProductFloatFee)){
//        	        	result.setFirstAnapleroticDate(finProductFloatFee.getProvisionFirstDay());
//        	        	result.setmDate(finProductFloatFee.getProvisionFirstDay());
//        	        }
        	        //delete by wm 与浮动管理费无关 end 2019-12-11
    	        	  result.setFirstAnapleroticDate(calendar.getTime());
    	        	  result.setAnapleroticEndDate(calendar.getTime());
    	        	  result.setmDate(calendar.getTime());
    	        	  result.setOperateType(prod.getOperationType());
    	        	  
    	        }else{
    	        	try {
						List<BussFinProductOpenPlanDto> list = finProductOpenPlanService.selectOpenPlanInfoByFinprodId(finprodId);
						if(list != null && list.size()>0){
							for (BussFinProductOpenPlanDto bussFinProductOpenPlanDto : list) {
								result.setOperateType(prod.getOperationType());
								//如果开放模式是高频
								if(StringUtil.equals(bussFinProductOpenPlanDto.getOpenMode(),DictCodeList.开放模式_高频开放.getValue())){
									result.setIsList("01");
									result.setFirstAnapleroticDate(DateUtil.platDateFromCache());
									result.setAnapleroticEndDate(prod.getMdate());
								}else{
									List<FinOpenPlan> openPlan = finProductOpenPlanService.queryOpenPlanListByFinprodId(finprodId);
									if(openPlan != null && openPlan.size()>0){
										result.setFirstAnapleroticDate(openPlan.get(0).getOpenNdate());
										result.setmDate(openPlan.get(openPlan.size()-1).getOpenNdate());
										result.setAnapleroticEndDate(openPlan.get(openPlan.size()-1).getOpenNdate());
									}
								}
							}
						}
						//result.setEffDate(prod.getVdate());
					} catch (Exception e) {
						e.printStackTrace();
					}
    	        	
    	        	 
    	        }
    	        //生效日默认为平台日+1,首次周期开始日为生效日-1
//    	        result.setEffDate(prod.getVdate());
//    	        result.setFirstAnapleroticCycleBegDate(DateUtil.addDay(prod.getVdate(), -1));
    	        result.setEffDate(DateUtil.addDay(DateUtil.platDateFromCache(),1));
    	        result.setFirstAnapleroticCycleBegDate(DateUtil.platDateFromCache());
    	        //result.setmDate(prod.getMdate());
    	        result.setCalendarId(prod.getCalendarId());
    	        //result.setFirstAnapleroticDate(prod.getVdate());
    	        result.setFinprodAbbr(prod.getFinprodAbbr());
    	        result.setIsAnapleroticList("N");
    	        lists.add(result);
    	        return lists.get(0);
    	}
      
    }
    /**
     * 产品代码删除业绩回补信息(产品要素调用)
     * <功能详细描述>
     * @param finprodId
     * @see
     * @since 1.0
     */
    public void deleteAnapleroticInfoByFinprodId(String finprodId){
        //产品代码查询业绩回补现金流id
        List<FinProductAnapleroticInfoDto> cashIds = this.queryAnapleroticInfoByFinprodId(finprodId);
        if(!StringUtil.isEmptyList(cashIds)){
            //循环删除业绩回补信息
            cashIds.forEach(cashId -> this.deleteProductAnapleroticInfoById(cashId.getAnapleroticId(),cashId.getEffDate(),BussinessDictCodeList.是否_是.getValue(),finprodId));
        }
    }
    
 
    /**
     * 删除回补一些信息
     * <功能详细描述>
     * @param anapleroticId
     * @param effDateStr
     * @param flag
     * @see
     * @since 1.0
     */
    public void deleteProductAnapleroticInfoById(String anapleroticId,Date effDate,String flag,String finprodId){
        
        //判断删除现金流信息
        if(StringUtil.equals(BussinessDictCodeList.是否_是.getValue(),flag)){
            FinCashInfo finCashInfo = new FinCashInfo();
            finCashInfo.setCashId(anapleroticId);
            baseFinProductCashInfoService.deleteByPrimaryKey(finCashInfo);
        }
        //删除业绩回补计算规则
        FinClawbackCalcRule dto = new FinClawbackCalcRule();
        dto.setCashId(anapleroticId);
        dto.setEffDate(effDate);
        baseFinAnapleroticCalcRuleService.delete(dto);
        // 业绩报酬计算规则
        FinFloatCalcRule finFloatCalcRule = new FinFloatCalcRule();
        finFloatCalcRule.setCashId(anapleroticId);//现金流代码
        //业绩回补计算规则Entity.生效日 = 标的类金融产品Entity.起息日（画面.生效日）
        finFloatCalcRule.setEffDate(effDate);//生效日
        
        finFloatCalcRuleMapper.deleteByPrimaryKey(finFloatCalcRule);
        
        //删除现金流计提规则
        BaseFinProductCashCountingRuleDto baseFinProductCashCountingRuleDto = new BaseFinProductCashCountingRuleDto();
        baseFinProductCashCountingRuleDto.setCashId(anapleroticId);
        baseFinProductCashCountingRuleDto.setEffDate(effDate);
        baseFinProductCashCountingRuleService.deleteByBaseFinProductCashCountingRuleDto(baseFinProductCashCountingRuleDto);
        //业绩报酬计算规则明细
        FinFloatCalcRuleDetail finfloatcalcruledetail = new FinFloatCalcRuleDetail();
        finfloatcalcruledetail.setCashId(anapleroticId);
        finfloatcalcruledetail.setEffDate(effDate);
        //修改 bug 回补信息调整-为有效的回补信息做修改为浮动管理费回补，提交审批后复核提交时报异常 add by wangcheng 20191028 
        finfloatcalcruledetail.setLev((long) 1);
        finFloatCalcRuleDetailMapper.deleteByPrimaryKey(finfloatcalcruledetail);
        //删除现金流计提计划
        finProductAnapleroticMapper.deleteScheByCashId(anapleroticId);
        //删除业绩基准信息表 add by cxq 20200108 解决OPEX-738
        if (StringUtil.isNotEmpty(finprodId)) {
        	FinBaseRuleInfo info = new FinBaseRuleInfo();
            info.setFinprodId(finprodId);
            info.setEffDate(effDate);
            info.setBaseRuleType(DictCodeListExt1.业绩基准类型_业绩回补.getValue());
            finProductAnapleroticMapper.deleteFinCashInfo(info);
		}
    }
    /**
     * 更加现金流代码--删除回补一些信息
     * <功能详细描述>
     * @param anapleroticId
     * @see
     * @since 1.0
     */
    public void insertProductAnapleroticInfo(FinProductAnapleroticInfoDto anapleroticDto, FinProductCommonInfoDto finProductCommonInfoDto,
    		boolean isHighOpen){
//        FinProductAnapleroticInfoDto anapleroticDto = finProductAnapleroticInfoDto.getFinProductAnapleroticInfoDto(); 
     
        //业绩回补计算规则Entity
        FinClawbackCalcRule finclawbackcalcrule  = new FinClawbackCalcRule();
        finclawbackcalcrule.setCashId(anapleroticDto.getAnapleroticId());//现金流代码
        //业绩回补计算规则Entity.生效日 = 标的类金融产品Entity.起息日（画面.生效日）
        finclawbackcalcrule.setEffDate(anapleroticDto.getEffDate());//生效日
        //业绩回补计算规则Entity.回补规则 = 画面.回补方式
        finclawbackcalcrule.setCawlbackRule(anapleroticDto.getAnapleroticRule());
        //业绩回补计算规则Entity.回补方式 = 画面.回补形式
        finclawbackcalcrule.setCawlbackType(anapleroticDto.getAnapleroticType());
        //业绩回补计算规则Entity.计算基础 = 画面.计费基础
        finclawbackcalcrule.setBasis(anapleroticDto.getAnapleroticBasic());
        //业绩回补计算规则Entity.生效规则 = 数据字典.生效规则.期初
        finclawbackcalcrule.setActRule(DictCodeList.生效规则_期初.getValue());
        //插入业绩回补计算规则
        baseFinAnapleroticCalcRuleService.insert(finclawbackcalcrule);
        
        // 业绩报酬计算规则
        FinFloatCalcRule finFloatCalcRule = new FinFloatCalcRule();
        finFloatCalcRule.setCashId(anapleroticDto.getAnapleroticId());//现金流代码
        //业绩回补计算规则Entity.生效日 = 标的类金融产品Entity.起息日（画面.生效日）
        finFloatCalcRule.setEffDate(anapleroticDto.getEffDate());//生效日
        //业绩回补计算规则Entity.计算基础 = 画面.计费基础
        finFloatCalcRule.setBasis(anapleroticDto.getAnapleroticBasic());
        //业绩回补计算规则Entity.资产净值日类型 = 画面.资产净值日
        finFloatCalcRule.setAstVdateType(anapleroticDto.getAstVluDateType());
        //业绩回补计算规则Entity.生效规则 = 数据字典.生效规则.期初
        finFloatCalcRule.setActRule(DictCodeList.生效规则_期初.getValue());
        //备注
        finFloatCalcRule.setRemark(anapleroticDto.getRemark());
        
        finFloatCalcRuleMapper.insert(finFloatCalcRule);
        
        // 现金流计提规则
        FinFloatCountingRule finCashCountingRule = new FinFloatCountingRule();
        finCashCountingRule.setCashId(anapleroticDto.getAnapleroticId());
        finCashCountingRule.setEffDate(anapleroticDto.getEffDate());//生效日
        if(isHighOpen){
            finCashCountingRule.setAccrType(BussinessDictCodeList.计提类型_高频.getValue());
        }
        else{
            finCashCountingRule.setAccrType(BussinessDictCodeList.计提类型_固定频率.getValue());
        }
        //现金流计提规则Entity.计提营业日准则 = 画面.营业日准则
        finCashCountingRule.setAccrTdayRule(anapleroticDto.getCycleTdayRule());
        //现金流计提规则Entity.首次计提日 = 画面.首次回补日
        finCashCountingRule.setfAccrDate(anapleroticDto.getFirstAnapleroticDate());
        //首个回补结束日
        finCashCountingRule.setfAccrEndDate(anapleroticDto.getFirstAnapleroticCycleEndDate());
        finCashCountingRule.setAccrCycle(1L);
        //现金流计提规则Entity.计提频率 = 画面.回补规则
        finCashCountingRule.setAccrCycleUnit(anapleroticDto.getAnapleroticFrequency());
        //计提结束日  = 画面.回补计提结束日
        finCashCountingRule.seteAccrDate(anapleroticDto.getAnapleroticEndDate()); 
        //插入现金流计提规则
        baseFinProductCashCountingRuleService.insertFinFloatCountingRule(finCashCountingRule);
        
        //业绩报酬计算规则明细
        FinFloatCalcRuleDetail finFloatCalcRuleDetail = new FinFloatCalcRuleDetail();
        //现金流计提规则Entity.现金流代码 = 现金流信息Entity(回补).现金流代码
        finFloatCalcRuleDetail.setCashId(anapleroticDto.getAnapleroticId());
        //现金流计提规则Entity.生效日 = 标的类金融产品Entity.起息日（画面.生效日）
        finFloatCalcRuleDetail.setEffDate(anapleroticDto.getEffDate());//生效日
        //业绩报酬计算规则明细Entity.层级 = 1
        finFloatCalcRuleDetail.setLev(1L);
        //业绩报酬计算规则明细Entity.业绩基准代码 = 业绩基准信息Entity.业绩基准代码   实际存产品代码见也基准1430行
        finFloatCalcRuleDetail.setBaseRuleId(anapleroticDto.getFinprodId());
        //业绩报酬计算规则明细Entity.系数 = 画面.回补比例 回补比例默认为100%
        //finFloatCalcRuleDetail.setCoefficient(ArithmeticUtil.divide(anapleroticDto.getAnapleroticRatio(), new BigDecimal(100),14));
        finFloatCalcRuleDetail.setCoefficient(BigDecimal.ONE);
        //业绩报酬计算规则明细Entity.利差 = 0
        finFloatCalcRuleDetail.setSpreadRate(BigDecimal.ZERO);
        //业绩报酬计算规则明细Entity.银行收取比例 = 1
        finFloatCalcRuleDetail.setChargeRatio(BigDecimal.ONE);
        String baseRuleId = CommonUtil.generateCashFlowCode(anapleroticDto.getFinprodId(), null, 0L, DictCodeListExt1.业绩基准类型_业绩回补.getValue());
        //净值型产品新建 回补新建提交审批时，暂时按照业绩基准类型_浮动管理费 存业绩报酬计算规则明细表add by wangcheng 20191113 start
//        if(finProductCommonInfoDto != null){
////        	if(StringUtil.equals(finProductCommonInfoDto.getBussFinProductFloatFeeDto().getRateType(), DictCodeList.利率类型_固定利率.getValue())){
////        		//业绩报酬计算规则明细Entity.业绩基准代码 = null
////        		finFloatCalcRuleDetail.setBaseRuleId(null);
////        		//业绩报酬计算规则明细Entity.利差 = 浮动管理费.初始利率
////        		finFloatCalcRuleDetail.setSpreadRate(ArithmeticUtil.divide(finProductCommonInfoDto.getBussFinProductFloatFeeDto().getRate(), new BigDecimal(100),14));
////        	}else{
//        		String baseRuleId = CommonUtil.generateCashFlowCode(anapleroticDto.getFinprodId(), null, finProductCommonInfoDto.getBranch(), DictCodeListExt1.业绩基准类型_浮动管理费.getValue());
//        		//业绩报酬计算规则明细Entity.业绩基准代码 = 浮动管理费.业绩基准代码
//        		finFloatCalcRuleDetail.setBaseRuleId(baseRuleId);
////        	}
//        }else{
//    		FinFloatCalcRuleDetail floatFee = finProductAnapleroticMapper.selectFloatFeeInfo(anapleroticDto.getFinprodId(), DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
//    		if(floatFee != null){
//    			//业绩报酬计算规则明细Entity.业绩基准代码 = 浮动管理费.业绩基准代码
//    			finFloatCalcRuleDetail.setBaseRuleId(floatFee.getBaseRuleId());
//    		}
//        }
		finFloatCalcRuleDetail.setBaseRuleId(baseRuleId);
        //插入业绩报酬计算规则明细
        finFloatCalcRuleDetailMapper.insert(finFloatCalcRuleDetail);
      //净值型产品新建 回补新建提交审批时，暂时按照业绩基准类型_浮动管理费 存业绩报酬计算规则明细表add by wangcheng 20191113 end
       //现金流计提计划
       if(!StringUtil.isEmptyList(anapleroticDto.getFinProductAnapleroticPlanList())){
           for (int i = 1; i <= anapleroticDto.getFinProductAnapleroticPlanList().size(); i++ ) {
               FinProductAnapleroticPlanDto finProductAnapleroticPlanDto = anapleroticDto.getFinProductAnapleroticPlanList().get(
                   i - 1);
               FinFloatAccrualSche finCashAccrsche = new FinFloatAccrualSche();
               finCashAccrsche.setCashId(anapleroticDto.getAnapleroticId());
               //现金流计提计划.计提日 = 画面回补计划列表.回补日
               finCashAccrsche.setAccrDate(finProductAnapleroticPlanDto.getAnapleroticDate());
               //现金流计提计划.计提开始日 = 画面回补计划列表.回补周期开始日
               finCashAccrsche.setAccrStrDate(finProductAnapleroticPlanDto.getAnapleroticCycleStartDate());
               //现金流计提计划.计提结束日 = 画面回补计划列表.回补周期结束日
               finCashAccrsche.setAccrEndDate(finProductAnapleroticPlanDto.getAnapleroticCycleEndDate());
               //现金流计提计划.调整前结束日 = 画面回补计划列表.回补周期结束日
               finCashAccrsche.setValDate(finProductAnapleroticPlanDto.getAnapleroticCycleEndDate());
               //插入数据
               finFloatAccrualScheMapper.insert(finCashAccrsche);
           }
       } 
       //业绩回补业绩基准插入
       if(StringUtil.isNotEmpty(anapleroticDto.getRateType())){
    	   FinBaseRuleInfo finBaseRuleInfo = new FinBaseRuleInfo();
    	   BeanUtil.copyPropertiesIgnoreNull(finBaseRuleInfo, anapleroticDto);
    	   finBaseRuleInfo.setBaseRuleId(baseRuleId);
           finBaseRuleInfo.setBaseRuleType(DictCodeListExt1.披露类型_单值披露.getValue());
           finBaseRuleInfo.setBaseRuleType(DictCodeListExt1.业绩基准类型_业绩回补.getValue());
           //目前无周期回补，且目前需求周期回补只会在母产品上回补，故默认为0L
           finBaseRuleInfo.setBranch(0L);
           finBaseRuleInfo.setRate(ArithmeticUtil.divide(anapleroticDto.getRate(), 100, 14));
           
           if (finProductCommonInfoDto!=null&&finProductCommonInfoDto.getVdate()!=null) {
               finBaseRuleInfo.setEffDate(finProductCommonInfoDto.getVdate());
           }
           else if(anapleroticDto.getEffDate()!=null){
        	   finBaseRuleInfo.setEffDate(anapleroticDto.getEffDate());
           }
           else {
               finBaseRuleInfo.setEffDate(DateUtil.platDateFromCache());
           }
           if (StringUtil.equals(anapleroticDto.getRateType(),
               DictCodeList.利率类型_浮动利率.getValue())) {
               MstIndexInfo mstIndexInfo = mstIndexInfoMapper.selectByPrimaryKey(
            		   anapleroticDto.getIndexId());
               if (mstIndexInfo != null) {
                   finBaseRuleInfo.setIndexSource(mstIndexInfo.getIndexType());
               }
               else {
                   finBaseRuleInfo.setIndexSource(DictCodeListExt1.基准类型_2_利率.getValue());
               }
           }
           finBaseRuleInfo.setFinprodId(anapleroticDto.getFinprodId());
           finBaseRuleInfo.setSpreadRate(
               ArithmeticUtil.divide(anapleroticDto.getSpreadRate(), 100, 14));

           // 一体化对应etliu start
//           resetType = finProductFloatFeeDto.getResetType(); // 业绩基准重置规则
           // 一体化对应etliu end

           finBaseRuleInfoMapper.insert(finBaseRuleInfo);
       }
    }
    public List<FinProductAnapleroticPlanDto> selectAnapleroticPlan(String anapleroticId){
    	return finProductAnapleroticMapper.selectAnapleroticPlan(anapleroticId);
    }
    private FinProductCommonInfoDto getProdInfoByProdStyle(String finprodId){
        Map<String,Object> map = new HashMap<>();
        map.put("finprodId", finprodId);
        map.put("finprodType", BussinessDictCodeList.金融产品类型_理财产品.getValue());
        return finProductAnapleroticMapper.getProdInfoByProdStyle(map);
    }
    /**
     * 条件查询业绩回补信息 --双击详情页面
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<FinProductAnapleroticInfoDto> queryAnapleroticInfoByIdForDetail(Map<String,Object> param){
        param.put("cashType1", DictCodeListExt1.现金流一级类型_回补.getValue());
        param.put("cashType2", DictCodeListExt1.现金流二级类型_回补.getValue());
        param.put("finprodType", BussinessDictCodeList.现金流业务类型_金融产品.getValue());
        param.put("isCalcY", BussinessDictCodeList.是否_是.getValue());
        
        param.put("baseRuleType03", DictCodeListExt1.业绩基准类型_业绩回补.getValue());
        param.put("dealMode11", DictCodeList.处理模式_分档.getValue());
        return  finProductAnapleroticMapper.queryAnapleroticInfoByIdForDetail(param);
    }
}
