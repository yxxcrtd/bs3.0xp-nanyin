package com.joyintech.tams.svreport.pbc.report.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.svreport.pbc.report.dto.PbcLoanDetailInfoSub;
/**
 * 资产负债管理-贷款、信托明细信息预览Mapper
 * @author hpp
 *
 */
public interface PbcPrdAssetDebtSubPreviewMapper {

	/**
	 * 贷款明细信息预览
	 * @param map
	 * @return
	 */
	List<PbcLoanDetailInfoSub> searchPageDX(Map<String,Object> map);
	
	/**
	 * 资金信托信息预览
	 * @param map
	 * @return
	 */
	List<PbcLoanDetailInfoSub> searchPageXT(Map<String,Object> map);
	
}
