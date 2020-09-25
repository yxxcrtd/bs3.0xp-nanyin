package com.joyintech.tams.component.business.validator;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.component.business.common.dao.CommonBusinessMapper;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
//import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 一些共通的check
 * @author 强成西
 */
public interface CommonValidator extends BeanValidator {
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

        if (StringUtil.isEmptyList(validMessageList)) {
            Field counterTypeField = ReflectionUtils.findField(value.getClass(), "counterType");
            if (DictCodeList.是否_是.getValue().equals(CacheUtil.getSysParam(BussinessConstant.IS_AUTO_GENERATE_NEGATE_DEAL))) {
                // check 属性交易对手时内部交易对手，只能选择卖出
                Field psField = ReflectionUtils.findField(value.getClass(), "ps");
                if (counterTypeField != null && psField != null) {
                    ReflectionUtils.makeAccessible(counterTypeField);
                    ReflectionUtils.makeAccessible(psField);
                    Object counterType = ReflectionUtils.getField(counterTypeField, value);
                    Object ps = ReflectionUtils.getField(psField, value);
                    if (StringUtil.isNotEmpty(counterType) && StringUtil.isNotEmpty(ps)) {
                        if ("N".equals(counterType) && !DictCodeList.交易方向_卖出.getValue().equals(ps)) {
                            validMessageList.add(new ValidMessage("ps", "选择内部交易对手时，交易方向只能选择卖出"));
                        }
                    }
                }
            }

            // check通道时，不能选择内部交易对手
            Field chlIdField = ReflectionUtils.findField(value.getClass(), "chlId");
            if (counterTypeField != null && chlIdField != null) {
                ReflectionUtils.makeAccessible(counterTypeField);
                ReflectionUtils.makeAccessible(chlIdField);
                Object counterType = ReflectionUtils.getField(counterTypeField, value);
                Object chlId = ReflectionUtils.getField(chlIdField, value);
                if (StringUtil.isNotEmpty(counterType) && StringUtil.isNotEmpty(chlId) && "N".equals(counterType)) {
                        validMessageList.add(new ValidMessage("chlId", "选择内部交易对手时，不能选择通道"));
                }
            }

            // check通道时，通道与组合不能挂同一个账户
            Field portfolioIdField = ReflectionUtils.findField(value.getClass(), "portfolioId");
            if (portfolioIdField != null && chlIdField != null) {
                ReflectionUtils.makeAccessible(portfolioIdField);
                ReflectionUtils.makeAccessible(chlIdField);
                Object portfolioId = ReflectionUtils.getField(portfolioIdField, value);
                Object chlId = ReflectionUtils.getField(chlIdField, value);
                if (StringUtil.isNotEmpty(portfolioId) && StringUtil.isNotEmpty(chlId)) {
                    CommonBusinessMapper mapper = SpringContextUtil.getBean(CommonBusinessMapper.class);
                    Boolean isSame = mapper.checkPortChlAcctNoSame((String) portfolioId, (String) chlId);
                    if (isSame != null && isSame) {
                        validMessageList.add(new ValidMessage("chlId", "所选择的通道与组合有相同的账户"));
                    }
                }
            }

            // check 属性bussTrdTransferOrder
            Field field = ReflectionUtils.findField(value.getClass(), "bussTrdTransferOrder");
            if (field != null) {
                ReflectionUtils.makeAccessible(field);
                Object orders = ReflectionUtils.getField(field, value);
                if (orders == null) {
                    checkOrder(value, validMessageList);
                }
                else if (orders instanceof List) {
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
            else {
                checkOrder(value, validMessageList);
            }
        }
        return validMessageList;
    }

    /**
     * check 属性stlPayOrders
     * @param value
     * @param validMessageList
     */
    default void checkOrder(Object value, List<ValidMessage> validMessageList) {
        Field field1 = ReflectionUtils.findField(value.getClass(), "stlPayOrders");
        if (field1 != null) {
            ReflectionUtils.makeAccessible(field1);
            Object orders1 = ReflectionUtils.getField(field1, value);
            if (orders1 instanceof StlPayOrders) {
                StlPayOrders order1 = (StlPayOrders) orders1;
                if ((DictCodeList.是否_是.getValue().equals(order1.getIsNeedSend()) || DictCodeList.是否_是.getCodeName().equals(order1.getIsNeedSend())) && (StringUtil.isEmpty(order1.getMyAcctNo()) || StringUtil.isEmpty(order1.getCounterAcctNo()))) {
                    validMessageList.add(new ValidMessage("是否发送指令是：是，收付款账户都不能为空。"));
                }
            }
        }
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
