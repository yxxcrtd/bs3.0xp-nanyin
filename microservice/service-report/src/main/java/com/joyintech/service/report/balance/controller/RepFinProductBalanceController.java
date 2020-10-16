/*
 * 文件名：RepFinProductBalanceController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.balance.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.balance.dto.RepFinProductBalanceDto;
import com.joyintech.service.report.balance.service.RepFinProductBalanceService;

/**
 *  产品余额表
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月1日
 * @see RepFinProductBalanceController
 * @since 1.0
 */
@RestController("backRepFinProductBalanceController")
@RequestMapping("/RepFinProductBalance")
public class RepFinProductBalanceController {

    @Autowired
    private RepFinProductBalanceService repFinProductBalanceService;
    /**
     * 查询
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<RepFinProductBalanceDto> selectAll(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return repFinProductBalanceService.selectForPage(page, length, param);
    }
    
    @PostMapping("getFinprodIdByPortfolioId")
    public  List<SelectOptionDto> getFinprodIdByPortfolioId(@RequestBody Map<String,Object> map){
        return repFinProductBalanceService.getFinprodIdByPortfolioId(map);
    }
    
}
