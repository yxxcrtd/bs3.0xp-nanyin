package com.joyintech.service.valuation.valmodel.batch.batchtask.controller;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.service.valuation.valmodel.batch.batchtask.dto.BatchExecutingStatusDto;
import com.joyintech.service.valuation.valmodel.batch.batchtask.dto.BatchTaskDto;
import com.joyintech.service.valuation.valmodel.batch.batchtask.service.ModBatchTaskService;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModBatchLogDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author 王波
 * @date 2021-07-13
 * @see ModBatchTaskController
 * @since 1.0
 */
@RestController
@RequestMapping("modValBatchTask")
public class ModBatchTaskController {

    @Autowired
    private ModBatchTaskService modBatchTaskService;

    @PostMapping("getAllBatchTask")
    public List<BatchTaskDto> getAllBatchTask(@RequestBody Map<String, Object> params) {
        return modBatchTaskService.getBatchTask(params);
    }

    @PostMapping("insertBatch")
    public boolean insertBatch(@RequestBody Map<String, Object> params) {
        Date dealDate = DateUtil.parse(params.get("execDate").toString());
        // 选中的数据文件
        String datas = (String)params.get("datas");

        // 真实操作人
        String realName = (String)params.get("realName");

        // 资产代码集合
        String finprodIds = (String)params.get("finprodId");

        String selectedBatchGroup = datas;
        String selectedProductGroup = "";
        String startDate = DateUtil.format(dealDate, DateUtil.DEFAULT_DATE_FORMAT_EN);
        String endDate = DateUtil.format(dealDate, DateUtil.DEFAULT_DATE_FORMAT_EN);
        String batchGroupType = GlobalConstant.BATCH_EXECUTE_TYPE_SINGLE;
        String prodGroupType = GlobalConstant.BATCH_EXECUTE_TYPE_GROUP;
        /** modify by yanya on 20210901 检验是否有跑批的数据，单独检验 **/
        //if (!modBatchTaskService.checkIsRunning(finprodIds)) {
            modBatchTaskService.cleanBatchParams();
            return modBatchTaskService.executeBatch(realName, selectedBatchGroup,
                    selectedProductGroup, startDate, endDate, batchGroupType, prodGroupType, finprodIds);
        //}
        //return false;
    }

    /**
     * 检验是否有正在跑批的产品
     * @param params
     * @return
     */
    @PostMapping("checkIsRunning")
    public boolean checkIsRunning(@RequestBody Map<String, Object> params) {
        // 资产代码集合
        String finprodIds = (String) params.get("finprodId");
        return modBatchTaskService.checkIsRunning(finprodIds);
    }


    @PostMapping("findBatchExeTask/{page}/{length}")
    public Page<ModBatchLogDetailDTO> findBatchExeTask(@RequestBody Map<String, Object> params,
                                                       @PathVariable("page") int page, @PathVariable("length") int length) {
        return modBatchTaskService.findBatchExeTask(params, page, length);
    }

    @PostMapping("findBatchExeTaskForExport")
    public List<ModBatchLogDetailDTO> findBatchExeTaskForExport(@RequestBody Map<String, Object> params) {
        return modBatchTaskService.findBatchExeTaskForExport(params);
    }


    @PostMapping("getAssetInfo/{page}/{length}")
    public Page<SelectOptionDto> getAssetInfo(@RequestBody Map<String, Object> params,
                                              @PathVariable("page") int page, @PathVariable("length") int length) {
        return modBatchTaskService.getAssetInfo(params, page, length);
    }


    @PostMapping("findBatchExeDetail/{page}/{length}")
    public Page<BatchExecutingStatusDto> findBatchExeDetail(@RequestBody Map<String, Object> params,
                                                            @PathVariable("page") int page, @PathVariable("length") int length) {
        return modBatchTaskService.findBatchExeDetail(params, page, length);
    }

}
