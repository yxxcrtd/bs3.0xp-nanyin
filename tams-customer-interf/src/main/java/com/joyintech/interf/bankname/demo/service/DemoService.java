/*
 * 文件名：DemoService.java
 * 版权：Copyright 2019-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：王波
 * 修改时间：2019/5/27 15:22
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.interf.bankname.demo.service;

import com.joyintech.interf.bankname.demo.send.DemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王波
 * @version 1.0 2019年05月27日
 * @see
 * @since 1.0
 */
@Service
public class DemoService {

    @Autowired
    private DemoDao demoDao;

    public void demo() {
        System.out.println(demoDao.send());
    }
}
