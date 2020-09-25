package com.joyintech.service.period.trdprojint.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.period.trdprojint.service.TrdProjectDurationInterestService;
import com.joyintech.tams.invest.common.dto.period.trdprojbuyoff.TrdProjectBuyOffInterestDto;

/**
 * @program: component-base
 * @description: 买断利率管理微服务
 * @author: 强成西
 * @create: 2018-10-23 14:52
 **/
@RestController
@RequestMapping("/TrdProjectDurationInterest")
public class TrdProjectDurationInterestController {

    @Autowired
    private TrdProjectDurationInterestService trdProjectDurationInterestService;

    /**
     * 根据条件查询买断利率
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdProjectBuyOffInterestDto> findPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                      @PathVariable("length") int length) {
        return trdProjectDurationInterestService.findPage(page, length, param);
    }

}
