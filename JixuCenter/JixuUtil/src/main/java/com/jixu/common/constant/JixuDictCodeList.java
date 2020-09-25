package com.jixu.common.constant;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;

public enum JixuDictCodeList implements BaseDictCodeList {

	All(JixuDictCode.ADAPTER_TYPE, "0", "所有适配器"),
	Zg_Adapter(JixuDictCode.ADAPTER_TYPE, "1", "资管系统适配器"),
	Other_Adapter(JixuDictCode.ADAPTER_TYPE, "2", "三方系统适配器"),
	Dis_Adapter(JixuDictCode.ADAPTER_TYPE, "3", "信息披露适配器"),
	Wind_Adapter(JixuDictCode.ADAPTER_TYPE, "4", "wind接口适配器"),
	Sale_Adapter(JixuDictCode.ADAPTER_TYPE, "5", "销售接口适配器"),
	Risk_Adapter(JixuDictCode.ADAPTER_TYPE, "6", "事后风险适配器"),
	Val_Adapter(JixuDictCode.ADAPTER_TYPE, "7", "估值核算适配器"),
	Chb_Adapter(JixuDictCode.ADAPTER_TYPE, "8", "中债系统适配器"),
	Funds_Adapter(JixuDictCode.ADAPTER_TYPE, "9", "资金系统适配器"),
	Invest_Adapter(JixuDictCode.ADAPTER_TYPE, "10", "投资交易适配器"),
	
	
	EXPSEQ_01_TYPE(JixuDictCode.ADAPTER_EXPFILESEQ_TYPE, "01", "每日零点序列归零"),
	EXPSEQ_02_TYPE(JixuDictCode.ADAPTER_EXPFILESEQ_TYPE, "02", "序列一直叠加"),
	;

	private String value;

	public void setDictCode(JixuDictCode jixudictCode) {
		this.jixudictCode = jixudictCode;
	}

	private JixuDictCode jixudictCode;
	/**
	 * 明细名称
	 */
	private String codeName;

	JixuDictCodeList(JixuDictCode jixudictCode, String value, String codeName) {
		this.jixudictCode = jixudictCode;
		this.value = value;
		this.setCodeName(codeName);
	}

	/**
	 * 获取数据字典详细类
	 * 
	 * @param jixudictCode
	 * @param value
	 * @return
	 */
	public static JixuDictCodeList from(JixuDictCode jixudictCode, String value) {
		JixuDictCodeList[] values = JixuDictCodeList.values();
		for (JixuDictCodeList code : values) {
			if (StringUtil.equals(value, code.getValue()) && code.getDictCode() == jixudictCode) {
				return code;
			}
		}
		return null;
	}

	/**
	 * 获取数据字典明细
	 * 
	 * @param jixudictCode
	 * @return
	 */
	public static List<SelectOptionDto> fromDictCode(JixuDictCode jixudictCode) {
		List<SelectOptionDto> result = new ArrayList<>();
		JixuDictCodeList[] values = JixuDictCodeList.values();
		for (JixuDictCodeList code : values) {
			if (code.getDictCode() == jixudictCode) {
				SelectOptionDto option = new SelectOptionDto();
				option.setId(code.getValue());
				option.setText(code.getCodeName());
				result.add(option);
			}
		}
		return result;
	}

	/**
	 * 获取数据字典明细list
	 * 
	 * @param dictCode
	 * @return
	 */
	public static List<String> valuesfromDictCode(JixuDictCode jixudictCode) {
		List<String> result = new ArrayList<>();
		JixuDictCodeList[] values = JixuDictCodeList.values();
		for (JixuDictCodeList code : values) {
			if (code.getDictCode() == jixudictCode) {
				result.add(code.getCodeName());
			}
		}
		return result;
	}

	@Override
	public JixuDictCode getDictCode() {
		return jixudictCode;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getCodeName() {
		return codeName;
	}

	@Override
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

}
