package com.joyintech.service.disclosure.information.reveal.reportreveal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.service.disclosure.information.reveal.reportreveal.service.ReportRevealManage003Service;

/**
 * 附件上传
 * 
 * @author Administrator
 */
@RestController(value="serviceReportRevealManage003Contoller")
@RequestMapping("/ReportRevealManage003")
public class ReportRevealManage003Contoller extends BaseController {

    /**
     * 
     */
    @Autowired
    private ReportRevealManage003Service reportRevealManage003Service;

    @PostMapping("/updateRepRevealById")
    public int updateRepRevealById(@RequestBody Map<String, Object> param) {
        return reportRevealManage003Service.updateRepRevealById(param);
    }


}
