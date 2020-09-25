/*
 * 文件名：ValElectReconciliation001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年7月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.controller;

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
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.dto.ValElectReconciliation001Dto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.service.ValElectReconciliation001Service;

/**
 * 电子对账controller 〈功能详细描述〉
 * 
 * @author tanxuan
 * @version 1.0 2019年12月9日
 * @see ValElectReconciliation001Controller
 * @since 1.0
 */
@RestController
@RequestMapping("/ValElectReconciliation001")
public class ValElectReconciliation001Controller {

    @Autowired
    private ValElectReconciliation001Service valElectReconciliation001Service;

    /**
     * 查询方法
     * 
     * @return Page<ValElectReconciliation001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValElectReconciliation001Dto> findPage(@RequestBody Map<String, Object> param,
                                                       @PathVariable("page") int page,
                                                       @PathVariable("length") int length)
        throws Exception {
        return valElectReconciliation001Service.findPage(page, length, param);
    }

    /**
     * 页面产品下拉框查询方法 <功能详细描述>
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/getFinprodId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> param,
                                              @PathVariable("page") int page,
                                              @PathVariable("length") int length) {
        return valElectReconciliation001Service.getFinprodId(page, length, param);
    }

    /**
     * 调整 <功能详细描述>
     * 
     * @param listdto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/adjustValue")
    public String adjustValue(@RequestBody List<ValElectReconciliation001Dto> listdto) {
        return valElectReconciliation001Service.adjustValue(listdto);
    }

}
