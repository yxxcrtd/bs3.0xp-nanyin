/*
 * 文件名：MstCfetsCustomerTrader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.bankuplink.basic.cfetscustomertrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.bankuplink.basic.cfetscustomertrader.dto.CfetsCustomerTrader001Dto;
import com.joyintech.tams.invest.bankuplink.basic.cfetscustomertrader.service.CfetsCustomerTrader001Service;

/**
 * 银行间交易员信息 〈功能详细描述〉
 * 
 * @author yufei
 * @version 1.0 2020年5月12日
 * @see CfetsCustomerTrader001Controller
 * @since 1.0
 */
@Controller
@PageId("CfetsCustomerTrader001")
@RequestMapping("/invest/bankuplink/basic/cfetscustomertrader/CfetsCustomerTrader001")
public class CfetsCustomerTrader001Controller extends BaseController {

    /**
     * {@Autowired}
     */
    @Autowired
    private CfetsCustomerTrader001Service cfetsCustomerTrader001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 页面查询，表格分页 <功能详细描述>
     * 
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<CfetsCustomerTrader001Dto> selectAllMstCaptialAccount()
        throws Exception {
        Page<CfetsCustomerTrader001Dto> page = cfetsCustomerTrader001Service.selectForPage(
            RequestUtil.getPageParam());
        return page;
    }

    /**
     * 文件导出 <一句话功能简述> <功能详细描述>
     * 
     * @return ResponseEntity
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return cfetsCustomerTrader001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}
