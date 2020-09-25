package com.joyintech.tams.invest.common.risktrial.dto;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.risktrial.dict.SupervisionStat;
import com.joyintech.tams.invest.common.risktrial.util.RiskUtil;
/**
 * 阈值解析
 * @author 裴家俊
 * @version 1.0 2018年11月30日
 * @see PointDetail
 * @since 1.0
 */
public class PointDetail {

	private String type;
	private LimitPointThreshold limitPointThreshold;
	private ForbidPointThreshold forbidPointThreshold;

	public PointDetail(String vdetailJson,String wdetailJson,String indType) throws Exception {
		//ObjectMapper mapper = new ObjectMapper();
		Map<?, ?> violationMap = JsonUtils.json2Object(vdetailJson, Map.class);
		try {
			this.type = indType;
		} catch(Exception e) {
			throw new Exception("invalid indicator type: " + type);
		}
		
		Map<?, ?> vThreshold1Map = (Map<?, ?>)violationMap.get("threshold_1");
		String vThreshold1Value = (String)vThreshold1Map.get("value");
		
		if(this.type.indexOf(RiskUtil.FORBID)!=-1) {
			String isModel=(String)vThreshold1Map.get("is_model");
			forbidPointThreshold = new ForbidPointThreshold(vThreshold1Value,isModel);
		} else if(this.type.indexOf(RiskUtil.LIMIT)!=-1) {
			limitPointThreshold = new LimitPointThreshold();
			
			String vThreshold1Op = (String)vThreshold1Map.get("op");
			String vThreshold1Unit = (String)vThreshold1Map.get("unit");
			PointThreshold vThreshold1 = new PointThreshold(vThreshold1Op, vThreshold1Value,vThreshold1Unit);
			limitPointThreshold.setViolationThreshold1(vThreshold1);
			
			Map<?, ?> vThreshold2Map = (Map<?, ?>)violationMap.get("threshold_2");
			if(vThreshold2Map != null) {
				String vThreshold2Lnk = (String)vThreshold2Map.get("lnk");
				if(DictCodeList.连接符_并且.getValue().equals(vThreshold2Lnk) || DictCodeList.连接符_或.getValue().equals(vThreshold2Lnk)) {
					String vThreshold2Op = (String)vThreshold2Map.get("op");
					String vThreshold2Value = (String)vThreshold2Map.get("value");
					String vThreshold2Unit = (String)vThreshold2Map.get("unit");
					PointThreshold vThreshold2 = 
							new PointThreshold(vThreshold2Lnk, vThreshold2Op, vThreshold2Value,vThreshold2Unit);
					limitPointThreshold.setViolationThreshold2(vThreshold2);
				}
			}
			
			Map<?, ?> warningMap =  JsonUtils.json2Object(wdetailJson, Map.class);
			if(warningMap != null) {
				Map<?, ?> wThreshold1Map = (Map<?, ?>)warningMap.get("threshold_1");
				if(wThreshold1Map != null) {
					String wThreshold1Value = (String)wThreshold1Map.get("value");
					String wThreshold1Op = (String)wThreshold1Map.get("op");
					String wThreshold1Unit = (String)wThreshold1Map.get("unit");
					PointThreshold wThreshold1 = new PointThreshold(wThreshold1Op, wThreshold1Value,wThreshold1Unit);
					limitPointThreshold.setWarningThreshold1(wThreshold1);
				}
				
				Map<?, ?> wThreshold2Map = (Map<?, ?>)warningMap.get("threshold_2");
				if(wThreshold2Map != null) {
					String wThreshold2Lnk = (String)wThreshold2Map.get("lnk");
					if(DictCodeList.连接符_并且.getValue().equals(wThreshold2Lnk) || DictCodeList.连接符_或.getValue().equals(wThreshold2Lnk)) {
						String wThreshold2Op = (String)wThreshold2Map.get("op");
						String wThreshold2Value = (String)wThreshold2Map.get("value");
						String wThreshold2Unit = (String)wThreshold2Map.get("unit");
						PointThreshold wThreshold2 = 
								new PointThreshold(wThreshold2Lnk, wThreshold2Op, wThreshold2Value,wThreshold2Unit);
						limitPointThreshold.setWarningThreshold2(wThreshold2);
					}
				}
			}
		}
	}
	
	public String showVThreshold() {
		if(this.type.indexOf(RiskUtil.FORBID)!=-1){
			return forbidPointThreshold.toString();
		}
		else{
			return limitPointThreshold.toVString();
		}
	}
	public String showWThreshold() {
		if(this.type.indexOf(RiskUtil.FORBID)!=-1){
			return StringUtil.EMPTY;
		}
		else{
			return limitPointThreshold.toWString();
		}
	}
	public SupervisionStat check(Object value) {
		if(this.type.indexOf(RiskUtil.FORBID)!=-1){
			//---add by jjpei 20190812---解析两列的情况---
			String fobStr=(String)value;
			if(fobStr.indexOf("||")!=-1){
				String[] fobArray=fobStr.trim().split(",");
				fobStr="";
				for(String fobEntry:fobArray){
					String[] fobSubArray=fobEntry.trim().split("\\|\\|");
					if(fobSubArray.length>=1){
						fobStr+=fobSubArray[0].toString()+",";
					}
				}
			}
			//---add by jjpei 20190812---解析两列的情况---
			return forbidPointThreshold.check(fobStr);
		}
		else{
			return limitPointThreshold.check(Double.parseDouble(value.toString()));
		}
	}
	
}
