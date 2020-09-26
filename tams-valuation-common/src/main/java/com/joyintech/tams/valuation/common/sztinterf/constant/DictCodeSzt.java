/*
 * 文件名：DictCodeSzt.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改时间：2019年12月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.sztinterf.constant;

import com.joyintech.framework.base.BaseDictCode;
import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum DictCodeSzt implements BaseDictCode {

    /**
     * 深圳通前台页面调用接口接口传递参数字段
     */
    深圳通页面调用接口参数字段("SZT_PAGE_INTERF_COLUMN"),

    /**
     * 深圳通接口查询参数字段
     */
    深圳通接口查询参数字段("SZT_QUERY_COLUMN"),

    /**
     * 深圳通科目信息接口字段
     */
    深圳通接口报文公共接口字段("SZT_COMMON_COLUMN"),
    /**
     * 深圳通科目信息接口字段
     */
    深圳通接口科目信息报文字段("SZT_SUBJECTINFO_COLUMN"),

    /**
     * 深圳通估值接口字段
     */
    深圳通接口估值报文字段("SZT_BOOKVALUATION_COLUMN"),

    /**
     * 深圳通科目余额接口字段
     */
    深圳通接口科目余额报文字段("SZT_SUBJECTBALANCE_COLUMN"),

    /**
     * 深圳通接口报文返回字段
     */
    深圳通接口报文返回字段("SZT_REC_COLUMN"),

    深圳通接口发送结果("SZT_SEND_RESULT"),

    ;

    /**
     * 对应于数据字典code
     */
    private String value;

    DictCodeSzt(String value) {
        this.value = value;
    }

    /**
     * @param value
     *            数据字典值
     * @return DictCode
     * @see
     * @since 1.0
     */
    public static DictCodeSzt get(String value) {
        DictCodeSzt[] values = DictCodeSzt.values();
        for (DictCodeSzt code : values) {
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
    public static DictCodeSzt fromValue(String value) {
        for (DictCodeSzt dictCode : DictCodeSzt.values()) {
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
    public static Model checkDictCode(Model model, DictCodeSzt dictCodeSzt, String[] str, boolean flag) {
        DataDict dataDict = CacheUtil.getDictCode(dictCodeSzt.getValue());
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
        return model.addAttribute(dictCodeSzt.getValue(), JsonUtils.objectArray2Json(options.toArray()));
    }
}
