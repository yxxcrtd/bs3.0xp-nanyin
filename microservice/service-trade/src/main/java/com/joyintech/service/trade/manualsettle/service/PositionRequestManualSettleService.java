package com.joyintech.service.trade.manualsettle.service;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.entrustmsgbymq.dto.TrdTempDto;
import com.joyintech.service.trade.entrustmsgbymq.service.PositionRequestTradeReportService;
import com.joyintech.service.trade.entrustmsgbymq.service.TradePreClearingService;
import com.joyintech.service.trade.manualsettle.dao.ManualSettleMapper;
import com.joyintech.service.trade.manualsettle.dto.ManualSettleDto;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.common.base.dao.EtrEntrustAddMapper;
import com.joyintech.tams.common.base.dao.EtrEntrustMapper;
import com.joyintech.tams.common.base.dao.TrdProductTempDealAddMapper;
import com.joyintech.tams.common.base.dao.TrdProductTempDealMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.position.service.CommonPositionEngineService;
import com.joyintech.tams.invest.common.trade.dto.PortfolioCalculateDto;
import com.joyintech.tams.invest.common.trade.service.TrdCommonBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.joyintech.tams.constant.DictCodeList.是否_否;
import static com.joyintech.tams.invest.common.position.constant.PositionCommonConstant.*;

@Service
public class PositionRequestManualSettleService {
    private static final Logger logger = LoggerFactory.getLogger(PositionRequestManualSettleService.class);

    @Autowired
    private CommonPositionEngineService commonPositionEngineService;

    @Autowired
    private TrdProductTempDealMapper trdProductTempDealMapper;

    @Autowired
    private TrdProductTempDealAddMapper trdProductTempDealAddMapper;

    @Autowired
    private ManualSettleMapper manualSettleMapper;

    @Autowired
    private EtrEntrustAddMapper etrEntrustAddMapper;

    @Autowired
    private EtrEntrustMapper etrEntrustMapper;

    @Autowired
    private TrdCommonBaseService trdCommonBaseService;

    public static final String TRADE_PLAT = "TRD_MANUAL_SETTLE";

    /**
     * EtrExchangeTradeSaveRequest请求对象使用
     *
     * @param entity
     */
    public void sendRequest(ManualSettleDto entity) {
        logger.debug("【发送手工交收头寸请求】entity: {}", JSON.toJSONString(entity));
        Map<String, Object> resultMap = new HashMap<>();
        setCommonField(entity, resultMap);
        String finprodType = entity.getFinprodType2();
//        if(!entity.getBusiType().equals("01")&&!entity.getBusiType().equals("02")&&!entity.getBusiType().equals("03")&&!entity.getBusiType().equals("04")){
//            finprodType = DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue();
//        }else {
//            finprodType = DictCodeListExt1.金融产品类型_投管用_债券.getValue();
//            String type6 = commonPositionEngineService.getType6(entity.getFinprodId());
//            if (DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue().equals(type6)) {
//                finprodType += "-" + type6;
//            }
//        }
        String type6 = commonPositionEngineService.getType6(entity.getFinprodId());
        if (DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue().equals(type6)
                // ZGTFCP-1372:增加债券资产类型条件 modify by fuyongsheng 20220319 start
                && DictCodeListExt1.金融产品类型_投管用_债券.getValue().equals(finprodType)) {
            // ZGTFCP-1372:增加债券资产类型条件 modify by fuyongsheng 20220319 end
            finprodType += "-" + type6;
        }
        TrdProductTempDeal trdProductTempDeal = trdProductTempDealMapper.selectByPrimaryKey(entity.getTradeId());
        EtrEntrustAdd etrEntrustAdd = etrEntrustAddMapper.selectByPrimaryKey(entity.getEntrustNo());
        if(etrEntrustAdd != null){
            if (DictCodeList.回购类型_质押式协议回购.getValue().equals(etrEntrustAdd.getAssetType1())
                    || (DictCodeList.回购类型_质押式竞价回购.getValue().equals(etrEntrustAdd.getAssetType1()))) {
                finprodType += "-" + etrEntrustAdd.getAssetType1();
            }
        }
        logger.debug("【手工交收查询头寸接口参数】finprodType:{},ps:{}", finprodType, trdProductTempDeal.getPs());
        String interType = commonPositionEngineService.getPositionInterType("TRD", TRADE_PLAT, finprodType, trdProductTempDeal.getPs(), DictCodeListExt3.共通业务类型_手工交收.getValue(), entity.getClass().getName());
        logger.debug("【手工交收查询头寸接口结果】interType:{}", interType);
        if (INTERF_TYPE_BOND002.equals(interType)) {
            this.bond002(entity, resultMap);
        } else if (INTERF_TYPE_BOND003.equals(interType)) {
            this.bond003(entity, resultMap);
        } else if (INTERF_TYPE_REPO002.equals(interType)) {
            this.repo002(entity, resultMap);
        }
        if(StringUtil.isNotEmpty(resultMap.get(INTERFACE_TYPE))){
            PositionHttpRequest.send(resultMap);
        }else{
            logger.debug("[成交回报未找到对应的接口配置]");
        }
    }

    /**
     * 场内现券-指令
     *
     * @param entity
     * @param resultMap
     */
    private void bond002(ManualSettleDto entity, Map<String, Object> resultMap) {
        resultMap.put("interfaceType", INTERF_TYPE_BOND002);
        TrdProductTempDeal trdProductTempDeal = trdProductTempDealMapper.selectByPrimaryKey(entity.getTradeId());
        // ZGTFCP-1281:修改获取业务类型方法 modify by fuyongsheng 20220318 start
        // String busiType = commonPositionEngineService.getPositionBusiType(trdProductTempDeal.getFinprodType2(), trdProductTempDeal.getPs(), null);
        EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(entity.getEntrustNo());
        EtrEntrustAdd etrEntrustAdd = etrEntrustAddMapper.selectByPrimaryKey(entity.getEntrustNo());
        String busiType = commonPositionEngineService.getPositionBusiType(etrEntrust.getFinprodType2(), etrEntrust.getPs(),
                etrEntrustAdd.getAssetType1());
        // ZGTFCP-1281:修改获取业务类型方法 modify by fuyongsheng 20220318 end
        resultMap.put("busiType", busiType);
        if (StringUtil.equals(entity.getPageModel(), DictCodeListExt3.交收操作类型_交收.getValue())) {
            resultMap.put("busiAct", DictCodeListExt3.业务动作_交收确认.getValue());
        } else if (StringUtil.equals(entity.getPageModel(), DictCodeListExt3.交收操作类型_回溯.getValue())) {
            resultMap.put("busiAct", DictCodeListExt3.业务动作_交收回溯.getValue());
        } else if (StringUtil.equals(entity.getPageModel(), DictCodeListExt3.交收操作类型_交收失败.getValue())) {
            resultMap.put("busiAct", DictCodeListExt3.业务动作_交收失败.getValue());
        }
        resultMap.put("ps", trdProductTempDeal.getPs());
        String deliveryType = commonPositionEngineService.getDeliveryType(entity.getFinprodId());
        resultMap.put("settleSpeed", parseSettleSpreed(deliveryType));
        resultMap.put("bidResult", 是否_否.getValue());
        resultMap.put("purchModle", null);
        resultMap.put("tradePlace", entity.getTradeMarket());
        if((DictCodeList.交易平台_大宗交易平台.getValue().equals(entity.getTradePlat())
                ||DictCodeList.交易平台_综合协议交易平台.getValue().equals(entity.getTradePlat()))
                &&(DictCodeList.交易方向_买入.getValue().equals(trdProductTempDeal.getPs())
                ||DictCodeList.交易方向_卖出.getValue().equals(trdProductTempDeal.getPs()))
                &&(DictCodeListExt3.申报类型_意向申报.getValue().equals(etrEntrustAdd.getDealMode())
                ||DictCodeListExt3.申报类型_定价申报.getValue().equals(etrEntrustAdd.getDealMode())
                ||DictCodeListExt3.申报类型_点击成交申报.getValue().equals(etrEntrustAdd.getDealMode())
                ||DictCodeListExt3.申报类型_成交申报.getValue().equals(etrEntrustAdd.getDealMode())
                ||DictCodeListExt3.申报类型_日均价固定价格申报.getValue().equals(etrEntrustAdd.getDealMode())
                ||DictCodeListExt3.申报类型_收盘价固定价格申报.getValue().equals(etrEntrustAdd.getDealMode()))){
            resultMap.put("sharesTradePlatform", DictCodeList.交易平台_大宗交易平台.getValue());
        }else {
            resultMap.put("sharesTradePlatform", DictCodeList.交易平台_竞价撮合平台.getValue());
        }
        resultMap.put("deliveryType", deliveryType);
        resultMap.put("decisionAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("systemAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("workFlowId", null);
        resultMap.put("cdate", entity.getSettleDate());
        resultMap.put("ccy", trdProductTempDeal.getCcy());
        resultMap.put("finprodId", entity.getFinprodId());
        resultMap.put("bTotalTradeId", entity.getTradeId());

        Map<String,Object> paraMap = new HashMap<>();
        paraMap.put("dealNo", entity.getTradeId());
        List<PortfolioCalculateDto> list = trdCommonBaseService.queryPortfolioCalculate(paraMap);

        List<Map<String, Object>> ptlList = new ArrayList<>();
        for (PortfolioCalculateDto dto:list) {
            Map<String, Object> pltMap = new HashMap<>();
            pltMap.put("portfolioId", dto.getPortfolioId());
            pltMap.put("tradeId", entity.getSettleSeqNo());
            pltMap.put("etrId", etrEntrust.getEntrustId());
            pltMap.put("ordEntrustId", null);
            pltMap.put("etrSecAmount", dto.getEtrSecAmount());
            pltMap.put("ordId", dto.getOrderId());
            pltMap.put("ordSecAmount", dto.getOrdSecAmount());
            pltMap.put("cashAcctId", entity.getAcctNo());
            pltMap.put("secAcctId", entity.getSecAcctId());
            pltMap.put("secAmount", dto.getSecAmount());
            pltMap.put("cashAmount", dto.getCashAmount());
            pltMap.put("invAim", trdProductTempDeal.getInvAim());
            pltMap.put("integrateSecAmount", dto.getIntegrateSecAmount());
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
    private void bond003(ManualSettleDto entity, Map<String, Object> resultMap) {
        resultMap.put("interfaceType", INTERF_TYPE_BOND003);

        TrdProductTempDeal trdProductTempDeal = trdProductTempDealMapper.selectByPrimaryKey(entity.getTradeId());

        // String busiType = commonPositionEngineService.getPositionBusiType(trdProductTempDeal.getFinprodType2(), trdProductTempDeal.getPs(), null);
        // ZGTFCP-1281:修改获取业务类型方法 modify by fuyongsheng 20220318 start
        // String busiType = commonPositionEngineService.getPositionBusiType(trdProductTempDeal.getFinprodType2(), trdProductTempDeal.getPs(), null);
        EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(entity.getEntrustNo());
        EtrEntrustAdd etrEntrustAdd = etrEntrustAddMapper.selectByPrimaryKey(entity.getEntrustNo());
        String busiType = commonPositionEngineService.getPositionBusiType(etrEntrust.getFinprodType2(), etrEntrust.getPs(),
                etrEntrustAdd.getAssetType1());
        // ZGTFCP-1281:修改获取业务类型方法 modify by fuyongsheng 20220318 end
        resultMap.put("busiType", busiType);
        if (StringUtil.equals(entity.getPageModel(), DictCodeListExt3.交收操作类型_交收.getValue())) {
            resultMap.put("busiAct", DictCodeListExt3.业务动作_交收确认.getValue());
        } else if (StringUtil.equals(entity.getPageModel(), DictCodeListExt3.交收操作类型_回溯.getValue())) {
            resultMap.put("busiAct", DictCodeListExt3.业务动作_交收回溯.getValue());
        } else if (StringUtil.equals(entity.getPageModel(), DictCodeListExt3.交收操作类型_交收失败.getValue())) {
            resultMap.put("busiAct", DictCodeListExt3.业务动作_交收失败.getValue());
        }
        resultMap.put("ps", trdProductTempDeal.getPs());
        String deliveryType = commonPositionEngineService.getDeliveryType(entity.getFinprodId());
        resultMap.put("settleSpeed", parseSettleSpreed(deliveryType));
        resultMap.put("purchModle", null);
        resultMap.put("tradePlace", entity.getTradeMarket());
        if((DictCodeList.交易平台_大宗交易平台.getValue().equals(entity.getTradePlat())
                ||DictCodeList.交易平台_综合协议交易平台.getValue().equals(entity.getTradePlat()))
                &&(DictCodeList.交易方向_买入.getValue().equals(trdProductTempDeal.getPs())
                ||DictCodeList.交易方向_卖出.getValue().equals(trdProductTempDeal.getPs()))
                &&(DictCodeListExt3.申报类型_意向申报.getValue().equals(etrEntrustAdd.getDealMode())
                ||DictCodeListExt3.申报类型_定价申报.getValue().equals(etrEntrustAdd.getDealMode())
                ||DictCodeListExt3.申报类型_点击成交申报.getValue().equals(etrEntrustAdd.getDealMode())
                ||DictCodeListExt3.申报类型_成交申报.getValue().equals(etrEntrustAdd.getDealMode())
                ||DictCodeListExt3.申报类型_日均价固定价格申报.getValue().equals(etrEntrustAdd.getDealMode())
                ||DictCodeListExt3.申报类型_收盘价固定价格申报.getValue().equals(etrEntrustAdd.getDealMode()))){
            resultMap.put("sharesTradePlatform", DictCodeList.交易平台_大宗交易平台.getValue());
        }else {
            resultMap.put("sharesTradePlatform", DictCodeList.交易平台_竞价撮合平台.getValue());
        }
        resultMap.put("deliveryType", deliveryType);
        resultMap.put("decisionAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("systemAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("workFlowId", null);
        resultMap.put("cdate", entity.getSettleDate());
        resultMap.put("ccy", trdProductTempDeal.getCcy());
        resultMap.put("bTotalTradeId", entity.getTradeId());

        Map<String,Object> paraMap = new HashMap<>();
        paraMap.put("dealNo", entity.getTradeId());
        List<PortfolioCalculateDto> list = trdCommonBaseService.queryPortfolioCalculate(paraMap);
        List<Map<String, Object>> ptlCashList = new ArrayList<>(4);
        List<Map<String, Object>> bondSecList = new ArrayList<>(4);
        for (PortfolioCalculateDto dto : list){
            Map<String, Object> ptlCash = new HashMap<>(8);
            ptlCash.put("portfolioId", dto.getPortfolioId());
            ptlCash.put("cashAcctId", entity.getAcctNo());
            ptlCash.put("tradeId", entity.getTradeId());
            ptlCash.put("etrId", etrEntrust.getEntrustId());
            ptlCash.put("cashAmount", dto.getCashAmount());
            ptlCash.put("etrSecAmount", dto.getEtrSecAmount());
            ptlCash.put("ordId", dto.getOrderId());
            ptlCash.put("ordSecAmount", dto.getOrdSecAmount());
            ptlCashList.add(ptlCash);

            Map<String, Object> bondSec = new HashMap<>();
            bondSec.put("portfolioId", dto.getPortfolioId());
            bondSec.put("finprodId", entity.getFinprodId());
            bondSec.put("secAcctId", entity.getSecAcctId());
            // ZGTFCP-1372:修改字段赋值 modify by fuyongsheng 20220319 start
            bondSec.put("tradeId", entity.getTradeId());
            bondSec.put("etrId", etrEntrust.getEntrustId());
            // ZGTFCP-1372:修改字段赋值 modify by fuyongsheng 20220319 end
            bondSec.put("integrateSecAmount", new BigDecimal(0));
            bondSec.put("ownSecAmount", dto.getOwnSecAmount());
            bondSec.put("secAmount", dto.getSecAmount());
            bondSecList.add(bondSec);
        }
        resultMap.put("ptlCashList", ptlCashList);
        resultMap.put("bondSecList", bondSecList);
    }

    /**
     * 质押式协议回购-指令
     *
     * @param entity
     * @param resultMap
     */
    private void repo002(ManualSettleDto entity, Map<String, Object> resultMap) {
        resultMap.put("interfaceType", INTERF_TYPE_REPO002);

        InfContractDetailfile infContractDetailfile = manualSettleMapper.selectInfContractDetailfile(entity.getEntrustNo(), entity.getDealNo());

        // String busiType = commonPositionEngineService.getPositionBusiType(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue(), infContractDetailfile.getPs(), DictCodeList.回购类型_质押式协议回购.getValue());
        // ZGTFCP-1281:修改获取业务类型方法 modify by fuyongsheng 20220318 start
        // String busiType = commonPositionEngineService.getPositionBusiType(trdProductTempDeal.getFinprodType2(), trdProductTempDeal.getPs(), null);
        EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(entity.getEntrustNo());
        EtrEntrustAdd etrEntrustAdd = etrEntrustAddMapper.selectByPrimaryKey(entity.getEntrustNo());
        String busiType = commonPositionEngineService.getPositionBusiType(etrEntrust.getFinprodType2(), etrEntrust.getPs(),
                etrEntrustAdd.getAssetType1());
        // ZGTFCP-1281:修改获取业务类型方法 modify by fuyongsheng 20220318 end
        resultMap.put("busiType", busiType);
        // ZGTFCP-1372:修改字段赋值 modify by fuyongsheng 20220319 start
        resultMap.put("totalTradeId", entity.getSeqNo());
        // ZGTFCP-1372:修改字段赋值 modify by fuyongsheng 20220319 end
        if (StringUtil.equals(entity.getPageModel(), DictCodeListExt3.交收操作类型_交收.getValue())) {
            resultMap.put("busiAct", DictCodeListExt3.业务动作_交收确认.getValue());
            // ZGTFCP-1372:修改字段赋值 modify by fuyongsheng 20220319 start
            resultMap.put("bTotalTradeId", entity.getTradeId());
            // ZGTFCP-1372:修改字段赋值 modify by fuyongsheng 20220319 end
        } else if (StringUtil.equals(entity.getPageModel(), DictCodeListExt3.交收操作类型_回溯.getValue())) {
            resultMap.put("busiAct", DictCodeListExt3.业务动作_交收回溯.getValue());
            // ZGTFCP-1372:修改字段赋值 modify by fuyongsheng 20220319 start
            resultMap.put("bTotalTradeId", entity.getSeqNo());
            // ZGTFCP-1372:修改字段赋值 modify by fuyongsheng 20220319 end
        } else if (StringUtil.equals(entity.getPageModel(), DictCodeListExt3.交收操作类型_交收失败.getValue())) {
            resultMap.put("busiAct", DictCodeListExt3.业务动作_交收失败.getValue());
            // ZGTFCP-1372:修改字段赋值 modify by fuyongsheng 20220319 start
            resultMap.put("bTotalTradeId", entity.getTradeId());
            // ZGTFCP-1372:修改字段赋值 modify by fuyongsheng 20220319 end
        }
        resultMap.put("ps", infContractDetailfile.getPs());
        resultMap.put("tradePlace", entity.getTradeMarket());
        resultMap.put("settleSpeed", null);
        resultMap.put("sharesTradePlatform", null);
        resultMap.put("deliveryType", null);
        resultMap.put("decisionAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("systemAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("workFlowId", null);
        resultMap.put("cdate", entity.getSettleDate());
        resultMap.put("ccy", infContractDetailfile.getCcy());


        // 首期组合现金数据集
        if (DictCodeListExt3.交易方向_正回购.getValue().equals(infContractDetailfile.getPs())
                || DictCodeListExt3.交易方向_首期.getValue().equals(infContractDetailfile.getPs())
                || DictCodeListExt3.交易方向_提前购回.getValue().equals(infContractDetailfile.getPs())
                || (DictCodeListExt3.交易方向_到期购回.getValue().equals(infContractDetailfile.getPs()))) {
            List<Map<String, Object>> list = new ArrayList<>(4);
            Map<String, Object> map = new HashMap<>();
            map.put("portfolioId", entity.getPortfolioId());
            map.put("cashAcctId", entity.getAcctNo());
            // ZGTFCP-1372:修改字段赋值 modify by fuyongsheng 20220319 start
            map.put("tradeId", entity.getTradeId());
            map.put("etrId", etrEntrust.getEntrustId());
            // ZGTFCP-1372:修改字段赋值 modify by fuyongsheng 20220319 end
            map.put("cashAmount", entity.getPrin());
            list.add(map);
            resultMap.put("ptlCashList", list);
        }

        // 轧差组合现金数据集
        if (DictCodeListExt3.交易方向_到期续做.getValue().equals(infContractDetailfile.getPs())) {
            List<Map<String, Object>> list = new ArrayList<>(4);
            Map<String, Object> map = new HashMap<>();
            map.put("portfolioId", entity.getPortfolioId());
            map.put("cashAcctId", entity.getAcctNo());
            // ZGTFCP-1372:修改字段赋值 modify by fuyongsheng 20220319 start
            map.put("tradeId", entity.getTradeId());
            map.put("etrId", etrEntrust.getEntrustId());
            // ZGTFCP-1372:修改字段赋值 modify by fuyongsheng 20220319 end

            // "IF 回购类型 = 正回购 THEN 指令.到期续作金额 - 指令.首期的到期结算金额
            // IF 回购类型 = 逆回购  and 成交编号找不到对应的首期 THEN 指令.到期续作金额
            // IF 回购类型 = 逆回购  and 成交编号找到对应的首期 THEN  指令.到期续作金额 - 指令.首期的到期结算金额"
            if (StringUtil.isNotEmpty(entity.getFinprodId())) {
                map.put("cashAmount", entity.getSettleAmt());
            } else{
                map.put("cashAmount", entity.getSettleAmt().subtract(entity.getmTradeAmt()));
            }
            list.add(map);
            resultMap.put("ptlOffsetCashList", list);
        }

        // 质押券数据集	ptlSecList
        // if (DictCodeListExt3.交易方向_解除质押.getValue().equals(infContractDetailfile.getPs())) {
        if (DictCodeListExt3.交易方向_正回购.getValue().equals(infContractDetailfile.getPs())) {
            List<Map<String, Object>> list = new ArrayList<>(4);
            Map<String, Object> map = new HashMap<>();
            map.put("portfolioId", entity.getPortfolioId());
            map.put("finprodId", entity.getPledgeId());
            map.put("secAcctId", entity.getSecAcctId());
            // ZGTFCP-1372:修改字段赋值 modify by fuyongsheng 20220319 start
            map.put("tradeId", entity.getTradeId());
            map.put("etrId", etrEntrust.getEntrustId());
            // ZGTFCP-1372:修改字段赋值 modify by fuyongsheng 20220319 end
            map.put("integrateSecAmount", new BigDecimal(0));
            map.put("ownSecAmount", entity.getPledgeNumber().multiply(new BigDecimal(100)));
            map.put("secAmount", entity.getPledgeNumber().multiply(new BigDecimal(100)));
            list.add(map);
            resultMap.put("ptlSecList", list);
        }

        // 解除质押券数据集	ptlRelieveSecList
        if (DictCodeListExt3.交易方向_解除质押.getValue().equals(infContractDetailfile.getPs())
                || DictCodeListExt3.交易方向_到期续做.getValue().equals(infContractDetailfile.getPs())) {
            List<Map<String, Object>> list = new ArrayList<>(4);
            Map<String, Object> map = new HashMap<>();
            map.put("portfolioId", entity.getPortfolioId());
            map.put("finprodId", entity.getPledgeId());
            map.put("secAcctId", entity.getSecAcctId());
            // ZGTFCP-1372:修改字段赋值 modify by fuyongsheng 20220319 start
            map.put("tradeId", entity.getTradeId());
            map.put("etrId", etrEntrust.getEntrustId());
            // ZGTFCP-1372:修改字段赋值 modify by fuyongsheng 20220319 end
            map.put("integrateSecAmount", new BigDecimal(0));
            if(DictCodeListExt3.交易方向_解除质押.getValue().equals(infContractDetailfile.getPs())){
                map.put("ownSecAmount", entity.getPledgeNumber().multiply(new BigDecimal(100)));
                map.put("secAmount", entity.getPledgeNumber().multiply(new BigDecimal(100)));
            }else {
                map.put("ownSecAmount", entity.getPledgeNumber());
                map.put("secAmount", entity.getPledgeNumber());
            }
            list.add(map);
            resultMap.put("ptlRelieveSecList", list);
        }
    }

    /**
     * 解析清算速度
     * <p>
     * 清算速度	见数据字典：清算速度
     * "IF 变量.结算方式 = 数据字典.DELIVERY_TYPE.净额结算 THEN 数据字典.清算速度.T+1
     * ELSE IF 变量.结算方式 = 数据字典.DELIVERY_TYPE.RTGS THEN 数据字典.清算速度.T+0
     * ELSE 数据字典.清算速度.T+1
     *
     * @param deliveryType 封装集合
     * @return 清算速度
     */
    private String parseSettleSpreed(String deliveryType) {
        String settleSpreed = BussinessDictCodeList.清算速度_T加1.getValue();
        if (DictCodeListExt3.结算方式_头寸引擎_净额结算.getValue().equals(deliveryType)) {
            settleSpreed = BussinessDictCodeList.清算速度_T加1.getValue();
        } else if ((DictCodeListExt3.结算方式_头寸引擎_RTGS.getValue()).equals(deliveryType)) {
            settleSpreed = BussinessDictCodeList.清算速度_T加0.getValue();
        }
        return settleSpreed;
    }

    /**
     * 设置公共字段
     *
     * @param entity    实例对象
     * @param resultMap 封装集合
     */
    private void setCommonField(ManualSettleDto entity, Map<String, Object> resultMap) {
        resultMap.put("userId", LoginUserUtil.getUserName());
        resultMap.put("totalTradeId", entity.getTradeId());
        // resultMap.put("balanceCheck", "Y");
        resultMap.put("balanceCheck", "N");
        resultMap.put("flowStatus", DictCodeListExt3.流程节点_审批生效.getValue());
        resultMap.put("modelType", DictCodeListExt1.子系统代码_交易.getValue());
    }
}
