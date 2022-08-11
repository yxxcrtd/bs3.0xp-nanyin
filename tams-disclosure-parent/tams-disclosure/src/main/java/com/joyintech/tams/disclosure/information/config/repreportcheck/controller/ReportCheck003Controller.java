package com.joyintech.tams.disclosure.information.config.repreportcheck.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.base.entity.RepDataCheck;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.disclosure.information.config.repreportcheck.service.ReportCheck003Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@PageId("ReportCheck003")
@RequestMapping("/disclosure/information/config/repreportcheck/ReportCheck003")
public class ReportCheck003Controller extends BaseController {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ReportCheck003Controller.class);

    @Autowired
    private ReportCheck003Service reportCheck003Service;

    @Override
    public String init(Model model) {
        String fileNo=this.getParam("fileNo");
        model.addAttribute("fileNo",fileNo);

        return super.init(model);
    }

    @RequestMapping(value="/previewReport", method = RequestMethod.POST)
    public void previewReport(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> params = RequestUtil.getPageParam();
        reportCheck003Service.previewReport(params.get("fileNo").toString(),response);
    }
}
