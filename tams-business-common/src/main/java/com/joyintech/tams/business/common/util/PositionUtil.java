package com.joyintech.tams.business.common.util;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.constant.PositionDictCodeListExt;
import com.joyintech.tams.business.common.dto.PositionBondExecuteParam;
import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.business.common.service.PositionHttpRequest;

import java.util.Map;

public class PositionUtil {

    /**
     * 初始化头寸通用信息
     * @param totalTradeId
     * @return
     */
    public static PositionBondExecuteParam initDto(String totalTradeId){
        PositionBondExecuteParam result = new PositionBondExecuteParam();
        result.setInterfaceType("Bond001"); // 规则根节点的编号，默认现券根节点
        result.setUserId(LoginUserUtil.getUserName());
//        result.setComputerId(PositionFutureManager.QUEUE_POSITION_CALLBACK); // 从配置文件获取，机器标识"queue.position.callback"
        result.setTotalTradeId(totalTradeId); // 委托或交易编号
        if (StringUtil.isEmpty(result.getBalanceCheck())){
            result.setBalanceCheck(PositionDictCodeListExt.是否_是.getValue()); // Y：需校验   N：不需校验, 初始化默认为Y
        }
        return result;
    }

    /**
     * 捕获异常处理
     * @param e
     * @return
     */
    public static Message ajaxErrorReturn(Exception e) {
        String message = e.getMessage();
        if (StringUtil.isNotEmpty(message)){
            String[] arr = message.split("\\|");
            if (arr != null && arr.length == 2){
                /**
                 * 头寸引擎业务失败，待前端确认是否校验
                 */
                if (StringUtil.equals(PositionHttpRequest.CODE_BUSI_REFUSE, arr[0])){
                    /**
                     * 余额不足，前端判断code 300确认
                     */
                    BaseMap bm = BaseMap.newMap()
                            .put("code", PositionHttpRequest.CODE_BUSI_REFUSE)
                            .put("data", JsonUtils.json2Object(arr[1], Map.class));
                    return new Message().success().setData(bm);
                }
            }else {
                return new Message().fail().setData(message);
            }
        }
        return new Message().fail().setData("无返回信息，未知异常");
    }
}
