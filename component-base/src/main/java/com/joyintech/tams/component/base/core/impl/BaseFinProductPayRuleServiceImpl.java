/*
 * 文件名：CashFlowPaymentRulesImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.core.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.FinCashPayRuleMapper;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.base.core.BaseFinProductPayRuleService;
import com.joyintech.tams.component.base.core.dto.BaseFinProductPayRuleDto;
import com.joyintech.tams.component.base.core.impl.dao.BaseFinProductPayRuleMapper;
import com.joyintech.tams.invest.common.constant.BussinessConstant;

/**
 * 金融产品现金流支付规则组件 〈功能详细描述〉
 * 
 * @author xiexinting
 * @version 1.0 2018年8月29日
 * @see BaseFinProductPayRuleServiceImpl
 * @since 1.0
 */
@Service
public class BaseFinProductPayRuleServiceImpl extends BaseService<FinCashPayRule> implements BaseFinProductPayRuleService {

    /**
     * 现金流支付规则表mapper对象
     */
    @Autowired
    private FinCashPayRuleMapper finCashPayRuleMapper;

    /**
     * 现金流支付规则自定义mapper对象
     */
    @Autowired
    private BaseFinProductPayRuleMapper cashFlowPaymentRulesMapper;

    /**
     * 新建现金流支付规则
     */
    @Override
    public boolean insert(List<FinCashPayRule> list) {

        if (list != null && list.size() > 0) {
            for (FinCashPayRule dto : list) {
                // 一体化对应etliu 现金流支付规则表，支付频率没存值，可能有问题
//                if (StringUtil.isBlank(dto.getPayCycle())) {
//                    dto.setPayCycle(BussinessConstant.DEFAULT_PAY_CYCLE);
//                }
                
                finCashPayRuleMapper.insert(dto);
            }
            return true;
        }

        return false;
    }

    /**
     * 删除现金流支付规则
     */
    @Override
    public boolean delete(FinCashPayRule dto) {
        if (StringUtil.isNotEmpty(dto.getCashId())) {
            cashFlowPaymentRulesMapper.delete(dto);
            return true;
        }
        return false;
    }

    /**
     * 修改现金流支付规则
     */
    @Override
    public boolean update(BaseFinProductPayRuleDto dto) {
        String cashFlowId = dto.getCashFlowId();
        List<FinCashPayRule> list = dto.getFinCashPayRuleList();
        if (StringUtil.isNotEmpty(cashFlowId) && list != null && list.size()>0) {
            FinCashPayRule entity = new FinCashPayRule();
            entity.setCashId(cashFlowId);
            entity.setEffDate(dto.getEffectivDay());
            if (this.delete(entity)) {
               return this.insert(list);
            }
        }
        return false;
    }

    /**
     * 查询现金流支付规则
     */
    @Override
    public List<FinCashPayRule> select(FinCashPayRule dto) {
        return cashFlowPaymentRulesMapper.select(dto);
    }
}
