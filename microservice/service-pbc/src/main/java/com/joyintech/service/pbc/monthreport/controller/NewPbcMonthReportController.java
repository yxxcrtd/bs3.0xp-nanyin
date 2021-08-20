package com.joyintech.service.pbc.monthreport.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.service.NewPbcMonthReportService;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import com.joyintech.tams.svreport.pbc.report.service.PbcMonthReport001Service;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 人行报表 服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.0 2019年4月26日
 * @see NewPbcMonthReportController
 * @since 1.0
 */
@RestController
@RequestMapping("/NewPbcMonthReport")
public class NewPbcMonthReportController {
    /**
     * service
     */
    @Autowired
    private NewPbcMonthReportService newPbcMonthReportService;

	/**
	 *
	 * 业务画面导入检查事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("checkMonthReport")
	public AjaxResult checkMonthReport(String fileName) {
		return newPbcMonthReportService.checkMonthReport(fileName);
	}

	/**
	 *
	 * 业务画面导入检查事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("importMonthReport")
	public List<ExcelValidDto> importMonthReport(String fileName) {
		return newPbcMonthReportService.importMonthReport(fileName);
	}
	
}
