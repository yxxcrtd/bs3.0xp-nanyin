package com.joyintech.tams.invest.product.messageadjust.finproductquotation.validator; 

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.messageadjust.finproductquotation.dto.FinProductQuotation001Dto;
import com.joyintech.tams.invest.product.messageadjust.finproductquotation.dto.FinProductQuotation002Dto;
import com.joyintech.tams.invest.product.messageadjust.finproductquotation.dto.FinProductQuotation003Dto;
import com.joyintech.tams.invest.product.messageadjust.finproductquotation.service.FinProductQuotation002Service;

/**
 * 产品报价管理Validator
 * 
 * @author wangkang
 * @version 1.0 2018年10月11日
 * @see FinProductQuotation002Validator
 * @since 1.0
 */
@Component
public class FinProductQuotation002Validator implements BeanValidator {

    /**
     * 
     */
    @Autowired
    private FinProductQuotation002Service finProductQuotation002Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;

    @Override
    public List<ValidMessage> valid(Object value) {
    	//数据保存、提交时的校验
        FinProductQuotation002Dto dto = (FinProductQuotation002Dto)value;
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();

        //产品代码不为空
        if (StringUtil.isEmpty(dto.getFinprodId())) {
            errorList.add(new ValidMessage("finprodId",
                getMessage("BUSS_COM_E_0038", getMessage("FinProductQuotation002.finprodId"))));
        }
        //生效日不为空
        if (dto.getEffDate() == null) {
            errorList.add(new ValidMessage("effDate",
                getMessage("BUSS_COM_E_0038", getMessage("FinProductQuotation002.effDate"))));
        }
        //报价日 
        /*if (dto.getReleaseDate() == null) {
            errorList.add(new ValidMessage("releaseDate",
                getMessage("BUSS_COM_E_0038", getMessage("FinProductQuotation002.releaseDate"))));
        }*/
        // 分层产品的校验
        if (StringUtil.equals(dto.getIsLay(), DictCodeList.是否_是.getValue())) {
            if (StringUtil.isEmptyList(dto.getLayTypeInfoList())) {
                errorList.add(new ValidMessage("layTypeInfoList",
                    getMessage("BUSS_COM_E_0038", getMessage("FinProductQuotation002.layInfo"))));
            }
            else {
                for (FinProductQuotation003Dto finProductQuotation003Dto : dto.getLayTypeInfoList()) {
                	//delete by wm 去除分层产品对金额上下限的校验 start 2019-08-01
//                	if (StringUtil.isNotEmpty(finProductQuotation003Dto.getAmtMin())
//                        && !StringUtil.isNotEmpty(finProductQuotation003Dto.getAmtMax())) {
//                        errorList.add(new ValidMessage("layTypeInfoList", getMessage(
//                            "BUSS_COM_E_0038", getMessage("FinProductQuotation002.amtMax"))));
//                    }
//                    if (!StringUtil.isNotEmpty(finProductQuotation003Dto.getAmtMin())
//                        && StringUtil.isNotEmpty(finProductQuotation003Dto.getAmtMax())) {
//                        errorList.add(new ValidMessage("layTypeInfoList", getMessage(
//                            "BUSS_COM_E_0038", getMessage("FinProductQuotation002.amtMin"))));
//                    }
//                    if (StringUtil.isNotEmpty(finProductQuotation003Dto.getAmtMin())
//                        && StringUtil.isNotEmpty(finProductQuotation003Dto.getAmtMax())) {
//                        if (finProductQuotation003Dto.getAmtMin().compareTo(
//                            finProductQuotation003Dto.getAmtMax()) > 0) {
//                            errorList.add(new ValidMessage("layTypeInfoList",
//                                getMessage("FIN_PROD_E_0017",
//                                    getMessage("FinProductQuotation002.amtMin"),getMessage("FinProductQuotation002.amtMax"))));
//                        }
//                    }
                    //delete by wm 去除分层产品对金额上下限的校验 end 2019-08-01
                    if (!StringUtil.isNotEmpty(finProductQuotation003Dto.getYield())) {
                        errorList.add(new ValidMessage("layTypeInfoList",
                            getMessage("BUSS_COM_E_0038", getMessage("FinProductQuotation002.yield"))));
                    }
                    /*if (!StringUtil.isNotEmpty(finProductQuotation003Dto.getCustomLevel())
                        && !StringUtil.isNotEmpty(finProductQuotation003Dto.getAmtMin())
                        && !StringUtil.isNotEmpty(finProductQuotation003Dto.getAmtMax())
                        && !StringUtil.isNotEmpty(finProductQuotation003Dto.getTermMax())
                        && !StringUtil.isNotEmpty(finProductQuotation003Dto.getTermMin())) {
                        // 客户类型、金额分层、期限分层不可同时为空！
                        errorList.add(
                            new ValidMessage("layTypeInfoList", getMessage("FIN_PROD_E_0072")));
                    }
                    else {
                        if (StringUtil.isNotEmpty(finProductQuotation003Dto.getAmtMin())
                            && !StringUtil.isNotEmpty(finProductQuotation003Dto.getAmtMax())) {
                            errorList.add(new ValidMessage("layTypeInfoList", getMessage(
                                "BUSS_COM_E_0038", getMessage("FinProductQuotation002.amtMax"))));
                        }
                        if (!StringUtil.isNotEmpty(finProductQuotation003Dto.getAmtMin())
                            && StringUtil.isNotEmpty(finProductQuotation003Dto.getAmtMax())) {
                            errorList.add(new ValidMessage("layTypeInfoList", getMessage(
                                "BUSS_COM_E_0038", getMessage("FinProductQuotation002.amtMin"))));
                        }
                        if (StringUtil.isNotEmpty(finProductQuotation003Dto.getAmtMin())
                            && StringUtil.isNotEmpty(finProductQuotation003Dto.getAmtMax())) {
                            if (finProductQuotation003Dto.getAmtMin().compareTo(
                                finProductQuotation003Dto.getAmtMax()) > 0) {
                                errorList.add(new ValidMessage("layTypeInfoList",
                                    getMessage("FIN_PROD_E_0017",
                                        getMessage("FinProductQuotation002.amtMin"),getMessage("FinProductQuotation002.amtMax"))));
                            }
                        }
                        if (StringUtil.isNotEmpty(finProductQuotation003Dto.getTermMax())
                            && !StringUtil.isNotEmpty(finProductQuotation003Dto.getTermMin())) {
                            errorList.add(new ValidMessage("layTypeInfoList", getMessage(
                                "BUSS_COM_E_0038", getMessage("FinProductQuotation002.termMin"))));
                        }
                        if (!StringUtil.isNotEmpty(finProductQuotation003Dto.getTermMax())
                            && StringUtil.isNotEmpty(finProductQuotation003Dto.getTermMin())) {
                            errorList.add(new ValidMessage("layTypeInfoList", getMessage(
                                "BUSS_COM_E_0038", getMessage("FinProductQuotation002.termMax"))));
                        }
                        if (StringUtil.isNotEmpty(finProductQuotation003Dto.getTermMax())
                            && StringUtil.isNotEmpty(finProductQuotation003Dto.getTermMin())) {
                            if (finProductQuotation003Dto.getTermMin().compareTo(
                                finProductQuotation003Dto.getTermMax()) > 0) {
                                errorList.add(new ValidMessage("layTypeInfoList",
                                    getMessage("FIN_PROD_E_0017",
                                        getMessage("FinProductQuotation002.termMin"),getMessage("FinProductQuotation002.termMax"))));
                            }
                        }
                    }
                    if (StringUtil.isNotEmpty(finProductQuotation003Dto.getTermMax())) {
                        if (finProductQuotation003Dto.getTermMax().compareTo(
                            new Long(finProductQuotation003Dto.getTermMax().intValue())) != 0) {
                            errorList.add(new ValidMessage("layTypeInfoList", getMessage(
                                "FW_E_0002", getMessage("FinProductQuotation002.termMax"))));
                        }
                    }
                    if (StringUtil.isNotEmpty(finProductQuotation003Dto.getTermMin())) {
                        if (finProductQuotation003Dto.getTermMin().compareTo(
                            new Long(finProductQuotation003Dto.getTermMin().intValue())) != 0) {
                            errorList.add(new ValidMessage("layTypeInfoList", getMessage(
                                "FW_E_0002", getMessage("FinProductQuotation002.termMin"))));
                        }
                    }*/
                }
            }

        }
        else {
            if (!StringUtil.isNotEmpty(dto.getYield())) {
                errorList.add(new ValidMessage("yield",
                    getMessage("BUSS_COM_E_0038", getMessage("FinProductQuotation002.yield"))));
            }
        }

        // 生效日存在报价
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            if (StringUtil.isNotEmpty(dto.getFinprodId()) && StringUtil.isNotEmpty(dto.getEffDate())) {
                if (finProductQuotation002Service.checkEffDateQuoteExists(dto.getFinprodId(),
                    DateUtil.format(dto.getEffDate())) > 0) {
                    errorList.add(new ValidMessage("effDate",
                        getMessage("FIN_PROD_E_0009", DateUtil.format(dto.getEffDate()),
                            this.getMessage("FinProductQuotation002.quoteInfo"))));
                }
            }
        }
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
            if (StringUtil.isNotEmpty(dto.getFinprodId()) && StringUtil.isNotEmpty(dto.getEffDate())
                && StringUtil.isNotEmpty(dto.getEffDateOld()) 
                && dto.getEffDate().compareTo(dto.getEffDateOld()) != 0) {
                if (finProductQuotation002Service.checkEffDateQuoteExists(dto.getFinprodId(),
                    DateUtil.format(dto.getEffDate())) > 0) {
                    errorList.add(new ValidMessage("effDate",
                        getMessage("FIN_PROD_E_0009", DateUtil.format(dto.getEffDate()),
                            this.getMessage("FinProductQuotation002.quoteInfo"))));
                }
            }
        }
        if (StringUtil.isNotEmpty(dto.getFinprodId()) && StringUtil.isNotEmpty(dto.getEffDate())) {
            // 画面.生效日 < 标的类金融产品.起息日 时，提交报错
            FinProductQuotation001Dto temp = new FinProductQuotation001Dto();
            temp = finProductQuotation002Service.getVdateAndMdate(dto.getFinprodId());
            if (dto.getEffDate().compareTo(temp.getVdate()) < 0) {
                errorList.add(new ValidMessage("effDate", getMessage("BUSS_COM_E_0004",
                    getMessage("FinProductQuotation002.effDate") + DateUtil.format(
                        dto.getEffDate()),
                    getMessage("FinProductQuotation002.vdate")
                                           + DateUtil.format(temp.getVdate()))));
            }
            // 画面.生效日 > 标的类金融产品.到期日 时，提交报错
            if (dto.getEffDate().compareTo(temp.getMdate()) > 0) {
                errorList.add(new ValidMessage("effDate", getMessage("BUSS_COM_E_0003",
                    getMessage("FinProductQuotation002.effDate") + DateUtil.format(
                        dto.getEffDate()),
                    getMessage("FinProductQuotation002.mdate")
                                           + DateUtil.format(temp.getMdate()))));
            }
            // 画面.生效日 < 平台日 ,提交时报错
            if (dto.getEffDate().compareTo(iPlatformDate.getSysDate()) < 0) {
                errorList.add(new ValidMessage("effDate",
                    getMessage("BUSS_COM_E_0004",
                        getMessage("FinProductQuotation002.effDate") + DateUtil.format(
                            dto.getEffDate()),
                        getMessage("FinProductQuotation001.currentDate")
                                               + iPlatformDate.getSysDateStr())));
            }
        }

        return errorList;
    }
}
