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
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.etr.EtrCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrPositionHoldDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService.getSeqNo;

/**
 * @program: 2.1.0
 * @description:拆借指令头寸生成类
 * @author: 强成西
 * @create: 2019-10-12 15:06
 **/
@Service
public class EtrLendCashPositionDealService extends EtrCashPositionBaseService {
    
    /**
     * 
     * 生成拆借交易持仓信息
     * <功能详细描述>
     * @param dto 交易信息
     * @param type 持仓类型
     * @return 持仓信息
     * @see
     * @since 1.0
     */
    public static void genLendDealCashPosition(BaseTradeProductDealDto dto, DictCodeList type, EtrPositionHoldDto etrPositionHoldDto) {
        TrdProductDeal deal = dto.getTrdProductDeal();
        TrdProductDealAdd dealAdd = dto.getTrdProductDealAdd();
        // 指令信息
        List<StlPayOrdersDto> stlPayOrdersDtos = dto.getStlPayOrdersList();
        // 托管账户
        String acctId = EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
            deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue());
        if (StringUtil.isEmpty(deal.getChlId())) { // 直投
            if (DictCodeList.交易方向_买入.getValue().equals(deal.getPs())) { // 买入
                // 拆入
                if (DictCodeListExt1.金融产品类型_投管用_拆入.getValue().equals(deal.getFinprodType2())) {
                    if (DictCodeList.持仓类型_实际.equals(type)) { // 有效
                        // 交易生成的现金流水
                        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
                                dto);
                        // 可用现金 增加交易本金金额
                        etrCashPositionFlowDto.setAmount(deal.getPrinAmt());
                        etrCashPositionFlowDto.setAcctId(acctId);
                        etrCashPositionFlowDto.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
                        etrCashPositionFlowDto.setOrderId(deal.getTradeId());
                        etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);

                        if (StringUtil.isNotEmpty(dto.getEntrustId())) { // 有委托
                            // 预增现金 减少交易本金金额
                            EtrCashPositionFlowDto etrCashPositionFlowDto2 = new EtrCashPositionFlowDto();
                            BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto2, etrCashPositionFlowDto);
                            etrCashPositionFlowDto2.setAmount(ArithmeticUtil.multiply(deal.getPrinAmt(), new BigDecimal(-1)));
                            etrCashPositionFlowDto2.setAcctId(acctId);
                            etrCashPositionFlowDto2.setHodingType(DictCodeListExt1.持仓类型_委托指令预增.getValue());
                            etrCashPositionFlowDto2.setOrderId(deal.getTradeId());
                            etrCashPositionFlowDto2.setSeqno(getSeqNo());
                            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto2);
                        }
                    } 
                } else if (DictCodeListExt1.金融产品类型_投管用_拆出.getValue().equals(deal.getFinprodType2())) { //拆出
                    if (DictCodeList.持仓类型_可用.equals(type) || etrPositionHoldDto.isNeedNegative()) { // 提交
                        if (StringUtil.isEmpty(dto.getEntrustId())) { // 无委托
                            // 交易生成的现金流水
                            EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
                                    dto);
                            // 可用现金减少交易本金金额
                            etrCashPositionFlowDto.setAmount(ArithmeticUtil.multiply(deal.getPrinAmt(), new BigDecimal(-1)));
                            etrCashPositionFlowDto.setAcctId(acctId);
                            etrCashPositionFlowDto.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
                            etrCashPositionFlowDto.setOrderId(deal.getTradeId());
                            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                        }
                    }
                }

            } else if (DictCodeList.交易方向_到期.getValue().equals(deal.getPs())) {
                // 拆入
                if (DictCodeListExt1.金融产品类型_投管用_拆入.getValue().equals(deal.getFinprodType2())) {
                    if (DictCodeList.持仓类型_可用.equals(type) || etrPositionHoldDto.isNeedNegative()) { // 提交
                        if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                            stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                                // 交易生成的现金流水
                                EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
                                        dto);
                                // 可用现金 到期日减少到账金额
                                etrCashPositionFlowDto.setAmount(ArithmeticUtil.multiply(stlPayOrdersDto.getSettleAmt(), new BigDecimal(-1)));
                                etrCashPositionFlowDto.setAcctId(stlPayOrdersDto.getMyAcctNo());
                                etrCashPositionFlowDto.setCdate(deal.getMdate());
                                etrCashPositionFlowDto.setOrderId(stlPayOrdersDto.getOrderId());
                                etrCashPositionFlowDto.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
                                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                            });
                        }
                    }
                } else if (DictCodeListExt1.金融产品类型_投管用_拆出.getValue().equals(deal.getFinprodType2())) { //拆出
                    if (DictCodeList.持仓类型_可用.equals(type) || etrPositionHoldDto.isNeedNegative()) { // 提交
                        if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                            stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                                EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
                                        dto);
                                // 预增现金 到期日增加实收本息和
                                etrCashPositionFlowDto.setAmount(stlPayOrdersDto.getSettleAmt());
                                etrCashPositionFlowDto.setAcctId(stlPayOrdersDto.getMyAcctNo());
                                etrCashPositionFlowDto.setCdate(deal.getMdate());
                                etrCashPositionFlowDto.setOrderId(stlPayOrdersDto.getOrderId());
                                etrCashPositionFlowDto.setHodingType(DictCodeListExt1.持仓类型_委托指令预增.getValue());
                                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                            });
                        }
                    }
                    if (DictCodeList.持仓类型_实际.equals(type)) { // 有效
                        if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                            stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                                EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
                                        dto);
                                // 可用现金 到期日增加实收本息和
                                etrCashPositionFlowDto.setAmount(stlPayOrdersDto.getSettleAmt());
                                etrCashPositionFlowDto.setAcctId(stlPayOrdersDto.getMyAcctNo());
                                etrCashPositionFlowDto.setCdate(deal.getMdate());
                                etrCashPositionFlowDto.setOrderId(stlPayOrdersDto.getOrderId());
                                etrCashPositionFlowDto.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
                                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);

                                // 预增现金 到期日减少实收本息和
                                EtrCashPositionFlowDto etrCashPositionFlowDto2 = new EtrCashPositionFlowDto();
                                BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto2, etrCashPositionFlowDto);
                                etrCashPositionFlowDto2.setAmount(ArithmeticUtil.multiply(stlPayOrdersDto.getSettleAmt(), new BigDecimal(-1)));
                                etrCashPositionFlowDto2.setAcctId(stlPayOrdersDto.getMyAcctNo());
                                etrCashPositionFlowDto2.setCdate(deal.getMdate());
                                etrCashPositionFlowDto2.setOrderId(stlPayOrdersDto.getOrderId());
                                etrCashPositionFlowDto2.setHodingType(DictCodeListExt1.持仓类型_委托指令预增.getValue());
                                etrCashPositionFlowDto2.setSeqno(getSeqNo());
                                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto2);
                            });
                        }
                    }
                }
            }
        }
    }
    
    /**
     * 
     * 生成拆借委托开始持仓信息
     * <功能详细描述>
     * @param dto 交易信息
     * @return 持仓信息
     * @see
     * @since 1.0
     */
    public static void genLendEtrStartCashPosition(EntrustCommonDto dto, EtrPositionHoldDto etrPositionHoldDto) {
        EtrEntrust etrEntrust = dto.getEtrEntrust();
        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDtoFromEtr(
            dto);
        // 托管户
        String acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
            DictCodeListExt2.资金账户类型_托管户.getValue());
        if (StringUtil.isEmpty(etrEntrust.getChlId())) { // 直投
            if (DictCodeListExt1.金融产品类型_投管用_拆入.getValue().equals(etrEntrust.getFinprodType2())) { // 拆入
                // 预增现金 开始日增加本金金额
                etrCashPositionFlowDto.setHodingType(DictCodeListExt1.持仓类型_委托指令预增.getValue());
                etrCashPositionFlowDto.setAmount(etrEntrust.getEntrustAmt());
            } else if (DictCodeListExt1.金融产品类型_投管用_拆出.getValue().equals(etrEntrust.getFinprodType2())) {
                // 可用现金 开始日减少本金金额
                etrCashPositionFlowDto.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
                etrCashPositionFlowDto.setAmount(ArithmeticUtil.multiply(etrEntrust.getEntrustAmt(), new BigDecimal(-1)));
            }

            etrCashPositionFlowDto.setAcctId(acctId);
            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
        }
    }
    
    /**
     * 
     * 生成拆借委托关闭持仓信息
     * <功能详细描述>
     * @param dto 交易信息
     * @return 持仓信息
     * @see
     * @since 1.0
     */
    public static void genLendEtrCloseCashPosition(EntrustCommonDto dto, EtrPositionHoldDto etrPositionHoldDto) {
        EtrEntrust etrEntrust = dto.getEtrEntrust();
        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDtoFromEtr(
            dto);
        // 托管户
        String acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
            DictCodeListExt2.资金账户类型_托管户.getValue());
        BigDecimal etrFinish = EtrCommonCashPositionDealService.getEtrFinishAmt(etrEntrust.getEntrustId());
        if (StringUtil.isEmpty(etrEntrust.getChlId())) { // 直投
            if (DictCodeListExt1.金融产品类型_投管用_拆入.getValue().equals(etrEntrust.getFinprodType2())) { // 拆入
                // 预增现金 开始日减少本金金额
                etrCashPositionFlowDto.setHodingType(DictCodeListExt1.持仓类型_委托指令预增.getValue());
                etrCashPositionFlowDto.setAmount(ArithmeticUtil.multiply(ArithmeticUtil.subtract(etrEntrust.getEntrustAmt(), etrFinish), new BigDecimal(-1)));
            } else if (DictCodeListExt1.金融产品类型_投管用_拆出.getValue().equals(etrEntrust.getFinprodType2())) {
                // 可用现金 开始日减少本金金额
                etrCashPositionFlowDto.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
                etrCashPositionFlowDto.setAmount(ArithmeticUtil.subtract(etrEntrust.getEntrustAmt(), etrFinish));
            }
            etrCashPositionFlowDto.setAcctId(acctId);
            etrCashPositionFlowDto.setOffsetId(etrEntrust.getEntrustId());
            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
        }
    }
}
