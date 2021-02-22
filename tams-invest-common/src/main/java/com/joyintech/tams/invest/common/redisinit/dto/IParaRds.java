/*
 * 文件名：ICond.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.redisinit.dto;

/**
 * 初始化REDIS相关条件
 * 
 * @author 裴家俊
 * @version 1.0 2019年9月20日
 * @see IParaRds
 * @since 1.0
 */
public interface IParaRds {

   String getCommonBusinessType();

    String getParaCode();

    String getRedisModel();

    String getField();

}
