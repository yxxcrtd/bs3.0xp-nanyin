package com.joyintech.tams.svreport.pbc.classic.prdassetdebt.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.svreport.pbc.report.dao.PbcPrdAssetDebtSubPreviewMapper;
import com.joyintech.tams.svreport.pbc.report.dto.PbcLoanDetailInfoSub;

/**
 * 资产负债管理-贷款、信托明细信息预览Service
 * @author hpp
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PbcPrdAssetDebtSubPreviewService001 extends BaseService<PbcLoanDetailInfoSub>{

	@Autowired
	private PbcPrdAssetDebtSubPreviewMapper pbcPrdAssetDebtSubPreviewMapper;
	
	
	/**
     * 贷款明细信息预览
     * @param searchParam
     * @return
     */
	public Page<PbcLoanDetailInfoSub> searchPageDX(Map<String, Object> searchParam) {
		int pagenum = StringUtil.integerValueOf(""+searchParam.get(GlobalConstant.PARAM_PAGE), 1);
        int length = StringUtil.integerValueOf(""+searchParam.get(GlobalConstant.PARAM_LENGTH), GlobalConstant.DEFAULT_PAGE_SIZE);
        Page<PbcLoanDetailInfoSub> pageList = queryForPage(pagenum,length,()->pbcPrdAssetDebtSubPreviewMapper.searchPageDX(searchParam));
		dealReturnInfo(pageList.getRoot());
		return pageList;
	}
	
	/**
     * 资金信托信息预览
     * @param searchParam
     * @return
     */
	public Page<PbcLoanDetailInfoSub> searchPageXT(Map<String, Object> searchParam) {
		int pagenum = StringUtil.integerValueOf(""+searchParam.get(GlobalConstant.PARAM_PAGE), 1);
        int length = StringUtil.integerValueOf(""+searchParam.get(GlobalConstant.PARAM_LENGTH), GlobalConstant.DEFAULT_PAGE_SIZE);
        Page<PbcLoanDetailInfoSub> pageList = queryForPage(pagenum,length,()->pbcPrdAssetDebtSubPreviewMapper.searchPageXT(searchParam));
		dealReturnInfo(pageList.getRoot());
		return pageList;
	}
	
	/**
     * 共通方法：查询返回结果处理
     * 
     * @param pageList
     */
    private void dealReturnInfo(List<PbcLoanDetailInfoSub> pageList) {
        for (PbcLoanDetailInfoSub dto : pageList) {
        	dto.setLoansType(SysDataUtil.getZZDictCodeAndName(
        			DictCode.贷款种类, dto.getLoansType()));
        	dto.setLoanContractOrgCounty(SysDataUtil.getZZDictCodeAndName(
        			DictCode.区域划分_县, dto.getLoanContractOrgCounty()));
        	dto.setBorrowerType(SysDataUtil.getZZDictCodeAndName(
        			DictCode.人行客户类型, dto.getBorrowerType()));
        	dto.setBorrowerCounty(SysDataUtil.getZZDictCodeAndName(
        			DictCode.区域划分_县, dto.getBorrowerCounty()));
        	dto.setBorrowerNationality(SysDataUtil.getZZDictCodeAndName(
        			DictCode.所属国家或地区, dto.getBorrowerNationality()));
        	dto.setIndustryInfo(SysDataUtil.getZZDictCodeAndName(
        			DictCode.行业门内编码, dto.getIndustryInfo()));
        	dto.setEnterpriseInvestorTypeFirst(SysDataUtil.getZZDictCodeAndName(
        			DictCode.经济成分一级, dto.getEnterpriseInvestorTypeFirst()));
        	dto.setEnterpriseInvestorTypeSec(SysDataUtil.getZZDictCodeAndName(
        			DictCode.经济成分二级, dto.getEnterpriseInvestorTypeSec()));
        	dto.setEnterpriseInvestorTypeThird(SysDataUtil.getZZDictCodeAndName(
        			DictCode.经济成分三级, dto.getEnterpriseInvestorTypeThird()));
        	dto.setEnterpriseScale(SysDataUtil.getZZDictCodeAndName(
        			DictCode.企业规模, dto.getEnterpriseScale()));
        	dto.setLoanPrdType(SysDataUtil.getZZDictCodeAndName(
        			DictCode.贷款产品类别, dto.getLoanPrdType()));
        	dto.setLoanActualInvest(SysDataUtil.getZZDictCodeAndName(
        			DictCode.行业大类编码, dto.getLoanActualInvest()));
        	dto.setRateFixed(SysDataUtil.getZZDictCodeAndName(
        			DictCode.利率是否固定, dto.getRateFixed()));
        	dto.setLoanGuaranteeMode(SysDataUtil.getZZDictCodeAndName(
        			DictCode.贷款担保方式, dto.getLoanGuaranteeMode()));
        	dto.setLoanQuality(SysDataUtil.getZZDictCodeAndName(
        			DictCode.贷款质量, dto.getLoanQuality()));
        	dto.setLoanState(SysDataUtil.getZZDictCodeAndName(
        			DictCode.贷款状态, dto.getLoanState()));
        	dto.setOrigCcy(SysDataUtil.getZZDictCodeAndName(
        			DictCode.币种_中债, dto.getOrigCcy()));
        	dto.setAmtCcy(SysDataUtil.getZZDictCodeAndName(
        			DictCode.币种_中债, dto.getAmtCcy()));
        	dto.setLoanVdateStr(
        			DateUtil.format(dto.getLoanVdate(), DateUtil.DEFAULT_DATE_FORMAT_EN));
        	dto.setLoanMdateStr(
        			DateUtil.format(dto.getLoanMdate(), DateUtil.DEFAULT_DATE_FORMAT_EN));
        	dto.setLoanExtensionDueDateStr(
        			DateUtil.format(dto.getLoanExtensionDueDate(), DateUtil.DEFAULT_DATE_FORMAT_EN));
        	
        	dto.setCcy(SysDataUtil.getZZDictCodeAndName(DictCode.币种_中债, dto.getCcy()));
        	dto.setStockType(SysDataUtil.getZZDictCodeAndName(DictCode.股权种类, dto.getStockType()));
        }
    }
	
    /**
     * 导出excel查询
     * --贷款明细预览
     * @param searchParam
     * @return
     */
    public List<PbcLoanDetailInfoSub> queryReportListDK(Map<String, Object> searchParam){
    	List<PbcLoanDetailInfoSub> listsDK = new ArrayList<PbcLoanDetailInfoSub>();
    	String keyList = (String)searchParam.get("keyList");
    	if(StringUtil.isNotEmpty(keyList)){
    		try {
				listsDK = JsonUtils.json2List(keyList, PbcLoanDetailInfoSub.class);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
    	}else{
    		listsDK = pbcPrdAssetDebtSubPreviewMapper.searchPageDX(searchParam);
    		dealReturnInfo(listsDK);
    	}
		return listsDK;
    }
    
    /**
     * 导出excel查询
     * --资金信托预览
     * @param searchParam
     * @return
     */
    public List<PbcLoanDetailInfoSub> queryReportListXT(Map<String, Object> searchParam){
    	List<PbcLoanDetailInfoSub> listsXT = new ArrayList<PbcLoanDetailInfoSub>();
    	String keyList = (String)searchParam.get("keyList");
    	if(StringUtil.isNotEmpty(keyList)){
    		try {
    			listsXT = JsonUtils.json2List(keyList, PbcLoanDetailInfoSub.class);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
    	}else{
    		listsXT = pbcPrdAssetDebtSubPreviewMapper.searchPageXT(searchParam);
    		dealReturnInfo(listsXT);
    	}
		return listsXT;
    }
    
}
