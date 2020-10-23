package com.joyintech.service.pbc.classic.prdassetdebt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.svreport.pbc.classic.prdassetdebt.service.PbcPrdAssetDebtSubPreviewService001;
import com.joyintech.tams.svreport.pbc.report.dto.PbcLoanDetailInfoSub;

/**
 * 资产负债管理-贷款、信托明细信息预览Controller
 * @author hpp
 *
 */
@RestController("backPbcPrdAssetDebtSubPreviewController")
@RequestMapping("/PbcPrdAssetDebtSubPreview")
public class PbcPrdAssetDebtSubPreviewController {
	
	@Autowired
	private PbcPrdAssetDebtSubPreviewService001 pbcPrdAssetDebtSubPreviewService001;
	
	/**
    * 贷款明细信息预览
    * @param param
    * @return
    */
	@PostMapping("searchPageDX/{page}/{length}")
	public Page<PbcLoanDetailInfoSub> searchPageDX(@RequestBody Map<String, Object> param) {
		Page<PbcLoanDetailInfoSub> page = pbcPrdAssetDebtSubPreviewService001.searchPageDX(param);
		return page;
	}
	
	/**
    * 资金信托信息预览
    * @param param
    * @return
    */
	@PostMapping("searchPageXT/{page}/{length}")
	public Page<PbcLoanDetailInfoSub> searchPageXT(@RequestBody Map<String, Object> param) {
		Page<PbcLoanDetailInfoSub> page = pbcPrdAssetDebtSubPreviewService001.searchPageXT(param);
		return page;
	}
   
	/**
	 * 导出excel查询
     * --贷款明细预览
     * @param param
     * @return
     */
	@PostMapping("queryReportListDK")
	public List<PbcLoanDetailInfoSub> queryReportListDK(@RequestBody Map<String, Object> param) {
		List<PbcLoanDetailInfoSub> lists = pbcPrdAssetDebtSubPreviewService001.queryReportListDK(param);
		return lists;
	}
   
	/**
	 * 导出excel查询
     * --资金信托预览
     * @param param
     * @return
     */
	@PostMapping("queryReportListXT")
	public List<PbcLoanDetailInfoSub> queryReportListXT(@RequestBody Map<String, Object> param) {
		List<PbcLoanDetailInfoSub> lists = pbcPrdAssetDebtSubPreviewService001.queryReportListXT(param);
		return lists;
	}
	   
}
