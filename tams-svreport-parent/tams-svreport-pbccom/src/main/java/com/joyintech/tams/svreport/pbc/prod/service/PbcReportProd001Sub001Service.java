package com.joyintech.tams.svreport.pbc.prod.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
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
 * 功能:表1-1  资管产品募集余额统计模板
 * @since 1.6.0
 * @version 1.6.0
 * @作者：lingxq
 * @创建时间：2019年3月4日
 * @修改记录：
 */
@Service
@TemplateName(PbcTemplateName.PBC_REPORT_1_1)
public class PbcReportProd001Sub001Service extends PbcReportProdTotalService  {
	@Autowired
	private PbcMonthPrdPostionMapper pbcMonthPrdPostionMapper;
	
	
	public void generateReport(Date generateDate,Date reportStartDate,Date reportEndDate,List<PbcMonthPrdPostionDto> posList,boolean isEdit){
		//主表信息
		PbcMonthReport pbcMonthReport = getReportModel(generateDate,reportStartDate,reportEndDate,PbcReportType.PBC_REPORT_1_1,isEdit);
		String reportuuid = pbcMonthReport.getReportuuid();
		
		//明细
		Map<String,PbcReportProdDetailDto> resultTMP = getReportDetailList(reportuuid,PbcReportType.PBC_REPORT_1_1,DictCodeListExt1.维护数据状态_有效.getValue());

		//update by leipeng 20200319  汇总数据提取全量模板
		Map<String,PbcReportProdDetailDto> result = new HashMap<>();
		result.putAll(resultTMP);
		Map<String,Map<String,PbcReportProdDetailDto>> deptMap = new HashMap();
		
		if(posList==null||posList.isEmpty()){
			 // 数据先删后插
			 insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,null,result,PbcReportType.PBC_REPORT_1_1,false);
			 return;
		}
		
		
//		 PbcReportProdDetailDto line100000 = result.get(PbcReportProperycode.MJYE_AMT_100000_CPMJYEZJ); //   产品募集余额总计
////		 PbcReportProdDetailDto line110000 = result.get(PbcReportProperycode.MJYE_AMT_110000_KHLX); //   客户类型
//		 PbcReportProdDetailDto line111000 = result.get(PbcReportProperycode.MJYE_AMT_111000_ZH); //   住户
//		 PbcReportProdDetailDto line112000 = result.get(PbcReportProperycode.MJYE_AMT_112000_GYZF); //   广义政府
//		 PbcReportProdDetailDto line113000 = result.get(PbcReportProperycode.MJYE_AMT_113000_FJRQY); //   非金融企业
//		 PbcReportProdDetailDto line114000 = result.get(PbcReportProperycode.MJYE_AMT_114000_JRJGST); //   金融机构（实体）
//		 PbcReportProdDetailDto line115000 = result.get(PbcReportProperycode.MJYE_AMT_115000_TDMDZT); //   特定目的载体
//		 PbcReportProdDetailDto line116000 = result.get(PbcReportProperycode.MJYE_AMT_116000_JW); //    境外
////		 PbcReportProdDetailDto line120000 = result.get(PbcReportProperycode.MJYE_AMT_120000_YXFS); //   运行方式
//		 PbcReportProdDetailDto line121000 = result.get(PbcReportProperycode.MJYE_AMT_121000_KFSJZX); //   开放式净值型
//		 PbcReportProdDetailDto line122000 = result.get(PbcReportProperycode.MJYE_AMT_122000_KFSFJZX); //   开放式非净值型
//		 PbcReportProdDetailDto line123000 = result.get(PbcReportProperycode.MJYE_AMT_123000_FBSJZX); //   封闭式净值型
//		 PbcReportProdDetailDto line124000 = result.get(PbcReportProperycode.MJYE_AMT_124000_FBSFJZX); //   封闭式非净值型
////		 PbcReportProdDetailDto line130000 = result.get(PbcReportProperycode.MJYE_AMT_130000_MJFS); //   募集方式
//		 PbcReportProdDetailDto line131000 = result.get(PbcReportProperycode.MJYE_AMT_131000_GKMJ); //   公开募集
//		 PbcReportProdDetailDto line132000 = result.get(PbcReportProperycode.MJYE_AMT_132000_FGKMJ); //   非公开募集
////		 PbcReportProdDetailDto line140000 = result.get(PbcReportProperycode.MJYE_AMT_140000_GLFS); //   管理方式
//		 PbcReportProdDetailDto line141000 = result.get(PbcReportProperycode.MJYE_AMT_141000_JHGL); //   集合管理
//		 PbcReportProdDetailDto line142000 = result.get(PbcReportProperycode.MJYE_AMT_142000_DDGL); //   单独管理
////		 PbcReportProdDetailDto line150000 = result.get(PbcReportProperycode.MJYE_AMT_150000_STZZ); //   受托职责
//		 PbcReportProdDetailDto line151000 = result.get(PbcReportProperycode.MJYE_AMT_151000_ZDGL); //   主动管理
//		 PbcReportProdDetailDto line152000 = result.get(PbcReportProperycode.MJYE_AMT_152000_BDGL); //   被动管理
////		 PbcReportProdDetailDto line160000 = result.get(PbcReportProperycode.MJYE_AMT_160000_CPLX); //   产品类型
//		 PbcReportProdDetailDto line161000 = result.get(PbcReportProperycode.MJYE_AMT_161000_GDSYL); //   固定收益类
//		 PbcReportProdDetailDto line162000 = result.get(PbcReportProperycode.MJYE_AMT_162000_QYL); //   权益类
//		 PbcReportProdDetailDto line163000 = result.get(PbcReportProperycode.MJYE_AMT_163000_SPJJRYSPL); //   商品及金融衍生品类
//		 PbcReportProdDetailDto line164000 = result.get(PbcReportProperycode.MJYE_AMT_164000_HHL); //   混合类
////		 PbcReportProdDetailDto line190000 = result.get(PbcReportProperycode.MJYE_AMT_190000_F); //   附:
//		 PbcReportProdDetailDto line191000 = result.get(PbcReportProperycode.MJYE_AMT_191000_QZFJCP); //   其中：分级产品
//		 PbcReportProdDetailDto line192000 = result.get(PbcReportProperycode.MJYE_AMT_192000_QZSYQZRCP); //   其中：收益权转让产品
//		 PbcReportProdDetailDto line193000 = result.get(PbcReportProperycode.MJYE_AMT_193000_QZHBSCJJ); //   其中：货币市场基金
		 
		 Map<String, BigDecimal> rateMap = new HashMap<>();
		 String source = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);
		 for(PbcMonthPrdPostionDto pos :posList){
			 
			 
			// add by leipeng 20200319 获取所有部门对应信息,并列出单独的表结构
			 if(null!=pos.getInstitutionCode()&&null==deptMap.get(pos.getInstitutionCode())){
				 Map<String,PbcReportProdDetailDto> singDeptMap = getReportDetailList(reportuuid,PbcReportType.PBC_REPORT_1_1,DictCodeListExt1.维护数据状态_有效.getValue());

				 deptMap.put(pos.getInstitutionCode(), singDeptMap);
			 }
			 
			 
			 //合计
//			 setDetailValue(line100000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_100000_CPMJYEZJ,
					 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 
			 //按分类
			 if(DictCodeListExt2.客户二级类型_住户.getValue().equals(pos.getCustomerType2())){
//				 setDetailValue(line111000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_111000_ZH,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.客户二级类型_广义政府.getValue().equals(pos.getCustomerType2())){
//				 setDetailValue(line112000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_112000_GYZF,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.客户二级类型_非金融企业.getValue().equals(pos.getCustomerType2())){
//				 setDetailValue(line113000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_113000_FJRQY,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.客户二级类型_银行业存款类金融机构.getValue().equals(pos.getCustomerType2())
			    ||DictCodeListExt2.客户二级类型_银行业非存款类金融机构.getValue().equals(pos.getCustomerType2())
			    ||DictCodeListExt2.客户二级类型_非银行业金融机构.getValue().equals(pos.getCustomerType2())){
//				 setDetailValue(line114000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_114000_JRJGST,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
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
//				 setDetailValue(line115000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_115000_TDMDZT,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if (pos.getCustomerType2()!=null&&pos.getCustomerType2().contains(DictCodeListExt2.客户一级类型_境外.getValue())) {
//				 setDetailValue(line116000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_116000_JW,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.产品运作模式_开放式净值型.getValue().equals(pos.getOperateMode())){
//				 setDetailValue(line121000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_121000_KFSJZX,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.产品运作模式_开放式非净值型.getValue().equals(pos.getOperateMode())){
//				 setDetailValue(line122000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_122000_KFSFJZX,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.产品运作模式_封闭式净值型.getValue().equals(pos.getOperateMode())){
//				 setDetailValue(line123000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_123000_FBSJZX,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.产品运作模式_封闭式非净值型.getValue().equals(pos.getOperateMode())){
//				 setDetailValue(line124000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_124000_FBSFJZX,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.募集方式_中债_公募.getValue().equals(pos.getRaisemode())){
//				 setDetailValue(line131000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_131000_GKMJ,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.募集方式_中债_私募.getValue().equals(pos.getRaisemode())){
//				 setDetailValue(line132000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_132000_FGKMJ,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeList.排期发行方式_集合.getValue().equals(pos.getManageType())){
//				 setDetailValue(line141000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_141000_JHGL,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeList.排期发行方式_单一.getValue().equals(pos.getManageType())){
//				 setDetailValue(line142000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_142000_DDGL,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.产品受托职责_中债_主动管理.getValue().equals(pos.getFiduciaryDuty())){
//				 setDetailValue(line151000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_151000_ZDGL,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.产品受托职责_中债_被动管理.getValue().equals(pos.getFiduciaryDuty())){
//				 setDetailValue(line152000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_152000_BDGL,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeList.投资性质_固定收益类.getValue().equals(pos.getIvstorientation())){
//				 setDetailValue(line161000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_161000_GDSYL,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeList.投资性质_权益类.getValue().equals(pos.getIvstorientation())){
//				 setDetailValue(line162000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_162000_QYL,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeList.投资性质_商品及金融衍生品类.getValue().equals(pos.getIvstorientation())){
//				 setDetailValue(line163000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_163000_SPJJRYSPL,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeList.投资性质_混合类.getValue().equals(pos.getIvstorientation())){
//				 setDetailValue(line164000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_164000_HHL,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeList.是否_中债_是.getValue().equals(pos.getIsConstracte())){
//				 setDetailValue(line191000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_191000_QZFJCP,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.产品转让类型_中债_收益权转让.getValue().equals(pos.getTransferType())){
//				 setDetailValue(line192000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_192000_QZSYQZRCP,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
			 if(DictCodeList.是否_中债_是.getValue().equals(pos.getIsMarketFund())){
//				 setDetailValue(line193000,pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJYE_AMT_193000_QZHBSCJJ,
						 	pos.getCcy(),pos.getEndamtsumOth(),rateMap,generateDate,source);
			 }
		 }
		 
		 // 数据先删后插
		 insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,null,result,PbcReportType.PBC_REPORT_1_1,true);
		 
		 
		//add by leipeng 20200319  各法人生成数据
			for (Map.Entry<String,Map<String,PbcReportProdDetailDto>> map : deptMap.entrySet()) {
				// 各法人数据先删后插
				insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,map.getKey(),map.getValue(),PbcReportType.PBC_REPORT_1_1,true);
			}
	 }

}
