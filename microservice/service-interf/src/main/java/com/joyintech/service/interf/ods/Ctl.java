/*
 * 文件名：Ctl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ods;

import java.io.File;
import java.io.IOException;

/**
 * 控制文件接口
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月3日
 * @see Ctl
 * @since 1.0
 */
public interface Ctl {

    /**
     * 取得控制文件
     * 
     * @param path 文件路径
     * @return 控制文件
     * @throws IOException 文件读写异常
     * @see
     * @since 1.0
     */
    File getCtlFile(String path)
        throws IOException;
}
