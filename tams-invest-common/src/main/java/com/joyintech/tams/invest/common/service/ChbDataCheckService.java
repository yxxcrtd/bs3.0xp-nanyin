package com.joyintech.tams.invest.common.service;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.ChbCheckRuleDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        Map<String, Object> param = BeanUtil.object2Map(entity);
        param.put("entityClass", entity.getClass().getName());

        checkResultList.addAll(restTemplate.postForList(ServiceApplication.CHB_SERVICE_NAME + "/openapi/chbDataCheck?"+tailStr, param, ValidMessage.class));
        return checkResultList;
    }

    public Map<String,String> getCheckRule(String pageId, String pageFlag) {

        List<ChbCheckRuleDto> chbCheckRuleDtoList = restTemplate.getForList(ServiceApplication.CHB_SERVICE_NAME + "/openapi/queryChbCheckRule?pageId="+pageId + "&pageFlag=" + pageFlag, ChbCheckRuleDto.class);
        if (CollectionUtils.isNotEmpty(chbCheckRuleDtoList)){
            return chbCheckRuleDtoList.stream().collect(Collectors.toMap(rule -> rule.getSrcproperty() + GlobalConstant.UNDER_LINE + rule.getCheckmethod(), rule -> rule.getRemark()));
        }
        return new HashMap<>();
    }
}
