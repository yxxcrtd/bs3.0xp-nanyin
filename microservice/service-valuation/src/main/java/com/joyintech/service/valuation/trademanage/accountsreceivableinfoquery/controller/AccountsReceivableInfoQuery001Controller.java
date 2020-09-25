/*
 * 文件名：AccountsReceivableInfoQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangzhao
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.trademanage.accountsreceivableinfoquery.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.trademanage.accountsreceivableinfoquery.dto.AccountsReceivableInfoQuery001Dto;
import com.joyintech.service.valuation.trademanage.accountsreceivableinfoquery.service.AccountsReceivableInfoQuery001Service;

/**
 * 应收款项信息查询
 * 
 * @author zhangzhao
 * @version 1.0 2018年10月9日
 * @see AccountsReceivableInfoQuery001Controller
 * @since 1.0
 */
@RestController(value="serviceAccountsReceivableInfoQuery001Controller")
@RequestMapping("/AccountsReceivableInfoQuery001")
public class AccountsReceivableInfoQuery001Controller extends BaseController {

    @Autowired
    private AccountsReceivableInfoQuery001Service accountsReceivableInfoQuery001Service;

    @PostMapping("/findPage/{page}/{length}")
    public Page<AccountsReceivableInfoQuery001Dto> findPage(@RequestBody Map<String, Object> param,
                                                            @PathVariable("page") int page,
                                                            @PathVariable("length") int length)
        throws Exception {
        return accountsReceivableInfoQuery001Service.findByPage(page, length, param);
    }

    @PostMapping("/findPageout")
    public List<AccountsReceivableInfoQuery001Dto> findPageout(@RequestBody Map<String, Object> param)
        throws Exception {
        return accountsReceivableInfoQuery001Service.findPageout(param);
    }

    /**
     * 标的资产下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    @PostMapping("/getBdFinprodId")
    public List<SelectOptionDto> getBdFinprodId(@RequestBody Map<String, Object> param)
        throws Exception {
        return accountsReceivableInfoQuery001Service.getBdFinprodId(param);
    }

}
