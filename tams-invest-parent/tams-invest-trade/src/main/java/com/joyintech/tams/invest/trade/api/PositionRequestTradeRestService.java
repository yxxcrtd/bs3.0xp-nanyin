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
    private TrdCommonBaseService trdCommonBaseService;

    @Autowired
    private EtrEntrustMapper etrEntrustMapper;

    public static final String OPT_TYPE_NEW = "new";

    public static final String TRADE_PLAT = "TRANS_MATCH";

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
            Map<String, Object> resultMap = new HashMap<>();
            for (BaseTradeProductDealDto entity:finProductTradeDto.getBaseTradeProductDealDtos()) {
                TrdFinProductDealTotal dealTotal = entity.getTrdFinProductDealTotal();
                this.sendPositionHttpRequest(entity);
            }
            PositionHttpRequest.send(resultMap);
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
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("decisionAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("systemAct", DictCodeListExt3.系统动作_新建.getValue());
        this.setCommonField(entity, resultMap);
        String finprodType = entity.getTrdProductDeal().getFinprodType2();
        String type6 = commonPositionEngineService.getType6(entity.getTrdProductDeal().getFinprodId());
        if (DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue().equals(type6)) {
            finprodType += "-" + type6;
        }
        String interType = commonPositionEngineService.getPositionInterType(PositionConstant.POSITION_TYPE_TRD, TRADE_PLAT,
                finprodType, entity.getTrdProductDeal().getPs(), DictCodeListExt3.业务动作_成交匹配.getValue(), entity.getClass().getName());
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
        PositionHttpRequest.send(resultMap);
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
        if (StringUtil.isNotEmpty(dealEntity)) {
            Map<String,Object> param = Maps.newHashMap();
            param.put("fTradeId",dealEntity.getfTradeId());
            List<PortfolioCalculateDto> portfolioCalculateDtoList =  trdCommonBaseService.queryPortfolioCalculate(param);
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(portfolioCalculateDtoList)) {
                PositionRequestBaseBean bean;
                // 组合数据集
                List<Map<String, Object>> ptlList = new ArrayList<>();
                for (PortfolioCalculateDto item : portfolioCalculateDtoList) {
                    bean = new PositionRequestBaseBean();
                    bean.setPortfolioId(item.getPortfolioId());
                    // 业务编号
                    bean.setTradeId(dealEntity.getTradeId());
                    bean.setEtrId(entity.getEntrustId());
                    bean.setFinprodId(dealEntity.getFinprodId());
                    bean.setCashAcctId(item.getCashAcctId());
                    bean.setSecAcctId(item.getSecAcctId());
                    bean.setSecAmount(item.getSecAmount());
                    bean.setCashAmount(item.getCashAmount());
                    Map<String, Object> map = BeanUtil.object2Map(bean);
                    map.put(ORD_ENTRUST_ID,null);
                    // 指令份额	etrSecAmount
                    map.put("etrSecAmount",item.getEtrSecAmount());
                    // 下单编号	ordId
                    map.put("ordId",item.getOrderId());
                    // 下单份额	ordSecAmount
                    map.put("ordSecAmount",item.getOrdSecAmount());
                    ptlList.add(map);
                }
                resultMap.put("ptlList", ptlList);
                // 组合附属数据集
                List<Map<String, Object>> ptlSubList = new ArrayList<>();
                for (PortfolioCalculateDto item : portfolioCalculateDtoList) {
                    bean = new PositionRequestBaseBean();
                    bean.setPortfolioId(item.getPortfolioId());
                    bean.setFinprodId(dealEntity.getFinprodId());
                    // 业务编号
                    bean.setTradeId(dealEntity.getTradeId());
                    bean.setEtrId(entity.getEntrustId());
                    bean.setSecAcctId(item.getSecAcctId());
                    bean.setSecAmount(item.getSecAmount());
                    Map<String, Object> map = BeanUtil.object2Map(bean);
                    ptlSubList.add(map);
                }
                resultMap.put("ptlSubList", ptlSubList);
            }
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
        if (StringUtil.isNotEmpty(dealEntity)) {
            String ps = dealEntity.getPs();
            Map<String,Object> param = Maps.newHashMap();
            param.put("fTradeId",dealEntity.getfTradeId());
            List<PortfolioCalculateDto> portfolioCalculateDtoList =  trdCommonBaseService.queryPortfolioCalculate(param);
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(portfolioCalculateDtoList)) {
                PositionRequestBaseBean bean;
                // 组合数据集
                List<Map<String, Object>> ptlList = new ArrayList<>();
                for (PortfolioCalculateDto item : portfolioCalculateDtoList) {
                    bean = new PositionRequestBaseBean();
                    bean.setPortfolioId(item.getPortfolioId());
                    bean.setFinprodId(dealEntity.getFinprodId());
                    bean.setTradeId(dealEntity.getTradeId());
                    bean.setEtrId(entity.getEntrustId());
                    bean.setSecAcctId(item.getSecAcctId());
                    bean.setSecAmount(dealEntity.getPrinAmt());
                    bean.setCashAcctId(item.getCashAcctId());
                    bean.setCashAmount(item.getCashAmount());
                    Map<String, Object> map = BeanUtil.object2Map(bean);
                    map.put("etrSecAmount",item.getEtrSecAmount());
                    map.put("ordId",item.getOrderId());
                    map.put("ordSecAmount",item.getOrdSecAmount());
                    map.put(ORD_ENTRUST_ID,null);
                    ptlList.add(map);
                }
                resultMap.put(PTL_LIST, ptlList);

                // 组合配股/新股申购数据集	ptlRoList
                if (DictCodeListExt2.交易方向_配股.getValue().equals(ps) || DictCodeList.交易方向_申购.getValue().equals(ps)) {
                    List<Map<String, Object>> ptlRoList = new ArrayList<>();
                    for (PortfolioCalculateDto item : portfolioCalculateDtoList) {
                        bean = new PositionRequestBaseBean();
                        bean.setPortfolioId(item.getPortfolioId());
                        bean.setFinprodId(dealEntity.getFinprodId());
                        bean.setTradeId(dealEntity.getTradeId());
                        bean.setEtrId(entity.getEntrustId());
                        bean.setSecAcctId(item.getSecAcctId());
                        bean.setSecAmount(item.getSecAmount());
                        bean.setCashAcctId(item.getCashAcctId());
                        // 配股/申购代码
                        bean.setFinprodId(getStockId(dealEntity.getEntrustId()));
                        bean.setCashAmount(item.getCashAmount());
                        Map<String, Object> map = BeanUtil.object2Map(bean);
                        map.put("etrSecAmount",item.getEtrSecAmount());
                        map.put("ordId",item.getOrderId());
                        map.put("ordSecAmount",item.getOrdSecAmount());
                        map.put(ORD_ENTRUST_ID,null);
                        ptlRoList.add(map);
                    }
                    resultMap.put(PTL_RO_LIST, ptlRoList);
                }
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
        String balanceCheck = "Y";
        if (StringUtil.isNotEmpty(entity.getSoftSkipMode()) && StringUtil.equals(entity.getSoftSkipMode(), "Y")) {
            balanceCheck = "N";
        }
        resultMap.put("userId", SecurityUtil.getLoginUser().getUserName());
        resultMap.put("totalTradeId", entity.getEntrustId());
        resultMap.put("balanceCheck", balanceCheck);
        resultMap.put("flowStatus", DictCodeListExt3.流程节点_流程提交.getValue());
        resultMap.put("modelType", DictCodeListExt1.子系统代码_委托.getValue());
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
        resultMap.put("busiType", DictCodeListExt3.头寸业务类型_质押式回购到期.getValue());
        // 业务动作
        resultMap.put("busiAct", DictCodeListExt3.业务动作_成交匹配.getValue());
        resultMap.put("ps", trdProductDeal.getPs());
        resultMap.put("tradePlace", trdProductDeal.getTradeMarket());
        resultMap.put("settleSpeed", null);
        resultMap.put("sharesTradePlatform", null);
        resultMap.put("deliveryType", null);
        resultMap.put("decisionAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("systemAct", DictCodeListExt3.系统动作_新建.getValue());
        resultMap.put("workFlowId", null);
        resultMap.put("cdate", trdProductDeal.getTradeDate());
        resultMap.put("ccy", trdProductDeal.getCcy());
        resultMap.put("totalTradeId", null);
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
        plt.put("standardSecAmount", null);
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
     * 可转债
     *
     * @param entity
     * @param resultMap
     */
    private void bond003(BaseTradeProductDealDto entity, Map<String, Object> resultMap) {

    }
}
