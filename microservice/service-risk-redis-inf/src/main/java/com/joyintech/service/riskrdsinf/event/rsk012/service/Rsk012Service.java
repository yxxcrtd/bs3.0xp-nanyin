/*
 * 文件名：Rsk003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.event.rsk012.service;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.redis.common.entity.IValue;
import com.joyintech.redis.msg.entity.MessageBody.Operation;
import com.joyintech.service.riskrdsinf.common.service.OccupyReleasService;
import com.joyintech.service.riskrdsinf.core.annotation.INF;
import com.joyintech.service.riskrdsinf.core.client.ClientData;
import com.joyintech.service.riskrdsinf.core.exception.InterfException;
import com.joyintech.service.riskrdsinf.core.util.InfMapValUtil;
import com.joyintech.service.riskrdsinf.event.rsk004.entity.Rsk004InEntity;
import com.joyintech.service.riskrdsinf.event.rsk004.service.Rsk004Service;
import com.joyintech.service.riskrdsinf.process.entity.RskApproval002Dto;
import com.joyintech.service.riskrdsinf.process.service.RskApproval002Service;
import com.joyintech.tams.common.base.entity.RskIndicatorParaCalcdetail;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.occupy.entity.IOccupyRelParam;
import com.joyintech.tams.invest.common.riskinfrds.occupy.entity.IOcpRelParamItem;
import com.joyintech.tams.invest.common.riskinfrds.occupy.entity.OccupyRelParam;
import com.joyintech.tams.invest.common.riskinfrds.param.service.IndicatorParamValService;

/**
 * 额度释放接口处理详细-批量
 * 
 * @author fuxj
 * @version 1.0 2019年8月5日
 * @see Rsk012Service
 * @since 1.0
 */
@Service
@INF("RSK012")
public class Rsk012Service extends OccupyReleasService<Rsk004InEntity> {
	public static final Stack<IOccupyRelParam> stack = new Stack<IOccupyRelParam>(); // 创建堆栈对象

	/**
	 * 试算列表
	 */
	public static final String DATALIST = "RSK_LIST";

	@Autowired
	private RskApproval002Service processSrc;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Map<String, Map<String, Rsk004InEntity>> handle(ClientData request, ClientData response)
			throws InterfException {
		// 指标参数计算明细
		List<RskIndicatorParaCalcdetail> details = new ArrayList<>();
		// 组合，指标，指标值
		Map<String, Map<String, Map<String, IValue<Object>>>> cachedMap = new HashMap<>();

		Map<String, Object> body = request.getBody();
		// 试算列表
		List<Map> mapList = new ArrayList<>();
		try {
			mapList = getList(body);
		} catch (IOException e) {
			throw new InterfException(e);
		}
		List<ClientData> groupMap = new ArrayList<>();

		final IndicatorParamValService paramValSrv = getParamValSrv();
		List<IOccupyRelParam> ListIOccupyRel = new ArrayList<>();
		// 取得所有指标参数的值
		Map<String, Map<String, IValue<Object>>> paraValMapSum = new HashMap<String, Map<String, IValue<Object>>>();
		List<Map> paraValMapList = new ArrayList<>();
        // add by zhangrui1 start
        List<String> fields = new ArrayList<>();
        final List<Map> tempList = mapList;
        handleByLock(() -> {
    	for (Map mapData : tempList) {
		//for (Map mapData : mapList) {
	    // add by zhangrui1 end
			ClientData rsp = new ClientData();
			ClientData res = new ClientData();
			groupMap.add(rsp);

			// 从报文体取得预占/实占编号
			String occupyCode = (String) mapData.get(EVENT_ID);
			// 从Redis取得预占/实占信息
			IOccupyRelParam ocpRelParam = getOccupyRelParam(occupyCode, getOccupyType());
			// 若预占/实占信息不存在
			if (ocpRelParam == null) {
			    return null;
//				throw new InterfException((getOccupyType() == PRE_OCCUPY ? "预占" : "实占") + "对应的信息不存在!");
			}
			// modify by zhangrui1 start
			//handleByLock(() -> {
            calcMixAmount(mapData, true);
			// modify by zhangrui1 end
	            String uuid =  String.valueOf(System.currentTimeMillis());
	            mapData.put("uuid", uuid);
				// 净值
				calcAmount(mapData, () -> ocpRelParam.getNetPrice());
				// 预占/实占信息相关参数列表
				Map<String, IOcpRelParamItem> items = ocpRelParam.getItems();
				// 构造取参数值的条件
				Map<String, Map<String, IValue<Object>>> hashList = makeHashList(items, mapData);
				// 取得所有指标参数的值
				Map<String, Map<String, IValue<Object>>> paraValMap = paramValSrv.getParamVal(hashList);
				/*
				 *使用新计算的值替换redis中的值 
				 */
				for(String key:paraValMap.keySet()) {
					Map<String, IValue<Object>> valMap = paraValMap.get(key);
					for(int i=0;i<paraValMapList.size();i++) {
						Map<String, Map<String, IValue<Object>>> tempParaValMap = paraValMapList.get(i);
						if(tempParaValMap.containsKey(key)) {
							Map<String, IValue<Object>> tempValMap = tempParaValMap.get(key);
							for(String field:tempValMap.keySet()) {
								if(valMap.containsKey(field)) {
									valMap.put(field, tempValMap.get(field));
								}
							}
						}
					}	
				}
				// 若指标参数值未初始化到Redis则抛出异常
				if (CollectionUtils.isEmpty(paraValMap)) {
					throw new InterfException("指标参数值不存在");
				}
				List<RskIndicatorParaCalcdetail> detail = new ArrayList<>();
				// 计算参数值、预占/实占额度
				calcParamVal(paraValMap, mapData, items, detail);
				
				detail.forEach(single->{
	        		// 业务编号
					single.setTradeId(occupyCode);
	        		// 业务类型
					single.setTradeType((String) mapData.get(EVENT_TYPE));
	        		// 设置监督日期
					single.setSupDate(DateUtil.parse(mapData.get("eventDate").toString()));
	        		// 设置时间戳
	        		Object obj = mapData.get("uuid");
	        		single.setDotimestamp(DateUtil.format(new Date(new Long(obj.toString())),DateUtil.DEFAULT_DATETIME_FORMAT_SSS));
	        		// 设置UUID
	        		single.setUuid(IDGenerator.generate());
	        		// 创建人
	        		single.setCreateUser(getInfId());
	        		// 更新人
	        		single.setUpdateUser(getInfId());
	        	});
				paraValMapList.add(paraValMap);
				details.addAll(detail);
				// modify by zhangrui1 start
				//return ocpRelParam;
			//});
            // modify by zhangrui1 end
			if (!isRevoke(mapData)) {
				ListIOccupyRel.add(ocpRelParam);
			}
			else {
				fields.add(occupyCode + GlobalConstant.COLON + getOccupyType());
			}
		}
		// 更新参数值信息
    	for(Map<String, Map<String, IValue<Object>>> map:paraValMapList) {
    		paramValSrv.setParamVal(map);	
    	}
		// modify by zhangrui1 start
        getOcpService().deleteInfoFromRedis(RedisKeyEnum.预占实占信息.key(), fields.toArray(new String[fields.size()]));
		//for (Map mapData : mapList) {
		//	String occupyCode = (String) mapData.get(EVENT_ID);
			// 更新DB
	        //saveData(mapData, false, getInfId()); 
	        // 撤销的场合，直接删除预占实占信息
	     //   if (isRevoke(mapData)) {
	    //    	getOcpService().deleteInfoFromRedis(RedisKeyEnum.预占实占信息.key(), occupyCode + GlobalConstant.COLON + getOccupyType());
	    //    }
		//}
		// modify by zhangrui1 end
		if (!StringUtil.isEmptyList(ListIOccupyRel)) {
			// modify by zhangrui1 start
//			ListIOccupyRel.forEach(ocpRelParam->{setOccupyRelParam(ocpRelParam);});
			getOcpService().saveToRedis(ListIOccupyRel.toArray(new IOccupyRelParam[ListIOccupyRel.size()]));
			// modify by zhangrui1 end
		}
		// add by zhangrui1 start
        // 更新资产实时规模信息
        refreshAssetAmout();
		    return 0;
        });
		for (Map<String, Object> mapData : mapList) {
			if(StringUtil.isNotEmpty(mapData.get("uuid"))) {
				saveData(mapData, false, getInfId()); 	
			}
		}
		// add by zhangrui1 end
        // 参数计算明细
        if (!StringUtil.isEmptyList(details)) {
			saveDb(Operation.W, details.toArray(new RskIndicatorParaCalcdetail[details.size()]));
		}
		return null;
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
		} catch (IOException e) {
			getLogger().error("处理异常", e);
			// throw new InterfException(e);
			return true;
		}
		for (Map item : mapList) {
			Rsk004InEntity info = InfMapValUtil.mapDataToObject(item, getParamClass());
			boolean res = checkErr(info, response);
			if (res)
				return res;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Class<Rsk004InEntity> getParamClass() {
		return (Class<Rsk004InEntity>) ((ParameterizedType) Rsk004Service.class.getGenericSuperclass())
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void saveData(Map<String, Object> mapData, boolean isApprove, String user) throws InterfException {
		try {
			Rsk004InEntity record = InfMapValUtil.mapDataToObject(mapData, Rsk004InEntity.class);
			// 设置备份数据
			record.setAmount(record.getAmountOld());
			record.setTradeNum(record.getTradeNumOld());
			getBusiService().saveOcpInfo(record, isApprove, STATUS_RELEASE, user);
		} catch (Exception e) {
			getLogger().error("实占释放信息保存异常", e);
			throw new InterfException("实占释放信息保存异常");
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getOccupyType() {
		return REL_OCCUPY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean checkWithCond(Rsk004InEntity obj, ClientData response) {
		RskApproval002Dto dto = new RskApproval002Dto();
		dto.setEventId(obj.getEventId());
		if (processSrc.isInWorkflow(dto)) {
			doException(response, "风险审批流程中的交易不允许撤销", null);
			return true;
		}
		return false;
	}

}
