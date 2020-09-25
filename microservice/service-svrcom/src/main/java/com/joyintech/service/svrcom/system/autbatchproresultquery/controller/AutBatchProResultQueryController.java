/*
 * 文件名：AutBatchProResultQueryController001.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zuoyinyi
 * 修改时间：2017年11月14日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.service.svrcom.system.autbatchproresultquery.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.system.autbatchproresultquery.dto.AutBatchProResultQuery001Dto;
import com.joyintech.tams.report.system.autbatchproresultquery.service.AutBatchProResultQuery001Service;

/**
 * 自动报送批处理服务r
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see AutBatchProResultQueryController
 * @since 1.0
 */
@RestController
@RequestMapping("/AutBatchProResultQuery")
public class AutBatchProResultQueryController{

    /**
     * 自动报送批处理结果查询Service
     */
    @Autowired
    private AutBatchProResultQuery001Service autBatchProResultQuery001Service;


    /**
     *
     * 查询自动批处理日志结果列表
     * @return 结果列表，分页显示
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<AutBatchProResultQuery001Dto> queryBatchProResult(@RequestBody Map<String, Object> param) {
        return autBatchProResultQuery001Service.queryBatchProResult(param);
    }
}
