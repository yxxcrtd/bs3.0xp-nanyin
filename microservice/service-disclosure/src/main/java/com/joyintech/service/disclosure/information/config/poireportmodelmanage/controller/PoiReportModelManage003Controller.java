package com.joyintech.service.disclosure.information.config.poireportmodelmanage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.service.disclosure.information.config.poireportmodelmanage.service.PoiReportModelManage003Service;

/**
 * 报表模板管理-导出报表
 * 
 * @author zhangyf
 * @version 1.0 2020年2月21日
 * @see PoiReportModelManage003Controller
 * @since 1.0
 */
@RestController(value = "servicePoiReportModelManage003Controller")
@RequestMapping("/PoiReportModelManage003")
public class PoiReportModelManage003Controller extends BaseController {

	@Autowired
	private PoiReportModelManage003Service poiReportModelManage003Service;
	
	@PostMapping("createRepReveal")
    public void createRepReveal(@RequestBody Map<String, Object> param) throws Exception {
		poiReportModelManage003Service.createRepReveal(param);
    }
}
