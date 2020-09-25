/*
 * 文件名：RepRaiseSumRegist.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repraisesumregist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.RepRaiseSum;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.debtreport.repraisesumregist.service.RepRaiseSumRegistService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 募集期总量登记
 * @author fanshuanghu
 * @version 1.0 2018年12月28日
 * @see RepRaiseSumRegistController
 * @since 1.0
 */
@RestController
@RequestMapping("/RepRaiseSumRegist")
public class RepRaiseSumRegistController {
    
    @Autowired
    private RepRaiseSumRegistService repRaiseSumRegistService;
    
    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<RepRaiseSum> findPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return repRaiseSumRegistService.findPage(page, length, param);
    }
    
    /**
     * 获取金融产品代码下拉框集合
     * @return 金融产品代码下拉框集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getProductCode")
    public List<SelectOptionDto> getProductCode(@RequestBody Map<String,Object> map){
        return repRaiseSumRegistService.getProductCode(map);
    }
}
