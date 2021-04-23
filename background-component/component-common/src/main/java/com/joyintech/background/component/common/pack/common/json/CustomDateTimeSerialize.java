package com.joyintech.background.component.common.pack.common.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.joyintech.background.component.common.pack.common.util.DateUtil;

/**
 * 自定义日期类型的json解析
 * 
 * @author 强成西
 * @version 1.0 2017年8月25日
 * @see CustomDateTimeSerialize
 * @since 1.0
 */
public class CustomDateTimeSerialize extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
        if (value != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DEFAULT_DATETIME_FORMAT);
            gen.writeString(sdf.format(value));
        }
    }
}