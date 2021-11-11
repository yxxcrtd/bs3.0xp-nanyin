/*
 * 文件名：MessageController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改时间：2018年2月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.framework.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.joyintech.framework.JoyinApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.logging.LoggingActionService;
import com.joyintech.framework.message.PropertyMessageSource;
import com.joyintech.framework.navbar.Navbar;
import com.joyintech.framework.navbar.NavbarService;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;

/**
 * Framework控制器
 * 
 * @version 1.0 2018年2月1日
 * @see FrameworkController
 * @since 1.0
 */
@RestController
public class FrameworkController {

    /**
     * PropertyMessageSource
     */
    @Autowired
    private PropertyMessageSource messageResource;

    /**
     * HttpServletResponse
     */
    @Autowired
    private HttpServletResponse response;
//
//    /**
//     * 
//     */
//    @Autowired
//    @Qualifier("commonExportService")
//    private CommonExportService<?> exportService;

    /**
     * 进度条接口
     */
    @Autowired
    private ProgressBarService progressBarService;

    /**
     * NavbarService
     */
    @Autowired
    private NavbarService navbarService;

    /**
     * @return 导航
     */
    @RequestMapping("/navbar/list")
    public List<Navbar> navbars() {
        return navbarService.selectNavbars();
    }

    /**
     * 处理tab页关闭
     */
    @RequestMapping("/closePage")
    public void closePage() {
        // log
        String pageId = RequestUtil.getParam(GlobalConstant.PAGE_ID);
        if (StringUtil.isNotEmpty(pageId)) {
            LoggingActionService loggingActionService = SpringContextUtil.getBean(
                LoggingActionService.class);
            loggingActionService.LoggingLogInfo(pageId, "退出访问页面", null);
        }
    }

    /**
     * 关闭应用
     */
    @PostMapping("/shutdown")
    public void shutdown() {
        JoyinApplication.shutdown();
    }

    /**
     * 重启应用
     */
    @PostMapping("/restart")
    public void restart() {
        JoyinApplication.restart();
    }

    /**
     * 服务器信息，可以用来判断服务器是否启动
     */
    @GetMapping("/info")
    public String info() {
        return "running";
    }

    /**
     * 后台消息转javascript
     */
    @RequestMapping(value = "/public/js/message", method = RequestMethod.GET)
    public void printMessage() {
        // 获取i18n定义的所有消息
        Map<String, String> messages = messageResource.getAllMessage(null, "message",
            LocaleContextHolder.getLocale());

        // 获取i18n定义的所有消息
        Map<String, String> labels = messageResource.getAllMessage(null, "label",
            LocaleContextHolder.getLocale());

        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");

            response.getWriter().println(
                " var JoyinMessage = " + JsonUtils.object2Json(messages) + ";");
            response.getWriter().println(
                " var JoyinLabel = " + JsonUtils.object2Json(labels) + ";");
            response.getWriter().println(
                " top.JoyinLabel = " + JsonUtils.object2Json(labels) + ";");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取画面进度条
     * 
     * @param pageId 画面ID
     * @param key 进度条ID
     * @return 进度值
     */
    @RequestMapping(value = "/common/{pageId}/percent/{barId}", method = RequestMethod.GET)
    public double commonPercent(@PathVariable("pageId") String pageId,
                                @PathVariable("barId") String key) {
        return progressBarService.getPercent(pageId, key);
    }

    /**
     * 获取画面进度条
     * 
     * @param pageId 画面ID
     * @param key 进度条ID
     * @return 进度值
     */
    @RequestMapping(value = "/common/{pageId}/clear/{barId}", method = RequestMethod.GET)
    public double clearProgressBar(@PathVariable("pageId") String pageId,
                                @PathVariable("barId") String key) {
        progressBarService.removePercent(pageId, ProgressBarService.FILE_UPLOAD_ID);
        return 100;
    }

}
