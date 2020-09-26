/*
 * 文件名：InvestorsHoldInfoRegist001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.svreport.pbc.assetdebt.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.svreport.pbc.assetdebt.dao.PbcReportDebtTermDetailMapper;
import com.joyintech.tams.svreport.pbc.assetdebt.dto.PbcReportDebtTermDetail;
import com.joyintech.tams.svreport.pbc.common.annotation.TemplateName;
import com.joyintech.tams.svreport.pbc.common.constants.PbcTemplateName;
import com.joyintech.tams.svreport.pbc.common.service.PbcExportService;
import com.joyintech.tams.svreport.pbc.report.dao.PbcMonthReportMapper;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;

/**
 * 资产负债剩余期限service
 *
 * @author zhangzhongliang
 * @version 1.0 2019年3月05日
 * @since 1.0
 */
@Service
@TemplateName(PbcTemplateName.PBC_REPORT_3_3)
public class PbcReportDebtTermDetailService extends PbcExportService {

	@Autowired
	private PbcReportDebtTermDetailMapper pbcReportDebtTermDetailMapper;

	@Autowired
	private PbcMonthReportMapper pbcMonthReportMapper;

	@Override
	public Map<Object, Object> createData(Map<String, Object> param) {
		Map<Object, Object> data = new HashMap<Object, Object>();
		if (param.get("reportuuid") == null) {
			return data;
		}

		String reportuuid = param.get("reportuuid").toString();
		PbcMonthReport pbcReport = pbcMonthReportMapper.selectByPrimaryKey(reportuuid);
		// 如果报表为空则返回data
        if (pbcReport == null){
            return data;
        }
		pbcReport.setProdVariety(pbcReport.getProdVariety() + "-"
				+ SysDataUtil.getNameFromDataDict(DictCode.产品品种, pbcReport.getProdVariety()));
		data.put("report", pbcReport);
		List<PbcReportDebtTermDetail> listDetail = pbcReportDebtTermDetailMapper.selectByReportuuid(reportuuid);
		// 导出excel或预览时，值为0的单元格显示成0还是显示成空，读取系统参数
    	String isShowZero = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SHOW_ZERO);
    	for (PbcReportDebtTermDetail detail : listDetail) {
			dealProdData(detail, isShowZero);
			data.put(detail.getPropertycode(), divide10000(detail));
		}
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Object, Object> createDataMS(Map<String, Object> param) {
		return restTemplate.postForObject(
				SvrServiceApplication.PBC_SERVICE_NAME + "/PbcMonthReportAsset/createDataDebtTermDetail", param,
				Map.class);
	}

	private PbcReportDebtTermDetail divide10000(PbcReportDebtTermDetail detail) {
		BigDecimal tenthousand = new BigDecimal(10000);
		if (detail.getDebtInstrument() != null)// 债务工具
			detail.setDebtInstrument(detail.getDebtInstrument().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		if (detail.getStockRight() != null)// 股权
			detail.setStockRight(detail.getStockRight().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		if (detail.getSpecialShare() != null)// 特定目的载体份额
			detail.setSpecialShare(detail.getSpecialShare().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		if (detail.getActualAmt() != null)// 实收本金
			detail.setActualAmt(detail.getActualAmt().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		return detail;
	}

	/**
	 * @Description 导出数据处理
	 * @param param
	 * @param data
	 * @return void
	 * @version 1.7 2019年5月13日
	 */
	public void createDataForExp(Map<String, Object> param, Map<Object, Object> data) {
		if (param.get("reportuuid") == null) {
			return;
		}

		String reportuuid = param.get("reportuuid").toString();
		PbcMonthReport pbcReport = pbcMonthReportMapper.selectByPrimaryKey(reportuuid);
		pbcReport.setProdVariety(pbcReport.getProdVariety() + "-"
				+ SysDataUtil.getNameFromDataDict(DictCode.产品品种, pbcReport.getProdVariety()));
		data.put("report", pbcReport);
		List<PbcReportDebtTermDetail> listDetail = pbcReportDebtTermDetailMapper.selectByReportuuid(reportuuid);
		// 导出excel或预览时，值为0的单元格显示成0还是显示成空，读取系统参数
    	String isShowZero = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SHOW_ZERO);
    	for (PbcReportDebtTermDetail detail : listDetail) {
			dealProdData(detail,isShowZero);
			data.put(detail.getPropertycode(), divide10000(detail));
		}
	}
	/**
     * @Description 导出预览为零的数据显示空还是显示零
     * @param detail
     * @version 1.7 2019年7月4日
     * @param isShowZero 
     */
    private void dealProdData(PbcReportDebtTermDetail model, String isShowZero) {
    	// 显示成零
		if("01".equals(isShowZero)){
			if(model.getDebtInstrument()==null){
				model.setDebtInstrument(BigDecimal.ZERO);
			}
			if(model.getStockRight()==null){
				model.setStockRight(BigDecimal.ZERO);
			}
			if(model.getSpecialShare()==null){
				model.setSpecialShare(BigDecimal.ZERO);
			}
			if(model.getActualAmt()==null){
				model.setActualAmt(BigDecimal.ZERO);
			}
		} else if ("02".equals(isShowZero)) {
			if (model.getDebtInstrument() != null && BigDecimal.ZERO.compareTo(model.getDebtInstrument()) == 0) {
				model.setDebtInstrument(null);
			}
			if (model.getStockRight() != null && BigDecimal.ZERO.compareTo(model.getStockRight()) == 0) {
				model.setStockRight(null);
			}
			if (model.getSpecialShare() != null && BigDecimal.ZERO.compareTo(model.getSpecialShare()) == 0) {
				model.setSpecialShare(null);
			}
			if (model.getActualAmt() != null && BigDecimal.ZERO.compareTo(model.getActualAmt()) == 0) {
				model.setActualAmt(null);
			}
		}
	}
}
