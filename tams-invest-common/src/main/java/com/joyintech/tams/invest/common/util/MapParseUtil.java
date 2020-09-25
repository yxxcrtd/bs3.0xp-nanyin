/*
 * 文件名：XmlParseUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xkni
 * 修改时间：2020年2月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.util;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tools.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * Map转换类
 *
 * @author xkni
 * @version 1.0 2020年2月17日
 * @see MapParseUtil
 * @since 1.0
 */
public class MapParseUtil {

    private MapParseUtil() {
        throw new IllegalStateException();
    }

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(MapParseUtil.class);

    // 将map值全部转换为大写
    public static Map<String, Object> transformUpperCase(Map<String, Object> orgMap) {
        Map<String, Object> resultMap = new HashMap<>();
        if (orgMap == null || orgMap.isEmpty()) {
            return resultMap;
        }
        Set<String> keySet = orgMap.keySet();
        for (String key : keySet) {
            String newKey = key.toUpperCase();
            Object value=orgMap.get(key);
            if(value instanceof List) {
                List<Map<String, Object>> list = (List<Map<String, Object>>) value;
                List<Map<String, Object>> listRes = new ArrayList<Map<String, Object>>();
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> map2 : list) {
                        Map<String, Object> mapres = transformUpperCase(map2);
                        listRes.add(mapres);
                    }
                    resultMap.put(newKey, listRes);
                }
            }
            else if(value instanceof HashMap ||value instanceof Map)
            {
                Map<String, Object> mapres = transformUpperCase((Map<String, Object>)value);
                resultMap.put(newKey, mapres);
            }
            else {
                resultMap.put(newKey, orgMap.get(key));
            }
        }
        return resultMap;
    }

}
