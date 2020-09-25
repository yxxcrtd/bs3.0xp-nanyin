/*
 * 文件名：FinProductBuild001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.build.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.productelement.build.dto.FinProductBuild001Dto;
import com.joyintech.tams.invest.product.productelement.build.dto.FinProductBuild002Dto;
import com.joyintech.tams.invest.product.productelement.build.service.FinProductBuild001Service;

/**
 * 产品新建管理验证类
 * 
 * @author zhangliang
 * @version 1.0 2018年10月23日
 * @see FinProductBuild001Validator
 * @since 1.0
 */
@Component
public class FinProductBuild001Validator implements BeanValidator {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductBuild001Service finProductBuild001Service;

    /**
     * {@link MstWorkflowCommonService}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FinProductBuild001Dto entity = (FinProductBuild001Dto)value;
        // 验证信息
        List<ValidMessage> validList = new ArrayList<ValidMessage>();

        if (StringUtil.equals(entity.getOperateType(), "edit")
            || StringUtil.equals(entity.getOperateType(), "delete")) {
            // 选中的记录.有效状态 = ‘有效’ 且 选中的记录.发行状态 IN (‘发行成功’, ‘发行失败’)时
            if (StringUtil.equals(entity.getEffectStatus(),
                DictCodeListExt1.画面数据状态_有效.getCodeName())) {
                if (!StringUtil.equals(entity.getIssueStatus(),          // update by dingjunfeng 2019-3-28
                		DictCodeListExt1.发行状态_确认发行.getValue())) {//&&!StringUtil.equals(entity.getIssueStatus(),DictCodeListExt1.发行状态_发行成功.getValue())
                    validList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0033",
                        DictCodeListExt1.发行状态_确认发行.getCodeName())));
                }
                if (finProductBuild001Service.queryProdCountByFinprodId(
                    entity.getFinprodId()) == 0) {
                    validList.add(new ValidMessage(this.getMessage("COMMON_E_0022", this.getMessage("FinProductBuild003.table.finprodMarketId"), entity.getFinprodMarketId())));
                }
            }
            else {
                validList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032",
                    DictCodeListExt1.有效状态_有效.getCodeName())));
            }
            // 发行状态管理流程中数据check
            WflValidInputParameter wflValidInputParameterIssue = new WflValidInputParameter();
            wflValidInputParameterIssue.setPageId("FinProductBuild003");
            wflValidInputParameterIssue.setWorkflowId(entity.getWorkflowId());
            wflValidInputParameterIssue.setBusinessDataPk2(entity.getFinprodMarketId());
            if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameterIssue)) {
                validList.add(new ValidMessage(getMessage("FIN_PROD_E_0077",
                    getMessage("FinProductBuild001.issueStatusMan"))));
            }
            
            if (StringUtil.isNotEmpty(entity.getFinprodId())){
                finProductBuild001Service.wflInFlowCheck(entity.getWorkflowId(), entity.getFinprodId(), validList);
            }
        }
        else if (StringUtil.equals(entity.getOperateType(), "issueStatus")) {
            List<FinProductBuild002Dto> selectedItem = entity.getSelectedItem();
            if (!StringUtil.isEmptyList(selectedItem)) {
                boolean effectStatusFlag = true;
                boolean issueStatusFlag = true;
                StringBuffer sb = new StringBuffer();
                for (FinProductBuild002Dto finProductBuild002Dto : selectedItem) {
                    if (!StringUtil.equals(finProductBuild002Dto.getEffectStatus(),
                        DictCodeListExt1.画面数据状态_有效.getCodeName())) {
                        if (effectStatusFlag) {
                            validList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032",
                                DictCodeListExt1.有效状态_有效.getCodeName())));
                            effectStatusFlag = false;
                        }
                    }
                    if (!StringUtil.equals(finProductBuild002Dto.getIssueStatus(),
                        DictCodeListExt1.发行状态_确认发行.getValue())) {
                        if (issueStatusFlag) {
                            validList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0033",
                                DictCodeListExt1.发行状态_确认发行.getCodeName())));
                            issueStatusFlag = false;
                        }
                    }
                    if (finProductBuild001Service.queryProdCountByFinprodId(
                        finProductBuild002Dto.getFinprodId()) == 0) {
                        sb.append(entity.getFinprodMarketId());
                        sb.append(GlobalConstant.COMMA);
                    }
                    // 发行状态管理流程中数据check
                    WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
                    wflValidInputParameter.setPageId("FinProductBuild003");
                    wflValidInputParameter.setWorkflowId(entity.getWorkflowId());
                    wflValidInputParameter.setBusinessDataPk2(
                        finProductBuild002Dto.getFinprodMarketId());
                    if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
                        validList.add(new ValidMessage(getMessage("BUSS_COM_E_0027",
                            getMessage("FinProductScheduling002.prodId"),
                            finProductBuild002Dto.getFinprodMarketId())));
                    }
                }
                if (StringUtil.isNotEmpty(sb)) {
                    validList.add(new ValidMessage(this.getMessage("COMMON_E_0022",
                        this.getMessage("FinProductBuild003.table.finprodMarketId"),
                        sb.substring(0, sb.length() - GlobalConstant.COMMA.length()))));
                }
            }
        }
        else if (StringUtil.equals(entity.getOperateType(), "confirm")) {
            if (!StringUtil.equals(entity.getEffectStatus(),
                DictCodeListExt1.画面数据状态_待处理.getCodeName())) {
                validList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032",
                    DictCodeListExt1.画面数据状态_待处理.getCodeName())));
            }
            else {
                // 选中的记录在业务表中已被删除时
                if (finProductBuild001Service.querySchedulingCountByFinprodId(
                    entity.getFinprodId()) == 0) {
                    validList.add(new ValidMessage(this.getMessage("COMMON_E_0022", this.getMessage("FinProductBuild003.table.finprodMarketId"), entity.getFinprodMarketId())));
                }
            }
        }

        return validList;
    }

}
