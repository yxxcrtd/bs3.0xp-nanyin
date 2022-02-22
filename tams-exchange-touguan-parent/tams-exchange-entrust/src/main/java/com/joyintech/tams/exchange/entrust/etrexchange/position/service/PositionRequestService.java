package com.joyintech.tams.exchange.entrust.etrexchange.position.service;

import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.exchange.entrust.etrexchange.common.dto.EtrExchangeTradePortfolioRequest;
import com.joyintech.tams.exchange.entrust.etrexchange.common.dto.EtrExchangeTradeSaveRequest;
import com.joyintech.tams.exchange.entrust.etrexchange.position.bean.PositionRequestBaseBean;
import com.joyintech.tams.exchange.entrust.etrexchange.position.constant.PositionConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.position.service.CommonPositionEngineService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PositionRequestService {

    @Autowired
    private CommonPositionEngineService commonPositionEngineService;

    /**
     * EtrExchangeTradeSaveRequest请求对象使用
     *
     * @param entity
     */
    public void sendEtrExchangeTradeSaveRequest(EtrExchangeTradeSaveRequest entity) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        this.setCommonField(entity, resultMap);//设置公共字段，个性化的需要在具体接口中覆盖
        String type6 = commonPositionEngineService.getType6(entity.getFinprodId());
        String finprodType = entity.getFinprodType2();
        if (StringUtil.equals(type6, DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue())) {
            finprodType += "-" + type6;
        }
        String interType = commonPositionEngineService.getPositionInterType(PositionConstant.POSITION_TYPE_ETR, entity.getTradePlat(), finprodType, entity.getPs(), entity.getOptType(), entity.getClass().getName());
        if (StringUtil.equals(interType, PositionConstant.INTERF_TYPE_BOND002)) {
            this.bond002(entity, resultMap);
        } else if (PositionConstant.INTERF_TYPE_BOND003.equals(interType)) {
            this.bond003(entity, resultMap);
        } else {
            //TODO 根据条件判断调用其他接口
        }
        PositionHttpRequest.send(resultMap);
    }

    /**
     * doDelete时使用
     *
     * @param workflowId
     * @param modelType
     * @param busiType
     * @param ps
     */
    public void sendDeleteByWorkFlowId(String workflowId, String modelType, String busiType, String ps) {
        PositionHttpRequest.doDeleteByWorkFlowId(workflowId, modelType, busiType, ps);
    }

    /**
     * 场内现券-指令    bond002
     *
     * @param entity
     * @param resultMap
     */
    private void bond002(EtrExchangeTradeSaveRequest entity, Map<String, Object> resultMap) {
        resultMap.put("interfaceType", PositionConstant.INTERF_TYPE_BOND002);
        if (StringUtil.equals(entity.getPs(), BussinessDictCodeList.交易方向_行权回售.getValue())) {
            resultMap.put("busiType", DictCodeListExt3.头寸业务类型_债券行权交易.getValue());
        } else {
            resultMap.put("busiType", DictCodeListExt3.头寸业务类型_现券交易.getValue());
        }
        resultMap.put("busiAct", DictCodeListExt3.业务动作_投资指令.getValue());
        resultMap.put("ps", entity.getPs());

        String deliveryType = commonPositionEngineService.getDeliveryType(entity.getFinprodId());
        String settleSpreed = StringUtil.EMPTY;
        if (deliveryType.equals(DictCodeListExt3.结算方式_头寸引擎_净额结算.getValue())) {
            settleSpreed = BussinessDictCodeList.清算速度_T加1.getValue();
        } else if (deliveryType.equals(DictCodeListExt3.结算方式_头寸引擎_RTGS.getValue())) {
            settleSpreed = BussinessDictCodeList.清算速度_T加0.getValue();
        } else {
            settleSpreed = BussinessDictCodeList.清算速度_T加1.getValue();
        }
        resultMap.put("settleSpeed", settleSpreed);
        resultMap.put("bidResult", DictCodeList.是否_否.getValue());
        resultMap.put("purchModle", null);
        resultMap.put("tradePlace", entity.getTradeMarket());
        resultMap.put("sharesTradePlatform", entity.getTradePlat());
        resultMap.put("deliveryType", deliveryType);
        resultMap.put("workFlowId", entity.getWorkflowId());
        resultMap.put("cdate", DateUtil.format(entity.getVdate()));
        resultMap.put("ccy", entity.getCcy());
        resultMap.put("finprodId", entity.getFinprodId());
        resultMap.put("bTotalTradeId", null);
        if (CollectionUtils.isNotEmpty(entity.getPortfolioList())) {
            List<Map<String, Object>> ptlList = new ArrayList<>();
            for (EtrExchangeTradePortfolioRequest etrExchangeTradePortfolioRequest : entity.getPortfolioList()) {
                Map<String, Object> pltMap = new HashMap<>();
                pltMap.put("portfolioId", etrExchangeTradePortfolioRequest.getPortfolioId());
                pltMap.put("tradeId", null);
                pltMap.put("etrId", entity.getEntrustId());
                pltMap.put("etrSecAmount", null);
                pltMap.put("ordId", null);
                pltMap.put("ordSecAmount", null);
                pltMap.put("cashAcctId", etrExchangeTradePortfolioRequest.getAcctNo());
                pltMap.put("secAcctId", etrExchangeTradePortfolioRequest.getSecAcctId());
                pltMap.put("secAmount", null);//TODO 根据指令单位是张还是手来处理
                pltMap.put("cashAmount", etrExchangeTradePortfolioRequest.getFprice());
                pltMap.put("invAim", etrExchangeTradePortfolioRequest.getInvAim());
                pltMap.put("integrateSecAmount", null);
                pltMap.put("ownSecAmount", null);
                ptlList.add(pltMap);
            }
            resultMap.put("ptlList", ptlList);
        }
    }

    /**
     * 可转债-指令    bond003
     *
     * @param entity
     * @param resultMap
     */
    private void bond003(EtrExchangeTradeSaveRequest entity, Map<String, Object> resultMap) {
        resultMap.put("interfaceType", PositionConstant.INTERF_TYPE_BOND003);
        // 业务动作
        resultMap.put("busiAct", DictCodeListExt3.业务动作_投资指令.getValue());

        /**
         * 业务类型	见数据字典：头寸业务类型
         *
         * "IF 指令.交易方向 = 数据字典.交易方向.回售 THEN 数据字典. BUSI_TYPE_POSITION.债券行权交易
         * IF 指令.交易方向 = 数据字典.交易方向.买入、卖出 THEN  数据字典. BUSI_TYPE_POSITION.现券交易
         * IF 指令.交易方向 = 数据字典.交易方向.配债THEN  数据字典. BUSI_TYPE_POSITION.配债
         * IF 指令.交易方向 = 数据字典.交易方向.债转股 THEN  数据字典. BUSI_TYPE_POSITION.债转股
         * IF 指令.交易方向 = 数据字典.交易方向.上市流通 THEN  数据字典. BUSI_TYPE_POSITION.上市流通"
         */
        String ps = entity.getPs();
        if (BussinessDictCodeList.交易方向_行权回售.getValue().equals(ps)) {
            resultMap.put("busiType", DictCodeListExt3.头寸业务类型_债券行权交易.getValue());
        } else if (BussinessDictCodeList.交易方向_买入.getValue().equals(ps) || BussinessDictCodeList.交易方向_卖出.getValue().equals(ps)) {
            resultMap.put("busiType", DictCodeListExt3.头寸业务类型_现券交易.getValue());
        } else if (DictCodeListExt3.交易方向_配债.getValue().equals(ps)) {
            resultMap.put("busiType", DictCodeListExt3.头寸业务类型_配债.getValue());
        } else if (DictCodeListExt3.交易方向_债转股.getValue().equals(ps)) {
            resultMap.put("busiType", DictCodeListExt3.头寸业务类型_债转股.getValue());
        } else if (DictCodeList.交易方向_上市流通.getValue().equals(ps)) {
            resultMap.put("busiType", DictCodeListExt3.头寸业务类型_上市流通.getValue());
        }

        resultMap.put("settleSpeed", parseSettleSpreed(resultMap));

        // 申购方式	见数据字典：申购方式
        if (DictCodeList.交易方向_申购.getValue().equals(entity.getPs())) {
            resultMap.put("purchModle", DictCodeListExt2.申购方式_网上申购.getValue());
        }

        if (CollectionUtils.isNotEmpty(entity.getPortfolioList())) {
            PositionRequestBaseBean bean;

            // 组合现金数据集
            List<Map<String, Object>> ptlCashList = new ArrayList<>(4);
            for (EtrExchangeTradePortfolioRequest item : entity.getPortfolioList()) {
                Map<String, Object> map = new HashMap<>(8);
                map.put("portfolioId", item.getPortfolioId());
                // 资金账号
                map.put("cashAcctId", item.getAcctNo());
                // 业务编号
                map.put("tradeId", null);
                // 指令编号
                map.put("etrId", entity.getEntrustId());
                // 交易金额
                //"IF 交易方向 = 买入 、 卖出 THEN 指令.全价总额
                //IF 交易方向 = 申购 THEN 系统参数.预估中签比例*指令金额
                //IF 交易方向 = 配债 THEN 指令.指令金额"
                if (BussinessDictCodeList.交易方向_买入.getValue().equals(ps) || BussinessDictCodeList.交易方向_卖出.getValue().equals(ps)) {
                    map.put("cashAmount", item.getFprice().multiply(item.getEntrustNum()));
                } else if (DictCodeList.交易方向_申购.getValue().equals(ps)) {
                    // TODO 系统参数.预估中签比例
                } else if (DictCodeListExt3.交易方向_配债.getValue().equals(ps)) {
                    map.put("cashAmount", item.getEntrustAmt());
                }

                ptlCashList.add(map);
            }
            resultMap.put("ptlCashList", ptlCashList);

            if (DictCodeListExt3.交易方向_债转股.getValue().equals(ps)) {
                // 股票持仓数据集
                List<Map<String, Object>> sharesSecList = new ArrayList<>(4);
                for (EtrExchangeTradePortfolioRequest item : entity.getPortfolioList()) {
                    bean = new PositionRequestBaseBean();
                    bean.setPortfolioId(item.getPortfolioId());
                    bean.setFinprodId(item.getFinprodId());
                    bean.setSecAcctId(item.getSecAcctId());
                    bean.setTradeId(null);
                    bean.setEtrId(entity.getEntrustId());
                    bean.setSecAmount(entity.getToShareAvailableNum());
                    sharesSecList.add(BeanUtil.object2Map(bean));
                }
                resultMap.put("sharesSecList", sharesSecList);
            }

            // 可转债数据集
            List<Map<String, Object>> bondSecList = new ArrayList<>(4);
            for (EtrExchangeTradePortfolioRequest item : entity.getPortfolioList()) {
                bean = new PositionRequestBaseBean(item);
                //TODO "IF 指令.单位 = 张 THEN 指令.指令数量*100
                //IF 指令.单位 = 手 THEN 指令.指令数量*10*100"
                bean.setSecAmount(entity.getEntrustNum());

                Map<String, Object> map = BeanUtil.object2Map(bean);
                //融入券券面总额
                map.put("integrateSecAmount", null);
                //本方券券面总额
                map.put("ownSecAmount", null);
                bondSecList.add(map);
            }
            resultMap.put("bondSecList", bondSecList);

            if (DictCodeListExt3.交易方向_配债.getValue().equals(ps) || DictCodeList.交易方向_申购.getValue().equals(ps)) {
                // 配债申购数据集
                List<Map<String, Object>> applyBondSecList = new ArrayList<>(4);
                for (EtrExchangeTradePortfolioRequest item : entity.getPortfolioList()) {
                    bean = new PositionRequestBaseBean(item);
                    //TODO "IF 指令.单位 = 张 THEN 指令.指令数量*100
                    //IF 指令.单位 = 手 THEN 指令.指令数量*10*100"
                    bean.setSecAmount(entity.getEntrustNum());
                    applyBondSecList.add(BeanUtil.object2Map(bean));
                }
                resultMap.put("applyBondSecList", applyBondSecList);
            }
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
     * @param resultMap 封装集合
     * @return 清算速度
     */
    private String parseSettleSpreed(Map<String, Object> resultMap) {
        String deliveryType = (String) resultMap.get("deliveryType");
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
    private void setCommonField(EtrExchangeTradeSaveRequest entity, Map<String, Object> resultMap) {
        String balanceCheck = "Y";
        if (StringUtil.isNotEmpty(entity.getSoftSkipMode()) && StringUtil.equals(entity.getSoftSkipMode(), "Y")) {
            balanceCheck = "N";
        }
        resultMap.put("userId", SecurityUtil.getLoginUser().getUserName());
        resultMap.put("totalTradeId", entity.getEntrustId());
        resultMap.put("balanceCheck", balanceCheck);
        resultMap.put("flowStatus", DictCodeListExt3.流程节点_流程提交.getValue());
        resultMap.put("modelType", DictCodeListExt1.子系统代码_委托.getValue());

        if (StringUtil.equals(entity.getOptType(), "new")) {
            resultMap.put("decisionAct", DictCodeListExt3.系统动作_新建.getValue());
            resultMap.put("systemAct", DictCodeListExt3.系统动作_新建.getValue());
        } else if (StringUtil.equals(entity.getOptType(), "modify")) {
            resultMap.put("decisionAct", DictCodeListExt3.系统动作_修改.getValue());
            resultMap.put("systemAct", DictCodeListExt3.系统动作_修改.getValue());
        }

        // 结算方式	见数据字典：结算方式
        // 调用【共通:结算方式判断】 返回.结算方式
        String deliveryType = commonPositionEngineService.getDeliveryType(entity.getFinprodId());
        resultMap.put("deliveryType", deliveryType);
        // 交易方向
        resultMap.put("ps", entity.getPs());
        // 交易场所	见数据字典：交易场所
        resultMap.put("tradePlace", entity.getTradeMarket());
        // 交易平台	见数据字典：交易平台
        resultMap.put("sharesTradePlatform", entity.getTradePlat());
        // 流程编号	指令的工作流ID
        resultMap.put("workFlowId", entity.getWorkflowId());
        // 日期
        resultMap.put("cdate", DateUtil.format(entity.getVdate()));
        // 币种
        resultMap.put("ccy", entity.getCcy());
        // 前次交易编号
        resultMap.put("bTotalTradeId", null);
    }
}
