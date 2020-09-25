package com.joyintech.tams.disclosure.busicommon.infodisclosure.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbMktHoliday;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.RepRuleEntity;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.dao.ChbDeadLineMapper;
import com.joyintech.tams.disclosure.common.common.dto.ChbDeadLine001Dto;
import com.joyintech.tams.disclosure.common.common.util.DateCalcUtil;

/**
 * 生成披露截止日公共方法
 * @author Administrator
 *
 */
@Service("disclosureRevealDeadLineService")
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
	   public  Date getRevDeadlineByMark(Date basisDate,RepRuleEntity repRule ,Map<String, Date> dateMap){
		   int days = Integer.parseInt(repRule.getRevealValue());
		   Date date ;
           //若为数据周期结束日，则按照基准日后处理,并且将记住日修改为结束日
           if(StringUtil.equals(DictCodeListExt2.时点类型_数据结束日后.getValue(), repRule.getRevealType())){
               repRule.setRevealType(DictCodeListExt2.时点类型_基准日后.getValue());
               if(dateMap.get("endDate") != null){
                   basisDate = dateMap.get("endDate");
               }
           }
	       //披露截止日期
	       ChbDeadLine001Dto holidaymarket001Dto = new ChbDeadLine001Dto();
		   if(StringUtil.equals(DictCodeListExt1.日期类型_信批_自然日.getValue(), repRule.getRevealDateType())){
				if(StringUtil.equals(DictCodeListExt2.时点类型_基准日前.getValue(), repRule.getRevealType())){
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
				    	holidaymarket001Dto = chbDeadLineMapper.getChbDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
			                       dates, repRule.getRevealType() , days);
				    }
				    else{
				    	holidaymarket001Dto = chbDeadLineMapper.getChbDeadlineZero(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
			                       dates, repRule.getRevealType());
				    }
				    if(holidaymarket001Dto != null){
				    	date = holidaymarket001Dto.getBusiDate();
				    }
				    else{
				    	if(StringUtil.equals(DictCodeListExt2.时点类型_基准日前.getValue(), repRule.getRevealType())){
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
	   public Boolean isCanGen(Date basisDate, RepRuleEntity repRule, Map<String, Date> dateMap, Date sysDate){
		   boolean isGen = false;
		   int days = Integer.parseInt(repRule.getMakeValue());
		   Date date ;
	       //披露截止日期
	       ChbDeadLine001Dto holidaymarket001Dto = new ChbDeadLine001Dto();
		   if(StringUtil.equals(DictCodeListExt1.日期类型_信批_自然日.getValue(), repRule.getMakeDateType())){
				if(StringUtil.equals(DictCodeListExt2.基准区分_基准日前.getValue(), repRule.getMakeType())){
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
				    	holidaymarket001Dto = chbDeadLineMapper.getChbDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
			                       dates, repRule.getMakeType() , days);
				    }
				    else{
				    	holidaymarket001Dto = chbDeadLineMapper.getChbDeadlineZero(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
			                       dates, repRule.getMakeType());
				    }
				    if(holidaymarket001Dto != null){
				    	date = holidaymarket001Dto.getBusiDate();
				    }
				    else{
				    	if(StringUtil.equals(DictCodeListExt2.基准区分_基准日前.getValue(), repRule.getMakeType())){
							days = -1*days;
						}
						date = DateUtil.addDay(basisDate, days);
				    }
				    dateMap.put(key, date);
				}
	       }
		   if(DateUtil.compareDate(DateUtil.format(date), DateUtil.format(sysDate))==0){
			   isGen = true;
		   }
		   return isGen;
	   }
	   /**
	    * 通过传入的规则与基础日期判断是否可以生产披露
	    * @param basisDate 基准日
	    * @param repRule 报表规则
	    * @param sysDate 传入日期
	    * @return true表示可以生产false不可以生成
	    */
	   public Date isCanGenOpen(RepRuleEntity repRule, Map<String, Date> dateMap, Date sysDate, String prodId){
		   int days = Integer.parseInt(repRule.getMakeValue());
		   //日期list
		   List<Date> listDate = new ArrayList<Date>();
	       //披露截止日期
	       ChbDeadLine001Dto holidaymarket001Dto = new ChbDeadLine001Dto();
		   if(StringUtil.equals(DictCodeListExt1.日期类型_信批_自然日.getValue(), repRule.getMakeDateType())){
				if(StringUtil.equals(DictCodeListExt2.基准区分_基准日后.getValue(), repRule.getMakeType())){
					days = -1*days;
				}
				listDate.add(DateUtil.addDay(sysDate, days));
			}
			else {
				//若map中有，则直接在map中取值，否则查询工作日，并放入map中
				String key = DateUtil.format(sysDate) + GlobalConstant.VERTICAL_LINE + repRule.getMakeType()
					+ GlobalConstant.VERTICAL_LINE + repRule.getMakeValue();
				Date date ;
				if(dateMap.get(key)!=null){
					date = dateMap.get(key);
				}
				else{
				    String dates = DateUtil.format(sysDate);
				    if(days>0){
				    	holidaymarket001Dto = chbDeadLineMapper.getInfoDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
			                       dates, repRule.getMakeType() , days);
				    }
				    else{
				    	holidaymarket001Dto = chbDeadLineMapper.getInfoDeadlineZero(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
			                       dates, repRule.getMakeType());
				    }
				    if(holidaymarket001Dto != null){
				    	date = holidaymarket001Dto.getBusiDate();
				    }
				    else{
				    	if(StringUtil.equals(DictCodeListExt2.基准区分_基准日后.getValue(), repRule.getMakeType())){
							days = -1*days;
						}
						date = DateUtil.addDay(sysDate, days);
				    }
				    dateMap.put(key, date);
				}
				listDate.add(date);
				//若类型为生产时点后的，若后一天日节假日，则取出前面的节假日
				if(StringUtil.equals(DictCodeListExt2.基准区分_基准日后.getValue(), repRule.getMakeType())){
					if(date!=null&&!this.isWorkDay(DateUtil.addDay(date, 1), DisclosureDictCodeList.交易市场_中国法定节假日.getValue())){
						holidaymarket001Dto = chbDeadLineMapper.getChbDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
								DateUtil.format(date), repRule.getMakeType() , 1);
						if(holidaymarket001Dto!=null){
							Date busiDate = DateUtil.addDay(holidaymarket001Dto.getBusiDate(), -1);
							while(DateUtil.compareDate(busiDate, date)>0){
								listDate.add(busiDate);
								busiDate = DateUtil.addDay(busiDate, -1);
							}
						}
					}
				}
				//若类型为生产时点前的，若前一天日节假日，则取出前面的节假日
				else{
					if(date!=null&&!this.isWorkDay(DateUtil.addDay(date, -1), DisclosureDictCodeList.交易市场_中国法定节假日.getValue())){
						holidaymarket001Dto = chbDeadLineMapper.getChbDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
								DateUtil.format(date), repRule.getMakeType() , 1);
						if(holidaymarket001Dto!=null){
							Date busiDate = DateUtil.addDay(holidaymarket001Dto.getBusiDate(), 1);
							while(DateUtil.compareDate(busiDate, date)<0){
								listDate.add(busiDate);
								busiDate = DateUtil.addDay(busiDate, 1);
							}
						}
					}
				}
	       }
		   //将传入加减个规则日期获取基准日
//		   Date openDate = null;
		   for (Date execDate : listDate) {
			  Date openDate = chbDeadLineMapper.getOpenRevealDate(prodId, execDate, repRule.getBasisDateType());
			  if(openDate!=null){
				  return openDate;
			  }
		   }
		   //Date openDate = chbDeadLineMapper.getOpenRevealDate(prodId, date, repRule.getBasisDateType());
		   return null;
	   }
	   
	   
	   
	   
	   /**
	    * 通过传入的规则与基础日期判断是否可以生产披露
	    * @param basisDate 基准日
	    * @param repRule 报表规则
	    * @param sysDate 传入日期
	    * @return true表示可以生产false不可以生成
	    */
	   public Date isCanGenAdjust(RepRuleEntity repRule, Map<String, Date> dateMap, Date sysDate, String prodId){
		   int days = Integer.parseInt(repRule.getMakeValue());
		   //日期list
		   List<Date> listDate = new ArrayList<Date>();
	       //披露截止日期
	       ChbDeadLine001Dto holidaymarket001Dto = new ChbDeadLine001Dto();
		   if(StringUtil.equals(DictCodeListExt1.日期类型_信批_自然日.getValue(), repRule.getMakeDateType())){
				if(StringUtil.equals(DictCodeListExt2.基准区分_基准日后.getValue(), repRule.getMakeType())){
					days = -1*days;
				}
				listDate.add(DateUtil.addDay(sysDate, days));
			}
			else {
				//若map中有，则直接在map中取值，否则查询工作日，并放入map中
				String key = DateUtil.format(sysDate) + GlobalConstant.VERTICAL_LINE + repRule.getMakeType()
					+ GlobalConstant.VERTICAL_LINE + repRule.getMakeValue();
				Date date ;
				if(dateMap.get(key)!=null){
					date = dateMap.get(key);
				}
				else{
				    String dates = DateUtil.format(sysDate);
				    if(days>0){
				    	holidaymarket001Dto = chbDeadLineMapper.getInfoDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
			                       dates, repRule.getMakeType() , days);
				    }
				    else{
				    	holidaymarket001Dto = chbDeadLineMapper.getInfoDeadlineZero(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
			                       dates, repRule.getMakeType());
				    }
				    if(holidaymarket001Dto != null){
				    	date = holidaymarket001Dto.getBusiDate();
				    }
				    else{
				    	if(StringUtil.equals(DictCodeListExt2.基准区分_基准日后.getValue(), repRule.getMakeType())){
							days = -1*days;
						}
						date = DateUtil.addDay(sysDate, days);
				    }
				    dateMap.put(key, date);
				}
				listDate.add(date);
				//若类型为生产时点后的，若后一天日节假日，则取出前面的节假日
				if(StringUtil.equals(DictCodeListExt2.基准区分_基准日后.getValue(), repRule.getMakeType())){
					if(date!=null&&!this.isWorkDay(DateUtil.addDay(date, 1), DisclosureDictCodeList.交易市场_中国法定节假日.getValue())){
						holidaymarket001Dto = chbDeadLineMapper.getChbDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
								DateUtil.format(date), repRule.getMakeType() , 1);
						if(holidaymarket001Dto!=null){
							Date busiDate = DateUtil.addDay(holidaymarket001Dto.getBusiDate(), -1);
							while(DateUtil.compareDate(busiDate, date)>0){
								listDate.add(busiDate);
								busiDate = DateUtil.addDay(busiDate, -1);
							}
						}
					}
				}
				//若类型为生产时点前的，若前一天日节假日，则取出前面的节假日
				else{
					if(date!=null&&!this.isWorkDay(DateUtil.addDay(date, -1), DisclosureDictCodeList.交易市场_中国法定节假日.getValue())){
						holidaymarket001Dto = chbDeadLineMapper.getChbDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
								DateUtil.format(date), repRule.getMakeType() , 1);
						if(holidaymarket001Dto!=null){
							Date busiDate = DateUtil.addDay(holidaymarket001Dto.getBusiDate(), 1);
							while(DateUtil.compareDate(busiDate, date)<0){
								listDate.add(busiDate);
								busiDate = DateUtil.addDay(busiDate, 1);
							}
						}
					}
				}
	       }
		   //将传入加减个规则日期获取基准日
//		   Date openDate = null;
		   for (Date execDate : listDate) {
			  //修改查询
			  int count = chbDeadLineMapper.getAdjustDate(prodId, execDate, repRule.getBasisDateType());
			  //查到>0数据则更新
			  if(count>0){
				  return execDate;
			  }
		   }
		   //Date openDate = chbDeadLineMapper.getOpenRevealDate(prodId, date, repRule.getBasisDateType());
		   return null;
	   }
	   
	   public Date isCanGenJESH(RepRuleEntity repRule, Map<String, Date> dateMap, Date sysDate, String prodId){
		   int days = Integer.parseInt(repRule.getMakeValue());
		   //日期list
		   List<Date> listDate = new ArrayList<Date>();
	       //披露截止日期
	       ChbDeadLine001Dto holidaymarket001Dto = new ChbDeadLine001Dto();
		   if(StringUtil.equals(DisclosureDictCodeList.日期类型_自然日.getValue(), repRule.getMakeDateType())){
				if(StringUtil.equals(DisclosureDictCodeList.生成时点类型_后.getValue(), repRule.getMakeType())){
					days = -1*days;
				}
				listDate.add(DateUtil.addDay(sysDate, days));
			}
			else {
				//若map中有，则直接在map中取值，否则查询工作日，并放入map中
				String key = DateUtil.format(sysDate) + GlobalConstant.VERTICAL_LINE + repRule.getMakeType()
					+ GlobalConstant.VERTICAL_LINE + repRule.getMakeValue();
				Date date ;
				if(dateMap.get(key)!=null){
					date = dateMap.get(key);
				}
				else{
				    String dates = DateUtil.format(sysDate);
				    if(days>0){
				    	holidaymarket001Dto = chbDeadLineMapper.getInfoDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
			                       dates, repRule.getMakeType() , days);
				    }
				    else{
				    	holidaymarket001Dto = chbDeadLineMapper.getInfoDeadlineZero(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
			                       dates, repRule.getMakeType());
				    }
				    if(holidaymarket001Dto != null){
				    	date = holidaymarket001Dto.getBusiDate();
				    }
				    else{
				    	if(StringUtil.equals(DisclosureDictCodeList.生成时点类型_后.getValue(), repRule.getMakeType())){
							days = -1*days;
						}
						date = DateUtil.addDay(sysDate, days);
				    }
				    dateMap.put(key, date);
				}
				listDate.add(date);
				//若类型为生产时点后的，若后一天日节假日，则取出前面的节假日
				if(StringUtil.equals(DisclosureDictCodeList.生成时点类型_后.getValue(), repRule.getMakeType())){
					if(date!=null&&!this.isWorkDay(DateUtil.addDay(date, 1), DisclosureDictCodeList.交易市场_中国法定节假日.getValue())){
						holidaymarket001Dto = chbDeadLineMapper.getChbDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
								DateUtil.format(date), repRule.getMakeType() , 1);
						if(holidaymarket001Dto!=null){
							Date busiDate = DateUtil.addDay(holidaymarket001Dto.getBusiDate(), -1);
							while(DateUtil.compareDate(busiDate, date)>0){
								listDate.add(busiDate);
								busiDate = DateUtil.addDay(busiDate, -1);
							}
						}
					}
				}
				//若类型为生产时点前的，若前一天日节假日，则取出前面的节假日
				else{
					if(date!=null&&!this.isWorkDay(DateUtil.addDay(date, -1), DisclosureDictCodeList.交易市场_中国法定节假日.getValue())){
						holidaymarket001Dto = chbDeadLineMapper.getChbDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
								DateUtil.format(date), repRule.getMakeType() , 1);
						if(holidaymarket001Dto!=null){
							Date busiDate = DateUtil.addDay(holidaymarket001Dto.getBusiDate(), 1);
							while(DateUtil.compareDate(busiDate, date)<0){
								listDate.add(busiDate);
								busiDate = DateUtil.addDay(busiDate, 1);
							}
						}
					}
				}
	       }
		   //将传入加减个规则日期获取基准日
//		   Date openDate = null;
		   for (Date execDate : listDate) {
			  //修改查询
			   if(execDate!=null){
			   Date openDate = chbDeadLineMapper.getJESHLARGESHDATE(prodId, execDate, repRule.getBasisDateType());
				  if(openDate!=null){
					  return openDate;
			  }
			 }
		   }
		   //Date openDate = chbDeadLineMapper.getOpenRevealDate(prodId, date, repRule.getBasisDateType());
		   return null;
	   }
	   
	   public Date isCanGenMJTZ(RepRuleEntity repRule, Map<String, Date> dateMap, Date sysDate, String prodId){
		   int days = Integer.parseInt(repRule.getMakeValue());
		   //日期list
		   List<Date> listDate = new ArrayList<Date>();
	       //披露截止日期
	       ChbDeadLine001Dto holidaymarket001Dto = new ChbDeadLine001Dto();
		   if(StringUtil.equals(DisclosureDictCodeList.日期类型_自然日.getValue(), repRule.getMakeDateType())){
				if(StringUtil.equals(DisclosureDictCodeList.生成时点类型_后.getValue(), repRule.getMakeType())){
					days = -1*days;
				}
				listDate.add(DateUtil.addDay(sysDate, days));
			}
			else {
				//若map中有，则直接在map中取值，否则查询工作日，并放入map中
				String key = DateUtil.format(sysDate) + GlobalConstant.VERTICAL_LINE + repRule.getMakeType()
					+ GlobalConstant.VERTICAL_LINE + repRule.getMakeValue();
				Date date ;
				if(dateMap.get(key)!=null){
					date = dateMap.get(key);
				}
				else{
				    String dates = DateUtil.format(sysDate);
				    if(days>0){
				    	holidaymarket001Dto = chbDeadLineMapper.getInfoDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
			                       dates, repRule.getMakeType() , days);
				    }
				    else{
				    	holidaymarket001Dto = chbDeadLineMapper.getInfoDeadlineZero(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
			                       dates, repRule.getMakeType());
				    }
				    if(holidaymarket001Dto != null){
				    	date = holidaymarket001Dto.getBusiDate();
				    }
				    else{
				    	if(StringUtil.equals(DisclosureDictCodeList.生成时点类型_后.getValue(), repRule.getMakeType())){
							days = -1*days;
						}
						date = DateUtil.addDay(sysDate, days);
				    }
				    dateMap.put(key, date);
				}
				listDate.add(date);
				//若类型为生产时点后的，若后一天日节假日，则取出前面的节假日
				if(StringUtil.equals(DisclosureDictCodeList.生成时点类型_后.getValue(), repRule.getMakeType())){
					if(date!=null&&!this.isWorkDay(DateUtil.addDay(date, 1), DisclosureDictCodeList.交易市场_中国法定节假日.getValue())){
						holidaymarket001Dto = chbDeadLineMapper.getChbDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
								DateUtil.format(date), repRule.getMakeType() , 1);
						if(holidaymarket001Dto!=null){
							Date busiDate = DateUtil.addDay(holidaymarket001Dto.getBusiDate(), -1);
							while(DateUtil.compareDate(busiDate, date)>0){
								listDate.add(busiDate);
								busiDate = DateUtil.addDay(busiDate, -1);
							}
						}
					}
				}
				//若类型为生产时点前的，若前一天日节假日，则取出前面的节假日
				else{
					if(date!=null&&!this.isWorkDay(DateUtil.addDay(date, -1), DisclosureDictCodeList.交易市场_中国法定节假日.getValue())){
						holidaymarket001Dto = chbDeadLineMapper.getChbDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
								DateUtil.format(date), repRule.getMakeType() , 1);
						if(holidaymarket001Dto!=null){
							Date busiDate = DateUtil.addDay(holidaymarket001Dto.getBusiDate(), 1);
							while(DateUtil.compareDate(busiDate, date)<0){
								listDate.add(busiDate);
								busiDate = DateUtil.addDay(busiDate, 1);
							}
						}
					}
				}
	       }
		   //将传入加减个规则日期获取基准日
//		   Date openDate = null;
		   for (Date execDate : listDate) {
			  //修改查询
			   if(execDate!=null){
			   Date openDate = chbDeadLineMapper.getMJTZLARGESHDATE(prodId, execDate, repRule.getBasisDateType());
				  if(openDate!=null){
					  return openDate;
			  }
			 }
		   }
		   //Date openDate = chbDeadLineMapper.getOpenRevealDate(prodId, date, repRule.getBasisDateType());
		   return null;
	   }
	   	   
}
