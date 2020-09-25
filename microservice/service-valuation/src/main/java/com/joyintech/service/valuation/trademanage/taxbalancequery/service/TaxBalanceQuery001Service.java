/*
 * 文件名：TaxBalanceQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：leipeng
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.trademanage.taxbalancequery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.trademanage.taxbalancequery.dao.TaxBalanceQuery001Mapper;
import com.joyintech.service.valuation.trademanage.taxbalancequery.dto.TaxBalanceQuery001Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;


/**
 * 简易计税余额查询
 * @author leipeng
 * @version 1.0 2018年10月9日
 * @see TaxBalanceQuery001Service
 * @since 1.0
 */
@Service(value="serviceTaxBalanceQuery001Service")
public class TaxBalanceQuery001Service extends CommonExportService<TaxBalanceQuery001Dto>{

    @Autowired
    private TaxBalanceQuery001Mapper taxBalanceQuery001Mapper;
    
    /**
     * 根据条件分页查询
     * @param params
     * @return
     * @throws Exception
     */
    public Page<TaxBalanceQuery001Dto> findByPage(int page, int length, Map<String, Object> params) throws Exception{
    	//增加增值税类型限制
    	params.put("initvatTypes", DictCodeList.valuesfromDictCode(DictCode.增值税类型));
    	
    	//产品编号多选
    	if(StringUtil.isNotEmpty(params.get("finprodId"))){
    		params.put("finprodIds", params.get("finprodId").toString().split(","));
    	}
    	//资产分类
    	if(StringUtil.isNotEmpty(params.get("taxPType"))){
    		params.put("taxPTypes", params.get("taxPType").toString().split(","));
    	}
    	//收入类型
    	if(StringUtil.isNotEmpty(params.get("vatType"))){
    		params.put("vatTypes", params.get("vatType").toString().split(","));
    	}
    	
//    	return  super.queryForPage(page, length, ()->taxBalanceQuery001Mapper.selectList(params));
    	//资产代码
    	if(StringUtil.isNotEmpty(params.get("assetId"))){
    		params.put("assetIds", params.get("assetId").toString().split(","));
    	}
    	Page<TaxBalanceQuery001Dto> pageReturn =null;
    	if(StringUtil.isNotEmpty(params.get("prdCheckBox"))  && "Y".equals(params.get("prdCheckBox"))){
    		pageReturn = super.queryForPage(page, length, ()->taxBalanceQuery001Mapper.selectList(params));
    	}else{
    		pageReturn = super.queryForPage(page, length, ()->taxBalanceQuery001Mapper.selectListAsset(params));
    	}
    	
        return pageReturn;
    }
    
    /**
     * 根据条件分页查询
     * @param params
     * @return
     * @throws Exception
     */
    public List<TaxBalanceQuery001Dto> findByList(Map<String, Object> params) throws Exception{
        //增加增值税类型限制
        params.put("initvatTypes", DictCodeList.valuesfromDictCode(DictCode.增值税类型));
        
        //产品编号多选
        if(StringUtil.isNotEmpty(params.get("finprodId"))){
            params.put("finprodIds", params.get("finprodId").toString().split(","));
        }
        //资产分类
        if(StringUtil.isNotEmpty(params.get("taxPType"))){
            params.put("taxPTypes", params.get("taxPType").toString().split(","));
        }
        //收入类型
        if(StringUtil.isNotEmpty(params.get("vatType"))){
            params.put("vatTypes", params.get("vatType").toString().split(","));
        }
        
//        List<TaxBalanceQuery001Dto> page = taxBalanceQuery001Mapper.selectList(params);
      //资产代码
    	if(StringUtil.isNotEmpty(params.get("assetId"))){
    		params.put("assetIds", params.get("assetId").toString().split(","));
    	}
    	List<TaxBalanceQuery001Dto> page =null;
    	if(StringUtil.isNotEmpty(params.get("prdCheckBox"))  && "Y".equals(params.get("prdCheckBox"))){
    		 page = taxBalanceQuery001Mapper.selectList(params);
    	}else{
    		 page = taxBalanceQuery001Mapper.selectListAsset(params);
    	}
        
        return page;
    }

	public Page<SelectOptionDto> getAssetId(int page,int length,Map<String, Object> pageParam) {
		//return super.queryForPageForSelectReturn(page,length,()->taxBalanceQuery001Mapper.getAssetId(pageParam));
		//多级法人改造 add by hxy start 20200616
		int beginIndex = (page - 1) * length;
        int endIndex = page * length;
        List<SelectOptionDto> returnList = new ArrayList<SelectOptionDto>();
        //共用资产
        List<SelectOptionDto> ListPub =taxBalanceQuery001Mapper.getAssetIdPub(pageParam);
        returnList.addAll(ListPub);
        //非公用资产
        List<SelectOptionDto> ListPrivate =taxBalanceQuery001Mapper.getAssetId(pageParam);
        returnList.addAll(ListPrivate);
        int count = returnList.size();
        if (beginIndex > count) {
        	beginIndex = 0;
        }
        if (endIndex > count) {
        	endIndex = count;
        }
        returnList = returnList.subList(beginIndex, endIndex);
        Page<SelectOptionDto> reslist = new Page<>();
        reslist.setRoot(returnList);
        reslist.setRecords(count);
        return reslist;
        
        //多级法人改造 add by hxy end 20200616
	
	
	}
    
}
