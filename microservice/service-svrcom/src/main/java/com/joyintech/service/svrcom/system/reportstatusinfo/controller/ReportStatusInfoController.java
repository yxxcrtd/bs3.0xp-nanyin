/*
 * 文件名：ReportStatusInfoController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：wanghai 修改时间：2021年05月10日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.svrcom.system.reportstatusinfo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.dto.ChbFeedbackFileManagementDto;
import com.joyintech.tams.report.system.reportstatusinfo.service.ReportStatusInfoService;

@RestController("reportStatusInfoController")
@RequestMapping("/ReportStatusInfo")
public class ReportStatusInfoController {

    @Autowired
    private ReportStatusInfoService reportStatusInfoService;

    /**
     * 画面查询方法-发送状态查询页面
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ChbFeedbackFileManagementDto> findPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                            @PathVariable("length") int length) {
        return reportStatusInfoService.findPage(page, length, param);
    }

}
