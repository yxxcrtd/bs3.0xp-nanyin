package com.joyintech;

import com.joyintech.framework.JoyinApplication;
import com.joyintech.framework.config.WebApplication;

/**
 * 平台启动类
 */
public class PlatformApplication extends WebApplication {

   public static void main(String[] args) {
       JoyinApplication.run(PlatformApplication.class, args);
   }

}
