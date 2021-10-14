package com.joyintech.tams.cfets.etrib.util;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.constant.PositionDictCodeListExt;
import com.joyintech.tams.business.common.dto.PositionRuleInterDto;

public class PositionUtil {
    public static PositionRuleInterDto initDto(String totalTradeId){
        PositionRuleInterDto result = new PositionRuleInterDto();
        result.setInterfaceType("Bond001"); // 规则根节点的编号
        result.setUserId(LoginUserUtil.getUserName());
//        result.setComputerId(PositionFutureManager.QUEUE_POSITION_CALLBACK); // 从配置文件获取，机器标识"queue.position.callback"
        result.setTotalTradeId(totalTradeId); // 委托或交易编号
        if (StringUtil.isEmpty(result.getBalanceCheck())){
            result.setBalanceCheck(PositionDictCodeListExt.是否_是.getValue()); // Y：需校验   N：不需校验, 初始化默认为Y
        }
        return result;
    }
}
