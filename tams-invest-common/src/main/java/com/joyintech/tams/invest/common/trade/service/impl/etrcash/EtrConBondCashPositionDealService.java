package com.joyintech.tams.invest.common.trade.service.impl.etrcash;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.TrdFinProductBarter;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.constant.*;
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
 * @program: 2.1.1
 * @description:可转债：指令头寸更新
 * @author: gejiaao
 * @create: 2019-11-19 15:05
 **/
@Service
public class EtrConBondCashPositionDealService extends EtrCashPositionBaseService {

    static BigDecimal 债券面值 = new BigDecimal(100);


    public static void deal可转债EntrustStart(EntrustCommonDto entrustCommonDto, EtrPositionHoldDto etrPositionHoldDto) {
        if (entrustCommonDto != null) {
            EtrEntrust etrEntrust = entrustCommonDto.getEtrEntrust();
            String ps = etrEntrust.getPs();
//            BigDecimal 委托全价 = gen委托全价(etrEntrust.getEntrustId(), entrustCommonDto.getEtrEntrust().getEntrustDate());
//            // 委托生成的现金流水
//            EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDtoFromEtr(
//                    entrustCommonDto);
//            String acctId = gen账号(etrEntrust.getChlId(), etrEntrust.getCcy(),
//                    entrustCommonDto.getEtrEntrustAdd().getSecAcctId(),etrEntrust.getPortfolioId(), entrustCommonDto.getEtrEntrustAdd().getPurchModle(), etrCashPositionFlowDto);
//            // 减少委托券面总额*委托全价/100
//            etrCashPositionFlowDto.setAmount(etrEntrust.getEntrustNum().negate().multiply(债券面值));
//            etrCashPositionFlowDto.setAcctId(acctId);
//            etrCashPositionFlowDto.setOffsetId(entrustCommonDto.getEtrEntrust().getEntrustId());
//            etrCashPositionFlowDto.setCdate(etrEntrust.getEntrustDate());
//            etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
           if (DictCodeListExt1.交易方向_债转股.getValue().equals(ps)) {
                // 减少委托券面总额/100
                EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDtoFromEtr(
                    entrustCommonDto);
                etrSecPositionFlowDto.setAmount(etrEntrust.getEntrustNum().negate());
                etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
            }
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
            }
            else{
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
     * 获取现金流水需要的账号
     *
     * @param chlId                  通道代码
     * @param ccy                    币种
     * @param secManageAcctId        证券托管户
     * @param portfolioId            组合代码
     * @param purchModle             申购方式
     * @param etrCashPositionFlowDto 流水dto
     * @return 账号
     */
    private static String gen账号(String chlId, String ccy, String secManageAcctId, String portfolioId, String purchModle,
                                EtrCashPositionFlowDto etrCashPositionFlowDto) {
        if (StringUtil.isNotEmpty(chlId)) {
            etrCashPositionFlowDto.setChlFinprodId(chlId);
            return EtrCommonCashPositionDealService.select通道资金账户(chlId);
        } else {
            // 交易所无通道
            etrCashPositionFlowDto.setChlFinprodId(null);
            //申购方式为网上申购 取资金账户，网下取组合托管户
            if (StringUtil.equals(purchModle,DictCodeListExt2.申购方式_网上申购.getValue())){
                return EtrCommonCashPositionDealService.select资金账户(secManageAcctId, ccy);
            }else {
                return EtrCommonCashPositionDealService.select组合托管户(portfolioId, ccy, DictCodeListExt2.资金账户类型_托管户.getValue());
            }
        }
    }

    /**
     * 可转债 以持仓数量（张数）统计
     */
    public static void deal可转债CashPosition(BaseTradeProductDealDto finProductTradeDto, DictCodeList 持仓类型,
                                           EtrPositionHoldDto etrPositionHoldDto,String 交易流程状态) {
            if(StringUtil.equals(finProductTradeDto.getTrdProductDeal().getPs(), DictCodeListExt3.交易方向_债转股.getValue())){
            	//流程中减少债券指令可用持仓
            	if(StringUtil.isEmpty(finProductTradeDto.getTrdProductDealAdd().getEntrustId())&&DictCodeListExt1.交易流程状态_流程中.getValue().equals(交易流程状态)){
            		TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();
            		 // 交易生成的持仓流水 (债券)
            		EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
                            finProductTradeDto);
            		etrSecPositionFlowDto.setAmount(deal.getShareAmt().negate());
            		etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);           		
            	}
            	//交易生效（无委托）	增加股票指令可用持仓
            	if(StringUtil.isEmpty(finProductTradeDto.getTrdProductDealAdd().getEntrustId())&&DictCodeListExt1.交易流程状态_有效.getValue().equals(交易流程状态)){
            		TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();
                    TrdFinProductBarter barterDeal = finProductTradeDto.getTrdFinProductBarterList().get(0);
                    // 交易生成的持仓流水 (股票)
                    EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
                            finProductTradeDto);
                    etrSecPositionFlowDto.setAmount(barterDeal.getShareAmt());
                    etrSecPositionFlowDto.setFinprodId(barterDeal.getFinprodId());
                    etrSecPositionFlowDto.setCdate(deal.getTradeDate());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);     		
            	}
            	//交易撤销（无委托）	更新债券、股票指令可用持仓
            	if(StringUtil.isEmpty(finProductTradeDto.getTrdProductDealAdd().getEntrustId())&&DictCodeListExt1.交易流程状态_撤销.getValue().equals(交易流程状态)){
            		TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();
                    TrdFinProductBarter barterDeal = finProductTradeDto.getTrdFinProductBarterList().get(0);
                    // 交易生成的持仓流水 (债券)
            		EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
                            finProductTradeDto);
            		etrSecPositionFlowDto.setAmount(deal.getShareAmt().negate());
            		etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);    
                    // 交易生成的持仓流水 (股票)
                    EtrSecPositionFlowDto etrSecPositionFlowDto1 = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
                            finProductTradeDto);
                    etrSecPositionFlowDto1.setAmount(barterDeal.getShareAmt());
                    etrSecPositionFlowDto1.setFinprodId(barterDeal.getFinprodId());
                    etrSecPositionFlowDto1.setCdate(deal.getTradeDate());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto1);     		
            	}
            	
//                交易生效（有委托）	增加股票指令可用持仓		增加股票指令可用持仓
                if(StringUtil.isNotEmpty(finProductTradeDto.getTrdProductDealAdd().getEntrustId())&&持仓类型.equals(DictCodeList.持仓类型_实际)){
                    TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();
                    TrdProductDealAdd dealAdd = finProductTradeDto.getTrdProductDealAdd();
                    TrdFinProductBarter barterDeal = finProductTradeDto.getTrdFinProductBarterList().get(0);
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
                    // 交易生成的持仓流水 (股票)
                    EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
                            finProductTradeDto);
                    etrSecPositionFlowDto.setAmount(barterDeal.getShareAmt());
                    etrSecPositionFlowDto.setFinprodId(barterDeal.getFinprodId());
                    etrSecPositionFlowDto.setCdate(deal.getTradeDate());
                    etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                    if(stlPayOrdersDtos!= null && stlPayOrdersDtos.size()>0){
                        // 交易生成的现金流水
                        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
                                finProductTradeDto);
                        etrCashPositionFlowDto.setOrderId(finProductTradeDto.getTrdProductDeal().getTradeId());
                        etrCashPositionFlowDto.setAcctId(gen账号(deal.getChlId(),deal.getCcy(),deal.getSecManageAcctId(), dealAdd.getPortfolioId(), dealAdd.getPurchModle(),etrCashPositionFlowDto));
                        etrCashPositionFlowDto.setAmount(stlPayOrdersDtos.get(0).getSettleAmt());
                        etrCashPositionFlowDto.setCdate(stlPayOrdersDtos.get(0).getSettleDate());
                        etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                    }
                }
            }
            
            if(StringUtil.equals(finProductTradeDto.getTrdProductDeal().getPs(), DictCodeList.交易方向_申购确认.getValue())){
//              交易生效 增加债券持仓             
                  TrdProductDeal deal = finProductTradeDto.getTrdProductDeal();
                  TrdProductDealAdd dealAdd = finProductTradeDto.getTrdProductDealAdd();
//                  TrdFinProductBarter barterDeal = finProductTradeDto.getTrdFinProductBarterList().get(0);
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
                  // 交易生成的持仓流水 (债券)
                  if(持仓类型.equals(DictCodeList.持仓类型_实际)){
                  EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDto(
                          finProductTradeDto);
                  etrSecPositionFlowDto.setAmount(deal.getShareAmt());
                  etrSecPositionFlowDto.setFinprodId(deal.getFinprodId());
                  etrSecPositionFlowDto.setCdate(deal.getTradeDate());
                  etrSecPositionFlowDto.setSecAcctId(deal.getSecManageAcctId());
                  etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
                  }
                  if(DictCodeListExt1.交易流程状态_流程中.getValue().equals(交易流程状态) || DictCodeListExt1.交易流程状态_撤销.getValue().equals(交易流程状态)){
                  /**add by taoran 20200303 委托申购确认更新实时现金 start*/
                  if(stlPayOrdersDtos!= null && stlPayOrdersDtos.size()>0 && StringUtil.isNotEmpty(finProductTradeDto.getEntrustId())
                		  && DictCodeListExt2.申购类型_普通申购.getValue().equals(finProductTradeDto.getPurchType())){
                      // 交易生成的现金流水
                      EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
                              finProductTradeDto);
                      etrCashPositionFlowDto.setOrderId(finProductTradeDto.getTrdProductDeal().getTradeId());
                      etrCashPositionFlowDto.setAcctId(gen账号(deal.getChlId(),deal.getCcy(),deal.getSecManageAcctId(), dealAdd.getPortfolioId(), dealAdd.getPurchModle(),etrCashPositionFlowDto));
                      etrCashPositionFlowDto.setAmount(finProductTradeDto.getSecAmount());		//实时现金 申购券面 - 中签券面
                      etrCashPositionFlowDto.setCdate(stlPayOrdersDtos.get(0).getSettleDate());
                      etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                  }
                  /**add by taoran 20200303 委托申购确认更新实时现金 end*/
                  
                  /**add by yujiaping 20210409 申购确认更新资金账户指令可用现金 end*/
                  if(stlPayOrdersDtos!= null && stlPayOrdersDtos.size()>0 && !StringUtil.isNotEmpty(finProductTradeDto.getEntrustId())
                		  && DictCodeListExt2.申购类型_普通申购.getValue().equals(finProductTradeDto.getPurchType())){                  
                      EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDto(
                              finProductTradeDto);
                      etrCashPositionFlowDto.setOrderId(finProductTradeDto.getTrdProductDeal().getTradeId());
                      etrCashPositionFlowDto.setAcctId(gen账号(deal.getChlId(),deal.getCcy(),deal.getSecManageAcctId(), dealAdd.getPortfolioId(), dealAdd.getPurchModle(),etrCashPositionFlowDto));
                      etrCashPositionFlowDto.setAmount(stlPayOrdersDtos.get(0).getSettleAmt().negate());
                      etrCashPositionFlowDto.setCdate(stlPayOrdersDtos.get(0).getSettleDate());
                      etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
                  }
                  /**add by yujiaping 20210409 申购确认更新资金账户指令可用现金 end*/
              }
          }

    }

    public static void deal可转债EntrustClose(EntrustCommonDto entrustCommonDto, EtrPositionHoldDto etrPositionHoldDto) {
        EtrEntrust etrEntrust = entrustCommonDto.getEtrEntrust();
        BigDecimal etrFinish = EtrCommonCashPositionDealService.getEtrFinishAmt(etrEntrust.getEntrustId());
        String ps = etrEntrust.getPs();
//        // 可用现金 关闭日增加剩余委托券面总额*委托全价/100
//        EtrCashPositionFlowDto etrCashPositionFlowDto = EtrCommonCashPositionDealService.getEtrCashPositionFlowDtoFromEtr(
//                entrustCommonDto);
//        String acctId = gen账号(etrEntrust.getChlId(), etrEntrust.getCcy(),
//                entrustCommonDto.getEtrEntrustAdd().getSecAcctId(), etrEntrust.getPortfolioId(), entrustCommonDto.getEtrEntrustAdd().getPurchModle(), etrCashPositionFlowDto);
//        // 关闭日增加剩余委托券面总额*委托全价/100
//        etrCashPositionFlowDto.setAmount(
//                EtrCommonCashPositionDealService.getEtrSurplusAmt(etrEntrust.getEntrustId(), etrEntrust.getPortfolioId()));
//        etrCashPositionFlowDto.setAcctId(acctId);
//        etrCashPositionFlowDto.setCdate(etrEntrust.getEntrustDate());
//        etrPositionHoldDto.getPtlCashPositionFlows().add(etrCashPositionFlowDto);
        if (DictCodeListExt1.交易方向_债转股.getValue().equals(ps)) {
            EtrSecPositionFlowDto etrSecPositionFlowDto = EtrCommonCashPositionDealService.getEtrSecPositionFlowDtoFromEtr(
                entrustCommonDto);
            // 可用持仓 关闭日增加剩余委托券面总额/100
            etrSecPositionFlowDto.setAmount(ArithmeticUtil.subtract(etrEntrust.getEntrustNum(), etrFinish));
            etrPositionHoldDto.getPtlSecPositionFlows().add(etrSecPositionFlowDto);
        }
    }
}
