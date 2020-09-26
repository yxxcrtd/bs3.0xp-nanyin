package com.joyintech.tams.svreport.pbc.classic.prdassetdebt.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.pbc.report.dto.PbcLoanDetailInfoSub;

/**
 * 资产负债管理-贷款、信托明细信息预览Service
 * add by hpp
 */
@Service
public class PbcPrdAssetDebtSubPreviewService extends CommonExportService<PbcLoanDetailInfoSub>{

	/**
	 * 贷款明细信息预览
	 * @param param
	 * @return
	 */
	public Page<PbcLoanDetailInfoSub> searchPageDX(Map<String, Object> param){
		return restTemplate.postForPage(
	               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdAssetDebtSubPreview/searchPageDX",param,
	               PbcLoanDetailInfoSub.class);
	}
	
	/**
	 * 资金信托信息预览
	 * @param param
	 * @return
	 */
	public Page<PbcLoanDetailInfoSub> searchPageXT(Map<String, Object> param){
		return restTemplate.postForPage(
	               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdAssetDebtSubPreview/searchPageXT",param,
	               PbcLoanDetailInfoSub.class);
	}
	
	/**
     * 导出excel查询
     * --贷款明细预览
     * @return 导出
     */
	public List<PbcLoanDetailInfoSub> queryReportListDK(Map<String, Object> param){
		return restTemplate.postForList(
        		SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdAssetDebtSubPreview/queryReportListDK",param,
        		PbcLoanDetailInfoSub.class);
    }
	
	/**
     * 导出excel查询
     * --资金信托预览
     * @return 导出
     */
	public List<PbcLoanDetailInfoSub> queryReportListXT(Map<String, Object> param){
		return restTemplate.postForList(
        		SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdAssetDebtSubPreview/queryReportListXT",param,
        		PbcLoanDetailInfoSub.class);
    }
	
}
