package com.joyintech.tams.svreport.pbc.classic.prdassetdebt.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.svreport.pbc.classic.prdassetdebt.service.PbcPrdAssetDebtSubPreviewService;
import com.joyintech.tams.svreport.pbc.report.dto.PbcLoanDetailInfoSub;

/**
 * 资产负债管理-贷款、信托明细信息预览Controller
 * add by hpp
 */
@Controller
@PageId("PbcPrdAssetDebtSubPreview")
@RequestMapping("/pbc/prdassetdebt/PbcPrdAssetDebtSubPreview")
public class PbcPrdAssetDebtSubPreviewController extends BaseController{

	@Autowired
	private PbcPrdAssetDebtSubPreviewService pbcPrdAssetDebtSubPreviewService;
	
	/**
	 * 页面初始化
	 */
	@Override
	public String init(Model model) {
		Map<String, Object> map = RequestUtil.getPageParam();
		model.addAttribute("previewType", map.get("previewType"));
		model.addAttribute("reportuuid", map.get("reportuuid"));
		return super.init(model);
	}
	
	/**
	 * 贷款明细信息预览
	 * @return
	 */
	@ResponseBody
	@RequestMapping("searchPageDX")
	public Page<PbcLoanDetailInfoSub> searchPageDX(){
		Map<String, Object> searchParam = RequestUtil.getPageParam();
		Page<PbcLoanDetailInfoSub> page = pbcPrdAssetDebtSubPreviewService.searchPageDX(searchParam);
		return page;
	}
	
	/**
	 * 资金信托信息预览
	 * @return
	 */
	@ResponseBody
	@RequestMapping("searchPageXT")
	public Page<PbcLoanDetailInfoSub> searchPageXT(){
		Map<String, Object> searchParam = RequestUtil.getPageParam();
		Page<PbcLoanDetailInfoSub> page = pbcPrdAssetDebtSubPreviewService.searchPageXT(searchParam);
		return page;
	}
	
	/**
     * 导出excel
     *
     * @return 导出
     */
    @RequestMapping(value = ACTION_EXPORT)
    public ResponseEntity<byte[]> export() {
    	File outFile = null;
    	Map<String, Object> searchParam = RequestUtil.getPageParam();
    	
    	String pageid="";
    	if("DK".equals(searchParam.get("previewType"))){
    		pageid="贷款明细预览信息";
    		//贷款明细预览导出
    		List<PbcLoanDetailInfoSub> dtoListDK = this.pbcPrdAssetDebtSubPreviewService.queryReportListDK(RequestUtil.getParameters(getRequest()));
            outFile = pbcPrdAssetDebtSubPreviewService.makeFile(
                "/com/joyintech/tams/svreport/pbc/classic/prdassetdebt/conf/export_confDK.xml", dtoListDK);
    	}else if("XT".equals(searchParam.get("previewType"))){
    		pageid="资金信托预览信息";
    		//资金信托预览导出
    		List<PbcLoanDetailInfoSub> dtoListXT = this.pbcPrdAssetDebtSubPreviewService.queryReportListXT(RequestUtil.getParameters(getRequest()));
            outFile = pbcPrdAssetDebtSubPreviewService.makeFile(
                "/com/joyintech/tams/svreport/pbc/classic/prdassetdebt/conf/export_confXT.xml", dtoListXT);
    	}
    	
        return ExportUtil.export(
        		pageid + GlobalConstant.OPEN_PARENTHESIS
                 + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
                 + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }
	
}
