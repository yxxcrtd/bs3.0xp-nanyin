/*
 * 文件名：CheckReport001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月28日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.service.svrcom.submitexamination.checkreport.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.ChbReportCheck;
import com.joyintech.tams.report.submitexamination.checkreport.service.CheckReport001Service;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;

/**
 * 报送前检查服务
 * 〈功能详细描述〉
 * @author zhagnzhongliang
 * @version 1.0 2019年04月19日
 * @see CheckReportController
 * @since 1.0
 */
@RestController
@RequestMapping("/CheckReport")
public class CheckReportController extends BaseController{
    /**
     * 报送前检查service
     */
    @Autowired
    private CheckReport001Service checkReport001Service;

    /**
     * 查询列表
     *
     * @return Page
     */
    @PostMapping("/searchTable/{page}/{length}")
    public Page<CheckReport001Dto> searchTable(@RequestBody Map<String, Object> param){
        return checkReport001Service.searchTable(param);
    }

}
