package com.joyintech.tams.valuation.report.common.infodisclosure.util;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbMktHoliday;
import com.joyintech.tams.common.base.entity.RepRule;
import com.joyintech.tams.valuation.report.common.base.dao.ChbDeadLineMapper;
import com.joyintech.tams.valuation.report.common.base.entity.ChbDeadLine001Dto;
import com.joyintech.tams.valuation.report.common.constant.ReportDictCodeList;
import com.joyintech.tams.valuation.report.common.util.DateCalcUtil;

/**
 * 生成披露截止日公共方法
 * @author Administrator
 *
 */
@Service("valuationRevealDeadLineService")
public class RevealDeadLineService {
    /**
     * 报送时点mapper
     */
    @Autowired
    private ChbDeadLineMapper chbDeadLineMapper;
	   /**
	    * 根据条件获取截止日期
	    * @param basisDate 基准日
	    * @param repRule   规则
	    * @param dateMap   日期集合
	    * @return
	    */
	   public  Date getRevDeadlineByMark(Date basisDate,RepRule repRule ,Map<String, Date> dateMap){
		   int days = Integer.parseInt(repRule.getRevealValue());
		   Date date ;
	       //披露截止日期
	       ChbDeadLine001Dto holidaymarket001Dto = new ChbDeadLine001Dto();
		   if(StringUtil.equals(ReportDictCodeList.日期类型_自然日.getValue(), repRule.getRevealDateType())){
				if(StringUtil.equals(ReportDictCodeList.生成时点类型_前.getValue(), repRule.getRevealType())){
					days = -1*days;
				}
				date = DateUtil.addDay(basisDate, days);
			}
			else {
				//若map中有，则直接在map中取值，否则查询工作日，并放入map中
				String key = DateUtil.format(basisDate) + GlobalConstant.VERTICAL_LINE + repRule.getRevealType()
					+ GlobalConstant.VERTICAL_LINE + repRule.getRevealValue();
				if(dateMap.get(key)!=null){
					date = dateMap.get(key);
				}
				else{
				    String dates = DateUtil.format(basisDate);
				    if(days>0){
				    	holidaymarket001Dto = chbDeadLineMapper.getChbDeadline(ReportDictCodeList.交易市场_中国法定节假日.getValue(),
			                       dates, repRule.getRevealType() , days);
				    }
				    else{
				    	holidaymarket001Dto = chbDeadLineMapper.getChbDeadlineZero(ReportDictCodeList.交易市场_中国法定节假日.getValue(),
			                       dates, repRule.getRevealType());
				    }
				    if(holidaymarket001Dto != null){
				    	date = holidaymarket001Dto.getBusiDate();
				    }
				    else{
				    	if(StringUtil.equals(ReportDictCodeList.生成时点类型_前.getValue(), repRule.getRevealType())){
							days = -1*days;
						}
						date = DateUtil.addDay(basisDate, days);
				    }
				    dateMap.put(key, date);
				}
			}
		   date = DateCalcUtil.getEndOfDayTime(date, repRule.getRevealTime());
		   return date;
	   }
	   /**
	    * 通过传入的日期与市场代码类型在节假日表中查询数据
	    * @param date 传入日期
	    * @param markCode市场代码
	    * @return
	    */
	   public boolean isWorkDay(Date date, String markCode){
		   boolean isWork = false;
		   ChbMktHoliday chbMktHoliday = chbDeadLineMapper.selectChbMktHolidayByKey(markCode, date);
		   if(chbMktHoliday!=null&&"1".equals(chbMktHoliday.getIsHoliday())){
			   isWork = true;
		   }
		   return isWork;
	   }
	   /**
	    * 通过传入的规则与基础日期判断是否可以生产披露
	    * @param basisDate 基准日
	    * @param repRule 报表规则
	    * @param sysDate 传入日期
	    * @return true表示可以生产false不可以生成
	    */
	   public Boolean isCanGen(Date basisDate, RepRule repRule, Map<String, Date> dateMap, Date sysDate){
		   boolean isGen = false;
		   int days = Integer.parseInt(repRule.getRevealValue());
		   Date date ;
	       //披露截止日期
	       ChbDeadLine001Dto holidaymarket001Dto = new ChbDeadLine001Dto();
		   if(StringUtil.equals(ReportDictCodeList.日期类型_自然日.getValue(), repRule.getMakeDateType())){
				if(StringUtil.equals(ReportDictCodeList.生成时点类型_前.getValue(), repRule.getMakeType())){
					days = -1*days;
				}
				date = DateUtil.addDay(basisDate, days);
			}
			else {
				//若map中有，则直接在map中取值，否则查询工作日，并放入map中
				String key = DateUtil.format(basisDate) + GlobalConstant.VERTICAL_LINE + repRule.getMakeType()
					+ GlobalConstant.VERTICAL_LINE + repRule.getMakeValue();
				if(dateMap.get(key)!=null){
					date = dateMap.get(key);
				}
				else{
				    String dates = DateUtil.format(basisDate);
				    if(days>0){
				    	holidaymarket001Dto = chbDeadLineMapper.getChbDeadline(ReportDictCodeList.交易市场_中国法定节假日.getValue(),
			                       dates, repRule.getMakeType() , days);
				    }
				    else{
				    	holidaymarket001Dto = chbDeadLineMapper.getChbDeadlineZero(ReportDictCodeList.交易市场_中国法定节假日.getValue(),
			                       dates, repRule.getMakeType());
				    }
				    if(holidaymarket001Dto != null){
				    	date = holidaymarket001Dto.getBusiDate();
				    }
				    else{
				    	if(StringUtil.equals(ReportDictCodeList.生成时点类型_前.getValue(), repRule.getMakeType())){
							days = -1*days;
						}
						date = DateUtil.addDay(basisDate, days);
				    }
				    dateMap.put(key, date);
				}
	       }
		   if(DateUtil.compareDate(date, sysDate)==0){
			   isGen = true;
		   }
		   return isGen;
	   }
}
