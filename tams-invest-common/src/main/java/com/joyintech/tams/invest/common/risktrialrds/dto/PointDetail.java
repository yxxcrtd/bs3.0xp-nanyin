package com.joyintech.tams.invest.common.risktrialrds.dto;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.risktrialrds.dict.SupervisionStat;
import com.joyintech.tams.invest.common.risktrialrds.util.RiskUtil;
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
    private boolean isAbs;

	public PointDetail(String vdetailJson,String adetailJson,String wdetailJson,String indType) throws Exception {
		try {
			this.type = indType;
		} catch(Exception e) {
			throw new Exception("invalid indicator type: " + type);
		}

		if(this.type.indexOf(RiskUtil.FORBID)!=-1) {
			Map<?, ?> warningMap = JsonUtils.json2Object(vdetailJson,Map.class);
			if(warningMap != null) {
				Map<?, ?> wThreshold1Map = (Map<?, ?>) warningMap.get("threshold_1");
				if (wThreshold1Map != null) {
					String wThreshold1Value = (String) wThreshold1Map.get("value");
					String fbModel=(String)wThreshold1Map.get("fb_model");
					forbidPointThreshold = new ForbidPointThreshold(wThreshold1Value,fbModel);
				}
			}
		} else if(this.type.indexOf(RiskUtil.LIMIT)!=-1) {
			limitPointThreshold = new LimitPointThreshold();

			if(StringUtil.isNotEmpty(vdetailJson)) {
				initLimitPointThresh(vdetailJson, "FOUL", indType);
			}
			if(StringUtil.isNotEmpty(adetailJson)) {
				initLimitPointThresh(adetailJson, "APPROVE", indType);
			}
			if(StringUtil.isNotEmpty(wdetailJson)) {
				initLimitPointThresh(wdetailJson, "WARNING", indType);
			}
		}
	}

	private void initLimitPointThresh(String detailJson,String condType,String indType)throws Exception{
		if(condType.equals("FOUL")&&indType.indexOf(RiskUtil.FORBID)!=-1){
			Map<?, ?> warningMap =JsonUtils.json2Object(detailJson,Map.class);
			if(warningMap != null) {
				Map<?, ?> wThreshold1Map = (Map<?, ?>) warningMap.get("threshold_1");
				if (wThreshold1Map != null) {
					String wThreshold1Value = (String) wThreshold1Map.get("value");
					String fbModel=(String)wThreshold1Map.get("fb_model");
					forbidPointThreshold = new ForbidPointThreshold(wThreshold1Value,fbModel);
				}
			}
		}
		else {
			Map<?, ?> warningMap = JsonUtils.json2Object(detailJson, Map.class);
			if (warningMap != null) {
				Map<?, ?> wThreshold1Map = (Map<?, ?>) warningMap.get("threshold_1");
				if (wThreshold1Map != null) {
					String wThreshold1Value = (String) wThreshold1Map.get("value");
					String wThreshold1Op = (String) wThreshold1Map.get("op");
					String wThreshold1Unit = (String) wThreshold1Map.get("unit");
					this.isAbs = wThreshold1Map.get("is_model")!=null?"01".equals((String)wThreshold1Map.get("is_model"))?true:false:false;
					PointThreshold wThreshold1 = new PointThreshold(wThreshold1Op, wThreshold1Value, wThreshold1Unit);
					switch (condType) {
						case "APPROVE":
							limitPointThreshold.setApproveThreshold1(wThreshold1);
							break;
						case "WARNING":
							limitPointThreshold.setWarningThreshold1(wThreshold1);
							break;
						case "FOUL":
							limitPointThreshold.setViolationThreshold1(wThreshold1);
							break;
						default:
							break;
					}
				}

				Map<?, ?> wThreshold2Map = (Map<?, ?>) warningMap.get("threshold_2");
				if (wThreshold2Map != null) {
					String wThreshold2Lnk = (String) wThreshold2Map.get("lnk");
					if (BussinessDictCodeListRds.连接符_并且.getValue().equals(wThreshold2Lnk) || BussinessDictCodeListRds.连接符_或.getValue().equals(wThreshold2Lnk)) {
						String wThreshold2Op = (String) wThreshold2Map.get("op");
						String wThreshold2Value = (String) wThreshold2Map.get("value");
						String wThreshold2Unit = (String) wThreshold2Map.get("unit");
						PointThreshold wThreshold2 =
								new PointThreshold(wThreshold2Lnk, wThreshold2Op, wThreshold2Value, wThreshold2Unit);
						switch (condType) {
							case "APPROVE":
								limitPointThreshold.setApproveThreshold2(wThreshold2);
								break;
							case "WARNING":
								limitPointThreshold.setWarningThreshold2(wThreshold2);
								break;
							case "FOUL":
								limitPointThreshold.setViolationThreshold2(wThreshold2);
								break;
							default:
								break;
						}
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
			return limitPointThreshold.check(isAbs?Math.abs(Double.parseDouble(value.toString())):Double.parseDouble(value.toString()));
		}
	}

	public boolean isAbs() {
		return isAbs;
	}
}
