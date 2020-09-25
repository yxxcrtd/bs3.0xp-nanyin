/*
 * 文件名：MarketInformationController001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzehui
 * 修改时间：2018年7月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstbondquotationinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.basicdata.mstbondquotationinfo.dto.MstBondQuotationInfoDto;
import com.joyintech.tams.invest.basicdata.mstbondquotationinfo.service.MstBondQuotationInfoService;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 债券行情信息 债券行情信息查询
 * 
 * @author wangzehui
 * @version 1.0 2018年7月17日
 * @see MstBondQuotationInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("MstBondQuotationInfo001")
@RequestMapping("/invest/basicdata/mstbondquotationinfo/MstBondQuotationInfo001")
public class MstBondQuotationInfo001Controller extends BaseController {

    /**
     * 
     */
    @Autowired
    private MstBondQuotationInfoService mstBondQuotationInfoService;

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
        List<SuggestDto> list = mstBondQuotationInfoService.getSecIdOption(param);
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
        return mstBondQuotationInfoService.getFinProdAbbr(param);
    }

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstBondQuotationInfoDto> selectAll()
        throws Exception {
        Page<MstBondQuotationInfoDto> page = mstBondQuotationInfoService.selectForPage(
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
        ValidateGroupDelete.class}) MstBondQuotationInfoDto dto, BindingResult result) {
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
        ValidateGroupDelete.class}) MstBondQuotationInfoDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MstBondQuotationInfoDto> processParameter = new ProcessParameter<MstBondQuotationInfoDto>();
        WorkFlowParameter<MstBondQuotationInfoDto> params = new WorkFlowParameter<MstBondQuotationInfoDto>();
        params.setPageId("MstBondQuotationInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getSecId());
        params.setBusinessType(DictCodeList.共通业务类型_债券估值行情信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：债券代码 + "(" + 债券代码 + ")" + "-" + "估值日" + "-" + "估值日"
        params.setComment(entity -> SpringContextUtil.getTextValue("MstBondQuotationInfo001.secId") + "(" + entity.getSecId() + ")" + "-"
            + SpringContextUtil.getTextValue("MstBondQuotationInfo001.valueDate") + "("
            + DateUtil.format(entity.getValueDate()) + ")"+ this.getMessage("MstBondQuotationInfo001.valueSource") + "("
            + entity.getValueSourceStr() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstBondQuotationInfoService.start(processParameter,
            dto.getSelectedItem().toArray(
                new MstBondQuotationInfoDto[dto.getSelectedItem().size()]));
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
        return mstBondQuotationInfoService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
    
 // hsgexinghua   add by zhanngxing1 20200220 start

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        return mstBondQuotationInfoService.checkConfirmMsgForImportData(fileName);
    }

    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("/importFile")
    public List<ExcelValidDto> importData(String fileName) {
        return this.mstBondQuotationInfoService.importData(fileName);

    }

}
