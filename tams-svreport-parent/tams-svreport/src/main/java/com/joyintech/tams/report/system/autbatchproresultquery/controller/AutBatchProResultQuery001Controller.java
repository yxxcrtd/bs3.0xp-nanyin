/*
 * 文件名：AutBatchProResultQueryController001.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zuoyinyi
 * 修改时间：2017年11月14日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.system.autbatchproresultquery.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.system.autbatchproresultquery.dto.AutBatchProResultQuery001Dto;
import com.joyintech.tams.report.system.autbatchproresultquery.service.AutBatchProResultQueryMService;

/**
 * 自动报送批处理结果查询controller
 * @author zuoyinyi
 * @version 1.0 2017年11月14日
 * @see AutBatchProResultQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("AutBatchProResultQuery001")
@RequestMapping("/svreport/system/autbatchproresultquery/AutBatchProResultQuery001")
public class AutBatchProResultQuery001Controller extends BaseController{

    /**
     * 自动报送批处理结果查询Service
     */
    @Autowired
    private AutBatchProResultQueryMService autBatchProResultQueryMService;

    /**
     * {@inheritDoc}
     * @param model Model
     * @return pageId 画面ID
     */
    @Override
    public String init(Model model) {
        List<SelectOptionDto> optionsList = new ArrayList<>();
        List<BatchBeanName> autoSendCBBatchList = BatchBeanName.getAutoSendCBBatchList();
        for (BatchBeanName batchBeanName : autoSendCBBatchList) {
            SelectOptionDto options = new SelectOptionDto();
            options.setId(batchBeanName.getTaskId());
            options.setText(batchBeanName.getBatchName());
            optionsList.add(options);
        }

//        BatchBeanName[] value = BatchBeanName.values();
//        for (int i=0 ; i<value.length;i++) {
//            SelectOptionDto options = new SelectOptionDto();
//            options.setId(value[i].getTaskId());
//            options.setText(value[i].getBatchName());
//            optionsList.add(i, options);
//        }
        model.addAttribute("taskInfoList", JsonUtils.object2Json(optionsList.toArray()));
        return super.init(model);
    }

    /**
     *
     * 查询自动批处理日志结果列表
     * @return 结果列表，分页显示
     */
    @ResponseBody
    @RequestMapping(ReportGlobalConstant.SEARCH_PAGE)
    public Page<AutBatchProResultQuery001Dto> queryBatchProResult() {
        Map<String,Object> map = RequestUtil.getPageParam();
        return autBatchProResultQueryMService.queryBatchProResult(map);
    }
}
