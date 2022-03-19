package com.joyintech.tams.invest.trade.api;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.common.base.dao.EtrEntrustMapper;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.position.service.CommonPositionEngineService;
import com.joyintech.tams.invest.common.trade.dao.BaseAssetCashPositionCommMapper;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.common.trade.dto.PortfolioCalculateDto;
import com.joyintech.tams.invest.common.trade.dto.PortfolioCalculateDto;
import com.joyintech.tams.invest.common.trade.service.TrdCommonBaseService;
import com.joyintech.tams.invest.trade.exchangetransmatch.position.bean.PositionRequestBaseBean;
import com.joyintech.tams.invest.trade.exchangetransmatch.position.constant.PositionConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.joyintech.tams.constant.DictCodeList.是否_否;

import java.util.*;

import static com.joyintech.tams.invest.common.position.constant.PositionCommonConstant.*;

@Service
public class PositionRequestTradeRestService {
    private static final Logger logger = LoggerFactory.getLogger(PositionRequestTradeRestService.class);

    @Autowired
    private CommonPositionEngineService commonPositionEngineService;

    @Autowired
    private BaseAssetCashPositionCommMapper baseAssetCashPositionCommMapper;

    @Autowired
    private EtrEntrustMapper etrEntrustMapper;

    public static final String TRADE_PLAT = "TRANS_MATCH";

    public static final String STANDAR_SEC_ID = "XN_PRODID999";

    /**
     * 发送交易Rest头寸请求
     * @param finProductTradeDto
     */
    public void sendTradeRestPositionRequest(FinProductTradeDto finProductTradeDto) {
        logger.debug("【发送交易Rest头寸请求】entity: {}", JSON.toJSONString(finProductTradeDto));
        if(CollectionUtils.isEmpty(finProductTradeDto.getBaseTradeProductDealDtos())){
            return;
        }
        if(DictCodeListExt1.交易流程状态_撤销.getValue().equals(finProductTradeDto.getWorkflowStatus())){
            this.revoke(finProductTradeDto);
        }else if(DictCodeListExt1.交易流程状态_有效.getValue().equals(finProductTradeDto.getWorkflowStatus())){
            for (BaseTradeProductDealDto entity:finProductTradeDto.getBaseTradeProductDealDtos()) {
                this.sendPositionHttpRequest(entity);
            }
        }

    }

    /**
     * 撤销新头寸接口
     * @param finProductTradeDto
     */
    private void revoke(FinProductTradeDto finProductTradeDto){
        for (BaseTradeProductDealDto entity:finProductTradeDto.getBaseTradeProductDealDtos()) {
            TrdProductDeal deal = entity.getTrdProductDeal();
            // 资产二级类型
            String assetType2 = StringUtil.isNotEmpty(finProductTradeDto.getAssetType2()) ?
                    finProductTradeDto.getAssetType2() : baseAssetCashPositionCommMapper.selectFundType(deal.getFinprodId());
            String busiType = commonPositionEngineService.getPositionBusiType(deal.getFinprodType2(),
                    deal.getPs(), assetType2);
            PositionHttpRequest.dorevoke(deal.getTradeId(), PositionConstant.POSITION_TYPE_TRD, busiType, deal.getPs());
        }
    }


    /**
     * BaseTradeProductDealDto请求对象使用
     * @param entity
     */
    public void sendPositionHttpRequest(BaseTradeProductDealDto entity) {
        logger.debug("【发送存续期头寸请求】trdTempDto: {}", JSON.toJSONString(entity));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("decisionAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("systemAct", DictCodeListExt3.系统动作_新建.getValue());
        this.setCommonField(entity, resultMap);
        String finprodType = entity.getTrdProductDeal().getFinprodType2();
        String type6 = commonPositionEngineService.getType6(entity.getTrdProductDeal().getFinprodId());
        if (DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue().equals(type6)
                && DictCodeListExt1.金融产品类型_投管用_债券.getValue().equals(finprodType)) {
            finprodType += "-" + type6;
        }
        logger.debug("【存续期查询头寸接口参数】finprodType:{},ps:{}", finprodType, entity.getTrdProductDeal().getPs());
        String interType = commonPositionEngineService.getPositionInterType(PositionConstant.POSITION_TYPE_TRD, TRADE_PLAT,
                finprodType, entity.getTrdProductDeal().getPs(), DictCodeListExt3.业务动作_成交匹配.getValue(), entity.getClass().getName());
        logger.debug("【存续期查询头寸接口结果】interType:{}", interType);
        if (PositionConstant.INTERF_TYPE_FUND001.equals(interType)) {
            this.fund001(entity, resultMap);
        } else if (PositionConstant.INTERF_TYPE_STOCK001.equals(interType)) {
            this.stock001(entity, resultMap);
        }else if (PositionConstant.INTERF_TYPE_REPO003.equals(interType)) {
            this.repo003(entity, resultMap);
        }else if (PositionConstant.INTERF_TYPE_BOND002.equals(interType)) {
            this.bond002(entity, resultMap);
        }else if (PositionConstant.INTERF_TYPE_BOND003.equals(interType)) {
            this.bond003(entity, resultMap);
        }
        if(StringUtil.isNotEmpty(resultMap.get(INTERFACE_TYPE))){
            PositionHttpRequest.send(resultMap);
        }else{
            logger.debug("[未找到对应的接口配置]");
        }
    }

    /**
     * 基金-指令    fund001
     *
     * @param entity
     * @param resultMap
     */
    private void fund001(BaseTradeProductDealDto entity, Map<String, Object> resultMap) {
        resultMap.put(INTERFACE_TYPE, PositionConstant.INTERF_TYPE_FUND001);
        // 业务动作
        resultMap.put(BUSI_ACT, DictCodeListExt3.业务动作_成交匹配.getValue());
        resultMap.put(SETTLE_SPEED, null);
        // 结算方式
        resultMap.put(DELIVERY_TYPE, null);
        // 当日回转
        resultMap.put(SAME_DAY_TURN, 是否_否.getValue());
        // ETF申赎方式
        resultMap.put(ETFREDEMPTION_METHOD, null);
        TrdProductDeal dealEntity = entity.getTrdProductDeal();
        TrdProductDealAdd dealAddEntity = entity.getTrdProductDealAdd();
        List<StlPayOrdersDto> stlPayOrdersList = entity.getStlPayOrdersList();
        if (StringUtil.isNotEmpty(dealEntity)) {
            Map<String,Object> param = Maps.newHashMap();
            param.put("fTradeId",dealEntity.getfTradeId());
            PositionRequestBaseBean bean = new PositionRequestBaseBean();
            List<Map<String, Object>> ptlList = new ArrayList<>();
            bean.setPortfolioId(dealAddEntity.getPortfolioId());
            // 业务编号
            bean.setTradeId(dealEntity.getTradeId());
            bean.setEtrId(entity.getEntrustId());
            bean.setFinprodId(dealEntity.getFinprodId());
            bean.setCashAcctId(CollectionUtils.isEmpty(stlPayOrdersList)?"": stlPayOrdersList.get(0).getMyAcctNo());
            bean.setSecAcctId(dealEntity.getSecManageAcctId());
            bean.setSecAmount(dealEntity.getShareAmt());
            bean.setCashAmount(dealEntity.getTradeAmt());
            Map<String, Object> map = BeanUtil.object2Map(bean);
            map.put(ORD_ENTRUST_ID,null);
            // 指令份额	etrSecAmount
            map.put("etrSecAmount",null);
            // 下单编号	ordId
            map.put("ordId",null);
            // 下单份额	ordSecAmount
            map.put("ordSecAmount",null);
            ptlList.add(map);
            resultMap.put("ptlList", ptlList);
            // 组合附属数据集
            List<Map<String, Object>> ptlSubList = new ArrayList<>();
            bean = new PositionRequestBaseBean();
            bean.setPortfolioId(dealAddEntity.getPortfolioId());
            bean.setFinprodId(dealEntity.getFinprodId());
            // 业务编号
            bean.setTradeId(dealEntity.getTradeId());
            bean.setEtrId(entity.getEntrustId());
            bean.setSecAcctId(dealEntity.getSecManageAcctId());
            bean.setSecAmount(dealEntity.getShareAmt());
            Map<String, Object> paramMap = BeanUtil.object2Map(bean);
            ptlSubList.add(paramMap);
            resultMap.put("ptlSubList", ptlSubList);
        }
    }

    /**
     * 股票-指令    stock001
     *
     * @param entity
     * @param resultMap
     */
    private void stock001(BaseTradeProductDealDto entity, Map<String, Object> resultMap) {
        resultMap.put(INTERFACE_TYPE, PositionConstant.INTERF_TYPE_STOCK001);
        // 业务动作
        resultMap.put(BUSI_ACT, DictCodeListExt3.业务动作_成交匹配.getValue());
        // 清算速度
        resultMap.put(SETTLE_SPEED, null);
        // 结算方式
        resultMap.put(DELIVERY_TYPE, null);
        TrdProductDeal dealEntity = entity.getTrdProductDeal();
        TrdProductDealAdd dealAddEntity = entity.getTrdProductDealAdd();
        List<StlPayOrdersDto> stlPayOrdersList = entity.getStlPayOrdersList();
        if (StringUtil.isNotEmpty(dealEntity)) {
            String ps = dealEntity.getPs();
            Map<String,Object> param = Maps.newHashMap();
            param.put("fTradeId",dealEntity.getfTradeId());
            // 组合数据集
            List<Map<String, Object>> ptlList = new ArrayList<>();
            PositionRequestBaseBean  bean = new PositionRequestBaseBean();
            bean.setPortfolioId(dealAddEntity.getPortfolioId());
            bean.setFinprodId(dealEntity.getFinprodId());
            bean.setTradeId(dealEntity.getTradeId());
            bean.setEtrId(entity.getEntrustId());
            bean.setSecAcctId(dealEntity.getSecManageAcctId());
            bean.setSecAmount(dealEntity.getPrinAmt());
            bean.setCashAcctId(CollectionUtils.isEmpty(stlPayOrdersList)?"": stlPayOrdersList.get(0).getMyAcctNo());
            bean.setCashAmount(dealEntity.getTradeAmt());
            Map<String, Object> map = BeanUtil.object2Map(bean);
            map.put("etrSecAmount",stlPayOrdersList.get(0).getSettleAmt());
            map.put("ordId",CollectionUtils.isEmpty(stlPayOrdersList)?"": stlPayOrdersList.get(0).getOrderId());
            map.put("ordSecAmount",stlPayOrdersList.get(0).getSettleAmt());
            map.put(ORD_ENTRUST_ID,null);
            ptlList.add(map);
            resultMap.put(PTL_LIST, ptlList);
            // 组合配股/新股申购数据集	ptlRoList
            if (DictCodeListExt2.交易方向_配股.getValue().equals(ps) || DictCodeList.交易方向_申购.getValue().equals(ps)) {
                List<Map<String, Object>> ptlRoList = new ArrayList<>();
                bean = new PositionRequestBaseBean();
                bean.setPortfolioId(dealAddEntity.getPortfolioId());
                bean.setFinprodId(dealEntity.getFinprodId());
                bean.setTradeId(dealEntity.getTradeId());
                bean.setEtrId(entity.getEntrustId());
                bean.setSecAcctId(dealEntity.getSecManageAcctId());
                bean.setSecAmount(dealEntity.getShareAmt());
                bean.setCashAcctId(CollectionUtils.isEmpty(stlPayOrdersList)?"": stlPayOrdersList.get(0).getMyAcctNo());
                // 配股/申购代码
                bean.setFinprodId(getStockId(dealEntity.getEntrustId()));
                bean.setCashAmount(dealEntity.getTradeAmt());
                Map<String, Object> datamap = BeanUtil.object2Map(bean);
                datamap.put("etrSecAmount",null);
                datamap.put("ordId",null);
                datamap.put("ordSecAmount",null);
                datamap.put(ORD_ENTRUST_ID,null);
                ptlRoList.add(datamap);
                resultMap.put(PTL_RO_LIST, ptlRoList);
            }
        }
    }

    private String getStockId(String entrustId){
        EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(entrustId);
        if (StringUtil.isNotEmpty(etrEntrust)){
            return etrEntrust.getStockId();
        }
        return null;
    }

    /**
     * 设置公共字段
     *
     * @param entity    实例对象
     * @param resultMap 封装集合
     */
    private void setCommonField(BaseTradeProductDealDto entity, Map<String, Object> resultMap) {
        resultMap.put("userId", SecurityUtil.getLoginUser() != null ? SecurityUtil.getLoginUser().getUserName() : "system");
        resultMap.put("totalTradeId", entity.getTrdProductDeal().getTradeId());
        resultMap.put("balanceCheck", "N");
        resultMap.put("flowStatus", DictCodeListExt3.流程节点_审批生效.getValue());
        resultMap.put("modelType", DictCodeListExt1.子系统代码_交易.getValue());
        //设置头寸业务类型
        String busiType = commonPositionEngineService.getPositionBusiType(entity.getTrdProductDeal().getFinprodType2(), entity.getTrdProductDeal().getPs(), entity.getAssetType2());
        resultMap.put("busiType", busiType);
        // 结算方式	见数据字典：结算方式
        // 交易方向
        resultMap.put(PS, entity.getTrdProductDeal().getPs());
        // 交易场所	见数据字典：交易场所
        resultMap.put(TRADE_PLACE, entity.getTrdProductDeal().getTradeMarket());
        // 交易平台	见数据字典：交易平台
        resultMap.put(SHARES_TRADE_PLATFORM, entity.getTrdProductDeal().getTradePlat());
        // 流程编号	指令的工作流ID
        resultMap.put(WORK_FLOW_ID, entity.getTrdProductDeal().getWorkflowId());
        // 日期
        resultMap.put("cdate", DateUtil.format(entity.getTrdProductDeal().getTradeDate()));
        // 币种
        if(StringUtil.isNotEmpty(entity.getTrdProductDeal().getCcy())) {
            resultMap.put(CCY, entity.getTrdProductDeal().getCcy());
        }else {
            resultMap.put(CCY, DictCodeList.币种_人民币_CNY.getValue());
        }
        // 前次交易编号
        resultMap.put(B_TOTAL_TRADE_ID, null);
    }

    /**
     * 质押式通用回购
     *
     * @param entity
     * @param resultMap
     */
    private void repo003(BaseTradeProductDealDto entity, Map<String, Object> resultMap) {
        TrdFinProductDealTotal trdProductDealTotal = entity.getTrdFinProductDealTotal();
        TrdProductDeal trdProductDeal = entity.getTrdProductDeal();
        TrdProductDealAdd trdProductDealAdd = entity.getTrdProductDealAdd();
        List<StlPayOrdersDto> stlPayOrdersList = entity.getStlPayOrdersList();
        resultMap.put("interfaceType", INTERF_TYPE_REPO003);
        // 业务类型
        resultMap.put("busiType", DictCodeListExt3.头寸业务类型_通用回购到期.getValue());
        // 业务动作
        resultMap.put("busiAct", DictCodeListExt3.业务动作_成交匹配.getValue());
        if(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue().equals(trdProductDeal.getFinprodType2())){
            resultMap.put("ps", DictCodeListExt3.交易方向_正回购.getValue());
        }else if(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue().equals(trdProductDeal.getFinprodType2())){
            resultMap.put("ps", DictCodeListExt3.交易方向_逆回购.getValue());
        }
        resultMap.put("tradePlace", trdProductDeal.getTradeMarket());
        resultMap.put("settleSpeed", null);
        resultMap.put("sharesTradePlatform", null);
        resultMap.put("deliveryType", null);
        resultMap.put("decisionAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("systemAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("workFlowId", null);
        resultMap.put("cdate", trdProductDeal.getTradeDate());
        resultMap.put("ccy", trdProductDeal.getCcy());
        resultMap.put("bTotalTradeId", null);

        List<Map<String, Object>> ptlList = new ArrayList<>(4);

        Map<String, Object> plt = new HashMap<>(8);
        plt.put("portfolioId", trdProductDealAdd.getPortfolioId());
        if(!CollectionUtils.isEmpty(stlPayOrdersList)){
            plt.put("cashAcctId", stlPayOrdersList.get(0).getMyAcctNo());
        }
        plt.put("secAcctId", trdProductDealTotal.getSecManageAcctId());
        plt.put("tradeId", trdProductDeal.getTradeId());
        plt.put("etrId", null);
        plt.put("ordEntrustId", null);
        plt.put("standardSecId", STANDAR_SEC_ID);
        plt.put("standardSecAmount", trdProductDeal.getPrinAmt());
        plt.put("cashAmount", trdProductDeal.getTradeAmt());
        ptlList.add(plt);
        resultMap.put("ptlList", ptlList);
    }

    /**
     * 场内现券
     *
     * @param entity
     * @param resultMap
     */
    private void bond002(BaseTradeProductDealDto entity, Map<String, Object> resultMap) {
        TrdFinProductDealTotal trdProductDealTotal = entity.getTrdFinProductDealTotal();
        TrdProductDeal trdProductDeal = entity.getTrdProductDeal();
        TrdProductDealAdd trdProductDealAdd = entity.getTrdProductDealAdd();
        List<StlPayOrdersDto> stlPayOrdersList = entity.getStlPayOrdersList();
        resultMap.put("interfaceType", INTERF_TYPE_BOND002);
        // 业务类型
        resultMap.put("busiType", DictCodeListExt3.头寸业务类型_债券存续期交易.getValue());
        // 业务动作
        resultMap.put("busiAct", DictCodeListExt3.业务动作_成交匹配.getValue());
        resultMap.put("ps", trdProductDeal.getPs());
        String deliveryType = commonPositionEngineService.getDeliveryType(trdProductDeal.getFinprodId());
        resultMap.put("settleSpeed", this.parseSettleSpreed(deliveryType));
        resultMap.put("bidResult", 是否_否.getValue());
        resultMap.put("purchModle", null);
        resultMap.put("tradePlace", trdProductDeal.getTradeMarket());
        resultMap.put("sharesTradePlatform", null);
        resultMap.put("deliveryType", deliveryType);
        resultMap.put("decisionAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("systemAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("workFlowId", null);
        resultMap.put("cdate", trdProductDeal.getTradeDate());
        resultMap.put("ccy", trdProductDeal.getCcy());
        resultMap.put("finprodId", trdProductDeal.getFinprodId());
        resultMap.put("bTotalTradeId", null);

        List<Map<String, Object>> ptlList = new ArrayList<>();
        Map<String, Object> pltMap = new HashMap<>();
        pltMap.put("portfolioId", trdProductDeal.getPortfolioId());
        pltMap.put("tradeId", trdProductDeal.getTradeId());
        pltMap.put("etrId", null);
        pltMap.put("ordEntrustId", null);
        pltMap.put("etrSecAmount", null);
        pltMap.put("ordId", null);
        pltMap.put("ordSecAmount", null);
        if(!CollectionUtils.isEmpty(stlPayOrdersList)){
            pltMap.put("cashAcctId", stlPayOrdersList.get(0).getMyAcctNo());
        }
        pltMap.put("secAcctId", trdProductDealTotal.getSecManageAcctId());
        pltMap.put("secAmount", trdProductDeal.getShareAmt());
        pltMap.put("cashAmount", trdProductDeal.getTradeAmt());
        pltMap.put("invAim", trdProductDeal.getInvAim());
        pltMap.put("integrateSecAmount", null);
        pltMap.put("ownSecAmount", null);
        ptlList.add(pltMap);
        resultMap.put("ptlList", ptlList);
    }

    /**
     * 可转债
     *
     * @param entity
     * @param resultMap
     */
    private void bond003(BaseTradeProductDealDto entity, Map<String, Object> resultMap) {
        TrdFinProductDealTotal trdProductDealTotal = entity.getTrdFinProductDealTotal();
        TrdProductDeal trdProductDeal = entity.getTrdProductDeal();
        TrdProductDealAdd trdProductDealAdd = entity.getTrdProductDealAdd();
        List<StlPayOrdersDto> stlPayOrdersList = entity.getStlPayOrdersList();
        resultMap.put("interfaceType", INTERF_TYPE_BOND003);
        resultMap.put("busiType", DictCodeListExt3.头寸业务类型_债券存续期交易.getValue());
        // 业务动作
        resultMap.put("busiAct", DictCodeListExt3.业务动作_成交匹配.getValue());
        resultMap.put("ps", trdProductDeal.getPs());
        String deliveryType = commonPositionEngineService.getDeliveryType(trdProductDeal.getFinprodId());
        resultMap.put("settleSpeed", parseSettleSpreed(deliveryType));
        resultMap.put("purchModle", null);
        resultMap.put("tradePlace", trdProductDeal.getTradeMarket());
        resultMap.put("sharesTradePlatform", null);
        resultMap.put("deliveryType", deliveryType);
        resultMap.put("decisionAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("systemAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("workFlowId", null);
        resultMap.put("cdate", trdProductDeal.getTradeDate());
        resultMap.put("ccy", trdProductDeal.getCcy());
        resultMap.put("bTotalTradeId", null);

        List<Map<String, Object>> ptlCashList = new ArrayList<>(4);
        List<Map<String, Object>> bondSecList = new ArrayList<>(4);
        Map<String, Object> ptlCash = new HashMap<>(8);
        ptlCash.put("portfolioId", trdProductDeal.getPortfolioId());
        if(!CollectionUtils.isEmpty(stlPayOrdersList)){
            ptlCash.put("cashAcctId", stlPayOrdersList.get(0).getMyAcctNo());
        }
        ptlCash.put("tradeId", trdProductDeal.getTradeId());
        ptlCash.put("etrId", null);
        ptlCash.put("cashAmount", trdProductDeal.getTradeAmt());
        ptlCash.put("etrSecAmount", null);
        ptlCash.put("ordId", null);
        ptlCash.put("ordSecAmount", null);
        ptlCashList.add(ptlCash);

        Map<String, Object> bondSec = new HashMap<>();
        bondSec.put("portfolioId", trdProductDeal.getPortfolioId());
        bondSec.put("finprodId", trdProductDeal.getFinprodId());
        bondSec.put("secAcctId", trdProductDealTotal.getSecManageAcctId());
        bondSec.put("tradeId", trdProductDeal.getTradeId());
        bondSec.put("etrId", null);
        bondSec.put("integrateSecAmount", null);
        bondSec.put("ownSecAmount", null);
        bondSec.put("secAmount", trdProductDeal.getShareAmt());
        bondSecList.add(bondSec);
        resultMap.put("ptlCashList", ptlCashList);
        resultMap.put("bondSecList", bondSecList);
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
}
