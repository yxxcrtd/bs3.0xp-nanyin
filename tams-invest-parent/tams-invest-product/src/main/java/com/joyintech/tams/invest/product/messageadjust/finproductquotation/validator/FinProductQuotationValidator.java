package com.joyintech.tams.invest.product.messageadjust.finproductquotation.validator;  

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
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
import com.joyintech.tams.invest.product.messageadjust.finproductquotation.service.FinProductQuotation002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 产品报价管理Validator
 * 
 * @author wangkang
 * @version 1.0 2018年10月11日
 * @see FinProductQuotationValidator
 * @since 1.0
 */
@Component
public class FinProductQuotationValidator implements BeanValidator {

    /**
     * {@linkplain}
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
        FinProductQuotation001Dto dto = (FinProductQuotation001Dto)value;
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        if (WorkFlowUtil.isNotFromToDoList()) {
            // 删除时的check
            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                List<FinProductQuotation001Dto> selects = dto.getSelectedItem();
                // 批量删除的check
                if (selects != null && selects.size() > 0) {
                    for (FinProductQuotation001Dto select : selects) {
                        //是否是初始利率
                        if(StringUtil.isNotEmpty(select.getIsInitial()) && select.getIsInitial().equals(DictCodeList.是否_是.getValue())){
                            errorList.add(new ValidMessage(getMessage("初始化生成的产品报价信息不允许删除。产品代码："+select.getFinprodMarketId())));
                        }
                        //生效日、起息日不为空
                        if (StringUtil.isNotEmpty(select.getEffDate())
                            && StringUtil.isNotEmpty(select.getVdate())) {
                        	//起息日等于生效日，初始化生成的产品报价信息不允许操作
                            if (select.getEffDate() == select.getVdate()) {
                                errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0010")));
                            }
                        }
                        //外部接入的报价信息不允许操作
                        if (StringUtil.isNotEmpty(select.getInputType())) {
                        	// 一体化对应etliu start
//                            if (dto.getInputType() == DictCodeListExt1.录入方式_外部接入.getValue()) {
                        	if (dto.getInputType() == DictCodeListExt1.录入方式_接口导入.getValue()) {
                            // 一体化对应etliu end
                                errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0011")));
                            }
                        }
                        //生效日小于平台日的数据不允许删除。
                        if (DateUtil.compareDate(select.getEffDate(), iPlatformDate.getSysDate()) < 0) {
                            errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0015",
                                getMessage("FinProductQuotation001.effDate")+":"+DateUtil.format(select.getEffDate()),
                                getMessage("FinProductQuotation001.currentDate")+":"+DateUtil.getSysDate())));
                        }
                        if (StringUtil.isNotEmpty(select.getEffDate())
                            && StringUtil.isNotEmpty(select.getVdate())) {
                            if (select.getEffDate() == select.getVdate()) {
                                errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0010")));
                            }
                        }
                    }
                }
                else {
                    //是否是初始利率
                    if(StringUtil.isNotEmpty(dto.getIsInitial()) && dto.getIsInitial().equals(DictCodeList.是否_是.getValue())){
                        errorList.add(new ValidMessage(getMessage("初始化生成的产品报价信息不允许删除。产品代码："+dto.getFinprodMarketId())));
                    }
                    if (StringUtil.isNotEmpty(dto.getEffDate())
                        && StringUtil.isNotEmpty(dto.getVdate())) {
                        if (dto.getEffDate() == dto.getVdate()) {
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0010")));
                        }
                    }
                    if (StringUtil.isNotEmpty(dto.getInputType())) {
                    	// 一体化对应etliu start
//                        if (dto.getInputType() == DictCodeListExt1.录入方式_外部接入.getValue()) {
                    	if (dto.getInputType() == DictCodeListExt1.录入方式_接口导入.getValue()) {
                      	// 一体化对应etliu end
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0011")));
                        }
                    }
                    if (DateUtil.compareDate(dto.getEffDate(), iPlatformDate.getSysDate()) < 0) {
                        errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0015",
                            getMessage("FinProductQuotation001.effDate")+":"+DateUtil.format(dto.getEffDate()),
                            getMessage("FinProductQuotation001.currentDate")+":"+DateUtil.getSysDate())));
                    }
                    if (StringUtil.isNotEmpty(dto.getEffDate())
                        && StringUtil.isNotEmpty(dto.getVdate())) {
                        if (dto.getEffDate() == dto.getVdate()) {
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0010")));
                        }
                    }
                }

            }
            else if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                if (StringUtil.isEmpty(dto.getFinprodId())) {
                    errorList.add(new ValidMessage("", "产品代码不能为空！"));
                }
                if (StringUtil.isEmpty(dto.getFinprodAbbr())) {
                    errorList.add(new ValidMessage("", "产品简称不能为空！"));
                }
                if (dto.getEffDate() == null) {
                    errorList.add(new ValidMessage("", "生效日不能为空！"));
                }
                /*if (dto.getReleaseDate() == null) {
                    errorList.add(new ValidMessage("", "报价日不能为空！"));
                }*/
                // 不同类型的验证
                if (StringUtil.equals(dto.getProfitType(),
                    DictCodeList.收益类型_收益型.getValue())) {
                   /* if (StringUtil.isEmpty(dto.getSaleDepartment())) {
                        errorList.add(new ValidMessage("", "销售部门不能为空！"));
                    }*/
                    if (StringUtil.isNotEmpty(dto.getFinprodId())
                        && StringUtil.isNotEmpty(dto.getEffDate())) {
                        if (finProductQuotation002Service.checkEffDateQuoteExists(
                            dto.getFinprodId(), DateUtil.format(dto.getEffDate())) != 0) {
                            errorList.add(new ValidMessage("", getMessage("FIN_PROD_E_0009",
                                DateUtil.format(dto.getEffDate()), "有效的手工维护报价记录")));
                        }
                    }
                    // 分层产品的校验
                    if (StringUtil.equals(dto.getIsLay(), "Y")) {
                        /*if (StringUtil.isEmpty(dto.getCustomLevel())) {
                            errorList.add(new ValidMessage("", "客户等级不能为空！"));
                        }*/
                        if (!StringUtil.isNotEmpty(dto.getAmtMin())) {
                            errorList.add(new ValidMessage("", "金额下限不能为空！"));
                        }
                        if (!StringUtil.isNotEmpty(dto.getAmtMax())) {
                            errorList.add(new ValidMessage("", "金额上限不能为空！"));
                        }
                        /*if (!StringUtil.isNotEmpty(dto.getTermMax())) {
                            errorList.add(new ValidMessage("", "期限上限不能为空！"));
                        }
                        if (!StringUtil.isNotEmpty(dto.getTermMin())) {
                            errorList.add(new ValidMessage("", "期限下限不能为空！"));
                        }*/
                        if (!StringUtil.isNotEmpty(dto.getYield2())) {
                            errorList.add(new ValidMessage("", "收益率不能为空！"));
                        }
                    }
                    else if (StringUtil.equals(dto.getIsLay(), "N")) {
                        if (!StringUtil.isNotEmpty(dto.getYield())) {
                            errorList.add(new ValidMessage("", "收益率不能为空！"));
                        }
                    }

                }
                else if ((StringUtil.equals(dto.getProfitType(),
                    DictCodeList.收益类型_净值型.getValue()))
                         || (StringUtil.equals(dto.getFinprodType(),
                             DictCodeList.收益类型_货币型.getValue()))) {
                    if (!StringUtil.isNotEmpty(dto.getValuation())) {
                        errorList.add(new ValidMessage("", "单位净值/万份收益不能为空！"));
                    }
                    if (StringUtil.isNotEmpty(dto.getFinprodId())
                        && StringUtil.isNotEmpty(dto.getEffDate())) {
                        if (finProductQuotation002Service.getValueDate(dto) != 0) {
                            errorList.add(new ValidMessage("", getMessage("FIN_PROD_E_0009",
                                DateUtil.format(dto.getEffDate()), "有效的手工维护报价记录")));
                        }
                    }
                }

                if (StringUtil.isNotEmpty(dto.getFinprodId())
                    && StringUtil.isNotEmpty(dto.getEffDate())) {
                    // 画面.生效日 < 标的类金融产品.起息日 时，提交报错
                    FinProductQuotation001Dto temp = new FinProductQuotation001Dto();
                    temp = finProductQuotation002Service.getVdateAndMdate(dto.getFinprodId());
                    if (dto.getEffDate().compareTo(temp.getVdate()) < 0) {
                        errorList.add(
                            new ValidMessage("",
                                getMessage("BUSS_COM_E_0004",
                                    getMessage("FinProductQuotation002.effDate") + DateUtil.format(
                                        dto.getEffDate()),
                                    getMessage("FinProductQuotation002.vdate") + DateUtil.format(
                                        temp.getVdate()))));
                    }
                    // 画面.生效日 > 标的类金融产品.到期日 时，提交报错
                    if (dto.getEffDate().compareTo(temp.getMdate()) > 0) {
                        errorList.add(
                            new ValidMessage("",
                                getMessage("BUSS_COM_E_0003",
                                    getMessage("FinProductQuotation002.effDate") + DateUtil.format(
                                        dto.getEffDate()),
                                    getMessage("FinProductQuotation002.mdate") + DateUtil.format(
                                        temp.getMdate()))));
                    }
                    // 画面.生效日 < 平台日 ,提交时报错
                    if (dto.getEffDate().compareTo(
                        DateUtil.platDateFromCache()) < 0) {
                        errorList.add(new ValidMessage("",
                            getMessage("BUSS_COM_E_0004",
                                getMessage("FinProductQuotation002.effDate")
                                                          + DateUtil.format(dto.getEffDate()),
                                getMessage("FinProductQuotation001.currentDate") + DateUtil.getSysDate())));
                    }
                }
            }
            else if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                //修改check
                if (DateUtil.compareDate(dto.getEffDate(), iPlatformDate.getSysDate()) < 0) {
                    errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0018",
                        getMessage("FinProductQuotation001.effDate")+":"+DateUtil.format(dto.getEffDate()),
                        getMessage("FinProductQuotation001.currentDate")+":"+DateUtil.getSysDate())));
                }
                //是否是初始利率
                if(StringUtil.isNotEmpty(dto.getIsInitial()) && dto.getIsInitial().equals(DictCodeList.是否_是.getValue())){
                    errorList.add(new ValidMessage(getMessage("初始化生成的产品报价信息不允许修改。产品代码："+dto.getFinprodMarketId())));
                }
            }

        }
        

        return errorList;
    }
}
