package com.joyintech.service.trade.transfer.netplantransfer.dao;

import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 净值型计划转仓Mapper
 * @author liuwei
 * @version 1.0 2019年12月23日
 * @see NetPlanTransferMapper
 * @since 1.0
 */
@Repository
public interface NetPlanTransferMapper {

    /**
     * 获取单位净值
     * @param map 请求参数
     * @return 单位净值
     * @see
     * @since 1.0
     */
    BigDecimal getUnitValue(Map<String,Object> map);

    /**
     * 获取转仓信息—组合代码
     * @return 联想下拉框实体类集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPortfolioId(Map<String,Object> map);

    /**
     * 查询产品登记编码
     * @return
     * @see
     * @since 1.0
     */
    String selProdCode(Map<String,Object> map);

    /**
     * 查询收盘价
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal selCloPrice(Map<String,Object> map);

    /**
     * 更新交易报送表
     * @return
     * @see
     * @since 1.0
     */
    Integer updateRep(Map<String,Object> map);

    /**
     * 获取资产类型7
     * @return
     * @see
     * @since 1.0
     */
    String selectType7(String finprodId);

    /**
     * 获取单位净值列表
     * @return
     * @see
     * @since 1.0
     */
    List<BigDecimal> getUnitValueList(Map<String,Object> map);
}
