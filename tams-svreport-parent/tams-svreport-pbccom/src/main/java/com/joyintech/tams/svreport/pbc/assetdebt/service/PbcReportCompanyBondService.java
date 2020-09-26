package com.joyintech.tams.svreport.pbc.assetdebt.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.pbc.assetdebt.dao.PbcReportCompanyDetailMapper;
import com.joyintech.tams.svreport.pbc.assetdebt.dto.PbcReportAssetIncomeDetail;
import com.joyintech.tams.svreport.pbc.assetdebt.dto.PbcReportCompanyDetailDto;
import com.joyintech.tams.svreport.pbc.common.annotation.TemplateName;
import com.joyintech.tams.svreport.pbc.common.constants.PbcTemplateName;
import com.joyintech.tams.svreport.pbc.common.service.PbcExportService;
import com.joyintech.tams.svreport.pbc.report.dao.PbcMonthReportMapper;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;

/**
 * @Description 表2.5：企业债券分行业、企业规模统计
 * @author jiangxiao
 * @version 1.0 2019年3月6日
 * @since 1.0
 */
@Service
@TemplateName(PbcTemplateName.PBC_REPORT_2_5)
public class PbcReportCompanyBondService extends PbcExportService {

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PbcReportCompanyDetailMapper pbcReportCompanyDetailMapper;
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
		// 导出excel或预览时，值为0的单元格显示成0还是显示成空，读取系统参数
        String isShowZero = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SHOW_ZERO);
		List<PbcReportCompanyDetailDto> listDetail = pbcReportCompanyDetailMapper.selectByReportuuid(reportuuid);
		if (!CollectionUtils.isEmpty(listDetail)) {
			for (PbcReportCompanyDetailDto detail : listDetail) {
				// 处理数据
				dealCompanyDateListForExport(detail);
				dealProdData(detail, isShowZero);
				data.put(detail.getPropertycode(), detail);
			}
		}
		return data;
	}
	
	/**
     * 
     * 导出预览为0的数据显示为空还是为0.
     * 
     * @author liuyu
     * @time 2019年7月4日 下午4:22:09
     * @param detail
     * @param isShowZero
     *            01:显示为0, 02:显示为空
     */
	private void dealProdData(PbcReportCompanyDetailDto model, String isShowZero) {
        // 显示成零
        if("01".equals(isShowZero)){
            if(model.getLargeCompanyAmt()==null){
                model.setLargeCompanyAmt(BigDecimal.ZERO);
            }
            if(model.getMediumCompanyAmt()==null){
                model.setMediumCompanyAmt(BigDecimal.ZERO);
            }
            if(model.getSmallCompanyAmt()==null){
                model.setSmallCompanyAmt(BigDecimal.ZERO);
            }
            if(model.getMicroCompanyAmt()==null){
                model.setMicroCompanyAmt(BigDecimal.ZERO);
            }
            if(model.getOthAmt()==null){
                model.setOthAmt(BigDecimal.ZERO);
            }
            if(model.getSumAmt()==null){
                model.setSumAmt(BigDecimal.ZERO);
            }
		} else if ("02".equals(isShowZero)) {
			if (model.getLargeCompanyAmt() != null && BigDecimal.ZERO.compareTo(model.getLargeCompanyAmt()) == 0) {
				model.setLargeCompanyAmt(null);
			}
			if (model.getMediumCompanyAmt() != null && BigDecimal.ZERO.compareTo(model.getMediumCompanyAmt()) == 0) {
				model.setMediumCompanyAmt(null);
			}
			if (model.getSmallCompanyAmt() != null && BigDecimal.ZERO.compareTo(model.getSmallCompanyAmt()) == 0) {
				model.setSmallCompanyAmt(null);
			}
			if (model.getMicroCompanyAmt() != null && BigDecimal.ZERO.compareTo(model.getMicroCompanyAmt()) == 0) {
				model.setMicroCompanyAmt(null);
			}
			if (model.getOthAmt() != null && BigDecimal.ZERO.compareTo(model.getOthAmt()) == 0) {
				model.setOthAmt(null);
			}
			if (model.getSumAmt() != null && BigDecimal.ZERO.compareTo(model.getSumAmt()) == 0) {
				model.setSumAmt(null);
			}
		}
    }

	@SuppressWarnings("unchecked")
	@Override
	public Map<Object, Object> createDataMS(Map<String, Object> param) {
		return restTemplate.postForObject(
				SvrServiceApplication.PBC_SERVICE_NAME + "/PbcMonthReportAsset/createDataCompanyBond", param,
				Map.class);
	}

	public void dealCompanyDateListForExport(PbcReportCompanyDetailDto detail) {
		BigDecimal tenthousand = new BigDecimal(10000);
		// 大型企业
		if (detail.getLargeCompanyAmt() != null) {
			detail.setLargeCompanyAmt(detail.getLargeCompanyAmt().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		}
		if (detail.getMediumCompanyAmt() != null) {// 中型企业
			detail.setMediumCompanyAmt(detail.getMediumCompanyAmt().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		}
		if (detail.getSmallCompanyAmt() != null) {// 小型企业
			detail.setSmallCompanyAmt(detail.getSmallCompanyAmt().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		}
		if (detail.getMicroCompanyAmt() != null) {// 微型企业
			detail.setMicroCompanyAmt(detail.getMicroCompanyAmt().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		}
		if (detail.getOthAmt() != null) {// 其他（住户、广义政府及境外）
			detail.setOthAmt(detail.getOthAmt().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		}
		if (detail.getSumAmt() != null) {// 合计
			detail.setSumAmt(detail.getSumAmt().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		}
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
		// 导出excel或预览时，值为0的单元格显示成0还是显示成空，读取系统参数
        String isShowZero = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SHOW_ZERO);
		List<PbcReportCompanyDetailDto> listDetail = pbcReportCompanyDetailMapper.selectByReportuuid(reportuuid);
		if (!CollectionUtils.isEmpty(listDetail)) {
			for (PbcReportCompanyDetailDto detail : listDetail) {
				// 处理数据
				dealCompanyDateListForExport(detail);
				dealProdData(detail, isShowZero);
				data.put(detail.getPropertycode(), detail);
			}
		}
	}
}
