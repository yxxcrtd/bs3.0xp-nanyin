/*
 * 文件名：CfetsPortfolioAcctRela001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.bankuplink.basic.cfetsportfolioacctrela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.bankuplink.basic.cfetsportfolioacctrela.dto.CfetsPortfolioAcctRela001Dto;
import com.joyintech.tams.invest.bankuplink.basic.cfetsportfolioacctrela.service.CfetsPortfolioAcctRelaService;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;

/**
 * CFETS账户关联
 * 
 * @author yufei
 * @version 1.0 2020年5月11日
 * @see CfetsPortfolioAcctRela001Controller
 * @since 1.0
 */
@Controller
@PageId("CfetsPortfolioAcctRela001")
@RequestMapping("/invest/bankuplink/basic/cfetsportfolioacctrela/CfetsPortfolioAcctRela001")
public class CfetsPortfolioAcctRela001Controller extends BaseController {

    /**
     * {@inheritDoc}
     */
    @Autowired
    private CfetsPortfolioAcctRelaService cfetsPortfolioAcctRelaService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, BussinessDictCode.账户来源);
        return super.init(model);
    }

    /**
     * 查询机构信息
     * 
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/selectMstCfetsCustomerInfo")
    public Page<SelectOptionDto> selectCommonSelect() {
        return cfetsPortfolioAcctRelaService.selectCommonSelect(RequestUtil.getPageParam());
    }

    /**
     * 画面查询方法
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<CfetsPortfolioAcctRela001Dto> findPage()
        throws Exception {
        return cfetsPortfolioAcctRelaService.selectForPage(RequestUtil.getPageParam());
    }

    /**
     * 编辑时的check
     * 
     * @param dto 验证对象
     * @param result 验证结果
     * @return 验证信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody CfetsPortfolioAcctRela001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
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
        return cfetsPortfolioAcctRelaService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}
