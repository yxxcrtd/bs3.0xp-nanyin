/*
 * 文件名：ValManualVoucher002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete002Dto;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.service.ValManualVoucherTemplete002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 模板示例<br>
 * 手工凭证模板详情查看
 *
 * @author sunshuaishuai
 * @version 2.0 2021年2月8日
 * @see ValManualVoucherTemplete002Controller
 * @since 2.0
 */
@RestController(value="serviceValManualVoucherTemplete002Controller")
@RequestMapping("/${application.service}/ValManualVoucherTemplete002")
public class ValManualVoucherTemplete002Controller extends BaseController {

    @Autowired
    private ValManualVoucherTemplete002Service valManualVoucherTemplete002Service;

    /**
     * 根据模板编号bookId获取对应相同编号的所有数据
     *
     * @return 记账手工凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @PostMapping("/searchList")
    public List<ValManualVoucherTemplete002Dto> searchList(@RequestBody String tmpCode) {
        return valManualVoucherTemplete002Service.searchList(tmpCode);
    }

}
