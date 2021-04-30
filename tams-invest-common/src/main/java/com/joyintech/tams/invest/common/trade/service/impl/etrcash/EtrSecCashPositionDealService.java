package com.joyintech.tams.invest.common.trade.service.impl.etrcash;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.etr.EtrCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrPositionHoldDto;
import com.joyintech.tams.invest.common.dto.etr.EtrSecPositionFlowDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService.getSeqNo;

/**
 * @program: 2.1.0
 * @description:
 * @author: 强成西
 * @create: 2019-10-12 15:05
 **/
@Service
public class EtrSecCashPositionDealService extends EtrCashPositionBaseService {

    static BigDecimal 债券面值 = new BigDecimal(100);

    private static boolean is卖出(String 交易方向) {
        if (StringUtil.equals(DictCodeList.交易方向_行权_回售.getValue(), 交易方向)
            || StringUtil.equals(DictCodeList.交易方向_行权_赎回.getValue(), 交易方向)
            || StringUtil.equals(DictCodeList.交易方向_卖出.getValue(), 交易方向)) {
            return true;
        }
        return false;
    }

    /**
     * 债券以持仓数量（张数）统计 处理债券的
     */
    public static void deal债券CashPosition(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                          EtrPositionHoldDto etrPositionHoldDto) {
        // 交易场所
        String tradePlace = finProductTradeDto.getTrdProductDeal().getTradeMarket();
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();
        TrdProductDealAdd dealAdd = finProductTradeDto.getTrdProductDealAdd();
        List<StlPayOrdersDto> stlPayOrdersDtos = finProductTradeDto.getStlPayOrdersList();
        // 通道代码
        String chlId = finProductTradeDto.getTrdProductDeal().getChlId();
        // 通道性质
        String chlType = StringUtil.isEmpty(chlId) ? null : EtrCommonCashPositionDealService.queryChlType(chlId);
        // 交易方向
        String ps = finProductTradeDto.getTrdProductDeal().getPs();
        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();
        // 证券账户
        String secManageAcctId = finProductTradeDto.getTrdProductDeal().getSecManageAcctId();
        // 交易日期
        Date tradeDate = finProductTradeDto.getTrdProductDeal().getTradeDate();
        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);
        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);
        etrCashPositionFlowDto.setOrderId(finProductTradeDto.getTrdProductDeal().getTradeId());
        // 交易场所为银行间
        if (StringUtil.equals(tradePlace, DictCodeListExt1.交易场所_银行间.getValue())) {
            // TODO
            String 账号 = gen账号(finProductTradeDto.getTrdProductDeal().getTradeMarket(),
                finProductTradeDto.getTrdProductDeal().getChlId(), finProductTradeDto.getTrdProductDealAdd().getPortfolioId(),
                finProductTradeDto.getTrdProductDeal().getCcy(), finProductTradeDto.getTrdProductDeal().getSecManageAcctId(),
                    finProductTradeDto.getTrdProductDeal().getCounterId(),etrCashPositionFlowDto);
            // 是否包含通道
            if (StringUtil.isEmpty(chlId)) {
                // 委托直投买入（包括外部买入、内转买入）
                if (StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())) {
                    债券_银行间_委托直投买入(finProductTradeDto, 持仓类型, etrPositionHoldDto);
                }
                // 委托直投卖出/行权（包括外部卖出、内转卖出）
                //
                else if (is卖出(ps)) {
                    债券_银行间_委托直投卖出(finProductTradeDto, 持仓类型, etrPositionHoldDto);
                }
                else if (DictCodeList.交易方向_收息.getValue().equals(ps) || DictCodeList.交易方向_还本.getValue().equals(ps)) {
                    if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                        if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                            // 可用现金 支付日增加到账金额
                            // 预增现金 支付日减少到账金额
                            现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto);
                        }
                        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                            if (StringUtil.isEmpty(entrustId)) { // 无委托
                                // 预增现金 支付日增加到账金额
                                EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                                BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                                etrCashPositionFlowDto1.setSeqno(getSeqNo());
                                预增现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto1);
                            }
                        }
                    }
                }
                else if (DictCodeList.交易方向_到期.getValue().equals(ps)) {
                    if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                        if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                            现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto);
                        }
                    }
                    if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                        if (StringUtil.isEmpty(entrustId)) { // 无委托
                            // 可用持仓 提交日削减至0
                            etrSecPositionFlowDto.setAmount(deal.getShareAmt().negate());
                            etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                            // 预增现金 支付日增加到账金额
                            if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                                EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                                BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                                etrCashPositionFlowDto1.setSeqno(getSeqNo());
                                预增现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto1);
                            }
                        }
                    }
                }
            }
            else {
                if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue())) {
                    // 逐笔划款通道
                    etrCashPositionFlowDto.setChlFinprodId(null);
                    // 委托借逐笔划款通道买入（包括外部买入、内转买入）
                    if (StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())) {
                        债券_银行间_委托借逐笔划款通道买入(finProductTradeDto, 持仓类型, etrPositionHoldDto);
                    }
                    // 委托借逐笔划款通道卖出（包括外部卖出、内转卖出）
                    else if (is卖出(ps)) {
                        债券_银行间_委托借逐笔划款通道卖出(finProductTradeDto, 持仓类型, etrPositionHoldDto);
                    }
                    // 中间付息和还本
                    else if (DictCodeList.交易方向_收息.getValue().equals(ps) || DictCodeList.交易方向_还本.getValue().equals(ps)) {
                        if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                                // 可用现金 支付日增加到账金额
                                // 预增现金 支付日减少到账金额
                                现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto);
                            }
                            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                                if (StringUtil.isEmpty(entrustId)) { // 无委托
                                    // 预增现金 支付日增加到账金额
                                    EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                                    BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                                    etrCashPositionFlowDto1.setSeqno(getSeqNo());
                                    预增现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto1);
                                }
                            }
                        }
                    }
                    else if (DictCodeList.交易方向_到期.getValue().equals(ps)) {
                        if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                            if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                                现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto);
                            }
                        }
                        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                            if (StringUtil.isEmpty(entrustId)) { // 无委托
                                // 可用持仓 提交日削减至0
                                etrSecPositionFlowDto.setAmount(deal.getShareAmt().negate());
                                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                                    // 预增现金 支付日增加到账金额
                                    EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                                    BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                                    etrCashPositionFlowDto1.setSeqno(getSeqNo());
                                    预增现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto1);
                                }
                            }
                        }
                    }
                }
                // 一次性划款通道
                // TODO
                else {
                    // 委托借一次性划款通道买入（包括外部买入、内转买入）
                    if (StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())) {
                        债券_银行间_委托借一次性划款通道买入(finProductTradeDto, 持仓类型, etrPositionHoldDto);
                    }
                    // 委托借一次性通道卖出/行权（包括外部卖出、内转卖出）
                    else if (is卖出(ps)) {
                        债券_银行间_委托借一次性通道卖出(finProductTradeDto, 持仓类型, etrPositionHoldDto);
                    }
                    else if (DictCodeList.交易方向_收息.getValue().equals(ps) || DictCodeList.交易方向_还本.getValue().equals(ps)) {
                        if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                                // 可用现金 支付日增加到账金额
                                // 预增现金 支付日减少到账金额
                                现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto);
                            }
                            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                                if (StringUtil.isEmpty(entrustId)) { // 无委托
                                    // 预增现金 支付日增加到账金额
                                    EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                                    BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                                    etrCashPositionFlowDto1.setSeqno(getSeqNo());
                                    预增现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto1);
                                }
                            }
                        }
                    }
                    else if (DictCodeList.交易方向_到期.getValue().equals(ps)) {
                        if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                            if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                                现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto);
                            }
                        }
                        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                            if (StringUtil.isEmpty(entrustId)) { // 无委托
                                // 可用持仓 提交日削减至0
                                etrSecPositionFlowDto.setAmount(deal.getShareAmt().negate());
                                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                                    // 预增现金 支付日增加到账金额
                                    EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                                    BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                                    etrCashPositionFlowDto1.setSeqno(getSeqNo());
                                    预增现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto1);
                                }
                            }
                        }
                    }
                }
            }
        }
        // 非银行间
        else {
            // 是否包含通道
            if (StringUtil.isEmpty(chlId)) {
                // 委托直投买入（包括外部买入、内转买入）
                if (StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())) {
                    债券_银行间_委托直投买入(finProductTradeDto, 持仓类型, etrPositionHoldDto);
                }
                // 委托直投卖出/行权（包括外部卖出、内转卖出）
                else if (is卖出(ps)) {
                    债券_银行间_委托直投卖出(finProductTradeDto, 持仓类型, etrPositionHoldDto);
                }
                else if (DictCodeList.交易方向_收息.getValue().equals(ps) || DictCodeList.交易方向_还本.getValue().equals(ps)) {
                    if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                        if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                            // 可用现金 支付日增加到账金额
                            // 预增现金 支付日减少到账金额
                            现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto);
                        }
                        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                            if (StringUtil.isEmpty(entrustId)) { // 无委托
                                // 预增现金 支付日增加到账金额
                                EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                                BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                                etrCashPositionFlowDto1.setSeqno(getSeqNo());
                                预增现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto1);
                            }
                        }
                    }
                }
                else if (DictCodeList.交易方向_到期.getValue().equals(ps)) {
                    if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                        if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                            现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto);
                        }
                    }
                    if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                        if (StringUtil.isEmpty(entrustId)) { // 无委托
                            // 可用持仓 提交日削减至0
                            etrSecPositionFlowDto.setAmount(deal.getShareAmt().negate());
                            etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                            if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                                // 预增现金 支付日增加到账金额
                                EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                                BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                                etrCashPositionFlowDto1.setSeqno(getSeqNo());
                                预增现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto1);
                            }
                        }
                    }
                }
            }
            else {
                // 逐笔划款通道
                if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue())) {
                    etrCashPositionFlowDto.setChlFinprodId(null);
                    // 委托借逐笔划款通道买入（包括外部买入、内转买入）
                    if (StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())) {
                        债券_银行间_委托借逐笔划款通道买入(finProductTradeDto, 持仓类型, etrPositionHoldDto);
                    }
                    // 委托借逐笔划款通道卖出（包括外部卖出、内转卖出）
                    else if (is卖出(ps)) {
                        债券_银行间_委托借逐笔划款通道卖出(finProductTradeDto, 持仓类型, etrPositionHoldDto);
                    }
                    // 中间付息和还本
                    else if (DictCodeList.交易方向_收息.getValue().equals(ps) || DictCodeList.交易方向_还本.getValue().equals(ps)) {
                        if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                                // 可用现金 支付日增加到账金额
                                // 预增现金 支付日减少到账金额
                                现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto);
                            }
                            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                                if (StringUtil.isEmpty(entrustId)) { // 无委托
                                    // 预增现金 支付日增加到账金额
                                    EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                                    BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                                    etrCashPositionFlowDto1.setSeqno(getSeqNo());
                                    预增现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto1);
                                }
                            }
                        }
                    }
                    else if (DictCodeList.交易方向_到期.getValue().equals(ps)) {
                        if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                            if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                                现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto);
                            }
                        }
                        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                            if (StringUtil.isEmpty(entrustId)) { // 无委托
                                // 可用持仓 提交日削减至0
                                etrSecPositionFlowDto.setAmount(deal.getShareAmt().negate());
                                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                                    // 预增现金 支付日增加到账金额
                                    EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                                    BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                                    etrCashPositionFlowDto1.setSeqno(getSeqNo());
                                    预增现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto1);
                                }
                            }
                        }
                    }
                }
                // 一次性划款通道
                else {
                    // 委托借一次性划款通道买入（包括外部买入、内转买入）
                    if (StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())) {
                        债券_银行间_委托借一次性划款通道买入(finProductTradeDto, 持仓类型, etrPositionHoldDto);
                    }
                    // 委托借一次性通道卖出/行权（包括外部卖出、内转卖出）
                    else if (is卖出(ps)) {
                        债券_银行间_委托借一次性通道卖出(finProductTradeDto, 持仓类型, etrPositionHoldDto);
                    }
                    else if (DictCodeList.交易方向_收息.getValue().equals(ps) || DictCodeList.交易方向_还本.getValue().equals(ps)) {
                        if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                                // 可用现金 支付日增加到账金额
                                // 预增现金 支付日减少到账金额
                                现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto);
                            }
                            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                                if (StringUtil.isEmpty(entrustId)) { // 无委托
                                    // 预增现金 支付日增加到账金额
                                    EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                                    BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                                    etrCashPositionFlowDto1.setSeqno(getSeqNo());
                                    预增现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto1);
                                }
                            }
                        }
                    }
                    else if (DictCodeList.交易方向_到期.getValue().equals(ps)) {
                        if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                            if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                                现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto);
                            }
                        }
                        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                            if (StringUtil.isEmpty(entrustId)) { // 无委托
                                // 可用持仓 提交日削减至0
                                etrSecPositionFlowDto.setAmount(deal.getShareAmt().negate());
                                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                                // 预增现金 支付日增加到账金额
                                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                                    EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                                    BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                                    etrCashPositionFlowDto1.setSeqno(getSeqNo());
                                    预增现金处理(etrPositionHoldDto, deal, dealAdd, stlPayOrdersDtos, etrCashPositionFlowDto1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 债券_银行间_委托借一次性通道卖出
     * 
     * @param finProductTradeDto
     * @param 持仓类型 持仓类型
     * @param etrPositionHoldDto 流水容器
     */
    private static void 债券_银行间_委托借一次性通道卖出(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                          EtrPositionHoldDto etrPositionHoldDto) {
        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();
        // 证券账户
        String secManageAcctId = finProductTradeDto.getTrdProductDeal().getSecManageAcctId();
        // 资产代码
        String assetCode = finProductTradeDto.getTrdProductDeal().getFinprodId();
        // 交易日期
        Date tradeDate = finProductTradeDto.getTrdProductDeal().getTradeDate();
        // TODO
        BigDecimal 交易日减少份额 = finProductTradeDto.getTrdProductDeal().getShareAmt().negate();
        BigDecimal 交割日增加交易金额 = finProductTradeDto.getTrdProductDeal().getTradeAmt();
        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);
        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);
        etrCashPositionFlowDto.setOrderId(finProductTradeDto.getTrdProductDeal().getTradeId());
        String 账号 = gen账号(finProductTradeDto.getTrdProductDeal().getTradeMarket(),
            finProductTradeDto.getTrdProductDeal().getChlId(), finProductTradeDto.getTrdProductDealAdd().getPortfolioId(),
            finProductTradeDto.getTrdProductDeal().getCcy(), finProductTradeDto.getTrdProductDeal().getSecManageAcctId(),
                finProductTradeDto.getTrdProductDeal().getCounterId(),etrCashPositionFlowDto);
        // 设置账号
        etrCashPositionFlowDto.setAcctId(账号);
        // 交易生效
        if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
            etrCashPositionFlowDto.setCdate(finProductTradeDto.getTrdProductDeal().getSettleDate());
            // 有委托
            if (StringUtil.isNotEmpty(entrustId)) {
                // 现金流水dto处理
                // 交割日增加交易金额
                etrCashPositionFlowDto.setAmount(交割日增加交易金额);
                // 添加现金流水到目标dto
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                // TODO 委托冲销流水生成
//                etrPositionHoldDto.getPtlCashPositionFlows().add(gen冲销现金流水生成(etrCashPositionFlowDto, entrustId, 交易日减少份额));
            }
            // 无委托
            else {
                // 现金流水dto处理
                // 交割日增加交易金额
                etrCashPositionFlowDto.setAmount(交割日增加交易金额);
                // 添加现金流水到目标dto
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
            }
        }
        // 交易提交
        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
            if (StringUtil.isEmpty(entrustId)) {
                // 持仓流水dto处理
                // 交割日增加交易券面总额/债券面值
                etrSecPositionFlowDto.setAmount(交易日减少份额);
                // 添加持仓流水到目标dto
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            }
        }
    }

    /**
     * 债券_银行间_委托借一次性划款通道买入
     * 
     * @param finProductTradeDto
     * @param 持仓类型 持仓类型
     * @param etrPositionHoldDto 流水容器
     */
    private static void 债券_银行间_委托借一次性划款通道买入(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                            EtrPositionHoldDto etrPositionHoldDto) {
        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();
        // 证券账户
        String secManageAcctId = finProductTradeDto.getTrdProductDeal().getSecManageAcctId();
        // 资产代码
        String assetCode = finProductTradeDto.getTrdProductDeal().getFinprodId();
        // 交易日期
        Date tradeDate = finProductTradeDto.getTrdProductDeal().getTradeDate();
        // TODO
        BigDecimal 交易券份额 = finProductTradeDto.getTrdProductDeal().getShareAmt();
        BigDecimal 交易日减少金额 = finProductTradeDto.getTrdProductDeal().getTradeAmt().negate();
        // TODO 委托全价获取方式
        BigDecimal 委托全价 = finProductTradeDto.getTrdProductDeal().getFpriceAmt();
        // 交割日减少(交易金额-交易券面总额*委托全价/债券面值）
        // BigDecimal amount = ArithmeticUtil.subtract(交易日减少金额,
        // ArithmeticUtil.divide(ArithmeticUtil.multiply(交易券面总额, 委托全价), 债券面值, 2));
        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);
        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);
        etrCashPositionFlowDto.setOrderId(finProductTradeDto.getTrdProductDeal().getTradeId());
        String 账号 = gen账号(finProductTradeDto.getTrdProductDeal().getTradeMarket(),
            finProductTradeDto.getTrdProductDeal().getChlId(), finProductTradeDto.getTrdProductDealAdd().getPortfolioId(),
            finProductTradeDto.getTrdProductDeal().getCcy(), finProductTradeDto.getTrdProductDeal().getSecManageAcctId(),
                finProductTradeDto.getTrdProductDeal().getCounterId(),etrCashPositionFlowDto);
        // 设置账号
        etrCashPositionFlowDto.setAcctId(账号);
        // 交易生效
        if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
            etrSecPositionFlowDto.setCdate(finProductTradeDto.getTrdProductDeal().getSettleDate());
            etrCashPositionFlowDto.setCdate(finProductTradeDto.getTrdProductDeal().getSettleDate());
            // 有委托
            if (StringUtil.isNotEmpty(entrustId)) {
                // 持仓流水dto处理
                // 交割日增加交易券面总额/债券面值
                etrSecPositionFlowDto.setAmount(交易券份额);
                // 添加持仓流水到目标dto
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                // 现金流水dto处理
                // 交割日减少(交易金额-交易券面总额*委托全价/债券面值）
                etrCashPositionFlowDto.setAmount(交易日减少金额);
                // 添加现金流水到目标dto
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                // TODO 委托冲销流水生成
                etrPositionHoldDto.getPtlCashPositionFlows().add(gen冲销现金流水生成(etrCashPositionFlowDto, entrustId, 交易券份额));
            }
            // 无委托
            else {
                // 持仓流水dto处理
                // 交割日增加交易券面总额/债券面值
                etrSecPositionFlowDto.setAmount(交易券份额);
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                // // 现金流水dto处理
                // // 交割日减少(交易金额-交易券面总额*委托全价/债券面值）
                // etrCashPositionFlowDto.setAmount(交易日减少金额);
                // // 添加现金流水到目标dto
                // etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
            }
        }
        // 交易提交
        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
            if (StringUtil.isEmpty(entrustId)) {
                EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                etrCashPositionFlowDto1.setCdate(finProductTradeDto.getTrdProductDeal().getTradeDate());
                etrCashPositionFlowDto1.setSeqno(getSeqNo());
                // 现金流水dto处理
                etrCashPositionFlowDto1.setAmount(交易日减少金额);
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto1);
            }
        }
    }

    /**
     * 债券_银行间_委托借逐笔划款通道卖出
     * 
     * @param finProductTradeDto
     * @param 持仓类型 持仓类型
     * @param etrPositionHoldDto 流水容器
     */
    private static void 债券_银行间_委托借逐笔划款通道卖出(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                           EtrPositionHoldDto etrPositionHoldDto) {
        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();
        // 证券账户
        String secManageAcctId = finProductTradeDto.getTrdProductDeal().getSecManageAcctId();
        // 资产代码
        String assetCode = finProductTradeDto.getTrdProductDeal().getFinprodId();
        // 交易日期
        Date tradeDate = finProductTradeDto.getTrdProductDeal().getTradeDate();
        // TODO
        BigDecimal 交易日减少份额 = finProductTradeDto.getTrdProductDeal().getShareAmt().negate();
        BigDecimal 交割日增加交易金额 = finProductTradeDto.getTrdProductDeal().getTradeAmt();
        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);
        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);
        etrCashPositionFlowDto.setChlFinprodId(null);
        etrCashPositionFlowDto.setOrderId(finProductTradeDto.getTrdProductDeal().getTradeId());
        String 账号 = gen账号(finProductTradeDto.getTrdProductDeal().getTradeMarket(),
            finProductTradeDto.getTrdProductDeal().getChlId(), finProductTradeDto.getTrdProductDealAdd().getPortfolioId(),
            finProductTradeDto.getTrdProductDeal().getCcy(), finProductTradeDto.getTrdProductDeal().getSecManageAcctId(),
                finProductTradeDto.getTrdProductDeal().getCounterId(),etrCashPositionFlowDto);
        // 设置账号
        etrCashPositionFlowDto.setAcctId(账号);
        // 交易生效
        if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
            etrCashPositionFlowDto.setCdate(finProductTradeDto.getTrdProductDeal().getSettleDate());
            // 有委托
            if (StringUtil.isNotEmpty(entrustId)) {
                // 现金流水dto处理
                // 交割日增加交易金额
                etrCashPositionFlowDto.setAmount(交割日增加交易金额);
                // 添加现金流水到目标dto
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                // TODO 委托冲销流水生成
//                etrPositionHoldDto.getPtlCashPositionFlows().add(gen冲销现金流水生成(etrCashPositionFlowDto, entrustId, 交易日减少份额));
            }
            // 无委托
            else {
                // 现金流水dto处理
                // 交割日增加交易金额
                etrCashPositionFlowDto.setAmount(交割日增加交易金额);
                // 添加现金流水到目标dto
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
            }
        }
        // 交易提交
        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
            if (StringUtil.isEmpty(entrustId)) {
                // 持仓流水dto处理
                // 交割日增加交易券面总额/债券面值
                etrSecPositionFlowDto.setAmount(交易日减少份额);
                // 添加持仓流水到目标dto
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            }
        }
    }

    /**
     * 债券_银行间_委托借逐笔划款通道买入
     * 
     * @param finProductTradeDto
     * @param 持仓类型 持仓类型
     * @param etrPositionHoldDto 流水容器
     */
    private static void 债券_银行间_委托借逐笔划款通道买入(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                           EtrPositionHoldDto etrPositionHoldDto) {
        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();
        // 证券账户
        String secManageAcctId = finProductTradeDto.getTrdProductDeal().getSecManageAcctId();
        // 资产代码
        String assetCode = finProductTradeDto.getTrdProductDeal().getFinprodId();
        // 交易日期
        Date tradeDate = finProductTradeDto.getTrdProductDeal().getTradeDate();
        // TODO
        BigDecimal 交易日数量 = finProductTradeDto.getTrdProductDeal().getShareAmt();
        BigDecimal 交易日减少金额 = finProductTradeDto.getTrdProductDeal().getTradeAmt().negate();
        // TODO 委托全价获取方式
        BigDecimal 委托全价 = finProductTradeDto.getTrdProductDeal().getFpriceAmt();
        // 交割日减少(交易金额-交易券面总额*委托全价/债券面值）
        // BigDecimal amount = ArithmeticUtil.subtract(交易日减少金额,
        // ArithmeticUtil.divide(ArithmeticUtil.multiply(交易券面总额, 委托全价), 债券面值, 2));
        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);
        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);
        etrCashPositionFlowDto.setChlFinprodId(null);
        etrCashPositionFlowDto.setOrderId(finProductTradeDto.getTrdProductDeal().getTradeId());
        String 账号 = gen账号(finProductTradeDto.getTrdProductDeal().getTradeMarket(),
            finProductTradeDto.getTrdProductDeal().getChlId(), finProductTradeDto.getTrdProductDealAdd().getPortfolioId(),
            finProductTradeDto.getTrdProductDeal().getCcy(), finProductTradeDto.getTrdProductDeal().getSecManageAcctId(),
                finProductTradeDto.getTrdProductDeal().getCounterId(),etrCashPositionFlowDto);
        // 设置账号
        etrCashPositionFlowDto.setAcctId(账号);
        // 交易生效
        if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
            etrSecPositionFlowDto.setCdate(finProductTradeDto.getTrdProductDeal().getSettleDate());
            etrCashPositionFlowDto.setCdate(finProductTradeDto.getTrdProductDeal().getSettleDate());
            // 有委托
            if (StringUtil.isNotEmpty(entrustId)) {
                // 持仓流水dto处理
                // 交割日增加交易券面总额/债券面值
                etrSecPositionFlowDto.setAmount(交易日数量);
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                // 现金流水dto处理
                // 交割日减少(交易金额-交易券面总额*委托全价/债券面值）
                etrCashPositionFlowDto.setAmount(交易日减少金额);
                // 添加现金流水到目标dto
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                // TODO 委托冲销流水生成
                etrPositionHoldDto.getPtlCashPositionFlows().add(gen冲销现金流水生成(etrCashPositionFlowDto, entrustId, 交易日数量));
            }
            // 无委托
            else {
                // 持仓流水dto处理
                // 交割日增加交易券面总额/债券面值
                etrSecPositionFlowDto.setAmount(交易日数量);
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            }
        }
        // 交易提交
        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
            if (StringUtil.isEmpty(entrustId)) {
                EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                etrCashPositionFlowDto1.setSeqno(getSeqNo());
                etrCashPositionFlowDto1.setCdate(finProductTradeDto.getTrdProductDeal().getTradeDate());
                // 现金流水dto处理
                etrCashPositionFlowDto1.setAmount(交易日减少金额);
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto1);
            }
        }
    }

    /**
     * 债券_银行间_委托直投卖出
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto 流水容器
     */
    private static void 债券_银行间_委托直投卖出(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                      EtrPositionHoldDto etrPositionHoldDto) {
        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();
        // 证券账户
        String secManageAcctId = finProductTradeDto.getTrdProductDeal().getSecManageAcctId();
        // 资产代码
        String assetCode = finProductTradeDto.getTrdProductDeal().getFinprodId();
        // 交易日期
        Date tradeDate = finProductTradeDto.getTrdProductDeal().getTradeDate();
        // TODO
        BigDecimal 交易日减少数量 = finProductTradeDto.getTrdProductDeal().getShareAmt().negate();
        BigDecimal 交割日增加交易金额 = finProductTradeDto.getTrdProductDeal().getTradeAmt();
        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);
        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);
        etrCashPositionFlowDto.setOrderId(finProductTradeDto.getTrdProductDeal().getTradeId());
        String 账号 = gen账号(finProductTradeDto.getTrdProductDeal().getTradeMarket(),
            finProductTradeDto.getTrdProductDeal().getChlId(), finProductTradeDto.getTrdProductDealAdd().getPortfolioId(),
            finProductTradeDto.getTrdProductDeal().getCcy(), finProductTradeDto.getTrdProductDeal().getSecManageAcctId(),
                finProductTradeDto.getTrdProductDeal().getCounterId(),true,etrCashPositionFlowDto);
        // 设置账号
        etrCashPositionFlowDto.setAcctId(账号);
        // 交易生效
        if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
            etrCashPositionFlowDto.setCdate(finProductTradeDto.getTrdProductDeal().getSettleDate());
            // 有委托
            if (StringUtil.isNotEmpty(entrustId)) {
                // 现金流水dto处理
                // 交割日增加交易金额
                etrCashPositionFlowDto.setAmount(交割日增加交易金额);
                // 添加现金流水到目标dto
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
            }
            // 无委托
            else {
                // 现金流水dto处理
                // 交割日增加交易金额
                etrCashPositionFlowDto.setAmount(交割日增加交易金额);
                // 添加现金流水到目标dto
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
            }
        }
        // 交易提交
        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
            if (StringUtil.isEmpty(entrustId)) {
                // 持仓流水dto处理
                // 交割日增加交易券面总额/债券面值
                etrSecPositionFlowDto.setAmount(交易日减少数量);
                // 添加持仓流水到目标dto
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            }
        }
    }

    /**
     * 债券_银行间_委托直投买入
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto 流水容器
     */
    private static void 债券_银行间_委托直投买入(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                      EtrPositionHoldDto etrPositionHoldDto) {
        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();
        // 证券账户
        String secManageAcctId = finProductTradeDto.getTrdProductDeal().getSecManageAcctId();
        // 资产代码
        String assetCode = finProductTradeDto.getTrdProductDeal().getFinprodId();
        // 交易日期
        Date tradeDate = finProductTradeDto.getTrdProductDeal().getTradeDate();
        // TODO
        BigDecimal 交易日数量 = finProductTradeDto.getTrdProductDeal().getShareAmt();
        BigDecimal 交易日减少金额 = finProductTradeDto.getTrdProductDeal().getTradeAmt().negate();
        // TODO 委托全价获取方式
        // BigDecimal 委托全价 = finProductTradeDto.getTrdProductDeal().getFpriceAmt();
        // 交割日减少(交易金额-交易券面总额*委托全价/债券面值）
        // BigDecimal amount = ArithmeticUtil.subtract(交易日减少金额,
        // ArithmeticUtil.divide(ArithmeticUtil.multiply(交易券面总额, 委托全价), 债券面值, 2));
        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);
        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);
        etrCashPositionFlowDto.setOrderId(finProductTradeDto.getTrdProductDeal().getTotleTradeId());
        if(StringUtil.isNotEmpty(finProductTradeDto.getPurchModle())
        		&& StringUtil.equals(DictCodeListExt2.申购方式_线下申购.getValue(), finProductTradeDto.getPurchModle())){
        	String 账号 = gen账号1(finProductTradeDto.getTrdProductDeal().getTradeMarket(),
                    finProductTradeDto.getTrdProductDeal().getChlId(), finProductTradeDto.getTrdProductDealAdd().getPortfolioId(),
                    finProductTradeDto.getTrdProductDeal().getCcy(), finProductTradeDto.getTrdProductDeal().getSecManageAcctId(),
                        finProductTradeDto.getTrdProductDeal().getCounterId(),false,etrCashPositionFlowDto);
        	etrCashPositionFlowDto.setAcctId(账号);
        }
        else {
            // 交易场所为一级市场且是交易所的交易，付款账户为托管户 add by 马数凯 20210430 
            String 账号;
            if (StringUtil.equals(finProductTradeDto.getTrdProductDeal().getTradeMarketMode(),DictCodeListExt1.交易市场_一级市场.getValue())
                    && (StringUtil.equals(finProductTradeDto.getTrdProductDeal().getTradeMarket(),DictCodeListExt1.交易场所_上海证券交易所.getValue())
                    || StringUtil.equals(finProductTradeDto.getTrdProductDeal().getTradeMarket(),DictCodeListExt1.交易场所_深圳证券交易所.getValue()))){
                账号 = EtrCommonCashPositionDealService.select组合托管户(finProductTradeDto.getTrdProductDealAdd().getPortfolioId(), finProductTradeDto.getTrdProductDeal().getCcy(),DictCodeListExt2.资金账户类型_托管户.getValue());
            }else{
                账号 = gen账号(finProductTradeDto.getTrdProductDeal().getTradeMarket(),
                        finProductTradeDto.getTrdProductDeal().getChlId(), finProductTradeDto.getTrdProductDealAdd().getPortfolioId(),
                        finProductTradeDto.getTrdProductDeal().getCcy(), finProductTradeDto.getTrdProductDeal().getSecManageAcctId(),
                        finProductTradeDto.getTrdProductDeal().getCounterId(),etrCashPositionFlowDto);
            }
        
            // 设置账号
            etrCashPositionFlowDto.setAcctId(账号);
        }
        // 交易生效
        if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
            etrSecPositionFlowDto.setCdate(finProductTradeDto.getTrdProductDeal().getSettleDate());
            etrCashPositionFlowDto.setCdate(finProductTradeDto.getTrdProductDeal().getSettleDate());

            // 有委托
            if (StringUtil.isNotEmpty(entrustId)) {
                // 持仓流水dto处理
                // 交割日增加交易券面总额/债券面值
                etrSecPositionFlowDto.setAmount(交易日数量);
                // 添加持仓流水到目标dto
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                // 交割日减少(交易金额-交易券面总额*委托全价/债券面值）
                etrCashPositionFlowDto.setAmount(交易日减少金额);

                // 添加现金流水到目标dto
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                // TODO 委托冲销流水生成
                etrPositionHoldDto.getPtlCashPositionFlows().add(gen冲销现金流水生成(etrCashPositionFlowDto, entrustId, 交易日数量));
            }
            // 无委托
            else {
                // 持仓流水dto处理
                // 交割日增加交易券面总额/债券面值
                etrSecPositionFlowDto.setAmount(交易日数量);
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            }
        }
        // 交易提交
        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
            if (StringUtil.isEmpty(entrustId)) {
                // 直投无委托买入，应该交易日和驳回日更新金额流水
                etrCashPositionFlowDto.setCdate(finProductTradeDto.getTrdProductDeal().getTradeDate());
                // 现金流水dto处理
                EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                etrCashPositionFlowDto1.setCdate(finProductTradeDto.getTrdProductDeal().getTradeDate());
                etrCashPositionFlowDto1.setSeqno(getSeqNo());
                etrCashPositionFlowDto1.setAmount(交易日减少金额);
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto1);
            }
        }
    }

    private static void 预增现金处理(EtrPositionHoldDto etrPositionHoldDto, TrdProductDeal deal, TrdProductDealAdd dealAdd,
                               List<StlPayOrdersDto> stlPayOrdersDtos, EtrCashPositionFlowDto etrCashPositionFlowDto) {
        stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
            etrCashPositionFlowDto.setOrderId(stlPayOrdersDto.getOrderId());
            // 预增现金 支付日账日增加到账金额
            EtrCashPositionFlowDto etrCashPositionFlowDto2 = new EtrCashPositionFlowDto();
            BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto2, etrCashPositionFlowDto);
            etrCashPositionFlowDto2.setAmount(stlPayOrdersDto.getSettleAmt());
            etrCashPositionFlowDto2.setAcctId(stlPayOrdersDto.getMyAcctNo());
            etrCashPositionFlowDto2.setCdate(stlPayOrdersDto.getSettleDate());
            etrCashPositionFlowDto2.setHodingType(DictCodeListExt1.持仓类型_委托指令预增.getValue());
            etrCashPositionFlowDto2.setOffsetFlag(DictCodeList.冲正标识_正常交易.getValue());
            etrCashPositionFlowDto2.setSeqno(getSeqNo());
            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto2);
        });
    }

    private static void 现金处理(EtrPositionHoldDto etrPositionHoldDto, TrdProductDeal deal, TrdProductDealAdd dealAdd,
                             List<StlPayOrdersDto> stlPayOrdersDtos, EtrCashPositionFlowDto etrCashPositionFlowDto) {
        stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
            // 可用现金 支付日增加到账金额
            etrCashPositionFlowDto.setOrderId(stlPayOrdersDto.getOrderId());
            EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
            BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
            etrCashPositionFlowDto1.setAmount(stlPayOrdersDto.getSettleAmt());
            etrCashPositionFlowDto1.setAcctId(stlPayOrdersDto.getMyAcctNo());
            etrCashPositionFlowDto1.setCdate(stlPayOrdersDto.getSettleDate());
            etrCashPositionFlowDto1.setSeqno(getSeqNo());
            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto1);

            // 预增现金 支付日减少到账金额
            EtrCashPositionFlowDto etrCashPositionFlowDto2 = new EtrCashPositionFlowDto();
            BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto2, etrCashPositionFlowDto1);
            etrCashPositionFlowDto2.setAmount(stlPayOrdersDto.getSettleAmt().negate());
            etrCashPositionFlowDto2.setAcctId(stlPayOrdersDto.getMyAcctNo());
            etrCashPositionFlowDto2.setCdate(stlPayOrdersDto.getSettleDate());
            etrCashPositionFlowDto2.setHodingType(DictCodeListExt1.持仓类型_委托指令预增.getValue());
            etrCashPositionFlowDto2.setOffsetFlag(DictCodeList.冲正标识_正常交易.getValue());
            etrCashPositionFlowDto2.setSeqno(getSeqNo());
            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto2);
        });
    }

    /**
     * 获取现金流水需要的账号
     * 
     * @param tradePlace 交易场所
     * @param chlId 通道代码
     * @param portfolioId 组合代码
     * @param ccy 币种
     * @param secManageAcctId 证券托管户
     * @param counterId 交易对手
     * @param etrCashPositionFlowDto 流水dto
     * @return 账号
     */
    private static String gen账号(String tradePlace, String chlId, String portfolioId, String ccy, String secManageAcctId,String counterId,
    		EtrCashPositionFlowDto etrCashPositionFlowDto) {
        return gen账号(tradePlace,chlId,portfolioId,ccy,secManageAcctId,counterId,false,etrCashPositionFlowDto);
    }
    /**
     * 获取现金流水需要的账号
     *
     * @param tradePlace 交易场所
     * @param chlId 通道代码
     * @param portfolioId 组合代码
     * @param ccy 币种
     * @param secManageAcctId 证券托管户
     * @param counterId 交易对手
     * @param is卖出  是否是卖出
     * @param etrCashPositionFlowDto 流水dto
     * @return 账号
     */
    private static String gen账号(String tradePlace, String chlId, String portfolioId, String ccy, String secManageAcctId,String counterId,
                                boolean is卖出,
                                EtrCashPositionFlowDto etrCashPositionFlowDto){
        String chlType = StringUtil.isEmpty(chlId) ? null : EtrCommonCashPositionDealService.queryChlType(chlId);
        if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_一次性划款.getValue())
            || (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue()) && StringUtil.isBlank(counterId))) {
            etrCashPositionFlowDto.setChlFinprodId(chlId);
            return EtrCommonCashPositionDealService.select通道资金账户(chlId);
        }
        else {
            // 交易所无通道
            etrCashPositionFlowDto.setChlFinprodId(null);
            if (StringUtil.equals(tradePlace, DictCodeListExt1.交易场所_境外交易场所.getValue())) {
                return EtrCommonCashPositionDealService.select组合托管户(portfolioId, ccy, DictCodeListExt2.资金账户类型_托管户.getValue());
            }
            else if (!StringUtil.equals(tradePlace, DictCodeListExt1.交易场所_银行间.getValue()) && StringUtil.isNotEmpty(secManageAcctId)
            &&!(is卖出&&EtrCommonCashPositionDealService.isInTrade(counterId))) {
                return EtrCommonCashPositionDealService.select资金账户(secManageAcctId, ccy);
            }
            else {
                // 银行间深交所修改需求同一使用托管户（逐笔通道）
                return EtrCommonCashPositionDealService.select组合托管户(portfolioId, ccy, DictCodeListExt2.资金账户类型_托管户.getValue());
            }
        }
    }
    private static String gen账号1(String tradePlace, String chlId, String portfolioId, String ccy, String secManageAcctId,String counterId,
            boolean is卖出,
            EtrCashPositionFlowDto etrCashPositionFlowDto){
             String chlType = StringUtil.isEmpty(chlId) ? null : EtrCommonCashPositionDealService.queryChlType(chlId);
            if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_一次性划款.getValue())) {
            etrCashPositionFlowDto.setChlFinprodId(chlId);
           return EtrCommonCashPositionDealService.select通道资金账户(chlId);
            }
         else {
          // 交易所无通道
           etrCashPositionFlowDto.setChlFinprodId(null);
           if (StringUtil.equals(tradePlace, DictCodeListExt1.交易场所_境外交易场所.getValue())) {
           return EtrCommonCashPositionDealService.select组合托管户(portfolioId, ccy, DictCodeListExt2.资金账户类型_托管户.getValue());
            }
          /* else if (!StringUtil.equals(tradePlace, DictCodeListExt1.交易场所_银行间.getValue()) && StringUtil.isNotEmpty(secManageAcctId)
           &&!(is卖出&&EtrCommonCashPositionDealService.isInTrade(counterId))) {
           return EtrCommonCashPositionDealService.select资金账户(secManageAcctId, ccy);
          }*/
          else {
            // 银行间深交所修改需求同一使用托管户（逐笔通道）
          return EtrCommonCashPositionDealService.select组合托管户(portfolioId, ccy, DictCodeListExt2.资金账户类型_托管户.getValue());
          }
      }
   }
    
    public static void deal债券EntrustStart(EntrustCommonDto entrustCommonDto, EtrPositionHoldDto etrPositionHoldDto) {
        if (entrustCommonDto != null) {
            EtrEntrust etrEntrust = entrustCommonDto.getEtrEntrust();
            // 交易方向
            String ps = etrEntrust.getPs();
            BigDecimal 委托全价 = gen委托全价(etrEntrust.getEntrustId(), entrustCommonDto.getEtrEntrust().getEntrustDate());
            if (DictCodeList.交易方向_买入.getValue().equals(ps)) {
                // 委托生成的现金流水
                EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDtoFromEtr(
                    entrustCommonDto);
                String acctId;
                // if (StringUtil.isNotEmpty(etrEntrust.getChlId()) && DictCodeListExt2.划款方式_一次性划款.getValue().equals(
                // etrCommonCashPositionDealService.queryChlType(etrEntrust.getChlId()))) {
                // acctId = EtrCommonCashPositionDealService.select通道资金账户(etrEntrust.getChlId());
                // }
                // else {
                // acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
                // DictCodeListExt2.资金账户类型_托管户.getValue());
                // }
                acctId = gen账号(etrEntrust.getTradeMarket(), etrEntrust.getChlId(), etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
                    entrustCommonDto.getEtrEntrustAdd().getSecAcctId(),
                        entrustCommonDto.getEtrEntrust().getCounterId(), etrCashPositionFlowDto);
                // 减少委托券面总额*委托全价/100
                etrCashPositionFlowDto.setAmount(etrEntrust.getEntrustNum().negate().multiply(委托全价));
                etrCashPositionFlowDto.setAcctId(acctId);
                etrCashPositionFlowDto.setOffsetId(entrustCommonDto.getEtrEntrust().getEntrustId());
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
            }
            else if (is卖出(ps)) {
                // 减少委托券面总额/100
                EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDtoFromEtr(
                    entrustCommonDto);
                etrSecPositionFlowDto.setAmount(etrEntrust.getEntrustNum().negate());
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            }
        }
    }

    public static void deal债券EntrustClose(EntrustCommonDto entrustCommonDto, EtrPositionHoldDto etrPositionHoldDto) {
        EtrEntrust etrEntrust = entrustCommonDto.getEtrEntrust();
        // 交易方向
        String ps = etrEntrust.getPs();
        BigDecimal etrFinish = EtrCommonCashPositionDealService.getEtrFinishAmt(etrEntrust.getEntrustId());
        // BigDecimal 委托全价 = gen委托全价(etrEntrust.getEntrustId(),entrustCommonDto.getEtrEntrust().getEntrustDate());
        if (DictCodeList.交易方向_买入.getValue().equals(ps)) {
            // 可用现金 关闭日增加剩余委托券面总额*委托全价/100
            EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDtoFromEtr(
                entrustCommonDto);
            String acctId;
            // if (StringUtil.isNotEmpty(etrEntrust.getChlId()) && DictCodeListExt2.划款方式_一次性划款.getValue().equals(
            //                // etrCommonCashPositionDealService.queryChlType(etrEntrust.getChlId()))) {
            // acctId = EtrCommonCashPositionDealService.select通道资金账户(etrEntrust.getChlId());
            // }
            // else {
            // acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
            // DictCodeListExt2.资金账户类型_托管户.getValue());
            // }
            acctId = gen账号(etrEntrust.getTradeMarket(), etrEntrust.getChlId(), etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
                entrustCommonDto.getEtrEntrustAdd().getSecAcctId(),entrustCommonDto.getEtrEntrust().getCounterId(), etrCashPositionFlowDto);
            // 关闭日增加剩余委托券面总额*委托全价/100
            etrCashPositionFlowDto.setAmount(
                EtrCommonCashPositionDealService.getEtrSurplusAmt(etrEntrust.getEntrustId(), etrEntrust.getPortfolioId()));
            etrCashPositionFlowDto.setAcctId(acctId);
            etrCashPositionFlowDto.setOffsetId(etrEntrust.getEntrustId());
            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
        }
        else if (is卖出(ps)) {
            EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDtoFromEtr(
                entrustCommonDto);
            // 可用持仓 关闭日增加剩余委托券面总额/100
            etrSecPositionFlowDto.setAmount(ArithmeticUtil.subtract(etrEntrust.getEntrustNum(), etrFinish));
            etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
        }
    }

    /**
     * 获取委托全价
     * 
     * @param entrustId 委托代码
     * @param dealDate 日期
     * @return 委托全价
     */
    public static BigDecimal gen委托全价(String entrustId, Date dealDate) {
        EntrustCommonDto entrustCommonDto = EtrCommonCashPositionDealService.findEntrust(entrustId);
        // 价格模式_指定价格(DictCode.价格模式,"PRM01","指定价格"),
        // 价格模式_限价指令(DictCode.价格模式,"PRM02","限价指令"),
        // 价格模式_市价指令(DictCode.价格模式,"PRM03","市价指令"),
        if (StringUtil.equals(entrustCommonDto.getEtrEntrust().getPriceModel(), DictCodeListExt2.价格模式_指定价格.getValue())
            || StringUtil.equals(entrustCommonDto.getEtrEntrust().getPriceModel(), DictCodeListExt2.价格模式_限价指令.getValue())) {
            BigDecimal 委托净价 = entrustCommonDto.getEtrEntrust().getPrice();
            BigDecimal 委托全价 = null;
            if(委托净价==null){
                委托全价 = 债券面值;
            }else if(StringUtil.equals(DictCodeListExt1.交易市场_一级市场.getValue(),entrustCommonDto.getEtrEntrust().getTradeMarketMode())){
            	委托全价 = 委托净价;
            }
            else {
            	 //交易所债券特殊处理start----------------------------------------------------------------
            	 boolean isJiaoYiSuo = true;
                 if (!StringUtil.equals(DictCodeListExt1.交易场所_上海证券交易所.getValue(), entrustCommonDto.getEtrEntrust().getTradeMarket())
                     && !StringUtil.equals(DictCodeListExt1.交易场所_深圳证券交易所.getValue(), entrustCommonDto.getEtrEntrust().getTradeMarket())) {
                     // 应计利息根据后台估值核算中前一个自然日的数据计算，等于后台的应计利息总额/后台的债券持仓总数量                   
                     isJiaoYiSuo = false;
                 }
                // 如果交易场所为上交所、深交所的情况
                if (isJiaoYiSuo) {
                    // 付息债、贴现债、零息债，交割日+1
//                    if (StringUtil.equals(DictCodeList.息票品种_附息.getValue(), secInfoModelDto.getBondsType())
//                        || StringUtil.equals(DictCodeList.息票品种_贴现.getValue(), secInfoModelDto.getBondsType())
//                        || StringUtil.equals(DictCodeList.息票品种_零息.getValue(), secInfoModelDto.getBondsType())) {
                    if (!StringUtil.equals(entrustCommonDto.getEtrEntrust().getPs(), DictCodeList.交易方向_行权_回售.getValue())
                            && !StringUtil.equals(entrustCommonDto.getEtrEntrust().getPs(), DictCodeList.交易方向_行权_赎回.getValue())) {
                    	dealDate = DateUtil.addDay(dealDate, 1);
                    }
//                    }
                }
              //交易所债券特殊处理end----------------------------------------------------------------
                EtrCommonCashPositionDealService etrCommonCashPositionDealService = SpringContextUtil.getBean(EtrCommonCashPositionDealService.class);
                BigDecimal 单位应计利息 = etrCommonCashPositionDealService.gen应计利息(entrustCommonDto.getEtrEntrust().getFinprodId(),dealDate);
                委托全价 = ArithmeticUtil.doAdd(单位应计利息,委托净价);
            }
            return 委托全价;
        }
        else {
            // 金融产品代码
            String finprodId = entrustCommonDto.getEtrEntrust().getFinprodId();
            Map<String, Object> map = new HashMap<>();
            map.put("finprodId", finprodId);
            map.put("dealDate", dealDate);
            BigDecimal 最新市价 = EtrCommonCashPositionDealService.find最新市价(map);
            if (最新市价 == null) {
                最新市价 = 债券面值;
            }
            return 最新市价;
        }
    }

    /**
     * gen冲销现金流水生成
     * 
     * @param etrCashPositionFlowDto 正常流水
     * @param entrustId 委托代码
     * @return 冲销流水
     */
    public static EtrCashPositionFlowDto gen冲销现金流水生成(EtrCashPositionFlowDto etrCashPositionFlowDto, String entrustId,
                                                     BigDecimal amount) {
        EtrCashPositionFlowDto ret = new EtrCashPositionFlowDto();
        BeanUtil.copyPropertiesIgnoreNull(ret, etrCashPositionFlowDto);
        ret.setOffsetFlag(DictCodeList.冲正标识_冲销交易.getValue());
        ret.setSeqno(EtrCommonCashPositionDealService.getSeqNo());
        ret.setOffsetId(entrustId);
        EntrustCommonDto entrustCommonDto = EtrCommonCashPositionDealService.findEntrust(entrustId);
        BigDecimal 委托全价 = gen委托全价(entrustId, etrCashPositionFlowDto.getCdate());
        BigDecimal amountReal = ArithmeticUtil.multiply(amount, 委托全价 == null ? new BigDecimal(100) : 委托全价);
        ret.setAmount(amountReal == null ? amount : amountReal);
        ret.setSeqno(getSeqNo());
        return ret;
    }
}
