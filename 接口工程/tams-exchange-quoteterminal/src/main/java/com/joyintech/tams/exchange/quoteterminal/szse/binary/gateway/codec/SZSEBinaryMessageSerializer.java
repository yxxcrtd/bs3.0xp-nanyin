package com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway.codec;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class SZSEBinaryMessageSerializer implements Serializer {

    private static final Logger logger = LoggerFactory.getLogger(SZSEBinaryMessageSerializer.class);

    private Method resumeMethod;

    @Override
    public byte[] serialize(Object object) {
        if(object instanceof Message) {
            Message message = (Message) object;
            return message.toSZSEBinaryMessage();
        }
        return new byte[0];
    }

    @Override
    public <T> T deserialize(Class<T> objClass, byte[] data) {
        try {
            T t = objClass.newInstance();
            if (t instanceof Message) {
                if (resumeMethod == null) {
                    resumeMethod = objClass.getMethod("resumeMessageFrom", byte[].class);
                }
                resumeMethod.invoke(t, data);
                return t;
            }
        } catch (Exception e) {
            logger.error("deserialize error: ", e);
        }
        return null;
    }

    @Override
    public byte serializerAlgo() {
        return 1;
    }
}
