package com.joyintech.tams.invest.trade.exchangetransmatch.position.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.common.base.dao.EtrEntrustMapper;
import com.joyintech.tams.common.base.dao.StlSecAcctInfoMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.position.service.CommonPositionEngineService;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.common.trade.dto.PortfolioCalculateDto;
import com.joyintech.tams.invest.common.trade.service.TrdCommonBaseService;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonRevokeDto;
import com.joyintech.tams.invest.trade.api.PositionRequestTradeRestService;
import com.joyintech.tams.invest.trade.exchangetransmatch.position.bean.PositionRequestBaseBean;
import com.joyintech.tams.invest.trade.exchangetransmatch.position.bean.TrdMoreConvrateDto;
import com.joyintech.tams.invest.trade.exchangetransmatch.position.constant.PositionConstant;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;
import static com.joyintech.tams.constant.DictCodeList.是否_否;
import static com.joyintech.tams.constant.DictCodeListExt3.交易单位_份;
import static com.joyintech.tams.constant.DictCodeListExt3.交易单位_张;
import static com.joyintech.tams.invest.common.position.constant.PositionCommonConstant.*;

@Service
public class PositionRequestServiceFinProductTradeService extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger(PositionRequestServiceFinProductTradeService.class);

    public static final String STANDAR_SEC_ID = "XN_PRODID999";

    @Autowired
    private CommonPositionEngineService commonPositionEngineService;

    @Autowired
    private StlSecAcctInfoMapper stlSecAcctInfoMapper;

    @Autowired
    private EtrEntrustMapper etrEntrustMapper;

    @Autowired
    private TrdCommonBaseService trdCommonBaseService;
  
    public static final String TRADE_PLAT = "TRANS_MATCH";

    /**
     * BaseTradeProductDealDto请求对象使用
     * @param finProductTradeDto
     */
    public void sendFinProductTradeDto(FinProductTradeDto finProductTradeDto) {
        logger.debug("【发送成交匹配头寸请求】finProductTradeDto:{}", JSON.toJSONString(finProductTradeDto));
        for (BaseTradeProductDealDto entity:finProductTradeDto.getBaseTradeProductDealDtos()) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("decisionAct", DictCodeListExt3.系统动作_新建.getValue());
            resultMap.put("systemAct", DictCodeListExt3.系统动作_新建.getValue());
            this.setCommonField(entity, resultMap);//设置公共字段，个性化的需要在具体接口中覆盖
            String type6 = commonPositionEngineService.getType6(entity.getTrdProductDeal().getFinprodId());
            String finprodType = entity.getTrdProductDeal().getFinprodType2();
            if (DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue().equals(type6)) {
                finprodType += "-" + type6;
            }
            if (DictCodeList.回购类型_质押式协议回购.getValue().equals(entity.getAssetType2())
                    || (DictCodeList.回购类型_质押式竞价回购.getValue().equals(entity.getAssetType2()))) {
                finprodType += "-" + entity.getAssetType2();
            }
            String interType =
                    commonPositionEngineService.getPositionInterType(PositionConstant.POSITION_TYPE_TRD, TRADE_PLAT, finprodType, entity.getTrdProductDeal().getPs(),
                            DictCodeListExt3.业务动作_成交匹配.getValue(), entity.getClass().getName());
            if (PositionConstant.INTERF_TYPE_BOND002.equals(interType)) {
                this.bond002(entity, resultMap);
            } else if (PositionConstant.INTERF_TYPE_BOND003.equals(interType)) {
                this.bond003(entity, resultMap);
            } else if (PositionConstant.INTERF_TYPE_FUND001.equals(interType)) {
                this.fund001(entity, resultMap);
            } else if (PositionConstant.INTERF_TYPE_REPO003.equals(interType)) {
                this.repo001(entity, resultMap);
            } else if (PositionConstant.INTERF_TYPE_REPO002.equals(interType)) {
                this.repo002(entity, resultMap);
            } else if (PositionConstant.INTERF_TYPE_STOCK001.equals(interType)) {
                this.stock001(entity, resultMap);
            }
            if(StringUtil.isNotEmpty(resultMap.get(INTERFACE_TYPE))){
                PositionHttpRequest.send(resultMap);
            }else{
                logger.debug("[成交匹配未找到对应的接口配置]");
            }
        }
    }

    /**
     * 撤销时使用---撤销指令-共通接口
     *
     * @param exchangeTransMatchCommonRevokeDtoList
     */
    public void sendRevoke(List<ExchangeTransMatchCommonRevokeDto> exchangeTransMatchCommonRevokeDtoList) {
        for (ExchangeTransMatchCommonRevokeDto entity:exchangeTransMatchCommonRevokeDtoList) {
            String busiType = commonPositionEngineService.getPositionBusiType(entity.getFinprodType2(),entity.getPs(), null);
            PositionHttpRequest.dorevoke(entity.getTradeId(), PositionConstant.POSITION_TYPE_TRD, busiType, entity.getPs());
        }
    }

    /**
     * 场内现券-指令    bond002
     *
     * @param entity
     * @param resultMap
     */
    private void bond002(BaseTradeProductDealDto entity, Map<String, Object> resultMap) {
        resultMap.put(INTERFACE_TYPE, PositionConstant.INTERF_TYPE_BOND002);
        resultMap.put(BUSI_ACT, DictCodeListExt3.业务动作_成交匹配.getValue());
        resultMap.put(BID_RESULT, 是否_否.getValue());
        resultMap.put(PURCH_MODLE, null);
        TrdProductDeal dealEntity = entity.getTrdProductDeal();
        if (StringUtil.isNotEmpty(dealEntity)){
            resultMap.put(FINPROD_ID, dealEntity.getFinprodId());
            String deliveryType = commonPositionEngineService.getDeliveryType(dealEntity.getFinprodId());
            resultMap.put(SETTLE_SPEED, commonPositionEngineService.parseSettleSpreed((String) resultMap.get(DELIVERY_TYPE)));
            resultMap.put(TRADE_PLACE, dealEntity.getTradeMarket());
            resultMap.put(SHARES_TRADE_PLATFORM, dealEntity.getTradePlat());
            resultMap.put(DELIVERY_TYPE, deliveryType);
            resultMap.put(WORK_FLOW_ID, dealEntity.getWorkflowId());
            resultMap.put("cdate", DateUtil.format(dealEntity.getTradeDate()));
            resultMap.put(FINPROD_ID, dealEntity.getFinprodId());
            resultMap.put(B_TOTAL_TRADE_ID, null);
            Map<String,Object> param = Maps.newHashMap();
            param.put("fTradeId",dealEntity.getfTradeId());
            List<PortfolioCalculateDto> portfolioCalculateDtoList =  trdCommonBaseService.queryPortfolioCalculate(param);
            if (CollectionUtils.isNotEmpty(portfolioCalculateDtoList)) {
                List<Map<String, Object>> ptlList = new ArrayList<>();
                for (PortfolioCalculateDto portfolioCalculateDto : portfolioCalculateDtoList) {
                    Map<String, Object> pltMap = new HashMap<>();
                    pltMap.put("portfolioId", portfolioCalculateDto.getPortfolioId());
                    pltMap.put("tradeId", dealEntity.getTradeId());
                    pltMap.put("etrId", entity.getEntrustId());
                    pltMap.put("etrSecAmount", portfolioCalculateDto.getEtrSecAmount());
                    pltMap.put("ordId", portfolioCalculateDto.getOrderId());
                    pltMap.put("ordSecAmount", portfolioCalculateDto.getOrdSecAmount());
//                    pltMap.put("cashAcctId", getCashAcctId(entity.getTrdProductDeal().getSecManageAcctId()));
//                    pltMap.put("secAcctId", entity.getTrdProductDeal().getSecManageAcctId());
                    pltMap.put("cashAcctId", portfolioCalculateDto.getCashAcctId());
                    pltMap.put("secAcctId", portfolioCalculateDto.getSecAcctId());
                    pltMap.put("secAmount", portfolioCalculateDto.getSecAmount());
                    pltMap.put("cashAmount", portfolioCalculateDto.getCashAmount());
                    pltMap.put("invAim", dealEntity.getInvAim());
                    pltMap.put("integrateSecAmount", portfolioCalculateDto.getIntegrateSecAmount());
                    pltMap.put("ownSecAmount", portfolioCalculateDto.getOwnSecAmount());
                    ptlList.add(pltMap);
                }
                resultMap.put("ptlList", ptlList);
            }
        }
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
     * 可转债-指令    bond003
     *
     * @param entity
     * @param resultMap
     */
    private void bond003(BaseTradeProductDealDto entity, Map<String, Object> resultMap) {
        resultMap.put(INTERFACE_TYPE, PositionConstant.INTERF_TYPE_BOND003);
        // 业务动作
        resultMap.put(BUSI_ACT, DictCodeListExt3.业务动作_成交匹配.getValue());
        TrdProductDeal dealEntity = entity.getTrdProductDeal();
        if (StringUtil.isNotEmpty(dealEntity)){
            String ps = dealEntity.getPs();
            resultMap.put(SETTLE_SPEED, commonPositionEngineService.parseSettleSpreed((String) resultMap.get(DELIVERY_TYPE)));
            // 申购方式	见数据字典：申购方式
            if (DictCodeList.交易方向_申购.getValue().equals(dealEntity.getPs())) {
                resultMap.put(PURCH_MODLE, DictCodeListExt2.申购方式_网上申购.getValue());
            }
            resultMap.put(DELIVERY_TYPE, commonPositionEngineService.getDeliveryType(dealEntity.getFinprodId()));
            Map<String,Object> param = Maps.newHashMap();
            param.put("fTradeId",dealEntity.getfTradeId());
            List<PortfolioCalculateDto> portfolioCalculateDtoList =  trdCommonBaseService.queryPortfolioCalculate(param);
            if (CollectionUtils.isNotEmpty(portfolioCalculateDtoList)) {
                PositionRequestBaseBean bean;
                // 组合现金数据集
                List<Map<String, Object>> ptlCashList = new ArrayList<>();
                for (PortfolioCalculateDto item : portfolioCalculateDtoList) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("portfolioId", item.getPortfolioId());
                    // 业务编号
                    map.put("tradeId", dealEntity.getTradeId());
                    // 资金账号
                    map.put("cashAcctId", dealEntity.getSecManageAcctId());
                    // 指令编号
                    map.put("etrId", dealEntity.getEntrustId());
                    // 交易金额
                    map.put("cashAmount", item.getCashAmount());
                    // 指令券面总额
                    map.put("etrSecAmount", item.getEtrSecAmount());
                    // 下单编号
                    map.put("ordId", item.getOrderId());
                    // 下单券面总额
                    map.put("ordSecAmount", item.getOrdSecAmount());
                    ptlCashList.add(map);
                }
                resultMap.put("ptlCashList", ptlCashList);

                if (DictCodeListExt3.交易方向_债转股.getValue().equals(ps)) {
                    // 股票持仓数据集
                    List<Map<String, Object>> sharesSecList = new ArrayList<>();
                    for (PortfolioCalculateDto item : portfolioCalculateDtoList) {
                        bean = new PositionRequestBaseBean();
                        bean.setPortfolioId(item.getPortfolioId());
                        bean.setFinprodId(dealEntity.getFinprodId());
                        bean.setSecAcctId(item.getSecAcctId());
                        bean.setTradeId(dealEntity.getTradeId());
                        bean.setEtrId(entity.getEntrustId());
                        bean.setSecAmount(item.getSecAmount());
                        Map<String, Object> map = BeanUtil.object2Map(bean);
                        sharesSecList.add(map);
                    }
                    resultMap.put(SHARES_SEC_LIST, sharesSecList);
                }

                // 可转债数据集
                List<Map<String, Object>> bondSecList = new ArrayList<>();
                for (PortfolioCalculateDto item : portfolioCalculateDtoList) {
                    bean = new PositionRequestBaseBean();
                    bean.setPortfolioId(item.getPortfolioId());
                    bean.setFinprodId(dealEntity.getFinprodId());
                    bean.setSecAcctId(item.getSecAcctId());
                    bean.setTradeId(dealEntity.getTradeId());
                    bean.setEtrId(entity.getEntrustId());
                    bean.setSecAmount(item.getSecAmount());
                    Map<String, Object> map = BeanUtil.object2Map(bean);
                    //融入券券面总额
                    map.put(INTEGRATE_SEC_AMOUNT, item.getIntegrateSecAmount());
                    //本方券券面总额
                    map.put(OWN_SEC_AMOUNT, item.getOwnSecAmount());
                    bondSecList.add(map);
                }
                resultMap.put("bondSecList", bondSecList);
                if (DictCodeListExt3.交易方向_配债.getValue().equals(ps) || DictCodeList.交易方向_申购.getValue().equals(ps)) {
                    // 配债申购数据集
                    List<Map<String, Object>> applyBondSecList = new ArrayList<>();
                    for (PortfolioCalculateDto item : portfolioCalculateDtoList) {
                        bean = new PositionRequestBaseBean();
                        bean.setPortfolioId(item.getPortfolioId());
                        bean.setFinprodId(dealEntity.getFinprodId());
                        bean.setSecAcctId(item.getSecAcctId());
                        bean.setTradeId(dealEntity.getTradeId());
                        bean.setEtrId(entity.getEntrustId());
                        bean.setSecAmount(item.getSecAmount());
                        Map<String, Object> map = BeanUtil.object2Map(bean);
                        applyBondSecList.add(map);
                    }
                    resultMap.put("applyBondSecList", applyBondSecList);
                }
            }
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
        if (StringUtil.isNotEmpty(dealEntity)) {
            Map<String,Object> param = Maps.newHashMap();
            param.put("fTradeId",dealEntity.getfTradeId());
            List<PortfolioCalculateDto> portfolioCalculateDtoList =  trdCommonBaseService.queryPortfolioCalculate(param);
            if (CollectionUtils.isNotEmpty(portfolioCalculateDtoList)) {
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
                    // "IF 指令.指令单位 = 手 THEN 指令.指令数量*10000
                    //IF 指令.指令单位 = 份 THEN 指令.指令数量"
                    Map<String, Object> map = BeanUtil.object2Map(bean);
                    // 下单委托编号	ordEntrustId
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
     * 质押式通用回购    repo001
     *
     * @param entity
     * @param resultMap
     */
    private void repo001(BaseTradeProductDealDto entity, Map<String, Object> resultMap) {
        // resultMap.put(INTERFACE_TYPE, PositionConstant.INTERF_TYPE_REPO001);
        resultMap.put(INTERFACE_TYPE, PositionConstant.INTERF_TYPE_REPO003);
        // 业务动作
        resultMap.put(BUSI_ACT, DictCodeListExt3.业务动作_成交匹配.getValue());
        resultMap.put(SETTLE_SPEED, null);
        // 结算方式
        resultMap.put(DELIVERY_TYPE, null);
        TrdProductDeal dealEntity = entity.getTrdProductDeal();
        if (StringUtil.isNotEmpty(dealEntity)) {
            String ps = dealEntity.getPs();
            Map<String,Object> param = Maps.newHashMap();
            param.put("fTradeId",dealEntity.getfTradeId());
            List<PortfolioCalculateDto> portfolioCalculateDtoList =  trdCommonBaseService.queryPortfolioCalculate(param);
            if (CollectionUtils.isNotEmpty(portfolioCalculateDtoList)) {
                PositionRequestBaseBean bean;
                // 组合数据集
                List<Map<String, Object>> ptlList = new ArrayList<>();
                for (PortfolioCalculateDto item : portfolioCalculateDtoList) {
                    bean = new PositionRequestBaseBean();
                    bean.setPortfolioId(item.getPortfolioId());
                    bean.setFinprodId(dealEntity.getFinprodId());
                    bean.setSecAcctId(item.getSecAcctId());
                    bean.setCashAcctId(item.getCashAcctId());
                    bean.setTradeId(dealEntity.getTradeId());
                    bean.setEtrId(entity.getEntrustId());
                    bean.setCashAmount(item.getCashAmount());
                    Map<String, Object> map = BeanUtil.object2Map(bean);
                    //下单委托编号
                    map.put(ORD_ENTRUST_ID, null);
                    // 标准券代码
                    map.put(STANDARD_SEC_ID, STANDAR_SEC_ID);
                    // 标准券总额
                    map.put(STANDARD_SEC_AMOUNT, item.getStandardSecAmount());
                    // 再折算率	reConversionRate "调用【再折算率查询】参数：资金账户 = 指令资金账户
                    Map<String, Object> paramMap = Maps.newHashMap();
                    paramMap.put("acctNo", item.getCashAcctId());
                    TrdMoreConvrateDto moreConvrate = this.getMoreConvrate(paramMap);
                    if (StringUtil.isNotEmpty(moreConvrate)) {
                        map.put("reConversionRate", moreConvrate.getMoreConvrate());
                    }
                    ptlList.add(map);
                }
                resultMap.put(PTL_LIST, ptlList);
                // 质押券数据集	ptlSecList
                if (DictCodeListExt3.交易方向_质押券入库.getValue().equals(ps) || DictCodeListExt3.交易方向_质押券出库.getValue().equals(ps)) {
                    List<Map<String, Object>> ptlSecList = new ArrayList<>();
                    for (PortfolioCalculateDto item : portfolioCalculateDtoList) {
                        bean = new PositionRequestBaseBean();
                        bean.setPortfolioId(item.getPortfolioId());
                        bean.setFinprodId(dealEntity.getFinprodId());
//                        bean.setSecAcctId(dealEntity.getSecManageAcctId());
                        bean.setSecAcctId(item.getSecAcctId());
                        bean.setTradeId(dealEntity.getTradeId());
                        bean.setEtrId(entity.getEntrustId());
                        bean.setSecAmount(item.getSecAmount());
                        Map<String, Object> map = BeanUtil.object2Map(bean);
                        //融入券券面总额
                        map.put(INTEGRATE_SEC_AMOUNT, item.getIntegrateSecAmount());
                        //本方券券面总额 指令对应组合.指令数量*100
                        map.put(OWN_SEC_AMOUNT, item.getOwnSecAmount());
                        ptlSecList.add(map);
                    }
                    resultMap.put(PTL_SEC_LIST, ptlSecList);
                }
            }
        }
    }

    /**
     * 场内指令-查询质押券再折算率
     * @param pageParam
     * @return
     */
    public TrdMoreConvrateDto getMoreConvrate(Map<String, Object> pageParam) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("acctNo", (String) pageParam.get("acctNo"));
        Map<String, Object> res = restTemplate.postForObject(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/InterfSysTrdConfig/getMoreConvrate", param, Map.class);
        if (res != null && res.get("data") != null) {
            List<TrdMoreConvrateDto> list = (List<TrdMoreConvrateDto>) res.get("data");
            if (list != null && list.size() > 0) {
                return JSON.parseObject(JSON.toJSONString(list.get(0)), TrdMoreConvrateDto.class);
            }
        }
        return null;
    }

    /**
     * 质押式协议回购-指令    repo002
     *
     * @param entity
     * @param resultMap
     */
    private void repo002(BaseTradeProductDealDto entity, Map<String, Object> resultMap) {
        resultMap.put(INTERFACE_TYPE, PositionConstant.INTERF_TYPE_REPO002);
        // 业务动作
        resultMap.put(BUSI_ACT, DictCodeListExt3.业务动作_成交匹配.getValue());
        // 清算速度
        resultMap.put(SETTLE_SPEED, null);
        // 结算方式
        resultMap.put(DELIVERY_TYPE, null);
        // 交易平台
//        resultMap.put("tradePlace", null);
        TrdProductDeal dealEntity = entity.getTrdProductDeal();
        if (StringUtil.isNotEmpty(dealEntity)){
            String ps = dealEntity.getPs();
            resultMap.put("ps", dealEntity.getPs());
            Map<String,Object> param = Maps.newHashMap();
            param.put("fTradeId",dealEntity.getfTradeId());
            List<PortfolioCalculateDto> portfolioCalculateDtoList =  trdCommonBaseService.queryPortfolioCalculate(param);
            if (CollectionUtils.isNotEmpty(portfolioCalculateDtoList)) {
                PositionRequestBaseBean bean;
                // 首期组合现金数据集
                if (DictCodeListExt3.交易方向_正回购.getValue().equals(ps)
                        || DictCodeListExt3.交易方向_首期.getValue().equals(ps)
                        || DictCodeListExt3.交易方向_提前购回.getValue().equals(ps)
                        || (DictCodeListExt3.交易方向_到期购回.getValue().equals(ps))) {
                    List<Map<String, Object>> list = new ArrayList<>();
                    for (PortfolioCalculateDto item : portfolioCalculateDtoList) {
                        bean = new PositionRequestBaseBean();
                        bean.setPortfolioId(item.getPortfolioId());
//                    bean.setCashAcctId(getCashAcctId(dealEntity.getSecManageAcctId()));
                        bean.setCashAcctId(bean.getCashAcctId());
                        bean.setTradeId(dealEntity.getTradeId());
                        bean.setEtrId(entity.getEntrustId());
                        bean.setCashAmount(item.getCashAmount());
                        Map<String, Object> map = BeanUtil.object2Map(bean);
                        list.add(map);
                    }
                    resultMap.put("ptlCashList", list);
                }
                // 轧差组合现金数据集
                if (DictCodeListExt3.交易方向_到期续做.getValue().equals(ps)) {
                    List<Map<String, Object>> list = new ArrayList<>();
                    for (PortfolioCalculateDto item : portfolioCalculateDtoList) {
                        bean = new PositionRequestBaseBean();
                        bean.setPortfolioId(item.getPortfolioId());
//                    bean.setCashAcctId(getCashAcctId(dealEntity.getSecManageAcctId()));
                        bean.setCashAcctId(item.getCashAcctId());
                        bean.setTradeId(dealEntity.getTradeId());
                        bean.setEtrId(entity.getEntrustId());
                        bean.setCashAmount(item.getCashAmount());
                        Map<String, Object> map = BeanUtil.object2Map(bean);
                        list.add(map);
                    }
                    resultMap.put("ptlOffsetCashList", list);
                }
                // 质押券数据集	ptlSecList
                // if (DictCodeListExt3.交易方向_解除质押.getValue().equals(ps)) {
                if (!DictCodeListExt3.交易方向_正回购.getValue().equals(ps)) {
                    List<Map<String, Object>> list = new ArrayList<>();
                    for (PortfolioCalculateDto item : portfolioCalculateDtoList) {
                        bean = new PositionRequestBaseBean();
                        bean.setPortfolioId(item.getPortfolioId());
                        bean.setFinprodId(dealEntity.getFinprodId());
//                    bean.setSecAcctId(dealEntity.getSecManageAcctId());
                        bean.setSecAcctId(item.getSecAcctId());
                        bean.setTradeId(dealEntity.getTradeId());
                        bean.setEtrId(entity.getEntrustId());
                        bean.setSecAmount(item.getSecAmount());
                        Map<String, Object> map = BeanUtil.object2Map(bean);
                        //融入券券面总额
                        map.put(INTEGRATE_SEC_AMOUNT, item.getIntegrateSecAmount());
                        //本方券券面总额 指令.质押券数量*100
                        map.put(OWN_SEC_AMOUNT, item.getOwnSecAmount());

                        list.add(map);
                    }
                    resultMap.put("ptlSecList", list);
                }
                // 解除质押券数据集	ptlRelieveSecList
                if (DictCodeListExt3.交易方向_解除质押.getValue().equals(ps) || DictCodeListExt3.交易方向_到期续做.getValue().equals(ps)) {
                    List<Map<String, Object>> list = new ArrayList<>();
                    for (PortfolioCalculateDto item : portfolioCalculateDtoList) {
                        bean = new PositionRequestBaseBean();
                        bean.setPortfolioId(item.getPortfolioId());
                        bean.setFinprodId(dealEntity.getFinprodId());
//                    bean.setSecAcctId(dealEntity.getSecManageAcctId());
                        bean.setSecAcctId(item.getSecAcctId());
                        bean.setTradeId(dealEntity.getTradeId());
                        bean.setEtrId(entity.getEntrustId());
                        bean.setSecAmount(item.getSecAmount());
                        Map<String, Object> map = BeanUtil.object2Map(bean);
                        //融入券券面总额
                        map.put(INTEGRATE_SEC_AMOUNT, item.getIntegrateSecAmount());
                        //本方券券面总额 指令.质押券数量*100
                        map.put(OWN_SEC_AMOUNT, item.getOwnSecAmount());
                        list.add(map);
                    }
                    resultMap.put("ptlRelieveSecList", list);
                }
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
            if (CollectionUtils.isNotEmpty(portfolioCalculateDtoList)) {
                PositionRequestBaseBean bean;
                // 组合数据集
                List<Map<String, Object>> ptlList = new ArrayList<>();
                for (PortfolioCalculateDto item : portfolioCalculateDtoList) {
                    bean = new PositionRequestBaseBean();
                    bean.setPortfolioId(item.getPortfolioId());
                    bean.setFinprodId(dealEntity.getFinprodId());
                    bean.setTradeId(dealEntity.getTradeId());
                    bean.setEtrId(entity.getEntrustId());
//                    bean.setSecAcctId(dealEntity.getSecManageAcctId());
                    bean.setSecAcctId(item.getSecAcctId());
                    bean.setSecAmount(dealEntity.getPrinAmt());
//                    bean.setCashAcctId(getCashAcctId(dealEntity.getSecManageAcctId()));
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
//                        bean.setSecAcctId(dealEntity.getSecManageAcctId());
                        bean.setSecAcctId(item.getSecAcctId());
                        bean.setSecAmount(item.getSecAmount());
//                        bean.setCashAcctId(getCashAcctId(dealEntity.getSecManageAcctId()));
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
        resultMap.put("userId", SecurityUtil.getLoginUser() != null ? SecurityUtil.getLoginUser().getUserName() : "system");
        // resultMap.put("totalTradeId", entity.getEntrustId());
        resultMap.put("totalTradeId", entity.getTrdProductDeal().getTradeId());
        resultMap.put("balanceCheck", balanceCheck);
        resultMap.put("flowStatus", DictCodeListExt3.流程节点_流程提交.getValue());
        resultMap.put("modelType", DictCodeListExt1.子系统代码_委托.getValue());
        //设置头寸业务类型
        String busiType = commonPositionEngineService.getPositionBusiType(entity.getTrdProductDeal().getFinprodType2(), entity.getTrdProductDeal().getPs(), entity.getAssetType2());
        resultMap.put("busiType", busiType);

        // 结算方式	见数据字典：结算方式
        // 调用【共通:结算方式判断】 返回.结算方式
//        String deliveryType = commonPositionEngineService.getDeliveryType(entity.getTrdProductDeal().getFinprodId());
//        resultMap.put("deliveryType", deliveryType);
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


    private String getCashAcctId(String secAcctId){
        if (!StringUtil.isNotEmpty(secAcctId)){
            return null;
        }
        StlSecAcctInfo stlSecAcctInfo = stlSecAcctInfoMapper.selectByPrimaryKey(secAcctId);
        if (StringUtil.isNotEmpty(stlSecAcctInfo)){
            return stlSecAcctInfo.getTrdAcctId();
        }
        return null;
    }

}
