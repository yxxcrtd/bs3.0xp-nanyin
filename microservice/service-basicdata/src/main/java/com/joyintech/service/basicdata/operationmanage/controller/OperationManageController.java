/*
 * 文件名：OperationManageController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年2月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.operationmanage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.operationmanage.dto.OperationManageDto;
import com.joyintech.service.basicdata.operationmanage.service.OperationManageService;

/**
 * 
 * 运营管理
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2019年2月19日
 * @see OperationManageController
 * @since 1.0
 */
@RestController
@RequestMapping("/OperationManage")
public class OperationManageController {
	
	@Autowired
	private OperationManageService operationManageService;
	
	@PostMapping("/selectForPage/{page}/{length}")
    public Page<OperationManageDto> selectForPage(@RequestBody Map<String, Object> pageParam,
                                                           @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return operationManageService.selectForPage(page,length,pageParam);
    }
	
	/**
     * 
     * 获取组合代码下拉列表
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getPortfolioId")
    public List<SuggestDto> getPortfolioId(@RequestBody Map<String, Object> pageParam)
        throws Exception {
        return operationManageService.getPortfolioId(pageParam);
    }
    
    /**
     * 
     * 获取组合代码下拉列表
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getPortfolioId1")
    public List<SuggestDto> getPortfolioId1(@RequestBody Map<String, Object> pageParam)
        throws Exception {
        return operationManageService.getPortfolioId1(pageParam);
    }
	
}
