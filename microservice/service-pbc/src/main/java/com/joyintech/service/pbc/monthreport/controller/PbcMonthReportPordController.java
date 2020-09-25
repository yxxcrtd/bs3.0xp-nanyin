package com.joyintech.service.pbc.monthreport.controller;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.svreport.pbc.prod.dto.PbcReportProdDetailDto;
import com.joyintech.tams.svreport.pbc.prod.service.PbcReportProd001Sub001Service;
import com.joyintech.tams.svreport.pbc.prod.service.PbcReportProdTotalService;
import com.joyintech.tams.svreport.pbc.report.dao.PbcMonthReportMapper;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;


/**
 * 人行报表prod服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.0 2019年4月26日
 * @see PbcMonthReportPordController
 * @since 1.0
 */
@RestController
@RequestMapping("/PbcMonthReportProd")
public class PbcMonthReportPordController{
    /**
     * service
     */
    @Autowired
    private PbcReportProd001Sub001Service pbcReportProd001Sub001Service;


    

	@PostMapping("createDataProd")
	public Map<Object, Object> createDataProd(@RequestBody Map<String,Object> param){
		return pbcReportProd001Sub001Service.createData(param);
	}
	
	@PostMapping("selectPbcMonthReportByPrimaryKey")
	public PbcMonthReport selectPbcMonthReportByPrimaryKey(@RequestBody String reportuuid){
		return  pbcReportProd001Sub001Service.selectPbcMonthReportByPrimaryKey(reportuuid);
	}
	
	
	@PostMapping("selectProdDetailByReportuuid")
	public List<PbcReportProdDetailDto> selectProdDetailByReportuuid(@RequestBody String reportuuid){
		return  pbcReportProd001Sub001Service.selectProdDetailByReportuuid(reportuuid);
	}
	


}
