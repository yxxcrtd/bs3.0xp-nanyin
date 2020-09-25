package com.joyintech.service.product.nolimit.maxissuablesize.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.nolimit.maxissuablesize.dto.MaxIssuableSizeDto;
import com.joyintech.service.product.nolimit.maxissuablesize.service.MaxIssuableSizeService;
/**
 * 微服务端最大可发行规模查询controller
 * 〈功能详细描述〉
 * @author qufangxu
 * @version 1.0 2020年1月1日
 * @see MaxIssuableSizeController
 * @since 1.0
 */
@RestController("backMaxIssuableSizeController_dtx")
@RequestMapping("/nolimit/MaxIssuableSize")
public class MaxIssuableSizeController {
	/**
	 * 微服务端最大可发行规模查询service
	 */
	@Autowired
	private MaxIssuableSizeService maxIssuableSizeSevice;
	
	/**
	 * 最大可发行规模查询 分页查询
	 * <功能详细描述>
	 * @param param
	 * @param page
	 * @param length
	 * @return
	 * @throws Exception
	 * @see 
	 * @since 1.0
	 */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<MaxIssuableSizeDto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page, 
    		@PathVariable("length") int length) throws Exception{
        return maxIssuableSizeSevice.selectForPage(page, length, pageParam);
    }
    
    /**
     * 产品规划系列联想框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
	@PostMapping("queryPrdPlanSerisIdInfo")
	public List<SelectOptionDto> queryPrdPlanSerisIdInfo(@RequestBody Map<String, Object> param) {
		return maxIssuableSizeSevice.queryPrdPlanSerisIdInfo(param);
	}
    	
}
