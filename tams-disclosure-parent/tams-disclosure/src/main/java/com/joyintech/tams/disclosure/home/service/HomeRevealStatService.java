/**
 * 
 */
package com.joyintech.tams.disclosure.home.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepInfo;
import com.joyintech.tams.common.base.entity.RepJobRange;
import com.joyintech.tams.common.base.entity.RepScreen;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.common.util.DurationUtil;
import com.joyintech.tams.disclosure.common.common.service.HolidayService;
import com.joyintech.tams.disclosure.home.dao.HomeRevealStatMapper;
import com.joyintech.tams.disclosure.home.dto.DisHomePageStat;
import com.joyintech.tams.disclosure.home.dto.HomePageStatsDto;
import com.joyintech.tams.disclosure.home.dto.RepRevealInfo;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dao.RepDetailsShow001Mapper;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;

/**
 * @author chenxc
 *
 */

@Service
public class HomeRevealStatService {
	
	@Autowired
	private HomeRevealStatMapper homeRevealStatMapper;
	
	@Autowired
	private RepDetailsShow001Mapper repDetailsShow001Mapper;
	
	@Autowired
	private HolidayService holidayService;

	/**
	 * PlatformDate
	 */
	@Autowired(required = false)
	private CommonPlatformDateService commonPlatformDateService;
	
	public RepRevealInfo queryCountsByHomeType(String homePageType, Date statDate) throws ParseException {
		RepRevealInfo repRevealInfo = new RepRevealInfo();
		
		List<RepInfo> listRepInfo = homeRevealStatMapper.selectRepInfoByHomePageType(homePageType);
		for ( RepInfo rep:listRepInfo ) {
			RepRevealInfo info = new RepRevealInfo(); 
			String repId = rep.getRepId();
	        	
	    	if ( repId.equals("CP_001_002_005") || repId.equals("CP_001_002_006") ) {//每周净值公告/报告：并且每周第一个工作日=当天，未到期的产品
	    		//找到本周一
	    		Calendar cal = Calendar.getInstance();
	    		cal.setTime(statDate);
	    		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//周日
	    		Date sunday = cal.getTime();
	    		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);//周六
	    		Date saturday = cal.getTime();
	    		
	    		//本周的第一个工作日
	    		Map<String,Object> p = new HashMap<>();
	    		p.put("busi_date", sunday);
	    		p.put("is_holiday", "0");
	    		p.put("market_code", "01");
	    		Date sDate = holidayService.getNextWorkDay(p);
	    		
	    		//统计已发生的列
				info = queryOtherCounts(repId,sunday,saturday);
				
				//统计预计披露的列
				int counts = queryExpectCounts(repId,sDate,saturday,statDate);
	    		info.setExpectRevealCounts(counts);
				
	    	}
	    	else if (repId.equals("CP_001_002_015") || repId.equals("CP_001_002_016") ) {  //季报
	    		//找到本季度的第一天
	    		Date qDate = DurationUtil.getQuarterStart(statDate.getYear()+1900,statDate.getMonth()/3+1);
	    		
	    		Map<String,Object> p = new HashMap<>();
	    		p.put("busi_date", DateUtil.addDay(qDate, -1));
	    		p.put("is_holiday", "0");
	    		p.put("market_code", "01");
	    		p.put("days", 3);
	    		Date sDate = holidayService.getAferNWorkDay(p);
	    		p.put("days", 15);
	    		Date eDate = holidayService.getAferNWorkDay(p);
	    		
	    		//统计已发生的列：用上个季度的末的日期进行查询
//	    		Date lastQuarterEndDate = this.getLastDurationEndDate(statDate, "Q");
//				info = queryOtherCounts(repId,lastQuarterEndDate,lastQuarterEndDate);
				info = queryOtherCounts(repId,sDate,eDate);

				//统计预计披露的列
				int counts = queryExpectCounts(repId,sDate,eDate,statDate);
	    		info.setExpectRevealCounts(counts);
			}
			else if( repId.equals("CP_001_002_017") || repId.equals("CP_001_002_018") ) {  //半年报

				if(DateUtil.getMonth(DateUtil.format(statDate))==7){
					//找到本周期的第一天
					Date qDate = DurationUtil.getHalfYearStart(statDate.getYear()+1900,statDate.getMonth()/6+1);
					Map<String,Object> p = new HashMap<>();
					p.put("busi_date", DateUtil.addDay(qDate, -1));
					p.put("is_holiday", "0");
					p.put("market_code", "01");
					p.put("days", 3);
					Date sDate = holidayService.getAferNWorkDay(p);
					p.put("days", 60);
					Date eDate = holidayService.getAferNWorkDay(p);

					//统计已发生的列
//	    		Date lastQuarterEndDate = this.getLastDurationEndDate(statDate, "H");
//				info = queryOtherCounts(repId,lastQuarterEndDate,lastQuarterEndDate);
					info = queryOtherCounts(repId,sDate,eDate);

					//统计预计披露的列
					int counts = queryExpectCounts(repId,sDate,eDate,statDate);
					info.setExpectRevealCounts(counts);
				}else{
					info.setExpectRevealCounts(0);
				}


			}
			else if (repId.equals("CP_001_002_019") || repId.equals("CP_001_002_020") ){  //年报
	    		//找到本周期的第一天
	    		Date qDate = DurationUtil.getYearStart(statDate.getYear()+1900);
	    		Map<String,Object> p = new HashMap<>();
	    		p.put("busi_date", DateUtil.addDay(qDate, -1));
	    		p.put("is_holiday", "0");
	    		p.put("market_code", "01");
	    		p.put("days", 3);
	    		Date sDate = holidayService.getAferNWorkDay(p);
	    		p.put("days", 90);
	    		Date eDate = holidayService.getAferNWorkDay(p);

	    		//统计已发生的列
//	    		Date lastQuarterEndDate = this.getLastDurationEndDate(statDate, "Y");
//				info = queryOtherCounts(repId,lastQuarterEndDate,lastQuarterEndDate);
				info = queryOtherCounts(repId,sDate,eDate);

				//统计预计披露的列	    		
				int counts = queryExpectCounts(repId,sDate,eDate,statDate);
	    		info.setExpectRevealCounts(counts);
			}
			else {  //其他报表
				//统计已发生的列
				info = queryOtherCounts(repId,statDate,statDate);
				
				//统计预计披露的列
				int counts = queryExcpectCountsByRepId(repId,statDate);
				info.setExpectRevealCounts(counts);
	    	}

	    	repRevealInfo.setExpectRevealCounts(repRevealInfo.getExpectRevealCounts()+info.getExpectRevealCounts());
	    	repRevealInfo.setRevealCounts(repRevealInfo.getRevealCounts()+info.getRevealCounts());
	    	repRevealInfo.setGenFailCounts(repRevealInfo.getGenFailCounts()+info.getGenFailCounts());
	    	repRevealInfo.setToBeReviewedCounts(repRevealInfo.getToBeReviewedCounts()+info.getToBeReviewedCounts());
	    	repRevealInfo.setToBeConfirmedCounts(repRevealInfo.getToBeConfirmedCounts()+info.getToBeConfirmedCounts());
	    	repRevealInfo.setToBeTgReviewedCounts(repRevealInfo.getToBeTgReviewedCounts()+info.getToBeTgReviewedCounts());
	    	repRevealInfo.setToBeRevealedCounts(repRevealInfo.getToBeRevealedCounts()+info.getToBeRevealedCounts());
		}
		return repRevealInfo;
	}
	
	private int queryExpectCounts(String repId,Date sDate,Date eDate,Date statDate ) {
		int counts = 0;
		if(StringUtil.equals("CP_001_002_005",repId)||StringUtil.equals("CP_001_002_006",repId)){
			counts = genStat(repId,sDate);
		}else {
			if ( sDate.compareTo(statDate)==0 ) { //当日就是一个季度的第3个工作日，生成统计数据
				counts = genStat(repId,sDate);
			} else if (sDate.compareTo(statDate)<0 && eDate.compareTo(statDate)>=0 ) {
				counts = queryStat(repId,sDate);
				if ( counts==-1 )  //首次生成
					counts = genStat(repId,sDate);
			} else {
				counts = 0;
			}
		}
		return counts;
	}

	private int queryDQExpectCounts(String repId,Date sDate,Date eDate,Date statDate ) {
		int counts = 0;

		return counts;
	}
	
	private RepRevealInfo queryOtherCounts( String repId,Date sDate,Date eDate ) {
		
		List<HomePageStatsDto> listStats = homeRevealStatMapper.selectHomePageStat(repId,sDate,eDate);
		
		RepRevealInfo info = new RepRevealInfo();

		if ( listStats==null )
			return info;
		
		for (HomePageStatsDto dto : listStats) {
			if (dto.getHomePageType() == null || dto.getHomePageType().length() < 1)
				continue;
			// 生成成功
			info.setGenFailCounts(info.getGenFailCounts() + dto.getCounts());

			if("02".equals(dto.getReviewStatus())||"02".equals(dto.getReviewTgStatus())){
				if ("01".equals(dto.getRevealStatus())) { // 已披露：披露状态='01'
					info.setRevealCounts(info.getRevealCounts() + dto.getCounts());
				} else { // 待披露
					info.setToBeRevealedCounts(info.getToBeRevealedCounts() + dto.getCounts());
				}
			}

			if (!"1".equals(dto.getConfirmStatus())) { // 待确认：确认状态：0,2
				info.setToBeConfirmedCounts(info.getToBeConfirmedCounts() + dto.getCounts());
			}
			//NYLCXP-1784 【我的主页】报表类型为日报，待复核没有统计被托管退回和被披露退回
			if ("01".equals(dto.getReviewStatus()) ||"04".equals(dto.getReviewStatus()) ||"05".equals(dto.getReviewStatus())) {
				info.setToBeReviewedCounts(info.getToBeReviewedCounts() + dto.getCounts());
			}
			//NYLCXP-1786【我的主页】报表类型为日报，待托管复核没有统计被披露退回
			if (StringUtil.equals(dto.getReviewStatus(),"03")
					&& ("01".equals(dto.getReviewTgStatus()) ||"03".equals(dto.getReviewTgStatus()))) {
				info.setToBeTgReviewedCounts(info.getToBeTgReviewedCounts() + dto.getCounts());
			}
		}
		if(StringUtil.equals("CP_001_003_001",repId)
				||StringUtil.equals("CP_001_003_002",repId)
				||StringUtil.equals("CP_001_002_009",repId)
				||StringUtil.equals("CP_001_002_010",repId)
				||StringUtil.equals("CP_001_002_011",repId)
				||StringUtil.equals("CP_001_002_012",repId)
				||StringUtil.equals("CP_001_002_013",repId)
				||StringUtil.equals("CP_001_002_014",repId)){
			//到期公告
			//开放分红公告的已披露要特殊处理 查询披露截止日等于当日，并且已披露的数据
			info.setRevealCounts(homeRevealStatMapper.selectRevealedData(repId,sDate));
		}

		return info;
	}
	
	private int queryExcpectCountsByRepId(String repId,Date statDate) {
		Map<String,Object> params = new HashMap<String,Object>();
		// 当前平台日
		String platformDate = DateUtil.format(commonPlatformDateService.getSysDate());
		Map<String,Object> expectConditions = getExpectRevealCondition(repId,statDate,platformDate);
		if ( expectConditions!=null && expectConditions.size()>0 )
			params.putAll(expectConditions);
		
		String sql = getRandgeRule(repId,params);
		int counts = homeRevealStatMapper.selectCountsByCondition(sql);
		return counts;
	}
	
    private String getRandgeRule(String repId,Map<String, Object> params){

    	//根据报表编码查询规则数据
        List<RepJobRange> ruleList = repDetailsShow001Mapper.getRepJobRangeInfo(repId);

        //根据规则数据设置查询条件
        StringBuffer sb = new StringBuffer();
        if(!StringUtil.isEmptyList(ruleList)){
            for(RepJobRange dto:ruleList){
                if(StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_募集方式.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_发行方式.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_发行状态.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_开放模式.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_是否独立开户.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_产品类型.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_理财类型.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_产品期限类型.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_开放频率.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_是否是指数型产品.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_是否是客户周期型产品.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_信批产品类别.getValue())
                        ||StringUtil.equals(dto.getRuleType1(), DictCodeExt1.发行方式_信批.getValue())){
                    sb.append(" and ").append(dto.getRuleType1()).append(dto.getRuleType2()).append("'")
                            .append(dto.getRuleType4()).append("'");
                }else if(StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_起息日.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_到期日.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_清盘日.getValue())){
                    String sysDate=DateUtil.getCurrentDate();
                    if(dto.getRuleType4()==null||StringUtil.isEmpty(dto.getRuleType4())){
                        sb.append(" and ").append("to_char(").append(dto.getRuleType1()).append(",'yyyy-mm-dd')").append(dto.getRuleType2()).append("'")
                        .append(sysDate).append("'");
                    }
                    else{
                        sb.append(" and ").append("to_char(").append(dto.getRuleType1()).append(",'yyyy-mm-dd')").append(dto.getRuleType2()).append("'")
                        .append(dto.getRuleType4()).append("'");
                    }
                }
            }
        }
        
        List<RepScreen> listScreen = repDetailsShow001Mapper.getRepScreenList(repId);
        List<String> listScreenKey = new ArrayList<>();
        listScreen.forEach(item->listScreenKey.add(item.getScreenKey()));
        
    	if( listScreenKey.contains("pprodid") ) {  //母产品维度
    		if ( listScreenKey.contains("prodid")) {  //子产品维度
    			sb.append(" and REP_PROD_TYPE in ('02','03')");
    		} else {
    			sb.append(" and REP_PROD_TYPE in ('01','02')");
    		}
        }
        
        for ( Entry<String, Object> entry:params.entrySet() ) {	
        	if ( entry.getValue()!=null && entry.getValue().toString().length()>0 ) {
        		String str = " and " + entry.getValue();
        		sb.append(str);
        	}
        }
        
        return sb.toString();
    }

    private Map<String,Object> getExpectRevealCondition(String repId,Date statDate,String planDate) {
    	Map<String,Object> map = new HashMap<String,Object>();
    	String condition = "";
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String queryDate = sdf.format(statDate);
    	
    	if ( repId.equals("CP_001_001_001") ) {//发行公告：成立日=当天
    		condition = "SET_DATE=to_date('"+queryDate + "','yyyy-mm-dd')";
    	}
    	else if ( repId.equals("CP_001_002_003") || repId.equals("CP_001_002_004") ) { //日开产品净值公告，未到期的产品
    		condition = "info.MDATE>=to_date('"+queryDate+"','yyyy-mm-dd')";
    	}
    	else if ( repId.equals("CP_001_002_007") ) {//周期型产品开放公告（公募产品）：并且运作起始日=当天
    		condition = " exists ( select 1 from"  
    				+ " DIS_PROD_PLAN plan"  
    				+ " where" 
    				+ " plan.OPERATION_START_DATE = to_date('"+queryDate+"','yyyy-mm-dd')" 
    				+ " and plan.DATA_STATUS='E'"
    				+ " and info.P_PROD_ID = plan.PROD_ID)";
    	}
    	else if ( repId.equals("CP_001_002_008") ) {//周期型产品开放公告（公募产品-单赎回）：并且自动赎回日=当天
    		condition = " exists ( select 1 from" + 
    				" DIS_PROD_PLAN plan" + 
    				" where" + 
    				" plan.AUTO_REDEEM_DATE = to_date('"+queryDate+"','yyyy-mm-dd')" + 
    				" and info.P_PROD_ID = plan.PROD_ID)";
    	}    	
    	else if ( repId.equals("CP_001_002_009")|| repId.equals("CP_001_002_010")
    			|| repId.equals("CP_001_002_013")|| repId.equals("CP_001_002_014")) {
					//开放、净值及分红公告：并且当期收益分配基准日=当天
    		        //净值及分红报告；
    		condition = " exists ( select 1 from"  
    				+ " DIS_PROD_BONUSPLAN plan"  
    				+ " where" 
    				+ " plan.SETT_DATE = to_date('"+queryDate+"','yyyy-mm-dd')" 
    				+ " and plan.DATA_STATUS='E'"
					+ " and plan.BONUS_MODE='01'"
    				+ " and info.PROD_ID = plan.PROD_ID)";
    	}
		else if ( repId.equals("CP_001_003_001")|| repId.equals("CP_001_003_002")
		) {
			//到期公告；
			condition = " exists ( select 1 from"
					+ " DIS_PROD_BONUSPLAN plan"
					+ " where"
					+ " plan.SETT_DATE = to_date('"+queryDate+"','yyyy-mm-dd')"
					+ " and plan.DATA_STATUS='E'"
					+ " and plan.BONUS_MODE='02'"
					+ " and info.PROD_ID = plan.PROD_ID)";
		}
    	else if ( repId.equals("CP_001_002_011") ) {//开放及净值公告（公募）：并且开放结束日+1=当天
    		Map<String,Object> p = new HashMap<>();
    		p.put("busi_date", statDate);
    		p.put("is_holiday", "0");
    		p.put("market_code", "01");
    		Date preStatDate = holidayService.getLastWorkDay(p);
    		String preQueryDate = sdf.format(preStatDate);
    		condition = " exists ( select 1 from"  
    				+ " DIS_PROD_PLAN plan" 
    				+ " where" 
    				+ " plan.OPEN_END_DATE = to_date('"+preQueryDate+"','yyyy-mm-dd')" 
    				+ " and plan.DATA_STATUS='E'"
    				+ " and info.P_PROD_ID = plan.PROD_ID"
					+ " and not exists (select 1 from dis_prod_bonusplan dpb where plan.OPEN_END_DATE = dpb.sett_date and info.PROD_ID = dpb.PROD_ID and dpb.data_status = 'E'))";
    	}
    	else if ( repId.equals("CP_001_002_012") ) {//开放及净值报告（私募）：并且运作起始日=当天
    		condition = " exists ( select 1 from"  
    				+ " DIS_PROD_PLAN plan" 
    				+ " where" 
    				+ " plan.OPERATION_START_DATE = to_date('"+queryDate+"','yyyy-mm-dd')" 
    				+ " and plan.DATA_STATUS='E'" 
    				+ " and info.P_PROD_ID = plan.PROD_ID"
					+ " and not exists (select 1 from dis_prod_bonusplan dpb where plan.OPEN_END_DATE = dpb.sett_date and info.PROD_ID = dpb.PROD_ID and dpb.data_status = 'E'))";
    	}    	
    	else if ( repId.equals("CP_001_002_005") || repId.equals("CP_001_002_006") ) {//每周净值公告：并且每周第一个工作日=当天，未到期的产品
    		//找到上周的第一天
//    		Date qDate = getLastDurationFirstDate(statDate,"W");
//    		String qDateStr = sdf.format(qDate);
//    		condition = " info.MDATE>=to_date('"+qDateStr+"','yyyy-mm-dd')";
			condition = planDate + "=" +queryDate;
//    		//找到本周一
//    		Calendar cal = Calendar.getInstance();
//    		cal.setTime(statDate);
//    		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//周一
//    		Date firstWorkDateOfWeek = cal.getTime();
//    		if ( holidayService.isHoliday(firstWorkDateOfWeek, "04")==true ) {
//        		Map<String,Object> p = new HashMap<>();
//        		p.put("busi_date", firstWorkDateOfWeek);
//        		p.put("is_holiday", "0");
//        		p.put("market_code", "04");
//    			firstWorkDateOfWeek = holidayService.getNextWorkDay(p);
//    		}
//    		String firstWorkDateofWeekStr = sdf.format(firstWorkDateOfWeek);
//    		condition += " and exists ( select 1 from" + 
//    				" dual" + 
//    				" where to_date('"+firstWorkDateofWeekStr+"','yyyy-mm-dd')=to_date('"+queryDate+"')"; 
    	}
    	else if ( repId.equals("CP_001_002_015") || repId.equals("CP_001_002_016")  ) {//季度报告：下一季度第三个工作日<=当天<=下一季度15个工作日之前
    		//找到上季度的第一天
			//定期报表必须满足成立日距离（上季末最后一天）大于90天，（上季末最后一天）离实际到期日大于90天
			String qDate = DateUtil.format(getLastMonthDay(statDate),"yyyy-MM-dd");
			condition = " exists (select 1 from v_rep_prod_info v "
						+ " where v.PROD_ID = nvl(info.p_prod_id, info.prod_id)"
						+ " and v.STATUS = 'E' "
						+ " and (to_date('"+qDate + "','yyyy-MM-dd') - v.SET_DATE) > 90 "
						+ "and (v.MDATE - to_date('"+qDate + "','yyyy-MM-dd') > 90)) ";

    	}
    	else if ( repId.equals("CP_001_002_017") || repId.equals("CP_001_002_018")  ) {//半年度报告：下一半年度第三个工作日<=当天<=下一半年度90个工作日之前
    		//找到上个半年的第一天
			//定期报表必须满足成立日距离结束日大于90天，结束日离实际到期日大于90天
			String qDate = DateUtil.format(getLastMonthDay(statDate),"yyyy-MM-dd");
			condition = " exists (select 1 from v_rep_prod_info v "
					+ " where v.PROD_ID = nvl(info.p_prod_id, info.prod_id)"
					+ " and v.STATUS = 'E' "
					+ " and (to_date('"+qDate + "','yyyy-MM-dd') - v.SET_DATE) > 90 "
					+ "and (v.MDATE - to_date('"+qDate + "','yyyy-MM-dd') > 90)) ";
    	}
    	else if ( repId.equals("CP_001_002_019") || repId.equals("CP_001_002_020")  ) {//年度报告：下一年度第三个工作日<=当天<=下一年度60个工作日之前
    		//找到上个年度的第一天
			//定期报表必须满足成立日距离结束日大于90天，结束日离实际到期日大于90天
			String qDate = DateUtil.format(getLastMonthDay(statDate),"yyyy-MM-dd");
			condition = " exists (select 1 from v_rep_prod_info v "
					+ " where v.PROD_ID = nvl(info.p_prod_id, info.prod_id)"
					+ " and v.STATUS = 'E' "
					+ " and (to_date('"+qDate + "','yyyy-MM-dd') - v.SET_DATE) > 90 "
					+ "and (v.MDATE - to_date('"+qDate + "','yyyy-MM-dd') > 90) )";
    	}
    	
    	if ( condition.length()>0 )
			condition = condition + "AND info.ISSUE_STATUS = '01'";
			condition = condition + "AND info.PD_STATUS = '01'";
    		map.put("condition", condition);
    	return map;
    }
    
    private Date getLastDurationFirstDate(Date queryDate, String durationType ) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(queryDate);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		if ( durationType.equals("W") ) {
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//周一
	        return DateUtil.addWeeks(cal.getTime(), -1);
		}
		else if ( durationType.equals("Y")) {
    		year = year-1;
    		cal.set(year,0,1);
    	} else if ( durationType.equals("H") ) {
    		if (month<6 ) {
    			month = 6;
    			year = year-1; 
    		} else {
    			month = 0;
    		}
    		cal.set(year,month,1);
    	} else if ( durationType.equals("Q") ) {
    		if ( month<3 ) {
    			year = year-1;
    			month = 9;
    		} else if ( month>=3 && month<6 ) {
    			month = 0;
    		} else if ( month>=6 && month<9 ) {
    			month = 3;
    		} else if ( month>=9 && month<12 ) {
    			month = 6;
    		}
    		cal.set(year,month,1);
    	}
    	return cal.getTime();
    }
    
    private Date getLastDurationEndDate(Date queryDate, String durationType ) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(queryDate);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day=30;
		if ( durationType.equals("Q") ) {
    		if ( month<3 ) {
    			year = year-1;
    			month = 11;
    			day=31;
    		} else if ( month>=3 && month<6 ) {
    			month = 2;
    			day=30;
    		} else if ( month>=6 && month<9 ) {
    			month = 5;
    			day=30;
    		} else if ( month>=9 && month<12 ) {
    			month = 8;
    			day=31;
    		}
    	}
		else if ( durationType.equals("H") ) {
    		if ( month<6 ) {
    			year = year-1;
    			month = 11;
    			day=31;
    		} else {
    			month = 5;
    			day=30;
    		}
    	}
		else if (  durationType.equals("Y") ) {
			year = year-1;
			month=11;
			day = 31;
		}
		cal.set(year,month,day);
    	return cal.getTime();
    }
    
    
    private int genStat(String repId,Date statDate) {
    	int counts = queryExcpectCountsByRepId(repId,statDate);
    	DisHomePageStat dto = new DisHomePageStat();
    	dto.setStatId(IDGenerator.generate());
    	dto.setRepId(repId);
    	dto.setStatDate(statDate);
    	dto.setExpectCounts(counts);
		homeRevealStatMapper.delete(dto);
    	homeRevealStatMapper.insert(dto);
    	return counts;
    }
    
    private int queryStat(String repId,Date statDate) {
    	DisHomePageStat stat = homeRevealStatMapper.select(repId,statDate);
    	if ( stat!=null )
    		return stat.getExpectCounts();
    	else
    		return -1;
    }

	public Date getLastMonthDay(Date todayDate) {
		Calendar calendar = Calendar.getInstance();
		// 把平台日期赋值给Calendar
		calendar.setTime(todayDate);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-1);
		int endday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, endday);
		return calendar.getTime();
	}
    
}
