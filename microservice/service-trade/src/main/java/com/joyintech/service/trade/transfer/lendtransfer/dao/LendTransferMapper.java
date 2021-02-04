package com.joyintech.service.trade.transfer.lendtransfer.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.transfer.lendtransfer.dto.LendTransferDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 拆借资产转仓Mapper
 * @author liuwei
 * @version 1.0 2020年02月18日
 * @see LendTransferMapper
 * @since 1.0
 */
@Repository
public interface LendTransferMapper {
    /**
     * 获取转仓信息—组合代码
     * @return 联想下拉框实体类集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPortfolioId(Map<String, Object> map);

    /**
     * 获取转仓基本信息
     * @return 交易信息
     * @see
     * @since 1.0
     */
    LendTransferDto getTradeInfo(Map<String,Object> map);
}
