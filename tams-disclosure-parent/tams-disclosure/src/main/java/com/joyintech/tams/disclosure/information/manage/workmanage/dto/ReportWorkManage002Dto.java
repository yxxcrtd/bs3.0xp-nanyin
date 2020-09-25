/*
 * 
 */
package com.joyintech.tams.disclosure.information.manage.workmanage.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepJob;

/**
 * 
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author ZhangYangFei
 * @version 1.0 2018年11月12日
 * @see ReportWorkManage002Dto
 * @since 1.0
 */
@PageId("SystemBatchQuartzConfig002")
public class ReportWorkManage002Dto extends RepJob implements ITableRow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 329393699533706761L;
	/**
	 * 产品集合
	 */
	private List<ReportWorkManage003Dto> chooseProdTable = new ArrayList<>();
	/**
	 * 规则集合
	 */
	private List<ReportWorkManage004Dto> settedRule = new ArrayList<>();

	/**
	 * 文件类型集合
	 */
	private List<String> fileTypeList = new ArrayList<>();

	private String prdGrpName;
	private String taskGrpName;
	private String yearType;
	private String yearAppoint;
	private List<String> yearAppointList;
	private String monthType;
	private String monthAppoint;
	private List<String> monthAppointList;
	private String dayType;
	private String dayAppoint;
	private List<String> dayAppointList;
	private String weekType;
	private String weekAppoint;
	private List<String> weekAppointList;
	private String hourType;
	private String hourAppoint;
	private List<String> hourAppointList;
	private String minuteType;
	private String minuteAppoint;
	private List<String> minuteAppointList;
	private String operateType;
	private String pageModel;
	private String isProdRelated;
	/**
	 * 报表名称
	 */
	private String repName;

	public String getRepName() {
		return repName;
	}

	public void setRepName(String repName) {
		this.repName = repName;
	}

	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getPrdGrpName() {
		return this.prdGrpName;
	}

	public void setPrdGrpName(String prdGrpName) {
		this.prdGrpName = prdGrpName;
	}

	public String getTaskGrpName() {
		return this.taskGrpName;
	}

	public void setTaskGrpName(String taskGrpName) {
		this.taskGrpName = taskGrpName;
	}

	/**
	 * TODO 需要确认字段 <功能详细描述>
	 * 
	 * @return
	 * @see
	 * @since 1.0
	 */
	public String getQuartsConfig() {
		return this.getLoopDateValue();
	}

	public String getType(String pattern, int idx) {
		if (StringUtil.isEmpty(pattern)) {
			return null;
		} else {
			pattern = pattern.split(" ")[idx];
			if (StringUtil.equals(pattern, "*")) {
				return "*";
			} else {
				return StringUtil.equals(pattern, "?") ? "?" : ".";
			}
		}
	}

	public String getYearType() {
		return StringUtil.isNotEmpty(this.yearType) ? this.yearType : this.getType(this.getQuartsConfig(), 6);
	}

	public void setYearType(String yearType) {
		this.yearType = yearType;
	}

	public String getYearAppoint() {
		return this.yearAppointList != null ? StringUtil.joinString(this.yearAppointList, ",")
				: (this.getQuartsConfig() == null ? "" : this.getQuartsConfig().split(" ")[6]);
	}

	public void setYearAppoint(String yearAppoint) {
		this.yearAppoint = yearAppoint;
	}

	public String getMonthType() {
		return StringUtil.isNotEmpty(this.monthType) ? this.monthType : this.getType(this.getQuartsConfig(), 4);

	}

	public void setMonthType(String monthType) {
		this.monthType = monthType;
	}

	public String getMonthAppoint() {
		return this.monthAppointList != null ? StringUtil.joinString(this.monthAppointList, ",")
				: (this.getQuartsConfig() == null ? "" : this.getQuartsConfig().split(" ")[4]);
	}

	public void setMonthAppoint(String monthAppoint) {
		this.monthAppoint = monthAppoint;
	}

	public String getDayType() {
		return StringUtil.isNotEmpty(this.dayType) ? this.dayType : this.getType(this.getQuartsConfig(), 3);

	}

	public void setDayType(String dayType) {
		this.dayType = dayType;
	}

	public String getDayAppoint() {
		return this.dayAppointList != null ? StringUtil.joinString(this.dayAppointList, ",")
				: (this.getQuartsConfig() == null ? "" : this.getQuartsConfig().split(" ")[3]);
	}

	public void setDayAppoint(String dayAppoint) {
		this.dayAppoint = dayAppoint;
	}

	public String getWeekType() {
		return StringUtil.isNotEmpty(this.weekType) ? this.weekType : this.getType(this.getQuartsConfig(), 5);

	}

	public void setWeekType(String weekType) {
		this.weekType = weekType;
	}

	public String getWeekAppoint() {
		return this.weekAppointList != null ? StringUtil.joinString(this.weekAppointList, ",")
				: (this.getQuartsConfig() == null ? "" : this.getQuartsConfig().split(" ")[5]);
	}

	public void setWeekAppoint(String weekAppoint) {
		this.weekAppoint = weekAppoint;
	}

	public String getHourType() {
		return StringUtil.isNotEmpty(this.hourType) ? this.hourType : this.getType(this.getQuartsConfig(), 2);

	}

	public void setHourType(String hourType) {
		this.hourType = hourType;
	}

	public String getHourAppoint() {
		return this.hourAppointList != null ? StringUtil.joinString(this.hourAppointList, ",")
				: (this.getQuartsConfig() == null ? "" : this.getQuartsConfig().split(" ")[2]);
	}

	public void setHourAppoint(String hourAppoint) {
		this.hourAppoint = hourAppoint;
	}

	public String getMinuteType() {
		return StringUtil.isNotEmpty(this.minuteType) ? this.minuteType : this.getType(this.getQuartsConfig(), 1);

	}

	public void setMinuteType(String minuteType) {
		this.minuteType = minuteType;
	}

	public String getMinuteAppoint() {
		return this.minuteAppointList != null ? StringUtil.joinString(this.minuteAppointList, ",")
				: (this.getQuartsConfig() == null ? "" : this.getQuartsConfig().split(" ")[1]);
	}

	public void setMinuteAppoint(String minuteAppoint) {
		this.minuteAppoint = minuteAppoint;
	}

	private String getConfigPattern(String type, String appoint) {
		return StringUtil.equals(type, "*") ? "*"
				: (String) StringUtil.splitToList(",", StringUtil.getOrElse(appoint, "")).stream()
						.filter(StringUtils::isNotEmpty).distinct().sorted().collect(Collectors.joining(","));
	}

	private String getDayConfigPattern() {
		if (StringUtil.equals(this.dayType, "*")) {
			if (!StringUtil.equals(this.weekType, "*")) {
				this.dayType = "?";
				return "?";
			} else {
				return "*";
			}
		} else {
			return (String) StringUtil.splitToList(",", StringUtil.getOrElse(this.getDayAppoint(), "")).stream()
					.filter(StringUtils::isNotEmpty).distinct().sorted().collect(Collectors.joining(","));
		}
	}

	private String getWeekConfigPattern() {
		if (StringUtil.equals(this.weekType, "*")) {
			this.weekType = "?";
			return "?";
		} else {
			return (String) StringUtil.splitToList(",", StringUtil.getOrElse(this.getWeekAppoint(), "")).stream()
					.filter(StringUtils::isNotEmpty).distinct().sorted().collect(Collectors.joining(","));
		}
	}

	public String toQuartzConfig() {
		return "0 " + this.getConfigPattern(this.minuteType, this.getMinuteAppoint()) + " "
				+ this.getConfigPattern(this.hourType, this.getHourAppoint()) + " " + this.getDayConfigPattern() + " "
				+ this.getConfigPattern(this.monthType, this.getMonthAppoint()) + " " + this.getWeekConfigPattern()
				+ " " + this.getConfigPattern(this.yearType, this.getYearAppoint());
	}

	private String getConfigPatternDesc(String type, String appoint, String name) {
		return StringUtil.equals(type, "*") ? SpringContextUtil.getTextValue("SystemBatchQuartzConfig002.every") + name
				: appoint + name;
	}

	public static String getWeekDesc(String type, String week) {
		return !StringUtil.equals(type, "*") && !StringUtil.equals(type, "?")
				? SpringContextUtil.getTextValue("SystemBatchQuartzConfig002.every")
						+ (String) StringUtil.splitToList(",", week).stream().map((item) -> {
							return SpringContextUtil.getTextValue("SystemBatchQuartzConfig002.week" + item);
						}).collect(Collectors.joining(","))
				: "";
	}

	public static String getDayDesc(String type, String appoint) {
		if (StringUtil.equals(type, "*")) {
			return SpringContextUtil.getTextValue("SystemBatchQuartzConfig002.every")
					+ SpringContextUtil.getTextValue("SystemBatchQuartzConfig002.day");
		} else {
			return StringUtil.equals(type, "?") ? ""
					: appoint + SpringContextUtil.getTextValue("SystemBatchQuartzConfig002.day");
		}
	}

	public String toQuartzConfigDesc() {
		return this.getConfigPatternDesc(this.getYearType(), this.getYearAppoint(),
				SpringContextUtil.getTextValue("SystemBatchQuartzConfig002.year"))
				+ this.getConfigPatternDesc(this.getMonthType(), this.getMonthAppoint(),
						SpringContextUtil.getTextValue("SystemBatchQuartzConfig002.month"))
				+ getWeekDesc(this.getWeekType(), this.getWeekAppoint())
				+ getDayDesc(this.getDayType(), this.getDayAppoint())
				+ this.getConfigPatternDesc(this.getHourType(), this.getHourAppoint(),
						SpringContextUtil.getTextValue("SystemBatchQuartzConfig002.hour2"))
				+ this.getConfigPatternDesc(this.getMinuteType(), this.getMinuteAppoint(),
						SpringContextUtil.getTextValue("SystemBatchQuartzConfig002.minute2"));
	}

	public String getPageModel() {
		return this.pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public List<String> getYearAppointList() {
		return this.yearAppointList;
	}

	public void setYearAppointList(List<String> yearAppointList) {
		this.yearAppointList = yearAppointList;
	}

	public List<String> getMonthAppointList() {
		return this.monthAppointList;
	}

	public void setMonthAppointList(List<String> monthAppointList) {
		this.monthAppointList = monthAppointList;
	}

	public List<String> getDayAppointList() {
		return this.dayAppointList;
	}

	public void setDayAppointList(List<String> dayAppointList) {
		this.dayAppointList = dayAppointList;
	}

	public List<String> getWeekAppointList() {
		return this.weekAppointList;
	}

	public void setWeekAppointList(List<String> weekAppointList) {
		this.weekAppointList = weekAppointList;
	}

	public List<String> getHourAppointList() {
		return this.hourAppointList;
	}

	public void setHourAppointList(List<String> hourAppointList) {
		this.hourAppointList = hourAppointList;
	}

	public List<String> getMinuteAppointList() {
		return this.minuteAppointList;
	}

	public void setMinuteAppointList(List<String> minuteAppointList) {
		this.minuteAppointList = minuteAppointList;
	}

	public List<String> getFileTypeList() {
		return fileTypeList;
	}

	public void setFileTypeList(List<String> fileTypeList) {
		this.fileTypeList = fileTypeList;
	}

	public List<ReportWorkManage003Dto> getChooseProdTable() {
		return chooseProdTable;
	}

	public void setChooseProdTable(List<ReportWorkManage003Dto> chooseProdTable) {
		this.chooseProdTable = chooseProdTable;
	}

	public List<ReportWorkManage004Dto> getSettedRule() {
		return settedRule;
	}

	public void setSettedRule(List<ReportWorkManage004Dto> settedRule) {
		this.settedRule = settedRule;
	}

	public String getIsProdRelated() {
		return isProdRelated;
	}

	public void setIsProdRelated(String isProdRelated) {
		this.isProdRelated = isProdRelated;
	}

}
