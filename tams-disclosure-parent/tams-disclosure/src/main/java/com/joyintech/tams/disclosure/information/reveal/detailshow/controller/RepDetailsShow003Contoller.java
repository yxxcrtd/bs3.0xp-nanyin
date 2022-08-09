package com.joyintech.tams.disclosure.information.reveal.detailshow.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.information.reveal.detailshow.service.RepDetailsShow001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 模板示例<br>
 * 模板预览,该页面供其他页面引用
 * @author 何阳阳
 * @version 1.0 2018年3月13日
 * @see RepDetailsShow003Contoller
 * @since 1.0
 */

@Controller
@PageId("RepDetailsShow003")
@RequestMapping("/disclosure/information/reveal/detailshow/RepDetailsShow003")
public class RepDetailsShow003Contoller extends BaseController {

    @Autowired
    private RepDetailsShow001Service repDetailsShow001Service;

    @Autowired
    private GetPrdInfoSelectService getPrdInfoSelectService;
	/**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    
    public String init(Model model) {
        model.addAttribute(ReportGlobalConstant.REPORT_SERVER_URL, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_URL));
        model.addAttribute(ReportGlobalConstant.REPORT_USERNAME, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_USERNAME));
        model.addAttribute(ReportGlobalConstant.REPORT_PASSWORD, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_PASSWORD));
        model.addAttribute(ReportGlobalConstant.REPORT_SERVER_PATH, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_PATH));

        model.addAttribute("repId",getParam("repId"));
        model.addAttribute("repName",getParam("repName"));

        model.addAttribute("cureDate", DateUtil.getCurrentDate());
        return super.init(model);
    }
    
}