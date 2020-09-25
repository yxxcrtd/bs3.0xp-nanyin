package com.joyintech.service.disclosure.information.reveal.reportconfirm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.service.disclosure.information.reveal.reportconfirm.service.ReportConfirmManage004Service;

@RestController(value="serviceReportConfirmManage004Contoller")
@RequestMapping("/ReportConfirmManage004")
public class ReportConfirmManage004Contoller extends BaseController {

    @Autowired
    ReportConfirmManage004Service reportConfirmManage004Service;

    @PostMapping("/querySameCount")
    public int querySameCount(@RequestBody Map<String, Object> params)
        throws Exception {
        return reportConfirmManage004Service.querySameCount(params);
    }

}
