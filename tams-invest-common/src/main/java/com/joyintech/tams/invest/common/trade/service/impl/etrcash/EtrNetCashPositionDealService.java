package com.joyintech.tams.invest.common.trade.service.impl.etrcash;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.etr.EtrCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrPositionHoldDto;
import com.joyintech.tams.invest.common.dto.etr.EtrSecPositionFlowDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService.getSeqNo;

/**
 * @program: 2.1.0
 * @description: 货币型、净值型计划
 * @author: 强成西
 * @create: 2019-10-12 15:07
 **/
@Service
public class EtrNetCashPositionDealService extends EtrCashPositionBaseService {

//    @Autowired
//    private static TrdProductDealMapper trdProductDealMapper;
    /**
     * 净值型计划、货币型计划（持仓以份额记）
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    public static void deal计划类CashPosition(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                           EtrPositionHoldDto etrPositionHoldDto) {
        // 交易方向
        String ps = finProductTradeDto.getTrdProductDeal().getPs();

        if (StringUtil.equals(ps, DictCodeList.交易方向_申购.getValue())) {
            计划类_申购(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(ps, DictCodeList.交易方向_申购确认.getValue())) {
            计划类_申购确认(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())) {
            计划类_买入(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(ps, DictCodeList.交易方向_卖出.getValue())) {
            计划类_卖出(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(ps, DictCodeList.交易方向_赎回.getValue())) {
            计划类_赎回(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(ps, DictCodeList.交易方向_赎回确认.getValue())) {
            计划类_赎回确认(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(ps, DictCodeList.交易方向_现金分红.getValue())) {
            计划类_现金分红(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(ps, DictCodeList.交易方向_红利再投.getValue())) {
            计划类_红利再投(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
    }

    /**
     * 净值型计划、货币型计划（持仓以份额记） 阶段：委托开始
     * 
     * @param entrustCommonDto
     * @param etrPositionHoldDto
     */
    public static void deal计划类EntrustStart(EntrustCommonDto entrustCommonDto, EtrPositionHoldDto etrPositionHoldDto) {
        if (entrustCommonDto != null) {
            // 委托信息
            EtrEntrust etrEntrust = entrustCommonDto.getEtrEntrust();

            // 交易方向
            String ps = etrEntrust.getPs();

            // add by etliu 20210421 权益类资产相关代码合并 start
            //权益类资产  标的挂逐笔划款通道，从组合托管户出流水，通道置空，否则造成主键冲突
            if(null!=entrustCommonDto.getEtrEntrust()&&StringUtil.equals(entrustCommonDto.getEtrEntrust().getFinprodType2(), DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue())){
                entrustCommonDto.getEtrEntrust().setChlId(StringUtil.EMPTY);
            }
            // add by etliu 20210421 权益类资产相关代码合并 end

            if (DictCodeList.交易方向_申购.getValue().equals(ps)) {
                // 委托生成的现金流水
                EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDtoFromEtr(
                    entrustCommonDto);
                // 1.直投
                // 可用现金(组合、托管户):减少委托金额
                String acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
                    DictCodeListExt2.资金账户类型_托管户.getValue());
                etrCashPositionFlowDto.setAmount(etrEntrust.getEntrustAmt().negate());
                etrCashPositionFlowDto.setAcctId(acctId);
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
            }
            else if (DictCodeList.交易方向_赎回.getValue().equals(ps)) {
                // 委托生成的持仓流水
                EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDtoFromEtr(
                    entrustCommonDto);
                // 1.直投
                // 可用持仓(组合):减少委托份额（若为委外委托金额赎回时，按照最新净值（记为N0）计算份额）
                BigDecimal 委托金额 = etrEntrust.getEntrustAmt();
                BigDecimal 委托份额 = etrEntrust.getEntrustNum();
                if (委托份额 != null &&  委托份额.compareTo(BigDecimal.ZERO) != 0) {
                    etrSecPositionFlowDto.setAmount(委托份额.negate());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                } else {
                    BigDecimal netValue = EtrCommonCashPositionDealService.getPlanNetValue(etrEntrust.getFinprodId(),
                            etrEntrust.getEntrustDate());
                    etrSecPositionFlowDto.setAmount(ArithmeticUtil.divide(委托金额, netValue, 2).negate());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                }

            }
        }
    }

    /**
     * 净值型计划、货币型计划（持仓以份额记） 阶段：委托关闭
     * 
     * @param entrustCommonDto
     * @param etrPositionHoldDto
     */
    public static void deal计划类EntrustClose(EntrustCommonDto entrustCommonDto, EtrPositionHoldDto etrPositionHoldDto) {
        // 委托信息
        EtrEntrust etrEntrust = entrustCommonDto.getEtrEntrust();

        // 交易方向
        String ps = etrEntrust.getPs();

        // 委托已完成额度
        BigDecimal etrFinish = EtrCommonCashPositionDealService.getEtrFinishAmt(etrEntrust.getEntrustId());

        if (DictCodeList.交易方向_申购.getValue().equals(ps)) {

            // 可用现金 关闭日增加剩余委托本金金额
            EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDtoFromEtr(
                entrustCommonDto);
            // 1.直投
            // 可用现金(组合、托管户):减少委托金额
            String acctId = EtrCommonCashPositionDealService.select组合托管户(etrEntrust.getPortfolioId(), etrEntrust.getCcy(),
                DictCodeListExt2.资金账户类型_托管户.getValue());
            etrCashPositionFlowDto.setChlFinprodId(null);
            // 关闭日增加剩余委托本金金额
            etrCashPositionFlowDto.setAmount(ArithmeticUtil.subtract(etrEntrust.getEntrustAmt(), etrFinish));
            etrCashPositionFlowDto.setAcctId(acctId);
            etrCashPositionFlowDto.setOffsetId(etrEntrust.getEntrustId());
            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
        }
        else if (DictCodeList.交易方向_赎回.getValue().equals(ps)) {
            // 1.直投
            // 可用持仓(组合):关闭日增加剩余委托份额
            EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDtoFromEtr(
                entrustCommonDto);
            // 关闭日增加剩余委托份额（若为委外委托金额赎回时，按照剩余委托金额和N0计算份额）
            BigDecimal 委托金额 = etrEntrust.getEntrustAmt();
            BigDecimal 委托份额 = etrEntrust.getEntrustNum();
            if (委托份额 != null &&  委托份额.compareTo(BigDecimal.ZERO) != 0) {
                etrSecPositionFlowDto.setAmount(ArithmeticUtil.subtract(委托份额, etrFinish));
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            } else {
                BigDecimal netValue = EtrCommonCashPositionDealService.getPlanNetValue(etrEntrust.getFinprodId(),
                        etrEntrust.getEntrustDate());
                BigDecimal sub = ArithmeticUtil.subtract(委托金额, etrFinish);
                etrSecPositionFlowDto.setAmount(ArithmeticUtil.divide(sub, netValue, 2));
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            }
        }
    }

    /**
     * 计划类_申购 阶段：
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 计划类_申购(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                               EtrPositionHoldDto etrPositionHoldDto) {
        // 金融产品交易表
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        // 金融产品交易扩张表
        TrdProductDealAdd dealAdd = finProductTradeDto.getTrdProductDealAdd();

        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();

        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);

        /** 场外基金_申购 **/
        if (DictCodeList.持仓类型_可用.equals(持仓类型) || (etrPositionHoldDto.isNeedNegative() && DictCodeList.持仓类型_实际.equals(持仓类型))) { // 提交
            // 1.无委托直投
            // 可用现金(组合、托管户):申购确认日减少交易金额
            if (StringUtil.isEmpty(entrustId)) {
                etrCashPositionFlowDto.setAmount(deal.getTradeAmt().negate());
                etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                    deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
            }
        }
    }

    /**
     * 计划类_申购确认 阶段：
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 计划类_申购确认(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                 EtrPositionHoldDto etrPositionHoldDto) {
        // 金融产品交易表
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);
        if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
            // 1.委托直投、无委托直投
            // 可用持仓(组合）：申购确认日期增加交易份额
            etrSecPositionFlowDto.setAmount(deal.getShareAmt());
            etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
        }

    }

    /**
     * 计划类_买入 阶段：
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 计划类_买入(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                               EtrPositionHoldDto etrPositionHoldDto) {
        // 金融产品交易表
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        // 金融产品交易扩张表
        TrdProductDealAdd dealAdd = finProductTradeDto.getTrdProductDealAdd();

        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();

        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);

        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);

        if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
            // 1.委托直投、无委托直投
            // 可用持仓(组合):买入交易日增加交易份额
            etrSecPositionFlowDto.setAmount(deal.getShareAmt());
            etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
        }
        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
            // 1.无委托直投
            // 可用现金(组合、托管户):买入确认日减少交易金额
            if (StringUtil.isEmpty(entrustId)) {
                etrCashPositionFlowDto.setAmount(deal.getTradeAmt().negate());
                etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                    deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
                etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
            }
        }

    }

    /**
     * 计划类_卖出 阶段：
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 计划类_卖出(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                               EtrPositionHoldDto etrPositionHoldDto) {
        // 金融产品交易表
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        // 金融产品交易扩张表
        TrdProductDealAdd dealAdd = finProductTradeDto.getTrdProductDealAdd();

        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();

        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);

        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);
        if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
            // 1.委托直投、无委托直投
            // 可用现金(组合、托管户）：卖出交易日增加交易金额
            etrCashPositionFlowDto.setAmount(deal.getTradeAmt());
            etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(), deal.getCcy(),
                DictCodeListExt2.资金账户类型_托管户.getValue()));
            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
            if (StringUtil.isNotEmpty(entrustId)) {
                // 委托dto
                EntrustCommonDto entrustCommonDto = EtrCommonCashPositionDealService.findEntrust(entrustId);
                EtrEntrust etrEntrust = entrustCommonDto.getEtrEntrust();
                BigDecimal 委托金额 = etrEntrust.getEntrustAmt();
                if (委托金额 != null &&  委托金额.compareTo(BigDecimal.ZERO) != 0) {
                    // 冲销增加委托份额
                    BigDecimal netValue = EtrCommonCashPositionDealService.getPlanNetValue(etrEntrust.getFinprodId(),
                            etrEntrust.getEntrustDate());
                    etrSecPositionFlowDto.setAmount(ArithmeticUtil.divide(委托金额, netValue, 2));
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                    // 正常减少卖出份额
                    EtrSecPositionFlowDto etrSecPositionFlowDto1 = new EtrSecPositionFlowDto();
                    BeanUtil.copyPropertiesIgnoreNull(etrSecPositionFlowDto1, etrSecPositionFlowDto);
                    etrSecPositionFlowDto1.setSeqno(getSeqNo());
                    etrSecPositionFlowDto1.setAmount(deal.getShareAmt().negate());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto1);
                }
            }
        }
        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
            // 1.无委托直投
            // 可用持仓(组合)：卖出交易日减少交易份额
            // 减少交易份额（若为委外委托金额赎回时，按照最新净值（记为N1）计算份额）TODO
            if (StringUtil.isEmpty(entrustId)) {
                BigDecimal 交易份额 = deal.getShareAmt();
                BigDecimal 交易金额 = deal.getTradeAmt();
                if (交易份额 != null &&  交易份额.compareTo(BigDecimal.ZERO) != 0) {
                    etrSecPositionFlowDto.setAmount(交易份额.negate());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                } else {
                    BigDecimal netValue = EtrCommonCashPositionDealService.getPlanNetValue(deal.getFinprodId(),
                            deal.getTradeDate());
                    etrSecPositionFlowDto.setAmount(ArithmeticUtil.divide(交易金额, netValue, 2).negate());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                }
            }
        }
    }

    /**
     * 计划类_赎回 阶段：
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 计划类_赎回(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                               EtrPositionHoldDto etrPositionHoldDto) {
        // 金融产品交易表
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();

        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);

        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
            // 1.无委托直投
            // 可用持仓(组合)：赎回申请日减少交易份额
            // 减少交易份额（若为委外委托金额赎回时，按照最新净值（记为N1）计算份额） TODO
            if (StringUtil.isEmpty(entrustId)) {
                BigDecimal 交易份额 = deal.getShareAmt();
                BigDecimal 交易金额 = deal.getTradeAmt();
                if (交易份额 != null &&  交易份额.compareTo(BigDecimal.ZERO) != 0) {
                    etrSecPositionFlowDto.setAmount(交易份额.negate());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                } else {
                    BigDecimal netValue = EtrCommonCashPositionDealService.getPlanNetValue(deal.getFinprodId(),
                            deal.getTradeDate());
                    etrSecPositionFlowDto.setAmount(ArithmeticUtil.divide(交易金额, netValue, 2).negate());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                }
            }
        }
    }

    /**
     * 计划类_赎回确认 阶段：
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 计划类_赎回确认(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                 EtrPositionHoldDto etrPositionHoldDto) {
        // 金融产品交易表
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        // 金融产品交易扩张表
        TrdProductDealAdd dealAdd = finProductTradeDto.getTrdProductDealAdd();

        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);
        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
                finProductTradeDto);

        if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
            // 1.委托直投
            // 可用现金(组合、托管户):赎回确认日增加交易金额
            etrCashPositionFlowDto.setAmount(deal.getTradeAmt());
            etrCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(), deal.getCcy(),
                DictCodeListExt2.资金账户类型_托管户.getValue()));
            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);

            // 份额处理
            // 获取申请交易
            String 申请交易编号 = deal.getbTradeId();
            TrdProductDealMapper trdProductDealMapper = SpringContextUtil.getBean(TrdProductDealMapper.class);
            TrdProductDeal bDeal = trdProductDealMapper.selectByPrimaryKey(申请交易编号);
            BigDecimal 申请金额 = bDeal.getTradeAmt();
            // 金额申请
            if (申请金额 != null &&  申请金额.compareTo(BigDecimal.ZERO) != 0) {
                // 冲销增加申请份额
                BigDecimal netValue = EtrCommonCashPositionDealService.getPlanNetValue(bDeal.getFinprodId(),
                        bDeal.getTradeDate());
                etrSecPositionFlowDto.setAmount(ArithmeticUtil.divide(申请金额, netValue, 2));
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                // 正常减少确认份额
                EtrSecPositionFlowDto etrSecPositionFlowDto1 = new EtrSecPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(etrSecPositionFlowDto1, etrSecPositionFlowDto);
                etrSecPositionFlowDto1.setSeqno(getSeqNo());
                etrSecPositionFlowDto1.setAmount(deal.getShareAmt().negate());
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto1);
            }

        }
    }

    /**
     * 计划类_现金分红 阶段：
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 计划类_现金分红(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                 EtrPositionHoldDto etrPositionHoldDto) {

        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        TrdProductDealAdd dealAdd = finProductTradeDto.getTrdProductDealAdd();

        List<StlPayOrdersDto> stlPayOrdersDtos = finProductTradeDto.getStlPayOrdersList();

        // 交易生成的现金流水
        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
            finProductTradeDto);

        // add by etliu 20210421 权益类资产相关代码合并 start
        //生成现金流数据  不带通道
        if((StringUtil.equals(finProductTradeDto.getTrdProductDeal().getFinprodType2(),DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue()))
                &&StringUtil.isNotEmpty(etrCashPositionFlowDto.getChlFinprodId())){
            etrCashPositionFlowDto.setChlFinprodId(StringUtil.EMPTY);
        }
        // add by etliu 20210421 权益类资产相关代码合并 end

        // 委托代码
        String entrustId = finProductTradeDto.getEntrustId();
        if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
            // 1.直投
            // 可用现金(组合、托管户):资金到账日增加到账金额
            // 预增现金(组合、托管户)：资金到账日减少到账金额
            String acctId = EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(), deal.getCcy(),
                DictCodeListExt2.资金账户类型_托管户.getValue());
            可用现金增加(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto, acctId);
        }
        if (DictCodeList.持仓类型_可用.equals(持仓类型) || etrPositionHoldDto.isNeedNegative()) { // 提交
            if (StringUtil.isEmpty(entrustId)) { // 无委托
                // 1.直投
                // 预增现金(组合、托管户）：资金到账日增加到账金额
                String acctId = EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(), deal.getCcy(),
                    DictCodeListExt2.资金账户类型_托管户.getValue());
                EtrCashPositionFlowDto etrCashPositionFlowDto1 = new EtrCashPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlowDto1, etrCashPositionFlowDto);
                etrCashPositionFlowDto1.setSeqno(getSeqNo());
                预增现金处理(etrPositionHoldDto, stlPayOrdersDtos, etrCashPositionFlowDto1, acctId);
            }
        }
    }

    /**
     * 计划类_红利再投 阶段：
     * 
     * @param finProductTradeDto
     * @param 持仓类型
     * @param etrPositionHoldDto
     */
    private static void 计划类_红利再投(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                 EtrPositionHoldDto etrPositionHoldDto) {
        TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();

        // 交易生成的持仓流水
        EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
            finProductTradeDto);
        // 持仓流水交易编号 = 金融产品交易.汇总交易编号
        etrSecPositionFlowDto.setTradeId(deal.getTotleTradeId());

        if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
            // 1.直投
            // 可用持仓(组合):红利再投日增加红利再投份额
            etrSecPositionFlowDto.setAmount(deal.getShareAmt());
            etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
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
