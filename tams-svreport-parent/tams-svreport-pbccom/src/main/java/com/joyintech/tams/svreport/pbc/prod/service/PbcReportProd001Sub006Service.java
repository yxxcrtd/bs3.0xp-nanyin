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
import com.joyintech.framework.util.IDGenerator;
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
 * 功能:表1-6  到期未兑付
 * @since 1.6.0
 * @version 1.6.0
 * @作者：lingxq
 * @创建时间：2019年3月4日
 * @修改记录：
 */
@Service
@TemplateName(PbcTemplateName.PBC_REPORT_1_6)
public class PbcReportProd001Sub006Service extends PbcReportProdTotalService  {
	@Autowired
	private PbcMonthPrdPostionMapper pbcMonthPrdPostionMapper;
	
	
	public void generateReport(Date generateDate,Date reportStartDate,Date reportEndDate,List<PbcMonthPrdPostionDto> posList,boolean isEdit){
		//主表信息
		PbcMonthReport pbcMonthReport = getReportModel(generateDate,reportStartDate,reportEndDate,PbcReportType.PBC_REPORT_1_6,isEdit);
		String reportuuid = pbcMonthReport.getReportuuid();
		
		//明细
		Map<String,PbcReportProdDetailDto> resultTMP = getReportDetailList(reportuuid,PbcReportType.PBC_REPORT_1_6,DictCodeListExt1.维护数据状态_有效.getValue());

		
		//update by leipeng 20200319  汇总数据提取全量模板
		Map<String,PbcReportProdDetailDto> result = new HashMap<>();
		result.putAll(resultTMP);
		Map<String,Map<String,PbcReportProdDetailDto>> deptMap = new HashMap();
		

		 List<PbcMonthPrdPostionDto> posList2 =
				 	pbcMonthPrdPostionMapper.selectByDateWithoutCustomer(DateUtil.format(generateDate));//用于统计只数
		 if(posList==null||posList.isEmpty()){
			 //没有头寸，不需要插入
			 insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,null,result,PbcReportType.PBC_REPORT_1_6,false);
			 return;
		 }
		 
//		 PbcReportProdDetailDto line600000 = result.get(PbcReportProperycode.DQWDF_AMT_600000_ZJ); //   产品募集余额总计
////		 PbcReportProdDetailDto line610000 = result.get(PbcReportProperycode.DQWDF_AMT_610000_KHLX); //   客户类型
//		 PbcReportProdDetailDto line611000 = result.get(PbcReportProperycode.DQWDF_AMT_611000_ZH); //   住户
//		 PbcReportProdDetailDto line612000 = result.get(PbcReportProperycode.DQWDF_AMT_612000_GYZF); //   广义政府
//		 PbcReportProdDetailDto line613000 = result.get(PbcReportProperycode.DQWDF_AMT_613000_FJRQY); //   非金融企业
//		 PbcReportProdDetailDto line614000 = result.get(PbcReportProperycode.DQWDF_AMT_614000_JRJGST); //   金融机构（实体）
//		 PbcReportProdDetailDto line615000 = result.get(PbcReportProperycode.DQWDF_AMT_615000_TDMDZT); //   特定目的载体
//		 PbcReportProdDetailDto line616000 = result.get(PbcReportProperycode.DQWDF_AMT_616000_JW); //   境外
////		 PbcReportProdDetailDto line620000 = result.get(PbcReportProperycode.DQWDF_AMT_620000_YXFS); //   运行方式
//		 PbcReportProdDetailDto line621000 = result.get(PbcReportProperycode.DQWDF_AMT_621000_KFSJZX); //   开放式净值型
//		 PbcReportProdDetailDto line622000 = result.get(PbcReportProperycode.DQWDF_AMT_622000_KFSFJZX); //   开放式非净值型
//		 PbcReportProdDetailDto line623000 = result.get(PbcReportProperycode.DQWDF_AMT_623000_FBSJZX); //   封闭式净值型
//		 PbcReportProdDetailDto line624000 = result.get(PbcReportProperycode.DQWDF_AMT_624000_FBSFJZX); //   封闭式非净值型
////		 PbcReportProdDetailDto line630000 = result.get(PbcReportProperycode.DQWDF_AMT_630000_MJFS); //   募集方式
//		 PbcReportProdDetailDto line631000 = result.get(PbcReportProperycode.DQWDF_AMT_631000_GKMJ); //   公开募集
//		 PbcReportProdDetailDto line632000 = result.get(PbcReportProperycode.DQWDF_AMT_632000_FGKMJ); //   非公开募集
////		 PbcReportProdDetailDto line640000 = result.get(PbcReportProperycode.DQWDF_AMT_640000_GLFS); //   管理方式
//		 PbcReportProdDetailDto line641000 = result.get(PbcReportProperycode.DQWDF_AMT_641000_JHGL); //   集合管理
//		 PbcReportProdDetailDto line642000 = result.get(PbcReportProperycode.DQWDF_AMT_642000_DDGL); //   单独管理
////		 PbcReportProdDetailDto line650000 = result.get(PbcReportProperycode.DQWDF_AMT_650000_STZZ); //   受托职责
//		 PbcReportProdDetailDto line651000 = result.get(PbcReportProperycode.DQWDF_AMT_651000_ZDGL); //   主动管理
//		 PbcReportProdDetailDto line652000 = result.get(PbcReportProperycode.DQWDF_AMT_652000_BDGL); //   被动管理
////		 PbcReportProdDetailDto line660000 = result.get(PbcReportProperycode.DQWDF_AMT_660000_CPLX); //   产品类型
//		 PbcReportProdDetailDto line661000 = result.get(PbcReportProperycode.DQWDF_AMT_661000_GDSYL); //   固定收益类
//		 PbcReportProdDetailDto line662000 = result.get(PbcReportProperycode.DQWDF_AMT_662000_QYL); //   权益类
//		 PbcReportProdDetailDto line663000 = result.get(PbcReportProperycode.DQWDF_AMT_663000_SPJJRYSPL); //   商品及金融衍生品类
//		 PbcReportProdDetailDto line664000 = result.get(PbcReportProperycode.DQWDF_AMT_664000_HHL); //   混合类
////		 PbcReportProdDetailDto line690000 = result.get(PbcReportProperycode.DQWDF_AMT_690000_F); //   附:
//		 PbcReportProdDetailDto line691000 = result.get(PbcReportProperycode.DQWDF_AMT_691000_QZFJCP); //   其中：分级产品
//		 PbcReportProdDetailDto line692000 = result.get(PbcReportProperycode.DQWDF_AMT_692000_QZSYQZRCP); //   其中：收益权转让产品
//		 PbcReportProdDetailDto line693000 = result.get(PbcReportProperycode.DQWDF_AMT_693000_QZHBSCJJ); //   其中：货币市场基金
		 
		 Map<String, BigDecimal> rateMap = new HashMap<>();
		 String source = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);
		 /*到期未兑付统计由期末余额值换为剩余成本值  gaojian 2019-12-20 begin*/
		 for(PbcMonthPrdPostionDto pos :posList){
			// 销售区域为计划单列市的不统计（省份内已经统计）：210200大连市、330200宁波市、350200厦门市、370200青岛市、440300深圳市
			if("210200".equals(pos.getBuyPlace()) || "330200".equals(pos.getBuyPlace()) || "350200".equals(pos.getBuyPlace()) || 
					"370200".equals(pos.getBuyPlace()) || "440300".equals(pos.getBuyPlace())) {
				continue;
			}
			
			
			 // add by leipeng 20200319 获取所有部门对应信息,并列出单独的表结构
			 if(null!=pos.getInstitutionCode()&&null==deptMap.get(pos.getInstitutionCode())){
				 Map<String,PbcReportProdDetailDto> singDeptMap = getReportDetailList(reportuuid,PbcReportType.PBC_REPORT_1_6,DictCodeListExt1.维护数据状态_有效.getValue());

				 deptMap.put(pos.getInstitutionCode(), singDeptMap);
			 }
			 
			
			 	if(DictCodeListExt2.兑付状态_中债_到期未兑付.getValue().equals(pos.getPaystatus())){
				 //合计
//				 setDetailValue2(line600000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
				 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_600000_ZJ,
						 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 //按分类
				 if(DictCodeListExt2.客户二级类型_住户.getValue().equals(pos.getCustomerType2())){
//					 setDetailValue2(line611000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_611000_ZH,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.客户二级类型_广义政府.getValue().equals(pos.getCustomerType2())){
//					 setDetailValue2(line612000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_612000_GYZF,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.客户二级类型_非金融企业.getValue().equals(pos.getCustomerType2())){
//					 setDetailValue2(line613000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_613000_FJRQY,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.客户二级类型_银行业存款类金融机构.getValue().equals(pos.getCustomerType2())
				    ||DictCodeListExt2.客户二级类型_银行业非存款类金融机构.getValue().equals(pos.getCustomerType2())
						 /*****G06升级改造 leipeng 20210128   start**/
						 ||DictCodeListExt2.客户二级类型_保险业金融机构.getValue().equals(pos.getCustomerType2())
						 ||DictCodeListExt2.客户二级类型_信托公司.getValue().equals(pos.getCustomerType2())
						 ||DictCodeListExt2.客户二级类型_证券公司.getValue().equals(pos.getCustomerType2())
						 ||DictCodeListExt2.客户二级类型_基金公司.getValue().equals(pos.getCustomerType2())
						 ||DictCodeListExt2.客户二级类型_其他金融机构.getValue().equals(pos.getCustomerType2())
						 /*****G06升级改造 leipeng 20210128   end**/
					||DictCodeListExt2.客户二级类型_非银行业金融机构.getValue().equals(pos.getCustomerType2())){
//					 setDetailValue2(line614000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_614000_JRJGST,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
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
//					 setDetailValue2(line615000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_615000_TDMDZT,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(pos.getCustomerType2()!=null&&pos.getCustomerType2().contains(DictCodeListExt2.客户一级类型_境外.getValue())){
//					 setDetailValue2(line616000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_616000_JW,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品运作模式_开放式净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue2(line621000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_621000_KFSJZX,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品运作模式_开放式非净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue2(line622000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_622000_KFSFJZX,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品运作模式_封闭式净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue2(line623000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_623000_FBSJZX,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品运作模式_封闭式非净值型.getValue().equals(pos.getOperateMode())){
//					 setDetailValue2(line624000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_624000_FBSFJZX,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.募集方式_中债_公募.getValue().equals(pos.getRaisemode())){
//					 setDetailValue2(line631000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_631000_GKMJ,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.募集方式_中债_私募.getValue().equals(pos.getRaisemode())){
//					 setDetailValue2(line632000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_632000_FGKMJ,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.排期发行方式_集合.getValue().equals(pos.getManageType())){
//					 setDetailValue2(line641000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_641000_JHGL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.排期发行方式_单一.getValue().equals(pos.getManageType())){
//					 setDetailValue2(line642000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_642000_DDGL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品受托职责_中债_主动管理.getValue().equals(pos.getFiduciaryDuty())){
//					 setDetailValue2(line651000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_651000_ZDGL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品受托职责_中债_被动管理.getValue().equals(pos.getFiduciaryDuty())){
//					 setDetailValue2(line652000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_652000_BDGL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.投资性质_固定收益类.getValue().equals(pos.getIvstorientation())){
//					 setDetailValue2(line661000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_661000_GDSYL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.投资性质_权益类.getValue().equals(pos.getIvstorientation())){
//					 setDetailValue2(line662000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_662000_QYL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.投资性质_商品及金融衍生品类.getValue().equals(pos.getIvstorientation())){
//					 setDetailValue2(line663000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_663000_SPJJRYSPL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.投资性质_混合类.getValue().equals(pos.getIvstorientation())){
//					 setDetailValue2(line664000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_664000_HHL,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.是否_中债_是.getValue().equals(pos.getIsConstracte())){
//					 setDetailValue2(line691000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_691000_QZFJCP,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeListExt2.产品转让类型_中债_收益权转让.getValue().equals(pos.getTransferType())){
//					 setDetailValue2(line692000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_692000_QZSYQZRCP,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
				 if(DictCodeList.是否_中债_是.getValue().equals(pos.getIsMarketFund())){
//					 setDetailValue2(line693000,pos.getCcy(),/*pos.getEndamtsumOth()*/pos.getEndCost(),rateMap,generateDate,source);
					 setDetailValueMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_693000_QZHBSCJJ,
							 	pos.getCcy(),/*pos.getRedamt()*/pos.getEndCost(),rateMap,generateDate,source);
				 }
			 
			 }
		 }
		 /*到期未兑付统计由期末余额值换为剩余成本值  gaojian 2019-12-20 end*/
		 for(PbcMonthPrdPostionDto pos :posList2){
			 // 销售区域为计划单列市的不统计（省份内已经统计）：210200大连市、330200宁波市、350200厦门市、370200青岛市、440300深圳市
			 if("210200".equals(pos.getBuyPlace()) || "330200".equals(pos.getBuyPlace()) || "350200".equals(pos.getBuyPlace()) || 
					 "370200".equals(pos.getBuyPlace()) || "440300".equals(pos.getBuyPlace())) {
				 continue;
			 }
			 
			 
			 // add by leipeng 20200319 获取所有部门对应信息,并列出单独的表结构
			 if(null!=pos.getInstitutionCode()&&null==deptMap.get(pos.getInstitutionCode())){
				 Map<String,PbcReportProdDetailDto> singDeptMap = getReportDetailList(reportuuid,PbcReportType.PBC_REPORT_1_6,DictCodeListExt1.维护数据状态_有效.getValue());

				 deptMap.put(pos.getInstitutionCode(), singDeptMap);
			 }
			 
			 
			 if(DictCodeListExt2.兑付状态_中债_到期未兑付.getValue().equals(pos.getPaystatus())){
				 //合计
//				 setProdNum(line600000);
				 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_600000_ZJ);
				 //按分类
				 if(DictCodeListExt2.产品运作模式_开放式净值型.getValue().equals(pos.getOperateMode())){
//					 setProdNum(line621000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_621000_KFSJZX);

				 }
				 if(DictCodeListExt2.产品运作模式_开放式非净值型.getValue().equals(pos.getOperateMode())){
//					 setProdNum(line622000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_622000_KFSFJZX);

				 }
				 if(DictCodeListExt2.产品运作模式_封闭式净值型.getValue().equals(pos.getOperateMode())){
//					 setProdNum(line623000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_623000_FBSJZX);
				 }
				 if(DictCodeListExt2.产品运作模式_封闭式非净值型.getValue().equals(pos.getOperateMode())){
//					 setProdNum(line624000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_624000_FBSFJZX);
				 }
				 if(DictCodeListExt2.募集方式_中债_公募.getValue().equals(pos.getRaisemode())){
//					 setProdNum(line631000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_631000_GKMJ);
				 }
				 if(DictCodeListExt2.募集方式_中债_私募.getValue().equals(pos.getRaisemode())){
//					 setProdNum(line632000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_632000_FGKMJ);
				 }
				 if(DictCodeList.排期发行方式_集合.getValue().equals(pos.getManageType())){
//					 setProdNum(line641000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_641000_JHGL);
				 }
				 if(DictCodeList.排期发行方式_单一.getValue().equals(pos.getManageType())){
//					 setProdNum(line642000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_642000_DDGL);
				 }
				 if(DictCodeListExt2.产品受托职责_中债_主动管理.getValue().equals(pos.getFiduciaryDuty())){
//					 setProdNum(line651000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_651000_ZDGL);
				 }
				 if(DictCodeListExt2.产品受托职责_中债_被动管理.getValue().equals(pos.getFiduciaryDuty())){
//					 setProdNum(line652000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_652000_BDGL);
				 }
				 if(DictCodeList.投资性质_固定收益类.getValue().equals(pos.getIvstorientation())){
//					 setProdNum(line661000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_661000_GDSYL);
				 }
				 if(DictCodeList.投资性质_权益类.getValue().equals(pos.getIvstorientation())){
//					 setProdNum(line662000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_662000_QYL);
				 }
				 if(DictCodeList.投资性质_商品及金融衍生品类.getValue().equals(pos.getIvstorientation())){
//					 setProdNum(line663000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_663000_SPJJRYSPL);
				 }
				 if(DictCodeList.投资性质_混合类.getValue().equals(pos.getIvstorientation())){
//					 setProdNum(line664000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_664000_HHL);
				 }
				 if(DictCodeList.是否_中债_是.getValue().equals(pos.getIsConstracte())){
//					 setProdNum(line691000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_691000_QZFJCP);
				 }
				 if(DictCodeListExt2.产品转让类型_中债_收益权转让.getValue().equals(pos.getTransferType())){
//					 setProdNum(line692000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_692000_QZSYQZRCP);
				 }
				 if(DictCodeList.是否_中债_是.getValue().equals(pos.getIsMarketFund())){
//					 setProdNum(line693000);
					 setProdNumMap(result,deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DQWDF_AMT_693000_QZHBSCJJ);
				 }
			 
			 }
		 }
		 
		 
		// 数据先删后插
		insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,null,result,PbcReportType.PBC_REPORT_1_6,true);
		
		//add by leipeng 20200319  各法人生成数据
		for (Map.Entry<String,Map<String,PbcReportProdDetailDto>> map : deptMap.entrySet()) {
			// 各法人数据先删后插
			insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,map.getKey(),map.getValue(),PbcReportType.PBC_REPORT_1_6,true);
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
