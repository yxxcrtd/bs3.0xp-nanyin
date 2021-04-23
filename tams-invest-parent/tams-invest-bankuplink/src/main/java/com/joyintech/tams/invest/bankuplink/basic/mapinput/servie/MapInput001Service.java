/*
 * 文件名：MapInput001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2020年5月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.bankuplink.basic.mapinput.servie;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;


/**
 * Map数据输入控制类
 * 
 * @author zhangliang
 * @version 1.0 2020年5月27日
 * @see MapInput001Service
 * @since 1.0
 */
@Service
public class MapInput001Service extends BaseService {

    /**
     * 模拟接收报文调用
     * 
     * @param paramMap
     * @see
     * @since 1.0
     */
    public void getMapByRequestBody(Map<String, Object> paramMap){
        // 判断请求方式
        // 对话报价
        if (StringUtil.equals(paramMap.get("sysQuoteType"), "01")){
            restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME
                + "/CfetsQuotedCommon/receiveBondDialogueRequest",
                paramMap, Object.class);
        }
        // 请求报价
        else if (StringUtil.equals(paramMap.get("sysQuoteType"), "02")){
            restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME
                + "/CfetsQuotedCommon/receiveBondRequestRequest",
                paramMap, Object.class);
        }
        // 回购报价
        else if (StringUtil.equals(paramMap.get("sysQuoteType"), "03")){
            restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME
                + "/CfetsQuotedCommon/receiveRepoDialogueRequest",
                paramMap, Object.class);
        }
    }
}
