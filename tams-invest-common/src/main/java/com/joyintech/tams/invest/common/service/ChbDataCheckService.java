package com.joyintech.tams.invest.common.service;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 对外开放接口
 * @auth 何阳阳
 * @date 2021/7/7
 */
@Service
public class ChbDataCheckService {

    @Autowired
    private JoyinRestTemplate restTemplate;

    public List<ValidMessage> chbCheckCommon(List<ValidMessage> checkResultList, Object entity, String pageId, String repType) {
        if(checkResultList == null) {
            checkResultList = new ArrayList<>();
        }
        // 调用service-chb服务的接口进行校验
        checkResultList.addAll(restTemplate.postForList(ServiceApplication.CHB_SERVICE_NAME + "/openapi/chbDataCheck/{pageId}/{repType}", entity, ValidMessage.class, pageId, repType));
        return checkResultList;
    }
}
