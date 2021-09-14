/*
 * 文件名：ChbFeedbackFileManagement002Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：徽银理财资管系统
 * 修改人：wanghai
 * 修改时间：2021年05月10日
 * 修改内容：新建
 * 系统名称：徽银理财资管系统
 */
 
package com.joyintech.tams.report.system.chbfeedbackfilemanagement.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.dto.ChbFeedbackFileManagement002Dto;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.dto.ChbFeedbackFileMatchDetailDto;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.service.ChbFeedbackFileManagement002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 中债系统报送反馈文件匹配处理
 * 〈功能详细描述〉
 * @author wanghai
 * @version 1.0 2021年5月10日
 * @see ChbFeedbackFileManagement002Controller
 * @since 1.0
 */
@Controller
@RequestMapping("/svreport/system/chbfeedbackfilemanagement/ChbFeedbackFileManagement002")
@PageId("ChbFeedbackFileManagement002")
public class ChbFeedbackFileManagement002Controller extends BaseController{
    /**
     * 报送信息反馈Service
     */
    @Autowired
    private ChbFeedbackFileManagement002Service chbFeedbackFileManagement002Service;
    
    @Override
    public String init(Model model) {
        ChbFeedbackFileManagement002Dto entity = chbFeedbackFileManagement002Service
                .queryMatchInfo(RequestUtil.getPageParam());
        model.addAttribute("entity", entity);
        model.addAttribute("MATCHED_SUCCESS", DictCodeListExt3.中债_匹配结果_匹配成功.getValue());
    	return super.init(model);
    }

    /**
    * 取得查询数据
    * @return 存在于查询条件的数据
    */
    @RequestMapping("fileHandle")
    @ResponseBody
    public AjaxResult fileHandle(@RequestBody ChbFeedbackFileManagement002Dto dto) {
        return chbFeedbackFileManagement002Service.fileHandle(dto);
    }
}
