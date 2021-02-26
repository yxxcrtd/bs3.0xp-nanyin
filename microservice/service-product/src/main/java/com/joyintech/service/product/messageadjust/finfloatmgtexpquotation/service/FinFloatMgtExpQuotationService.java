/*
 * 文件名：FinFloatMgtExpQuotationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.messageadjust.finfloatmgtexpquotation.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.common.service.FinProductOpenPlanService;
import com.joyintech.service.product.messageadjust.bmquotation.dto.BussBenchMarkInfoDto;
import com.joyintech.service.product.messageadjust.finfloatmgtexpquotation.dao.FinFloatMgtExpQuotationMapper;
import com.joyintech.service.product.messageadjust.finfloatmgtexpquotation.dto.FinFloatMgtExpQuotationDto;
import com.joyintech.service.product.messageadjust.finfloatmgtexpquotation.dto.IntRateWeight;
import com.joyintech.tams.common.base.dao.FinBaseRuleInfoMapper;
import com.joyintech.tams.common.base.dao.FinCashInfoMapper;
import com.joyintech.tams.common.base.dao.FinCashPayRuleMapper;
import com.joyintech.tams.common.base.dao.FinCashPlanMapper;
import com.joyintech.tams.common.base.dao.FinFloatAccrualScheMapper;
import com.joyintech.tams.common.base.dao.FinFloatCalcRuleDetailMapper;
import com.joyintech.tams.common.base.dao.FinFloatCalcRuleMapper;
import com.joyintech.tams.common.base.dao.FinFloatCountingRuleMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.dao.MstIndexInfoMapper;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfoKey;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPayRuleKey;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinCashPlanKey;
import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;
import com.joyintech.tams.common.base.entity.FinFloatAccrualScheKey;
import com.joyintech.tams.common.base.entity.FinFloatCalcRule;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetail;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetailKey;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleKey;
import com.joyintech.tams.common.base.entity.FinFloatCountingRule;
import com.joyintech.tams.common.base.entity.FinFloatCountingRuleKey;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.FinOpenRule;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.MstIndexInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2018年10月19日
 * @see FinFloatMgtExpQuotationService
 * @since 1.0
 */
@Service("backFinFloatMgtExpQuotationService")
public class FinFloatMgtExpQuotationService extends BaseService<FinFloatMgtExpQuotationDto>{
    /**
     * 产品费用报价查询mapper
     */
    @Autowired
    private FinFloatMgtExpQuotationMapper finFloatMgtExpQuotationMapper;
    /**
     * 业绩报酬计算规则明细
     */
    @Autowired
    private FinFloatCalcRuleDetailMapper finFloatCalcRuleDetailMapper;
    /**
     * 业绩报酬计算规则
     */
    @Autowired
    private FinFloatCalcRuleMapper finFloatCalcRuleMapper;
    /**
     * 标的类金融产品
     */
    @Autowired
    private FinProductMapper finProductMapper;
    /**
     * 现金流计提计划
     */
    @Autowired
    private FinFloatAccrualScheMapper finFloatAccrualScheMapper;
    /**
     * 现金流计提规则
     */
    @Autowired
    private FinFloatCountingRuleMapper finfloatCountingRuleMapper;
    /**
     * 现金流支付规则
     */
    @Autowired
    private FinCashPayRuleMapper finCashPayRuleMapper;

    /**
     * 现金流信息
     */
    @Autowired
    private FinCashInfoMapper finCashInfoMapper;

    /**
     * 现金流信息
     */
    @Autowired
    private FinCashPlanMapper finCashPlanMapper;

    @Autowired(required = false)
    private MstIndexInfoMapper mstIndexInfoMapper;
    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;
    /**
     * 业绩基准信息
     */
    @Autowired
    private FinBaseRuleInfoMapper finBaseRuleInfoMapper;
    
    @Autowired
    private FinProductOpenPlanService finProductOpenPlanService;
    /**
     * 浮动管理费报价查询
     * <功能详细描述>
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<FinFloatMgtExpQuotationDto> selectForPage(int page, int length,Map<String, Object> pageParam)
            throws Exception {
        return super.queryForPage(page, length, () -> finFloatMgtExpQuotationMapper.selectForPage(pageParam));
    }
    
    /**
     * 浮动管理费报价查询
     * <功能详细描述>
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<FinFloatMgtExpQuotationDto> selectForList(Map<String, Object> pageParam)
            throws Exception {
        String keys = (String)pageParam.get("keys");
        pageParam.put("keys", StringUtil.splitToList(GlobalConstant.COMMA, keys));
        return finFloatMgtExpQuotationMapper.selectForPage(pageParam);
    }
    
    /**
     * 产品代码联想框
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodId(Map<String,Object> pageParam){
        return finFloatMgtExpQuotationMapper.getFinprodId(pageParam);
    }

    /**
     * 判断现金流信息是否存在
     * @param finprodId
     * @return
     */
    public FinFloatMgtExpQuotationDto selectAlreadHave(String finprodId){
    	Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", finprodId);
        param.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        param.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        param.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        return finFloatMgtExpQuotationMapper.selectAlreadHave(param);
    }


    public FinOpenRule queryOpenRuleByFinprodId(String finprodId){
    	Map<String,Object> param=new HashMap<String,Object>();
    	String cashId_amt = CommonUtil.generateCashFlowCode(
    			finprodId,
                DictCodeListExt1.现金流业务类型_金融产品.getValue(),
                Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)),
                DictCodeListExt1.现金流二级类型_本金.getValue());
    	//String openType=DictCodeList.申赎开放类型_申赎开放.getValue();
    	List<String> openType = new ArrayList<>();
    	openType.add(DictCodeList.开放模式_定期开放.getValue());
        openType.add(DictCodeList.开放模式_周期开放.getValue());
    	param.put("cashId_amt", cashId_amt);
    	param.put("openTypeList", openType);
    	return finFloatMgtExpQuotationMapper.queryOpenRuleByFinprodId(param);
    }
    /**
     * 浮动管理费新建时相关规则表存数处理
     * @param entity  页面相关数据
     * @param finProduct 产品基本信息
     * @param cashExist  现金流代码是否存在 true 存在 false 不存在
     * @throws Exception 异常
     */
    private void doCompleteFloatRule(FinFloatMgtExpQuotationDto entity, FinProduct finProduct, boolean cashExist) throws Exception{
        //补充
        //现金流支付规则
        FinCashPayRule finCashPayRule = new FinCashPayRule();
        finCashPayRule.setCashId(entity.getCashId());
        finCashPayRule.setEffDate(entity.getEffDate());
        finCashPayRule.setPayCycle(entity.getPayCycle());
        finCashPayRule.setFirstPayDate(entity.getFirstPayDate());
        finCashPayRule.setEndTdayRule(entity.getEndTdayRule());
        finCashPayRule.setPayTdayRule(entity.getPayTdayRule());
        finCashPayRule.setFinprodId(entity.getFinprodId());
        finCashPayRule.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        finCashPayRule.setBranch(entity.getBranch());
        finCashPayRule.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
        if(cashExist){
        	finCashPayRuleMapper.deleteByPrimaryKey(finCashPayRule);
        }
        finCashPayRuleMapper.insert(finCashPayRule);
        //生成计提计划
        // 净值型、货币型产品：估值：浮动管理费有计提频率的概念，分高频和固定频率。前台没管
        //根据产品代码查找开放规则
        List<FinFloatAccrualSche> finFloatAccrualScheNew = new ArrayList<FinFloatAccrualSche>();
        String finprodId = StringUtil.isNotEmpty(entity.getpFinprodId())?entity.getpFinprodId():entity.getFinprodId();//支持分层产品，以【父产品代码】查询开放规则
        FinOpenRule finOpenRuleBak = this.queryOpenRuleByFinprodId(finprodId);
        if (!StringUtil.equals(finProduct.getProfitType(), DictCodeList.收益类型_收益型.getValue())) {
        	if(cashExist){
        		FinFloatCountingRuleKey finFloatCountingRuleKey = new FinFloatCountingRuleKey();
        		finFloatCountingRuleKey.setCashId(entity.getCashId());
        		finFloatCountingRuleKey.setEffDate(entity.getEffDate());
        		finfloatCountingRuleMapper.deleteByPrimaryKey(finFloatCountingRuleKey);
        	}
            // 开放式产品
            if (StringUtil.equals(DictCodeList.开放类型_开放式.getValue(),finProduct.getOperationType())
            		&&finOpenRuleBak != null) {
                // 高频开放（开放规则.开放模式 = 高频开放）的给高频计提
                if (StringUtil.equals(DictCodeList.开放模式_高频开放.getValue(), finOpenRuleBak.getOpenMode())) {
                    // 1、现金流计提规则表：
                    FinFloatCountingRule finCashCountingRule = new FinFloatCountingRule();
                    finCashCountingRule.setCashId(entity.getCashId());
                    /*if (StringUtil.isNotEmpty(finProduct.getVdate())) {
                        finCashCountingRule.setEffDate(finProduct.getVdate());
                    }
                    else {
                        finCashCountingRule.setEffDate(platFormDate);
                    }*/
                    finCashCountingRule.setEffDate(entity.getEffDate());
                    finCashCountingRule.setAccrType(DictCodeList.计提类型_高频.getValue());
                    finCashCountingRule.setAccrCycle(1L);
                    finCashCountingRule.setAccrCycleUnit(DictCodeList.频率_日.getValue());
                    finCashCountingRule.setAccrTdayRule(entity.getAccrTdayRule());
//                    finCashCountingRule.setIntervalRule(entity.getInterValRule());
                    finCashCountingRule.setfAccrEndDate(entity.getfAccrEndDate());
                    finCashCountingRule.seteAccrDate(entity.geteAccrDate());
                    finCashCountingRule.setfAccrDate(entity.getfAccrDate());
                    finfloatCountingRuleMapper.insert(finCashCountingRule);

                    // 2、业绩报酬计提计划：空
                }
                // 非高频开放（开放规则.开放模式 != 高频开放）的存计提计划，按开放周期结束日计提
                else {
                    // 1、现金流计提规则表：
                    FinFloatCountingRule finCashCountingRule = new FinFloatCountingRule();
                    finCashCountingRule.setCashId(entity.getCashId());
                    finCashCountingRule.setEffDate(entity.getEffDate());
					finCashCountingRule.setAccrTdayRule(entity.getAccrTdayRule());
                    finCashCountingRule.setAccrType(DictCodeList.计提类型_固定频率.getValue());
                    finCashCountingRule.setIntervalRule(entity.getIntervalRule());
                    finCashCountingRule.setfAccrEndDate(entity.getfAccrEndDate());
                    finCashCountingRule.seteAccrDate(entity.geteAccrDate());
				    //finCashCountingRule.setAccrType(DictCodeList.计提类型_固定频率.getValue());
                    finCashCountingRule.setAccrCycle(finOpenRuleBak.getOpenCycle());
                    finCashCountingRule.setAccrCycleUnit(finOpenRuleBak.getOpenCycleUnit());
                    finCashCountingRule.setAccrDayType(finOpenRuleBak.getDayType());
                    finCashCountingRule.setAccrDayValue(finOpenRuleBak.getDayValue());
                    finCashCountingRule.setfAccrDate(entity.getfAccrDate());

                    finfloatCountingRuleMapper.insert(finCashCountingRule);

                    List<Date> busiDate = new ArrayList<Date>();
//                    List<BussFinProductOpenPlanDto> list = finProductOpenPlanService.selectOpenPlanInfoByFinprodId(finprodId);
                    //获取开放计划
                    List<FinOpenPlan> finOpenPlanList = finProductOpenPlanService.queryOpenPlanListByFinprodId(finprodId);
                    //获取旧的开放计划,并且将浮动管理费
                	if(cashExist){
                		List<FinFloatAccrualSche> finFloatAccrualScheOld = finFloatMgtExpQuotationMapper.queryFloatScheByCashId(entity.getCashId(), entity.getEffDate());
                    	if(!StringUtil.isEmptyList(finFloatAccrualScheOld)){
                    		addDateToList(busiDate,finFloatAccrualScheOld.get(finFloatAccrualScheOld.size()-1).getAccrDate());
                    		finFloatAccrualScheNew.addAll(finFloatAccrualScheOld);
                    	}
                	}
                    if(StringUtil.equals(DictCodeList.收益类型_净值型.getValue(), finProduct.getProfitType())){
                    	for (FinOpenPlan finOpenPlan : finOpenPlanList) {
                    		if(finOpenPlan.getOpenNdate().compareTo(entity.getEffDate())>=0){
                        		addDateToList(busiDate,finOpenPlan.getOpenNdate());
                    		}
    					}
                    }
                    else if(StringUtil.equals(DictCodeList.收益类型_货币型.getValue(), finProduct.getProfitType())){
                    	for (FinOpenPlan finOpenPlan : finOpenPlanList) {
                    		if(finOpenPlan.getOpenDateEndUn().compareTo(entity.getEffDate())>=0){
                    			addDateToList(busiDate,finOpenPlan.getOpenDateEndUn());
                    		}
    					}
                    }
                    if(!cashExist){
                        addDateToList(busiDate,entity.getEffDate());
                    }
                    addDateToList(busiDate,finProduct.getMdate());
                    addDateToList(busiDate,entity.getfAccrDate());
                    addDateToList(busiDate,entity.geteAccrDate());
                    Date startDate = busiDate.get(0);
                    boolean flag = true;
                    for(Date endDate: busiDate){
                        if(startDate.compareTo(endDate)==0){ 
                            startDate = endDate;
                            continue;
                        }
                        if(endDate.compareTo(entity.getfAccrDate())<0){
                            startDate = endDate;
                            continue;
                        }
                        if(endDate.compareTo(entity.geteAccrDate())>0){
                            startDate = endDate;
                            continue;
                        }
                        FinFloatAccrualSche finFloatAccrualScheFirst = new FinFloatAccrualSche();
                        BeanUtil.copyProperties(finFloatAccrualScheFirst, finProduct);
                        finFloatAccrualScheFirst.setCashId(entity.getCashId());
                        finFloatAccrualScheFirst.setAccrDate(endDate);
                        finFloatAccrualScheFirst.setAccrStrDate(startDate);
                        //只有当第一次新建的时候，才会是初始减一
                        if(flag&&!cashExist){
                        	 finFloatAccrualScheFirst.setAccrStrDate(DateUtil.addDay(startDate, -1));
                        	 flag = false;
                        }

                        finFloatAccrualScheFirst.setAccrEndDate(endDate);
                        finFloatAccrualScheFirst.setValDate(endDate);
                        //finFloatAccrualScheMapper.insert(finFloatAccrualScheFirst);
                        finFloatAccrualScheNew.add(finFloatAccrualScheFirst);
                        startDate = endDate;
                    }                            
                }
            }
            // 封闭式产品 保存到期日计提。
            else if (StringUtil.equals(DictCodeList.开放类型_封闭式.getValue(), finProduct.getOperationType())
                //TISHKBS-1363  封闭式产品没有开放计划 modify by caojin 20201004
//                    &&finOpenRuleBak != null
            ) {
                // 1、现金流计提规则表：
                FinFloatCountingRule finCashCountingRule = new FinFloatCountingRule();
                finCashCountingRule.setCashId(entity.getCashId());
                /*if (StringUtil.isNotEmpty(finProduct.getVdate())) {
                    finCashCountingRule.setEffDate(finProduct.getVdate());
                }
                else {
                    finCashCountingRule.setEffDate(platFormDate);
                }*/                        
                finCashCountingRule.setEffDate(entity.getEffDate());
                finCashCountingRule.setAccrType(DictCodeList.计提类型_固定频率.getValue());
                //TISHKBS-1363  封闭式产品没有开放计划 modify by caojin 20201004
//                finCashCountingRule.setAccrCycle(finOpenRuleBak.getOpenCycle());
                finCashCountingRule.setAccrCycleUnit(entity.getPayCycle());
//                finCashCountingRule.setAccrDayType(finOpenRuleBak.getDayType());
//                finCashCountingRule.setAccrDayValue(finOpenRuleBak.getDayValue());
                finCashCountingRule.setAccrTdayRule(DictCodeList.营业日准则_不调整.getValue());
                finCashCountingRule.setfAccrDate(entity.getfAccrDate());
                if (StringUtil.isNotEmpty(entity.geteAccrDate())) {
                    finCashCountingRule.seteAccrDate(entity.geteAccrDate());
                }
                finfloatCountingRuleMapper.insert(finCashCountingRule);
                // 2、业绩报酬计提计划：
                FinFloatAccrualSche finFloatAccrualSche = new FinFloatAccrualSche();
                finFloatAccrualSche.setCashId(entity.getCashId());
                finFloatAccrualSche.setAccrDate(entity.getfAccrDate());
                finFloatAccrualSche.setAccrStrDate(DateUtil.addDay(entity.getVdate(), -1));
                finFloatAccrualSche.setAccrEndDate(entity.geteAccrDate());
                //若已存在现金流，计提计划先删除再插入，否则直接插入
                if(cashExist){
                	finFloatMgtExpQuotationMapper.deleteFloatScheByCashId(entity.getCashId());
                }
                finFloatAccrualScheMapper.insert(finFloatAccrualSche);
//                finFloatAccrualScheNew.add(finFloatAccrualSche);
            }
            //当现金流代码存在并且新的计提计划列表有值
            if(cashExist&&!StringUtil.isEmptyList(finFloatAccrualScheNew)){
            	finFloatMgtExpQuotationMapper.deleteFloatScheByCashId(entity.getCashId());
            	for (FinFloatAccrualSche finFloatAccrualSche : finFloatAccrualScheNew) {
            		// 查询无重复
            		FinFloatAccrualScheKey key = new FinFloatAccrualScheKey();
					key.setCashId(finFloatAccrualSche.getCashId());
					key.setAccrDate(finFloatAccrualSche.getAccrDate());
					FinFloatAccrualSche fas = finFloatAccrualScheMapper.selectByPrimaryKey(key);
					if(!StringUtil.isNotEmpty(fas)) {
            		finFloatAccrualScheMapper.insert(finFloatAccrualSche);
					}
				}
            }
            //当现金流计提计划没有，并且新的列表有值
            else if(!StringUtil.isEmptyList(finFloatAccrualScheNew)){
            	for (FinFloatAccrualSche finFloatAccrualSche : finFloatAccrualScheNew) {
            		// 查询无重复
            		FinFloatAccrualScheKey key = new FinFloatAccrualScheKey();
					key.setCashId(finFloatAccrualSche.getCashId());
					key.setAccrDate(finFloatAccrualSche.getAccrDate());
					FinFloatAccrualSche fas = finFloatAccrualScheMapper.selectByPrimaryKey(key);
					if(!StringUtil.isNotEmpty(fas)) {
            		finFloatAccrualScheMapper.insert(finFloatAccrualSche);
					}
				}
            }
        }
        //付费计划插入
        List<FinCashPlan> planList = entity.getFinCashPlanList();
        if(!StringUtil.isEmptyList(planList)){
        	if(cashExist){
        		finFloatMgtExpQuotationMapper.deleteCashPlanByCashId(entity.getCashId());
        	}
        	for (FinCashPlan finCashPlan : planList) {
        		// 查询无重复
				FinCashPlanKey key = new FinCashPlanKey();
				key.setCashId(finCashPlan.getCashId());
				key.setMdate(finCashPlan.getMdate());
				FinCashPlan fcp = finCashPlanMapper.selectByPrimaryKey(key);
				if(!StringUtil.isNotEmpty(fcp)) {
        		finCashPlanMapper.insert(finCashPlan);
				}
			}
        }
    }

    /**
     * 工作流后的db操作
     * <功能详细描述>
     * @param entity FinFloatMgtExpQuotationDto
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public void doComplete(FinFloatMgtExpQuotationDto entity) throws Exception{
    	// 平台日期
        Date platFormDate = iPlatformDate.getSysDate();
        String type = entity.getOperateType();
        FinProduct finProduct = finProductMapper.selectByPrimaryKey(entity.getFinprodId());
        //删除
        if (StringUtil.equals(type, "DELETE")) {
            //删除明细表时的主键
            FinFloatCalcRuleDetailKey key=new FinFloatCalcRuleDetailKey();
            //判断有无明细时的主键
            FinFloatCalcRuleDetailKey keyDetail=new FinFloatCalcRuleDetailKey();
            FinFloatCalcRuleDetailKey keyDetail2=new FinFloatCalcRuleDetailKey();
            //删除业绩报酬计算规则的主键
            FinFloatCalcRuleKey key2=new FinFloatCalcRuleKey();
            //删除现金流计提规则表的主键
            FinFloatCountingRuleKey countKey = new FinFloatCountingRuleKey();
            //删除现金流计提计划表的主键
            FinFloatAccrualScheKey scheKey= new FinFloatAccrualScheKey();
            //删除现金流支付规则表的主键
            FinCashPayRuleKey payKey = new FinCashPayRuleKey();
            //删除现金流付费计划的主键
            FinCashPlanKey planKey = new FinCashPlanKey();
            key.setCashId(entity.getCashId());
            key.setEffDate(entity.getEffDate());
            key.setLev(entity.getLev());

            //删除明细表
            finFloatMgtExpQuotationMapper.deleteByPrimaryKey(key);
            keyDetail.setCashId(entity.getCashId());
            keyDetail.setEffDate(entity.getEffDate());
            keyDetail2.setCashId(entity.getCashId());
            key2.setCashId(entity.getCashId());
            key2.setEffDate(entity.getEffDate());
            countKey.setCashId(entity.getCashId());
            countKey.setEffDate(entity.getEffDate());
            payKey.setCashId(entity.getCashId());
            payKey.setEffDate(entity.getEffDate());
            planKey.setCashId(entity.getCashId());
            scheKey.setCashId(entity.getCashId());
            //现金流代码和生效日检索有无业绩报酬计算规则明细
            int count=finFloatMgtExpQuotationMapper.selectByPrimaryKey(keyDetail);
            int count2 = finFloatMgtExpQuotationMapper.selectByPrimaryKey(keyDetail2);
            if( count == 0){//无明细数据时删除计算规则表
                finFloatCalcRuleMapper.deleteByPrimaryKey(key2);
                if(count2==0){
                	//删除现金流信息
                	finCashInfoMapper.deleteByPrimaryKey(entity.getCashId());
                	//删除现金流计提规则
                	finfloatCountingRuleMapper.deleteByPrimaryKey(countKey);
                	//删除现金流计提计划
                    //scheKey.setAccrDate(entity.getfAccrDate());
                	finFloatAccrualScheMapper.deleteByPrimaryKey(scheKey);
                    finFloatMgtExpQuotationMapper.deleteFloatAccrByPrimaryKey(scheKey);
                	//删除现金流支付规则
                	finCashPayRuleMapper.deleteByPrimaryKey(payKey);
                	//删除现金流付费计划
                	finCashPlanMapper.deleteByPrimaryKey(planKey);
                }
                //删除现金流计提规则
                finfloatCountingRuleMapper.deleteByPrimaryKey(countKey); 
                //删除现金流支付规则
                finCashPayRuleMapper.deleteByPrimaryKey(payKey);
                //删除业绩基准 删除最后一条时删除业绩基准
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("effDate", entity.getEffDate());
                map.put("finprodId", entity.getFinprodId());
                map.put("baseRuleType02", DictCodeListExt1.业绩基准类型_浮动管理费.getValue());
                finFloatMgtExpQuotationMapper.deleteBussBenchMarkInfo(map);
            }
            //当还有明细时，将lev从1重新开始排序
            else{
            	//首先查询出剩余的明细列表
            	List<FinFloatCalcRuleDetail> detailList = finFloatMgtExpQuotationMapper.queryFloatDetailByIdAndDate(key);
            	//先删除明细列表,再逐笔插入
            	finFloatMgtExpQuotationMapper.deleteFloatDetailByIdAndDate(key);
            	for(int i=0; i<detailList.size(); i++){
            		FinFloatCalcRuleDetail finFloatCalcRuleDetail = detailList.get(i);
            		finFloatCalcRuleDetail.setLev((long)i+1);
            		finFloatCalcRuleDetailMapper.insert(finFloatCalcRuleDetail);
            	}
            }
            
        }//新建
        else if (StringUtil.equals(type, "NEW")) {
            //判断现金流信息是否存在
        	FinFloatMgtExpQuotationDto finCashInfo = this.selectAlreadHave(entity.getFinprodId());
            //现金流信息不存在，则规则保存（新建时，若有浮动管理费，规则不变，不需要调整；若没有浮动管理费，则规则生成）
            if(finCashInfo == null){
                String cashId_floatFee = null;
                cashId_floatFee = CommonUtil.generateCashFlowCode(
                    entity.getFinprodId(),
                    DictCodeListExt1.现金流业务类型_金融产品.getValue(), entity.getBranch(),
                    DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
                entity.setCashId(cashId_floatFee);
                FinCashInfo finCashInfo_floatFee = new FinCashInfo();
                finCashInfo_floatFee.setCashId(cashId_floatFee);
                finCashInfo_floatFee.setCashType1(DictCodeListExt1.现金流一级类型_费用.getValue());
                finCashInfo_floatFee.setCashType2(DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
                finCashInfo_floatFee.setCcy(finProduct.getCcy());
                finCashInfo_floatFee.setFinprodId(entity.getFinprodId());
                finCashInfo_floatFee.setFinprodType(DictCodeListExt1.现金流业务类型_金融产品.getValue());
                finCashInfo_floatFee.setBranch(entity.getBranch());
                finCashInfo_floatFee.setFeeId(entity.getFeeId());
                finCashInfoMapper.insert(finCashInfo_floatFee);

                
                this.doCompleteFloatRule(entity, finProduct, false);
                // 现金流计提规则
               /* FinFloatCountingRule finFloatCountingRule = new FinFloatCountingRule();
                finFloatCountingRule.setCashId(entity.getCashId());
                if (StringUtil.isNotEmpty(finProduct.getVdate())) {
                    finFloatCountingRule.setEffDate(finProduct.getVdate());
                }
                else {
                    finFloatCountingRule.setEffDate(platFormDate);
                }
                finFloatCountingRule.setfAccrDate(entity.getfAccrDate());

                //补充
                finFloatCountingRule.seteAccrDate(entity.geteAccrDate());//计提结束日
                finFloatCountingRule.setAccrType(entity.getAccrType());
                finFloatCountingRule.setIntervalRule(entity.getIntervalRule());
                finFloatCountingRule.setAccrTdayRule(entity.getAccrTdayRule());
                finFloatCountingRule.setfAccrEndDate(entity.getfAccrEndDate());//首个计提区间结束日
                finfloatCountingRuleMapper.insert(finFloatCountingRule);*/


                //补充
                //现金流支付规则
//                FinCashPayRule finCashPayRule = new FinCashPayRule();
//                finCashPayRule.setCashId(entity.getCashId());
//                /*if (StringUtil.isNotEmpty(finProduct.getVdate())) {
//                	finCashPayRule.setEffDate(finProduct.getVdate());
//                }
//                else {
//                	finCashPayRule.setEffDate(platFormDate);
//                }*/
//                finCashPayRule.setEffDate(entity.getEffDate());
//                finCashPayRule.setPayCycle(entity.getPayCycle());
//                finCashPayRule.setFirstPayDate(entity.getFirstPayDate());
//                finCashPayRule.setEndTdayRule(entity.getEndTdayRule());
//                finCashPayRule.setPayTdayRule(entity.getPayTdayRule());
//                finCashPayRule.setFinprodId(entity.getFinprodId());
//                finCashPayRule.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
//                finCashPayRule.setBranch(entity.getBranch());
//                finCashPayRule.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
//                finCashPayRuleMapper.insert(finCashPayRule);
//
//
//                //生成计提计划
//
//                // 净值型、货币型产品：估值：浮动管理费有计提频率的概念，分高频和固定频率。前台没管
//                //根据产品代码查找开放规则
//                String finprodId = StringUtil.isNotEmpty(entity.getpFinprodId())?entity.getpFinprodId():entity.getFinprodId();//支持分层产品，以【父产品代码】查询开放规则
//                FinOpenRule finOpenRuleBak = this.queryOpenRuleByFinprodId(finprodId);
//                if (!StringUtil.equals(finProduct.getProfitType(), DictCodeList.收益类型_收益型.getValue())) {
//                    // 开放式产品
//                    if (StringUtil.equals(DictCodeList.开放类型_开放式.getValue(),finProduct.getOperationType())
//                    		&&finOpenRuleBak != null) {
//                        // 高频开放（开放规则.开放模式 = 高频开放）的给高频计提
//                        if (StringUtil.equals(DictCodeList.计提方式_每日新增.getValue(), entity.getAccrType())) {
//                            // 1、现金流计提规则表：
//                            FinFloatCountingRule finCashCountingRule = new FinFloatCountingRule();
//                            finCashCountingRule.setCashId(cashId_floatFee);
//                            /*if (StringUtil.isNotEmpty(finProduct.getVdate())) {
//                                finCashCountingRule.setEffDate(finProduct.getVdate());
//                            }
//                            else {
//                                finCashCountingRule.setEffDate(platFormDate);
//                            }*/
//                            finCashCountingRule.setEffDate(entity.getEffDate());
//                            finCashCountingRule.setAccrType(DictCodeList.计提方式_每日新增.getValue());
//                            finCashCountingRule.setAccrCycle(1L);
//                            finCashCountingRule.setAccrCycleUnit(DictCodeList.频率_日.getValue());
//                            finCashCountingRule.setAccrTdayRule(DictCodeList.营业日准则_不调整.getValue());
//                            finCashCountingRule.seteAccrDate(entity.geteAccrDate());
//                            finCashCountingRule.setfAccrDate(entity.getfAccrDate());
//                            finfloatCountingRuleMapper.insert(finCashCountingRule);
//
//                            // 2、业绩报酬计提计划：空
//                        }
//                        // 非高频开放（开放规则.开放模式 != 高频开放）的存计提计划，按开放周期结束日计提
//                        else {
//                            // 1、现金流计提规则表：
//                            FinFloatCountingRule finCashCountingRule = new FinFloatCountingRule();
//                            finCashCountingRule.setCashId(cashId_floatFee);
//                            /*if (StringUtil.isNotEmpty(finProduct.getVdate())) {
//                                finCashCountingRule.setEffDate(finProduct.getVdate());
//                            }
//                            else {
//                                finCashCountingRule.setEffDate(platFormDate);
//                            }*/
//                            finCashCountingRule.setEffDate(entity.getEffDate());
//							finCashCountingRule.setAccrTdayRule(entity.getAccrTdayRule());
//                            finCashCountingRule.setAccrType(DictCodeList.计提方式_区间倒减.getValue());
//                            finCashCountingRule.setIntervalRule(entity.getIntervalRule());
//                            finCashCountingRule.setfAccrEndDate(entity.getfAccrEndDate());
//                            finCashCountingRule.seteAccrDate(entity.geteAccrDate());
//						    //finCashCountingRule.setAccrType(DictCodeList.计提类型_固定频率.getValue());
//                            finCashCountingRule.setAccrCycle(finOpenRuleBak.getOpenCycle());
//                            finCashCountingRule.setAccrCycleUnit(finOpenRuleBak.getOpenCycleUnit());
//                            finCashCountingRule.setAccrDayType(finOpenRuleBak.getDayType());
//                            finCashCountingRule.setAccrDayValue(finOpenRuleBak.getDayValue());
//                            finCashCountingRule.setfAccrDate(entity.getfAccrDate());
//
//                            finfloatCountingRuleMapper.insert(finCashCountingRule);
//
//                            List<Date> busiDate = new ArrayList<Date>();
//                            List<BussFinProductOpenPlanDto> list = finProductOpenPlanService.selectOpenPlanInfoByFinprodId(finprodId);
//                            BussFinProductOpenPlanDto bussFinProductOpenPlanDto = null;
//                            BussFinProductOpenPlanDto bussFinProductOpenPlanRedDto = null;
//                            for(BussFinProductOpenPlanDto d:list){
//	                       		 if(StringUtils.equals(DictCodeList.申赎开放类型_申购开放.getValue(), d.getOpenType())||
//	                       				 StringUtils.equals(DictCodeList.申赎开放类型_申赎开放.getValue(), d.getOpenType())){
//	                       			bussFinProductOpenPlanDto=d;
//	                       		 }else{
//	                       			bussFinProductOpenPlanRedDto=d;
//	                       		 }
//	                       	 }
//                            if(StringUtil.equals(DictCodeList.收益类型_净值型.getValue(), finProduct.getProfitType())){
//                                if(bussFinProductOpenPlanDto!=null && !StringUtil.isEmptyList(bussFinProductOpenPlanDto.getFinOpenPlanList())){
//                                    for(FinOpenPlan plan:bussFinProductOpenPlanDto.getFinOpenPlanList()){
//                                        addDateToList(busiDate,plan.getOpenNdate());
//                                    }
//                                }
//                                if(bussFinProductOpenPlanRedDto!=null && !StringUtil.isEmptyList(bussFinProductOpenPlanRedDto.getFinOpenPlanList())){
//                                    for(FinOpenPlan plan:bussFinProductOpenPlanRedDto.getFinOpenPlanList()){
//                                        addDateToList(busiDate,plan.getOpenNdate());
//                                    }
//                                }
//                            }else if(StringUtil.equals(DictCodeList.收益类型_货币型.getValue(), finProduct.getProfitType())){
//                                if(bussFinProductOpenPlanDto!=null && !StringUtil.isEmptyList(bussFinProductOpenPlanDto.getFinOpenPlanList())){
//                                    for(FinOpenPlan plan:bussFinProductOpenPlanDto.getFinOpenPlanList()){
//                                        addDateToList(busiDate,plan.getOpenDateEnd());
//                                    }
//                                }
//                                if(bussFinProductOpenPlanRedDto!=null && !StringUtil.isEmptyList(bussFinProductOpenPlanRedDto.getFinOpenPlanList())){
//                                    for(FinOpenPlan plan:bussFinProductOpenPlanRedDto.getFinOpenPlanList()){
//                                        addDateToList(busiDate,plan.getOpenDateEnd());
//                                    }
//                                }
//                            }
//
//                            addDateToList(busiDate,finProduct.getVdate());
//                            addDateToList(busiDate,finProduct.getMdate());
//                            addDateToList(busiDate,entity.getfAccrDate());
//                            addDateToList(busiDate,entity.geteAccrDate());
//                            Date startDate = busiDate.get(0);
//                            boolean flag = true;
//                            for(Date endDate: busiDate){
//                                if(startDate.compareTo(endDate)==0){
//                                    startDate = endDate;
//                                    continue;
//                                }
//                                if(endDate.compareTo(entity.getfAccrDate())<0){
//                                    startDate = endDate;
//                                    continue;
//                                }
//                                if(endDate.compareTo(entity.geteAccrDate())>0){
//                                    startDate = endDate;
//                                    continue;
//                                }
//                                FinFloatAccrualSche finFloatAccrualScheFirst = new FinFloatAccrualSche();
//                                BeanUtil.copyProperties(finFloatAccrualScheFirst, finProduct);
//                                finFloatAccrualScheFirst.setCashId(cashId_floatFee);
//                                finFloatAccrualScheFirst.setAccrDate(endDate);
//                                finFloatAccrualScheFirst.setAccrStrDate(startDate);
//                                if(flag){
//                                	 finFloatAccrualScheFirst.setAccrStrDate(DateUtil.addDay(startDate, -1));
//                                	 flag = false;
//                                }
//
//                                finFloatAccrualScheFirst.setAccrEndDate(endDate);
//                                finFloatAccrualScheFirst.setValDate(endDate);
//                                finFloatAccrualScheMapper.insert(finFloatAccrualScheFirst);
//                                startDate = endDate;
//                            }                            
//                        }
//                    }
//                    // 封闭式产品 保存到期日计提。
//                    else if (StringUtil.equals(DictCodeList.开放类型_封闭式.getValue(), finProduct.getOperationType())) {
//                        // 1、现金流计提规则表：
//                        FinFloatCountingRule finCashCountingRule = new FinFloatCountingRule();
//                        finCashCountingRule.setCashId(cashId_floatFee);
//                        /*if (StringUtil.isNotEmpty(finProduct.getVdate())) {
//                            finCashCountingRule.setEffDate(finProduct.getVdate());
//                        }
//                        else {
//                            finCashCountingRule.setEffDate(platFormDate);
//                        }*/                        
//                        finCashCountingRule.setEffDate(entity.getEffDate());
//                        finCashCountingRule.setAccrType(DictCodeList.计提类型_固定频率.getValue());
//                        finCashCountingRule.setAccrCycle(finOpenRuleBak.getOpenCycle());
//                        finCashCountingRule.setAccrCycleUnit(finOpenRuleBak.getOpenCycleUnit());
//                        finCashCountingRule.setAccrDayType(finOpenRuleBak.getDayType());
//                        finCashCountingRule.setAccrDayValue(finOpenRuleBak.getDayValue());
//                        finCashCountingRule.setAccrTdayRule(DictCodeList.营业日准则_不调整.getValue());
//                        finCashCountingRule.setfAccrDate(entity.getfAccrDate());
//                        if (StringUtil.isNotEmpty(entity.geteAccrDate())) {
//                            finCashCountingRule.seteAccrDate(entity.geteAccrDate());
//                        }
//                        finfloatCountingRuleMapper.insert(finCashCountingRule);
//                        // 2、业绩报酬计提计划：
//                        FinFloatAccrualSche finFloatAccrualSche = new FinFloatAccrualSche();
//                        finFloatAccrualSche.setCashId(cashId_floatFee);
//                        finFloatAccrualSche.setAccrDate(entity.getfAccrDate());
//                        finFloatAccrualSche.setAccrStrDate(DateUtil.addDay(entity.getVdate(), -1));
//                        finFloatAccrualSche.setAccrEndDate(entity.geteAccrDate());
//                        finFloatAccrualScheMapper.insert(finFloatAccrualSche);
//                    }
//                }
            }else {
            	entity.setCashId(finCashInfo.getCashId());
            	
            	this.doCompleteFloatRule(entity, finProduct, true);
                //若存在现金流信息，则调整为最新的规则 add by jyl start
                //现金流支付规则
//                FinCashPayRule finCashPayRule = new FinCashPayRule();
//                finCashPayRule.setCashId(entity.getCashId());
//                finCashPayRule.setEffDate(entity.getEffDate());
//                finCashPayRule.setPayCycle(entity.getPayCycle());
//                finCashPayRule.setFirstPayDate(entity.getFirstPayDate());
//                finCashPayRule.setEndTdayRule(entity.getEndTdayRule());
//                finCashPayRule.setPayTdayRule(entity.getPayTdayRule());
//                finCashPayRule.setFinprodId(entity.getFinprodId());
//                finCashPayRule.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
//                finCashPayRule.setBranch(entity.getBranch());
//                finCashPayRule.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
//                finCashPayRuleMapper.insert(finCashPayRule);
//                //生成计提计划
//                // 净值型、货币型产品：估值：浮动管理费有计提频率的概念，分高频和固定频率。前台没管
//                //根据产品代码查找开放规则
//                String finprodId = StringUtil.isNotEmpty(entity.getpFinprodId())?entity.getpFinprodId():entity.getFinprodId();//支持分层产品，以【父产品代码】查询开放规则
//                FinOpenRule finOpenRuleBak = this.queryOpenRuleByFinprodId(finprodId);
//                if (!StringUtil.equals(finProduct.getProfitType(), DictCodeList.收益类型_收益型.getValue())) {
//                    // 开放式产品
//                    if (StringUtil.equals(DictCodeList.开放类型_开放式.getValue(),finProduct.getOperationType())
//                            &&finOpenRuleBak != null) {
//                        // 非高频计提
//                         // 1、现金流计提规则表：
//                            FinFloatCountingRule finCashCountingRule = new FinFloatCountingRule();
//                            finCashCountingRule.setCashId(finCashInfo.getCashId());
//                            finCashCountingRule.setEffDate(entity.getEffDate());
//                            finCashCountingRule.setAccrTdayRule(entity.getAccrTdayRule());
//                            finCashCountingRule.setAccrType(DictCodeList.计提方式_区间倒减.getValue());
//                            finCashCountingRule.setIntervalRule(entity.getIntervalRule());
//                            finCashCountingRule.setfAccrEndDate(entity.getfAccrEndDate());
//                            finCashCountingRule.seteAccrDate(entity.geteAccrDate());
//                            finCashCountingRule.setAccrCycle(finOpenRuleBak.getOpenCycle());
//                            finCashCountingRule.setAccrCycleUnit(finOpenRuleBak.getOpenCycleUnit());
//                            finCashCountingRule.setAccrDayType(finOpenRuleBak.getDayType());
//                            finCashCountingRule.setAccrDayValue(finOpenRuleBak.getDayValue());
//                            finCashCountingRule.setfAccrDate(entity.getfAccrDate());
//                            finfloatCountingRuleMapper.insert(finCashCountingRule);
//
//
//                    }
//                }
            }
            //add by jyl end
            //业绩基准表存储
            BussBenchMarkInfoDto bussBenchMarkInfoDto = entity.getBussBenchMarkInfoDto();
            if(bussBenchMarkInfoDto!=null && StringUtil.isNotEmpty(bussBenchMarkInfoDto.getRateType())){
            	FinBaseRuleInfo finBaseRuleInfo = new FinBaseRuleInfo();
            	BeanUtil.copyPropertiesIgnoreNull(finBaseRuleInfo, bussBenchMarkInfoDto);
            	if(StringUtil.isEmpty(finBaseRuleInfo.getBaseRuleId())){
            		finBaseRuleInfo.setBaseRuleId(CommonUtil.generateCashFlowCode(entity.getFinprodId(), null, entity.getBranch(), DictCodeListExt1.业绩基准类型_浮动管理费.getValue()));
            	}
            	//按照主键先删除在插入业绩基准信息
            	FinBaseRuleInfoKey finBaseRuleInfoKey = new FinBaseRuleInfoKey();
            	finBaseRuleInfoKey.setBaseRuleId(finBaseRuleInfo.getBaseRuleId());
            	finBaseRuleInfoKey.setEffDate(entity.getEffDate());
            	finBaseRuleInfoMapper.deleteByPrimaryKey(finBaseRuleInfoKey);
            	finBaseRuleInfo.setFinprodId(entity.getFinprodId());
            	finBaseRuleInfo.setEffDate(entity.getEffDate());
            	finBaseRuleInfo.setBranch(entity.getBranch());
            	finBaseRuleInfo.setBaseRuleType(DictCodeListExt1.业绩基准类型_浮动管理费.getValue());
            	if(finBaseRuleInfo.getRate()!=null){
            		finBaseRuleInfo.setRate(finBaseRuleInfo.getRate().divide(new BigDecimal(100)));
            	}
            	if (StringUtil.equals(finBaseRuleInfo.getRateType(),DictCodeList.利率类型_浮动利率.getValue())) {
                    MstIndexInfo mstIndexInfo = mstIndexInfoMapper.selectByPrimaryKey(
                        finBaseRuleInfo.getIndexId());
                    if (mstIndexInfo != null) {
                        finBaseRuleInfo.setIndexSource(mstIndexInfo.getIndexType());
                    }
                    else {
                        finBaseRuleInfo.setIndexSource(DictCodeListExt1.基准类型_2_利率.getValue());
                    }
                }
            	if(finBaseRuleInfo.getSpreadRate()!=null){
            		finBaseRuleInfo.setSpreadRate(finBaseRuleInfo.getSpreadRate().divide(new BigDecimal(100)));
            	}
            	finBaseRuleInfoMapper.insert(finBaseRuleInfo);
            }
            FinFloatCalcRuleKey key=new FinFloatCalcRuleKey();
            key.setCashId(entity.getCashId());
            key.setEffDate(entity.getEffDate());
            FinFloatCalcRule finFloatCalcRule=finFloatCalcRuleMapper.selectByPrimaryKey(key);
            List<FinFloatMgtExpQuotationDto> list=entity.getSelectedItem1();
            //判断有无数据存在
            if(StringUtil.isNotEmpty(finFloatCalcRule)){
                //更新
            	// 一体化对应etliu
//              finFloatCalcRule.setRelaeseDate(entity.getRelaeseDate());
                finFloatCalcRule.setRemark(entity.getRemark());
                finFloatCalcRuleMapper.updateByPrimaryKey(finFloatCalcRule);
            }else{//添加
                FinFloatCalcRule finFloatCalcRuleIns=new FinFloatCalcRule();
                finFloatCalcRuleIns.setCashId(entity.getCashId());
                finFloatCalcRuleIns.setEffDate(entity.getEffDate());

                // 一体化对应etliu start
//                finFloatCalcRuleIns.setRelaeseDate(entity.getRelaeseDate());
             // 一体化对应etliu end
                finFloatCalcRuleIns.setBaseRule(entity.getBaseRule());

                finFloatCalcRuleIns.setAstVdateType(entity.getAstVdateType());
                finFloatCalcRuleIns.setBaseType(entity.getBasicBill());
                finFloatCalcRuleIns.setBaseDateType(entity.getAstVdateType());
                finFloatCalcRuleIns.setBasis(entity.getBasis());
                finFloatCalcRuleIns.setRemark(entity.getRemark());
                finFloatCalcRuleIns.setCreateUser(entity.getCreateUser());
                finFloatCalcRuleIns.setUpdateUser(entity.getCreateUser());
                finFloatCalcRuleMapper.insert(finFloatCalcRuleIns);
            }
            //按层级先删除明细信息再保存明细信息
            if(!list.isEmpty()){
                FinFloatCalcRuleDetailKey detailKey=new FinFloatCalcRuleDetailKey();
                detailKey.setCashId(entity.getCashId());
                detailKey.setEffDate(entity.getEffDate());
                for(FinFloatMgtExpQuotationDto dto:list){
                    detailKey.setLev(dto.getLev());
                    finFloatCalcRuleDetailMapper.deleteByPrimaryKey(detailKey);
                }
                FinFloatCalcRuleDetail finFloatCalcRuleDetail=new FinFloatCalcRuleDetail();
                finFloatCalcRuleDetail.setCashId(entity.getCashId());
                finFloatCalcRuleDetail.setEffDate(entity.getEffDate());
                //根据金融产品代码得到起息日
//                FinFloatMgtExpQuotationDto vdateDto=getVdateAndMdate(entity.getFinprodId());
                //根据生效日日和产品代码得到最新的基准代码
                Map<String,Object> map=new HashMap<>();
                map.put("finprodId", entity.getFinprodId());
                map.put("effDate", DateUtil.format(entity.getEffDate()));
                map.put("BASE_RULE_TYPE_02", DictCodeListExt1.业绩基准类型_浮动管理费.getValue());
                List<FinFloatMgtExpQuotationDto> baseRuleIdDto=getIntRateById(map);
                for(FinFloatMgtExpQuotationDto dto:list){
                    finFloatCalcRuleDetail.setLev(dto.getLev());
                    finFloatCalcRuleDetail.setBaseRuleId(baseRuleIdDto.get(0).getBaseRuleId());
                    finFloatCalcRuleDetail.setCoefficient(dto.getCoefficient());
                    finFloatCalcRuleDetail.setSpreadRate(dto.getSpreadRate().divide(new BigDecimal(100)));
                    finFloatCalcRuleDetail.setChargeRatio(dto.getChargeRatio().divide(new BigDecimal(100)));
                    finFloatCalcRuleDetail.setUpdateUser(entity.getCreateUser());
                    finFloatCalcRuleDetail.setCreateUser(entity.getCreateUser());
                    finFloatCalcRuleDetailMapper.insert(finFloatCalcRuleDetail);
                }
            }
        }
        else{//修改
        	FinCashPayRuleKey payKey = new FinCashPayRuleKey();
            payKey.setCashId(entity.getCashId());
            payKey.setEffDate(entity.getEffDate());
            finCashPayRuleMapper.deleteByPrimaryKey(payKey);
        	FinCashPayRule finCashPayRule = new FinCashPayRule();
            finCashPayRule.setCashId(entity.getCashId());
            if (StringUtil.isNotEmpty(entity.getEffDate())) {
            	finCashPayRule.setEffDate(entity.getEffDate());
            }
            else {
            	finCashPayRule.setEffDate(platFormDate);
            }
            finCashPayRule.setPayCycle(entity.getPayCycle());
            finCashPayRule.setFirstPayDate(entity.getFirstPayDate());
            finCashPayRule.setEndTdayRule(entity.getEndTdayRule());
            finCashPayRule.setPayTdayRule(entity.getPayTdayRule());
            finCashPayRule.setFinprodId(entity.getFinprodId());
            finCashPayRule.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
            finCashPayRule.setBranch(entity.getBranch());
            finCashPayRule.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
            finCashPayRuleMapper.insert(finCashPayRule);
        	BussBenchMarkInfoDto bussBenchMarkInfoDto = entity.getBussBenchMarkInfoDto();
            if(bussBenchMarkInfoDto!=null && StringUtil.isNotEmpty(bussBenchMarkInfoDto.getRateType())){
            	FinBaseRuleInfo finBaseRuleInfo = new FinBaseRuleInfo();
            	BeanUtil.copyPropertiesIgnoreNull(finBaseRuleInfo, bussBenchMarkInfoDto);
            	FinBaseRuleInfoKey finBaseRuleInfoKey = new FinBaseRuleInfoKey();
            	finBaseRuleInfoKey.setBaseRuleId(bussBenchMarkInfoDto.getBaseRuleId());
            	finBaseRuleInfoKey.setEffDate(entity.getEffDate());
            	finBaseRuleInfoMapper.deleteByPrimaryKey(finBaseRuleInfoKey);
            	finBaseRuleInfo.setFinprodId(entity.getFinprodId());
            	finBaseRuleInfo.setEffDate(entity.getEffDate());
            	finBaseRuleInfo.setBranch(entity.getBranch());
            	finBaseRuleInfo.setBaseRuleType(DictCodeListExt1.业绩基准类型_浮动管理费.getValue());
            	if(finBaseRuleInfo.getRate()!=null){
            		finBaseRuleInfo.setRate(finBaseRuleInfo.getRate().divide(new BigDecimal(100)));
            	}
            	if (StringUtil.equals(finBaseRuleInfo.getRateType(),DictCodeList.利率类型_浮动利率.getValue())) {
                    MstIndexInfo mstIndexInfo = mstIndexInfoMapper.selectByPrimaryKey(
                        finBaseRuleInfo.getIndexId());
                    if (mstIndexInfo != null) {
                        finBaseRuleInfo.setIndexSource(mstIndexInfo.getIndexType());
                    }
                    else {
                        finBaseRuleInfo.setIndexSource(DictCodeListExt1.基准类型_2_利率.getValue());
                    }
                }
            	if(finBaseRuleInfo.getSpreadRate()!=null){
            		finBaseRuleInfo.setSpreadRate(finBaseRuleInfo.getSpreadRate().divide(new BigDecimal(100)));
            	}
            	finBaseRuleInfoMapper.insert(finBaseRuleInfo);
            }
            FinFloatCalcRuleKey key=new FinFloatCalcRuleKey();
            key.setCashId(entity.getCashId());
            key.setEffDate(entity.getEffDateOld());
            FinFloatCalcRule calcRule =finFloatCalcRuleMapper.selectByPrimaryKey(key);
            calcRule.setEffDate(entity.getEffDate());
            calcRule.setRemark(entity.getRemark());
            finFloatCalcRuleMapper.deleteByPrimaryKey(key);
            //生效日修改后添加
            finFloatCalcRuleMapper.insert(calcRule);
            //有层级时
            if(StringUtil.isNotEmpty(entity.getSelectedItem1()) ){
                FinFloatCalcRuleDetailKey detailKey=new FinFloatCalcRuleDetailKey();
                detailKey.setCashId(entity.getCashId());
                detailKey.setEffDate(entity.getEffDateOld());
                finFloatMgtExpQuotationMapper.deleteByPrimaryKey(detailKey);
                Map<String,Object> map=new HashMap<>();
                map.put("finprodId", entity.getFinprodId());
                map.put("effDate", DateUtil.format(entity.getEffDate()));
                map.put("BASE_RULE_TYPE_02", DictCodeListExt1.业绩基准类型_浮动管理费.getValue());
                List<FinFloatMgtExpQuotationDto> baseRuleIdDto=getIntRateById(map);
                for(FinFloatMgtExpQuotationDto dto:entity.getSelectedItem1()){
                    detailKey.setLev(dto.getLev());
                    //查出明细数据，删除再添加
                    FinFloatCalcRuleDetail ruleDetail = new FinFloatCalcRuleDetail();
                    //FinFloatCalcRuleDetail ruleDetail = finFloatCalcRuleDetailMapper.selectByPrimaryKey(detailKey);
                    ruleDetail.setBaseRuleId(baseRuleIdDto.get(0).getBaseRuleId());
                    ruleDetail.setCashId(entity.getCashId());
                    ruleDetail.setEffDate(entity.getEffDate());
                    ruleDetail.setLev(dto.getLev());
                    ruleDetail.setEffDate(entity.getEffDate());
                    ruleDetail.setCoefficient(dto.getCoefficient());
                    ruleDetail.setSpreadRate(dto.getSpreadRate().divide(new BigDecimal(100)));
                    ruleDetail.setChargeRatio(dto.getChargeRatio().divide(new BigDecimal(100)));
                    ruleDetail.setUpdateUser(entity.getCreateUser());
                    ruleDetail.setCreateUser(entity.getCreateUser());
                    finFloatCalcRuleDetailMapper.insert(ruleDetail);
                }
            }
            //标的类金融产品起息日修改
            /*FinProduct finProduct = finProductMapper.selectByPrimaryKey(entity.getFinprodId());
            finProduct.setVdate(entity.getEffDate());
            finProductMapper.updateByPrimaryKey(finProduct);*/
            //现金流计提规则
            //delete by wm 2019-11-06 修改时计提相关置灰不可修改 start
//            FinFloatCountingRuleKey countKey = new FinFloatCountingRuleKey();
//            countKey.setCashId(entity.getCashId());
//            countKey.setEffDate(entity.getEffDateOld());
//            FinFloatCountingRule countRule = finfloatCountingRuleMapper.selectByPrimaryKey(countKey);
//            countRule.setEffDate(entity.getEffDate());
//            countRule.seteAccrDate(entity.geteAccrDate());
//            countRule.setfAccrDate(entity.getfAccrDate());
//            finfloatCountingRuleMapper.deleteByPrimaryKey(countKey);
//            finfloatCountingRuleMapper.insert(countRule);
            //delete by wm 2019-11-06 修改时计提相关置灰不可修改 end
            //现金流支付规则
            /*FinCashPayRuleKey payKey = new FinCashPayRuleKey();
            payKey.setCashId(entity.getCashId());
            payKey.setEffDate(entity.getEffDateOld());
            FinCashPayRule payRule = finCashPayRuleMapper.selectByPrimaryKey(payKey);
            payRule.setEffDate(entity.getEffDate());
            finCashPayRuleMapper.deleteByPrimaryKey(payKey);
            finCashPayRuleMapper.insert(payRule);*/
        }
    }

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
     *
     * 产品代码时联动费用名称等
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public FinFloatMgtExpQuotationDto getFeeinfos(Map<String,Object> param){
		param.put("iplatformDate", DateUtil.getSysDate());//add by yejiejie 2019-2-15
        return finFloatMgtExpQuotationMapper.getFeeinfos(param);
    }
    /**
     * 费用名称联想框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFeeInfo(Map<String,Object> param){
        return finFloatMgtExpQuotationMapper.getFeeInfo(param);
    }
    /**
     * 产品代码，生效日联动利差等
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<FinFloatMgtExpQuotationDto> getRuleDetail(Map<String,Object> param){
    	FinFloatMgtExpQuotationDto dto = this.selectAlreadHave(param.get("finprodId").toString());
    	if(StringUtil.isNotEmpty(dto)){
    		param.put("cashId", dto.getCashId());
    		param.put("SESSION_SYS_DATE", DateUtil.getSysDate());//add by yejiejie 2019-2-15
    		return finFloatMgtExpQuotationMapper.getRuleDetail(param);
    	}else{
    		return null;
    	}

    }
    /**
     * 处理基准利率
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    private List<FinFloatMgtExpQuotationDto> select(Map<String, Object> pageParam) {
        List<FinFloatMgtExpQuotationDto> dtoList = finFloatMgtExpQuotationMapper.selectForPage(pageParam);
        if(dtoList != null && dtoList.size()>0){
            for(FinFloatMgtExpQuotationDto dto:dtoList){
                dto.setSESSION_SYS_DATE((String)pageParam.get("SESSION_SYS_DATE"));
                BigDecimal realIntRate=getIntRate(dto);
                //基准利率=基准利率*系数+利差
                if(dto.getCoefficient() == null){
                    if(dto.getSpreadRate() != null){
                        dto.setIntRate(realIntRate.add(dto.getSpreadRate()));
                    }else{
                        dto.setIntRate(realIntRate);
                    }
                }
                else if(dto.getCoefficient() != null && dto.getSpreadRate() != null){
                    dto.setIntRate(realIntRate.multiply(dto.getCoefficient()).add(dto.getSpreadRate()));
                }
            }
        }
        return dtoList;
    }
    /**
     * 得到基准利率
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal getIntRate(FinFloatMgtExpQuotationDto dto){
        BigDecimal realIntRate = BigDecimal.valueOf(0);
        BigDecimal intRate = BigDecimal.valueOf(0);
        //基准来源 = 利率行情时
        //从利率行情表中取得基准利率
        if(DictCodeListExt1.基准类型_2_利率.getValue().equals(dto.getIndexSource())){
            intRate = finFloatMgtExpQuotationMapper.getIntRate(dto.getIndexId());
            realIntRate = intRate;
        }
        //基准类型 = 复合基准时
        //查询出该基准代码所对应的所有行情信息
        else if(DictCodeListExt1.基准类型_2_复合.getValue().equals(dto.getIndexSource())){
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("indexId", dto.getIndexId());
            //设置平台日
//            param.put("SESSION_SYS_DATE", pageParam.get("SESSION_SYS_DATE"));
            param.put("SESSION_SYS_DATE", dto.getSESSION_SYS_DATE());
            //查出出该基准代码所对应的所有行情信息
            List<IntRateWeight> intRateWeightList = finFloatMgtExpQuotationMapper.getIntRateWeight(param);
            if(intRateWeightList != null && intRateWeightList.size()>0){
                //基准利率=基准利率+利率*权重
                for(IntRateWeight entity:intRateWeightList){
                    realIntRate = realIntRate.add(intRate.add(entity.getIntRate().multiply(entity.getWeight())));
                }
            }
        }
        return realIntRate;
    }
    /**
     *
     * 根据产品代码和生效日得到基准编号并得到基准利率
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<FinFloatMgtExpQuotationDto> getIntRateById(Map<String,Object> param){
//        FinFloatMgtExpQuotationDto dto = finFloatMgtExpQuotationMapper.getIndexId(param);
//        if(null != dto){
////            dto.setSESSION_SYS_DATE((String)param.get("SESSION_SYS_DATE"));
////            BigDecimal intRate=getIntRate(dto);
////            dto.setIntRate(intRate);
//            return dto;
//        }else{
//            return new FinFloatMgtExpQuotationDto();
//        }
        return finFloatMgtExpQuotationMapper.getIndexId(param);
    }
    /**
     * 根据生效日判断浮动管理费计提数量
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public int getFloatFeeCount(Map<String,Object> param){
        return finFloatMgtExpQuotationMapper.getFloatFeeCount(param);
    }
    /**
     * 查看，修改时的数据
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public FinFloatMgtExpQuotationDto selectDetail(Map<String,Object> param){
        return finFloatMgtExpQuotationMapper.selectDetail(param);
    }
    /**
     * 根据产品代码得到起息日和到期日
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinFloatMgtExpQuotationDto getVdateAndMdate(String finprodId){
        return finFloatMgtExpQuotationMapper.getVdateAndMdate(finprodId);
    }
    /**
     * 查询是否是存续期产品
     * @param finprodId
     * @return
     */
	public FinFloatMgtExpQuotationDto queryProductByProdId(String finprodId) {
		Map<String, Object> pageParam=new HashMap<String,Object>();
		pageParam.put("finprodId",finprodId);
		pageParam.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
		List<String> profitType = new ArrayList<String>();
        profitType.add(DictCodeList.收益类型_净值型.getValue());
        profitType.add(DictCodeList.收益类型_货币型.getValue());
        pageParam.put("profitTypeList", profitType);
		pageParam.put("ISSUE_STATUS01", DictCodeListExt1.发行状态_发行成功.getValue());
        pageParam.put("platDate", iPlatformDate.getSysDate());
		return finFloatMgtExpQuotationMapper.queryProductByProdId(pageParam);
	}
	public FinFloatMgtExpQuotationDto queryProductByProdIdAndProdStyle(Map<String, Object> param) {
		return finFloatMgtExpQuotationMapper.queryProductByProdIdAndProdStyle(param);
	}
	/**
     * 查看，修改时的数据的业绩基准信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public BussBenchMarkInfoDto selectBussBenchMarkInfo(Map<String,Object> param){
        return finFloatMgtExpQuotationMapper.selectBussBenchMarkInfo(param);
    }
    
    //ZGBSS-42 非首次新建浮动管理费报价时 生效日选择开放计划开放开始日+1天作为生效日 add by yujiaping 20210118 start
    public boolean checkeffDate(Map<String,Object> param) {
        if (StringUtil.isEmpty((String)param.get("dateStr")) || StringUtil.isEmpty((String)param.get("finprodId"))) {
            return false;
        }
        int dataCount = finFloatMgtExpQuotationMapper.checkeffDate(param);
        if (dataCount > 0) {
            return true;
        }
        return false;
    }
    //ZGBSS-42 非首次新建浮动管理费报价时 生效日选择开放计划开放开始日+1天作为生效日 add by yujiaping 20210118 end
    public String getopenMode(Map<String,Object> param) {       
        return finFloatMgtExpQuotationMapper.getopenMode(param);
    }
    public String getopenType(Map<String,Object> param) {       
        return finFloatMgtExpQuotationMapper.getopenType(param);
    }
    public String getisInitial(Map<String,Object> param) {       
        return finFloatMgtExpQuotationMapper.getisInitial(param);
    }
}