/*
 * 文件名：RocketMQPublishTest.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.bankuplink.rocketmq.topic;

import java.util.Calendar;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.joyintech.tams.invest.bankuplink.rocketmq.topic.tag.TopicQuoteInterfTag;

/**
 * RocketMQ 发布消息测试（可删除）
 * @author Jiaoxujin
 * @version 1.0 2020年5月18日
 * @see RabbitMQPublishTest
 * @since 1.0
 */
@RestController
@RequestMapping("/test/RabbitMQ")
public class RabbitMQPublishTest {
    @Autowired
    private BankUplinkPublishService publishService;

    // GET方式可通过浏览器直接测试
    @RequestMapping(value="/publish/bankuplink/quoteinterf", method = RequestMethod.GET)
    public String publishQuoteInterf(@RequestParam("message") String message,
                          @RequestParam("tag") String tag) {
        if (TopicQuoteInterfTag.get(tag) == null) {
            return String.format("TAG={%s}在TopicQuoteInterfTag中未定义，拒绝发送。", tag);
        }
        else {
            String timeStr = DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss.SSS");
            boolean result = publishService.publishMessage(message);
            return String.format("发送时间：%s，发送结果：%s", timeStr, String.valueOf(result));
        }
    }

    @RequestMapping(value="/publish/bankuplink/rabbitmq", method = RequestMethod.GET)
    public String publishToMq(@RequestParam("topic") String topic,
                                              @RequestParam("message") String message,
                                              @RequestParam("tag") String tag) {
        String timeStr = DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss.SSS");
        boolean result = publishService.publishMessage(message);
        return String.format("发送时间：%s，发送结果：%s", timeStr, String.valueOf(result));
    }
}
