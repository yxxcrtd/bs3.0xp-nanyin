/*
 * 文件名：BussFinProductFeeInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;
import com.joyintech.tams.constant.DictCodeList;

/**
 * 付费信息业务组件验证类
 * 
 * @author zhangliang
 * @version 1.0 2018年10月17日
 * @see BussFinProductFeeInfoValidator
 * @since 1.0
 */
@Component
public class BussFinProductFeeInfoValidator implements BeanValidator {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        BussFinProductFeeInfoDto bussFinProductFeeInfoDto = (BussFinProductFeeInfoDto)value;

        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        List<BussFinProductFeeInfoDto> bussFinProductFeeInfoDtoList = bussFinProductFeeInfoDto.getBussFinProductFeeInfoDtoList();
        if (!StringUtil.isEmptyList(bussFinProductFeeInfoDtoList)) {
            // if (StringUtil.equals(bussFinProductFeeInfoDto.getPageId(), "FinProductBuild002")) {
            for (int i = 0; i < bussFinProductFeeInfoDtoList.size(); i++ ) {
                BussFinProductFeeInfoDto finProductFeeInfoDto = bussFinProductFeeInfoDtoList.get(
                    i);
                //不规则费用非必填
                if(StringUtil.equals(DictCodeList.收费类型_不规则费用.getValue(), finProductFeeInfoDto.getChargeType())){
                    continue;
                }
                if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getFeeId())) {
                    validList.add(
                        new ValidMessage("bussFinProductFeeInfoDtoList[" + (i) + "].feeId",
                            getMessage("BUSS_COM_E_0038",
                                getMessage("BussFinProductFeeInfo.feeName"))));
                }
                if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getFeeType())) {
                    validList.add(
                        new ValidMessage("bussFinProductFeeInfoDtoList[" + (i) + "].feeType",
                            getMessage("BUSS_COM_E_0038",
                                getMessage("BussFinProductFeeInfo.feeType"))));
                }
                if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getFeeRate())) {
                    validList.add(
                        new ValidMessage("bussFinProductFeeInfoDtoList[" + (i) + "].feeRate",
                            getMessage("BUSS_COM_E_0038",
                                getMessage("BussFinProductFeeInfo.feeRate"))));
                } else if(BigDecimal.ZERO.compareTo(finProductFeeInfoDto.getFeeRate()) == 0){
                    validList.add(
                        new ValidMessage("bussFinProductFeeInfoDtoList[" + (i) + "].feeRate",
                            getMessage("BUSS_COM_E_0036",
                                getMessage("BussFinProductFeeInfo.feeRate"), "0")));
                }
                if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getBasicBill())) {
                    validList.add(
                        new ValidMessage("bussFinProductFeeInfoDtoList[" + (i) + "].basicBill",
                            getMessage("BUSS_COM_E_0038",
                                getMessage("BussFinProductFeeInfo.basicBill"))));
                }
                if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getBasis())) {
                    validList.add(new ValidMessage(
                        "bussFinProductFeeInfoDtoList[" + (i) + "].basis",
                        getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFeeInfo.basis"))));
                }
                if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getPayFeeRule())) {
                    validList.add(
                        new ValidMessage("bussFinProductFeeInfoDtoList[" + (i) + "].payFeeRule",
                            getMessage("BUSS_COM_E_0038",
                                getMessage("BussFinProductFeeInfo.payFeeRule"))));
                }
                else {
                    if (!StringUtil.equals(finProductFeeInfoDto.getPayFeeRule(),
                        DictCodeList.频率_不规则.getValue())) {
                        // 一体化对应etliu end
                        if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getFirstCalEndDate())) {
                            validList.add(new ValidMessage(
                                "bussFinProductFeeInfoDtoList[" + (i) + "].firstCalEndDate",
                                getMessage("BUSS_COM_E_0038",
                                    getMessage("BussFinProductFeeInfo.firstCalEndDate"))));
                        }
                        if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getCalcFeeTradeRule())) {
                            validList.add(new ValidMessage(
                                "bussFinProductFeeInfoDtoList[" + (i) + "].calcFeeTradeRule",
                                getMessage("BUSS_COM_E_0038",
                                    getMessage("BussFinProductFeeInfo.calcFeeTradeRule"))));
                        }
                        if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getPayFeeTradeRule())) {
                            validList.add(new ValidMessage(
                                "bussFinProductFeeInfoDtoList[" + (i) + "].payFeeTradeRule",
                                getMessage("BUSS_COM_E_0038",
                                    getMessage("BussFinProductFeeInfo.payFeeTradeRule"))));
                        }
                    }
                }
                
            }
            // }
            // 判断费用类型是否重复
          //modify by wm 2019-08-03 判断费用类型是否重复 start 
            List<BussFinProductFeeInfoDto> bussFinProductFeeInfoDtoListTmp =  new ArrayList<BussFinProductFeeInfoDto>();
//            bussFinProductFeeInfoDtoListTmp.addAll(bussFinProductFeeInfoDtoList);
            for (BussFinProductFeeInfoDto bussFinProductFeeInfoDto2 : bussFinProductFeeInfoDtoList) {
            	BussFinProductFeeInfoDto bussFinProductFeeInfoTmp = new BussFinProductFeeInfoDto();
            	BeanUtil.copyPropertiesIgnoreNull(bussFinProductFeeInfoTmp, bussFinProductFeeInfoDto2);
				if(bussFinProductFeeInfoTmp.getFeeTypeStr()!=null){
					bussFinProductFeeInfoTmp.setFeeType(bussFinProductFeeInfoDto2.getFeeTypeStr());
				}
				bussFinProductFeeInfoDtoListTmp.add(bussFinProductFeeInfoTmp);
			}
            Map<String, BussFinProductFeeInfoDto> goalInfoMap1 = bussFinProductFeeInfoDtoListTmp.stream().collect(
                    Collectors.toMap(BussFinProductFeeInfoDto:: getProdStyleAndFeeTypeNew, a -> a, (k1, k2) -> k1));
            if (goalInfoMap1.size() != bussFinProductFeeInfoDtoList.size()) {
                validList.add(new ValidMessage("bussFinProductFeeInfoDtoList",
                    this.getMessage("FIN_PROD_E_0076")));
            }
            
//            Map<String, BussFinProductFeeInfoDto> goalInfoMap = bussFinProductFeeInfoDtoList.stream().collect(
//                Collectors.toMap(BussFinProductFeeInfoDto:: getProdStyleAndFeeType, a -> a, (k1, k2) -> k1));
//            if (goalInfoMap.size() != bussFinProductFeeInfoDtoList.size()) {
//                validList.add(new ValidMessage("bussFinProductFeeInfoDtoList",
//                    this.getMessage("FIN_PROD_E_0076")));
//            }
            //modify by wm 2019-08-03 判断费用类型是否重复 end 
        }

        return validList;
    }

}
