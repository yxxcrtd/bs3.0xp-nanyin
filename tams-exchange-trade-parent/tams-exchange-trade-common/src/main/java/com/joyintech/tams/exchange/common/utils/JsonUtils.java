package com.joyintech.tams.exchange.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

/**
 * Created by yking on 2017/12/6 0006. God bless you!
 */
public class JsonUtils {
    public static <T> T getObjectFromJson(String str, Class<T> cls) {
        return JSON.parseObject(str, cls);
    }

    public static <T> List<T> getObjectListFromJson(String str, Class<T> cls) {
        return JSON.parseArray(str, cls);
    }

    public static String getJsonFromObject(Object object) {
        return JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
    }

}
