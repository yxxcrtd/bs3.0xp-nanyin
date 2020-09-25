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

import java.util.List;
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
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPortfolioPackage003Dto;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPortfolioPackageDtlImp;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPortfolioPoolConfigDto;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPtlPortfolioDto;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.service.RskSupervisionPortfPoint003Service;
 


/**
 * 监督指标
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see RskSupervisionPortfPoint003Controller
 * @since 1.0
 */
@RestController("backRskSupervisionPortfPoint003Controller_dtx")
@RequestMapping("/RskSupervisionPortf003Point")
public class RskSupervisionPortfPoint003Controller {

	/**
	 * RskSupervisionIndicatorService
	 */
	@Autowired
	private RskSupervisionPortfPoint003Service rskSupervisionPortfPoint003Service;
	

	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<RskPortfolioPoolConfigDto> selectForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return rskSupervisionPortfPoint003Service.selectForPage(page, length, param);
    }

    /**
     * 插入方法
     * @param dto 画面参数
     * @return 保存结果
     */
    @Transactional
    @DTXTransaction
    @PostMapping("save")
    public void save(@RequestBody RskPortfolioPackage003Dto dto){
         rskSupervisionPortfPoint003Service.insert(dto);
    }

    /**
     * 更新方法
     * @param dto 画面参数
     * @return 保存结果
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public void update(@RequestBody RskPortfolioPackage003Dto dto){
         rskSupervisionPortfPoint003Service.update(dto);
    }
    
    /**
     * 查询组合包基本信息
     * @return 查询结果集
     * @throws Exception 异常
     */
    @GetMapping("queryPortfBagInfoByBagId")
    public RskPortfolioPackage003Dto queryPortfBagInfoByBagId(String bagId) throws Exception {
        return rskSupervisionPortfPoint003Service.queryPortfBagInfoByBagId(bagId);
    }
    
    /**
     * 查询组合包是否关联指标
     * @return 查询结果集
     * @throws Exception 异常
     */
    @GetMapping("selectReleteIndByBagId")
    public int selectReleteIndByBagId(String bagId) throws Exception {
        return rskSupervisionPortfPoint003Service.selectReleteIndByBagId(bagId);
    }
    
    /**
     * 插入方法
     * @param dto 画面参数
     * @return 保存结果
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteBag")
    public void deleteBag(@RequestBody String bagId){
         rskSupervisionPortfPoint003Service.deleteBag(bagId);
    }
    
    /**
     * 新建编辑场合时查询组合包名称是否重复
     * @param portfBagName
     */
	@GetMapping("/searchRepeatPortfNameByPortName")
	public int searchRepeatPortfNameByPortName(String portfBagName) {
		return rskSupervisionPortfPoint003Service.searchRepeatPortfNameByPortName(portfBagName);
	}
	
	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectPortElementForPage/{page}/{length}")
    public Page<RskPtlPortfolioDto> selectPortElementForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return rskSupervisionPortfPoint003Service.selectPortElementForPage(page, length, param);
    }
    
    /**
     * 查询组合包组合基本信息
     * @return 查询结果集
     * @throws Exception 异常
     */
    @GetMapping("queryPortfolioPackageDtlByBagId")
    public List<String> queryPortfolioPackageDtlByBagId(String bagId) throws Exception {
        return rskSupervisionPortfPoint003Service.queryPortfolioPackageDtlByBagId(bagId);
    }
}
