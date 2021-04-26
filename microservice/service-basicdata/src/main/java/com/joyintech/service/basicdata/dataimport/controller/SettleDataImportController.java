/*
 * 文件名：SettleDataImport001Controller.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年08月06日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.service.basicdata.dataimport.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.basicdata.dataimport.dto.SettleTransResultDto;
import com.joyintech.service.basicdata.dataimport.service.SettleDataImportService;
import com.joyintech.tams.common.base.entity.SettleTransPara;

/**
 * @author 王波
 * @date 2020年08月06日
 * @see SettleDataImportController
 * @since 1.0
 */
@RestController
@RequestMapping("/SettleDataImport001")
public class SettleDataImportController {

    @Autowired
    private SettleDataImportService settleDataImportService;

    @PostMapping("findLogPage/{page}/{length}")
    public Page<SettleTransResultDto> findLogPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page, 
    			@PathVariable("length") int length) throws Exception{
        return settleDataImportService.findLogPage(page, length, pageParam);
    }

    @PostMapping("insertSettleTransParas")
    public Integer insertSettleTransParas(@RequestBody List<SettleTransPara> settleTransParas) {
        return settleDataImportService.insertSettleTransParas(settleTransParas);
    }

    @PostMapping("checkIsBatchRunning")
    public int checkIsBatchRunning() {
        return settleDataImportService.checkIsBatchRunning();
    }

}
