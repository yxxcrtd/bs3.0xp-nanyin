package com.joyintech.tams.invest.common.trade.service.impl.etrcash;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.etr.EtrCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrPositionHoldDto;
import com.joyintech.tams.invest.common.dto.etr.EtrSecPositionFlowDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.trade.dto.EtrSecPositionDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService.getSeqNo;

/**
 * @program: 2.1.0
 * @description:
 * @author: 强成西
 * @create: 2019-10-12 15:07
 * 计划型利率
 **/
@Service
public class EtrPlanRateCashPositionDealService{

    @Autowired
    private static EtrCommonCashPositionDealService etrCommonCashPositionDealService;

    @Autowired
    public void setEtrCashPositionFlowMapper(EtrCommonCashPositionDealService commonCashPositionDealService) {
        EtrPlanRateCashPositionDealService.etrCommonCashPositionDealService= commonCashPositionDealService;
    }




    public static void deal计划利率型CashPosition(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                             EtrPositionHoldDto etrPositionHoldDto) {
        EtrSecPositionDto etrSecPositionDto = new EtrSecPositionDto();
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();
        String portfolioId = finProductTradeDto.getTrdProductDealAdd().getPortfolioId();
        /**etrSecPositionDto.setPortfolioId(finProductTradeDto.getTrdProductDealAdd().getPortfolioId());
        String secAcctId = finProductTradeDto.getTrdProductDeal().getSecManageAcctId();
        if (StringUtil.isEmpty(secAcctId) && StringUtil.isEmpty(finProductTradeDto.getTrdProductDeal().getChlId())) {
            secAcctId = "999999";
        }
        else if (StringUtil.isNotEmpty(finProductTradeDto.getTrdProductDeal().getChlId())) {
            secAcctId = finProductTradeDto.getTrdProductDeal().getChlId();
        }

        etrSecPositionDto.setSecAcctId(secAcctId);
        etrSecPositionDto.setFinprodId(finProductTradeDto.getTrdProductDeal().getFinprodId());
        etrSecPositionDto.setBranch(finProductTradeDto.getTrdProductDeal().getBranch());
        etrSecPositionDto.setCdate(finProductTradeDto.getTrdProductDeal().getTradeDate());
        etrSecPositionDto.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
        //查询持仓可用查询可用持仓
        BigDecimal bigDecimal = EtrCommonCashPositionDealService.selectAvailablePositionAmount(etrSecPositionDto);**/
        // 通道代码
        String chlId = deal.getChlId();
        // 交易方向
        String ps = deal.getPs();
        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();

        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
                finProductTradeDto);
        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
                finProductTradeDto);

        if (StringUtil.isEmpty(chlId)) { // 直投
            直投(持仓类型, etrPositionHoldDto, finProductTradeDto.getStlPayOrdersList(), deal,
                    ps, entrustId, etrSecPositionFlowDto, etrCashPositionFlowDto,portfolioId);
        } else { // 借通道
            // 通道性质
            String chlType = StringUtil.isEmpty(chlId) ? null : EtrCommonCashPositionDealService.queryChlType(chlId);
            if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue())) {
                etrCashPositionFlowDto.setChlFinprodId(null);
                通道(持仓类型, etrPositionHoldDto, finProductTradeDto.getStlPayOrdersList(), deal, ps,
                        entrustId, etrSecPositionFlowDto, etrCashPositionFlowDto,portfolioId);
            }
            // 一次性划款通道
            else {
                一次性划款(持仓类型, etrPositionHoldDto, finProductTradeDto.getStlPayOrdersList(), deal, ps, entrustId,
                        etrSecPositionFlowDto, etrCashPositionFlowDto, portfolioId);
            }
        }
    }

    private static void 一次性划款(DictCodeList 持仓类型, EtrPositionHoldDto etrPositionHoldDto,
                              List<StlPayOrdersDto> stlPayOrdersDtos,
                              TrdProductDeal deal, String ps, String entrustId, EtrSecPositionFlowDto etrSecPositionFlowDto, EtrCashPositionFlowDto 
                                      etrCashPositionFlowDto, String portfolioId) {
        BigDecimal 交易金额=deal.getTradeAmt();
        if(stlPayOrdersDtos!=null){
            交易金额=stlPayOrdersDtos.get(0).getSettleAmt();}
        if (DictCodeList.交易方向_买入.getValue().equals(ps)) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                if (StringUtil.isEmpty(entrustId)) { // 无委托
                    // 可用持仓 交易日增加本金金额
                    持仓交易(etrPositionHoldDto, deal, etrSecPositionFlowDto,
                            ArithmeticUtil.doAdd( BigDecimal.ZERO,deal.getPrinAmt()), DictCodeListExt1.持仓类型_委托指令可用.getValue());
                }
                else { // 有委托
                    // 可用持仓 交易日增加本金金额
                    持仓交易(etrPositionHoldDto, deal, etrSecPositionFlowDto,
                            ArithmeticUtil.doAdd( BigDecimal.ZERO,deal.getPrinAmt()),DictCodeListExt1.持仓类型_委托指令可用.getValue());
                    // 有委托增加一笔利息现金的减少
                    有委托增加一笔利息现金的减少(etrPositionHoldDto, deal, etrCashPositionFlowDto, portfolioId);
                }
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                if (StringUtil.isEmpty(entrustId)) {
                    // 通道 交易日减少交易本金金额
                    EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                    BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                    etrCashPositionFlowDto1.setSeqno(getSeqNo());
                    现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                            ArithmeticUtil.subtract(BigDecimal.ZERO, 交易金额), DictCodeListExt1.持仓类型_委托指令可用.getValue(),
                            portfolioId, DictCodeListExt2.划款方式_一次性划款.getValue());
                }
            }
        }
        else if (DictCodeList.交易方向_卖出.getValue().equals(ps)) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                if (StringUtil.isEmpty(entrustId)) { // 无委托
                    // 可用现金 通道 交易日增加交易金额
                    现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto,
                            交易金额, DictCodeListExt1.持仓类型_委托指令可用.getValue(),
                            portfolioId, DictCodeListExt2.划款方式_一次性划款.getValue());
                }
                else { // 有委托
                    // 可用现金 通道 交易日增加交易金额
                    现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto,
                            交易金额,DictCodeListExt1.持仓类型_委托指令可用.getValue(),
                            portfolioId, DictCodeListExt2.划款方式_一次性划款.getValue());
                }
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                if (StringUtil.isEmpty(entrustId)) {
                    // 可用持仓 通道 交易日减少本金金额
                    持仓交易(etrPositionHoldDto, deal, etrSecPositionFlowDto,
                            ArithmeticUtil.subtract(BigDecimal.ZERO, deal.getPrinAmt()), DictCodeListExt1.持仓类型_委托指令可用.getValue());
                }
            }
        }
        else if (DictCodeList.交易方向_收息.getValue().equals(ps)) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                    stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                        // 可用现金 通道 资金到账日增加到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                        etrCashPositionFlowDto1.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                                ArithmeticUtil.doAdd(BigDecimal.ZERO, stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令可用.getValue(), portfolioId, DictCodeListExt2.划款方式_一次性划款.getValue());
                        // 预增现金 通道 资金到账日减少到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto2 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto2, etrCashPositionFlowDto);
                        etrCashPositionFlowDto2.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto2,
                                ArithmeticUtil.subtract(BigDecimal.ZERO, stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令预增.getValue(), portfolioId, DictCodeListExt2.划款方式_一次性划款.getValue());
                    });
                }
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                if (StringUtil.isEmpty(entrustId)) { // 无委托
                    if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                        stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                            // 预增现金 通道 资金到账日增加到账金额
                            EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                            BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                            etrCashPositionFlowDto1.setSeqno(getSeqNo());
                            现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                                ArithmeticUtil.doAdd(BigDecimal.ZERO, stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令预增.getValue(), portfolioId, DictCodeListExt2.划款方式_一次性划款.getValue());
                        });
                    }
                }
            }
        }
        else if (DictCodeList.交易方向_还本.getValue().equals(ps)) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                    stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                        // 可用现金 通道 资金到账日增加到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                        etrCashPositionFlowDto1.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                                ArithmeticUtil.doAdd(BigDecimal.ZERO, stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令可用.getValue(), portfolioId, DictCodeListExt2.划款方式_一次性划款.getValue());
                        // 预增现金 通道 资金到账日减少到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto2 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto2, etrCashPositionFlowDto);
                        etrCashPositionFlowDto2.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto2,
                                ArithmeticUtil.subtract(BigDecimal.ZERO, stlPayOrdersDto.getSettleAmt()), DictCodeListExt1.持仓类型_委托指令预增.getValue(),
                                portfolioId, DictCodeListExt2.划款方式_一次性划款.getValue());
                    });
                }
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                    stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                        // 预增现金 通道 资金到账日增加到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                        etrCashPositionFlowDto1.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                            ArithmeticUtil.doAdd(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                            DictCodeListExt1.持仓类型_委托指令预增.getValue(),portfolioId, DictCodeListExt2.划款方式_一次性划款.getValue());
                    });
                }
                // 可用持仓 通道 还本本金
                EtrSecPositionFlowDto etrSecPositionFlowDto1 = new EtrSecPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(etrSecPositionFlowDto1, etrSecPositionFlowDto);
                etrSecPositionFlowDto1.setSeqno(getSeqNo());
                持仓交易(etrPositionHoldDto, deal, etrSecPositionFlowDto1,
                        ArithmeticUtil.subtract( BigDecimal.ZERO, deal.getPrinAmt()),DictCodeListExt1.持仓类型_委托指令可用.getValue());
            }
        }
        else if (DictCodeList.交易方向_到期.getValue().equals(ps)) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                    stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                        // 可用现金 通道 资金到账日增加到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                        etrCashPositionFlowDto1.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                                ArithmeticUtil.doAdd(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令可用.getValue(),portfolioId, DictCodeListExt2.划款方式_一次性划款.getValue());
                        // 预增现金 通道 资金到账日减少到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto2 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto2, etrCashPositionFlowDto);
                        etrCashPositionFlowDto2.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto2,
                                ArithmeticUtil.subtract(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令预增.getValue(),portfolioId, DictCodeListExt2.划款方式_一次性划款.getValue());
                    });
                }
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                    stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                        // 预增现金 通道 资金到账日增加到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                        etrCashPositionFlowDto1.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                            ArithmeticUtil.doAdd(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                            DictCodeListExt1.持仓类型_委托指令预增.getValue(),portfolioId, DictCodeListExt2.划款方式_一次性划款.getValue());
                    });
                }
                // 可用持仓 通道 提交日削减至0
                EtrSecPositionFlowDto etrSecPositionFlowDto1 = new EtrSecPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(etrSecPositionFlowDto1, etrSecPositionFlowDto);
                etrSecPositionFlowDto1.setSeqno(getSeqNo());
                持仓交易(etrPositionHoldDto, deal, etrSecPositionFlowDto1,
                        ArithmeticUtil.subtract( BigDecimal.ZERO, deal.getPrinAmt()),DictCodeListExt1.持仓类型_委托指令可用.getValue());
            }
        }
    }

    private static void 有委托增加一笔利息现金的减少(EtrPositionHoldDto etrPositionHoldDto, TrdProductDeal deal, EtrCashPositionFlowDto etrCashPositionFlowDto, String portfolioId) {
        if (deal.getIntAmt() != null && deal.getIntAmt().compareTo(BigDecimal.ZERO) > 0) {
            EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
            BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
            现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                    ArithmeticUtil.subtract(BigDecimal.ZERO, deal.getIntAmt()), DictCodeListExt1.持仓类型_委托指令可用.getValue(),
                    portfolioId, null);
        }
    }

    private static void 通道(DictCodeList 持仓类型, EtrPositionHoldDto etrPositionHoldDto,
                           List<StlPayOrdersDto> stlPayOrdersDtos, TrdProductDeal deal, String ps, String entrustId,
                           EtrSecPositionFlowDto etrSecPositionFlowDto, EtrCashPositionFlowDto etrCashPositionFlowDto, String portfolioId) {
        BigDecimal 交易金额=deal.getTradeAmt();
        if(stlPayOrdersDtos!=null){
            交易金额=stlPayOrdersDtos.get(0).getSettleAmt();}
        if (DictCodeList.交易方向_买入.getValue().equals(ps)) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                if (StringUtil.isEmpty(entrustId)) { // 无委托
                    // 可用持仓 通道 交易日增加本金金额
                    持仓交易(etrPositionHoldDto, deal, etrSecPositionFlowDto,
                            ArithmeticUtil.doAdd( BigDecimal.ZERO,deal.getPrinAmt()), DictCodeListExt1.持仓类型_委托指令可用.getValue());
                }
                else { // 有委托
                    // 可用持仓 通道 交易日增加本金金额
                    持仓交易(etrPositionHoldDto, deal, etrSecPositionFlowDto,
                            ArithmeticUtil.doAdd( BigDecimal.ZERO,deal.getPrinAmt()),DictCodeListExt1.持仓类型_委托指令可用.getValue());
                    有委托增加一笔利息现金的减少(etrPositionHoldDto, deal, etrCashPositionFlowDto, portfolioId);
                }
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                if (StringUtil.isEmpty(entrustId)) {
                    // 可用现金 交易日减少交易本金金额
                    现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto,
                            ArithmeticUtil.subtract(BigDecimal.ZERO, 交易金额), DictCodeListExt1.持仓类型_委托指令可用.getValue(),
                            portfolioId, DictCodeListExt2.划款方式_逐笔划款.getValue());
                }
            }
        }
        else if (DictCodeList.交易方向_卖出.getValue().equals(ps)) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                if (StringUtil.isEmpty(entrustId)) { // 无委托
                    // 可用现金 托管户 交易日增加交易金额
                    现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto,
                            交易金额, DictCodeListExt1.持仓类型_委托指令可用.getValue(),
                            portfolioId, DictCodeListExt2.划款方式_逐笔划款.getValue());
                }
                else { // 有委托
                    // 可用现金 托管户 交易日增加交易金额
                    现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto,
                            交易金额, DictCodeListExt1.持仓类型_委托指令可用.getValue(),
                            portfolioId, DictCodeListExt2.划款方式_逐笔划款.getValue());
                }
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                if (StringUtil.isEmpty(entrustId)) {
                    // 可用持仓 通道 交易日减少本金金额
                    持仓交易(etrPositionHoldDto, deal, etrSecPositionFlowDto,
                            ArithmeticUtil.subtract(BigDecimal.ZERO, deal.getPrinAmt()), DictCodeListExt1.持仓类型_委托指令可用.getValue());
                }
            }
        }
        else if (DictCodeList.交易方向_收息.getValue().equals(ps)) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                    stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                        // 可用现金 资金到账日增加到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                        etrCashPositionFlowDto1.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                                ArithmeticUtil.doAdd(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令可用.getValue(), portfolioId, DictCodeListExt2.划款方式_逐笔划款.getValue());
                        // 预增现金 资金到账日减少到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto2 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto2, etrCashPositionFlowDto);
                        etrCashPositionFlowDto2.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto2,
                                ArithmeticUtil.subtract(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令预增.getValue(), portfolioId, DictCodeListExt2.划款方式_逐笔划款.getValue());
                    });
                }
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                if (StringUtil.isEmpty(entrustId)) { // 无委托
                    if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                        stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                            // 预增现金 资金到账日增加到账金额
                            EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                            BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                            etrCashPositionFlowDto1.setSeqno(getSeqNo());
                            现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                                ArithmeticUtil.doAdd(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令预增.getValue(), portfolioId, DictCodeListExt2.划款方式_逐笔划款.getValue());
                        });
                    }
                }
            }
        }
        else if (DictCodeList.交易方向_还本.getValue().equals(ps)) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                    stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                        // 可用现金 托管户 资金到账日增加到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                        etrCashPositionFlowDto1.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                                ArithmeticUtil.doAdd(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令可用.getValue(), portfolioId, DictCodeListExt2.划款方式_逐笔划款.getValue());
                        // 预增现金 托管户 资金到账日减少到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto2 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto2, etrCashPositionFlowDto);
                        etrCashPositionFlowDto2.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto2,
                                ArithmeticUtil.subtract(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令预增.getValue(), portfolioId, DictCodeListExt2.划款方式_逐笔划款.getValue());
                    });
                }
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                    stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                        // 预增现金 托管户 资金到账日增加到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                        etrCashPositionFlowDto1.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                            ArithmeticUtil.doAdd(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                            DictCodeListExt1.持仓类型_委托指令预增.getValue(), portfolioId, DictCodeListExt2.划款方式_逐笔划款.getValue());
                    });
                }
                // 可用持仓 通道 提交日减少提前支取金额
                EtrSecPositionFlowDto etrSecPositionFlowDto1 = new EtrSecPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(etrSecPositionFlowDto1, etrSecPositionFlowDto);
                etrSecPositionFlowDto1.setSeqno(getSeqNo());
                持仓交易(etrPositionHoldDto, deal, etrSecPositionFlowDto1,
                        ArithmeticUtil.subtract( BigDecimal.ZERO, deal.getPrinAmt()),DictCodeListExt1.持仓类型_委托指令可用.getValue());
            }
        }
        else if (DictCodeList.交易方向_到期.getValue().equals(ps)) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                    stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                        // 可用现金 资金到账日增加到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                        etrCashPositionFlowDto1.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                                ArithmeticUtil.doAdd(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令可用.getValue(), portfolioId, DictCodeListExt2.划款方式_逐笔划款.getValue());
                        // 预增现金 资金到账日减少到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto2 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto2, etrCashPositionFlowDto);
                        etrCashPositionFlowDto2.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto2,
                                ArithmeticUtil.subtract(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令预增.getValue(), portfolioId, DictCodeListExt2.划款方式_逐笔划款.getValue());
                    });
                }
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                    stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                        // 预增现金 资金到账日增加到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                        etrCashPositionFlowDto1.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                            ArithmeticUtil.doAdd(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                            DictCodeListExt1.持仓类型_委托指令预增.getValue(), portfolioId, DictCodeListExt2.划款方式_逐笔划款.getValue());
                    });
                }
                // 可用持仓 通道 提交日削减至0
                EtrSecPositionFlowDto etrSecPositionFlowDto1 = new EtrSecPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(etrSecPositionFlowDto1, etrSecPositionFlowDto);
                etrSecPositionFlowDto1.setSeqno(getSeqNo());
                持仓交易(etrPositionHoldDto, deal, etrSecPositionFlowDto1,
                        ArithmeticUtil.subtract( BigDecimal.ZERO,deal.getPrinAmt()),DictCodeListExt1.持仓类型_委托指令可用.getValue());
            }
        }
    }

    private static void 直投(DictCodeList 持仓类型, EtrPositionHoldDto etrPositionHoldDto,
                           List<StlPayOrdersDto> stlPayOrdersDtos, TrdProductDeal deal, String ps, String entrustId, EtrSecPositionFlowDto etrSecPositionFlowDto, EtrCashPositionFlowDto etrCashPositionFlowDto, String portfolioId) {
        BigDecimal 交易金额=deal.getTradeAmt();
        if(stlPayOrdersDtos!=null){
            交易金额=stlPayOrdersDtos.get(0).getSettleAmt();}
        if (DictCodeList.交易方向_买入.getValue().equals(ps)) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                if (StringUtil.isEmpty(entrustId)) { // 无委托
                    // 可用持仓 交易日增加本金金额
                    持仓交易(etrPositionHoldDto, deal, etrSecPositionFlowDto,
                            ArithmeticUtil.doAdd( BigDecimal.ZERO,deal.getPrinAmt()), DictCodeListExt1.持仓类型_委托指令可用.getValue());
                } else { // 有委托
                    // 可用持仓 交易日增加本金金额
                    持仓交易(etrPositionHoldDto, deal, etrSecPositionFlowDto,
                            ArithmeticUtil.doAdd( BigDecimal.ZERO,deal.getPrinAmt()),DictCodeListExt1.持仓类型_委托指令可用.getValue());
                    // 有委托增加一笔利息现金的减少
                    有委托增加一笔利息现金的减少(etrPositionHoldDto, deal, etrCashPositionFlowDto, portfolioId);
                }
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                if (StringUtil.isEmpty(entrustId)) {
                    // 托管户 可用现金 交易日减少交易本金金额
                    现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto,
                            ArithmeticUtil.subtract(BigDecimal.ZERO, 交易金额), DictCodeListExt1.持仓类型_委托指令可用.getValue(),
                            portfolioId, null);
                }
            }
        }
        else if (DictCodeList.交易方向_卖出.getValue().equals(ps)) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                if (StringUtil.isEmpty(entrustId)) { // 无委托
                    // 可用现金 托管户 交易日增加交易金额
                    现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto,
                            交易金额, DictCodeListExt1.持仓类型_委托指令可用.getValue(),portfolioId,
                            null);
                }
                else { // 有委托
                    // 可用现金 托管户 交易日增加交易金额
                    现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto,
                            交易金额, DictCodeListExt1.持仓类型_委托指令可用.getValue(),portfolioId,
                            null);
                }
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                if (StringUtil.isEmpty(entrustId)) {
                    // 可用持仓 交易日减少本金金额
                    持仓交易(etrPositionHoldDto, deal, etrSecPositionFlowDto,
                            ArithmeticUtil.subtract(BigDecimal.ZERO, deal.getPrinAmt()), DictCodeListExt1.持仓类型_委托指令可用.getValue());
                }
            }
        }
        else if (DictCodeList.交易方向_收息.getValue().equals(ps)) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                    stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                        // 可用现金 资金到账日增加到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                        etrCashPositionFlowDto1.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                                ArithmeticUtil.doAdd(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令可用.getValue(),portfolioId, null);
                        // 预增现金 资金到账日减少到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto2 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto2, etrCashPositionFlowDto);
                        etrCashPositionFlowDto2.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto2,
                                ArithmeticUtil.subtract(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令预增.getValue(),portfolioId, null);
                    });
                }
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                    stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                        // 预增现金 资金到账日增加到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                        etrCashPositionFlowDto1.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                            ArithmeticUtil.doAdd(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                            DictCodeListExt1.持仓类型_委托指令预增.getValue(),portfolioId, null);
                    });
                }
            }
        }
        else if (DictCodeList.交易方向_还本.getValue().equals(ps)) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                    stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                        // 可用现金 资金到账日增加到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                        etrCashPositionFlowDto1.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                                ArithmeticUtil.doAdd(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令可用.getValue(),portfolioId, null);
                        // 预增现金 资金到账日减少到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto2 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto2, etrCashPositionFlowDto);
                        etrCashPositionFlowDto2.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto2,
                                ArithmeticUtil.subtract(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令预增.getValue(),portfolioId, null);
                    });
                }
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                    stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                        // 预增现金 资金到账日增加到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                        etrCashPositionFlowDto1.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                            ArithmeticUtil.doAdd(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                            DictCodeListExt1.持仓类型_委托指令预增.getValue(),portfolioId, null);
                    });
                }
                if (deal.getPrinAmt() != null && deal.getPrinAmt().compareTo(BigDecimal.ZERO) > 0) {
                    EtrSecPositionFlowDto etrSecPositionFlowDto1 = new EtrSecPositionFlowDto();
                    BeanUtil.copyPropertiesIgnoreNull(etrSecPositionFlowDto1, etrSecPositionFlowDto);
                    etrSecPositionFlowDto1.setSeqno(getSeqNo());
                    // 可用持仓 提交日减少提前支取金额
                    持仓交易(etrPositionHoldDto, deal, etrSecPositionFlowDto1,
                        ArithmeticUtil.subtract(BigDecimal.ZERO, deal.getPrinAmt()), DictCodeListExt1.持仓类型_委托指令可用.getValue());
                }
            }
        }
        else if (DictCodeList.交易方向_到期.getValue().equals(ps)) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                    stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                        // 可用现金 资金到账日增加到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                        etrCashPositionFlowDto1.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                                ArithmeticUtil.doAdd(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令可用.getValue(),portfolioId, null);
                        // 预增现金 资金到账日减少到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto2 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto2, etrCashPositionFlowDto);
                        etrCashPositionFlowDto2.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto2,
                                ArithmeticUtil.subtract(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                                DictCodeListExt1.持仓类型_委托指令预增.getValue(),portfolioId, null);
                    });
                }
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                    stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                        // 预增现金 资金到账日增加到账金额
                        EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                        etrCashPositionFlowDto1.setSeqno(getSeqNo());
                        现金交易(etrPositionHoldDto, deal, etrCashPositionFlowDto1,
                            ArithmeticUtil.doAdd(BigDecimal.ZERO,stlPayOrdersDto.getSettleAmt()),
                            DictCodeListExt1.持仓类型_委托指令预增.getValue(),portfolioId, null);
                    });
                }
                if (deal.getPrinAmt() != null && deal.getPrinAmt().compareTo(BigDecimal.ZERO) > 0) {
                    // 可用持仓 提交日削减至0
                    EtrSecPositionFlowDto etrSecPositionFlowDto1 = new EtrSecPositionFlowDto();
                    BeanUtil.copyPropertiesIgnoreNull(etrSecPositionFlowDto1, etrSecPositionFlowDto);
                    etrSecPositionFlowDto1.setSeqno(getSeqNo());
                    持仓交易(etrPositionHoldDto, deal, etrSecPositionFlowDto1,
                        ArithmeticUtil.subtract(BigDecimal.ZERO, deal.getPrinAmt()), DictCodeListExt1.持仓类型_委托指令可用.getValue());
                }
            }
        }
    }

    private static void 现金交易(EtrPositionHoldDto etrPositionHoldDto, TrdProductDeal deal, EtrCashPositionFlowDto etrCashPositionFlowDto,
                             BigDecimal subtract,String type,String portfolioId, String chlType) {
        String secAcctId = deal.getSecManageAcctId();
        secAcctId = EtrCommonCashPositionDealService.select组合托管户(portfolioId, deal.getCcy(),
                DictCodeListExt2.资金账户类型_托管户.getValue());
        if (StringUtil.isEmpty(secAcctId) && StringUtil.isEmpty(deal.getChlId())) {
            secAcctId = "999999";
        }
        else if (StringUtil.isNotEmpty(deal.getChlId())) {
            if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_一次性划款.getValue())) {
                secAcctId = EtrCommonCashPositionDealService.select通道资金账户(deal.getChlId());
            }
        }
        etrCashPositionFlowDto.setAcctId(secAcctId);
        etrCashPositionFlowDto.setOrderId(deal.getTradeId());
        if(subtract!=null){
            etrCashPositionFlowDto.setAmount(subtract);
        }else{
            etrCashPositionFlowDto.setAmount(BigDecimal.ZERO);
        }
        etrCashPositionFlowDto.setHodingType(type);
        if(StringUtil.isNotEmpty(chlType)) {
        	etrCashPositionFlowDto.setChlFinprodId(deal.getChlId());
        }
        if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue())) {
            etrCashPositionFlowDto.setChlFinprodId(null);
        }
        //因为数据库是必填字段，暂存业务编码
        etrCashPositionFlowDto.setOffsetId(deal.getTradeId());
        etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
    }

    private static void 持仓交易(EtrPositionHoldDto etrPositionHoldDto, TrdProductDeal deal, EtrSecPositionFlowDto etrSecPositionFlowDto,BigDecimal subtract,String type) {
        if(subtract!=null){
            etrSecPositionFlowDto.setAmount(subtract);
        }else{
            etrSecPositionFlowDto.setAmount(BigDecimal.ZERO);
        }
        etrSecPositionFlowDto.setHodingType(type);
        etrSecPositionFlowDto.setFinprodId(deal.getFinprodId());
        etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
    }


    public static void deal计划利率型CEntrustStart(EntrustCommonDto entrustCommonDto, EtrPositionHoldDto etrPositionHoldDto) {
        if (entrustCommonDto != null) {
            EtrEntrust etrEntrust = entrustCommonDto.getEtrEntrust();
            // 交易方向
            String ps = etrEntrust.getPs();
            if (DictCodeList.交易方向_买入.getValue().equals(ps)) {
                // 委托生成的现金流水
                EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDtoFromEtr(
                        entrustCommonDto);
                String acctId;
                if (StringUtil.isNotEmpty(etrEntrust.getChlId()) && DictCodeListExt2.划款方式_一次性划款.getValue().equals(
                       EtrCommonCashPositionDealService.queryChlType(etrEntrust.getChlId()))) {
                    acctId = EtrCommonCashPositionDealService.select通道资金账户(etrEntrust.getChlId());
                }
                else {
                    acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
                            DictCodeListExt1.资金账户类型_产品托管户.getValue());
                    etrCashPositionFlowDto.setChlFinprodId(null);
                }
                // 减少委托本金金额
                //因为是必填项，暂时存储委托代码
                etrCashPositionFlowDto.setOffsetId(entrustCommonDto.getEtrEntrust().getEntrustId());
                etrCashPositionFlowDto.setAmount(etrEntrust.getEntrustAmt().negate());
                etrCashPositionFlowDto.setAcctId(acctId);
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
            }
            else if (DictCodeList.交易方向_卖出.getValue().equals(ps)) {
                // 委托生成的持仓流水
                // 减少本金金额
                EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDtoFromEtr(
                        entrustCommonDto);
                etrSecPositionFlowDto.setAmount(etrEntrust.getEntrustNum().negate());
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            }
        }
    }

    public static void deal计划利率型CEntrustClose(EntrustCommonDto entrustCommonDto, EtrPositionHoldDto etrPositionHoldDto) {
        EtrEntrust etrEntrust = entrustCommonDto.getEtrEntrust();
        // 交易方向
        String ps = etrEntrust.getPs();
        BigDecimal etrFinish = EtrCommonCashPositionDealService.getEtrFinishAmt(etrEntrust.getEntrustId());
        if (DictCodeList.交易方向_买入.getValue().equals(ps)) {
            // 可用现金 关闭日增加剩余委托本金金额
            EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDtoFromEtr(
                    entrustCommonDto);
            String acctId;
            if (StringUtil.isNotEmpty(etrEntrust.getChlId()) && DictCodeListExt2.划款方式_一次性划款.getValue().equals(
                    EtrCommonCashPositionDealService.queryChlType(etrEntrust.getChlId()))) {
                acctId = EtrCommonCashPositionDealService.select通道资金账户(etrEntrust.getChlId());
            }
            else {
                acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
                        DictCodeListExt2.资金账户类型_托管户.getValue());
                etrCashPositionFlowDto.setChlFinprodId(null);
            }

            // 关闭日增加剩余委托本金金额
            etrCashPositionFlowDto.setAmount(ArithmeticUtil.subtract(etrEntrust.getEntrustAmt(), etrFinish));
            etrCashPositionFlowDto.setAcctId(acctId);
            etrCashPositionFlowDto.setOffsetId(etrEntrust.getEntrustId());
            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
        }
        else if (DictCodeList.交易方向_卖出.getValue().equals(ps)) {
            EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDtoFromEtr(
                    entrustCommonDto);
            // 可用持仓 关闭日增加剩余委托本金金额
            etrSecPositionFlowDto.setAmount(ArithmeticUtil.subtract(etrEntrust.getEntrustNum(), etrFinish));
            etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
        }
    }

}


