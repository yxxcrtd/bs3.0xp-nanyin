package com.joyintech.tams.common.basicdata.marketdata.fundmarketinfo.controller;

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
import com.joyintech.tams.common.basicdata.marketdata.fundmarketinfo.dto.ComFundMarketInfo001dto;
import com.joyintech.tams.common.basicdata.marketdata.fundmarketinfo.service.ComFundMarketInfo001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * add by zwq 2021-01-30
 * 每日基金行情新建和编辑
 */
@Controller
@PageId("ComFundMarketInfo001")
@RequestMapping("/common/basicdata/marketdata/fundmarketinfo/ComFundMarketInfo002")
public class ComFundMarketInfo002Controller extends BaseController {
    @Autowired
    private ComFundMarketInfo001Service comFundMarketInfo001Service;

    /**
     * 重写init方法 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.基金类型);
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            ComFundMarketInfo001dto dto = comFundMarketInfo001Service.getBussinessEntityByWorkflowId(
                    workflowId);
//            model.addAttribute("pageModel",dto.getPageModel());
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else if (WorkFlowUtil.isNotFromToDoList() &&
                StringUtil.isNotEmpty(pageParam.get("fundCode")) && StringUtil.isNotEmpty(pageParam.get("valueDate"))) {
            //查询数据
            HashMap<String, Object> map = new HashMap<>();
            map.put("fundCode",pageParam.get("fundCode"));
            map.put("valueDate",pageParam.get("valueDate"));
            ComFundMarketInfo001dto dto = null;
            try {
                dto = comFundMarketInfo001Service.queryDtoByKey(map).get(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody ComFundMarketInfo001dto dto) {
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        if (!StringUtil.isNotEmpty(dto.getFinprodId())){
            errorList.add(new ValidMessage("基金代码为必填项！"));
        }
        if (!StringUtil.isNotEmpty(dto.getFundName())){
            errorList.add(new ValidMessage("基金名称为必填项！"));
        }
        if (!StringUtil.isNotEmpty(dto.getFundType())){
            errorList.add(new ValidMessage("基金类型为必填项！"));
        }
        if (!StringUtil.isNotEmpty(dto.getValueDate())){
            errorList.add(new ValidMessage("估值日期为必填项！"));
        }
        if (dto.getClosePrice() == null){
            errorList.add(new ValidMessage("收盘价为必填项！"));
        }
        if (dto.getAveragePrice() == null) {
            errorList.add(new ValidMessage("平均价为必填项！"));
        }
        if (dto.getMaxPrice() == null) {
            errorList.add(new ValidMessage("最高价为必填项！"));
        }
        if (dto.getMinPrice() == null) {
            errorList.add(new ValidMessage("最低价为必填项！"));
        }
        if (dto.getBuyOnePrice() == null) {
            errorList.add(new ValidMessage("买一价为必填项！"));
        }
        if (dto.getSellOnePrice() == null) {
            errorList.add(new ValidMessage("卖一价为必填项！"));
        }
        if (errorList.size()>0)
        {
            ajaxResult.setErrorList(errorList);
            return ajaxResult;
        }

        Map<String, Object> map = new HashMap<>();
        /** modify by yanya on 20210329 去除截取前四位字符的逻辑**/
        //String fundCode = dto.getFinprodId().substring(4);
        String fundCode = dto.getFinprodId();
        map.put("fundCode",fundCode);
        map.put("valueDate",dto.getValueDate());
        List<ComFundMarketInfo001dto> valFundMarketInfo001dto = comFundMarketInfo001Service.queryDtoByKey(map);
        if (valFundMarketInfo001dto.size()>0 && !StringUtil.equals(dto.getPageModel(),"1")){
            errorList.add(new ValidMessage("基金代码为："+dto.getFinprodId()+",并且估值日期为："+dto.getValueDate()+"的数据已存在！请重新选择！"));
        }
        ajaxResult.setErrorList(errorList);
        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody ComFundMarketInfo001dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ComFundMarketInfo001dto> processParameter = new ProcessParameter<ComFundMarketInfo001dto>();
        WorkFlowParameter<ComFundMarketInfo001dto> params = new WorkFlowParameter<ComFundMarketInfo001dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
//        if (StringUtil.isEmpty(dto.getSecId())) {
//            dto.setSecId(dto.getFinprodId().substring(4));      // 将secId设置为finprodId的截取
//        }
        params.setBusinessCode(dto.getFinprodId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_每日基金行情信息.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity ->"基金代码(" + entity.getFinprodId() + ")" + "-" + "估值日期(" + entity.getValueDate()+ ")"
                + "-" + "基金类型(" + entity.getFundTypeFmt() + ")");
        processParameter.setWorkFlowParameter(params);

        // 流程开始
        String msg = comFundMarketInfo001Service.start(processParameter, dto);
        return new AjaxResult(msg);

    }

    /**
     * 查询基金代码
     */
    @ResponseBody
    @RequestMapping("getFinProductList")
    public Page<SelectOptionDto> getFinProductList() throws Exception {
        Map<String, Object> map = RequestUtil.getPageParam();
        map.put("finprodType","F06");
        return comFundMarketInfo001Service.getFinProductList(map);
    }
    /**
     *
     * 资产信息检索
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getAssetInfo")
    public ComFundMarketInfo001dto getAssetInfo(@RequestBody Map<String, Object> param){
        param.put("finprodType","F06");
        return comFundMarketInfo001Service.getAssetInfo(param);
    }
}
