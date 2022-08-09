package com.joyintech.tams.disclosure.busicommon.rptdata.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.ChbMktHolidayMapper;
import com.joyintech.tams.common.base.dao.RepExtConusMapper;
import com.joyintech.tams.common.base.entity.ChbMktHoliday;
import com.joyintech.tams.common.base.entity.ChbMktHolidayKey;
import com.joyintech.tams.common.base.entity.DisNostdCreditor;
import com.joyintech.tams.common.base.entity.DisProdAsset;
import com.joyintech.tams.common.base.entity.RepAssetsComb;
import com.joyintech.tams.common.base.entity.RepExtConus;
import com.joyintech.tams.common.base.entity.RepExtConusKey;
import com.joyintech.tams.common.base.entity.RepIndexSub;
import com.joyintech.tams.common.base.entity.RepInvestAccount;
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
				e.printStackTrace();
			}

			//报表基本产品信息，母产品维度
			try {
				DurationReportDto dto= durationReportMapper.selectProdInfo(paraMap);
				if ( dto!=null ) {
					reportDto = dto; 
				}
			} catch (Exception e) {
				logger.error("报表产品基本信息错误.");
				e.printStackTrace();				
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
					item.setAssetsValue(item.getAssetsValue().setScale(2,BigDecimal.ROUND_DOWN));
					item.setUnitValue(item.getUnitValue().setScale(4,BigDecimal.ROUND_DOWN));
					item.setUnitValueT(item.getUnitValueT().setScale(4,BigDecimal.ROUND_DOWN));
					listFinIndex.add(CollectionUtils.bean2Map(item));
				});
				reportDto.setListFinIndex(listFinIndex);
			} catch (Exception e) {
				logger.error("报表财务指标信息错误.");
				e.printStackTrace();				
			}
			
			//业绩基准：子产品维度
			List<Map<String,Object>> listAchieveBasis = new ArrayList<>();
			List<RepProdInfo> listProdChildren = durationReportMapper.selectProdChildren(paraMap);
			for (RepProdInfo child:listProdChildren ) {
				Map<String,Object> mapChild = new HashMap<String,Object>();
				mapChild.put("prodId", child.getProdId());
				if ( rptType.equals(DisReportType.JDBG_ZSX) || rptType.equals(DisReportType.JDBG_FZSX)) {  //季报
					mapChild.put("duration",DurationUtil.getQuarterDuration(year, quarter, child.getSetDate()));
					Date durStartDate = DurationUtil.getQuarterStart(year, quarter);
					Date durEndDate = DurationUtil.getQuarterEnd(year, quarter);
					mapChild.put("achieveBasis",this.calcAchivementBasis(child,durStartDate,durEndDate) );
				} else if ( rptType.equals(DisReportType.BNDBG_ZSX) || rptType.equals(DisReportType.BNDBG_FZSX) ) {  //半年报
					mapChild.put("duration",DurationUtil.getHalfYearDuration(year, halfYear, child.getSetDate()));
					Date durStartDate = DurationUtil.getHalfYearStart(year, halfYear);
					Date durEndDate = DurationUtil.getHalfYearEnd(year, halfYear);
					mapChild.put("achieveBasis",this.calcAchivementBasis(child,durStartDate,durEndDate) );
				} else if ( rptType.equals(DisReportType.NDBG_ZSX) || rptType.equals(DisReportType.NDBG_FZSX) ) { //年报  
					mapChild.put("duration",DurationUtil.getYearDuration(year, child.getSetDate()));
					Date durStartDate = DurationUtil.getYearStart(year);
					Date durEndDate = DurationUtil.getYearEnd(year);
					mapChild.put("achieveBasis",this.calcAchivementBasis(child,durStartDate,durEndDate) );
				}				
				listAchieveBasis.add(mapChild);
			}
			reportDto.setListAchieveBasis(listAchieveBasis);
			
			//报告指标
			try {
				List<RepIndexSub> listRepIndex = durationReportMapper.selectReportIndex (paraMap);  //一般只有一条
				if ( listRepIndex!=null && listRepIndex.size()>0 ) {
					for ( RepIndexSub item:listRepIndex ) {
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
					String perVal = "份额净值为"+String.valueOf(BigDecimal.ONE.setScale(4,BigDecimal.ROUND_DOWN));
					if ( "01".equals(reportDto.getProdType()) ) { //净值型
						perVal = "份额净值为"+reportDto.getListFinIndex().get(0).get("unitValue").toString();
						if ( !"02".equals(reportDto.getRepProdType()) ) {  //母产品，循环每个子产品
							perVal = "";
							for ( Map<String,Object> map:reportDto.getListFinIndex() ) {
								perVal += map.get("prodId")+"份额净值为"+map.get("unitValue").toString()+"元，";
							}
							perVal = perVal.substring(0, perVal.length()-2);
						}
					}
					reportDto.setYjbx(perVal);
				} catch (Exception e ) {
					logger.error("报表业绩表现数据错误。",e);
					e.printStackTrace();
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
					item.setFixPreRatio(item.getFixAfterRatio().setScale(2,BigDecimal.ROUND_DOWN));
					item.setFixPreRatio(item.getFixAfterRatio().setScale(2,BigDecimal.ROUND_DOWN));
					item.setFdPreRatio(item.getFdAfterRatio().setScale(2,BigDecimal.ROUND_DOWN));
					item.setFdPreRatio(item.getFdAfterRatio().setScale(2,BigDecimal.ROUND_DOWN));
					item.setMixPreRatio(item.getMixAfterRatio().setScale(2,BigDecimal.ROUND_DOWN));
					item.setMixPreRatio(item.getMixAfterRatio().setScale(2,BigDecimal.ROUND_DOWN));
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
				for ( RepAssetsComb item:listDto ) {
					BigDecimal pd = BigDecimal.ZERO;
					BigDecimal fv = BigDecimal.ZERO;
					if ( i<10 ) {
						item.setPersonalField(++i+"");
						listAssetDetail.add(CollectionUtils.bean2Map(item));
						pd = item.getPdProp();
						fv = item.getFairValue();
					} else {
						if ( item.getPdProp().equals(pd) && item.getFairValue().equals(fv) ) {
							item.setPersonalField(++i+"");
							listAssetDetail.add(CollectionUtils.bean2Map(item));
						} else {
							break;
						}
					}
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
					reportDto.setRepRelDeal(CollectionUtils.bean2Map(listRelDeal.get(0)));
				} else {
					reportDto.setRepRelDeal(new HashMap<>());
				}
				
			} catch (Exception e) {
				logger.error("报表关联交易信息错误.");
				e.printStackTrace();				
			}
			
			listResult.add(CollectionUtils.bean2Map(reportDto));
		} catch (Exception e) {
			logger.error("定期报表数据查询错误", e);
			e.printStackTrace();
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
			
			if ( "02".equals(prod.getIssuingWay()) ) {  //封闭式：max(产品成立日，统计周期开始日）
				if (prod.getSetDate().compareTo(sDate)>0) {
					sDate = prod.getSetDate();
				}
			} else if ( "01".equals(prod.getIssuingWay()) ) {  //开放式
				List<RepProdBasicChange> listBasis = durationReportMapper.selectAchievementBasis(prod.getProdId());
				List<RepProdBasicChange> listEffBasis = new ArrayList<>();
				//查询是否在区间内有业绩基准变更，变更的判断规则是变更表的数据记录，与需求中的变更判断思路不一样，经过讨论，按变更履历表来
				//有无业绩基准变更，过滤listBasis状态为D的数据，如果条数减少，就是有变更
				listBasis.forEach( item->{
					if ( "E".equals(item.getStatus()) )
						listEffBasis.add(item);
					}
				);
				
				if ( listBasis.size()<=listEffBasis.size() ) {  //无业绩基准变更：max(产品成立日，统计周期开始日）
					if (prod.getSetDate().compareTo(sDate)>0) {
						sDate = prod.getSetDate();
					}
				} else {  //有业绩基准变更
					sDate = listBasis.get(0).getEffDate();  //最新的变更生效日
				}
				
				int days = DateUtil.getBetweenDayNumber(sDate,eDate);
				//查找指数行情
				for (RepProdBasicChange basis:listEffBasis ) {
					RepExtConusKey key = new RepExtConusKey();
					key.setDataType(basis.getDataType());
					key.setDataDate(holidayService.getPreWorkDate(sDate));
					RepExtConus sConus = repExtConusMapper.selectByPrimaryKey(key);
					key.setDataDate(eDate);
					RepExtConus eConus = repExtConusMapper.selectByPrimaryKey(key);
					basisValue = ( eConus.getMarketInfo().subtract(sConus.getMarketInfo()).subtract(BigDecimal.ONE) ).multiply(new BigDecimal(365));
					basisValue = basisValue.divide(new BigDecimal(days),2,RoundingMode.HALF_DOWN);
				}
			}
		} catch (Exception e) {
			logger.error("业绩比较基准计算错误！");
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
			year = Integer.parseInt(paraMap.get("year").toString());

			if ( rptType.equals(DisReportType.JDBG_ZSX) || rptType.equals(DisReportType.JDBG_FZSX)) {  //季报
				sDate = DurationUtil.getQuarterStart(year, quarter);
				eDate = DurationUtil.getQuarterEnd(year, quarter);
			} else if ( rptType.equals(DisReportType.BNDBG_ZSX) || rptType.equals(DisReportType.BNDBG_FZSX) ) {  //半年报
				sDate = DurationUtil.getHalfYearStart(year, halfYear);
				eDate = DurationUtil.getHalfYearEnd(year, halfYear);
			} else if ( rptType.equals(DisReportType.NDBG_ZSX) || rptType.equals(DisReportType.NDBG_FZSX) ) { //年报  
				sDate = DurationUtil.getYearStart(year);
				eDate = DurationUtil.getYearEnd(year);
			}						
		} catch ( Exception e ) {
			logger.error("报表区间数据错误.");
			e.printStackTrace();
		}
		repReveal.setStartDate(sDate);
		repReveal.setEndDate(eDate);
		return repReveal;
	}
}
