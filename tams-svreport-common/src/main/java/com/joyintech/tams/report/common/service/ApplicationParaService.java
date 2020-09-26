/*
 * 文件名：ApplicationParaService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：刘二通
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * application.yml中设置的系统参数管理service
 *
 * @author 刘二通
 * @version 1.0 2018年10月18日
 * @see ApplicationParaService
 * @since 1.0
 */
@Service
public class ApplicationParaService {
    /**
     * 上游接口解析分割符
     */
    @Value("${application.reportInterfSplitCharacter}")
    private String reportInterfSplitCharacter;


    /**
     * 获取 reportInterfSplitCharacter
     * @return reportInterfSplitCharacter.
     */
    public String getReportInterfSplitCharacter() {
        return reportInterfSplitCharacter;
    }
}
