package com.joyintech.tams.exchange.service.trade.position.service;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
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
import com.joyintech.tams.invest.common.position.service.CommonPositionEngineService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static com.joyintech.tams.constant.DictCodeList.交易平台_竞价撮合平台;
import static com.joyintech.tams.constant.DictCodeList.是否_否;
import static com.joyintech.tams.constant.DictCodeListExt3.*;
import static com.joyintech.tams.exchange.common.constant.joyin.DictCodeList.交易方向_申购;
import static com.joyintech.tams.invest.common.position.constant.PositionCommonConstant.*;

@Service
public class PositionRequestServiceInstructionRequest {

    @Autowired
    private CommonPositionEngineService commonPositionEngineService;
    @Autowired
    private PortfolioManager portfolioManager;
    @Autowired
    private InstructionPledgeService instructionPledgeService;

    /**
     * Instruction 请求对象使用
     */
    public void sendRequest(PositionInstructionRequest entity, String busiAct, String decisionAct, String systemAct) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("busiAct", busiAct);
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
        String interType = commonPositionEngineService.getPositionInterType(POSITION_TYPE_TRD, entity.getTradePlat(), finprodType, entity.getPs(), "new", entity.getClass().getName());
        if (INTERF_TYPE_BOND002.equals(interType)) {
            this.bond002(entity, resultMap);
        } else if (INTERF_TYPE_BOND003.equals(interType)) {
            this.bond003(entity, resultMap);
        } else if (INTERF_TYPE_FUND001.equals(interType)) {
            this.fund001(entity, resultMap);
        } else if (INTERF_TYPE_REPO001.equals(interType)) {
            this.repo001(entity, resultMap);
        } else if (INTERF_TYPE_REPO002.equals(interType)) {
            this.repo002(entity, resultMap);
        } else if (INTERF_TYPE_STOCK001.equals(interType)) {
            this.stock001(entity, resultMap);
        }
        PositionHttpRequest.send(resultMap);
    }

    /**
     * 场内现券-下单    bond002
     *
     * @param entity    下单信息
     * @param resultMap 参数集合
     */
    private void bond002(PositionInstructionRequest entity, Map<String, Object> resultMap) {
        resultMap.put(INTERFACE_TYPE, INTERF_TYPE_BOND002);
        resultMap.put(BUSI_ACT, 业务动作_交易下单.getValue());

        resultMap.put(DELIVERY_TYPE, parseDeliveryType(entity));
        resultMap.put(SETTLE_SPEED, parseSettleSpreed(resultMap));
        resultMap.put(BID_RESULT, 是否_否.getValue());
        resultMap.put(PURCH_MODLE, null);
        resultMap.put(FINPROD_ID, entity.getFinprodId());
        resultMap.put(B_TOTAL_TRADE_ID, null);

        List<PortfolioCalculateResponse> calculates = getCalculateList(entity);
        int calculateSize = calculates.size();

        if (CollectionUtils.isNotEmpty(calculates)) {
            PositionRequestBaseBean bean;
            // 组合数据集
            List<Map<String, Object>> ptlList = new ArrayList<>(calculateSize);
            for (PortfolioCalculateResponse item : calculates) {
                bean = new PositionRequestBaseBean(entity);
                bean.setSecAmount(getSecAmountByBondUnit(BigDecimal.valueOf(item.getEntrustNum())));
                bean.setCashAmount(item.getEntrustAmt());

                Map<String, Object> map = BeanUtil.object2Map(bean);
                // 下单委托编号
                map.put(ORD_ENTRUST_ID, entity.getEntrustNo());
                map.put(INV_AIM, entity.getInvAim());
                map.put(INTEGRATE_SEC_AMOUNT, null);
                map.put(OWN_SEC_AMOUNT, bean.getSecAmount());
                ptlList.add(map);
            }
            resultMap.put(PTL_LIST, ptlList);
        }
    }

    /**
     * 可转债-下单    bond003
     *
     * @param entity
     * @param resultMap
     */
    private void bond003(PositionInstructionRequest entity, Map<String, Object> resultMap) {
        resultMap.put(INTERFACE_TYPE, INTERF_TYPE_BOND003);
        // 业务动作
        resultMap.put(BUSI_ACT, 业务动作_交易下单.getValue());

        resultMap.put(DELIVERY_TYPE, parseDeliveryType(entity));
        resultMap.put(SETTLE_SPEED, parseSettleSpreed(resultMap));
        resultMap.put(PURCH_MODLE, null);

        List<PortfolioCalculateResponse> calculates = getCalculateList(entity);
        int calculateSize = calculates.size();

        if (CollectionUtils.isNotEmpty(calculates)) {
            PositionRequestBaseBean bean;

            // 组合现金数据集
            List<Map<String, Object>> list = new ArrayList<>(calculateSize);
            for (PortfolioCalculateResponse item : calculates) {
                bean = new PositionRequestBaseBean(entity);
                bean.setPortfolioId(item.getPortfolioId());
                bean.setFinprodId(null);
                bean.setTradeId(entity.getEntrustNo());
                bean.setEtrId(entity.getEntrustId());
                bean.setCashAmount(item.getEntrustAmt());

                Map<String, Object> map = BeanUtil.object2Map(bean);
                list.add(map);
            }
            resultMap.put(PTL_CASH_LIST, list);

            // 股票持仓数据集
            String ps = entity.getPs();
            if (交易方向_债转股.getValue().equals(ps)) {
                // 股票持仓数据集
                List<Map<String, Object>> sharesSecList = new ArrayList<>(calculateSize);
                for (PortfolioCalculateResponse item : calculates) {
                    bean = new PositionRequestBaseBean();
                    bean.setPortfolioId(item.getPortfolioId());
                    bean.setFinprodId(entity.getConShareId());
                    bean.setSecAmount(entity.getConNum());
                    Map<String, Object> map = BeanUtil.object2Map(bean);
                    sharesSecList.add(map);
                }
                resultMap.put(SHARES_SEC_LIST, sharesSecList);
            }

            // 可转债数据集
            List<Map<String, Object>> bondSecList = new ArrayList<>(calculateSize);
            for (PortfolioCalculateResponse item : calculates) {
                bean = new PositionRequestBaseBean(entity);
                bean.setPortfolioId(item.getPortfolioId());
                bean.setSecAmount(getSecAmountByBondUnit(BigDecimal.valueOf(item.getEntrustNum())));

                Map<String, Object> map = BeanUtil.object2Map(bean);
                //融入券券面总额
                map.put(INTEGRATE_SEC_AMOUNT, null);
                //本方券券面总额
                map.put(OWN_SEC_AMOUNT, bean.getSecAmount());
                bondSecList.add(map);
            }
            resultMap.put("bondSecList", bondSecList);

            if (交易方向_配债.getValue().equals(ps) || 交易方向_申购.getValue().equals(ps)) {
                // 配债申购数据集
                List<Map<String, Object>> applyBondSecList = new ArrayList<>(calculateSize);
                for (PortfolioCalculateResponse item : calculates) {
                    bean = new PositionRequestBaseBean(entity);
                    bean.setPortfolioId(item.getPortfolioId());
                    bean.setFinprodId(entity.getConShareId());
                    bean.setSecAmount(getSecAmountByBondUnit(BigDecimal.valueOf(item.getEntrustNum())));
                    Map<String, Object> map = BeanUtil.object2Map(bean);
                    applyBondSecList.add(map);
                }
                resultMap.put(APPLY_BOND_SEC_LIST, applyBondSecList);
            }
        }
    }

    /**
     * 基金-下单    fund001
     *
     * @param entity
     * @param resultMap
     */
    private void fund001(PositionInstructionRequest entity, Map<String, Object> resultMap) {
        resultMap.put(INTERFACE_TYPE, INTERF_TYPE_FUND001);
        // 业务动作
        resultMap.put(BUSI_ACT, 业务动作_交易下单.getValue());
        // 清算速度
        resultMap.put(SETTLE_SPEED, null);
        // 结算方式
        resultMap.put(DELIVERY_TYPE, null);
        // 当日回转
        resultMap.put(SAME_DAY_TURN, 是否_否);
        // ETF申赎方式
        resultMap.put(ETFREDEMPTION_METHOD, null);

        List<PortfolioCalculateResponse> calculates = getCalculateList(entity);
        int calculateSize = calculates.size();

        if (CollectionUtils.isNotEmpty(calculates)) {
            PositionRequestBaseBean bean;

            // 组合数据集
            List<Map<String, Object>> ptlList = new ArrayList<>(calculateSize);
            for (PortfolioCalculateResponse item : calculates) {
                bean = new PositionRequestBaseBean(entity);
                bean.setPortfolioId(item.getPortfolioId());
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

    /**
     * 质押式通用回购-下单    repo001
     *
     * @param entity    下单对象
     * @param resultMap 参数集合
     */
    private void repo001(PositionInstructionRequest entity, Map<String, Object> resultMap) {
        resultMap.put(INTERFACE_TYPE, INTERF_TYPE_REPO001);
        // 业务动作
        resultMap.put(BUSI_ACT, 业务动作_交易下单.getValue());

        resultMap.put(SETTLE_SPEED, null);
        resultMap.put(SHARES_TRADE_PLATFORM, null);
        resultMap.put(DELIVERY_TYPE, null);
        // TODO 汇总交易编号 uuid和header一样?
        resultMap.put(TOTAL_TRADE_ID, null);

        // 调用组合计算接口，获取分配信息
        List<PortfolioCalculateResponse> calculates = getCalculateList(entity);
        int calculateSize = calculates.size();

        if (CollectionUtils.isNotEmpty(calculates)) {
            PositionRequestBaseBean bean;

            // 组合数据集
            List<Map<String, Object>> ptlList = new ArrayList<>(calculateSize);
            for (PortfolioCalculateResponse item : calculates) {
                bean = new PositionRequestBaseBean(entity);
                bean.setPortfolioId(item.getPortfolioId());
                bean.setFinprodId(null);
                bean.setTradeId(entity.getEntrustNo());
                bean.setEtrId(null);
                bean.setCashAmount(item.getEntrustAmt());
                bean.setSecAmount(null);

                Map<String, Object> map = BeanUtil.object2Map(bean);
                // 下单委托编号
                map.put(ORD_ENTRUST_ID, entity.getEntrustNo());
                // 标准券代码
                map.put(STANDARD_SEC_ID, STANDAR_SEC_ID);
                // 标准券总额 委托数量*折算率
                map.put(STANDARD_SEC_AMOUNT, item.getEntrustAmt());
                // 再折算率	reConversionRate "调用【再折算率查询】参数：资金账户 = 指令资金账户 TODO
                ImmutableMap<String, Object> param = ImmutableMap.of("acctNo", entity.getAcctNo());
//                TrdMoreConvrateDto moreConvrate = instruction.getMoreConvrate(param);
//                if (Objects.nonNull(moreConvrate)) {
//                    map.put("reConversionRate", moreConvrate.getMoreConvrate());
//                }
                ptlList.add(map);
            }
            resultMap.put(PTL_LIST, ptlList);

            String ps = entity.getPs();
            // 质押券数据集	ptlSecList
            if (交易方向_质押券入库.getValue().equals(ps) || 交易方向_质押券出库.getValue().equals(ps)) {
                List<Map<String, Object>> list = new ArrayList<>(calculateSize);
                for (PortfolioCalculateResponse item : calculates) {
                    bean = new PositionRequestBaseBean(entity);
                    bean.setPortfolioId(item.getPortfolioId());
                    bean.setFinprodId(entity.getFinprodId());
                    bean.setEtrId(null);
                    bean.setCashAmount(null);
                    // 券面总额 下单数量*100
                    bean.setSecAmount(BigDecimal.valueOf(item.getEntrustNum() * 100L));

                    Map<String, Object> map = BeanUtil.object2Map(bean);
                    //融入券券面总额
                    map.put(INTEGRATE_SEC_AMOUNT, null);
                    //本方券券面总额 指令对应组合.指令数量*100
                    map.put(OWN_SEC_AMOUNT, bean.getSecAmount());

                    list.add(map);
                }
                resultMap.put(PTL_SEC_LIST, list);
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
        resultMap.put(INTERFACE_TYPE, INTERF_TYPE_REPO002);
        // 业务动作
        resultMap.put(BUSI_ACT, 业务动作_交易下单.getValue());

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
            List<Map<String, Object>> list = new ArrayList<>(1);
            bean = new PositionRequestBaseBean(entity);
            bean.setEtrId(null);
            bean.setSecAmount(null);

            Map<String, Object> map = BeanUtil.object2Map(bean);
            list.add(map);
            resultMap.put("ptlCashList", list);
        }

        // 轧差组合现金数据集
        if (DictCodeListExt3.交易方向_到期续做.getValue().equals(ps)) {
            List<Map<String, Object>> list = new ArrayList<>(1);
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
            resultMap.put(PTL_SEC_LIST, list);
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
        resultMap.put(INTERFACE_TYPE, INTERF_TYPE_STOCK001);
        // 业务动作
        resultMap.put(BUSI_ACT, 业务动作_交易下单.getValue());
        // 清算速度
        resultMap.put(SETTLE_SPEED, null);

        // 调用组合计算接口，获取分配信息
        List<PortfolioCalculateResponse> calculates = getCalculateList(entity);
        int calculateSize = calculates.size();

        if (CollectionUtils.isNotEmpty(calculates)) {
            PositionRequestBaseBean bean;

            // 组合数据集
            List<Map<String, Object>> ptlList = new ArrayList<>(calculateSize);
            for (PortfolioCalculateResponse item : calculates) {
                bean = new PositionRequestBaseBean(entity);
                bean.setPortfolioId(item.getPortfolioId());
                bean.setTradeId(entity.getEntrustId());
                bean.setEtrId(null);
                bean.setCashAmount(item.getEntrustAmt());
                bean.setSecAmount(BigDecimal.valueOf(item.getEntrustNum()));

                Map<String, Object> map = BeanUtil.object2Map(bean);
                // 下单委托编号
                map.put(ORD_ENTRUST_ID, entity.getEntrustNo());
                ptlList.add(map);
            }
            resultMap.put(PTL_LIST, ptlList);

            String ps = entity.getPs();
            // 组合配股/新股申购数据集	ptlRoList
            if (DictCodeListExt2.交易方向_配股.getValue().equals(ps) || 交易方向_申购.getValue().equals(ps)) {
                List<Map<String, Object>> ptlRoList = new ArrayList<>(calculateSize);
                for (PortfolioCalculateResponse item : calculates) {
                    bean = new PositionRequestBaseBean(entity);
                    bean.setPortfolioId(item.getPortfolioId());
                    bean.setTradeId(entity.getEntrustId());
                    bean.setEtrId(null);
                    // 配股/申购代码
                    bean.setFinprodId(entity.getConShareId());
                    bean.setCashAmount(item.getEntrustAmt());
                    bean.setSecAmount(BigDecimal.valueOf(item.getEntrustNum()));

                    Map<String, Object> map = BeanUtil.object2Map(bean);
                    // 下单委托编号
                    map.put(ORD_ENTRUST_ID, entity.getEntrustNo());
                    ptlRoList.add(map);
                }
                resultMap.put(PTL_RO_LIST, ptlRoList);
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
     * 解析结算方式
     *
     * @param instruction 下单信息
     * @return 结算方式
     */
    private String parseDeliveryType(Instruction instruction) {
        return commonPositionEngineService.getDeliveryType(instruction.getFinprodId());
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
        resultMap.put("userId", entity.getTradeId());
        resultMap.put("totalTradeId", entity.getEntrustId());
        resultMap.put("balanceCheck", balanceCheck);
        resultMap.put("flowStatus", DictCodeListExt3.流程节点_审批生效.getValue());
        resultMap.put("modelType", DictCodeListExt1.子系统代码_交易.getValue());

        //设置头寸业务类型
        String busiType = commonPositionEngineService.getPositionBusiType(entity.getFinprodType2(), entity.getPs(), entity.getAssetType());
        resultMap.put(BUSI_TYPE, busiType);

        resultMap.put(DELIVERY_TYPE, null);
        // 交易方向
        resultMap.put(PS, entity.getPs());
        // 交易场所	见数据字典：交易场所
        resultMap.put(TRADE_PLACE, entity.getTradeMarket());
        // 交易平台	见数据字典：交易平台
        if (StringUtils.isEmpty(entity.getTradePlat())) {
            resultMap.put(SHARES_TRADE_PLATFORM, 交易平台_竞价撮合平台);
        } else {
            resultMap.put(SHARES_TRADE_PLATFORM, entity.getTradePlat());
        }
        // 流程编号	指令的工作流ID
        resultMap.put(WORK_FLOW_ID, null);
        // 日期
        resultMap.put("cdate", DateUtil.format(entity.getVdate()));
        // 币种
        if (StringUtils.isNotEmpty(entity.getCcy())) {
            resultMap.put(CCY, entity.getCcy());
        } else {
            resultMap.put(CCY, DictCodeList.币种_人民币_CNY.getValue());
        }
        // 前次交易编号
        resultMap.put(B_TOTAL_TRADE_ID, null);
    }

    /**
     * 获取组合计算信息
     *
     * @param entity 下单对象
     * @return 组合计算信息
     */
    private List<PortfolioCalculateResponse> getCalculateList(Instruction entity) {
        // 组装下单对象
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
}
