/*
 * 文件名：ReportTemplate001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.repreportcheck.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.disclosure.information.config.repreportcheck.dto.ReportCheckDto;
import com.joyintech.tams.disclosure.information.config.repreportcheck.service.ReportCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 报表数据校验
 *
 * @author lishicheng
 * @version 1.0 2018年11月9日
 * @see ReportCheck001Controller
 * @since 1.0
 */
@Controller
@PageId("ReportCheck001")
@RequestMapping("/disclosure/information/config/repreportcheck/ReportCheck001")
public class ReportCheck001Controller extends BaseController {

    @Autowired
    private ReportCheckService reportCheckService;


    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ReportCheck001Controller.class);

    @Override
    public String init(Model model) {

        this.setSelectOptionToModel(model, DictCodeExt1.信披报表类别);
        return super.init(model);
    }



    /**
     * 左侧栏查询 <一句话功能简述> <功能详细描述>
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ReportCheckDto> findPage() {
        Page<ReportCheckDto> page = reportCheckService.findByPage(RequestUtil.getPageParam());
        return page;
    }

}
