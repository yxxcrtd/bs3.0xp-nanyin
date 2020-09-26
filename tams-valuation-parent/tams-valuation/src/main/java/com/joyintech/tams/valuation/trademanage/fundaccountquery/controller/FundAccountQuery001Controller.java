/*
 * 文件名：FundAccountQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.fundaccountquery.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.trademanage.fundaccountquery.dto.FundAccountQuery001Dto;
import com.joyintech.tams.valuation.trademanage.fundaccountquery.service.FundAccountQuery001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 资金账户查询-Controller
 * 
 * @author niuqianyu
 * @version 1.0 2018年10月11日
 * @see FundAccountQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("FundAccountQuery001")
@RequestMapping("/valuation/trademanage/fundaccountquery/FundAccountQuery001")
public class FundAccountQuery001Controller extends BaseController {

    @Autowired
    private FundAccountQuery001Service fundAccountQuery001Service;

    /**
     * 资金账户查询页面初始化 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.资金账户类型);
        return super.init(model);
    }

    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<FundAccountQuery001Dto> findPage()
        throws Exception {
        return fundAccountQuery001Service.queryFundAccountList(RequestUtil.getPageParam());
    }

    /**
     * 导出数据
     * 
     * @return ResponseEntity<byte[]>
     * @throws Exception ExportUtil.export
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        String datas = this.getParam("datas");
        List<FundAccountQuery001Dto> listdto;
        if (datas == null) {
            listdto = fundAccountQuery001Service.findAll(RequestUtil.getPageParam());
        }
        else {
            listdto = JsonUtils.json2List(datas, FundAccountQuery001Dto.class);
        }
        File outFile = fundAccountQuery001Service.makeFile(
            "/com/joyintech/tams/valuation/trademanage/fundaccountquery/conf/export_fundaccountquery001.xml",
            listdto);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date())
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }

}
