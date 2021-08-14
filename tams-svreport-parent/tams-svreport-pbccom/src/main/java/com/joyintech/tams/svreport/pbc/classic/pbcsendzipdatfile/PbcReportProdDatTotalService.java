package com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.PbcSendZipDatFileMapper;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportProdDatMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportProdDatDto;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.dao.PbcPrdBaseinfoEndSubMapper;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dao.PbcPrdBaseinfoEndSubDatMapper;
import com.joyintech.tams.svreport.pbc.common.service.PbcDatPriviewService;
import com.joyintech.tams.svreport.pbc.report.dao.PbcMonthReportMapper;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 功能:人行报送明细总入口
 * @since 1.6.0
 * @version 1.6.0
 * @作者：meifaxin
 * @创建时间：2021年7月29日
 * @修改记录：
 */
@Service
public abstract class PbcReportProdDatTotalService extends PbcDatPriviewService {
    
	@Autowired
	private PbcReportProdDatMapper pbcReportProdDatMapper;
	@Autowired
	private PbcSendZipDatFileMapper pbcSendZipDatFileMapper;
	@Override
    public Map<Object, Object> createData(Map<String,Object> param){
    	Map<Object,Object> data  = new HashMap<Object,Object>();
    	if(param.get("reportuuid")==null){
    		return data;
    	}
		String reportuuid=param.get("reportuuid").toString();
		String reporttype=param.get("reporttype").toString();
		PbcSendZipDatFile pbcReport = pbcSendZipDatFileMapper.selectByUUIDAndType(reportuuid,reporttype);
		// 如果报表为空则返回data
		if (pbcReport == null){
			return data;
		}
		//pbcReport.setProdVariety(pbcReport.getProdVariety() + "-" + SysDataUtil.getNameFromDataDict(DictCode.产品品种, pbcReport.getProdVariety()));
		data.put("report",JsonUtils.object2Map(pbcReport));
		// 导出excel或预览时，值为0的单元格显示成0还是显示成空，读取系统参数
		String isShowZero = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SHOW_ZERO);
		List<PbcReportProdDatDto> listDetail = pbcReportProdDatMapper.selectPbcReportPrdDatDiff(reportuuid,reporttype);
		if (!CollectionUtils.isEmpty(listDetail)){
			for(PbcReportProdDatDto detail : listDetail){
				dealAreaDateListForDiff(detail);
				dealAreaDateListForExport(detail);
				dealProdData(detail,isShowZero);
				data.put(detail.getPropertyCode(), JsonUtils.object2Map(detail));
			}
		}
    	return  data;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public Map<Object, Object> createDataMS(Map<String,Object> param){
    	return createData(param);
    }



	/**
	 * @Description 导出预览为零的数据显示空还是显示零
	 * @param detail
	 * @version 1.7 2021年8月2日
	 * @param isShowZero
	 */
	private void dealProdData(PbcReportProdDatDto detail, String isShowZero) {
		// 显示成零
		if("01".equals(isShowZero)){
			if(detail.getCny()==null){
				detail.setCny(BigDecimal.ZERO);
			}
			if(detail.getUsdToCny()==null){
				detail.setUsdToCny(BigDecimal.ZERO);
			}
			if(detail.getEurToCny()==null){
				detail.setEurToCny(BigDecimal.ZERO);
			}
			if(detail.getGbpToCny()==null){
				detail.setGbpToCny(BigDecimal.ZERO);
			}
			if(detail.getJpyToCny()==null){
				detail.setJpyToCny(BigDecimal.ZERO);
			}
			if(detail.getOthToCny()==null){
				detail.setOthToCny(BigDecimal.ZERO);
			}
			if(detail.getTotal()==null){
				detail.setTotal(BigDecimal.ZERO);
			}
			if(detail.getProdcounts()==null){
				detail.setProdcounts(new Long(0));
			}
		} else if("02".equals(isShowZero)){
			if(detail.getCny()!=null&&(BigDecimal.ZERO).compareTo(detail.getCny())==0){
				detail.setCny(null);
			}
			if(detail.getUsdToCny()!=null&&(BigDecimal.ZERO).compareTo(detail.getUsdToCny())==0){
				detail.setUsdToCny(null);
			}
			if(detail.getEurToCny()!=null&&(BigDecimal.ZERO).compareTo(detail.getEurToCny())==0){
				detail.setEurToCny(null);
			}
			if(detail.getGbpToCny()!=null&&(BigDecimal.ZERO).compareTo(detail.getGbpToCny())==0){
				detail.setGbpToCny(null);
			}
			if(detail.getJpyToCny()!=null&&(BigDecimal.ZERO).compareTo(detail.getJpyToCny())==0){
				detail.setJpyToCny(null);
			}
			if(detail.getOthToCny()!=null&&(BigDecimal.ZERO).compareTo(detail.getOthToCny())==0){
				detail.setOthToCny(null);
			}
			if(detail.getTotal()!=null&&(BigDecimal.ZERO).compareTo(detail.getTotal())==0){
				detail.setTotal(null);
			}
			if(detail.getProdcounts()!=null&&(new Long(0)).compareTo(detail.getProdcounts())==0){
				detail.setProdcounts(null);
			}
		}
	}

	/**
	 * 功能:处理查询出的产品报表明细数据
	 * @since 1.6.0
	 * @version 1.6.0
	 * @param detail
	 * @return
	 * PbcReportAssetDebtDetailDto
	 */
	public void dealAreaDateListForExport(PbcReportProdDatDto detail) {
		BigDecimal tenthousand = new BigDecimal(10000);
		// 人民币
		if(detail.getCny() != null){
			detail.setCny(detail.getCny().divide(tenthousand, 6, BigDecimal.ROUND_HALF_UP));
		}
		//外币
//		if(detail.getForeignCcy() != null){
//			detail.setForeignCcy(detail.getForeignCcy().divide(tenthousand));
//		}
		//美元折人民币
		if(detail.getUsdToCny() != null){
			detail.setUsdToCny(detail.getUsdToCny().divide(tenthousand, 6, BigDecimal.ROUND_HALF_UP));
		}
		//欧元折人民币
		if(detail.getEurToCny() != null){
			detail.setEurToCny(detail.getEurToCny().divide(tenthousand, 6, BigDecimal.ROUND_HALF_UP));
		}
		//英镑折人民币
		if(detail.getGbpToCny() != null){
			detail.setGbpToCny(detail.getGbpToCny().divide(tenthousand, 6, BigDecimal.ROUND_HALF_UP));
		}
		//日元折人民币
		if(detail.getJpyToCny() != null){
			detail.setJpyToCny(detail.getJpyToCny().divide(tenthousand, 6, BigDecimal.ROUND_HALF_UP));
		}
		//其他币种(外币)折人民币
		if(detail.getOthToCny() != null){
			detail.setOthToCny(detail.getOthToCny().divide(tenthousand, 6, BigDecimal.ROUND_HALF_UP));
		}
		//合计
		if(detail.getTotal() != null){
			detail.setTotal(detail.getTotal().divide(tenthousand, 6, BigDecimal.ROUND_HALF_UP));
		}
	}

	/**
	 * 功能:处理查询出资管产品统计模板和资管产品统计模板DAT差异
	 * @since 1.6.0
	 * @version 1.6.0
	 * @param detail
	 * @return
	 * PbcReportAssetDebtDetailDto
	 */
	public void dealAreaDateListForDiff(PbcReportProdDatDto detail) {
		// 人民币
		if(!StringUtil.equals(detail.getCny(),detail.getCnyPrd())){
			detail.setCnyDiff(1);
		}
		//美元折人民币
		if(!StringUtil.equals(detail.getUsdToCny(),detail.getUsdToCnyPrd())){
			detail.setUsdToCnyDiff(1);
		}
		//欧元折人民币
		if(!StringUtil.equals(detail.getEurToCny(),detail.getUsdToCnyPrd())){
			detail.setEurToCnyDiff(1);
		}
		//英镑折人民币
		if(!StringUtil.equals(detail.getGbpToCny(),detail.getGbpToCnyPrd())){
			detail.setGbpToCnyDiff(1);
		}
		//日元折人民币
		if(!StringUtil.equals(detail.getJpyToCny(),detail.getJpyToCnyPrd())){
			detail.setJpyToCnyDiff(1);
		}
		//其他币种(外币)折人民币
		if(!StringUtil.equals(detail.getOthToCny(),detail.getOthToCnyPrd())){
			detail.setOthToCnyDiff(1);
		}
		//合计
		if(!StringUtil.equals(detail.getTotal(),detail.getTotalPrd())){
			detail.setTotalDiff(1);
		}
	}

    



	
	

    
}
