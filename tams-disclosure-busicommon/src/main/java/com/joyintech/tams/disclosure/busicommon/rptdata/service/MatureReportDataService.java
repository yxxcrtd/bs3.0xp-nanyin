package com.joyintech.tams.disclosure.busicommon.rptdata.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.ChbMktHolidayMapper;
import com.joyintech.tams.common.base.entity.ChbMktHoliday;
import com.joyintech.tams.common.base.entity.ChbMktHolidayKey;
import com.joyintech.tams.common.base.entity.DisProdBonusplan;
import com.joyintech.tams.common.base.entity.RepFeeTrd;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.busicommon.common.util.CollectionUtils;
import com.joyintech.tams.disclosure.busicommon.rptdata.dao.BonusReportMapper;
import com.joyintech.tams.disclosure.busicommon.rptdata.dao.MatureReportMapper;
import com.joyintech.tams.disclosure.busicommon.rptdata.dto.BonusReportDto;
import com.joyintech.tams.disclosure.busicommon.rptdata.dto.MatureReportDto;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;

/**
 * 到期公告-数据获取服务
 * @author chenxc
 * @version 1.0 2022年07月20日
 * @see 
 * @since 1.0
 */
@Service
public class MatureReportDataService implements IReportDataService {

	@Autowired
	private MatureReportMapper matureReportMapper;
	
	@Autowired
	private BonusReportMapper bonusReportMapper;
	
	@Autowired
	private ChbMktHolidayMapper chbMktHolidayMapper;

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(MatureReportDataService.class);

	@Override
	public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {

		List<Map<String, Object>> listResult = new ArrayList<>();

		MatureReportDto matureReportDto = new MatureReportDto();
		try {
//			String prodId = (paraMap.get("portnum") == null ? null : paraMap.get("portnum").toString());
//			Date genDate = (paraMap.get("endDate") == null ? new Date()
//					: DateUtil.parse(paraMap.get("endDate").toString()));
			
			List<BonusReportDto> listBonusReport = matureReportMapper.selectBonusPlan(paraMap);
			if ( listBonusReport!=null && listBonusReport.size()>0 ) {   //取最新的分红计划，做到期信息
				BonusReportDto dto = listBonusReport.get(0);
				matureReportDto.setProdName(dto.getProdName());
				matureReportDto.setProdId(dto.getProdId());
				matureReportDto.setProdRe(dto.getProdRe());
				matureReportDto.seteDate(dto.geteDate());
				
				if ( "02".equals(dto.getBonusMode()) ) {  //分红方式==到期兑付
					matureReportDto.setmDate( getNextWorkDate(dto.getSettDate()) );
				} else {
					matureReportDto.setmDate(dto.getSettDate());
				}
				
				//签字日期：收益分配基准日+1工作日
				matureReportDto.setGenDate(matureReportDto.getmDate());
				
	        	//期限天数
				matureReportDto.setRunDays( DateUtil.getBetweenDayNumber(dto.geteDate(),dto.getmDate()));
				
			}

			//累计净值：分红计算清单：历史累计分红
			//参考年化收益率：分红计算清单：本期清单年化收益率
			//客户收益：分红计算清单：历史累计应分配金额
			BigDecimal accuValue = BigDecimal.ONE;
			BigDecimal yearYield = BigDecimal.ZERO;
			BigDecimal custProfit = BigDecimal.ZERO;
			for ( BonusReportDto report:listBonusReport ) {  //计算每次分红的累计值
	        	//上一分配基准日
	        	if ( "01".equals(report.getIssuingWay()) ) {  //开放式
	        		DisProdBonusplan plan = bonusReportMapper.selectLastBonusPlan(paraMap);
	        		if ( plan==null  ) { //第一个计划
	        			report.setLastSettDate(report.geteDate());
	        		} else {
	        			report.setLastSettDate(plan.getSettDate());
	        		}
	        	} else {
	        		report.setLastSettDate(report.geteDate());
	        	}
	        	
	        	Date settDate = DateUtil.parse("2020-01-01");

	        	//运行天数
	        	if ( report.getLastSettDate().compareTo(report.geteDate())==0 ){
	        		report.setRunDays( DateUtil.getBetweenDayNumber(report.getSettDate(),report.getLastSettDate())+1);
	        	} else {
	        		report.setRunDays( DateUtil.getBetweenDayNumber(report.getSettDate(),report.getLastSettDate()));
	        	}
	        	
	        	//可供分配利润
	        	report.setDistributableProfit(report.getAssetsValue().subtract(report.getCapital()));
	
	        	//业绩比较基准收益
	        	report.setAchievementBasisProfit(report.getCapital().multiply(report.getAchievementBasisProp()));
	        	
	        	//超出收益
	        	report.setExcessProfit(report.getDistributableProfit().subtract(report.getAchievementBasisProfit()));
	        	
	        	//业绩报酬
	        	report.setFloatFee(report.getExcessProfit().multiply(report.getExcessAchievementProp()));
	        	
	        	//应分配利润
	        	report.setDistributedProfit(report.getDistributableProfit().subtract(report.getFloatFee()));
	        	
	        	//单位分红
	        	report.setUnitProfit(report.getDistributedProfit().divide(report.getCapital(),4,RoundingMode.HALF_UP));
	        	//年化收益率
	        	report.setYearYield(report.getUnitProfit().multiply(new BigDecimal(365)).divide(  new BigDecimal(report.getRunDays()),4,RoundingMode.HALF_UP  ));

	        	accuValue = accuValue.add(report.getUnitProfit());
	        	custProfit = custProfit.add(report.getDistributedProfit());
	        	if ( report.getSettDate().compareTo(settDate)>0 ) //取最近的分红计划的年化收益率
	        		yearYield = report.getYearYield(); 
		        	
			}
			matureReportDto.setSumValueAmt(accuValue);
			matureReportDto.setRefYearYield(yearYield);
			matureReportDto.setCustProfit(custProfit);
			
			//查询所有相关费用，通过主产品查找，如果是子产品id，还要找到主产品id
			List<RepFeeTrd> listFee = matureReportMapper.selectFees(paraMap);
			matureReportDto.setPurFee(BigDecimal.ZERO);
			matureReportDto.setRedemFee(BigDecimal.ZERO);
			for ( RepFeeTrd fee:listFee ) {
				if ("01".equals(fee.getFeeType()) ) {
					matureReportDto.setTrustFee(fee.getAccrual());
				} else if ("02".equals(fee.getFeeType()) ) {
					matureReportDto.setMgrFee(fee.getAccrual());
				} else if ("03".equals(fee.getFeeType()) ) {
					matureReportDto.setFloatFee(fee.getAccrual());
				} else if ("04".equals(fee.getFeeType()) ) {
					matureReportDto.setSaleFee(fee.getAccrual());
				} else if ("05".equals(fee.getFeeType()) ) {
					matureReportDto.setPurFee(fee.getAccrual());
				} else if ("06".equals(fee.getFeeType()) ) {
					matureReportDto.setRedemFee(fee.getAccrual());
				}
			}
			listResult.add(CollectionUtils.bean2Map(matureReportDto));
		} catch (Exception e) {
			logger.error("到期报表数据错误", e);
			e.printStackTrace();
		}

		return listResult;
	}
	
	/**
	 * 下一个工作日，不包含当日
	 * @param currDate
	 * @return
	 */
	private Date getNextWorkDate(Date currDate ) {
		ChbMktHolidayKey key = new ChbMktHolidayKey();
		Date iDate = currDate;
		do {
			iDate = DateUtil.addDay(iDate, 1);
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
	
	public RepReveal getRepReveal(Map<String, Object> paraMap) {
		RepReveal repReveal = new RepReveal();
		repReveal.setRepId(paraMap.get("repId").toString());
		repReveal.setProdid(paraMap.get("prodId").toString());
		
		Date eDate = DateUtil.parse(paraMap.get("platformDate").toString());
		List<BonusReportDto> listBonusReport = matureReportMapper.selectBonusPlan(paraMap);
		if ( listBonusReport!=null && listBonusReport.size()>0 ) {   //取最新的分红计划，做到期信息
			BonusReportDto dto = listBonusReport.get(0);
			eDate = dto.getSettDate();
		}
		repReveal.setEndDate(eDate);
		return repReveal;
	}

}
