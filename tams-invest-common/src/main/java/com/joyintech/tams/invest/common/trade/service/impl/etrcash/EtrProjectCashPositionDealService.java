package com.joyintech.tams.invest.common.trade.service.impl.etrcash;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.etr.EtrCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrPositionHoldDto;
import com.joyintech.tams.invest.common.dto.etr.EtrSecPositionFlowDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService.getSeqNo;

/**
 * @program: 2.1.0
 * @description:
 * @author: 强成西
 * @create: 2019-10-12 15:07
 **/
@Service
public class EtrProjectCashPositionDealService extends EtrCashPositionBaseService {

    /**
     * 项目类资产（持仓以本金金额记）
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    public static void deal项目类资产CashPosition(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                             EtrPositionHoldDto etrPositionHoldDto) {

        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();
        TrdProductDealAdd dealAdd = finProductTradeDto.getTrdProductDealAdd();
        List<StlPayOrdersDto> stlPayOrdersDtos = finProductTradeDto.getStlPayOrdersList();
        // 通道代码
        String chlId = deal.getChlId();
        // 交易方向
        String ps = deal.getPs();
        // 通道性质
        String chlType = StringUtil.isEmpty(chlId) ? null : etrCommonCashPositionDealService.queryChlType(chlId);

        // // 委托代码
        // String entrustId = finProductTradeDto.getEntrustId();
        // // 证券账户
        // String secManageAcctId = finProductTradeDto.getTrdProductDeal().getSecManageAcctId();
        // // 资产代码
        // String assetCode = finProductTradeDto.getTrdProductDeal().getFinprodId();
        // // 交易日期
        // Date tradeDate = finProductTradeDto.getTrdProductDeal().getTradeDate();

        BigDecimal 交易券面总额 = finProductTradeDto.getTrdProductDeal().getPrinAmt();
        BigDecimal 交易金额 = finProductTradeDto.getTrdProductDeal().getTradeAmt();
        if(finProductTradeDto.getStlPayOrdersList()!=null &&finProductTradeDto.getStlPayOrdersList().size()>0) {
            交易金额 = finProductTradeDto.getStlPayOrdersList().get(0).getSettleAmt();//划款指令的现金流
        }
        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();

        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);
        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);
        etrCashPositionFlowDto.setOrderId(deal.getTradeId());
        // 无通道
        if (StringUtil.isEmpty(chlId)) {
            String acctId = EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(), deal.getCcy(),
                    DictCodeListExt2.资金账户类型_托管户.getValue());
            etrCashPositionFlowDto.setChlFinprodId(null);
            // 委托直投买入（包括外部买入、内转买入）
            if (StringUtil.equals(ps, BussinessDictCodeList.交易方向_买入.getValue())) {
                // 交易生效
                if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
                    // 持仓流水dto处理
                    // 交割日增加本金金额
                    etrSecPositionFlowDto.setAmount(交易券面总额);
                    // 添加持仓流水到目标dto
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                    if (StringUtil.isNotEmpty(entrustId)) { // 交易生效-有委托，冲销委托本金，减少实付金额
                        // 现金流水dto处理
                        // 交易日减少交易本金金额
                        etrCashPositionFlowDto.setAmount(交易券面总额);
                        etrCashPositionFlowDto.setAcctId(acctId);
                        etrCashPositionFlowDto.setOffsetFlag("01");
                        etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);

                        EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                        BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                        etrCashPositionFlowDto1.setSeqno(getSeqNo());
                        etrCashPositionFlowDto1.setAmount(交易金额.negate());
                        etrCashPositionFlowDto1.setOffsetFlag(null);
                        etrCashPositionFlowDto1.setOffsetId(null);
                        etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto1);
                    }
                }
                if (StringUtil.isEmpty(entrustId)) { // 交易提交-无委托
                    // 交易提交
                    if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
                        // 现金流水dto处理
                        // 交易日减少交易本金金额
                        etrCashPositionFlowDto.setAmount(交易金额.negate());
                        etrCashPositionFlowDto.setAcctId(acctId);
                        etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                    }
                }
            }
            // 委托直投卖出、转出
            else if (StringUtil.equals(ps, BussinessDictCodeList.交易方向_卖出.getValue())) {
                // 交易生效
                if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
                    // 现金流水dto处理
                    // 交割日增加交易金额
                    etrCashPositionFlowDto.setAmount(交易金额);
                    etrCashPositionFlowDto.setAcctId(acctId);
                    etrCashPositionFlowDto.setCdate(deal.getSettleDate());
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                }
                // 交易提交
                if (StringUtil.isEmpty(entrustId)) { // 交易提交-无委托
                    // 交易提交
                    if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
                        // 持仓流水dto处理
                        // 交易日减少本金金额
                        etrSecPositionFlowDto.setAmount(交易券面总额.negate());
                        // 添加持仓流水到目标dto
                        etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                    }
                }
            }
            // 中间付息(直投)
            else if (StringUtil.equals(ps, BussinessDictCodeList.交易方向_收息.getValue())) {
                // 交易生效
                if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
                    // 资金到账日增加到账金额
                    deal(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                }
                // 交易提交
                if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
                    if (StringUtil.isEmpty(entrustId)) { // 无委托
                        预增现金处理(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                    }
                }
            }
            // 中间还本（直投）
            else if (StringUtil.equals(ps, BussinessDictCodeList.交易方向_还本.getValue())) {
                // 交易生效
                if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
                    // 资金到账日增加到账金额
                    deal(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                }
                // 交易提交
                if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
                    if (StringUtil.isEmpty(entrustId)) { // 无委托
                        // 资金到账日增加到账金额
                        预增现金处理(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                        // 提交日减少还本金额
                        if (deal.getPrinAmt() != null && deal.getPrinAmt().compareTo(BigDecimal.ZERO) > 0) {
                            etrSecPositionFlowDto.setAmount(deal.getPrinAmt().negate());
                            etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                        }
                    }
                }
            }
            // 到期（直投）
            else if (StringUtil.equals(ps, BussinessDictCodeList.交易方向_到期.getValue())) {
                // 交易生效
                if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
                    deal(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                }
                // 交易提交
                if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
                    if (StringUtil.isEmpty(entrustId)) { // 无委托
                        // 可用持仓 提交日削减至0
                        if (deal.getPrinAmt() != null && deal.getPrinAmt().compareTo(BigDecimal.ZERO) > 0) {
                            etrSecPositionFlowDto.setAmount(deal.getPrinAmt().negate());
                            etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                        }
                        预增现金处理(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                    }
                }
            }
        }
        else {
            // 逐笔划款通道
            if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue())) {
                String acctId = EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(), deal.getCcy(),
                        DictCodeListExt2.资金账户类型_托管户.getValue());
                etrCashPositionFlowDto.setChlFinprodId(null);
                etrCashPositionFlowDto.setAcctId(acctId);
                // 买入、转入
                if (StringUtil.equals(ps, BussinessDictCodeList.交易方向_买入.getValue())) {
                    // 交易生效
                    if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
                        // 持仓流水dto处理
                        // 交割日增加本金金额
                        etrSecPositionFlowDto.setAmount(交易券面总额);
                        // 添加持仓流水到目标dto
                        etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                        if (StringUtil.isNotEmpty(entrustId)) { // 交易生效-有委托，冲销委托本金，减少实付金额
                            // 现金流水dto处理
                            // 交易日减少交易本金金额
                            etrCashPositionFlowDto.setAmount(交易券面总额);
                            etrCashPositionFlowDto.setOffsetFlag("01");
                            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);

                            EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                            BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                            etrCashPositionFlowDto1.setSeqno(getSeqNo());
                            etrCashPositionFlowDto1.setAmount(交易金额.negate());
                            etrCashPositionFlowDto.setOffsetFlag(null);
                            etrCashPositionFlowDto1.setOffsetId(null);
                            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto1);
                        }
                    }
                    // 交易提交
                    if (StringUtil.isEmpty(entrustId)) { // 交易提交-无委托
                        // 交易提交
                        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
                            // 现金流水dto处理
                            // 交易日减少交易本金金额
                            etrCashPositionFlowDto.setAmount(交易金额.negate());
                            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                        }
                    }
                }
                // 卖出、转出
                else if (StringUtil.equals(ps, BussinessDictCodeList.交易方向_卖出.getValue())) {
                    // 交易生效
                    if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
                        // 现金流水dto处理
                        // 交割日增加交易金额
                        etrCashPositionFlowDto.setAmount(交易金额);
                        etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                            deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                        etrCashPositionFlowDto.setCdate(deal.getSettleDate());
                        etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                    }
                    if (StringUtil.isEmpty(entrustId)) { // 交易提交-无委托
                        // 交易提交
                        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
                            // 持仓流水dto处理
                            // 交易日减少本金金额
                            etrSecPositionFlowDto.setAmount(交易券面总额.negate());
                            // 添加持仓流水到目标dto
                            etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                        }
                    }
                }
                // 中间付息(逐笔划款通道)
                else if (StringUtil.equals(ps, BussinessDictCodeList.交易方向_收息.getValue())) {
                    // 交易生效
                    if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
                        // 资金到账日增加到账金额
                        deal(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                    }
                    // 交易提交
                    if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
                        if (StringUtil.isEmpty(entrustId)) { // 无委托
                            预增现金处理(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                        }
                    }
                }
                // 中间还本(逐笔划款通道)
                else if (StringUtil.equals(ps, BussinessDictCodeList.交易方向_还本.getValue())) {
                    // 交易生效
                    if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
                        deal(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                    }
                    // 交易提交
                    if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
                        if (StringUtil.isEmpty(entrustId)) { // 无委托
                            // 提交日减少还本金额
                            if (deal.getPrinAmt() != null && deal.getPrinAmt().compareTo(BigDecimal.ZERO) > 0) {
                                etrSecPositionFlowDto.setAmount(deal.getPrinAmt().negate());
                                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                            }
                            预增现金处理(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                        }
                    }
                }
                // 到期(逐笔划款通道)
                else if (StringUtil.equals(ps, BussinessDictCodeList.交易方向_到期.getValue())) {
                    // 交易生效
                    if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
                        deal(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                    }
                    // 交易提交
                    if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
                        if (StringUtil.isEmpty(entrustId)) { // 无委托
                            // 可用持仓 提交日削减至0
                            if (deal.getPrinAmt() != null && deal.getPrinAmt().compareTo(BigDecimal.ZERO) > 0) {
                                etrSecPositionFlowDto.setAmount(deal.getPrinAmt().negate());
                                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                            }
                            预增现金处理(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                        }
                    }
                }
            }
            // 一次性划款通道
            else {
                String acctId = EtrCommonCashPositionDealService.select通道资金账户(chlId);
                etrCashPositionFlowDto.setAcctId(acctId);
                // 买入、转入
                if (StringUtil.equals(ps, BussinessDictCodeList.交易方向_买入.getValue())) {
                    // 交易生效
                    if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
                        // 持仓流水dto处理
                        // 交割日增加本金金额
                        etrSecPositionFlowDto.setAmount(交易券面总额);
                        // 添加持仓流水到目标dto
                        etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                        if (StringUtil.isNotEmpty(entrustId)) { // 交易生效-有委托，冲销委托本金，减少实付金额
                            // 现金流水dto处理
                            // 交易日减少交易本金金额
                            etrCashPositionFlowDto.setAmount(交易券面总额);
                            etrCashPositionFlowDto.setOffsetFlag("01");
                            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);

                            EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                            BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                            etrCashPositionFlowDto1.setSeqno(getSeqNo());
                            etrCashPositionFlowDto1.setAmount(交易金额.negate());
                            etrCashPositionFlowDto1.setOffsetFlag(null);
                            etrCashPositionFlowDto1.setOffsetId(null);
                            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto1);
                        }
                    }
                    // 交易提交
                    if (StringUtil.isEmpty(entrustId)) { // 交易提交-无委托
                        // 交易提交
                        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
                            // 现金流水dto处理
                            // 交易日减少交易本金金额
                            etrCashPositionFlowDto.setAmount(交易金额.negate());
                            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                        }
                    }
                }
                // 卖出、转出
                else if (StringUtil.equals(ps, BussinessDictCodeList.交易方向_卖出.getValue())) {
                    // 交易生效
                    if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
                        // 现金流水dto处理
                        // 交割日增加交易金额
                        etrCashPositionFlowDto.setAmount(交易金额);
                        etrCashPositionFlowDto.setAcctId(acctId);
                        etrCashPositionFlowDto.setCdate(deal.getSettleDate());
                        etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                    }
                    if (StringUtil.isEmpty(entrustId)) { // 交易提交-无委托
                        // 交易提交
                        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
                            // 持仓流水dto处理
                            // 交易日减少本金金额
                            etrSecPositionFlowDto.setAmount(交易券面总额.negate());
                            // 添加持仓流水到目标dto
                            etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                        }
                    }
                }
                // 中间付息
                else if (StringUtil.equals(ps, BussinessDictCodeList.交易方向_收息.getValue())) {
                    // 交易生效
                    if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
                        deal(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                    }
                    // 交易提交
                    if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
                        if (StringUtil.isEmpty(entrustId)) { // 无委托
                            EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                            BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                            etrCashPositionFlowDto1.setSeqno(getSeqNo());
                            预增现金处理(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto1, acctId);
                        }
                    }
                }
                // 中间还本
                else if (StringUtil.equals(ps, BussinessDictCodeList.交易方向_还本.getValue())) {
                    // 交易生效
                    if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
                        deal(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                    }
                    // 交易提交
                    if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
                        if (StringUtil.isEmpty(entrustId)) { // 无委托
                            // 提交日减少还本金额
                            if (deal.getPrinAmt() != null && deal.getPrinAmt().compareTo(BigDecimal.ZERO) > 0) {
                                etrSecPositionFlowDto.setAmount(deal.getPrinAmt().negate());
                                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                            }
                            预增现金处理(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                        }
                    }
                }
                // 到期
                else if (StringUtil.equals(ps, BussinessDictCodeList.交易方向_到期.getValue())) {
                    // 交易生效
                    if (DictCodeList.持仓类型_实际.equals(持仓类型)) {
                        deal(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                    }
                    // 交易提交
                    if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) {
                        if (StringUtil.isEmpty(entrustId)) { // 无委托
                            // 可用持仓 提交日削减至0
                            if (deal.getPrinAmt() != null && deal.getPrinAmt().compareTo(BigDecimal.ZERO) > 0) {
                                etrSecPositionFlowDto.setAmount(deal.getPrinAmt().negate());
                                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                            }
    
                            预增现金处理(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                        }
                    }
                }
            }
        }
    }

    private static void deal(EtrPositionHoldDto etrPositionHoldDto, List<StlPayOrdersDto> stlPayOrdersDtos,
                             EtrCashPositionFlowDto etrCashPositionFlowDto, String acctId) {
        if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
            stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                etrCashPositionFlowDto.setOrderId(stlPayOrdersDto.getOrderId());
                // 可用现金 资金到账日增加到账金额
                EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                etrCashPositionFlowDto1.setAmount(stlPayOrdersDto.getSettleAmt());
                etrCashPositionFlowDto1.setAcctId(stlPayOrdersDto.getMyAcctNo());
                etrCashPositionFlowDto1.setCdate(stlPayOrdersDto.getSettleDate());
                etrCashPositionFlowDto1.setSeqno(getSeqNo());
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto1);

                // 预增现金 资金到账日减少到账金额
                EtrCashPositionFlowDto etrCashPositionFlowDto2 = new EtrCashPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto2, etrCashPositionFlowDto1);
                etrCashPositionFlowDto2.setAmount(stlPayOrdersDto.getSettleAmt().negate());
                etrCashPositionFlowDto2.setAcctId(stlPayOrdersDto.getMyAcctNo());
                etrCashPositionFlowDto2.setCdate(stlPayOrdersDto.getSettleDate());
                etrCashPositionFlowDto2.setHodingType(DictCodeListExt1.持仓类型_委托指令预增.getValue());
                etrCashPositionFlowDto2.setSeqno(getSeqNo());
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto2);
            });
        }
    }

    private static void 预增现金处理(EtrPositionHoldDto etrPositionHoldDto, List<StlPayOrdersDto> stlPayOrdersDtos,
                               EtrCashPositionFlowDto etrCashPositionFlowDto, String acctId) {
        if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
            stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                // 预增现金 资金到账日增加到账金额
                etrCashPositionFlowDto.setOrderId(stlPayOrdersDto.getOrderId());
                EtrCashPositionFlowDto etrCashPositionFlowDto2 = new EtrCashPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto2, etrCashPositionFlowDto);
                etrCashPositionFlowDto2.setAmount(stlPayOrdersDto.getSettleAmt());
                etrCashPositionFlowDto2.setAcctId(stlPayOrdersDto.getMyAcctNo());
                etrCashPositionFlowDto2.setCdate(stlPayOrdersDto.getSettleDate());
                etrCashPositionFlowDto2.setHodingType(DictCodeListExt1.持仓类型_委托指令预增.getValue());
                etrCashPositionFlowDto2.setSeqno(getSeqNo());
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto2);
            });
        }
    }

    public static void deal项目类资产EntrustStart(EntrustCommonDto entrustCommonDto, EtrPositionHoldDto etrPositionHoldDto) {
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
                    etrCommonCashPositionDealService.queryChlType(etrEntrust.getChlId()))) {
                    acctId = EtrCommonCashPositionDealService.select通道资金账户(etrEntrust.getChlId());
                }
                else {
                    acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
                        DictCodeListExt2.资金账户类型_托管户.getValue());
                    etrCashPositionFlowDto.setChlFinprodId(null);
                }
                // 减少委托本金金额
                etrCashPositionFlowDto.setAmount(etrEntrust.getEntrustAmt().negate());
                etrCashPositionFlowDto.setAcctId(acctId);
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
            }
            else if (DictCodeList.交易方向_卖出.getValue().equals(ps)) {
                // 委托生成的持仓流水
                // 减少本金金额
                EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDtoFromEtr(
                    entrustCommonDto);
                etrSecPositionFlowDto.setAmount(etrEntrust.getEntrustAmt().negate());
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            }
        }
    }

    public static void deal项目类资产EntrustClose(EntrustCommonDto entrustCommonDto, EtrPositionHoldDto etrPositionHoldDto) {
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
                     etrCommonCashPositionDealService.queryChlType(etrEntrust.getChlId()))) {
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
            etrSecPositionFlowDto.setAmount(ArithmeticUtil.subtract(etrEntrust.getEntrustAmt(), etrFinish));
            etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
        }
    }

}
