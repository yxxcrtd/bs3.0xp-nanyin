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
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneFactorTypeDto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.service.MarketRiskSceneManage002Service;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.dto.RiskFactor003Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.service.RiskFactor001Service;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.service.RiskFactor003Service01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author chenyunlong
 * @version 1.0 2019年8月24日
 * @see RiskFactor003Controller
 * @since 1.0
 * 历史页面展示
 */
@Controller
@PageId("RiskFactor003")
@RequestMapping("/invest/risk/stresstestmanage/riskfactor/RiskFactor003")
public class RiskFactor003Controller extends BaseController {

    @Autowired(required = false)
    private RiskFactor001Service service;

    @Autowired(required = false)
    private RiskFactor003Service01 RiskFactor003Service01;

    /**
     * marketRiskSceneManageService,用于查询因子二级分类
     */
    @Autowired
    private MarketRiskSceneManage002Service marketRiskSceneManage002Service;

    /**
     *
     * 历史数据初始化查询条件
     * @author
     * @version
     * @see
     * @since 1.0
     */
    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
        this.setSelectOptionToModel(model, DictCode.风险因子管理_因子值结构);
        this.setSelectOptionToModel(model, DictCode.风险因子管理_启用状态);
        this.setSelectOptionToModel(model, DictCode.风险因子管理_风险分类);
        model.addAllAttributes(RequestUtil.getPageParam());
        model.addAttribute("entity1", RequestUtil.getPageParam());
        List<MarketRiskSceneFactorTypeDto> ftrTypeList = marketRiskSceneManage002Service.queryFactorTypeList("");
        model.addAttribute("ftrType2List", ftrTypeList);
        return super.init(model);
    }
    /**
     *
     * 历史数据查询方法
     * @author
     * @version
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findOnePage")
    public Page<RiskFactor003Dto> selectForPage()
        throws Exception {
       Map<String, Object> map = RequestUtil.getParameters(getRequest());
       if(!map.get("twoClass").equals("")&&map.get("twoClass")!=null){
           map.put("investtype1", null);
           map.put("investtype2", null);
           map.put("FTRVALUESTRUCT", null);
           map.put("twoClass", map.get("twoClass"));
           map.put("FTRSTATUS", null);
       }
          Page<RiskFactor003Dto> page = RiskFactor003Service01.selectAll(map);
        return page;
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return RiskFactor003Service01.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));

    }
   
}