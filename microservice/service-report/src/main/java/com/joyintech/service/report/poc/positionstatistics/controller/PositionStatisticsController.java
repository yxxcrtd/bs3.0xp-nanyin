/*
 * 文件名：SecAssetStatisticsController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.poc.positionstatistics.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.poc.positionstatistics.dto.PositionStatisticsDto;
import com.joyintech.service.report.poc.positionstatistics.service.PositionStatisticsService; 

/**
 * 头寸统计表微服务Controller
 * 〈功能详细描述〉
 * @author liupeilin
 * @version 1.0 2019年11月6日
 * @see PositionStatisticsController
 * @since 1.0
 */
@RestController
@RequestMapping("/PositionStatistics")
public class PositionStatisticsController {
    @Autowired
    private PositionStatisticsService positionStatisticsService;
    
    /**
     * 画面查询方法
     * 
     * @return 查询结果集
     * @throws Exception
     *             异常
     */
    @PostMapping("selectForPage")
    public List<PositionStatisticsDto> selectForPage(@RequestBody Map<String, Object> selectParam){
        List<PositionStatisticsDto> list = positionStatisticsService.selectForPage(selectParam);
        return list;
    } 
    
    /**
     * 理财经理的下拉框
     * 
     * @return 查询结果集
     * @throws Exception
     *             异常
     */
    @PostMapping("queryManagerInfo")
    public List<SelectOptionDto> queryManagerInfo(@RequestBody Map<String, Object> param) {
        return positionStatisticsService.queryManagerInfo(param);
    }

}
