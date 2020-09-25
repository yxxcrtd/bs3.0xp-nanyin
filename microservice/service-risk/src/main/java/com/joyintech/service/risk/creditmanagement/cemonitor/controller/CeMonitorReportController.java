package com.joyintech.service.risk.creditmanagement.cemonitor.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.risk.creditmanagement.cemonitor.dto.CreditLinesDealDto;
import com.joyintech.service.risk.creditmanagement.cemonitor.service.CeMonitorReportService;

/**
 * @description: CeMonitorReportController
 * @author: 裴家俊
 * @create: 2018-11-01 14:37
 **/
@RestController
@RequestMapping("/CeMonitorReport")
public class CeMonitorReportController {
    /**
     * 授信额度监测Service
     */
    @Autowired
    private CeMonitorReportService ceMonitorReportService;

    /**
     * 画面查询方法
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findOrgPage/{page}/{length}")
    public Page<CreditLinesDealDto> selectOrgForPage(@RequestBody Map<String, Object> param,
                                                        @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        return ceMonitorReportService.selectOrgForPage(page, length, param);
    }
    /**
     * 画面查询方法
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findAssetPage/{page}/{length}")
    public Page<CreditLinesDealDto> selectAssetForPage(@RequestBody Map<String, Object> param,
                                                        @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        return ceMonitorReportService.selectAssetForPage(page, length, param);
    }
    /**
     * 画面查询方法
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findAssetGroupPage/{page}/{length}")
    public Page<CreditLinesDealDto> selectAssetGroupForPage(@RequestBody Map<String, Object> param,
                                                        @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        return ceMonitorReportService.selectAssetGroupForPage(page, length, param);
    }
}
