/*
 * 文件名：MarketInformationController001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzehui
 * 修改时间：2018年7月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.common.basicdata.marketdata.bondmarketinfo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.common.basicdata.marketdata.bondmarketinfo.dto.ComBondMarketInfo001Dto;
import com.joyintech.tams.common.basicdata.marketdata.bondmarketinfo.service.ComBondMarketInfo001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 债券行情信息 债券行情信息查询
 * 
 * @author wangzehui
 * @version 1.0 2018年7月17日
 * @see ComBondMarketInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("ComBondMarketInfo001")
@RequestMapping("/common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo001")
public class ComBondMarketInfo001Controller extends BaseController {

    /**
     *每日债券行情service
     */
    @Autowired
    private ComBondMarketInfo001Service comBondMarketInfo001Service;
    
    /**
     * 入口
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.估值来源);
        return super.init(model);
    }

    /**
     * 债券代码下拉选择框
     * @param param 参数
     * @return list结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("getSecId")
    public List<SuggestDto> getSecIdOption(String param)
        throws Exception {
        List<SuggestDto> list = comBondMarketInfo001Service.getSecIdOption(param);
        return list;
    }

    /**
     * 债券简称的下拉框
     * 
     * @param param 参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("getFinProdAbbr")
    public List<SuggestDto> getFinProdAbbr(String param)
        throws Exception {
        return comBondMarketInfo001Service.getFinProdAbbr(param);
    }

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ComBondMarketInfo001Dto> selectAll()
        throws Exception {
        Page<ComBondMarketInfo001Dto> page = comBondMarketInfo001Service.selectForPage(
            RequestUtil.getParameters(getRequest()));
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
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) ComBondMarketInfo001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     *  删除
     * @param dto 页面选中的数据
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) ComBondMarketInfo001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ComBondMarketInfo001Dto> processParameter = new ProcessParameter<ComBondMarketInfo001Dto>();
        WorkFlowParameter<ComBondMarketInfo001Dto> params = new WorkFlowParameter<ComBondMarketInfo001Dto>();
        params.setPageId("ValBondQuotationInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getSecId());
        params.setBusinessType(DictCodeList.共通业务类型_债券估值行情信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：债券代码 + "(" + 债券代码 + ")" + "-" + "估值日" + "-" + "估值日"
        params.setComment(entity -> SpringContextUtil.getTextValue("ValBondQuotationInfo001.secId") + "(" + entity.getSecId() + ")" + "-"
            + SpringContextUtil.getTextValue("ValBondQuotationInfo001.valueDate") + "("
            + DateUtil.format(entity.getValueDate()) + ")"+ this.getMessage("ValBondQuotationInfo001.valueSource") + "("
            + entity.getValueSourceStr() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = comBondMarketInfo001Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new ComBondMarketInfo001Dto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
        
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
        /** modify by yanya on 20210209 增加按照选择数据进行导出 start**/
        String datas = this.getParam("datas");
        Map<String, Object> params= RequestUtil.getPageParam();
        List<ComBondMarketInfo001Dto> list = null;
        if (StringUtil.isNotEmpty(datas)) {
            list = JsonUtils.json2List(datas, ComBondMarketInfo001Dto.class);
        }
        else {
            list = comBondMarketInfo001Service.selectAllList(params);
        }
//        return comBondMarketInfo001Service.export(this.getPageId(),
//            RequestUtil.getParameters(getRequest()));
        File outFile = comBondMarketInfo001Service.makeFile(
                "/com/joyintech/tams/valuation/basedata/marketdata/valbondquotationinfo/conf/export_conf.xml",
                list);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date())
                        + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT
                        + FileNameUtil.getFileExtName(outFile),
                outFile, true);
        /** modify by yanya on 20210209 增加按照选择数据进行导出 end**/

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
        return this.comBondMarketInfo001Service.importData(fileName);
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
         List<List<String>> dataList = comBondMarketInfo001Service.dataAnalysis(fileName);
         List<Integer> checkResult = new ArrayList<>();
         if (null != dataList && dataList.size() > 0) {
             // 导入文件重复数据检查
        	 checkResult = comBondMarketInfo001Service.duplicateCheck(dataList);
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
        List<List<String>> dataList = comBondMarketInfo001Service.dataAnalysis(fileName);
        List<Integer> checkResult = new ArrayList<>();
        if (null != dataList && dataList.size() > 0) {
            // 导入文件重复数据检查
       	 checkResult = comBondMarketInfo001Service.duplicateCheck(dataList);
       	 List<ComBondMarketInfo001Dto> equityList = comBondMarketInfo001Service.converToBean(dataList,checkResult);
       	 // 保存，已存在则更新
       	 comBondMarketInfo001Service.dealAfterCheck(equityList);
        }
        return checkResult;
    }

}
