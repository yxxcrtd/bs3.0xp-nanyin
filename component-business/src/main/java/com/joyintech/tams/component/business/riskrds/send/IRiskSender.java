/*
 * 文件名：IRiskSender.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年9月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.riskrds.send;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.riskrds.constant.InterfUtil;
import com.joyintech.tams.invest.common.client.ClientData;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;

/**
 * 风控调用接口
 * 
 * @author zhangrui1
 * @version 1.0 2020年9月9日
 * @see IRiskSender
 * @since 1.0
 */
public interface IRiskSender {

    /**
     * 风控调用接口
     * 
     * @param headParam 报文头参数
     * @param body 报文体
     * @return 计算结果
     */
    default AjaxResult send(Map<String, Object> headParam, Map<String, Object> body) {
        ClientData returnCli = clientSend(headParam, body);
        if (StringUtil.isNotEmpty(returnCli) && StringUtil.isNotEmpty(returnCli.getHead())) {
            Map<String, Object> head = returnCli.getHead();
            if (StringUtil.equals(head.get(InterfUtil.std400msgt), InterfUtil.E)) {
                return getAjaxResult(returnCli, (String)head.get(InterfUtil.stdrtninfo),
                    MessageType.错误消息);
                }
            else {
                Map<String, Object> body2 = returnCli.getBody();
                String statusRisk = StringUtil.isNotEmpty(body2) && StringUtil.isNotEmpty(
                    body2.get("rskStatus")) ? (String)body2.get("rskStatus") : null;
                if (StringUtil.equals(statusRisk, BussinessDictCodeListRds.指标状态_禁止.getValue())) {
                    return getAjaxResult(returnCli, InterfUtil.FORBID_MES, MessageType.错误消息);
                }
            }
        }
        return null;
    }

    /**
     * 获取返回的信息
     * 
     * @param message
     * @return 返回的信息
     */
    default AjaxResult getAjaxResult(Object returnCli, String message, MessageType messageType) {
        AjaxResult ar = new AjaxResult();
        ar.setData(returnCli);
        List<ValidMessage> warnList = new ArrayList<>();
        warnList.add(new ValidMessage(message));
        ar.setErrorList(warnList);
        ar.setMessageType(messageType);
        return ar;
    }

    /**
     * 风控调用接口
     * 
     * @param headParam 报文头参数
     * @param body 报文体
     * @return 计算结果
     */
    ClientData clientSend(Map<String, Object> headParam, Map<String, Object> body);
}
