/*
 * 文件名：Rsk999Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.test.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrdsinf.common.service.EventBaseService;
import com.joyintech.service.riskrdsinf.core.annotation.INF;
import com.joyintech.service.riskrdsinf.core.client.ClientData;
import com.joyintech.service.riskrdsinf.core.exception.InterfException;
import com.joyintech.redis.common.entity.IValue;
import com.joyintech.redis.common.entity.Value;
import com.joyintech.redis.core.base.PipeWriteService;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.redisinit.dto.ImplAsset;
import com.joyintech.tams.invest.common.redisinit.dto.ImplEvent;
import com.joyintech.tams.invest.common.redisinit.dto.ImplEventItem;
import com.joyintech.tams.invest.common.redisinit.dto.ImplEventParaRel;
import com.joyintech.tams.invest.common.redisinit.dto.ImplIndicator;
import com.joyintech.tams.invest.common.redisinit.dto.ImplParam;
import com.joyintech.tams.invest.common.redisinit.dto.ImplPortf;
import com.joyintech.tams.invest.common.redisinit.dto.ImplPtlIndicator;
import com.joyintech.tams.invest.common.riskinfrds.base.entity.IBusiness;
import com.joyintech.tams.invest.common.riskinfrds.base.service.RedisBaseService;
import com.joyintech.tams.invest.common.riskinfrds.event.entity.EventMap;
import com.joyintech.tams.invest.common.riskinfrds.event.service.RskEventService;
import com.joyintech.tams.invest.common.riskinfrds.indic.service.IndicatorService;
import com.joyintech.tams.invest.common.riskinfrds.info.entity.IAssetInfo;
import com.joyintech.tams.invest.common.riskinfrds.info.entity.IProdInfo;
import com.joyintech.tams.invest.common.riskinfrds.info.service.AssetInfoRedisService;
import com.joyintech.tams.invest.common.riskinfrds.info.service.ProdInfoRedisService;
import com.joyintech.tams.invest.common.riskinfrds.param.service.IndicatorParamService;
import com.joyintech.tams.invest.common.riskinfrds.prod.service.PtlIndicatorService;

/**
 * 测试类接口
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月22日
 * @see Rsk999Service
 * @since 1.0
 */
@INF("RSK999")
@Service
public class Rsk999Service extends EventBaseService<String> {

    @Autowired
    private PipeWriteService<String, String, IValue<Object>> valService;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Map<String, Map<String, String>> handle(ClientData request, ClientData response)
        throws InterfException {
        testAll(request, response);
        return null;
    }

    /**
     * 写入产品信息
     * 
     * @param cnt 产品数量
     */
    private void writeProd(int cnt){
    	ProdInfoRedisService service = getProdInfoSrc();
    	IProdInfo[] prodArr = new IProdInfo[cnt];
    	for(int i = 0; i < cnt; i++){
    		ImplPortf prodInfo = new ImplPortf();
    		// 产品代码
    		prodInfo.setPortfolioId(PTL_KEY + i);
    		// 产品名称
    		prodInfo.setPortfolioName("债券" + i);
    		// 投资性质
    		prodInfo.setInvestNature("01");
    		// 开放类型
            //-------update by peijiajun 核风险---------start--------
    		//prodInfo.setOpenType("01");
            //-------update by peijiajun 核风险-----------end------
    		// 开放频率
    		prodInfo.setOpenMode("01");
    		// 收益类型
    		prodInfo.setProfitType("01");
    		// 产品业绩比较基准
    		prodInfo.setPcbAmout(BigDecimal.ONE);
    		// 是否启用风险
    		prodInfo.setRiskEnabled(BussinessDictCodeListRds.是否_是.getValue());
    		// 日历代码
    		prodInfo.setCalendarId("01");
    		prodInfo.setCreateTime(null);
    		prodInfo.setUpdateTime(null);
    		prodInfo.setCreateUser(null);
    		prodInfo.setUpdateUser(null);
    		prodArr[i] = prodInfo;
    	}
    	service.saveToRedis(prodArr);
    }

    /**
     * 写入资产信息
     * 
     * @param cnt 资产数量
     * @param mk 交易市场
     */
    private void writeAsset(int cnt, String mk) {
        AssetInfoRedisService service = getAssetInfoSrc();
        IAssetInfo[] infoArr = new IAssetInfo[cnt];
        for (int i = 0; i < cnt; i++) {
            ImplAsset info = new ImplAsset();
            // 资产代码
            String code = StringUtil.substring("" + (1000000 + i), 1);
            info.setFinprodId(code + "." + mk);
            // 资产名称
            info.setFinprodAbbr("债券类资产" + i);
            // 资产大类-债券类资产
            info.setType6("05");
            // 资产细类-债券
            info.setType7("01");
            // 日历代码
            info.setCalendarId("001");
            // 投资性质-债权类
            info.setInvestNature("02");
            // 市场代码
            info.setFinprodMarketId(code);
            // 到期日
            info.setMdate(DateUtil.parse("2019-12-31"));
            // 交易场所-上交所
            info.setTradeMarket(mk);
            // 起息日
            info.setVdate(DateUtil.parse("2019-01-01"));
            info.setCreateTime(null);
            info.setUpdateTime(null);
            info.setCreateUser(null);
            info.setUpdateUser(null);
            infoArr[i] = info;
        }
        service.saveToRedis(infoArr);
    }

    private List<ImplEventItem> getMatchEventItem(int i) {
        List<ImplEventItem> itemList = new ArrayList<>();
        // 5个特征值
        String[] itemCodes = {"assetType", "assetType2", "ps", "investType"};
        String[] itemValues = {"05", "01", "T01", "01,02,03"};
        String[] matchTypes = {"=", "=", "=", "IN"};
        for (int x = 0; x < 4; x++ ) {
            ImplEventItem item = new ImplEventItem();
            // 特征值代码
            item.setItemCode(itemCodes[x]);
            // 特征值
            item.setItemValue(itemValues[x]);
            // 匹配方式
            item.setMatchType(matchTypes[x]);
            item.setCreateTime(null);
            item.setUpdateTime(null);
            item.setCreateUser(null);
            item.setUpdateUser(null);
            itemList.add(item);
        }
        return itemList;
    }

    private List<ImplEventItem> getUnMatchEventItem(int i) {
        List<ImplEventItem> itemList = new ArrayList<>();
        // 5个特征值
        String[] itemCodes = {"assetType", "assetType2", "ps", "investType"};
        String[] itemValues = {"05", "01", "T01", "01,02,03"};
        String[] matchTypes = {"<>", "<>", "<>", "NO"};
        for (int x = 0; x < 5; x++ ) {
            ImplEventItem item = new ImplEventItem();
            // 特征值代码
            item.setItemCode(itemCodes[x]);
            // 特征值
            item.setItemValue(itemValues[x]);
            // 匹配方式
            item.setMatchType(matchTypes[x]);
            item.setCreateTime(null);
            item.setUpdateTime(null);
            item.setCreateUser(null);
            item.setUpdateUser(null);
            itemList.add(item);
        }
        return itemList;
    }

    protected void testAll(ClientData request, ClientData response) {
        long start = System.currentTimeMillis();

        /******************************/
        /*********** 产品信息 **********/
        /******************************/
        // 组合数量
        int ptlCnt = new Integer(request.getBodyField("ptlCnt").toString());
        writeProd(ptlCnt);
        response.setBodyField("ptlCnt", "加载产品数量：" + ptlCnt);

        /******************************/
        /*********** 资产信息 **********/
        /******************************/
        int assetCnt = new Integer(request.getBodyField("assetCnt").toString());
        // 写入资产信息
        writeAsset(assetCnt, "SH");
        response.setBodyField("assetCnt", "加载资产数量：" + assetCnt);

        /******************************/
        /*********** 风险事件 **********/
        /******************************/
        // 风险事件数量
        int eventRiskCnt = new Integer(request.getBodyField("eventRiskCnt").toString());
        // 每个指标特征值数量
        int eventItemCnt = new Integer(request.getBodyField("eventItemCnt").toString());
        // 每个指标绑定的参数数量
        int eventParaCnt = new Integer(request.getBodyField("eventParaCnt").toString());
        // 写入风险事件 1000，每个事件有5个特征值，每次可触发1/10 = 100个事件，总共计算4个参数
        writeEvent(eventRiskCnt, eventItemCnt, eventParaCnt);
        response.setBodyField("eventRiskCnt", "加载风险事件数量：" + eventRiskCnt);
        response.setBodyField("eventItemCnt", "加载每个风险事件特征值数量：" + eventItemCnt);
        response.setBodyField("eventParaCnt", "加载每个风险事件关联参数数量：" + eventItemCnt);

        /******************************/
        /********* 组合关联指标 ********/
        /******************************/
        // 组合包数量
        int ptlBagCnt = new Integer(request.getBodyField("ptlBagCnt").toString());
        // 组合绑定的指标数量
        int ptlIndCnt = new Integer(request.getBodyField("ptlInd").toString());
        // 写入组合关联指标 3000个组合，每个组合关联100个指标，每个组合挂5个组合包，每个组合包挂100个指标
        writePtlIndicator(ptlCnt, ptlIndCnt, ptlBagCnt);
        response.setBodyField("ptlBagCnt", "加载每个组合关联组合包数量：" + ptlBagCnt);
        response.setBodyField("ptlIndCnt", "加载每个组合关联指标数量：" + ptlIndCnt);

        /******************************/
        /********* 指标基本信息 ********/
        /******************************/
        // 指标数量
        int indCnt = new Integer(request.getBodyField("indCnt").toString());
        // 写入指标 200，每个指标对应5个参数
        writeIndicator(indCnt);
        response.setBodyField("indCnt", "加载指标数量：" + indCnt);
        
        /******************************/
        /********* 指标参数信息 ********/
        /******************************/
        // 指标参数数量
        int indParamCnt = new Integer(request.getBodyField("indParamCnt").toString());
        // 写入指标参数信息 100
        writeParam(indParamCnt, "01");
        writeParam(indParamCnt, "02");
        writeParam(indParamCnt, "03");
        response.setBodyField("indParamCnt", "指标参数数量：" + indParamCnt * 3);

        /******************************/
        /********** 指标参数值 *********/
        /******************************/
        // 写入指标参数值 100个参数，每个参数对应3000个组合，5个组合包
        writeVal(indParamCnt, ptlCnt, ptlBagCnt);
        response.setBodyField("indParamCnt", "指标参数值数量：" + indParamCnt * (ptlCnt + ptlBagCnt));

        long end = System.currentTimeMillis();
        getLogger().info("write time is {}", end - start);
        response.setBodyField("writeTime", "执行时间：" + (end - start) + "ms");
    }

    protected static final String PARAM_KEY = "PARAM";

    protected static final String PTL_KEY = "PTL";

    protected static final String PTLBAG_KEY = "PTLBAG";

    /**
     * 写入指标参数值,每个参数值初始化为1000
     * 
     * @param p 指标参数数量
     * @param cnt 组合数量
     * @param bagCnt 组合包数量
     */
    private void writeVal(int p, int cnt, int bagCnt) {
        Map<String, Map<String, IValue<Object>>> list = new HashMap<>();
        String header = RedisKeyEnum.指标参数值.key();
        for (int i = 0; i < p; i++ ) {// 循环参数
            for (int j = 1; j < 4; j++ ) {// 实时、买入预占、卖出预占
                String key = header + PARAM_KEY + i + ":0" + j;
                Map<String, IValue<Object>> hash = list.get(key);
                if (hash == null) {
                    hash = new HashMap<>();
                    list.put(key, hash);
                }
                for (int x = 0; x < cnt; x++ ) {// 循环组合
                    Value<Object> val = new Value<>(str -> new BigDecimal(str));
                    val.value("1000");
                    hash.put(PTL_KEY + x, val);
                }

                for (int x = 0; x < bagCnt; x++ ) {// 循环组合包
                    Value<Object> val = new Value<>(str -> new BigDecimal(str));
                    val.value("1000");
                    hash.put(PTLBAG_KEY + x, val);
                }
            }
        }
        valService.writeList(list);
    }

    /**
     * 写入指标参数信息
     * 
     * @param cnt 指标参数数量
     * @param lidu 指标粒度
     */
    private void writeParam(int cnt, String lidu) {
        IndicatorParamService service = getIndParamSrv();
        writeRedis(cnt, service, (i) -> {
            ImplParam param = new ImplParam();
            param.setContent("SELECT 1 FROM DUAL");
            param.setParaCode(PARAM_KEY + i);
            param.setParaGranularity(lidu);
            param.setFieldArrayRedis("portfolioCode");
            param.setCreateTime(null);
            param.setUpdateTime(null);
            param.setCreateUser(null);
            param.setUpdateUser(null);
            return param;
        });
    }

    protected static final String IND_KEY = "IND";

    /**
     * 写入指标信息
     * 
     * @param cnt 指标数量
     */
    private void writeIndicator(int cnt) {
        IndicatorService service = getIndService();
        writeRedis(cnt, service, (i) -> {
            ImplIndicator ind = new ImplIndicator();
            ind.setWarnCond(
                "{\"threshold_1\":{\"value\":\"5\",\"op\":\"<=\",\"unit\":\"PERCENT\"}}");
            ind.setApproveCond(
                "{\"threshold_1\":{\"value\":\"3\",\"op\":\"<=\",\"unit\":\"PERCENT\"}}");
            ind.setFoulCond(
                "{\"threshold_1\":{\"value\":\"2\",\"op\":\"<\",\"unit\":\"PERCENT\"}}");
            ind.setExpr(
                "${PARAM0:02} / ${PARAM4:02}");
            ind.setIndId(IND_KEY + i);
            ind.setIndName("指标" + i);
            ind.setExprGradRedis(ind.getExpr());
            ind.setTimePoint("01");
            ind.setIndParamList();
            ind.setIndType("LIMIT_01");
            ind.setCreateTime(null);
            ind.setUpdateTime(null);
            ind.setCreateUser(null);
            ind.setUpdateUser(null);
            return ind;
        });
    }

    /**
     * redis写入共通
     * 
     * @param cnt 写入数量
     * @param service redis操作服务
     * @param fun 数据处理接口
     */
    private <V extends IBusiness> void writeRedis(int cnt, RedisBaseService<V> service,
                                                  DoData<V> fun) {
        if (fun != null) {
            PipeWriteService<String, String, V> writer = service.getWriter();
            Map<String, Map<String, V>> list = new HashMap<>();
            for (int i = 0; i < cnt; i++ ) {
                V ind = fun.doData(i);
                String key = ind.getKey();
                Map<String, V> map = list.getOrDefault(key, new HashMap<>());
                map.put(ind.getField(), ind);
                list.put(key, map);
            }
            writer.writeList(list);
        }
    }

    /**
     * 写入组合管理指标
     * 
     * @param cnt 组合个数
     * @param p 每个组合挂指标的个数
     * @param bagCnt 组合包个数
     */
    private void writePtlIndicator(int cnt, int p, int bagCnt) {
        PtlIndicatorService service = getPtlIndService();
        writeRedis(cnt, service, (i) -> {
            ImplPtlIndicator ptlInd = new ImplPtlIndicator();
            ptlInd.setPortfolioId(PTL_KEY + i);
            ptlInd.setPortfIdRedis(PTL_KEY + i);

            Map<String, Set<String>> indMap = new HashMap<>();

            // 初始化组合包
            Map<String, Object> bagList = new HashMap<>();
            for (int x = 0; x < bagCnt; x++ ) {
                bagList.put(PTLBAG_KEY + x, "组合包" + x);

                Set<String> indList = new HashSet<>();
                for (int m = 0; m < 2; m++ ) {
                    indList.add(IND_KEY + m);
                }
                indMap.put(PTLBAG_KEY + x, indList);
            }
            ptlInd.setPortfolioBagIdRedis(bagList);

            // 初始化组合指标
            Set<String> indList = new HashSet<>();
            for (int x = 0; x < p; x++ ) {
                indList.add(IND_KEY + x);
            }
            indMap.put(PTL_KEY + i, indList);
            ptlInd.setPtlIndicatorRedis(indMap);
            ptlInd.setCreateTime(null);
            ptlInd.setUpdateTime(null);
            ptlInd.setCreateUser(null);
            ptlInd.setUpdateUser(null);
            return ptlInd;
        });
    }

    protected static final String EVENT_KEY = "EVENT";
    
    /**
     * 写入风险事件
     * 
     * @param cnt 风险事件个数
     * @param m 每个风险事件的特征值个数
     * @param p 每个风险事件影响的参数个数
     */
    private void writeEvent(int cnt, int m, int p) {
        RskEventService service = getRskEventSrv();
        Map<String, Map<String, EventMap>> infoList = new HashMap<>();
        EventMap eMap = new EventMap();
        for (int i = 0; i < cnt; i++ ) {
            ImplEvent event = new ImplEvent();
            event.setEventCode(EVENT_KEY + i);
            // 设置特征值
            if (Math.floorMod(i, m) == 0) {
                event.setImplEventItemList(getMatchEventItem(i));
            }
            else {
                event.setImplEventItemList(getUnMatchEventItem(i));
            }
            // 设置关联参数
            List<ImplEventParaRel> relList = new ArrayList<>();
            for (int x = 0; x < p; x++ ) {
                ImplEventParaRel rel = new ImplEventParaRel();
                rel.setEventCode(EVENT_KEY + i);
                rel.setParaCode(PARAM_KEY + x);
                rel.setParaGranularity("02");
                rel.setRelItem(BussinessDictCodeListRds.风险事件运算关系对象_金额.getValue());
                rel.setRelType("+");
                rel.setCreateTime(null);
                rel.setUpdateTime(null);
                rel.setCreateUser(null);
                rel.setUpdateUser(null);
                relList.add(rel);
            }
            event.setiEventParaRelList(relList);
            event.setCreateTime(null);
            event.setUpdateTime(null);
            event.setCreateUser(null);
            event.setUpdateUser(null);
            eMap.add(event);
        }
        Map<String, EventMap> map = new HashMap<>();
        map.put("ALL", eMap);
        infoList.put(RedisKeyEnum.风险事件配置.key(), map);
        service.getWriter().writeList(infoList);
    }

    interface DoData<V extends IBusiness> {

        V doData(int i);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(String obj, ClientData response) {
        return false;
    }
}
