package com.joyintech.tams.exchange.service.trade.server.agent.jsd;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author lifangpeng
 * @date 2021/11/4
 */
public class MessageManager {
    private static final Logger logger = LoggerFactory.getLogger(MessageManager.class);

    private static Map<String, Field[]> CLASS_FIELDS = Maps.newConcurrentMap();


    public static <T> T getObjectFromStrings(List<String> strings, Class<T> cls) {
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

    public static Field[] getField(Class cls) {
        Field[] fields = CLASS_FIELDS.get(cls.getName());
        if (Objects.isNull(fields)) {
            fields = cls.getDeclaredFields();
            CLASS_FIELDS.put(cls.getName(), fields);
        }
        return fields;
    }

    private static void setField(Field field, Object object, String value) {
        try {
            String fieldType = field.getType().getName();
            if (fieldType.equals("java.lang.String")) {
                field.set(object, value);
            } else if (fieldType.equals("int")) {
                if (!Strings.isNullOrEmpty(value)) {
                    field.set(object, Integer.valueOf(value));
                }
            } else if (fieldType.equals("char")) {
                field.set(object, value.charAt(0));
            } else if (fieldType.equals("long")) {
                if (!Strings.isNullOrEmpty(value)) {
                    field.set(object, Long.valueOf(value));
                }
            } else if (fieldType.equals("double")) {
                if (!Strings.isNullOrEmpty(value)) {
                    field.set(object, Double.valueOf(value));
                }
            } else if (fieldType.equals("float")) {
                if (!Strings.isNullOrEmpty(value)) {
                    field.set(object, Float.valueOf(value));
                }
            } else {
                field.set(object, null);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
