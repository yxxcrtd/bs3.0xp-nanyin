package com.joyintech.tams.report.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.report.home.dto.VbusinessHomeDto;
import com.joyintech.tams.report.home.service.BusinessHomeMService;

/**
 * @Description 业务首页
 * @author jiangxiao
 * @version 1.7 2019年5月16日
 * @since 1.7
 */
@Controller
@PageId("SvreportHome002")
@RequestMapping("/system/businessHome")
public class BusinessHomeController extends BaseController {

	@Autowired
	private BusinessHomeMService businessHomeMService;
	
	@RequestMapping("/searchBusinessHomeInfo")
    @ResponseBody
    public List<VbusinessHomeDto> searchBusinessHomeInfo() {
		List<String> menulist = businessHomeMService.selectMenulist();
        return businessHomeMService.searchBusinessHomeInfo(this.getParam("searchDate"),menulist);
    }
	
	@RequestMapping("/getSystemNow")
    @ResponseBody
    public AjaxResult getSystemNow(){
        return businessHomeMService.getSystemNow();
    }
}
