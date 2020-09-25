package com.joyintech.tams.invest.common.autoperiod.ptlreceinfo.dao;

import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.invest.common.autoperiod.ptlreceinfo.dto.PtlReceInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PtlReceInfoMapper {
    PtlReceInfo genPtlReceInfo(Map<String, Object> map);
    List<FinCashCalcRule> finCashCalcRuleList(String CASH_ID);
    List<FinCashPlan> selectFinCashPlanList(String CASH_ID);
}
