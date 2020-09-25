/*
 * 文件名：BaseController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.cache.service.SysParamInfService;
import com.joyintech.service.interf.core.annotation.ThreadPoolExcuted;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.service.SendEsbService;

/**
 * 接口Controller基类
 * 
 * @param <T> 参数
 * @author zhangrui1
 * @version 1.0 2018年10月30日
 * @see BaseController
 * @since 1.0
 */
public abstract class BaseController<T> {

    /**
     * 服务需要使用的变量存储Map（解决多线程单例不安全问题）
     */
    private static ThreadLocal<Map<String, Object>> controllerInfoMap = ThreadLocal.withInitial(
        HashMap::new);

    /**
     * HttpServletRequest
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * 线程池
     */
    @Autowired(required = false)
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 系统参数Service
     */
    @Autowired
    private SysParamInfService paraService;

    /**
     * 平台日期
     */
    private String sysDate;

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 接口服务执行方法
     * 
     * @param prodList 产品列表
     * @param isThreadPoolExcuted 是否开启线程池
     * @return 返回执行结果
     * @see
     * @since 1.0
     */
    public List<Object> excuteService(List<T> prodList, boolean isThreadPoolExcuted) {
        if (StringUtil.isEmptyList(prodList)) {
            return new ArrayList<>();
        }
        List<Object> result = new ArrayList<>();
        String uuid = IDGenerator.generate();
        // isThreadPoolExcuted()是否启需要用线程池，isThreadPoolExcuted是否启用线程池
        if (isThreadPoolExcuted() && isThreadPoolExcuted) {
            CountDownLatch countDown = new CountDownLatch(prodList.size());
            for (T dto : prodList) {
                // 请求参数取得
                Map<String, Object> param = getParamMap(dto, uuid);
                SendEsbService<?, ?> service = getService(dto);
                // 线程池执行
                threadPoolTaskExecutor.execute(new Runnable() {

                    @Override
                    public void run() {
                        // 接口启动执行
                        Object obj = service.sendRequest(param);
                        result.add(obj);
                        // 线程计数
                        countDown.countDown();
                        // 写入返回结果
                        doResult(dto, service);
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
        }
        else {
            for (T dto : prodList) {
                SendEsbService<?, ?> service = getService(dto);
                Map<String, Object> param = getParamMap(dto, uuid);
                Object obj = service.sendRequest(param);
                result.add(obj);
                doResult(dto, service);
            }
        }
        return result;
    }

    /**
     * 设置执行结果
     * 
     * @param dto 返回实例
     * @param service 执行服务实例
     * @see
     * @since 1.0
     */
    public abstract void doResult(T dto, SendEsbService<?, ?> service);

    /**
     * 获取服务参数
     * 
     * @param dto 产品实例
     * @param uuid 处理识别码
     * @return 服务参数
     * @see
     * @since 1.0
     */
    private Map<String, Object> getParamMap(T dto, String uuid) {
        Map<String, Object> param = new HashMap<>();
        param.put(InterfConstant.UUID, uuid);
        param.put(InterfConstant.SYS_DATE, getSysDate());
        param.put(InterfConstant.PROD_INFO, dto);
        setParam(param);
        return param;
    }

    /**
     * 设置服务参数
     * 
     * @param param 服务参数
     * @see
     * @since 1.0
     */
    public void setParam(Map<String, Object> param) {

    }

    /**
     * 接口服务执行方法
     * 
     * @param t 产品实例
     * @see
     * @since 1.0
     */
    public void excuteService(T t) {
        String uuid = IDGenerator.generate();
        Map<String, Object> param = getParamMap(t, uuid);
        SendEsbService<?, ?> service = getService(t);
        service.sendRequest(param);
        doResult(t, service);
    }

    /**
     * 获取服务实例
     * 
     * @param t 产品实例
     * @return 服务实例
     * @see
     * @since 1.0
     */
    public abstract SendEsbService<?, ?> getService(T t);

    /**
     * 是否启用线程池
     * 
     * @return true:是,false:否
     * @see
     * @since 1.0
     */
    private boolean isThreadPoolExcuted() {
        ThreadPoolExcuted excuted = this.getClass().getAnnotation(ThreadPoolExcuted.class);
        return excuted != null;
    }

    /**
     * 获取 request
     * 
     * @return request.
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * 取得平台日期
     * 
     * @return 平台日期
     * @see
     * @since 1.0
     */
    public String getSysDate() {
        if (StringUtil.isEmpty(this.sysDate)) {
            this.sysDate = paraService.getSysDate();
        }
        return this.sysDate;
    }

    /**
     * 获取画面ID
     * 
     * @param param 请求参数
     * @return 画面ID
     * @see
     * @since 1.0
     */
    public String getPageId(Map<String, Object> param) {
        if (param == null) {
            return StringUtil.EMPTY;
        }
        return (String)param.get(InterfConstant.PAGE_ID);
    }

    /**
     * 获取 调用模块
     * 
     * @param param 请求参数
     * @return 调用模块
     * @see
     * @since 1.0
     */
    public String getCallModel(Map<String, Object> param) {
        if (param == null) {
            return StringUtil.EMPTY;
        }
        return (String)param.get(InterfConstant.CALL_MODEL);
    }

    /**
     * 设置 平台时间
     * 
     * @param sysDate 平台时间
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    /**
     * 设置属性
     * 
     * @param key 属性名
     * @param value 属性值
     */
    protected void setControllerInfo(String key, Object value) {
        controllerInfoMap.get().put(key, value);
    }

    /**
     * 取得属性值
     * 
     * @param key 属性名
     * @return 属性值
     */
    protected Object getControllerInfo(String key) {
        return controllerInfoMap.get().get(key);
    }
}
