package com.joyintech.tams.invest.trade.bondmarkettrade.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.trade.bondmarkettrade.dto.TrdBondMarketTradeDto;
import com.joyintech.tams.invest.trade.common.validator.TradeCommonValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 债券上市流通交易保存提交check
 *
 * @author 马数凯
 * @version 1.0 2021年3月29日
 * @see com.joyintech.tams.invest.trade.bondmarkettrade.validator.TrdBondMarketTradeDtoValidator
 * @since 1.0
 */
@Component
public class TrdBondMarketTradeDtoValidator implements TradeCommonValidator {
    @Override
    public List<ValidMessage> bussValid(Object value) {
        TrdBondMarketTradeDto inputDto = (TrdBondMarketTradeDto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        if (StringUtil.isEmpty(inputDto.getFinprodId())) {
            errorList.add(new ValidMessage("finprodId",
                    getMessage("FW_E_0006", getMessage("TrdBondMarketTrade001.finprodAbbr"))));
        }

        if (StringUtil.isEmpty(inputDto.getAppearMarketId())) {
            errorList.add(new ValidMessage("appearMarketId",
                    getMessage("FW_E_0006", getMessage("TrdBondMarketTrade001.appearMarketAbbr"))));
        }
        
        return errorList;
    }
}
