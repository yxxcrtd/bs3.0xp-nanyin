package com.joyintech.service.valuation.lossmodel.batchtask.controller;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.lossmodel.batchtask.dto.BatchTaskDto;
import com.joyintech.service.valuation.lossmodel.batchtask.service.ModImpairementBatchTaskService;
import com.joyintech.system.batch.batchmonitor.dto.BatchExecutingStatusDto;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModBatchLogDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 王波
 * @date 2021-07-27
 * @see ModImpairementBatchTaskController
 * @since 1.0
 */
@RestController
@RequestMapping("modBatchTask")
public class ModImpairementBatchTaskController {

    @Autowired
    private ModImpairementBatchTaskService modImpairementBatchTaskService;

    @PostMapping("getAllBatchTask")
    public List<BatchTaskDto> getAllBatchTask(@RequestBody Map<String, Object> params) {
        return modImpairementBatchTaskService.getBatchTask(params);
    }


    @PostMapping("insertBatch")
    public boolean insertBatch(@RequestBody Map<String, Object> params) {
        Date dealDate = DateUtil.parse(params.get("execDate").toString());
        // 资产代码集合
        String finprodIds = (String)params.get("finprodId");

        modImpairementBatchTaskService.cleanBatchParams(params);

        return modImpairementBatchTaskService.executeBatch(dealDate, dealDate,finprodIds);
    }

    @PostMapping("checkIsRunning")
    public boolean checkIsRunning(@RequestBody Map<String, Object> params) {
        // 资产代码集合
        String finprodIds = (String)params.get("finprodId");
        return modImpairementBatchTaskService.checkIsRunning(finprodIds);
    }

    @PostMapping("findBatchExeTask/{page}/{length}")
    public Page<ModBatchLogDetailDTO> findBatchExeTask(@RequestBody Map<String, Object> params,
                                                       @PathVariable("page") int page, @PathVariable("length") int length) {
        return modImpairementBatchTaskService.findBatchExeTask(params, page, length);
    }

    @PostMapping("findBatchExeTaskForExport")
    public List<ModBatchLogDetailDTO> findBatchExeTaskForExport(@RequestBody Map<String, Object> params) {
        return modImpairementBatchTaskService.findBatchExeTaskForExport(params);
    }


    @PostMapping("getAssetInfo/{page}/{length}")
    public Page<SelectOptionDto> getAssetInfo(@RequestBody Map<String, Object> params,
                                              @PathVariable("page") int page, @PathVariable("length") int length) {
        return modImpairementBatchTaskService.getAssetInfo(params, page, length);
    }


    @PostMapping("findBatchExeDetail/{page}/{length}")
    public Page<BatchExecutingStatusDto> findBatchExeDetail(@RequestBody Map<String, Object> params,
                                                            @PathVariable("page") int page, @PathVariable("length") int length) {
        return modImpairementBatchTaskService.findBatchExeDetail(params, page, length);
    }
}
