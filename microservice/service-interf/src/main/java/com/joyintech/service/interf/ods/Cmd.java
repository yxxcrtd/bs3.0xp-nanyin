/*
 * 文件名：Cmd.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ods;

/**
 * 命令取得接口
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月3日
 * @see Cmd
 * @since 1.0
 */
public interface Cmd {

    /**
     * 获取命令字符串
     * 
     * @param ctl 执行控制文件
     * @return 命令字符串
     * @see
     * @since 1.0
     */
    String getCmd(String ctl);
}
