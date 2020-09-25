/*
 * 文件名：Rsk003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.event.rsk006.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import com.joyintech.tams.invest.common.riskinfrds.info.entity.IProdInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrdsinf.common.service.EventBaseService;
import com.joyintech.service.riskrdsinf.core.annotation.INF;
import com.joyintech.service.riskrdsinf.core.client.ClientData;
import com.joyintech.service.riskrdsinf.core.constant.InfColumns;
import com.joyintech.service.riskrdsinf.core.exception.InterfException;
import com.joyintech.service.riskrdsinf.event.rsk006.entity.Rsk006InEntity;
import com.joyintech.service.riskrdsinf.event.rsk006.entity.Rsk006ItemEntity;
import com.joyintech.redis.common.entity.IValue;
import com.joyintech.redis.msg.entity.MessageBody.Operation;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.common.base.dao.RskDimEntrustInfoMapper;
import com.joyintech.tams.common.base.entity.RskIndicatorParaCalcdetail;
import com.joyintech.tams.common.base.entity.RskIsupTrialCls;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.riskinfrds.calendar.entity.ICalendar;
import com.joyintech.tams.invest.common.riskinfrds.event.entity.IEvent;
import com.joyintech.tams.invest.common.riskinfrds.indic.entity.IIndicator;
import com.joyintech.tams.invest.common.riskinfrds.param.entity.IParam;
import com.joyintech.tams.invest.common.riskinfrds.prod.entity.IPtlIndicator;

/**
 * 模糊委托接口详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月29日
 * @see Rsk006Service
 * @since 1.0
 */
@Service
@INF("RSK006")
public class Rsk006Service extends EventBaseService<Rsk006InEntity> {

    /**
     * 线程池
     */
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 模糊委托Mapper
     */
    @Autowired
    private RskDimEntrustInfoMapper dimMapper;

    /**
     * 试算列表
     */
    public static final String RSK_CALC_LIST = "rskCalcList";

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected Map<String, Map<String, Rsk006InEntity>> handle(ClientData request,
                                                              ClientData response)
        throws InterfException {
        // 取得报文体
        Map<String, Object> bodyMap = request.getBody();
        // 试算列表
        List<Map> mapList = new ArrayList<>();
        try {
            mapList = getList(bodyMap);
        }
        catch (IOException e) {
            throw new InterfException(e);
        }
        Map<String, ClientData> groupMap = new HashMap<>();
        CountDownLatch countDown = new CountDownLatch(mapList.size());
        for (Map item : mapList) {
            ClientData rsp = new ClientData();
            ClientData res = new ClientData();
            res.setBody(item);
            exchangeCcy(res);
            // 取得基金估值行情
            if (isCalcAmount()) {
            	calcAmount(item, ()->getUnitPrice((String) item.get(ASSETCODE)));
            }
            item.put(InfColumns.COM_SYSDATE.fieldName(),
                bodyMap.get(InfColumns.COM_SYSDATE.fieldName()));
            item.put(EVENT_TYPE, "3");
            String portfolioCode = (String)item.get(PORTFOLIOCODE);
            // 取得产品信息
            IProdInfo prodInfo = getProdInfo(portfolioCode);
            // 是否启动风险
            String riskEnabled = null;
            if (prodInfo != null) {
                // 是否启动风险
                riskEnabled = prodInfo.getRiskEnabled();
            }
            else {
            	throw new InterfException("产品代码【" + portfolioCode + "】对应的信息不存在！");
            }
            // 未启动风险则不进行预占
            if (CommonUtil.isType(riskEnabled, BussinessDictCodeListRds.是否_否)) {
                throw new InterfException("产品(" + portfolioCode + ")未启动风险!");
            }
            String assetCode = (String)item.get(ASSETCODE);
            assetCode = StringUtil.isEmpty(assetCode) ? "ALL" : assetCode;
        	String astCode = assetCode;
            threadPoolTaskExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        calc(item, rsp);
                        List<Map<String,Object>> rskList = (List<Map<String, Object>>) rsp.getBodyField(RSK_LIST);
                        if (!StringUtil.isEmptyList(rskList)) {
                        	rskList.forEach(en->{
                        		if (en != null) {
                        			en.put(ASSETCODE, astCode);
                        		}
                        	});
                        }
                    }
                    catch (InterfException e) {
                        doException(rsp, "模糊委托试算异常", e);
                    }
                    finally {
                        // 线程计数
                        countDown.countDown();
                        groupMap.put(astCode, rsp);
                    }
                }

            });
        }
        try {
            // 等待线程结束
            countDown.await();
        }
        catch (InterruptedException e) {
            getLogger().error("线程池中断异常", e);
            Thread.currentThread().interrupt();
        }
        // 设置返回
        setResponse(groupMap, response);
        // 更新DB
        final List<Map> dataList = mapList;
        threadPoolTaskExecutor.execute(new Runnable() {

            @Override
            public void run() {
                saveData(dataList, response);
            }

        });

        return null;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void saveData(List<Map> mapList, ClientData response) {
        String rskStatus = (String)response.getBodyField(RSKSTATUS);
        if (CommonUtil.isType(rskStatus, BussinessDictCodeListRds.指标状态_禁止)) {
            return;
        }
        if (StringUtil.isEmptyList(mapList)) {
            return;
        }
        mapList.forEach(map -> {
        	Rsk006ItemEntity dto = BeanUtil.mapToObject(map, Rsk006ItemEntity.class);
            if (dto != null && !StringUtil.isEmpty(dto.getEventId())) {
                dto.setProdId((String) map.get(PORTFOLIOCODE));
                dto.setWflStatus(BussinessDictCodeListRds.有效状态_待处理.getValue());
                // 设置备份数据
                dto.setAmount(dto.getAmountOld());
                dto.setTradeNum(dto.getTradeNumOld());
                dimMapper.insertSelective(dto);
            }
        });
    }

    /**
     * 合并时算结果
     * 
     * @param groupMap 试算结果
     * @param response 输出
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    protected void setResponse(Map<String, ClientData> groupMap, ClientData response) {
        String rtnCode = InfColumns.COM_RTNCODE.fieldName();
        String rtnMsg = InfColumns.COM_RTNMSG.fieldName();
        response.setHeadField(rtnCode, AAAAAAA);
        response.setBodyField(RSKSTATUS, BussinessDictCodeListRds.指标状态_通过.getValue());
        if (!CollectionUtils.isEmpty(groupMap)) {
            List<Map<String, Object>> rskList = new ArrayList<>();
            groupMap.entrySet().forEach(en -> {
                ClientData value = en.getValue();
                if (value != null && !value.isEmpty()) {
                    String rtCd = (String)value.getHeadField(rtnCode);
                    if (!StringUtil.equals(AAAAAAA, rtCd) && !StringUtil.isEmpty(rtCd)) {
                        response.setHeadField(rtnCode, rtCd);
                        response.setHeadField(rtnMsg, value.getHeadField(rtnMsg));
                    }
                    // 当有一个资产不达标时则不通过
                    String kriStatus = (String)value.getBodyField(RSKSTATUS);
                    String rskStatus = (String)response.getBodyField(RSKSTATUS);
                    rskStatus = CommonUtil.compareStr(kriStatus, rskStatus,
                        BussinessDictCodeListRds.运算符_大于.getValue()) ? kriStatus : rskStatus;
                    response.setBodyField(RSKSTATUS, rskStatus);
                    // 合并试算结果
                    List<Map<String, Object>> temp = (List<Map<String, Object>>)value.getBodyField(
                        RSK_LIST);
                    if (temp != null) {
                        rskList.addAll(temp);
                    }
                }
            });
            response.setBodyField(RSK_LIST, rskList);
        }
    }

    protected void calc(Map<String, Object> mapData, ClientData rsp)
        throws InterfException {
    	// 指标参数计算明细
    	List<RskIndicatorParaCalcdetail> details = new ArrayList<>();
        // 交易方向数据字典转换
        changePsValue(mapData);
        // 备份组合代码
        mapData.put(PROD_ID, mapData.get(PORTFOLIOCODE));
        // 取得资产基本信息
        Map<String, Object> assetInfo = getAssetInfo(mapData);
        // 若资产基本信息不为空
        if (!CollectionUtils.isEmpty(assetInfo)) {
            mapData.putAll(assetInfo);
        }
        else if (StringUtil.isNotEmpty(mapData.get(ASSETCODE))) {
        	throw new InterfException(mapData.get(ASSETCODE) + "的资产信息不存在");
        }
        // 取得日历信息
        ICalendar calendar = getCalendar((String)mapData.get(CALENDAR_ID));
        // 匹配风险事件
        List<IEvent> eveList = getEvent(mapData, calendar);
        // 若风险事件不存在，则试算通过
        if (StringUtil.isEmptyList(eveList)) {
            // 试算通过
            getLogger().info(LOG_INFO_EVENT);
            return;
        }
        // 从风险事件列表中取出相关的参数列表
        Set<String> paraIdList = getParamIdList(eveList);
        // 取得组合关联指标信息
        IPtlIndicator ptlInd = getPtlIndicator(mapData);
        // 若无 组合关联指标信息，则试算通过
        if (ptlInd == null) {
            getLogger().info(LOG_INFO_PASS);
            return;
        }
        
        log("处理前的产品关联指标详细：【{}】", ptlInd.getPtlIndicator().entrySet(), en->en != null ? en.getKey() + "=[" + StringUtil.join(en.getValue(), GlobalConstant.COMMA) + "]" : "");

        // 取得所有指标代码
        Set<String> indIdList = getIndIdList(ptlInd);
        // 若组合未关联指标信息，则试算通过
        if (CollectionUtils.isEmpty(indIdList)) {
            getLogger().info(LOG_INFO_PASS);
            return;
        }
        // 取得所有的指标信息
        Map<String, IIndicator> indMap = getIndicator(indIdList);
        // 若指标信息不存在，则抛出异常
        if (CollectionUtils.isEmpty(indMap)) {
            throw new InterfException("指标信息不存在");
        }
        
        // 风险事件相关参数与指标相关参数糅合去重(并集)
        Map<String, Set<String>> ptlIndMap = ptlInd.getPtlIndicator();
        unionParamId(indMap, paraIdList, ptlIndMap);
        //wangyang1 风险二级法人改造 start
        if (MultilevelDataAuthorityUtil.isEnabled() && StringUtil.isNotEmpty(mapData.get("instCode"))) {
            String instCode = mapData.get("instCode").toString();
            Set<String> paraIdListChd = new HashSet<>();
            paraIdList.forEach(item->{
                if (item.indexOf(BussinessDictCodeListRds.指标参数实现类型_固定值.getValue())!=-1) { 
                    paraIdListChd.add(item.replace(BussinessDictCodeListRds.指标参数实现类型_固定值.getValue(),instCode));
                }
            });
            paraIdList.addAll(paraIdListChd);
        } 
        //wangyang1 风险二级法人改造 end 
        // 取得所有指标参数信息
        Map<String, IParam> paramMap = getParam(paraIdList);
        // 若指标参数不存在则抛出异常
        if (CollectionUtils.isEmpty(paramMap)) {
            throw new InterfException("指标参数信息不存在");
        }

        log("涉及的指标参数有：【{}】", paramMap.entrySet(), en->en != null ? en.getKey() : "");

        // 判断监督时点，若不满足则指标不计算,判断是否剔除资产，若满足则指标不计算
        checkTpAndIndivAd(indMap, mapData, ptlInd);
        // 当指标都被剔除时，直接试算通过
        if (CollectionUtils.isEmpty(ptlInd.getPtlIndicator())) {
            getLogger().info("无需要计算的指标，试算通过");
            return;
        }

        log("处理后的产品关联指标详细：【{}】", ptlInd.getPtlIndicator().entrySet(), en->en != null ? en.getKey() + "=[" + StringUtil.join(en.getValue(), GlobalConstant.COMMA) + "]" : "");

        // 构建组合与指标参数索引
        Map<String, Map<String, String>> ptlParamMap = getPtlParamMap(ptlInd, indMap);
        
        // 执行事务处理（Redis操作）
        handleByLock(() -> {
            String uuid =  String.valueOf(System.currentTimeMillis());
            mapData.put("uuid", uuid);
            // 取得所有指标参数的值
            Map<String, Map<String, IValue<Object>>> paraValMap = getParamValSrv().getParamVal(
                createCondition(paramMap, mapData, ptlParamMap));
            // 若指标参数值未初始化到Redis则抛出异常
            if (CollectionUtils.isEmpty(paraValMap)) {
                throw new InterfException("指标参数值不存在");
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
        // TODO 禁（可）投指标处理
        // 处理返回结果
        writeResponse(rsp, indMap, paramMap);
        if (StringUtil.isNotEmpty(mapData.get(EVENT_ID))) {
        	RskIsupTrialCls isupTrialCls = new RskIsupTrialCls();
            // 业务类型
            isupTrialCls.setTradeType("3");
            // 业务编号
            isupTrialCls.setTradeId((String) mapData.get(EVENT_ID));
            // 试算结论
            isupTrialCls.setConclusion((String) rsp.getBodyField(RSKSTATUS));
            // 设置流水号
            isupTrialCls.setSeqNo((String) mapData.get("uuid"));
            // 保存风险计算结论
            setUserDt(isupTrialCls);
            saveDb(Operation.W, isupTrialCls);
            // 保存监督指标计算结果
            saveRskIsupTrial(rsp, mapData, (String) mapData.get(EVENT_ID));
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private List<Map> getList(Map<String, Object> dataMap)
        throws IOException {
        Object obj = dataMap.get(RSK_CALC_LIST);
        if (obj instanceof List) {
            return (List<Map>)obj;
        }
        if (obj instanceof String) {
            String listStr = (String)obj;
            return JsonUtils.json2List(listStr, Map.class);
        }
        return new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(Rsk006InEntity obj, ClientData response) {
        return false;
    }

}
