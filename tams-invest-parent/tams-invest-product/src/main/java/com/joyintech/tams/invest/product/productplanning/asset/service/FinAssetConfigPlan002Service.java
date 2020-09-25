/*
 * 文件名：FinAssetConfigPlan001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouyongjun
 * 修改时间：2019年12月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productplanning.asset.service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.AssetConfigDetailMapper;
import com.joyintech.tams.common.base.dao.AssetConfigMonDetailMapper;
import com.joyintech.tams.common.base.dao.AssetConfigPlanMapper;
import com.joyintech.tams.common.base.entity.AssetConfigDetail;
import com.joyintech.tams.common.base.entity.AssetConfigDetailKey;
import com.joyintech.tams.common.base.entity.AssetConfigMonDetail;
import com.joyintech.tams.common.base.entity.AssetConfigMonDetailKey;
import com.joyintech.tams.common.base.entity.AssetConfigPlan;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productplanning.asset.dto.FinAssetClassDto;
import com.joyintech.tams.invest.product.productplanning.asset.dto.FinAssetConfigPlan001Dto;
import com.joyintech.tams.invest.product.productplanning.asset.dto.FinTacticConfigOptDto;
import com.joyintech.tams.invest.product.productplanning.common.dto.HoldPositionDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 资产配置
 * 
 * @author zhouyongjun
 * @version 1.0 2019年12月26日
 * @see FinAssetConfigPlan002Service
 * @since 1.0
 */
@Service
public class FinAssetConfigPlan002Service extends BaseService<FinAssetConfigPlan001Dto> 
	implements BaseProcessService<FinAssetConfigPlan001Dto> {

    /**
     * 得到资产配置计划列表
     *
     * @param Map<String, Object> pageParam
     * @return Page<FinAssetConfigPlan001Dto>结果
     * @see
     * @since 1.0
     */
    public Page<FinAssetConfigPlan001Dto> getFinAssetConfigPlanList(Map<String, Object> pageParam)throws Exception{
    	List<FinAssetConfigPlan001Dto> list = new ArrayList<FinAssetConfigPlan001Dto>();
    	Page<FinAssetConfigPlan001Dto> page = new Page<>();
    	
    	Date configMonth = null;
    	if(!StringUtil.isNotEmpty(pageParam.get("configMonth2"))){
    		page.setRoot(list);
            page.setRecords(list.size());
        	return page;
    	}
		//得到年末对应的日期
		configMonth = new SimpleDateFormat("yyyy-MM").parse((String)pageParam.get("configMonth2"));
		configMonth = DateUtil.getLastDay(configMonth);
    	BigDecimal tagetScale = new BigDecimal((String)pageParam.get("tagetScale"));
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("configMonth", configMonth);
    	param.put("endmonth", DateUtil.format(configMonth));
    	//资产配置计划列表
    	List<FinTacticConfigOptDto> optList = restTemplate.postForList(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinAssetConfigPlan/queryTactics",param,FinTacticConfigOptDto.class);
    	
    	//调用【资产分类查询】
    	List<FinAssetClassDto> assetClassList = restTemplate.postForList(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinAssetConfigPlan/queryAssetClassList",param,FinAssetClassDto.class);
    	String assetConfigCode = null;
    	if(StringUtil.isNotEmpty(pageParam.get("assetConfigCode"))){
    		assetConfigCode = (String)pageParam.get("assetConfigCode");
    	}
    	for (FinTacticConfigOptDto dto : optList) {
    		FinAssetConfigPlan001Dto assetDto = new FinAssetConfigPlan001Dto();
    		assetDto.setAssetConfigCode(assetConfigCode);
    		String assetType = dto.getAssetType();
    		
    		//创建资产分类对象
    		List<String> type9 = new ArrayList<String>();
        	List<String> type10 = new ArrayList<String>();
        	List<String> type11 = new ArrayList<String>();
        	List<String> type12 = new ArrayList<String>();
        	//循环资产大类
        	for (FinAssetClassDto assetClass : assetClassList) {
    			if(StringUtil.equals(assetClass.getAssetType(),assetType)) {
    				type9.add(assetClass.getType2());
    				type10.add(assetClass.getType3());
    				type11.add(assetClass.getType4());
    				type12.add(assetClass.getType5());
    			}
    		}
        	type9 = type9.stream().distinct().collect(Collectors.toList());
        	type10 = type10.stream().distinct().collect(Collectors.toList());
        	type11 = type11.stream().distinct().collect(Collectors.toList());
        	type12 = type12.stream().distinct().collect(Collectors.toList());
        	
    		BigDecimal corRatio = dto.getCorRatio();
    		assetDto.setAssetType(assetType);
    		assetDto.setTotalRatio(corRatio);
    		assetDto.setTotalAmt(tagetScale!=null && !BigDecimal.ZERO.equals(tagetScale)? tagetScale.multiply(corRatio) : null);
    		
	    	String benchDate = (String)pageParam.get("benchDate");
	    	Map<String, Object> pomap = new HashMap<String, Object>();
	    	pomap.put("benchDate", benchDate);
	    	//调用【持仓查询】
	    	List<HoldPositionDto> plist = restTemplate.postForList(
	                ServiceApplication.PRODUCT_SERVICE_NAME + "/PlanningCommon/queryHoldPosition",pomap,HoldPositionDto.class);
	    	BigDecimal curPerAmt = BigDecimal.ZERO;
	    	//LOOP 估值列表
	    	for (HoldPositionDto pdto : plist) {
	    		if(type9.contains(pdto.getType9())&&type10.contains(pdto.getType10())&&type11.contains(pdto.getType11())&&type12.contains(pdto.getType12())) {
	    			if(StringUtil.equals("02", assetType)||StringUtil.equals("03", assetType))
	    				curPerAmt = curPerAmt.add(pdto.getShartAmt()==null?BigDecimal.ZERO:pdto.getShartAmt().multiply(new BigDecimal(100)));
	    			else
	    				curPerAmt = curPerAmt.add(pdto.getShartAmt()==null?BigDecimal.ZERO:pdto.getShartAmt());
	    		}
	    	}
    		assetDto.setCurPerAmt(curPerAmt);
	    	Map<String, Object> param2 = new HashMap<String, Object>();
	    	param2.put("holdingType", DictCodeList.持仓类型_实际.getValue());
	    	param2.put("cdate", benchDate);
	    	param2.put("type9", type9);
	    	param2.put("type10", type10);
	    	param2.put("type11", type11);
	    	param2.put("type12", type12);
	    	param2.put("benchDate", benchDate);
	    	Date benchDateEnd = getLastDay(DateUtil.parse(benchDate));
	    	param2.put("benchDateEnd", DateUtil.format(benchDateEnd));
	    	List<AssetConfigMonDetail> delList = new ArrayList<AssetConfigMonDetail>();
	    	if(!type9.isEmpty()&&!type10.isEmpty()&&!type11.isEmpty()&&!type12.isEmpty())
	    	delList = restTemplate.postForList(
	                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinAssetConfigPlan/getCurEndAmt",param2, AssetConfigMonDetail.class);
	    	int month = DateUtil.getMonth(benchDate);
	    	int vmonth = DateUtil.getMonth(DateUtil.format(configMonth));
	    	//调用【资产还本到期金额】
	    	while (month <= 12 ) {
	    		if(delList.isEmpty()) {
	    			Method method = assetDto.getClass().getDeclaredMethod("setCurEndAmt" + month, BigDecimal.class);
	                method.invoke(assetDto, BigDecimal.ZERO);
	                BigDecimal curInvAmt = ArithmeticUtil.divide(assetDto.getTotalAmt().subtract(assetDto.getCurPerAmt())
	    					, new BigDecimal(12-vmonth), 14);
	    			method = assetDto.getClass().getDeclaredMethod("setCurInvAmt" + month, BigDecimal.class);
	                method.invoke(assetDto, curInvAmt);
	    		}	    			
	    		//对月份进行循环计算
		    	for (AssetConfigMonDetail del : delList) {
		    		String mon = del.getAssetMonth();
		    		if(StringUtil.isNotBlank(mon)) {
		    			BigDecimal curEndAmt = BigDecimal.ZERO;
		    			Calendar calendar = Calendar.getInstance();
		    	        calendar.setTime(DateUtils.parseDate(mon, "yyyyMM"));
			    		if(month == calendar.get(Calendar.MONTH) + 1) 
		    			curEndAmt = del.getCurEndAmt();
		    			Method method = assetDto.getClass().getDeclaredMethod("setCurEndAmt" + month, BigDecimal.class);
		                method.invoke(assetDto, curEndAmt);
			    		if(StringUtil.equals(vmonth, 12)) {
			    			assetDto.setCurInvAmt12(null);
			    		}
			    		else {
			    			BigDecimal curInvAmt = ArithmeticUtil.divide(assetDto.getTotalAmt().subtract(assetDto.getCurPerAmt())
			    					, new BigDecimal(12-vmonth), 14);
			    			method = assetDto.getClass().getDeclaredMethod("setCurInvAmt" + month, BigDecimal.class);
			                method.invoke(assetDto, curInvAmt.add(curEndAmt));
			    		}
			    		
		    		}
		    	}
		    	month++;
	    	}
	    	list.add(assetDto);
    	}
    	page.setRoot(list);
        page.setRecords(list.size());
    	return page;
    }
    
    /**
     * 得到年末日期
     *
     * @param Date todayDate
     * @return Date
     * @see
     * @since 1.0
     */
    private Date getLastDay(Date todayDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(todayDate);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        return calendar.getTime();
    }

    /**
     * 得到资产配置计划
     *
     * @param String assetConfigCode
     * @return FinAssetConfigPlan001Dto
     * @see
     * @since 1.0
     */
    public FinAssetConfigPlan001Dto getAssetConfigPlan(String assetConfigCode) {
    	AssetConfigPlan dto = assetConfigPlanMapper.selectByPrimaryKey(assetConfigCode);
    	FinAssetConfigPlan001Dto entity = new FinAssetConfigPlan001Dto();
    	if(dto!=null) {
        	entity.setAssetConfigCode(assetConfigCode);
        	entity.setBenchDate(dto.getBenchDate());
        	entity.setConfigMonth(dto.getConfigMonth());
        	entity.setConfigMonth2(new SimpleDateFormat("yyyy-MM").format(dto.getConfigMonth()));
        	entity.setGenDate(dto.getGenDate());
        	entity.setTagetScale(dto.getTagetScale());
        }
        return entity;
    }
    
    @Autowired
    private AssetConfigPlanMapper assetConfigPlanMapper;
    @Autowired
    private AssetConfigDetailMapper assetConfigDetailMapper;
    @Autowired
    private AssetConfigMonDetailMapper assetConfigMonDetailMapper;
    
    /**
     * 执行保存逻辑
     *
     * @param FinAssetConfigPlan001Dto entity
     * @param OperationType type
     * @return
     * @see
     * @since 1.0
     */
    @Override
    @Transactional
    public void doComplete(FinAssetConfigPlan001Dto entity, OperationType type) {
    	entity.setOperateType(type.toString());
    	//新建逻辑
    	if (StringUtil.equals(type.toString(), "NEW")) {
    		String assetConfigCode = entity.getAssetConfigCode();
    		AssetConfigPlan assetConfigPlan = new AssetConfigPlan();
    		assetConfigPlan.setAssetConfigCode(assetConfigCode);
    		assetConfigPlan.setGenDate(entity.getGenDate());
    		assetConfigPlan.setBenchDate(entity.getBenchDate());
    		assetConfigPlan.setConfigMonth(entity.getConfigMonth());
    		assetConfigPlan.setTagetScale(entity.getTagetScale());
    		assetConfigPlanMapper.insert(assetConfigPlan);
    		List<FinAssetConfigPlan001Dto> list = entity.getSelectedItem();
    		for (FinAssetConfigPlan001Dto dto : list) {
    			AssetConfigDetail asetConfigDetail = new AssetConfigDetail();
    			asetConfigDetail.setAssetConfigCode(assetConfigCode);
    			asetConfigDetail.setAssetType(dto.getAssetType());
    			asetConfigDetail.setTotalRatio(dto.getTotalRatio());
    			asetConfigDetail.setTotalAmt(dto.getTotalAmt());
    			asetConfigDetail.setCurPerAmt(dto.getCurPerAmt());
        		assetConfigDetailMapper.insert(asetConfigDetail);
        		int month = DateUtil.getMonth(DateUtil.format(entity.getBenchDate()));
    	    	while (month <= 12 ) {
	        		AssetConfigMonDetail assetConfigMonDetail= new AssetConfigMonDetail();
	        		assetConfigMonDetail.setAssetConfigCode(assetConfigCode);
	        		assetConfigMonDetail.setAssetType(dto.getAssetType());
	        		assetConfigMonDetail.setAssetMonth(String.valueOf(month));
	        		Method method;
					try {
						method = dto.getClass().getDeclaredMethod("getCurEndAmt" + month);
		        		assetConfigMonDetail.setCurEndAmt((BigDecimal)method.invoke(dto));
						method = dto.getClass().getDeclaredMethod("getCurInvAmt" + month);
		        		assetConfigMonDetail.setCurInvAmt((BigDecimal)method.invoke(dto));
					} catch (Exception e) {
						e.printStackTrace();
					} 
	        		assetConfigMonDetailMapper.insert(assetConfigMonDetail);
	        		month++;
        		}
    		}
    	}
    	else {
    		//修改逻辑
    		String assetConfigCode = entity.getAssetConfigCode();
			AssetConfigPlan plan = assetConfigPlanMapper.selectByPrimaryKey(assetConfigCode);
			Map<String, Object> param = new HashMap<String, Object>();
	    	param.put("assetConfigCode", assetConfigCode);
	    	List<AssetConfigDetail> details = restTemplate.postForList(
	                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinAssetConfigPlan/queryAssetConfigDetails",param, AssetConfigDetail.class);
			for (AssetConfigDetail dto : details) {
    			AssetConfigDetailKey key1 = new AssetConfigDetailKey();
    			key1.setAssetConfigCode(assetConfigCode);
    			key1.setAssetType(dto.getAssetType());
    			assetConfigDetailMapper.deleteByPrimaryKey(key1);   			
    			int month = DateUtil.getMonth(DateUtil.format(plan.getBenchDate()));
    	    	while (month <= 12 ) {
	    			AssetConfigMonDetailKey key2 = new AssetConfigMonDetailKey();
	    			key2.setAssetConfigCode(assetConfigCode);
	    			key2.setAssetType(dto.getAssetType());
	    			key2.setAssetMonth(String.valueOf(month));
	    			assetConfigMonDetailMapper.deleteByPrimaryKey(key2);
	    			month++;
    			}
    		}
    		AssetConfigPlan assetConfigPlan = new AssetConfigPlan();
    		assetConfigPlan.setAssetConfigCode(assetConfigCode);
    		assetConfigPlan.setGenDate(entity.getGenDate());
    		assetConfigPlan.setBenchDate(entity.getBenchDate());
    		assetConfigPlan.setConfigMonth(entity.getConfigMonth());
    		assetConfigPlan.setTagetScale(entity.getTagetScale());
    		assetConfigPlanMapper.updateByPrimaryKey(assetConfigPlan);
    		List<FinAssetConfigPlan001Dto> list = entity.getSelectedItem();
    		for (FinAssetConfigPlan001Dto dto : list) {
    			AssetConfigDetail asetConfigDetail = new AssetConfigDetail();
    			asetConfigDetail.setAssetConfigCode(assetConfigCode);
    			asetConfigDetail.setAssetType(dto.getAssetType());
    			asetConfigDetail.setTotalRatio(dto.getTotalRatio());
    			asetConfigDetail.setTotalAmt(dto.getTotalAmt());
    			asetConfigDetail.setCurPerAmt(dto.getCurPerAmt());
        		assetConfigDetailMapper.insert(asetConfigDetail);
        		int month = DateUtil.getMonth(DateUtil.format(entity.getBenchDate()));
    	    	while (month <= 12 ) {
            		AssetConfigMonDetail assetConfigMonDetail= new AssetConfigMonDetail();
            		assetConfigMonDetail.setAssetConfigCode(assetConfigCode);
            		assetConfigMonDetail.setAssetType(dto.getAssetType());
            		assetConfigMonDetail.setAssetMonth(String.valueOf(month));
            		Method method;
    				try {
    					method = dto.getClass().getDeclaredMethod("getCurEndAmt" + month);
    	        		assetConfigMonDetail.setCurEndAmt((BigDecimal)method.invoke(dto));
    					method = dto.getClass().getDeclaredMethod("getCurInvAmt" + month);
    	        		assetConfigMonDetail.setCurInvAmt((BigDecimal)method.invoke(dto));
    				} catch (Exception e) {
    					e.printStackTrace();
    				} 
            		assetConfigMonDetailMapper.insert(assetConfigMonDetail);
            		month++;
        		}
    		}
    		
    		
    	}
    }
    
    /*private void insertRecords(FinAssetConfigPlan001Dto entity, String assetConfigCode,Date benchDate) {
    	AssetConfigPlan assetConfigPlan = new AssetConfigPlan();
		assetConfigPlan.setAssetConfigCode(assetConfigCode);
		assetConfigPlan.setGenDate(entity.getGenDate());
		assetConfigPlan.setBenchDate(entity.getBenchDate());
		assetConfigPlan.setConfigMonth(entity.getConfigMonth());
		assetConfigPlan.setTagetScale(entity.getTagetScale());
		assetConfigPlanMapper.insert(assetConfigPlan);
    	List<FinAssetConfigPlan001Dto> list = entity.getSelectedItem();
		for (FinAssetConfigPlan001Dto dto : list) {
			AssetConfigDetail asetConfigDetail = new AssetConfigDetail();
			asetConfigDetail.setAssetConfigCode(assetConfigCode);
			asetConfigDetail.setAssetType(entity.getAssetType());
			asetConfigDetail.setTotalRatio(dto.getTotalRatio());
			asetConfigDetail.setTotalAmt(dto.getTotalAmt());
			asetConfigDetail.setCurPerAmt(dto.getCurPerAmt());
    		assetConfigDetailMapper.insert(asetConfigDetail);
    		int month = DateUtil.getMonth(DateUtil.format(benchDate));
	    	while (month <= 12 ) {
        		AssetConfigMonDetail assetConfigMonDetail= new AssetConfigMonDetail();
        		assetConfigMonDetail.setAssetConfigCode(assetConfigCode);
        		assetConfigMonDetail.setAssetType(entity.getAssetType());
        		assetConfigMonDetail.setAssetMonth(String.valueOf(month));
        		Method method;
				try {
					method = entity.getClass().getDeclaredMethod("getCurEndAmt" + month);
	        		assetConfigMonDetail.setCurEndAmt((BigDecimal)method.invoke(entity));
					method = entity.getClass().getDeclaredMethod("getCurInvAmt" + month);
	        		assetConfigMonDetail.setCurInvAmt((BigDecimal)method.invoke(entity));
				} catch (Exception e) {
					e.printStackTrace();
				} 
        		assetConfigMonDetailMapper.insert(assetConfigMonDetail);
    		}
		}
    }*/
    
    /**
     * 查询资产配置计划
     *
     * @param Map<String, Object> map
     * @return Page结果
     * @see
     * @since 1.0
     */
    public List<AssetConfigPlan> queryAssetConfigPlan(Map<String, Object> map) {
    	List<AssetConfigPlan> list = restTemplate.postForList(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinAssetConfigPlan/queryAssetConfigPlan",map,AssetConfigPlan.class);
        return list;
    }
}
