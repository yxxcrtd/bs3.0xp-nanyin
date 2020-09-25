package com.joyintech.invest.interf.common.core.utility;

import com.joyintech.invest.interf.common.core.service.InterfRecvComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: tams-base
 * @description: 投管接收测试
 * @author: 王波
 * @create: 2020-02-10 11:29
 */
@RestController
@RequestMapping("/invest/interf/recv")
public class InvestRecvController {

    @Autowired
    private InterfRecvComponent recvComponent;

    @RequestMapping("commonRecv")
    public Map<String, Object> commonRecv(Map<String, Object> dataMap) {
        return recvComponent.recv(dataMap);
    }

}
