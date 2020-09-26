/*
 * 文件名：ValAssetsAmortizeSet001CheckValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年12月6日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assetsamortize.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.assetsamortize.dto.ValAssetsAmortizeSet001Dto;
import com.joyintech.tams.valuation.assetsamortize.service.ValAssetsAmortizeSet001Service;
import com.joyintech.tams.valuation.assetsamortize.service.ValAssetsAmortizeSet002Service;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年12月6日
 * @see ValAssetsAmortizeSet001CheckValidator
 * @since 1.0
 */
@Component
public class ValAssetsAmortizeSet001CheckValidator implements BeanValidator {

    /**
     * valAssetsAmortizeSet001Service
     */
    @Autowired
    private ValAssetsAmortizeSet001Service valAssetsAmortizeSet001Service;

    /**
     * valAssetsAmortizeSet002Service
     */
    @Autowired
    private ValAssetsAmortizeSet002Service valAssetsAmortizeSet002Service;

    /**
     * {@inheritDoc}
     */
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();
        ValAssetsAmortizeSet001Dto dto = (ValAssetsAmortizeSet001Dto)value;
        // 删除 check
        if (StringUtil.equals(dto.getOperate(), "delete")) {
            String cdate = valAssetsAmortizeSet001Service.getCdate(dto.getBooksetId());
            if (DateUtil.isDateStr(cdate)) {

                if ((DateUtil.compareDate(cdate, dto.getCdate()) == 1)) {
                    // 选择记录的会计日期小于账套的会计日期，不给删除！
                    String msg = this.getMessage("ASSETSAMORTIZESET_E_001");
                    validMessageList.add(new ValidMessage(msg));
                    return validMessageList;
                }

            }
            // 选择记录的在流程中，不给删除！
            if (valAssetsAmortizeSet001Service.checkBusinessDataInFlow(dto)) {
                // 选择记录的在流程中，不给删除！
                String msg = this.getMessage("ASSETSAMORTIZESET_E_002");
                validMessageList.add(new ValidMessage(msg));
                return validMessageList;

            }

        }
        // 新建 check
        if (StringUtil.equals(dto.getOperate(), "new")) {

            List<ValAssetsAmortizeSet001Dto> listDto = dto.getListValAssetsAmortizeSet001Dto();
            for (ValAssetsAmortizeSet001Dto item : listDto) {
                // 流程是否存在
                if (valAssetsAmortizeSet002Service.checkBusinessDataInFlow(item)) {
                    String msg = this.getMessage("ValAssetsAmortizeSet.busiId") + item.getBusiId()
                                 + this.getMessage("ValAssetsAmortizeSet.cdate");
                    validMessageList.add(
                        new ValidMessage(getMessage("COMMON_E_0021", msg, item.getCdate())));

                }

                if (valAssetsAmortizeSet002Service.checkBokAmoConfig(item)) {
                    String msg = this.getMessage("ValAssetsAmortizeSet.busiId") + item.getBusiId()
                                 + this.getMessage("ValAssetsAmortizeSet.cdate");
                    validMessageList.add(new ValidMessage(
                        getMessage("ASSETSAMORTIZESET_E_003", msg, item.getCdate())));
                }

            }

        }

        return validMessageList;
    }
}
