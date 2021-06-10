package com.joyintech.service.valuation.basicdata.base.service;/**
 * @author yanya
 * @create 2021-03-03 9:10
 */

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basicdata.base.dao.BaseFinProductPayRuleMapper;
import com.joyintech.service.valuation.basicdata.base.dto.BaseFinProductPayRuleDto;
import com.joyintech.tams.common.base.dao.FinCashPayRuleMapper;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName BaseFinProductPayRuleService
 * @Description: 现金流支付规则
 * @Author yanya
 * @Date 2021/3/5 0005
 * @Version V1.0
 **/
@Service(value="serviceBaseFinProductPayRuleService")
@Transactional(rollbackFor = Exception.class)
public class BaseFinProductPayRuleService  extends BaseService<FinCashPayRule> {

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
    public List<FinCashPayRule> select(FinCashPayRule dto) {
        return cashFlowPaymentRulesMapper.select(dto);
    }
}
