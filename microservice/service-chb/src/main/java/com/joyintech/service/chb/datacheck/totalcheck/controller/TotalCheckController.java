/*
 * 文件名：TotalCheckController.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：魏玉强
 * 修改时间：2019年05月16日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.service.chb.datacheck.totalcheck.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.svreport.chb.datacheck.totalcheck.dto.ChbTotalCheckDetailDto;
import com.joyintech.tams.svreport.chb.datacheck.totalcheck.dto.ChbTotalCheckDto;
import com.joyintech.tams.svreport.chb.datacheck.totalcheck.service.TotalCheck001Service;


/**
 * 延时报送检查服务
 * 〈功能详细描述〉
 * @author zhagnzhongliang
 * @version 1.0 2019年04月19日
 * @see TotalCheckController
 * @since 1.0
 */
@RestController
@RequestMapping("/TotalCheck")
public class TotalCheckController{
    
    /**
     * 总体核查Service
     */
    @Autowired
    private TotalCheck001Service totalCheck001Service;

    
    /**
     * 延时报送检查的查询方法
     * @param param 存储了查询参数的Map
     * @return Map 存储了查询结果的Map
     * @see
     * @since 1.0
     */
    @PostMapping("/searchData")
    public Map<String,ChbTotalCheckDto> searchData(@RequestBody Map<String, Object> param){
        return totalCheck001Service.searchData(param);
    }
    
    /**
     * 延时报送检查的查询方法
     * @param param 存储了查询参数的Map
     * @return Map 存储了查询结果的Map
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ChbTotalCheckDetailDto> searchTable(@RequestBody Map<String, Object> param){
        return totalCheck001Service.searchTable(param);
    }
}
