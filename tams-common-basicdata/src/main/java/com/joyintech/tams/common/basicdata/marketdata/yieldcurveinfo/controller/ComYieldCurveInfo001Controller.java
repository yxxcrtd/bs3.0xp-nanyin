/*
 * 文件名：YieldCurveInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhao
 * 修改时间：2018年7月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.common.basicdata.marketdata.yieldcurveinfo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.common.basicdata.marketdata.yieldcurveinfo.dto.ComYieldCurveInfoDto;
import com.joyintech.tams.common.basicdata.marketdata.yieldcurveinfo.service.ComYieldCurveInfoService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 收益率曲线信息
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhao
 * @version 1.0 2018年8月9日
 * @see ComYieldCurveInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("ComYieldCurveInfo001")
@RequestMapping("/common/basicdata/marketdata/yieldcurveinfo/ComYieldCurveInfo001")
public class ComYieldCurveInfo001Controller extends BaseController{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private ComYieldCurveInfoService comYieldCurveInfoService;
    
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
    public Page<ComYieldCurveInfoDto> selectAll()
        throws Exception {
        Page<ComYieldCurveInfoDto> page = comYieldCurveInfoService.selectForPage(RequestUtil.getParameters(getRequest()));
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
    public AjaxResult check(@RequestBody  @Validated({ValidateGroupDelete.class}) ComYieldCurveInfoDto dto, BindingResult result) {
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
    public AjaxResult delete(@RequestBody  @Validated({ValidateGroupDelete.class}) ComYieldCurveInfoDto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<ComYieldCurveInfoDto> processParameter = new ProcessParameter<ComYieldCurveInfoDto>();
        WorkFlowParameter<ComYieldCurveInfoDto> params = new WorkFlowParameter<ComYieldCurveInfoDto>();
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
        String msg = comYieldCurveInfoService.start(processParameter,
            dto.getSelectedItem().toArray(new ComYieldCurveInfoDto[dto.getSelectedItem().size()]));
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

        String datas = this.getParam("datas");
        List<ComYieldCurveInfoDto> listdto ;

        if (datas == null) {
            listdto = comYieldCurveInfoService.selectAllList(RequestUtil.getPageParam());
        }
        else {
            listdto = JsonUtils.json2List(datas, ComYieldCurveInfoDto.class);
        }

        for (int i=0;i<listdto.size();i++){
            ComYieldCurveInfoDto valYieldCurveInfoDto = listdto.get(i);
            valYieldCurveInfoDto.setCurveDateFormatStr( DateUtil.format(valYieldCurveInfoDto.getCurveDate(),"yyyy-MM-dd"));
        }
        File outFile = comYieldCurveInfoService.makeFile(
                "/com/joyintech/tams/valuation/basedata/marketdata/valyieldcurveinfo/conf/export_conf.xml",
                listdto);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date())
                        + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT
                        + FileNameUtil.getFileExtName(outFile),
                outFile, true);
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
        return comYieldCurveInfoService.getCurveName(RequestUtil.getPageParam());
    }
    //添加曲线名称的联想下拉框事件 add by lishuping 20191008 end
    
    
    /**
     * 导入（必填项校验）
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importData(String fileName)
            throws Exception{
        return this.comYieldCurveInfoService.importData(fileName);
    }
    
    
    /**
     * 导入（重复数据获取）
     * 
     * @param fileName 导入的文件名
     * @return checkResult
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("duplicateCheck")
    public List<Integer> duplicateCheck(String fileName)
        throws Exception {
         List<List<String>> dataList = comYieldCurveInfoService.dataAnalysis(fileName);
         List<Integer> checkResult = new ArrayList<>();
         if (null != dataList && dataList.size() > 0) {
             // 导入文件重复数据检查
        	 checkResult = comYieldCurveInfoService.duplicateCheck(dataList);
         }
        return checkResult;
    }
    
    /**
     * 导入（数据保存）
     * 
     * @param fileName
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("importFileSave")
    public List<Integer> importFileSave(String fileName)
        throws Exception {
        List<List<String>> dataList = comYieldCurveInfoService.dataAnalysis(fileName);
        List<Integer> checkResult = new ArrayList<>();
        if (null != dataList && dataList.size() > 0) {
            // 导入文件重复数据检查
       	 checkResult = comYieldCurveInfoService.duplicateCheck(dataList);
       	 List<ComYieldCurveInfoDto> equityList = comYieldCurveInfoService.converToBean(dataList,checkResult);
       	 // 保存，已存在则更新
       	 comYieldCurveInfoService.dealAfterCheck(equityList);
        }
        return checkResult;
    }
}
