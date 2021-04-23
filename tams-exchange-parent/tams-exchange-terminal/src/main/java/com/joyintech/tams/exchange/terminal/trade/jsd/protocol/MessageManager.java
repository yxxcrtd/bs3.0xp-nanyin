package com.joyintech.tams.exchange.terminal.trade.jsd.protocol;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req.ReqData;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req.ReqHeader;
import com.joyintech.tams.exchange.terminal.utils.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MessageManager {

    private static final Logger logger = LoggerFactory.getLogger(MessageManager.class);

    private static Map<String, ProtocolInfo> messageInfoMap = Maps.newHashMap();

    private Map<String, Field[]> classFields = Maps.newConcurrentMap();

    private Map<Integer, MessageBean> reqMessages = Maps.newConcurrentMap();

    static {
        try {
            List<Class<?>> classList = ClassUtil.getClasses(ReqData.class.getPackage().getName());
            logger.info("initReqInfo, size = {}", classList.size());
            classList.forEach(cls -> {
                ProtocolInfo protocolInfo = (ProtocolInfo) cls.getAnnotation(ProtocolInfo.class);
                messageInfoMap.put(cls.getName(), protocolInfo);
            });
        } catch (Exception e) {
            logger.error("initReqInfo: error:{}", e);
        }
    }

    public Field[] getField(Class cls) {
        Field[] fields = classFields.get(cls.getName());
        if (Objects.isNull(fields)) {
            fields = cls.getDeclaredFields();
            classFields.put(cls.getName(), fields);
        }
        return fields;
    }

    private void setField(Field field, Object object, String value) {
        try {
            String fieldType = field.getType().getName();
            if (fieldType.equals("java.lang.String")) {
                field.set(object, value);
            } else if (fieldType.equals("int")) {
                if (!Strings.isNullOrEmpty(value))
                    field.set(object, Integer.valueOf(value));
            } else if (fieldType.equals("char")) {
                field.set(object, value.charAt(0));
            } else if (fieldType.equals("long")) {
                if (!Strings.isNullOrEmpty(value))
                    field.set(object, Long.valueOf(value));
            } else if (fieldType.equals("double")) {
                if (!Strings.isNullOrEmpty(value))
                    field.set(object, Double.valueOf(value));
            } else if (fieldType.equals("float")) {
                if (!Strings.isNullOrEmpty(value))
                    field.set(object, Float.valueOf(value));
            } else {
                field.set(object, null);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


    public <T> T getObjectFromStrings(List<String> strings, Class<T> cls) {
        try {
            Field[] fields = getField(cls);
            T t = cls.newInstance();
            for (int i = 0; i < fields.length; i++) {
                setField(fields[i], t, strings.get(i));
            }
            return t;
        } catch (Exception e) {
            logger.error(e.getMessage());

        }
        return null;
    }


    public boolean addMessage(ReqHeader reqHeader) {
        ProtocolInfo protocolInfo = messageInfoMap.get(reqHeader.getClass().getName());
        if (Objects.nonNull(protocolInfo)) {
            reqHeader.functionCode = protocolInfo.functionCode();
            MessageBean messageBean = new MessageBean();
            messageBean.setReqId(reqHeader.reqId);
            messageBean.setFunctionCode(protocolInfo.functionCode());
            messageBean.setHandledZeroPackage(false);
            try {
                Field field = reqHeader.getClass().getField("isTotal");

                messageBean.setIsTotal(field.getChar(reqHeader));
            } catch (Exception e) {
                logger.debug("没有返回合计");
                messageBean.setIsTotal('0');
            }
            messageBean.setProtocolInfo(protocolInfo);
            reqMessages.put(reqHeader.reqId, messageBean);
            return true;
        }
        return false;
    }

    public MessageBean getMessage(int reqId) {
        return reqMessages.get(reqId);
    }

    public void removeMessage(int reqId) {
        reqMessages.remove(reqId);
    }

}
