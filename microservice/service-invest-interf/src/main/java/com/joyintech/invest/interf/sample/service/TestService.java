package com.joyintech.invest.interf.sample.service;

import com.joyintech.framework.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王波
 * @version 1.0 2020年01月16日
 * @see TestService
 * @since 1.0
 */
@Service
public class TestService extends BaseService {

    public void test() {
        Map<String, Object> dataMap = new HashMap<>();

        Map<String, Object> head = new HashMap<>();
        Map<String, Object> body = new HashMap<>();
        head.put("stdprocode", "send");
        dataMap.put("head", head);
        dataMap.put("body", body);


        simpleRestTemplate.postForEntity("http://127.0.0.1:8211/invest/interf/execute", dataMap, Object.class);
    }
}
