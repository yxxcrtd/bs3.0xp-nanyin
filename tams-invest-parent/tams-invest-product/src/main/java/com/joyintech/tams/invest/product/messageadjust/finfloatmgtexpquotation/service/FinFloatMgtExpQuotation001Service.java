/*
 * 文件名：FinFloatMgtExpQuotation001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finfloatmgtexpquotation.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
//import com.joyintech.tams.common.base.dao.FinCashPlanMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussFinProductFeeInfoService;
//import com.joyintech.tams.component.business.prod.BussFinProductFeeInfoService;
import com.joyintech.tams.component.business.prod.BussFinProductFloatFeeService;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkInfoDto;
//import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFloatFeeDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.calculator.AdjustedTradeDayCal;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.messageadjust.finfloatmgtexpquotation.dto.FinFloatMgtExpQuotation001Dto;
import com.joyintech.tams.invest.product.productelement.build.dto.FinProductBuild002Dto;
import com.joyintech.tams.invest.product.productelement.build.service.FinProductBuild002Service;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 信息调整管理_浮动管理费报价管理Service
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2018年10月11日
 * @see FinFloatMgtExpQuotation001Service
 * @since 1.0
 */
@Service
public class FinFloatMgtExpQuotation001Service extends CommonExportService<FinFloatMgtExpQuotation001Dto> 
    implements BaseProcessService<FinFloatMgtExpQuotation001Dto>{
    /**
     * {@linkplain}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductBuild002Service finProductBuild002Service;
    /**
     * {@linkplain}
     */
    @Autowired
    private BussFinProductFeeInfoService bussFinProductFeeInfoService;
    /**
     * 共同组件
     */
    @Autowired(required =false  )
    private CommonBusinessService commonBusinessService;
    
    /**
     * 标的类金融茶品
     */
    @Autowired
    private FinProductMapper finProductMapper;
//    @Autowired
//    private FinCashPlanMapper finCashPlanMapper;
    /**
     * 浮动管理费
     */
    @Autowired
    private BussFinProductFloatFeeService bussFinProductFloatFeeService;
    /**
     * 付息计划，开放计划，利率重置，申购计划，赎回计划计算用和日期关联的Service
     */
    @Autowired
    private DateCalService dateCalService;
    /**
     * 产品代码联想下拉框
     * @param param String
     * @return List<SelectOptionDto>
     */
    public List<SelectOptionDto> getFinprodIds(Map<String, Object> param) {
        //数据字典.金融产品类型.理财产品, 数据字典.金融产品类型.理财产品分层
        /*Map<String,Object> param=new HashMap<String,Object>();
        param.put("finprodId", finprodId);*/
        param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        //add by cxq 查询和新建应该过滤没有发行成功的产品。 BSTOO-1037
        param.put("issueStatus", DictCodeList.发送状态_发送成功.getValue());
        //end
        List<String> list = new ArrayList<String>();
        list.add(DictCodeList.收益类型_净值型.getValue());
        list.add(DictCodeList.收益类型_货币型.getValue());
        param.put("profitTypeList", list);
        param.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
                + "/FinFloatMgtExpQuotation/getFinprodId",param,SelectOptionDto.class);      
    }
    /**
     * 产品代码联想下拉框
     * @param param String
     * @return List<SelectOptionDto>
     */
    public List<SelectOptionDto> getFinprodIdsByDate(Map<String,Object> param) {
        //数据字典.金融产品类型.理财产品, 数据字典.金融产品类型.理财产品分层
        param.put("finprodId", param.get("finprodId"));
        param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        //add by cxq 查询和新建应该过滤没有发行成功的产品。 BSTOO-1037
        param.put("issueStatus", DictCodeList.发送状态_发送成功.getValue());
        //end
        List<String> list = new ArrayList<String>();
        list.add(DictCodeList.收益类型_净值型.getValue());
        list.add(DictCodeList.收益类型_货币型.getValue());
        param.put("profitTypeList", list);
        param.put("SESSION_SYS_DATE", DateUtil.getSysDate());
        if(!StringUtil.isNotEmpty(param.get("initValue"))){
            param.put("userId", SecurityUtil.getLoginUser().getUserName());
        }
        param.put("initValue", null);
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
                + "/FinFloatMgtExpQuotation/getFinprodId",param,SelectOptionDto.class);      
    }
    /**
     * 
     * 浮动管理费报价管理-查询
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<FinFloatMgtExpQuotation001Dto>
     * @see
     * @since 1.0
     */
    public Page<FinFloatMgtExpQuotation001Dto> selectForPage(Map<String, Object> pageParam){
        //得到产品代码
        String finprodId = (String)pageParam.get("finprodId");
        if(StringUtil.isNotEmpty(finprodId)){
            pageParam.put("finprodIdList", StringUtil.splitToList(GlobalConstant.COMMA,finprodId));
        }
        String prodStyle = (String)pageParam.get("prodStyle");//支持分层
        if(StringUtil.isNotEmpty(prodStyle)){
            pageParam.put("prodStyleList", StringUtil.splitToList(GlobalConstant.COMMA,prodStyle));
        }
        pageParam.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        List<String> profitType = new ArrayList<String>();
        profitType.add(DictCodeList.收益类型_净值型.getValue());
        profitType.add(DictCodeList.收益类型_货币型.getValue());
        pageParam.put("profitTypeList", profitType);
        pageParam.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        pageParam.put("userId", SecurityUtil.getLoginUser().getUserName());
        pageParam.put("SESSION_SYS_DATE", DateUtil.getSysDate());
        //add by cxq 查询和新建应该过滤没有发行成功的产品。 BSTOO-1037
        pageParam.put("issueStatus", DictCodeList.发送状态_发送成功.getValue());
        //end
        return restTemplate.postForPage(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinFloatMgtExpQuotation/selectForPage", pageParam,
                FinFloatMgtExpQuotation001Dto.class);
    }
    
    /**
     * 
     * 浮动管理费报价管理-查询
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<FinFloatMgtExpQuotation001Dto>
     * @see
     * @since 1.0
     */
    public List<FinFloatMgtExpQuotation001Dto> selectForList(Map<String, Object> pageParam){
        //得到产品代码
        String finprodId = (String)pageParam.get("finprodId");
        if(StringUtil.isNotEmpty(finprodId)){
            pageParam.put("finprodIdList", StringUtil.splitToList(GlobalConstant.COMMA,finprodId));
        }
        String prodStyle = (String)pageParam.get("prodStyle");//支持分层
        if(StringUtil.isNotEmpty(prodStyle)){
            pageParam.put("prodStyleList", StringUtil.splitToList(GlobalConstant.COMMA,prodStyle));
        }
        pageParam.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        List<String> profitType = new ArrayList<String>();
        profitType.add(DictCodeList.收益类型_净值型.getValue());
        profitType.add(DictCodeList.收益类型_货币型.getValue());
        pageParam.put("profitTypeList", profitType);
        pageParam.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        pageParam.put("userId", SecurityUtil.getLoginUser().getUserName());
        pageParam.put("SESSION_SYS_DATE", DateUtil.getSysDate());
        pageParam.put("issueStatus", DictCodeList.发送状态_发送成功.getValue());
        return restTemplate.postForList(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinFloatMgtExpQuotation/selectForList", pageParam,
                FinFloatMgtExpQuotation001Dto.class);
    }
    
    /**
     * 查看，修改时的数据
     * <功能详细描述>
     * @param finprodId String
     * @param effDate String
     * @param cashId String
     * @return FinFloatMgtExpQuotation001Dto
     * @see
     * @since 1.0
     */
    public FinFloatMgtExpQuotation001Dto selectDetail(String finprodId, String effDate ,String cashId){
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("finprodId", finprodId);
        param.put("effDate", effDate);
        param.put("cashId", cashId);
        param.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        return restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinFloatMgtExpQuotation/selectDetail", param,
                FinFloatMgtExpQuotation001Dto.class);
        
    }
    /**
     * 新建查询同一代码、生效日、投资周期下是否已存在
     * <功能详细描述>
     * @param finprodId String
     * @param effDate String
     * @param cashId String
     * @return FinFloatMgtExpQuotation001Dto
     * @see
     * @since 1.0
     */
    public FinFloatMgtExpQuotation001Dto selectManaFeeDetail(String finprodId, String effDate ,String investmentCycle){
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("finprodId", finprodId);
        param.put("effDate", effDate);
        param.put("investmentCycle", investmentCycle);
        param.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        return restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinFloatMgtExpQuotation/selectDetail", param,
                FinFloatMgtExpQuotation001Dto.class);
        
    }
    /**
     * 判断现金流信息是否存在
     * @param finprodId
     * @return
     */
    public FinFloatMgtExpQuotation001Dto selectAlreadHave(String finprodId){
    	return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                + "/FinFloatMgtExpQuotation/selectAlreadHave?finprodId="
                + finprodId,
                FinFloatMgtExpQuotation001Dto.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinFloatMgtExpQuotation001Dto entity, OperationType type) {
        
        entity.setBranch(Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        entity.setOperateType(type.toString());
        //判断有无现金流代码
        if(StringUtil.isEmpty(entity.getCashId())){
            //调用组件生成现金流代码
            String cashId=commonBusinessService.generateCashFlowCode(entity.getFinprodId(), 
                DictCodeListExt1.现金流业务类型_金融产品.getValue(),
                Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)), 
                DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
            entity.setCashId(cashId);        
        }
        entity.setUpdateUser(LoginUserUtil.getUserName());
        //新建
        if(StringUtil.equals(type.toString(), "NEW")){
        	//查询现金流信息
            FinFloatMgtExpQuotation001Dto finFloatMgtExpQuotation001Dto = this.selectAlreadHave(entity.getFinprodId());
        	//查询产品基本信息
            FinProductBuild002Dto finProductBuild002Dto = finProductBuild002Service.queryProdInfoByFinprodId(
    				StringUtil.isNotEmpty(entity.getpFinprodId())?entity.getpFinprodId():entity.getFinprodId());
        	//若无现金流信息
            List<FinCashPlan> feePlanList_insert = new ArrayList<FinCashPlan>();
            if(finFloatMgtExpQuotation001Dto ==null){
        		//产品.交易日历
//        		BussFinProductFeeInfoDto feeInfo= new BussFinProductFeeInfoDto();
                BussFinProductFloatFeeDto bussFinProductFloatFeeDto = new BussFinProductFloatFeeDto();
        		//根据费用信息生成付费计划
                if (!StringUtil.equals(entity.getPayCycle(),DictCodeList.频率_不规则.getValue())) {
                    BeanUtil.copyPropertiesIgnoreNull(bussFinProductFloatFeeDto, entity);
                    //起息日
                    bussFinProductFloatFeeDto.setVdate(finProductBuild002Dto.getVdate());
                    //到期日
                    bussFinProductFloatFeeDto.setMdate(finProductBuild002Dto.getMdate());
                    //交易日历
                    bussFinProductFloatFeeDto.setDealCalId(finProductBuild002Dto.getCalendarId());
                    
        			//付费规则
//        			feeInfo.setPayFeeRule(entity.getPayCycle());
//        			//首次计提日
//        			feeInfo.setProvisionFirstDay(entity.getfAccrDate());
//        			//收益类型
//        			feeInfo.setProfitType(entity.getProfitType());
//        			//到期日
//        			feeInfo.setVdate(entity.getfAccrDate());
//        			//到期日
//        			feeInfo.setMdate(entity.geteAccrDate());
        			
                    entity.setMdate(entity.geteAccrDate());
//                    feeInfo.setFirstCalEndDate(entity.geteAccrDate());
//        			//交易日历
//        			feeInfo.setDealCalId(finProductBuild002Dto.getCalendarId());
//        			//计费营业日准则
//        			feeInfo.setCalcFeeTradeRule(entity.getEndTdayRule());
//        			//付费营业日准则
//        			feeInfo.setPayFeeTradeRule(entity.getPayTdayRule());
//        			
//        			feeInfo.seteAccrDate(entity.geteAccrDate());
        			//新生成的付费计划
                    List<FinCashPlan> feePlanList_new = bussFinProductFloatFeeService.generateFloatFeePlan(bussFinProductFloatFeeDto);
                    for (FinCashPlan finCashPlan : feePlanList_new) {
                    	finCashPlan.setCashId(entity.getCashId());
                    	finCashPlan.setFinprodId(entity.getFinprodId());
                    	finCashPlan.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
                    	finCashPlan.setBranch(Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
//                        finCashPlanMapper.insert(finCashPlan);
                    	feePlanList_insert.add(finCashPlan);
                    }
                }
            }
            else{

        		//产品.交易日历
                BussFinProductFloatFeeDto bussFinProductFloatFeeDto = new BussFinProductFloatFeeDto();
        		//根据费用信息生成付费计划
                if (!StringUtil.equals(entity.getPayCycle(),DictCodeList.频率_不规则.getValue())) {
                    BeanUtil.copyPropertiesIgnoreNull(bussFinProductFloatFeeDto, entity);
                    //起息日
                    bussFinProductFloatFeeDto.setVdate(finProductBuild002Dto.getVdate());
                    //到期日
                    bussFinProductFloatFeeDto.setMdate(finProductBuild002Dto.getMdate());
                    //交易日历
                    bussFinProductFloatFeeDto.setDealCalId(finProductBuild002Dto.getCalendarId());
                    
                    entity.setMdate(entity.geteAccrDate());
                    //旧的付费计划
                    List<FinCashPlan> feePlanList_old = bussFinProductFeeInfoService.queryFeePlanListByFinprodId(entity.getFinprodId(),
                    		entity.getFeeId());
        			//新生成的付费计划
                    List<FinCashPlan> feePlanList_new = bussFinProductFloatFeeService.generateFloatFeePlan(bussFinProductFloatFeeDto);
                    boolean mdateFlag = true;
                    int count = 0;
                    for (FinCashPlan finCashPlan : feePlanList_old) {
                        if (finCashPlan.getMdate().compareTo(entity.getFirstPayDate()) <= 0) {
                            feePlanList_insert.add(finCashPlan);

                            if (finCashPlan.getMdate().compareTo(entity.getFirstPayDate()) == 0) {
                                mdateFlag = false;
                            }
                            count++;
                        }
                        else if (mdateFlag) {
                        	count++;
                            finCashPlan.setMdate(entity.getFirstPayDate());
                            finCashPlan.setMdateUnadjust(entity.getFirstPayDate());

                            // 交易日历取得
                            List<String> dealCalendarHolidayList = dateCalService.getDealCalendarHoliday(entity.getMdate(),
                                DateUtil.addDay(entity.getFirstPayDate(), 8), finProductBuild002Dto.getCalendarId());
                            // 支付日根据营业日准则进行调整
                            Date tradePaymentDay = DateUtil.parse(AdjustedTradeDayCal.getAdjustedTradeDay(
                                DateUtil.format(entity.getFirstPayDate()), entity.getPayTdayRule(), dealCalendarHolidayList));

                            finCashPlan.setPayDate(tradePaymentDay);
                            finCashPlan.setPayDateUnadjust(tradePaymentDay);
                            finCashPlan.setUpdateUser(LoginUserUtil.getUserName());
                            finCashPlan.setUpdateTime(new Date());
                            feePlanList_insert.add(finCashPlan);
                            break;
                        }
                    }
                    for (FinCashPlan finCashPlan : feePlanList_new) {
                    	if(finCashPlan.getMdate().compareTo(entity.getFirstPayDate()) <= 0){
                    	    continue;
                    	}
                    	count++;
                    	finCashPlan.setCashNum((long)count);
                    	finCashPlan.setCashType(DictCodeList.现金流类型_费用支付.getValue());
                    	finCashPlan.setCashId(entity.getCashId());
                    	finCashPlan.setFinprodId(entity.getFinprodId());
                    	finCashPlan.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
                    	finCashPlan.setBranch(Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
//                        finCashPlanMapper.insert(finCashPlan);
                    	feePlanList_insert.add(finCashPlan);
                    }
//                    for (FinCashPlan finCashPlan : feePlanList_insert) {
//                    	finCashPlanMapper.insert(finCashPlan);
//                    }
                }
            
            }
            entity.setFinCashPlanList(feePlanList_insert);
        }
        
        restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinFloatMgtExpQuotation/doComplete",
            entity, Integer.class);
    }
    /**
     * 导出
     * {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
    	PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId("FinFloatMgtExpQuotation001");
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto); 
         String value1 = isEleShow.get("FinFloatMgtExpQuotation001_sendStatus");
         String value2 = isEleShow.get("send_remark_table");
        if(StringUtil.equals(value1, DictCodeList.是否_是.getValue())&&StringUtil.equals(value2, DictCodeList.是否_是.getValue())) {
        	 exportParam.config("/com/joyintech/tams/invest/product/messageadjust/finfloatmgtexpquotation/conf/export_conf.xml")
             .dataSource(DataSourceType.MASERT);
        }else if(StringUtil.equals(value1, DictCodeList.是否_否.getValue())&&StringUtil.equals(value2, DictCodeList.是否_否.getValue()) ) {
        	 exportParam.config("/com/joyintech/tams/invest/product/messageadjust/finfloatmgtexpquotation/conf/export_conf_N.xml")
             .dataSource(DataSourceType.MASERT);
        } 
    }
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Page<FinFloatMgtExpQuotation001Dto> selectPageData(Map<String, Object> selectParam) {
        
        return selectForPage(selectParam);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals("intRate", propName)) {
            cell.setCellValue("业绩基准");
        }
    }
    
    /**
     * 新建页面画面联动
     * <功能详细描述>
     * @param finprodId 
     * @return FinFloatMgtExpQuotation001Dto
     * @see
     * @since 1.0
     */
    public FinFloatMgtExpQuotation001Dto getFeeinfos(String finprodId){
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("finprodId", finprodId);
        param.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        return restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinFloatMgtExpQuotation/getFeeinfos",
                param, FinFloatMgtExpQuotation001Dto.class); 
    }
    /**
     * 费用名称联想框
     * <功能详细描述>
     * @param feeId String
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFeeInfo(String feeId){
//        StringUtil.defaultString(feeId)
        Map<String, Object> map=new HashMap<>();
        map.put("feeId", feeId);
        map.put("feeType", DictCodeList.费用品种类型_浮动管理费.getValue());
        return restTemplate.postForList(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinFloatMgtExpQuotation/getFeeInfo" , map, SelectOptionDto.class); 
    }
    
    /**
     * 市场代码和生效日联动利差等
     * <功能详细描述>
     * @param finprodId String
     * @param effDate String
     * @param lev String
     * @return List<FinFloatMgtExpQuotation001Dto>
     * @see
     * @since 1.0
     */
    public List<FinFloatMgtExpQuotation001Dto> getRuleDetail(String finprodId,String effDate, String lev,String pageModel){
        Map<String,Object> param=new HashMap<String,Object>();
        if(StringUtil.isEmpty(finprodId)){
            return new ArrayList<FinFloatMgtExpQuotation001Dto>();
        }
        else{
            param.put("finprodId", finprodId);
            param.put("effDate", effDate);
            param.put("lev", "");
            param.put("pageModel",pageModel);
            param.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
            param.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
            return restTemplate.postForList(
                    ServiceApplication.PRODUCT_SERVICE_NAME + "/FinFloatMgtExpQuotation/getRuleDetail",
                    param, FinFloatMgtExpQuotation001Dto.class); 
        }
    }
    /**
     * 流程中的数据查找
     * <功能详细描述>
     * @param workflowId String
     * @param beanName String
     * @return List<FinFloatMgtExpQuotation001Dto>
     * @see
     * @since 1.0
     */
    public List<FinFloatMgtExpQuotation001Dto> ruleDetailQueryByWflId(String workflowId,
            String beanName){
        List<FinFloatMgtExpQuotation001Dto> resList = new ArrayList<FinFloatMgtExpQuotation001Dto>();
        // 父画面传入的工作流代码
        FinFloatMgtExpQuotation001Dto dto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
            workflowId, beanName, FinFloatMgtExpQuotation001Dto.class);
        if (dto != null) {
            resList = dto.getSelectedItem1();
        }
        return resList;
    }
    /**
     * 根据生效日判断浮动管理费计提数量
     * <功能详细描述>
     * @param finprodId String
     * @param effDate String
     * @return int
     * @see
     * @since 1.0
     */
    public int getFloatFeeCount(String finprodId,String effDate){
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("finprodId", finprodId);
        param.put("effDate", effDate);
     // 一体化对应etliu start
//        param.put("cashType2", DictCodeListExt2.费用类型_浮动管理费.getValue());
        param.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
     // 一体化对应etliu end
        return restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinFloatMgtExpQuotation/getFloatFeeCount",
                param, Integer.class); 
    }
    /**
     * 根据产品代码得到起息日和到期日
     * <功能详细描述>
     * @param finprodId String
     * @return FinFloatMgtExpQuotation001Dto
     * @see
     * @since 1.0
     */
    public FinFloatMgtExpQuotation001Dto getVdateAndMdate(String finprodId){
        return restTemplate.getForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinFloatMgtExpQuotation/getVdateAndMdate?finprodId=" +finprodId, 
                FinFloatMgtExpQuotation001Dto.class);
    }
    
    /**
     * 
     * 获取标的类金融产品
     * <功能详细描述>
     * @param finprodId String
     * @return FinProduct
     * @see
     * @since 1.0
     */
    public FinProduct getFinProduct(String finprodId){
        return finProductMapper.selectByPrimaryKey(finprodId);
    }
    
    /**
     * 
     * 根据产品代码和生效日得到基准编号并得到基准利率
     * <功能详细描述>
     * @param param Map<String,Object>
     * @return List<FinFloatMgtExpQuotationDto>
     * @see
     * @since 1.0
     */
    public List<FinFloatMgtExpQuotation001Dto> getIntRateById(Map<String,Object> param){
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinFloatMgtExpQuotation/getIntRateById", param,
            FinFloatMgtExpQuotation001Dto.class);
    }
    /**
     * 查询是否是存续期产品
     * @param finprodId
     * @return
     */
	public FinFloatMgtExpQuotation001Dto queryProductByProdId(String finprodId) {
		return restTemplate.getForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/FinFloatMgtExpQuotation/queryProductByProdId?finprodId=" +finprodId, 
		        FinFloatMgtExpQuotation001Dto.class);
		
		
	}
	 /**
     * 费用名称联想框
     * <功能详细描述>
     * @param finprodId String
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryLayerProdInfoByFinprodId(String finprodId){
        Map<String, Object> map=new HashMap<>();
        map.put("finprodId", finprodId);
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("branch", Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBuild/queryLayerProdInfoByFinprodId",
            map, SelectOptionDto.class);
    }

    /**
     * 获取分层产品产品款型
	* @param map
	* @return
	*/
    public List<SelectOptionDto> queryLayerProdStyleList(Map<String,Object> map){
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBuild/getLayerParamFinprodIdList",
            map, SelectOptionDto.class);
    }
    
    /**
     * 查询是否是存续期产品
     * @param dto
     * @return
     */
	public FinFloatMgtExpQuotation001Dto queryProductByProdIdAndProdStyle(FinFloatMgtExpQuotation001Dto dto) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("finprodId", dto.getFinprodId());
		map.put("pFinprodId", dto.getpFinprodId());
		map.put("prodStyle", dto.getProdStyle());
		return restTemplate.postForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/FinFloatMgtExpQuotation/queryProductByProdIdAndProdStyle",map, 
		        FinFloatMgtExpQuotation001Dto.class);	
	}
	
	
	@SuppressWarnings("unchecked")
    public Map<String,String> sendFloatMgtExpQuotationSale(List<String> finProdIds) {
        return restTemplate.postForObject(
                ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/FinProductInterf/sendFloatMgtExpQuotationSale", finProdIds,
                Map.class);
    }
	/**
	 * 浮动业绩基准查询
	 * @return
	 */
	public List<SelectOptionDto> getFinBaseRuleInfo() {
	    return restTemplate.getForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/BenchMarketInfo/getFinBaseRuleInfo",
            SelectOptionDto.class);
	}
	/**
     * 查看，修改时的浮动管理费的业绩基准
     * <功能详细描述>
     * @param finprodId String
     * @param effDate String
     * @return FinFloatMgtExpQuotation001Dto
     * @see
     * @since 1.0
     */
    public BussBenchMarkInfoDto selectBussBenchMarkInfo(String finprodId, String effDate){
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("finprodId", finprodId);
        param.put("effDate", effDate);
        param.put("baseRuleType02", DictCodeListExt1.业绩基准类型_浮动管理费.getValue());
        param.put("sysFloatDate", DateUtil.getSysDate());
        return restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinFloatMgtExpQuotation/selectBussBenchMarkInfo", param,
                BussBenchMarkInfoDto.class);
        
    }
    
    //ZGBSS-42 非首次新建浮动管理费报价时 生效日选择开放计划开放开始日+1天作为生效日 add by yujiaping 20210118 start
    public boolean checkeffDate(String dateStr, String finprodId) {
    	Map<String,Object> param=new HashMap<String,Object>();
    	param.put("finprodId", finprodId);
    	param.put("dateStr", dateStr);
    	return restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinFloatMgtExpQuotation/checkeffDate", param,
                boolean.class);
    }
    //ZGBSS-42 非首次新建浮动管理费报价时 生效日选择开放计划开放开始日+1天作为生效日 add by yujiaping 20210118 end
    
    /**
     * 获取开放模式 <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    public String getopenMode(String finprodId) {
    	Map<String,Object> param=new HashMap<String,Object>();
    	param.put("finprodId", finprodId);
    	return restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinFloatMgtExpQuotation/getopenMode", param,
                String.class);
    }
    
    /**
     * 获取发行方式 <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    public String getopenType(String finprodId) {
    	Map<String,Object> param=new HashMap<String,Object>();
    	param.put("finprodId", finprodId);
    	return restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinFloatMgtExpQuotation/getopenType", param,
                String.class);
    }
    
    /**
     * 获取是否是初始化生成的产品浮动管理费 <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    public String getisInitial(String finprodId,String effDate) {
    	Map<String,Object> param=new HashMap<String,Object>();
    	param.put("finprodId", finprodId);
    	param.put("effDate", effDate);
    	return restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinFloatMgtExpQuotation/getisInitial", param,
                String.class);
    }
}