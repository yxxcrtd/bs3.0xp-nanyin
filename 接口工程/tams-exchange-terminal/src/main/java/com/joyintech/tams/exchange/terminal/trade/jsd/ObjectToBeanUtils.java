package com.joyintech.tams.exchange.terminal.trade.jsd;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;

/**
 * create By MAXX on 2020/6/23
 */
public class ObjectToBeanUtils {
    /***
     * @Description: List<Object>转List<T>
     * @Param: [objectList, clz]
     * @return: java.util.List<T>
     * @Author: MAXX
     * @Date: 2020/6/23
     */
    public static <T> List<T> objectToBean(List<Object> objectList, Class<T> clz) {
        if (Objects.isNull(objectList)) {
            return null;
        }
        List<T> list = Lists.newArrayListWithCapacity(objectList.size());
        objectList.forEach(object -> {
            T bean = (T) object;
            list.add(bean);

        });
        return list;
    }
}
