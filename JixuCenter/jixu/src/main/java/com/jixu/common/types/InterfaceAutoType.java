package com.jixu.common.types;

import java.util.HashMap;

/**
 * @ClassName InterfaceAutoType
 * @author Pan Wei
 * @Date 2017年5月13日 下午2:21:20
 * @Description []
 */

public enum InterfaceAutoType {
	eachDay(1, "每个自然日"), eachBusinessDay(2, "每个工作日"), eachWeek(3, "每个星期"), eachMonth(
			4, "每个月"), eachQuarter(5, "每个季度"), eachYear(6, "每年"), eachNDays(7,
			"每隔N个自然日"), eachNBusinessDays(8, "每隔N个工作日"), userDefine(9, "自定义");

	private int index;
	private String name;

	private InterfaceAutoType(int index, String name) {
		this.name = name;
		this.index = index;
	}

	public static final HashMap<Integer, String> autoTypeHM = new HashMap<Integer, String>();
	static {
		for (InterfaceAutoType autotype : InterfaceAutoType.values()) {
			autoTypeHM.put(autotype.index, autotype.name);
		}
	}

	public static String getName(int index) {
		for (InterfaceAutoType c : InterfaceAutoType.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static InterfaceAutoType getByValue(int value) {
		for (InterfaceAutoType tradeStatus : values()) {
			if (tradeStatus.getIndex() == value) {
				return tradeStatus;
			}
		}
		return null;
	}

	public static String genAutoJobTime(int autoType, String autoTime){
		String[] times=autoTime.split(":");
		String returntime=times[2]+" "+times[1]+" "+times[0];
		switch(InterfaceAutoType.getByValue(autoType)){
		case eachDay:
			returntime=returntime+" * * ?";
			break;
		case eachBusinessDay:
		case eachWeek:
		case eachMonth:
		case eachQuarter:
		case eachYear:
		case eachNDays:
		case eachNBusinessDays:
		default:
			returntime=returntime+" * * ?";
			break;
		}
		return returntime;
	}
}
