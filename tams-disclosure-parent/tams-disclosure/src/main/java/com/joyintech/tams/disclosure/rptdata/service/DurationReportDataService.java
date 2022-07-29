package com.joyintech.tams.disclosure.rptdata.service;

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
import com.joyintech.tams.common.base.entity.RepExtConus;
import com.joyintech.tams.common.base.entity.RepExtConusKey;
import com.joyintech.tams.common.base.entity.RepProdBasicChange;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.disclosure.common.CollectionUtils;
import com.joyintech.tams.disclosure.common.DisReportType;
import com.joyintech.tams.disclosure.common.DurationUtil;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;
import com.joyintech.tams.disclosure.rptdata.dao.DurationReportMapper;
import com.joyintech.tams.disclosure.rptdata.dto.DurationReportDto;

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
	private ChbMktHolidayMapper chbMktHolidayMapper;
	
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
				reportDto= durationReportMapper.selectProdInfo(paraMap);
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
			} catch ( Exception e ) {
				logger.error("报表区间数据错误.");
				e.printStackTrace();
			}
			
			//财务指标：子产品维度
			try {
				List<Map<String,Object>> listFinIndex = durationReportMapper.selectFinIndex(paraMap);
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
					mapChild.put("duration",DurationUtil.getHalfYearDuration(year, quarter, child.getSetDate()));
					Date durStartDate = DurationUtil.getHalfYearStart(year, quarter);
					Date durEndDate = DurationUtil.getHalfYearEnd(year, quarter);
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
				List<Map<String,Object>> listRepIndex = durationReportMapper.selectReportIndex (paraMap);  //一般只有一条
				if ( listRepIndex!=null && listRepIndex.size()>0 ) {
					for ( Map<String,Object> item:listRepIndex ) {
						if ( "tzclhyzfx".equals(item.get("PRI_ID").toString() ) ) {
							reportDto.setTzclhyzfx( item.get("INDEX_VALUE").toString() );
						}
						if ( "ldxfxfx".equals(item.get("PRI_ID").toString()) ) {
							reportDto.setLdxfxfx(item.get("INDEX_VALUE").toString() );
						}
						if ( "tgrbg1".equals(item.get("PRI_ID").toString() ) ) {
							reportDto.setTgrbg1( item.get("INDEX_VALUE").toString() );
						}
						if ( "tgrbg2".equals(item.get("PRI_ID").toString()) ) {
							reportDto.setTgrbg2(item.get("INDEX_VALUE").toString() );
						}
						if ( "tgrbg3".equals(item.get("PRI_ID").toString()) ) {
							reportDto.setTgrbg3(item.get("INDEX_VALUE").toString() );
						}												
					}
				} 
					
			} catch (Exception e) {
				logger.error("报表指标参数信息错误.");
				e.printStackTrace();				
			}
			
			//产品资产组合
			try {
				List<Map<String,Object>> listAssetComb = durationReportMapper.selectProdAssetComb (paraMap);
				if ( listAssetComb!=null && listAssetComb.size()>0 )
					reportDto.setProdAssetComb(listAssetComb.get(0));
				else
					reportDto.setProdAssetComb(new HashMap<>());
			} catch (Exception e) {
						logger.error("报表产品资产组合信息错误.");
						e.printStackTrace();				
			}
			
			//产品资产明细
			try {
				List<Map<String,Object>> listAssetDetail = durationReportMapper.selectProdAssetDetail (paraMap);
				reportDto.setListAssetsDetail(listAssetDetail);
			}  catch (Exception e) {
				logger.error("报表产品资产投资明细信息错误.");
				e.printStackTrace();				
			}

			//TODO:非标资产

			//产品投资账户
			try {
				List<Map<String,Object>> listInvestAccts = durationReportMapper.selectAccts (paraMap);
				reportDto.setListAccts(listInvestAccts);
			}  catch (Exception e) {
				logger.error("报表投资账户信息错误.");
				e.printStackTrace();				
			}
			
			//关联交易
			try {
				List<Map<String,Object>> listRelDeal = durationReportMapper.selectRelDeals (paraMap);
				if ( listRelDeal!=null && listRelDeal.size()>0 ) {
					reportDto.setRepRelDeal(listRelDeal.get(0));
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
					key.setDataDate(this.getPreWorkDate(sDate));
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
	
	/**
	 * 获取前一个工作日，不包含当日
	 * @param currDate
	 * @return
	 */
	private Date getPreWorkDate(Date currDate ) {
		ChbMktHolidayKey key = new ChbMktHolidayKey();
		Date iDate = currDate;
		do {
			iDate = DateUtil.addDay(iDate, -1);
			key.setBusiDate(iDate);
			key.setMarketCode("01");
			ChbMktHoliday holiday = chbMktHolidayMapper.selectByPrimaryKey(key);
			if ( holiday==null ) {
				return iDate;
			}
			if ( "0".equals(holiday.getIsHoliday()) ) {  //工作日
				return iDate;
			}
		} while (true);

	}
}
