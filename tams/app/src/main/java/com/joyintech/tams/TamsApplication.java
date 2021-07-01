package com.joyintech.tams;

import com.joyintech.framework.JoyinApplication;
import com.joyintech.framework.config.WebApplication;
import com.joyintech.tams.common.ErrorPageFilter;
import org.springframework.context.annotation.Bean;

import java.util.Locale;

/**
 * @author 王波
 * @version 1.0 2019年04月23日
 * @see 
 * @since 1.0
 */
public class TamsApplication extends WebApplication {
    @Bean
    public ErrorPageFilter errorPageFilter() {
        return new ErrorPageFilter();
    }
    public static void main(String [] args) {
        Locale local = new Locale("zh", "CN");
        Locale.setDefault(local);
        JoyinApplication.run(TamsApplication.class, args);
    }
}
