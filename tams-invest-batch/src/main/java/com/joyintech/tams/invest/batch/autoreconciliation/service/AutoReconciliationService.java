package com.joyintech.tams.invest.batch.autoreconciliation.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListStl;
import com.joyintech.tams.invest.batch.autoreconciliation.dao.AutoReconciliationMapper;
import com.joyintech.tams.invest.batch.busiAutoAddSecInfo.service.BusiAutoAddSecInfoService;
import com.joyintech.tams.invest.common.reconciliation.dto.DayEndAccountDaichuliDto;
import com.joyintech.tams.invest.common.reconciliation.service.ReconciliationCommonService;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 自动对账批处理
 *
 * @author fuyongsheng
 * @version 1.0 2021年10月26日
 * @see AutoReconciliationService
 * @since 1.0
 */
@Service
public class AutoReconciliationService {

    @Autowired
    private ReconciliationCommonService reconciliationCommonService;

    @Autowired
    private AutoReconciliationMapper autoReconciliationMapper;
    /**
     * 平台日获取接口
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;

    @Transactional(rollbackFor=Exception.class)
    public void reconciliation(Date batchDate){
        Map<String, Object> param = new HashMap<>();
        String sysDate = iPlatformDate.getSysDateStr();
        param.put("sysdate", sysDate);
        param.put("batchDate", DateUtil.format(batchDate));
        param.put("PARA_CODE_02", DictCodeListStl.参数代码_对账维度.getValue());
        param.put("PARA_VALUE_01", DictCodeListStl.对账维度_证券汇总.getValue());
        param.put("PARA_VALUE_02", DictCodeListStl.对账维度_委托汇总.getValue());
        param.put("PARA_VALUE_03", DictCodeListStl.对账维度_成交明细.getValue());
        param.put("CHECK_RESULT_0", DictCodeListExt3.对账状态结果_不一致.getValue());
        //获取待处理的对账数据
        List<DayEndAccountDaichuliDto> list = autoReconciliationMapper.queryAccountTodoList(param);
        reconciliationCommonService.executeReconciliation(list);
    }
}
