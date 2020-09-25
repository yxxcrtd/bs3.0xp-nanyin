package com.joyintech.service.pbc.monthreport.controller;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.svreport.pbc.assetdebt.service.PbcReportAreaService;
import com.joyintech.tams.svreport.pbc.assetdebt.service.PbcReportAssetDebtService;
import com.joyintech.tams.svreport.pbc.assetdebt.service.PbcReportAssetIncomeDetailService;
import com.joyintech.tams.svreport.pbc.assetdebt.service.PbcReportCompanyBondService;
import com.joyintech.tams.svreport.pbc.assetdebt.service.PbcReportCompanyService;
import com.joyintech.tams.svreport.pbc.assetdebt.service.PbcReportDebtTermDetailService;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;


/**
 * 人行报表asset服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.0 2019年4月26日
 * @see PbcMonthReportAsseetController
 * @since 1.0
 */
@RestController
@RequestMapping("/PbcMonthReportAsset")
public class PbcMonthReportAsseetController{
    /**
     * service
     */
    @Autowired
    private PbcReportAssetDebtService pbcReportAssetDebtService;
    @Autowired
    private PbcReportCompanyService pbcReportCompanyService;
    @Autowired
    private PbcReportAreaService pbcReportAreaService;
    @Autowired
    private PbcReportAssetIncomeDetailService pbcReportAssetIncomeDetailService;
    @Autowired
    private PbcReportCompanyBondService pbcReportCompanyBondService;
    @Autowired
    private PbcReportDebtTermDetailService PbcReportDebtTermDetailService;
    

	@PostMapping("createDataAssetDebt")
	public Map<Object, Object> createDataAssetDebt(@RequestBody Map<String,Object> param){
		return pbcReportAssetDebtService.createData(param);
	}

	@PostMapping("createDataCompany")
	public Map<Object, Object> createDataCompany(@RequestBody Map<String,Object> param){
		return pbcReportCompanyService.createData(param);
	}
	
	@PostMapping("createDataArea")
	public Map<Object, Object> createDataArea(@RequestBody Map<String,Object> param){
		return pbcReportAreaService.createData(param);
	}
	
	@PostMapping("createDataAssetIncomeDetail")
	public Map<Object, Object> createDataAssetIncomeDetail(@RequestBody Map<String,Object> param){
		return pbcReportAssetIncomeDetailService.createData(param);
	}
	
	@PostMapping("createDataCompanyBond")
	public Map<Object, Object> createDataCompanyBond(@RequestBody Map<String,Object> param){
		return pbcReportCompanyBondService.createData(param);
	}
	
	@PostMapping("createDataDebtTermDetail")
	public Map<Object, Object> createDataDebtTermDetail(@RequestBody Map<String,Object> param){
		return PbcReportDebtTermDetailService.createData(param);
	}

}
