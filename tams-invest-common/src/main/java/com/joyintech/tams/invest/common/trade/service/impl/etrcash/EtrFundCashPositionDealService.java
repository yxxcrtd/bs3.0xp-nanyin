package com.joyintech.tams.invest.common.trade.service.impl.etrcash;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
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
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService.getSeqNo;

/**
 * @program: 2.1.0
 * @description:基金：指令头寸更新
 * @author: 强成西
 * @create: 2019-10-12 15:07
 **/
@Service
public class EtrFundCashPositionDealService extends EtrCashPositionBaseService {

    /**
     * 基金（持仓以份额记）
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    public static void deal基金CashPosition(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                          EtrPositionHoldDto etrPositionHoldDto) {
        // 交易方向
        String ps = finProductTradeDto.getTrdProductDeal().getPs();

        if (StringUtil.equals(ps, DictCodeListExt2.交易方向_认购.getValue())) {
            基金_认购(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        if (StringUtil.equals(ps, DictCodeList.交易方向_认购确认.getValue())) {
            基金_认购确认(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(ps, DictCodeList.交易方向_申购.getValue())) {
            基金_申购(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(ps, DictCodeList.交易方向_申购确认.getValue())) {
            基金_申购确认(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())) {
            基金_买入(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(ps, DictCodeList.交易方向_卖出.getValue())) {
            基金_卖出(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(ps, DictCodeList.交易方向_赎回.getValue())) {
            基金_赎回(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(ps, DictCodeList.交易方向_赎回确认.getValue())) {
            基金_赎回确认(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(ps, DictCodeList.交易方向_现金分红.getValue())) {
            基金_现金分红(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(ps, DictCodeList.交易方向_红利再投.getValue())) {
            基金_红利再投(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(ps, DictCodeList.交易方向_份额强增.getValue())) {
            基金_份额折算_强增(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(ps, DictCodeList.交易方向_份额强减.getValue())) {
            基金_份额折算_强减(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
    }

    /**
     * 基金（持仓以份额记） 阶段：委托开始
     * 
     * @param entrustCommonDto
     * @param etrPositionHoldDto
     */
    public static void deal基金EntrustStart(EntrustCommonDto entrustCommonDto, EtrPositionHoldDto etrPositionHoldDto) {
        if (entrustCommonDto != null) {
            // 委托信息
            EtrEntrust etrEntrust = entrustCommonDto.getEtrEntrust();

            // 委托信息附加
            EtrEntrustAdd etrEntrustAdd = entrustCommonDto.getEtrEntrustAdd();

            // 交易方向
            String ps = etrEntrust.getPs();

            // 交易场所
            String tradePlace = entrustCommonDto.getEtrEntrust().getTradeMarket();

            // 通道代码
            String chlId = etrEntrust.getChlId();
            
            //证券托管户
            String secManageAcctId= etrEntrustAdd.getSecAcctId();
            
            //币种
            String ccy = etrEntrust.getCcy();
            
            //交易对手
            String counterId = etrEntrust.getCounterId();
            
            

            // 通道性质
            String chlType = StringUtil.isEmpty(chlId) ? null : etrCommonCashPositionDealService.queryChlType(chlId);

            if (DictCodeList.交易方向_申购.getValue().equals(ps) || DictCodeListExt2.交易方向_认购.getValue().equals(ps)) {
                // 委托生成的现金流水
                EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDtoFromEtr(
                    entrustCommonDto);
                String acctId;
                /** 场外基金 **/
                if (StringUtil.equals(tradePlace, BussinessDictCodeList.交易场所_场外.getValue())) {
                    //证券托管户存在时  券商账户减少委托金额
                	if(StringUtil.isNotEmpty(secManageAcctId)){
                    	acctId =EtrCommonCashPositionDealService.select资金账户(secManageAcctId, ccy);
                    }
                	// 1.直投
                    // 可用现金(组合、托管户):减少委托金额
                    else if (StringUtil.isEmpty(chlId)) {
                        acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
                            DictCodeListExt2.资金账户类型_托管户.getValue());
                    }
                    // 2.通道一次性划款
                    // 可用现金(组合、通道):减少委托金额
                    else if (StringUtil.isNotEmpty(chlId) && DictCodeListExt2.划款方式_一次性划款.getValue().equals(chlType)) {
                        acctId = EtrCommonCashPositionDealService.select通道资金账户(etrEntrust.getChlId());
                    }
                    // 3.通道逐笔划款
                    // 可用现金(组合、托管户):减少委托金额
                    else {
                        acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
                            DictCodeListExt2.资金账户类型_托管户.getValue());
                        etrCashPositionFlowDto.setChlFinprodId(null);
                    }
                }
                /** 场内基金 **/
                else {
                	//证券托管户存在时  券商账户减少委托金额
                	if(StringUtil.isNotEmpty(secManageAcctId)){
                    	acctId =EtrCommonCashPositionDealService.select资金账户(secManageAcctId, ccy);
                    }
                	// 1.直投
                    // 可用现金(组合、资金账户):减少委托金额
                	else if (StringUtil.isEmpty(chlId)) {
                        acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(),
                                etrEntrust.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue());
                    }
                    // 2.通道一次性划款
                    // 可用现金(组合、通道):减少委托金额
                    else if (StringUtil.isNotEmpty(chlId) && DictCodeListExt2.划款方式_一次性划款.getValue().equals(chlType)) {
                        acctId = EtrCommonCashPositionDealService.select通道资金账户(etrEntrust.getChlId());
                    }
                    // 3.通道逐笔划款
                    // 可用现金(组合、资金账户):减少委托金额
                    else {
                        acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(),
                                etrEntrust.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue());
                        etrCashPositionFlowDto.setChlFinprodId(null);
                    }
                }

                // 减少委托金额
                etrCashPositionFlowDto.setAmount(etrEntrust.getEntrustAmt().negate());
                etrCashPositionFlowDto.setAcctId(acctId);
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
            }
            else if (DictCodeList.交易方向_买入.getValue().equals(ps)) {
                // 委托生成的现金流水
                EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDtoFromEtr(
                    entrustCommonDto);
                String acctId;
                /** 场外基金 **/
                if (StringUtil.equals(tradePlace, BussinessDictCodeList.交易场所_场外.getValue())) {
                	/**场外交易对手为内部交易对手，不需要从券商账户扣款**/
                	//证券托管户存在时  券商账户减少委托金额
//                	if(StringUtil.isNotEmpty(secManageAcctId)){
//                    	acctId =EtrCommonCashPositionDealService.select资金账户(secManageAcctId, ccy);
//                    }
                	// 1.直投
                    // 可用现金(组合、托管户):减少委托金额
//                    else 
                    if (StringUtil.isEmpty(chlId)) {
                        acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
                            DictCodeListExt2.资金账户类型_托管户.getValue());
                    }
                    // 2.通道一次性划款
                    // 可用现金(组合、通道):减少委托金额
                    else if (StringUtil.isNotEmpty(chlId) && DictCodeListExt2.划款方式_一次性划款.getValue().equals(chlType)) {
                        acctId = EtrCommonCashPositionDealService.select通道资金账户(etrEntrust.getChlId());
                    }
                    // 3.通道逐笔划款
                    // 可用现金(组合、托管户):减少委托金额
                    else {
                        acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
                            DictCodeListExt2.资金账户类型_托管户.getValue());
                        etrCashPositionFlowDto.setChlFinprodId(null);
                    }
                }
                /** 场内基金 **/
                else {
                	//证券托管户存在时  券商账户减少委托金额
                	if(StringUtil.isNotEmpty(secManageAcctId)&&!EtrCommonCashPositionDealService.isInTrade(counterId)){
                    	acctId =EtrCommonCashPositionDealService.select资金账户(secManageAcctId, ccy);
                    }
                	// 1.直投
                    // 可用现金(组合、资金账户):减少委托金额
                	else if (StringUtil.isEmpty(chlId)) {
                        acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(),
                                etrEntrust.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue());
                    }
                    // 2.通道一次性划款
                    // 可用现金(组合、通道):减少委托金额
                    else if (StringUtil.isNotEmpty(chlId) && DictCodeListExt2.划款方式_一次性划款.getValue().equals(chlType)) {
                        acctId = EtrCommonCashPositionDealService.select通道资金账户(etrEntrust.getChlId());
                    }
                    // 3.通道逐笔划款
                    // 可用现金(组合、资金账户):减少委托金额
                    else {
                        acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(),
                                etrEntrust.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue());
                        etrCashPositionFlowDto.setChlFinprodId(null);
                    }
                }

                // 减少委托金额
                etrCashPositionFlowDto.setAmount(etrEntrust.getEntrustAmt().negate());
                etrCashPositionFlowDto.setAcctId(acctId);
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
            }
            else if (DictCodeList.交易方向_赎回.getValue().equals(ps) || DictCodeList.交易方向_卖出.getValue().equals(ps)) {
                // 委托生成的持仓流水
                EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDtoFromEtr(
                    entrustCommonDto);
                /** 场外基金 **/
                if (StringUtil.equals(tradePlace, BussinessDictCodeList.交易场所_场外.getValue())) {
                    // 1.直投
                    // 可用持仓(组合):减少委托份额
                    // 2.通道一次性划款
                    // 可用持仓(组合、通道):减少委托份额
                    // 3.通道逐笔划款
                    // 可用持仓(组合、通道):减少委托份额
                    // 1.直投
                    // 可用现金(组合、托管户):减少委托金额
                    etrSecPositionFlowDto.setAmount(etrEntrust.getEntrustNum().negate());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                }
                /** 场内基金 **/
                else {
                    // 1.直投
                    // 可用持仓(组合、证券账户):减少委托份额
                    // 2.通道一次性划款
                    // 可用持仓(组合、通道):减少委托份额
                    // 3.通道逐笔划款
                    // 可用持仓(组合、通道):减少委托份额
                    etrSecPositionFlowDto.setAmount(etrEntrust.getEntrustNum().negate());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                }

            }
        }
    }

    /**
     * 基金（持仓以份额记） 阶段：委托关闭
     * 
     * @param entrustCommonDto
     * @param etrPositionHoldDto
     */
    public static void deal基金EntrustClose(EntrustCommonDto entrustCommonDto, EtrPositionHoldDto etrPositionHoldDto) {
        // 委托信息
        EtrEntrust etrEntrust = entrustCommonDto.getEtrEntrust();

        // 委托信息附加
        EtrEntrustAdd etrEntrustAdd = entrustCommonDto.getEtrEntrustAdd();

        // 交易方向
        String ps = etrEntrust.getPs();

        // 委托已完成额度
        BigDecimal etrFinish = EtrCommonCashPositionDealService.getEtrFinishAmt(etrEntrust.getEntrustId());

        // 交易场所
        String tradePlace = entrustCommonDto.getEtrEntrust().getTradePlat() != null ?
                entrustCommonDto.getEtrEntrust().getTradePlat() : entrustCommonDto.getEtrEntrust().getTradeMarket();

        // 通道代码
        String chlId = etrEntrust.getChlId();

        // 通道性质
        String chlType = StringUtil.isEmpty(chlId) ? null : etrCommonCashPositionDealService.queryChlType(chlId);

        if (DictCodeList.交易方向_申购.getValue().equals(ps)) {

            // 可用现金 关闭日增加剩余委托本金金额
            EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDtoFromEtr(
                entrustCommonDto);

            String acctId;

            /** 场外基金 **/
            if (StringUtil.equals(tradePlace, BussinessDictCodeList.交易场所_场外.getValue())) {
                // 1.直投
                // 可用现金(组合、托管户):减少委托金额
                /*if (StringUtil.isEmpty(chlId)) {
//                    acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
//                        DictCodeListExt2.资金账户类型_托管户.getValue());
                    acctId = EtrCommonCashPositionDealService.select证券托管户(etrEntrustAdd.getSecAcctId());
                }
                // 2.通道一次性划款
                // 可用现金(组合、通道):减少委托金额
                else*/
                if (StringUtil.isNotEmpty(chlId) && DictCodeListExt2.划款方式_一次性划款.getValue().equals(chlType)) {
                    acctId = EtrCommonCashPositionDealService.select通道资金账户(etrEntrust.getChlId());
                }
                // 3.通道逐笔划款
                // 可用现金(组合、托管户):减少委托金额
                else {
                    acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
                        DictCodeListExt2.资金账户类型_托管户.getValue());
                    etrCashPositionFlowDto.setChlFinprodId(null);
                }
            }
            /** 场内基金 **/
            else {
                // 1.直投
                // 可用现金(组合、资金账户):减少委托金额
                if (StringUtil.isEmpty(chlId)) {
//                    acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
//                            DictCodeListExt2.资金账户类型_托管户.getValue());
                    acctId = EtrCommonCashPositionDealService.select证券托管户(etrEntrustAdd.getSecAcctId());
                }
                // 2.通道一次性划款
                // 可用现金(组合、通道):减少委托金额
                else if (StringUtil.isNotEmpty(chlId) && DictCodeListExt2.划款方式_一次性划款.getValue().equals(chlType)) {
                    acctId = EtrCommonCashPositionDealService.select通道资金账户(etrEntrust.getChlId());
                }
                // 3.通道逐笔划款
                // 可用现金(组合、资金账户):减少委托金额
                else {
                    acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
                            DictCodeListExt2.资金账户类型_托管户.getValue());
                    etrCashPositionFlowDto.setChlFinprodId(null);
                }
            }
            // 关闭日增加剩余委托本金金额
            etrCashPositionFlowDto.setAmount(ArithmeticUtil.subtract(etrEntrust.getEntrustAmt(), etrFinish));
            etrCashPositionFlowDto.setAcctId(acctId);
            etrCashPositionFlowDto.setOffsetId(etrEntrust.getEntrustId());
            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
        }
        else if (DictCodeList.交易方向_赎回.getValue().equals(ps)) {
            /** 场外基金 **/
            // 1.直投
            // 可用持仓(组合):关闭日增加剩余委托份额
            // 2.通道一次性划款
            // 可用持仓(组合、通道):关闭日增加剩余委托份额
            // 3.通道逐笔划款
            // 可用持仓(组合、通道):关闭日增加剩余委托份额

            /** 场内基金 **/
            // 1.直投
            // 可用持仓(组合、证券账户):关闭日增加剩余委托份额
            // 2.通道一次性划款
            // 可用持仓(组合、通道):关闭日增加剩余委托份额
            // 3.通道逐笔划款
            // 可用持仓(组合、通道):关闭日增加剩余委托份额

            EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDtoFromEtr(
                entrustCommonDto);
            // 可用持仓 关闭日增加剩余委托本金金额
                etrSecPositionFlowDto.setAmount(ArithmeticUtil.subtract(etrEntrust.getEntrustNum(), etrFinish));
            etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
        }
    }

    /**
     * 基金_认购
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 基金_认购(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                              EtrPositionHoldDto etrPositionHoldDto) {
        // 金融产品交易表
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();
        TrdProductDealAdd dealAdd = finProductTradeDto.getTrdProductDealAdd();
        // 交易场所
        String tradePlace = finProductTradeDto.getTrdProductDeal().getTradePlat();

        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();

        // 通道代码
        String chlId = finProductTradeDto.getTrdProductDeal().getChlId();

        // 通道性质
        String chlType = StringUtil.isEmpty(chlId) ? null : etrCommonCashPositionDealService.queryChlType(chlId);

        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);

        /** 场内基金 **/
        if (!StringUtil.equals(tradePlace, BussinessDictCodeList.交易场所_场外.getValue())) {
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                // 1.无委托直投
                // 可用现金(组合、资金账户):认购交易日减少交易金额
                if (StringUtil.isEmpty(entrustId) && StringUtil.isEmpty(chlId)) {
//                    etrCashPositionFlowDto.setAcctId(
//                        EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
//                                deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                    etrCashPositionFlowDto.setAcctId(((BussTrdTransferOrderDto)finProductTradeDto.getStlPayOrdersList().get(0)).getPayAcctNo());
                    etrCashPositionFlowDto.setAmount(deal.getTradeAmt().negate());
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                }
                // 2.无委托通道
                else if (StringUtil.isEmpty(entrustId) && StringUtil.isEmpty(chlId)) {
                    // 2.1 无委托通道逐笔划款
                    // 可用现金(组合、资金账户):认购交易日减少交易金额
                    if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue())) {
                        etrCashPositionFlowDto.setAcctId(
                            EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                                    deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                        etrCashPositionFlowDto.setChlFinprodId(null);
                    }
                    // 2.2 无委托通道一次性划款
                    // 可用现金(组合、通道):认购交易日减少交易金额
                    else {
                        etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select通道资金账户(chlId));
                    }
                    etrCashPositionFlowDto.setAmount(deal.getTradeAmt().negate());
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                }
            }
        }
    }

    /**
     * 基金_认购确认 阶段：
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 基金_认购确认(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                EtrPositionHoldDto etrPositionHoldDto) {
        // 金融产品交易表
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        // 交易场所
        String tradePlace = finProductTradeDto.getTrdProductDeal().getTradePlat();

        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);

        /** 场内基金 **/
        if (!StringUtil.equals(tradePlace, BussinessDictCodeList.交易场所_场外.getValue())) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                // 1.委托直投、无委托直投
                // 可用持仓(组合、证券账户)：认购确认日增加交易份额
                // 2.委托通道逐笔划款、委托通道一次性划款、无委托通道逐笔划款、无委托通道一次性划款
                // 可用持仓(组合、通道)：认购确认日增加交易份额
                etrSecPositionFlowDto.setAmount(deal.getShareAmt());
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            }
        }
    }

    /**
     * 基金_申购
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 基金_申购(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                              EtrPositionHoldDto etrPositionHoldDto) {
        // 金融产品交易表
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        // 金融产品交易扩张表
        TrdProductDealAdd dealAdd = finProductTradeDto.getTrdProductDealAdd();

        // 交易场所
        String tradePlace = finProductTradeDto.getTrdProductDeal().getTradeMarket();

        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();

        // 通道代码
        String chlId = finProductTradeDto.getTrdProductDeal().getChlId();

        // 通道性质
        String chlType = StringUtil.isEmpty(chlId) ? null : etrCommonCashPositionDealService.queryChlType(chlId);

        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);

        /** 场外基金_申购 **/
        if (StringUtil.equals(tradePlace, BussinessDictCodeList.交易场所_场外.getValue())) {
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                // 1.无委托直投
                // 可用现金(组合、托管户):申购确认日减少交易金额
                if (StringUtil.isEmpty(entrustId) && StringUtil.isEmpty(chlId)) {
                    etrCashPositionFlowDto.setAmount(deal.getTradeAmt().negate());
                    etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                        deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                }
                // 2.无委托通道
                else if (StringUtil.isEmpty(entrustId) && StringUtil.isNotEmpty(chlId)) {
                    // 2.1 无委托通道逐笔划款
                    // 可用现金(组合、托管户):申购确认日减少交易金额
                    if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue())) {
                        etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                            deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                        etrCashPositionFlowDto.setChlFinprodId(null);
                    }
                    // 2.2 无委托通道一次性划款
                    // 可用现金(组合、通道):申购确认日减少交易金额
                    else {
                        etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select通道资金账户(chlId));
                    }
                    etrCashPositionFlowDto.setAmount(deal.getTradeAmt().negate());
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                }
            }
        }
        /** 场内基金_申购 **/
        else {
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                // 1.无委托直投
                // 可用现金(组合、资金账户):申购确认日减少交易金额
                if (StringUtil.isEmpty(entrustId) && StringUtil.isEmpty(chlId)) {
                    etrCashPositionFlowDto.setAmount(deal.getTradeAmt().negate());
//                    etrCashPositionFlowDto.setAcctId(
//                        EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
//                                deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                    etrCashPositionFlowDto.setAcctId(((BussTrdTransferOrderDto)finProductTradeDto.getStlPayOrdersList().get(0)).getPayAcctNo());
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                }
                // 2.无委托通道
                else if (StringUtil.isEmpty(entrustId) && StringUtil.isEmpty(chlId)) {
                    // 2.1 无委托通道逐笔划款
                    // 可用现金(组合、资金账户):申购确认日减少交易金额
                    if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue())) {
                        etrCashPositionFlowDto.setAcctId(
                            EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                                    deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                        etrCashPositionFlowDto.setChlFinprodId(null);
                    }
                    // 2.2 无委托通道一次性划款
                    // 可用现金(组合、通道):申购确认日减少交易金额
                    else {
                        etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select通道资金账户(chlId));
                    }
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                    etrCashPositionFlowDto.setAmount(deal.getTradeAmt().negate());
                }
            }
        }
    }

    /**
     * 基金_申购确认
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 基金_申购确认(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                EtrPositionHoldDto etrPositionHoldDto) {
        // 金融产品交易表
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        // 交易场所
        String tradePlace = finProductTradeDto.getTrdProductDeal().getTradePlat();

        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);

        /** 场外基金_申购确认 **/
        if (StringUtil.equals(tradePlace, BussinessDictCodeList.交易场所_场外.getValue())) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                // 1.委托直投、无委托直投
                // 可用持仓(组合）：申购确认日期增加交易份额
                // 2.委托通道逐笔划款、委托通道一次性划款、无委托通道逐笔划款、无委托通道一次性划款
                // 可用持仓(组合、通道）：申购确认日期增加交易份额
                etrSecPositionFlowDto.setAmount(deal.getShareAmt());
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            }
        }
        /** 场内基金_申购确认 **/
        else {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                // 1.委托直投、无委托直投
                // 可用持仓(组合、证券账户）：申购确认日期增加交易份额
                // 2.委托通道逐笔划款、委托通道一次性划款、无委托通道逐笔划款、无委托通道一次性划款
                // 可用持仓(组合、通道）：申购确认日期增加交易份额
                etrSecPositionFlowDto.setAmount(deal.getShareAmt());
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            }
        }
    }

    /**
     * 基金_买入 阶段：
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 基金_买入(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                              EtrPositionHoldDto etrPositionHoldDto) {
        // 金融产品交易表
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        // 金融产品交易扩张表
        TrdProductDealAdd dealAdd = finProductTradeDto.getTrdProductDealAdd();

        // 交易场所
        String tradePlace = finProductTradeDto.getTrdProductDeal().getTradePlat();

        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();

        // 通道代码
        String chlId = finProductTradeDto.getTrdProductDeal().getChlId();

        // 通道性质
        String chlType = StringUtil.isEmpty(chlId) ? null : etrCommonCashPositionDealService.queryChlType(chlId);

        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);

        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);
        etrCashPositionFlowDto.setOrderId(deal.getTradeId());

        /** 场外基金_买入 **/
        if (StringUtil.equals(tradePlace, BussinessDictCodeList.交易场所_场外.getValue())) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                // 1.委托直投、无委托直投
                // 可用持仓(组合):买入交易日增加交易份额
                // 2.委托通道逐笔划款、委托通道一次性划款、无委托通道逐笔划款、无委托通道一次性划款
                // 可用持仓(组合、通道):买入交易日增加交易份额
                etrSecPositionFlowDto.setAmount(deal.getShareAmt());
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                // 1.无委托直投
                // 可用现金(组合、托管户):买入确认日减少交易金额
                if (StringUtil.isEmpty(entrustId) && StringUtil.isEmpty(chlId)) {
                    etrCashPositionFlowDto.setAmount(deal.getTradeAmt().negate());
                    etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                        deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                }
                // 2.无委托通道
                else if (StringUtil.isEmpty(entrustId) && StringUtil.isNotEmpty(chlId)) {
                    // 2.1 无委托通道逐笔划款
                    // 可用现金(组合、托管户):买入确认日减少交易金额
                    if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue())) {
                        etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                            deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                        etrCashPositionFlowDto.setChlFinprodId(null);
                    }
                    // 2.2 无委托通道一次性划款
                    // 可用现金(组合、通道):买入确认日减少交易金额
                    else {
                        etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select通道资金账户(chlId));
                    }
                    etrCashPositionFlowDto.setAmount(deal.getTradeAmt().negate());
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                }
            }
        }
        /** 场内基金_买入 **/
        else {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                // 1.委托直投、无委托直投
                // 可用持仓(组合、证券账户):买入交易日增加交易份额
                if (StringUtil.isEmpty(chlId)) {
                    etrSecPositionFlowDto.setAmount(deal.getShareAmt());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                }
                // 2.委托通道逐笔划款、委托通道一次性划款、无委托通道逐笔划款、无委托通道一次性划款
                // 可用持仓(组合、通道):买入交易日增加交易份额
                else {
                    etrSecPositionFlowDto.setAmount(deal.getShareAmt());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                }
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                // 1.无委托直投
                // 可用现金(组合、资金账户):买入确认日减少交易金额
                if (StringUtil.isEmpty(entrustId) && StringUtil.isEmpty(chlId)) {
                    etrCashPositionFlowDto.setAmount(deal.getTradeAmt().negate());
//                    etrCashPositionFlowDto.setAcctId(
//                        EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
//                                deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                    etrCashPositionFlowDto.setAcctId((finProductTradeDto.getStlPayOrdersList().get(0)).getMyAcctNo());
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                }
                // 2.无委托通道
                else if (StringUtil.isEmpty(entrustId) && StringUtil.isNotEmpty(chlId)) {
                    // 2.1 无委托通道逐笔划款
                    // 可用现金(组合、资金账户):买入确认日减少交易金额
                    if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue())) {
                        etrCashPositionFlowDto.setAcctId(
                            EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                                    deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                        etrCashPositionFlowDto.setChlFinprodId(null);
                    }
                    // 2.2 无委托通道一次性划款
                    // 可用现金(组合、通道):买入确认日减少交易金额
                    else {
                        etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select通道资金账户(chlId));
                    }
                    etrCashPositionFlowDto.setAmount(deal.getTradeAmt().negate());
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                }
            }
        }
    }

    /**
     * 基金_卖出 阶段：
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 基金_卖出(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                              EtrPositionHoldDto etrPositionHoldDto) {
        // 金融产品交易表
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        // 金融产品交易扩张表
        TrdProductDealAdd dealAdd = finProductTradeDto.getTrdProductDealAdd();

        // 交易场所
        String tradePlace = finProductTradeDto.getTrdProductDeal().getTradePlat();

        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();

        // 通道代码
        String chlId = finProductTradeDto.getTrdProductDeal().getChlId();

        // 通道性质
        String chlType = StringUtil.isEmpty(chlId) ? null : etrCommonCashPositionDealService.queryChlType(chlId);

        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);

        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);

        /** 场外基金_卖出 **/
        if (StringUtil.equals(tradePlace, BussinessDictCodeList.交易场所_场外.getValue())) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                // 1.委托直投、无委托直投
                // 可用现金(组合、托管户）：卖出交易日增加交易金额
                if (StringUtil.isEmpty(chlId)) {
                    etrCashPositionFlowDto.setAmount(deal.getTradeAmt());
                    etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                        deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                }
                // 2.委托通道、无委托通道
                else {
                    // 2.1 通道逐笔划款
                    // 可用现金(组合、托管户）：卖出交易日增加交易金额
                    if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue())) {
                        etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                            deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                        etrCashPositionFlowDto.setChlFinprodId(null);
                    }
                    // 2.2 委通道一次性划款
                    // 可用现金(组合、通道）：卖出交易日增加交易金额
                    else {
                        etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select通道资金账户(chlId));
                    }
                    etrCashPositionFlowDto.setAmount(deal.getTradeAmt());
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                }
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                // 1.无委托直投
                // 可用持仓(组合)：卖出交易日减少交易份额
                if (StringUtil.isEmpty(entrustId) && StringUtil.isEmpty(chlId)) {
                    etrSecPositionFlowDto.setAmount(deal.getShareAmt().negate());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                }
                // 2.无委托通道逐笔划款、无委托通道一次性划款
                // 可用持仓(组合、通道):卖出交易日减少交易份额
                if (StringUtil.isEmpty(entrustId) && StringUtil.isNotEmpty(chlId)) {
                    etrSecPositionFlowDto.setAmount(deal.getShareAmt().negate());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                }
            }
        }
        /** 场内基金_卖出 **/
        else {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                // 1.委托直投、无委托直投
                // 可用现金(组合、资金账户）：卖出交易日增加交易金额
                if (StringUtil.isEmpty(chlId)) {
                    etrCashPositionFlowDto.setAmount(deal.getTradeAmt());
                    etrCashPositionFlowDto.setAcctId(
                        EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                                deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                }
                // 2.委托通道、无委托通道
                else {
                    // 2.1 通道逐笔划款
                    // 可用现金(组合、资金账户）：卖出交易日增加交易金额
                    if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue())) {
                        etrCashPositionFlowDto.setAcctId(
                            EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                                    deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                        etrCashPositionFlowDto.setChlFinprodId(null);
                    }
                    // 2.2 通道一次性划款
                    // 可用现金(组合、通道）：卖出交易日增加交易金额
                    else {
                        etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select通道资金账户(chlId));
                    }
                    etrCashPositionFlowDto.setAmount(deal.getTradeAmt());
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                }
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                // 1.无委托直投
                // 可用持仓(组合、证券账户)：卖出交易日减少交易份额
                if (StringUtil.isEmpty(entrustId) && StringUtil.isEmpty(chlId)) {
                    etrSecPositionFlowDto.setAmount(deal.getShareAmt().negate());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                }
                // 2.无委托通道逐笔划款、无委托通道一次性划款
                // 可用持仓(组合、通道):卖出交易日减少交易份额
                if (StringUtil.isEmpty(entrustId) && StringUtil.isNotEmpty(chlId)) {
                    etrSecPositionFlowDto.setAmount(deal.getShareAmt().negate());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                }
            }
        }
    }

    /**
     * 基金_赎回 阶段：
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 基金_赎回(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                              EtrPositionHoldDto etrPositionHoldDto) {
        // 金融产品交易表
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        // 交易场所
        String tradePlace = finProductTradeDto.getTrdProductDeal().getTradePlat();

        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();

        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);

        /** 场外基金_赎回 **/
        if (StringUtil.equals(tradePlace, BussinessDictCodeList.交易场所_场外.getValue())) {
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                // 1.无委托直投
                // 可用持仓(组合)：赎回申请日减少交易份额
                // 2.无委托通道逐笔划款、无委托通道一次性划款
                // 可用持仓(组合、通道):赎回申请日减少交易份额
                if (StringUtil.isEmpty(entrustId)) {
                    etrSecPositionFlowDto.setAmount(deal.getShareAmt().negate());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                }
            }
        }
        /** 场内基金_赎回 **/
        else {
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                // 1.无委托直投
                // 可用持仓(组合)：赎回申请日减少交易份额
                // 2.无委托通道逐笔划款、无委托通道一次性划款
                // 可用持仓(组合、通道):赎回申请日减少交易份额
                if (StringUtil.isEmpty(entrustId)) {
                    etrSecPositionFlowDto.setAmount(deal.getShareAmt().negate());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                }
            }
        }
    }

    /**
     * 基金_赎回确认 阶段：
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 基金_赎回确认(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                EtrPositionHoldDto etrPositionHoldDto) {
        // 金融产品交易表
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        // 金融产品交易扩张表
        TrdProductDealAdd dealAdd = finProductTradeDto.getTrdProductDealAdd();

        // 交易场所
        String tradePlace = finProductTradeDto.getTrdProductDeal().getTradePlat();

        // 通道代码
        String chlId = finProductTradeDto.getTrdProductDeal().getChlId();

        // 通道性质
        String chlType = StringUtil.isEmpty(chlId) ? null : etrCommonCashPositionDealService.queryChlType(chlId);

        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);

        /** 场外基金_赎回确认 **/
        if (StringUtil.equals(tradePlace, BussinessDictCodeList.交易场所_场外.getValue())) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                // 1.委托直投、无委托直投
                // 可用现金(组合、托管户):赎回确认日增加交易金额
                if (StringUtil.isEmpty(chlId)) {
                    etrCashPositionFlowDto.setAmount(deal.getTradeAmt());
                    etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                        deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                }
                // 2.委托通道逐笔划款、委托通道一次性划款、无委托通道逐笔划款、无委托通道一次性划款
                else {
                    // 2.1 通道逐笔划款
                    // 可用现金(组合、托管户):赎回确认日增加交易金额
                    if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue())) {
                        etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                            deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                        etrCashPositionFlowDto.setChlFinprodId(null);
                    }
                    // 2.2 通道一次性划款
                    // 可用现金(组合、通道):赎回确认日增加交易金额
                    else {
                        etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select通道资金账户(chlId));
                    }
                    etrCashPositionFlowDto.setAmount(deal.getTradeAmt());
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                }
            }
        }
        /** 场内基金_赎回确认 **/
        else {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                // 1.委托直投、无委托直投
                // 可用现金(组合、资金账户):赎回确认日增加交易金额
                if (StringUtil.isEmpty(chlId)) {
                    etrCashPositionFlowDto.setAmount(deal.getTradeAmt());
                    etrCashPositionFlowDto.setAcctId(
                        EtrCommonCashPositionDealService.select证券托管户(deal.getSecManageAcctId()));
//                    etrCashPositionFlowDto.setAcctId(((BussTrdTransferOrderDto)finProductTradeDto.getStlPayOrdersList().get(0)).getRecAcctNo());
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                }
                // 2.委托通道逐笔划款、委托通道一次性划款
                else {
                    // 2.1 通道逐笔划款
                    // 可用现金(组合、资金账户):赎回确认日增加交易金额
                    if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue())) {
                        etrCashPositionFlowDto.setAcctId(
                            EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                                    deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                        etrCashPositionFlowDto.setChlFinprodId(null);
                    }
                    // 2.2 通道一次性划款
                    // 可用现金(组合、通道):赎回确认日增加交易金额
                    else {
                        etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select通道资金账户(chlId));
                    }
                    etrCashPositionFlowDto.setAmount(deal.getTradeAmt());
                    etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);

                }
            }
        }
    }

    /**
     * 基金_现金分红 阶段：
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 基金_现金分红(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                EtrPositionHoldDto etrPositionHoldDto) {

        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        TrdProductDealAdd dealAdd = finProductTradeDto.getTrdProductDealAdd();

        List<StlPayOrdersDto> stlPayOrdersDtos = finProductTradeDto.getStlPayOrdersList();

        // 交易场所
        String tradePlace = finProductTradeDto.getTrdProductDeal().getTradePlat();

        // 通道代码
        String chlId = finProductTradeDto.getTrdProductDeal().getChlId();
        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();
        
        // 通道性质
        String chlType = StringUtil.isEmpty(chlId) ? null : etrCommonCashPositionDealService.queryChlType(chlId);

        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);

        /** 场外基金_现金分红 **/
        if (StringUtil.equals(tradePlace, BussinessDictCodeList.交易场所_场外.getValue())) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                // 1.直投
                // 可用现金(组合、托管户):资金到账日增加到账金额
                // 预增现金(组合、托管户)：资金到账日减少到账金额
                String acctId;
                if (StringUtil.isEmpty(chlId)) {
                    acctId = EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(), deal.getCcy(),
                        DictCodeListExt2.资金账户类型_托管户.getValue());
                }
                // 通道
                else {
                    // 2.逐笔划款通道
                    // 预增现金(组合、托管户）：资金到账日增加到账金额
                    // 预增现金(组合、托管户)：资金到账日减少到账金额
                    if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue())) {
                        acctId = EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(), deal.getCcy(),
                            DictCodeListExt2.资金账户类型_托管户.getValue());
                        etrCashPositionFlowDto.setChlFinprodId(null);
                    }
                    // 3.一次性划款通道
                    // 预增现金(组合、通道）：资金到账日增加到账金额
                    // 预增现金(组合、托管户)：资金到账日减少到账金额
                    else {
                        acctId = EtrCommonCashPositionDealService.select通道资金账户(chlId);
                    }
                }
                可用现金增加(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                if (StringUtil.isEmpty(entrustId)) { // 无委托
                    String acctId;
                    // 1.直投
                    // 预增现金(组合、托管户）：资金到账日增加到账金额
                    if (StringUtil.isEmpty(chlId)) {
                        acctId = EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(), deal.getCcy(),
                            DictCodeListExt2.资金账户类型_托管户.getValue());
                    }
                    // 通道
                    else {
                        // 2.逐笔划款通道
                        // 预增现金(组合、托管户）：资金到账日增加到账金额
                        if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue())) {
                            acctId = EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(), deal.getCcy(),
                                DictCodeListExt2.资金账户类型_托管户.getValue());
                            etrCashPositionFlowDto.setChlFinprodId(null);
                        }
                        // 3.一次性划款通道
                        // 预增现金(组合、通道）：资金到账日增加到账金额
                        else {
                            acctId = EtrCommonCashPositionDealService.select通道资金账户(chlId);
                        }
                    }
                    预增现金处理(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                }
            }
        }
        /** 场内基金_现金分红 **/
        else {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                String acctId;
                // 1.直投
                // 可用现金(组合、资金账户):资金到账日增加到账金额
                // 预增现金(组合、资金账户)：资金到账日减少到账金额
                if (StringUtil.isEmpty(chlId)) {
                    acctId = EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                            deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue());
                }
                // 通道
                else {
                    // 2.逐笔划款通道
                    // 预增现金(组合、资金账户）：资金到账日增加到账金额
                    // 预增现金(组合、资金账户)：资金到账日减少到账金额
                    if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue())) {
                        acctId = EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                                deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue());
                        etrCashPositionFlowDto.setChlFinprodId(null);
                    }
                    // 3.一次性划款通道
                    // 预增现金(组合、通道）：资金到账日增加到账金额
                    // 预增现金(组合、托管户)：资金到账日减少到账金额
                    else {
                        acctId = EtrCommonCashPositionDealService.select通道资金账户(chlId);
                    }
                }
                可用现金增加(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
            }
            if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
                if (StringUtil.isEmpty(entrustId)) { // 无委托
                    String acctId;
                    // 1.直投
                    // 预增现金(组合、资金账户）：资金到账日增加到账金额
                    if (StringUtil.isEmpty(chlId)) {
                        acctId = EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                            deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue());
                    }
                    // 通道
                    else {
                        // 2.逐笔划款通道
                        // 预增现金(组合、资金账户）：资金到账日增加到账金额
                        if (StringUtil.equals(chlType, DictCodeListExt2.划款方式_逐笔划款.getValue())) {
                            acctId = EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                                deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue());
                            etrCashPositionFlowDto.setChlFinprodId(null);
                        }
                        // 3.一次性划款通道
                        // 预增现金(组合、通道）：资金到账日增加到账金额
                        else {
                            acctId = EtrCommonCashPositionDealService.select通道资金账户(chlId);
                        }
                    }
                    预增现金处理(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
                }
            }
        }
    }

    /**
     * 基金_红利再投 阶段：
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 基金_红利再投(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                EtrPositionHoldDto etrPositionHoldDto) {
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        // 交易场所
        String tradePlace = deal.getTradePlat();

        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);
        // 持仓流水交易编号 = 金融产品交易.汇总交易编号
        etrSecPositionFlowDto.setTradeId(deal.getTotleTradeId());

        /** 场外基金_红利再投 **/
        if (StringUtil.equals(tradePlace, BussinessDictCodeList.交易场所_场外.getValue())) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                // 1.直投
                // 可用持仓(组合):红利再投日增加红利再投份额
                // 2.通道
                // 可用持仓(组合、通道):红利再投日增加红利再投份额
                etrSecPositionFlowDto.setAmount(deal.getShareAmt());
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            }
        }
        /** 场内基金_红利再投 **/
        else {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                // 1.直投
                // 可用持仓(组合、证券账户):红利再投日增加红利再投份额
                // 2.通道
                // 可用持仓(组合、通道):红利再投日增加红利再投份额
                etrSecPositionFlowDto.setAmount(deal.getShareAmt());
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            }
        }
    }

    /**
     * 基金_份额折算(份额强增) 阶段：
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 基金_份额折算_强增(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                   EtrPositionHoldDto etrPositionHoldDto) {
        // 金融产品交易
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        // 交易场所
        String tradePlace = finProductTradeDto.getTrdProductDeal().getTradePlat();

        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);

        // 持仓流水交易编号 = 金融产品交易.汇总交易编号
        etrSecPositionFlowDto.setTradeId(deal.getTotleTradeId());

        /** 场内基金_份额强增 **/
        if (!StringUtil.equals(tradePlace, BussinessDictCodeList.交易场所_场外.getValue())) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                // 1.直投
                // 可用持仓(组合、证券账户):折算日份额变更为折算后份额
                // 2.通道
                // 可用持仓(组合、通道):折算日份额变更为折算后份额
                etrSecPositionFlowDto.setAmount(deal.getShareAmt());
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            }
        }
    }

    /**
     * 基金_份额折算(份额强减) 阶段：
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 基金_份额折算_强减(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                   EtrPositionHoldDto etrPositionHoldDto) {
        // 金融产品交易
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        // 交易场所
        String tradePlace = finProductTradeDto.getTrdProductDeal().getTradePlat();

        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);
        // 持仓流水交易编号 = 金融产品交易.汇总交易编号
        etrSecPositionFlowDto.setTradeId(deal.getTotleTradeId());

        /** 场内基金_份额强减 **/
        if (!StringUtil.equals(tradePlace, BussinessDictCodeList.交易场所_场外.getValue())) {
            if (DictCodeList.持仓类型_可用.equals(持仓类型)) { // 有效
                // 1.直投
                // 可用持仓(组合、证券账户):折算日份额变更为折算后份额
                // 2.通道
                // 可用持仓(组合、通道):折算日份额变更为折算后份额
                etrSecPositionFlowDto.setAmount(deal.getShareAmt().negate());
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            }
        }
    }

    private static void 预增现金处理(EtrPositionHoldDto etrPositionHoldDto, List<StlPayOrdersDto> stlPayOrdersDtos,
                               EtrCashPositionFlowDto etrCashPositionFlowDto, String acctId) {
        if (StringUtil.isEmpty(acctId) || stlPayOrdersDtos == null || stlPayOrdersDtos.isEmpty()) {
            return;
        }
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
            String QsAcctId = EtrCommonCashPositionDealService.selectZQAcctId(stlPayOrdersDto.getPortfolioId(), stlPayOrdersDto.getCcy(),
                    etrCashPositionFlowDto2.getAcctId());
            if(StringUtil.isNotEmpty(QsAcctId)){
                return;
            }
            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto2);
        });
    }

    /**
     * 可用现金增加，预增现金减少
     * 
     * @param etrPositionHoldDto
     * @param stlPayOrdersDtos
     * @param etrCashPositionFlowDto
     * @param acctId
     */
    private static void 可用现金增加(EtrPositionHoldDto etrPositionHoldDto, List<StlPayOrdersDto> stlPayOrdersDtos,
                               EtrCashPositionFlowDto etrCashPositionFlowDto, String acctId) {
        if (StringUtil.isEmpty(acctId) || stlPayOrdersDtos == null || stlPayOrdersDtos.isEmpty()) {
            return;
        }
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

            String QsAcctId = EtrCommonCashPositionDealService.selectZQAcctId(stlPayOrdersDto.getPortfolioId(), stlPayOrdersDto.getCcy(),
                    etrCashPositionFlowDto1.getAcctId());
            if(StringUtil.isNotEmpty(QsAcctId)){
                return;
            }
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
