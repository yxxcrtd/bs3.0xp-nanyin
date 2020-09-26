/*
 * 文件名：ValOrdersQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月17日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.squaremanagement.ordersquery.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.squaremanagement.ordersquery.dto.ValOrdersQuery001Dto;
import com.joyintech.tams.valuation.squaremanagement.ordersquery.service.ValOrdersQuery001Service;

/**
 * 
 * 结算指令查询
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValOrdersQuery001Contoller
 * @since 1.0
 */
@Controller
@PageId("ValOrdersQuery001")
@RequestMapping("/valuation/squaremanagement/ordersquery/ValOrdersQuery001")
public class ValOrdersQuery001Contoller extends BaseController {
    
    /**
     * ValOrdersQuery001Service
     */
    @Autowired
    private ValOrdersQuery001Service valOrdersQuery001Service;
    
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        //业务类别下拉
      // TODO
//        this.setSelectOptionToModel(model, DictCode.业务类型_结算类型); 
        //交易方向下拉
        this.setSelectOptionToModel(model, DictCode.交易方向);
        return super.init(model);
    }
    
    /**
     * 
     * 查询结算指令分页信息
     * @return 分页信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValOrdersQuery001Dto> findPage() throws Exception{
        //结算日期开始时间
        String settleDateStart = this.getParam("settleDateStart");
        //结算日期结束时间
        String settleDateEnd = this.getParam("settleDateEnd");
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        if(StringUtil.isNotEmpty(settleDateStart)){
            paraMap.put("settleDateStart", DateUtil.parse(settleDateStart));
        }
        if(StringUtil.isNotEmpty(settleDateEnd)){
            paraMap.put("settleDateEnd", DateUtil.parse(settleDateEnd));
        }
        return valOrdersQuery001Service.findPage(paraMap);
    }
}
