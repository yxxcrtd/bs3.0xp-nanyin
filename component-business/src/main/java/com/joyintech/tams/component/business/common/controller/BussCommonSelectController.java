/*
 * 文件名：CommonSelectController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.component.business.common.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.common.BussCommonSelectService;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 下拉框业务共通组件
 * 
 * @author 杨智
 */
@Controller
@RequestMapping("/business/common/CommonSelect")
public class BussCommonSelectController extends BaseController {

    /**
     * 共通下拉框Service
     */
    @Autowired(required = false)
    private BussCommonSelectService bussCommonSelectService;

    @RequestMapping("get{selectTarget}")
    @ResponseBody
    public List<SuggestDto> getCommonSelect(@PathVariable("selectTarget") String selectTarget,
                                            @RequestParam Map<String, Object> param) {
        if (bussCommonSelectService == null) {
            return new ArrayList<>();
        }

        // 查询组合添加权限限制
        if (StringUtil.equals(selectTarget, "PortfolioId")) {
            param.put("loginUserName", LoginUserUtil.getUserName());
        }
        param.put("portType", DictCodeListExt1.组合类型_投资类组合.getValue());
        param.put("portType1", DictCodeListExt1.组合类型_子组合.getValue());
        return bussCommonSelectService.getCommonSelect(selectTarget, param);
    }

    @RequestMapping("select{selectTarget}")
    @ResponseBody
    public Page<SelectOptionDto> selectCommonSelect(@PathVariable("selectTarget") String selectTarget) {
        if (bussCommonSelectService == null) {
            return new Page<>();
        }
        Map<String, Object> param = RequestUtil.getPageParam();
        // 查询组合添加权限限制
        if (StringUtil.equals(selectTarget, "PortfolioId")
            && !StringUtil.isNotEmpty(param.get("initValue"))) {
            param.put("loginUserName", LoginUserUtil.getUserName());
        }
        param.put("portType01", DictCodeListExt1.组合类型_投资类组合.getValue());
        param.put("sysDate", DateUtil.getSysDate());
        return bussCommonSelectService.selectCommonSelectForPage(selectTarget, param);
    }

    @RequestMapping("select{selectTarget}ByRequestBody")
    @ResponseBody
    public List<SelectOptionDto> selectCommonSelectByRequestBody(@PathVariable("selectTarget") String selectTarget,
                                                                 @RequestBody Map<String, Object> param) {
        if (bussCommonSelectService == null) {
            return new ArrayList<>();
        }
        // 查询组合添加权限限制
        if (StringUtil.equals(selectTarget, "PortfolioId")
            && !StringUtil.isNotEmpty(param.get("initValue"))) {
            param.put("loginUserName", LoginUserUtil.getUserName());
        }
        param.put("portType", DictCodeListExt1.组合类型_投资类组合.getValue());
        param.put("portType1", DictCodeListExt1.组合类型_子组合.getValue());
        return bussCommonSelectService.selectCommonSelect(selectTarget, param);
    }

    /**
     * 共通下拉框查询，支持分页，查询参数通过url参数传递
     * 
     * @param selectTarget 目标服务
     * @param param url参数
     * @return 下拉框查询结果
     * @see
     * @since 1.0
     */
    @RequestMapping("qry{selectTarget}ByRequestParam")
    @ResponseBody
    public Page<SelectOptionDto> qryCommonSelectByRequestParam(@PathVariable("selectTarget") String selectTarget,
                                                               @RequestParam Map<String, Object> param) {
        if (bussCommonSelectService == null) {
            return new Page<SelectOptionDto>();
        }

        // 查询组合添加权限限制
        if (StringUtil.equals(selectTarget, "PortfolioId")) {
            param.put("loginUserName", LoginUserUtil.getUserName());
        }
        param.put("portType", DictCodeListExt1.组合类型_投资类组合.getValue());
        param.put("portType1", DictCodeListExt1.组合类型_子组合.getValue());
        return bussCommonSelectService.qryCommonSelect(selectTarget, param);
    }

    /**
     * 共通下拉框查询，支持分页，查询参数通过post报文体传递
     * 
     * @param selectTarget 目标服务
     * @param param url参数
     * @return 下拉框查询结果
     * @see
     * @since 1.0
     */
    @RequestMapping("qry{selectTarget}ByRequestBody")
    @ResponseBody
    public Page<SelectOptionDto> qryCommonSelectByRequestBody(@PathVariable("selectTarget") String selectTarget,
                                                              @RequestBody Map<String, Object> param) {
        if (bussCommonSelectService == null) {
            return new Page<SelectOptionDto>();
        }

        // 查询组合添加权限限制
        if (StringUtil.equals(selectTarget, "PortfolioId")) {
            param.put("loginUserName", LoginUserUtil.getUserName());
        }
        param.put("portType", DictCodeListExt1.组合类型_投资类组合.getValue());
        param.put("portType1", DictCodeListExt1.组合类型_子组合.getValue());
        return bussCommonSelectService.qryCommonSelect(selectTarget, param);
    }

    /**
     * 共通下拉框查询，支持分页
     * 
     * @param selectTarget 目标服务
     * @return 下拉框查询结果
     * @see
     * @since 1.0
     */
    @RequestMapping("qry{selectTarget}")
    @ResponseBody
    public Page<SelectOptionDto> qryCommonSelect(@PathVariable("selectTarget") String selectTarget) {
        if (bussCommonSelectService == null) {
            return new Page<SelectOptionDto>();
        }
        Map<String, Object> param = RequestUtil.getPageParam();
        // 查询组合添加权限限制
        if (StringUtil.equals(selectTarget, "PortfolioId")
            && !StringUtil.isNotEmpty(param.get("initValue"))) {
            param.put("loginUserName", LoginUserUtil.getUserName());
        }
        param.put("portType", DictCodeListExt1.组合类型_投资类组合.getValue());
        param.put("portType1", DictCodeListExt1.组合类型_子组合.getValue());
        return bussCommonSelectService.qryCommonSelect(selectTarget, param);
    }

}
