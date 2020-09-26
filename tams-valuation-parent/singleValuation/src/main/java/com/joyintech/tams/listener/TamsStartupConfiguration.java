/*
 * 文件名：TamsStartupConfiguration.java
 * 版权：Copyright 2019-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：王波
 * 修改时间：2019/6/27 17:14
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.listener;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.system.config.StartupConfiguration;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.service.PageElementConfigService;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 王波
 * @version 1.0 2019年06月27日
 * @see
 * @since 1.0
 */
@Component
public class TamsStartupConfiguration extends StartupConfiguration {
    @Autowired
    private PageElementConfigService pageElementConfigService;
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(TamsStartupConfiguration.class);

    @Override
    public void afterStartup(ApplicationContext applicationContext) {
        super.afterStartup(applicationContext);
        cachePageElement();
        logger.info("TAMS服务启动完成...");
    }

    /**
     * 缓存画面元素
     */
    private void cachePageElement() {
        List<PageElementConfigDto> allElementConfig = pageElementConfigService.getAllElementConfig();
        CacheUtil.putAppCache(PageElementUtil.PAGE_ELEMENT_CACHE_KEY, allElementConfig);
    }
}
