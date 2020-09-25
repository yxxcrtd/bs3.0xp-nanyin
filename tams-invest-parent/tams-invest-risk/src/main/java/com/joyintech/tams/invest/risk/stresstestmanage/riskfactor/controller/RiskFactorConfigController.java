/*
 * 文件名：RiskFactor003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：历史页面展示
 * 修改人：chenyunlong
 * 修改时间：2019年8月24日
 * 修改内容：新建
 * 系统名称：AMAS
 */

package com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.dto.RiskFactor003Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.service.RiskFactor001Service;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.service.RiskFactor003Service01;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.service.RiskFactorConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author chenyunlong
 * @version 1.0 2019年8月24日
 * @see RiskFactorConfigController
 * @since 1.0
 * peizhi
 */
@Controller
@PageId("RiskFactorConfig")
@RequestMapping("/invest/risk/stresstestmanage/riskfactor/RiskFactorConfig")
public class RiskFactorConfigController extends BaseController {

    @Autowired(required = false)
    private RiskFactorConfigService RiskFactorConfigService;

    @Autowired(required = false)
    private RiskFactor003Service01 RiskFactor003Service01;

    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub风险因子管理_二级分类
        this.setSelectOptionToModel(model, DictCode.风险因子管理_因子值结构);
        this.setSelectOptionToModel(model, DictCode.风险因子管理_启用状态);
        this.setSelectOptionToModel(model, DictCode.风险因子管理_值处理方式);
        this.setSelectOptionToModel(model, DictCode.风险因子管理_二级分类);
        return super.init(model);
    }
/**
 *
 * 配置信息转换成sql
 * @author
 * @version
 * @see
 * @since 1.0
 */
    @ResponseBody
    @RequestMapping("CellSql")
    public List<String> selectForPage()
        throws Exception {
        Map<String, Object> map = RequestUtil.getParameters(getRequest());

        return RiskFactorConfigService.selectAll(map);
    }


   
}