package com.joyintech.tams.invest.common.service;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.util.StringUtil;
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
        String tailStr = "";
        if(StringUtil.isNotEmpty(pageId)){
            tailStr = "pageId="+pageId;
        }
        if(StringUtil.isNotEmpty(repType)){
            if (StringUtil.isNotEmpty(tailStr)){
                tailStr = tailStr.concat("&repType="+repType);
            }else{
                tailStr = "repType="+repType;
            }
        }


        checkResultList.addAll(restTemplate.postForList(ServiceApplication.CHB_SERVICE_NAME + "/openapi/chbDataCheck?"+tailStr, entity, ValidMessage.class));
        return checkResultList;
    }
}
