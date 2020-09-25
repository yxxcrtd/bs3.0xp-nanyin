package com.joyintech.service.asset.common.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.service.asset.common.service.AutoBondCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 自动加券
 * 更新业务表
 */
@RestController("AutoBondCouponController_dtx")
@RequestMapping("/AutoBondCoupon")
public class AutoBondCouponController {

    @Autowired
    private AutoBondCouponService  autoBondCouponService;

    /**
     * 修改
     * @param list
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public void update(){
        autoBondCouponService.update();
    }
}
