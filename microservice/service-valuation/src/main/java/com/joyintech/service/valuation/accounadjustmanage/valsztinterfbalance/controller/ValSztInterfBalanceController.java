/*
 * 文件名：TrdSpotexInvestTradeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年7月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valsztinterfbalance.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.accounadjustmanage.valsztinterfbalance.dto.ValSztInterfBalanceDto;
import com.joyintech.service.valuation.accounadjustmanage.valsztinterfbalance.service.ValSztInterfBalanceService;

/**
 * 深证通科目余额对账查询controller 从xybranch20190115版本合并过来
 * 〈功能详细描述〉
 * @author yanya
 * @version 1.0 2019年11月2日
 * @see ValSztInterfBalanceController
 * @since 1.0
 */
@RestController
@RequestMapping("/ValSztInterfBalance")
public class ValSztInterfBalanceController {

    /**
     * 深证通科目余额对账
     */
    @Autowired
    private ValSztInterfBalanceService valSztInterfBalanceService;

    /**
     * 查询方法
     * @return Page<ValSztInterfSubjectDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValSztInterfBalanceDto> findPage(@RequestBody Map<String, Object> param,
                                                 @PathVariable("page") int page, @PathVariable("length") int length)
                                                     throws Exception {
        return valSztInterfBalanceService.findPage(page, length, param);
    }
    

}
