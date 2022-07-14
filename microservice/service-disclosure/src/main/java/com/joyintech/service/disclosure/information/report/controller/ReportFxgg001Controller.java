
package com.joyintech.service.disclosure.information.report.controller;

import java.util.Map;

import com.joyintech.service.disclosure.information.report.service.ReportFxgg001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;


/**
 * 发行公告
 *
 * @author chenggaoling
 * @version 1.0 2022年07月14日
 * @see ReportFxgg001Controller
 * @since 1.0
 */
@RestController(value="serviceReportFxgg001Controller")
@RequestMapping("/ReportFxgg001")
public class ReportFxgg001Controller extends BaseController{
    
    @Autowired
    private ReportFxgg001Service reportFxgg001Service;
    
    
    @PostMapping("/findFxgg")
    public Map<String, Object> findFxgg(@RequestBody Map<String, Object> param) {
        return reportFxgg001Service.findFxgg(param);
    }
}
