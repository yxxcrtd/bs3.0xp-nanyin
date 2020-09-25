/*
 * 文件名：Rsk011Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.1.2
 * 修改人：dengyu
 * 修改时间：2020年3月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.1.2
 */

package com.joyintech.service.riskrdsinf.event.rsk011.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.redis.common.entity.IValue;
import com.joyintech.service.riskrdsinf.cache.InterfCacheUtil;
import com.joyintech.service.riskrdsinf.common.service.EventBaseService;
import com.joyintech.service.riskrdsinf.core.annotation.INF;
import com.joyintech.service.riskrdsinf.core.client.ClientData;
import com.joyintech.service.riskrdsinf.core.constant.InfColumns;
import com.joyintech.service.riskrdsinf.core.exception.InterfException;
import com.joyintech.service.riskrdsinf.event.rsk002.entity.Rsk002InEntity;
import com.joyintech.tams.common.base.entity.RskIndicatorParaCalcdetail;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.riskinfrds.calendar.entity.ICalendar;
import com.joyintech.tams.invest.common.riskinfrds.event.entity.IEvent;
import com.joyintech.tams.invest.common.riskinfrds.indic.entity.IIndicator;
import com.joyintech.tams.invest.common.riskinfrds.info.entity.IProdInfo;
import com.joyintech.tams.invest.common.riskinfrds.param.entity.IParam;
import com.joyintech.tams.invest.common.riskinfrds.prod.entity.IPtlIndicator;

/**
 * 风险批量试算服务接口处理
 * 
 * @author dengyu
 * @version 1.0 2020年3月23日
 * @see Rsk011Service
 * @since 1.0
 */
@Service
@INF("RSK011")
public class Rsk011Service extends EventBaseService<Rsk002InEntity> {

    /**
     * 试算列表
     */
    public static final String RSK_CALC_LIST = "rskCalcList";

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
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected Map<String, Map<String, Rsk002InEntity>> handle(ClientData request,  ClientData response)
        throws InterfException {

    	// 指标参数计算明细
     	List<RskIndicatorParaCalcdetail> details = new ArrayList<>();
        //组合，指标，指标值
     	// modify by zhangrui1 start
     	//Map<String, Map<String, Map<String, IValue<Object>>>> cachedMap =new HashMap<>();
     	Map<String, Map<String, IValue<Object>>> localCache = new HashMap<>();
     	// modify by zhangrui1 end
        
        Map<String, Object> body = request.getBody();
        // 试算列表
        List<Map> mapList = new ArrayList<>();
        try {
            mapList = getList(body);
        }
        catch (IOException e) {
            throw new InterfException(e);
        }
        HashMap <String ,IProdInfo> prodInfoCache =new HashMap<String ,IProdInfo>();
        
        initIProdInfo(prodInfoCache,mapList);
        
        List< ClientData> groupMap = new ArrayList<>();
        
        for (Map mapData : mapList) {
        	// 添加日期元素 王贵奇 20200512
        	mapData.put(InfColumns.COM_SYSDATE.fieldName(), body.get(InfColumns.COM_SYSDATE.fieldName()));
            ClientData rsp = new ClientData();
            ClientData res = new ClientData();
            groupMap.add(rsp);
             // 取得产品信息
            String portfolioCode = (String)mapData.get(PORTFOLIOCODE);
          IProdInfo prodInfo=prodInfoCache.get(portfolioCode);
          // 是否启动风险
          String riskEnabled = null;
          if (prodInfo != null) {
              riskEnabled = prodInfo.getRiskEnabled();
          }else {
          	throw new InterfException("产品代码【" + portfolioCode + "】对应的信息不存在！");
          }
          // 未启动风险则不进行预占
          if (CommonUtil.isType(riskEnabled, BussinessDictCodeListRds.是否_否)) {
              throw new InterfException("产品(" + portfolioCode + ")未启动风险!");
          }
          // 交易方向数据字典转换
          changePsValue(mapData);
          // 取得资产基本信息
          Map<String, Object> assetInfo = getAssetInfo(mapData);
          if (!isAssetNoNeeds()) {
              // 若资产基本信息不为空
              if (!CollectionUtils.isEmpty(assetInfo)) {
                  mapData.putAll(assetInfo);
              }
              else if (StringUtil.isNotEmpty(mapData.get(ASSETCODE))) {
                  throw new InterfException(mapData.get(ASSETCODE) + "的资产信息不存在");
              }
              else if (StringUtil.isNotEmpty(mapData.get(MARKET_CODE))) {
                  throw new InterfException(mapData.get(MARKET_CODE) + "的资产信息不存在");
              }
          }
          // 取得基金估值行情
          if (isCalcAmount()) {
          	calcAmount(mapData, ()->getUnitPrice((String) mapData.get(ASSETCODE)));
          }
          // 取得日历信息
          ICalendar calendar = getCalendar((String)mapData.get(CALENDAR_ID));
          // 匹配风险事件
          List<IEvent> eveList = getEvent(mapData, calendar);
          // 若风险事件不存在，则试算通过
          // 触发的风险事件,不计算指标,则试算通过
          if (StringUtil.isEmptyList(eveList) ||!isCalcIndicator(eveList)) {
              // 试算通过
              response.setBodyField(RSKSTATUS, BussinessDictCodeListRds.指标状态_通过.getValue());
              response.setBodyField(RSK_LIST, new ArrayList<>());
              getLogger().info(LOG_INFO_EVENT);
              continue;
          }
          
          // 从风险事件列表中取出相关的参数列表
          Set<String> paraIdList = getParamIdList(eveList);
          // 取得组合关联指标信息
          IPtlIndicator ptlInd = getPtlIndicator(mapData);
          // 若无 组合关联指标信息，则试算通过
          if (ptlInd == null) {
              response.setBodyField(RSKSTATUS, BussinessDictCodeListRds.指标状态_通过.getValue());
              response.setBodyField(RSK_LIST, new ArrayList<>());
              getLogger().info(LOG_INFO_PASS);
              continue;
          }
          //*********************************风险计算*************************************************
          
          // 取得所有指标代码
          Set<String> indIdList = getIndIdList(ptlInd);
          // 若组合未关联指标信息，则试算通过
          if (CollectionUtils.isEmpty(indIdList)) {
              getLogger().info(LOG_INFO_PASS);
              response.setBodyField(RSKSTATUS, BussinessDictCodeListRds.指标状态_通过.getValue());
              response.setBodyField(RSK_LIST, new ArrayList<>());
              continue;
          }
          log("处理前的产品关联指标详细：【{}】", ptlInd.getPtlIndicator().entrySet(), en->en != null ? en.getKey() + "=[" + StringUtil.join(en.getValue(), GlobalConstant.COMMA) + "]" : "");
         
          // 取得所有的指标信息
          Map<String, IIndicator> indMap = getIndicator(indIdList);
          // 若指标信息不存在，则抛出异常
          if (CollectionUtils.isEmpty(indMap)) {
              throw new InterfException("指标信息不存在");
          }
          // 构建组合与指标参数索引
          Map<String, Map<String, String>> ptlParamMap = getPtlParamMap(ptlInd, indMap);

          Map<String, Set<String>> ptlIndMap = ptlInd.getPtlIndicator();
          // 将风险事件关联的指标参数挂到组合（包）
          putEventParam(ptlParamMap, paraIdList);
          // 风险事件相关参数与指标相关参数糅合去重(并集)
          unionParamId(indMap, paraIdList, ptlIndMap);
          // 一条指标都没有的时候
          if (CollectionUtils.isEmpty(indMap) || CollectionUtils.isEmpty(indMap.values())) {
              // 状态
              response.setBodyField(RSKSTATUS, BussinessDictCodeListRds.指标状态_通过.getValue());
              // 试算结果
              response.setBodyField(RSK_LIST, new ArrayList<>());
              continue;
          }
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

          checkTpAndIndivAd(indMap, mapData, ptlInd);
          // 当指标都被剔除时，试算通过，直接进行预占
          if (CollectionUtils.isEmpty(ptlIndMap) || CollectionUtils.isEmpty(ptlIndMap.values())) {
              getLogger().info("无指标需要计算，试算通过");
              // 状态
              response.setBodyField(RSKSTATUS, BussinessDictCodeListRds.指标状态_通过.getValue());
              // 试算结果
              response.setBodyField(RSK_LIST, new ArrayList<>());
              continue;
          }
          log("处理后的产品关联指标详细：【{}】", ptlInd.getPtlIndicator().entrySet(), en->en != null ? en.getKey() + "=[" + StringUtil.join(en.getValue(), GlobalConstant.COMMA) + "]" : "");

          // 执行事务处理（Redis操作）
          handleByLock(() -> {
              // add by zhangrui1 start
              calcMixAmount(mapData, false);
              // add by zhangrui1 end
              String uuid =  String.valueOf(System.currentTimeMillis());
              mapData.put("uuid", uuid);
            
              //需要从redis检索的值
              Map<String, Map<String, IValue<Object>>> condition=createCondition(paramMap, mapData, ptlParamMap);
              // modify by zhangrui1 start
              //Map<String, Map<String, IValue<Object>>> paraValMap =  new HashMap<>();
              // 取得所有指标参数的值
              //if(cachedMap.containsKey(portfolioCode)){
            	//  paraValMap = cachedMap.get(portfolioCode);
              //}else{
               //paraValMap = getParamValSrv().getParamVal(condition);
               //cachedMap.put(portfolioCode, paraValMap);
              //}
              // 每一轮影响的参数可能都不一样，不能完全依赖上一次的结果，应该取出本次受影响的参数，然后将上次计算后的参数再更新进来
              Map<String, Map<String, IValue<Object>>> paraValMap = getParamValSrv().getParamVal(condition);
              mergeMap(paraValMap, localCache);
              // modify by zhangrui1 end

              // 若指标参数值未初始化到Redis则抛出异常
              if (CollectionUtils.isEmpty(paraValMap)) {
                  //----update by zxjjpei 20200318 若存在固定值参数则让通过 start---
                  if(checkSqlParamType(paramMap)){
                      throw new InterfException("指标参数值不存在");
                  }else {
                      getLogger().info("没有SQL类型参数且指标参数值不存在，直接通过");
                      return null;
                  }
                  //----update by zxjjpei 20200318 若存在固定值参数则让通过 end---
              }
              
              // 计算参数值
              Map<String, Map<String, IValue<Object>>> newValMap = calcParamVal(eveList, paraValMap, null, details);
              
              // modify by zhangrui1 计算结果出现重复问题对应 start
              //refreshCache(newValMap,cachedMap,portfolioCode);
              // modify by zhangrui1 计算结果出现重复问题对应 end
              
              if (!CollectionUtils.isEmpty(indMap) && !CollectionUtils.isEmpty(indMap.values())) { 
                  // 固定值参数
                  Map<String, Object> map = getFixedParaVal(paramMap,mapData);
                  // ---zxjjpei---枚举型参数 走库型参数 20200312 start-----
                  Map<String, Object> mapFbd = getForbidParaVal(paramMap,mapData);
                  Map<String, Object> mapDbCal =getDataBaseParaVal(paramMap,mapData);
                  // ---zxjjpei---枚举型参数 走库型参数 20200312 end-----
                  // 指标参数原值
                  Map<String, Map<String, Object>> pValMap = parseParaVal(ptlParamMap, paraValMap);
                  // 加入固定值
                  unionParaVal(pValMap, map);
                  // --add by jjpei 20200312 加入走库参数 start---
                  unionParaVal(pValMap, mapDbCal);
                  // --add by jjpei 20200312 加入走库参数 end---
                //----add by jjpei 20200312 把固定值与走库加上画面的这一笔进行计算 start-------
                  calcFixParamVal(eveList,map);
                  calcFixParamVal(eveList,mapDbCal);
                  //----add by jjpei 20200312 把固定值与走库加上画面的这一笔进行计算 end-------
                  // 指标计算原值
                  calcIndicator(ptlInd, indMap, mapData, pValMap, true);
                  // 指标参数新值
                  pValMap = parseParaVal(ptlParamMap, calcValWithoutEvt(getParamIdList(eveList), eveList, newValMap));
                  // 加入固定值
                  unionParaVal(pValMap, map);
                  // ---zxjjpei---加入枚举型参数值 走库型参数 20200312 start-----
                  unionParaVal(pValMap,mapFbd);
                  unionParaVal(pValMap,mapDbCal);
                  // ---zxjjpei---加入枚举型参数值 走库型参数 20200312 end-----
                  // 指标计算新值
                  calcIndicator(ptlInd, indMap, mapData, pValMap, false);
                  indMap.entrySet().forEach(en -> checkIndTrend(en.getValue()));
                  // 处理返回结果
                  writeResponse(rsp, indMap, paramMap);
              }
              // add by zhangrui1 计算结果出现重复问题对应 start
              mergeMap(localCache, newValMap);
              // add by zhangrui1 计算结果出现重复问题对应 end
              return 0;
          });

          // add by zhangrui1 试算返回值增加资产名称 start
          handleResponse(rsp, assetInfo);
          // add by zhangrui1 试算返回值增加资产名称 end 
          //*********************************风险计算*************************************************
        }
        // 设置返回
        setResponse(groupMap, response);
        return null;
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
    protected void setResponse(List<ClientData> groupMap, ClientData response) {
        String rtnCode = InfColumns.COM_RTNCODE.fieldName();
        String rtnMsg = InfColumns.COM_RTNMSG.fieldName();
        response.setHeadField(rtnCode, AAAAAAA);
        response.setBodyField(RSKSTATUS, BussinessDictCodeListRds.指标状态_通过.getValue());
        if (!CollectionUtils.isEmpty(groupMap)) {
            List<Map<String, Object>> rskList = new ArrayList<>();
            groupMap.forEach(value -> {
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
 
    // add by zhangrui1 start
    /**
     * 将newMap内容覆盖到targetMap中去
     * 
     * @param targetMap 目标MAP
     * @param newMap 变化MAP
     */
    protected void mergeMap(final Map<String, Map<String, IValue<Object>>> targetMap, Map<String, Map<String, IValue<Object>>> newMap) {
        if (CollectionUtils.isEmpty(newMap) || CollectionUtils.isEmpty(targetMap)) {
            return;
        }
        newMap.entrySet().forEach(en -> {
            String key = en.getKey();
            Map<String, IValue<Object>> temp = targetMap.getOrDefault(key, new HashMap<>());
            temp.putAll(en.getValue());
            targetMap.put(key, temp);
        });
     }
//	private void refreshCache(Map<String, Map<String, IValue<Object>>> newValMap,
//			Map<String, Map<String, Map<String, IValue<Object>>>> cachedMap, String portfolioCode) {
//		if(cachedMap.containsKey(portfolioCode)){
//			cachedMap.get(portfolioCode).putAll(newValMap);
//		}else{
//			Map<String, Map<String, Map<String, IValue<Object>>>> newMap =new HashMap<>();
//			newMap.put(portfolioCode, newValMap);
//		cachedMap.putAll(newMap);
//		}
//	}
    // add by zhangrui1 end
	private void initIProdInfo(HashMap<String,IProdInfo> prodInfoCache, List<Map> mapList) {
        for (Map mapData : mapList) {
          String portfolioCode = (String)mapData.get(PORTFOLIOCODE);
          if(!prodInfoCache.containsKey(portfolioCode)){
        	  IProdInfo  prodInfo = getProdInfo(portfolioCode);
        	  prodInfoCache.put(portfolioCode, prodInfo);
        }
       }
	}
	/**
     * 是否做过预占
     * 
     * @param request 请求报文
     * @return true:做过预占
     */
    protected boolean isPreOcp(ClientData request){
        // 预占编号
        String preCode = (String)request.getBodyField("preOccupyCode");
        String sysCode = (String)request.getBodyField("sourceSys");
        if (StringUtil.isEmpty(preCode) || StringUtil.isEmpty(sysCode)) {
            return false;
        }
        String preOccupyCode = sysCode + GlobalConstant.COLON + preCode;
        // 预占信息存在
        return getOccupy(preOccupyCode, PRE_OCCUPY) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(Rsk002InEntity obj, ClientData response) {
    	String ps = obj.getPs();
    	String infAssetNoNeedPs = InterfCacheUtil.getSysParam(INF_ASSET_NONEED_PS);
    	if (StringUtil.contains(infAssetNoNeedPs, ps)) {
    		return false;
    	}
        if (StringUtil.isEmpty(obj.getAssetCode()) && (StringUtil.isEmpty(obj.getMarketCode()) || StringUtil.isEmpty(obj.getTradePlace()))
        		&& StringUtil.isEmpty(obj.getAssetType())) {
            doException(response, "资产大类、资产代码、以及市场代码和交易场所不能同时为空", null);
            return true;
        }
        return false;
    }
}
