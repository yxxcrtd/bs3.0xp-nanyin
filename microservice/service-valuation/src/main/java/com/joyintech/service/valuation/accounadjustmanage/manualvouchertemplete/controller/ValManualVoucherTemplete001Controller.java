/*
 * 文件名：ValManualVoucher001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.controller;


import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete001Dto;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete001SearchDto;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.service.ValManualVoucherTemplete001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 模板示例<br>
 * 手工凭证模板查询
 *
 * @author sunshuaishuai
 * @version 2.0 2021年2月8日
 * @see ValManualVoucherTemplete001Controller
 * @since 2.0
 */
@RestController(value="serviceValManualVoucherTemplete001Controller")
@RequestMapping("/${application.service}/ValManualVoucherTemplete001")
public class ValManualVoucherTemplete001Controller extends BaseController {

    /**
     * ValManualVoucherTemplete001Service
     */
    @Autowired
    private ValManualVoucherTemplete001Service valManualVoucherTemplete001Service;

    /**
     * 记账手工凭证数据取得
     *
     * @return 记账手工凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValManualVoucherTemplete001Dto> findPage(@RequestBody Map<String, ValManualVoucherTemplete001SearchDto> map,
                                                         @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        ValManualVoucherTemplete001SearchDto dto = map.get("dto");
        return valManualVoucherTemplete001Service.findPage(page,length,dto);
    }

    /**
     *
     * 根据条件查询全部信息
     * @param dto 筛选条件
     * @return 全部信息
     * @see
     * @since 1.0
     */
    @PostMapping("/selectAll")
    public List<ValManualVoucherTemplete001Dto> selectAll(@RequestBody ValManualVoucherTemplete001SearchDto dto) {
        return valManualVoucherTemplete001Service.selectAll(dto);
    }

}
