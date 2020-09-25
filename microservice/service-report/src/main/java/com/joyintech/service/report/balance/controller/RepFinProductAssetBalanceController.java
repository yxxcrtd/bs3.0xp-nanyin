/*
 * 文件名：RepFinProductAssetBalanceController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月5日
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
import com.joyintech.service.report.balance.dto.RepFinProductAssetBalanceDto;
import com.joyintech.service.report.balance.service.RepFinProductAssetBalanceService;

/**
 *  资产余额表
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月5日
 * @see RepFinProductAssetBalanceController
 * @since 1.0
 */
@RestController
@RequestMapping("/RepFinProductAssetBalance")
public class RepFinProductAssetBalanceController {

    @Autowired
    private RepFinProductAssetBalanceService repFinProductAssetBalanceService;
    @PostMapping("findPage/{page}/{length}")
    public Page<RepFinProductAssetBalanceDto> selectAll(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return repFinProductAssetBalanceService.selectForPage(page, length, param);
    }
    
    /**
     * 汇总（导出）
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("selectAllListSum")
    public List<RepFinProductAssetBalanceDto> selectAllListSum(@RequestBody Map<String, Object> param) throws Exception {
        return repFinProductAssetBalanceService.selectAllListSum(param);
    }
    
    
    /**
     *  底层明细查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectAllList")
    public List<RepFinProductAssetBalanceDto> selectAllList(@RequestBody Map<String, Object> param) throws Exception {
        return repFinProductAssetBalanceService.selectAllList(param);
    }
    
    
    
}
