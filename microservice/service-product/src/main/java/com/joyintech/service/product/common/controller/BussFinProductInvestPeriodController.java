
package com.joyintech.service.product.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.product.common.dto.BussFinProductInvestPeriodDto;
import com.joyintech.service.product.common.service.BussFinProductInvestPeriodService;

/**
 * 投资周期列表组件
 * 
 * @author wm
 * @version 1.0 2018年9月29日
 * @see BussFinProductInvestPeriodController
 * @since 1.0
 */
@RestController("bussFinProductInvestPeriod_dtx")
@RequestMapping("/BussFinProductInvestPeriod")
public class BussFinProductInvestPeriodController {
	
	/**
	 * {@linkplain}
	 */
	@Autowired(required=false)
	private BussFinProductInvestPeriodService bussFinProductInvestPeriodService;
	
	/**
     * 页面参数设置
     * @return 基准信息
     */
    @PostMapping("/queryPeriodList")
    public List<BussFinProductInvestPeriodDto> queryPeriodList(@RequestBody Map<String,Object> param) {
        return bussFinProductInvestPeriodService.queryPeriodList(param);
    }
    
}
