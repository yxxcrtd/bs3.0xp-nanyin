/**
 * 
 */
package com.joyintech.tams.disclosure.busicommon.poireport.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.RepReportCheckMapper;
import com.joyintech.tams.common.base.entity.RepDataCheck;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.RepReportCheck;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.common.oss.service.FileRemoteService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt5;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.dao.ReportInfoMapper;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;
import com.joyintech.tams.disclosure.busicommon.poireport.dao.PoiReportMapper;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisReportType;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 报表检查服务<br>
 * 
 * @author chenxc
 * @version 1.0 2022年08月01日
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PoiReportCheckService {

	@Autowired
	private ReportInfoMapper reportInfoMapper;

	@Autowired
	private PoiReportMapper poiReportMapper;
	
	@Resource
	private FileRemoteService minioFileRemoteService;
	
	@Autowired
	private PoiReportService poiReportService;

	@Autowired
	private RepReportCheckMapper repReportCheckMapper;
	

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());


	public Map<String, Object>  dataCheck(Map<String, Object> params,Map<String, Object> dataMap){
		Map<String, Object>resultMap=new HashMap<>();

		StringBuffer resultSb=new StringBuffer();

		String reportId = params.get("repid").toString();   //报表编号
		ReportInfo reportInfo = reportInfoMapper.getReportInfoByID(reportId);

		params.put("pprodId", params.get("pprodId")==null?params.get("pprodid"):params.get("pprodId"));
		params.put("prodid", params.get("prodid")==null?params.get("pprodid"):params.get("prodid"));  //单产品只传了pprodid
		params.put("prodId", params.get("prodId")==null?params.get("prodid"):params.get("prodId"));
		params.put("repId", params.get("repId")==null?params.get("repid"):params.get("repId"));

		RepReveal repReveal = poiReportService.getRepReveal(params);

		int index=1;
		String rptType = params.get("uri").toString();
		String prodId = StringUtil.isNotEmpty(repReveal.getProdid())?repReveal.getProdid():(StringUtil.isNotEmpty(params.get("prodid"))?params.get("prodid").toString():"");

		if (StringUtil.isNotEmpty(rptType)) {
			//对所有报告的通用校验
			//生成报告时，若存在某个统计项取不到值的情况，需要在本模块进行提示 提示“生成报告所需数据有缺失”
			for (String key:dataMap.keySet()){
				if(!key.contains("_xx")){
					if(dataMap.get(key) instanceof List){
						if (CollectionUtils.isEmpty((List)dataMap.get(key))) {
							if (!dataMap.containsKey(key+"_xx")) {
								if(dataMap.get(key) instanceof String){
									dataMap.put(key,"xxx");
								}
								resultSb.append((index++)+"："+"生成信披报告所需数据有缺失，请到【报表数据校验】功能查阅当前报表详情。<br>");
								break;
							}
						}
					}else{
						if (!StringUtil.isNotEmpty(dataMap.get(key))) {
							if (!dataMap.containsKey(key+"_xx")) {
								if(dataMap.get(key) instanceof String){
									dataMap.put(key,"xxx");
								}
								resultSb.append((index++)+"："+"生成信披报告所需数据有缺失，请到【报表数据校验】功能查阅当前报表详情。<br>");
								break;
							}
						}
					}
				}
			}

			if (StringUtil.equals(rptType,DisReportType.FXGG)) {
				//发行公告
				//产品募集金额只能是整数，不能出现角和分；
				if(StringUtil.isNotEmpty(dataMap.get("raiseAmtVal"))){
					BigDecimal raiseAmt=(BigDecimal)dataMap.get("raiseAmtVal");
					BigDecimal raiseAmt2=raiseAmt.setScale(0,BigDecimal.ROUND_DOWN);
					if(!(raiseAmt2.compareTo(raiseAmt)==0)){
						resultSb.append((index++)+"："+"产品募集金额只能是整数"+"<br>");
					}
				}

			}else if (StringUtil.equals(rptType,DisReportType.KFJZJFHGG_BNYS)
					||StringUtil.equals(rptType,DisReportType.KFJZJFHGG_BNYN)){
				//	开放、净值及分红公告
				//分红权益登记日一定是对应产品某一开放周期的开放结束日（根据产品排期计划判断）或产品的到期日（根据产品分红到期计划判断）
				String baseDate=(String) dataMap.get("baseDate");
				if(poiReportMapper.checkBaseDate(prodId,baseDate)<1){
					resultSb.append((index++)+"："+"分红权益登记日一定是对应产品某一开放周期的开放结束日"+"<br>");
				}

			}else if (StringUtil.equals(rptType,DisReportType.JZJFHBG_SMKF)){
				//净值及分红报告
				//分红权益登记日一定是对应产品某一开放周期的开放结束日（根据产品排期计划判断）或产品的到期日（根据产品分红到期计划判断）
				String settDate=(String) dataMap.get("settDate");
				String pprodId=StringUtil.isNotEmpty(params.get("pprodId"))?params.get("pprodId").toString():dataMap.get("pprodId").toString();
				if(StringUtil.equals(params.get("opttype"),"batch")){
					pprodId=dataMap.get("pprodId").toString();
				}
				if(poiReportMapper.checkBaseDate(pprodId,settDate)<1){
					resultSb.append((index++)+"："+"分红权益登记日一定是对应产品某一开放周期的开放结束日"+"<br>");
				}
			}
			else if (StringUtil.equals(rptType,DisReportType.QSBG)){
				//清算报告 资产类合计=负债类合计；
				String sumAssetAmt=(String)dataMap.get("sumAssetAmt");
				String sumDebtAmt=(String)dataMap.get("sumDebtAmt");;
				if (!StringUtil.equals(sumAssetAmt,sumDebtAmt)) {
					resultSb.append((index++)+"："+"清算报告资产类合计不等于负债类合计"+"<br>");
				}
			}
			else if(StringUtil.equals(rptType,DisReportType.JDBG_ZSX)
					||StringUtil.equals(rptType,DisReportType.JDBG_FZSX)
					||StringUtil.equals(rptType,DisReportType.BNDBG_ZSX)
					||StringUtil.equals(rptType,DisReportType.BNDBG_FZSX)
					||StringUtil.equals(rptType,DisReportType.NDBG_ZSX)
					||StringUtil.equals(rptType,DisReportType.NDBG_FZSX)){
				//先把规则查询出来
				List<RepDataCheck>checkList=poiReportMapper.getRepDataCheckList();
				Map<String,List<RepDataCheck>>checkMap=checkList.stream().collect(JoyinCollectors.groupingBy(x->x.getCheckId()));
				//季报、半年报、年报
				//获取产品信息
				RepProdInfo prodInfo=poiReportMapper.getProdInfo(prodId);

				//获取报告期末产品资产组合情况
				Map<String,Object> prodAssetComb=(Map<String,Object>)dataMap.get("prodAssetComb");

				if(StringUtil.isNotEmpty(prodInfo)){
					//	当产品的“投资性质”为“固定收益类”时，【报告期末产品资产组合情况】-固定收益类穿透前及穿透后占比必须大于等于80%； 100000001
					if(StringUtil.equals(prodInfo.getNatureInvest(), DictCodeListExt5.信披投资性质_固定收益类.getValue())){
						RepDataCheck repDataCheck=checkMap.get("100000001").get(0);
						String tips1="当产品的“投资性质”为“固定收益类”时，【报告期末产品资产组合情况】-固定收益类穿透前占比";
						//穿透前
						if(StringUtil.isNotEmpty(prodAssetComb.get("fixPreRatio"))){
							BigDecimal fixPreRatio=(BigDecimal)prodAssetComb.get("fixPreRatio");
							String s1=this.singleCheck(repDataCheck,fixPreRatio,tips1);
							if (StringUtil.isNotEmpty(s1)) {
								resultSb.append((index++)+"："+s1+"<br>");
							}
						}else{
							resultSb.append((index++)+"："+"固定收益类穿透前占比未获取到"+"<br>");
						}
						//穿透后
						if(StringUtil.isNotEmpty(prodAssetComb.get("fixAfterRatio"))){
							String tips2="当产品的“投资性质”为“固定收益类”时，【报告期末产品资产组合情况】-固定收益类穿透后占比";
							BigDecimal fixAfterRatio=(BigDecimal)prodAssetComb.get("fixAfterRatio");
							String s2=this.singleCheck(repDataCheck,fixAfterRatio,tips2);
							if (StringUtil.isNotEmpty(s2)) {
								resultSb.append((index++)+"："+s2+"<br>");
							}
						}else{
							resultSb.append((index++)+"："+"固定收益类穿透后占比未获取到"+"<br>");
						}
					}
					//	当产品的“投资性质”为“权益类”时，【报告期末产品资产组合情况】-权益类穿透前及穿透后占比必须大于等于80%； 100000002
					else if(StringUtil.equals(prodInfo.getNatureInvest(), DictCodeListExt5.信披投资性质_权益类.getValue())){
						RepDataCheck repDataCheck=checkMap.get("100000002").get(0);
						//穿透前
						if(StringUtil.isNotEmpty(prodAssetComb.get("equityPreRatio"))){
							BigDecimal equityPreRatio=(BigDecimal)prodAssetComb.get("equityPreRatio");
							String tips1="当产品的“投资性质”为“权益类”时，【报告期末产品资产组合情况】-权益类穿透前占比";
							String s1=this.singleCheck(repDataCheck,equityPreRatio,tips1);
							if (StringUtil.isNotEmpty(s1)) {
								resultSb.append((index++)+"："+s1+"<br>");
							}
						}else{
							resultSb.append((index++)+"："+"权益类穿透前占比未获取到"+"<br>");
						}
						//穿透后
						if(StringUtil.isNotEmpty(prodAssetComb.get("equityAfterRatio"))){
							BigDecimal equityAfterRatio=(BigDecimal)prodAssetComb.get("equityAfterRatio");
							String tips2="当产品的“投资性质”为“权益类”时，【报告期末产品资产组合情况】-权益类穿透后占比";
							String s2=this.singleCheck(repDataCheck,equityAfterRatio,tips2);
							if (StringUtil.isNotEmpty(s2)) {
								resultSb.append((index++)+"："+s2+"<br>");
							}
						}else{
							resultSb.append((index++)+"："+"权益类穿透后占比未获取到"+"<br>");
						}

					}
					//	当产品的“投资性质”为“商品及衍生品类”时，【报告期末产品资产组合情况】-商品及衍生品类穿透前及穿透后占比必须大于等于80%； 100000003
					else if(StringUtil.equals(prodInfo.getNatureInvest(), DictCodeListExt5.信披投资性质_商品及金融衍生品类.getValue())){
						RepDataCheck repDataCheck=checkMap.get("100000003").get(0);
						//穿透前
						if(StringUtil.isNotEmpty(prodAssetComb.get("fdPreRatio"))){
							BigDecimal fdPreRatio=(BigDecimal)prodAssetComb.get("fdPreRatio");
							String tips1="当产品的“投资性质”为“商品及衍生品类”时，【报告期末产品资产组合情况】-商品及衍生品类穿透前占比";
							String s1=this.singleCheck(repDataCheck,fdPreRatio,tips1);
							if (StringUtil.isNotEmpty(s1)) {
								resultSb.append((index++)+"："+s1+"<br>");
							}
						}else{
							resultSb.append((index++)+"："+"商品及衍生品类穿透前占比未获取到"+"<br>");
						}

						//穿透后
						if(StringUtil.isNotEmpty(prodAssetComb.get("fdAfterRatio"))){
							BigDecimal fdAfterRatio=(BigDecimal)prodAssetComb.get("fdAfterRatio");
							String tips2="当产品的“投资性质”为“商品及衍生品类”时，【报告期末产品资产组合情况】-商品及衍生品类穿透后占比";
							String s2=this.singleCheck(repDataCheck,fdAfterRatio,tips2);
							if (StringUtil.isNotEmpty(s2)) {
								resultSb.append((index++)+"："+s2+"<br>");
							}
						}else{
							resultSb.append((index++)+"："+"商品及衍生品类穿透后占比未获取到"+"<br>");
						}
					}
					//	当产品的“投资性质”为“混合类”时，【报告期末产品资产组合情况】-固定收益类、权益类、商品及衍生品类穿透前及穿透后占比必须小于80%； 100000004
					else if(StringUtil.equals(prodInfo.getNatureInvest(), DictCodeListExt5.信披投资性质_混合类.getValue())){
						RepDataCheck repDataCheck=checkMap.get("100000004").get(0);

						//穿透前
						if(StringUtil.isNotEmpty(prodAssetComb.get("fixPreRatio"))){
							BigDecimal fixPreRatio=(BigDecimal)prodAssetComb.get("fixPreRatio");
							String tips1="当产品的“投资性质”为“混合类”时，【报告期末产品资产组合情况】-固定收益类穿透前占比";
							String s1=this.singleCheck(repDataCheck,fixPreRatio,tips1);
							if (StringUtil.isNotEmpty(s1)) {
								resultSb.append((index++)+"："+s1+"<br>");
							}
						}else{
							resultSb.append((index++)+"："+"固定收益类穿透前占比未获取到"+"<br>");
						}
						//穿透后
						if(StringUtil.isNotEmpty(prodAssetComb.get("fixAfterRatio"))){
							BigDecimal fixAfterRatio=(BigDecimal)prodAssetComb.get("fixAfterRatio");
							String tips2="当产品的“投资性质”为“混合类”时，【报告期末产品资产组合情况】-固定收益类穿透后占比";
							String s2=this.singleCheck(repDataCheck,fixAfterRatio,tips2);
							if (StringUtil.isNotEmpty(s2)) {
								resultSb.append((index++)+"："+s2+"<br>");
							}
						}else{
							resultSb.append((index++)+"："+"固定收益类穿透后占比未获取到"+"<br>");
						}

						//穿透前
						if(StringUtil.isNotEmpty(prodAssetComb.get("equityPreRatio"))){
							BigDecimal equityPreRatio=(BigDecimal)prodAssetComb.get("equityPreRatio");
							String tips1="当产品的“投资性质”为“混合类”时，【报告期末产品资产组合情况】-权益类穿透前占比";
							String s1=this.singleCheck(repDataCheck,equityPreRatio,tips1);
							if (StringUtil.isNotEmpty(s1)) {
								resultSb.append((index++)+"："+s1+"<br>");
							}
						}else{
							resultSb.append((index++)+"："+"权益类穿透前占比未获取到"+"<br>");
						}
						//穿透后
						if(StringUtil.isNotEmpty(prodAssetComb.get("equityAfterRatio"))){
							BigDecimal equityAfterRatio=(BigDecimal)prodAssetComb.get("equityAfterRatio");
							String tips2="当产品的“投资性质”为“混合类”时，【报告期末产品资产组合情况】-权益类穿透后占比";
							String s2=this.singleCheck(repDataCheck,equityAfterRatio,tips2);
							if (StringUtil.isNotEmpty(s2)) {
								resultSb.append((index++)+"："+s2+"<br>");
							}
						}else{
							resultSb.append((index++)+"："+"权益类穿透后占比未获取到"+"<br>");
						}

						//穿透前
						if(StringUtil.isNotEmpty(prodAssetComb.get("fdPreRatio"))){
							BigDecimal fdPreRatio=(BigDecimal)prodAssetComb.get("fdPreRatio");
							String tips1="当产品的“投资性质”为“混合类”时，【报告期末产品资产组合情况】-商品及衍生品类穿透前占比";
							String s1=this.singleCheck(repDataCheck,fdPreRatio,tips1);
							if (StringUtil.isNotEmpty(s1)) {
								resultSb.append((index++)+"："+s1+"<br>");
							}
						}else{
							resultSb.append((index++)+"："+"商品及衍生品类穿透前占比未获取到"+"<br>");
						}
						if(StringUtil.isNotEmpty(prodAssetComb.get("fdAfterRatio"))){
							//穿透后
							BigDecimal fdAfterRatio=(BigDecimal)prodAssetComb.get("fdAfterRatio");
							String tips2="当产品的“投资性质”为“混合类”时，【报告期末产品资产组合情况】-商品及衍生品类穿透后占比";
							String s2=this.singleCheck(repDataCheck,fdAfterRatio,tips2);
							if (StringUtil.isNotEmpty(s2)) {
								resultSb.append((index++)+"："+s2+"<br>");
							}
						}else{
							resultSb.append((index++)+"："+"商品及衍生品类穿透后占比未获取到"+"<br>");
						}

					}
					//	当“产品类型”为“现管类”时，【报告期末产品资产组合情况】-权益类穿透前及穿透后占比必须等于0% 100000009
					if(StringUtil.equals(prodInfo.getProdType(),DictCodeListExt5.信披产品类型_现管类产品.getValue())){
						RepDataCheck repDataCheck2=checkMap.get("100000009").get(0);
						//穿透前
						if(StringUtil.isNotEmpty(prodAssetComb.get("equityPreRatio"))){
							BigDecimal equityPreRatio=(BigDecimal)prodAssetComb.get("equityPreRatio");
							String tips1="当“产品类型”为“现管类”时，【报告期末产品资产组合情况】-权益类穿透前占比";
							String s1=this.singleCheck(repDataCheck2,equityPreRatio,tips1);
							if (StringUtil.isNotEmpty(s1)) {
								resultSb.append((index++)+"："+s1+"<br>");
							}
						}else{
							resultSb.append((index++)+"："+"权益类穿透前占比未获取到"+"<br>");
						}
						//穿透后
						if(StringUtil.isNotEmpty(prodAssetComb.get("equityAfterRatio"))){
							BigDecimal equityAfterRatio=(BigDecimal)prodAssetComb.get("equityAfterRatio");
							String tips2="当“产品类型”为“现管类”时，【报告期末产品资产组合情况】-权益类穿透后占比";
							String s2=this.singleCheck(repDataCheck2,equityAfterRatio,tips2);
							if (StringUtil.isNotEmpty(s2)) {
								resultSb.append((index++)+"："+s2+"<br>");
							}
						}else{
							resultSb.append((index++)+"："+"权益类穿透后占比未获取到"+"<br>");
						}
					}

				}

					if(StringUtil.equals(rptType,DisReportType.BNDBG_ZSX)
						||StringUtil.equals(rptType,DisReportType.BNDBG_FZSX)
						||StringUtil.equals(rptType,DisReportType.NDBG_ZSX)
						||StringUtil.equals(rptType,DisReportType.NDBG_FZSX)){
						//半年报、年报：
						//TODO:
						//	当产品的募集方式为“公募”，发行方式为“开放式”时，“报告期末产品杠杆水平”小于等于140； 100000005
						if(StringUtil.equals(prodInfo.getRaiseType(),DictCodeListExt5.信披募集方式_公募.getValue())
						&&StringUtil.equals(prodInfo.getIssuingWay(),DictCodeListExt5.发行方式_开放式.getValue())){
							RepDataCheck repDataCheck=checkMap.get("100000005").get(0);
							if(StringUtil.isNotEmpty(dataMap.get("leverage"))){
								BigDecimal leverage=(BigDecimal)dataMap.get("leverage");
								String tips1="当产品的募集方式为“公募”，发行方式为“开放式”时，“报告期末产品杠杆水平”";
								String s1=this.singleCheck(repDataCheck,leverage,tips1);
								if (StringUtil.isNotEmpty(s1)) {
									resultSb.append((index++)+"："+s1+"<br>");
								}
							}else {
								resultSb.append((index++)+"："+"报告期末产品杠杆水平未获取到"+"<br>");
							}

						}

						//	当“产品类型”为“现管类产品”时，“报告期末产品杠杆水平”小于等于120； 100000006
						if(StringUtil.equals(prodInfo.getProdType(),DictCodeListExt5.信披产品类型_现管类产品.getValue())){
							RepDataCheck repDataCheck=checkMap.get("100000006").get(0);
							if(StringUtil.isNotEmpty(dataMap.get("leverage"))){
								BigDecimal leverage=(BigDecimal)dataMap.get("leverage");
								String tips1="当“产品类型”为“现管类产品”时，“报告期末产品杠杆水平”";
								String s1=this.singleCheck(repDataCheck,leverage,tips1);
								if (StringUtil.isNotEmpty(s1)) {
									resultSb.append((index++)+"："+s1+"<br>");
								}
							}else {
								resultSb.append((index++)+"："+"报告期末产品杠杆水平未获取到"+"<br>");
							}

						}

						//	当产品的募集方式为“私募”且“是否分级产品”为“否”或产品募集方式为“公募”且发行方式为“封闭式”时，“报告期末产品杠杆水平”小于等于200； 100000007
						if((StringUtil.equals(prodInfo.getRaiseType(),DictCodeListExt5.信披募集方式_公募.getValue())
								&&StringUtil.equals(prodInfo.getIssuingWay(),DictCodeListExt5.发行方式_封闭式.getValue())
						||(StringUtil.equals(prodInfo.getRaiseType(),DictCodeListExt5.信披募集方式_私募.getValue())
								&&StringUtil.equals(prodInfo.getIsGrade(), DictCodeList.是否_中债_否.getValue())))){
							RepDataCheck repDataCheck=checkMap.get("100000007").get(0);
							if(StringUtil.isNotEmpty(dataMap.get("leverage"))){
								BigDecimal leverage=(BigDecimal)dataMap.get("leverage");
								String tips1="当产品的募集方式为“私募”且“是否分级产品”为“否”或产品募集方式为“公募”且发行方式为“封闭式”时，“报告期末产品杠杆水平”";
								String s1=this.singleCheck(repDataCheck,leverage,tips1);
								if (StringUtil.isNotEmpty(s1)) {
									resultSb.append((index++)+"："+s1+"<br>");
								}
							}else {
								resultSb.append((index++)+"："+"报告期末产品杠杆水平未获取到"+"<br>");
							}
						}

						//	当产品募集方式为“私募”且“是否是分级产品”为“是”的时候，“报告期末产品杠杆水平”小于等于140； 100000008
						if(StringUtil.equals(prodInfo.getRaiseType(),DictCodeListExt5.信披募集方式_私募.getValue())
								&&StringUtil.equals(prodInfo.getIsGrade(), DictCodeList.是否_中债_是.getValue())){
							RepDataCheck repDataCheck=checkMap.get("100000008").get(0);
							if(StringUtil.isNotEmpty(dataMap.get("leverage"))){
								BigDecimal leverage=(BigDecimal)dataMap.get("leverage");
								String tips1="当产品募集方式为“私募”且“是否是分级产品”为“是”的时候，“报告期末产品杠杆水平”";
								String s1=this.singleCheck(repDataCheck,leverage,tips1);
								if (StringUtil.isNotEmpty(s1)) {
									resultSb.append((index++)+"："+s1+"<br>");
								}
							}else {
								resultSb.append((index++)+"："+"报告期末产品杠杆水平未获取到"+"<br>");
							}
						}
					}

					//对于定期报告中需要从【报表指标管理】或“合作机构信息维护”、“产品资产组合情况信息维护”、“非标债权类资产明细信息维护”“关联交易情况信息维护”中取值的统计项，
					// 若在生成报告时，上述内容取不到值或无有效数据（系统生成报告的日期在数据的生效期及有效截止日期之间），也需要在本界面生成一条对应数据项为空的提示；
					//TODO:
				if(CollectionUtils.isEmpty((List)dataMap.get("listNostds"))){
					resultSb.append((index++)+"："+"非标债权类资产明细信息缺失<br>");
				}
				if(((Map<String,Object>)dataMap.get("repRelDeal")).isEmpty()){
					resultSb.append((index++)+"："+"关联交易情况信息缺失<br>");
				}
				if(prodAssetComb.isEmpty()){
					resultSb.append((index++)+"："+"产品资产组合情况信息缺失<br>");
				}
				if(!StringUtil.isNotEmpty(dataMap.get("partner"))){
					resultSb.append((index++)+"："+"合作机构信息缺失<br>");
				}

				if(StringUtil.equals(rptType,DisReportType.JDBG_ZSX)
						||StringUtil.equals(rptType,DisReportType.JDBG_FZSX)){
					if(!StringUtil.isNotEmpty(dataMap.get("tzclhyzfx"))
							||!StringUtil.isNotEmpty(dataMap.get("ldxfxfx"))){
						resultSb.append((index++)+"："+"报表指标信息缺失<br>");
					}
				}else{
					if(!StringUtil.isNotEmpty(dataMap.get("tzclhyzfx"))
							||!StringUtil.isNotEmpty(dataMap.get("ldxfxfx"))
							||!StringUtil.isNotEmpty(dataMap.get("tgrbg1"))
							||!StringUtil.isNotEmpty(dataMap.get("tgrbg2"))
							||!StringUtil.isNotEmpty(dataMap.get("tgrbg3"))){
						resultSb.append((index++)+"："+"报表指标信息缺失<br>");
					}
				}
 			}

			//校验完成之后判断有没有错误
			if(StringUtil.isNotEmpty(resultSb)){
				//生成文件
				UploadFile uploadFile=poiReportService.genReportFileForCheck(dataMap);

				RepReportCheck repReportCheck=new RepReportCheck();

				repReportCheck.setReportCheckId(IDGenerator.generate(32));
				repReportCheck.setRepId(reportId);
				repReportCheck.setReportType(reportInfo.getReportCategory());
				repReportCheck.setReportName(reportInfo.getReportName());
				repReportCheck.setProdId(prodId);
				repReportCheck.setCheckDate((Date)params.get("platformDate"));
				repReportCheck.setEndDate(repReveal.getEndDate());
				repReportCheck.setErrormsg(resultSb.toString().replace("<br>",""));
				repReportCheck.setFileNo(uploadFile.getFileNo());
				repReportCheck.setDataStatus("E");

				//入库REP_REPORT_CHECK
				//数据全删全插
				poiReportMapper.deleteCheckData(repReportCheck);

				repReportCheckMapper.insertSelective(repReportCheck);

				//resultSb.append("<br>请到“报表数据检验窗口”，查询当前报表详情。");
				resultMap.put("resultSb",resultSb.toString());
			}
		}

		return resultMap;
	}

	private String singleCheck(RepDataCheck repDataCheck,BigDecimal val,String tips){
		String result="";
		if(StringUtil.equals(repDataCheck.getCheckType(),DictCodeListExt5.信披数据校验比较符_小于.getValue())){
			BigDecimal compareVal=new BigDecimal(repDataCheck.getParamValue1());
			if(val.compareTo(compareVal)>=0){
				result=tips+"必须小于"+repDataCheck.getParamValue1();
			}
		} else if (StringUtil.equals(repDataCheck.getCheckType(),DictCodeListExt5.信披数据校验比较符_小于等于.getValue())) {
			BigDecimal compareVal=new BigDecimal(repDataCheck.getParamValue1());
			if(val.compareTo(compareVal)>0){
				result=tips+"必须小于等于"+repDataCheck.getParamValue1();
			}
		}else if (StringUtil.equals(repDataCheck.getCheckType(),DictCodeListExt5.信披数据校验比较符_大于.getValue())) {
			BigDecimal compareVal=new BigDecimal(repDataCheck.getParamValue1());
			if(val.compareTo(compareVal)<=0){
				result=tips+"必须大于"+repDataCheck.getParamValue1();
			}
		}else if (StringUtil.equals(repDataCheck.getCheckType(),DictCodeListExt5.信披数据校验比较符_大于等于.getValue())) {
			BigDecimal compareVal=new BigDecimal(repDataCheck.getParamValue1());
			if(val.compareTo(compareVal)<0){
				result=tips+"必须大于等于"+repDataCheck.getParamValue1();
			}
		}else if (StringUtil.equals(repDataCheck.getCheckType(),DictCodeListExt5.信披数据校验比较符_范围控制_不包含临界值.getValue())) {
			BigDecimal paramValue1=new BigDecimal(repDataCheck.getParamValue1());
			BigDecimal paramValue2=new BigDecimal(repDataCheck.getParamValue2());
			if(!(val.compareTo(paramValue1)>0&&val.compareTo(paramValue2)<0)){
				result=tips+"不在区间"+repDataCheck.getParamValue1()+"（不含）,"+repDataCheck.getParamValue2()+"（不含）内";
			}

		}else if (StringUtil.equals(repDataCheck.getCheckType(),DictCodeListExt5.信披数据校验比较符_范围控制_包含较小的临界值.getValue())) {
			BigDecimal paramValue1=new BigDecimal(repDataCheck.getParamValue1());
			BigDecimal paramValue2=new BigDecimal(repDataCheck.getParamValue2());
			if(!(val.compareTo(paramValue1)>=0&&val.compareTo(paramValue2)<0)){
				result=tips+"不在区间"+repDataCheck.getParamValue1()+"（含）,"+repDataCheck.getParamValue2()+"（不含）内";
			}
		}else if (StringUtil.equals(repDataCheck.getCheckType(),DictCodeListExt5.信披数据校验比较符_范围控制_包含较大的临界值.getValue())) {
			BigDecimal paramValue1=new BigDecimal(repDataCheck.getParamValue1());
			BigDecimal paramValue2=new BigDecimal(repDataCheck.getParamValue2());
			if(!(val.compareTo(paramValue1)>0&&val.compareTo(paramValue2)<=0)){
				result=tips+"不在区间"+repDataCheck.getParamValue1()+"（不含）,"+repDataCheck.getParamValue2()+"（含）内";
			}
		}else if (StringUtil.equals(repDataCheck.getCheckType(),DictCodeListExt5.信披数据校验比较符_等于.getValue())) {
			BigDecimal compareVal=new BigDecimal(repDataCheck.getParamValue1());
			if(val.compareTo(compareVal)!=0){
				result=tips+"必须等于"+repDataCheck.getParamValue1();
			}
		}else if (StringUtil.equals(repDataCheck.getCheckType(),DictCodeListExt5.信披数据校验比较符_范围控制_同时包含较小和较大的临界值.getValue())) {
			BigDecimal paramValue1=new BigDecimal(repDataCheck.getParamValue1());
			BigDecimal paramValue2=new BigDecimal(repDataCheck.getParamValue2());
			if(!(val.compareTo(paramValue1)>=0&&val.compareTo(paramValue2)<=0)){
				result=tips+"不在区间"+repDataCheck.getParamValue1()+"（含）,"+repDataCheck.getParamValue2()+"（含）内";
			}
		}

		return result;
	}

	public String statusCheck(Map<String, Object> params){
		params.put("repId", params.get("repId")==null?params.get("repid"):params.get("repId"));
		//
		int count = reportInfoMapper.getRepInfoStatus(params);
		if (count<1) {
			return "启用状态为初始化的报表,无法生成该报表";
		}
		return null;
	}

}
