package com.joyintech.service.product.nolimit.nonStandardAvailableCredit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.product.messageadjust.bmquotation.controller.FinProductBmQuotationController;
import com.joyintech.service.product.nolimit.nonStandardAvailableCredit.dto.NonStandardAvailableCreditDto;
import com.joyintech.service.product.nolimit.nonStandardAvailableCredit.service.NonStandardAvailableCreditService;
/**
 * 微服务-非标可用铬电镀查询
 * 〈功能详细描述〉
 * @author dongzhikang
 * @version 1.0  
 * @see NonStandardAvailableCredit
 * @since 1.0
 */
@RestController("NonStandardAvailableCredit_dtx")
@RequestMapping("/NonStandardAvailableCredit")
public class NonStandardAvailableCreditController {
	@Autowired
	NonStandardAvailableCreditService nonStandardAvailableCreditService;
	
	
	  /**
     * 双击查看界面和修改界面的初始化 通过点击的数据信息查询相关数据
     * 
     * @param param 查询参数
     * @return 结果
     * @see
     * @since 1.0
     */
	 @PostMapping("selectForPage/{page}/{length}")
	    public Page<NonStandardAvailableCreditDto> selectAll(@RequestBody Map<String, Object> parameters,
	                                                      @PathVariable("page") int page,
	                                                      @PathVariable("length") int length)
	        throws Exception {
	        Page<NonStandardAvailableCreditDto> pages = nonStandardAvailableCreditService.selectForPage(page,
	            length, parameters);
	        return pages;
	    }
}
