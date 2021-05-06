package com.joyintech.tams.invest.prebatch.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.prebatch.dto.SysPrebatchResultDetail001Dto;
import com.joyintech.tams.invest.prebatch.service.SysPrebatchResult002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@PageId("SysPrebatchResult002")
@RequestMapping("/invest/prebatch/SysPrebatchResult002")
public class SysPrebatchResult002Controller extends BaseController {

    @Autowired
    private SysPrebatchResult002Service sysPrebatchResult002Service;

    @Override
    public String init(Model model) {
        // String operateType = getParam("operateType");
        String seqNo = getParam("seqNo");
        String batchSeqNo = getParam("batchSeqNo");
        // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(seqNo)) {
            // 根据主键获取业务实体
           // sysPrebatchResult002Service
            // 设置到model中
            model.addAttribute("seqNo", seqNo);
        }

        return super.init(model);
    }

    /**
     * 查询 <功能详细描述>
     *
     * @return page分页参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<SysPrebatchResultDetail001Dto> findPage()
            throws Exception {
        Page<SysPrebatchResultDetail001Dto> page = sysPrebatchResult002Service.findPage(
                RequestUtil.getPageParam());
        return page;
    }

    /**
     * 组合代码联想下拉框
     *
     * @return 组合代码列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryPortfolioIdInfo")
    public Page<SelectOptionDto> queryPortfolioIdInfo() {

        return sysPrebatchResult002Service.queryPortfolioIdInfo(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("queryFinprodIdInfo")
    public Page<SelectOptionDto> queryFinprodIdInfo() {
        return sysPrebatchResult002Service.queryFinprodIdInfo(RequestUtil.getPageParam());
    }

}
