/*
 * 文件名：EtrBondFirstLBiddingService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.exchange.entrust.etrexchange.agreementrep.service;


import com.joyintech.background.component.common.pack.common.security.User;
import com.joyintech.background.component.order.exchange.business.dto.*;
import com.joyintech.background.component.order.exchange.common.dto.EtrBaseDto;
import com.joyintech.background.component.order.exchange.common.dto.EtrParameterDto;
import com.joyintech.background.component.order.exchange.common.dto.EtrSinglePortfolioDto;
import com.joyintech.background.component.order.exchange.common.service.impl.EtrComponentFactory;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.service.entrust.etrcommanddispatcher.service.EtrCommandDispatcherService;
import com.joyintech.system.base.entity.SysUser;
import com.joyintech.tams.common.base.dao.EtrEntrustMapper;
import com.joyintech.tams.common.base.dao.FinDealPledgeMapper;
import com.joyintech.tams.common.base.dao.FinTradeProductMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.base.entrust.BusiAutoEtrDispService;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.exchange.entrust.etrexchange.agreementrep.dao.EtrExchangeAgreRepTradeMapper;
import com.joyintech.tams.exchange.entrust.etrexchange.agreementrep.dto.*;
import com.joyintech.tams.exchange.entrust.etrexchange.common.dto.EtrExchangeTradePositionsCommonRequest;
import com.joyintech.tams.exchange.entrust.etrexchange.common.dto.SelectOptionRequestDto;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.position.dto.PostionParameter;
import com.joyintech.tams.invest.common.position.service.CommonPositionService;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 场内交易委托 〈功能详细描述〉
 *
 * @author zhangnian
 * @version 1.0 2019年5月6日
 * @see EtrExchangeAgreRepTrade002Service
 * @since 1.0
 */
@Service
public class EtrExchangeAgreRepTrade002Service extends CommonExportService {

    private static final Logger logger = LoggerFactory.getLogger(EtrExchangeAgreRepTrade002Service.class);

    @Autowired
    private EtrExchangeAgreRepTradeMapper etrExchangeAgreRepTradeMapper;

    /**
     * 平台日期
     */
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;


    public static final String ENTRUST_RECORD_MODIFY = "modify";
    
    public static final String ENTRUST_RECORD_NEW = "new";

    public static String queryrRepurchaseQuote = "/joyin/entrust/integrated/repurchase_quote";

    public static String queryIntegratedTick = "/joyin/quote/tick";
    /**
     BPB	上海协议回购成交申报		    正回购
     BPC	上海协议回购到期续做申报		到期续做
     BPD	上海协议回购解除质押		    接触质押
     BPE	上海协议回购换券申报		    中途换券
     BPF	上海协议回购提前终止申报		提前购回
     */
    private static final String SB_SHXYHGCJ = "BPB";
    private static final String SB_SHXYHGDQXZ = "BPC";
    private static final String SB_SHXYHGJCZY = "BPD";
    private static final String SB_SHXYHGHJSB = "BPE";
    private static final String SB_SHXYHGTQZZSB = "BPF";

    /**
     * 持仓信息查询
     *
     * @param request
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrExchangeAgreRepTrade002Dto> findPositionsPage(EtrExchangeTradePositionsCommonRequest request) {
        Map<String,Object> pageParam = new HashMap<>();
        if (StringUtil.isNotEmpty(request.getPortfolioIdList())) {
            pageParam.put("portfolioIdList",request.getPortfolioIdList());
        }
        if (StringUtil.isNotEmpty(request.getFinprodMarketId())) {
            pageParam.put("finprodMarketId",request.getFinprodMarketId());
        }
        if (StringUtil.isNotEmpty(request.getTradeMarket())) {
            pageParam.put("tradeMarket",request.getTradeMarket());
        }

        pageParam.put("userId", SecurityUtil.getLoginUser().getUserName());
        pageParam.put("sysDate", iPlatformDate.getSysDate());
        pageParam.put("POSITION_TYPE_03", BussinessDictCodeList.持仓类型_实际.getValue());
        pageParam.put("POSITION_TYPE_04", BussinessDictCodeList.持仓类型_质押.getValue());
        pageParam.put("POSITION_TYPE_05", BussinessDictCodeList.持仓类型_融入.getValue());
        pageParam.put("POSITION_TYPE_06", BussinessDictCodeList.持仓类型_融出.getValue());
        pageParam.put("VALUATION_SOURCE_CSD", BussinessDictCodeList.估值来源_中证登.getValue());
        pageParam.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        pageParam.put("FINPROD_TYPE_TG_F06", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        pageParam.put("FINPROD_TYPE_TG_F10", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        pageParam.put("PS_T01", BussinessDictCodeList.交易方向_买入.getValue());
        pageParam.put("PS_T02", BussinessDictCodeList.交易方向_卖出.getValue());
        pageParam.put("PS_T03", BussinessDictCodeList.交易方向_行权回售.getValue());
        pageParam.put("PS_T05", BussinessDictCodeList.交易方向_申购确认.getValue());
        pageParam.put("PS_T19", BussinessDictCodeList.交易方向_赎回确认.getValue());
        pageParam.put("PS_T46", DictCodeListExt2.交易方向_配股.getValue());
        pageParam.put("PS_T48", DictCodeListExt2.交易方向_新股中签.getValue());
        pageParam.put("PS_T49", DictCodeListExt3.交易方向_债转股.getValue());
        pageParam.put("PS_T97", DictCodeListExt3.交易方向_配债.getValue());
        pageParam.put("PS_T04", DictCodeList.交易方向_申购.getValue());
        pageParam.put("TRADE_PLACE_03", BussinessDictCodeList.交易场所_深圳证券交易所.getValue());
        pageParam.put("TRADE_PLACE_04", BussinessDictCodeList.交易场所_上海证券交易所.getValue());
        Page<EtrExchangeAgreRepTrade002Dto> result = super.queryForPage(request.getPageMode().getPageNo(), request.getPageMode().getPageSize(), () -> etrExchangeAgreRepTradeMapper.findPositionsPage(pageParam));
        return result;
    }

    /**
     * 在途回购查询
     *
     * @param request int
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrExchangeOnWayRep002Dto> findOnWayRepPage(EtrExchangeOnWayRepRequestDto request) {
        Map<String,Object> pageParam = new HashMap<>();
        pageParam.put("userId",SecurityUtil.getLoginUser().getUserName());
        pageParam.put("tradeId",request.getTradeId());
        pageParam.put("sysdate", iPlatformDate.getSysDate());
        pageParam.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        pageParam.put("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        pageParam.put("TRADE_PLACE_03", DictCodeListExt1.交易场所_上海证券交易所.getValue());
        pageParam.put("UNIT_01", DictCodeListExt3.交易单位_张.getValue());
        pageParam.put("UNIT_04", DictCodeListExt3.交易单位_手.getValue());

        Page<EtrExchangeOnWayRep002Dto> result = super.queryForPage(request.getPageMode().getPageNo(), request.getPageMode().getPageSize(), () -> etrExchangeAgreRepTradeMapper.findOnWayRepPage(pageParam));
        return result;
    }

    private String mathEntrustProp(String prop){
        switch (prop) {
            case SB_SHXYHGCJ:
                return DictCodeListExt3.交易方向_正回购.getValue();
            case SB_SHXYHGDQXZ:
                return DictCodeListExt3.交易方向_到期续做.getValue();
            case SB_SHXYHGJCZY:
                return DictCodeListExt3.交易方向_解除质押.getValue();
            case SB_SHXYHGHJSB:
                return DictCodeListExt3.交易方向_中途换券.getValue();
            case SB_SHXYHGTQZZSB:
                return DictCodeListExt3.交易方向_提前购回.getValue();
            default:
                return prop;
        }
    }

    /**
     * 调用非公开行情接口
     * @param request
     * @return
     */
    public Page<EtrExchangeUnOpenQuota002Dto> findUnOpenQuotaPage(EtrExchangeUnOpenQuotaRequestDto request) {
        Page<EtrExchangeUnOpenQuota002Dto> page = new Page<>();
        List<EtrExchangeUnOpenQuota002Dto> list = new ArrayList<>();
        List<Map<String, Object>> repurchaseQuotes = new ArrayList<>();
        //接行情数据  start
        String param = "?action_in=1";
        //actionIn   0：公开行情查询 1:非公开行情查询
        // entrustProp 非必填
        Map quoteMap = restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + queryrRepurchaseQuote + param, Map.class);
        if (quoteMap != null && quoteMap.get("data") != null) {
            List<Map<String, Object>> quoteList = (List<Map<String, Object>>) quoteMap.get("data");
            if (!StringUtil.isEmptyList(quoteList)) {
                repurchaseQuotes.addAll(quoteList);
            }
        }
        //接行情数据 end

        //条件筛选
        if(StringUtil.isNotEmpty(request.getRequestId())){
            repurchaseQuotes = repurchaseQuotes
                    .stream()
                    .filter(item -> StringUtil.contains(item.get("cbpconferId") == null ? null : item.get("cbpconferId").toString(), request.getRequestId()))
                    .collect(Collectors.toList());
        }
        if(StringUtil.isNotEmpty(request.getBussType())){
             repurchaseQuotes = repurchaseQuotes
                    .stream()
                    .filter(item -> StringUtil.equals(item.get("entrustProp") == null ? null : mathEntrustProp(item.get("entrustProp").toString()), request.getBussType()))
                    .collect(Collectors.toList());
        }
        if(StringUtil.isNotEmpty(request.getFinprodName())){
            repurchaseQuotes = repurchaseQuotes
                    .stream()
                    .filter(item -> StringUtil.equals(item.get("stockCode") == null ? null : item.get("stockCode").toString(), request.getFinprodName()))
                    .collect(Collectors.toList());
        }

        //去重
        HashSet<String> hSet = new HashSet<>();
        List<Map<String, Object>> newRepurchaseQuotes = new ArrayList<>();
        List<String> stockCodes = new ArrayList<>();
        repurchaseQuotes.forEach(temp->{
            if(StringUtil.isNotEmpty(temp.get("cbpconferId"))&&hSet.add(temp.get("cbpconferId").toString())){
                newRepurchaseQuotes.add(temp);
                // 质押券代码
                if (StringUtil.isNotEmpty(temp.get("stockCode"))) {
                    stockCodes.add(temp.get("stockCode").toString());
                }
            }
        });
        hSet.clear();repurchaseQuotes.clear();
        List<Map<String,Object>> faceValues = new ArrayList<>();
        //封装dto
        //处理标的和面值
        Map<String,Object> tempPara = new HashMap<String, Object>();
        tempPara.put("finprodMarketIdList",stockCodes);
        tempPara.put("tradeDate",DateUtil.getSysDate());
        if (stockCodes.size() > 0) {
            faceValues.addAll(restTemplate.postForObject(
                    ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSecuritiesAccountInfo/getFaceValueList", tempPara,
                    List.class));
        }

        newRepurchaseQuotes.forEach(tmp -> {
            EtrExchangeUnOpenQuota002Dto dto = new EtrExchangeUnOpenQuota002Dto();
            //资金账号
            ///dto.setAcctNo(model.getId());
//            if(StringUtil.isNotEmpty(tmp.get("account_id").toString())){
//                dto.setAcctNo(tmp.get("account_id").toString());
//            }
            // 请求号
            if (StringUtil.isNotEmpty(tmp.get("cbpconferId"))) {
                dto.setReqOnId(tmp.get("cbpconferId").toString());
            }
            // 请求类型
            /**
             BPB	上海协议回购成交申报		    正回购
             BPC	上海协议回购到期续做申报		到期续做
             BPD	上海协议回购解除质押		    接触质押
             BPE	上海协议回购换券申报		    中途换券
             BPF	上海协议回购提前终止申报		提前购回
             */
            String entrustProp = tmp.get("entrustProp") == null ? null : tmp.get("entrustProp").toString();
            if (StringUtil.isNotEmpty(entrustProp)) {
                //BPB	上海协议回购成交申报		    正回购
                if (StringUtil.equals(SB_SHXYHGCJ, entrustProp)) {
                    dto.setReqType(DictCodeListExt3.交易方向_正回购.getValue());
                }
                //BPC	上海协议回购到期续做申报		到期续做
                else if (StringUtil.equals(SB_SHXYHGDQXZ, entrustProp)) {
                    dto.setReqType(DictCodeListExt3.交易方向_到期续做.getValue());
                }
                //BPD	上海协议回购解除质押		    接触质押
                else if (StringUtil.equals(SB_SHXYHGJCZY, entrustProp)) {
                    dto.setReqType(DictCodeListExt3.交易方向_解除质押.getValue());
                }
                //BPE	上海协议回购换券申报		    中途换券
                else if (StringUtil.equals(SB_SHXYHGHJSB, entrustProp)) {
                    dto.setReqType(DictCodeListExt3.交易方向_中途换券.getValue());
                }
                //BPF	上海协议回购提前终止申报		提前购回
                else if (StringUtil.equals(SB_SHXYHGTQZZSB, entrustProp)) {
                    dto.setReqType(DictCodeListExt3.交易方向_提前购回.getValue());
                }
                else{
                    return;
                }
            }
            // 协议回购质权人类型
            if (StringUtil.isNotEmpty(tmp.get("bprsrcStatus"))) {
                dto.setRedoType(tmp.get("bprsrcStatus").toString());
            }
            // 质押券代码
            if (StringUtil.isNotEmpty(tmp.get("stockCode"))) {
                dto.setRepSecId(tmp.get("stockCode").toString());
            }
            // 质押券名称
            if (StringUtil.isNotEmpty(tmp.get("stockName"))) {
                dto.setPledgeStampName(tmp.get("stockName").toString());
            }
            // 原质押代码  忽略 【属于 重复字段】
            // 质押数量
            if (StringUtil.isNotEmpty(tmp.get("impawnAmount"))) {
                dto.setRepSecNum(BigDecimal.valueOf(Double.valueOf(tmp.get("impawnAmount").toString())));
            }
            // 质押券面总额合计 = 质押数量 * 最新面值 [FinProductCommonBatchService.getFaceValue(String finprodId, Date happenDate)]
            if (StringUtil.isNotEmpty(dto.getRepSecNum())) {
                faceValues.forEach(item ->{
                    if(StringUtil.isNotEmpty(tmp.get("stockCode"))&&StringUtil.isNotEmpty(item.get("finprodMarketId"))&&StringUtil.isNotEmpty(item.get("faceValue"))
                            &&StringUtil.equals(tmp.get("stockCode").toString(),item.get("finprodMarketId").toString())){
                        dto.setPledgeStampBalance(dto.getRepSecNum().multiply(new BigDecimal(item.get("faceValue").toString())));
                    }
                });
            }
            // 折算比例
            if (StringUtil.isNotEmpty(tmp.get("funderRatio"))) {
                dto.setConRatio(BigDecimal.valueOf(Double.valueOf(tmp.get("funderRatio").toString())));
            }
            // 回购金额 = 质押券面总额合计*折算比例
            if (StringUtil.isNotEmpty(tmp.get("impawnBalance"))) {
                dto.setTradeAmt(BigDecimal.valueOf(Double.valueOf(tmp.get("impawnBalance").toString())));
            }
            else if (StringUtil.isNotEmpty(dto.getPledgeStampBalance()) && StringUtil.isNotEmpty(dto.getConRatio())) {
                dto.setTradeAmt(dto.getPledgeStampBalance().multiply(dto.getConRatio()).setScale(2,BigDecimal.ROUND_HALF_UP));
            }
            // 回购利率
            if (StringUtil.isNotEmpty(tmp.get("expireYearRate"))) {
                dto.setYield(BigDecimal.valueOf(Double.valueOf(tmp.get("expireYearRate").toString())));
            }
            // 回购期限
            if (StringUtil.isNotEmpty(tmp.get("repoTerm"))) {
                dto.setTerm(Integer.valueOf(tmp.get("repoTerm").toString()));
            }
            // 实际占款天数
            if (StringUtil.isNotEmpty(tmp.get("fundUsedDays"))) {
                dto.setOccupyDays(Integer.valueOf(tmp.get("fundUsedDays").toString()));
            }
            // 首次结算日
            if (StringUtil.isNotEmpty(tmp.get("firstExchdate"))) {
                dto.setFristSettleDate(DateUtil.format(DateUtil.parse(tmp.get("firstExchdate").toString())));
            }
            // 回购到期日
            if (StringUtil.isNotEmpty(tmp.get("repurchaseDate"))) {
                dto.setBackDate(DateUtil.format(DateUtil.parse(tmp.get("repurchaseDate").toString())));
            }
            // 到期结算日
            if (StringUtil.isNotEmpty(tmp.get("settleEndDate"))) {
                dto.setExpireSettleDate(DateUtil.format(DateUtil.parse(tmp.get("settleEndDate").toString())));
            }
            // 原质押券代码
            if (StringUtil.isNotEmpty(tmp.get("oldImpawnCode"))) {
                dto.setOrigPledgeStampCode(tmp.get("oldImpawnCode").toString());
            }
            // 回购利息
            if (StringUtil.isNotEmpty(tmp.get("profit"))) {
                dto.setIntAmt(BigDecimal.valueOf(Double.valueOf(tmp.get("profit").toString())));
            }
            // 到期结算金额
            if (StringUtil.isNotEmpty(tmp.get("settleBalance"))) {
                dto.setExpireSettleAmt(BigDecimal.valueOf(Double.valueOf(tmp.get("settleBalance").toString())));
            }
            // 成交编号
            if (StringUtil.isNotEmpty(tmp.get("origCbpconferId"))) {
                dto.setTradeId(tmp.get("origCbpconferId").toString());
            }
            // 发起方交易员代码
            if (StringUtil.isNotEmpty(tmp.get("oppoTraderId"))) {
                dto.setSponsorTraderCode(tmp.get("oppoTraderId").toString());
            }
            // 发起方交易商代码
            if (StringUtil.isNotEmpty(tmp.get("oppoAgency"))) {
                dto.setSponsorDealerCode(tmp.get("oppoAgency").toString());
            }
            // 本方交易员代码
            if (StringUtil.isNotEmpty(tmp.get("traderId"))) {
                dto.setTraderCode(tmp.get("traderId").toString());
            }
            // 补充协议
            if (StringUtil.isNotEmpty(tmp.get("remark"))) {
                dto.setAddCla(tmp.get("remark").toString());
            }

            // 回购利率和折算率应该乘以100
            if (dto.getYield() != null) {
                dto.setYield(dto.getYield());
            }
            if (dto.getConRatio() != null) {
                dto.setConRatio(ArithmeticUtil.multiply(dto.getConRatio(), BigDecimal.valueOf(100)));
            }

            list.add(dto);
        });
        page.setRoot(list);
        return page;
    }

    public Page<EtrExchangeAgreRepTrade003Dto> selectEtrEntrust(EtrExchangeAgreRepTrade003RequestDto request){
        Map<String,Object> param = new HashMap<>();
//        param.put("entrustStatus", request.getEntrustStatus());
        param.put("entrustId", request.getEntrustId());
        param.put("sysDate",iPlatformDate.getSysDateStr());
        param.put("userId", SecurityUtil.getLoginUser().getUserName());
        param.put("PS_03", DictCodeListExt3.交易方向_正回购.getValue());
        param.put("PS_04", DictCodeListExt3.交易方向_逆回购.getValue());
        param.put("PS_T68", DictCodeListExt3.交易方向_质押券入库.getValue());
        param.put("PS_T69", DictCodeListExt3.交易方向_质押券出库.getValue());
        param.put("TRADE_PLACE_03", DictCodeListExt1.交易场所_上海证券交易所.getValue());
        param.put("UNIT_01", DictCodeListExt3.交易单位_张.getValue());
        param.put("UNIT_04", DictCodeListExt3.交易单位_手.getValue());
        param.put("portfolioName", request.getPortfolioName());
        param.put("finprodId", request.getFinprodId());
        param.put("bussType", request.getBussType());
        param.put("manager", request.getManager());
        param.put("date", request.getDate());
        Page<EtrExchangeAgreRepTrade003Dto> page =  super.queryForPage(request.getPageMode().getPageNo(), request.getPageMode().getPageSize(), () -> etrExchangeAgreRepTradeMapper.selectEtrEntrust(param));

        List<SysUser> userList = restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME + "/EntrustCommon/getAllUserList",
                param, SysUser.class);
        for(EtrExchangeAgreRepTrade003Dto dto:page.getRoot()){
            if(StringUtil.isNotEmpty(dto.getNextUser())&&StringUtil.isNotEmpty(userList)){
                String sysUsers = userList.stream().filter(x-> Arrays.asList(dto.getNextUser().split(",")).contains(x.getUserName()))
                        .map(SysUser::getRealName)
                        .collect(Collectors.joining(",","",""));
                dto.setNextUser(sysUsers);
            }
        }
        return page;
    }
}
