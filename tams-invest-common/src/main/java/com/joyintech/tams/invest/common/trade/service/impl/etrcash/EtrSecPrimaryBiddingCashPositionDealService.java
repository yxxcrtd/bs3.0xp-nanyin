package com.joyintech.tams.invest.common.trade.service.impl.etrcash;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.PtlCashPositionFlowMapper;
import com.joyintech.tams.common.base.entity.BidOrderBidPrice;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrPositionHoldDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @program: 2.1.0
 * @description:债券一级投标
 * @author: 
 * @create: 2019-10-14 
 **/
@Service
public class EtrSecPrimaryBiddingCashPositionDealService extends EtrCashPositionBaseService {

	private static PtlCashPositionFlowMapper ptlCashPositionFlowMapper;

	public static void deal债券一级投标EntrustStart(EntrustCommonDto entrustCommonDto, EtrPositionHoldDto etrPositionHoldDto) {
		EtrEntrust etrEntrust = entrustCommonDto.getEtrEntrust();
		// 委托生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDtoFromEtr(
            entrustCommonDto);
        String acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(),
                etrEntrust.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue());
        // 减少投标券面总额对应的可用现金 add by xxg entrustNum 非空判断  20210802
        etrCashPositionFlowDto.setAmount(ArithmeticUtil.multiply(etrEntrust.getEntrustNum() ==null?new BigDecimal(0):etrEntrust.getEntrustNum(), new BigDecimal(100)).negate());
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
		/**add by sunhui 20210719 重庆银行：中标结果维护，【是否头寸冻结】为是时，冻结组合可用现金   start**/
		if(StringUtil.equals(bidPriceDto.getIsFrozenPosition(), BussinessDictCodeList.是否_是.getValue())){
			//1.指令可用冻结
			EtrCashPositionFlowDto etrCashPositionFlowDtoFrozen = new EtrCashPositionFlowDto();
			BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDtoFrozen, etrCashPositionFlowDto);
			etrCashPositionFlowDtoFrozen.setSeqno(IDGenerator.generate());
			etrCashPositionFlowDtoFrozen.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());//冻结的现金
			//冻结的头寸 = 中标券面总额*100*净价
			etrCashPositionFlowDtoFrozen.setAmount(ArithmeticUtil.multiply(ArithmeticUtil.divide(实际中标券面总额,new BigDecimal("100"),2, RoundingMode.HALF_UP), new BigDecimal("-1"),bidPriceDto.getWinCprice()));
			etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDtoFrozen);
			//2.组合可用冻结
			PtlCashPositionFlowDto PtlCashPositionFlowDto = new PtlCashPositionFlowDto();
			BeanUtil.copyPropertiesIgnoreNull(PtlCashPositionFlowDto,etrCashPositionFlowDtoFrozen);
			PtlCashPositionFlowDto.setSeqno(IDGenerator.generate());
			PtlCashPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
			ptlCashPositionFlowMapper.insertSelective(PtlCashPositionFlowDto);
		}
		/**add by sunhui 20210719 重庆银行：中标结果维护，【是否头寸冻结】为是时，冻结组合可用现金   end**/
	}

	@Autowired(required = false)
	public void setPtlCashPositionFlowMapper(PtlCashPositionFlowMapper ptlCashPositionFlowMapper) {
		EtrSecPrimaryBiddingCashPositionDealService.ptlCashPositionFlowMapper = ptlCashPositionFlowMapper;
	}
}
