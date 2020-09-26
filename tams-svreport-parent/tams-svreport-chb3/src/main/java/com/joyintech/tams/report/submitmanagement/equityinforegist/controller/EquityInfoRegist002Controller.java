/*
 * 文件名：EquityInfoRegist002Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：Administrator
 * 修改时间：2017年9月28日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.submitmanagement.equityinforegist.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dto.EquityInfoRegist001Dto;
import com.joyintech.tams.report.submitmanagement.equityinforegist.service.EquityInfoRegist001Service;
import com.joyintech.tams.report.submitmanagement.equityinforegist.service.EquityInfoRegistMService;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2017年9月28日
 * @see EquityInfoRegist002Controller
 * @since 1.0
 */
@Controller
@PageId("EquityInfoRegist002")
@RequestMapping("/svreport/submitmanagement/equityinforegist/EquityInfoRegist002")
public class EquityInfoRegist002Controller extends BaseController {
    
    @Autowired
    private EquityInfoRegistMService equityInfoRegistMService;
    
    @Autowired
    private EquityInfoRegist001Service equityInfoRegist001Service;
    /**
     * 初始化
     * {@inheritDoc}
     */
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        String deleteKeys = this.getParam("deleteKeys");
        if (StringUtil.isNotEmpty(deleteKeys)) {
            model.addAttribute(GlobalConstant.ENTITY,deleteKeys);
        }
        return super.init(model);
    }
    @RequestMapping(value = "/exportDel")
    public ResponseEntity<byte[]> exportDel() {
        List<EquityInfoRegist001Dto> list = this.equityInfoRegistMService
            .queryExportRegistDelList(RequestUtil.getPageParam());
        File outFile = equityInfoRegist001Service.makeFile(
            "/com/joyintech/tams/report/submitmanagement/equityinforegist/conf/export_conf.xml", list);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS + GlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }
}
