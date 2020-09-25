/*
 * 文件名：PtlFundAcctPosiService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年2月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlfundacctposi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.portfolio.ptlactivityperiod.dto.PtlActivityPeriod001Dto;
import com.joyintech.service.portfolio.ptlfundacctposi.dao.PtlFundAcctPosiMapper;
import com.joyintech.service.portfolio.ptlfundacctposi.dto.PtlFundAcctPosiDto;

/**
 * 
 * 组合资金账户头寸查询
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2019年2月20日
 * @see PtlFundAcctPosiService
 * @since 1.0
 */
@Service
public class PtlFundAcctPosiService extends BaseService<PtlFundAcctPosiDto>{
	
	@Autowired
	private PtlFundAcctPosiMapper ptlFundAcctPosiMapper;
	
	/**
     * 分页查询
     * @param map 分页查询条件
     * @return page对象
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<PtlFundAcctPosiDto> selectForPage(int page,int length,Map<String,Object> pageParam) throws Exception{
        // add by caiqun 20200819 东莞 工单YHZGCPB20200814003  start
        //pageParam.put("cdate", DateUtil.parse((String)pageParam.get("cdate")));
        pageParam.put("startCDate", DateUtil.parse((String)pageParam.get("startCDate")));
        pageParam.put("endCDate", DateUtil.parse((String)pageParam.get("endCDate")));
        // add by caiqun 20200819 东莞 工单YHZGCPB20200814003  end
    	//  bug fixed:TISHKBS-75  只统计资金账户、券商账户余额，经代码核实只查询账户编码为A01/A07账户
//    	    pageParam.put("TGZH", DictCodeListExt1.资金账户类型_产品托管户.getValue());
//          pageParam.put("JYSZJZH", DictCodeListExt1.资金账户类型_券商资金账户.getValue());

        return super.queryForPage(page, length,() -> ptlFundAcctPosiMapper.select(pageParam));
    }
	
    /**
     * 分页查询
     * @param map 分页查询条件
     * @return page对象
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<PtlFundAcctPosiDto> selectForPage1(int page,int length,Map<String,Object> pageParam) throws Exception{
		pageParam.put("cdate", DateUtil.parse((String)pageParam.get("cdate")));
		Page<PtlFundAcctPosiDto> pages = super.queryForPage(page, length,() -> ptlFundAcctPosiMapper.select1(pageParam));
        return pages;
    }
    
    /**
     * 分页查询
     * @param map 分页查询条件
     * @return page对象
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<PtlFundAcctPosiDto> selectForPage2(int page,int length,Map<String,Object> pageParam) throws Exception{
    	pageParam.put("cdate", DateUtil.parse((String)pageParam.get("cdate")));
    	pageParam.put("startCdate", DateUtil.parse((String)pageParam.get("startCdate")));
		pageParam.put("endCdate", DateUtil.parse((String)pageParam.get("endCdate")));
    	Page<PtlFundAcctPosiDto> pages = super.queryForPage(page, length,() -> ptlFundAcctPosiMapper.select2(pageParam));
        return pages;
    }
    
    /**
     * 
     * 组合代码下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getPortfolioId(Map<String, Object> param,int page,int length){
        return super.queryForPageForSelectReturn(page,length,()->ptlFundAcctPosiMapper.getPortfolioId(param));
	}
}
