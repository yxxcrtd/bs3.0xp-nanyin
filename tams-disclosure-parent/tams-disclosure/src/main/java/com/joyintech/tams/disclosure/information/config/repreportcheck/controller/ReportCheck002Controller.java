package com.joyintech.tams.disclosure.information.config.repreportcheck.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.common.base.entity.RepDataCheck;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.disclosure.information.config.repreportcheck.service.ReportCheck002Service;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@PageId("ReportCheck002")
@RequestMapping("/disclosure/information/config/repreportcheck/ReportCheck002")
public class ReportCheck002Controller extends BaseController {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ReportCheck002Controller.class);

    @Autowired
    private ReportCheck002Service reportCheck002Service;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCodeExt1.信披数据校验比较符);

        List<RepDataCheck>dataList=reportCheck002Service.getRepDataCheckList();
        model.addAttribute("dataList",dataList);

        return super.init(model);
    }

    @ResponseBody
    @PostMapping("check")
    public AjaxResult check(@RequestBody Map<String, Object> param) throws IOException {
        List<RepDataCheck>dataList=JsonUtils.json2List((String) param.get("dataList"),RepDataCheck.class);
        return reportCheck002Service.check(dataList);
    }

    @ResponseBody
    @PostMapping("save")
    public AjaxResult save(@RequestBody Map<String, Object> param) throws IOException {
        List<RepDataCheck>dataList=JsonUtils.json2List((String) param.get("dataList"),RepDataCheck.class);
        return reportCheck002Service.save(dataList);
    }

}
