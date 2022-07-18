/*
 * 文件名：disProdPlanManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.disclosure.information.config.prodbonusplanmanage.controller;


import com.joyintech.framework.base.BaseController;

import com.joyintech.service.disclosure.information.config.prodbonusplanmanage.dto.ProdBonusplanDto;
import com.joyintech.service.disclosure.information.config.prodbonusplanmanage.service.ProdBonusplanManager002Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 报表模板管理新建
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author longhao
 * @version 1.0 2018年11月9日
 * @see ProdBonusplanManager002Controller
 * @since 1.0
 */
@RestController(value = "serviceProdBonusplanManager002Controller")
@RequestMapping("/ProdBonusplanManager002")
public class ProdBonusplanManager002Controller extends BaseController {

    @Autowired
    ProdBonusplanManager002Service prodBonusplanManager002Service;
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ProdBonusplanManager002Controller.class);

    @GetMapping("getProdBonusplanDto")
    public ProdBonusplanDto getProdBonusplanDto(String keyId) {
        return prodBonusplanManager002Service.getProdBonusplanDto(keyId);
    }


    @PostMapping("insert")
    public void insert(@RequestBody ProdBonusplanDto dto) {
        prodBonusplanManager002Service.insert(dto);
    }

    @PostMapping("update")
    public void update(@RequestBody ProdBonusplanDto dto) {
        prodBonusplanManager002Service.update(dto);
    }


}
