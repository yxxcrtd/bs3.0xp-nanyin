package com.joyintech.tams.invest.common.trade.service.impl.etrcash;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.tams.common.base.entity.BidOrderBidPrice;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.etr.EtrCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrPositionHoldDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @program: 2.1.0
 * @description:债券一级投标
 * @author: 
 * @create: 2019-10-14 
 **/
@Service
public class EtrSecPrimaryBiddingCashPositionDealService extends EtrCashPositionBaseService {

	public static void deal债券一级投标EntrustStart(EntrustCommonDto entrustCommonDto, EtrPositionHoldDto etrPositionHoldDto) {
		EtrEntrust etrEntrust = entrustCommonDto.getEtrEntrust();   
		// 委托生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDtoFromEtr(
            entrustCommonDto);
        String acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(),
                etrEntrust.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue());
        // 减少投标券面总额对应的可用现金
        etrCashPositionFlowDto.setAmount(ArithmeticUtil.multiply(etrEntrust.getEntrustNum(), new BigDecimal(100)).negate());
        etrCashPositionFlowDto.setAcctId(acctId);
        etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
	}
	
	public static void deal债券一级投标EntrustEnd(EntrustCommonDto entrustCommonDto, EtrPositionHoldDto etrPositionHoldDto) {
		EtrEntrust etrEntrust = entrustCommonDto.getEtrEntrust();   
		// 委托生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDtoFromEtr(
            entrustCommonDto);
        String acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(),
                etrEntrust.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue());
        //释放投标券面总额对应的可用现金
        etrCashPositionFlowDto.setAmount(ArithmeticUtil.multiply(etrEntrust.getEntrustNum(), new BigDecimal(100)));
        etrCashPositionFlowDto.setAcctId(acctId);
        etrCashPositionFlowDto.setOffsetId(etrEntrust.getEntrustId());
		etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
	}

	public static void 中标结果维护(BidOrderBidPrice bidPriceDto, EtrPositionHoldDto etrPositionHoldDto) {
		// 委托代码
		String entrustId = bidPriceDto.getEntrustId();
		// 委托dto
		EntrustCommonDto entrustCommonDto = EtrCommonCashPositionDealService.findEntrust(entrustId);
		EtrEntrust etrEntrust = entrustCommonDto.getEtrEntrust();
		// 交易生成的现金流水
		EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService
				.getEtrCashPositionFlowDtoFromEtr(entrustCommonDto);
		String acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
				DictCodeListExt2.资金账户类型_托管户.getValue());
		// 释放未中标券面总额对应的可用现金
		BigDecimal 投标券面总额 = bidPriceDto.getTheWinPrinAmt();
		BigDecimal 实际中标券面总额 = bidPriceDto.getWinPrinAmt();
        etrCashPositionFlowDto.setAmount(ArithmeticUtil.subtract(实际中标券面总额, 投标券面总额));
		etrCashPositionFlowDto.setAcctId(acctId);
		etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
	}
}
