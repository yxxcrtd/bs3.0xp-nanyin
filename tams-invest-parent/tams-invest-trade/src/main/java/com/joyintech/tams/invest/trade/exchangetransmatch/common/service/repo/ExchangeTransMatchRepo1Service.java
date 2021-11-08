package com.joyintech.tams.invest.trade.exchangetransmatch.common.service.repo;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.TrdProductDealDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonEntrustDto;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 债券质押式回购（首期）
 * @author meifaxin
 * @date 2021/11/1
 * @see ExchangeTransMatchRepo1Service
 * @since 1.0
 */
@Service
public class ExchangeTransMatchRepo1Service extends ExchangeTransMatchRepoCommonService {


	@Override
	protected TrdProductDeal createDeal(ExchangeTransMatchCommonDto settleTransData, ExchangeTransMatchCommonEntrustDto entrustDto, TrdFinProductDealTotal trdFinProductDealTotal) {
			// 创建交易信息
            TrdProductDealDto trdProductDeal = createProductDeal(settleTransData);
            // 金融产品代码
	        trdProductDeal.setFinprodId(trdProductDeal.getTradeId());
	        // 起息日
	        trdProductDeal.setVdate(settleTransData.getVdate());
            // 到期日
	        trdProductDeal.setMdate(settleTransData.getMdate());
            // 交付日
	        trdProductDeal.setPayDate(settleTransData.getVpayDate());
            // 交易份额，交易本金，随交易支付费用，交易金额，未随交易支付费用，总交易费用，成交编号
            //calcFeeAmtPay(trdProductDeal, settleTransDataDtos);
            // 清算速度
	        trdProductDeal.setSettleSpeed(0L);
            // 交易份额
	        trdProductDeal.setShareAmt(entrustDto.getMarchQty());
            // 交易本金
	        trdProductDeal.setPrinAmt(entrustDto.getMarchQty());
            // 利息总额
	        trdProductDeal.setIntAmt(settleTransData.getIntAmt());
            // 全价总额
	        trdProductDeal.setFpriceAmt(entrustDto.getMarchAmt());
            // 随交易支付费用
	        if(StringUtil.equals(DictCodeList.是否_是.getValue(), settleTransData.getIsPayWithTrade())) {
               trdProductDeal.setFeeAmtPay(ArithmeticUtil.doAdd(settleTransData.getJsfAmt(), settleTransData.getGhfAmt(), settleTransData.getZgfAmt(), settleTransData.getSxfAmt(), settleTransData.getSettleFee(), settleTransData.getStampFee(), settleTransData.getCommissionAmt()));
            }else {
               trdProductDeal.setFeeAmtPay(ArithmeticUtil.doAdd(settleTransData.getJsfAmt(), settleTransData.getGhfAmt(), settleTransData.getZgfAmt(), settleTransData.getSxfAmt(), settleTransData.getSettleFee(), settleTransData.getStampFee()));
            }
            // 交易金额
	        trdProductDeal.setTradeAmt(entrustDto.getMarchAmt());
            // 未随交易支付费用
	        if(!StringUtil.equals(DictCodeList.是否_是.getValue(), settleTransData.getIsPayWithTrade())) {
                trdProductDeal.setFeeAmtUnpay(settleTransData.getCommissionAmt());
            }
            // 总交易费用
	        trdProductDeal.setFeeAmt(ArithmeticUtil.doAdd(trdProductDeal.getFeeAmtPay(), trdProductDeal.getFeeAmtUnpay()));
            // 利息总额
	        trdProductDeal.setIntAmt(null);
            // 全价总额
	        trdProductDeal.setFpriceAmt(null);
            // 交易方向
	        trdProductDeal.setPs(DictCodeList.交易方向_买入.getValue());
            // 汇总交易编号
	        trdProductDeal.setTotleTradeId(null);
            // 投管业务类型
	        if(StringUtil.equals(settleTransData.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())) {
              trdProductDeal.setTgBusiType(DictCodeListExt1.平安业务类型_正回购.getValue());
            }
	        else {
				trdProductDeal.setTgBusiType(DictCodeListExt1.平安业务类型_逆回购.getValue());
			}
	        return trdProductDeal;
	}


	public List<StlPayOrdersDto> generateStlPayOrdersList(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto, TrdProductDeal trdProductDeal) {
		List<StlPayOrdersDto> stlPayOrdersList = new ArrayList<>();
		StlPayOrdersDto stlPayOsrdersDto = createStlPayOrders(dealDto, entrustDto, trdProductDeal);
		// 收付方向
		if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue(), dealDto.getFinprodType2())) {
			stlPayOsrdersDto.setPs(DictCodeListExt1.收付方向_支出.getValue());
		} else {
			stlPayOsrdersDto.setPs(DictCodeList.收付方向_收入.getValue());
		}
		stlPayOrdersList.add(stlPayOsrdersDto);
		return stlPayOrdersList;
	}
	}
