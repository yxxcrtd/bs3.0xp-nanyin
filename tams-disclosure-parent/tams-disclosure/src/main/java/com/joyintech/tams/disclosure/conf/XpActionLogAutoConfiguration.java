/*
 * 文件名：XpActionLogAutoConfiguration.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2019年1月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.disclosure.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.joyintech.framework.logging.LoggingActionService;
import com.joyintech.tams.disclosure.system.useraction.XpLoggingUserAction;
import com.joyintech.tools.actionlog.dao.ActionLogMapper;
import com.joyintech.workflow.engine.service.ProcessService;
/**
 * 用户操作日志记录自动配置类
 * 
 * @author ZhangYangFei
 * @version 1.0 2019年1月29日
 * @see XpActionLogAutoConfiguration
 * @since 1.0
 */
@Configuration
@ConditionalOnClass(ProcessService.class)
@ConditionalOnBean({ProcessService.class, ActionLogMapper.class})
public class XpActionLogAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LoggingActionService loggingActionService(ActionLogMapper mapper, ProcessService processService) {
        XpLoggingUserAction logging = new XpLoggingUserAction();
        logging.setMapper(mapper);
        logging.setProcessService(processService);
        return logging;
    }
}
