package com.joyintech.tams.valuation.valmodel.batch.batchtask.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.tams.valuation.valmodel.batch.batchtask.model.dto.BatchExecutingStatusDto;
import com.joyintech.tams.valuation.valmodel.batch.batchtask.service.ModValBatchTask001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author 王波
 * @date 2021-07-13
 * @see ModValBatchTask001Controller
 * @since 1.0
 */
@Controller
@PageId("ModValBatchTask001")
@RequestMapping("/modelvaluation/batch/batchtask/ModValBatchTask001")
public class ModValBatchTask001Controller extends BaseController {

    @Autowired
    private ModValBatchTask001Service modBatchTask001Service;

    @Autowired
    private IPlatformDate platformDateImpl;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        // 获取平台日
        model.addAttribute("iPlatformDate", platformDateImpl.getSysDate());
        // 平台日字符串格式
        model.addAttribute("SESSION_SYS_DATE", DateUtil.format(platformDateImpl.getSysDate()));

        model.addAttribute("BATCH_LIST", modBatchTask001Service.getBatchList(RequestUtil.getPageParam()));
        return super.init(model);
    }

    /**
     * 获取树列表
     * @return
     */
    @RequestMapping("/getInterGroup")
    @ResponseBody
    public List<TreeNode> getInterGroup(){
        Map<String,Object> map = RequestUtil.getPageParam();
        List<TreeNode> treeList = modBatchTask001Service.getInterGroup(map);
        return treeList;
    }

    /**
     * 执行按钮处理
     * @return
     */
    @RequestMapping("execute")
    @ResponseBody
    public String execute() {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return modBatchTask001Service.execute(pageParam);
    }

    @RequestMapping("getAssetInfo")
    @ResponseBody
    public Page<SelectOptionDto> getAssetInfo() {
        Map<String, Object> pageParam = RequestUtil.getParameters(getRequest());
        return modBatchTask001Service.getAssetInfo(pageParam);
    }

    @RequestMapping("findLogPage")
    @ResponseBody
    public Page<BatchExecutingStatusDto> findBatchExeDetail(String sysDate){
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        sysDate=(String)pageParam.get("importDate");
        return modBatchTask001Service.findBatchExeDetail(sysDate);
    }

}
