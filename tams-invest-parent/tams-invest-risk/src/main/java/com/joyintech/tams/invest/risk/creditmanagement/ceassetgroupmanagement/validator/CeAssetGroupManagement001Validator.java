/*
 * 文件名：CePreOccupancyManagementValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月10日
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
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.dto.CeAssetGroupManagement001Dto;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.service.CeAssetGroupManagement002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉
 * 资产系列校验
 * @author dongzhiling
 * @version 1.0 2019年1月10日
 * @see CeAssetGroupManagement001Validator
 * @since 1.0
 */
@Component
public class CeAssetGroupManagement001Validator implements BeanValidator{
    
    @Autowired
    private CeAssetGroupManagement002Service ceAssetGroupManagement002Service;
    
    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        CeAssetGroupManagement001Dto dto = (CeAssetGroupManagement001Dto)value;
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            // 判断系列名称是否重复
            int count = ceAssetGroupManagement002Service.checkIsExistByassetpoolName(
                StringUtil.EMPTY, dto.getAssetpoolName());
            if (count > 0) {
                /** modify by yanya on 20190930 系列名称重复时 ，提醒“系列已存在！start
                validList.add(new ValidMessage(this.getMessage("RSK_W_0041")));**/
//                validList.add(new ValidMessage(this.getMessage("RSK_W_0043")));
            	// modify by wj 20200424  改为提醒 {xxxx}系列已存在  ---
                validList.add(new ValidMessage(this.getMessage("RSK_W_0044",dto.getAssetpoolName())));
                /** modify by yanya on 20190930 系列名称重复时 ，提醒“系列已存在！end **/
            } 
            // 判断是否输入系列名称
            /** add by gaopeng on 20191009 系列名称为空时 ，提醒“请输入系列名称！start**/
            if(dto.getAssetpoolName()==null||"".equals(dto.getAssetpoolName())) {
            	validList.add(new ValidMessage(this.getMessage("RSK_W_0067")));
            }
            /** add by gaopeng on 20191009 系列名称为空时 ，提醒“请输入系列名称！end **/
            //判断流程中是否存在相同的数据
            WflValidInputParameter param = new WflValidInputParameter();
            param.setBusinessDataPk2(dto.getAssetpoolName());
            param.setPageId("CeAssetGroupManagement002");
            if(null != mstWorkflowCommonService && 
                mstWorkflowCommonService.isInWflProcess(param)) {
                validList.add(new ValidMessage(this.getMessage("COMMON_E_0012",dto.getAssetpoolName())));
            }
            
        }
        if (!StringUtil.equals(dto.getOperateType(), "edit")
            && StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
            // 判断系列名称是否重复
            int count = ceAssetGroupManagement002Service.checkIsExistByassetpoolName(
                dto.getSeqno(), dto.getAssetpoolName());
            if (count>0) {
            	 /** modify by gaopeng on 20191009 系列名称重复时 ，提醒“系列已存在！start
                validList.add(new ValidMessage(this.getMessage("RSK_W_0041")));**/
                validList.add(new ValidMessage(this.getMessage("RSK_W_0044",dto.getAssetpoolName())));
                /** modify by gaopeng on 20191009 系列名称重复时 ，提醒“系列已存在！end **/
            }
            //判断流程中是否存在相同的数据
            WflValidInputParameter param = new WflValidInputParameter();
            param.setWorkflowId(dto.getWorkflowId());//去除自身这个流程
            param.setBusinessDataPk2(dto.getAssetpoolName());
            param.setPageId("CeAssetGroupManagement002");
            if(null != mstWorkflowCommonService && 
                mstWorkflowCommonService.isInWflProcess(param)){
                validList.add(new ValidMessage(this.getMessage("COMMON_E_0012",dto.getAssetpoolName())));
            }
        }
        //修改时验证
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
            if (ceAssetGroupManagement002Service.checkBusinessDataInFlow(dto)) {
                validList.add(new ValidMessage(this.getMessage("COMMON_E_0012",dto.getSeqno())));
            }
            if (!StringUtil.isNotEmpty(ceAssetGroupManagement002Service.getBusinessInfoBySeqno(dto.getSeqno()))) {
                validList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
            }
            return validList;
        }
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            if (dto.getFlag() != null && dto.getFlag() == true) {
                if (StringUtil.isNotEmpty(dto.getSeqno())) {
                    if (ceAssetGroupManagement002Service.checkBusinessDataInFlow(dto)) {
                        validList.add(
                            new ValidMessage(this.getMessage("COMMON_E_0012", dto.getSeqno())));
                    }
                    if (!StringUtil.isNotEmpty(
                        ceAssetGroupManagement002Service.getBusinessInfoBySeqno(dto.getSeqno()))) {
                        validList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
                    }
                }
                // 判断是否关联授信客户编号
                int count = ceAssetGroupManagement002Service.checkisExistId(dto.getSeqno());
                if (count > 0) {
                    validList.add(new ValidMessage(this.getMessage("RSK_W_0046")));
                }
                // 判断该资产系列是否存在明细
                if (ceAssetGroupManagement002Service.checkIsExistDetail(dto.getSeqno()) > 0) {
                    validList.add(new ValidMessage(this.getMessage("RSK_W_0047")));
                }
            }
        }
        return validList;
    }

}
