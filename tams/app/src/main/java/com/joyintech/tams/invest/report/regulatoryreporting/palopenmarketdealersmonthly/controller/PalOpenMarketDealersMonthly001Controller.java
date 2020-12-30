/**  
 * @Project     tams-invest-report  
 * @File        PalOpenMarketDealersMonthly001Controller.java  
 * @Package     com.joyintech.tams.invest.report.regulatoryreporting.palopenmarketdealersmonthly.controller  
 * @Version     V1.0  
 * @Date        2019年8月2日 上午11:27:06  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */  
    
package com.joyintech.tams.invest.report.regulatoryreporting.palopenmarketdealersmonthly.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.report.regulatoryreporting.palopenmarketdealersmonthly.service.PalOpenMarketDealersMonthly001Service;

/**  
 * @Description 公开市场业务一级交易商月报表控制层  
 * @ClassName   PalOpenMarketDealersMonthly001Controller  
 * @Date        2019年8月2日 上午11:27:06  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */
@Controller
@PageId("PalOpenMarketDealersMonthly001")
@RequestMapping("/invest/report/regulatoryreporting/palopenmarketdealersmonthly/PalOpenMarketDealersMonthly001")
public class PalOpenMarketDealersMonthly001Controller extends BaseController{
	
	@Autowired
	private PalOpenMarketDealersMonthly001Service palOpenMarketDealersMonthlyService;
	/**
	 * 初始化
	 */
	@Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
		Date reportStartDate = DateUtil.platDateFromCache();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM");
		model.addAttribute("searchDataTime", sdf.format(reportStartDate));
		return super.init(model);
	}
	
	/**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() throws Exception {
        return palOpenMarketDealersMonthlyService.export(RequestUtil.getParameters(getRequest()));
    }
}
