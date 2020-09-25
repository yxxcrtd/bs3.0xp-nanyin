/*
 * 文件名：InfData.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年7月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.core.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 接口数据结构
 * 
 * @author zhangrui1
 * @version 1.0 2019年7月29日
 * @see ClientData
 * @since 1.0
 */
public class ClientData implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -4109825904872389940L;

    private Map<String, Object> head = new HashMap<>();

    private Map<String, Object> body = new HashMap<>();

    private static final String KEY_HEADER = "head";

    private static final String KEY_BODY = "body";

    public void setHeadField(String field, Object obj) {
        head.put(field, obj);
    }

    public void setBodyField(String field, Object obj) {
        body.put(field, obj);
    }

    public Object getHeadField(String key) {
        return head.get(key);
    }

    public Object getBodyField(String key) {
        return body.get(key);
    }

    public Map<String, Object> convertToMap() {
        Map<String, Object> data = new HashMap<>();
        data.put(KEY_HEADER, head);
        data.put(KEY_BODY, body);
        return data;
    }

    @SuppressWarnings("unchecked")
    public void convert(Map<String, Object> dataMap) {
        if (dataMap == null) {
            return;
        }
        this.head = (Map<String, Object>)dataMap.getOrDefault(KEY_HEADER, new HashMap<>());
        this.body = (Map<String, Object>)dataMap.getOrDefault(KEY_BODY, new HashMap<>());
    }

    public boolean isEmpty() {
        return isHeaderEmpty() && isBodyEmpty();
    }

    public boolean isHeaderEmpty() {
        return isEmptyMap(head);
    }

    public boolean isBodyEmpty() {
        return isEmptyMap(body);
    }

    private boolean isEmptyMap(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public Map<String, Object> getHead() {
        return head;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setHead(Map<String, Object> header) {
        this.head = header;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }
}
