/*
 * 文件名：RskSupervisionPortfPointController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.riskrds.limitmanagement.portfolioindicator.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPortfolioIndicator002Dto;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.service.RskSupervisionPortfPoint002Service;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dto.RskSupervisionIndicator002Dto;


/**
 * 组合关联监督指标
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see RskSupervisionPortfPoint002Controller
 * @since 1.0
 */
@RestController("backRskSupervisionPortfPoint002Controller_dtx")
@RequestMapping("/RskSupervisionPortf002Point")
public class RskSupervisionPortfPoint002Controller {

	/**
	 * RskSupervisionIndicatorService
	 */
	@Autowired
	private RskSupervisionPortfPoint002Service rskSupervisionPortfPoint002Service;
	

	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/queryRelatedIndList/{page}/{length}")
    public Page<RskSupervisionIndicator002Dto> queryRelatedIndList(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return rskSupervisionPortfPoint002Service.queryRelatedIndList(page, length, param);
    }

    /**
     * 查询组合成分方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @GetMapping("getPortfolioConst")
    public String getPortfolioConst(String portfolioCode) throws Exception {
        return rskSupervisionPortfPoint002Service.getPortfolioConst(portfolioCode);
    }
    
    /**
     * 根据组合代码查询关联指标信息
     * @return 查询结果集
     * @throws Exception 异常
     */
    @GetMapping("queryPortfIndByPortfId")
    public RskPortfolioIndicator002Dto queryPortfIndByPortfId(String portfId,String indId) throws Exception {
        return rskSupervisionPortfPoint002Service.queryPortfIndByPortfId(portfId,indId);
    }
    
    /**
     * 插入
     * @param dto
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody RskPortfolioIndicator002Dto dto) throws Exception {
    	rskSupervisionPortfPoint002Service.insert(dto);
    }
    
    /**
	 * 更新有效区间
	 * @return 
	 */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
	public void update(@RequestBody RskPortfolioIndicator002Dto dto) {
		rskSupervisionPortfPoint002Service.update(dto);
	}
    /**
     * 删除
     * @param 页面选中的数据
     * @return 结果
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody RskPortfolioIndicator002Dto dto) throws Exception {
        return rskSupervisionPortfPoint002Service.delete(dto);
    }
    
    /**
     * 新建编辑场合时查询监督指标名称是否重复
     * @param indId portfolioId 
     */
	@GetMapping("/searchRelatedIndByPortf")
	public int searchRelatedIndByPortf(String indId,String portfolioId) {
		return rskSupervisionPortfPoint002Service.searchRelatedIndByPortf(indId,portfolioId);
	}
	
	@GetMapping("/searchRelatedIndByPortfBagSingle")
	public int searchRelatedIndByPortfBagSingle(String indId,String portfolioId) {
		return rskSupervisionPortfPoint002Service.searchRelatedIndByPortfBagSingle(indId,portfolioId);
	}
	
	@GetMapping("/searchRelatedIndByPortfBag")
	public int searchRelatedIndByPortfBag(String indId,String portfolioId) {
		return rskSupervisionPortfPoint002Service.searchRelatedIndByPortfBag(indId,portfolioId);
	}
}
