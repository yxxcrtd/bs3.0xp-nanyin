/*
 * 文件名：IDGenerator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年1月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.common.pack.common.util;

import java.util.UUID;

/**
 * ID生成工具
 * @author 何阳阳
 * @version 1.0 2018年1月16日
 * @see IDGenerator
 * @since 1.0
 */
public final class IDGenerator {
    
    /**
     * 获取一个32位长度的UUID
     * @return UUID
     */
    public static String generate() {
        return generate(32);
    }
    
    /**
     * 获取一个指定长度的UUID<br>
     * length可取值为：12、16、20、32，如果不符合以上4个值，则直接返回36位UUID
     * @param length 长度
     * @return UUID
     */
    public static String generate(int length) {
        String uuid = UUID.randomUUID().toString();
        String[] components = uuid.split("-");
        if(components.length == 5) {
            if(length == 20) {
                return components[0] + components[1] + components[2] + components[3];
            }
            else if(length == 32) {
                return components[0] + components[1] + components[2] + components[3] + components[4];
            }
        }
        return uuid;
    }
}
