package com.joyintech.tams.exchange.common.constant.joyin;

/**
 * @Author: think
 * @Description: 系统参数开关
 * @Date: Created in 10:53 2020/11/4
 */
public enum Switches {
    OPEN("开启", "Y"),
    CLOSE("关闭", "N");

    public String name;
    public String value;

    Switches(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
