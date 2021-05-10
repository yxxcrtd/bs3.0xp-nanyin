/*
 * 文件名：ValPlannedassetquery002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.assets.plan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.assets.plan.dto.ValPlannedassetquery001Dto;
import com.joyintech.tams.valuation.assets.plan.service.ValPlannedassetquery002Service;

/**
 * 模板示例<br>
 * 计划类资产查询
 * 
 * @author zhangzhao
 * @version 2.0 2018年7月19日
 * @see ValPlannedassetquery002Controller
 * @since 2.0
 */
@Controller
@PageId("ValPlannedassetquery002")
@RequestMapping("/valuation/assets/plan/ValPlannedassetquery002")
public class ValPlannedassetquery002Controller extends BaseController {
    
    /**
     * ValPlannedassetquery002Service
     */
    @Autowired
    private ValPlannedassetquery002Service valPlannedassetquery002Service;

    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        
        String profitType=this.getParam("profitType");
        model.addAttribute("profitType", profitType);
        
        String finprodId=this.getParam("finprodId");

        this.setSelectOptionToModel(model, DictCode.境内外); 
        this.setSelectOptionToModel(model, DictCode.推算方式);
        this.setSelectOptionToModel(model, DictCode.营业日准则);
        this.setSelectOptionToModel(model, DictCode.募集方式);
        this.setSelectOptionToModel(model, DictCode.收益类型);
        this.setSelectOptionToModel(model, DictCode.本金收益特征);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.计息基础);
        this.setSelectOptionToModel(model, DictCode.频率);
        this.setSelectOptionToModel(model, DictCode.基金分红频率);
        this.setSelectOptionToModel(model, DictCode.计划类资产类型);
        this.setSelectOptionToModel(model, DictCode.申购确认速度);
        this.setSelectOptionToModel(model, DictCode.交易日历);
        /** modify by yanya on 20201222 画面增加是否数据字典**/
        this.setSelectOptionToModel(model, DictCode.是否);
      
      Map<String,Object> map=new HashMap<String,Object>();
      map.put("finprodId", finprodId);
      ValPlannedassetquery001Dto dto=valPlannedassetquery002Service.queryBondasetByfinprodId(map);
      ValPlannedassetquery001Dto dto1 =valPlannedassetquery002Service.queryIntInfo(map);
      model.addAttribute("entity", dto);
      model.addAttribute("entity1", dto1);
      return super.init(model);
  }
    
    /**
     * 详情页表单数据的获取-利率报价信息
     * 
     */
    @RequestMapping("planintrate")
    @ResponseBody
    public List<ValPlannedassetquery001Dto> queryintrate(){
        String finprodId = this.getParam("finprodId");
        Map<String, Object> map = new HashMap<>();
        map.put("finprodId", finprodId);
        return valPlannedassetquery002Service.queryintrate(map);
    }

    /**
     * 详情页表单数据的获取-付息计划列表
     * 
     */
    @RequestMapping("planintpay")
    @ResponseBody
    public List<ValPlannedassetquery001Dto> queryintpay(){
        String finprodId = this.getParam("finprodId");
        Map<String, Object> map = new HashMap<>();
        map.put("finprodId", finprodId);
        return valPlannedassetquery002Service.queryintpay(map);  
    }
    
    /**
     * 详情页表单数据的获取-还本计划列表
     * 
     */
    @RequestMapping("planrepay")
    @ResponseBody
    public List<ValPlannedassetquery001Dto> queryrepay(){
        String finprodId = this.getParam("finprodId");
        Map<String, Object> map = new HashMap<>();
        map.put("finprodId", finprodId);
        return valPlannedassetquery002Service.queryrepay(map);  
    }
    
    /**
     * 详情页表单数据的获取-分红计划信息
     * 
     */
    @RequestMapping("planbonus")
    @ResponseBody
    public List<ValPlannedassetquery001Dto> querybonus(){
        String finprodId = this.getParam("finprodId");
        Map<String, Object> map = new HashMap<>();
        map.put("finprodId", finprodId);
        return valPlannedassetquery002Service.querybonus(map);  
    }
    

}
