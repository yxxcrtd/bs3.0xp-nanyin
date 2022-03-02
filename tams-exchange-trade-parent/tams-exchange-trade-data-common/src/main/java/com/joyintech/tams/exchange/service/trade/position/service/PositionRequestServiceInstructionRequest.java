package com.joyintech.tams.exchange.service.trade.position.service;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.exchange.common.bean.OrderPlaceBean;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.constant.joyin.BussinessDictCodeList;
import com.joyintech.tams.exchange.common.constant.joyin.DictCodeList;
import com.joyintech.tams.exchange.common.constant.joyin.DictCodeListExt1;
import com.joyintech.tams.exchange.common.db.domain.Instruction;
import com.joyintech.tams.exchange.common.db.domain.InstructionPledge;
import com.joyintech.tams.exchange.common.request.PositionInstructionRequest;
import com.joyintech.tams.exchange.common.response.PortfolioCalculateResponse;
import com.joyintech.tams.exchange.datacommon.db.service.InstructionPledgeService;
import com.joyintech.tams.exchange.service.trade.management.service.PortfolioManager;
import com.joyintech.tams.exchange.service.trade.position.bean.PositionRequestBaseBean;
import com.joyintech.tams.exchange.service.trade.position.constant.PositionExchangeConstant;
import com.joyintech.tams.invest.common.position.service.CommonPositionEngineService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static com.joyintech.tams.constant.DictCodeList.交易平台_竞价撮合平台;
import static com.joyintech.tams.constant.DictCodeList.是否_否;
import static com.joyintech.tams.constant.DictCodeListExt3.交易单位_份;
import static com.joyintech.tams.constant.DictCodeListExt3.交易单位_张;
import static com.joyintech.tams.exchange.service.trade.position.constant.PositionExchangeConstant.*;

@Service
public class PositionRequestServiceInstructionRequest {

    public static final String STANDAR_SEC_ID = "XN_PRODID999";
    @Autowired
    private CommonPositionEngineService commonPositionEngineService;
    @Autowired
    private PortfolioManager portfolioManager;
    @Autowired
    private InstructionPledgeService instructionPledgeService;

    /**
     * Instruction 请求对象使用
     *
     * @param entity
     */
    public void sendRequest(PositionInstructionRequest entity, String decisionAct, String systemAct) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("decisionAct", decisionAct);
        resultMap.put("systemAct", systemAct);
        this.setCommonField(entity, resultMap);//设置公共字段，个性化的需要在具体接口中覆盖
        String type6 = commonPositionEngineService.getType6(entity.getFinprodId());
        String finprodType = entity.getFinprodType2();
        if (DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue().equals(type6)) {
            finprodType += "-" + type6;
        }
        if (DictCodeList.回购类型_质押式协议回购.getValue().equals(entity.getAssetType())
                || (DictCodeList.回购类型_质押式竞价回购.getValue().equals(entity.getAssetType()))) {
            finprodType += "-" + entity.getAssetType();
        }
        String interType = commonPositionEngineService.getPositionInterType(PositionExchangeConstant.POSITION_TYPE_ETR, entity.getTradePlat(), finprodType, entity.getPs(), "new", entity.getClass().getName());
        if (PositionExchangeConstant.INTERF_TYPE_BOND002.equals(interType)) {
            this.bond002(entity, resultMap);
        } else if (PositionExchangeConstant.INTERF_TYPE_BOND003.equals(interType)) {
            this.bond003(entity, resultMap);
        } else if (PositionExchangeConstant.INTERF_TYPE_FUND001.equals(interType)) {
            this.fund001(entity, resultMap);
        } else if (PositionExchangeConstant.INTERF_TYPE_REPO001.equals(interType)) {
            this.repo001(entity, resultMap);
        } else if (PositionExchangeConstant.INTERF_TYPE_REPO002.equals(interType)) {
            this.repo002(entity, resultMap);
        } else if (PositionExchangeConstant.INTERF_TYPE_STOCK001.equals(interType)) {
            this.stock001(entity, resultMap);
        }
        PositionHttpRequest.send(resultMap);
    }
//
//    /**
//     * doDelete时使用---作废指令-共通接口
//     *
//     * @param entity
//     */
//    public void sendDelete(EtrExchangeTradeSaveRequest entity) {
//    	String busiType = commonPositionEngineService.getPositionBusiType(entity.getFinprodType2(), entity.getPs(), entity.getAssetType2());
//        PositionHttpRequest.doDeleteByWorkFlowId(entity.getWorkflowId(), PositionExchangeConstant.POSITION_TYPE_ETR, busiType, entity.getPs());
//    }
//
//    /**
//     * 撤销时使用---撤销指令-共通接口
//     *
//     * @param entity
//     */
//    public void sendRevoke(EtrExchangeTradeOperateRequest entity) {
//    	String busiType = commonPositionEngineService.getPositionBusiType(entity.getFinprodType2(), entity.getPs(), null);
//        PositionHttpRequest.dorevoke(entity.getEntrustId(), PositionExchangeConstant.POSITION_TYPE_TRD, busiType, entity.getPs());
//    }
//
//    /**
//     * 关闭时使用---关闭-共通接口
//     *
//     * @param entity
//     */
//    public void sendClose(EtrExchangeTradeOperateRequest entity) {
//    	String busiType = commonPositionEngineService.getPositionBusiType(entity.getFinprodType2(), entity.getPs(), null);
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("userId", SecurityUtil.getLoginUser().getUserName());
//        resultMap.put("totalTradeId", entity.getWorkflowId());
//        resultMap.put("modelType", PositionExchangeConstant.POSITION_TYPE_ETR);
//        resultMap.put(BUSI_TYPE, busiType);
//        resultMap.put("ps", entity.getPs());
//        resultMap.put("decisionAct", DictCodeListExt3.系统动作_关闭.getValue());
//        resultMap.put("systemAct", DictCodeListExt3.系统动作_关闭.getValue());
//        resultMap.put("closeId", entity.getEntrustId());
//        PositionHttpRequest.batchClose(resultMap);
//    }

    /**
     * 场内现券-下单    bond002
     *
     * @param entity
     * @param resultMap
     */
    private void bond002(Instruction entity, Map<String, Object> resultMap) {
        resultMap.put("interfaceType", PositionExchangeConstant.INTERF_TYPE_BOND002);
        resultMap.put(BUSI_ACT, DictCodeListExt3.业务动作_交易下单.getValue());
        resultMap.put(PS, entity.getPs());

        String deliveryType = commonPositionEngineService.getDeliveryType(entity.getFinprodId());
        String settleSpreed = StringUtil.EMPTY;
        if (deliveryType.equals(DictCodeListExt3.结算方式_头寸引擎_净额结算.getValue())) {
            settleSpreed = BussinessDictCodeList.清算速度_T加1.getValue();
        } else if (deliveryType.equals(DictCodeListExt3.结算方式_头寸引擎_RTGS.getValue())) {
            settleSpreed = BussinessDictCodeList.清算速度_T加0.getValue();
        } else {
            settleSpreed = BussinessDictCodeList.清算速度_T加1.getValue();
        }
        resultMap.put(SETTLE_SPEED, settleSpreed);
        resultMap.put("bidResult", 是否_否.getValue());
        resultMap.put("purchModle", null);
        resultMap.put(TRADE_PLACE, entity.getTradeMarket());
        resultMap.put("sharesTradePlatform", entity.getTradePlat());
        resultMap.put(DELIVERY_TYPE, deliveryType);
        resultMap.put("workFlowId", null);
        resultMap.put("cdate", DateUtil.format(entity.getVdate()));
        resultMap.put("finprodId", entity.getFinprodId());
        resultMap.put("bTotalTradeId", null);
//        if (CollectionUtils.isNotEmpty(entity.getPortfolioList())) {
//            List<Map<String, Object>> ptlList = new ArrayList<>();
//            for (EtrExchangeTradePortfolioRequest etrExchangeTradePortfolioRequest : entity.getPortfolioList()) {
//                Map<String, Object> pltMap = new HashMap<>();
//                pltMap.put("portfolioId", etrExchangeTradePortfolioRequest.getPortfolioId());
//                pltMap.put("tradeId", null);
//                pltMap.put("etrId", entity.getEntrustId());
//                pltMap.put("etrSecAmount", null);
//                pltMap.put("ordId", null);
//                pltMap.put("ordSecAmount", null);
//                pltMap.put("cashAcctId", etrExchangeTradePortfolioRequest.getAcctNo());
//                pltMap.put("secAcctId", etrExchangeTradePortfolioRequest.getSecAcctId());
//                pltMap.put("secAmount", getSecAmountByBondUnit(entity.getEntrustNum()));
//                pltMap.put("cashAmount", etrExchangeTradePortfolioRequest.getFprice());
//                pltMap.put("invAim", etrExchangeTradePortfolioRequest.getInvAim());
//                pltMap.put("integrateSecAmount", null);
//                pltMap.put("ownSecAmount", null);
//                ptlList.add(pltMap);
//            }
//            resultMap.put(PTL_LIST, ptlList);
//        }
    }

    /**
     * 根据指令单位是张还是手来处理交易金额
     * 债券使用
     *
     * @param entrustNum 委托数量
     */
    private BigDecimal getSecAmountByBondUnit(BigDecimal entrustNum) {
        BigDecimal secAmount = BigDecimal.ZERO;
        if (Objects.isNull(entrustNum)) {
            return secAmount;
        }
        String bondTradedUnit = CacheUtil.getSysParam("bond_traded_unit");
        if (交易单位_张.getValue().equals(bondTradedUnit)) {
            secAmount = entrustNum.multiply(BigDecimal.valueOf(100L));
        } else if (交易单位_份.getValue().equals(bondTradedUnit)) {
            secAmount = entrustNum.multiply(BigDecimal.valueOf(1000L));
        }
        return secAmount;
    }

    /**
     * 可转债-下单    bond003
     *
     * @param entity
     * @param resultMap
     */
    private void bond003(Instruction entity, Map<String, Object> resultMap) {
        resultMap.put("interfaceType", PositionExchangeConstant.INTERF_TYPE_BOND003);
        // 业务动作
        resultMap.put(BUSI_ACT, DictCodeListExt3.业务动作_交易下单.getValue());

        String ps = entity.getPs();

        resultMap.put(SETTLE_SPEED, parseSettleSpreed(resultMap));

        // 申购方式	见数据字典：申购方式
        if (DictCodeList.交易方向_申购.getValue().equals(entity.getPs())) {
            resultMap.put(PURCH_MODLE, DictCodeListExt2.申购方式_网上申购.getValue());
        }

//        if (CollectionUtils.isNotEmpty(entity.getPortfolioList())) {
//            PositionRequestBaseBean bean;
//
//            // 组合现金数据集
//            List<Map<String, Object>> ptlCashList = new ArrayList<>(4);
//            for (EtrExchangeTradePortfolioRequest item : entity.getPortfolioList()) {
//                Map<String, Object> map = new HashMap<>(8);
//                map.put("portfolioId", item.getPortfolioId());
//                // 资金账号
//                map.put("cashAcctId", item.getAcctNo());
//                // 业务编号
//                map.put("tradeId", null);
//                // 指令编号
//                map.put("etrId", entity.getEntrustId());
//                // 交易金额
//                //"IF 交易方向 = 买入 、 卖出 THEN 指令.全价总额
//                //IF 交易方向 = 申购 THEN 系统参数.预估中签比例*指令金额
//                //IF 交易方向 = 配债 THEN 指令.指令金额"
//                if (BussinessDictCodeList.交易方向_买入.getValue().equals(ps) || BussinessDictCodeList.交易方向_卖出.getValue().equals(ps)) {
//                    map.put("cashAmount", item.getFprice().multiply(item.getEntrustNum()));
//                } else if (DictCodeList.交易方向_申购.getValue().equals(ps)) {
//                    // TODO 系统参数.预估中签比例
//                } else if (DictCodeListExt3.交易方向_配债.getValue().equals(ps)) {
//                    map.put("cashAmount", item.getEntrustAmt());
//                }
//
//                ptlCashList.add(map);
//            }
//            resultMap.put("ptlCashList", ptlCashList);
//
//            if (DictCodeListExt3.交易方向_债转股.getValue().equals(ps)) {
//                // 股票持仓数据集
//                List<Map<String, Object>> sharesSecList = new ArrayList<>(4);
//                for (EtrExchangeTradePortfolioRequest item : entity.getPortfolioList()) {
//                    bean = new PositionRequestBaseBean();
//                    bean.setPortfolioId(item.getPortfolioId());
//                    bean.setFinprodId(item.getFinprodId());
//                    bean.setSecAcctId(item.getSecAcctId());
//                    bean.setTradeId(null);
//                    bean.setEtrId(entity.getEntrustId());
//                    bean.setSecAmount(entity.getToShareAvailableNum());
//                    Map<String, Object> map = BeanUtil.object2Map(bean);
//                    sharesSecList.add(map);
//                }
//                resultMap.put("sharesSecList", sharesSecList);
//            }
//
//            // 可转债数据集
//            List<Map<String, Object>> bondSecList = new ArrayList<>(4);
//            for (EtrExchangeTradePortfolioRequest item : entity.getPortfolioList()) {
//                bean = new PositionRequestBaseBean(item);
//                bean.setSecAmount(getSecAmountByBondUnit(item.getEntrustNum()));
//
//                Map<String, Object> map = BeanUtil.object2Map(bean);
//                //融入券券面总额
//                map.put("integrateSecAmount", null);
//                //本方券券面总额
//                map.put("ownSecAmount", null);
//                bondSecList.add(map);
//            }
//            resultMap.put("bondSecList", bondSecList);
//
//            if (DictCodeListExt3.交易方向_配债.getValue().equals(ps) || DictCodeList.交易方向_申购.getValue().equals(ps)) {
//                // 配债申购数据集
//                List<Map<String, Object>> applyBondSecList = new ArrayList<>(4);
//                for (EtrExchangeTradePortfolioRequest item : entity.getPortfolioList()) {
//                    bean = new PositionRequestBaseBean(item);
//                    bean.setSecAmount(getSecAmountByBondUnit(item.getEntrustNum()));
//                    Map<String, Object> map = BeanUtil.object2Map(bean);
//                    applyBondSecList.add(map);
//                }
//                resultMap.put("applyBondSecList", applyBondSecList);
//            }
//        }
    }

    /**
     * 基金-下单    fund001
     *
     * @param entity
     * @param resultMap
     */
    private void fund001(PositionInstructionRequest entity, Map<String, Object> resultMap) {
        resultMap.put("interfaceType", PositionExchangeConstant.INTERF_TYPE_FUND001);
        // 业务动作
        resultMap.put(BUSI_ACT, DictCodeListExt3.业务动作_交易下单.getValue());
        // 清算速度
        resultMap.put(SETTLE_SPEED, null);
        // 当日回转
        resultMap.put(SAME_DAY_TURN, 是否_否);
        // ETF申赎方式
        resultMap.put(ETFREDEMPTION_METHOD, null);

        List<PortfolioCalculateResponse> calculates = getCalculateList(entity);

        if (CollectionUtils.isNotEmpty(calculates)) {
            PositionRequestBaseBean bean;

            // 组合数据集
            List<Map<String, Object>> ptlList = new ArrayList<>(4);
            for (PortfolioCalculateResponse item : calculates) {
                bean = new PositionRequestBaseBean(entity);
                bean.setEtrId(entity.getEntrustNo());
                bean.setCashAmount(item.getEntrustAmt());
                bean.setSecAmount(BigDecimal.valueOf(item.getEntrustNum()));

                Map<String, Object> map = BeanUtil.object2Map(bean);
                // 下单委托编号
                map.put(ORD_ENTRUST_ID, entity.getEntrustNo());
                ptlList.add(map);
            }
            resultMap.put(PTL_LIST, ptlList);

            // 组合附属数据集 空
            resultMap.put("ptlSubList", Lists.newArrayList());
        }
    }

    private List<PortfolioCalculateResponse> getCalculateList(Instruction entity) {
        // 调用组合计算接口，获取分配信息
        OrderPlaceBean orderPlaceBean = new OrderPlaceBean();
        orderPlaceBean.setEntrustPrice(entity.getEntrustPrice());
        orderPlaceBean.setEntrustVolume(entity.getEntrustNum());
        orderPlaceBean.setInstructionId(entity.getEntrustId());

        // 调用组合计算接口，得出各组合分配到的数量金额
        ResultBean resultBean = portfolioManager.queryPortfolioCalculate(orderPlaceBean);
        if (resultBean.getErrorCode() == 0) {
            return (List<PortfolioCalculateResponse>) resultBean.getData();
        }
        return Lists.newArrayList();
    }

    /**
     * 设置交易平台参数
     *
     * @param entity    指令信息
     * @param resultMap 参数集合
     */
    private void handleSharesTradePlatform(Instruction entity, Map<String, Object> resultMap) {
        // IF（下单.交易平台 = 大宗交易平台 or 综合协议平台） and 交易方向 = 买入、卖出  and 交易类型 = 意向申报、定价申报、点击成交申报、成交申报、盘后定向申报
        //  THEN 数据字典.交易系统.大宗交易
        // else 数据字典.交易系统.竞价交易 end if

        resultMap.put(SHARES_TRADE_PLATFORM, null);
    }

    /**
     * 质押式通用回购-下单    repo001
     *
     * @param entity
     * @param resultMap
     */
    private void repo001(PositionInstructionRequest entity, Map<String, Object> resultMap) {
        resultMap.put("interfaceType", PositionExchangeConstant.INTERF_TYPE_REPO001);
        // 业务动作
        resultMap.put(BUSI_ACT, DictCodeListExt3.业务动作_交易下单.getValue());

        resultMap.put(SETTLE_SPEED, null);
        resultMap.put(SHARES_TRADE_PLATFORM, null);
        // TODO 汇总交易编号 uuid和header一样
        resultMap.put(TOTAL_TRADE_ID, null);

        // 调用组合计算接口，获取分配信息
        List<PortfolioCalculateResponse> calculates = getCalculateList(entity);

        if (CollectionUtils.isNotEmpty(calculates)) {
            PositionRequestBaseBean bean;

            // 组合数据集
            List<Map<String, Object>> ptlList = new ArrayList<>(4);
            for (PortfolioCalculateResponse item : calculates) {
                bean = new PositionRequestBaseBean(entity);
                bean.setFinprodId(null);
                bean.setTradeId(entity.getEntrustNo());
                bean.setEtrId(null);
                bean.setCashAmount(item.getEntrustAmt());
                bean.setSecAmount(BigDecimal.valueOf(item.getEntrustNum()));

                Map<String, Object> map = BeanUtil.object2Map(bean);
                // 下单委托编号
                map.put(ORD_ENTRUST_ID, entity.getEntrustNo());
                // 标准券代码
                map.put(STANDAR_SEC_ID, STANDAR_SEC_ID);
                // 标准券总额 标准券数量*折算率
                map.put(STANDARD_SEC_AMOUNT, entity.getEntrustAmt());
                // 再折算率	reConversionRate "调用【再折算率查询】参数：资金账户 = 指令资金账户 TODO
                ImmutableMap<String, Object> param = ImmutableMap.of("acctNo", entity.getAcctNo());
//                TrdMoreConvrateDto moreConvrate = instruction.getMoreConvrate(param);
//                if (Objects.nonNull(moreConvrate)) {
//                    map.put("reConversionRate", moreConvrate.getMoreConvrate());
//                }
                ptlList.add(map);
            }

            String ps = entity.getPs();
            // 质押券数据集	ptlSecList
            if (DictCodeListExt3.交易方向_质押券入库.getValue().equals(ps) || DictCodeListExt3.交易方向_质押券出库.getValue().equals(ps)) {
                List<Map<String, Object>> list = new ArrayList<>(4);
                for (PortfolioCalculateResponse item : calculates) {
                    bean = new PositionRequestBaseBean(entity);
                    bean.setTradeId(entity.getEntrustNo());
                    bean.setEtrId(null);
                    // 券面总额 指令.指令数量*100
                    bean.setSecAmount(bean.getSecAmount().multiply(BigDecimal.valueOf(100)));

                    Map<String, Object> map = BeanUtil.object2Map(bean);
                    //融入券券面总额
                    map.put("integrateSecAmount", null);
                    //本方券券面总额 指令对应组合.指令数量*100
                    map.put("ownSecAmount", bean.getSecAmount());

                    list.add(map);
                }
                resultMap.put("ptlSecList", list);
            }
        }
    }

    /**
     * 质押式协议回购-下单    repo002
     *
     * @param entity
     * @param resultMap
     */
    private void repo002(PositionInstructionRequest entity, Map<String, Object> resultMap) {
        resultMap.put("interfaceType", PositionExchangeConstant.INTERF_TYPE_REPO002);
        // 业务动作
        resultMap.put(BUSI_ACT, DictCodeListExt3.业务动作_交易下单.getValue());

        resultMap.put(SETTLE_SPEED, null);
        resultMap.put(SHARES_TRADE_PLATFORM, null);
        resultMap.put(DELIVERY_TYPE, null);

        // 协议回购不存在多组合，不需要循环多组合列表
        PositionRequestBaseBean bean;
        String ps = entity.getPs();
        // 首期组合现金数据集
        if (DictCodeListExt3.交易方向_正回购.getValue().equals(ps)
                || DictCodeListExt3.交易方向_逆回购.getValue().equals(ps)
                || DictCodeListExt3.交易方向_首期.getValue().equals(ps)
                || DictCodeListExt3.交易方向_提前购回.getValue().equals(ps)
                || (DictCodeListExt3.交易方向_到期购回.getValue().equals(ps))) {
            List<Map<String, Object>> list = new ArrayList<>(4);
            bean = new PositionRequestBaseBean(entity);
            bean.setEtrId(null);
            bean.setSecAmount(null);

            Map<String, Object> map = BeanUtil.object2Map(bean);
            list.add(map);
            resultMap.put("ptlCashList", list);
        }

        // 轧差组合现金数据集
        if (DictCodeListExt3.交易方向_到期续做.getValue().equals(ps)) {
            List<Map<String, Object>> list = new ArrayList<>(4);
            bean = new PositionRequestBaseBean(entity);
            bean.setTradeId(entity.getEntrustNo());
            bean.setEtrId(entity.getEntrustId());
            bean.setSecAmount(null);
            // 交易金额 TODO
            // "IF 回购类型 = 正回购 THEN 指令.到期续作金额 - 指令.首期的到期结算金额
            // IF 回购类型 = 逆回购  and 成交编号找不到对应的首期 THEN 指令.到期续作金额
            // IF 回购类型 = 逆回购  and 成交编号找到对应的首期 THEN  指令.到期续作金额 - 指令.首期的到期结算金额"
            if (DictCodeListExt3.交易方向_正回购.getValue().equals(resultMap.get("ps"))) {
                bean.setCashAmount(null);
            } else if (DictCodeListExt3.交易方向_逆回购.getValue().equals(resultMap.get("ps"))) {
                // 成交编号
            }

            Map<String, Object> map = BeanUtil.object2Map(bean);
            list.add(map);
            resultMap.put("ptlOffsetCashList", list);
        }

        // 质押券数据集	ptlSecList
        if (DictCodeListExt3.交易方向_解除质押.getValue().equals(ps)) {
            List<Map<String, Object>> list = new ArrayList<>(4);
            // 查询质押券集合
            List<InstructionPledge> instructionPledges = instructionPledgeService.queryInstructionPledgesByInstructionId(entity.getEntrustId());
            for (InstructionPledge item : instructionPledges) {
                bean = new PositionRequestBaseBean();
                bean.setPortfolioId(entity.getPortfolioId());
                // 债券代码 指令.质押券的金融产品代码
                bean.setFinprodId(item.getFinprodId());
                bean.setSecAcctId(entity.getSecAcctId());
                bean.setTradeId(entity.getEntrustId());
                bean.setEtrId(null);
                bean.setSecAmount(BigDecimal.valueOf(100L).multiply(item.getPledgeNumber()));

                Map<String, Object> map = BeanUtil.object2Map(bean);
                //融入券券面总额
                map.put("integrateSecAmount", null);
                //本方券券面总额 指令.质押券数量*100
                map.put("ownSecAmount", BigDecimal.valueOf(100L).multiply(item.getPledgeNumber()));

                list.add(map);
            }
            resultMap.put("ptlSecList", list);
        }

        // 解除质押券数据集	ptlRelieveSecList
        if (DictCodeListExt3.交易方向_解除质押.getValue().equals(ps) || DictCodeListExt3.交易方向_到期续做.getValue().equals(ps)) {
            List<Map<String, Object>> list = new ArrayList<>(4);
            // 查询质押券集合
            List<InstructionPledge> instructionPledges = instructionPledgeService.queryInstructionPledgesByInstructionId(entity.getEntrustId());
            for (InstructionPledge item : instructionPledges) {
                bean = new PositionRequestBaseBean();
                bean.setPortfolioId(entity.getPortfolioId());
                // 债券代码 指令.质押券的金融产品代码
                bean.setFinprodId(item.getFinprodId());
                bean.setSecAcctId(entity.getSecAcctId());
                bean.setTradeId(entity.getEntrustId());
                bean.setEtrId(entity.getEntrustNo());
                if (DictCodeListExt3.交易方向_解除质押.getValue().equals(ps)) {
                    bean.setSecAmount(BigDecimal.valueOf(100L).multiply(item.getPledgeNumber()));
                } else {
                    // 下单到期续作对应首期的成交回报.质押券数量. 到期续作利用首期成交编号找首期的质押券
                    bean.setSecAmount(item.getOldPledgeNumber());
                }

                Map<String, Object> map = BeanUtil.object2Map(bean);
                //融入券券面总额
                map.put("integrateSecAmount", null);
                //本方券券面总额 指令.质押券数量*100
                if (DictCodeListExt3.交易方向_解除质押.getValue().equals(ps)) {
                    map.put("ownSecAmount", BigDecimal.valueOf(100L).multiply(item.getPledgeNumber()));
                } else {
                    map.put("ownSecAmount", item.getOldPledgeNumber());
                    // 下单到期续作对应首期的成交回报.质押券数量. 到期续作利用首期成交编号找首期的质押券
                    bean.setSecAmount(item.getOldPledgeNumber());
                }
                list.add(map);
            }
            resultMap.put("ptlRelieveSecList", list);
        }
    }

    /**
     * 股票-下单    stock001
     *
     * @param entity
     * @param resultMap
     */
    private void stock001(PositionInstructionRequest entity, Map<String, Object> resultMap) {
        resultMap.put("interfaceType", PositionExchangeConstant.INTERF_TYPE_STOCK001);
        // 业务动作
        resultMap.put(BUSI_ACT, DictCodeListExt3.业务动作_交易下单.getValue());
        // 交易方向
        String ps = entity.getPs();
        // 清算速度
        resultMap.put(SETTLE_SPEED, null);

        // 调用组合计算接口，获取分配信息
        List<PortfolioCalculateResponse> calculates = getCalculateList(entity);

        if (CollectionUtils.isNotEmpty(calculates)) {
            PositionRequestBaseBean bean;

            // 组合数据集
            List<Map<String, Object>> ptlList = new ArrayList<>(4);
            for (PortfolioCalculateResponse item : calculates) {
                bean = new PositionRequestBaseBean(entity);
                bean.setCashAmount(item.getEntrustAmt());
                bean.setSecAmount(BigDecimal.valueOf(item.getEntrustNum()));

                Map<String, Object> map = BeanUtil.object2Map(bean);
                // 下单委托编号
                map.put(DELIVERY_TYPE, entity.getEntrustNo());
                ptlList.add(map);
            }
            resultMap.put(PTL_LIST, ptlList);

            // 组合配股/新股申购数据集	ptlRoList
            if (DictCodeListExt2.交易方向_配股.getValue().equals(ps) || DictCodeList.交易方向_申购.getValue().equals(ps)) {
                List<Map<String, Object>> ptlRoList = new ArrayList<>(4);
                for (PortfolioCalculateResponse item : calculates) {
                    bean = new PositionRequestBaseBean(entity);
                    // 配股/申购代码
                    bean.setFinprodId(entity.getConShareId());
                    bean.setCashAmount(item.getEntrustAmt());
                    bean.setSecAmount(BigDecimal.valueOf(item.getEntrustNum()));

                    Map<String, Object> map = BeanUtil.object2Map(bean);
                    // 下单委托编号
                    map.put(ORD_ENTRUST_ID, entity.getEntrustNo());
                    ptlRoList.add(map);
                }
                resultMap.put("ptlRoList", ptlRoList);
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
        String deliveryType = (String) resultMap.get(DELIVERY_TYPE);
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
    private void setCommonField(Instruction entity, Map<String, Object> resultMap) {
        String balanceCheck = "Y";
//        if (StringUtil.isNotEmpty(entity.getSoftSkipMode()) && StringUtil.equals(entity.getSoftSkipMode(), "Y")) {
//            balanceCheck = "N";
//        }
        resultMap.put("userId", SecurityUtil.getLoginUser().getUserName());
        resultMap.put("totalTradeId", entity.getEntrustId());
        resultMap.put("balanceCheck", balanceCheck);
        resultMap.put("flowStatus", DictCodeListExt3.流程节点_流程提交.getValue());
        resultMap.put("modelType", DictCodeListExt1.子系统代码_委托.getValue());

        //设置头寸业务类型
        String busiType = commonPositionEngineService.getPositionBusiType(entity.getFinprodType2(), entity.getPs(), entity.getAssetType());
        resultMap.put(BUSI_TYPE, busiType);

        // 结算方式	见数据字典：结算方式
//        // 调用【共通:结算方式判断】 返回.结算方式
//        String deliveryType = commonPositionEngineService.getDeliveryType(entity.getFinprodId());
        resultMap.put(DELIVERY_TYPE, null);
        // 交易方向
        resultMap.put(PS, entity.getPs());
        // 交易场所	见数据字典：交易场所
        resultMap.put(TRADE_PLACE, entity.getTradeMarket());
        // 交易平台	见数据字典：交易平台
        if (StringUtil.isEmpty(entity.getTradePlat())) {
            resultMap.put(SHARES_TRADE_PLATFORM, 交易平台_竞价撮合平台);
        } else {
            resultMap.put(SHARES_TRADE_PLATFORM, entity.getTradePlat());
        }
        // 流程编号	指令的工作流ID
        resultMap.put(WORK_FLOW_ID, null);
        // 日期
        resultMap.put("cdate", DateUtil.format(entity.getVdate()));
        // 币种
        if (StringUtil.isNotEmpty(entity.getCcy())) {
            resultMap.put(CCY, entity.getCcy());
        } else {
            resultMap.put(CCY, DictCodeList.币种_人民币_CNY.getValue());
        }
        // 前次交易编号
        resultMap.put(B_TOTAL_TRADE_ID, null);
    }
}
