/*
 * 文件名：ValManualVoucher003Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年7月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.validator;


import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dao.ValManualVoucherTemplete003Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete003AddDto;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete003Dto;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.service.ValManualVoucherTemplete003Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 手工凭证模板提交校验
 * 
 * @author sunshuaishuai
 * @version 1.0 2021年2月23日
 * @see ValManualVoucherTemplete003Validator
 * @since 1.0
 */
@Component
public class ValManualVoucherTemplete003Validator implements BeanValidator {
    @Autowired
    private ValManualVoucherTemplete003Mapper valManualVoucherTemplete003Mapper;

    @Autowired
    private ValManualVoucherTemplete003Service valManualVoucherTemplete003Service;
    /**
     * 列表确认时校验
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ValManualVoucherTemplete003Dto dto = (ValManualVoucherTemplete003Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();

        if(StringUtil.isNotEmpty(dto.getTmpName()) && StringUtil.isNotEmpty(dto.getTmpCode())){
            Map<String,Object> map = new HashMap<>();
            map.put("tmpCode",dto.getTmpCode());
            map.put("tmpName",dto.getTmpName());
            int count = valManualVoucherTemplete003Service.checkByTmpName(map);
            if(count>0){
                String msg = this.getMessage("VAL_COMMON_E_0028",
                        this.getMessage("ValManualVoucherTemplete.tmpName")
                        );
                errorList.add(new ValidMessage(msg));
            }
        }
        if(StringUtil.isNotEmpty(dto.getTmpName()) && !StringUtil.isNotEmpty(dto.getTmpCode())){
            int count = valManualVoucherTemplete003Service.checkTmpName(dto.getTmpName());
            if(count>0){
                String msg = this.getMessage("VAL_COMMON_E_0028",
                        this.getMessage("ValManualVoucherTemplete.tmpName")
                );
                errorList.add(new ValidMessage(msg));
            }
        }
        // 列表为空
        if(CollectionUtils.isEmpty(dto.getListAddDto())){
            // 凭证分录不能为空
            String msg = this.getMessage("VAL_COMMON_E_0002",
                    this.getMessage("ValManualVoucher.listAddDto"));
            errorList.add(new ValidMessage(msg));
        }else{
            for (ValManualVoucherTemplete003AddDto detail : dto.getListAddDto()) {
                // 如果科目号为空
                if (StringUtils.isEmpty(detail.getSubjectNo())) {
                    String msg = this.getMessage("VAL_COMMON_E_0002",
                            this.getMessage("ValManualVoucher.subjectNo"));
                    errorList.add(new ValidMessage(msg));
                    return errorList;
                }
                // 借方金额、贷方金额都不为空时
                if ((detail.getdAmt() != null)
                        && (detail.getcAmt() != null)) {
                    // 借方金额、贷方金额只能填写一个
                    String msg = this.getMessage("MANUAL_VOUCHER_E_002",
                            this.getMessage("ValManualVoucher.dAmt"),
                            this.getMessage("ValManualVoucher.cAmt"));
                    errorList.add(new ValidMessage(msg));
                    return errorList;
                }
                    // 借方金额、贷方金额都为空时
//                } else if ((detail.getdAmt() == null )
//                        && (detail.getcAmt() == null )) {
//                    // 借方金额、贷方金额不能同时为空
//                    String msg = this.getMessage("MANUAL_VOUCHER_E_004",
//                            this.getMessage("ValManualVoucher.dAmt"),
//                            this.getMessage("ValManualVoucher.cAmt"));
//                    errorList.add(new ValidMessage(msg));
//                    return errorList;
//                }

                // 取得列表币种
//                if (StringUtils.isEmpty(detail.getCcy())) {
//                    String msg = this.getMessage("VAL_COMMON_E_0002",
//                            this.getMessage("ValManualVoucher.ccy"));
//                    errorList.add(new ValidMessage(msg));
//                    return errorList;
//                }
                if(((detail.getdAmt() != null) || (detail.getcAmt() != null)) && detail.getCcy()==null){
                    String msg = this.getMessage("VAL_COMMON_E_0002",
                            this.getMessage("ValManualVoucher.ccy"));
                    errorList.add(new ValidMessage(msg));
                }
            }
        }

        //判断当前流程是否存在
        if(StringUtil.isEmpty(dto.getWorkflowId()) && valManualVoucherTemplete003Service.checkBusinessDataInFlow(dto)){
            //流程存在情况
            errorList.add(new ValidMessage(getMessage("COMMON_E_0021","标识",dto.getTmpCode())));
        }

        return errorList;
    }

}
