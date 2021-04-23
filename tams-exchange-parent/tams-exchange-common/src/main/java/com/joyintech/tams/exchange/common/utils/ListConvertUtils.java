package com.joyintech.tams.exchange.common.utils;

import com.joyintech.framework.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListConvertUtils {
    private static final Logger logger = LoggerFactory.getLogger(ListConvertUtils.class);

    public static <T> List<T> convertList(List<?> list, Class<T> beanClass) {
        if (list != null && list.size() > 0) {
            List<T> resultList = new ArrayList<T>(list.size());
            list.forEach(item -> {
                try {
                    if (item instanceof Map) {
                        resultList.add((T) JsonUtils.map2entity((Map<String, Object>) item, beanClass));
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            });
            return resultList;
        }
        return (List<T>) list;
    }
}
