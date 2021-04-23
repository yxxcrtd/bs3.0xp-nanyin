package com.joyintech.tams.invest.common.position.service;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.FelContext;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.common.dto.etr.EtrSecPositionFlowDto;
import com.joyintech.tams.invest.common.position.dao.CommonPtlPositionMapper;
import com.joyintech.tams.invest.common.position.dto.PostionParameter;
import com.joyintech.tams.invest.common.position.dto.PtlPositionFlowCfg;
import com.joyintech.tams.invest.common.position.dto.PtlPositionScene;
import com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService;
import com.joyintech.tams.invest.common.util.ExpressionUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CommonPositionService {

	private static  Map<String,Object> SCENE_CFG ;
	private static  Map<String,Object> FLOW_CFG ;

	private final static BigDecimal oneHundred = new BigDecimal(100);

	@Autowired
	private CommonPtlPositionMapper commonPtlPositionMapper;
	@Autowired
	private EtrCashPositionMapper etrCashPositionMapper;
	@Autowired
	private EtrCashPositionFlowMapper etrCashPositionFlowMapper;
	@Autowired
	private EtrSecPositionMapper etrSecPositionMapper;
	@Autowired
	private EtrSecPositionFlowMapper etrSecPositionFlowMapper;
	@Autowired
	private PtlSecPositionMapper ptlSecPositionMapper;
	@Autowired
	private PtlSecPositionFlowMapper ptlSecPositionFlowMapper;
	@Autowired
	private PtlCashPositionMapper ptlCashPositionMapper;
	@Autowired
	private PtlCashPositionFlowMapper ptlCashPositionFlowMapper;
	@Autowired
	private EtrEntrustMapper etrEntrustMapper;
	
	/**
	 * 头寸处理入口
	 */
	public boolean process(PostionParameter parameter) {
		if(!SCENE_CFG.containsKey(parameter.toKey())){
			return false;//没有配置对应场景主键
		}
		List<PtlPositionScene> ptlPositionSceneList = (List<PtlPositionScene>) SCENE_CFG.get(parameter.toKey());
		String sceneId = "";
		for(PtlPositionScene ptlPositionScene:ptlPositionSceneList){
			String dynamicCondition = ptlPositionScene.getDynamicCondition();
			if (StringUtil.isNotEmpty(dynamicCondition)){
				Object flag = ExpressionUtil.eval(dynamicCondition, parameter);
				if((boolean) flag){
					sceneId = ptlPositionScene.getSceneId();
					break;
				}
			}else {
				sceneId = ptlPositionScene.getSceneId();
				break;
			}

		}
		if(!FLOW_CFG.containsKey(sceneId)){
			return false;//无场景对应流水信息
		}
		List<PtlPositionFlowCfg> scenePtlPositionFlowCfg = (List<PtlPositionFlowCfg>) FLOW_CFG.get(sceneId);
		for(PtlPositionFlowCfg ptlPositionFlowCfg:scenePtlPositionFlowCfg){
			if(StringUtil.equals(ptlPositionFlowCfg.getProcessType(), "01")){//默认配置增加新流水
				commonPositionFlow(parameter,ptlPositionFlowCfg);
			}else if(StringUtil.equals(ptlPositionFlowCfg.getProcessType(), "02")){//撤回退回共通
				etrRevokePositionFlow(parameter,ptlPositionFlowCfg);
			}else{
				String[] s= StringUtil.split(ptlPositionFlowCfg.getClassMethod(),".");
				Map<String, Object> param = new HashMap<>();
				Object  object = SpringContextUtil.getBean(s[0]);
				Method method = null;
				try {
					method = CommonPositionService.class.getMethod(s[1], Map.class);
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
				Map<String, Object> properties = getParamMap(parameter, ptlPositionFlowCfg);
				try {
					assert method != null;
					method.invoke(object,properties);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	/**
	 * 公共流水处理方式
	 * @param parameter
	 * @param ptlPositionFlowCfg
	 */
	public void commonPositionFlow(Map<String, Object> parameter , PtlPositionFlowCfg ptlPositionFlowCfg) {
		Map<String, Object> properties = getParamMap(parameter, ptlPositionFlowCfg);
		if(StringUtil.equals(ptlPositionFlowCfg.getFlowType(), "01")){//指令现金
			EtrCashPositionFlow etrCashPositionFlow = BeanUtil.mapToObject(properties, EtrCashPositionFlow.class);
			if(!StringUtil.equals(ptlPositionFlowCfg.getProcessConfig(), "01")){
				etrCashPositionFlow.setAmount(etrCashPositionFlow.getAmount().negate());
			}
			if (etrCashPositionFlow.getCdate() == null){
				etrCashPositionFlow.setCdate(getSysDate());
			}
			etrCashPositionFlow.setSeqno(getUuid());
			if(!StringUtil.isNotEmpty(etrCashPositionFlow.getAcctId())){
				String acctId ="";
				//账户为空
				if(StringUtil.isNotEmpty(properties.get("secAcctId"))){//证券账户不为空 取证券账户资金账户
					acctId = commonPtlPositionMapper.selectAcctIdBySecAcctId(properties);
				}
				else if(StringUtil.isNotEmpty(properties.get("portfolioId"))){//组合不为空 取组合资金账户
					acctId = commonPtlPositionMapper.selectAcctIdByPortfolioId(properties);
				}
				etrCashPositionFlow.setAcctId(acctId);
			}
			insertEtrCashPositionFlow(etrCashPositionFlow);//入库
			
		}else if(StringUtil.equals(ptlPositionFlowCfg.getFlowType(), "02")){//指令持仓
			EtrSecPositionFlow etrSecPositionFlow = BeanUtil.mapToObject(properties, EtrSecPositionFlow.class);
			if(!StringUtil.equals(ptlPositionFlowCfg.getProcessConfig(), "01")){
				etrSecPositionFlow.setAmount(etrSecPositionFlow.getAmount().negate());
			}
			if (etrSecPositionFlow.getCdate() == null){
				etrSecPositionFlow.setCdate(getSysDate());
			}
			etrSecPositionFlow.setSeqno(getUuid());//设置主键
			insertEtrSecPositionFlow(etrSecPositionFlow);//入库
		}else if(StringUtil.equals(ptlPositionFlowCfg.getFlowType(), "03")){//交易现金
			PtlCashPositionFlow ptlCashPositionFlow = BeanUtil.mapToObject(properties, PtlCashPositionFlow.class);
			if(!StringUtil.equals(ptlPositionFlowCfg.getProcessConfig(), "01")){
				ptlCashPositionFlow.setAmount(ptlCashPositionFlow.getAmount().negate());
			}
			if (ptlCashPositionFlow.getCdate() == null){
				ptlCashPositionFlow.setCdate(getSysDate());
			}
			ptlCashPositionFlow.setSeqno(getUuid());//设置主键
			if(!StringUtil.isNotEmpty(ptlCashPositionFlow.getAcctId())){
				String acctId ="";
				//账户为空
				if(StringUtil.isNotEmpty(properties.get("secAcctId"))){//证券账户不为空 取证券账户资金账户
					acctId = commonPtlPositionMapper.selectAcctIdBySecAcctId(properties);
				}
				else if(StringUtil.isNotEmpty(properties.get("portfolioId"))){//组合不为空 取组合资金账户
					acctId = commonPtlPositionMapper.selectAcctIdByPortfolioId(properties);
				}
				ptlCashPositionFlow.setAcctId(acctId);
			}
			insertPtlCashPositionFlow(ptlCashPositionFlow);//入库
		}else if(StringUtil.equals(ptlPositionFlowCfg.getFlowType(), "04")){//交易持仓
			PtlSecPositionFlow ptlSecPositionFlow = BeanUtil.mapToObject(properties, PtlSecPositionFlow.class);
			if(!StringUtil.equals(ptlPositionFlowCfg.getProcessConfig(), "01")){
				ptlSecPositionFlow.setAmount(ptlSecPositionFlow.getAmount().negate());
			}
			if (ptlSecPositionFlow.getCdate() == null){
				ptlSecPositionFlow.setCdate(getSysDate());
			}
			ptlSecPositionFlow.setSeqno(getUuid());//设置主键
			insertPtlSecPositionFlow(ptlSecPositionFlow);//入库
		}
	}
	
	/**
	 * 公共撤销、退回流水处理方式
	 * @param
	 * @param ptlPositionFlowCfg
	 */
	public void etrRevokePositionFlow(Map<String, Object> properties , PtlPositionFlowCfg ptlPositionFlowCfg) {
		Map<String, Object> param = getParamMap(properties, ptlPositionFlowCfg);
		if(StringUtil.isNotEmpty(param.get("entrustId"))){//指令
			List<EtrCashPositionFlow> etrCashPositionFlowList = commonPtlPositionMapper.selectEtrCashPositionFlowByEntrustId(param);
			for(EtrCashPositionFlow etrCashPositionFlow:etrCashPositionFlowList){
				etrCashPositionFlow.setSeqno(getUuid());//设置主键
				etrCashPositionFlow.setAmount(etrCashPositionFlow.getAmount().negate());
			}
			List<EtrSecPositionFlow> etrSecPositionFlowList = commonPtlPositionMapper.selectEtrSecPositionFlowByEntrustId(param);
			for(EtrSecPositionFlow etrSecPositionFlow:etrSecPositionFlowList){
				etrSecPositionFlow.setSeqno(getUuid());//设置主键
				etrSecPositionFlow.setAmount(etrSecPositionFlow.getAmount().negate());
			}
		}
		if(StringUtil.isNotEmpty(param.get("tradeId"))){//交易
			List<PtlCashPositionFlow> ptlCashPositionFlowList = commonPtlPositionMapper.selectPtlCashPositionFlowByTradeId(param);
			for(PtlCashPositionFlow ptlCashPositionFlow:ptlCashPositionFlowList){
				ptlCashPositionFlow.setSeqno(getUuid());//设置主键
				ptlCashPositionFlow.setAmount(ptlCashPositionFlow.getAmount().negate());
			}
			List<PtlSecPositionFlow> ptlSecPositionFlowList = commonPtlPositionMapper.selectPtlSecPositionFlowByTradeId(param);
			for(PtlSecPositionFlow ptlSecPositionFlow:ptlSecPositionFlowList){
				ptlSecPositionFlow.setSeqno(getUuid());//设置主键
				ptlSecPositionFlow.setAmount(ptlSecPositionFlow.getAmount().negate());
			}
		}
	}

	private Map<String, Object> getParamMap(Map<String, Object> properties, PtlPositionFlowCfg ptlPositionFlowCfg) {
		Map<String,Object> dataMap =ptlPositionFlowCfg.getDataMap();
		Map<String, Object> param = new HashMap<>();
		for(String key:dataMap.keySet()){
			if (StringUtil.isNotEmpty(dataMap.get(key)) && dataMap.get(key).toString().startsWith("custom")){
				Object o = ExpressionUtil.eval((String)dataMap.get(key), properties);
				param.put(key, o);
			}else {
				param.put(key, dataMap.get(key));
			}
		}
		return param;
	}
	

	/**
	 * 启动时调此方法,加载2个配置表的数据
	 */
	public Map<String,Object> init() {
		initSceneConfig();//初始化场景配置
		
		initFlowCfgConfig();//初始化
		Map<String,Object> result =new HashMap<>();
		result.put("status", "success");
		return result;
	}


	/**
	 * 初始化结果对应
	 */
	private void initFlowCfgConfig() {
		Map<String,Object> ptlPositionFlowCfgMap =new ConcurrentHashMap<String,Object>();
		List<PtlPositionFlowCfg> ptlPositionFlowCfgList = commonPtlPositionMapper.getAllPtlPositionFlowCfg();
		for(PtlPositionFlowCfg ptlPositionFlowCfg: ptlPositionFlowCfgList){
			if(ptlPositionFlowCfgMap.containsKey(ptlPositionFlowCfg.getSceneId())){
				continue;
			}
			List<PtlPositionFlowCfg> scenePtlPositionFlowCfg = new ArrayList<>();
			for(PtlPositionFlowCfg ptlPositionFlowCfg2: ptlPositionFlowCfgList){
				if(StringUtil.equals(ptlPositionFlowCfg.getSceneId(), ptlPositionFlowCfg2.getSceneId())){
					Map<String, Object> dataMap =null;
					try{
						dataMap = JsonUtils.json2Object(ptlPositionFlowCfg2.getParameterConfig(), Map.class);
					}catch (Exception e) {
						
					}
					ptlPositionFlowCfg2.setDataMap(dataMap);
					scenePtlPositionFlowCfg.add(ptlPositionFlowCfg2);
				}
			}
			ptlPositionFlowCfgMap.put(ptlPositionFlowCfg.getSceneId(), scenePtlPositionFlowCfg);
		}
		FLOW_CFG = ptlPositionFlowCfgMap;
		
	}


	/**
	 * 初始化场景信息
	 */
	private void initSceneConfig() {
		Map<String,Object> ptlPositionSceneMap =new ConcurrentHashMap<String,Object>();
		List<PtlPositionScene> ptlPositionSceneList = commonPtlPositionMapper.getAllPtlPositionScene();
		for(PtlPositionScene ptlPositionScene: ptlPositionSceneList){
			if(ptlPositionSceneMap.containsKey(ptlPositionScene.toKey())){
				continue;
			}
			List<PtlPositionScene> postionParameterList = new ArrayList<>();
			for(PtlPositionScene ptlPositionScene1: ptlPositionSceneList){
				if(StringUtil.equals(ptlPositionScene.getSceneId(), ptlPositionScene1.getSceneId())){
					postionParameterList.add(ptlPositionScene1);
				}
			}
			ptlPositionSceneMap.put(ptlPositionScene.toKey(), postionParameterList);
		}
		SCENE_CFG =ptlPositionSceneMap;
	}
	
	/**
	 * 生成uuid
	 * @return
	 */
	private String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 到期续作，冲销新质押券持仓
	 * @param param
	 * @return
	 */
	public boolean renewalAfterMaturity(Map<String,Object> param) {
		// 指令环节，新质押券持仓
		BigDecimal etrAmt = BigDecimal.ZERO;
		List<EtrSecPositionFlow> etrSecPositionFlows = commonPtlPositionMapper.selectEtrSecPositionFlowByFin(param);
		if (etrSecPositionFlows.size() > 0){
			etrAmt = etrSecPositionFlows.get(0).getAmount();
		}
		BigDecimal nowPledgeAmt = CommonUtil.getBigDecimal(param,"amount",BigDecimal.ZERO);
		// 不需要冲销
		if (etrAmt.compareTo(nowPledgeAmt) == 0){
			return true;
		}
		etrSecPositionFlows.forEach(etrSecPositionFlow -> {
			etrSecPositionFlow.setOffsetFlag("02");
			// 原指令冲正标识更新
			etrSecPositionFlowMapper.updateByPrimaryKeySelective(etrSecPositionFlow);
			// 重新插入一条反向流水
			etrSecPositionFlow.setAmount(etrSecPositionFlow.getAmount().negate());
			etrSecPositionFlow.setOffsetId(String.valueOf(param.get("entrustId")));
			etrSecPositionFlow.setInvAim(String.valueOf(param.get("invAim")));
			etrSecPositionFlow.setSeqno(IDGenerator.generate());
			insertEtrSecPositionFlow(etrSecPositionFlow);
		});
		// 根据交易持仓重新生成指令可用持仓
		List<PtlSecPositionFlow> ptlSecPositionFlowList = commonPtlPositionMapper.selectPtlSecPositionFlowByEntrustId(param);
		ptlSecPositionFlowList.forEach(ptlSecPositionFlow -> {
			EtrSecPositionFlow etrSecPositionFlow = new EtrSecPositionFlow();
			BeanUtil.copyPropertiesIgnoreNull(etrSecPositionFlow,ptlSecPositionFlow);
			etrSecPositionFlow.setSeqno(IDGenerator.generate());
			if (StringUtil.equals(etrSecPositionFlow.getHodingType(),DictCodeList.持仓类型_质押.getValue())){
				etrSecPositionFlow.setAmount(etrSecPositionFlow.getAmount().negate());
			}
			etrSecPositionFlow.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
			insertEtrSecPositionFlow(etrSecPositionFlow);
		});
		return true;
	}

	/**
	 * 指令撤回、关闭，交易冲销
	 * @param param
	 * @return
	 */
	public boolean revokeEtrSecPositionFlow(Map<String,Object> param){
		//已完成额度
		BigDecimal finishAmt = commonPtlPositionMapper.getEtrFinishAmt(param);
		// 委托
		EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(String.valueOf(param.get("entrustId")));

		//委托额度
		BigDecimal entrustAmt = BigDecimal.ZERO;
		if (StringUtil.isNotEmpty(etrEntrust)) {
			entrustAmt = etrEntrust.getEntrustAmt();
			if (entrustAmt == null || BigDecimal.ZERO.compareTo(entrustAmt) >= 0) {
				entrustAmt = etrEntrust.getEntrustNum();
			}
		}
		// 指令关闭 or 委托完成，冲销指令可用持仓，根据交易持仓重新生成指令可用持仓
		if (StringUtil.equals(DictCodeList.是否_是.getValue(),param.get("closeFlag")) || entrustAmt.compareTo(finishAmt) <= 0){
			//查询原指令持仓流水，冲销指令可用持仓
			List<EtrSecPositionFlow> etrSecPositionFlowList = commonPtlPositionMapper.selectEtrSecPositionFlowByEntrustId(param);
			etrSecPositionFlowList.forEach(etrSecPositionFlow -> {
				etrSecPositionFlow.setOffsetFlag("02");
				// 原指令冲正标识更新
				etrSecPositionFlowMapper.updateByPrimaryKeySelective(etrSecPositionFlow);
				// 重新插入一条反向流水
				etrSecPositionFlow.setAmount(etrSecPositionFlow.getAmount().negate());
				etrSecPositionFlow.setOffsetId(String.valueOf(param.get("entrustId")));
				etrSecPositionFlow.setInvAim(String.valueOf(param.get("invAim")));
				etrSecPositionFlow.setSeqno(IDGenerator.generate());
				insertEtrSecPositionFlow(etrSecPositionFlow);
			});
			// 根据交易持仓重新生成指令可用持仓
			List<PtlSecPositionFlow> ptlSecPositionFlowList = commonPtlPositionMapper.selectPtlSecPositionFlowByEntrustId(param);
			ptlSecPositionFlowList.forEach(ptlSecPositionFlow -> {
				EtrSecPositionFlow etrSecPositionFlow = new EtrSecPositionFlow();
				BeanUtil.copyPropertiesIgnoreNull(etrSecPositionFlow,ptlSecPositionFlow);
				etrSecPositionFlow.setSeqno(IDGenerator.generate());
				if (StringUtil.equals(etrSecPositionFlow.getHodingType(),DictCodeList.持仓类型_质押.getValue())){
					etrSecPositionFlow.setAmount(etrSecPositionFlow.getAmount().negate());
				}
				etrSecPositionFlow.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
				insertEtrSecPositionFlow(etrSecPositionFlow);
			});

			//查询原指令现金流水，冲销指令可用现金
			List<EtrCashPositionFlow> etrCashPositionFlows = commonPtlPositionMapper.selectEtrCashPositionFlowByEntrustId(param);
			etrCashPositionFlows.forEach(etrCashPositionFlow -> {
				// 原指令冲正标识更新
				etrCashPositionFlow.setOffsetFlag("02");
				etrCashPositionFlowMapper.updateByPrimaryKeySelective(etrCashPositionFlow);
				// 重新插入一条反向流水
				etrCashPositionFlow.setAmount(etrCashPositionFlow.getAmount().negate());
				etrCashPositionFlow.setOffsetId(String.valueOf(param.get("entrustId")));
				etrCashPositionFlow.setSeqno(IDGenerator.generate());
				insertEtrCashPositionFlow(etrCashPositionFlow);
			});
			// 根据交易现金流水重新生成指令可用现金
			List<PtlCashPositionFlow> ptlCashPositionFlows = commonPtlPositionMapper.selectPtlCashPositionFlowByEntrustId(param);
			ptlCashPositionFlows.forEach(ptlCashPositionFlow -> {
				EtrCashPositionFlow etrCashPositionFlow = new EtrCashPositionFlow();
				BeanUtil.copyPropertiesIgnoreNull(etrCashPositionFlow,ptlCashPositionFlow);
				etrCashPositionFlow.setSeqno(IDGenerator.generate());
				etrCashPositionFlow.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
				insertEtrCashPositionFlow(etrCashPositionFlow);
			});
		}

		return true;
	}

	/**
	 * 入库委托现金流水信息
	 * @param etrCashPositionFlow
	 */
	public void insertEtrCashPositionFlow (EtrCashPositionFlow etrCashPositionFlow){
		etrCashPositionFlowMapper.insertSelective(etrCashPositionFlow);
		if(getSysDate().compareTo(etrCashPositionFlow.getCdate()) != 0){//非T+0流水不需要更新余额
			return;
		}
		EtrCashPosition etrCashPosition = commonPtlPositionMapper.selectEtrCashPosition(etrCashPositionFlow);
		if(StringUtil.isNotEmpty(etrCashPosition)){
			etrCashPosition.setAmount(ArithmeticUtil.doAdd(etrCashPosition.getAmount(),etrCashPositionFlow.getAmount()));
//			etrCashPositionMapper.updateByPrimaryKeySelective(etrCashPosition);
			commonPtlPositionMapper.updateEtrCashPosition(etrCashPosition);
		}else{
			etrCashPosition = new EtrCashPosition();
			BeanUtil.copyPropertiesIgnoreNull(etrCashPosition, etrCashPositionFlow);
			etrCashPositionMapper.insertSelective(etrCashPosition);
		}
	}
	
	/**
	 * 入库委托持仓流水信息
	 * @param etrSecPositionFlow
	 */
	public void insertEtrSecPositionFlow (EtrSecPositionFlow etrSecPositionFlow){
		etrSecPositionFlowMapper.insertSelective(etrSecPositionFlow);
		if(getSysDate().compareTo(etrSecPositionFlow.getCdate()) != 0){//非T+0流水不需要更新余额
			return;
		}
		EtrSecPosition etrSecPosition = commonPtlPositionMapper.selectEtrSecPosition(etrSecPositionFlow);
		if(StringUtil.isNotEmpty(etrSecPosition)){
			etrSecPosition.setAmount(ArithmeticUtil.doAdd(etrSecPosition.getAmount(),etrSecPositionFlow.getAmount()));
			commonPtlPositionMapper.updateEtrSecPosition(etrSecPosition);
		}else{
			etrSecPosition = new EtrSecPosition();
			BeanUtil.copyPropertiesIgnoreNull(etrSecPosition, etrSecPositionFlow);
			etrSecPositionMapper.insertSelective(etrSecPosition);
		}
	}
	
	/**
	 * 入库交易现金流水信息
	 * @param ptlCashPositionFlow
	 */
	public void insertPtlCashPositionFlow (PtlCashPositionFlow ptlCashPositionFlow){
		ptlCashPositionFlowMapper.insertSelective(ptlCashPositionFlow);
		if(getSysDate().compareTo(ptlCashPositionFlow.getCdate()) != 0){//非T+0流水不需要更新余额
			return;
		}
		PtlCashPosition ptlCashPosition = commonPtlPositionMapper.selectPtlCashPosition(ptlCashPositionFlow);
		if(StringUtil.isNotEmpty(ptlCashPosition)){
			ptlCashPosition.setAmount(ArithmeticUtil.doAdd(ptlCashPosition.getAmount(),ptlCashPositionFlow.getAmount()));
			commonPtlPositionMapper.updatePtlCashPosition(ptlCashPosition);
		}else{
			ptlCashPosition = new PtlCashPosition();
			BeanUtil.copyPropertiesIgnoreNull(ptlCashPosition, ptlCashPositionFlow);
			ptlCashPositionMapper.insertSelective(ptlCashPosition);
		}

	}
	
	/**
	 * 入库交易持仓流水信息
	 * @param ptlSecPositionFlow
	 */
	public void insertPtlSecPositionFlow (PtlSecPositionFlow ptlSecPositionFlow){
		ptlSecPositionFlowMapper.insertSelective(ptlSecPositionFlow);
		if(getSysDate().compareTo(ptlSecPositionFlow.getCdate()) != 0){//非T+0流水不需要更新余额
			return;
		}
		PtlSecPosition ptlSecPosition = commonPtlPositionMapper.selectPtlSecPosition(ptlSecPositionFlow);
		if(StringUtil.isNotEmpty(ptlSecPosition)){
			ptlSecPosition.setAmount(ArithmeticUtil.doAdd(ptlSecPosition.getAmount(),ptlSecPositionFlow.getAmount()));
			commonPtlPositionMapper.updatePtlSecPosition(ptlSecPosition);
		}else{
			ptlSecPosition = new PtlSecPosition();
			BeanUtil.copyPropertiesIgnoreNull(ptlSecPosition, ptlSecPositionFlow);
			ptlSecPositionMapper.insertSelective(ptlSecPosition);
		}

	}
	
	
	/**
	 * 获取平台日
	 * @return
	 */
	public Date getSysDate(){
		return commonPtlPositionMapper.getSysDate();
	}
	
	
	private BigDecimal object2BigDecimal(Object object){
		if(!StringUtil.isNotEmpty(object)){
			return BigDecimal.ZERO;
		}
		if(object instanceof String){
			return new BigDecimal((String)object);
		}
		return BigDecimal.ZERO;
	}
	
	private Date object2Date(Object object){
		if(!StringUtil.isNotEmpty(object)){
			return null;
		}
		if(object instanceof Long){
			new Date((Long)object);
		}
		if(object instanceof String){
			return DateUtil.parse((String)object);
		}
		return null;
	}

	public String getPreTrdCounterId(String tradeId) {
		return commonPtlPositionMapper.getPreTrdCounterId(tradeId);
	}

	public String getFinTradeProduct(String tradeId) {
		return commonPtlPositionMapper.getFinTradeProduct(tradeId);
	}
}
