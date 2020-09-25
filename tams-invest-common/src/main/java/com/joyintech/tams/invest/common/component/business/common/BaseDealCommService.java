package com.joyintech.tams.invest.common.component.business.common;

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
public interface BaseDealCommService {

    /**
     * @description:金融产品交易共通业务逻辑处理
     * @param: [finProductTradeDto]
     * @return: com.joyintech.tams.invest.common.CommonResult
     * @date: 2018/9/28
     */
    CommonResult execute(FinProductTradeDto finProductTradeDto);

    /**
     * @description: 2.写入类型为[实际]的组合持仓流水与组合现金流水
     * @param: [BaseTradeProductDealDtoList, 持仓类型]
     * @return: void
     * @date: 2018/9/29
     */
    Boolean insertPosition(FinProductTradeDto finProductTradeDto);
}
