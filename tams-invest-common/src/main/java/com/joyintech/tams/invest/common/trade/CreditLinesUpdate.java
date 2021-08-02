package com.joyintech.tams.invest.common.trade;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesInputDto;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesOutputDto;

public interface CreditLinesUpdate {
    /**
     * 批量预占接口
     * @param inputDtoList
     */
    void occupyCreditLinesEtr(List<CreditLinesInputDto> inputDtoList, Date tradeDate);

    /**
     * 批量check接口
     * @param inputDtoList
     * @return
     */
    List<CreditLinesOutputDto> occupyCreditLinesCheck(List<CreditLinesInputDto> inputDtoList);

    /**
     * 批量实占接口
     * @param inputDtoList
     */
    void occupyCreditLinesTrd(List<CreditLinesInputDto> inputDtoList, Date tradeDate);

    /**
     * 计算关联方交易占用额度
     * finprodId  资产代码，counterId  交易对手代码， 交易金额  （名义本金：债券-券面总额）,交易日期 ： tradeDate。
     * @param mapList
     * @return
     */
    List<Map<String,Object>> calcRelatedOccupyAmt(List<Map<String,Object>> mapList);

    /**
     * 获取授信使用条件
     */
    List<CreditLines> findCreditLinesCondition(CreditLinesInputDto inputDto);
}
