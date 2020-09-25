package com.joyintech.invest.interf.common.core.utility.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.contants.InterfColumns;
import com.joyintech.invest.interf.common.core.contants.InterfList;
import com.joyintech.invest.interf.common.core.service.InvestInterfCommonService;
import com.joyintech.invest.interf.common.core.service.InterfRecvBaseService;
import com.joyintech.invest.interf.common.core.service.InterfRecvComponent;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;

/**
 * @description 接口核心API入口
 * @author 王波
 * @version 1.0 2020年01月16日
 * @see InterfUtilityController
 * @since 1.0
 */
@RestController
@RequestMapping("/invest/InterfUtility")
public class InterfUtilityController {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 共通
     */
    @Autowired(required = false)
    private InterfRecvComponent interfRecvComponent;

    /**
     * @ 核心执行方法（接收接口）
     * @param resStr 报文
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("recv")
    public String recv(@RequestBody String resStr) {

        logger.info("请求报文:{}", resStr);
        Map<String, Object> dataMap = JsonUtils.json2Object(resStr, Map.class);

        Map<String, Object> head = (Map<String, Object>)dataMap.get("head");

        // 1.1 若参数为空，则处理结束返回结果
        if (head == null) {
            return JsonUtils.object2Json(errMsg("参数异常【空的参数列表】"));
        }
        String interfId = (String)head.get("INF_ID");

        // 1.1 若参数为空，则处理结束返回结果
        if (StringUtils.isBlank(interfId)) {
            return JsonUtils.object2Json(errMsg("参数异常【空的参数列表】"));
        }
        // 接口模块处理
        return JsonUtils.object2Json(interfRecvComponent.recv(dataMap));
    }

    /**
     * @Description errMsg
     * @param errMsg
     * @return
     * @see
     * @since 1.0
     */
    private Map<String, Object> errMsg(String errMsg) {
        ClientData data = new ClientData();
        data.setHeadField(InterfColumns.COM_RTNCODE.fieldName(), 1);
        data.setHeadField(InterfColumns.COM_RTNMSG.fieldName(), errMsg);
        return data.convertToMap();
    }
}
