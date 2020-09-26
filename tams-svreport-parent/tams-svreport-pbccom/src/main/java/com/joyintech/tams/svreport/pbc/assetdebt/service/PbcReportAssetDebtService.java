package com.joyintech.tams.svreport.pbc.assetdebt.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.pbc.assetdebt.dao.PbcReportAssetDebtDetailMapper;
import com.joyintech.tams.svreport.pbc.assetdebt.dto.PbcReportAssetDebtDetailDto;
import com.joyintech.tams.svreport.pbc.common.annotation.TemplateName;
import com.joyintech.tams.svreport.pbc.common.constants.PbcTemplateName;
import com.joyintech.tams.svreport.pbc.common.service.PbcExportService;
import com.joyintech.tams.svreport.pbc.report.dao.PbcMonthReportMapper;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;

/**
 * 功能:人行报表-资产负债端service
 * 
 * @since 1.6.0
 * @version 1.6.0
 * @作者：weiyuqiang @创建时间：2019年3月4日 @修改记录：
 */
@Service
@TemplateName(PbcTemplateName.PBC_REPORT_2_1)
public class PbcReportAssetDebtService extends PbcExportService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PbcReportAssetDebtDetailMapper pbcReportAssetDebtDetailMapper;

    @Autowired
    private PbcMonthReportMapper pbcMonthReportMapper;

    private DecimalFormat df = new DecimalFormat("0.000000");

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
        List<PbcReportAssetDebtDetailDto> listDetail = pbcReportAssetDebtDetailMapper.selectByReportuuid(reportuuid);
        if (!CollectionUtils.isEmpty(listDetail)) {
            for (PbcReportAssetDebtDetailDto detail : listDetail) {
                // 处理数据
                dealAssetDebtDateListForExport(detail);
                dealProdData(detail, isShowZero);
                data.put(detail.getPropertycode(), detail);
            }
        }
        return data;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Object, Object> createDataMS(Map<String, Object> param) {
        return restTemplate.postForObject(SvrServiceApplication.PBC_SERVICE_NAME + "/PbcMonthReportAsset/createDataAssetDebt", param, Map.class);
    }

    /**
     * @Description 导出数据处理
     * @param param
     * @param data
     * @return
     * @return Map<Object,Object>
     * @version 1.7 2019年5月13日
     */
    public Map<Object, Object> createDataForExp(Map<String, Object> param, Map<Object, Object> data) {
        if (param.get("reportuuid") == null) {
            return data;
        }
        String reportuuid = param.get("reportuuid").toString();
        PbcMonthReport pbcReport = pbcMonthReportMapper.selectByPrimaryKey(reportuuid);
        pbcReport.setProdVariety(pbcReport.getProdVariety() + "-" + SysDataUtil.getNameFromDataDict(DictCode.产品品种, pbcReport.getProdVariety()));
        data.put("report", pbcReport);
        // 导出excel或预览时，值为0的单元格显示成0还是显示成空，读取系统参数
        String isShowZero = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SHOW_ZERO);
        List<PbcReportAssetDebtDetailDto> listDetail = pbcReportAssetDebtDetailMapper.selectByReportuuid(reportuuid);
        if (!CollectionUtils.isEmpty(listDetail)) {
            for (PbcReportAssetDebtDetailDto detail : listDetail) {
                // 处理数据
                dealAssetDebtDateListForExport(detail);
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
    private void dealProdData(PbcReportAssetDebtDetailDto detail, String isShowZero) {
        if ("01".equals(isShowZero)) {
            if(detail.getCny() == null){
                detail.setCny(BigDecimal.ZERO);
            }
            if(detail.getUsdToCny() == null){
                detail.setUsdToCny(BigDecimal.ZERO);
            }
            if(detail.getEurToCny() == null){
                detail.setEurToCny(BigDecimal.ZERO);
            }
            if(detail.getGbpToCny() == null){
                detail.setGbpToCny(BigDecimal.ZERO);
            }
            if(detail.getJpyToCny() == null){
                detail.setJpyToCny(BigDecimal.ZERO);
            }
            if(detail.getOthToCny() == null){
                detail.setOthToCny(BigDecimal.ZERO);
            }
            if(detail.getTotal() == null){
                detail.setTotal(BigDecimal.ZERO);
            }
            
		} else if ("02".equals(isShowZero)) {
			if (detail.getCny() != null && BigDecimal.ZERO.compareTo(detail.getCny()) == 0) {
				detail.setCny(null);
			}
			if (detail.getUsdToCny() != null && BigDecimal.ZERO.compareTo(detail.getUsdToCny()) == 0) {
				detail.setUsdToCny(null);
			}
			if (detail.getEurToCny() != null && BigDecimal.ZERO.compareTo(detail.getEurToCny()) == 0) {
				detail.setEurToCny(null);
			}
			if (detail.getGbpToCny() != null && BigDecimal.ZERO.compareTo(detail.getGbpToCny()) == 0) {
				detail.setGbpToCny(null);
			}
			if (detail.getJpyToCny() != null && BigDecimal.ZERO.compareTo(detail.getJpyToCny()) == 0) {
				detail.setJpyToCny(null);
			}
			if (detail.getOthToCny() != null && BigDecimal.ZERO.compareTo(detail.getOthToCny()) == 0) {
				detail.setOthToCny(null);
			}
			if (detail.getTotal() != null && BigDecimal.ZERO.compareTo(detail.getTotal()) == 0) {
				detail.setTotal(null);
			}
		}
    }

    /**
     * 功能:处理查询出的资产负债报表明细数据
     * 
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月4日
     * @param detail
     * @return PbcReportAssetDebtDetailDto
     */
    public void dealAssetDebtDateListForExport(PbcReportAssetDebtDetailDto detail) {
        BigDecimal tenthousand = new BigDecimal(10000);
        // Add By QuJianZhong 20200508 金额单位由万元改为元 start
        if(null!=detail.getReporttype() && "ONEPRD_ASSET_DEBT".equals(detail.getReporttype())){
        	tenthousand = new BigDecimal(1);
        }
        // Add By QuJianZhong 20200508 金额单位由万元改为元 end
        
        // 人民币
        if (detail.getCny() != null) {
            detail.setCny(detail.getCny().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
            detail.setCnyString(df.format(detail.getCny()));
        }
        if (detail.getUsdToCny() != null) {// 美元折人民币
            detail.setUsdToCny(detail.getUsdToCny().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
            detail.setUsdToCnyString(df.format(detail.getUsdToCny()));
        }
        if (detail.getEurToCny() != null) {// 欧元折人民币
            detail.setEurToCny(detail.getEurToCny().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
            detail.setEurToCnyString(df.format(detail.getEurToCny()));
        }
        if (detail.getGbpToCny() != null) {// 英镑折人民币
            detail.setGbpToCny(detail.getGbpToCny().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
            detail.setGbpToCnyString(df.format(detail.getGbpToCny()));
        }
        if (detail.getJpyToCny() != null) {// 日元折人民币
            detail.setJpyToCny(detail.getJpyToCny().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
            detail.setJpyToCnyString(df.format(detail.getJpyToCny()));
        }
        if (detail.getOthToCny() != null) {// 其他币种折人民币
            detail.setOthToCny(detail.getOthToCny().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
            detail.setOthToCnyString(df.format(detail.getOthToCny()));
        }
        if (detail.getTotal() != null) {// 合计
            detail.setTotal(detail.getTotal().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
            detail.setTotalString(df.format(detail.getTotal()));
        }
    }
    
    
    
    public Map<Object, Object> createDataForExport(Map<String, Object> param) {
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
        if(StringUtil.isNotEmpty(param.get("pbcAccountId"))){
        	pbcReport.setPbcAccountId(param.get("pbcAccountId").toString());
        }
        pbcReport.setProdVariety(pbcReport.getProdVariety() + "-" + SysDataUtil.getNameFromDataDict(DictCode.产品品种, pbcReport.getProdVariety()));
        data.put("report", pbcReport);
        // 导出excel或预览时，值为0的单元格显示成0还是显示成空，读取系统参数
        String isShowZero = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SHOW_ZERO);
        List<PbcReportAssetDebtDetailDto> listDetail = pbcReportAssetDebtDetailMapper.selectByReportuuidForExport(reportuuid);
        if (!CollectionUtils.isEmpty(listDetail)) {
            for (PbcReportAssetDebtDetailDto detail : listDetail) {
                // 处理数据
                dealAssetDebtDateListForExport(detail);
                dealProdData(detail, isShowZero);
                data.put(detail.getPropertycode(), detail);
            }
        }
        return data;
    }


}
