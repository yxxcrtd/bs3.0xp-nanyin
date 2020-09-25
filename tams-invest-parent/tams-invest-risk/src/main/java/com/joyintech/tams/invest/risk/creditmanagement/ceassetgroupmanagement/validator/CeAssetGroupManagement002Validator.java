/*
 * 文件名：CeAssetGroupManagement002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.risk.creditmanagement.ceasset.dto.CreditLinesAstDto;
import com.joyintech.tams.invest.risk.creditmanagement.ceasset.service.CeAsset002Service;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.dto.CeAssetGroupManagement002Dto;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.service.CeAssetGroupManagement003Service;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author dongzhiling
 * @version 1.0 2019年1月25日
 * @see CeAssetGroupManagement002Validator
 * @since 1.0
 */
@Component
public class CeAssetGroupManagement002Validator implements BeanValidator {

    /**
     * CeAssetGroupManagement003Service
     */
    @Autowired
    private CeAssetGroupManagement003Service ceAssetGroupManagement003Service;

    /**
     * MstWorkflowCommonService
     */
    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;
    
    @Autowired
    CeAsset002Service ceAsset002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        CeAssetGroupManagement002Dto dto = (CeAssetGroupManagement002Dto)value;
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            if (StringUtil.isEmpty(dto.getSeqno())) {
            	 /** modify by gaopeng on 20191009 没有选择系列时 ，提醒“请选择序列名称！start
                validList.add(new ValidMessage(this.getMessage("RSK_W_0042")));**/
                validList.add(new ValidMessage(this.getMessage("RSK_C_0042")));
                /** modify by gaopeng on 20191009 系列名称重复时 ，提醒“请选择序列名称！end **/
            }
            // 校验添加的资产明细是否已被添加
            List<CeAssetGroupManagement002Dto> selectedItem = dto.getSelectedItem();
            if (StringUtil.equals(dto.getOperateType(), "save")
                || StringUtil.equals(dto.getOperateType(), "submit")) {
                if (!StringUtil.isEmptyList(selectedItem)) {
                    List<CeAssetGroupManagement002Dto> totalList = this.getBusinessInfoByWorkflowId(
                        dto.getWorkflowId(), false);
                    
                    // add by wj  20200429 --- 增加校验  判断  流程中的 授信资产 
                    List<CreditLinesAstDto> assetList = getCeAssetListByWorkflowId(dto.getWorkflowId(), false);
                    
                    // 页面中选中的资产明细
                    for (CeAssetGroupManagement002Dto ceAssetGroupManagement002Dto : selectedItem) {
                        // 数据库已存在资产明细判断
                    	// modifyby wj 20200424 是否存在其他授信系列中 将系列name查出 放入提示中
                        String assetpoolName = ceAssetGroupManagement003Service.checkIsExistAssetPoolNew(
                            ceAssetGroupManagement002Dto.getFinprodId());
                        if (StringUtil.isNotEmpty(assetpoolName)) {
                            validList.add(new ValidMessage(this.getMessage("RSK_W2_0002",
                                ceAssetGroupManagement002Dto.getFinprodId(),assetpoolName)));
                        }
                        
                        // 资产授信判断 判断是否已存在单个资产授信中。
                        int countLine = ceAssetGroupManagement003Service.checkisExistAssetByCredit(ceAssetGroupManagement002Dto.getFinprodId());
                        if (countLine > 0) {
                        	validList.add(new ValidMessage(this.getMessage("RSK_W2_0001",
                                    ceAssetGroupManagement002Dto.getFinprodId())));
                        }
                        if (!StringUtil.isEmptyList(totalList)) {
                            for (int i = 0; i < totalList.size(); i++ ) {
                                // 逐个判断资产明细
                                if (StringUtil.equals(ceAssetGroupManagement002Dto.getFinprodId(),
                                    totalList.get(i).getFinprodId())) {
                                    validList.add(new ValidMessage(this.getMessage("RSK_W2_0003",
                                        ceAssetGroupManagement002Dto.getFinprodId())));
                                }
                            }
                        }
                        
                        // add by wj 20200429--- 增加校验  判断  流程中的 授信资产 
                        if (!StringUtil.isEmptyList(assetList)) {
                            for (int i = 0; i < assetList.size(); i++ ) {
                                // 逐个判断资产明细
                                if (StringUtil.equals(ceAssetGroupManagement002Dto.getFinprodId(),
                                		assetList.get(i).getCustomerId())) {
                                    validList.add(new ValidMessage(this.getMessage("RSK_W2_0004",
                                        ceAssetGroupManagement002Dto.getFinprodId())));
                                }
                            }
                        }
                        
                    }
                }
                else {
                    validList.add(new ValidMessage(this.getMessage("RSK_W_0045")));
                }
            }
            
        }
        if (!StringUtil.equals(dto.getOperateType(), "edit")
            && StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
            List<CeAssetGroupManagement002Dto> totalList = this.getBusinessInfoByWorkflowId(
                dto.getWorkflowId(), true);
            List<CeAssetGroupManagement002Dto> selectedItem3 = dto.getSelectedItem();
            if (!StringUtil.isEmptyList(selectedItem3)) {
                for (CeAssetGroupManagement002Dto ceAssetGroupManagement002Dto : selectedItem3) {
                    if (!StringUtil.isEmptyList(totalList)) {
                        for (int i = 0; i < totalList.size(); i++ ) {
                            // 逐个判断资产明细
                            if (StringUtil.equals(ceAssetGroupManagement002Dto.getFinprodId(),
                                totalList.get(i).getFinprodId())) {
                                validList.add(new ValidMessage(this.getMessage("RSK_W_0043",
                                    ceAssetGroupManagement002Dto.getFinprodId(),dto.getAssetpoolName())));
                            }
                        }
                    }
                }
            }
        }
        return validList;
    }

    /**
     * <一句话功能简述> 筛选获取流程中的资产明细
     * 
     * @param workflowId 工作流代码
     * @param isdelete 是否过滤自身流程
     * @return 流程中存在的资产明细
     * @see
     * @since 1.0
     */
    private List<CeAssetGroupManagement002Dto> getBusinessInfoByWorkflowId(String workflowId,
                                                                           Boolean isdelete) {
        // 查询流程中的工作流代码
        String pageId = "CeAssetGroupManagement003";
        List<CeAssetGroupManagement002Dto> list = ceAssetGroupManagement003Service.getBusinessWorkflowId(
            pageId);
        List<CeAssetGroupManagement002Dto> totalList = new ArrayList<>();
        if (isdelete == true) {
            for (int i = list.size() - 1; i >= 0; i-- ) {
                if (StringUtil.equals(list.get(i).getWorkflowId(), workflowId)) {
                    list.remove(i);
                }
            }
        }
        // 流程中的资产明细查询并汇总
        for (CeAssetGroupManagement002Dto ceAssetGroupManagement002Dto2 : list) {
            CeAssetGroupManagement002Dto business = ceAssetGroupManagement003Service.getBussinessEntityByWorkflowId(
                ceAssetGroupManagement002Dto2.getWorkflowId());
            // 流程中的资产明细
            List<CeAssetGroupManagement002Dto> selectedItem2 = business.getSelectedItem();
            totalList.addAll(selectedItem2);
        }
        return totalList;
    }
    
    
    
    /**
     * <一句话功能简述> 筛选获取流程中的资产明细
     * 
     * @param workflowId 工作流代码
     * @param isdelete 是否过滤自身流程
     * @return 流程中存在的资产
     * @see
     * @since 1.0
     */
    private List<CreditLinesAstDto> getCeAssetListByWorkflowId(String workflowId,Boolean isdelete) {
        // 查询流程中的工作流代码
        String pageId = "CeAsset002";
        List<CeAssetGroupManagement002Dto> list = ceAssetGroupManagement003Service.getBusinessWorkflowId(
            pageId);
        List<CreditLinesAstDto> ceAssetList = new ArrayList<>();
        if (isdelete == true) {
            for (int i = list.size() - 1; i >= 0; i-- ) {
                if (StringUtil.equals(list.get(i).getWorkflowId(), workflowId)) {
                    list.remove(i);
                }
            }
        }
        // 流程中的资产明细查询并汇总
        for (CeAssetGroupManagement002Dto ceAssetGroupManagement002Dto2 : list) {
        	CreditLinesAstDto business = ceAsset002Service.getBussinessEntityByWorkflowId(
                ceAssetGroupManagement002Dto2.getWorkflowId());
            // 流程中的资产明细
        	ceAssetList.add(business);
        }
        return ceAssetList;
    }

}
