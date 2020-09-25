package com.joyintech.service.product.prdrisklevel.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.prdrisklevel.dto.Page2Dto;
import com.joyintech.service.product.prdrisklevel.dto.PrdRiskLevel;
import com.joyintech.service.product.prdrisklevel.dto.PrdRiskLevelDto;
import com.joyintech.tams.common.base.entity.MockTradeDeal;

@Repository
public interface PrdRiskLevelDtoMapper {
	
	/**
     * 产品设计编码下拉框
     */
    List<SelectOptionDto> productDesignCode(Map<String, Object> paramMap);
    
    /**
     *  风险等级评定查询 
     */
    List<PrdRiskLevelDto> riskLevelQuery(Map<String, Object> paramMap);
    
    /**
     * 模拟资产交易最新查询
     */
    List<MockTradeDeal> simulateLatestTradeQuery(Map<String, Object> paramMap);
    
    /**
     * 新增 产品风险等级评定
     */
    int insertPrdRiskLevel(PrdRiskLevel prdRiskLevel);
    
    /**
     * 删除 产品风险等级评定
     */
    int deletePrdRiskLevel(String seqNo);
    
    /**
     * 修改 产品风险等级评定
     */
    int updatePrdRiskLevel(PrdRiskLevel prdRiskLevel);
    
    /**
     * 主键查询一条记录
     */
    PrdRiskLevelDto selectByPk(String pk);
    
    /**
     * page2展示
     */
    List<Page2Dto> selectPageParam(Map<String,Object> paraMap);
    
    /**
     * <!-- 产品设计系列名称模糊查询 -->
     */
    List<SelectOptionDto> selectFinProductName(Map<String,Object> paraMap);
    
}