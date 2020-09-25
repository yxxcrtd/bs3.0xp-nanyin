package com.joyintech.service.riskrds.limitmanagement.rsksupervisionlog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionlog.dto.RskSupervisionLogDto;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionlog.service.RskSupervisionLogService;

/**
 * 指数日志管理
 * @author : 裴家俊
 * @version V1.0
 * @Project: microservice
 */
@RestController("backRskSupervisionLogController")
@RequestMapping("/RskSupervisionLog")
public class RskSupervisionLogController {

    /**
     * 指数日志管理Service
     */
    @Autowired
    private RskSupervisionLogService rskSupervisionLogService;

    /**
     * 画面查询方法
     *
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<RskSupervisionLogDto> selectAll(@RequestBody Map<String, Object> param,
                                                           @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return rskSupervisionLogService.selectForPage(page, length, param);
    }

}
