/*
 * 文件名：Rsk003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.event.rsk200.service;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrdsinf.cache.InterfCacheUtil;
import com.joyintech.service.riskrdsinf.core.annotation.INF;
import com.joyintech.service.riskrdsinf.core.client.ClientData;
import com.joyintech.service.riskrdsinf.core.constant.InfColumns;
import com.joyintech.service.riskrdsinf.core.exception.InterfException;
import com.joyintech.service.riskrdsinf.core.util.InfMapValUtil;
import com.joyintech.service.riskrdsinf.event.rsk003.entity.Rsk003InEntity;
import com.joyintech.service.riskrdsinf.event.rsk003.service.Rsk003Service;
import com.joyintech.service.riskrdsinf.event.rsk004.service.Rsk004Service;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;

/**
 * 额度占用接口处理详细-批量
 * 
 * @author fuxj
 * @version 1.0 2019年8月5日
 * @see Rsk200Service
 * @since 1.0
 */
@Service
@INF("RSK200")
public class Rsk200Service  extends Rsk003Service {
	public static final Stack<ClientData> stack = new Stack<ClientData>(); // 创建堆栈对象
    @Autowired
    private Rsk004Service rsk004Service;
    
    /**
          * 试算列表
     */
    public static final String DATALIST = "RSK_LIST";
    
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map<String, Map<String, Rsk003InEntity>> handle(ClientData request, ClientData response)
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
        
//		Map<String, Object> head = request.getHead();
		Map<String, Object> body = request.getBody();
//		List<Map<String, Object>> list = (List<Map<String, Object>>) body.get(request.KEY_DATALIST);

		List resultList = new ArrayList<>();
		// 堆清空
		stack.clear();
		try {
			// add by zhangrui1 start
			response.setBodyField(RSKSTATUS, BussinessDictCodeListRds.指标状态_通过.getValue());
			// add by zhangrui1 end
			for (Map item : mapList) {
				ClientData rsp = new ClientData();
				ClientData res = new ClientData();
				// add by zhangrui1 start
				res.setHead(request.getHead());
				// add by zhangrui1 end
				res.setBody(item);
				// 外层加工的
//				body.forEach((k, v) -> {
//					if (v instanceof List) {
//
//					} else if(!StringUtil.equals(BigDecimal.ZERO, v)){  
//					      item.put(k, v); 
//					}
//				});
				item.put("consumerIP",body.get("consumerIP")); 
				item.put("consumerPort",body.get("consumerPort"));
				item.put("sysDate",body.get("sysDate")); 
				super.handle(res, rsp);
				// add by zhangrui1 start
				String rskStatus = (String) response.getBodyField(RSKSTATUS);
				String kriStatus = (String) rsp.getBodyField(RSKSTATUS);
				String type = BussinessDictCodeListRds.运算符_大于等于.getValue();
				// rskStatus >= kriStatus ? rskStatus : kriStatus
				response.setBodyField(RSKSTATUS, CommonUtil.compareStr(rskStatus, kriStatus, type) 
						? rskStatus : kriStatus);
				Object obj = rsp.getBodyField(RSK_LIST);
				if (obj instanceof List) {
					resultList.addAll((List<Map<String, Object>>) obj);
				}
				// add by zhangrui1 end
				// 如果失败，或者异常。弹堆
				if (BussinessDictCodeListRds.指标状态_禁止.getValue().equals(rsp.getBodyField(RSKSTATUS))) {
					rollbackStackToRedis();
					// add by zhangrui1 start
					response.setBodyField(RSKSTATUS, rsp.getBodyField(RSKSTATUS));
					doException(response, res.getBodyField(EVENT_ID) + "交易超限，所有交易回滚", null);
					// add by zhangrui1 end
					break;
				} else {
					// 暂存
					stack.push(res);
				}
				// modify by zhangrui1 start
				// 状态标识。如果都通过，则返回通过，如果中间遇到不通过，则总的结果也返回为不通过
				//response.setBodyField(RSKSTATUS, rsp.getBodyField(RSKSTATUS));
				//Object o = rsp.getBodyField(RSK_LIST);

				//if (o instanceof ArrayList) {
				//	if (!((ArrayList) o).isEmpty()) {
				//		resultList.addAll(((ArrayList) o));
				//	}
				//}
				// modify by zhangrui1 end
			}
		} catch (Exception e) {
			// 回滚redis的信息
			rollbackStackToRedis();
			doException(response, "额度占用接口处理详细-批量", e);
		} finally {
			/**
			 * 结果集
			 */
			response.setBodyField(RSK_LIST, resultList);
			stack.clear();
		}

		return null;
	}
	/**
	 * 释放占用的额度
	 */
	private void rollbackStackToRedis(){
		while(!stack.isEmpty()){
			ClientData request = stack.pop();
			// add by zhangrui start
			request.setHeadField(InfColumns.COM_INFID.fieldName(), BussinessDictCodeListRds.接口系统ID_实占释放接口.getValue());
			// add by zhangrui end
			request.getBody().put("revokeFlg",BussinessDictCodeListRds.是否_是.getValue());
            rsk004Service.execute(request);
		}
	}

	   
    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(Rsk003InEntity obj, ClientData response) {
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
	
	@SuppressWarnings("unchecked")
	@Override
	protected boolean checkRequestErr(ClientData request, ClientData response) {
		Map<String, Object> body = request.getBody();
		 // 取得报文体
        Map<String, Object> bodyMap = request.getBody();
        // 试算列表
        List<Map> mapList = new ArrayList<>();
        try {
            mapList = getList(bodyMap);
        }
        catch (IOException e) {
        	getLogger().error("处理异常", e);
           // throw new InterfException(e);
            return true;
        }
		for (Map item : mapList) {
			Rsk003InEntity info = InfMapValUtil.mapDataToObject(item, getParamClass());
			boolean res = checkErr(info, response);
			if (res)
				return res;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Class<Rsk003InEntity> getParamClass() {
		return (Class<Rsk003InEntity>) ((ParameterizedType) Rsk003Service.class.getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getList(Map<String, Object> dataMap) throws IOException {
		Object obj = dataMap.get(DATALIST);
		if (obj instanceof List) {
			return (List<Map>) obj;
		}
		if (obj instanceof String) {
			String listStr = (String) obj;
			return JsonUtils.json2List(listStr, Map.class);
		}
		return new ArrayList<>();
	}
}
