package com.joyintech.tams.invest.common.service;

import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.component.svrreport.chb.chbcommoncheck.dao.ChbDataCheckCommonMapper;
import com.joyintech.component.svrreport.chb.chbcommoncheck.dto.ChbCheckRule;
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
public class ChbDataCheckService {

    @Autowired
    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;

    @Autowired
    private ChbDataCheckCommonMapper chbDataCheckCommonMapper;

    public List<ValidMessage> chbCheckCommon(List<ValidMessage> checkResultList, Object entity, String pageId, String repType) {
        return chbDataCheckCommonUtil.chbCheckCommon(checkResultList, entity, pageId, repType, ValidMessage.class);
    }

    public Map<String,String> getCheckRule(String pageId, String pageFlag) {
        List<ChbCheckRule> chbCheckRuleList = chbDataCheckCommonMapper.queryChbCheckRule(pageId, pageFlag);
        if (CollectionUtils.isNotEmpty(chbCheckRuleList)){
            return chbCheckRuleList.stream().collect(Collectors.toMap(rule -> rule.getSrcproperty() + GlobalConstant.UNDER_LINE + rule.getCheckmethod(), rule -> rule.getRemark()));
        }
        return new HashMap<>();
    }
}
