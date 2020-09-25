/*
 * 文件名：RiskFactor001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：因子查询页面
 * 修改人：chenyunlong
 * 修改时间：2019年8月24日
 * 系统名称：AMAS
 */

package com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskFactroRManageDto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneFactorTypeDto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.service.MarketRiskSceneManage002Service;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.dto.RiskFactor001Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.service.RiskFactor001Service;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.service.RiskFactor002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author chenyunlong
 * @version 1.0 2019年8月24日
 * @see RiskFactor001Controller
 * @since 1.0
 */
@Controller
@PageId("RiskFactor001")
@RequestMapping("/invest/risk/stresstestmanage/riskfactor/RiskFactor001")
public class RiskFactor001Controller extends BaseController {

    @Autowired(required = false)
    private RiskFactor001Service service;

    @Autowired(required = false)
    private RiskFactor002Service RiskFactor002Service;

    /**
     * marketRiskSceneManageService,用于查询因子二级分类
     */
    @Autowired
    private MarketRiskSceneManage002Service marketRiskSceneManage002Service;

    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
        this.setSelectOptionToModel(model, DictCode.风险因子管理_因子值结构);
        this.setSelectOptionToModel(model, DictCode.因子二级分类);
        this.setSelectOptionToModel(model, DictCode.因子风险分类);
        this.setSelectOptionToModel(model, DictCode.风险因子管理_启用状态);
        List<MarketRiskSceneFactorTypeDto> ftrTypeList = marketRiskSceneManage002Service.queryFactorTypeList("");
        model.addAttribute("ftrType2List", ftrTypeList);
        return super.init(model);
    }

    /*
     * 查询方法
     * */
    @ResponseBody
    @RequestMapping("findOnePage")
    public Page<RiskFactor001Dto> selectForPage()
        throws Exception {
        Map<String, Object> map = RequestUtil.getParameters(getRequest());
        return service.selectAll(map);
    }

    /*
     * 查询因子下拉框
     * */
    @ResponseBody
    @RequestMapping("selectRiskFactorMap")
    public List<SelectOptionDto> selectRiskFactorMap(@RequestParam Map<String, Object> param)
        throws Exception {

        return service.selectRiskFactorMap(param);
    }
    /*
     * 查询因子下拉框
     * */
    @ResponseBody
    @RequestMapping("selectRiskFactorMap2")
    public List<SelectOptionDto> selectRiskFactorMap2(@RequestParam Map<String, Object> param)
            throws Exception {

        return service.selectRiskFactorMap2(param);
    }
    /*
     * 查询因子风险分类
     * */
    @ResponseBody
    @RequestMapping("selectRiskFactorType1")
    public List<SelectOptionDto> selectRiskFactorMapType1(@RequestParam Map<String, Object> param)
        throws Exception {

        return service.selectRiskFactorMapType1(param);

    }
    /*
     * 查询因子因子分类
     * */
    @ResponseBody
    @RequestMapping("selectRiskFactorType2")
    public List<SelectOptionDto> selectRiskFactorMapType2()
        throws Exception {
        Map<String, Object> map = RequestUtil.getParameters(getRequest());

        return service.selectRiskFactorMapType2(map);
    }

    /**
     * 启用停用check检查因子是否被情景使用
     * 
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody RiskFactor001Dto dto) {
        String msg = null;
        AjaxResult ajaxR = new AjaxResult();
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        List<RiskFactor001Dto> jtList = dto.getSelectedItem();
        //判断是所选因子全部已使用
        int i =  0;
        for (RiskFactor001Dto riskFactor001Dto : jtList) {
            Map<String, Object> map = RequestUtil.getParameters(getRequest());
            map.put("ftrid", riskFactor001Dto.getFtrid());
            List<FlowRiskFactroRManageDto> FlowRiskFactroRManageDto = service.check(map);
            if (FlowRiskFactroRManageDto.size() > 0) {
            	i++;
                msg = (msg==null?(riskFactor001Dto.getFtrid()):msg + " " + riskFactor001Dto.getFtrid());

            }
        }
        if (msg!=null && 1 == jtList.size()) {
        	msg = msg + ":已被情景使用，无法进行停启操作！ ";
        	errorList.add(new ValidMessage("", msg));
        }
        else if(msg != null && i == jtList.size()){
        	msg = msg + ":均被情景使用，无法进行停启操作！ ";
        	errorList.add(new ValidMessage("", msg));
        }
        else if(msg !=null && i != jtList.size()){
        	msg = msg + ":已被情景使用，无法进行停启操作！继续将跳过该因子！ ";
        }
        ajaxR.setMessage(msg);
        ajaxR.setErrorList(errorList);
        return ajaxR;
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
        return service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));

    }
    /**
     * 启停审批流程
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(OPERATE_TYPE_SUBMIT)
    public AjaxResult delete(@RequestBody @Validated RiskFactor001Dto dto, BindingResult br)
        throws Exception {

        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        String msg = "";
        if (dto.getElementId().equals("RiskFactor001_btn_y")) {
            // 查询系列名称
            ProcessParameter<RiskFactor001Dto> processParameter = new ProcessParameter<RiskFactor001Dto>();
            WorkFlowParameter<RiskFactor001Dto> params = new WorkFlowParameter<RiskFactor001Dto>();
            params.setPageId("RiskFactor002");
            params.setElementId(dto.getElementId());
            params.setBusinessCode(RiskFactor001Dto::getFtrid);
            params.setBusinessType(BussinessDictCodeList.因子停启_因子启动.getValue());
            params.setComment(entity -> {
                return WorkFlowUtil.concatComment("因子启停：", entity.getFtrid() + "正在进行启动操作！",
                    entity.getCreateUser());
            });

            // 设置流程业务说明, entity即传入的dto
            processParameter.setWorkFlowParameter(params);
            msg = msg + "  " + RiskFactor002Service.start(processParameter,
                dto.getSelectedItem().toArray(new RiskFactor001Dto[dto.getSelectedItem().size()]));

        }
        else {
            Iterator<RiskFactor001Dto> it = dto.getSelectedItem().iterator();
            while (it.hasNext()) {
                String Ftrid = it.next().getFtrid();
                Map<String, Object> map = RequestUtil.getParameters(getRequest());
                map.put("ftrid", Ftrid);
                List<FlowRiskFactroRManageDto> FlowRiskFactroRManageDto = service.check(map);
                if (FlowRiskFactroRManageDto.size() > 0) {
                    it.remove();
                }
            }

            ProcessParameter<RiskFactor001Dto> processParameter = new ProcessParameter<RiskFactor001Dto>();
            WorkFlowParameter<RiskFactor001Dto> params = new WorkFlowParameter<RiskFactor001Dto>();
            params.setPageId("RiskFactor002");
            params.setElementId(dto.getElementId());
            params.setBusinessCode(RiskFactor001Dto::getFtrid);
            params.setBusinessType(BussinessDictCodeList.因子停启_因子停用.getValue());
            params.setComment(entity -> {
                return WorkFlowUtil.concatComment("因子启停：", entity.getFtrid() + "正在进行停止操作！",
                    entity.getCreateUser());
            });
            // 设置流程业务说明, entity即传入的dto
            processParameter.setWorkFlowParameter(params);
            msg = msg + "  " + RiskFactor002Service.start(processParameter,
                dto.getSelectedItem().toArray(new RiskFactor001Dto[dto.getSelectedItem().size()]));

        }

        return new AjaxResult(msg);
    }
}