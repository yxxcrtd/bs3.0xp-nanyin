package com.joyintech.tams.disclosure.busicommon.rptdata.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.RepExtConusMapper;
import com.joyintech.tams.common.base.entity.DisNostdCreditor;
import com.joyintech.tams.common.base.entity.DisProdAsset;
import com.joyintech.tams.common.base.entity.DisProdPlanInfo;
import com.joyintech.tams.common.base.entity.RepAssetsComb;
import com.joyintech.tams.common.base.entity.RepExtConus;
import com.joyintech.tams.common.base.entity.RepExtConusKey;
import com.joyintech.tams.common.base.entity.RepIndexSub;
import com.joyintech.tams.common.base.entity.RepInvestAccount;
import com.joyintech.tams.common.base.entity.RepJobRange;
import com.joyintech.tams.common.base.entity.RepProdBasicChange;
import com.joyintech.tams.common.base.entity.RepProdIndex;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.RepRelDeal;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.busicommon.common.util.CollectionUtils;
import com.joyintech.tams.disclosure.busicommon.common.util.DurationUtil;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisReportType;
import com.joyintech.tams.disclosure.busicommon.rptdata.dao.DurationReportMapper;
import com.joyintech.tams.disclosure.busicommon.rptdata.dto.DurationReportDto;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;

/**
 * 存续期报告-数据获取服务
 * @author chenxc
 * @version 1.0 2022年07月20日
 * @since 1.0
 */
@Service
public class DurationReportDataService implements IReportDataService {

	@Autowired
	private DurationReportMapper durationReportMapper;
	
	@Autowired
	private RepExtConusMapper repExtConusMapper;
	
	@Autowired
	private HolidayService holidayService;
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(DurationReportDataService.class);

	@Override
	public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {

		List<Map<String, Object>> listResult = new ArrayList<>();

		try {
			DurationReportDto reportDto = new DurationReportDto();
			DecimalFormat df = new DecimalFormat("#,##0.00");
			//报告参数整理
			int year = 2022;
			int quarter = 2;
			int halfYear =1;
			Date currDate = new Date();
			Date reportDate = new Date();
			String rptType = paraMap.get("uri").toString();
			try {

				year = Integer.parseInt(paraMap.get("year").toString());
				currDate = (Date)(paraMap.get("platformDate"));
				
				if ( rptType.equals(DisReportType.JDBG_ZSX) || rptType.equals(DisReportType.JDBG_FZSX)) {  //季报
					quarter= Integer.parseInt(paraMap.get("quarter").toString());
					reportDate = DurationUtil.getQuarterEnd(year, quarter);
				} else if ( rptType.equals(DisReportType.BNDBG_ZSX) || rptType.equals(DisReportType.BNDBG_FZSX) ) {  //半年报
					halfYear= Integer.parseInt(paraMap.get("halfyear").toString());
					reportDate = DurationUtil.getHalfYearEnd(year,halfYear);
				} else if ( rptType.equals(DisReportType.NDBG_ZSX) || rptType.equals(DisReportType.NDBG_FZSX) ) { //年报  
					reportDate = DurationUtil.getYearEnd(year);
				}
				paraMap.put("reportDate", reportDate);
				//paraMap.put("prodId",paraMap.get("pprodid"));   //定期报告维度：母产品或者单产品
			} catch (Exception e ) {
				logger.error("报表参数错误.");
				//e.printStackTrace();
			}

			//报表基本产品信息，母产品维度
			try {
				DurationReportDto dto= durationReportMapper.selectProdInfo(paraMap);
				if ( DateUtil.getBetweenDayNumber(dto.geteDate(),reportDate)<=90 || DateUtil.getBetweenDayNumber(reportDate, dto.getmDate())<=90 )  //不需要生成定期报表
					return null;
				
				if ( dto!=null ) {
					reportDto = dto; 
				}
				
			} catch (Exception e) {
				logger.error("报表产品基本信息错误.",e);
			}
			
			//报表时间信息
			try {
				reportDto.setYear(year);
				reportDto.setHalfYear(halfYear);
				reportDto.setQuarter(quarter);
				reportDto.setReportDate(reportDate);
				reportDto.setGenDate(currDate);
				
				if ( rptType.equals(DisReportType.JDBG_ZSX) || rptType.equals(DisReportType.JDBG_FZSX)) {  //季报
					reportDto.setDuration(DurationUtil.getQuarterDuration(year, quarter, reportDto.geteDate()));
					reportDto.setDurStartDate(DurationUtil.getQuarterStart(year, quarter));
					reportDto.setDurEndDate(DurationUtil.getQuarterEnd(year, quarter));
				} else if ( rptType.equals(DisReportType.BNDBG_ZSX) || rptType.equals(DisReportType.BNDBG_FZSX) ) {  //半年报
					reportDto.setDuration(DurationUtil.getHalfYearDuration(year, halfYear, reportDto.geteDate()));
					reportDto.setDurStartDate(DurationUtil.getHalfYearStart(year, halfYear));
					reportDto.setDurEndDate(DurationUtil.getHalfYearEnd(year, halfYear));
				} else if ( rptType.equals(DisReportType.NDBG_ZSX) || rptType.equals(DisReportType.NDBG_FZSX) ) { //年报  
					reportDto.setDuration(DurationUtil.getYearDuration(year, reportDto.geteDate()));
					reportDto.setDurStartDate(DurationUtil.getYearStart(year));
					reportDto.setDurEndDate(DurationUtil.getYearEnd(year));
				}						
			} catch ( Exception e ) {
				logger.error("报表区间数据错误.",e);
			}
			
			//财务指标：子产品维度
			try {
				List<RepProdIndex> listDto = durationReportMapper.selectFinIndex(paraMap);
				List<Map<String,Object>> listFinIndex = new ArrayList<>();
				listDto.forEach(item->{
					item.setAssetsValue(item.getAssetsValue()==null?item.getAssetsValue():item.getAssetsValue().setScale(2,BigDecimal.ROUND_DOWN));
					item.setUnitValue(item.getUnitValue()==null? item.getUnitValue():item.getUnitValue().setScale(4,BigDecimal.ROUND_DOWN));
					item.setUnitValueT(item.getUnitValueT()==null?item.getUnitValueT():item.getUnitValueT().setScale(4,BigDecimal.ROUND_DOWN));
					item.setOtherFee(item.getOtherFee()==null? item.getOtherFee():item.getOtherFee().setScale(2,BigDecimal.ROUND_DOWN));
					Map<String, Object> map = CollectionUtils.bean2Map(item);
					if ( item.getAssetsValue()!=null )
						map.put("assetsValueStr", df.format(item.getAssetsValue()));
					else
						map.put("assetsValueStr", "");
					if ( item.getOtherFee()!=null )
						map.put("otherFeeStr", df.format(item.getOtherFee()));
					else
						map.put("otherFeeStr", "");					
					listFinIndex.add(map);
					
				});
				reportDto.setListFinIndex(listFinIndex);
			} catch (Exception e) {
				logger.error("报表财务指标信息错误.",e);		
			}
			
			//业绩基准：子产品维度
			List<Map<String,Object>> listAchieveBasis = new ArrayList<>();
			List<RepProdInfo> listProdChildren = durationReportMapper.selectProdChildren(paraMap);
			for (RepProdInfo child:listProdChildren ) {
				Map<String,Object> mapChild = new HashMap<String,Object>();
				mapChild.put("prodId", child.getProdId());
				List<Map<String,Object>> listBasis = new ArrayList<>();
				if ( rptType.equals(DisReportType.JDBG_ZSX) || rptType.equals(DisReportType.JDBG_FZSX)) {  //季报
					mapChild.put("duration",DurationUtil.getQuarterDuration(year, quarter, child.getSetDate()));
					Date durStartDate = DurationUtil.getQuarterStart(year, quarter);
					Date durEndDate = DurationUtil.getQuarterEnd(year, quarter);
					listBasis = this.calcAchivementBasis3(child,durStartDate,durEndDate);
				} else if ( rptType.equals(DisReportType.BNDBG_ZSX) || rptType.equals(DisReportType.BNDBG_FZSX) ) {  //半年报
					mapChild.put("duration",DurationUtil.getHalfYearDuration(year, halfYear, child.getSetDate()));
					Date durStartDate = DurationUtil.getHalfYearStart(year, halfYear);
					Date durEndDate = DurationUtil.getHalfYearEnd(year, halfYear);
					listBasis = this.calcAchivementBasis3(child,durStartDate,durEndDate);
				} else if ( rptType.equals(DisReportType.NDBG_ZSX) || rptType.equals(DisReportType.NDBG_FZSX) ) { //年报  
					mapChild.put("duration",DurationUtil.getYearDuration(year, child.getSetDate()));
					Date durStartDate = DurationUtil.getYearStart(year);
					Date durEndDate = DurationUtil.getYearEnd(year);
					listBasis = this.calcAchivementBasis3(child,durStartDate,durEndDate);
				}				
				listAchieveBasis.addAll(listBasis);
			}
			reportDto.setListAchieveBasis(listAchieveBasis);
			
			//报告指标
			try {
				//区间内有效指标
				List<RepIndexSub> listRepIndex = durationReportMapper.selectReportIndex (paraMap);  
				List<RepIndexSub> listEffRepIndex = new ArrayList<>(); 
				//找到符合本产品的有效指标
				String prodId = paraMap.get("prodId").toString();
				for ( RepIndexSub idx:listRepIndex ) { 
					if ( isEffIndex(prodId,idx) )
						listEffRepIndex.add(idx);
				}
				if ( listEffRepIndex!=null && listEffRepIndex.size()>0 ) {  //一般只有一条
					for ( RepIndexSub item:listEffRepIndex ) {
						if ( "tzclhyzfx".equals(item.getPriId()) ) {
							reportDto.setTzclhyzfx( item.getIndexValue() );
						}
						if ( "ldxfxfx".equals(item.getPriId()) ) {
							reportDto.setLdxfxfx( item.getIndexValue() );
						}
						if ( "tgrbg1".equals(item.getPriId()) ) {
							reportDto.setTgrbg1( item.getIndexValue() );
						}
						if ( "tgrbg2".equals(item.getPriId()) ) {
							reportDto.setTgrbg2( item.getIndexValue() );
						}
						if ( "tgrbg3".equals(item.getPriId()) ) {
							reportDto.setTgrbg3( item.getIndexValue() );
						}												
					}
				} 
				
				//报告期内产品的业绩表现
				//1.1净值型产品：取“产品基础指标表”半年末/年末最后一天的产品单位净值，保留4位小数；
				//1.2现管类产品：固定为1.0000】元。
				//2.产品是分级产品：
				//本产品Z10002【子产品代码1】份额净值为1.0108【产品基础指标：子产品1期末单位净值，保留4位小数】元,Z10030【子产品代码2】份额净值为1.0109【子产品2期末单位净值，保留4位小数】元。】
				try {
					String perVal = "";
					if ( "01".equals(reportDto.getProdType()) ) { //净值型
						if ( !"02".equals(reportDto.getRepProdType()) ) {  //母产品，循环每个子产品
							perVal = "";
							for ( Map<String,Object> map:reportDto.getListFinIndex() ) {
								//if ( map.get("unitValue")==null  ) continue;
								perVal += map.get("prodId")+"份额净值为"+ (map.get("unitValue")==null? "0.00":map.get("unitValue").toString())+"元，";
							}
							perVal = perVal.substring(0, perVal.length()-2);
						} else {
							perVal = "份额净值为" + reportDto.getListFinIndex().get(0).get("unitValue").toString();
						}
					} else {  //现管类：固定为1.0000
						if ( !"02".equals(reportDto.getRepProdType()) ) {  //母产品，循环每个子产品
							perVal = "";
							for ( Map<String,Object> map:reportDto.getListFinIndex() ) {
								perVal += map.get("prodId")+"份额净值为"+"1.0000"+"元，";
							}
							perVal = perVal.substring(0, perVal.length()-2);
						} else {
							perVal = "份额净值为" + String.valueOf(BigDecimal.ONE.setScale(4,BigDecimal.ROUND_DOWN));
						}
					}
					reportDto.setYjbx(perVal);
				} catch (Exception e ) {
					logger.error("报表业绩表现数据错误。",e);
					reportDto.setYjbx("");
				}
				
			} catch (Exception e) {
				logger.error("报表指标参数信息错误.");
				e.printStackTrace();				
			}
			
			//产品资产组合
			try {
				List<DisProdAsset> listAssetComb = durationReportMapper.selectProdAssetComb (paraMap);	
				listAssetComb.forEach(item->{
					item.setEquityAfterRatio(item.getEquityAfterRatio().setScale(2,BigDecimal.ROUND_DOWN));
					item.setEquityPreRatio(item.getEquityPreRatio().setScale(2,BigDecimal.ROUND_DOWN));
					item.setFixAfterRatio(item.getFixAfterRatio().setScale(2,BigDecimal.ROUND_DOWN));
					item.setFixPreRatio(item.getFixPreRatio().setScale(2,BigDecimal.ROUND_DOWN));
					item.setFdAfterRatio(item.getFdAfterRatio().setScale(2,BigDecimal.ROUND_DOWN));
					item.setFdPreRatio(item.getFdPreRatio().setScale(2,BigDecimal.ROUND_DOWN));
					item.setMixAfterRatio(item.getMixAfterRatio().setScale(2,BigDecimal.ROUND_DOWN));
					item.setMixPreRatio(item.getMixPreRatio().setScale(2,BigDecimal.ROUND_DOWN));
				});		
				if ( listAssetComb!=null && listAssetComb.size()>0 )
					reportDto.setProdAssetComb( CollectionUtils.bean2Map(listAssetComb.get(0)));
				else
					reportDto.setProdAssetComb(new HashMap<>());
			} catch (Exception e) {
						logger.error("报表产品资产组合信息错误.");
						e.printStackTrace();				
			}
			
			//产品资产明细
			try {
				List<RepAssetsComb> listDto = durationReportMapper.selectProdAssetDetail (paraMap);
				List<Map<String,Object>> listAssetDetail = new ArrayList<>();
				int i=0;
				//取10条，但如果占比和公允价值的一样，则全部报出来
				BigDecimal pd = BigDecimal.ZERO;
				BigDecimal fv = BigDecimal.ZERO;
				for ( RepAssetsComb item:listDto ) {
					if ( i<10 ) {
						item.setPersonalField(++i+"");
						Map<String,Object> map = CollectionUtils.bean2Map(item);
						if ( item.getFairValue()!=null ) 
							map.put("fairValueStr", df.format(item.getFairValue()));
						else
							map.put("fairValueStr", "");
						if ( item.getPdProp()!=null ) 
							map.put("pdProp", df.format(item.getPdProp()));
						else
							map.put("pdProp", "");						
						listAssetDetail.add(map);
					} else {  //多出10条，如果值完全相同也要报出
						if ( item.getPdProp().equals(pd) && item.getFairValue().equals(fv) ) {
							item.setPersonalField(++i+"");
							Map<String,Object> map = CollectionUtils.bean2Map(item);
							if ( item.getFairValue()!=null )
								map.put("fairValueStr", df.format(item.getFairValue()));
							else
								map.put("fairValueStr", "");
							if ( item.getPdProp()!=null ) 
								map.put("pdProp", df.format(item.getPdProp()));
							else
								map.put("pdProp", "");							
							listAssetDetail.add(map);
						} else {
							break;
						}
					}
					pd = item.getPdProp();
					fv = item.getFairValue();
				}
				reportDto.setListAssetsDetail(listAssetDetail);
			}  catch (Exception e) {
				logger.error("报表产品资产投资明细信息错误.");
				e.printStackTrace();				
			}

			//非标资产
			try {
				List<DisNostdCreditor> listDto = durationReportMapper.selectNostdCreditor (paraMap);
				List<Map<String,Object>> listNostds = new ArrayList<>();
				int i=1;
				listDto.forEach(item->{
					listNostds.add(CollectionUtils.bean2Map(item));
				});						
				reportDto.setListNostds(listNostds);
			} catch (Exception e) {
				logger.error("产品投资非标资产明细信息数据错误。");
				e.printStackTrace();				
			}
			
			//产品投资账户
			try {
				List<RepInvestAccount> listDto = durationReportMapper.selectAccts (paraMap);
				List<Map<String,Object>> listInvestAccts = new ArrayList<>();
				int i=1;
				listDto.forEach(item->{
					listInvestAccts.add(CollectionUtils.bean2Map(item));
				});			
				reportDto.setListAccts(listInvestAccts);
			}  catch (Exception e) {
				logger.error("报表投资账户信息错误.");
				e.printStackTrace();				
			}
			
			//关联交易
			try {
				List<RepRelDeal> listRelDeal = durationReportMapper.selectRelDeals (paraMap);
				if ( listRelDeal!=null && listRelDeal.size()>0 ) {
					Map<String,Object> map = CollectionUtils.bean2Map(listRelDeal.get(0));
					RepRelDeal item = listRelDeal.get(0);
					//投资于关联方发行的证券、关联方作为融资人的非标准化债权类资产业务交易金额
					if (item.getCreditorAmt()!=null)
						map.put("creditorAmt", df.format(item.getCreditorAmt()));
					else {
						map.put("creditorAmt", "");
					}
					//投资于关联方承销的证券交易金额
					if (item.getBondAmt()!=null)
						map.put("bondAmt", df.format(item.getBondAmt()));
					else {
						map.put("bondAmt", "");
					}
					//与关联方作为交易对手开展的投融资业务交易金额
					if (item.getInvestAmt()!=null)
						map.put("investAmt", df.format(item.getInvestAmt()));
					else {
						map.put("investAmt", "");
					}
					//投资关联方作为管理人的资产管理产品交易金额
					if (item.getAssetAmt()!=null)
						map.put("assetAmt", df.format(item.getAssetAmt()));
					else {
						map.put("assetAmt", "");
					}
					//支付关联方托管费
					if (item.getTrustFee()!=null)
						map.put("trustFee", df.format(item.getTrustFee()));
					else {
						map.put("trustFee", "");
					}
					//支付关联方代销费
					if (item.getSellFee()!=null)
						map.put("sellFee", df.format(item.getSellFee()));
					else {
						map.put("sellFee", "");
					}
					//发生其他关联交易金额
					if (item.getOtherFee()!=null)
						map.put("otherFee", df.format(item.getOtherFee()));
					else {
						map.put("otherFee", "");
					}
					reportDto.setRepRelDeal(map);
				} else {
					reportDto.setRepRelDeal(new HashMap<>());
				}
				
			} catch (Exception e) {
				logger.error("报表关联交易信息错误.");
				e.printStackTrace();				
			}
			
			listResult.add(CollectionUtils.bean2Map(reportDto));
			
			//金额千分符转换
			//reportDto.setCapital(new BigDecimal("123456.789"));
			if ( reportDto.getCapital()!=null )
				listResult.get(0).put("capitalStr", df.format(reportDto.getCapital()) );
			else
				listResult.get(0).put("capitalStr", " " );
			
			//排除检查项
			listResult.get(0).put("endDate_xx", "");
			listResult.get(0).put("leverage_xx", "");
			listResult.get(0).put("capital_xx", "");
			listResult.get(0).put("prodId_xx", "");
			listResult.get(0).put("disDate_xx", "");
			listResult.get(0).put("signDate_xx", "");
			listResult.get(0).put("tzclhyzfx_xx", "");
			listResult.get(0).put("runDays_xx", "");
			listResult.get(0).put("tgrbg2_xx", "");
			listResult.get(0).put("tgrbg3_xx", "");
			listResult.get(0).put("tgrbg1_xx", "");
			listResult.get(0).put("reportTitle_xx", "");
			listResult.get(0).put("ldxfxfx_xx", "");
			listResult.get(0).put("startDate_xx", "");
			listResult.get(0).put("yjbx_xx", "");
			listResult.get(0).put("listFinIndex_xx", "");
			
		} catch (Exception e) {
			logger.error("定期报表数据查询错误", e);
		}

		return listResult;
	}

	/**
	 * 【（结束日期的指数行情/起始日期-1工作日的指数行情）-1】/（结束日期-起始日期）*365，截位法保留2位小数
	 */
	private BigDecimal calcAchivementBasis(RepProdInfo prod,Date durStartDate,Date durEndDate) {
		BigDecimal basisValue = BigDecimal.ZERO;
		
		try {
			Date sDate = durStartDate;
			Date eDate = durEndDate;
			
			//查询指定的产品在统计区间内的所有业绩基准
			Map<String,Object> mapParas = new HashMap<>();
			mapParas.put("prodId", prod.getpProdId());   //这里传入母产品id（P_PROD_ID)，业绩基准明细绑定在母产品上
			mapParas.put("sDate", sDate);
			mapParas.put("eDate",eDate);
			List<RepProdBasicChange> listBasis = durationReportMapper.selectAchievementBasis(mapParas);
			
			//查询指定的产品在统计区间内的有效业绩基准
			List<RepProdBasicChange> listEffBasis = new ArrayList<>();
			for ( RepProdBasicChange item:listBasis ) {
				if ( "E".equals(item.getStatus()) ) {
					listEffBasis.add(item);
				}
//				//更新日期在统计区间内，且状态为D，说明有变更
//				if ( item.getUpdateTime()!=null && item.getUpdateTime().compareTo(sDate)>=0 && item.getUpdateTime().compareTo(eDate)<=0 && "D".equals(item.getStatus()) ) {
//					changeFlag=true;
//				}
			}
			//查询是否在区间内有业绩基准变更，变更的判断规则是变更表的数据记录，与需求中的变更判断思路不一样，经过讨论，按变更履历表来
			//有无业绩基准变更的依据：查询边变更履历表的EFF_DATE在报表区间内的所有变更履历数据，全部为E的无变更，全部为D的无变更，有E且有D的为有变更；
			//采用EFF_DATE的依据是：需求说明，如果3月25日做了变更，但EFF_DATE是4月2日，仍然算第一季度无变更
			//程序中有无业绩基准变更，过滤listBasis状态为D的数据，如果条数减少，就是有变更
			//非指数变更为指数型产品，变更履历表里面只有E状态的数据，也算变更；
			//区间内是否有业绩基准变更的标志
			boolean changeFlag = false;
			if ( listEffBasis.size()>0 ) {  //区间内必须有E的数据，才有可能变更
				if ( listBasis.size()>listEffBasis.size()  ) {  //有E,D数据，有变更
					changeFlag=true;
				} else {  //相等，表示只有E的数据，
					if ( "1".equals(listEffBasis.get(0).getChangeType())) {  //可能由非指数型产品变更为指数型产品，算变更
						changeFlag = true;
					}
				}
			}
			
			
			//查询指定产品最新的业绩基准指数
			if ( "02".equals(prod.getIssuingWay()) ) {  //封闭式：max(产品成立日，统计周期开始日）
				if (prod.getSetDate().compareTo(sDate)>0) {
					sDate = prod.getSetDate();
				}
			} else if ( "01".equals(prod.getIssuingWay()) ) {  //开放式
				if ( changeFlag==true ) {  //无业绩基准变更：max(产品成立日，统计周期开始日）
					sDate = listEffBasis.get(0).getEffDate();  //最新的变更生效日
				} else {  //无业绩基准变更
					if (prod.getSetDate().compareTo(sDate)>0) {
						sDate = prod.getSetDate();
					}
				}
			}
			
			int days = DateUtil.getBetweenDayNumber(sDate,eDate);
			
			//查找指数行情
			for (RepProdBasicChange basis:listEffBasis ) {
				BigDecimal val = BigDecimal.ZERO;
				RepExtConusKey key = new RepExtConusKey();
				key.setDataType(basis.getDataType());
				key.setDataDate(holidayService.getPreWorkDate(sDate));
				RepExtConus sConus = repExtConusMapper.selectByPrimaryKey(key);
				if ( sConus==null || sConus.getMarketInfo()==null ) {
					logger.error("没有找到起始日前一工作日行情。");
					continue;
				}
				key.setDataDate(eDate);
				RepExtConus eConus = repExtConusMapper.selectByPrimaryKey(key);
				if ( eConus==null || eConus.getMarketInfo()==null ) {
					logger.error("没有找到起始日前一工作日行情。");
					continue;
				}
				val = ( eConus.getMarketInfo().divide(sConus.getMarketInfo(),20,BigDecimal.ROUND_DOWN ).subtract(BigDecimal.ONE) ).multiply(new BigDecimal(365));
				val = val.multiply(percent2BigDecimal(basis.getProportion())).divide(new BigDecimal(days),20,BigDecimal.ROUND_DOWN);
				basisValue = basisValue.add(val);
			}
			basisValue = new BigDecimal(100).multiply(basisValue).setScale(2, BigDecimal.ROUND_DOWN);
		} catch (Exception e) {
			logger.error("业绩比较基准计算错误！",e);
		}
		return basisValue;
	}
	
	/**
	 * 【（结束日期的指数行情/起始日期-1工作日的指数行情）-1】/（结束日期-起始日期）*365，截位法保留2位小数
	 */
	private BigDecimal calcAchivementBasis2(RepProdInfo prod,Date durStartDate,Date durEndDate) {
		BigDecimal basisValue = BigDecimal.ZERO;
		
		try {
			Date sDate = durStartDate;
			Date eDate = durEndDate;
			
			//查询指定的产品在统计区间结束日最近的一次有效的变更数据：
			Map<String,Object> mapParas = new HashMap<>();
			mapParas.put("prodId", prod.getpProdId());   //这里传入母产品id（P_PROD_ID)，业绩基准明细绑定在母产品上
			mapParas.put("sDate", sDate);
			mapParas.put("eDate",eDate);
			List<RepProdBasicChange> listBasis = durationReportMapper.selectAchievementBasis2(mapParas);
			
			//查询指定的产品在统计区间内的有效业绩基准
			List<RepProdBasicChange> listEffBasis = new ArrayList<>();
			if (listBasis==null || listBasis.size()<1 ) 
				return BigDecimal.ZERO;
			
			String groupId = listBasis.get(0).getGroupId();
			for ( RepProdBasicChange item:listBasis ) {
				if ( "E".equals(item.getStatus()) && groupId.equals(item.getGroupId())) { //有效状态，并且是同一组行情的
					listEffBasis.add(item);
				}
//				//更新日期在统计区间内，且状态为D，说明有变更
//				if ( item.getUpdateTime()!=null && item.getUpdateTime().compareTo(sDate)>=0 && item.getUpdateTime().compareTo(eDate)<=0 && "D".equals(item.getStatus()) ) {
//					changeFlag=true;
//				}
			}
			
			
			//查询是否在区间内有业绩基准变更，变更的判断规则是变更表的数据记录，与需求中的变更判断思路不一样，经过讨论，按变更履历表来
			//有无业绩基准变更的依据：查询边变更履历表的EFF_DATE在报表区间内的所有变更履历数据，全部为E的无变更，全部为D的无变更，有E且有D的为有变更；
			//采用EFF_DATE的依据是：需求说明，如果3月25日做了变更，但EFF_DATE是4月2日，仍然算第一季度无变更
			//程序中有无业绩基准变更，过滤listBasis状态为D的数据，如果条数减少，就是有变更
			//非指数变更为指数型产品，变更履历表里面只有E状态的数据，也算变更；
			//区间内是否有业绩基准变更的标志
			boolean changeFlag = false;
			if ( listBasis.get(0).getUpdateTime().compareTo(sDate)>0 )  //变更时间在本次统计区间内，则算有变更 
				changeFlag = true;
			
			//查询指定产品最新的业绩基准指数
			if ( "02".equals(prod.getIssuingWay()) ) {  //封闭式：max(产品成立日，统计周期开始日）
				if (prod.getSetDate().compareTo(sDate)>0) {
					sDate = prod.getSetDate();
				}
			} else if ( "01".equals(prod.getIssuingWay()) ) {  //开放式
				if ( changeFlag==true ) {  //无业绩基准变更：max(产品成立日，统计周期开始日）
					
					//计算变更生效日=变更日期后，最近的一次运作起始日
					Map <String,Object> p= new HashMap<String,Object>();
					p.put("startDate",listBasis.get(0).getUpdateTime());
					p.put("prodId",prod.getpProdId());
//					if ( "01".equals(prod.getRepProdType()) ) //主产品
//						p.put("prodId",prod.getProdId());
//					else
//						p.put("prodId",prod.getpProdId());  //单产品
					List<DisProdPlanInfo> listPlan = durationReportMapper.selectProdPlan(p);
					if ( listPlan==null || listPlan.size()<1)
						sDate = prod.getSetDate();
					else
						sDate = listPlan.get(0).getOperationStartDate();
				} else {  //无业绩基准变更
					if (prod.getSetDate().compareTo(sDate)>0) {
						sDate = prod.getSetDate();
					}
				}
			}
			
			int days = DateUtil.getBetweenDayNumber(sDate,eDate);
			
			//查找指数行情
			for (RepProdBasicChange basis:listEffBasis ) {
				BigDecimal val = BigDecimal.ZERO;
				RepExtConusKey key = new RepExtConusKey();
				key.setDataType(basis.getDataType());
				key.setDataDate(holidayService.getPreWorkDate(sDate));
				RepExtConus sConus = repExtConusMapper.selectByPrimaryKey(key);
				if ( sConus==null || sConus.getMarketInfo()==null ) {
					logger.error("没有找到起始日前一工作日行情。");
					continue;
				}
				key.setDataDate(eDate);
				RepExtConus eConus = repExtConusMapper.selectByPrimaryKey(key);
				if ( eConus==null || eConus.getMarketInfo()==null ) {
					logger.error("没有找到起始日前一工作日行情。");
					continue;
				}
				val = ( eConus.getMarketInfo().divide(sConus.getMarketInfo(),20,BigDecimal.ROUND_DOWN ).subtract(BigDecimal.ONE) ).multiply(new BigDecimal(365));
				val = val.multiply(percent2BigDecimal(basis.getProportion())).divide(new BigDecimal(days),20,BigDecimal.ROUND_DOWN);
				basisValue = basisValue.add(val);
			}
			basisValue = new BigDecimal(100).multiply(basisValue).setScale(2, BigDecimal.ROUND_DOWN);
		} catch (Exception e) {
			logger.error("业绩比较基准计算错误！",e);
		}
		return basisValue;
	}	
	
	public RepReveal getRepReveal(Map<String, Object> paraMap) {
		RepReveal repReveal = new RepReveal();

		//报告参数整理
		int year = 2022;
		int quarter = 2;
		int halfYear =1;
		Date sDate = new Date();
		Date eDate = new Date();
		
		String rptType = paraMap.get("uri").toString();
		try {
			year = paraMap.get("year")!=null?Integer.parseInt(paraMap.get("year").toString()):2022;
			if ( rptType.equals(DisReportType.JDBG_ZSX) || rptType.equals(DisReportType.JDBG_FZSX)) {  //季报
				quarter = Integer.parseInt(paraMap.get("quarter").toString());
				sDate = DurationUtil.getQuarterStart(year, quarter);
				eDate = DurationUtil.getQuarterEnd(year, quarter);
			} else if ( rptType.equals(DisReportType.BNDBG_ZSX) || rptType.equals(DisReportType.BNDBG_FZSX) ) {  //半年报
				halfYear = Integer.parseInt(paraMap.get("halfyear").toString());
				sDate = DurationUtil.getHalfYearStart(year, halfYear);
				eDate = DurationUtil.getHalfYearEnd(year, halfYear);
			} else if ( rptType.equals(DisReportType.NDBG_ZSX) || rptType.equals(DisReportType.NDBG_FZSX) ) { //年报  
				sDate = DurationUtil.getYearStart(year);
				eDate = DurationUtil.getYearEnd(year);
			}						
		} catch ( Exception e ) {
			logger.error("报表区间数据错误.",e);
			//e.printStackTrace();
		}
		repReveal.setStartDate(sDate);
		repReveal.setEndDate(eDate);
		return repReveal;
	}
	
	private boolean isEffIndex ( String prodId, RepIndexSub idx ) {
		List<RepJobRange> listRules = durationReportMapper.selectIndexRuleByJobId(idx.getId());
		StringBuffer sql = new StringBuffer();
		sql.append("and prod_id='"+prodId+"' ");
		if ( listRules!=null ) {
			for ( RepJobRange rule:listRules ) {
				if(StringUtil.equals(rule.getRuleType1(),"FINPROD_NAME")){
					String result="";
					String str=rule.getRuleType4();
					List<String>strList=StringUtil.splitToList(",",str);
					for (int i = 0; i < strList.size(); i++) {
						String ss=strList.get(i);
						result+="'"+ss+"'";
						if(i<strList.size()-1){
							result+=",";
						}
					}
					if(StringUtil.isNotEmpty(result)){
						sql.append(" and ");
						sql.append("prod_id in (");
						sql.append(result);
						sql.append(")");
					}
				}else if(StringUtil.equals(rule.getRuleType1(),"INVEST_TYPE")){
					sql.append(" and ");
					sql.append("NATURE_INVEST");
					sql.append(rule.getRuleType2());
					if ( "02".equals(rule.getRuleType3()) )
						sql.append("'");
					sql.append(rule.getRuleType4());
					if ( "02".equals(rule.getRuleType3()) )
						sql.append("'");
				}
				else{
					sql.append(" and ");
					sql.append(rule.getRuleType1());
					sql.append(rule.getRuleType2());
					if ( "02".equals(rule.getRuleType3()) )
						sql.append("'");
					sql.append(rule.getRuleType4());
					if ( "02".equals(rule.getRuleType3()) )
						sql.append("'");
				}
			}
		}
		List<RepProdInfo> list = durationReportMapper.selectProdInfoBySql(sql.toString());
		if (list.size()>0)
			return true;
		else
			return false;
	}
	
    private static BigDecimal percent2BigDecimal(String percent) {
    	try {
    		return new BigDecimal(percent.trim().replace("%", "")).divide(BigDecimal.valueOf(100));
    	} catch (Exception e) {
    		return null;
    	}
    }
    
	/**
	 * 【（结束日期的指数行情/起始日期-1工作日的指数行情）-1】/（结束日期-起始日期）*365，截位法保留2位小数
	 */
	private List<Map<String,Object>> calcAchivementBasis3(RepProdInfo prod,Date durStartDate,Date durEndDate) {

		List<Map<String,Object>> listBasis = new ArrayList<>();
		try {
			Date sDate = durStartDate;
			Date eDate = durEndDate;
			
			//查询指定的产品在统计区间结束日最近的一次有效的变更数据：
			Map<String,Object> mapParas = new HashMap<>();
			mapParas.put("prodId", prod.getProdId());   //这里传入母产品id（P_PROD_ID)，业绩基准明细绑定在母产品上
			mapParas.put("sDate", sDate);
			mapParas.put("eDate",eDate);
			List<RepProdBasicChange> listChange = durationReportMapper.selectAchievementBasis3(mapParas);
			
			if ( listChange==null || listChange.size()<1 ) {
				logger.error("指定的产品无业绩基准参数设置，子产品编号 :"+mapParas.get("prodId"));
	        	Map<String,Object> mapBasis = new HashMap<String,Object>();
	        	mapBasis.put("prodId", prod.getProdId());
	        	String dur="";
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
	    		dur = sdf.format(durStartDate);
	    		dur = dur+" - " + sdf.format(durEndDate);
	        	mapBasis.put("duration", dur);
	        	BigDecimal  basisValue = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN);;
	        	mapBasis.put("achieveBasis", basisValue+"%");
	        	listBasis.add(mapBasis);
				return listBasis;
			}
			
			TreeSet<Date> setDate = new TreeSet<Date>();
			Map<Date,List<RepProdBasicChange>> mapChange = new HashMap<>();
			//按生效日进行分组，将生效日相同的作为一组
			for ( RepProdBasicChange basis:listChange ) {
				Date key = basis.getEffDate();
				setDate.add(key);
				List<RepProdBasicChange> list = mapChange.get(key);
				if ( list==null ) {
					list = new ArrayList<RepProdBasicChange>();
					list.add(basis);
					mapChange.put(key, list);
				} else {
					list.add(basis);
				}
			}
			List<Date> listDate = new ArrayList<>(setDate);
		    Collections.sort(listDate);
			
			//循环treemap，按阶段进行计算业绩基准
	        for ( int i=0;i<listDate.size();i++ ) {
	        	Map<String,Object> mapBasis = new HashMap<String,Object>();
	        	mapBasis.put("prodId", prod.getProdId());
	        	
	        	Date dsDate = listDate.get(i);
	        	List<RepProdBasicChange> listDChange = mapChange.get(dsDate);
	        	
	        	if ( dsDate.compareTo(sDate)<0 ) {
	        		dsDate = sDate;
	        	}
	        	
	        	Date deDate = null;
	        	if ( i<listDate.size()-1 )
	        		deDate = DateUtils.addDays(listDate.get(i+1),-1);  //下一个点的时间-1天
	        	else
	        		deDate = eDate;
	        	
	        	//取行情的日期：取结束日行情，如果结束日是节假日，取前1个工作日
	        	Date conusEDate = holidayService.getPreWorkDate(deDate,"04",true);
	        	
	        	int days = DateUtil.getBetweenDayNumber(dsDate,conusEDate)+1;
	        	String dur="";
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
	    		dur = sdf.format(dsDate);
	    		dur = dur+" - " + sdf.format(deDate);
	        	mapBasis.put("duration", dur);
	        	
	        	BigDecimal basisValue = BigDecimal.ZERO;
	    		
				//查找指数行情
				for (RepProdBasicChange basis:listDChange ) {
					BigDecimal val = BigDecimal.ZERO;
					RepExtConusKey key = new RepExtConusKey();
					key.setDataType(basis.getDataType());
					key.setDataDate(holidayService.getPreWorkDate(dsDate,"04"));  //交易所日历的前一天
					key.setStatus("E");
					RepExtConus sConus = repExtConusMapper.selectByPrimaryKey(key);
					if ( sConus==null || sConus.getMarketInfo()==null ) {
						logger.error("没有找到起始日前一工作日行情。");
						continue;
					}
					key.setDataDate(conusEDate);
					RepExtConus eConus = repExtConusMapper.selectByPrimaryKey(key);
					if ( eConus==null || eConus.getMarketInfo()==null ) {
						logger.error("没有找到结束日行情。");
						continue;
					}
					val = ( eConus.getMarketInfo().divide(sConus.getMarketInfo(),20,BigDecimal.ROUND_DOWN ).subtract(BigDecimal.ONE) ).multiply(new BigDecimal(365));
					val = val.multiply(percent2BigDecimal(basis.getProportion())).divide(new BigDecimal(days),20,BigDecimal.ROUND_DOWN);
					basisValue = basisValue.add(val);
				}
				basisValue = new BigDecimal(100).multiply(basisValue).setScale(2, BigDecimal.ROUND_DOWN);
				mapBasis.put("achieveBasis", basisValue+"%");
				listBasis.add(mapBasis);
	        }
		} catch (Exception e) {
			logger.error("业绩比较基准计算错误！",e);
		}
		return listBasis;
	}	
	
}
