package com.joyintech.tams.invest.common.reconciliation.dao;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.reconciliation.dto.DayEndAccountDaichuliDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReconciliationCommonMapper {
    /**
     * 获取待处理的对账数据
     * @param param param
     * @return
     */
    List<DayEndAccountDaichuliDto> queryAccountTodoList(Map<String, Object> param);

    /**
     * 查询对账参数
     * @param param
     * @return
     */
    List<StlSecReconPara> queryAccountPara(Map<String, Object> param);

    /**
     * 交易费用配置查询
     * @param searchParam 方案查询信息
     * @return
     */
    List<TrdFeeConfig> getTrdFeeConfig(Map<String, Object> searchParam);

    /**
     * 根据费用配置流水号，查询交易费用配置明细
     * @param seqno
     * @return
     */
    List<TrdFeeConfigDetail> getTrdFeeConfigDetail(String seqno);

    /**
     * 查询成交数据明细
     * @param param param
     * @return
     */
    List<SettleTransData> getSettleTransData(Map<String, Object> param);

    /**
     * 删除成交明细数据
     * @param param param
     */
    void deleteStlSecReconDetail(Map<String, Object> param);

    /**
     * 批量插入券商对账结果
     * @param list 处理结果
     */
    void insertStlSecReconBatchData(List<StlSecRecon> list);

    /**
     * 批量插入券商对账结果详情
     * @param list 处理结果详情
     */
    void insertStlSecReconDetailBatchData(List<StlSecReconDetail> list);
}
