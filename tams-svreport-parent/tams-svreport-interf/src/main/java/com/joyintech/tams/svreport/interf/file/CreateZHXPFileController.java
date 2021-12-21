/*
 * 文件名：CheckReport001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月28日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.svreport.interf.file;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.common.BaseInterfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 生成综合信披文件接口
 *
 * @author zcw
 * @version 1.0 2021年11月30日
 * @see CreateZHXPFileController
 * @since 1.0
 */
@Controller
@RequestMapping("/svreport/interf/file/CreateZHXPFile")
public class CreateZHXPFileController extends BaseController{

    /**
     * 线程池
     */
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    /**
     * 生成文件返回文件路径
     *
     * @return
     */
    @RequestMapping("/createFile")
    @ResponseBody
    public String createFile() throws InterruptedException {
        // service
        String serviceName = RequestUtil.getParam("service");
        // 日期
        String date = RequestUtil.getParam("date");
        // 返回路径
        String path = CacheUtil.getSysParam("11")+date;

        threadPoolTaskExecutor.submit(new Runnable() {
            @Override
            public void run() {
                BaseInterfService baseInterfService = (BaseInterfService) SpringContextUtil.getBean(serviceName);
                // TODO 路径和产品的参数问题
                baseInterfService.createFileFromEntity(null, date, path, "");
            }
        });

        return path;
    }


}
