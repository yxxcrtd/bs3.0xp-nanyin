/*
 * 文件名：FinProductPayInt002DtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年1月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.payint.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;
import com.joyintech.tams.invest.product.payint.dto.FinProductPayInt002Dto;

/**
 * 产品中间付息确认保存提交校验
 *
 * @author etliu
 * @version 1.0 2019年1月8日
 * @see FinProductPayInt002DtoValidator
 * @since 1.0
 */
@Component
public class FinProductPayInt002DtoValidator implements BeanValidator {

    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    @Autowired
    private IPlatformDate platformDateImpl;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FinProductPayInt002Dto inputDto = (FinProductPayInt002Dto) value;
        List<ValidMessage> errorList = new ArrayList<>();

        if (bussTrdTransferOrderService != null) {
            List<ValidMessage> list = bussTrdTransferOrderService.check(
                inputDto.getBussTrdTransferOrder());
            errorList.addAll(list);
        }
        //校验收付款账户
        if(!StringUtil.isEmptyList(inputDto.getBussTrdTransferOrder())){
            for (BussTrdTransferOrderDto order : inputDto.getBussTrdTransferOrder()) {
                if (!StringUtil.isNotEmpty(order) || (!StringUtil.isNotEmpty(order.getRecAcctNo())&&"Y".equals(order.getIsNeedSend()))) {
                    errorList.add(new ValidMessage("bussTrdTransferOrder",
                            getMessage("FW_E_0006", getMessage("BussTrdTransferOrder.recAcctNo"))));
                }
            }
        }
        // 利息明细信息Grid 实付利息
        if (!StringUtil.isEmptyList(inputDto.getIntDetailDataList())) {
            boolean emptyFlg = false;
//            boolean compareErrorFlg = false;
            for (ReduceInfoDto intDetailDto : inputDto.getIntDetailDataList()) {
                if (intDetailDto.getActInterest() == null) {
                    emptyFlg = true;
                }
//                else if (intDetailDto.getActInterest().compareTo(BigDecimal.ZERO) <= 0) {
//                    compareErrorFlg = true;
//                }
            }

            String itemName = getMessage("FinProductPayInt002.intDetailInfoGrid")
                + GlobalConstant.POINT
                + getMessage("FinProductPayInt002.intDetailInfo.actInt");
            if (emptyFlg) {
                ValidMessage validMessage = new ValidMessage("intDetailDataList",
                    getMessage("BUSS_COM_E_0038", itemName));
                errorList.add(validMessage);
            }
//            if (compareErrorFlg) {
//                ValidMessage validMessage = new ValidMessage("intDetailDataList",
//                    getMessage("BUSS_COM_E_0036", itemName, "0"));
//                errorList.add(validMessage);
//            }
        }
        if(!StringUtil.isNotEmpty(inputDto.getFinprodId())){
        	ValidMessage validMessage = new ValidMessage("finprodId","产品代码不能为空");
            errorList.add(validMessage);
        }else{
        	if(inputDto.getPayintStartDate().compareTo(inputDto.getPayintEndDate())>=0) {
            	ValidMessage validMessage = new ValidMessage("payintEndDate","付息结束日不能小于等于付息起始日");
                    errorList.add(validMessage);
            }
        }
        if(StringUtil.isNotEmpty(inputDto.getPayDate())){
        	if(inputDto.getPayDate().compareTo(platformDateImpl.getSysDate())<0) {
            	ValidMessage validMessage = new ValidMessage("payDate","支付日不能小于平台日期");
                    errorList.add(validMessage);
            }
        }
        if(StringUtil.isNotEmpty(inputDto.getPayDate())){
            if(inputDto.getPayDate().compareTo(inputDto.getPayintEndDate())<0) {
                ValidMessage validMessage = new ValidMessage("payDate","支付日不能小于本次付息结束日");
                    errorList.add(validMessage);
            }
        }
        return errorList;
    }

}
