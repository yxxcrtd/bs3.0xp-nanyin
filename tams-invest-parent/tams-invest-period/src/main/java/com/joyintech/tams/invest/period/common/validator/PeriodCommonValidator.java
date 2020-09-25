/*
 * 文件名：PeriodCommonValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年1月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.period.common.validator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCodeList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.WflPaTrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdProductDealAdd;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.period.trdbondduration.dto.TrdBondDuration002ProcessDto;
import com.joyintech.tams.invest.period.trdbondduration.service.TrdBondDuration002Service;
import com.joyintech.tams.invest.period.trdlendduration.dto.TrdLendDuration001Dto;
import com.joyintech.tams.invest.period.trdlendduration.service.TrdLendDurationDealEntity;
import org.springframework.util.ReflectionUtils;

/**
 * 存续期交易共通Validator
 *
 * @author etliu
 * @version 1.0 2019年1月30日
 * @see PeriodCommonValidator
 * @since 1.0
 */
public interface PeriodCommonValidator extends BeanValidator {
    
    /**
     * 日志
     */
    public static Logger logger = LoggerFactory.getLogger(PeriodCommonValidator.class);

    /**
     * 验证方法
     *
     * @param value 需要被验证的业务对象
     * @return 验证消息，如果为空则表示验证通过
     * @since 1.0
     */
    @Override
    default List<ValidMessage> valid(Object value) {
        // 先执行各个画面的业务check
        List<ValidMessage> validMessageList = this.bussValid(value);
        
        // 当业务check没有问题，则执行风险相关交易共通check
        if (StringUtil.isEmptyList(validMessageList)) {
            try {
//                TrdProductDeal trdProductDeal = null;
//                
//                // 债券存续期
//                if (value instanceof TrdBondDuration002ProcessDto) {
//                    TrdBondDuration002ProcessDto inputDto = (TrdBondDuration002ProcessDto) value;
//                    TrdBondDuration002Service pageService = SpringContextUtil.getBean(
//                        TrdBondDuration002Service.class);
//                    if (pageService != null
//                        && (StringUtil.equals("submit", inputDto.getOperateType())
//                            || StringUtil.equals("save", inputDto.getOperateType()))) {
//                        pageService.genDataForInsertDto(inputDto);
//                        List<WflTrdProductDeal> wflTrdProductDealList = inputDto.getTrdBondDurDbInertDto().getWflTrdProductDealList();
//
//                        trdProductDeal = new TrdProductDeal();
//                    }
//                }
//                // 拆借存续期
//                if (value instanceof TrdLendDuration001Dto) {
//                    TrdLendDuration001Dto inputDto = (TrdLendDuration001Dto) value;
//                    TrdLendDurationDealEntity pageService = SpringContextUtil.getBean(
//                        TrdLendDurationDealEntity.class);
//                    if (pageService != null
//                        && (StringUtil.equals("submit", inputDto.getOperateType())
//                            || StringUtil.equals("save", inputDto.getOperateType()))) {
//                        List<WflPaTrdProductDeal> wflPaTrdProductDeals = new ArrayList<>();
//                        List<WflTrdProductDealAdd> wflTrdProductDealAdds = new ArrayList<>();
//                        List<WflTrdProductDeal> wflTrdProductDealList = pageService.gen交易业务流程(
//                            inputDto, wflPaTrdProductDeals, wflTrdProductDealAdds);
//
//                        trdProductDeal = new TrdProductDeal();
//                    }
//                }
//
//                // 调用风险相关交易共通check
//                if (trdProductDeal != null) {
//                    
//                }
                Field field = ReflectionUtils.findField(value.getClass(), "bussTrdTransferOrder");
                if (field != null) {
                    ReflectionUtils.makeAccessible(field);
                    Object orders = ReflectionUtils.getField(field, value);
                    if (orders instanceof List) {
                        List<Object> orderList = (ArrayList) orders;
                        for (Object order : orderList) {
                            if (order instanceof BussTrdTransferOrderDto) {
                                BussTrdTransferOrderDto dto = (BussTrdTransferOrderDto) order;
                                if ((DictCodeList.是否_是.getValue().equals(dto.getIsNeedSend()) || DictCodeList.是否_是.getCodeName().equals(dto.getIsNeedSend())) && (StringUtil.isEmpty(dto.getRecAcctNo()) || StringUtil.isEmpty(dto.getPayAcctNo()))) {
                                    validMessageList.add(new ValidMessage("是否发送指令是：是，收付款账户都不能为空。"));
                                }
                            }
                        }
                    }
                }
            }
            catch (Exception e) {
                logger.info("执行风险相关交易共通check出现异常，异常信息如下：");
                logger.info(e.getMessage());
                e.printStackTrace();
            }
        }
        
        return validMessageList;
    }

    /**
     * 验证方法
     *
     * @param value 需要被验证的业务对象
     * @return 验证消息，如果为空则表示验证通过
     * @since 1.0
     */
    List<ValidMessage> bussValid(Object value);
}
