package com.joyintech.service.riskrds.limitmanagement.interlog.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.riskrds.limitmanagement.interlog.dto.RepInterLogDto;
import com.joyintech.service.riskrds.limitmanagement.interlog.service.RepInterLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 接口日志管理
 * @author 郑俊平
 * @version 1.0 2019年9月4日
 * @see RepInterLogController
 * @since 1.0
 */
@RestController("backRepInterLogController")
@RequestMapping("/RepInterLog")
public class RepInterLogController {
    /**
     * 接口日志管理Service
     */
    @Autowired
    private RepInterLogService repInterLogService;

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
    public Page<RepInterLogDto> selectAll(@RequestBody Map<String, Object> param,
                                          @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        return repInterLogService.selectForPage(page, length, param);
    }
    
    @GetMapping("selectInfo")
    public RepInterLogDto selectInfo(String uuid){
    	return repInterLogService.selectUuid(uuid);
    }
}
