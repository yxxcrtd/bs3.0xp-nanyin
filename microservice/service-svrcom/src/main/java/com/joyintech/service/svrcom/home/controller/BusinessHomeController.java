package com.joyintech.service.svrcom.home.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.report.home.dto.VbusinessHomeDto;
import com.joyintech.tams.report.home.service.BusinessHomeService;

/**
 * BusinessHome服务
 * 〈功能详细描述〉
 * @author jiangxiao
 * @version 1.0 2019年06月26日
 * @see BusinessHomeController
 * @since 1.0
 */
@RestController("backBusinessHomeController")
@RequestMapping("/BusinessHome")
public class BusinessHomeController extends BaseController {

	@Autowired
	private BusinessHomeService businessHomeService;
	
	/**
    *
    * 取得信息表区域的数据
    * @return 信息表区域的统计数据
    */
	@PostMapping("/selectBusinessHomeInfo")
    public List<VbusinessHomeDto> searchInfoArea(@RequestBody Map<String, Object> param) {
        return businessHomeService.selectBusinessHomeInfo((String)param.get("searchDate"),(List<String>) param.get("menulist"));
    }
    
	@GetMapping("/selectMenulist")
    public List<String> selectMenulist() {
        return businessHomeService.selectMenulist();
    }
    
}
