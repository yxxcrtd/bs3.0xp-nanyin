/*
 * 文件名：FlowRiskSceneManage001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.common.ValidateGroupEffective;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManage001Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManageListDto;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.service.FlowRiskSceneManage001Service;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.service.FlowRiskSceneManage002Service;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.service.FlowRiskSceneManage003Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 流动性风险-情景管理
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年8月13日
 * @see FlowRiskSceneManage001Controller
 * @since 1.0
 */
@Controller
@PageId("FlowRiskSceneManage001")
@RequestMapping("/invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage001")
public class FlowRiskSceneManage001Controller extends BaseController {

    /**
     * flowRiskSceneManageService
     */
    @Autowired
    private FlowRiskSceneManage001Service flowRiskSceneManage001Service;
    
    @Autowired
    private FlowRiskSceneManage002Service flowRiskSceneManage002Service;
    
    @Autowired
    private FlowRiskSceneManage003Service  FlowRiskSceneManage003Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DictCode.情景类型);
        setSelectOptionToModel(model, DictCode.情景二级类型);
        setSelectOptionToModel(model, DictCode.情景启用状态);
        setSelectOptionToModel(model, DictCode.开放模式); // 适用产品
        
        List<SelectOptionDto> options = new ArrayList<>();
        List<FlowRiskSceneManageListDto> list= flowRiskSceneManage002Service.queryFlowSttFactor();
        for(FlowRiskSceneManageListDto sceMaDto:list){
            SelectOptionDto selDto1 = new SelectOptionDto();
            selDto1.setId(sceMaDto.getFtrId());
            selDto1.setText(sceMaDto.getFtrName());
            options.add(selDto1);
        }
        model.addAttribute(DictCode.流动性风险因子.getValue(), JsonUtils.objectArray2Json(options.toArray()));

        return super.init(model);
    }

    /** 情景分页查询 */
    @ResponseBody
    @RequestMapping("/findPage")
    public Page<FlowRiskSceneManage001Dto> selectForPage() throws Exception {
        Map<String, Object> map = RequestUtil.getParameters(getRequest());
        
        Set<String> idSet = new HashSet<>();
        if (map.get("sceneIds") != null) {
            idSet.addAll(Arrays.asList(map.get("sceneIds").toString().split(",")));
        }
        if (map.get("sceneNames") != null) {
            // 前台传上来的也是情景ID
            idSet.addAll(Arrays.asList(map.get("sceneNames").toString().split(",")));
            map.remove("sceneNames");
        }
        map.put("sceneIds", idSet.stream().filter(p -> StringUtils.isNotBlank(p)).collect(Collectors.toList()));
        
        if (map.get("ftrIds") != null) {
            map.put("ftrIds", Arrays.asList(map.get("ftrIds").toString().split(",")).stream().filter(p -> StringUtils.isNotBlank(p)).collect(Collectors.toList()));
        }

        return flowRiskSceneManage001Service.selectForPage(map);
    }

    /** 情景批量或单个启用停用 */
    @ResponseBody
    @RequestMapping("/updateScenesEffStatus")
    public AjaxResult updateScenesEffStatus(@RequestBody @Validated({ValidateGroupEffective.class}) 
        FlowRiskSceneManage001Dto dto, BindingResult br)  throws Exception {
        AjaxResult checkResult = commonCheck(br, dto.getOperateType());

     /*   if (!br.hasErrors()) {
            Integer rows = flowRiskSceneManage001Service.updateScenesEffStatus(dto);
            checkResult.setMessage(BussinessDictCodeList.情景启用状态_启用.getValue().equals(dto.getEffStatus()) ? 
                getMessage("RSK_W_0065", rows) : getMessage("RSK_W_0066", rows));
            checkResult.setData(JSON.parse("{\"updatedRows\": " + rows + "}"));
            checkResult.setStatuCode(200);
        }*/

        if (br.hasErrors()) {
            return checkResult;
        }
        String msg = "";
        if (dto.getElementId().equals("FlowRiskSceneManage001_btn_effective")) {
        	dto.getSelectedItem().stream().forEach((item)->item.setEffStatus(DictCodeListRsk.情景启用状态_启用.getValue()));
            // 查询系列名称
            ProcessParameter<FlowRiskSceneManage001Dto> processParameter = new ProcessParameter<FlowRiskSceneManage001Dto>();
            WorkFlowParameter<FlowRiskSceneManage001Dto> params = new WorkFlowParameter<FlowRiskSceneManage001Dto>();
            params.setPageId("FlowRiskSceneManage002");
            params.setElementId(dto.getElementId());
            params.setBusinessCode(FlowRiskSceneManage001Dto::getSceneId);
            params.setBusinessType(BussinessDictCodeList.情景启用状态_启用.getValue());
            params.setComment(entity -> {
                return WorkFlowUtil.concatComment("情景启用状态_启用：", entity.getSceneId() + "正在进行启动操作！",
                    entity.getCreateUser());
            });

            // 设置流程业务说明, entity即传入的dto
            processParameter.setWorkFlowParameter(params);
            msg = msg + "  " + FlowRiskSceneManage003Service.start(processParameter,
                dto.getSelectedItem().toArray(new FlowRiskSceneManage001Dto[dto.getSelectedItem().size()]));
        }
        else {
        	dto.getSelectedItem().stream().forEach((item)->item.setEffStatus(DictCodeListRsk.情景启用状态_停用.getValue()));
            ProcessParameter<FlowRiskSceneManage001Dto> processParameter = new ProcessParameter<FlowRiskSceneManage001Dto>();
            WorkFlowParameter<FlowRiskSceneManage001Dto> params = new WorkFlowParameter<FlowRiskSceneManage001Dto>();
            params.setPageId("FlowRiskSceneManage002");
            params.setElementId(dto.getElementId());
            params.setBusinessCode(FlowRiskSceneManage001Dto::getSceneId);
            params.setBusinessType(BussinessDictCodeList.情景启用状态_停用.getValue());
            params.setComment(entity -> {
                return WorkFlowUtil.concatComment("情景启用状态_停用：", entity.getSceneId() + "正在进行停止操作！",
                    entity.getCreateUser());
            });
            // 设置流程业务说明, entity即传入的dto
            processParameter.setWorkFlowParameter(params);
            msg = msg + "  " + FlowRiskSceneManage003Service.start(processParameter,
                dto.getSelectedItem().toArray(new FlowRiskSceneManage001Dto[dto.getSelectedItem().size()]));
        }

        return new AjaxResult(msg);
    }

    /**
     * 删除check
     * 
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/checkDelete")
    public AjaxResult checkDelete(@RequestBody @Validated({ValidateGroupDelete.class}) 
        FlowRiskSceneManage001Dto dto, BindingResult result) {
        AjaxResult checkResult = commonCheck(result, dto.getOperateType());
        return checkResult;
    }
    
    /**
     * 启停check
     * 
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/checkEffective")
    public AjaxResult checkEffective(@RequestBody @Validated({ValidateGroupEffective.class}) 
        FlowRiskSceneManage001Dto dto, BindingResult result) {
        AjaxResult checkResult = commonCheck(result, dto.getOperateType());
        return checkResult;
    }

    /**
     * 删除-审批流程
     * 
     * @return AjaxResult
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) 
        FlowRiskSceneManage001Dto dto, BindingResult br) throws Exception {
        AjaxResult checkResult = commonCheck(br, dto.getOperateType());
        if (br.hasErrors()) {
            //return new AjaxResult(br);
            return checkResult;
        }

        ProcessParameter<FlowRiskSceneManage001Dto> processParameter = new ProcessParameter<FlowRiskSceneManage001Dto>();
        WorkFlowParameter<FlowRiskSceneManage001Dto> params = new WorkFlowParameter<FlowRiskSceneManage001Dto>();
        params.setPageId(dto.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(FlowRiskSceneManage001Dto::getSceneId);
        params.setBusinessType(BussinessDictCodeList.情景管理_删除情景.getValue());
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment("情景删除：[scene_id=", entity.getSceneId() + "]正在进行删除操作！", entity.getCreateUser());
        });
        processParameter.setWorkFlowParameter(params);
        processParameter.setPageId(params.getPageId());
        processParameter.setOperationType(OperationType.DELETE);
//        HttpServletRequest request = getRequest();
//        request.setAttribute(WflConstants.OPER_TYPE, OperationType.DELETE.name());

        String msg = flowRiskSceneManage001Service.start(processParameter, 
            dto.getSelectedItem().toArray(new FlowRiskSceneManage001Dto[dto.getSelectedItem().size()]));

        return new AjaxResult(msg);
    }

    /** 
     * 情景自动匹配完成-每次都是从oracle中查询
     */
    @ResponseBody
    @GetMapping("fuzzyNameSelectScene")
    @Deprecated
    public List<SelectOptionDto> fuzzyNameSelectScene() throws Exception {
        //Map<String, Object> map = RequestUtil.getParameters(getRequest());
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> pageMap = RequestUtil.getPageParam();
        Object search = pageMap.get("search");
        if (search != null) {
            paramMap.put("sceneName", StringUtils.trim(search.toString()));
        }

        return flowRiskSceneManage001Service.fuzzySelectScene(paramMap);
    }
    
    @ResponseBody
    @GetMapping("fuzzyCodeSelectScene")
    @Deprecated
    public List<SelectOptionDto> fuzzyCodeSelectScene() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> pageMap = RequestUtil.getPageParam();
        Object search = pageMap.get("search");
        if (search != null) {
            paramMap.put("sceneId", StringUtils.trim(search.toString()));
        }

        return flowRiskSceneManage001Service.fuzzySelectScene(paramMap);
    }
    
    @ResponseBody
    @GetMapping("fuzzySelectScene")
    public List<SelectOptionDto> fuzzySelectScene() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> pageMap = RequestUtil.getPageParam();
        Object search = pageMap.get("search");
        if (search != null) {
            paramMap.put("scenes", StringUtils.trim(search.toString()));
        }

        return flowRiskSceneManage001Service.fuzzySelectScene(paramMap);
    }

    /** 
     * 情景自动匹配完成-JAVA中筛选
     */
    @ResponseBody
    @RequestMapping("/getSceneSuggestList")
    public List<SuggestDto> getSceneSuggestList( ) {
        // 可能收不到数据
        //@RequestParam("fuzzyText") String fuzzyText
        Map<String, Object> map = RequestUtil.getParameters(getRequest());
        return flowRiskSceneManage001Service.getSceneSuggestList(map);
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
    	Map<String, Object> map = RequestUtil.getParameters(getRequest());
        
        Set<String> idSet = new HashSet<>();
        if (map.get("sceneIds") != null) {
            idSet.addAll(Arrays.asList(map.get("sceneIds").toString().split(",")));
        }
        if (map.get("sceneNames") != null) {
            // 前台传上来的也是情景ID
            idSet.addAll(Arrays.asList(map.get("sceneNames").toString().split(",")));
            map.remove("sceneNames");
        }
        map.put("sceneIds", idSet.stream().filter(p -> StringUtils.isNotBlank(p)).collect(Collectors.toList()));
        
        if (map.get("ftrIds") != null) {
            map.put("ftrIds", Arrays.asList(map.get("ftrIds").toString().split(",")).stream().filter(p -> StringUtils.isNotBlank(p)).collect(Collectors.toList()));
        }
        return flowRiskSceneManage001Service.export(this.getPageId(),
                map);

    }
}
