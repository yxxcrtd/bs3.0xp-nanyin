/*
 * 文件名：QuoteInterfTopicListener.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.bankuplink.rocketmq.topic.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
/*import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;*/
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.web.socket.SocketService;
/*import com.joyintech.mq.rocket.GroupMessageListener;
import com.joyintech.mq.rocket.WebSocketSendType;*/
import com.joyintech.system.user.dto.User;

/**
 * 银行间上行模块RocketMQ消息的订阅与WebScoket推送处理<br />
 * 每个Group写一个GroupMessageListener
 * @author Jiaoxujin
 * @version 1.0 2020年5月18日
 * @see BankUplinkTopicsListener
 * @since 1.0
 */
/*@Component
public class BankUplinkTopicsListener extends GroupMessageListener {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Value("${application.rocketmq.group.bankuplink.name:bankuplink}")
    private String groupName;   
    
    @Value("${application.rocketmq.group.bankuplink.messagemodel:BROADCASTING}")
    private String messagemodel;    

    @Autowired(required = false)
    private SocketService socket;

    @Autowired(required = false)
    private SessionDAO sessionDao;
    
    @Value("${application.rocketmq.topic.bond:trcms_pfms_tbank_bond}")
    private String bondTopic;
    
    @Value("${application.rocketmq.topic.repo:trcms_pfms_tbank_repo}")
    private String repoTopic;
    
    *//**
     * {@inheritDoc}
     *//*
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                    ConsumeConcurrentlyContext context) {
        log.debug(String.format("%s Received New Messages: %s %n", Thread.currentThread().getName(), msgs));
        if (socket == null) return ConsumeConcurrentlyStatus.RECONSUME_LATER;

        String topci, tag, msg;

        for (MessageExt message : msgs) {
            try {
                // 消息主题
                topci = message.getTopic();
                log.debug("TOPCI is: {}", topci);
                // 消息TAG
                tag = message.getTags();
                log.debug("TAG is: {}", tag);
                // 消息体
                msg = new String(message.getBody(), "UTF-8");
                JSONObject jsonObj = JSONObject.parseObject(msg);
                
                // 判断推送方式
                String sendType = jsonObj.getString("sendType");
                
                if (WebSocketSendType.指定单用户.getValue().equals(sendType)) {
                    String toUser = jsonObj.getString("toUser");

                    if (StringUtils.isNotBlank(toUser)) {
                        // 推送给指定用户
                        socket.sendToUser(toUser, "/" + topci + "/" + tag, msg);

                        // 再向管理员账号推送
                        // sendToOnlineAdminList(message.getTopic(), tag, msg);
                    }
                    else {
                        log.warn("订阅到的消息中无\"toUser\"信息，无法将消息推送到指定用户。");
                    }
                }
                else if (WebSocketSendType.指定多用户.getValue().equals(sendType)) {
                    JSONArray userList = jsonObj.getJSONArray("toUserList");

                    if (CollectionUtils.isNotEmpty(userList)) {
                        // 推送给指定用户
                        for (Object userObj : userList) {
                            socket.sendToUser(userObj.toString(), "/" + topci + "/" + tag, msg);
                        }
                    }
                    else {
                        log.warn("订阅到的消息中无\"toUserList\"信息，无法将消息推送到指定的多个用户。");
                    }
                }
                else if (WebSocketSendType.广播.getValue().equals(sendType)) {
                    socket.send("/" + topci + "/" + tag, msg);
                }
                else {
                    log.warn("订阅到的消息中无\"sendType\"信息，或值无效。");
                }
            }
            catch (Exception e) {
               log.error(e.getMessage());
               return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }

        // 标记该消息已经被成功消费
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    *//**
     * 获取所有在线用户名 
     *//*
    private List<String> getOnlineUserNameList() {
        List<String> userNameList = new ArrayList<>();
        if(null != sessionDao) {
            // 所有登录用户
            Collection<Session> sessions = sessionDao.getActiveSessions();
            for (final Session session : sessions) {
                if (null != session) {
                    final String username = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
                    if(!"null".equals(username)) {
                        userNameList.add(username);
                    }
                }
            }
        }
        return userNameList;
    }

    *//**
     * 获取所有在线的管理员用户名 
     *//*
    private List<String> getOnlineAdminNameList() {
        List<String> resultList = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<User> adminUserList = CacheUtil.getAppCache("cache_key_admin_user_list", List.class);
        
        if (CollectionUtils.isNotEmpty(adminUserList)) {
            List<String> adminList = adminUserList.stream().map(p -> p.getUserName()).collect(Collectors.toList());
            List<String> onlineList = getOnlineUserNameList();
            resultList = onlineList.parallelStream().filter(p -> adminList.contains(p)).collect(Collectors.toList());
        }

        return resultList;
    }

    private void sendToOnlineAdminList(String topic, String tag, String msg) {
        List<String> adminList = getOnlineAdminNameList();
        for (String admin : adminList) {
            socket.sendToUser(admin, "/RocketMQ/" + groupName + "/" + topic + "/" + tag, msg);
        }
    }

    @Override
    public String getGroupName() {
        return this.groupName;
    }

    @Override
    public String getMessagemodel() {
        return this.messagemodel;
    }

    @Override
    public void setTopicList() {
        super.topicList.add(bondTopic);
        super.topicList.add(repoTopic);
    }

    @Override
    public void setTopicTagsMap() {
        super.topicTagsMap.put(bondTopic, Arrays.asList("newBondDialogueQuoteAll", "refreshDayOfferTableLoad", "newBondMarketOfferQuoteAll"));
        super.topicTagsMap.put(repoTopic, Arrays.asList("newRepoDialogueQuoteAll", "refreshDayOfferTableLoad"));
    }
}
*/