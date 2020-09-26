/*
 * 文件名：PayTheVatQuery002CheckValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年2月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.trademanage.paythevatquery.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.trademanage.paythevatquery.dto.PayTheVatQuery002Dto;
import com.joyintech.tams.valuation.trademanage.paythevatquery.dto.PayTheVatQuery002TableDto;
import com.joyintech.tams.valuation.trademanage.paythevatquery.service.PayTheVatQuery002Service;

/**
 * 增值税缴纳校验
 * @author wangqidong
 * @version 1.0 2019年2月28日
 * @see PayTheVatQuery002CheckValidator
 * @since 1.0
 */
@Component
public class PayTheVatQuery002CheckValidator implements BeanValidator{
    
    @Autowired
    private PayTheVatQuery002Service payTheVatQuery002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        PayTheVatQuery002Dto dto = (PayTheVatQuery002Dto)value;
        // 错误信息
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        if(CollectionUtils.isEmpty(dto.getListpayTheVatQuery002TableDto())){
            // 必须有明细数据才可执行流程
            errorList.add(new ValidMessage(getMessage("需有产品缴纳增值税明细数据")));
            return errorList;
        }
        
        // 检查是否有在流程中的数据
        if (payTheVatQuery002Service.checkBusinessDataInFlow(dto)) {
            // 当页面选择数据状态为撤销时 提示"选择的有效数据。";
            errorList.add(new ValidMessage(getMessage("当前数据已在流程中")));
        }
        
        // 查询缴税/退税日期小于账套日期的账套
        Map<String, Object> params = new HashMap<>();
        String booksetIds = "";
        for(PayTheVatQuery002TableDto item :dto.getListpayTheVatQuery002TableDto()){
            // 校验时去除最后一行汇总行
            if(StringUtil.isEmpty(item.getBooksetName())){
                continue;
            }
            if(StringUtil.isNotEmpty(booksetIds)){
                booksetIds += ","+item.getBooksetId();
            }
            else{
                booksetIds = item.getBooksetId();
            }
        }
        params.put("booksetId", booksetIds);
        params.put("payment2", dto.getPayment2());
        List<PayTheVatQuery002Dto> listdto = payTheVatQuery002Service.checkdate(params);       
        if(!CollectionUtils.isEmpty(listdto)){
            listdto.forEach(item->{
                errorList.add(new ValidMessage(getMessage("缴税/退税日期不能小于账套【"+item.getBooksetId()+"】的账套日期")));
            });
        }
        return errorList;
    }

}
