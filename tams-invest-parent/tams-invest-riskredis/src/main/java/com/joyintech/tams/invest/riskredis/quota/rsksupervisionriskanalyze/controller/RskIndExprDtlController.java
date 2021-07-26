/*
 * 文件名：RskIndExprDtlController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年10月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.riskredis.quota.rsksupervisionriskanalyze.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionriskanalyze.dto.RskIndExprDtlDto;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionriskanalyze.service.RskIndExprDtlService;


/**
 * 监控值计算明细
 * 
 * @author zhangrui1
 * @version 1.0 2020年10月28日
 * @see RskIndExprDtlController
 * @since 1.0
 */
@Controller
@PageId("RskIndExprDtl")
@RequestMapping("/invest/riskredis/quota/rsksupervisionriskanalyze/RskIndExprDtl")
public class RskIndExprDtlController extends BaseController {

    @Autowired
    private RskIndExprDtlService service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // 运算符下拉框
        setSelectOptionToModel(model, BussinessDictCodeRds.操作符);
        // 指标代码
        String indId = RequestUtil.getParam("indId");
        // 指标名称
        String indName = RequestUtil.getParam("indName");
        // 指标计算明细
        String mathProcess = RequestUtil.getParam("mathProcess");
        List<RskIndExprDtlDto> paramList = service.initList(indId, indName, mathProcess);
        // 参数下拉框
        List<SelectOptionDto> select = service.getParamSelect();
        model.addAttribute("INDICATOR_PARAM",
            JsonUtils.objectArray2Json(select.toArray()));
        // 返回明细结果
        service.setParaStr(paramList, select);
        RskIndExprDtlDto entity = new RskIndExprDtlDto();
        entity.setParamList(paramList);
        model.addAttribute(GlobalConstant.ENTITY, entity);
        return "invest/riskredis/quota/rsksupervisionriskanalyze/RskIndExprDtl";
    }
}
