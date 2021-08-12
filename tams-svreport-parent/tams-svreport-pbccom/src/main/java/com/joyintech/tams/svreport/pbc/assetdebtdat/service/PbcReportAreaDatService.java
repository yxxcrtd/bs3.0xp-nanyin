package com.joyintech.tams.svreport.pbc.assetdebtdat.service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.PbcSendZipDatFileMapper;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportAreaDatDetailMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportAreaDatDetailDto;
import com.joyintech.tams.svreport.pbc.common.annotation.TemplateName;
import com.joyintech.tams.svreport.pbc.common.constants.PbcDatTemplateName;
import com.joyintech.tams.svreport.pbc.common.service.PbcDatPriviewService;
import com.joyintech.tams.svreport.pbc.report.dao.PbcMonthReportMapper;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 功能:人行报表-资产负债端service
 * @since 1.6.0
 * @version 1.6.0
 * @作者：weiyuqiang
 * @创建时间：2021年8月4日
 * @修改记录：
 */
@Service
@TemplateName(PbcDatTemplateName.PBC_DAT_REPORT_2_3)
public class PbcReportAreaDatService extends PbcDatPriviewService {
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PbcReportAreaDatDetailMapper pbcReportAreaDatDetailMapper;
	@Autowired
	private PbcSendZipDatFileMapper pbcSendZipDatFileMapper;
	@Autowired
	private PbcMonthReportMapper pbcMonthReportMapper;

    @Override
    public Map<Object, Object> createData(Map<String,Object> param){
    	Map<Object,Object> data  = new HashMap<Object,Object>();
    	if(param.get("reportuuid")==null){
    		return data;
    	}
    	String reportuuid = param.get("reportuuid").toString();
		String reporttype = param.get("reporttype").toString();
		PbcSendZipDatFile pbcReport = pbcSendZipDatFileMapper.selectByUUIDAndType(reportuuid,reporttype);
		// 如果报表为空则返回data
        if (pbcReport == null){
            return data;
        }
    	data.put("report", JsonUtils.object2Map(pbcReport));
    	// 导出excel或预览时，值为0的单元格显示成0还是显示成空，读取系统参数
        String isShowZero = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SHOW_ZERO);
    	List<PbcReportAreaDatDetailDto> listDetail = pbcReportAreaDatDetailMapper.selectDatDiffByReport(reportuuid,reporttype);
    	if (!CollectionUtils.isEmpty(listDetail)){
			for(PbcReportAreaDatDetailDto detail : listDetail){
				//登记表和DAT表数据差异比较
				dealAreaListForDiff(detail);
				// 处理数据
				dealAreaDateListForExport(detail);
				dealProdData(detail,isShowZero);
				data.put(detail.getPropertycode(), JsonUtils.object2Map(detail));
			}
    	}
    	return  data;
    }

    /**
     * 
    * 导出预览为0的数据显示为空还是为0.
    * @author liuyu
    * @time 2019年7月4日 下午4:22:09
    * @param detail
    * @param isShowZero 01:显示为0, 02:显示为空
     */
    private void dealProdData(PbcReportAreaDatDetailDto detail, String isShowZero) {
       if("01".equals(isShowZero)){
           if(detail.getBalance() == null){
               detail.setBalance(BigDecimal.ZERO);
           }
		} else if ("02".equals(isShowZero)) {
			if (detail.getBalance() != null && BigDecimal.ZERO.compareTo(detail.getBalance()) == 0) {
				detail.setBalance(null);
			}
		}
    }

    @SuppressWarnings("unchecked")
	@Override
    public Map<Object, Object> createDataMS(Map<String,Object> param){
    	return createData(param);
    }

	/**
	 * 功能:处理查询出的资产负债报表明细数据
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2021年8月4日
	 * @param detail
	 * @return 
	 * PbcReportAssetDebtDetailDto
	 */
	public void dealAreaDateListForExport(PbcReportAreaDatDetailDto detail) {
		BigDecimal tenthousand = new BigDecimal(10000);
		//余额
		if(detail.getBalance() != null){
			detail.setBalance(detail.getBalance().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		} 
	}

	/**
	 * @Description 导出数据处理
	 * @param param
	 * @param data
	 * @return void
	 * @version 1.7 2021年8月4日
	 */
	public void createDataForExp(Map<String, Object> param, Map<Object, Object> data) {
		if(param.get("reportuuid")==null){
    		return;
    	}
    	String reportuuid = param.get("reportuuid").toString();
    	PbcMonthReport pbcReport = pbcMonthReportMapper.selectByPrimaryKey(reportuuid);
    	pbcReport.setProdVariety(pbcReport.getProdVariety() + "-" + SysDataUtil.getNameFromDataDict(DictCode.产品品种, pbcReport.getProdVariety()));
    	data.put("report", JsonUtils.object2Map(pbcReport));
    	// 导出excel或预览时，值为0的单元格显示成0还是显示成空，读取系统参数
        String isShowZero = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SHOW_ZERO);
    	List<PbcReportAreaDatDetailDto> listDetail = pbcReportAreaDatDetailMapper.selectByReportuuid(reportuuid);
    	if (!CollectionUtils.isEmpty(listDetail)){
			for(PbcReportAreaDatDetailDto detail : listDetail){
				// 处理数据
				dealAreaDateListForExport(detail);
				dealProdData(detail, isShowZero);
				data.put(detail.getPropertycode(), detail);
			}
    	}
	}
	/**
	 * 功能:登记表和DAT表数据差异比较
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2021年8月4日
	 * @param detail
	 * @return
	 */
	public void dealAreaListForDiff(PbcReportAreaDatDetailDto detail) {
		//余额
		if(!StringUtil.equals(detail.getBalance(),detail.getBalancePrd())){
				detail.setBalanceDiff(1);
		}
	}
}
