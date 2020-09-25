package com.jixu.common.constant;

import com.joyintech.framework.base.BaseDictCode;

public enum JixuDictCode implements BaseDictCode{
	/**
	   *       适配器类型
	 */
	ADAPTER_TYPE("ADAPTER_TYPE"),
	
	/**
	 * 数据字典导出文件SEQ类型
	 */
	ADAPTER_EXPFILESEQ_TYPE("ADAPTER_EXPFILESEQ_TYPE"),
	;

	/**
	 * 对应于数据字典code
	 */
	private String value;

	JixuDictCode(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public void setValue(String value) {
		// TODO Auto-generated method stub
		this.value = value;
	}

	/**
	 * 通过value生成枚举
	 *
	 * @param value
	 *            value值
	 * @return JixuDictCode
	 */
	public static JixuDictCode fromValue(String value) {
		for (JixuDictCode jixudictCode : JixuDictCode.values()) {
			if (jixudictCode.getValue().equals(value)) {
				return jixudictCode;
			}
		}
		return null;
	}
}
