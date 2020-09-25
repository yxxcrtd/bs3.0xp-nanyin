package com.joyintech.tams.component.business.trade;

import com.joyintech.tams.invest.common.CommonResult;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;

/**
 * 提供金融产品交易共通业务逻辑处理
 *
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.component.business.period.service
 * @Description: 金融产品交易共通
 * @date Date : 2018年09月27日 15:31
 */
public interface BaseTradeProductDealCommService {
    /**
     * @description:金融产品交易共通业务逻辑处理
     * @param: [finProductTradeDto]
     * @return: com.joyintech.tams.invest.common.CommonResult
     * @date: 2018/9/28
     */
    CommonResult execute(FinProductTradeDto finProductTradeDto);

    /**
     * “作废”详细逻辑
     *
     * @param workflowId 工作流代码
     * @return 处理结果
     * @see
     * @since 1.0
     */
    CommonResult delete(String workflowId);

    /**
     * 根据工作流代码，删除流程表数据，用于工作流完成时
     *
     * @param workflowId 工作流代码
     * @return 处理结果
     * @see
     * @since 1.0
     */
    CommonResult deleteWflData(String workflowId);

    /**
     * @description: 2.写入类型为[实际]的组合持仓流水与组合现金流水
     * @param: [BaseTradeProductDealDtoList, 持仓类型]
     * @return: void
     * @date: 2018/9/29
     */
    Boolean insertPosition(FinProductTradeDto finProductTradeDto);
}
