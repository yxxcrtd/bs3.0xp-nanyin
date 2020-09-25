/*
 * 文件名：BatchProcessing001.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：tangliang
 * 修改时间：2017年9月19日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.service.chb3.system.batchprocessing.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.report.system.batchprocessing.dto.BatchProcessing001Dto;
import com.joyintech.tams.report.system.batchprocessing.service.BatchProcessing001Service;

/**
 * 批处理服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see BatchProcessingController
 * @since 1.0
 */
@RestController
@RequestMapping("/ChbBatchProcessing")
public class BatchProcessingController {
    /**
     * batchProcessing001Service
     */
    @Autowired
    private BatchProcessing001Service batchProcessing001Service;

    /**
     * 刷新列表
     * 
     * @return Page
     */
    @PostMapping("dealBatchProcess")
    public Page<BatchProcessing001Dto> dealBatchProcess(@RequestBody Map<String, Object> param){
        return batchProcessing001Service.dealBatchProcess(param);
    }
    /**
     * 检查画面元素
     * 
     * @return Page
     */
    @PostMapping("check")
    public AjaxResult check(@RequestBody Map<String, Object> param){
        //检查画面元素
        AjaxResult result = batchProcessing001Service.check(param);
        result.setData(DateUtil.format(new Date(), DateUtil.DATETIME_FORMAT_WITH_SSS_YEAR1));
        return result;
    }
    /**
     * 检查画面元素
     * 
     * @return Page
     */
    @PostMapping("reflushLog")
    public AjaxResult reflushLog(@RequestBody Map<String, Object> param){
        //检查画面元素
        AjaxResult result = batchProcessing001Service.reflushLog(param);
        return result;
    }
}
