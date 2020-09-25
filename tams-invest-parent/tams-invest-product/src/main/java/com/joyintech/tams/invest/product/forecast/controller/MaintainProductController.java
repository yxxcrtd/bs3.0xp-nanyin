/*
 * 文件名：MaintainProductController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2019年7月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.forecast.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.product.forecast.dto.FinFlowForecastConfig;
import com.joyintech.tams.invest.product.forecast.dto.FinFlowForecastDealAmontDto;
import com.joyintech.tams.invest.product.forecast.service.FinFlowForecastService;


/**
 * 流动性预测表维护-产品管理处 〈功能详细描述〉
 * 
 * @author chendijun
 * @version 1.0 2019年7月31日
 * @see MaintainProductController
 * @since 1.0
 */
@Controller
@PageId("MaintainProduct001")
@RequestMapping("/invest/product/forecast/MaintainProduct001")
public class MaintainProductController extends BaseController {

    @Autowired
    private FinFlowForecastService flowForecastService;

    @Override
    public String init(Model model) {
        FinFlowForecastConfig entity = new FinFlowForecastConfig();
        entity.setQueryDate(DateUtil.format(DateUtil.platDateFromCache(), DateUtil.DEFAULT_DATE_FORMAT_EN));
        entity.setDepType(DictCodeListExt2.流动性预测维护_部门类型_产品管理处.getValue());
        model.addAttribute("entity", entity);
        model.addAttribute("TARGETTYPE_JJCPKFQJSS",
            DictCodeListExt2.流动性预测维护_指标种类_净值型产品开放期净申赎.getValue());
        return super.init(model);
    }

    /**
     * 查询流动性储备情况列表 <功能详细描述>
     * 
     * @param feeId
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findReserveDetail")
    public Page<FinFlowForecastConfig> findReserveDetail()
        throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("queryDate", this.getRequest().getParameter("queryDate"));
        Page<FinFlowForecastConfig> reserveDetailList = flowForecastService.findReserveDetail(map);
        return reserveDetailList;
    }

    @ResponseBody
    @RequestMapping("getTargetName")
    public FinFlowForecastConfig getTargetName()
        throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String queryType = this.getRequest().getParameter("queryType");
        String queryDate = this.getRequest().getParameter("queryDate");
        FinFlowForecastConfig flowForecastConfig = new FinFlowForecastConfig();
        if (StringUtil.equals(queryType, "findReserve")) {
            flowForecastConfig = flowForecastService.getTargetNameOfReserve();
        }
        else if (StringUtil.equals(queryType, "forecast")) {
            Date qryDate = sdf.parse(queryDate);
            flowForecastConfig = flowForecastService.dateTransfore(qryDate, flowForecastConfig);
        }
        else if (StringUtil.equals(queryType, "netValueOpenforecast")) {
            Date qryDate = sdf.parse(queryDate);
            flowForecastConfig = flowForecastService.dateTransfore(qryDate, flowForecastConfig);
        }

        return flowForecastConfig;
    }

    @ResponseBody
    @RequestMapping("findForecast")
    public Page<FinFlowForecastConfig> findForecast()
        throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("queryDate", this.getRequest().getParameter("queryDate"));
        map.put("depType", this.getRequest().getParameter("depType"));
        Page<FinFlowForecastConfig> forecastList = flowForecastService.findForecast(map);
        return forecastList;
    }

    @ResponseBody
    @RequestMapping("findNetValueOpenforecast")
    public Page<FinFlowForecastDealAmontDto> findNetValueOpenforecast()
        throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("queryDate", this.getRequest().getParameter("queryDate"));
        Page<FinFlowForecastDealAmontDto> netValueOpenforecastList = flowForecastService.findNetValueOpenforecast(
            map);
        return netValueOpenforecastList;
    }

    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated FinFlowForecastConfig dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        String msg = flowForecastService.save(dto);
        return new AjaxResult(msg);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated FinFlowForecastConfig dto, BindingResult result) {
        return commonCheck(result, "save");
    }
}
