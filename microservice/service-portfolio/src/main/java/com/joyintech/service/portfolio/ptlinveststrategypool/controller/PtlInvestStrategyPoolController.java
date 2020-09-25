/*
 * 文件名：PtlInvestStrategyPoolController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wm
 * 修改时间：2019年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlinveststrategypool.controller;


import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlinveststrategypool.dto.PtlInvStrategyDetailDto;
import com.joyintech.service.portfolio.ptlinveststrategypool.dto.PtlInvestStrategyPoolDto;
import com.joyintech.service.portfolio.ptlinveststrategypool.service.PtlCommonSearchService;
import com.joyintech.service.portfolio.ptlinveststrategypool.service.PtlInvestStrategyPoolService;

/**
 * 
 * 投资组合策略管理
 * 
 * @author wm
 * @version 1.0 2019年8月14日
 * @see PtlInvestStrategyPoolController
 * @since 1.0
 */
@RestController("ptlInvestStrategyPoolController_dtx")
@RequestMapping("/PtlInvestStrategyPool")
public class PtlInvestStrategyPoolController {
	@Autowired
	private PtlInvestStrategyPoolService ptlInvestStrategyPoolService;
    @Autowired
    private PtlCommonSearchService commonSearchService;
	
	  /**
		 * 画面查询方法
		 * 
		 * @param param 请求参数
		 * @param page 页面
		 * @param length 长度
		 * @return 查询结果集
		 * @throws Exception 异常
		 */
		@PostMapping("selectForPage/{page}/{length}")
		public Page<PtlInvestStrategyPoolDto> selectForPage(@RequestBody Map<String, Object> pageParam,
				@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
			if(StringUtil.isNotEmpty(pageParam.get("strategyId"))){
				pageParam.put("strategyId", StringUtil.splitToList(null, pageParam.get("strategyId").toString()));
			}
			if(StringUtil.isNotEmpty(pageParam.get("type2"))){
				pageParam.put("type2", StringUtil.splitToList(null, pageParam.get("type2").toString()));
			}
			if(StringUtil.isNotEmpty(pageParam.get("type3"))){
				pageParam.put("type3", StringUtil.splitToList(null, pageParam.get("type3").toString()));
			}
			if(StringUtil.isNotEmpty(pageParam.get("type4"))){
				pageParam.put("type4", StringUtil.splitToList(null, pageParam.get("type4").toString()));
			}
			if(StringUtil.isNotEmpty(pageParam.get("type5"))){
				pageParam.put("type5", StringUtil.splitToList(null, pageParam.get("type5").toString()));
			}
			return ptlInvestStrategyPoolService.selectForPage(page, length, pageParam);
		}
		/**
		 * 画面查询方法
		 * 
		 * @param param 请求参数
		 * @param page 页面
		 * @param length 长度
		 * @return 查询结果集
		 * @throws Exception 异常
		 */
		@PostMapping("selectIsPtlPortfolioCount")
		public Integer selectIsPtlPortfolioCount(@RequestBody Map<String, Object> pageParam) throws Exception {
			return ptlInvestStrategyPoolService.selectIsPtlPortfolioCount(pageParam);
		}
		/**
		 * 
		 * @param pageParam
		 * @return
		 * @throws Exception
		 */
		@PostMapping("delete")
		@Transactional
		@DTXTransaction
		public Integer delete(@RequestBody PtlInvestStrategyPoolDto entity) throws Exception {
			return ptlInvestStrategyPoolService.delete(entity);
		}
		/**
	     * 根据策略代码查询策略详情
	     * @param tradeId
	     * @return
	     */
	    @GetMapping("getPtlInvestStrategyPool")
	    public PtlInvestStrategyPoolDto getPtlInvestStrategyPool(String strategyId){
	        return ptlInvestStrategyPoolService.getPtlInvestStrategyPool(strategyId);
	    }
	    /**
	     * 根据策略代码查询策略详情列表
	     * @param tradeId
	     * @return
	     */
	    @GetMapping("getPtlInvestStrategyPoolDetail")
	    public List<PtlInvStrategyDetailDto> getPtlInvestStrategyPoolDetail(String strategyId){
	        return ptlInvestStrategyPoolService.getPtlInvestStrategyPoolDetail(strategyId);
	    }
	    /**
	     * excel导出查询
	     * @param tradeId
	     * @return
	     */
	    @PostMapping("selectForExport")
	    public List<PtlInvestStrategyPoolDto> selectForExport(@RequestBody Map<String, Object> pageParam){
	    	if(StringUtil.isNotEmpty(pageParam.get("strategyId"))){
				pageParam.put("strategyId", StringUtil.splitToList(null, pageParam.get("strategyId").toString()));
			}
			if(StringUtil.isNotEmpty(pageParam.get("type2"))){
				pageParam.put("type2", StringUtil.splitToList(null, pageParam.get("type2").toString()));
			}
			if(StringUtil.isNotEmpty(pageParam.get("type3"))){
				pageParam.put("type3", StringUtil.splitToList(null, pageParam.get("type3").toString()));
			}
			if(StringUtil.isNotEmpty(pageParam.get("type4"))){
				pageParam.put("type4", StringUtil.splitToList(null, pageParam.get("type4").toString()));
			}
			if(StringUtil.isNotEmpty(pageParam.get("type5"))){
				pageParam.put("type5", StringUtil.splitToList(null, pageParam.get("type5").toString()));
			}
	        return ptlInvestStrategyPoolService.selectForExport(pageParam);
	    }
	    /**
	     * 更新策略指标表
	     * @param pageParam
	     * @return
	     */
		@Transactional
		@DTXTransaction
	    @PostMapping("updateStrategyEveIndexByStrategyId")
	    public Integer updateStrategyEveIndexByStrategyId(@RequestBody Map<String, Object> pageParam){
	        return ptlInvestStrategyPoolService.updateStrategyEveIndexByStrategyId(pageParam);
	    }
	    /**
	     * 修改相关功能
	     * @param entity 修改内容
	     * @return
	     */
		@Transactional
		@DTXTransaction
	    @PostMapping("editStrategyPoolByEntity")
	    public Integer editStrategyPoolByEntity(@RequestBody PtlInvestStrategyPoolDto entity){
	        return ptlInvestStrategyPoolService.editStrategyPoolByEntity(entity);
	    }
	    
	    /**
	     * 策略代码生成
	     * @see
	     * @since 1.0
	     */
	    @PostMapping("createStrategyId")
	    public String createStrategyId() {
	        return ptlInvestStrategyPoolService.createStrategyId();
	    }
        
        /**
         * 资产分类2多选下拉框加载
         * @param pageParam 参数
         * @return list
         * @see
         * @since 1.0
         */
        @PostMapping("queryMulType2/{page}/{length}")
        public Page<SelectOptionDto> queryMulType2(@RequestBody Map<String, Object> pageParam,
                                                   @PathVariable("page") int page, @PathVariable("length") int length) {
            return commonSearchService.queryMulType2(page, length, pageParam);
        }
        
	    /**
	     * 资产分类3多选下拉框加载
	     * @param pageParam 参数
	     * @return list
	     * @see
	     * @since 1.0
	     */
        @PostMapping("queryMulType3/{page}/{length}")
        public Page<SelectOptionDto> queryMulType3(@RequestBody Map<String, Object> pageParam,
                                                   @PathVariable("page") int page, @PathVariable("length") int length) {
            return commonSearchService.queryMulType3(page, length, pageParam);
        }

        /**
         * 资产分类4多选下拉框加载
         * @param pageParam 参数
         * @return list
         * @see
         * @since 1.0
         */
        @PostMapping("queryMulType4/{page}/{length}")
        public Page<SelectOptionDto> queryMulType4(@RequestBody Map<String, Object> pageParam,
                                                   @PathVariable("page") int page, @PathVariable("length") int length) {
            return commonSearchService.queryMulType4(page, length, pageParam);
        }
        

        /**
         * 资产分类5多选下拉框加载
         * @param pageParam 参数
         * @return list
         * @see
         * @since 1.0
         */
        @PostMapping("queryMulType5/{page}/{length}")
        public Page<SelectOptionDto> queryMulType5(@RequestBody Map<String, Object> pageParam,
                                                   @PathVariable("page") int page, @PathVariable("length") int length) {
            return commonSearchService.queryMulType5(page, length, pageParam);
        }
}
