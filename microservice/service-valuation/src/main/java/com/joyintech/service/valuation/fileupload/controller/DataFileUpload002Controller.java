/*
 * 文件名：FileUpload002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.fileupload.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joyintech.service.valuation.assetsamortize.dto.ValAssetsAmortizeSet001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.fileupload.service.DataFileUpload002Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 文件上传
 * 
 * @author zhangwei
 * @version 1.0 2018年11月14日
 * @see FileUpload002Controller
 * @since 1.0
 */
//@RestController(value="serviceDataFileUpload002Controller")
@RestController("dataFileUpload002Ctl_dtx")
@RequestMapping("/DataFileUpload002")
public class DataFileUpload002Controller extends BaseController {

    @Autowired
    DataFileUpload002Service dataFileUpload002Service;

    @PostMapping("/insertFileTransferInfo")
    public int insertFileTransferInfo(@RequestBody Map<String, Object> map) throws Exception {
        return dataFileUpload002Service.insertFileTransferInfo(map);
    }

    @PostMapping("/countByCondition")
    public int countByCondition(@RequestBody Map<String, Object> map) {
        return dataFileUpload002Service.countByCondition(map);
    }
}
