package com.joyintech.tams.invest.common.trade.service.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.BidOrderBidPrice;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.CommonResult;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.business.common.dto.PortfolioCashPositionsFlowInputDto;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.etr.EtrCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrPositionHoldDto;
import com.joyintech.tams.invest.common.dto.etr.EtrSecPositionFlowDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.trade.dao.BaseAssetCashPositionCommMapper;
import com.joyintech.tams.invest.common.trade.dto.EtrSecPositionDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import com.joyintech.tams.invest.common.trade.service.impl.etrcash.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 实现类
 */
@Service // TODO 这里不要放开影响正常业务运行
public class BaseEtrCashPositionCommServiceImpl extends BaseService implements BaseEtrCashPositionCommService {

    /**
     * 共通Service
     */
    @Autowired(required = false)
    private EtrCommonCashPositionDealService etrCommonCashPositionDealService;

    @Autowired
    private BaseAssetCashPositionCommMapper baseAssetCashPositionCommMapper;
    
    /**
     * 平台日期
     */
    @Autowired
    private IPlatformDate platformDateImpl;

    @Override
    public CommonResult executeEtrCashPosition(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                               boolean needNegative,String ieFlag,String isOverdueDeal) {
        // 资产类型
        String finprodType = finProductTradeDto.getTrdProductDeal().getFinprodType2();
        // 处理结果
        EtrPositionHoldDto etrPositionHoldDto = new EtrPositionHoldDto();
        // 持仓流水
        List<EtrSecPositionFlowDto> etrSecPositionFlows = new ArrayList<>();
        // 现金流水
        List<EtrCashPositionFlowDto> etrCashPositionFlows = new ArrayList<>();
        etrPositionHoldDto.setPtlCashPositionFlows(etrCashPositionFlows);
        etrPositionHoldDto.setPtlSecPositionFlows(etrSecPositionFlows);
        etrPositionHoldDto.setNeedNegative(needNegative);
        Map<String, Object> vDateNato;
        String profitType = null;
        if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())) {
            vDateNato = baseAssetCashPositionCommMapper.selectVDateNato(finProductTradeDto.getTrdProductDeal().getFinprodId());
            /**
             * 收益类型
             */
            profitType = vDateNato == null ? null : (String)vDateNato.get("PROFIT_TYPE");
        }

        if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_债券.getValue())
                && !(StringUtil.equals(finProductTradeDto.getTrdProductDeal().getPs(),DictCodeList.交易方向_申购确认.getValue())
                ||StringUtil.equals(finProductTradeDto.getTrdProductDeal().getPs(), DictCodeListExt3.交易方向_债转股.getValue()))
        ) {
            EtrSecCashPositionDealService.deal债券CashPosition(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_存款.getValue())) {
            EtrDepoCashPositionDealService.deal存款CashPosition(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())
                 && DictCodeList.收益类型_收益型.getValue().equals(profitType)) {
            EtrPlanRateCashPositionDealService.deal计划利率型CashPosition(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_拆入.getValue())
                 || StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_拆出.getValue())) {
            EtrLendCashPositionDealService.genLendDealCashPosition(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())) {
            EtrProjectCashPositionDealService.deal项目类资产CashPosition(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue())
                 || StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_债券借贷融出.getValue())) {
            EtrSecLendCashPositionDealService.deal债券借贷CashPosition(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_基金.getValue())) {
            EtrFundCashPositionDealService.deal基金CashPosition(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())
                 && (DictCodeList.收益类型_净值型.getValue().equals(profitType) || DictCodeList.收益类型_货币型.getValue().equals(profitType))) {
            EtrNetCashPositionDealService.deal计划类CashPosition(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
                 || StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
            EtrRepoCashPositionDealService.deal质押式回购CashPosition(finProductTradeDto, 持仓类型, etrPositionHoldDto,ieFlag);
        }
        else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue())
                 || StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())) {
            EtrBuyoutCashPositionDealService.deal买断式回购CashPosition(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_股票.getValue())) {
            EtrSharesCashPositionDealService.deal股票CashPosition(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_债券.getValue())
                && (StringUtil.equals(finProductTradeDto.getTrdProductDeal().getPs(),DictCodeList.交易方向_申购确认.getValue())
                ||StringUtil.equals(finProductTradeDto.getTrdProductDeal().getPs(), DictCodeListExt3.交易方向_债转股.getValue()))) {
            EtrConBondCashPositionDealService.deal可转债CashPosition(finProductTradeDto, 持仓类型, etrPositionHoldDto);
        }
        else if (StringUtil.equals(finprodType, DictCodeListExt1.一次性费用类型_通道费.getValue())) {
            if (DictCodeList.持仓类型_实际.equals(持仓类型)) { // 有效
                TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();
                TrdProductDealAdd dealAdd = finProductTradeDto.getTrdProductDealAdd();
                // 指令信息
                List<StlPayOrdersDto> stlPayOrdersDtos = finProductTradeDto.getStlPayOrdersList();
                // 托管账户
                String acctId = EtrCommonCashPositionDealService.select组合托管户(dealAdd.getPortfolioId(),
                        deal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue());
                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                    stlPayOrdersDtos.forEach(stlPayOrdersDto -> {
                        // 交易生成的现金流水
                        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
                                finProductTradeDto);
                        // 可用现金 到期日减少到账金额
                        etrCashPositionFlowDto.setAmount(ArithmeticUtil.multiply(stlPayOrdersDto.getSettleAmt(), new BigDecimal(-1)));
                        etrCashPositionFlowDto.setAcctId(stlPayOrdersDto.getMyAcctNo());
                        etrCashPositionFlowDto.setCdate(stlPayOrdersDto.getSettleDate());
                        etrCashPositionFlowDto.setOrderId(stlPayOrdersDto.getOrderId());
                        etrCashPositionFlowDto.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
                        etrCashPositionFlowDto.setChlFinprodId(null);
                        etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                    });
                }
            }
        }
        // 反转金额
        if (needNegative) {
            if (!etrCashPositionFlows.isEmpty()) {
                for (EtrCashPositionFlowDto etrCashPosition : etrCashPositionFlows) {
                    if (etrCashPosition.getAmount() != null) {
                        etrCashPosition.setAmount(etrCashPosition.getAmount().negate());
                    }
                    if (etrCashPosition.getOrderId().lastIndexOf('R') != etrCashPosition.getOrderId().length() - 1) {
                        etrCashPosition.setOrderId(etrCashPosition.getOrderId() + "R");
                    }
                }
            }
            if (!etrSecPositionFlows.isEmpty()) {
                for (EtrSecPositionFlowDto etrSecPosition : etrSecPositionFlows) {
                    if (etrSecPosition.getAmount() != null) {
                        etrSecPosition.setAmount(etrSecPosition.getAmount().negate());
                    }
                    if (etrSecPosition.getTradeId().lastIndexOf('R') != etrSecPosition.getTradeId().length() - 1) {
                        etrSecPosition.setTradeId(etrSecPosition.getTradeId() + "R");
                    }
                }
            }
        }
        //逾期交易不需要维护持仓
        if(StringUtil.equals(DictCodeList.是否_是.getValue(), isOverdueDeal)){
        	etrPositionHoldDto.setPtlSecPositionFlows(null);
        }
        // 维护入库流水及持仓
        etrCommonCashPositionDealService.持久化流水Dto(etrPositionHoldDto);
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    @Override
    public CommonResult entrustStartExecute(EntrustCommonDto entrustCommonDto) {
        if (entrustCommonDto != null) {
            // 处理结果
            EtrPositionHoldDto etrPositionHoldDto = new EtrPositionHoldDto();
            // 持仓流水
            List<EtrSecPositionFlowDto> etrSecPositionFlows = new ArrayList<>();
            // 现金流水
            List<EtrCashPositionFlowDto> etrCashPositionFlows = new ArrayList<>();
            etrPositionHoldDto.setPtlCashPositionFlows(etrCashPositionFlows);
            etrPositionHoldDto.setPtlSecPositionFlows(etrSecPositionFlows);
            etrPositionHoldDto.setDealEtr(true);
            EtrEntrust etrEntrust = entrustCommonDto.getEtrEntrust();

            // 资产类型
            String finprodType = etrEntrust.getFinprodType2();

            Map<String, Object> vDateNato;
            String profitType = null;
            if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())) {
                vDateNato = baseAssetCashPositionCommMapper.selectVDateNato(etrEntrust.getFinprodId());
                /**
                 * 收益类型
                 */
                profitType = vDateNato == null ? null : (String)vDateNato.get("PROFIT_TYPE");
            }

            if (DictCodeListExt1.金融产品类型_投管用_债券.getValue().equals(finprodType)
                    && !(StringUtil.equals(etrEntrust.getPs(),DictCodeList.交易方向_申购.getValue())
                    		||StringUtil.equals(etrEntrust.getPs(),DictCodeListExt1.交易方向_债转股.getValue()))) {
                EtrSecCashPositionDealService.deal债券EntrustStart(entrustCommonDto, etrPositionHoldDto);
            }
            else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_存款.getValue())) {
                EtrDepoCashPositionDealService.deal存款EntrustStart(entrustCommonDto, etrPositionHoldDto);
            }
            else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())
                    && DictCodeList.收益类型_收益型.getValue().equals(profitType)) {
                EtrPlanRateCashPositionDealService.deal计划利率型CEntrustStart(entrustCommonDto, etrPositionHoldDto);
            }
            else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_拆入.getValue())
                     || StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_拆出.getValue())) {
                EtrLendCashPositionDealService.genLendEtrStartCashPosition(entrustCommonDto, etrPositionHoldDto);
            }
            else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())) {
                EtrProjectCashPositionDealService.deal项目类资产EntrustStart(entrustCommonDto, etrPositionHoldDto);
            }
            else if (StringUtil.equals(finprodType, DictCodeListExt2.金融产品类型_投管用_一级投标债券.getValue())) {
                EtrSecPrimaryBiddingCashPositionDealService.deal债券一级投标EntrustStart(entrustCommonDto, etrPositionHoldDto);
            }
            else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_基金.getValue())) {
                EtrFundCashPositionDealService.deal基金EntrustStart(entrustCommonDto, etrPositionHoldDto);
            }
            else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue())
                     || StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_债券借贷融出.getValue())) {
                EtrSecLendCashPositionDealService.deal债券借贷CEntrustStart(entrustCommonDto, etrPositionHoldDto);
            }
            else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())
                    && (DictCodeList.收益类型_净值型.getValue().equals(profitType) || DictCodeList.收益类型_货币型.getValue().equals(profitType))) {
                EtrNetCashPositionDealService.deal计划类EntrustStart(entrustCommonDto, etrPositionHoldDto);
            }
            else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
                     || StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
                EtrRepoCashPositionDealService.deal质押式回购EntrustStart(entrustCommonDto, etrPositionHoldDto);
            }
            else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue())
                     || StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())) {
                EtrBuyoutCashPositionDealService.deal买断式回购EntrustStart(entrustCommonDto, etrPositionHoldDto);
            }
            else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_股票.getValue())) {
                EtrSharesCashPositionDealService.deal股票EntrustStart(entrustCommonDto, etrPositionHoldDto);
            }
            //add by gaopeng 增加债转股委托资金头寸 start
            else if (DictCodeListExt1.金融产品类型_投管用_债券.getValue().equals(finprodType)
                    && (StringUtil.equals(etrEntrust.getPs(),DictCodeList.交易方向_申购.getValue())
                    		||StringUtil.equals(etrEntrust.getPs(),DictCodeListExt1.交易方向_债转股.getValue()))) {
                EtrConBondCashPositionDealService.deal可转债EntrustStart(entrustCommonDto, etrPositionHoldDto);
            }
          //add by gaopeng 增加债转股委托资金头寸 end
            EtrCommonCashPositionDealService.持久化流水Dto(etrPositionHoldDto);
        }
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    @Override
    public CommonResult entrustCloseExecute(String entrustId) {
        if (StringUtil.isNotEmpty(entrustId)) {
            EntrustCommonDto entrustCommonDto = EtrCommonCashPositionDealService.findEntrust(entrustId);
            if (entrustCommonDto != null && entrustCommonDto.getEtrEntrust()!=null) {
                // 处理结果
                EtrPositionHoldDto etrPositionHoldDto = new EtrPositionHoldDto();
                // 持仓流水
                List<EtrSecPositionFlowDto> etrSecPositionFlows = new ArrayList<>();
                // 现金流水
                List<EtrCashPositionFlowDto> etrCashPositionFlows = new ArrayList<>();
                etrPositionHoldDto.setPtlCashPositionFlows(etrCashPositionFlows);
                etrPositionHoldDto.setPtlSecPositionFlows(etrSecPositionFlows);
                etrPositionHoldDto.setDealEtr(true);
                EtrEntrust etrEntrust = entrustCommonDto.getEtrEntrust();

                // 资产类型
                String finprodType = etrEntrust.getFinprodType2();

                Map<String, Object> vDateNato;
                String profitType = null;
                if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())) {
                    vDateNato = baseAssetCashPositionCommMapper.selectVDateNato(etrEntrust.getFinprodId());
                    /**
                     * 收益类型
                     */
                    profitType = vDateNato == null ? null : (String)vDateNato.get("PROFIT_TYPE");
                }

                if (DictCodeListExt1.金融产品类型_投管用_债券.getValue().equals(etrEntrust.getFinprodType2())
                        && !(StringUtil.equals(etrEntrust.getPs(),DictCodeList.交易方向_申购.getValue())
                        		||StringUtil.equals(etrEntrust.getPs(),DictCodeListExt1.交易方向_债转股.getValue()))) {
                    EtrSecCashPositionDealService.deal债券EntrustClose(entrustCommonDto, etrPositionHoldDto);
                }
                else if (StringUtil.equals(etrEntrust.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_存款.getValue())) {
                    EtrDepoCashPositionDealService.deal存款EntrustClose(entrustCommonDto, etrPositionHoldDto);
                }
                else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())
                        && DictCodeList.收益类型_收益型.getValue().equals(profitType)) {
                    EtrPlanRateCashPositionDealService.deal计划利率型CEntrustClose(entrustCommonDto, etrPositionHoldDto);
                }
                else if (StringUtil.equals(etrEntrust.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_拆入.getValue())
                         || StringUtil.equals(etrEntrust.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_拆出.getValue())) {
                    EtrLendCashPositionDealService.genLendEtrCloseCashPosition(entrustCommonDto, etrPositionHoldDto);
                }
                else if (StringUtil.equals(etrEntrust.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())) {
                    EtrProjectCashPositionDealService.deal项目类资产EntrustClose(entrustCommonDto, etrPositionHoldDto);
                }
                else if (StringUtil.equals(etrEntrust.getFinprodType2(), DictCodeListExt2.金融产品类型_投管用_一级投标债券.getValue())) {
                    EtrSecPrimaryBiddingCashPositionDealService.deal债券一级投标EntrustEnd(entrustCommonDto, etrPositionHoldDto);
                }
                else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_基金.getValue())) {
                    EtrFundCashPositionDealService.deal基金EntrustClose(entrustCommonDto, etrPositionHoldDto);
                }
                else if (StringUtil.equals(etrEntrust.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue())
                         || StringUtil.equals(etrEntrust.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券借贷融出.getValue())) {
                    EtrSecLendCashPositionDealService.deal债券借贷CEntrustClose(entrustCommonDto, etrPositionHoldDto);
                }
                else if (StringUtil.equals(finprodType, DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())
                        && (DictCodeList.收益类型_净值型.getValue().equals(profitType) || DictCodeList.收益类型_货币型.getValue().equals(profitType))) {
                    EtrNetCashPositionDealService.deal计划类EntrustClose(entrustCommonDto, etrPositionHoldDto);
                }
                else if (StringUtil.equals(etrEntrust.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
                         || StringUtil.equals(etrEntrust.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
                    EtrRepoCashPositionDealService.deal质押式回购EntrustClose(entrustCommonDto, etrPositionHoldDto);
                }
                else if (StringUtil.equals(etrEntrust.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue())
                         || StringUtil.equals(etrEntrust.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())) {
                    EtrBuyoutCashPositionDealService.deal买断式回购EntrustClose(entrustCommonDto, etrPositionHoldDto);
                }
                else if (StringUtil.equals(etrEntrust.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_股票.getValue())) {
                    EtrSharesCashPositionDealService.deal股票EntrustClose(entrustCommonDto, etrPositionHoldDto);
                }
                else if (DictCodeListExt1.金融产品类型_投管用_债券.getValue().equals(etrEntrust.getFinprodType2())
                        && (StringUtil.equals(etrEntrust.getPs(),DictCodeList.交易方向_申购.getValue())||
                        		StringUtil.equals(etrEntrust.getPs(),DictCodeListExt1.交易方向_债转股.getValue()))) {
                    EtrConBondCashPositionDealService.deal可转债EntrustClose(entrustCommonDto, etrPositionHoldDto);
                }
                // 委托撤回或拒绝时，当前平台日处理实时持仓
                etrPositionHoldDto.getPtlCashPositionFlows().forEach(etrCashPositionFlowDto -> etrCashPositionFlowDto.setCdate(platformDateImpl.getSysDate()));
                etrPositionHoldDto.getPtlSecPositionFlows().forEach(etrSecPositionFlowDto -> etrSecPositionFlowDto.setCdate(platformDateImpl.getSysDate()));
                EtrCommonCashPositionDealService.持久化流水Dto(etrPositionHoldDto);
            }
        }
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    @Override
    public CommonResult executeBidResultsMaintenanceExecute(BidOrderBidPrice bidPriceDto) {
        // 处理结果
        EtrPositionHoldDto etrPositionHoldDto = new EtrPositionHoldDto();
        // 现金流水
        List<EtrCashPositionFlowDto> etrCashPositionFlows = new ArrayList<>();
        etrPositionHoldDto.setPtlCashPositionFlows(etrCashPositionFlows);
        // 债券一级投标中标结果维护
        EtrSecPrimaryBiddingCashPositionDealService.中标结果维护(bidPriceDto, etrPositionHoldDto);
        EtrCommonCashPositionDealService.持久化流水Dto(etrPositionHoldDto);
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    @Override
    public BigDecimal qryAvailablePosition(EtrSecPositionDto etrSecPositionDto) {
        return EtrCommonCashPositionDealService.selectAvailablePositionAmount(etrSecPositionDto);
    }

    @Override
    public BigDecimal qryCommandAvailableCash(PtlCashPositionFlowDto ptlCashPositionFlowDto) {
        return etrCommonCashPositionDealService.selectCommandAvailableCash(ptlCashPositionFlowDto);
    }

    @Override
    public BigDecimal qryCommandAdvanceCash(PtlCashPositionFlowDto ptlCashPositionFlowDto) {
        return etrCommonCashPositionDealService.selectCommandAdvanceCash(ptlCashPositionFlowDto);
    }

    // 标准券 入库
    @Override
    public CommonResult StandardBondExecute(List<EtrSecPositionFlowDto> list, DictCodeList 出入库方向) {
        EtrPositionHoldDto etrPositionHoldDto = new EtrPositionHoldDto();
        // 持仓流水
        List<EtrSecPositionFlowDto> etrSecPositionFlows = new ArrayList<>();
        // 现金流水
        List<EtrCashPositionFlowDto> etrCashPositionFlows = new ArrayList<>();
        etrPositionHoldDto.setPtlCashPositionFlows(etrCashPositionFlows);
        etrPositionHoldDto.setPtlSecPositionFlows(etrSecPositionFlows);
        EtrRepoCashPositionDealService.deal标准券_出入库CashPosition(list, 出入库方向, etrPositionHoldDto);
        // 维护入库流水及持仓
        EtrCommonCashPositionDealService.持久化流水Dto(etrPositionHoldDto);
        // 处理结果
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    /**
     * 产品端影响实时现金共通方法
     * 
     * @param cashInputDto
     * @return
     */
    public CommonResult productEtrCashPosition(PortfolioCashPositionsFlowInputDto cashInputDto) {
        String ps = cashInputDto.getTrdProductDeal().getPs();

        List<EtrCashPositionFlowDto> etrCashPositionFlows = new ArrayList<>();
        EtrPositionHoldDto etrPositionHoldDto = new EtrPositionHoldDto();
        etrPositionHoldDto.setPtlCashPositionFlows(etrCashPositionFlows);

        cashInputDto.getStlPayOrders().forEach(stlPayOrdersDto -> {
            // 现金流水
            EtrCashPositionFlowDto ret = new EtrCashPositionFlowDto();
            ret.setSeqno(IDGenerator.generate());
            ret.setCcy(cashInputDto.getTrdProductDeal().getCcy());
            ret.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
            ret.setChlFinprodId(cashInputDto.getTrdProductDeal().getChlId());
            ret.setOffsetFlag(DictCodeList.冲正标识_正常交易.getValue());
            ret.setPortfolioId(cashInputDto.getPortfolioId());
            ret.setCdate(cashInputDto.getTrdProductDeal().getTradeDate());
            String acctId = EtrCommonCashPositionDealService.select组合托管户(ret.getPortfolioId(), ret.getCcy(),
                DictCodeListExt1.资金账户类型_产品托管户.getValue());
            ret.setAcctId(acctId);
            ret.setOrderId(stlPayOrdersDto.getOrderId());

            if (StringUtil.equals(ps, DictCodeList.交易方向_产品认购.getValue())
                || StringUtil.equals(ps, DictCodeList.交易方向_产品申购.getValue())) {
                ret.setAmount(stlPayOrdersDto.getSettleAmt());
            }
            else if (StringUtil.equals(ps, DictCodeList.交易方向_产品赎回.getValue())
        			|| StringUtil.equals(ps, DictCodeList.交易方向_产品到期.getValue())
                     || StringUtil.equals(ps, DictCodeList.交易方向_产品现金分红.getValue())) {
                ret.setAmount(stlPayOrdersDto.getSettleAmt().negate());
            }
            if(cashInputDto.getNeedNegative()){//确定是撤销交易，交易金额取反
                ret.setAmount(stlPayOrdersDto.getSettleAmt().negate());
            }
            
            if(ret.getAmount()!=null && cashInputDto.getNeedNegative() && (StringUtil.equals(ps, DictCodeList.交易方向_产品赎回.getValue())
            		|| StringUtil.equals(ps, DictCodeList.交易方向_产品到期.getValue())
    				|| StringUtil.equals(ps, DictCodeList.交易方向_产品现金分红.getValue()))){//fixed BSTOZ-858bug
            	ret.setAmount(ret.getAmount().negate());//赎回撤销交易金额取反  add by zhouxiuping at 2019/10/31
            }
            
            // 添加现金流水到目标dto
            etrPositionHoldDto.getPtlCashPositionFlows().add(ret);
        });
        // 维护入库流水及持仓
        EtrCommonCashPositionDealService.持久化流水Dto(etrPositionHoldDto);
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    @Override
    public CommonResult portfolioEtrCashPosition(PtlPositionHoldDto ptlPositionHoldDto, boolean 是否插入母组合持仓) {
        List<EtrCashPositionFlowDto> etrCashPositionFlows = new ArrayList<>();
        EtrPositionHoldDto etrPositionHoldDto = new EtrPositionHoldDto();
        etrPositionHoldDto.setPtlCashPositionFlows(etrCashPositionFlows);
        etrPositionHoldDto.setPtlSecPositionFlows(new ArrayList<>());

        if (ptlPositionHoldDto != null && ptlPositionHoldDto.getPtlCashPositionFlows() != null && !ptlPositionHoldDto.getPtlCashPositionFlows().isEmpty()) {
            ptlPositionHoldDto.getPtlCashPositionFlows().forEach(cashInputDto -> {
                EtrCashPositionFlowDto ret = new EtrCashPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(ret, cashInputDto);
                ret.setSeqno(IDGenerator.generate());
                ret.setOffsetFlag(DictCodeList.冲正标识_正常交易.getValue());
                ret.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
                etrPositionHoldDto.getPtlCashPositionFlows().add(ret);
                etrPositionHoldDto.setDealEtr(是否插入母组合持仓);
            });
        }


        if (ptlPositionHoldDto != null && ptlPositionHoldDto.getPtlSecPositionFlows() != null && !ptlPositionHoldDto.getPtlSecPositionFlows().isEmpty()) {
            ptlPositionHoldDto.getPtlSecPositionFlows().forEach(secInputDto -> {
                EtrSecPositionFlowDto ret = new EtrSecPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(ret, secInputDto);
                ret.setSeqno(IDGenerator.generate());
                ret.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
                etrPositionHoldDto.getPtlSecPositionFlows().add(ret);
                etrPositionHoldDto.setDealEtr(是否插入母组合持仓);
            });
        }

        // 维护入库流水及持仓
        EtrCommonCashPositionDealService.持久化流水Dto(etrPositionHoldDto);

        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    /**
     * 股票存续期撤销
     * @param tradeId
     * @return
     */
    public CommonResult revokePosition(String tradeId) {
        EtrPositionHoldDto etrPositionHoldDto = EtrCommonCashPositionDealService.getRevokePosition(tradeId);
        // 维护入库流水及持仓
        EtrCommonCashPositionDealService.持久化流水Dto(etrPositionHoldDto);
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }
}
