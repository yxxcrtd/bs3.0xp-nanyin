package com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.interf.common.util.ObjEqual;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportProdDatMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportProdDatDiffDto;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dao.PbcPrdBaseinfoSubMapper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dto.PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dao.PbcPrdBaseinfoSubDatMapper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dto.PbcPrdBaseinfoSubDat;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.dao.PbcPrdBaseinfoEndSubMapper;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.dto.PbcPrdBaseinfoEndSub;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dao.PbcPrdBaseinfoEndSubDatMapper;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dto.PbcPrdBaseinfoEndSubDat;
import com.joyintech.tams.svreport.pbc.common.service.PbcDatPriviewService;
import com.joyintech.tams.svreport.pbc.prod.dao.PbcReportProdDetailMapper;
import com.joyintech.tams.svreport.pbc.prod.dto.PbcReportProdDetailDto;
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
    private PbcPrdBaseinfoSubDatMapper pbcPrdBaseinfoSubDatMapper;
	@Autowired
	private PbcPrdBaseinfoSubMapper pbcPrdBaseinfoSubMapper;
	@Autowired
	private PbcPrdBaseinfoEndSubDatMapper pbcPrdBaseinfoEndSubDatMapper;
	@Autowired
	private PbcPrdBaseinfoEndSubMapper pbcPrdBaseinfoEndSubMapper;
	@Autowired
	private PbcMonthReportMapper pbcMonthReportMapper;
	@Autowired
	private PbcReportProdDatMapper pbcReportProdDatMapper;

	@Override
    public Map<Object, Object> createData(Map<String,Object> param){
    	Map<Object,Object> data  = new HashMap<Object,Object>();
    	if(param.get("reportuuid")==null){
    		return data;
    	}
    	String reportuuid=param.get("reportuuid").toString();
		String reporttype=param.get("reporttype").toString();
		if(reporttype.equals(DictCodeListExt2.人行报送明细类型_产品基本信息.getValue())){
			PbcPrdBaseinfoSubDat pbcPrdBaseinfoSubDat = pbcPrdBaseinfoSubDatMapper.selectByPrimaryKey(reportuuid);
			PbcPrdBaseinfoSub pbcPrdBaseinfoSub=pbcPrdBaseinfoSubMapper.selectByPrimaryKey(reportuuid);
			Map<String,Integer> redMap=ObjEqual.domainEquals(pbcPrdBaseinfoSubDat,pbcPrdBaseinfoSub);
			data.put("report", dealPbcPrdBaseinfoSubDat(pbcPrdBaseinfoSubDat));
			data.put("redMap", redMap);
			return data;
		}else if(reporttype.equals(DictCodeListExt2.人行报送明细类型_产品终止信息.getValue())){
			PbcPrdBaseinfoEndSubDat pbcPrdBaseinfoEndSubDat = pbcPrdBaseinfoEndSubDatMapper.selectByPrimaryKey(reportuuid);
			PbcPrdBaseinfoEndSub pbcPrdBaseinfoEndSub=pbcPrdBaseinfoEndSubMapper.selectByPrimaryKey(reportuuid);
			Map<String,Integer> redMap=ObjEqual.domainEquals(pbcPrdBaseinfoEndSubDat,pbcPrdBaseinfoEndSub);
			data.put("report", pbcPrdBaseinfoEndSubDat);
			data.put("redMap", redMap);
			return data;
		}else {
			PbcMonthReport pbcReport = pbcMonthReportMapper.selectByPrimaryKey(reportuuid);
			// 如果报表为空则返回data
			if (pbcReport == null){
				return data;
			}
			pbcReport.setProdVariety(pbcReport.getProdVariety() + "-" + SysDataUtil.getNameFromDataDict(DictCode.产品品种, pbcReport.getProdVariety()));
			data.put("report",JsonUtils.object2Map(pbcReport));
			// 导出excel或预览时，值为0的单元格显示成0还是显示成空，读取系统参数
			String isShowZero = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SHOW_ZERO);
			List<PbcReportProdDatDiffDto> listDetail = pbcReportProdDatMapper.selectPbcReportPrdDatDiff(reportuuid,reporttype);
			if (!CollectionUtils.isEmpty(listDetail)){
				for(PbcReportProdDatDiffDto detail : listDetail){
					//PBC_REPORT_PROD表和PBC_REPORT_PROD_DAT差异的实体类
					dealAreaDateListForDiff(detail);
					dealProdData(detail,isShowZero);
					// 处理数据
					if (!DictCodeListExt2.人行报表名称_资管产品只数情况统计表.getValue().equals(pbcReport.getReporttype())){
						// 处理数据
						dealAreaDateListForExport(detail);
					}
					data.put(detail.getPropertyCode(), JsonUtils.object2Map(detail));
				}
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
	 * 功能:处理查询出的产品报表明细数据
	 * @since 1.6.0
	 * @version 1.6.0
	 * @param result
	 * @return
	 * PbcPrdBaseinfoSubDat
	 */
	public PbcPrdBaseinfoSubDat dealPbcPrdBaseinfoSubDat(PbcPrdBaseinfoSubDat result) {
		if (StringUtil.isNotEmpty(result.getPbcProdType())){
			result.setPbcProdType( SysDataUtil.getNameFromDataDict(DictCode.人行产品种类,result.getPbcProdType()));
		}
		if (StringUtil.isNotEmpty(result.getCustomerType())){
			result.setCustomerType( SysDataUtil.getNameFromDataDict(DictCode.人行客户类型,result.getCustomerType()));
		}
		if (StringUtil.isNotEmpty(result.getRaiseType())){
			result.setRaiseType( SysDataUtil.getNameFromDataDict(DictCode.人行募集方式,result.getRaiseType()));
		}
		if (StringUtil.isNotEmpty(result.getManageType())){
			result.setManageType( SysDataUtil.getNameFromDataDict(DictCode.人行管理类型,result.getManageType()));
		}
		if (StringUtil.isNotEmpty(result.getFiduciaryDuty())){
			result.setFiduciaryDuty( SysDataUtil.getNameFromDataDict(DictCode.人行管理方式,result.getFiduciaryDuty()));
		}
		if (StringUtil.isNotEmpty(result.getOperateMode())){
			result.setOperateMode(result.getOperateMode() + StringUtil.SPACE + SysDataUtil.getNameFromDataDict(DictCode.人行产品运作模式,result.getOperateMode()));
		}
		if (StringUtil.isNotEmpty(result.getContMode())){
			result.setContMode(SysDataUtil.getNameFromDataDict(DictCode.人行合作模式,result.getContMode()));
		}
		if (StringUtil.isNotEmpty(result.getBusiModel())){
			result.setBusiModel( SysDataUtil.getNameFromDataDict(DictCode.人行业务模式,result.getBusiModel()));
		}
		if (StringUtil.isNotEmpty(result.getIncomeGuaranIdent())){
			result.setIncomeGuaranIdent(StringUtil.SPACE + SysDataUtil.getNameFromDataDict(DictCode.人行是否,result.getIncomeGuaranIdent()));
		}
		if (StringUtil.isNotEmpty(result.getPrinGuaranIdent())){
			result.setPrinGuaranIdent( SysDataUtil.getNameFromDataDict(DictCode.人行是否,result.getPrinGuaranIdent()));
		}
		if (StringUtil.isNotEmpty(result.getTermRightFlag())){
			result.setTermRightFlag( SysDataUtil.getNameFromDataDict(DictCode.人行有无,result.getTermRightFlag()));
		}
		if (StringUtil.isNotEmpty(result.getCusRedeemFlag())){
			result.setCusRedeemFlag( SysDataUtil.getNameFromDataDict(DictCode.人行有无,result.getCusRedeemFlag()));
		}
		if (StringUtil.isNotEmpty(result.getCreditFlag())){
			result.setCreditFlag( SysDataUtil.getNameFromDataDict(DictCode.人行是否,result.getCreditFlag()));
		}
		if (StringUtil.isNotEmpty(result.getCreditType())){
			result.setCreditType( SysDataUtil.getNameFromDataDict(DictCode.人行信誉机构类型,result.getCreditType()));
		}
		if (StringUtil.isNotEmpty(result.getCreditMode())){
			//多个用,切开
			String [] creditModes=result.getCreditMode().split(GlobalConstant.COMMA);
			String creditMode="";
			int i=0;
			for(String single:creditModes){
				i++;
				creditMode=creditMode+SysDataUtil.getNameFromDataDict(DictCode.人行增信形式,single);
				if(i<creditModes.length){
					creditMode=creditMode+GlobalConstant.COMMA;
				}
			}
			result.setCreditMode(creditMode);
		}
		if (StringUtil.isNotEmpty(result.getIsConstracte())){
			result.setIsConstracte( SysDataUtil.getNameFromDataDict(DictCode.人行是否,result.getIsConstracte()));
		}
		if (StringUtil.isNotEmpty(result.getTransferType())){
			result.setTransferType( SysDataUtil.getNameFromDataDict(DictCode.人行是否,result.getTransferType()));
		}
		if (StringUtil.isNotEmpty(result.getIdentiFlag())){
			result.setIdentiFlag( SysDataUtil.getNameFromDataDict(DictCode.人行理财产品发起机构标识,result.getIdentiFlag()));
		}
		if(result.getOutDepCtry()!=null){
			List<String> outDepCtrys=StringUtil.splitToList(GlobalConstant.COMMA, result.getOutDepCtry());
			result.setOutDepCtry("");
			for(String outDepCtry : outDepCtrys){
				result.setOutDepCtry(result.getOutDepCtry()+","+outDepCtry+" "+SvrDataUtil.getNameFromDataDict(DictCode.所属国家或地区, outDepCtry));
			}
			result.setOutDepCtry(result.getOutDepCtry().substring(1));
		}
		//募集资金币种
		if (StringUtil.isNotEmpty(result.getRaiseCcy())){
			String raiseCcyStr= SysDataUtil.getNameFromDataDict(DictCode.币种,result.getRaiseCcy());
			result.setRaiseCcy(raiseCcyStr);
		}
		//兑付本金币种
		if (StringUtil.isNotEmpty(result.getCashPrinCcy())){
			String cashPrinCcy= SysDataUtil.getNameFromDataDict(DictCode.币种,result.getCashPrinCcy());
			result.setCashPrinCcy(cashPrinCcy);
		}
		//兑付收益币种
		if (StringUtil.isNotEmpty(result.getCashProfCcy())){
			String cashProfCcy= SysDataUtil.getNameFromDataDict(DictCode.币种,result.getCashProfCcy());
			result.setCashProfCcy(cashProfCcy);
		}
		// 境内托管机构名称
		if(result.getInDepName()!=null){
			result.setInDepName(result.getInDepName().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.境内托管机构名称, result.getInDepName()));
		}
		return result;
	}

	/**
	 * @Description 导出预览为零的数据显示空还是显示零
	 * @param detail
	 * @version 1.7 2021年8月2日
	 * @param isShowZero
	 */
	private void dealProdData(PbcReportProdDatDiffDto detail, String isShowZero) {
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
	public void dealAreaDateListForExport(PbcReportProdDatDiffDto detail) {
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
	public void dealAreaDateListForDiff(PbcReportProdDatDiffDto detail) {
		// 人民币
		if(!detail.getCny().equals(detail.getCnyPrd())){
			detail.setCnyDiff(1);
		}else{
			detail.setCnyDiff(0);
		}
		//外币
//		if(detail.getForeignCcy() != null){
//			detail.setForeignCcy(detail.getForeignCcy().divide(tenthousand));
//		}
		//美元折人民币
		if(!detail.getUsdToCny().equals(detail.getUsdToCnyPrd())){
			detail.setUsdToCnyDiff(1);
		}else{
			detail.setUsdToCnyDiff(0);
		}
		//欧元折人民币
		if(!detail.getEurToCny().equals(detail.getUsdToCnyPrd())){
			detail.setEurToCnyDiff(1);
		}else{
			detail.setEurToCnyDiff(0);
		}
		//英镑折人民币
		if(!detail.getGbpToCny().equals(detail.getGbpToCnyPrd())){
			detail.setGbpToCnyDiff(1);
		}
		//日元折人民币
		if(!detail.getJpyToCny().equals(detail.getJpyToCnyPrd())){
			detail.setJpyToCnyDiff(1);
		}
		//其他币种(外币)折人民币
		if(!detail.getOthToCny().equals(detail.getOthToCnyPrd())){
			detail.setOthToCnyDiff(1);
		}
		//合计
		if(!detail.getTotal().equals(detail.getTotalPrd())){
			detail.setTotalDiff(1);
		}
	}

    



	
	

    
}
