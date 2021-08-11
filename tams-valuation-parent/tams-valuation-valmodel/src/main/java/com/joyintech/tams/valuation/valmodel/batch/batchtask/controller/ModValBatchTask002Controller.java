package com.joyintech.tams.valuation.valmodel.batch.batchtask.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.valmodel.batch.batchtask.model.dto.ModBatchDetailDto;
import com.joyintech.tams.valuation.valmodel.batch.batchtask.service.ModValBatchTask001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author 王波
 * @date 2021-07-20
 * @see ModValBatchTask002Controller
 * @since 1.0
 */
@Controller
@PageId("ModValBatchTask002")
@RequestMapping("/modelvaluation/batch/batchtask/ModValBatchTask002")
public class ModValBatchTask002Controller  extends BaseController {

    @Autowired
    private ModValBatchTask001Service modValBatchTask001Service;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        model.addAttribute("detailSeqNo", RequestUtil.getParam("detailSeqNo"));
        model.addAttribute("taskId", RequestUtil.getParam("taskId"));
        model.addAttribute("execStatus", RequestUtil.getParam("execStatus"));

        return super.init(model);
    }

    @RequestMapping(value = "findBatchExeTask")
    @ResponseBody
    public Page<ModBatchDetailDto> findBatchExeTask() {
        return modValBatchTask001Service.findBatchExeTask(RequestUtil.getPageParam());
    }


    /**
     * 导出信息excel
     * @return excel
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export(){
        List<ModBatchDetailDto> dtoList
                = modValBatchTask001Service.findBatchExeTaskForExport(RequestUtil.getPageParam());
        File outFile = modValBatchTask001Service.makeFile(
                "/com/joyintech/tams/valuation/valmodel/batch/batchtask/config/export_conf.xml", dtoList);
        return ExportUtil.export(
                "批处理详情" + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
}
