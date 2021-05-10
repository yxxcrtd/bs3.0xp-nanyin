/*
 * 文件名：ValTrdAccountmfConfig001Service.java 
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：tanxuan
 * 修改时间：2021年3月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.valuationparametermanage.valtrdaccountmfconfig.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.valuationparametermanage.valtrdaccountmfconfig.dao.ValTrdAccountmfConfig001Mapper;
import com.joyintech.service.valuation.valuationparametermanage.valtrdaccountmfconfig.dto.ValTrdAccountmfConfig001Dto;
import com.joyintech.tams.common.base.dao.BokBooksetDateMapper;
import com.joyintech.tams.common.base.dao.BokBooksetMapper;
import com.joyintech.tams.common.base.dao.TrdAccountmfConfigMapper;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.tams.common.base.entity.TrdAccountmfConfig;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 费用预提配置
 * @author tanxuan
 * @version 1.0 2021年3月18日
 * @see ValTrdAccountmfConfig001Service
 * @since 1.0
 */
@Service(value="serviceValTrdAccountmfConfig001Service")
@Transactional(rollbackFor = Exception.class)
public class ValTrdAccountmfConfig001Service extends CommonExportService<ValTrdAccountmfConfig001Dto> implements BaseProcessService<ValTrdAccountmfConfig001Dto> {

	@Autowired
	private ValTrdAccountmfConfig001Mapper valTrdAccountmfConfig001Mapper;
	
	@Autowired
	private TrdAccountmfConfigMapper trdAccountmfConfigMapper;
	
	@Autowired
	private BokBooksetDateMapper bokBooksetDateMapper;
	
	@Autowired
	private BokBooksetMapper bokBooksetMapper;
	
	/**
     * 页面查询
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    public Page<ValTrdAccountmfConfig001Dto> findPage(int page,int length,Map<String, Object> params) throws Exception{
        return super.queryForPage(page,length,()->valTrdAccountmfConfig001Mapper.queryAllList(params));
    }
    
	/**
     * 根据页面条件查询所有结果
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public List<ValTrdAccountmfConfig001Dto> queryAllList(Map<String, Object> params) throws Exception{
    	return valTrdAccountmfConfig001Mapper.queryAllList(params);
    }
    
    /**
     * 检索账套代码列表（只取理财账套）
     * 
     * @param param 过滤参数
     * @return 账套代码列表
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBooksetId(int page,int length,Map<String, Object> params) throws Exception{
    	params.put("booksetType", DictCodeList.账套类别_理财账套.getValue());
        return super.queryForPageForSelectReturn(page,length,()->valTrdAccountmfConfig001Mapper.getBooksetId(params));
    }
    
    /**
     * 根据账套代码获取账套日期
     * 
     * @param param 过滤参数
     * @return 账套日期
     * @see
     * @since 1.0
     */
    public BokBooksetDate getBooksetDate(Map<String, Object> params) {
    	String booksetId = (String) params.get("booksetId");
        return bokBooksetDateMapper.selectByPrimaryKey(booksetId);
    }
    
    /**
     * 根据账套代码获取账套结束日
     * 
     * @param param 过滤参数
     * @return 账套结束日
     * @see
     * @since 1.0
     */
    public BokBookset getBooksetEndDate(Map<String, Object> params) {
    	String booksetId = (String) params.get("booksetId");
        return bokBooksetMapper.selectByPrimaryKey(booksetId);
    }
    
    /**
     * 根据流水号查询预提费用配置信息
     * 
     * @param param 过滤参数
     * @return 预提费用配置信息
     * @see
     * @since 1.0
     */
    public ValTrdAccountmfConfig001Dto findBySeqNo(Map<String, Object> params) {
    	List<ValTrdAccountmfConfig001Dto> dtoList = valTrdAccountmfConfig001Mapper.queryAllList(params);
    	ValTrdAccountmfConfig001Dto dto = new ValTrdAccountmfConfig001Dto();
    	if(dtoList != null && !dtoList.isEmpty()){
    		dto = dtoList.get(0);
    	}
        return dto;
    }
	
	@Override
	public void doComplete(ValTrdAccountmfConfig001Dto entity, OperationType type) {
		TrdAccountmfConfig dto = new TrdAccountmfConfig();
		BeanUtil.copyPropertiesIgnoreNull(dto, entity);
		dto.setPositionStartDate(DateUtil.parse(entity.getPositionStartDate()));
		dto.setPositionEndDate(DateUtil.parse(entity.getPositionEndDate()));
		if(OperationType.DELETE.equals(type)) {
        	trdAccountmfConfigMapper.deleteByPrimaryKey(entity.getSeqNo());
//        }else if(OperationType.MODIFY.equals(type)){
//			trdAccountmfConfigMapper.updateByPrimaryKey(dto);
//        }else if(OperationType.NEW.equals(type)){
//			trdAccountmfConfigMapper.insert(dto);
		}
	}

}
