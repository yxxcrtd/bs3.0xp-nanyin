/*
 * 文件名：ValProductInfoQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.reveal.reportconfirm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManage001Dto;
import com.joyintech.service.disclosure.information.reveal.reportconfirm.service.ReportConfirmManage001Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 金融产品交易查询
 * 
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValNetValueConfirm001Contoller
 * @since 1.0
 */
@RestController(value="serviceReportConfirmManage001Contoller_dtx")
@RequestMapping("/ReportConfirmManage001")
public class ReportConfirmManage001Contoller extends BaseController {

    /**
     * ValProductInfoQuery001Service
     */
    @Autowired
    private ReportConfirmManage001Service reportConfirmManage001Service;

    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ReportConfirmManage001Dto> findPage(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return reportConfirmManage001Service.findPage(page, length, param);
    }

    /**
     * 不分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/queryPageList")
    public List<ReportConfirmManage001Dto> queryPageList(@RequestBody Map<String, Object> param)
        throws Exception {
        return reportConfirmManage001Service.queryPageList(param);
    }

    /**
     * 流程完成后的操作
     * 
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping(value = "/doComplete/{action}")
    public void doComplete(@RequestBody ReportConfirmManage001Dto dto, @PathVariable("action") String action) {
        reportConfirmManage001Service.doComplete(dto, OperationType.valueOf(action));
    }

    /**
     * 取消确认
     * 
     * @param dto
     * @param action
     */
    @PostMapping(value = "/confirmCancle")
    public int confirmCancle(@RequestBody Map<String, Object> params) {
        return reportConfirmManage001Service.confirmCancle(params);
    }

    /**
     * 代办中修改数据时同步更新
     * 
     * @param dto
     * @param action
     */
    @PostMapping(value = "/findNewData")
    public ReportConfirmManage001Dto findNewData(@RequestBody ReportConfirmManage001Dto wflList) {
        return reportConfirmManage001Service.findNewData(wflList);
    }

    @GetMapping(value = "/getFileName")
    public String getFileName(String sysFilepath) {
        return reportConfirmManage001Service.getFileName(sysFilepath);
    }

    @GetMapping(value = "/getFormate")
    public String getFormate(String sysFilepath) {
        return reportConfirmManage001Service.getFormate(sysFilepath);
    }

    /**
     * 删除service
     * 
     * @param wflList
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping(value = "/deleteComfirmByPrimaryKey")
    public int deleteComfirmByPrimaryKey(@RequestBody ReportConfirmManage001Dto wflList) {
        return reportConfirmManage001Service.deleteComfirmByPrimaryKey(wflList);
    }

    /**
     * 删除文件
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteFile")
    public int deleteFile(@RequestBody Map<String, Object> param) {
        return reportConfirmManage001Service.deleteFile(param);
    }

}
