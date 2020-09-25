/*
 * 文件名：YieldCurveInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhao
 * 修改时间：2018年7月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.yieldcurveinfo.controller;

import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.basicdata.yieldcurveinfo.dto.MstYieldCurveInfoDto;
import com.joyintech.tams.invest.basicdata.yieldcurveinfo.service.MstYieldCurveInfoService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

import java.util.List;
/**
 * 收益率曲线信息
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhao
 * @version 1.0 2018年8月9日
 * @see MstYieldCurveInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("MstYieldCurveInfo001")
@RequestMapping("/invest/basicdata/yieldcurveinfo/MstYieldCurveInfo001")
public class MstYieldCurveInfo001Controller extends BaseController{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private MstYieldCurveInfoService mstYieldCurveInfoService;
    
    @Override
    public String init(Model model) {
        //添加曲线利率类型数据字典 add by lishuping 20191008 start
        this.setSelectOptionToModel(model, DictCode.收益率曲线类型,DictCode.收益率曲线,DictCode.曲线利率类型);
        //添加曲线利率类型数据字典 add by lishuping 20191008 end
        return super.init(model);
    }
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @return Page<MstYieldCurveInfoDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstYieldCurveInfoDto> selectAll()
        throws Exception {
        Page<MstYieldCurveInfoDto> page = mstYieldCurveInfoService.selectForPage(RequestUtil.getParameters(getRequest()));
        return page;
    }
    
    
    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody  @Validated({ValidateGroupDelete.class}) MstYieldCurveInfoDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 
     * 删除操作
     * <功能详细描述>
     * @param dto MstYieldCurveInfoDto
     * @param br br
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody  @Validated({ValidateGroupDelete.class})  MstYieldCurveInfoDto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<MstYieldCurveInfoDto> processParameter = new ProcessParameter<MstYieldCurveInfoDto>();
        WorkFlowParameter<MstYieldCurveInfoDto> params = new WorkFlowParameter<MstYieldCurveInfoDto>();
        params.setPageId("MstYieldCurveInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item->item.getCurveCode());
        params.setBusinessType(DictCodeList.共通业务类型_收益率曲线信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity ->"曲线名称"+"("+ SysDataUtil.getNameFromDataDict(DictCode.收益率曲线,
                entity.getCurveCode()) +")"+"-"+"曲线日期" + "(" + DateUtil.format(entity.getCurveDate()) + ")"
                +"-"+"区线类型"+"("+SysDataUtil.getNameFromDataDict(DictCode.收益率曲线类型,
                    entity.getCurveType())+")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstYieldCurveInfoService.start(processParameter, 
            dto.getSelectedItem().toArray(new MstYieldCurveInfoDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    
    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception{
        return mstYieldCurveInfoService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }

    //添加曲线名称的联想下拉框事件 add by lishuping 20191008 start
    /**
     * 曲线名称的联想下拉框事件
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getCurveName")
    public Page<SelectOptionDto> getCurveName(){
        return mstYieldCurveInfoService.getCurveName(RequestUtil.getPageParam());
    }
    //添加曲线名称的联想下拉框事件 add by lishuping 20191008 end
}
