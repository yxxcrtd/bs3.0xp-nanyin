/*
 * 文件名：RepFinProductRenewalRegistController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repfinproductrenewalregist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.RepDuration;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.debtreport.repfinproductrenewalregist.service.RepFinProductRenewalRegistService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author fanshuanghu
 * @version 1.0 2018年12月23日
 * @see RepFinProductRenewalRegistController
 * @since 1.0
 */
@RestController
@RequestMapping("/RepFinProductRenewalRegist")
public class RepFinProductRenewalRegistController {
    
    @Autowired
    private RepFinProductRenewalRegistService repFinProductRenewalRegistService;
    
    @PostMapping("findPage/{page}/{length}")
    public Page<RepDuration> findPage(@RequestBody Map<String,Object> map,
                                      @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return repFinProductRenewalRegistService.findPage(page,length,map);
    }
    /**
     * 获取金融产品代码
     * @return 金融产品代码集合
     * @see
     * @since 1.0
     */
    @PostMapping("getRepFinprodName")
    public List<SelectOptionDto> getRepFinprodName(@RequestBody Map<String,Object> map){
        return repFinProductRenewalRegistService.getRepFinprodName(map);
    }
    /**
     * 获取登记编号
     * @return 登记编号实体类
     * @see
     * @since 1.0
     */
    @PostMapping("getRepFinprodRegId")
    public List<SelectOptionDto> getRepFinprodRegId(@RequestBody Map<String,Object> map){
        return repFinProductRenewalRegistService.getRepFinprodRegId(map);
    }
}
