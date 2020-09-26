package com.joyintech.tams.svreport.pbc.prod.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.pbc.common.annotation.TemplateName;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportProperycode;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportType;
import com.joyintech.tams.svreport.pbc.common.constants.PbcTemplateName;
import com.joyintech.tams.svreport.pbc.prod.dao.PbcMonthPrdPostionMapper;
import com.joyintech.tams.svreport.pbc.prod.dto.PbcMonthPrdPostionDto;
import com.joyintech.tams.svreport.pbc.prod.dto.PbcReportProdDetailDto;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;


/**
 * 功能:表3-2  新发产品分期限募集金额
 * @since 1.6.0
 * @version 1.6.0
 * @作者：lingxq
 * @创建时间：2019年3月4日
 * @修改记录：
 */
@Service
@TemplateName(PbcTemplateName.PBC_REPORT_3_2)
public class PbcReportProd003Sub002Service extends PbcReportProdTotalService  {
	@Autowired
	private PbcMonthPrdPostionMapper pbcMonthPrdPostionMapper;
	
	public void generateReport(Date generateDate,Date reportStartDate,Date reportEndDate,List<PbcMonthPrdPostionDto> posList,boolean isEdit){
		//主表信息
		PbcMonthReport pbcMonthReport = getReportModel(generateDate, 
													   reportStartDate, 
													   reportEndDate,
													   PbcReportType.PBC_REPORT_3_2,isEdit);
		
		//明细
		Map<String,PbcReportProdDetailDto> resultTMP = getReportDetailList(pbcMonthReport.getReportuuid(),PbcReportType.PBC_REPORT_3_2,DictCodeListExt1.维护数据状态_有效.getValue());

		
		//update by leipeng 20200319  汇总数据提取全量模板
		Map<String,PbcReportProdDetailDto> result = new HashMap<>();
		result.putAll(resultTMP);
		Map<String,Map<String,PbcReportProdDetailDto>> deptMap = new HashMap();
		
		 if(posList==null||posList.isEmpty()){
			 //没有头寸，不需要插入
			 insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,null,result,PbcReportType.PBC_REPORT_3_2,false);
			 return;
		 }
//		 PbcReportProdDetailDto linef00000 = result.get(PbcReportProperycode.AMT_F00000_XFCPMJJEZJ); //   新发产品募集金额总计
//		 PbcReportProdDetailDto linef10000 = result.get(PbcReportProperycode.AMT_F10000_KFSJZXZJ); //   开放式净值型总计
////		 PbcReportProdDetailDto linef11000 = result.get(PbcReportProperycode.AMT_F11000_1GYHYX); //     1个月（含）以下
////		 PbcReportProdDetailDto linef12000 = result.get(PbcReportProperycode.AMT_F12000_13GYH); //       1-3个月（含）
////		 PbcReportProdDetailDto linef13000 = result.get(PbcReportProperycode.AMT_F13000_13GYH); //       1-3个月（含）
////		 PbcReportProdDetailDto linef14000 = result.get(PbcReportProperycode.AMT_F14000_612GYH); //       6-12个月（含）
////		 PbcReportProdDetailDto linef15000 = result.get(PbcReportProperycode.AMT_F15000_12NH); //       1-2年（含）
////		 PbcReportProdDetailDto linef16000 = result.get(PbcReportProperycode.AMT_F16000_23NH); //       2-3年（含）
////		 PbcReportProdDetailDto linef17000 = result.get(PbcReportProperycode.AMT_F17000_3NYS); //       3年以上
//		 PbcReportProdDetailDto linef18000 = result.get(PbcReportProperycode.AMT_F18000_WGDQX); //       无固定期限
//		 PbcReportProdDetailDto linef20000 = result.get(PbcReportProperycode.AMT_F20000_KFSFJZXZJ); //   开放式非净值型总计
////		 PbcReportProdDetailDto linef21000 = result.get(PbcReportProperycode.AMT_F21000_1GYHYX); //     1个月（含）以下
////		 PbcReportProdDetailDto linef22000 = result.get(PbcReportProperycode.AMT_F22000_13GYH); //       1-3个月（含）
////		 PbcReportProdDetailDto linef23000 = result.get(PbcReportProperycode.AMT_F23000_36GYH); //       3-6个月（含）
////		 PbcReportProdDetailDto linef24000 = result.get(PbcReportProperycode.AMT_F24000_612GYH); //       6-12个月（含）
////		 PbcReportProdDetailDto linef25000 = result.get(PbcReportProperycode.AMT_F25000_12NH); //       1-2年（含）
////		 PbcReportProdDetailDto linef26000 = result.get(PbcReportProperycode.AMT_F26000_23NH); //       2-3年（含）
////		 PbcReportProdDetailDto linef27000 = result.get(PbcReportProperycode.AMT_F27000_3NYS); //       3年以上
//		 PbcReportProdDetailDto linef28000 = result.get(PbcReportProperycode.AMT_F28000_WGDQX); //       无固定期限
//		 PbcReportProdDetailDto linef30000 = result.get(PbcReportProperycode.AMT_F30000_FBSJZXZJ); //   封闭式净值型总计
//		 PbcReportProdDetailDto linef31000 = result.get(PbcReportProperycode.AMT_F31000_1GYHYX); //     1个月（含）以下
//		 PbcReportProdDetailDto linef32000 = result.get(PbcReportProperycode.AMT_F32000_13GYH); //       1-3个月（含）
//		 PbcReportProdDetailDto linef33000 = result.get(PbcReportProperycode.AMT_F33000_36GYH); //       3-6个月（含）
//		 PbcReportProdDetailDto linef34000 = result.get(PbcReportProperycode.AMT_F34000_612GYH); //       6-12个月（含）
//		 PbcReportProdDetailDto linef35000 = result.get(PbcReportProperycode.AMT_F35000_12NH); //       1-2年（含）
//		 PbcReportProdDetailDto linef36000 = result.get(PbcReportProperycode.AMT_F36000_23NH); //       2-3年（含）
//		 PbcReportProdDetailDto linef37000 = result.get(PbcReportProperycode.AMT_F37000_3NYS); //       3年以上
////		 PbcReportProdDetailDto linef38000 = result.get(PbcReportProperycode.AMT_F38000_WGDQX); //       无固定期限
//		 PbcReportProdDetailDto linef40000 = result.get(PbcReportProperycode.AMT_F40000_FBSFJZXZJ); //   封闭式非净值型总计
//		 PbcReportProdDetailDto linef41000 = result.get(PbcReportProperycode.AMT_F41000_1GYHYX); //     1个月（含）以下
//		 PbcReportProdDetailDto linef42000 = result.get(PbcReportProperycode.AMT_F42000_13GYH); //       1-3个月（含）
//		 PbcReportProdDetailDto linef43000 = result.get(PbcReportProperycode.AMT_F43000_36GYH); //    3-6个月（含）
//		 PbcReportProdDetailDto linef44000 = result.get(PbcReportProperycode.AMT_F44000_612GYH); //    6-12个月（含）
//		 PbcReportProdDetailDto linef45000 = result.get(PbcReportProperycode.AMT_F45000_12NH); //    1-2年（含）
//		 PbcReportProdDetailDto linef46000 = result.get(PbcReportProperycode.AMT_F46000_23NH); //    2-3年（含）
//		 PbcReportProdDetailDto linef47000 = result.get(PbcReportProperycode.AMT_F47000_3NYS); //    3年以上
//		 PbcReportProdDetailDto linef48000 = result.get(PbcReportProperycode.AMT_F48000_WGDQX); //     无固定期限
		 Map<String, BigDecimal> rateMap = new HashMap<>();

		 String source = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);
		 for(PbcMonthPrdPostionDto pos :posList){
			 if(!DictCodeListExt2.存续状态_未起息.getValue().equals(pos.getDurationflag())
					 && pos.getVdate().compareTo(DateUtil.parse(DateUtil.format(reportStartDate))) >= 0
					 && pos.getVdate().compareTo(DateUtil.parse(DateUtil.format(reportEndDate))) <= 0){
				 
				// add by leipeng 20200319 获取所有部门对应信息,并列出单独的表结构
				 if(null!=pos.getInstitutionCode()&&null==deptMap.get(pos.getInstitutionCode())){
					 Map<String,PbcReportProdDetailDto> singDeptMap = getReportDetailList(pbcMonthReport.getReportuuid(),PbcReportType.PBC_REPORT_3_2,DictCodeListExt1.维护数据状态_有效.getValue());

					 deptMap.put(pos.getInstitutionCode(), singDeptMap);
				 }
				 
				 
				 //新发产品募集金额总计
//				 setDetailValue(linef00000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 
				 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F00000_XFCPMJJEZJ,
						 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
				 
				 /**
				  * 按期限分类：
				    * 0：属于"无固定期限"										
			        * 0<合同期限<=30:属于"1个月（含）以下"										
					* 30<合同期限<=90:属于"1-3个月（含）"										
					* 90<合同期限<=180:属于"3-6个月（含）"										
					* 180<合同期限<=360:属于"6-12个月（含"										
					* 360<合同期限<=720:属于"1-2年（含）"										
					* 720<合同期限<=1080:属于"2-3年（含）"										
					* 1080<合同期限:属于"3年以上"										
				  */
				 if(DictCodeListExt2.产品运作模式_开放式净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue(linef10000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F10000_KFSJZXZJ,
							 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
					 if(null != pos.getTerm() && pos.getTerm().compareTo(BigDecimal.ZERO) == 0){
//					    setDetailValue(linef18000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
					    setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F18000_WGDQX,
							 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
					 }
				 }
				 
				 if(DictCodeListExt2.产品运作模式_开放式非净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue(linef20000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F20000_KFSFJZXZJ,
							 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
					 if(null != pos.getTerm() && pos.getTerm().compareTo(BigDecimal.ZERO) == 0){
//						setDetailValue(linef28000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
						setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F28000_WGDQX,
							 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
					 }
				 }
				 
				 if(DictCodeListExt2.产品运作模式_封闭式净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue(linef30000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F30000_FBSJZXZJ,
							 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
					 if(null != pos.getTerm()){
						 if(pos.getTerm().compareTo(BigDecimal.ZERO) == 0 ){
	//						 setDetailValue(linef38000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(BigDecimal.ZERO) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("30")) <= 0){
//							 setDetailValue(linef31000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F31000_1GYHYX,
									 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("30")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("90")) <= 0){
//							 setDetailValue(linef32000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F32000_13GYH,
									 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("90")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("180")) <= 0){
//							 setDetailValue(linef33000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F33000_36GYH,
									 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("180")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("360")) <= 0){
//							 setDetailValue(linef34000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F34000_612GYH,
									 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("360")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("720")) <= 0){
//							 setDetailValue(linef35000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F35000_12NH,
									 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("720")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("1080")) <= 0){
//							 setDetailValue(linef36000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F36000_23NH,
									 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else{
//							 setDetailValue(linef37000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F37000_3NYS,
									 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }
					 }
				 }
				 
				 if(DictCodeListExt2.产品运作模式_封闭式非净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue(linef40000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F40000_FBSFJZXZJ,
							 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
					 if(null != pos.getTerm()){
						 if(pos.getTerm().compareTo(BigDecimal.ZERO) == 0 ){
	//						 setDetailValue(linef48000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(BigDecimal.ZERO) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("30")) <= 0){
//							 setDetailValue(linef41000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F41000_1GYHYX,
									 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("30")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("90")) <= 0){
//							 setDetailValue(linef42000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F42000_13GYH,
									 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("90")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("180")) <= 0){
//							 setDetailValue(linef43000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F43000_36GYH,
									 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("180")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("360")) <= 0){
//							 setDetailValue(linef44000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F44000_612GYH,
									 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("360")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("720")) <= 0){
//							 setDetailValue(linef45000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F45000_12NH,
									 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("720")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("1080")) <= 0){
//							 setDetailValue(linef46000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F46000_23NH,
									 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else{
//							 setDetailValue(linef47000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_F47000_3NYS,
									 	pos.getCcy(),pos.getDirtyamtsum()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }
					 }
				 }
		    }
		 }
		 
		// 数据先删后插
	    insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,null,result,PbcReportType.PBC_REPORT_3_2,true);
	    
	    //add by leipeng 20200319  各法人生成数据
  		for (Map.Entry<String,Map<String,PbcReportProdDetailDto>> map : deptMap.entrySet()) {
  			// 各法人数据先删后插
  			insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,map.getKey(),map.getValue(),PbcReportType.PBC_REPORT_3_2,true);
  		}
	
	}
	
	@Override
	public void setDetailValue(PbcReportProdDetailDto dto,String ccy,BigDecimal value,Map<String, BigDecimal> rateMap,Date generateDate,String source){
        BigDecimal cny = ChbCcyConvertUtil.exchangeMoney(
        		ccy,DictCodeListExt1.币种_中债_人民币.getValue(),generateDate,value,rateMap,source);
        if(DictCodeListExt1.币种_中债_人民币.getValue().equals(ccy)){//人民币
            dto.setCny(dto.getCny().add(cny));
        }else{ //外币
            dto.setOthToCny((dto.getOthToCny().add(cny)));
        }
        //合计
        dto.setTotal(dto.getTotal().add(cny));
    }
	
	
	
	/**
     * 多法人改造
     * @param dto
     * @param deptDto
     * @param code
     * @param ccy
     * @param value
     * @param rateMap
     * @param generateDate
     * @param source
     */
    public void setDetailValueMap(Map<String,PbcReportProdDetailDto> dto,Map<String,PbcReportProdDetailDto> deptDto,String code,
    				String ccy,BigDecimal value,Map<String, BigDecimal> rateMap,Date generateDate,String source){
    	//汇总数据计算
    	setDetailValue(dto.get(code),ccy,/*pos.getRedamt()*/value,rateMap,generateDate,source);
    	//部门数据计算
    	if(null!=deptDto&&null!=deptDto.get(code)){
    		setDetailValue(deptDto.get(code),ccy,/*pos.getRedamt()*/value,rateMap,generateDate,source);
    	}
    }
}
