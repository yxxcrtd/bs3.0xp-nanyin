package com.joyintech.tams.valuation.lossmodel.batchtask.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.tams.valuation.lossmodel.batchtask.model.dto.BatchExecutingStatusDto;
import com.joyintech.tams.valuation.lossmodel.batchtask.service.ModImpaireBatchTask001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author 王波
 * @date 2021-07-27
 * @see ModImpaireBatchTask001Controller
 * @since 1.0
 */
@Controller
@PageId("ModImpaireBatchTask001")
@RequestMapping("/model/impairment/batch/batchtask/ModImpaireBatchTask001")
public class ModImpaireBatchTask001Controller extends BaseController {
    
    @Autowired
    private ModImpaireBatchTask001Service modImpaireBatchTask001Service;

    @Autowired
    private IPlatformDate platformDateImpl;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        // 获取平台日
        model.addAttribute("iPlatformDate", platformDateImpl.getSysDate());
        // 平台日字符串格式
        model.addAttribute("SESSION_SYS_DATE", DateUtil.format(platformDateImpl.getSysDate()));

        model.addAttribute("BATCH_LIST", modImpaireBatchTask001Service.getBatchList(RequestUtil.getPageParam()));
        return super.init(model);
    }

    /**
     * 执行按钮处理
     * @return
     */
    @RequestMapping("execute")
    @ResponseBody
    public String execute() {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return modImpaireBatchTask001Service.execute(pageParam);
    }

    @RequestMapping("getAssetInfo")
    @ResponseBody
    public Page<SelectOptionDto> getAssetInfo() {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return modImpaireBatchTask001Service.getAssetInfo(pageParam);
    }

    @RequestMapping("findLogPage")
    @ResponseBody
    public Page<BatchExecutingStatusDto> findBatchExeDetail(String sysDate){
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        sysDate=(String)pageParam.get("importDate");
        return modImpaireBatchTask001Service.findBatchExeDetail(sysDate);
    }

}
