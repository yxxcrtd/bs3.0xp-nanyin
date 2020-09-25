/*
 * 文件名：BigDecimalSerializer.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：JIAOXUJIN copy from com.joyintech.tams.valuation.report.common.util.BigDecimalSerializer
 * 修改时间：2017年2月9日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.common.util;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @version 1.0 2019年8月22日
 * @see BigDecimalSerializer
 * @since 1.0
 */
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException, JsonProcessingException {
        // TODO Auto-generated method stub
        if (value == null) {
            gen.writeString(StringUtils.EMPTY);
        }
        else {
            gen.writeString(value.stripTrailingZeros().toPlainString());
        }
    }

}
