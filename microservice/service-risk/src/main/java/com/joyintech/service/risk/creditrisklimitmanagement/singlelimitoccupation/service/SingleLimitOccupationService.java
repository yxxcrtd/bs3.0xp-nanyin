/*
 * 文件名：CeAssetGroupManagementService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditrisklimitmanagement.singlelimitoccupation.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditrisklimitmanagement.singlelimitoccupation.dao.SingleLimitOccupationMapper;
import com.joyintech.service.risk.creditrisklimitmanagement.singlelimitoccupation.dto.SingleLimitOccupationDto;
import com.joyintech.tams.common.base.dao.CreditRuleInfoMapper;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.entity.CreditRuleInfo;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinProductTypeKey;

/**
 * 
 * 单笔资产额度校验规则
 * @author chengzhong
 * @version 1.0 2020年02月26日
 * @see SingleLimitOccupationDto
 * @since 1.0
 */
@Service
public class SingleLimitOccupationService extends BaseService {
    
    /**
     * SingleLimitOccupationMapper
     */
    @Autowired
    private SingleLimitOccupationMapper singleLimitOccupationMapper;
    
    /**
     * CreditRuleInfoMapper
     */
    @Autowired
    private CreditRuleInfoMapper creditRuleInfoMapper;
    
    /**
     * finProductTypeMapper
     */
    @Autowired
    private FinProductTypeMapper finProductTypeMapper;
 
    /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
    @SuppressWarnings("unchecked")
	public Page<SelectOptionDto> getLimitLabel(Map<String, Object> map, int page, int length) {
    	return this.queryForPageForSelectReturn(page, length,()->singleLimitOccupationMapper.getLimitLabel(map));
    }
    
    /**
     * <一句话功能简述>
     * 查询系列明细导出数据
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
	public Page<SingleLimitOccupationDto> selectForPage(int page, int length,
                                                            Map<String, Object> param) throws Exception {
        return super.queryForPage(page, length, () -> singleLimitOccupationMapper.selectForPage(param));
    }

    /**
     * 资产代码：下拉框
     *
     * @param
     * @return 资产代码：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public Page<SelectOptionDto> getFinprodMarketIds(int page, int length, Map<String, Object> param) throws Exception {
        return this.queryForPage(page, length,
                () -> singleLimitOccupationMapper.getFinprodMarketIds(param));
    }
	
    /**
     * 插入
     * @param entity
     * @return
     * @since 1.0
     */
    public void insert(CreditRuleInfo entity) {
    	creditRuleInfoMapper.insert(entity);
    }
    
    /**
     * 插入
     * @param entity
     * @return
     * @since 1.0
     */
    public void update(CreditRuleInfo entity) {
    	creditRuleInfoMapper.updateByPrimaryKey(entity);
    }
    
    /**
     * 插入
     * @param entity
     * @return
     * @since 1.0
     */
    public void delete(CreditRuleInfo entity) {
    	creditRuleInfoMapper.deleteByPrimaryKey(entity.getCreditRuleId());
    }  
    
	/**
	 * 获取资产类型
	 * @param finprodId
	 * @return
	 */
	public FinProductType getFinProductType(String finprodId){
		FinProductTypeKey key = new FinProductTypeKey();
		key.setFinprodId(finprodId);
		key.setBranch(0L);
		return finProductTypeMapper.selectByPrimaryKey(key);
	}
	
	/**
	 * 获取单资产规则信息
	 * @param creditRuleId
	 * @return
	 */
	public SingleLimitOccupationDto getBussinessEntityByIndId(Map<String, Object> map) {
		return singleLimitOccupationMapper.getBussinessEntityByIndId(map);
	}
	
}
