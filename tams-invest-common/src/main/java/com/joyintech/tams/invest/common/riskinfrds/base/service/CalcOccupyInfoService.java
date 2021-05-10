/*
 * 文件名：EventBaseService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.base.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.redis.common.entity.IValue;
import com.joyintech.redis.common.entity.Value;
import com.joyintech.redis.common.util.JoyinRedisUtil;
import com.joyintech.redis.core.data.IRedisHash;
import com.joyintech.redis.core.data.RedisHash;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.common.base.entity.RskIndicatorParaCalcdetail;
import com.joyintech.tams.common.base.entity.RskIsupTrial;
import com.joyintech.tams.common.base.entity.RskIsupTrialCls;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.calendar.entity.ICalendar;
import com.joyintech.tams.invest.common.riskinfrds.calendar.service.CalendarService;
import com.joyintech.tams.invest.common.riskinfrds.event.entity.IEvent;
import com.joyintech.tams.invest.common.riskinfrds.event.entity.IEventParaRel;
import com.joyintech.tams.invest.common.riskinfrds.event.service.RskEventService;
import com.joyintech.tams.invest.common.riskinfrds.indic.entity.IIndicator;
import com.joyintech.tams.invest.common.riskinfrds.indic.entity.IIndicatorResult;
import com.joyintech.tams.invest.common.riskinfrds.indic.entity.IIndividLimit;
import com.joyintech.tams.invest.common.riskinfrds.indic.service.IndicatorService;
import com.joyintech.tams.invest.common.riskinfrds.info.service.AssetInfoRedisService;
import com.joyintech.tams.invest.common.riskinfrds.occupy.entity.IOccupyRelParam;
import com.joyintech.tams.invest.common.riskinfrds.occupy.entity.IOcpRelParamItem;
import com.joyintech.tams.invest.common.riskinfrds.occupy.entity.OccupyRelParam;
import com.joyintech.tams.invest.common.riskinfrds.occupy.entity.OcpRelParamItem;
import com.joyintech.tams.invest.common.riskinfrds.occupy.service.OccupyRelParamService;
import com.joyintech.tams.invest.common.riskinfrds.param.entity.IParam;
import com.joyintech.tams.invest.common.riskinfrds.param.service.IndicatorParamService;
import com.joyintech.tams.invest.common.riskinfrds.param.service.IndicatorParamValService;
import com.joyintech.tams.invest.common.riskinfrds.prod.entity.IPtlIndicator;
import com.joyintech.tams.invest.common.riskinfrds.prod.service.PtlIndicatorService;
import com.joyintech.tams.invest.common.risktrialrds.util.RiskUtil;

/**
 * 预占信息计算Service
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月29日
 * @see CalcOccupyInfoService
 * @since 1.0
 */
@Service
public class CalcOccupyInfoService {

    /**
     * 控制台日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 服务线程数据
     */
    private ThreadLocal<Map<String, Object>> serviceInfo = ThreadLocal.withInitial(HashMap::new);
 
    /**
     * 指标关联组合信息读写服务
     */
    @Autowired
    private PtlIndicatorService ptlIndService;

    /**
     * 指标信息Redis读写服务
     */
    @Autowired
    private IndicatorService indService;

    /**
     * 风险前置服务
     */
    @Autowired
    private RskEventService rskEventSrv;

    /**
     * 预占/实占参数信息服务
     */
    @Autowired
    private OccupyRelParamService ocpService;

    /**
     * 指标参数信息Redis读写信息服务
     */
    @Autowired
    private IndicatorParamService indParamSrv;

    /**
     * 指标计算服务
     */
    @Autowired
    private IndicatorCalcService calcService;

    /**
     * 指标参数值读写服务
     */
    @Autowired
    private IndicatorParamValService paramValSrv;

    /**
     * 资产基本信息更新Redis服务
     */
    @Autowired
    private AssetInfoRedisService assetInfoSrc;

    /**
     * 日历信息Redis读写服务
     */
    @Autowired
    private CalendarService calendarSrc;

    /**
     * KEY:组合代码
     */
    public static final String PORTFOLIOCODE = "portfolioCode";

    /**
     * KEY:交易对手
     */
    public static final String COUNTERID = "counterid";
    /**
     * KEY:资产代码
     */
    public static final String ASSETCODE = "assetCode";

    /**
     * KEY:交易方向
     */
    public static final String PS = "ps";

    /**
     * KEY:资管委托编号
     */
    public static final String PROD_ID = "prodIdForBk";

    /**
     * KEY:事件代码
     */
    public static final String EVENT_ID = "eventId";

    /**
     * KEY:日历编号
     */
    public static final String CALENDAR_ID = "calendarCode";

    /**
     * KEY:资管委托编号
     */
    public static final String ENTRUST_CODE = "entrustCode";

    /**
     * KEY:事件类型
     */
    public static final String EVENT_TYPE = "eventType";

    /**
     * KEY:监控时点
     */
    public static final String TIME_POINT = "timePoint";

    /**
     * KEY:资产代码
     */
    public static final String ASSET_CODE = "assetCode";

    /**
     * KEY:试算结果
     */
    public static final String RSKSTATUS = "rskStatus";

    /**
     * KEY:指标代码
     */
    public static final String KRICODE = "kriCode";

    /**
     * KEY:指标名称
     */
    public static final String KRINAME = "kriName";

    /**
     * KEY:指标类型
     */
    public static final String KRITYPE = "kriType";

    /**
     * KEY:审批线
     */
    public static final String APPROVELINE = "approveLine";

    /**
     * KEY:预警线
     */
    public static final String WARNINGLINE = "warningLine";

    /**
     * KEY:禁止线
     */
    public static final String FORBIDDENLN = "forbiddenLn";

    /**
     * KEY:联合监控状态
     */
    public static final String UNITESTATUS = "uniteStatus";

    /**
     * KEY:指标计算值
     */
    public static final String KRIVALUE = "kriValue";

    /**
     * KEY:原值
     */
    public static final String OLDVALUE = "oldValue";

    /**
     * KEY:趋势
     */
    public static final String KRITREND = "kriTrend";

    /**
     * KEY:指标状态
     */
    public static final String KRISTATUS = "kriStatus";

    /**
     * KEY:试算结果列表
     */
    public static final String RSK_LIST = "RSK_LIST";

    /**
     * KEY:自动启动工作流系统
     */
    public static final String INF_STAPP_SYS = "infStappSys";

    /**
     * 处理类型：预占
     */
    public static final int PRE_OCCUPY = 1;

    /**
     * 处理类型：计算
     */
    public static final int RSK_CALC = 0;

    /**
     * 处理类型：实占
     */
    public static final int REL_OCCUPY = 2;

    /**
     * 状态_占用
     */
    public static final String STATUS_OCCUPY = "0";

    /**
     * 状态_释放
     */
    public static final String STATUS_RELEASE = "1";

    public static final String LOG_INFO_PASS = "组合关联指标信息不存在，试算通过";

    public static final String LOG_INFO_EVENT = "未触发风控事项，试算通过";
    
    public static final String LOG_NO_IND_VAL = "指标参数值不存在";

    protected static final String MIXAMOUNTKEY = "mixAmount";
   
    protected static final String ASSET_AMT_VAL = "assetAmtVal";
    
    protected static final List<String> indReplaceList = Arrays.asList("CPZZC","CPJZCJZ");
    /**
     * 模糊委托试算
     * 
     * @param mapData 模糊委托信息
     * @param response 计算结果
     */
    public void dimCalc(Map<String, Object> mapData, Map<String, Object> response) {
    	// 指标参数计算明细
    	List<RskIndicatorParaCalcdetail> details = new ArrayList<>();
        // 备份组合代码
        mapData.put(PROD_ID, mapData.get(PORTFOLIOCODE));
        // 资产代码
        String assetCode = (String)mapData.get(ASSETCODE);
        // 取得资产基本信息
        Map<String, Object> assetInfo = getAssetInfo(assetCode);
        // 若资产基本信息不为空
        if (!CollectionUtils.isEmpty(assetInfo)) {
            mapData.putAll(assetInfo);
        }
        else if (StringUtil.isNotEmpty(assetCode)) {
        	logger.error("{}的资产信息不存在", assetCode);
        	return;
        }
        // 取得日历信息
        ICalendar calendar = getCalendar((String)mapData.get(CALENDAR_ID));
        // 匹配风险事件
        List<IEvent> eveList = getEvent(mapData, calendar);
        // 若风险事件不存在，则试算通过
        if (StringUtil.isEmptyList(eveList)) {
            // 试算通过
        	logger.info(LOG_INFO_EVENT);
            return;
        }
        // 从风险事件列表中取出相关的参数列表
        Set<String> paraIdList = getParamIdList(eveList);
        // 取得组合关联指标信息
        IPtlIndicator ptlInd = getPtlIndicator(mapData);
        // 若无 组合关联指标信息，则试算通过
        if (ptlInd == null) {
        	logger.info(LOG_INFO_PASS);
            return;
        }
        
        // 取得所有指标代码
        Set<String> indIdList = getIndIdList(ptlInd);
        // 若组合未关联指标信息，则试算通过
        if (CollectionUtils.isEmpty(indIdList)) {
        	logger.info(LOG_INFO_PASS);
            return;
        }
        // 取得所有的指标信息
        Map<String, IIndicator> indMap = getIndicator(indIdList);
        // 若指标信息不存在，则抛出异常
        if (CollectionUtils.isEmpty(indMap)) {
        	logger.error("指标信息不存在");
        	return;
        }
        
        // 风险事件相关参数与指标相关参数糅合去重(并集)
        Map<String, Set<String>> ptlIndMap = ptlInd.getPtlIndicator();
        unionParamId(indMap, paraIdList, ptlIndMap);
        // 取得所有指标参数信息
        Map<String, IParam> paramMap = getParam(paraIdList);
        // 若指标参数不存在则抛出异常
        if (CollectionUtils.isEmpty(paramMap)) {
        	logger.error("指标参数信息不存在");
        	return;
        }

        // 判断监督时点，若不满足则指标不计算,判断是否剔除资产，若满足则指标不计算
        checkTpAndIndivAd(indMap, mapData, ptlInd);
        // 当指标都被剔除时，直接试算通过
        if (CollectionUtils.isEmpty(ptlInd.getPtlIndicator())) {
        	logger.info("无需要计算的指标，试算通过");
            return;
        }

        // 构建组合与指标参数索引
        Map<String, Map<String, String>> ptlParamMap = getPtlParamMap(ptlInd, indMap);
        
        // 执行事务处理（Redis操作）
        CommonUtil.handleByLock(() -> {
            // 取得所有指标参数的值
            Map<String, Map<String, IValue<Object>>> paraValMap = getParamValSrv().getParamVal(
                createCondition(paramMap, mapData, ptlParamMap));
            // 若指标参数值未初始化到Redis则抛出异常
            if (CollectionUtils.isEmpty(paraValMap)) {
            	logger.error(LOG_NO_IND_VAL);
            	return -1;
            }
            // 计算参数值
            Map<String, Map<String, IValue<Object>>> newValMap = calcParamVal(eveList,
                paraValMap, null, details);
            // 固定值参数
            Map<String, Object> map = getFixedParaVal(paramMap,mapData);
            // 指标参数原值
            Map<String, Map<String, Object>> pValMap = parseParaVal(ptlParamMap, paraValMap);
            // 加入固定值
            unionParaVal(pValMap, map);
            // 指标计算原值
            calcIndicator(ptlInd, indMap, mapData, pValMap, true);
            // 指标参数新值
            pValMap = parseParaVal(ptlParamMap, calcValWithoutEvt(getParamIdList(eveList), eveList, newValMap));
            // 加入固定值
            unionParaVal(pValMap, map);
            // 指标计算新值
            calcIndicator(ptlInd, indMap, mapData, pValMap, false);
            return null;
        });
        indMap.entrySet().forEach(en -> checkIndTrend(en.getValue()));
        // 处理返回结果
        writeResponse(response, indMap);
        if (StringUtil.isNotEmpty(mapData.get(EVENT_ID))) {
        	RskIsupTrialCls isupTrialCls = new RskIsupTrialCls();
            // 业务类型
            isupTrialCls.setTradeType("3");
            // 业务编号
            isupTrialCls.setTradeId((String) mapData.get(EVENT_ID));
            // 试算结论
            isupTrialCls.setConclusion((String) response.get(RSKSTATUS));
            // 设置流水号
            isupTrialCls.setSeqNo((String) mapData.get("uuid"));
            // 保存风险计算结论
            setUserDt(isupTrialCls);
            // 保存监督指标计算结果
            saveRskIsupTrial(response, mapData, (String) mapData.get(EVENT_ID), response);
        }
    }

    public void calc(Map<String, Object> mapData, Map<String, Object> response) {
        try {
            // 备份组合代码
            mapData.put(PORTFOLIOCODE, mapData.get("prodId"));
            // 资产代码
            String assetCode = (String)mapData.get(ASSETCODE);
            // 取得资产基本信息
            Map<String, Object> assetInfo = getAssetInfo(assetCode);
            // 若资产基本信息不为空
            if (!CollectionUtils.isEmpty(assetInfo)) {
                mapData.putAll(assetInfo);
            }
            /************************************/
            /** 风险计算 ***************************/
            /************************************/
            // 取得日历信息
            ICalendar calendar = getCalendar((String)mapData.get(CALENDAR_ID));
            // 匹配风险事件
            List<IEvent> eveList = getEvent(mapData, calendar);
            // 若风险事件不存在，则预占失败
            if (StringUtil.isEmptyList(eveList)) {
                logger.error("风险事件不存在，则预占失败");
                return;
            }
            // 取得组合关联指标信息
            IPtlIndicator ptlInd = getPtlIndicator(mapData);
            // 若无 组合关联指标信息，则试算通过，直接进行预占
            if (ptlInd == null) {
                logger.info(LOG_INFO_PASS);
                occupyWithoutCalc(eveList, mapData, response, null);
                return;
            }
            occupyWithCalc(ptlInd, eveList, mapData, response);
        }
        catch (Exception e) {
            logger.error("处理异常", e);
        }
    }

    /**
     * 风险计算后未达到违规线时进行预占
     * 
     * @param ptlInd 组合关联指标
     * @param eveList 风险事件
     * @param mapData 接口输入
     * @see
     * @since 1.0
     */
    private void occupyWithCalc(IPtlIndicator ptlInd, List<IEvent> eveList,
                                Map<String, Object> mapData, Map<String, Object> resMap) {
    	// 指标参数计算明细
    	List<RskIndicatorParaCalcdetail> details = new ArrayList<>();
        String occupyCode = (String)mapData.get(EVENT_ID);
        // 取得所有指标代码
        Set<String> indIdList = getIndIdList(ptlInd);
        // 若组合未关联指标信息，则试算通过，直接进行预占
        if (CollectionUtils.isEmpty(indIdList)) {
            logger.info(LOG_INFO_PASS);
            occupyWithoutCalc(eveList, mapData, resMap, ptlInd);
            return;
        }
        // 取得所有的指标信息
        Map<String, IIndicator> indMap = getIndicator(indIdList);
        // 若指标信息不存在，则抛出异常，不进行预占处理
        if (CollectionUtils.isEmpty(indMap)) {
            logger.info("指标信息不存在，试算通过");
            occupyWithoutCalc(eveList, mapData, resMap, ptlInd);
            return;
        }
        // 判断监督时点，若不满足则指标不计算,判断是否剔除资产，若满足则指标不计算
        checkTpAndIndivAd(indMap, mapData, ptlInd);
        // 当指标都被剔除时，试算通过，直接进行预占
        Map<String, Set<String>> ptlIndMap = ptlInd.getPtlIndicator();
        if (CollectionUtils.isEmpty(ptlIndMap) || CollectionUtils.isEmpty(ptlIndMap.values())) {
            logger.info("无指标需要计算，试算通过");
            occupyWithoutCalc(eveList, mapData, resMap, ptlInd);
            return;
        }
        // 从风险事件列表中取出相关的参数列表
        Set<String> paraIdList = getParamIdList(eveList);
        // 风险事件相关参数与指标相关参数糅合去重(并集)
        unionParamId(indMap, paraIdList, ptlInd.getPtlIndicator());
        // 一条指标都没有的时候
        if (CollectionUtils.isEmpty(indMap) || CollectionUtils.isEmpty(indMap.values())) {
            occupyWithoutCalc(eveList, mapData, resMap, ptlInd);
            return;
        }
        // 取得所有指标参数信息
        Map<String, IParam> paramMap = getParam(paraIdList);
        // 若指标参数不存在则抛出异常
        if (CollectionUtils.isEmpty(paramMap)) {
            logger.error("指标参数信息不存在");
            return;
        }
        // 构建组合与指标参数索引
        Map<String, Map<String, String>> ptlParamMap = getPtlParamMap(ptlInd, indMap);

        RskIsupTrialCls isupTrialCls = new RskIsupTrialCls();
        // 业务类型
        isupTrialCls.setTradeType((String)mapData.get(EVENT_TYPE));
        // 业务编号
        isupTrialCls.setTradeId(occupyCode);
        Map<String, Object> response = new HashMap<>();
        // 执行事务处理（Redis操作）
        CommonUtil.handleByLock(() -> {
            String uuid =  String.valueOf(System.currentTimeMillis());
            mapData.put("seqNo", uuid);
            // 取得所有指标参数的值
            Map<String, Map<String, IValue<Object>>> paraValMap = getParamValSrv().getParamVal(
                createCondition(paramMap, mapData, ptlParamMap));
            // 若指标参数值未初始化到Redis则抛出异常
            if (CollectionUtils.isEmpty(paraValMap)) {
                logger.error(LOG_NO_IND_VAL);
                return -1;
            }
            // 添加预占参数信息
            OccupyRelParam newOcpRelParam = new OccupyRelParam();
            Map<String, IOcpRelParamItem> ocpPmItem = new HashMap<>();
            newOcpRelParam.setItems(ocpPmItem);
            // 计算参数值
            Map<String, Map<String, IValue<Object>>> newValMap = calcParamVal(eveList,
                paraValMap, newOcpRelParam, details);
            if (!CollectionUtils.isEmpty(indMap) && !CollectionUtils.isEmpty(indMap.values())) {
                // 固定值参数
                Map<String, Object> map = getFixedParaVal(paramMap,mapData);
                // 指标参数原值
                Map<String, Map<String, Object>> pValMap = parseParaVal(ptlParamMap, paraValMap);
                // 加入固定值
                unionParaVal(pValMap, map);
                // 指标计算原值
                calcIndicator(ptlInd, indMap, mapData, pValMap, true);
                // 指标参数新值
                pValMap = parseParaVal(ptlParamMap, calcValWithoutEvt(getParamIdList(eveList), eveList, newValMap));
                // 加入固定值
                unionParaVal(pValMap, map);
                // 指标计算新值
                calcIndicator(ptlInd, indMap, mapData, pValMap, false);
                indMap.entrySet().forEach(en -> checkIndTrend(en.getValue()));
                // 处理返回结果
                writeResponse(response, indMap);
            }
            // 更新Redis
            getParamValSrv().setParamVal(newValMap);
            newOcpRelParam.setOccupyCode(occupyCode);
            // 类型
            String eventType = (String)mapData.get(EVENT_TYPE);
            // 预占/实占类型
            newOcpRelParam.setOccupyType(StringUtil.equals(eventType, "0") ? PRE_OCCUPY : REL_OCCUPY);
            // 预占/实占参数信息列表
            newOcpRelParam.setItems(ocpPmItem);
            // 更新预占/实占信息
            setOccupyRelParam(newOcpRelParam);
            return 0;
        });
        Object wflStatus = mapData.get("wflStatus");
        Object eventDate = mapData.get("eventDate");
        if (StringUtil.equals(wflStatus, BussinessDictCodeListRds.有效状态_待处理.getValue())
        		&& !StringUtil.equals(eventDate, DateUtil.getSysDate())) {
        	String rskStatus = (String)response.get(RSKSTATUS);
            // 试算结论
            isupTrialCls.setConclusion(rskStatus);
            // 流水号
            isupTrialCls.setSeqNo((String) mapData.get("uuid"));
            // 保存风险计算结论
            setUserDt(isupTrialCls);
            resMap.put("ISUP_TRIAL_CLS", isupTrialCls);
            // 保存监督指标计算结果
            saveRskIsupTrial(response, mapData, occupyCode, resMap);
        }
        // 参数计算明细
        freshDetail(details, paramMap);
        saveDetails(details, mapData, occupyCode);
        resMap.put("PARA_VAL_DETAILS", details);
    }

    public void calcIndParam(Map<String, Object> mapData, Map<String, Object> response) {
        try {
            // 备份组合代码
            mapData.put(PORTFOLIOCODE, mapData.get("prodId"));
            // 资产代码
            String assetCode = (String)mapData.get(ASSETCODE);
            // 取得资产基本信息
            Map<String, Object> assetInfo = getAssetInfo(assetCode);
            // 若资产基本信息不为空
            if (!CollectionUtils.isEmpty(assetInfo)) {
                mapData.putAll(assetInfo);
            }
            /************************************/
            /** 风险计算 ***************************/
            /************************************/
            // 取得日历信息
            ICalendar calendar = getCalendar((String)mapData.get(CALENDAR_ID));
            // 匹配风险事件
            List<IEvent> eveList = getEvent(mapData, calendar);
            // 若风险事件不存在，则预占失败
            if (StringUtil.isEmptyList(eveList)) {
                logger.error("风险事件不存在，则预占失败");
                return;
            }
            IPtlIndicator ptlInd = getPtlIndicator(mapData);
            // 若无 组合关联指标信息，则试算通过，直接进行预占 
            occupyWithoutCalc(eveList, mapData, response, ptlInd); 
//            occupyWithoutCalc(eveList, mapData, response, null);
        }
        catch (Exception e) {
            logger.error("处理异常", e);
        }
    }

    /**
     * 保存 指标参数计算明细
     * 
     * @param details 参数计算明细
     * @param mapData 报文
     * @param occupyCode 预占实占编号
     */
    protected void saveDetails(List<RskIndicatorParaCalcdetail> details, Map<String, Object> mapData, String occupyCode){
        if (!StringUtil.isEmptyList(details)) {
        	details.forEach(detail->{
        		// 业务编号
        		detail.setTradeId(occupyCode);
        		// 业务类型
        		detail.setTradeType((String) mapData.get(EVENT_TYPE));
        		// 设置监督日期
        		detail.setSupDate(DateUtil.parse((String)mapData.get("sysDate")));
        		// 设置时间戳
        		Object obj = mapData.get("seqNo");
        		detail.setDotimestamp(DateUtil.format(new Date(new Long(obj.toString())),DateUtil.DEFAULT_DATETIME_FORMAT_SSS));
        		// 设置UUID
        		detail.setUuid(IDGenerator.generate());
        		String user = "batch";
        		// 创建人
        		detail.setCreateUser(user);
        		// 更新人
        		detail.setUpdateUser(user);
        	});
        }
    }

    /**
     * 固定值参数加入指标计算的参数列表
     * 
     * @param paraVal 指标计算的参数列表
     * @param map 固定值参数
     * @see
     * @since 1.0
     */
    protected void unionParaVal(Map<String, Map<String, Object>> paraVal,
                                Map<String, Object> map) {
        if (!CollectionUtils.isEmpty(map)) {
            paraVal.entrySet().forEach(en -> {
                Map<String, Object> value = en.getValue();
                value.putAll(map);
            });
        }
    }

    /**
     * 固定值参数值
     * 
     * @param paramMap 指标参数
     * @return 固定值参数值
     * @see
     * @since 1.0
     */
    protected Map<String, Object> getFixedParaVal(Map<String, IParam> paramMap,Map<String, Object> mapData) {
        Map<String, Object> map = new HashMap<>(); 
        paramMap.entrySet().forEach(en -> {
            String key = en.getKey();
            IParam para = en.getValue();
            if (para != null && CommonUtil.isType(para.getParaDisplayType(),
                BussinessDictCodeListRds.指标参数实现类型_固定值)) {
                String content = para.getContent();
                BigDecimal value = new BigDecimal(content);
                map.put(key, value);
            }
        });
        if (MultilevelDataAuthorityUtil.isEnabled()) { 
            paramMap.entrySet().forEach(en -> {
                String key = en.getKey();
                IParam para = en.getValue(); 
                String instCode = mapData.get("instCode").toString();
                if (!StringUtil.isEmpty(instCode)) {
                    if (para != null && CommonUtil.isType(para.getParaDisplayType(),
                        BussinessDictCodeListRds.指标参数实现类型_固定值) &&
                        instCode.toString().equals(para.getInstitutionCode())) {
                        String content = para.getContent();
                        BigDecimal value = new BigDecimal(content); 
                        map.put(key.replace(instCode,BussinessDictCodeListRds.指标参数实现类型_固定值.getValue()), value);
                    }
                }
            });
        }
        return map;
    }
    /**
     * 无指标计算，直接进行预占
     * 
     * @param eveList 风险事件
     * @param mapData 接口输入
     * @param ptlInd 组合关联指标信息
     * @see
     * @since 1.0
     */
    private void occupyWithoutCalc(List<IEvent> eveList, Map<String, Object> mapData,
                                   Map<String, Object> resMap,
                                   IPtlIndicator ptlInd) {
    	// 指标参数计算明细
    	List<RskIndicatorParaCalcdetail> details = new ArrayList<>();
        String occupyCode = (String)mapData.get(EVENT_ID);
        // 从风险事件列表中取出相关的参数列表
        Set<String> paraIdList = getParamIdList(eveList);
        // 取得所有指标参数信息
        Map<String, IParam> paramMap = getParam(paraIdList);
        // 若指标参数不存在则抛出异常
        if (CollectionUtils.isEmpty(paramMap)) {
            logger.error("指标参数信息不存在，预占失败");
            return;
        }
        // 取得组合关联所有指标代码
        Set<String> indIdList = getIndIdList(ptlInd);
        // 指标参数查询条件
        Map<String, Map<String, IValue<Object>>> paraCond = null;
        // 若无 组合关联指标信息，则只需组合代码参与指标参数计算
        if (CollectionUtils.isEmpty(indIdList)) {
            // 根据参数信息构建参数值查询条件
            paraCond = createParaCond(paramMap, mapData);
        }
        else {
            // 取得所有的指标信息
            Map<String, IIndicator> indMap = getIndicator(indIdList);
            // 若指标信息不存在，则只需组合代码参与指标参数计算
            if (CollectionUtils.isEmpty(indMap) || CollectionUtils.isEmpty(indMap.values())) {
                paraCond = createParaCond(paramMap, mapData);
            }
            else {
                // 构建组合与指标参数索引
                Map<String, Map<String, String>> ptlParamMap = getPtlParamMap(ptlInd, indMap);
                paraCond = createCondition(paramMap, mapData, ptlParamMap);
            }
        }
        final Map<String, Map<String, IValue<Object>>> paraList = paraCond;
        int type = CommonUtil.handleByLock(() -> {
            String uuid =  String.valueOf(System.currentTimeMillis());
            mapData.put("seqNo", uuid);
            IndicatorParamValService service = getParamValSrv();
            // 取得所有指标参数的值
            Map<String, Map<String, IValue<Object>>> paraValMap = service.getParamVal(
                paraList);
            // 若指标参数值未初始化到Redis则抛出异常
            if (CollectionUtils.isEmpty(paraValMap)) {
                logger.error(LOG_NO_IND_VAL);
                return -1;
            }
            // 添加预占参数信息
            OccupyRelParam newOcpRelParam = new OccupyRelParam();
            Map<String, IOcpRelParamItem> ocpPmItem = new HashMap<>();
            
            newOcpRelParam.setItems(ocpPmItem);
            // 计算参数值
            Map<String, Map<String, IValue<Object>>> newValMap = calcParamVal(eveList,
                paraValMap, newOcpRelParam, details, mapData);
            // 更新Redis
            service.setParamVal(newValMap);
            newOcpRelParam.setOccupyCode((String)mapData.get(EVENT_ID));
            // 类型
            String eventType = (String)mapData.get(EVENT_TYPE);
            // 预占/实占类型
            newOcpRelParam.setOccupyType(StringUtil.equals(eventType, "0") ? PRE_OCCUPY : REL_OCCUPY);
            // 预占/实占参数信息列表
            newOcpRelParam.setItems(ocpPmItem);
            // 更新预占/实占信息
            setOccupyRelParam(newOcpRelParam);
            return 0;
        });
        // 参数计算明细
        if (type == 0) {
            freshDetail(details, paramMap);
            saveDetails(details, mapData, occupyCode);
            resMap.put("PARA_VAL_DETAILS", details);
        }
    }

    /**
     * 计算 需要参与计算却没有受到风险事件影响的参数
     * @param paraIdList 被影响的参数
     * @param eveList 风险事件
     * @param paraValMap 参数值列表
     */
    protected Map<String, Map<String, IValue<Object>>> calcValWithoutEvt(Set<String> paraIdList, List<IEvent> eveList, Map<String, Map<String, IValue<Object>>> paraValMap){
    	Map<String, Map<String, IValue<Object>>> tempMap = new HashMap<>();
    	paraValMap.entrySet().forEach(en->{
    		// 参数代码
            String paraCode = en.getKey();
            // 参数该粒度没有被影响，但需要参与计算且其它粒度被影响时
            String pCd = StringUtil.substring(paraCode, 0, StringUtil.indexOf(paraCode, GlobalConstant.COLON));
            if (!paraIdList.contains(paraCode) && (
                   paraIdList.contains(pCd + GlobalConstant.COLON + BussinessDictCodeListRds.参数粒度_买入.getValue())
                || paraIdList.contains(pCd + GlobalConstant.COLON + BussinessDictCodeListRds.参数粒度_卖出.getValue())
                || paraIdList.contains(pCd + GlobalConstant.COLON + BussinessDictCodeListRds.参数粒度_实时.getValue()))) {
            	tempMap.put(paraCode,en.getValue());
            }
    	});
        Map<String, Map<String, IValue<Object>>> newValMap = new HashMap<>();
        newValMap.putAll(paraValMap);
    	if (CollectionUtils.isEmpty(tempMap)) {
            return newValMap ;
    	}
    	forEachEvent(eveList, epr -> {
            // 参数代码
            String paraCode = epr.getParaCode();
            // 关系类型
            String relType = epr.getRelType();
            // 关系预算的对象值
            BigDecimal val = epr.getRelValue();
            String pCd = StringUtil.substring(paraCode, 0, StringUtil.indexOf(paraCode, GlobalConstant.COLON));
            tempMap.entrySet().forEach(en->{
                String tempCode = en.getKey();
                String pCd2 = StringUtil.substring(tempCode, 0, StringUtil.indexOf(tempCode, GlobalConstant.COLON));
                if (!StringUtil.equals(pCd2, pCd)) {
                	return;
                }
                // Map<指标参数值Redis识别域, 指标参数值>
                Map<String, IValue<Object>> valMap = en.getValue();
                if (CollectionUtils.isEmpty(valMap)) {
                    return;
                }
                // Map<指标参数值Redis识别域, 指标参数值>
                Map<String, IValue<Object>> temp = new HashMap<>();
                valMap.entrySet().forEach(item -> {
                    String key = item.getKey();
                    // 参数值
                    IValue<Object> paraVal = item.getValue();
                    //----zxjjpei 更改类型------------------
                    BigDecimal par = paraVal == null ? BigDecimal.ZERO : (BigDecimal) paraVal.value();
                    IValue<Object> newVal = new Value<>(
                        str -> StringUtil.isEmpty(str) ? BigDecimal.ZERO : new BigDecimal(str));
                    // 指标参数值计算
                    newVal.set(CommonUtil.math(par, val, relType));
                    temp.put(key, newVal);
                });
                newValMap.put(tempCode, temp);
            });
        });
    	return newValMap;
    }

    /**
     * 根据参数信息构建参数值查询条件
     * 
     * @param paramMap 参数信息
     * @param mapData 接口输入
     * @return 参数值查询条件
     * @see
     * @since 1.0
     */
    private Map<String, Map<String, IValue<Object>>> createParaCond(Map<String, IParam> paramMap,
                                                                        Map<String, Object> mapData) {
        Map<String, Map<String, IValue<Object>>> resList = new HashMap<>();
        paramMap.entrySet().forEach(en -> {
            // 指标参数代码
            String paraCode = en.getKey();
            // 指标参数信息
            IParam param = en.getValue();
            if (param != null
                && !CommonUtil.isType(param.getParaDisplayType(),
                    BussinessDictCodeListRds.指标参数实现类型_类名)
                && !CommonUtil.isType(param.getParaDisplayType(),
                    BussinessDictCodeListRds.指标参数实现类型_固定值)) {
                // Redis存储识别域
                String[] rdsFields = param.getRedisFieldArr();
                // 指标参数值Redis识别域
                String field = getFiled(rdsFields, mapData);
                Map<String, IValue<Object>> map = new HashMap<>();
                map.put(field, null);
                resList.put(paraCode, map);
            }
        });
        return resList;
    }

    /**
     * 取得 事件的时点
     * 
     * @param mapData 输入报文体
     * @return 事件的时点
     * @see
     * @since 1.0
     */
    public String getTimePoint(Map<String, Object> mapData) {
        String eventType = (String)mapData.get(EVENT_TYPE);
        String res = getTimePoint(eventType);
        mapData.putIfAbsent(TIME_POINT, res);
        return res;
    }

    /**
     * 根据事件类型判断监控时点
     * 
     * @param eventType 事件类型
     * @return 监控时点
     * @see
     * @since 1.0
     */
    public String getTimePoint(String eventType) {
        if (StringUtil.equals(eventType, "0")) {
            return BussinessDictCodeListRds.监督时分_委托监控.getValue();
        }
        else if (StringUtil.equals(eventType, "1")) {
            return BussinessDictCodeListRds.监督时分_交易监控.getValue();
        }
        else if (StringUtil.equals(eventType, "2")) {
            return BussinessDictCodeListRds.监督时分_交易监控.getValue();
        }
        return eventType;
    }

    /**
     * 判断监督时点，若不满足则指标不计算,判断是否剔除资产，若满足则指标不计算
     * 
     * @param indMap 指标列表
     * @param mapData 接口输入报文
     * @see
     * @since 1.0
     */
    public void checkTpAndIndivAd(Map<String, IIndicator> indMap, Map<String, Object> mapData,
                                  IPtlIndicator ptlInd) {
        // 事件时点
        String timePoint = getTimePoint(mapData);
        // 资产代码
        String assetCode = (String)mapData.get(ASSET_CODE);
        // 组合指标关联列表
        Map<String, Set<String>> ptlIndMap = ptlInd.getPtlIndicator();
        // 组合代码
        String ptlCode = ptlInd.getPtlCode();
        // 组合列表
        Set<String> indList = ptlIndMap.get(ptlCode);
        indMap.entrySet().forEach(en -> {
            // 指标代码
            String key = en.getKey();
            // 指标信息
            IIndicator ind = en.getValue();
            // 当指标监督时点不符合事件时点时，该指标不参与计算
            checkTimePoint(key, ind, indList, timePoint);
            // 判断是否剔除组合资产，若满足则指标不计算
            checkIndividAstDel(ind, indList, ptlCode, assetCode);
            Map<String, Object> ptlBagMap = ptlInd.getPtlBagIdList();
            Set<String> bagList = ptlBagMap == null ? new HashSet<>() : ptlBagMap.keySet();
            if (!CollectionUtils.isEmpty(bagList)) {
                bagList.forEach(str -> {
                    Set<String> tempList = ptlIndMap.get(str);
                    // 当指标监督时点不符合事件时点时，该指标不参与计算
                    checkTimePoint(key, ind, tempList, timePoint);
                    // 判断是否剔除组合包资产，若满足则指标不计算
                    checkIndividAstDel(ind, tempList, str, assetCode);
                });
            }
        });
    }

    public void checkTimePoint(String key, IIndicator ind, Set<String> indList, String timePoint) {
        // 指标监督时点
        String tp = ind.getTimePoint();
        // 当指标监督时点不符合事件时点时，该指标不参与计算
        if (!StringUtil.contains(tp, timePoint)) {
            indList.remove(key);
        }
    }

    /**
     * 剔除资产判断
     * 
     * @param ind 指标信息
     * @param indList 指标列表
     * @param ptlCode 组合(包)代码
     * @param assetCode 剔除的资产代码
     * @see
     * @since 1.0
     */
    public void checkIndividAstDel(IIndicator ind, Set<String> indList, String ptlCode,
                                   String assetCode) {
        // 取得剔除资产代码信息
        Set<String> tpList = ind.getPtlIndividAstDel(ptlCode);
        // 若目标资产在剔除的列表中，则删除该组合与指标的关系
        if (tpList.contains(assetCode)) {
            indList.remove(ind.getIndId());
        }
    }

    /**
     * 取得预占/实占信息
     * 
     * @param ocpCode 接口输入报文体
     * @param type 预占/实占类型
     * @return 预占/实占信息
     * @see
     * @since 1.0
     */
    public IOccupyRelParam getOccupy(String ocpCode, int type) {
        // 预占实占参数信息
        IOccupyRelParam ocpRelParam = null;
        if (!StringUtil.isEmpty(ocpCode)) {
            // 取得预占/实占信息
            ocpRelParam = getOccupyRelParam(ocpCode, type);
        }
        return ocpRelParam;
    }

    public List<IRedisHash<String, String, IValue<Object>>> createHashList(Set<String> paraIdList,
                                                                               IPtlIndicator ptlInd,
                                                                               Map<String, IParam> paramMap,
                                                                               Map<String, Object> mapData) {
        List<IRedisHash<String, String, IValue<Object>>> tList = new ArrayList<>();
        String ptlCode = ptlInd.getPtlCode();
        paraIdList.forEach(str -> {
            // 指标参数信息
            IParam para = paramMap.get(str);
            if (para != null && !CommonUtil.isType(para.getParaDisplayType(),
                BussinessDictCodeListRds.指标参数实现类型_类名)) {
                mapData.put(PORTFOLIOCODE, ptlCode);
                tList.add(createHash(str, para, mapData));
                ptlInd.getPtlBagIdList().keySet().forEach(ptlBag -> {
                    mapData.put(PORTFOLIOCODE, ptlBag);
                    tList.add(createHash(str, para, mapData));
                });
            }
        });
        return tList;
    }

    private IRedisHash<String, String, IValue<Object>> createHash(String paramId, IParam para,
                                                                      Map<String, Object> mapData) {
        // 指标参数值Redis识别域
        String field = getFiled(para.getRedisFieldArr(), mapData);
        RedisHash<String, String, IValue<Object>> hash = new RedisHash<>();
        hash.setKey(paramId);
        hash.setField(field);
        return hash;
    }

    /**
     * 判断指标趋势
     * 
     * @param ind 指标列表
     * @see
     * @since 1.0
     */
    public void checkIndTrend(IIndicator ind) {
        String cond = ind.getLimitCond();
        Map<String, IIndicatorResult> resMap = ind.getIndicatorResult();
        if (CollectionUtils.isEmpty(resMap)) {
            return;
        }
        for (Entry<String, IIndicatorResult> resEn : resMap.entrySet()) {
            IIndicatorResult res = resEn.getValue();
            IIndividLimit limit = res.getIndividLimit();
            if (limit != null) {
                cond = limit.getLimitCond();
            }
            Object indValNew = res.getIndicatorVal();
            BigDecimal newVal = indValNew == null ? BigDecimal.ZERO : new BigDecimal(
                indValNew.toString());
            Object indValOld = res.getIndicatorOldVal();
            BigDecimal oldVal = indValOld == null ? BigDecimal.ZERO : new BigDecimal(
                indValOld.toString());
            int resCp = newVal.compareTo(oldVal);
            res.setIndicatorTrend(StringUtil.contains(cond, ">") ? -resCp : resCp);
        }
    }

    /**
     * 构建组合与相关的指标参数索引</br>
     * <b>Map<组合代码,Map<指标参数代码,指标参数值Redis识别域>></b>
     * 
     * @param ptlInd 组合关联指标信息
     * @param indMap 指标信息Map <b>Map<指标代码,指标信息></b>
     * @return 组合与相关的指标参数索引
     * @see
     * @since 1.0
     */
    public Map<String, Map<String, String>> getPtlParamMap(IPtlIndicator ptlInd,
                                                           Map<String, IIndicator> indMap) {
        // 返回值
        Map<String, Map<String, String>> ptlParamMap = new HashMap<>();
        // 组合关联指标列表Map<组合代码,List<指标代码>>
        Map<String, Set<String>> ptlIndMap = ptlInd.getPtlIndicator();
        // 遍历组合关联指标列表
        ptlIndMap.entrySet().forEach(en -> {
            // 组合代码
            String key = en.getKey();
            // List<指标代码>
            Set<String> val = en.getValue();
            // Map<指标参数代码,指标参数值Redis识别域>
            Map<String, String> pam = new HashMap<>();
            // 遍历指标代码列表
            val.forEach(str -> {
                // 指标信息
                IIndicator ind = indMap.get(str);
                if (ind != null) {
                    // List<指标参数代码>
                    List<String> pList = ind.getIndParamList();
                    // 遍历指标参数代码列表
                    pList.forEach(p -> pam.put(p, StringUtil.EMPTY));
                }
            });
            ptlParamMap.put(key, pam);
        });
        return ptlParamMap;
    }

    /**
     * 指标计算,计算结果设置到 <b>指标信息{@link IIndicator}</b> 中
     * 
     * @param ptlInd 组合关联指标信息 {@link IPtlIndicator}
     * @param indMap 指标信息 <b>Map<指标代码, 指标信息></b>
     * @param pValMap 指标参数值 <b>Map<指标参数代码, Map<指标参数值Redis识别域, 指标参数值>></b>
     * @param isOld 是否原指标值
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public void calcIndicator(IPtlIndicator ptlInd, Map<String, IIndicator> indMap,
                              Map<String, Object> mapData,
                              Map<String, Map<String, Object>> pValMap, boolean isOld) {
        calcService.calcIndicator(ptlInd, indMap, mapData, pValMap, isOld);
    }

    public Map<String, Map<String, Object>> parseParaVal(Map<String, Map<String, String>> ptlParamMap,
                                                         Map<String, Map<String, IValue<Object>>> valMap) {
        // 返回值 Map<组合代码, Map<指标参数, 指标参数值>>
        Map<String, Map<String, Object>> pValMap = new HashMap<>();
        // 遍历组合与相关指标参数索引
        ptlParamMap.entrySet().forEach(en -> {
            // 组合代码
            String ptlCode = en.getKey();
            // Map<指标参数代码, 指标参数值Redis识别域>
            Map<String, String> temp = en.getValue();
            // Map<指标参数代码, 指标参数值>
            Map<String, Object> pMap = new HashMap<>();
            temp.entrySet().forEach(item -> {
                // 指标参数代码
                String key = item.getKey();
                // 指标参数值Redis识别域
                String val = item.getValue();
                // 指标参数值
                IValue<Object> bVal = JoyinRedisUtil.getValue(valMap, key, val);
                pMap.put(key, bVal == null ? BigDecimal.ZERO : bVal.value());
            });
            pValMap.put(ptlCode, pMap);
        });
        return pValMap;
    }

    /**
     * 取得 风险事件
     * 
     * @param mapData 委托/交易信息
     * @param calendar 日历信息
     * @return 风险事件列表
     * @see
     * @since 1.0
     */
    public List<IEvent> getEvent(Map<String, Object> mapData, ICalendar calendar) {
        return rskEventSrv.matchEvent(mapData, calendar);
    }

    /**
     * 取得 组合关联指标信息
     * 
     * @param mapData 委托/交易信息
     * @return 组合关联指标信息
     * @see
     * @since 1.0
     */
    public IPtlIndicator getPtlIndicator(Map<String, Object> mapData) {
//        String ptlCode = (String)mapData.get(PORTFOLIOCODE);
//        return ptlIndService.getPtlIndicator(ptlCode);
        List<String> fieldList = new ArrayList<>();
        Map<String,Object> ptlMap = new HashMap<>();
        String ptlCode = (String)mapData.get(PORTFOLIOCODE);
        fieldList.add(ptlCode);
        // 交易对手对应的组合关联指标情况
        String issuer = (String)mapData.get(COUNTERID);
        if (!StringUtil.isEmpty(issuer)) {
            fieldList.add(issuer);
        }
        Map<String, IPtlIndicator> resultMap = ptlIndService.getMapFromRedis(
            RedisKeyEnum.组合关联指标配置.key(), fieldList);
        IPtlIndicator result = resultMap.get(ptlCode);
        IPtlIndicator issPtl = resultMap.get(issuer);
        // 有相同组合包则该组合包指标不进行试算，删除组合包
        if (issPtl != null && result != null && issPtl.getPtlBagIdList() != null
            && result.getPtlBagIdList() != null && result.getPtlIndicator() != null) {
            Map<String, Object> resMap = result.getPtlBagIdList();
            issPtl.getPtlBagIdList().entrySet().forEach(en -> {
                String key = en.getKey();
                resMap.remove(key);
                result.getPtlIndicator().remove(key);
            });
        }
        if (result != null) {
            ptlMap.put(result.getPtlCode(), result.getPtlName());
            if (result.getPtlBagIdList() != null) {
                ptlMap.putAll(result.getPtlBagIdList());
            }
        }
        setServiceInfo("PTL_NAME", ptlMap);
        return result;
    }

    /**
     * 取得 组合关联的所有指标代码
     * 
     * @param ptlIndicator 组合关联指标信息
     * @return 组合关联的所有指标代码
     * @see
     * @since 1.0
     */
    public Set<String> getIndIdList(IPtlIndicator ptlIndicator) {
        if (ptlIndicator == null) {
            return new HashSet<>();
        }
        Set<String> indIdList = new HashSet<>();
        Map<String, Set<String>> ptlIndMap = ptlIndicator.getPtlIndicator();
        if (CollectionUtils.isEmpty(ptlIndMap)) {
            return indIdList;
        }
        ptlIndMap.values().forEach(item -> {
            if (!CollectionUtils.isEmpty(item)) {
                indIdList.addAll(item);
            }
        });
        return indIdList;
    }

    /**
     * 取得 指标信息
     * 
     * @param indIdList 指标代码列表
     * @return 指标信息
     * @see
     * @since 1.0
     */
    public Map<String, IIndicator> getIndicator(Set<String> indIdList) {
        return indService.getIndicatorMap(indIdList);
    }

    /**
     * 从风险事件列表中取出相关的参数列表
     * 
     * @param eveList 风险事件列表
     * @return 参数列表
     * @see
     * @since 1.0
     */
    public Set<String> getParamIdList(List<IEvent> eveList) {
        // 指标中的所有参数代码
        Set<String> paraIdList = new HashSet<>();
        // 风险事件关联的所有指标参数代码
        forEachEvent(eveList, epr -> paraIdList.add(epr.getParaCode()));
        return paraIdList;
    }

    /**
     * 风险事件相关参数与指标相关参数糅合去重
     * 
     * @param indMap 指标列表
     * @param paraIdList 风险事件相关参数列表
     * @see
     * @since 1.0
     */
    public void unionParamId(Map<String, IIndicator> indMap, Set<String> paraIdList,
                             Map<String, Set<String>> ptlIndMap) {
        Set<String> indCodeSet = new HashSet<>();
        // 参数代码列表长度
        int size = paraIdList.size();
        for (IIndicator ind : indMap.values()) {
            // 指标参数列表
            List<String> indPList = ind.getIndParamList();
            // 指标参数列表长度
            int lSize = indPList.size();
            paraIdList.addAll(indPList);
            // 添加后参数代码列表长度
            int pSize = paraIdList.size();
            // 去除未受影响的指标
            // 原长度 + 添加元素的长度 = 添加后的长度 （没有相同的数据）
            if (size + lSize == pSize) {
                // 删除未受影响的指标
                indCodeSet.add(ind.getField());
                // 删除未受影响的指标参数
                paraIdList.removeAll(indPList);
            }
            else {
                size = pSize;
            }
        }
        if (!CollectionUtils.isEmpty(indCodeSet) && !CollectionUtils.isEmpty(ptlIndMap)) {
            indCodeSet.forEach(code -> ptlIndMap.entrySet().forEach(set -> {
                Set<String> value = set.getValue();
                value.remove(code);
            }));
        }
    }

    /**
     * 取得 指标参数信息
     * 
     * @param paraIdList 指标参数代码
     * @return 指标参数信息 Map<指标参数代码,指标参数信息>
     * @see
     * @since 1.0
     */
    public Map<String, IParam> getParam(Set<String> paraIdList) {
        return indParamSrv.getParamList(paraIdList);
    }

    /**
     * 取得 预占/实占参数信息
     * 
     * @param ocpCode 预占/实占代码
     * @param type 预占/实占类型
     * @return 预占/实占参数信息
     * @see
     * @since 1.0
     */
    public IOccupyRelParam getOccupyRelParam(String ocpCode, int type) {
        return ocpService.getOccupyRelParamMap(ocpCode, type);
    }

    /**
     * 保存 预占/实占参数信息
     * 
     * @param ocpRelParam 预占/实占代码
     * @return 预占/实占参数信息
     * @see
     * @since 1.0
     */
    public void setOccupyRelParam(IOccupyRelParam ocpRelParam) {
        ocpService.saveToRedis(ocpRelParam);
    }

    /**
     * 取得 日历信息
     * 
     * @param calendarId 日历编号
     * @return 日历信息
     * @see
     * @since 1.0
     */
    public ICalendar getCalendar(String calendarId) {
        return calendarSrc.getCalendar(calendarId);
    }

    /**
     * 遍历风险事件相关的指标参数
     * 
     * @param eveList 风险事件 <b>List<{@link IEvent}></b>
     * @param var1 处理逻辑
     * @see
     * @since 1.0
     */
    private void forEachEvent(List<IEvent> eveList, Consumer<IEventParaRel> var1) {
        eveList.forEach(eve -> {
            List<IEventParaRel> eprList = eve.getEventParaRel();
            if (!StringUtil.isEmptyList(eprList)) {
                eprList.forEach(var1);
            }
        });
    }

    /**
     * 构建指标参数查询条件
     * 
     * @param paramMap 指标参数信息 <b>Map<指标参数代码, 指标参数信息></b>
     * @param mapData 委托/交易信息
     * @param ptlParamMap 组合相关的指标参数 <b>Map<组合代码, Map<指标参数代码, 指标参数值Redis识别域>></b>
     * @return 指标参数查询条件
     * @see
     * @since 1.0
     */
    public Map<String, Map<String, IValue<Object>>> createCondition(Map<String, IParam> paramMap,
                                                                        Map<String, Object> mapData,
                                                                        Map<String, Map<String, String>> ptlParamMap) {
        // 查询条件列表
        Map<String, Map<String, IValue<Object>>> paraValList = new HashMap<>();
        // 指标参数信息
        paramMap.entrySet().forEach(pEn -> {
            IParam para = paramMap.get(pEn.getKey());
            String pCode = pEn.getKey();
            if (para != null
                && !CommonUtil.isType(para.getParaDisplayType(), BussinessDictCodeListRds.指标参数实现类型_类名)
                && !para.isEnumType()) {
                String field = getFiled(para.getRedisFieldArr(), mapData);
                // 指标参数值Redis识别域
                Map<String, IValue<Object>> map = paraValList.getOrDefault(pEn.getKey(),
                    new HashMap<>());
                map.put(field, null);
                paraValList.put(pCode, map);
            }
        });
        // 遍历组合相关的指标参数
        ptlParamMap.entrySet().forEach(en -> {
            // 组合代码
            String ptlCode = en.getKey();
            // Map<指标参数代码, 指标参数值Redis识别域>
            Map<String, String> paramList = en.getValue();
            mapData.put(PORTFOLIOCODE, ptlCode);
            paramList.entrySet().forEach(pEn -> {
                // 指标参数代码
                String pCode = pEn.getKey();
                // 指标参数信息
                IParam para = paramMap.get(pCode);
                if (para != null && !CommonUtil.isType(para.getParaDisplayType(),
                    BussinessDictCodeListRds.指标参数实现类型_类名) && !para.isEnumType()) {
                    // 指标参数值Redis识别域
                    String field = getFiled(para.getRedisFieldArr(), mapData);
                    pEn.setValue(field);
                    Map<String, IValue<Object>> map = paraValList.getOrDefault(pCode,
                        new HashMap<>());
                    map.put(field, null);
                    paraValList.put(pCode, map);
                }
            });
        });
        return paraValList;
    }

    /**
     * 根据风险事件计算指标参数
     * 
     * @param eveList 风险事件 List<{@link IEvent}>
     * @param paraValMap 指标参数值 <b>Map<指标参数代码, Map<指标参数值Redis识别域, 指标参数值>></b>
     * @param ocpRelParamObj 预占实占信息
     * @param details 计算流水明细列表
     * @see
     * @since 1.0
     */
    protected Map<String, Map<String, IValue<Object>>> calcParamVal(List<IEvent> eveList,
                                                                        Map<String, Map<String, IValue<Object>>> paraValMap,
                                                                        IOccupyRelParam ocpRelParamObj,
                                                                        List<RskIndicatorParaCalcdetail> details) {
        Map<String, IOcpRelParamItem> ocpPmItemMap = ocpRelParamObj == null ? new HashMap<>() : ocpRelParamObj.getItems();
        // 预占过的指标参数列表
        Set<String> indSet = CollectionUtils.isEmpty(
            ocpPmItemMap) ? new HashSet<>() : ocpPmItemMap.keySet();
        // 返回值 Map<指标参数代码, Map<指标参数值Redis识别域, 指标参数值>>
        Map<String, Map<String, IValue<Object>>> newValMap = new HashMap<>();
        newValMap.putAll(paraValMap);
        forEachEvent(eveList, epr -> {
            // 参数代码
            String paraCode = epr.getParaCode();
            // 当参数代码在预占过的指标参数列表中，则参数不计算
            if (indSet.contains(paraCode)) {
                return;
            } 
            // 关系类型
            String relType = epr.getRelType();
            // add by zhangrui1 start
            // 关系预算的对象值
//          BigDecimal val = epr.getRelValue();
            BigDecimal val1 = epr.getRelValue();
            // 占比金额
            if (StringUtil.equals(epr.getRelItem(), MIXAMOUNTKEY)) {
                val1 = getMixAmount();
            } 
            BigDecimal val = val1;
            // add by zhangrui1 end
            // Map<指标参数值Redis识别域, 指标参数值>
            Map<String, IValue<Object>> valMap = paraValMap.get(paraCode);
            if (CollectionUtils.isEmpty(valMap)) {
                return;
            }
            // Map<指标参数值Redis识别域, 指标参数值>
            Map<String, IValue<Object>> temp = new HashMap<>();
            HashSet<String> hs = new HashSet<>();
            valMap.entrySet().forEach(en -> {
                String key = en.getKey();
                // 参数值
                IValue<Object> paraVal = en.getValue();
                //----zxjjpei 更改类型------------------
                //若是禁投类或日期类处理
                if(StringUtil.isNotEmpty(paraVal)&&paraVal.value() instanceof String){
                    String parStr=paraVal.value().toString();
                    IValue<Object> newVal = new Value<>(
                            str -> StringUtil.isEmpty(str) ? StringUtil.EMPTY : str.toString());
                    newVal.set(parStr);
                    temp.put(key, newVal);
                    return;
                }
                BigDecimal par = paraVal == null ? BigDecimal.ZERO : (BigDecimal)paraVal.value();
                IValue<Object> newVal = new Value<>(
                    str -> StringUtil.isEmpty(str) ? BigDecimal.ZERO : new BigDecimal(str));
                // 指标参数值计算
                newVal.set(CommonUtil.math(par, val, relType));
                temp.put(key, newVal);
                hs.add(key);
                // 计算明细
                //----zxjjpei 更改类型------------------
                details.add(createDetail(paraCode, key, par, (BigDecimal)newVal.value(), CommonUtil.math(BigDecimal.ZERO, val, relType)));
            });
            newValMap.put(paraCode, temp);
            //----add by zxjjpei 20200401 检查是否需要更新redis start----
            if(!checkUpRdsParam(paraCode)){
                return;
            }
            //----add by zxjjpei 20200401 检查是否需要更新redis end----
            OcpRelParamItem ocpPmItem = new OcpRelParamItem();
            // 预占/实占的对象
            ocpPmItem.setOccupyItem(epr.getRelItem());
            // 预占/实占操作符号
            ocpPmItem.setOccupyOper(relType);
            // 预占/实占金额
            ocpPmItem.setOccupyVal(val);
            // 指标参数编号
            ocpPmItem.setParamCode(paraCode);
            // 参数值Redis存储Field
            ocpPmItem.setRedisFields(hs);
            ocpPmItemMap.put(paraCode, ocpPmItem);
        });
        // 记录未变更的参数值
        paraValMap.entrySet().forEach(en->{
            String paraCode = en.getKey();
            // 参数值
            Map<String,IValue<Object>> paraMap = en.getValue();
            if (!containsPara(details,paraCode) && paraMap != null) {
                paraMap.entrySet().forEach(pMap -> {
                    String key = pMap.getKey();
                    // 参数值
                    IValue<Object> paraVal = pMap.getValue();
                    //----zxjjpei 更改类型------------------
                    if(StringUtil.isNotEmpty(paraVal)&&paraVal.value() instanceof String){
                        return;
                    }
                    BigDecimal par = paraVal == null ? BigDecimal.ZERO : (BigDecimal)paraVal.value();
                    // 计算明细
                    details.add(createDetail(paraCode, key, par, par, BigDecimal.ZERO));
                });
            }
        });
        return newValMap;
    }

    /**
     * 根据风险事件计算指标参数
     * 
     * @param eveList 风险事件 List<{@link IEvent}>
     * @param paraValMap 指标参数值 <b>Map<指标参数代码, Map<指标参数值Redis识别域, 指标参数值>></b>
     * @param ocpRelParamObj 预占实占信息
     * @param details 计算流水明细列表
     * @see
     * @since 1.0
     */
    protected Map<String, Map<String, IValue<Object>>> calcParamVal(List<IEvent> eveList,
                                                                        Map<String, Map<String, IValue<Object>>> paraValMap,
                                                                        IOccupyRelParam ocpRelParamObj,
                                                                        List<RskIndicatorParaCalcdetail> details,Map<String, Object> mapData) {
        Map<String, IOcpRelParamItem> ocpPmItemMap = ocpRelParamObj == null ? new HashMap<>() : ocpRelParamObj.getItems();
        // 预占过的指标参数列表
        Set<String> indSet = CollectionUtils.isEmpty(
            ocpPmItemMap) ? new HashSet<>() : ocpPmItemMap.keySet();
        // 返回值 Map<指标参数代码, Map<指标参数值Redis识别域, 指标参数值>>
        Map<String, Map<String, IValue<Object>>> newValMap = new HashMap<>();
        newValMap.putAll(paraValMap);
        forEachEvent(eveList, epr -> {
            // 参数代码
            String paraCode = epr.getParaCode();
            // 当参数代码在预占过的指标参数列表中，则参数不计算
            if (indSet.contains(paraCode)) {
                return;
            } 
            // 关系类型
            String relType = epr.getRelType();
            // add by zhangrui1 start
            // 关系预算的对象值
//          BigDecimal val = epr.getRelValue();
            BigDecimal val1 = epr.getRelValue();
            // 占比金额
            if (StringUtil.equals(epr.getRelItem(), MIXAMOUNTKEY)) {
                val1 = getMixAmount();
            }
            if (StringUtil.isNotEmpty(mapData.get(PS)) 
                && mapData.get(PS).toString().equals(DictCodeList.交易方向_产品认购.getValue())
                && indReplaceList.contains(paraCode.substring(0, paraCode.indexOf(":")==-1?1:paraCode.indexOf(":")))) {
                if (StringUtil.isNotEmpty(mapData.get("ocpStatus")) 
                && mapData.get("ocpStatus").toString().equals("1")) {
                    val1 = BigDecimal.ZERO;
                } 
            }
            BigDecimal val = val1;
            // add by zhangrui1 end
            // Map<指标参数值Redis识别域, 指标参数值>
            Map<String, IValue<Object>> valMap = paraValMap.get(paraCode);
            if (CollectionUtils.isEmpty(valMap)) {
                return;
            }
            // Map<指标参数值Redis识别域, 指标参数值>
            Map<String, IValue<Object>> temp = new HashMap<>();
            HashSet<String> hs = new HashSet<>();
            valMap.entrySet().forEach(en -> {
                String key = en.getKey();
                // 参数值
                IValue<Object> paraVal = en.getValue();
                //----zxjjpei 更改类型------------------
                //若是禁投类或日期类处理
                if(StringUtil.isNotEmpty(paraVal)&&paraVal.value() instanceof String){
                    String parStr=paraVal.value().toString();
                    IValue<Object> newVal = new Value<>(
                            str -> StringUtil.isEmpty(str) ? StringUtil.EMPTY : str.toString());
                    newVal.set(parStr);
                    temp.put(key, newVal);
                    return;
                }
                BigDecimal par = paraVal == null ? BigDecimal.ZERO : (BigDecimal)paraVal.value();
                IValue<Object> newVal = new Value<>(
                    str -> StringUtil.isEmpty(str) ? BigDecimal.ZERO : new BigDecimal(str));
                // 指标参数值计算
                newVal.set(CommonUtil.math(par, val, relType));
                temp.put(key, newVal);
                hs.add(key);
                // 计算明细
                //----zxjjpei 更改类型------------------
                details.add(createDetail(paraCode, key, par, (BigDecimal)newVal.value(), CommonUtil.math(BigDecimal.ZERO, val, relType)));
            });
            //----add by zxjjpei 20200401 检查是否需要更新redis start----
            if(!checkUpRdsParam(paraCode)){
                return;
            }
            //----add by zxjjpei 20200401 检查是否需要更新redis end----
            newValMap.put(paraCode, temp); 
            OcpRelParamItem ocpPmItem = new OcpRelParamItem();
            // 预占/实占的对象
            ocpPmItem.setOccupyItem(epr.getRelItem());
            // 预占/实占操作符号
            ocpPmItem.setOccupyOper(relType);
            // 预占/实占金额
            ocpPmItem.setOccupyVal(val);
            // 指标参数编号
            ocpPmItem.setParamCode(paraCode);
            // 参数值Redis存储Field
            ocpPmItem.setRedisFields(hs);
            ocpPmItemMap.put(paraCode, ocpPmItem);
        });
        // 记录未变更的参数值
        paraValMap.entrySet().forEach(en->{
            String paraCode = en.getKey();
            // 参数值
            Map<String,IValue<Object>> paraMap = en.getValue();
            if (!containsPara(details,paraCode) && paraMap != null) {
                paraMap.entrySet().forEach(pMap -> {
                    String key = pMap.getKey();
                    // 参数值
                    IValue<Object> paraVal = pMap.getValue();
                    //----zxjjpei 更改类型------------------
                    if(StringUtil.isNotEmpty(paraVal)&&paraVal.value() instanceof String){
                        return;
                    }
                    BigDecimal par = paraVal == null ? BigDecimal.ZERO : (BigDecimal)paraVal.value();
                    // 计算明细
                    details.add(createDetail(paraCode, key, par, par, BigDecimal.ZERO));
                });
            }
        });
        return newValMap;
    }
    /**
     * 构建计算明细
     * 
     * @param paraCode 参数代码
     * @param key redis识别域
     * @param oldOne 原值
     * @param newOne 新值
     * @param chgOne 变化值
     * @return 计算明细
     */
    private RskIndicatorParaCalcdetail createDetail(String paraCode, String key, BigDecimal oldOne, BigDecimal newOne, BigDecimal chgOne){
    	// 计算明细
        RskIndicatorParaCalcdetail detail = new RskIndicatorParaCalcdetail();
        int index = StringUtil.indexOf(paraCode, GlobalConstant.COLON);
        // 参数代码
        detail.setParaCode(StringUtil.substring(paraCode, 0, index));
        // 参数粒度
        detail.setParaGranularity(StringUtil.substring(paraCode, index + 1));
        // 原值
        detail.setOldValue(oldOne);
        // 新值
        detail.setNewValue(newOne);
        String[] keyArr = StringUtil.split(key, GlobalConstant.COMMA);
        // 设置产品代码
        detail.setPortfolioId(keyArr != null && keyArr.length > 0 ? keyArr[0] : "");
        // 设置资产代码
        detail.setAssetCode(keyArr != null && keyArr.length > 1 ? keyArr[1] : "");
        // 变化值
        detail.setChangeValue(chgOne);
        return detail;
    }

    protected void freshDetail(List<RskIndicatorParaCalcdetail> details, Map<String, IParam> paramMap){
        if (StringUtil.isEmptyList(details)) {
            return;
        }
        details.forEach(detail->{
            String paraCode = detail.getParaCode() + GlobalConstant.COLON + detail.getParaGranularity();
            IParam param = paramMap.get(paraCode);
            if (param == null) {
                return;
            }
            String[] arr = param.getRedisFieldArr();
            if (arr != null && arr.length == 1) {
                if (StringUtil.equals(arr[0], PORTFOLIOCODE)) {
                    detail.setAssetCode("");
                }
                else {
                    detail.setPortfolioId("");
                }
            }
        });
    }

    private boolean containsPara(List<RskIndicatorParaCalcdetail> details, String paraCode){
    	if (StringUtil.isEmptyList(details)) {
    		return false;
    	}
    	for(RskIndicatorParaCalcdetail detail:details) {
    		if (detail != null && StringUtil.equals(paraCode, detail.getParaCode() + GlobalConstant.COLON + detail.getParaGranularity())) {
    			return true;
    		}
    	}
    	return false;
    }

    protected String getFiled(String[] redisFld, Map<String, Object> mapData) {
        StringBuilder builder = new StringBuilder();
        if (!StringUtil.isNotEmpty(redisFld)) {
            return StringUtil.EMPTY;
        }
        for (int i = 0; i < redisFld.length; i++ ) {
            Object val = mapData.getOrDefault(redisFld[i], redisFld[i] + GlobalConstant.COLON + mapData.get(EVENT_ID));
            if (!StringUtil.isNotEmpty(val)) {
            	val = redisFld[i] + GlobalConstant.COLON + mapData.get(EVENT_ID);
            }
            builder.append(GlobalConstant.COMMA).append(val);
        }
        return builder.length() > 1 ? builder.substring(1) : StringUtil.EMPTY;
    }

    @SuppressWarnings("unchecked")
    public void saveRskIsupTrial(Map<String, Object> response, Map<String, Object> mapData,
                                 String occupyCode, Map<String, Object> resMap) {
        Object obj = response.get(RSK_LIST);
        List<RskIsupTrial> sendList = new ArrayList<>();
        if (obj != null) {
            List<Map<String, Object>> riskList = (List<Map<String, Object>>)obj;
            Object obj2 =  mapData.get("eventDate");
            Date dt = obj2 instanceof Date ? (Date) obj2 : DateUtil.parse(obj2.toString());
            riskList.forEach(map -> {
                RskIsupTrial info = new RskIsupTrial();
                info.setUuid(IDGenerator.generate());
                // 业务编号
                info.setTradeId(occupyCode);
                // 组合代码
                info.setPortfolioId(getStrFromMap(map, PORTFOLIOCODE));
                // 监督指标编号
                info.setIndId(getStrFromMap(map, KRICODE));
                // 监督日期
                info.setSupDate(dt);
                // 监督值
                info.setSupVal(getStrFromMap(map, KRIVALUE));
                // 监督状态
                info.setSupStat(getStrFromMap(map, KRISTATUS));
                // 联合监督状态
                info.setUniteSupStat(getStrFromMap(map, UNITESTATUS));
                // 预警条件
                info.setWarnCond(getStrFromMap(map, WARNINGLINE));
                // 审批条件
                info.setApproveCond(getStrFromMap(map, APPROVELINE));
                // 违规条件
                info.setFoulCond(getStrFromMap(map, FORBIDDENLN));
                // 流水号
                info.setSeqNo((String) mapData.get("uuid"));
                setUserDt(info);
                sendList.add(info);
            });
        }
        resMap.put("ISUP_TRIAL", sendList);
    }

    private String getStrFromMap(Map<String, Object> map, String key) {
        Object obj = map.get(key);
        return obj == null ? StringUtil.EMPTY : String.valueOf(obj);
    }

    /**
     * 将试算的结果写入返回的报文中
     * 
     * @param response 返回的报文
     * @param resMap 试算的结果
     * @see
     * @since 1.0
     */
    protected void writeResponse(Map<String, Object> response, Map<String, IIndicator> resMap) {
        response.put(RSKSTATUS, BussinessDictCodeListRds.指标状态_通过.getValue());
        if (CollectionUtils.isEmpty(resMap)) {
            return;
        }
        List<Map<String, Object>> rskList = new ArrayList<>();
        resMap.entrySet().forEach(en -> {
            IIndicator ind = en.getValue();
            Map<String, IIndicatorResult> uniteMap = ind.getIndicatorResult();
            if (CollectionUtils.isEmpty(uniteMap)) {
                return;
            }
            uniteMap.entrySet().forEach(resEn -> {
                String ptlCode = resEn.getKey();
                IIndicatorResult res = resEn.getValue();
                rskList.add(getResMap(response, ptlCode, ind, res, resMap));
            });
        });
        response.put(RSK_LIST, rskList);
    }

    protected Map<String, Object> getResMap(Map<String, Object> response, String ptlCode,
                                            IIndicator ind, IIndicatorResult res,
                                            Map<String, IIndicator> resMap) {
        Map<String, Object> temp = new HashMap<>();
        // 产品代码
        temp.put(PORTFOLIOCODE, ptlCode);
        // 指标名称
        temp.put(KRICODE, ind.getIndId());
        // 指标名称
        temp.put(KRINAME, ind.getIndName());
        // 指标类型
        String indCellType = ind.getIndType();
        temp.put(KRITYPE, indCellType);
        IIndividLimit limit = res.getIndividLimit();
        // 审批线
        temp.put(APPROVELINE, changeCond(indCellType,
            () -> limit != null ? limit.getApproveCond() : ind.getApproveCond()));
        // 预警线
        temp.put(WARNINGLINE, changeCond(indCellType,
            () -> limit != null ? limit.getWarnCond() : ind.getWarnCond()));
        // 禁止线
        temp.put(FORBIDDENLN, changeCond(indCellType,
            () -> limit != null ? limit.getFoulCond() : ind.getFoulCond()));
        // 指标计算值
        temp.put(KRIVALUE, CommonUtil.convertKriVal(res.getIndicatorVal(), ind));
        // 指标原值
        temp.put(OLDVALUE, CommonUtil.convertKriVal(res.getIndicatorOldVal(), ind));
        // 趋势
        int inTrend = res.getIndicatorTrend();
        String trendStr = "";
        if (inTrend == 0) {
            trendStr = "不变";
        }
        else {
            trendStr = res.getIndicatorTrend() > 0 ? "向好" : "向坏";
        }
        temp.put(KRITREND, trendStr);
        Set<String> uniteList = ind.getPtlIndividUnite(ptlCode);
        String uSts = res.getUniteStatus();
        String kriStatus = res.getIndStatus();
        // 指标状态
        temp.put(KRISTATUS, kriStatus);
        // 是否有联合指标设置
        boolean hasUnite = !CollectionUtils.isEmpty(uniteList);
        // 有联合指标、且指标状态不是正常、且联合状态为空时
        if (hasUnite) {
            uSts = checkStatus(kriStatus, uniteList, ptlCode, resMap);
            res.setUniteStatus(uSts);
            temp.put(UNITESTATUS, uSts);
        } else {
        	uSts = kriStatus;
        }
        // 当存在某个指标状态超过整体状态且趋势向坏的指标时试算不通过
        String rskStatus = (String)response.get(RSKSTATUS);
        if (StringUtil.isNotEmpty(uSts) && uSts.compareTo(rskStatus) > 0 && res.getIndicatorTrend() < 0) {
            response.put(RSKSTATUS, kriStatus);
        }
        return temp;
    }

    /**
     * 阈值条件解析
     * 
     * @param indCellType 指标类型
     * @param fun 阈值条件取得接口
     * @return 解析结果
     * @see
     * @since 1.0
     */
    private String changeCond(String indCellType, Limit fun) {
        try {
            return RiskUtil.transferWarnFoul(fun.limit(), indCellType);
        }
        catch (Exception e) {
            logger.error("阈值条件解析异常", e);
            return "";
        }
    }

    interface Limit {

        String limit();
    }

    /**
     * 指标联合设置判断
     * 
     * @param rskStatus 指标状态
     * @param indList 联合指标
     * @param ptlCode 组合代码
     * @param resMap 指标列表
     * @return 指标联合设置判断结果
     * @see
     * @since 1.0
     */
    protected String checkStatus(String rskStatus, Set<String> indList, String ptlCode,
                                 Map<String, IIndicator> resMap) {
        String min = rskStatus;
        List<IIndicatorResult> resList = new ArrayList<>();
        for (String str : indList) {
            IIndicator ind = resMap.get(str);
            IIndicatorResult res = ind.getPtlIndicatorResult(ptlCode);
            String temp = res.getIndStatus();
            if (StringUtil.isEmpty(temp)) {
                continue;
            }
            // 取最小状态
            min = min.compareTo(temp) < 0 ? min : temp;
            resList.add(res);
        }
        final String temp = min;
        resList.forEach(res -> res.setUniteStatus(temp));
        return min;
    }

    /**
     * 获取 ptlIndService
     * 
     * @return ptlIndService.
     */
    public PtlIndicatorService getPtlIndService() {
        return ptlIndService;
    }

    /**
     * 获取 indService
     * 
     * @return indService.
     */
    public IndicatorService getIndService() {
        return indService;
    }

    /**
     * 获取 rskEventSrv
     * 
     * @return rskEventSrv.
     */
    public RskEventService getRskEventSrv() {
        return rskEventSrv;
    }

    /**
     * 获取 ocpService
     * 
     * @return ocpService.
     */
    public OccupyRelParamService getOcpService() {
        return ocpService;
    }

    /**
     * 获取 indParamSrv
     * 
     * @return indParamSrv.
     */
    public IndicatorParamService getIndParamSrv() {
        return indParamSrv;
    }

    /**
     * 获取 calcService
     * 
     * @return calcService.
     */
    public IndicatorCalcService getCalcService() {
        return calcService;
    }

    /**
     * 获取 paramValSrv
     * 
     * @return paramValSrv.
     */
    public IndicatorParamValService getParamValSrv() {
        return paramValSrv;
    }

    /**
     * 获取 assetInfoSrc
     * 
     * @return assetInfoSrc.
     */
    public AssetInfoRedisService getAssetInfoSrc() {
        return assetInfoSrc;
    }

    /**
     * 取得 资产基本信息
     * 
     * @param assetCode 资产代码
     * @return 资产基本信息
     * @see
     * @since 1.0
     */
    protected Map<String, Object> getAssetInfo(String assetCode) {
        return assetInfoSrc.getAssetInfoAsMap(assetCode);
    }

    /**
     * 设置创建人时间等
     * 
     * @param info 数据
     * @see
     * @since 1.0
     */
    public void setUserDt(BaseEntity info) {
        String user = "system";
        // 创建人
        info.setCreateUser(user);
        // 更新人
        info.setUpdateUser(user);
        Date dt = DateUtil.now();
        // 创建时间
        info.setCreateTime(dt);
        // 更新时间
        info.setUpdateTime(dt);
    }
    
    protected BigDecimal getMixAmount() {
        return (BigDecimal) getServiceInfo(ASSET_AMT_VAL);
    }
    
    public Object getServiceInfo(String key) {
        return serviceInfo.get().get(key);
    }
    
    protected boolean checkUpRdsParam(String paraCode){
        boolean upRedis=true;
        Map<String, IParam> newValParamMap=getParam(new HashSet<String>(Arrays.asList(paraCode)));
        for(String key:newValParamMap.keySet()){
            if (!newValParamMap.get(key).isUpRedis()) {
                upRedis=false;
            }
        }
        return upRedis;
    }
    
    public void setServiceInfo(String key, Object info) {
        serviceInfo.get().put(key, info);
    }
}
