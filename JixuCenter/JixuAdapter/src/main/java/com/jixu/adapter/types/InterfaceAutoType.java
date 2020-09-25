package com.jixu.adapter.types;

import java.util.HashMap;

/**
 * @ClassName InterfaceAutoType
 * @author Pan Wei
 * @Date 2017年5月13日 下午2:21:20
 * @Description []
 */

public enum InterfaceAutoType {
	eachDay(1, "每个自然日"), eachBusinessDay(2, "每个工作日"), eachWeek(3, "每个星期"), eachMonth(
			4, "每个月"), eachNMinutes(11, "每N分钟"), userDefine(9, "自定义");

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

	public static String genAutoJobTime(int autoType, int nDays, String autoTime){
		if(autoTime==null){
			autoTime="00:00:00";
		}
		String[] times=autoTime.split(":");
		//如果:分割后没有3个字段，则判定为自定义时间
		String returntime=autoTime;
		if(times.length==3){
			returntime=times[2]+" "+times[1]+" "+times[0];
		}
		switch(InterfaceAutoType.getByValue(autoType)){
		case eachDay:
			returntime=returntime+" * * ?";
			break;
		case eachBusinessDay:
			returntime=returntime+" * * W";
			break;
		case eachWeek:
			int weekDay=nDays-1;
			if(weekDay==0)
				weekDay=7;
			returntime=returntime+" * * "+weekDay;
			break;
		case eachMonth:
			returntime=returntime+" " + nDays + " * ?";
			break;
		case eachNMinutes:
			returntime="0 */"+nDays+" * * * ?";
			break;
		case userDefine:
			returntime=autoTime;
			break;
		/*case eachNDays:
		case eachNBusinessDays:*/
		default:
			returntime=returntime+" * * ?";
			break;
		}
		return returntime;
	}
}
