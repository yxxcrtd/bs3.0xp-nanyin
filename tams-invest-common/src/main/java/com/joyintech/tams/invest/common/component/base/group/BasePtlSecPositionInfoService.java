/*
 * 文件名：BasePtlSecPositionInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.component.base.group;

import java.math.BigDecimal;

import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;

/**
 * 组合可用持仓和实际持仓查询更新组件接口
 * @author huwei
 * @version 1.0 2018年9月3日
 * @see BasePtlSecPositionInfoService
 * @since 1.0
 */
public interface BasePtlSecPositionInfoService {

    /**
     * 查询可用持仓
     * 
     * @param positionQueryDto PtlPositionFlowQueryDto
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    BigDecimal queryUsableSecPosition(PtlSecPositionFlowDto positionQueryDto);

    /**
     * 查询在途、实际、质押持仓
     *
     * @param positionQueryDto PtlPositionFlowQueryDto
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    BigDecimal queryOtherSecPosition(PtlSecPositionFlowDto positionQueryDto);

    /**
     * 插入持仓流水
     * 
     * @param positionFlowDto PtlSecPositionFlowDto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean inertSecPositionFlow(PtlSecPositionFlowDto positionFlowDto);

    /**
     * 删除持仓流水
     *
     * @param positionQueryDto PtlPositionFlowQueryDto
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    int deleteSecPosition(PtlSecPositionFlowDto positionQueryDto);
}
