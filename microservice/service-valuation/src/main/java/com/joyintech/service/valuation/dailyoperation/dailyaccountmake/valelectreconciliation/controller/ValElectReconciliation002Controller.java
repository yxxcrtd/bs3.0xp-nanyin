/*
 * 文件名：ValElectReconciliation002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年7月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.dto.ValElectReconciliation002Dto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.service.ValElectReconciliation002Service;

/**
 * 电子对账controller 〈功能详细描述〉
 * 
 * @author tanxuan
 * @version 1.0 2019年12月9日
 * @see ValElectReconciliation002Controller
 * @since 1.0
 */
@RestController("backValElectReconciliation002Controller")
@RequestMapping("/ValElectReconciliation002")
public class ValElectReconciliation002Controller {

    @Autowired
    private ValElectReconciliation002Service valElectReconciliation002Service;

    /**
     * 对账明细查询方法
     * 
     * @return Page<ValElectReconciliation002Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValElectReconciliation002Dto> findPage(@RequestBody Map<String, Object> param,
                                                       @PathVariable("page") int page,
                                                       @PathVariable("length") int length)
        throws Exception {
        return valElectReconciliation002Service.findPage(page, length, param);
    }

}
