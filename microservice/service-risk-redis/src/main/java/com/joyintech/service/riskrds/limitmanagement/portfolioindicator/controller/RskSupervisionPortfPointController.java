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
import com.joyintech.framework.select.TreeNode;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPortfolioIndicator002Dto;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.service.RskSupervisionPortfPointService;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dto.RskSupervisionIndicator002Dto;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dto.RskSupervisionIndicatorDto;


/**
 * 监督指标
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see RskSupervisionPortfPointController
 * @since 1.0
 */
@RestController("backRskSupervisionPortfPointController_dtx")
@RequestMapping("/RskSupervisionPortfPoint")
public class RskSupervisionPortfPointController {

	/**
	 * RskSupervisionIndicatorService
	 */
	@Autowired
	private RskSupervisionPortfPointService rskSupervisionPortfPointService;
	

	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<RskPortfolioIndicator002Dto> selectForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return rskSupervisionPortfPointService.selectForPage(page, length, param);
    }

    /**
     * 指标树画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectTableIndForPage/{page}/{length}")
    public Page<RskPortfolioIndicator002Dto> selectTableIndForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return rskSupervisionPortfPointService.selectTableIndForPage(page, length, param);
    }
    
	/**
	 * 指标树
	 * @param param
	 * @return
	 */
	@GetMapping("/getTreeIndicatorByIndType")
	public List<TreeNode<RskSupervisionIndicatorDto>> getTreeIndicatorByIndType(String indType) {
	    return rskSupervisionPortfPointService.getTreeIndicatorByIndType(indType);
	}
	
	 /**
     * 插入
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @Transactional
	@DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody RskSupervisionIndicator002Dto dto) throws Exception {
    	rskSupervisionPortfPointService.insert(dto);
    }
    
    
    @PostMapping("playPortfolio")
    public int playPortfolio(@RequestBody Map<String,Object> param) throws Exception {
    	return rskSupervisionPortfPointService.playPortfolio(param);
    }
    
    // add by zhuzhen 20191128 组合关联指标删除时校验联合设置 start
    /**
     * 根据组合代码，指标代码，查询个性化联合设置数据是否存在
     * @param param 参数
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("selectIndividUnite")
    public boolean selectIndividUnite(@RequestBody Map<String,Object> param) throws Exception {
        return rskSupervisionPortfPointService.selectIndividUnite(param);
    }
    // add by zhuzhen 20191128 组合关联指标删除时校验联合设置 end
}
