/*
 * 文件名：FileUpload001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.fileupload.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.fileupload.dto.DataFileUpload001Dto;
import com.joyintech.service.valuation.fileupload.service.DataFileUpload001Service;

/**
 * 文件上传
 * @author zhangwei
 * @version 1.0 2018年11月14日
 * @see FileUpload001Controller
 * @since 1.0
 */
//@RestController(value="serviceDataFileUpload001Controller")
@RestController("dataFileUpload001Ctl_dtx")
@RequestMapping("/DataFileUpload001")
public class DataFileUpload001Controller extends BaseController {

    @Autowired
    DataFileUpload001Service dataFileUpload001Service;
    
    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 根据条件分页查询
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<DataFileUpload001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return dataFileUpload001Service.findPage(page, length, param);
    }
    
    /**
     * 删除
     * 
     * @return
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public AjaxResult deleteScheme(@RequestBody DataFileUpload001Dto dto) throws Exception {
        String msg = StringUtils.EMPTY;

        dataFileUpload001Service.deleteByPrimaryKey(dto.getFileId());
        msg = "删除成功";
        return new AjaxResult(msg);
    }
    
}
