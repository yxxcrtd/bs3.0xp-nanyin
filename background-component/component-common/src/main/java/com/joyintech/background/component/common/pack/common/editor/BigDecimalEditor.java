package com.joyintech.background.component.common.pack.common.editor;

import java.math.BigDecimal;

import org.springframework.beans.propertyeditors.PropertiesEditor;

import com.joyintech.background.component.common.pack.common.util.StringUtil;

/**
 * BigDecimal类型转换
 * @author 何阳阳
 * @version 1.0 2018年1月16日
 * @see BigDecimalEditor
 * @since 1.0
 */
public class BigDecimalEditor extends PropertiesEditor {

    @Override
    public void setAsText(String text)
        throws IllegalArgumentException {
        if (StringUtil.isBlank(text)) {
            setValue(null);
        }
        else if (text.indexOf(",") != -1) {
            setValue(text.replaceAll("\\,", ""));
        }
        else {
            setValue(new BigDecimal(text));
        }
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}