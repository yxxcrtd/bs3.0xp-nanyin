package com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.ui.Model;

import com.joyintech.framework.base.BaseDictCode;
import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;

public enum MonitorDictCode implements BaseDictCode  {
    /**
     * 同业监测报表基础标题
     */
    主体名称("CUSTOMER_NAME"),
    失效日("MDATE"),
    期限("TERMS"),
    
    总额("TOTALAMT"),
    已用("USEDAMT"),
    剩余("REMAINDERAMT")
	;
	
	
	/**
	 * 对应于数据字典code
	 */
	private String value;

	MonitorDictCode(String value) {
		this.value = value;
	}

	/**
	 * @param value
	 *            数据字典值
	 * @return DictCode
	 * @see
	 * @since 1.0
	 */
	public static DictCode get(String value) {
		DictCode[] values = DictCode.values();
		for (DictCode code : values) {
			if (StringUtil.equals(value, code.getValue())) {
				return code;
			}
		}
		return null;
	}

	/**
	 * 获取 value
	 *
	 * @return value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置 value
	 *
	 * @param value
	 *            value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 通过value生成枚举
	 *
	 * @param value
	 *            value值
	 * @return DictCode
	 */
	public static DictCode fromValue(String value) {
		for (DictCode dictCode : DictCode.values()) {
			if (dictCode.getValue().equals(value)) {
				return dictCode;
			}
		}
		return null;
	}

	/**
	 * 自定义过滤参数
	 *
	 * @author wanggengyun
	 * @param dictCode:参数类型
	 * @param str:需要过滤的参数
	 * @param flag:true-包含，false-排除
	 * @return Model
	 * @see
	 * @since 1.0
	 */
	public static Model checkDictCode(Model model, DictCode dictCode, String[] str, boolean flag) {
		DataDict dataDict = CacheUtil.getDictCode(dictCode.getValue());
		List<? extends DataDictItem> itemList = dataDict.getItems();
		List<SelectOptionDto> options = new ArrayList<SelectOptionDto>();
		for (DataDictItem dictItem : itemList) {
			String itemCode = dictItem.getItemCode();
			if (flag) {
				if (Arrays.asList(str).contains(itemCode)) {
					SelectOptionDto dto = new SelectOptionDto();
					dto.setId(dictItem.getItemCode());
					dto.setText(dictItem.getItemValue());
					dto.setCol(dictItem.getDictCode());
					options.add(dto);
				}
			} else {
				if (!Arrays.asList(str).contains(itemCode)) {
					SelectOptionDto dto = new SelectOptionDto();
					dto.setId(dictItem.getItemCode());
					dto.setText(dictItem.getItemValue());
					dto.setCol(dictItem.getDictCode());
					options.add(dto);
				}
			}
		}
		return model.addAttribute(dictCode.getValue(), JsonUtils.objectArray2Json(options.toArray()));
	}

}
