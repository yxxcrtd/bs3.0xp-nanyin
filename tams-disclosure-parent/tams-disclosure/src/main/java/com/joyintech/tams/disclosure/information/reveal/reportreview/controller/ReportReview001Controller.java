package com.joyintech.tams.disclosure.information.reveal.reportreview.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.disclosure.information.reveal.reportreview.dto.ReportReview001Dto;
import com.joyintech.tams.disclosure.information.reveal.reportreview.service.ReportReview001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 *
 * 报表复核管理
 * @author zhengxiaolian
 * @version 1.0 2022年10月09日
 * @see ReportReview001Controller
 * @since 1.0
 */

@Controller
@PageId("ReportReview001")
@RequestMapping("/disclosure/information/reveal/reportreview/ReportReview001")
public class ReportReview001Controller extends BaseController {

    @Autowired
    private ReportReview001Service reportReview001Service;


    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCodeExt1.复核状态,DictCodeExt1.信披报表类别);
        return super.init(model);
    }

    @RequestMapping("getReportNameList")
    @ResponseBody
    public Page<SelectOptionDto> getReportNameList(){
        Map<String, Object> param= RequestUtil.getPageParam();
        Page<SelectOptionDto> prdInfoSelect = reportReview001Service.getReportNameList(param);
        return prdInfoSelect;
    }

    @RequestMapping("findPage")
    @ResponseBody
    public Page<ReportReview001Dto> findPage(){
        Map<String, Object> param= RequestUtil.getPageParam();
        Page<ReportReview001Dto> page = reportReview001Service.findPage(param);
        return page;
    }
}
