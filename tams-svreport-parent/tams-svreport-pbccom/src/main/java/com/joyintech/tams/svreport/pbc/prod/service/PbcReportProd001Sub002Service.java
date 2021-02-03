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
 * 功能:表1-2募集及兑付发生额
 * @since 1.6.0
 * @version 1.6.0
 * @作者：lingxq
 * @创建时间：2019年3月4日
 * @修改记录：
 */
@Service
@TemplateName(PbcTemplateName.PBC_REPORT_1_2)
public class PbcReportProd001Sub002Service extends PbcReportProdTotalService  {
	@Autowired
	private PbcMonthPrdPostionMapper pbcMonthPrdPostionMapper;
	
	
	public void generateReport(Date generateDate,Date reportStartDate,Date reportEndDate,List<PbcMonthPrdPostionDto> posList,boolean isEdit){
		//主表信息
		PbcMonthReport pbcMonthReport = getReportModel(generateDate,reportStartDate,reportEndDate,PbcReportType.PBC_REPORT_1_2,isEdit);
		String reportuuid = pbcMonthReport.getReportuuid();
		
		//明细
		Map<String,PbcReportProdDetailDto> resultTMP = getReportDetailList(reportuuid,PbcReportType.PBC_REPORT_1_2,DictCodeListExt1.维护数据状态_有效.getValue());

		//update by leipeng 20200319  汇总数据提取全量模板
		Map<String,PbcReportProdDetailDto> result = new HashMap<>();
		result.putAll(resultTMP);
		Map<String,Map<String,PbcReportProdDetailDto>> deptMap = new HashMap();
		
		
		 if(posList==null||posList.isEmpty()){
			 //没有头寸，不需要插入
			 insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,null,result,PbcReportType.PBC_REPORT_1_2,false);
			 return;
		 }
//		 PbcReportProdDetailDto line200000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_200000_ZJ); //   产品募集余额总计
////		 PbcReportProdDetailDto line210000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_210000_KHLX); //   客户类型
//		 PbcReportProdDetailDto line211000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_211000_ZH); //   住户
//		 PbcReportProdDetailDto line212000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_212000_GYZF); //   广义政府
//		 PbcReportProdDetailDto line213000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_213000_FJRQY); //   非金融企业
//		 PbcReportProdDetailDto line214000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_214000_JRJGST); //   金融机构（实体）
//		 PbcReportProdDetailDto line215000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_215000_TDMDZT); //   特定目的载体
//		 PbcReportProdDetailDto line216000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_216000_JW); //   境外
////		 PbcReportProdDetailDto line220000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_220000_YXFS); //   运行方式
//		 PbcReportProdDetailDto line221000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_221000_KFSJZX); //   开放式净值型
//		 PbcReportProdDetailDto line222000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_222000_KFSFJZX); //   开放式非净值型
//		 PbcReportProdDetailDto line223000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_223000_FBSJZX); //   封闭式净值型
//		 PbcReportProdDetailDto line224000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_224000_FBSFJZX); //   封闭式非净值型
////		 PbcReportProdDetailDto line230000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_230000_MJFS); //   募集方式
//		 PbcReportProdDetailDto line231000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_231000_GKMJ); //   公开募集
//		 PbcReportProdDetailDto line232000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_232000_FGKMJ); //   非公开募集
////		 PbcReportProdDetailDto line240000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_240000_GLFS); //   管理方式
//		 PbcReportProdDetailDto line241000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_241000_JHGL); //   集合管理
//		 PbcReportProdDetailDto line242000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_242000_DDGL); //   单独管理
////		 PbcReportProdDetailDto line250000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_250000_STZZ); //   受托职责
//		 PbcReportProdDetailDto line251000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_251000_ZDGL); //   主动管理
//		 PbcReportProdDetailDto line252000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_252000_BDGL); //   被动管理
////		 PbcReportProdDetailDto line260000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_260000_CPLX); //   产品类型
//		 PbcReportProdDetailDto line261000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_261000_GDSYL); //   固定收益类
//		 PbcReportProdDetailDto line262000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_262000_QYL); //   权益类
//		 PbcReportProdDetailDto line263000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_263000_SPJJRYSPL); //   商品及金融衍生品类
//		 PbcReportProdDetailDto line264000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_264000_HHL); //   混合类
////		 PbcReportProdDetailDto line290000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_290000_F); //   附:
//		 PbcReportProdDetailDto line291000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_291000_QZFJCP); //   其中：分级产品
//		 PbcReportProdDetailDto line292000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_292000_QZSYQZRCP); //   其中：收益权转让产品
//		 PbcReportProdDetailDto line293000 = result.get(PbcReportProperycode.MJDF_DQMJ_AMT_293000_QZHBSCJJ); //   其中：货币市场基金
//		 
//		 PbcReportProdDetailDto line200000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_200000_ZJ); //   产品募集余额总计
////		 PbcReportProdDetailDto line210000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_210000_KHLX); //   客户类型
//		 PbcReportProdDetailDto line211000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_211000_ZH); //   住户
//		 PbcReportProdDetailDto line212000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_212000_GYZF); //   广义政府
//		 PbcReportProdDetailDto line213000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_213000_FJRQY); //   非金融企业
//		 PbcReportProdDetailDto line214000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_214000_JRJGST); //   金融机构（实体）
//		 PbcReportProdDetailDto line215000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_215000_TDMDZT); //   特定目的载体
//		 PbcReportProdDetailDto line216000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_216000_JW); //   境外
////		 PbcReportProdDetailDto line220000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_220000_YXFS); //   运行方式
//		 PbcReportProdDetailDto line221000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_221000_KFSJZX); //   开放式净值型
//		 PbcReportProdDetailDto line222000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_222000_KFSFJZX); //   开放式非净值型
//		 PbcReportProdDetailDto line223000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_223000_FBSJZX); //   封闭式净值型
//		 PbcReportProdDetailDto line224000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_224000_FBSFJZX); //   封闭式非净值型
////		 PbcReportProdDetailDto line230000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_230000_MJFS); //   募集方式
//		 PbcReportProdDetailDto line231000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_231000_GKMJ); //   公开募集
//		 PbcReportProdDetailDto line232000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_232000_FGKMJ); //   非公开募集
////		 PbcReportProdDetailDto line240000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_240000_GLFS); //   管理方式
//		 PbcReportProdDetailDto line241000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_241000_JHGL); //   集合管理
//		 PbcReportProdDetailDto line242000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_242000_DDGL); //   单独管理
////		 PbcReportProdDetailDto line250000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_250000_STZZ); //   受托职责
//		 PbcReportProdDetailDto line251000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_251000_ZDGL); //   主动管理
//		 PbcReportProdDetailDto line252000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_252000_BDGL); //   被动管理
////		 PbcReportProdDetailDto line260000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_260000_CPLX); //   产品类型
//		 PbcReportProdDetailDto line261000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_261000_GDSYL); //   固定收益类
//		 PbcReportProdDetailDto line262000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_262000_QYL); //   权益类
//		 PbcReportProdDetailDto line263000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_263000_SPJJRYSPL); //   商品及金融衍生品类
//		 PbcReportProdDetailDto line264000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_264000_HHL); //   混合类
////		 PbcReportProdDetailDto line290000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_290000_F); //   附:
//		 PbcReportProdDetailDto line291000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_291000_QZFJCP); //   其中：分级产品
//		 PbcReportProdDetailDto line292000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_292000_QZSYQZRCP); //   其中：收益权转让产品
//		 PbcReportProdDetailDto line293000Sub2 = result.get(PbcReportProperycode.MJDF_DQDF_AMT_293000_QZHBSCJJ); //   其中：货币市场基金
		 
		 

		 Map<String, BigDecimal> rateMap = new HashMap<>();
		 String source = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);
		 for(PbcMonthPrdPostionDto pos :posList){
			 
			// add by leipeng 20200319 获取所有部门对应信息,并列出单独的表结构
			 if(null!=pos.getInstitutionCode()&&null==deptMap.get(pos.getInstitutionCode())){
				 Map<String,PbcReportProdDetailDto> singDeptMap = getReportDetailList(reportuuid,PbcReportType.PBC_REPORT_1_2,DictCodeListExt1.维护数据状态_有效.getValue());

				 deptMap.put(pos.getInstitutionCode(), singDeptMap);
			 }
			 
			 
			 //合计
//			 setDetailValue(line200000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//			 setDetailValue(line200000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);

			 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_200000_ZJ,
					 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
			 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_200000_ZJ,
					 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 //按分类
			 if(DictCodeListExt2.客户二级类型_住户.getValue().equals(pos.getCustomerType2())){
//				 setDetailValue(line211000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line211000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
//				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_211000_ZH,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_211000_ZH,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.客户二级类型_广义政府.getValue().equals(pos.getCustomerType2())){
//				 setDetailValue(line212000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line212000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_212000_GYZF,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_212000_GYZF,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.客户二级类型_非金融企业.getValue().equals(pos.getCustomerType2())){
//				 setDetailValue(line213000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line213000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_213000_FJRQY,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_213000_FJRQY,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
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
//				 setDetailValue(line214000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line214000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_214000_JRJGST,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_214000_JRJGST,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
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
//				 setDetailValue(line215000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line215000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_215000_TDMDZT,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_215000_TDMDZT,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if (pos.getCustomerType2()!=null&&pos.getCustomerType2().contains(DictCodeListExt2.客户一级类型_境外.getValue())) {
//				 setDetailValue(line216000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line216000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_216000_JW,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_216000_JW,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.产品运作模式_开放式净值型.getValue().equals(pos.getOperateMode())){
//				 setDetailValue(line221000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line221000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);

				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_221000_KFSJZX,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_221000_KFSJZX,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.产品运作模式_开放式非净值型.getValue().equals(pos.getOperateMode())){
//				 setDetailValue(line222000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line222000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_222000_KFSFJZX,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_222000_KFSFJZX,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.产品运作模式_封闭式净值型.getValue().equals(pos.getOperateMode())){
//				 setDetailValue(line223000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line223000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_223000_FBSJZX,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_223000_FBSJZX,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.产品运作模式_封闭式非净值型.getValue().equals(pos.getOperateMode())){
//				 setDetailValue(line224000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line224000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_224000_FBSFJZX,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_224000_FBSFJZX,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.募集方式_中债_公募.getValue().equals(pos.getRaisemode())){
//				 setDetailValue(line231000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line231000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_231000_GKMJ,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_231000_GKMJ,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.募集方式_中债_私募.getValue().equals(pos.getRaisemode())){
//				 setDetailValue(line232000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line232000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_232000_FGKMJ,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_232000_FGKMJ,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeList.排期发行方式_集合.getValue().equals(pos.getManageType())){
//				 setDetailValue(line241000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line241000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_241000_JHGL,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_241000_JHGL,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeList.排期发行方式_单一.getValue().equals(pos.getManageType())){
//				 setDetailValue(line242000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line242000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_242000_DDGL,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_242000_DDGL,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.产品受托职责_中债_主动管理.getValue().equals(pos.getFiduciaryDuty())){
//				 setDetailValue(line251000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line251000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_251000_ZDGL,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_251000_ZDGL,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.产品受托职责_中债_被动管理.getValue().equals(pos.getFiduciaryDuty())){
//				 setDetailValue(line252000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line252000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_252000_BDGL,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_252000_BDGL,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeList.投资性质_固定收益类.getValue().equals(pos.getIvstorientation())){
//				 setDetailValue(line261000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line261000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_261000_GDSYL,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_261000_GDSYL,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeList.投资性质_权益类.getValue().equals(pos.getIvstorientation())){
//				 setDetailValue(line262000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line262000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_262000_QYL,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_262000_QYL,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeList.投资性质_商品及金融衍生品类.getValue().equals(pos.getIvstorientation())){
//				 setDetailValue(line263000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line263000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_263000_SPJJRYSPL,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_263000_SPJJRYSPL,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeList.投资性质_混合类.getValue().equals(pos.getIvstorientation())){
//				 setDetailValue(line264000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line264000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_264000_HHL,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_264000_HHL,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeList.是否_中债_是.getValue().equals(pos.getIsConstracte())){
//				 setDetailValue(line291000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line291000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_291000_QZFJCP,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_291000_QZFJCP,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeListExt2.产品转让类型_中债_收益权转让.getValue().equals(pos.getTransferType())){
//				 setDetailValue(line292000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line292000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_292000_QZSYQZRCP,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_292000_QZSYQZRCP,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
			 if(DictCodeList.是否_中债_是.getValue().equals(pos.getIsMarketFund())){
//				 setDetailValue(line293000,pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
//				 setDetailValue(line293000Sub2,pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
				 
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQMJ_AMT_293000_QZHBSCJJ,
						 	pos.getCcy(),pos.getDirtyamtsum(),rateMap,generateDate,source);
				 setDetailValueAndDept(result,deptMap.get(pos.getInstitutionCode()),PbcReportProperycode.MJDF_DQDF_AMT_293000_QZHBSCJJ,
						 	pos.getCcy(),pos.getRedamt(),rateMap,generateDate,source);
			 }
		 }
		 
		// 数据先删后插
		insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,result,PbcReportType.PBC_REPORT_1_2,true);
		
		//add by leipeng 20200319  各法人生成数据
		for (Map.Entry<String,Map<String,PbcReportProdDetailDto>> map : deptMap.entrySet()) {
			// 各法人数据先删后插
			insertReportAndDetails(reportStartDate,reportEndDate,pbcMonthReport,map.getKey(),map.getValue(),PbcReportType.PBC_REPORT_1_2,true);
		}
	 }

}
