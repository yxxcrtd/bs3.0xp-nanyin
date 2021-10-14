package com.joyintech.tams.business.common.util;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.constant.PositionDictCodeListExt;
import com.joyintech.tams.business.common.dto.PositionRuleInterDto;
import com.joyintech.tams.business.common.service.PositionFutureManager;
import org.springframework.stereotype.Component;

@Component
public class PositionUtil {
    public static PositionRuleInterDto initDto(String totalTradeId){
        PositionRuleInterDto result = new PositionRuleInterDto();
        result.setInterfaceType("Bond001"); // 规则根节点的编号
        result.setUserId(LoginUserUtil.getUserName());
        result.setComputerId(PositionFutureManager.QUEUE_POSITION_CALLBACK); // 从配置文件获取，机器标识"queue.position.callback"
        result.setTotalTradeId(totalTradeId); // 委托或交易编号
        if (StringUtil.isEmpty(result.getBalanceCheck())){
            result.setBalanceCheck(PositionDictCodeListExt.是否_是.getValue()); // Y：需校验   N：不需校验, 初始化默认为Y
        }
        return result;
    }

    /**
     * 判断交易完成度
     * @param entrustId
     * @param tradeAmt
     * @return
     */
//    public static String etrComplete(String entrustId, BigDecimal tradeAmt){
//        if (StringUtil.isEmpty(entrustId)){
//            return PositionDictCodeListExt1.是否_否.getValue();
//        }
//        BigDecimal finishAmt = selectFinishAmtPositionMapper.selectfinishAmt(entrustId);
//        EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(entrustId);
//        if (etrEntrust == null){
//            throw new RuntimeException("该指令编号查不到对应指令：" + entrustId);
//        }
//        if(etrEntrust.getEntrustAmt().compareTo(BigDecimal.valueOf(0)) == 1){
//            return DictCodeList.是否_是.getValue();
//        }
//        int flag = ArithmeticUtil.doAdd(finishAmt, tradeAmt).divide(etrEntrust.getEntrustAmt()).multiply(BigDecimal.valueOf(100))
//                .compareTo(new BigDecimal("100.00"));
//        return flag < 0 ? DictCodeList.是否_否.getValue():DictCodeList.是否_是.getValue();
//    }

    public static AjaxResult ajaxErrorReturn(Exception e) {
        String message = e.getMessage();
        if (StringUtil.isNotEmpty(message)){
            String[] arr = message.split("\\|");
            if (arr != null && arr.length == 2){
                /**
                 * 头寸引擎业务失败，待前端确认是否校验
                 */
                if (StringUtil.equals(PositionFutureManager.CODE_BUSI_REFUSE, arr[0]) 
                        || StringUtil.contains(arr[0], PositionFutureManager.CODE_BUSI_REFUSE)){
                    return new AjaxResult(arr[1] + " 请确认是否继续？", MessageType.确认消息);
                }else{
                    return new AjaxResult(arr[1], MessageType.错误消息);
                }
            }else {
                return new AjaxResult(message, MessageType.错误消息);        
            }
        }
        return new AjaxResult("未知错误", MessageType.错误消息);
    }
}
