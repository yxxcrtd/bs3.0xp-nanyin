/*
 * 文件名：DemoJob.java
 * 版权：Copyright 2019-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：王波
 * 修改时间：2019/5/27 15:04
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.interf.bankname.job;

import com.joyintech.interf.bankname.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * 这是个示例任务
 * @author 王波
 * @version 1.0 2019年05月27日
 * @see
 * @since 1.0
 */
@Component
@EnableScheduling
public class DemoJob {

    @Autowired
    private DemoService demoService;

    public void task() {
        demoService.demo();
    }
}
