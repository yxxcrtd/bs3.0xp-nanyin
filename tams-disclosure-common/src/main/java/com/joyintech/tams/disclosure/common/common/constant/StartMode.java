/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.joyintech.tams.disclosure.common.common.constant;

/**
 * batch启动模式
 *
 * @author 强成西
 */
public enum StartMode {
    手动("0"), 自动("1");

    private String code;

    private StartMode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static StartMode fromCode(String code) {
        for (StartMode value : StartMode.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
