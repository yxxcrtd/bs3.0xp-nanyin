/*
 * 文件名：SettleDataClearController.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年08月10日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.service.basicdata.dataclear.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.basicdata.dataclear.dto.SettleBatchResultDto;
import com.joyintech.service.basicdata.dataclear.service.SettleDataClearService;
import com.joyintech.tams.common.base.entity.SettleBatchPara;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 王波
 * @date 2020年08月10日
 * @see SettleDataClearController
 * @since 1.0
 */
@RestController
@RequestMapping("/SettleDataClear")
public class SettleDataClearController {

    @Autowired
    private SettleDataClearService settleDataClearService;

    /**
     * 分页查询数据
     * @param page 页数
     * @param length 大小
     * @param pageParam 参数
     * @return 分页数据
     * @throws Exception
     */
    @PostMapping("findLogPage/{page}/{length}")
    public Page<SettleBatchResultDto> findLogPage(@PathVariable("page")int page, @PathVariable("length")int length,
                                                  @RequestBody Map<String, Object> pageParam) throws Exception{
        return settleDataClearService.findLogPage(page, length, pageParam);
    }

    /**
     * 检查是否有批处理正在进行
     * @return
     */
    @PostMapping("checkIsBatchRunning")
    public int checkIsBatchRunning() {
        return settleDataClearService.checkIsBatchRunning();
    }

    /**
     * 插入 数据清算参数表
     * @param settleTransParas  数据清算参数
     * @return
     */
    @PostMapping("insertSettleBatchParas")
    public Integer insertSettleBatchParas(@RequestBody List<SettleBatchPara> settleTransParas) {
        return settleDataClearService.insertSettleTransParas(settleTransParas);
    }
}
