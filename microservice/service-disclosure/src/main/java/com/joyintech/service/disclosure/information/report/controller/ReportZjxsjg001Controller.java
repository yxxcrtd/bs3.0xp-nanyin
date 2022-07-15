
package com.joyintech.service.disclosure.information.report.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.service.disclosure.information.report.service.ReportZjxsjg001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 发行公告
 *
 * @author chenggaoling
 * @version 1.0 2022年07月14日
 * @see ReportZjxsjg001Controller
 * @since 1.0
 */
@RestController(value="ReportZjxsjg001Controller")
@RequestMapping("/ReportZjxsjg001")
public class ReportZjxsjg001Controller extends BaseController{
    
    @Autowired
    private ReportZjxsjg001Service reportZjxsjg001Service;
    
    
    @PostMapping("/findZjxsjg")
    public Map<String, Object> findFxgg(@RequestBody Map<String, Object> param) {
        return reportZjxsjg001Service.findZjxsjg(param);
    }
}
