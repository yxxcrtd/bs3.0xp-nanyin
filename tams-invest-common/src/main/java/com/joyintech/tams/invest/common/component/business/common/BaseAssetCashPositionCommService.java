package com.joyintech.tams.invest.common.component.business.common;

import java.util.List;

import com.joyintech.tams.invest.common.component.business.common.dto.PortfolioCashPositionsFlowInputDto;
import com.joyintech.tams.invest.common.component.business.common.dto.PortfolioPositionsFlowInputDto;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;

/**
 * 业务共通组件.资产头寸现金持仓共通组件
 * 
 * @author 强成西
 */
public interface BaseAssetCashPositionCommService {

    /**
     * 持仓处理详细
     * 
     * @param inputDto PortfolioPositionsFlowInputDto
     * @return com.joyintech.component.business.common.dto.PtlSecPositionDto
     * @date 2018/9/28
     */
    List<PtlSecPositionFlowDto> positionDeal(PortfolioPositionsFlowInputDto inputDto);

    /**
     * 现金处理详细
     * 
     * @param inputDto PortfolioPositionsFlowInputDto
     * @return com.joyintech.component.business.common.dto.PtlSecPositionDto
     * @date 2018/9/28
     */
    List<PtlCashPositionFlowDto> cashDeal(PortfolioCashPositionsFlowInputDto inputDto);
}
