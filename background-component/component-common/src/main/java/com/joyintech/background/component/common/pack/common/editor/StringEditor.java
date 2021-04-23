package com.joyintech.background.component.common.pack.common.editor;

import org.springframework.beans.propertyeditors.PropertiesEditor;

import com.joyintech.background.component.common.pack.common.util.StringUtil;


/**
 * 过滤特殊字符
 * @author 何阳阳
 * @version 1.0 2018年1月16日
 * @see StringEditor
 * @since 1.0
 */
public class StringEditor extends PropertiesEditor {

    @Override
    public void setAsText(String text)
        throws IllegalArgumentException {
        text = StringUtil.trimHtmlTag(text);
//        text = StringUtil.trim(StringUtil.replaceSpecialStr(text));
        setValue(text);
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}