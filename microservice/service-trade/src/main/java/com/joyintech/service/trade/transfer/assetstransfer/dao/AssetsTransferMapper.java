package com.joyintech.service.trade.transfer.assetstransfer.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.service.trade.transfer.assetstransfer.dto.AssetsTransferDto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 资产转仓一览Mapper
 * @author liuwei
 * @version 1.0 2019年12月11日
 * @see AssetsTransferMapper
 * @since 1.0
 */
@Repository
public interface AssetsTransferMapper {

    /**
     * 获取组合代码联想文本框
     * @return 联想文本框实体类集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPortfolioId(Map<String,Object> map);

    /**
     * 获取组合代码联想文本框
     * @return 联想文本框实体类集合
     * @see
     * @since 1.0
     */
    List<TreeNode> getPortfolioIdTree(Map<String,Object> map);

    /**
     * 查询待转仓交易
     *
     * @param pageParam
     * @return
     * @throws Exception
     */
    List<AssetsTransferDto> findUnTransPage(Map<String, Object> pageParam);

    /**
     * 查询转仓明细交易
     *
     * @param pageParam
     * @return
     * @throws Exception
     */
    List<AssetsTransferDto> findTransPage(Map<String, Object> pageParam);

    /**
     * 获取资产代码联想下拉框
     * @return 联想下拉框实体类集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodId(Map<String,Object> map);

    /**
     * 获取在途的持仓
     * @return 在途的持仓
     * @see
     * @since 1.0
     */
    BigDecimal selectFlowAmt(Map<String,Object> map);

    /**
     * 获取处于流程中的持仓（但是未记录在在持仓流水中）
     * @return 处于流程中的持仓（但是未记录在在持仓流水中）
     * @see
     * @since 1.0
     */
    BigDecimal selectWflAmt(Map<String,Object> map);
}
