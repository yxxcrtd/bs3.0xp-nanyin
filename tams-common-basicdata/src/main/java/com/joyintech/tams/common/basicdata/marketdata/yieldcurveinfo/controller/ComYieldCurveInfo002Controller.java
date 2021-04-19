/*
 * 文件名：CurrencyFundInfo002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhao
 * 修改时间：2018年7月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.common.basicdata.marketdata.yieldcurveinfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.basicdata.marketdata.yieldcurveinfo.dto.ComYieldCurveInfoDto;
import com.joyintech.tams.common.basicdata.marketdata.yieldcurveinfo.service.ComYieldCurveInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 收益率曲线信息
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhao
 * @version 1.0 2018年8月9日
 * @see ComYieldCurveInfo002Controller
 * @since 1.0
 */
@Controller
@PageId("ComYieldCurveInfo002")
@RequestMapping("/common/basicdata/marketdata/yieldcurveinfo/ComYieldCurveInfo002")
public class ComYieldCurveInfo002Controller extends BaseController{
    /**
     * 
     */
    @Autowired
    private ComYieldCurveInfoService comYieldCurveInfoService;
    
    
    @Override
    public String init(Model model) {
        //添加曲线利率类型数据字典 add by lishuping 20191008 start
        this.setSelectOptionToModel(model, DictCode.收益率曲线类型,DictCode.收益率曲线,DictCode.曲线利率类型);
        //添加曲线利率类型数据字典 add by lishuping 20191008 end
        String curveCode = getParam("curveCode");
        String curveDate = getParam("curveDate");
        String curveType = getParam("curveType");
        String workflowId = getParam("workflowId");
        //修改时
        if (StringUtil.isNotEmpty(curveCode)) {
            ComYieldCurveInfoDto comYieldCurveInfoDto =null;
            Map<String,Object> pageParam=new HashMap<>();
            pageParam.put("curveCode", curveCode);
            pageParam.put("curveDate", curveDate);
            pageParam.put("curveType", curveType);
            List<ComYieldCurveInfoDto> selectAllList = comYieldCurveInfoService.selectAllList(pageParam);
            if(selectAllList!=null && selectAllList.size()>0){
                comYieldCurveInfoDto = selectAllList.get(0);
            }
            model.addAttribute("entity", comYieldCurveInfoDto);
        }
        else if(StringUtil.isNotEmpty(workflowId)){
            ComYieldCurveInfoDto comYieldCurveInfoDto = comYieldCurveInfoService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", comYieldCurveInfoDto);
        }
        return super.init(model);
    }

        
    /**
     * 
     * 保存时，check操作
     * <功能详细描述>
     * @param dto MstYieldCurveInfoDto
     * @param result result
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) ComYieldCurveInfoDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto MstYieldCurveInfoDto
     * @param br br
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) ComYieldCurveInfoDto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        WorkFlowParameter<ComYieldCurveInfoDto> params = new WorkFlowParameter<ComYieldCurveInfoDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        if(StringUtil.isEmpty(dto.getCurveCode())){
            dto.setCurveCode(IDGenerator.generate());
        }            
        params.setBusinessCode(dto.getCurveCode());
        params.setBusinessType(DictCodeList.共通业务类型_收益率曲线信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            //修改流程中的曲线名称和曲线类型 modified by lishuping 20191008 start
            entity ->"曲线名称"+"("+ String.format(entity.getCurveCode()) + ")"
                    +"-"+"曲线日期" + "(" + DateUtil.format(entity.getCurveDate()) + ")"
                +"-"+"区线类型"+"("+SysDataUtil.getNameFromDataDict(DictCode.曲线利率类型,
                    entity.getCurveType())+")");
        //修改流程中的曲线名称和曲线类型 modified by lishuping 20191008 end
        //给参数变成ProcessParameter而非WorkFlowParameter
        ProcessParameter<ComYieldCurveInfoDto> parameter = new ProcessParameter<ComYieldCurveInfoDto>();
        parameter.setWorkFlowParameter(params);
        //系统页面表IS_NEED_WORKFLOW 是否走流程，01-是，02-否
        String msg = comYieldCurveInfoService.start(parameter, dto);
        return new AjaxResult(msg);
    }

    //add by lishuping 20191008 start
    /**
     * 根据曲线名称查询n、k
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @RequestMapping("selectNK")
    @ResponseBody
    public ComYieldCurveInfoDto selectNK(@RequestBody ComYieldCurveInfoDto comYieldCurveInfoDto) {
        return comYieldCurveInfoService.selectNK(comYieldCurveInfoDto);
    }
    //add by lishuping 20191008 end
}
