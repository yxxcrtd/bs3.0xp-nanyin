package com.joyintech.tams.svreport.pbc.prod.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.ChbTypeMappingEx;
import com.joyintech.tams.common.svreport.base.service.ChbTypeMappingService;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.pbc.common.annotation.TemplateName;
import com.joyintech.tams.svreport.pbc.common.constants.PbcMonthReportConstants;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportProperycode;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportType;
import com.joyintech.tams.svreport.pbc.common.constants.PbcTemplateName;
import com.joyintech.tams.svreport.pbc.prod.dao.PbcMonthPrdPostionMapper;
import com.joyintech.tams.svreport.pbc.prod.dto.PbcMonthPrdPostionDto;
import com.joyintech.tams.svreport.pbc.prod.dto.PbcReportProdDetailDto;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;


/**
 * 功能:表1-4 境内募集余额分地区
 * @since 1.6.0
 * @version 1.6.0
 * @作者：lingxq
 * @创建时间：2019年3月4日
 * @修改记录：
 */
@Service
@TemplateName(PbcTemplateName.PBC_REPORT_1_4)
public class PbcReportProd001Sub004Service extends PbcReportProdTotalService  {
	@Autowired
	private PbcMonthPrdPostionMapper pbcMonthPrdPostionMapper;
	@Autowired
	private ChbTypeMappingService chbTypeMappingService;
	

	public void generateReport(Date generateDate,Date reportStartDate,Date reportEndDate,List<PbcMonthPrdPostionDto> posList,boolean isEdit){
		//主表信息
		PbcMonthReport pbcMonthReport = getReportModel(generateDate,reportStartDate,reportEndDate,PbcReportType.PBC_REPORT_1_4,isEdit);
		String reportuuid = pbcMonthReport.getReportuuid();
		
		//明细生成基础模板
		Map<String,PbcReportProdDetailDto> resultTMP = getReportDetailList(reportuuid,PbcReportType.PBC_REPORT_1_4,DictCodeListExt1.维护数据状态_有效.getValue());

		//update by leipeng 20200319  汇总数据提取全量模板
		Map<String,PbcReportProdDetailDto> result = new HashMap<>();
		result.putAll(resultTMP);
		 // 
		Map<String,Map<String,PbcReportProdDetailDto>> deptMap = new HashMap();
		
		
		 if(posList==null||posList.isEmpty()){
			 //没有头寸，不需要插入
			 insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,null,result,PbcReportType.PBC_REPORT_1_4,false);
			 return;
		 }
//		 PbcReportProdDetailDto line400000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_400000_JNMJYEZJ); //   境内募集余额总计
//		 PbcReportProdDetailDto line401000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_401000_BJ); //   北京
//		 PbcReportProdDetailDto line402000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_402000_TJ); //   天津
//		 PbcReportProdDetailDto line403000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_403000_HB); //   河北
//		 PbcReportProdDetailDto line404000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_404000_SX); //   山西
//		 PbcReportProdDetailDto line405000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_405000_NMG); //   内蒙古
//		 PbcReportProdDetailDto line406000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_406000_LN); //   辽宁
//		 PbcReportProdDetailDto line406001 = result.get(PbcReportProperycode.MJYEFDQ_AMT_406001_QZDL); //   其中：大连
//		 PbcReportProdDetailDto line407000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_407000_JL); //   吉林
//		 PbcReportProdDetailDto line408000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_408000_HLJ); //   黑龙江
//		 PbcReportProdDetailDto line409000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_409000_SH); //   上海
//		 PbcReportProdDetailDto line410000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_410000_JS); //   江苏
//		 PbcReportProdDetailDto line411000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_411000_ZJ); //   浙江
//		 PbcReportProdDetailDto line411001 = result.get(PbcReportProperycode.MJYEFDQ_AMT_411001_QZNB); //   其中：宁波
//		 PbcReportProdDetailDto line412000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_412000_AH); //   安徽
//		 PbcReportProdDetailDto line413000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_413000_FJ); //   福建
//		 PbcReportProdDetailDto line413001 = result.get(PbcReportProperycode.MJYEFDQ_AMT_413001_QZSM); //   其中：厦门
//		 PbcReportProdDetailDto line414000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_414000_JX); //   江西
//		 PbcReportProdDetailDto line415000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_415000_SD); //   山东
//		 PbcReportProdDetailDto line415001 = result.get(PbcReportProperycode.MJYEFDQ_AMT_415001_QZQD); //   其中：青岛
//		 PbcReportProdDetailDto line416000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_416000_HN); //   河南
//		 PbcReportProdDetailDto line417000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_417000_HB); //   湖北
//		 PbcReportProdDetailDto line418000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_418000_HN); //   湖南
//		 PbcReportProdDetailDto line419000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_419000_GD); //   广东
//		 PbcReportProdDetailDto line419001 = result.get(PbcReportProperycode.MJYEFDQ_AMT_419001_QZSZ); //   其中：深圳
//		 PbcReportProdDetailDto line420000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_420000_GX); //   广西
//		 PbcReportProdDetailDto line421000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_421000_HN); //   海南
//		 PbcReportProdDetailDto line422000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_422000_ZQ); //   重庆
//		 PbcReportProdDetailDto line423000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_423000_SC); //   四川
//		 PbcReportProdDetailDto line424000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_424000_GZ); //   贵州
//		 PbcReportProdDetailDto line425000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_425000_YN); //   云南
//		 PbcReportProdDetailDto line426000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_426000_XZ); //   西藏
//		 PbcReportProdDetailDto line427000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_427000_SX); //   陕西
//		 PbcReportProdDetailDto line428000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_428000_GS); //   甘肃
//		 PbcReportProdDetailDto line429000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_429000_QH); //   青海
//		 PbcReportProdDetailDto line430000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_430000_NX); //   宁夏
//		 PbcReportProdDetailDto line431000 = result.get(PbcReportProperycode.MJYEFDQ_AMT_431000_XJ); //   新疆
		 
		 Map<String, BigDecimal> rateMap = new HashMap<>();
		 String source = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);
		 
		 
		
		 
		
		 for(PbcMonthPrdPostionDto pos :posList){
			 if(pos.getCustomerType2()!=null&&pos.getCustomerType2().contains(DictCodeListExt2.客户一级类型_境外.getValue()))
				 continue;
			 ChbTypeMappingEx mapping = chbTypeMappingService.getMapping(PbcMonthReportConstants.MAPPINGCODE_BUYPLACE,
                     pos.getBuyPlace());
			 if(mapping==null)
				 continue;
			 String buyplace = mapping.getObjfield();
			 String buyplace2 = mapping.getObjfield2();
			 
			 
			 // add by leipeng 20200319 获取所有部门对应信息,并列出单独的表结构
			 if(null!=pos.getInstitutionCode()&&null==deptMap.get(pos.getInstitutionCode())){
				 Map<String,PbcReportProdDetailDto> singDeptMap = getReportDetailList(reportuuid,PbcReportType.PBC_REPORT_1_4,DictCodeListExt1.维护数据状态_有效.getValue());

				 deptMap.put(pos.getInstitutionCode(), singDeptMap);
			 }
			 
			 //合计
			 // 销售区域为计划单列市的不统计（省份内已经统计）：210200大连市、330200宁波市、350200厦门市、370200青岛市、440300深圳市
			 if(!"210200".equals(pos.getBuyPlace()) && !"330200".equals(pos.getBuyPlace()) &&!"350200".equals(pos.getBuyPlace()) &&
					 !"370200".equals(pos.getBuyPlace()) &&!"440300".equals(pos.getBuyPlace())) {
				 //汇总数据合计
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYEFDQ_AMT_400000_JNMJYEZJ,
						 								pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 //按购买地
			 if(DictCodeListExt2.销售区域_中债_北京市.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_401000_BJ,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_天津市.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_402000_TJ,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_河北省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_403000_HB,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_山西省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_404000_SX,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_内蒙古自治区.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_405000_NMG,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_辽宁省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_406000_LN,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_大连市.getValue().equals(buyplace2)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_406001_QZDL,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_吉林省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_407000_JL,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_黑龙江省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_408000_HLJ,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_上海市.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_409000_SH,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_江苏省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_410000_JS,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_浙江省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_411000_ZJ,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_宁波市.getValue().equals(buyplace2)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_411001_QZNB,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_安徽省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_412000_AH,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_福建省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_413000_FJ,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_厦门市.getValue().equals(buyplace2)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_413001_QZSM,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_江西省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_414000_JX,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_山东省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_415000_SD,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_青岛市.getValue().equals(buyplace2)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_415001_QZQD,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_河南省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_416000_HN,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_湖北省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_417000_HB,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_湖南省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_418000_HN,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_广东省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_419000_GD,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_深圳市.getValue().equals(buyplace2)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_419001_QZSZ,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_广西壮族自治区.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_420000_GX,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_海南省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_421000_HN,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_重庆市.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_422000_ZQ,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_四川省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_423000_SC,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_贵州省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_424000_GZ,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_云南省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_425000_YN,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_西藏自治区.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_426000_XZ,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_陕西省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_427000_SX,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_甘肃省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_428000_GS,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_青海省.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_429000_QH,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 else if(DictCodeListExt2.销售区域_中债_宁夏回族自治区.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_430000_NX,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.销售区域_中债_新疆维吾尔自治区.getValue().equals(buyplace)){
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),
						 PbcReportProperycode.MJYEFDQ_AMT_431000_XJ,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 
		 }
		// 汇总数据数据先删后插
		insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,null,result,PbcReportType.PBC_REPORT_1_4,true);	
			
		//add by leipeng 20200319  各法人生成数据
		for (Map.Entry<String,Map<String,PbcReportProdDetailDto>> map : deptMap.entrySet()) {
			// 各法人数据先删后插
			insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,map.getKey(),map.getValue(),PbcReportType.PBC_REPORT_1_4,true);
		}
	}

}
