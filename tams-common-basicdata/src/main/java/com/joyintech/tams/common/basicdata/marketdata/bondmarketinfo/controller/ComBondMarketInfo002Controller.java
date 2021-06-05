/*
 * 文件名：MarketInformationController002.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.common.basicdata.marketdata.bondmarketinfo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.basicdata.marketdata.bondmarketinfo.dto.ComBondMarketInfo001Dto;
import com.joyintech.tams.common.basicdata.marketdata.bondmarketinfo.service.ComBondMarketInfo001Service;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 债券行情信息新建 进行债券估值行情信息的新建
 * 
 * @author wangzehui
 * @version 1.0 2018年7月18日
 * @see ValBondQuotationInfo002Controller
 * @since 1.0
 */
@Controller
@PageId("ComBondMarketInfo002")
@RequestMapping("/common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo002")
public class ComBondMarketInfo002Controller extends BaseController {
   /**
     * 定义serviec对象
     */
    /**
     *每日债券行情service
     */
    @Autowired
    private ComBondMarketInfo001Service comBondMarketInfo001Service;

    @Override
    public String init(Model model) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            ComBondMarketInfo001Dto dto = comBondMarketInfo001Service.getBussinessEntityByWorkflowId(
                    workflowId);
//            model.addAttribute("pageModel",dto.getPageModel());
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else if (WorkFlowUtil.isNotFromToDoList() &&
                StringUtil.isNotEmpty(pageParam.get("secId")) && StringUtil.isNotEmpty(pageParam.get("valueDate"))) {
            //查询数据
            HashMap<String, Object> map = new HashMap<>();
            map.put("secId",pageParam.get("secId"));
            map.put("valueDate",pageParam.get("valueDate"));
            ComBondMarketInfo001Dto dto = null;
            try {
                List<ComBondMarketInfo001Dto> listTem = comBondMarketInfo001Service.queryDtoByKey(map);
                dto = comBondMarketInfo001Service.queryDtoByKey(map).get(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        return super.init(model);
    }


    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody ComBondMarketInfo001Dto dto) {
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        if (!StringUtil.isNotEmpty(dto.getFinprodId())){
            errorList.add(new ValidMessage("资产代码为必填项！"));
        }
        if (!StringUtil.isNotEmpty(dto.getValueDate())){
            errorList.add(new ValidMessage("估值日期为必填项！"));
        }
        if (dto.getCloPrice() == null){
            errorList.add(new ValidMessage("收盘价净价为必填项！"));
        }
        if (dto.getCloPrice() == null){
            errorList.add(new ValidMessage("平均价净价为必填项！"));
        }
        if (dto.getBuyOnePrice() == null){
            errorList.add(new ValidMessage("买一价净价为必填项！"));
        }
        if (dto.getSellOnePrice() == null){
            errorList.add(new ValidMessage("卖一价净价为必填项！"));
        }
        if (dto.getMaxPrice() == null){
            errorList.add(new ValidMessage("最高价净价为必填项！"));
        }
        if (dto.getMinPrice() == null){
            errorList.add(new ValidMessage("最低价净价为必填项！"));
        }
        if (dto.getCloPriceFull() == null){
            errorList.add(new ValidMessage("收盘价全价为必填项！"));
        }
        if (dto.getAvgPriceFull() == null){
            errorList.add(new ValidMessage("平均价全价为必填项！"));
        }
        if (dto.getBuyOnePriceFull() == null){
            errorList.add(new ValidMessage("买一价全价为必填项！"));
        }
        if (dto.getSellOnePriceFull() == null){
            errorList.add(new ValidMessage("卖一价全价为必填项！"));
        }
        if (dto.getMaxPriceFull() == null){
            errorList.add(new ValidMessage("最高价全价为必填项！"));
        }
        if (dto.getMinPriceFull() == null){
            errorList.add(new ValidMessage("最低价全价为必填项！"));
        }
        if (errorList.size()>0)
        {
            ajaxResult.setErrorList(errorList);
            return ajaxResult;
        }

        Map<String, Object> map = new HashMap<>();
        /** modify by yanya on 20210329 去除截取前四位字符的逻辑**/
        //String secId = dto.getFinprodId().substring(4);
        String secId = dto.getFinprodId();
        map.put("secId",secId);
        map.put("valueDate",dto.getValueDate());
        List<ComBondMarketInfo001Dto> valBondMarketInfo001Dto = comBondMarketInfo001Service.queryDtoByKey(map);
        if (valBondMarketInfo001Dto.size()>0 && !StringUtil.equals(dto.getPageModel(),"1")){
            errorList.add(new ValidMessage("资产代码为："+dto.getFinprodId()+",并且估值日期为："+dto.getValueDate()+"的数据已存在！请重新选择！"));
        }
        ajaxResult.setErrorList(errorList);
        return ajaxResult;
    }


    /**
     *
     * @param dto
     * @param br
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody ComBondMarketInfo001Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ComBondMarketInfo001Dto> processParameter = new ProcessParameter<ComBondMarketInfo001Dto>();
        WorkFlowParameter<ComBondMarketInfo001Dto> params = new WorkFlowParameter<ComBondMarketInfo001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        if (StringUtil.isEmpty(dto.getSecId())) {
            dto.setSecId(dto.getFinprodId());      // 将secId设置为finprodId的截取
        }
        params.setBusinessCode(dto.getSecId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_每日债券行情信息.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity ->"债券代码(" + entity.getSecId() + ")" + "-" + "估值日期(" + entity.getValueDate()+ ")");
        processParameter.setWorkFlowParameter(params);

        // 流程开始
        String msg = comBondMarketInfo001Service.start(processParameter, dto);
        return new AjaxResult(msg);

    }

    /**
     * 查询资产代码
     */
    @ResponseBody
    @RequestMapping("getFinProductList")
    public Page<SelectOptionDto> getFinProductList() throws Exception {
        Map<String, Object> map = RequestUtil.getPageParam();
        map.put("finprodType","F01");
        return comBondMarketInfo001Service.getFinProductList(map);
    }

}

