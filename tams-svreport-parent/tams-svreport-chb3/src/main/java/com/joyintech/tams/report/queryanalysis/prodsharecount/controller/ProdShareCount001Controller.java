/*
 * 文件名：ProdShareCount001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年6月29日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.queryanalysis.prodsharecount.controller;

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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.report.queryanalysis.prodsharecount.dto.ProdShareCount001Dto;
import com.joyintech.tams.report.queryanalysis.prodsharecount.service.ProdShareCountMService;

/**
 * 产品份额统计Controller
 * @author zuoyinyi
 * @version 1.0 2018年6月29日
 * @see ProdShareCount001Controller
 * @since 1.0
 */
@PageId("ProdShareCount001")
@RequestMapping("/svreport/queryanalysis/prodsharecount/ProdShareCount001")
@Controller
public class ProdShareCount001Controller extends BaseController{
    /**
     * 产品份额统计servive
     */
    @Autowired
    private ProdShareCountMService prodShareCountMService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        model.addAttribute("nowSysDate", DateUtil.getCurrentDate());
        return super.init(model);
    }

    /**
     *
     * 业务画面查询事件
     *
     * @return 结果列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<ProdShareCount001Dto> selectAllData() {
        Page<ProdShareCount001Dto> page = prodShareCountMService.searchPage(RequestUtil.getPageParam());
        return page;
    }

    /**
     *
     * 业务画面导出事件
     * @return 結果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() {
        List<ProdShareCount001Dto> dtoList = prodShareCountMService.setDataList(RequestUtil.getParameters(getRequest()));
        File outFile = prodShareCountMService.makeFile(
            "/com/joyintech/tams/report/queryanalysis/prodsharecount/conf/export_conf.xml", dtoList);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
                                 + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }
}
