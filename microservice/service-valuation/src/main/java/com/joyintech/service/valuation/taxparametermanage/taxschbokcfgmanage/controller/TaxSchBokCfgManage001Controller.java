/*
 * 文件名：TaxScheme001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.taxparametermanage.taxschbokcfgmanage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.taxparametermanage.taxschbokcfgmanage.dto.TaxSchBokCfgManage001Dto;
import com.joyintech.service.valuation.taxparametermanage.taxschbokcfgmanage.service.TaxSchBokCfgManage001Service;

/**
 * 场景计税配置管理
 * 
 * @author zhangwei
 * @version 1.0 2018年10月10日
 * @see TaxSchBokCfgManage001Controller
 * @since 1.0
 */
@RestController("serviceTaxSchBokCfgManage001Controller")
@RequestMapping("/TaxSchBokCfgManage001")
public class TaxSchBokCfgManage001Controller extends BaseController {

    @Autowired
    private TaxSchBokCfgManage001Service taxSchBokCfgManage001Service;

    /**
     * 获取场景计税配置管理 - 分页
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/findTaxSchaBokConfig/{page}/{length}")
    public Page<TaxSchBokCfgManage001Dto> findTaxSchaBokConfig(@RequestBody Map<String, Object> param,
                                                               @PathVariable("page") int page,
                                                               @PathVariable("length") int length)
        throws Exception {
        return taxSchBokCfgManage001Service.findTaxSchaBokConfigByPage(page, length, param);
    }

    /**
     * 获取场景计税配置管理 - 分页
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/findTaxSchaBokConfig")
    public List<TaxSchBokCfgManage001Dto> findTaxSchaBokConfig(@RequestBody Map<String, Object> param) {
        return taxSchBokCfgManage001Service.findTaxSchaBokConfig(param);
    }

    /**
     * 统计配置使用数
     * @param configId
     * @return
     */
    @GetMapping("/countUsed")
    public int countUsed(String configId) {
        return taxSchBokCfgManage001Service.countUsed(configId);
    }

}
