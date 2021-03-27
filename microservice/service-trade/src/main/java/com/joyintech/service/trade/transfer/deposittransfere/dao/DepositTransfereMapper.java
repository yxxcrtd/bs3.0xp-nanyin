package com.joyintech.service.trade.transfer.deposittransfere.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.transfer.deposittransfere.dto.DepositTransfereService001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 存款类资产交易转仓
 * @author hepengli
 * @version 1.0 2019年12月14日
 * @see DepositTransfereMapper
 * @since 1.0
 */
@Repository
public interface DepositTransfereMapper {

    /**
     * 查询组合代码
     * @param param
     * @return
     */
    List<SelectOptionDto> selectPortoId(Map<String, Object> param);

    /**
     * 查询利息信息
     * @param param
     * @return
     */
    DepositTransfereService001Dto queryAssetInt(Map<String, Object> param);

    String getProdRegistCode(Map<String, Object> map);

    /**
     * 获取交易编号list
     * @param map 日期、币种
     * @return 汇率（收盘价）
     * @see
     * @since 1.0
     */
    int getWorkflowTradeId(Map<String,Object> map);
}
