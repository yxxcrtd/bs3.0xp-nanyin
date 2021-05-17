/*
 * 文件名：ComSharePrice001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.common.basicdata.marketdata.mstshareprice.controller;

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
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.common.basicdata.marketdata.mstshareprice.dto.ComSharePriceDto;
import com.joyintech.tams.common.basicdata.marketdata.mstshareprice.service.ComSharePriceService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;


/**
 * 证券账户管理
 * 〈功能详细描述〉
 * @author wangpeng
 * @version 1.0 2019年6月28日
 * @see ComSharePrice001Controller
 * @since 1.0
 */
@Controller
@PageId("ComSharePrice001")
@RequestMapping("/common/basicdata/marketdata/mstshareprice/ComSharePrice001")
public class ComSharePrice001Controller extends BaseController{
    /**
     * 股票行情Service
     */
    @Autowired
    private ComSharePriceService comSharePriceService;
    
    /**
     * 页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return super.init(model);
    }
    /**
     * 页面查询，表格分页
     * 
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ComSharePriceDto> findPage()
        throws Exception {
        Page<ComSharePriceDto> page = comSharePriceService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }
    
    /**
     * 获取页面下拉选信息(资产代码信息）
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getFinprodIdForAdd")
    public Page<SelectOptionDto> getFinprodIdForAdd() 
        throws Exception {
        Page<SelectOptionDto> temp = comSharePriceService.getFinprodIdForAdd(RequestUtil.getPageParam());
        return temp;
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
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) ComSharePriceDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 删除按钮点击
     * @return AjaxResult
     * @param dto ComSharePriceDto
     * @throws Exception Exception
     * @param  br BindingResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) ComSharePriceDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ComSharePriceDto> processParameter = new ProcessParameter<ComSharePriceDto>();
        WorkFlowParameter<ComSharePriceDto> params = new WorkFlowParameter<ComSharePriceDto>();
        params.setPageId("ComSharePrice002");
//        params.setPageId(RequestUtil.getParam("pageId"));
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getSharesId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_股票行情信息.getValue());
//        params.setBusinessType("MST30");
        // 设置流程业务说明, entity即传入的dtoSpringContextUtil.getTextValue("MstBondQuotationInfo001.secId")
        params.setComment(
                entity -> SpringContextUtil.getTextValue("ComSharePrice001.sharesId")+"("+ entity.getSharesId() +")"+"-"+SpringContextUtil.getTextValue("ComSharePrice001.dealDate") + "(" + DateUtil.format(entity.getDealDate()) + ")"
                );
        processParameter.setWorkFlowParameter(params);
        String msg = comSharePriceService.start(processParameter,
            dto.getSelectedItem().toArray(
                new ComSharePriceDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    
    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        /** modify by yanya on 20210225 增加页面选中数据行进行导出 start**/
        String datas = this.getParam("datas");
        List<ComSharePriceDto> list = null;
        if (StringUtil.isNotEmpty(datas)) {
            list = JsonUtils.json2List(datas, ComSharePriceDto.class);
        }
        else {
            list = comSharePriceService.selectAllList(RequestUtil.getPageParam());
        }
//        return comSharePriceService.export(this.getPageId(),
//            RequestUtil.getParameters(getRequest()));
        File outFile = comSharePriceService.makeFile(
                "/com/joyintech/tams/valuation/basedata/marketdata/valshareprice/conf/export_conf.xml",
                list);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date())
                        + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT
                        + FileNameUtil.getFileExtName(outFile),
                outFile, true);
        /** modify by yanya on 20210225 增加页面选中数据行进行导出 end**/
    }
    
    
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
        return this.comSharePriceService.importData(fileName);
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
         List<List<String>> dataList = comSharePriceService.dataAnalysis(fileName);
         List<Integer> checkResult = new ArrayList<>();
         if (null != dataList && dataList.size() > 0) {
             // 导入文件重复数据检查
        	 checkResult = comSharePriceService.duplicateCheck(dataList);
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
        List<List<String>> dataList = comSharePriceService.dataAnalysis(fileName);
        List<Integer> checkResult = new ArrayList<>();
        if (null != dataList && dataList.size() > 0) {
            // 导入文件重复数据检查
       	 checkResult = comSharePriceService.duplicateCheck(dataList);
       	 List<ComSharePriceDto> equityList = comSharePriceService.converToBean(dataList,checkResult);
       	 // 保存，已存在则更新
       	comSharePriceService.dealAfterCheck(equityList);
        }
        return checkResult;
    }

}
