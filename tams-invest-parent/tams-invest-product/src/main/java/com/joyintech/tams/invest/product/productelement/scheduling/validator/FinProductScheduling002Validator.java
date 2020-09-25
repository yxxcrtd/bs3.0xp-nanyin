/*
 * 文件名：FinProductScheduling002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.scheduling.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.EncryptUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.product.productelement.scheduling.dto.PrdIssueScheduleDto;
import com.joyintech.tams.invest.product.productelement.scheduling.service.FinProductScheduling002Service;

/**
 * 产品排期Validator
 * 
 * @author zhangliang
 * @version 1.0 2018年8月22日
 * @see FinProductScheduling002Validator
 * @since 1.0
 */
@Component
public class FinProductScheduling002Validator implements BeanValidator {

    /**
     * {@link FinProductScheduling002Service}
     */
    @Autowired
    private FinProductScheduling002Service finProductScheduling002Service;

    /**
     * {@link MstWorkflowCommonService}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public List<ValidMessage> valid(Object value) {
        PrdIssueScheduleDto dto = (PrdIssueScheduleDto)value;
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();

        if (StringUtil.isNotEmpty(dto.getFinprodMarketId())) {
            // 规则：代码(1~3位，第1位为大写字母，第2~3位为数字或大写字母)+销售渠道代码(1位大写字母)+品牌代码(1位大写字母)+年份(2位数字)+产品序号(4位数）
            Pattern pattern = Pattern.compile("^[A-Za-z]{1}[A-Za-z0-9]{0,2}[A-Za-z]{2}[0-9]{6}$");
            Matcher matcher = pattern.matcher(dto.getFinprodMarketId());
            if (!matcher.matches()) {
                errorList.add(new ValidMessage("finprodMarketId",
                    getMessage("BUSS_COM_E_0044", getMessage("FinProductScheduling002.prodId"))));
            }

            if (finProductScheduling002Service.checkProdInfoExist(dto.getFinprodMarketId(), null,
                null, dto.getFinprodId()) > 0) {
                errorList.add(new ValidMessage("finprodMarketId",
                    getMessage("BUSS_COM_I_0017", getMessage("FinProductScheduling002.prodId"))));
            }

            // 流程中的市场代码(排期和产品新建)
            WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
            List<String> pageIdList = new ArrayList<String>();
            pageIdList.add("FinProductScheduling002");
            pageIdList.add("FinProductBuild002");
            wflValidInputParameter.setPageIdList(pageIdList);
            wflValidInputParameter.setWorkflowId(dto.getWorkflowId());
            wflValidInputParameter.setBusinessDataPk2(dto.getFinprodMarketId());
            if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
                errorList.add(new ValidMessage("finprodMarketId",
                    getMessage("BUSS_COM_I_0017", getMessage("FinProductScheduling002.prodId"))));
            }
        }

        if (StringUtil.isNotEmpty(dto.getFinprodName())) {
            if (finProductScheduling002Service.checkProdInfoExist(null, null, dto.getFinprodName(),
                dto.getFinprodId()) > 0) {
                errorList.add(new ValidMessage("finprodName", getMessage("BUSS_COM_I_0017",
                    getMessage("FinProductScheduling002.prodName"))));
            }

            // 流程中的市场代码(排期和产品新建)
            WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
            List<String> pageIdList = new ArrayList<String>();
            pageIdList.add("FinProductScheduling002");
            pageIdList.add("FinProductBuild002");
            wflValidInputParameter.setPageIdList(pageIdList);
            wflValidInputParameter.setWorkflowId(dto.getWorkflowId());
            wflValidInputParameter.setBusinessDataPk3(EncryptUtil.getInstance().md5(dto.getFinprodName()));
            if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
                errorList.add(new ValidMessage("finprodName", getMessage("BUSS_COM_I_0017",
                    getMessage("FinProductScheduling002.prodName"))));
            }
        }

        if (StringUtil.isNotEmpty(dto.getFinprodAbbr())) {
            if (finProductScheduling002Service.checkProdInfoExist(null, dto.getFinprodAbbr(), null,
                dto.getFinprodId()) > 0) {
                errorList.add(new ValidMessage("finprodAbbr", getMessage("BUSS_COM_I_0017",
                    getMessage("FinProductScheduling002.prodAbbr"))));
            }

            // 流程中的市场代码(排期和产品新建)
            WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
            List<String> pageIdList = new ArrayList<String>();
            pageIdList.add("FinProductScheduling002");
            pageIdList.add("FinProductBuild002");
            wflValidInputParameter.setPageIdList(pageIdList);
            wflValidInputParameter.setWorkflowId(dto.getWorkflowId());
            wflValidInputParameter.setBusinessDataPk4(EncryptUtil.getInstance().md5(dto.getFinprodAbbr()));
            if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
                errorList.add(new ValidMessage("finprodAbbr", getMessage("BUSS_COM_I_0017",
                    getMessage("FinProductScheduling002.prodAbbr"))));
            }
        }

        if (StringUtil.isNotEmpty(dto.getVdate()) && StringUtil.isNotEmpty(dto.getMdate())) {
            if (DateUtil.compareDate(dto.getVdate(), dto.getMdate()) > 0) {
                errorList.add(new ValidMessage("vdate",
                    getMessage("BUSS_COM_E_0003", getMessage("FinProductScheduling002.vdate"),
                        getMessage("FinProductScheduling002.mdate"))));
            }
        }

        if (StringUtil.isNotEmpty(dto.getRaiseStrdate())
            && StringUtil.isNotEmpty(dto.getRaiseEnddate())) {
            if (DateUtil.compareDate(dto.getRaiseStrdate(), dto.getRaiseEnddate()) > 0) {
                errorList.add(new ValidMessage("raiseStrdate",
                    getMessage("BUSS_COM_E_0003",
                        getMessage("FinProductScheduling002.raiseStrDate"),
                        getMessage("FinProductScheduling002.raiseEndDate"))));
            }
        }

        if (StringUtil.isNotEmpty(dto.getRaiseEnddate())
            && StringUtil.isNotEmpty(dto.getVdate())) {
            if (DateUtil.compareDate(dto.getRaiseEnddate(), dto.getVdate()) >= 0) {
                errorList.add(new ValidMessage("raiseEnddate",
                    getMessage("BUSS_COM_E_0035",
                        getMessage("FinProductScheduling002.raiseEndDate"),
                        getMessage("FinProductScheduling002.vdate"))));
            }
        }

        if (StringUtil.isNotEmpty(dto.getToffStartMid())
            && StringUtil.isNotEmpty(dto.getToffStartLow())) {
            if (dto.getToffStartLow().compareTo(dto.getToffStartMid()) > 0) {
                errorList.add(new ValidMessage("toffStartLow",
                    getMessage("BUSS_COM_E_0003",
                        getMessage("FinProductScheduling002.subStartAmtBot"),
                        getMessage("FinProductScheduling002.subStartAmtMid"))));
            }
        }

        if (StringUtil.isNotEmpty(dto.getToffStartMid())
            && StringUtil.isNotEmpty(dto.getToffStartHig())) {
            if (dto.getToffStartMid().compareTo(dto.getToffStartHig()) > 0) {
                errorList.add(new ValidMessage("toffStartMid",
                    getMessage("BUSS_COM_E_0003",
                        getMessage("FinProductScheduling002.subStartAmtMid"),
                        getMessage("FinProductScheduling002.subStartAmtHei"))));
            }
        }

        if (StringUtil.isNotEmpty(dto.getYieldMid()) && StringUtil.isNotEmpty(dto.getYieldLow())) {
            if (dto.getYieldLow().compareTo(dto.getYieldMid()) > 0) {
                errorList.add(new ValidMessage("yieldLow",
                    getMessage("BUSS_COM_E_0003",
                        getMessage("FinProductScheduling002.customRateBot"),
                        getMessage("FinProductScheduling002.customRateMid"))));
            }
        }

        if (StringUtil.isNotEmpty(dto.getYieldMid()) && StringUtil.isNotEmpty(dto.getYieldHig())) {
            if (dto.getYieldMid().compareTo(dto.getYieldHig()) > 0) {
                errorList.add(new ValidMessage("yieldMid",
                    getMessage("BUSS_COM_E_0003",
                        getMessage("FinProductScheduling002.customRateMid"),
                        getMessage("FinProductScheduling002.customRateHei"))));
            }
        }

        if (StringUtil.isNotEmpty(dto.getVdate()) && dto.getBussRepFinProductInfoDto() != null
            && StringUtil.isNotEmpty(dto.getBussRepFinProductInfoDto().getRepFirstOpenDay())) {
            if (DateUtil.compareDate(dto.getVdate(),
                dto.getBussRepFinProductInfoDto().getRepFirstOpenDay()) > 0) {
                errorList.add(new ValidMessage("vdate",
                    getMessage("BUSS_COM_E_0004",
                        getMessage("FinProductScheduling002.fristOpenCycleStrDate"),
                        getMessage("FinProductScheduling002.vdate"))));
            }
        }

        /*
         * if (WorkFlowUtil.isNotFromToDoList()) { if (StringUtil.equals(dto.getOperateType(),
         * "delete")) { if(finSubmittedModule001Mapper.deleteCheck(dto.getSchemeId())!= null){
         * errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0004",dto.getSchemeId(),
         * finSubmittedModule001Mapper.deleteCheck(dto.getSchemeId())))); } } }
         */
        return errorList;
    }
}
