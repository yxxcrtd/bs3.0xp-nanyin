/*
 * 文件名：SendEmailMessageController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年12月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.iemail.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.InterfEmailmessage;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.util.ServiceMapperUtil;
import com.joyintech.service.interf.iemail.dto.SendEmailDto;
import com.joyintech.service.interf.iemail.service.SendEmailMessageService;

/**
 * 发送邮件
 * 
 * @author 杨静敏
 * @version 1.0 2018年12月4日
 * @see SendEmailMessageController
 * @since 1.0
 */
@RestController
@RequestMapping("/email")
public class SendEmailMessageController {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 线程池
     */
    @Autowired(required = false)
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 发送已保存邮件
     * 
     * @param param 请求参数
     * @return 发送结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/sendSavedEmail")
    public List<SendEmailDto> sendSavedEmail(@RequestBody Map<String, Object> param) {
        String user = (String)param.get(GlobalConstant.CURRENT_USER);
        String uuid = IDGenerator.generate();
        List<String> serialNoList = (List<String>)param.get(InterfConstant.DATA_LIST);
        List<SendEmailDto> resultList = new ArrayList<>();
        // 如果传过来的列表为空则返回空
        if (StringUtil.isEmptyList(serialNoList)) {
            return resultList;
        }
        CountDownLatch countDown = new CountDownLatch(serialNoList.size());
        for (String serialNo : serialNoList) {
            threadPoolTaskExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    SendEmailMessageService service = getService();
                    service.init(uuid);
                    resultList.add(service.sendEmail(serialNo, user));
                    // 线程计数
                    countDown.countDown();
                }

            });
        }
        try {
            countDown.await();
        }
        catch (InterruptedException e) {
            logger.error("线程池中断异常");
            Thread.currentThread().interrupt();
        }
        return resultList;
    }

    /**
     * 取得服务类
     * 
     * @return 服务类
     * @see
     * @since 1.0
     */
    private SendEmailMessageService getService() {
        return ServiceMapperUtil.getApplicationContext().getBean(SendEmailMessageService.class);
    }

    /**
     * 发送新建邮件
     * 
     * @param email 邮件内容
     * @return 发送结果
     * @see
     * @since 1.0
     */
    @RequestMapping("/sendNewEmail")
    public SendEmailDto sendNewEmail(@RequestBody InterfEmailmessage email) {
        // 业务类型非空校验失败时直接返回错误信息
        if (!checkEmail(email)) {
            SendEmailDto dto = new SendEmailDto();
            dto.setSerialNo(email.getSerialno());
            dto.setHandleOK(false);
            dto.setErrMsg("业务类型不能为空");
            return dto;
        }
        SendEmailMessageService service = getService();
        String uuid = IDGenerator.generate();
        // 初始化变量
        service.init(uuid);
        // 保存邮件
        String serialNo = service.insertEmail(email);
        // 邮件保存失败时不发送
        if (!service.isHandleOK()) {
            return service.getReturn(serialNo);
        }
        return service.sendEmail(serialNo, email.getUpdateUser());
    }

    /**
     * 业务类型非空校验
     * 
     * @param email 邮件内容
     * @return 校验结果，true：非空、false：空
     * @see
     * @since 1.0
     */
    private boolean checkEmail(InterfEmailmessage email) {
        return !StringUtil.isEmpty(email.getBusitype());
    }
}
