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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.ArithmeticUtil;
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
@Service("backPtlFundAcctPosiService")
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
        if(MultilevelDataAuthorityUtil.isEnabled()) {
        	pageParam.put("isMultiLevel", "01");
        }
        Page<PtlFundAcctPosiDto> pageResult = super.queryForPage(page, length,() -> ptlFundAcctPosiMapper.select(pageParam));
        
        //多级法人下有数据隔离，汇总需特别处理,银行模式下直接在查询中汇总
        if(MultilevelDataAuthorityUtil.isEnabled()) {
        	//1.查询是否需要添加汇总
        	if(pageResult.getRoot()==null||pageResult.getRoot().size()<length){
        		//2. 检查Root是否存在，避免因查询为空报错
        		if(pageResult.getRoot()==null) {
        			pageResult.setRoot(new ArrayList<PtlFundAcctPosiDto>());
        		}
        		//3.初始化汇总对象
        		PtlFundAcctPosiDto summaryDto = new PtlFundAcctPosiDto();
        		summaryDto.setIsSummary(DictCodeListExt1.是或否_是.getValue());
        		BigDecimal summary = BigDecimal.ZERO;
        		//4 首先汇总本页
        		for(PtlFundAcctPosiDto dto:pageResult.getRoot()) {
        			summary = ArithmeticUtil.doAdd(summary,dto.getAmount1());
        		}
        		//5.循环获取所有页进行汇总
        		//5.1 初始化循环汇总的查询参数
        		int summaryLength = 1000;//单次查询1000
        		long summarySize = pageResult.getRecords();
        		if(pageResult.getRoot()!=null) {
        			summarySize = summarySize-pageResult.getRoot().size();
        		}
        		//5.2 剩余结果逐页查询汇总
        		int i;
        		for(i=1;i*summaryLength<summarySize;i++) {
        			Page<PtlFundAcctPosiDto> tempPage = super.queryForPage(i, summaryLength,() -> ptlFundAcctPosiMapper.select(pageParam));
            		for(PtlFundAcctPosiDto dto:tempPage.getRoot()) {
            			summary = ArithmeticUtil.doAdd(summary,dto.getAmount1());
            		}
        		}
        		//5.3 最后一页结果
        		int lastLength = (int)summarySize-(i-1)*summaryLength;
        		if(lastLength>0) {
        			Page<PtlFundAcctPosiDto> tempPage = super.queryForPage(i, summaryLength,() -> ptlFundAcctPosiMapper.select(pageParam));
            		for(i=0;i<lastLength;i++) {
            			PtlFundAcctPosiDto dto = tempPage.getRoot().get(i);
            			summary = ArithmeticUtil.doAdd(summary,dto.getAmount1());
            		}
        		}
        		//6. 设置汇总数据
        		summaryDto.setAmount1(summary);
        		//7. 设置汇总行。
        		pageResult.getRoot().add(summaryDto);
        	}
        	//8.总记录数需添加 汇总
        	pageResult.setRecords(pageResult.getRecords()+1);
        	//9.如果不含汇总行时为整页，则需要加页
        	if(pageResult.getRecords()%length==1) {
        		pageResult.setTotal(pageResult.getTotal()+1);
        	}
        }
        return pageResult;
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
