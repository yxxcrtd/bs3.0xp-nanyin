package com.joyintech.invest.app;

import com.joyintech.framework.JoyinApplication;
import com.joyintech.framework.config.WebApplication;

/**
 * 投管工程单体应用启动
 */
public class InvestSingleAppliation extends WebApplication {

    public static void main(String[] args) {
        JoyinApplication.run(InvestSingleAppliation.class, args);
    }
}
