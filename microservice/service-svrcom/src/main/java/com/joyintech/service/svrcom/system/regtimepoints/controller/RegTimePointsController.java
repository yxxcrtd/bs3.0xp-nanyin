// ******************************************************************************
// Copyright (C) 2016 Joyintech, All Rights Reserved.
// ******************************************************************************

package com.joyintech.service.svrcom.system.regtimepoints.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.system.regtimepoints.dto.RegTimePoints001Dto;
import com.joyintech.tams.report.system.regtimepoints.service.RegTimePoints001Service;


/**
 * 报送时点管理服务
 * 〈功能详细描述〉
 * @author zhagnzhongliang
 * @version 1.0 2019年04月19日
 * @see ExchangerateController
 * @since 1.0
 */
@RestController
@RequestMapping("/RegTimePoints")
public class RegTimePointsController {

    /**
     * 报送时点管理Service
     */
    @Autowired
    private RegTimePoints001Service regTimePoints001Service;

    /**
     * 查询按钮
     * @return 查询结果list
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<RegTimePoints001Dto> searchPage(@RequestBody Map<String, Object> param) {
        Page<RegTimePoints001Dto> pageList= regTimePoints001Service.searchPage(param);
        return pageList;
    }
    
    /**
     * 查询check
     *
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    @PostMapping("findRegTimePointsCount")
    public int check(@RequestBody Map<String, Object> param){
       return regTimePoints001Service.findRegTimePointsCount(param);
    }
    


    /**
     * 保存编辑后的参数
     *
     * @param dto 报送时点dto
     * @return 
     */
    @PostMapping("save")
    public void doSave(@RequestBody RegTimePoints001Dto dto) {
        regTimePoints001Service.updateRegTimePoints(dto);
    }
    
    /**
     * 保存编辑后的参数
     *
     * @param dto 报送时点dto
     * @return 结果
     */
    @PostMapping("updateAllRegTimePoints")
    public void saveData(@RequestBody Map<String, Object> param) {
        regTimePoints001Service.updateAllRegTimePoints(param);
    }
    
    /**
     * 查询报送时点
     * 
     * @return 数据list
     * @see
     * @since 1.0
     */
    @GetMapping("selectPoint")
    public List<RegTimePoints001Dto> selectPoint() {
        return regTimePoints001Service.selectPoint();
    }
}
