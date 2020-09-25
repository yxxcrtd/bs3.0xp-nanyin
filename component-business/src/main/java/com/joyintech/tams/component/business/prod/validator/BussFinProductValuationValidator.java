/*
 * 文件名：BussFinProductValuationValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.prod.dto.BussFinProductValuationDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 产品估值信息业务验证
 * 
 * @author 何阳阳
 * @version 1.0 2018年9月17日
 * @see BussFinProductValuationValidator
 * @since 1.0
 */
@Component
public class BussFinProductValuationValidator implements BeanValidator {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        BussFinProductValuationDto dto = (BussFinProductValuationDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();

        if (StringUtil.equals(dto.getPageId(), "FinProductBuild002")) {
            // 收益类型
            String profitType = dto.getProfitType();
            if (StringUtil.equals(profitType, DictCodeList.收益类型_收益型.getValue())) {
                // 当收益类型为“收益型”时，“利率小数位”，“利率处理方式”，“托管活期是否计息”必填
                if (null == dto.getRateDec()) {
                    resultList.add(new ValidMessage("rateDec",
                        getMessage("FIN_PROD_E_0024",
                            DictCodeList.收益类型_收益型.getCodeName(),
                            getMessage("ValuactionInfo.rateDec"))));
                } else if(dto.getRateDec() > 12){
                    resultList.add(new ValidMessage("rateDec", getMessage("FIN_PROD_E_0017",
                        getMessage("ValuactionInfo.rateDec"), "整数12")));
                }
                if (StringUtil.isEmpty(dto.getRatevalueMode())) {
                    resultList.add(new ValidMessage("ratevalueMode",
                        getMessage("FIN_PROD_E_0024",
                            DictCodeList.收益类型_收益型.getCodeName(),
                            getMessage("ValuactionInfo.ratevalueMode"))));
                }
                if (StringUtil.isEmpty(dto.getIsCountingCurInt())) {
                    resultList.add(new ValidMessage("isCountingCurInt",
                        getMessage("FIN_PROD_E_0024",
                            DictCodeList.收益类型_收益型.getCodeName(),
                            getMessage("ValuactionInfo.isCountingCurInt"))));
                }
            }
            else if (StringUtil.equals(profitType, DictCodeList.收益类型_净值型.getValue())) {
                // 当收益类型为 “净值型”时，“单位净值小数位”，“单位净值位数处理方式”，“托管活期是否计息”必填
                if (null == dto.getUnitvalueDec()) {
                    resultList.add(new ValidMessage("unitvalueDec",
                        getMessage("FIN_PROD_E_0024", DictCodeList.收益类型_净值型.getCodeName(),
                            getMessage("ValuactionInfo.unitvalueDec"))));
                }
                if (StringUtil.isEmpty(dto.getUnitvalueMode())) {
                    resultList.add(new ValidMessage("unitvalueMode",
                        getMessage("FIN_PROD_E_0024", DictCodeList.收益类型_净值型.getCodeName(),
                            getMessage("ValuactionInfo.unitvalueMode"))));
                }
                if (StringUtil.isEmpty(dto.getIsCountingCurInt())) {
                    resultList.add(new ValidMessage("isCountingCurInt",
                        getMessage("FIN_PROD_E_0024", DictCodeList.收益类型_净值型.getCodeName(),
                            getMessage("ValuactionInfo.isCountingCurInt"))));
                }
            }
            else if (StringUtil.equals(profitType, DictCodeList.收益类型_货币型.getValue())) {
                // 当收益类型为“货币型”时，“万份收益小数位”，“万份收益位数处理方式”必填
                if (null == dto.getMilionProfitDec()) {
                    resultList.add(new ValidMessage("milionProfitDec",
                        getMessage("FIN_PROD_E_0024", DictCodeList.收益类型_货币型.getCodeName(),
                            getMessage("ValuactionInfo.milionProfitDec"))));
                }
                if (StringUtil.isEmpty(dto.getMilionProfitDecMode())) {
                    resultList.add(new ValidMessage("milionProfitDecMode",
                        getMessage("FIN_PROD_E_0024", DictCodeList.收益类型_货币型.getCodeName(),
                            getMessage("ValuactionInfo.milionProfitDecMode"))));
                }
            }
        }else if(StringUtil.equals(dto.getPageId(), "FinNetValueProductBuild002")) {
        	if (null == dto.getUnitvalueDec()) {
                resultList.add(new ValidMessage("unitvalueDec", getMessage("BUSS_COM_E_0038",
                        getMessage("ValuactionInfo.unitvalueDec"))));
            } else if(dto.getUnitvalueDec() > 12){
                resultList.add(new ValidMessage("unitvalueDec", getMessage("FIN_PROD_E_0017",
                    getMessage("ValuactionInfo.unitvalueDec"), "整数12")));
            }
        	  if (StringUtil.isEmpty(dto.getUnitvalueMode())) {
                  resultList.add(new ValidMessage("unitvalueMode", getMessage("BUSS_COM_E_0038",
                          getMessage("ValuactionInfo.unitvalueMode"))));
              }
        		if (null == dto.getMilionProfitDec()) {
                    resultList.add(new ValidMessage("milionProfitDec", getMessage("BUSS_COM_E_0038",
                            getMessage("ValuactionInfo.yearYieldDec"))));
                } else if(dto.getMilionProfitDec() > 12){
                    resultList.add(new ValidMessage("milionProfitDec", getMessage("FIN_PROD_E_0017",
                        getMessage("ValuactionInfo.yearYieldDec"), "整数12")));
                }
        	  if (StringUtil.isEmpty(dto.getMilionProfitDecMode())) {
        		  resultList.add(new ValidMessage("milionProfitDecMode", getMessage("BUSS_COM_E_0038",
                          getMessage("ValuactionInfo.yearYieldDecMode"))));
              }
        	  if (StringUtil.equals(dto.getProfitType(), DictCodeList.收益类型_货币型.getValue())) {
        		  if (StringUtil.isEmpty(dto.getDealLossMode())) {// 亏损处理方式，缩减当前份额、延至下期处理
            		  resultList.add(new ValidMessage("dealLossMode", getMessage("BUSS_COM_E_0038",
                              getMessage("ValuactionInfo.dealLossMode"))));
                  }
        	  }
        	  if(null != dto.getUnitvalueDec() && dto.getUnitvalueDec()<0){
                  resultList.add(new ValidMessage("unitvalueDec",
                      getMessage("FIN_PROD_E_0023", getMessage("ValuactionInfo.unitvalueDec"),
                          "0")));
              }
        	  if(null != dto.getMilionProfitDec() && dto.getMilionProfitDec()<0){
                  resultList.add(new ValidMessage("milionProfitDec",
                      getMessage("FIN_PROD_E_0023", getMessage("ValuactionInfo.milionProfitDec"),
                          "0")));
              }
        }

        return resultList;
    }

}
