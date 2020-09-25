package com.joyintech.tams.invest.common.component.base.group.impl.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;

/**
 * 组合可用持仓和实际持仓查询更新组件Mapper
 */
@Repository
public interface BasePtlSecPositionInfoMapper {

    /**
     * 查询可用持仓
     *
     * @param positionQueryDto PtlSecPositionFlowDto
     * @return: java.math.BigDecimal
     * @date: 2018/9/29
     */
    BigDecimal selectSecPositionFlow(PtlSecPositionFlowDto positionQueryDto);

    /**
     * 查询在途、实际、质押持仓
     *
     * @param positionQueryDto
     * @return java.math.BigDecimal
     * @date 2018/9/30
     */
    BigDecimal selectSecPosition(PtlSecPositionFlowDto positionQueryDto);

    /**
     * 查询最大日期
     *
     * @param positionQueryDto
     * @return 最大日期
     * @date 2018/9/30
     */
    Date selectSecPositionMaxCdate(PtlSecPositionFlowDto positionQueryDto);

    /**
     * 检索组合持仓的最大日期
     *
     * @param positionFlowDto PtlSecPositionFlowDto
     * @return: java.util.Date
     * @date: 2018/9/30
     */
    String selectMaxCdate(PtlSecPositionFlowDto positionFlowDto);

    /**
     * 更新项目 <br/>
     * 数量 = 数量 + 输入参数.数量<br/>
     * 更新表 组合持仓 更新条件 <br/>
     * 组合代码 = 输入参数.组合代码<br/>
     * 证券管理账户/通道代码 = 输入参数.证券管理账户/通道代码<br/>
     * 金融产品代码 = 输入参数.金融产品代码<br/>
     * 分支序号 = 输入参数.分支序号<br/>
     * 持仓类型 = 输入参数.持仓类型 <br/>
     * 日期 = 循环.日期
     *
     * @param params
     * @return 更新的数量
     */
    int updatePosition(Map<String, Object> params);

    int deletePtlSecPositionFlow(PtlSecPositionFlowDto positionQueryDto);
}