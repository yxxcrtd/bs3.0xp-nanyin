package com.joyintech.tams.exchange.common.proto.serializer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class TickBeanSerializer implements RedisSerializer<QuoteProto.TickBean> {
    protected static final Logger logger = LoggerFactory.getLogger(TickBeanSerializer.class);

    @Override
    public byte[] serialize(QuoteProto.TickBean tickBean) throws SerializationException {
        return tickBean.toByteArray();
    }

    @Override
    public QuoteProto.TickBean deserialize(byte[] bytes) throws SerializationException {
        try {
            return QuoteProto.TickBean.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            logger.info("deserialize tickBean failed:{}", e.getMessage());
        }
        return null;
    }
}
