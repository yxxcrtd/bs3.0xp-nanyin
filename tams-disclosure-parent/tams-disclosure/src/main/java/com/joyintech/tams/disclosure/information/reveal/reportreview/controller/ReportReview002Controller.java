package com.joyintech.tams.disclosure.information.reveal.reportreview.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.base.entity.SysUser;
import com.joyintech.tams.disclosure.information.reveal.reportreview.service.ReportReview002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@PageId("ReportReview002")
@RequestMapping("/disclosure/information/reveal/reportreview/ReportReview002")
public class ReportReview002Controller extends BaseController {
    @Autowired
    private ReportReview002Service reportReview002Service;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    @RequestMapping("findPage")
    @ResponseBody
    public Page<SelectOptionDto> findPage(){
        Map<String, Object> param= RequestUtil.getPageParam();
        Page<SelectOptionDto> prdInfoSelect = reportReview002Service.findPage(param);
        return prdInfoSelect;
    }
}
