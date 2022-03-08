package com.joyintech.service.trade.entrustmsgbymq.service;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.service.trade.entrustmsgbymq.dto.TrdMoreConvrate;
import com.joyintech.service.trade.entrustmsgbymq.dto.TrdTempDto;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.common.base.dao.EtrEntrustAddMapper;
import com.joyintech.tams.common.base.dao.FinTradeProductMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.position.service.CommonPositionEngineService;
import com.joyintech.tams.invest.common.trade.dto.PortfolioCalculateDto;
import com.joyintech.tams.invest.common.trade.service.TrdCommonBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.joyintech.tams.constant.DictCodeList.是否_否;
import static com.joyintech.tams.invest.common.position.constant.PositionCommonConstant.*;

/**
 * 成交回报头寸service
 */
@Service
public class PositionRequestTradeReportService {
    private static final Logger logger = LoggerFactory.getLogger(PositionRequestTradeReportService.class);

    @Autowired
    private CommonPositionEngineService commonPositionEngineService;

    @Autowired
    private TrdCommonBaseService trdCommonBaseService;

    @Autowired
    private EtrEntrustAddMapper etrEntrustAddMapper;

    @Autowired
    private FinTradeProductMapper finTradeProductMapper;

    @Autowired(required = false)
    @Qualifier("simpleRestTemplate")
    protected JoyinRestTemplate simpleRestTemplate;

    public static final String STANDAR_SEC_ID = "XN_PRODID999";

    /**
     * 发送成交回报头寸请求
     * @param trdTempDto
     * @param detailFile
     */
    public void sendTradeReportPositionRequest(TrdTempDto trdTempDto) {
        logger.debug("【发送成交回报头寸请求】trdTempDto: {}", JSON.toJSONString(trdTempDto));
        Map<String, Object> resultMap = new HashMap<>();
        String tradePlat = trdTempDto.getTradePlat();
        String finprodType = trdTempDto.getFinprodType2();
        String ps = trdTempDto.getPs();
        String type6 = commonPositionEngineService.getType6(trdTempDto.getFinprodId());
        if (DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue().equals(type6)) {
            finprodType += "-" + type6;
        }
        String interType = commonPositionEngineService.getPositionInterType(POSITION_TYPE_TRD, tradePlat, finprodType, ps, DictCodeListExt3.业务动作_成交回报.getValue(), trdTempDto.getClass().getName());
        if (INTERF_TYPE_BOND002.equals(interType)) {
            this.bond002(trdTempDto, resultMap);
        } else if (INTERF_TYPE_BOND003.equals(interType)) {
            this.bond003(trdTempDto, resultMap);
        } else if (INTERF_TYPE_REPO003.equals(interType)) {
            this.repo003(trdTempDto, resultMap);
        } else if (INTERF_TYPE_FUND001.equals(interType)) {
            this.fund001(trdTempDto, resultMap);
        } else if (INTERF_TYPE_STOCK001.equals(interType)) {
            this.stock001(trdTempDto, resultMap);
        }
        PositionHttpRequest.send(resultMap);
    }

    /**
     * 场内现券-指令
     *
     * @param entity
     * @param resultMap
     */
    private void bond002(TrdTempDto entity, Map<String, Object> resultMap) {
        resultMap.put("interfaceType", INTERF_TYPE_BOND002);
        String busiType = commonPositionEngineService.getPositionBusiType(entity.getFinprodType2(), entity.getPs(),
                entity.getAssetType());
        // 业务类型
        resultMap.put("busiType", busiType);
        // 业务动作
        resultMap.put("busiAct", DictCodeListExt3.业务动作_成交回报.getValue());
        resultMap.put("ps", entity.getPs());
        String deliveryType = commonPositionEngineService.getDeliveryType(entity.getFinprodId());
        resultMap.put("settleSpeed", parseSettleSpreed(deliveryType));
        resultMap.put("bidResult", 是否_否.getValue());
        resultMap.put("purchModle", null);
        resultMap.put("tradePlace", entity.getTradeMarket());
        resultMap.put("sharesTradePlatform", this.getSharesTradePlatform(entity));
        resultMap.put("deliveryType", deliveryType);
        resultMap.put("decisionAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("systemAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("workFlowId", null);
        resultMap.put("cdate", entity.getTradeDate());
        resultMap.put("ccy", entity.getCcy());
        resultMap.put("finprodId", entity.getFinprodId());
        resultMap.put("bTotalTradeId", null);

        Map<String,Object> paraMap = new HashMap<>();
        paraMap.put("fTradeId", entity.getfTradeId());
        List<PortfolioCalculateDto> list = trdCommonBaseService.queryPortfolioCalculate(paraMap);

        List<Map<String, Object>> ptlList = new ArrayList<>();
        for (PortfolioCalculateDto dto:list) {
            Map<String, Object> pltMap = new HashMap<>();
            pltMap.put("portfolioId", dto.getPortfolioId());
            pltMap.put("tradeId", entity.getTradeId());
            pltMap.put("etrId", entity.getEntrustId());
            pltMap.put("ordEntrustId", null);
            pltMap.put("etrSecAmount", dto.getEtrSecAmount());
            pltMap.put("ordId", entity.getOrderId());
            pltMap.put("ordSecAmount", dto.getOrdSecAmount());
            pltMap.put("cashAcctId", dto.getCashAcctId());
            pltMap.put("secAcctId", entity.getSecAcctId());
            pltMap.put("secAmount", dto.getSecAmount());
            pltMap.put("cashAmount", dto.getCashAmount());
            pltMap.put("invAim", entity.getInvAim());
            pltMap.put("integrateSecAmount", null);
            pltMap.put("ownSecAmount", dto.getOwnSecAmount());
            ptlList.add(pltMap);
        }
        resultMap.put("ptlList", ptlList);
    }

    /**
     * 可转债-指令
     *
     * @param entity
     * @param resultMap
     */
    private void bond003(TrdTempDto entity, Map<String, Object> resultMap) {
        resultMap.put("interfaceType", INTERF_TYPE_BOND003);
        String busiType = commonPositionEngineService.getPositionBusiType(entity.getFinprodType2(), entity.getPs(),
                entity.getAssetType());
        resultMap.put("busiType", busiType);
        // 业务动作
        resultMap.put("busiAct", DictCodeListExt3.业务动作_成交回报.getValue());
        resultMap.put("ps", entity.getPs());
        String deliveryType = commonPositionEngineService.getDeliveryType(entity.getFinprodId());
        resultMap.put("settleSpeed", parseSettleSpreed(deliveryType));
        resultMap.put("purchModle", null);
        resultMap.put("tradePlace", entity.getTradeMarket());
        resultMap.put("sharesTradePlatform", this.getSharesTradePlatform(entity));
        resultMap.put("deliveryType", deliveryType);
        resultMap.put("decisionAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("systemAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("workFlowId", null);
        resultMap.put("cdate", entity.getSettleDate());
        resultMap.put("ccy", entity.getCcy());
        resultMap.put("bTotalTradeId", null);

        Map<String,Object> paraMap = new HashMap<>();
        paraMap.put("fTradeId", entity.getfTradeId());
        List<PortfolioCalculateDto> list = trdCommonBaseService.queryPortfolioCalculate(paraMap);
        List<Map<String, Object>> ptlCashList = new ArrayList<>(4);
        List<Map<String, Object>> bondSecList = new ArrayList<>(4);
        for (PortfolioCalculateDto dto : list){
            Map<String, Object> ptlCash = new HashMap<>(8);
            ptlCash.put("portfolioId", dto.getPortfolioId());
            ptlCash.put("cashAcctId", dto.getCashAcctId());
            ptlCash.put("tradeId", entity.getTradeId());
            ptlCash.put("etrId", entity.getEntrustId());
            ptlCash.put("cashAmount", dto.getCashAmount());
            ptlCash.put("etrSecAmount", dto.getEtrSecAmount());
            ptlCash.put("ordId", entity.getOrderId());
            ptlCash.put("ordSecAmount", dto.getOrdSecAmount());
            ptlCashList.add(ptlCash);

            Map<String, Object> bondSec = new HashMap<>();
            bondSec.put("portfolioId", dto.getPortfolioId());
            bondSec.put("finprodId", entity.getFinprodId());
            bondSec.put("secAcctId", dto.getSecAcctId());
            bondSec.put("tradeId", entity.getTradeId());
            bondSec.put("etrId", entity.getEntrustId());
            bondSec.put("integrateSecAmount", null);
            bondSec.put("ownSecAmount", dto.getOwnSecAmount());
            bondSec.put("secAmount", dto.getSecAmount());
            bondSecList.add(bondSec);
        }
        resultMap.put("ptlCashList", ptlCashList);
        resultMap.put("bondSecList", bondSecList);
    }

    /**
     * 质押式通用回购-指令
     *
     * @param entity
     * @param resultMap
     */
    private void repo003(TrdTempDto entity, Map<String, Object> resultMap) {
        resultMap.put("interfaceType", INTERF_TYPE_REPO003);
        String busiType = commonPositionEngineService.getPositionBusiType(entity.getFinprodType2(), entity.getPs(),
                entity.getAssetType());
        resultMap.put("busiType", busiType);
        // 业务动作
        resultMap.put("busiAct", DictCodeListExt3.业务动作_成交回报.getValue());
        resultMap.put("ps", entity.getPs());
        resultMap.put("tradePlace", entity.getTradeMarket());
        resultMap.put("settleSpeed", null);
        resultMap.put("sharesTradePlatform", null);
        resultMap.put("deliveryType", null);
        resultMap.put("decisionAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("systemAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("workFlowId", null);
        resultMap.put("cdate", entity.getTradeDate());
        resultMap.put("ccy", entity.getCcy());
        resultMap.put("totalTradeId", null);
        resultMap.put("bTotalTradeId", null);

        Map<String,Object> paraMap = new HashMap<>();
        paraMap.put("fTradeId", entity.getfTradeId());
        List<PortfolioCalculateDto> list = trdCommonBaseService.queryPortfolioCalculate(paraMap);
        List<Map<String, Object>> ptlList = new ArrayList<>(4);
        List<Map<String, Object>> ptlSecList = new ArrayList<>(4);
        for (PortfolioCalculateDto dto : list){
            Map<String, Object> plt = new HashMap<>(8);
            plt.put("portfolioId", dto.getPortfolioId());
            plt.put("cashAcctId", dto.getCashAcctId());
            plt.put("secAcctId", dto.getSecAcctId());
            plt.put("tradeId", entity.getTradeId());
            plt.put("etrId", entity.getOrderId());
            plt.put("ordEntrustId", null);
            plt.put("standardSecId", STANDAR_SEC_ID);
            plt.put("standardSecAmount", dto.getStandardSecAmount());
            plt.put("cashAmount", dto.getCashAmount());
            TrdMoreConvrate moreConvrate = this.getMoreConvrate(dto.getCashAcctId());
            if (Objects.nonNull(moreConvrate)) {
                plt.put("reConversionRate", moreConvrate.getMoreConvrate());
            }
            ptlList.add(plt);

            if(DictCodeListExt3.交易方向_质押券入库.getValue().equals(entity.getPs())
                    || DictCodeListExt3.交易方向_质押券出库.getValue().equals(entity.getPs())){
                Map<String, Object> ptlSec = new HashMap<>();
                ptlSec.put("portfolioId", dto.getPortfolioId());
                ptlSec.put("finprodId", entity.getFinprodId());
                ptlSec.put("secAcctId", dto.getSecAcctId());
                ptlSec.put("tradeId", entity.getTradeId());
                ptlSec.put("etrId", entity.getOrderId());
                ptlSec.put("integrateSecAmount", dto.getIntegrateSecAmount());
                ptlSec.put("ownSecAmount", dto.getOwnSecAmount());
                ptlSec.put("secAmount", dto.getSecAmount());
                ptlSecList.add(ptlSec);
            }
        }
        resultMap.put("ptlList", ptlList);
        resultMap.put("ptlSecList", ptlSecList);
    }

    /**
     * 基金-指令
     *
     * @param entity
     * @param resultMap
     */
    private void fund001(TrdTempDto entity, Map<String, Object> resultMap) {
        resultMap.put("interfaceType", INTERF_TYPE_FUND001);
        String busiType = commonPositionEngineService.getPositionBusiType(entity.getFinprodType2(), entity.getPs(),
                entity.getAssetType());
        resultMap.put("busiType", busiType);
        // 业务动作
        resultMap.put("busiAct", DictCodeListExt3.业务动作_成交回报.getValue());
        resultMap.put("ps", entity.getPs());
        resultMap.put("settleSpeed", null);
        resultMap.put("tradePlace", entity.getTradeMarket());
        resultMap.put("sharesTradePlatform", this.getSharesTradePlatform(entity));
        resultMap.put("deliveryType", null);
        resultMap.put("decisionAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("systemAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("workFlowId", null);
        resultMap.put("sameDayTurn", 是否_否.getValue());
        resultMap.put("ETFRedemptionMethod", null);
        resultMap.put("cdate", entity.getTradeDate());
        resultMap.put("ccy", entity.getCcy());
        resultMap.put("bTotalTradeId", null);

        Map<String,Object> paraMap = new HashMap<>();
        paraMap.put("fTradeId", entity.getfTradeId());
        List<PortfolioCalculateDto> list = trdCommonBaseService.queryPortfolioCalculate(paraMap);
        List<Map<String, Object>> ptlList = new ArrayList<>(4);
        for (PortfolioCalculateDto dto : list){
            Map<String, Object> plt = new HashMap<>(8);
            plt.put("portfolioId", dto.getPortfolioId());
            plt.put("tradeId", entity.getTradeId());
            plt.put("etrId", entity.getEntrustId());
            plt.put("finprodId", entity.getFinprodId());
            plt.put("cashAcctId", dto.getCashAcctId());
            plt.put("secAcctId", dto.getSecAcctId());
            plt.put("secAmount", dto.getSecAmount());
            plt.put("cashAmount", dto.getCashAmount());
            plt.put("ordEntrustId", null);
            plt.put("etrSecAmount", dto.getEtrSecAmount());
            plt.put("ordId", entity.getOrderId());
            plt.put("ordSecAmount", dto.getOrdSecAmount());
            ptlList.add(plt);
        }
        resultMap.put("ptlList", ptlList);
    }

    /**
     * 股票指令
     *
     * @param entity
     * @param resultMap
     */
    private void stock001(TrdTempDto entity, Map<String, Object> resultMap) {
        resultMap.put("interfaceType", INTERF_TYPE_STOCK001);
        String busiType = commonPositionEngineService.getPositionBusiType(entity.getFinprodType2(), entity.getPs(),
                entity.getAssetType());
        resultMap.put("busiType", busiType);
        // 业务动作
        resultMap.put("busiAct", DictCodeListExt3.业务动作_成交回报.getValue());
        resultMap.put("ps", entity.getPs());
        resultMap.put("tradePlace", entity.getTradeMarket());
        resultMap.put("settleSpeed", null);
        resultMap.put("sharesTradePlatform", this.getSharesTradePlatform(entity));
        resultMap.put("decisionAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("systemAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("workFlowId", null);
        resultMap.put("cdate", entity.getTradeDate());
        resultMap.put("ccy", entity.getCcy());
        resultMap.put("bTotalTradeId", null);

        Map<String,Object> paraMap = new HashMap<>();
        paraMap.put("fTradeId", entity.getfTradeId());
        List<PortfolioCalculateDto> list = trdCommonBaseService.queryPortfolioCalculate(paraMap);
        List<Map<String, Object>> ptlList = new ArrayList<>(4);
        List<Map<String, Object>> ptlRoList = new ArrayList<>(4);
        for (PortfolioCalculateDto dto : list){
            Map<String, Object> plt = new HashMap<>(8);
            plt.put("portfolioId", dto.getPortfolioId());
            plt.put("finprodId", entity.getFinprodId());
            plt.put("tradeId", entity.getTradeId());
            plt.put("etrId", entity.getEntrustId());
            plt.put("etrSecAmount", dto.getEtrSecAmount());
            plt.put("ordId", entity.getOrderId());
            plt.put("ordSecAmount", dto.getOrdSecAmount());
            plt.put("ordEntrustId", null);
            plt.put("cashAcctId", dto.getCashAcctId());
            plt.put("secAcctId", dto.getSecAcctId());
            plt.put("secAmount", dto.getSecAmount());
            plt.put("cashAmount", dto.getCashAmount());
            ptlList.add(plt);

            if(DictCodeListExt2.交易方向_配股.getValue().equals(entity.getPs())
                    || DictCodeList.交易方向_申购.getValue().equals(entity.getPs())){
                Map<String, Object> ptlRo = new HashMap<>();
                ptlRo.put("portfolioId", dto.getPortfolioId());
                ptlRo.put("finprodId", entity.getFinprodId());
                ptlRo.put("tradeId", entity.getTradeId());
                ptlRo.put("etrId", entity.getEntrustId());
                ptlRo.put("etrSecAmount", dto.getEtrSecAmount());
                ptlRo.put("ordId", entity.getOrderId());
                ptlRo.put("ordSecAmount", dto.getOrdSecAmount());
                ptlRo.put("ordEntrustId", null);
                ptlRo.put("cashAcctId", dto.getCashAcctId());
                ptlRo.put("secAcctId", dto.getSecAcctId());
                ptlRo.put("secAmount", dto.getSecAmount());
                ptlRo.put("cashAmount", dto.getCashAmount());
                ptlRoList.add(ptlRo);
            }
        }
        resultMap.put("ptlList", ptlList);
        resultMap.put("ptlRoList", ptlRoList);
    }

    /**
     * 解析清算速度
     * <p>
     * 清算速度	见数据字典：清算速度
     * "IF 变量.结算方式 = 数据字典.DELIVERY_TYPE.净额结算 THEN 数据字典.清算速度.T+1
     * ELSE 数据字典.清算速度.T+0
     *
     * @param deliveryType 封装集合
     * @return 清算速度
     */
    private String parseSettleSpreed(String deliveryType) {
        String settleSpreed = BussinessDictCodeList.清算速度_T加0.getValue();
        if (DictCodeListExt3.结算方式_头寸引擎_净额结算.getValue().equals(deliveryType)) {
            settleSpreed = BussinessDictCodeList.清算速度_T加1.getValue();
        }
        return settleSpreed;
    }

    /**
     * 查询再折算率
     * @param acctNo
     * @return
     */
    private TrdMoreConvrate getMoreConvrate(String acctNo){
        // 再折算率	reConversionRate "调用【再折算率查询】参数：资金账户 = 指令资金账户
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("acctNo", acctNo);
        Map<String, Object> res = simpleRestTemplate.postForObject(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/InterfSysTrdConfig/getMoreConvrate", param, Map.class);
        if (res != null && res.get("data") != null) {
            List<TrdMoreConvrate> list = (List<TrdMoreConvrate>) res.get("data");
            if (list != null && list.size() > 0) {
                return JSON.parseObject(JSON.toJSONString(list.get(0)), TrdMoreConvrate.class);
            }
        }
        return null;
    }

    private String getSharesTradePlatform(TrdTempDto entity){
        EtrEntrustAdd etrEntrustAdd = etrEntrustAddMapper.selectByPrimaryKey(entity.getEntrustId());
        if((DictCodeList.交易平台_大宗交易平台.getValue().equals(entity.getTradePlat())
                ||DictCodeList.交易平台_综合协议交易平台.getValue().equals(entity.getTradePlat()))
                &&(DictCodeList.交易方向_买入.getValue().equals(entity.getPs())
                ||DictCodeList.交易方向_卖出.getValue().equals(entity.getPs()))
                &&(DictCodeListExt3.申报类型_意向申报.getValue().equals(etrEntrustAdd.getDealMode())
                ||DictCodeListExt3.申报类型_定价申报.getValue().equals(etrEntrustAdd.getDealMode())
                ||DictCodeListExt3.申报类型_点击成交申报.getValue().equals(etrEntrustAdd.getDealMode())
                ||DictCodeListExt3.申报类型_成交申报.getValue().equals(etrEntrustAdd.getDealMode())
                ||DictCodeListExt3.申报类型_日均价固定价格申报.getValue().equals(etrEntrustAdd.getDealMode())
                ||DictCodeListExt3.申报类型_收盘价固定价格申报.getValue().equals(etrEntrustAdd.getDealMode()))){
            return DictCodeList.交易平台_大宗交易平台.getValue();
        }else {
            return DictCodeList.交易平台_竞价撮合平台.getValue();
        }
    }
}
