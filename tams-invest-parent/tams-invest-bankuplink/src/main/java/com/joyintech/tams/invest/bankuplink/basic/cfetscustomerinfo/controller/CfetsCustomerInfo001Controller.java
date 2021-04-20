/*
 * 文件名：MstCfetsCustomerTrader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.bankuplink.basic.cfetscustomerinfo.controller;

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
import com.joyintech.tams.invest.bankuplink.basic.cfetscustomerinfo.dto.CfetsCustomerInfo001Dto;
import com.joyintech.tams.invest.bankuplink.basic.cfetscustomerinfo.service.CfetsCustomerInfo001Service;

/**
 * 银行间机构信息 〈功能详细描述〉
 * 
 * @author yufei
 * @version 1.0 2020年5月12日
 * @see CfetsCustomerInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("CfetsCustomerInfo001")
@RequestMapping("/invest/bankuplink/basic/cfetscustomerinfo/CfetsCustomerInfo001")
public class CfetsCustomerInfo001Controller extends BaseController {

    /**
     * {@Autowired}
     */
    @Autowired
    private CfetsCustomerInfo001Service cfetsCustomerInfo001Service;

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
    public Page<CfetsCustomerInfo001Dto> selectAllMstCaptialAccount()
        throws Exception {
        Page<CfetsCustomerInfo001Dto> page = cfetsCustomerInfo001Service.selectForPage(
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
        return cfetsCustomerInfo001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}
