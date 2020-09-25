package com.joyintech.tams.disclosure.information.manage.workmanage.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.disclosure.common.common.service.BaseCommonService;
import com.joyintech.tams.disclosure.information.manage.workmanage.dto.ReportWorkManage003Dto;
import com.joyintech.tams.disclosure.information.manage.workmanage.service.ReportWorkManage003Service;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;

/**
 * 
 * 报表作业管理-预览产品查询
 * @author wanghaibin
 * @version 1.0 2018年12月05日
 * @see ValNetValueConfirm001Contoller
 * @since 1.0
 */
@Controller
@PageId("ReportWorkManage003")
@RequestMapping("/disclosure/information/manage/workmanage/ReportWorkManage003")
public class ReportWorkManage003Contoller extends BaseController{
	/**
     * ValProductInfoQuery001Service
     */
    @Autowired
    private ReportWorkManage003Service reportWorkManage003Service;
	@Autowired
    private BaseCommonService baseCommonService;
	/**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // 入账日期默认为系统平台日
        model.addAttribute("sysDate",DateUtil.format(baseCommonService.getSysDate(), "yyyy-MM-dd"));
        model.addAttribute("settedRule",this.getParam("settedRule"));
        model.addAttribute("hasProdTable",this.getParam("hasProdTable"));
        model.addAttribute("onlyView",this.getParam("onlyView"));
        return super.init(model);
    }
    /**
     * 获取产品信息
     *
     * @return 返回的数据
     * @throws IOException 
     * @exception Exception 异常
     * @since 1.0
     */
    @RequestMapping("/findProdPage")
    @ResponseBody
    public List<ReportWorkManage003Dto> findProdPage() throws IOException {
        return reportWorkManage003Service.findProdPage(RequestUtil.getPageParam());
    }
}
