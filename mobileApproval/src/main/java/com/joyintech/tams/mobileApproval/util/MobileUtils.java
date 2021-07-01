/**
 * <b>文件名：</b>MobileUtils<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/1 13:50<br>
 * Copyright(c) 2020 Joyin Tech,LTD. All Rights Reserved.
 */
package com.joyintech.tams.mobileApproval.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;

import org.apache.commons.lang.StringEscapeUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>方法：</b>MobileUtils<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/1 13:50<br>
 */
public class MobileUtils {

    public static String Objetc2Json(String code,Object data,String message){
        JSONObject object = new JSONObject();
        object.put("code", code);
        object.put("data", data);
        object.put("message", message);
        return JSON.toJSONStringWithDateFormat(object, DateUtil.DEFAULT_DATE_FORMAT_EN);
//        return StringEscapeUtils.unescapeJavaScript(object.toJSONString());
    }
    
    /**
     * 处理指令信息
     * @param bussTrdTransferOrder
     */
    public static void dealBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
    	for (BussTrdTransferOrderDto vo : bussTrdTransferOrder) {
			vo.setCcy(vo.getCcyText());
			vo.setPayType(vo.getPayTypeText());
			vo.setIsNeedSend(SysDataUtil.getNameFromDataDict(DictCode.是否, vo.getIsNeedSend()));
			vo.setInOutBank(SysDataUtil.getNameFromDataDict(DictCode.资产来源,vo.getInOutBank()));
		}
    }
    /**
     * 处理风险试算信息
     * @param riskTrialRetDto
     */
    public static void dealRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
    	for (RiskTrialRetDto vo : riskTrialRetDto) {
			vo.setKriType(vo.getKriTypeCn());
			if("0".equals(vo.getKriStatus())) {
				vo.setKriStatus("正常");
			}
			if("1".equals(vo.getKriStatus())) {
				vo.setKriStatus("预警");
			}
			if("2".equals(vo.getKriStatus())) {
				vo.setKriStatus("审批");
			}
			if("3".equals(vo.getKriStatus())) {
				vo.setKriStatus("违规");
			}
			if("4".equals(vo.getKriStatus())) {
				vo.setKriStatus("异常");
			}
		}
    }

	/**
	 * 转换为保留小数点2位
	 */
	public static BigDecimal transformTwoDecimalPoint(BigDecimal bigDecimal) {
		return bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 根据数据字典币种的codeName获取value
	 */
	public static String getValuefromCodeNameAndCode(String codeName,DictCode dictCode){
	//	List<String> valuelist = DictCodeList.valuesfromDictCode(DictCode.币种);
		DictCodeList[] values = DictCodeList.values();
		for (DictCodeList code : values) {
			if(code.getCodeName().equals(codeName)){
				return code.getValue();
			}
		}
		return null;
	}
}
