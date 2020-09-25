/*
 * 文件名：FinProductBuild001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.netvalueproduct.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.SysDataUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productelement.build.dto.FinProductBuild001Dto;
import com.joyintech.tams.invest.product.productelement.netvalueproduct.dto.FinNetValueProductBuild001Dto;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 产品新建管理
 * 
 * @author zhangliang
 * @version 1.0 2018年9月17日
 * @see FinNetValueProductBuild001Service
 * @since 1.0
 */
@Service
public class FinNetValueProductBuild001Service extends CommonExportService<FinNetValueProductBuild001Dto> implements BaseProcessService<FinNetValueProductBuild001Dto> {
    /**
     * {@link MstWorkflowCommonService}
     */
	 @Autowired
	    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doComplete(FinNetValueProductBuild001Dto entity, OperationType arg1) {
        restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                  + "/FinProductCommon/deleteFinProductCommon?finprodId="
                                  + entity.getFinprodId(),
            Integer.class);
    }

    /**
     * 查询排期所有数据
     * 
     * @param map 参数map
     * @return 排期所有数据
     * @see
     * @since 1.0
     */
    public Page<FinNetValueProductBuild001Dto> selectForPage(Map<String, Object> map) {
        //OPEX-856 【净值化产品管理】“投资性质”填入查询出结果后，然后清空这个筛选框。
        // 再次查询，查询结果还是之前填入“投资性质”时的情况。 guyunfei  20200214 start
        if (StringUtil.isNotEmpty(map.get("prodId"))) {
            map.put("prodId", StringUtil.splitToList(null, map.get("prodId").toString()));
        }else{
            map.put("prodId",new ArrayList<>());
        }
        if (StringUtil.isNotEmpty(map.get("groupId"))) {
            map.put("groupId", StringUtil.splitToList(null, map.get("groupId").toString()));
        }else{
            map.put("groupId",new ArrayList<>());
        }
        if (StringUtil.isNotEmpty(map.get("profitFlag"))) {
            map.put("profitFlag", StringUtil.splitToList(null, map.get("profitFlag").toString()));
        }else{
            map.put("profitFlag",new ArrayList<>());
        }
        if (StringUtil.isNotEmpty(map.get("issueType"))) {
            map.put("issueType", StringUtil.splitToList(null, map.get("issueType").toString()));
        }else{
            map.put("issueType", new ArrayList<>());
        }
        if(StringUtil.isNotEmpty(map.get("operationType"))){
            map.put("operationType", StringUtil.splitToList(null, map.get("operationType").toString()));
        }else{
            map.put("operationType",new ArrayList<>());
        }
        if(StringUtil.isNotEmpty(map.get("investNature"))){
            map.put("investNature", StringUtil.splitToList(null, map.get("investNature").toString()));
        }else{
            map.put("investNature",new ArrayList<>());
        }
		if(StringUtil.isNotEmpty(map.get("prodRegistCode"))){
            map.put("prodRegistCode", StringUtil.splitToList(null, map.get("prodRegistCode").toString()));
        }else{
            map.put("prodRegistCode",new ArrayList<>());
        }
        if(StringUtil.isNotEmpty(map.get("targetCustomType"))){
            List<String> targetCustomTypes = StringUtil.splitToList(null, map.get("targetCustomType").toString());
            Collections.sort(targetCustomTypes);  
            map.put("targetCustomTypeStr", StringUtil.joinString(targetCustomTypes,",")); 
            map.put("targetCustomType", targetCustomTypes);
        }else{
            map.put("targetCustomType",new ArrayList<>());
        }
        if(StringUtil.isNotEmpty(map.get("issueStatus"))){
            map.put("issueStatus", StringUtil.splitToList(null, map.get("issueStatus").toString()));
        }else{
            map.put("issueStatus",new ArrayList<>());
        }
        //OPEX-856 【净值化产品管理】“投资性质”填入查询出结果后，然后清空这个筛选框。
        // 再次查询，查询结果还是之前填入“投资性质”时的情况。 guyunfei  20200214 end

        // add by wangyang1 【吉林银行】 增加 产品经理  2019年9月29日 start
        if(StringUtil.isNotEmpty(map.get("prodManager"))){
            map.put("prodManager", StringUtil.splitToList(null, map.get("prodManager").toString()));
        } else {
            map.put("prodManager",null);
        }
       // add by wangyang1 【吉林银行】 增加 产品经理  end 
        map.put("qryFlag", "1");
        map.put("userId", SecurityUtil.getLoginUser().getUserName());
        map.put("branch", Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        Page<FinNetValueProductBuild001Dto> pageDto = restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBuild/selectForPage", map,
            FinNetValueProductBuild001Dto.class);
        List<FinNetValueProductBuild001Dto> ldto = pageDto.getRoot();
        // 将后台传来的值转换成数据字典值
        for (FinNetValueProductBuild001Dto mdto : ldto) {
            //销售渠道
            List<String> slist = new ArrayList<String>();
            List<String> saleChannelList = StringUtil.splitToList(GlobalConstant.COMMA,
                    mdto.getSaleChannel());
            for (int i = 0; i < saleChannelList.size(); i++ ) {
                if (StringUtil.isNotEmpty(saleChannelList.get(i))) {
                    String str = SysDataUtil.getNameFromDataDict(DictCode.销售渠道, saleChannelList.get(i));
                    slist.add(str);
                }
            }
            String saleChannel = StringUtil.joinString(slist, GlobalConstant.COMMA);
            mdto.setSaleChannel(saleChannel);

            //销售部门
            List<String> slist1 = new ArrayList<String>();
            List<String> saleDepartmentList = StringUtil.splitToList(GlobalConstant.COMMA,
                    mdto.getSaleDepartment());
            for (int i = 0; i < saleDepartmentList.size(); i++ ) {
                if (StringUtil.isNotEmpty(saleDepartmentList.get(i))) {
                    String str = SysDataUtil.getNameFromDataDict(DictCode.销售部门, saleDepartmentList.get(i));
                    slist1.add(str);
                }
            }
            String saleDepartment = StringUtil.joinString(slist1, GlobalConstant.COMMA);
            mdto.setSaleDepartment(saleDepartment);

            //目标客户类型
            List<String> slist2 = new ArrayList<String>();
            List<String> repInvestorTypeList = StringUtil.splitToList(GlobalConstant.COMMA,
                    mdto.getRepInvestorType());
            for (int i = 0; i < repInvestorTypeList.size(); i++ ) {
                if (StringUtil.isNotEmpty(repInvestorTypeList.get(i))) {
                    String str = SysDataUtil.getNameFromDataDict(DictCode.目标客户类型, repInvestorTypeList.get(i));
                    slist2.add(str);
                }
            }
            String repInvestorType = StringUtil.joinString(slist2, GlobalConstant.COMMA);
            mdto.setRepInvestorType(repInvestorType);
            mdto.setRateType(SysDataUtil.getNameFromDataDict(DictCode.利率类型,mdto.getRateType()));
        }
        return pageDto;
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/product/productelement/netvalueproduct/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinNetValueProductBuild001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 根据产品代码模糊查询联系下拉框
     * 
     * @param map 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryGroupIdInfo(Map<String, Object> map) {
        map.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBuild/queryGroupIdInfo", map,
            SelectOptionDto.class);
    }

    /**
     * 根据产品代码模糊查询联系下拉框
     * 
     * @param map 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryProdIdInfo(Map<String, Object> map) {
        map.put("userId", SecurityUtil.getLoginUser().getUserName());
        map.put("qryFlag","1");//add by zhangweiqi 查询净值化产品
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBuild/queryProdIdInfo", map,
            SelectOptionDto.class);
    }

    /**
     * 根据金融产品代码查询理财产品排期信息
     * 
     * @param finprodId 金融产品代码
     * @return 查询个数
     * @see
     * @since 1.0
     */
    public int querySchedulingCountByFinprodId(String finprodId) {
        return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                         + "/FinProductBuild/querySchedulingCountByFinprodId?finprodId="
                                         + finprodId,
            Integer.class);
    }

    /**
     * 根据金融产品代码查询标的类金融产品信息
     * 
     * @param finprodId 金融产品代码
     * @return 查询个数
     * @see
     * @since 1.0
     */
    public int queryProdCountByFinprodId(String finprodId) {
        return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                         + "/FinProductBuild/queryProdCountByFinprodId?finprodId="
                                         + finprodId,
            Integer.class);
    }
    /**
     * 信息调整流程中的check
     * 
     * @param workflowId 工作流代码
     * @param finprodId 金融产品代码
     * @param validList 验证信息
     * @return 验证信息
     * @see
     * @since 1.0
     */
    public List<ValidMessage> wflInFlowCheck(String workflowId, String finprodId, List<ValidMessage> validList){
        // 产品报价
        WflValidInputParameter wflValidInputParameterQuota = new WflValidInputParameter();
        wflValidInputParameterQuota.setPageId("FinProductQuotation002");
        wflValidInputParameterQuota.setWorkflowId(workflowId);
        wflValidInputParameterQuota.setBusinessDataPk1(finprodId);
        if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameterQuota)) {
            validList.add(new ValidMessage(getMessage("FIN_PROD_E_0077",
                getMessage("FinProductQuotation001"))));
        }
        
        // 产品费率报价管理
        WflValidInputParameter wflValidInputParameterFee = new WflValidInputParameter();
        wflValidInputParameterFee.setPageId("FinProductRateQuotation002");
        wflValidInputParameterFee.setWorkflowId(workflowId);
        wflValidInputParameterFee.setBusinessDataPk3(finprodId);
        if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameterFee)) {
            validList.add(new ValidMessage(getMessage("FIN_PROD_E_0077",
                getMessage("FinProductRateQuotation001"))));
        }
        
        // 产品业绩基准调整
        WflValidInputParameter wflValidInputParameterBench = new WflValidInputParameter();
        wflValidInputParameterBench.setPageId("FinProductBmQuotation002");
        wflValidInputParameterBench.setWorkflowId(workflowId);
        wflValidInputParameterBench.setBusinessDataPk1(finprodId);
        if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameterBench)) {
            validList.add(new ValidMessage(getMessage("FIN_PROD_E_0077",
                getMessage("FinProductBmQuotation001"))));
        }
        
        // 浮动管理费报价
        WflValidInputParameter wflValidInputParameterFloat = new WflValidInputParameter();
        wflValidInputParameterFloat.setPageId("FinFloatMgtExpQuotation002");
        wflValidInputParameterFloat.setWorkflowId(workflowId);
        wflValidInputParameterFloat.setBusinessDataPk1(finprodId);
        if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameterFloat)) {
            validList.add(new ValidMessage(getMessage("FIN_PROD_E_0077",
                getMessage("FinFloatMgtExpQuotation001"))));
        }
        
        // 开放计划调整
        WflValidInputParameter wflValidInputParameterOpen = new WflValidInputParameter();
        wflValidInputParameterOpen.setPageId("FinOpenPlanMaintenance001");
        wflValidInputParameterOpen.setWorkflowId(workflowId);
        wflValidInputParameterOpen.setBusinessDataPk2(finprodId);
        if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameterOpen)) {
            validList.add(new ValidMessage(getMessage("FIN_PROD_E_0077",
                getMessage("FinOpenPlanMaintenance001"))));
        }
        
        // 付费计划调整
        WflValidInputParameter wflValidInputParameterFeePlan = new WflValidInputParameter();
        wflValidInputParameterFeePlan.setPageId("FinPayPlanAdjustment001");
        wflValidInputParameterFeePlan.setWorkflowId(workflowId);
        wflValidInputParameterFeePlan.setBusinessDataPk2(finprodId);
        if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameterFeePlan)) {
            validList.add(new ValidMessage(getMessage("FIN_PROD_E_0077",
                getMessage("FinPayPlanAdjustment001"))));
        }
        
        // 到期日调整
        WflValidInputParameter wflValidInputParameterMdate = new WflValidInputParameter();
        wflValidInputParameterMdate.setPageId("FinMdateAdjustment002");
        wflValidInputParameterMdate.setWorkflowId(workflowId);
        wflValidInputParameterMdate.setBusinessDataPk1(finprodId);
        if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameterMdate)) {
            validList.add(new ValidMessage(getMessage("FIN_PROD_E_0077",
                getMessage("FinMdateAdjustment002"))));
        }
        
        // 付息日调整 
        WflValidInputParameter wflValidInputParameterPayPlan = new WflValidInputParameter();
        wflValidInputParameterPayPlan.setPageId("FinIntPayDateAdjustment001");
        wflValidInputParameterPayPlan.setWorkflowId(workflowId);
        wflValidInputParameterPayPlan.setBusinessDataPk3(finprodId);
        if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameterPayPlan)) {
            validList.add(new ValidMessage(getMessage("FIN_PROD_E_0077",
                getMessage("FinIntPayDateAdjustment001"))));
        }
        
        return validList;
    }


    /**
     * 根据净值型产品代码模糊查询联系下拉框
     * 
     * @param map 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryGroupIdInfoJz(Map<String, Object> map) {
        map.put("userId", SecurityUtil.getLoginUser().getUserName());
        map.put("flag","01");
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBuild/queryGroupIdInfo", map,
            SelectOptionDto.class);
    }

    /**
     * 净值化产品基本信息-发送销售接口状态
     *
     * @param finprodId
     * @return 发送结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public Map<String,String> sendNetValueInfoToSale(List<String> finprodIds) {
        return restTemplate.postForObject(
                ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/FinProductInterf/sendNetValueProductInfoToSale", finprodIds,
                Map.class);
    }
    public String getPERIODIC_MANAGE_INFORMATION(String sysCode,String paraCode) {
    	return restTemplate.getForObject(
    		    ServiceApplication.TRADE_SERVICE_NAME + "/common/trdcommonbase/selectParam/"+sysCode+"/"+paraCode
    		    , String.class);
    }
    /**
     *	 预览条件
     * @param finprodId 金融产品代码
     * @return 查询个数
     * @see  
     * @since 1.0
     */
    public int getPreview(String finprodId) {
        return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                         + "/FinProductBuild/getPreview?finprodId="
                                         + finprodId,
            Integer.class);
    }
  
}
