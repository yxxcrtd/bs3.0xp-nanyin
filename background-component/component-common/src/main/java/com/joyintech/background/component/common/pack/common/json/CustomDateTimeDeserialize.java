package com.joyintech.background.component.common.pack.common.json;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.joyintech.background.component.common.pack.common.util.DateUtil;
import com.joyintech.background.component.common.pack.common.util.StringUtil;

/**
 * 自定义日期类型的json解析
 * @author 强成西
 * @version 1.0 2017年8月25日
 * @see CustomDateTimeDeserialize
 * @since 1.0
 */
public class CustomDateTimeDeserialize extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {

        Date date = null;
        String text = jp.getText();
        try {
            if (StringUtil.isNotEmpty(text)) {
                date = new Date(Long.parseLong(text));
            }
        }
        catch (NumberFormatException e) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DEFAULT_DATETIME_FORMAT);
                date = sdf.parse(text);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        return date;
    }
}