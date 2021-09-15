package com.joyintech.tams.invest.common.service;

import com.joyintech.background.component.common.check.DataCheckCommonUtil;
import com.joyintech.background.component.common.check.dao.DataCheckCommonMapper;
import com.joyintech.background.component.common.check.dto.CheckRule;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.validation.ValidMessage;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class InvDataCheckService {

    @Autowired
    private DataCheckCommonUtil dataCheckCommonUtil;

    @Autowired
    private DataCheckCommonMapper dataCheckCommonMapper;

    public List<ValidMessage> chbCheckCommon(List<ValidMessage> checkResultList, Object entity, String pageId, String repType) {
        return dataCheckCommonUtil.chbCheckCommon(checkResultList, entity, pageId, repType, ValidMessage.class);
    }

    public Map<String,String> getCheckRule(String pageId, String pageFlag) {
        List<CheckRule> chbCheckRuleList = dataCheckCommonMapper.queryChbCheckRule(pageId, pageFlag);
        if (CollectionUtils.isNotEmpty(chbCheckRuleList)){
            return chbCheckRuleList.stream().collect(Collectors.toMap(rule -> rule.getSrcproperty() + GlobalConstant.UNDER_LINE + rule.getCheckmethod(), rule -> rule.getRemark()));
        }
        return new HashMap<>();
    }
}
