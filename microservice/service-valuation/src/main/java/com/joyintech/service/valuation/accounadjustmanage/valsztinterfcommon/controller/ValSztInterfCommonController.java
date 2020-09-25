/*
 * 文件名：TrdSpotexInvestTradeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年7月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valsztinterfcommon.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.accounadjustmanage.valsztinterfcommon.service.ValSztInterfCommonService;

/**
 * 深圳通页面公共方法controller 从xybranch20190115版本合并过来
 * 〈功能详细描述〉
 * @author yanya
 * @version 1.0 2019年11月2日
 * @see ValSztInterfCommonController
 * @since 1.0
 */
@RestController
@RequestMapping("/ValSztInterfCommon")
public class ValSztInterfCommonController {

    /**
     * 深圳通公共方法
     */
    @Autowired
    private ValSztInterfCommonService valSztInterfCommonService;

    @PostMapping("/getFinprodId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> param,
                                              @PathVariable("page") int page, @PathVariable("length") int length) {
        return valSztInterfCommonService.getFinprodId(page, length, param);
    }
    

}
