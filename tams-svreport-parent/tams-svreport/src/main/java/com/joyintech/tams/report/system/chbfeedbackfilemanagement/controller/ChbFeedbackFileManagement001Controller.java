/*
 * 文件名：ChbFeedbackFileManagement001Controller.java
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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.dto.ChbFeedbackFileManagement001Dto;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.service.ChbFeedbackFileManagement001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 中债系统报送反馈文件信息
 * 〈功能详细描述〉
 * @author wanghai
 * @version 1.0 2021年5月10日
 * @see ChbFeedbackFileManagement001Controller
 * @since 1.0
 */
@Controller
@RequestMapping("/svreport/system/chbfeedbackfilemanagement/ChbFeedbackFileManagement001")
@PageId("ChbFeedbackFileManagement001")
public class ChbFeedbackFileManagement001Controller extends BaseController{
    /**
     * 报送信息反馈Service
     */
    @Autowired
    private ChbFeedbackFileManagement001Service chbFeedbackFileManagement001Service;
    
    @Override
    public String init(Model model) {
    	super.setSelectOptionToModel(model, DictCode.报表名称, DictCode.中债一二三期文件处理状态);
        //解析页面跳转传过来的参数
        Map<String, Object> param = RequestUtil.getPageParam();
        String repSeqNo = (String) param.get("repSeqNo");
        String reportEtrType = (String) param.get("reportEtrType");
        String handleStatus = (String) param.get("handleStatus");
        String reportSendDateStr = (String) param.get("reportSendDate");
        String reportAcceptDateStr = (String) param.get("reportAcceptDate");
        model.addAttribute("repSeqNo", repSeqNo);
        model.addAttribute("reportEtrType", reportEtrType);
        model.addAttribute("handleStatus", handleStatus);
        model.addAttribute("reportSendDate", reportSendDateStr);
        model.addAttribute("reportAcceptDate", reportAcceptDateStr);
        if(StringUtil.isNotEmpty(reportEtrType) || StringUtil.isNotEmpty(handleStatus)
                || StringUtil.isNotEmpty(reportSendDateStr) || StringUtil.isNotEmpty(reportAcceptDateStr)
                || StringUtil.isNotEmpty(repSeqNo)){
            model.addAttribute("searchOnInit", "Y");
        }
    	return super.init(model);
    }
    /**
    * 取得查询数据
    * @return 存在于查询条件的数据
    */
    @RequestMapping(ReportGlobalConstant.SEARCH_PAGE)
    @ResponseBody
    public Page<ChbFeedbackFileManagement001Dto> search() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return chbFeedbackFileManagement001Service.searchInfo(param);
    }

    /**
    * 取得查询数据
    * @return 存在于查询条件的数据
    */
    @RequestMapping("backup")
    @ResponseBody
    public AjaxResult backup(@RequestBody ChbFeedbackFileManagement001Dto dto) {
        return chbFeedbackFileManagement001Service.backup(dto);
    }
}
