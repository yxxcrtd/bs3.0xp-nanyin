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
import com.joyintech.tams.constant.DictCodeList;
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
 * 功能:表1-5提前及延期兑付
 * @since 1.6.0
 * @version 1.6.0
 * @作者：lingxq
 * @创建时间：2019年3月4日
 * @修改记录：
 */
@Service
@TemplateName(PbcTemplateName.PBC_REPORT_1_5)
public class PbcReportProd001Sub005Service extends PbcReportProdTotalService  {
	@Autowired
	private PbcMonthPrdPostionMapper pbcMonthPrdPostionMapper;
	
	
	public void generateReport(Date generateDate,Date reportStartDate,Date reportEndDate,List<PbcMonthPrdPostionDto> posList,boolean isEdit){
		//主表信息
		PbcMonthReport pbcMonthReport = getReportModel(generateDate,reportStartDate,reportEndDate,PbcReportType.PBC_REPORT_1_5,isEdit);
		String reportuuid = pbcMonthReport.getReportuuid();
		
		//明细
		Map<String,PbcReportProdDetailDto> resultTMP = getReportDetailList(reportuuid,PbcReportType.PBC_REPORT_1_5,DictCodeListExt1.维护数据状态_有效.getValue());

		
		//update by leipeng 20200319  汇总数据提取全量模板
		Map<String,PbcReportProdDetailDto> result = new HashMap<>();
		result.putAll(resultTMP);
		Map<String,Map<String,PbcReportProdDetailDto>> deptMap = new HashMap();
		

		 List<PbcMonthPrdPostionDto> posList2 =
				 	pbcMonthPrdPostionMapper.selectByDateWithoutCustomer(DateUtil.format(generateDate));//用于统计只数
		 if(posList==null||posList.isEmpty()){
			 //没有头寸，不需要插入
			 insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,null,result,PbcReportType.PBC_REPORT_1_5,false);
			 return;
		 }
		 
//		 PbcReportProdDetailDto line500000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_500000_ZJ); //   产品募集余额总计
////		 PbcReportProdDetailDto line510000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_510000_KHLX); //   客户类型
//		 PbcReportProdDetailDto line511000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_511000_ZH); //   住户
//		 PbcReportProdDetailDto line512000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_512000_GYZF); //   广义政府
//		 PbcReportProdDetailDto line513000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_513000_FJRQY); //   非金融企业
//		 PbcReportProdDetailDto line514000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_514000_JRJGST); //   金融机构（实体）
//		 PbcReportProdDetailDto line515000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_515000_TDMDZT); //   特定目的载体
//		 PbcReportProdDetailDto line516000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_516000_JW); //   境外
////		 PbcReportProdDetailDto line520000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_520000_YXFS); //   运行方式
//		 PbcReportProdDetailDto line521000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_521000_KFSJZX); //   开放式净值型
//		 PbcReportProdDetailDto line522000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_522000_KFSFJZX); //   开放式非净值型
//		 PbcReportProdDetailDto line523000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_523000_FBSJZX); //   封闭式净值型
//		 PbcReportProdDetailDto line524000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_524000_FBSFJZX); //   封闭式非净值型
////		 PbcReportProdDetailDto line530000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_530000_MJFS); //   募集方式
//		 PbcReportProdDetailDto line531000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_531000_GKMJ); //   公开募集
//		 PbcReportProdDetailDto line532000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_532000_FGKMJ); //   非公开募集
////		 PbcReportProdDetailDto line540000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_540000_GLFS); //   管理方式
//		 PbcReportProdDetailDto line541000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_541000_JHGL); //   集合管理
//		 PbcReportProdDetailDto line542000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_542000_DDGL); //   单独管理
////		 PbcReportProdDetailDto line550000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_550000_STZZ); //   受托职责
//		 PbcReportProdDetailDto line551000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_551000_ZDGL); //   主动管理
//		 PbcReportProdDetailDto line552000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_552000_BDGL); //   被动管理
////		 PbcReportProdDetailDto line560000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_560000_CPLX); //   产品类型
//		 PbcReportProdDetailDto line561000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_561000_GDSYL); //   固定收益类
//		 PbcReportProdDetailDto line562000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_562000_QYL); //   权益类
//		 PbcReportProdDetailDto line563000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_563000_SPJJRYSPL); //   商品及金融衍生品类
//		 PbcReportProdDetailDto line564000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_564000_HHL); //   混合类
////		 PbcReportProdDetailDto line590000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_590000_F); //   附:
//		 PbcReportProdDetailDto line591000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_591000_QZFJCP); //   其中：分级产品
//		 PbcReportProdDetailDto line592000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_592000_QZSYQZRCP); //   其中：收益权转让产品
//		 PbcReportProdDetailDto line593000 = result.get(PbcReportProperycode.TQJYQDF_TQDF_AMT_593000_QZHBSCJJ); //   其中：货币市场基金
//		 
//		 PbcReportProdDetailDto line500000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_500000_ZJ); //   产品募集余额总计
////		 PbcReportProdDetailDto line510000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_510000_KHLX); //   客户类型
//		 PbcReportProdDetailDto line511000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_511000_ZH); //   住户
//		 PbcReportProdDetailDto line512000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_512000_GYZF); //   广义政府
//		 PbcReportProdDetailDto line513000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_513000_FJRQY); //   非金融企业
//		 PbcReportProdDetailDto line514000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_514000_JRJGST); //   金融机构（实体）
//		 PbcReportProdDetailDto line515000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_515000_TDMDZT); //   特定目的载体
//		 PbcReportProdDetailDto line516000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_516000_JW); //   境外
////		 PbcReportProdDetailDto line520000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_520000_YXFS); //   运行方式
//		 PbcReportProdDetailDto line521000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_521000_KFSJZX); //   开放式净值型
//		 PbcReportProdDetailDto line522000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_522000_KFSFJZX); //   开放式非净值型
//		 PbcReportProdDetailDto line523000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_523000_FBSJZX); //   封闭式净值型
//		 PbcReportProdDetailDto line524000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_524000_FBSFJZX); //   封闭式非净值型
////		 PbcReportProdDetailDto line530000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_530000_MJFS); //   募集方式
//		 PbcReportProdDetailDto line531000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_531000_GKMJ); //   公开募集
//		 PbcReportProdDetailDto line532000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_532000_FGKMJ); //   非公开募集
////		 PbcReportProdDetailDto line540000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_540000_GLFS); //   管理方式
//		 PbcReportProdDetailDto line541000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_541000_JHGL); //   集合管理
//		 PbcReportProdDetailDto line542000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_542000_DDGL); //   单独管理
////		 PbcReportProdDetailDto line550000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_550000_STZZ); //   受托职责
//		 PbcReportProdDetailDto line551000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_551000_ZDGL); //   主动管理
//		 PbcReportProdDetailDto line552000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_552000_BDGL); //   被动管理
////		 PbcReportProdDetailDto line560000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_560000_CPLX); //   产品类型
//		 PbcReportProdDetailDto line561000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_561000_GDSYL); //   固定收益类
//		 PbcReportProdDetailDto line562000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_562000_QYL); //   权益类
//		 PbcReportProdDetailDto line563000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_563000_SPJJRYSPL); //   商品及金融衍生品类
//		 PbcReportProdDetailDto line564000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_564000_HHL); //   混合类
////		 PbcReportProdDetailDto line590000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_590000_F); //   附:
//		 PbcReportProdDetailDto line591000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_591000_QZFJCP); //   其中：分级产品
//		 PbcReportProdDetailDto line592000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_592000_QZSYQZRCP); //   其中：收益权转让产品
//		 PbcReportProdDetailDto line593000Sub2 = result.get(PbcReportProperycode.TQJYQDF_YQDF_AMT_593000_QZHBSCJJ); //   其中：货币市场基金
		 
		 Map<String, BigDecimal> rateMap = new HashMap<>();
		 String source = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);
		 /*提前及延期兑付统计由兑付金额值换为当期到期成本值  gaojian 2019-12-20 begin*/
		 for(PbcMonthPrdPostionDto pos :posList){
			 
			 
			 // add by leipeng 20200319 获取所有部门对应信息,并列出单独的表结构
			 if(null!=pos.getInstitutionCode()&&null==deptMap.get(pos.getInstitutionCode())){
				 Map<String,PbcReportProdDetailDto> singDeptMap = getReportDetailList(reportuuid,PbcReportType.PBC_REPORT_1_5,DictCodeListExt1.维护数据状态_有效.getValue());

				 deptMap.put(pos.getInstitutionCode(), singDeptMap);
			 }
			 
			 
			// 销售区域为计划单列市的不统计（省份内已经统计）：210200大连市、330200宁波市、350200厦门市、370200青岛市、440300深圳市
			if("210200".equals(pos.getBuyPlace()) || "330200".equals(pos.getBuyPlace()) || "350200".equals(pos.getBuyPlace()) || 
					"370200".equals(pos.getBuyPlace()) || "440300".equals(pos.getBuyPlace())) {
				continue;
			}
			 if(DictCodeListExt2.兑付状态_中债_提前兑付.getValue().equals(pos.getPaystatus())){
				 //合计
//				 setDetailValue2(line500000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_500000_ZJ,
						 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 //按分类
				 if(DictCodeListExt2.客户二级类型_住户.getValue().equals(pos.getCustomerType2())){
//					 setDetailValue2(line511000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_511000_ZH,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.客户二级类型_广义政府.getValue().equals(pos.getCustomerType2())){
//					 setDetailValue2(line512000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_512000_GYZF,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.客户二级类型_非金融企业.getValue().equals(pos.getCustomerType2())){
//					 setDetailValue2(line513000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_513000_FJRQY,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.客户二级类型_银行业存款类金融机构.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_银行业非存款类金融机构.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_非银行业金融机构.getValue().equals(pos.getCustomerType2())){
//					 setDetailValue2(line514000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_514000_JRJGST,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.客户二级类型_银行非保本理财.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_信托公司资管产品.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_证券公司及其子公司资管产品.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_基金管理公司及其子公司专户.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_期货公司及其子公司资管产品.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_保险资管产品.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_金融资产投资公司资管产品.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_公募基金.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_私募机构私募基金.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_其他特定目的载体.getValue().equals(pos.getCustomerType2())){
//					 setDetailValue2(line515000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_515000_TDMDZT,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(pos.getCustomerType2()!=null&&pos.getCustomerType2().contains(DictCodeListExt2.客户一级类型_境外.getValue())){
//					 setDetailValue2(line516000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_516000_JW,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品运作模式_开放式净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue2(line521000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_521000_KFSJZX,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品运作模式_开放式非净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue2(line522000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_522000_KFSFJZX,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品运作模式_封闭式净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue2(line523000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_523000_FBSJZX,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品运作模式_封闭式非净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue2(line524000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_524000_FBSFJZX,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.募集方式_中债_公募.getValue().equals(pos.getRaisemode())){
//					 setDetailValue2(line531000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_531000_GKMJ,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.募集方式_中债_私募.getValue().equals(pos.getRaisemode())){
//					 setDetailValue2(line532000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_532000_FGKMJ,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.排期发行方式_集合.getValue().equals(pos.getManageType())){
//					 setDetailValue2(line541000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_541000_JHGL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.排期发行方式_单一.getValue().equals(pos.getManageType())){
//					 setDetailValue2(line542000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_542000_DDGL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品受托职责_中债_主动管理.getValue().equals(pos.getFiduciaryDuty())){
//					 setDetailValue2(line551000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_551000_ZDGL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品受托职责_中债_被动管理.getValue().equals(pos.getFiduciaryDuty())){
//					 setDetailValue2(line552000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_552000_BDGL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.投资性质_固定收益类.getValue().equals(pos.getIvstorientation())){
//					 setDetailValue2(line561000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_561000_GDSYL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.投资性质_权益类.getValue().equals(pos.getIvstorientation())){
//					 setDetailValue2(line562000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_562000_QYL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.投资性质_商品及金融衍生品类.getValue().equals(pos.getIvstorientation())){
//					 setDetailValue2(line563000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_563000_SPJJRYSPL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.投资性质_混合类.getValue().equals(pos.getIvstorientation())){
//					 setDetailValue2(line564000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_564000_HHL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.是否_中债_是.getValue().equals(pos.getIsConstracte())){
//					 setDetailValue2(line591000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_591000_QZFJCP,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品转让类型_中债_收益权转让.getValue().equals(pos.getTransferType())){
//					 setDetailValue2(line592000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_592000_QZSYQZRCP,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.是否_中债_是.getValue().equals(pos.getIsMarketFund())){
//					 setDetailValue2(line593000,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_593000_QZHBSCJJ,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
			 
			 }
			 if(DictCodeListExt2.兑付状态_中债_延期兑付.getValue().equals(pos.getPaystatus())){
				 //合计
//				 setDetailValue2(line500000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_500000_ZJ,
						 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 //按分类
				 if(DictCodeListExt2.客户二级类型_住户.getValue().equals(pos.getCustomerType2())){
//					 setDetailValue2(line511000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_511000_ZH,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.客户二级类型_广义政府.getValue().equals(pos.getCustomerType2())){
//					 setDetailValue2(line512000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_512000_GYZF,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.客户二级类型_非金融企业.getValue().equals(pos.getCustomerType2())){
//					 setDetailValue2(line513000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_513000_FJRQY,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.客户二级类型_银行业存款类金融机构.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_银行业非存款类金融机构.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_非银行业金融机构.getValue().equals(pos.getCustomerType2())){
//					 setDetailValue2(line514000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_514000_JRJGST,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.客户二级类型_银行非保本理财.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_信托公司资管产品.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_证券公司及其子公司资管产品.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_基金管理公司及其子公司专户.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_期货公司及其子公司资管产品.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_保险资管产品.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_金融资产投资公司资管产品.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_公募基金.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_私募机构私募基金.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_其他特定目的载体.getValue().equals(pos.getCustomerType2())){
//					 setDetailValue2(line515000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_515000_TDMDZT,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(pos.getCustomerType2()!=null&&pos.getCustomerType2().contains(DictCodeListExt2.客户一级类型_境外.getValue())){
//					 setDetailValue2(line516000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_516000_JW,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品运作模式_开放式净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue2(line521000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_521000_KFSJZX,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品运作模式_开放式非净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue2(line522000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_522000_KFSFJZX,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品运作模式_封闭式净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue2(line523000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_523000_FBSJZX,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品运作模式_封闭式非净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue2(line524000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_524000_FBSFJZX,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.募集方式_中债_公募.getValue().equals(pos.getRaisemode())){
//					 setDetailValue2(line531000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_531000_GKMJ,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.募集方式_中债_私募.getValue().equals(pos.getRaisemode())){
//					 setDetailValue2(line532000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_532000_FGKMJ,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.排期发行方式_集合.getValue().equals(pos.getManageType())){
//					 setDetailValue2(line541000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_541000_JHGL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.排期发行方式_单一.getValue().equals(pos.getManageType())){
//					 setDetailValue2(line542000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_542000_DDGL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品受托职责_中债_主动管理.getValue().equals(pos.getFiduciaryDuty())){
//					 setDetailValue2(line551000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_551000_ZDGL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品受托职责_中债_被动管理.getValue().equals(pos.getFiduciaryDuty())){
//					 setDetailValue2(line552000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_552000_BDGL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.投资性质_固定收益类.getValue().equals(pos.getIvstorientation())){
//					 setDetailValue2(line561000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_561000_GDSYL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.投资性质_权益类.getValue().equals(pos.getIvstorientation())){
//					 setDetailValue2(line562000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_562000_QYL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.投资性质_商品及金融衍生品类.getValue().equals(pos.getIvstorientation())){
//					 setDetailValue2(line563000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_563000_SPJJRYSPL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.投资性质_混合类.getValue().equals(pos.getIvstorientation())){
//					 setDetailValue2(line564000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_564000_HHL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.是否_中债_是.getValue().equals(pos.getIsConstracte())){
//					 setDetailValue2(line591000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_591000_QZFJCP,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品转让类型_中债_收益权转让.getValue().equals(pos.getTransferType())){
//					 setDetailValue2(line592000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_592000_QZSYQZRCP,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.是否_中债_是.getValue().equals(pos.getIsMarketFund())){
//					 setDetailValue2(line593000Sub2,pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_593000_QZHBSCJJ,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getRedCost(),rateMap,generateDate,source);
				 }
			 }
		 }
		 /*提前及延期兑付统计由兑付金额值换为当期到期成本值  gaojian 2019-12-20 end*/
		 //产品去重 算只数时
         Map<String,String> prodMap=new HashMap<String,String>();
		 for(PbcMonthPrdPostionDto pos :posList2){
			 // 销售区域为计划单列市的不统计（省份内已经统计）：210200大连市、330200宁波市、350200厦门市、370200青岛市、440300深圳市
			 if("210200".equals(pos.getBuyPlace()) || "330200".equals(pos.getBuyPlace()) || "350200".equals(pos.getBuyPlace()) || 
					 "370200".equals(pos.getBuyPlace()) || "440300".equals(pos.getBuyPlace())) {
				 continue;
			 }
		     if(prodMap.containsKey(pos.getProdid())) {
                 continue;
             }else {
                 prodMap.put(pos.getProdid(), pos.getProdid());  
             }
		     
		     
		  // add by leipeng 20200319 获取所有部门对应信息,并列出单独的表结构
//			 if(null!=pos.getInstitutionCode()&&null==deptMap.get(pos.getInstitutionCode())){
//				 Map<String,PbcReportProdDetailDto> singDeptMap = new HashMap<>();
//				 singDeptMap.putAll(resultTMP);
//				 deptMap.put(pos.getInstitutionCode(), singDeptMap);
//			 }
		     
		     
			 if(DictCodeListExt2.兑付状态_中债_提前兑付.getValue().equals(pos.getPaystatus())){
				 //合计
//				 setProdNum(line500000);
				 
				 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_500000_ZJ);
				 
				 //按分类
				 if(DictCodeListExt2.产品运作模式_开放式净值型.getValue().equals(pos.getOperateMode())){
//					 setProdNum(line521000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_521000_KFSJZX);
				 }
				 if(DictCodeListExt2.产品运作模式_开放式非净值型.getValue().equals(pos.getOperateMode())){
//					 setProdNum(line522000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_522000_KFSFJZX);
				 }
				 if(DictCodeListExt2.产品运作模式_封闭式净值型.getValue().equals(pos.getOperateMode())){
//					 setProdNum(line523000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_523000_FBSJZX);
				 }
				 if(DictCodeListExt2.产品运作模式_封闭式非净值型.getValue().equals(pos.getOperateMode())){
//					 setProdNum(line524000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_524000_FBSFJZX);
				 }
				 if(DictCodeListExt2.募集方式_中债_公募.getValue().equals(pos.getRaisemode())){
//					 setProdNum(line531000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_531000_GKMJ);
				 }
				 if(DictCodeListExt2.募集方式_中债_私募.getValue().equals(pos.getRaisemode())){
//					 setProdNum(line532000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_532000_FGKMJ);
				 }
				 if(DictCodeList.排期发行方式_集合.getValue().equals(pos.getManageType())){
//					 setProdNum(line541000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_541000_JHGL);
				 }
				 if(DictCodeList.排期发行方式_单一.getValue().equals(pos.getManageType())){
//					 setProdNum(line542000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_542000_DDGL);
				 }
				 if(DictCodeListExt2.产品受托职责_中债_主动管理.getValue().equals(pos.getFiduciaryDuty())){
//					 setProdNum(line551000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_551000_ZDGL);
				 }
				 if(DictCodeListExt2.产品受托职责_中债_被动管理.getValue().equals(pos.getFiduciaryDuty())){
//					 setProdNum(line552000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_552000_BDGL);
				 }
				 if(DictCodeList.投资性质_固定收益类.getValue().equals(pos.getIvstorientation())){
//					 setProdNum(line561000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_561000_GDSYL);
				 }
				 if(DictCodeList.投资性质_权益类.getValue().equals(pos.getIvstorientation())){
//					 setProdNum(line562000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_562000_QYL);
				 }
				 if(DictCodeList.投资性质_商品及金融衍生品类.getValue().equals(pos.getIvstorientation())){
//					 setProdNum(line563000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_563000_SPJJRYSPL);
				 }
				 if(DictCodeList.投资性质_混合类.getValue().equals(pos.getIvstorientation())){
//					 setProdNum(line564000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_564000_HHL);
				 }
				 if(DictCodeList.是否_中债_是.getValue().equals(pos.getIsConstracte())){
//					 setProdNum(line591000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_591000_QZFJCP);
				 }
				 if(DictCodeListExt2.产品转让类型_中债_收益权转让.getValue().equals(pos.getTransferType())){
//					 setProdNum(line592000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_592000_QZSYQZRCP);
				 }
				 if(DictCodeList.是否_中债_是.getValue().equals(pos.getIsMarketFund())){
//					 setProdNum(line593000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_TQDF_AMT_593000_QZHBSCJJ);
				 }
			 
			 }
			 if(DictCodeListExt2.兑付状态_中债_延期兑付.getValue().equals(pos.getPaystatus())){
				 //合计
//				 setProdNum(line500000Sub2);
				 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_500000_ZJ);
				 //按分类
				 if(DictCodeListExt2.产品运作模式_开放式净值型.getValue().equals(pos.getOperateMode())){
//					 setProdNum(line521000Sub2);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_521000_KFSJZX);
				 }
				 if(DictCodeListExt2.产品运作模式_开放式非净值型.getValue().equals(pos.getOperateMode())){
//					 setProdNum(line522000Sub2);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_522000_KFSFJZX);
				 }
				 if(DictCodeListExt2.产品运作模式_封闭式净值型.getValue().equals(pos.getOperateMode())){
//					 setProdNum(line523000Sub2);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_523000_FBSJZX);
				 }
				 if(DictCodeListExt2.产品运作模式_封闭式非净值型.getValue().equals(pos.getOperateMode())){
//					 setProdNum(line524000Sub2);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_524000_FBSFJZX);
				 }
				 if(DictCodeListExt2.募集方式_中债_公募.getValue().equals(pos.getRaisemode())){
//					 setProdNum(line531000Sub2);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_531000_GKMJ);
				 }
				 if(DictCodeListExt2.募集方式_中债_私募.getValue().equals(pos.getRaisemode())){
//					 setProdNum(line532000Sub2);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_532000_FGKMJ);
				 }
				 if(DictCodeList.排期发行方式_集合.getValue().equals(pos.getManageType())){
//					 setProdNum(line541000Sub2);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_541000_JHGL);
				 }
				 if(DictCodeList.排期发行方式_单一.getValue().equals(pos.getManageType())){
//					 setProdNum(line542000Sub2);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_542000_DDGL);
				 }
				 if(DictCodeListExt2.产品受托职责_中债_主动管理.getValue().equals(pos.getFiduciaryDuty())){
//					 setProdNum(line551000Sub2);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_551000_ZDGL);
				 }
				 if(DictCodeListExt2.产品受托职责_中债_被动管理.getValue().equals(pos.getFiduciaryDuty())){
//					 setProdNum(line552000Sub2);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_552000_BDGL);
				 }
				 if(DictCodeList.投资性质_固定收益类.getValue().equals(pos.getIvstorientation())){
//					 setProdNum(line561000Sub2);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_561000_GDSYL);
				 }
				 if(DictCodeList.投资性质_权益类.getValue().equals(pos.getIvstorientation())){
//					 setProdNum(line562000Sub2);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_562000_QYL);
				 }
				 if(DictCodeList.投资性质_商品及金融衍生品类.getValue().equals(pos.getIvstorientation())){
//					 setProdNum(line563000Sub2);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_563000_SPJJRYSPL);
				 }
				 if(DictCodeList.投资性质_混合类.getValue().equals(pos.getIvstorientation())){
//					 setProdNum(line564000Sub2);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_564000_HHL);
				 }
				 if(DictCodeList.是否_中债_是.getValue().equals(pos.getIsConstracte())){
//					 setProdNum(line591000Sub2);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_591000_QZFJCP);
				 }
				 if(DictCodeListExt2.产品转让类型_中债_收益权转让.getValue().equals(pos.getTransferType())){
//					 setProdNum(line592000Sub2);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_592000_QZSYQZRCP);
				 }
				 if(DictCodeList.是否_中债_是.getValue().equals(pos.getIsMarketFund())){
//					 setProdNum(line593000Sub2);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.TQJYQDF_YQDF_AMT_593000_QZHBSCJJ);
				 }	 
			 }
		 }
		 
		 
		// 数据先删后插
		insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,null,result,PbcReportType.PBC_REPORT_1_5,true);
		
		//add by leipeng 20200319  各法人生成数据
		for (Map.Entry<String,Map<String,PbcReportProdDetailDto>> map : deptMap.entrySet()) {
			// 各法人数据先删后插
			insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,map.getKey(),map.getValue(),PbcReportType.PBC_REPORT_1_5,true);
		}
	 }
	 
    public void setDetailValue2(PbcReportProdDetailDto dto,String ccy,BigDecimal value,Map<String, BigDecimal> rateMap,Date generateDate,String source){
        BigDecimal cny = ChbCcyConvertUtil.exchangeMoney(
        		ccy,DictCodeListExt1.币种_中债_人民币.getValue(),generateDate,value,rateMap,source);
        if(DictCodeListExt1.币种_中债_人民币.getValue().equals(ccy)){//人民币
            dto.setCny(dto.getCny().add(cny));
        }else{ //其他币种
            dto.setOthToCny((dto.getOthToCny().add(cny)));
        }
        //合计
        dto.setTotal(dto.getTotal().add(cny));
    }
    
    public void setProdNum(PbcReportProdDetailDto dto){
        dto.setProdcounts(dto.getProdcounts()+1);
    }
    
    
    /**
     * 多法人改造
     * @param dto
     * @param deptDto
     * @param code
     * @param ccy
     */
    public void setProdNumMap(Map<String,PbcReportProdDetailDto> dto,Map<String,PbcReportProdDetailDto> deptDto,String code){
    	//汇总数据计算
    	setProdNum(dto.get(code));
    	//部门数据计算
    	if(null!=deptDto&&null!=deptDto.get(code)){
    		setProdNum(deptDto.get(code));
    	}
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
    	setDetailValue2(dto.get(code),ccy,/*pos.getRedamt()*/value,rateMap,generateDate,source);
    	//部门数据计算
    	if(null!=deptDto&&null!=deptDto.get(code)){
    		setDetailValue2(deptDto.get(code),ccy,/*pos.getRedamt()*/value,rateMap,generateDate,source);
    	}
    }

}
