/*
 * 文件名：RepAssetDeptRegistController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年1月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repassetdeptregist.controller;

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
import com.joyintech.service.report.debtreport.repassetdeptregist.dto.RepAssetDeptRegistDto;
import com.joyintech.service.report.debtreport.repassetdeptregist.service.RepAssetDeptRegistService;

/**
 * 资产要素登记
 * @author fanshuanghu
 * @version 1.0 2019年1月19日
 * @see RepAssetDeptRegistController
 * @since 1.0
 */
@RestController
@RequestMapping("/RepAssetDeptRegist")
public class RepAssetDeptRegistController {
    @Autowired
    private RepAssetDeptRegistService repAssetDeptRegistService;
    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<RepAssetDeptRegistDto> findPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return repAssetDeptRegistService.selectForPage(page, length, param);
    }
    /**
     * 获取资产代码集合
     * @param map 请求参数
     * @return 资产代码集合
     * @see
     * @since 1.0
     */
    @PostMapping("getRepAssetCode")
    public List<SelectOptionDto> getRepAssetCode(@RequestBody Map<String,Object> map){
        return repAssetDeptRegistService.getRepAssetCode(map);
    }
}
