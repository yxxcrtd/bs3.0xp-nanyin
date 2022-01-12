package com.joyintech.background.component.common.pack.common.json;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 自定义日期类型的json解析
 * @author 强成西
 * @version 1.0 2017年8月25日
 * @see CustomDateDeserialize
 * @since 1.0
 */
public class CustomDateDeserialize extends JsonDeserializer<Date> {

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
                date = DateUtils.parseDate(text,"yyyy-MM-dd","yyyy-MM-dd HH:mm:ss","yyyy-MM-dd'T'HH:mm:ss.SSSXXX","yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        return date;
    }
}