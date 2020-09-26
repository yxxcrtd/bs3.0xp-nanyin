package com.joyintech.tams.svreport.pbc.assetdebt.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.svreport.pbc.assetdebt.dao.PbcReportAssetIncomeDetailMapper;
import com.joyintech.tams.svreport.pbc.assetdebt.dto.PbcReportAssetIncomeDetail;
import com.joyintech.tams.svreport.pbc.common.annotation.TemplateName;
import com.joyintech.tams.svreport.pbc.common.constants.PbcTemplateName;
import com.joyintech.tams.svreport.pbc.common.service.PbcExportService;
import com.joyintech.tams.svreport.pbc.report.dao.PbcMonthReportMapper;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;

/**
 * @Description 资产收益权按基础资产投向分类统计报表
 * @author jiangxiao
 * @version 1.0 2019年3月5日
 * @since 1.0
 */
@Service
@TemplateName(PbcTemplateName.PBC_REPORT_2_4)
public class PbcReportAssetIncomeDetailService extends PbcExportService {

    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PbcReportAssetIncomeDetailMapper pbcReportAssetIncomeDetailMapper;
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
        if (pbcReport == null) {
            return data;
        }
        pbcReport.setProdVariety(pbcReport.getProdVariety() + "-" + SysDataUtil.getNameFromDataDict(DictCode.产品品种, pbcReport.getProdVariety()));
        data.put("report", pbcReport);
        // 导出excel或预览时，值为0的单元格显示成0还是显示成空，读取系统参数
        String isShowZero = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SHOW_ZERO);
        List<PbcReportAssetIncomeDetail> listDetail = pbcReportAssetIncomeDetailMapper.selectByReportuuid(reportuuid);
        for (PbcReportAssetIncomeDetail detail : listDetail) {
            dealProdData(detail, isShowZero);
            data.put(detail.getPropertycode(), processDataForExport(detail));
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
    private void dealProdData(PbcReportAssetIncomeDetail detail, String isShowZero) {
        if ("01".equals(isShowZero)) {
            if(detail.getLoanIncomeRight() == null){
                detail.setLoanIncomeRight(BigDecimal.ZERO);
            }
            if(detail.getDebtSecuritiesIncomeRights() == null){
                detail.setDebtSecuritiesIncomeRights(BigDecimal.ZERO);
            }
            if(detail.getReceivableIncomeRights() == null){
                detail.setReceivableIncomeRights(BigDecimal.ZERO);
            }
            if(detail.getOtherCreditorRights() == null){
                detail.setOtherCreditorRights(BigDecimal.ZERO);
            }
            if(detail.getStockIncomeRight() == null){
                detail.setStockIncomeRight(BigDecimal.ZERO);
            }
            if(detail.getOtherEquityIncomeRights() == null){
                detail.setOtherEquityIncomeRights(BigDecimal.ZERO);
            }
            if(detail.getPropertyRights() == null){
                detail.setPropertyRights(BigDecimal.ZERO);
            }
            if(detail.getTotal() == null){
                detail.setTotal(BigDecimal.ZERO);
            }
            
		} else if ("02".equals(isShowZero)) {
			if (detail.getLoanIncomeRight() != null && BigDecimal.ZERO.compareTo(detail.getLoanIncomeRight()) == 0) {
				detail.setLoanIncomeRight(null);
			}
			if (detail.getDebtSecuritiesIncomeRights() != null
					&& BigDecimal.ZERO.compareTo(detail.getDebtSecuritiesIncomeRights()) == 0) {
				detail.setDebtSecuritiesIncomeRights(null);
			}
			if (detail.getReceivableIncomeRights() != null
					&& BigDecimal.ZERO.compareTo(detail.getReceivableIncomeRights()) == 0) {
				detail.setReceivableIncomeRights(null);
			}
			if (detail.getOtherCreditorRights() != null
					&& BigDecimal.ZERO.compareTo(detail.getOtherCreditorRights()) == 0) {
				detail.setOtherCreditorRights(null);
			}
			if (detail.getStockIncomeRight() != null && BigDecimal.ZERO.compareTo(detail.getStockIncomeRight()) == 0) {
				detail.setStockIncomeRight(null);
			}
			if (detail.getOtherEquityIncomeRights() != null
					&& BigDecimal.ZERO.compareTo(detail.getOtherEquityIncomeRights()) == 0) {
				detail.setOtherEquityIncomeRights(null);
			}
			if (detail.getPropertyRights() != null && BigDecimal.ZERO.compareTo(detail.getPropertyRights()) == 0) {
				detail.setPropertyRights(null);
			}
			if (detail.getTotal() != null && BigDecimal.ZERO.compareTo(detail.getTotal()) == 0) {
				detail.setTotal(null);
			}
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Object, Object> createDataMS(Map<String, Object> param) {
        return restTemplate.postForObject(SvrServiceApplication.PBC_SERVICE_NAME + "/PbcMonthReportAsset/createDataAssetIncomeDetail", param, Map.class);
    }

    /**
     * @Description 金额格式化
     * @param detail
     * @return PbcReportAssetIncomeDetail
     * @version 1.0 2019年3月5日
     */
    public PbcReportAssetIncomeDetail processDataForExport(PbcReportAssetIncomeDetail detail) {
        BigDecimal tenthousand = new BigDecimal(10000);
        // 贷款收益权
        if (detail.getLoanIncomeRight() != null) {
            detail.setLoanIncomeRight(detail.getLoanIncomeRight().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
        }
        // 债务证券收益权
        if (detail.getDebtSecuritiesIncomeRights() != null) {
            detail.setDebtSecuritiesIncomeRights(detail.getDebtSecuritiesIncomeRights().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
        }
        // 应收账款收益权
        if (detail.getReceivableIncomeRights() != null) {
            detail.setReceivableIncomeRights(detail.getReceivableIncomeRights().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
        }
        // 其他债权收益权
        if (detail.getOtherCreditorRights() != null) {
            detail.setOtherCreditorRights(detail.getOtherCreditorRights().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
        }
        // 股票收益权
        if (detail.getStockIncomeRight() != null) {
            detail.setStockIncomeRight(detail.getStockIncomeRight().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
        }
        // 其他股权收益权
        if (detail.getOtherEquityIncomeRights() != null) {
            detail.setOtherEquityIncomeRights(detail.getOtherEquityIncomeRights().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
        }
        // 物权资产收益权
        if (detail.getPropertyRights() != null) {
            detail.setPropertyRights(detail.getPropertyRights().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
        }
        // 总计
        if (detail.getTotal() != null) {
            detail.setTotal(detail.getTotal().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
        }
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
        pbcReport.setProdVariety(pbcReport.getProdVariety() + "-" + SysDataUtil.getNameFromDataDict(DictCode.产品品种, pbcReport.getProdVariety()));
        data.put("report", pbcReport);
        // 导出excel或预览时，值为0的单元格显示成0还是显示成空，读取系统参数
        String isShowZero = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SHOW_ZERO);
        List<PbcReportAssetIncomeDetail> listDetail = pbcReportAssetIncomeDetailMapper.selectByReportuuid(reportuuid);
        for (PbcReportAssetIncomeDetail detail : listDetail) {
            dealProdData(detail, isShowZero);
            data.put(detail.getPropertycode(), processDataForExport(detail));
        }
    }
}
