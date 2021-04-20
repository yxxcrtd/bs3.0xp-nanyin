package com.joyintech.background.component.common.pack.common.editor;

import org.springframework.beans.propertyeditors.PropertiesEditor;

/**
 * Long数据类型转换
 * @author 何阳阳
 * @version 1.0 2018年1月16日
 * @see LongEditor
 * @since 1.0
 */
public class LongEditor extends PropertiesEditor {

    @Override
    public void setAsText(String text)
        throws IllegalArgumentException {
        if (text == null || text.equals("")) {
            text = "0";
        }
        if (text.indexOf(",") != -1) {
            text = text.replaceAll("\\,", "");
        }
        setValue(Long.parseLong(text));
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}