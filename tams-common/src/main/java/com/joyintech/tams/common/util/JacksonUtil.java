package com.joyintech.tams.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public final class JacksonUtil {

    private static Logger log = LoggerFactory.getLogger(JacksonUtil.class);
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT); //化化输出
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS); //序列化空的POPJ
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); //不将date转化成timestamp
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //忽略未知属性
        mapper.disable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT); //不将空转化为null
        mapper.disable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES); //允许没有引号的字段名（非标准）
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    private JacksonUtil() {
    }

    public static <T> String toJson(T t) {
        return toJson(t, null);
    }

    public static <T> T jsonTo(String json, TypeReference<T> typeReference) {
        return jsonTo(json, typeReference, null);
    }

    public static <T> T jsonToBean(String json, Class<T> clazz) {
        return jsonToBean(json, clazz, null);
    }

    public static <T> T fileToBean(File file, Class<T> clazz) {
        return fileToBean(file, clazz, null);
    }

    public static <T> String toJson(T t, ObjectMapper objectMapper) {
        if (Objects.isNull(t))
            return null;
        try {
            return objectMapper == null ? mapper.writeValueAsString(t) : objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            log.error("to json error:" + t, e);
            return null;
        }
    }

    public static <T> T jsonToBean(String json, Class<T> clazz, ObjectMapper objectMapper) {
        if (StringUtils.isEmpty(json))
            return null;
        try {
            return objectMapper == null ? mapper.readValue(json, clazz) : objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("json to bean error:" + json, e);
            return null;
        }
    }

    public static <T> T fileToBean(File file, Class<T> clazz, ObjectMapper objectMapper) {
        if (!file.exists())
            return null;
        try {
            return objectMapper == null ? mapper.readValue(file, clazz) : objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            log.error("file to bean error:" + file.getName(), e);
            return null;
        }
    }

    public static <T> T jsonTo(String json, TypeReference<T> typeReference, ObjectMapper objectMapper) {
        if (StringUtils.isEmpty(json))
            return null;
        try {
            return objectMapper == null ? mapper.readValue(json, typeReference) : objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            log.error("json to map error:" + json, e);
            return null;
        }
    }

    public static <T> T jsonTo(String json, Class<T> clazz, ObjectMapper objectMapper) {
        if (StringUtils.isEmpty(json))
            return null;
        try {
            return mapper.readValue(json, new TypeReference<T>(){});
        } catch (Exception e) {
            log.error("json to map error:" + json, e);
            return null;
        }
    }
    

    public static String toJSONString(Object obj) {
        return obj != null ? toJSONString(obj, () -> "") : "";
    }

    public static String toJSONString(Object obj, Supplier<String> defaultSupplier) {
        try {
            return obj != null ? mapper.writeValueAsString(obj) : defaultSupplier.get();
        }
        catch (Exception e) {
            log.error(String.format("toJSONString %s", obj != null ? obj.toString() : "null"), e);
        }
        return defaultSupplier.get();
    }

    public static <T> T toJavaObject(String value, Class<T> tClass) {
        return !StringUtils.isEmpty(value) ? toJavaObject(value, tClass, () -> null) : null;
    }

    public static <T> T toJavaObject(Object obj, Class<T> tClass) {
        return obj != null ? toJavaObject(toJSONString(obj), tClass, () -> null) : null;
    }

    public static <T> T toJavaObject(String value, Class<T> tClass, Supplier<T> defaultSupplier) {
        try {
            if (StringUtils.isEmpty(value)) {
                return defaultSupplier.get();
            }
            return mapper.readValue(value, tClass);
        }
        catch (Exception e) {
            log.error(String.format("toJavaObject exception: <%s>, <%s>", value, tClass), e);
        }
        return defaultSupplier.get();
    }

    public static <T> List<T> toJavaObjectList(String value, Class<T> tClass) {
        return StringUtils.isEmpty(value) ? toJavaObjectList(value, tClass, () -> new ArrayList<>()) : new ArrayList<>();
    }

    public static <T> List<T> toJavaObjectList(Object obj, Class<T> tClass) {
        return obj != null ? toJavaObjectList(toJSONString(obj), tClass, () -> new ArrayList<>()) : new ArrayList<>();
    }

    public static <T> List<T> toJavaObjectList(String value, Class<T> tClass,
                                               Supplier<List<T>> defaultSupplier) {
        try {
            if (StringUtils.isEmpty(value)) {
                return defaultSupplier.get();
            }
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class,
                tClass);
            return mapper.readValue(value, javaType);
        }
        catch (Exception e) {
            log.error(String.format("toJavaObjectList exception: <%s>, <%s>", value, tClass), e);
        }
        return defaultSupplier.get();
    }

    public static <T> T jsonCopy(Object obj, Class<T> tClass) {
        return obj != null ? toJavaObject(toJSONString(obj), tClass) : null;
    }

    public static Map<String, Object> toMap(String value) {
        return StringUtils.isEmpty(value) ? toMap(value, () -> new HashMap<>()) : new HashMap<>();
    }

    public static Map<String, Object> toMap(Object value) {
        return value != null ? toMap(value, () -> new HashMap<>()) : new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(Object value,
                                            Supplier<Map<String, Object>> defaultSupplier) {
        if (value == null) {
            return defaultSupplier.get();
        }
        try {
            if (value instanceof Map) {
                return (Map<String, Object>)value;
            }
        }
        catch (Exception e) {
            log.info("fail to convert" + toJSONString(value), e);
        }
        return toMap(toJSONString(value), defaultSupplier);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(String value,
                                            Supplier<Map<String, Object>> defaultSupplier) {
        if (StringUtils.isEmpty(value)) {
            return defaultSupplier.get();
        }
        try {
            return toJavaObject(value, LinkedHashMap.class);
        }
        catch (Exception e) {
            log.error(String.format("toMap exception: <%s>", value), e);
        }
        return defaultSupplier.get();
    }

    public static List<Object> toList(String value) {
        return StringUtils.isEmpty(value) ? toList(value, () -> new ArrayList<>()) : new ArrayList<>();
    }

    public static List<Object> toList(Object value) {
        return value != null ? toList(value, () -> null) : null;
    }

    @SuppressWarnings("unchecked")
    public static List<Object> toList(String value, Supplier<List<Object>> defaultSuppler) {
        if (StringUtils.isEmpty(value)) {
            return defaultSuppler.get();
        }
        try {
            return toJavaObject(value, List.class);
        }
        catch (Exception e) {
            log.error("toList exception\n" + value, e);
        }
        return defaultSuppler.get();
    }

    @SuppressWarnings("unchecked")
    public static List<Object> toList(Object value, Supplier<List<Object>> defaultSuppler) {
        if (value == null) {
            return defaultSuppler.get();
        }
        if (value instanceof List) {
            return (List<Object>)value;
        }
        return toList(toJSONString(value), defaultSuppler);
    }
}