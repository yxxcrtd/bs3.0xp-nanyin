/*
 * 文件名：ShadowPriceScheme002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.valuationparametermanage.shadowpricescheme.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.valuationparametermanage.shadowpricescheme.dao.ShadowPriceScheme002Mapper;
import com.joyintech.service.valuation.valuationparametermanage.shadowpricescheme.dto.ShadowPriceScheme001Dto;
import com.joyintech.service.valuation.valuationparametermanage.shadowpricescheme.dto.ShadowPriceScheme002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
/**
 * 影子定价方案明细service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年11月23日
 * @see ShadowPriceScheme002Service
 * @since 1.0
 */
@Service(value="serviceShadowPriceScheme002Service")
public class ShadowPriceScheme002Service extends CommonExportService<ShadowPriceScheme002Dto> implements BaseProcessService<ShadowPriceScheme002Dto>{
	@Autowired
	private ShadowPriceScheme002Mapper shadowPriceScheme002Mapper;
    /**
     * 影子定价方案明细查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @throws Exception 
     */
	public Page<ShadowPriceScheme002Dto> findShadowPriceDetial(int page, int length,Map<String, Object> pageParam) throws Exception {
		// TODO Auto-generated method stub
		return super.queryForPage(page, length,()-> shadowPriceScheme002Mapper.findShadowPriceDetial(pageParam)); 
	}
	/**
	 * 影子方案明细新建
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 */
	public void insert(ShadowPriceScheme002Dto dto) {
		// TODO Auto-generated method stub
		shadowPriceScheme002Mapper.insert(dto);
	}
	
	/**
	 * 影子方案明细修改
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 */
	public void updateByPrimaryKeySelective(ShadowPriceScheme002Dto dto) {
		// TODO Auto-generated method stub
		shadowPriceScheme002Mapper.updateByPrimaryKeySelective(dto);
	}
	/**
	 * 修改时根据id查询数据
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param schemeDetailId
	 * @return
	 */
	public ShadowPriceScheme002Dto selectSchemeDetailBySchId(String schemeDetailId) {
		// TODO Auto-generated method stub
		return shadowPriceScheme002Mapper.selectSchemeDetailBySchId(schemeDetailId);
	}
	/**
	 * 影子定价方案明细批量删除
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param schemeDetailId
	 * @return
	 */
	public int deleteShadowPriceDetailBatch(String schemeDetailId) {
		// TODO Auto-generated method stub
		return shadowPriceScheme002Mapper.deleteShadowPriceDetailBatch(schemeDetailId);
	}
	/**
	 * 导出时查询所有
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param pageParam
	 * @return
	 */
	public List<ShadowPriceScheme002Dto> findShadowDetial(Map<String, Object> pageParam) {
		// TODO Auto-generated method stub
		return shadowPriceScheme002Mapper.findShadowPriceDetial(pageParam);
	}
	
	@Override
	public void doComplete(ShadowPriceScheme002Dto entity, OperationType type) {
		//新建操作
		 if(OperationType.NEW.equals(type)) {
		    	shadowPriceScheme002Mapper.insert(entity);
	        }
		// 删除操作
        if(OperationType.DELETE.equals(type)) {        	
        	shadowPriceScheme002Mapper.deleteShadowPriceDetailBatch(entity.getSchemeDetailId());
        }
        //影子方案明细修改
	    if(OperationType.MODIFY.equals(type)){
	    	shadowPriceScheme002Mapper.updateByPrimaryKeySelective(entity);
        }
	}
	/**
	 * 通过schName获取shcId
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param schemeId
	 */
	public ShadowPriceScheme001Dto getschemeIdByname(String schemeId) {
		// TODO Auto-generated method stub
		return shadowPriceScheme002Mapper.getschemeIdByname(schemeId);
	}
	
	/**
     * 校验是否重复数据
     * @param pageParam 参数
     * @return
     */
    public List<ShadowPriceScheme002Dto> checkShadowPriceDetial(Map<String, Object> pageParam) {
        return shadowPriceScheme002Mapper.checkShadowPriceDetial(pageParam);
    }
}
