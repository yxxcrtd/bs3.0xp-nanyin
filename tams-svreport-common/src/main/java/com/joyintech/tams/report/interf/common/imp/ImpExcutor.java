/*
 * 文件名：ImpExcutor.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年11月22日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.interf.common.imp;

import java.io.IOException;

/**
 * 数据导入接口
 * 
 * @author 何阳阳
 * @version 1.0 2017年11月22日
 * @see ImpExcutor
 * @since 1.0
 */
public interface ImpExcutor {

    /**
     * 导入数据
     * 
     * @param args 导入参数
     * @return 结果
     */
    boolean excute(ImpArgument args) throws IOException, InterruptedException;
}
