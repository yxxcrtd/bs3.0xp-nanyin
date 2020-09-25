/*
 * 文件名：FlowForecastService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2019年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.prdrisklevel.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.prdrisklevel.dao.PrdRiskLevelDtoMapper;
import com.joyintech.service.product.prdrisklevel.dto.Page2Dto;
import com.joyintech.service.product.prdrisklevel.dto.PrdRiskLevel;
import com.joyintech.service.product.prdrisklevel.dto.PrdRiskLevelDto;
import com.joyintech.tams.common.base.entity.MockTradeDeal;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * yangxiaofeng
 * 2019年10月28日
 * 新发产品风险等级评定查询
 */
@Service("prdRiskLevelService")
@Transactional(rollbackFor = Exception.class)
public class PrdRiskLevelService extends BaseService<PrdRiskLevel> {

	@Autowired
	private PrdRiskLevelDtoMapper prdRiskLevelMapper;

    /**
     * 产品代码下拉框
     * @param paramMap 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    public Page<SelectOptionDto> productDesignCode(int page, int length, Map<String, Object> paramMap) {
        paramMap.put("dataStatus", DictCodeListExt1.有效状态_有效.getValue());
        return this.queryForPageForSelectReturn(page, length, ()-> prdRiskLevelMapper.productDesignCode(paramMap));
    }
    
    /**
     * 风险等级评定查询
     */
    public Page<PrdRiskLevelDto> selectForPagePrdRiskLevel(int page, int length, Map<String, Object> paramMap) {
        return this.queryForPageForSelectReturn(page, length, ()-> prdRiskLevelMapper.riskLevelQuery(paramMap));
    }
    
    /**
     * 模拟资产交易查询
     */
    public Page<MockTradeDeal> selectForPageMockTradeDeal(int page, int length, Map<String, Object> paramMap) {
        return this.queryForPageForSelectReturn(page, length, ()-> prdRiskLevelMapper.simulateLatestTradeQuery(paramMap));
    }
    
	/**
	 * 新增 产品风险等级评定
	 */
	public int insertPrdRiskLevel(PrdRiskLevelDto entity) throws IllegalAccessException, InvocationTargetException {

		PrdRiskLevel prl = new PrdRiskLevel();
		prl.setSeqNo(IDGenerator.generate()); // 流水号
		prl.setGenerateTime(entity.getGenerateTime()); // 生成日期
		prl.setFinProductCode(entity.getFinProductCode()); // 金融产品代码
		prl.setBranch(0L); // 分支序号
		prl.setRiskLevel(entity.getRiskLevel()); // 风险等级
		prl.setAjustRiskLevel(entity.getAjustRiskLevel()); // 调整后风险等级
		prl.setCreateUser("system");
		prl.setCreateDept(entity.getCreateDept());
		prl.setCreateTime(new Date());
		prl.setUpdateUser("system");
		prl.setUpdateTime(new Date());
		
		return prdRiskLevelMapper.insertPrdRiskLevel(prl);
	}
	
	/**
	 * 修改 产品风险等级评定
	 */
	public int updatePrdRiskLevel(PrdRiskLevelDto entity) throws IllegalAccessException, InvocationTargetException {

		String pk = entity.getSeqNo();
		
		if(StringUtil.isNotEmpty(pk)) { // 修改
			
			PrdRiskLevel prl = new PrdRiskLevel();
			prl.setSeqNo(pk); // 流水号
			prl.setFinProductCode(entity.getFinProductCode()); // 金融产品代码
			prl.setRiskLevel(entity.getRiskLevel()); // 风险等级
			prl.setAjustRiskLevel(entity.getAjustRiskLevel()); // 调整后风险等级
			prl.setCreateUser("system");
			prl.setCreateDept(entity.getCreateDept());
			prl.setCreateTime(new Date());
			prl.setUpdateUser("system");
			prl.setUpdateTime(new Date());
			
			return prdRiskLevelMapper.updatePrdRiskLevel(prl);
			
		}else { // 新增
			
			PrdRiskLevel prl = new PrdRiskLevel();
			prl.setSeqNo(IDGenerator.generate()); // 流水号
			prl.setGenerateTime(new Date()); // 生成日期
			prl.setFinProductCode(entity.getFinProductCode()); // 金融产品代码
			prl.setBranch(0L); // 分支序号
			prl.setRiskLevel(entity.getRiskLevel()); // 风险等级
			prl.setAjustRiskLevel(entity.getAjustRiskLevel()); // 调整后风险等级
			prl.setCreateUser("system");
			prl.setCreateDept(entity.getCreateDept());
			prl.setCreateTime(new Date());
			prl.setUpdateUser("system");
			prl.setUpdateTime(new Date());
			
			return prdRiskLevelMapper.insertPrdRiskLevel(prl);
		}
		
		
	}
	
	/**
	 * 删除 产品风险等级评定
	 */
	public int deletePrdRiskLevel(PrdRiskLevelDto entity) throws IllegalAccessException, InvocationTargetException {

		return prdRiskLevelMapper.deletePrdRiskLevel(entity.getSeqNo());
	}
    
    /**
     * 加权平均计算风险等级
     */
    public String calcRiskGrade(Map<String,Object> paramMap) {
    	
    	BigDecimal valueRisk = null; // 风险值
    	BigDecimal total = null; // 总金额
    	BigDecimal weightTotal = null; // 权重汇总
    	BigDecimal levelRisk = null; // 风险等级
    	
    	List<MockTradeDeal> MockTradeDealList = prdRiskLevelMapper.simulateLatestTradeQuery(paramMap);
    	
    	if(StringUtil.isEmptyList(MockTradeDealList)) {
    		return "";
    	}else if(!StringUtil.isNotEmpty(MockTradeDealList.get(0))) {
    		return "";
    	}

    	for(MockTradeDeal mockTradeDeal : MockTradeDealList) {
    		if (StringUtil.isNotEmpty(mockTradeDeal)) {
				if (DictCodeListExt1.资产风险等级_01.getValue().equals(mockTradeDeal.getAssetRiskLevel())) {
					valueRisk = new BigDecimal("1");
				} else if (DictCodeListExt1.资产风险等级_02.getValue().equals(mockTradeDeal.getAssetRiskLevel())) {
					valueRisk = new BigDecimal("2");
				} else if (DictCodeListExt1.资产风险等级_03.getValue().equals(mockTradeDeal.getAssetRiskLevel())) {
					valueRisk = new BigDecimal("3");
				} else if (DictCodeListExt1.资产风险等级_04.getValue().equals(mockTradeDeal.getAssetRiskLevel())) {
					valueRisk = new BigDecimal("4");
				} else if (DictCodeListExt1.资产风险等级_05.getValue().equals(mockTradeDeal.getAssetRiskLevel())) {
					valueRisk = new BigDecimal("5");
				}

				total = ArithmeticUtil.doAdd(total, mockTradeDeal.getMockAmt());
				weightTotal = ArithmeticUtil.doAdd(weightTotal, ArithmeticUtil.multiply(valueRisk, mockTradeDeal.getMockAmt()));
			}
    	}
    	
    	levelRisk = ArithmeticUtil.divide(weightTotal, total, 14);
    	
    	// 计算新的风险等级
    	return riskRating(levelRisk);
    }
    
    /**
     * 风险等级判定
     */
    public static String riskRating(BigDecimal levelRisk) {
    	
    	String levelRiskValue = ""; // 风险等级数值
    	
    	if(levelRisk.compareTo(new BigDecimal("0"))>-1 && levelRisk.compareTo(new BigDecimal("1.25"))<1) {
    		levelRiskValue = DictCodeListExt1.资产风险等级_01.getValue();
    	}else if(levelRisk.compareTo(new BigDecimal("1.25"))==1 && levelRisk.compareTo(new BigDecimal("2.25"))<1) {
    		levelRiskValue = DictCodeListExt1.资产风险等级_02.getValue();
    	}else if(levelRisk.compareTo(new BigDecimal("2.25"))==1 && levelRisk.compareTo(new BigDecimal("3.25"))<1) {
    		levelRiskValue = DictCodeListExt1.资产风险等级_03.getValue();
    	}else if(levelRisk.compareTo(new BigDecimal("3.25"))==1 && levelRisk.compareTo(new BigDecimal("4.25"))<1) {
    		levelRiskValue = DictCodeListExt1.资产风险等级_04.getValue();
    	}else if(levelRisk.compareTo(new BigDecimal("4.25"))==1) {
    		levelRiskValue = DictCodeListExt1.资产风险等级_05.getValue();
    	}
    	
    	return levelRiskValue;
    }
    
    /**
     * 主键查询一条记录
     */
    public PrdRiskLevelDto selectByPk(String pk) {
    	return prdRiskLevelMapper.selectByPk(pk);
    }
    
    public Page2Dto selectPageParam(String prdDesignId){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("prdDesignId", prdDesignId);
    	
    	List<Page2Dto> list = prdRiskLevelMapper.selectPageParam(map);
    	
    	if(null!=list && list.size()>0) {
    		return list.get(0);
    	}else {
    		return null;
    	}
    }
    
    /**
     *	产品设计系列名称模糊查询
     */
    public List<SelectOptionDto> selectFinProductName(Map<String, Object> paramMap) {
    	paramMap.put("dataStatus", DictCodeListExt1.有效状态_有效.getValue());
    	return prdRiskLevelMapper.selectFinProductName(paramMap);
    }
}
