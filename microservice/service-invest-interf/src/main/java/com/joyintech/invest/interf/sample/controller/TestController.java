package com.joyintech.invest.interf.sample.controller;

import com.joyintech.invest.interf.sample.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test
 *
 * @author 王波
 * @version 1.0 2020年01月16日
 * @see TestController
 * @since 1.0
 */
@RequestMapping("test")
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("send")
    public void sendTest() {
        testService.test();
    }
}
