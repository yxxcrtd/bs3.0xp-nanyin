/*
 * 文件名：ReportStatusInfo001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：徽银理财资管系统
 * 修改人：wanghai
 * 修改时间：2021年05月10日
 * 修改内容：新建
 * 系统名称：徽银理财资管系统
 */
 
package com.joyintech.tams.report.system.reportstatusinfo.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.system.reportstatusinfo.dto.ReportStatusInfo001Dto;
import com.joyintech.tams.report.system.reportstatusinfo.service.ReportStatusInfo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * 发送状态查询页面
 * 〈功能详细描述〉
 * @author wanghai
 * @version 1.0 2021年5月10日
 * @see ReportStatusInfo001Controller
 * @since 1.0
 */
@Controller
@RequestMapping("/svreport/system/reportstatusinfo/ReportStatusInfo001")
@PageId("ReportStatusInfo001")
public class ReportStatusInfo001Controller extends BaseController{
    /**
     * 报送信息反馈Service
     */
    @Autowired
    private ReportStatusInfo001Service reportStatusInfo001Service;
    
    @Override
    public String init(Model model) {
    	super.setSelectOptionToModel(model, DictCode.报表名称, DictCode.报送状态, DictCode.报送反馈状态);
        //解析页面跳转传过来的参数
        Map<String, Object> param = RequestUtil.getPageParam();
        String repType = (String) param.get("repType");
        param.put("repStatus", DictCodeListExt2.报送状态_接口报送.getValue());
        model.addAttribute("repStatus", DictCodeListExt2.报送状态_接口报送.getValue());
        if(StringUtil.isNotEmpty(repType)){
            //从其他页面跳转过来
            model.addAttribute("repType", repType);
            model.addAttribute("registSeqNo", param.get("registSeqNo"));
        }else{
            //从菜单点击进来
            String sysDate = DateUtil.format(new Date());
            model.addAttribute("sysDate", sysDate);
        }
    	return super.init(model);
    }
    /**
    * 取得查询数据
    * @return 存在于查询条件的数据
    */
    @RequestMapping(ReportGlobalConstant.SEARCH_PAGE)
    @ResponseBody
    public Page<ReportStatusInfo001Dto> search() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return reportStatusInfo001Service.searchInfo(param);
    }

}
