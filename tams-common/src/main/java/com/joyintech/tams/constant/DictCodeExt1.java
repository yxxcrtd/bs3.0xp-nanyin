package com.joyintech.tams.constant;

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

/**
 * 数据字典枚举
 *
 * @author zhengxiaolian
 * @version 1.0 2022年07月20日
 * @see DictCodeListExt5
 * @since 1.0
 */
public enum DictCodeExt1 implements BaseDictCode {
    发行方式_信批("ISSUING_WAY"),

    是否是指数型产品("IS_INDEX"),

    是否是客户周期型产品("IS_PERIOD"),

    开放频率("OPEN_FREQ"),

    ;
    private String value;

    DictCodeExt1(String value) {
        this.value = value;
    }

    /**
     * @param value
     *            数据字典值
     * @return DictCodeExt1
     * @see
     * @since 1.0
     */
    public static DictCodeExt1 get(String value) {
        DictCodeExt1[] values = DictCodeExt1.values();
        for (DictCodeExt1 code : values) {
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
     * @return DictCodeExt1
     */
    public static DictCodeExt1 fromValue(String value) {
        for (DictCodeExt1 dictCode : DictCodeExt1.values()) {
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
    public static Model checkDictCode(Model model, DictCodeExt1 dictCode, String[] str, boolean flag) {
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
