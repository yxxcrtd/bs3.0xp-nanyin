/**  
 * @Project     tams-app  
 * @File        InvestmentsInfo001Controller.java  
 * @Package     com.joyintech.tams.invest.report.regulatoryreporting.investmentsinfo.controller  
 * @Version     V1.0  
 * @Date        2019年8月7日 上午9:56:59  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */  
    
package com.joyintech.tams.invest.report.regulatoryreporting.investmentsinfo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.report.regulatoryreporting.investmentsinfo.service.InvestmentsInfo001Service;

/**  
 * @Description 表内外投资业务情况统计表控制层  
 * @ClassName   InvestmentsInfo001Controller  
 * @Date        2019年8月7日 上午9:56:59  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */
@Controller
@PageId("InvestmentsInfo001")
@RequestMapping("/invest/report/regulatoryreporting/investmentsinfo/InvestmentsInfo001")
public class InvestmentsInfo001Controller extends BaseController{
	
	@Autowired
	private InvestmentsInfo001Service investmentsInfoService;
	
	private static Map<String, String> quarterMap = new LinkedHashMap<String, String>();
	
	static {
		quarterMap.put("03", "第一季度");
		quarterMap.put("06", "第二季度");
		quarterMap.put("09", "第三季度");
		quarterMap.put("12", "第四季度");
	}
	/**
	 * 初始化
	 */
	@Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
		List<SelectOptionDto> quarterList = new LinkedList<SelectOptionDto>();
		for (String key : quarterMap.keySet()) {
			SelectOptionDto selectOptionDto = new SelectOptionDto();
			selectOptionDto.setId(key);
			selectOptionDto.setText(quarterMap.get(key));
			selectOptionDto.setCol("");
			quarterList.add(selectOptionDto);
		}
		model.addAttribute("quarterList", JsonUtils.objectArray2Json(quarterList.toArray()));
		Date reportStartDate =  DateUtil.platDateFromCache();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy");
        model.addAttribute("searchDataEnd", sdf.format(reportStartDate));
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
        return investmentsInfoService.export(RequestUtil.getParameters(getRequest()));
    }
}
