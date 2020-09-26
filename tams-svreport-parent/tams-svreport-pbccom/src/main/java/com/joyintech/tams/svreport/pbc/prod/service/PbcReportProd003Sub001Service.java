package com.joyintech.tams.svreport.pbc.prod.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
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
 * 功能:表3-1  存续产品分期限募集余额
 * @since 1.6.0
 * @version 1.6.0
 * @作者：lingxq
 * @创建时间：2019年3月4日
 * @修改记录：
 */
@Service
@TemplateName(PbcTemplateName.PBC_REPORT_3_1)
public class PbcReportProd003Sub001Service extends PbcReportProdTotalService  {
	@Autowired
	private PbcMonthPrdPostionMapper pbcMonthPrdPostionMapper;
	
	/**
	 * 生成报表数据
	 * @param generateDate
	 * @param reportStartDate
	 * @param reportEndDate
	 */
	public void generateReport(Date generateDate,Date reportStartDate,Date reportEndDate,List<PbcMonthPrdPostionDto> posList,boolean isEdit){
		//主表信息
		PbcMonthReport pbcMonthReport = getReportModel(generateDate, 
													   reportStartDate, 
													   reportEndDate,
													   PbcReportType.PBC_REPORT_3_1,isEdit);
		
		//明细
		Map<String,PbcReportProdDetailDto> resultTMP = getReportDetailList(pbcMonthReport.getReportuuid(),PbcReportType.PBC_REPORT_3_1,DictCodeListExt1.维护数据状态_有效.getValue());

		
		//update by leipeng 20200319  汇总数据提取全量模板
		Map<String,PbcReportProdDetailDto> result = new HashMap<>();
		result.putAll(resultTMP);
		Map<String,Map<String,PbcReportProdDetailDto>> deptMap = new HashMap();
		
		 if(posList==null||posList.isEmpty()){
			 //没有头寸，不需要插入
			 insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,null,result,PbcReportType.PBC_REPORT_3_1,false);
			 return;
		 }
//		 PbcReportProdDetailDto linee00000 = result.get(PbcReportProperycode.AMT_E00000_CXCPMJYEZJ); //   存续产品募集余额总计
//		 PbcReportProdDetailDto linee10000 = result.get(PbcReportProperycode.AMT_E10000_KFSJZXZJ); //   开放式净值型总计
////		 PbcReportProdDetailDto linee11000 = result.get(PbcReportProperycode.AMT_E11000_1GYHYX); //     1个月（含）以下
////		 PbcReportProdDetailDto linee12000 = result.get(PbcReportProperycode.AMT_E12000_13GYH); //       1-3个月（含）
////		 PbcReportProdDetailDto linee13000 = result.get(PbcReportProperycode.AMT_E13000_36GYH); //       3-6个月（含）
////		 PbcReportProdDetailDto linee14000 = result.get(PbcReportProperycode.AMT_E14000_612GYH); //       6-12个月（含）
////		 PbcReportProdDetailDto linee15000 = result.get(PbcReportProperycode.AMT_E15000_12NH); //       1-2年（含）
////		 PbcReportProdDetailDto linee16000 = result.get(PbcReportProperycode.AMT_E16000_23NH); //       2-3年（含）
////		 PbcReportProdDetailDto linee17000 = result.get(PbcReportProperycode.AMT_E17000_3NYS); //       3年以上
//		 PbcReportProdDetailDto linee18000 = result.get(PbcReportProperycode.AMT_E18000_WGDQX); //       无固定期限
//		 PbcReportProdDetailDto linee20000 = result.get(PbcReportProperycode.AMT_E20000_KFSFJZXZJ); //   开放式非净值型总计
////		 PbcReportProdDetailDto linee21000 = result.get(PbcReportProperycode.AMT_E21000_1GYHYX); //     1个月（含）以下
////		 PbcReportProdDetailDto linee22000 = result.get(PbcReportProperycode.AMT_E22000_13GYH); //       1-3个月（含）
////		 PbcReportProdDetailDto linee23000 = result.get(PbcReportProperycode.AMT_E23000_36GYH); //       3-6个月（含）
////		 PbcReportProdDetailDto linee24000 = result.get(PbcReportProperycode.AMT_E24000_612GYH); //       6-12个月（含）
////		 PbcReportProdDetailDto linee25000 = result.get(PbcReportProperycode.AMT_E25000_12NH); //       1-2年（含）
////		 PbcReportProdDetailDto linee26000 = result.get(PbcReportProperycode.AMT_E26000_23NH); //       2-3年（含）
////		 PbcReportProdDetailDto linee27000 = result.get(PbcReportProperycode.AMT_E27000_3NYS); //       3年以上
//		 PbcReportProdDetailDto linee28000 = result.get(PbcReportProperycode.AMT_E28000_WGDQX); //       无固定期限
//		 PbcReportProdDetailDto linee30000 = result.get(PbcReportProperycode.AMT_E30000_FBSJZXZJ); //   封闭式净值型总计
//		 PbcReportProdDetailDto linee31000 = result.get(PbcReportProperycode.AMT_E31000_1GYHYX); //     1个月（含）以下
//		 PbcReportProdDetailDto linee32000 = result.get(PbcReportProperycode.AMT_E32000_13GYH); //       1-3个月（含）
//		 PbcReportProdDetailDto linee33000 = result.get(PbcReportProperycode.AMT_E33000_36GYH); //       3-6个月（含）
//		 PbcReportProdDetailDto linee34000 = result.get(PbcReportProperycode.AMT_E34000_612GYH); //       6-12个月（含）
//		 PbcReportProdDetailDto linee35000 = result.get(PbcReportProperycode.AMT_E35000_12NH); //       1-2年（含）
//		 PbcReportProdDetailDto linee36000 = result.get(PbcReportProperycode.AMT_E36000_23NH); //       2-3年（含）
//		 PbcReportProdDetailDto linee37000 = result.get(PbcReportProperycode.AMT_E37000_3NYS); //       3年以上
////		 PbcReportProdDetailDto linee38000 = result.get(PbcReportProperycode.AMT_E38000_WGDQX); //       无固定期限
//		 PbcReportProdDetailDto linee40000 = result.get(PbcReportProperycode.AMT_E40000_FBSFJZXZJ); //   封闭式非净值型总计
//		 PbcReportProdDetailDto linee41000 = result.get(PbcReportProperycode.AMT_E41000_1GYHYX); //     1个月（含）以下
//		 PbcReportProdDetailDto linee42000 = result.get(PbcReportProperycode.AMT_E42000_13GYH); //       1-3个月（含）
//		 PbcReportProdDetailDto linee43000 = result.get(PbcReportProperycode.AMT_E43000_36GYH); //       3-6个月（含）
//		 PbcReportProdDetailDto linee44000 = result.get(PbcReportProperycode.AMT_E44000_612GYH); //       6-12个月（含）
//		 PbcReportProdDetailDto linee45000 = result.get(PbcReportProperycode.AMT_E45000_12NH); //       1-2年（含）
//		 PbcReportProdDetailDto linee46000 = result.get(PbcReportProperycode.AMT_E46000_23NH); //       2-3年（含）
//		 PbcReportProdDetailDto linee47000 = result.get(PbcReportProperycode.AMT_E47000_3NYS); //       3年以上
////		 PbcReportProdDetailDto linee48000 = result.get(PbcReportProperycode.AMT_E48000_WGDQX); //       无固定期限
		 Map<String, BigDecimal> rateMap = new HashMap<>();
		 String source = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);
		 for(PbcMonthPrdPostionDto pos :posList){
			 if(DictCodeListExt2.存续状态_存续.getValue().equals(pos.getDurationflag())){
				 
				// add by leipeng 20200319 获取所有部门对应信息,并列出单独的表结构
				 if(null!=pos.getInstitutionCode()&&null==deptMap.get(pos.getInstitutionCode())){
					 Map<String,PbcReportProdDetailDto> singDeptMap = getReportDetailList(pbcMonthReport.getReportuuid(),PbcReportType.PBC_REPORT_3_1,DictCodeListExt1.维护数据状态_有效.getValue());

					 deptMap.put(pos.getInstitutionCode(), singDeptMap);
				 }
				 
				 //存续产品募集余额总计
//				 setDetailValue(linee00000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E00000_CXCPMJYEZJ,
						 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
				 
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
//					 setDetailValue(linee10000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E10000_KFSJZXZJ,
							 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
					 
					 if(null != pos.getTerm() && pos.getTerm().compareTo(BigDecimal.ZERO) == 0){
//					    setDetailValue(linee18000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
					    setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E18000_WGDQX,
							 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
					 }
				 }
				 
				 if(DictCodeListExt2.产品运作模式_开放式非净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue(linee20000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E20000_KFSFJZXZJ,
							 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
					 if(null != pos.getTerm() && pos.getTerm().compareTo(BigDecimal.ZERO) == 0){
//						setDetailValue(linee28000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
						setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E28000_WGDQX,
							 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
					 }
				 }
				 
				 if(DictCodeListExt2.产品运作模式_封闭式净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue(linee30000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E30000_FBSJZXZJ,
							 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
					 if(null != pos.getTerm()){
						 if(pos.getTerm().compareTo(BigDecimal.ZERO) == 0 ){
	//						 setDetailValue(linee38000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(BigDecimal.ZERO) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("30")) <= 0){
//							 setDetailValue(linee31000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E31000_1GYHYX,
									 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("30")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("90")) <= 0){
//							 setDetailValue(linee32000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E32000_13GYH,
									 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("90")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("180")) <= 0){
//							 setDetailValue(linee33000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E33000_36GYH,
									 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("180")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("360")) <= 0){
//							 setDetailValue(linee34000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E34000_612GYH,
									 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("360")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("720")) <= 0){
//							 setDetailValue(linee35000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E35000_12NH,
									 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("720")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("1080")) <= 0){
//							 setDetailValue(linee36000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E36000_23NH,
									 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else{
//							 setDetailValue(linee37000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E37000_3NYS,
									 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }
					 }
				 }
				 
				 if(DictCodeListExt2.产品运作模式_封闭式非净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue(linee40000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E40000_FBSFJZXZJ,
							 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
					 if(null != pos.getTerm()){
						 if(pos.getTerm().compareTo(BigDecimal.ZERO) == 0 ){
	//						 setDetailValue(linee48000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(BigDecimal.ZERO) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("30")) <= 0){
//							 setDetailValue(linee41000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E41000_1GYHYX,
									 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("30")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("90")) <= 0){
//							 setDetailValue(linee42000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E42000_13GYH,
									 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("90")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("180")) <= 0){
//							 setDetailValue(linee43000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E43000_36GYH,
									 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("180")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("360")) <= 0){
//							 setDetailValue(linee44000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E44000_612GYH,
									 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("360")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("720")) <= 0){
//							 setDetailValue(linee45000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E45000_12NH,
									 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else if(pos.getTerm().compareTo(new BigDecimal("720")) > 0 
								 && pos.getTerm().compareTo(new BigDecimal("1080")) <= 0){
//							 setDetailValue(linee46000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E46000_23NH,
									 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }else{
//							 setDetailValue(linee47000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
							 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_E47000_3NYS,
									 	pos.getCcy(),pos.getEndamtsumOth()/*pos.getRedCost()*/,rateMap,generateDate,source);
						 }
					 }
				 }
		   }
		 }
		 
		// 数据先删后插
		insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,null,result,PbcReportType.PBC_REPORT_3_1,true);
		
		//add by leipeng 20200319  各法人生成数据
		for (Map.Entry<String,Map<String,PbcReportProdDetailDto>> map : deptMap.entrySet()) {
			// 各法人数据先删后插
			insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,map.getKey(),map.getValue(),PbcReportType.PBC_REPORT_3_1,true);
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
