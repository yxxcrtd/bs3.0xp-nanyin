package com.joyintech.tams.business.common.util;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.business.common.constant.PositionDictCodeListExt;
import com.joyintech.tams.business.common.dto.PositionBondExecuteParam;
import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;
import java.util.List;
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
                    // 头寸返回对象
                    BaseMap data = JsonUtils.json2Object(arr[1], BaseMap.class);

                    StringBuffer strb = null;
                    Map responseMap_data = (Map) data.get("data");
                    if (responseMap_data != null){
                        strb = new StringBuffer("当前【\n");
                        List checkResults = (List) responseMap_data.get("checkResults");
                        if (checkResults != null) {
                            for (int i=0; i<checkResults.size();i++){
                                BaseMap flow = BaseMap.newMap((Map) checkResults.get(i));
                                BigDecimal CHECKBALANCE = flow.getBigDecimal("CHECKBALANCE");
                                String PORTFOLIO_ID = flow.getString("PORTFOLIO_ID");
                                String HODING_TYPE = flow.getString("HODING_TYPE");
                                strb.append(PORTFOLIO_ID);
                                strb.append("余额为");
                                strb.append(CHECKBALANCE);
                                strb.append("元【");
                                strb.append(SysDataUtil.getNameFromDataDict(DictCode.头寸资产类型, HODING_TYPE));
                                if (i == checkResults.size()){
                                    strb.append("】不足\n");
                                }else{
                                    strb.append("】不足、\n");
                                }
                            }
                        }
                        strb.append("】，请确认是否继续？");
                    }
                    
                    /**
                     * 余额不足，前端判断code 300确认
                     */
                    BaseMap bm = BaseMap.newMap()
                            .put("code", PositionHttpRequest.CODE_BUSI_REFUSE)
                            .put("message", strb)
                            .put("data", data);
                    return new Message().success().setData(bm);
                }
            }else {
                return new Message().fail().setMessage(message).setData(null);
            }
        }
        return new Message().fail().setMessage("无返回信息，未知异常").setData(null);
    }
}
