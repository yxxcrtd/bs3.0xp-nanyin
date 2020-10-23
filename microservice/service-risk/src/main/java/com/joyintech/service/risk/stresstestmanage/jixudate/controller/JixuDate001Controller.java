/*
 * 文件名：JixuDate001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年11月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.jixudate.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.risk.stresstestmanage.jixudate.service.JixuDate001Service;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年11月15日
 * @see JixuDate001Controller
 * @since 1.0
 */
@RestController("backJixuDate001Controller")
@RequestMapping("/JixuDate001")
public class JixuDate001Controller {

    @Autowired(required=false)
    private JixuDate001Service jixuDate001Service;
    
    /**
     * 得到jixu日期
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getJixuDate")
    public Date getJixuDate() throws Exception {
          
        return jixuDate001Service.getJixuDate();
    }
    
    /**
     * 更新jixu日期
     * <一句话功能简述>
     * <功能详细描述>
     * @param newDate
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("updateJixuDate")
    public int updateJixuDate(@RequestBody Date newDate) throws Exception {
          
        return jixuDate001Service.updateJixuDate(newDate);
    }
    
    
    
}
