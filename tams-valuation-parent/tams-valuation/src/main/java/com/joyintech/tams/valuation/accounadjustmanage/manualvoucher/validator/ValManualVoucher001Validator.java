/*
 * 文件名：ValVoucherQuery001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.manualvoucher.validator;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.BokBooksetDateMapper;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.tams.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.manualvoucher.service.ValManualVoucher001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * 手工凭证删除校验
 * @author weipeng
 * @version 1.0 2018年7月11日
 * @see ValManualVoucher003Validator
 * @since 1.0
 */
@Component
public class ValManualVoucher001Validator implements BeanValidator{
    
    @Autowired
    private ValManualVoucher001Service valManualVoucher001Service;

    @Autowired
    private BokBooksetDateMapper bokBooksetDateMapper;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        
        ValManualVoucher001Dto dto = (ValManualVoucher001Dto) value;
        /*add by sunshuaishuai  20210304 start*/
        List<ValManualVoucher001Dto> dtoList = dto.getSelectedItem();

        //错误信息
        ArrayList<ValidMessage> errorList = new ArrayList<>();

        //删除时的校验
        if(StringUtil.isNotEmpty(dtoList) && "delete".equals(dto.getOperateType())){
            for (ValManualVoucher001Dto entity : dtoList){
                //判断当前流程是否存在
                if(valManualVoucher001Service.checkBusinessDataInFlow(entity)){
                    //流程存在情况
                    errorList.add(new ValidMessage(getMessage("COMMON_E_0021","标识",entity.getSeqno())));
                }

                // 账套日
                BokBooksetDate valBokBooksetDate = bokBooksetDateMapper.selectByPrimaryKey(
                        entity.getBooksetId());
                // 记账日期 != 账套日期.当前日期
                if (!DateUtil.isSameDate(DateUtil.parse(entity.getBookDate()),valBokBooksetDate.getBooksetDate())) {
                    // 数据录入非当前日期
                    String msg= this.getMessage("MANUAL_VOUCHER_E_011","标识",entity.getBookId());
                    errorList.add(new ValidMessage(msg));

                }
            }
            return errorList;
        }
        //修改时的校验
        if(StringUtil.isNotEmpty(dto.getOperateType()) && "edit".equals(dto.getOperateType())){
            if(valManualVoucher001Service.checkBusinessDataInFlow(dto)){
                //流程存在情况
                errorList.add(new ValidMessage(getMessage("COMMON_E_0021","标识",dto.getSeqno())));
                return errorList;
            }

            // 账套日
            BokBooksetDate valBokBooksetDate = bokBooksetDateMapper.selectByPrimaryKey(
                    dto.getBooksetId());
            // 记账日期 != 账套日期.当前日期
            if (!DateUtil.isSameDate(DateUtil.parse(dto.getBookDate()),valBokBooksetDate.getBooksetDate())) {
                // 数据录入非当前日期
                String  msg = this.getMessage("MANUAL_VOUCHER_E_012");
                errorList.add(new ValidMessage(msg));
                return errorList;
            }
        }

        /*add by sunshuaishuai  20210304 end*/
        return errorList;
    }
    
}
