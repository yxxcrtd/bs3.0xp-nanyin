/*
 * 文件名：BussSettleOrderCommController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：wanghai 修改时间：2021年05月10日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.svrcom.system.chbfeedbackfilemanagement.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.dto.ChbFeedbackFileManagementDto;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.dto.ChbFeedbackFileMatchInfoDto;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.service.ChbFeedbackFileManagementService;


@RestController("chbFeedbackFileManagementController")
@RequestMapping("/ChbFeedbackFileManagement")
public class ChbFeedbackFileManagementController {

    @Autowired
    private ChbFeedbackFileManagementService chbFeedbackFileManagementService;

    /**
     * 画面查询方法-中债反馈文件数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ChbFeedbackFileManagementDto> findPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                            @PathVariable("length") int length) {
        return chbFeedbackFileManagementService.findPage(page, length, param);
    }

    /**
     * 查询反馈文件匹配信息
     * @return
     */
    @PostMapping("queryMatchInfo")
    public ChbFeedbackFileMatchInfoDto queryMatchInfo(@RequestBody Map<String, Object> param){
        String repSeqNo = (String) param.get("repSeqNo");
        String rtnRepMesNo = (String) param.get("rtnRepMesNo");
        String filePath = (String) param.get("filePath");
        String fileName = (String) param.get("fileName");
        String reportEtrType = (String) param.get("reportEtrType");
        return chbFeedbackFileManagementService.queryMatchInfo(repSeqNo, rtnRepMesNo, filePath, fileName, reportEtrType);
    }

    /**
     * 文件处理
     * @param dto
     */
    @PostMapping("fileHandle")
    public String fileHandle(@RequestBody ChbFeedbackFileMatchInfoDto dto){
        return chbFeedbackFileManagementService.fileHandle(dto);
    }

    /**
     * 文件处理
     * @param dto
     */
    @PostMapping("backupFile")
    public String backupFile(@RequestBody ChbFeedbackFileManagementDto dto){
        return chbFeedbackFileManagementService.backupFile(dto);
    }

}
