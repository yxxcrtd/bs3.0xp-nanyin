package com.joyintech.background.component.common.pack.common.editor;

import org.springframework.beans.propertyeditors.PropertiesEditor;

import com.joyintech.background.component.common.pack.common.util.StringUtil;

/**
 * Short类型的数据装换
 * 
 * @author 何阳阳
 * @version 1.0 2018年1月16日
 * @see ShortEditor
 * @since 1.0
 */
public class ShortEditor extends PropertiesEditor {

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAsText(String text)
        throws IllegalArgumentException {
        if (StringUtil.isEmpty(text)) {
            setValue(null);
        }
        super.setAsText(text);
    }
}
