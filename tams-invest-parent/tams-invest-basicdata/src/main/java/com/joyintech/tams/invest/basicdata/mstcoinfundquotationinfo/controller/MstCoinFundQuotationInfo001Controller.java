/*
 * 文件名：MstCoinFundQuotationInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年7月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstcoinfundquotationinfo.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.basicdata.mstcoinfundquotationinfo.dto.MstCoinFundQuotationInfo001Dto;
import com.joyintech.tams.invest.basicdata.mstcoinfundquotationinfo.service.MstCoinFundQuotationInfo001Service;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 货币型基金行情信息
 * 
 * @author fanshuanghu
 * @version 1.0 2018年7月16日
 * @see MstCoinFundQuotationInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("MstCoinFundQuotationInfo001")
@RequestMapping("/invest/basicdata/mstcoinfundquotationinfo/MstCoinFundQuotationInfo001")
public class MstCoinFundQuotationInfo001Controller extends BaseController {

    /**
     * service注入
     */
    @Autowired
    private MstCoinFundQuotationInfo001Service mstCoinFundQuotationInfo001Service;

    /**
     * 新建货币型基金行情信息画面
     * 
     * @param model 参数Model
     * @return String 返回请求的界面
     * @see
     * @since 1.0 {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 画面查询方法
     * 
     * @return Page<MstCoinFundQuotationInfo001Dto> 分页实体类结果
     * @throws Exception 错误
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstCoinFundQuotationInfo001Dto> selectAllCurrencyFund()
        throws Exception {
        Page<MstCoinFundQuotationInfo001Dto> page = mstCoinFundQuotationInfo001Service.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }

//    /**
//     * 画面导出功能实现
//     * 
//     * @return ResponseEntity 确认的实体类
//     * @throws Exception 异常
//     * @see
//     * @since 1.0
//     */
//    @RequestMapping("export")
//    public ResponseEntity<byte[]> export()
//        throws Exception {
//        return mstCoinFundQuotationInfo001Service.export(this.getPageId(),
//            RequestUtil.getParameters(getRequest()));
//    }
    /**
     * 画面导出功能实现
     * 
     * @return ResponseEntity 确认的实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return mstCoinFundQuotationInfo001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    /**
     * 删除按钮点击时的check
     * 
     * @param dto 实体类
     * @param result BindingResult 条件验证结果
     * @param 操作类型
     * @return commonCheck check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) MstCoinFundQuotationInfo001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 画面删除操作
     * 
     * @param dto 实体类
     * @param br 要检查的错误集
     * @return AjaxResult 条件验证结果
     * @throws Exception 错误
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) MstCoinFundQuotationInfo001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 设置留痕参数和工作流参数
        ProcessParameter<MstCoinFundQuotationInfo001Dto> processParameter = new ProcessParameter<MstCoinFundQuotationInfo001Dto>();
        WorkFlowParameter<MstCoinFundQuotationInfo001Dto> params = new WorkFlowParameter<MstCoinFundQuotationInfo001Dto>();
        params.setPageId("MstCoinFundQuotationInfo002");
        params.setElementId(dto.getElementId());
        // finprodId基金代码进行设置工作流代码
        params.setBusinessCode(item -> item.getFinprodId());
        params.setBusinessType(DictCodeList.共通业务类型_基金类估值行情信息.getValue());
        // 设置流程业务说明, entity即传入的流程信息
        // "基金代码"+"("+基金代码+")"+"-""估值日"
        // +"("+估值日+")"
        params.setComment(entity -> this.getMessage("MstCoinFundQuotationInfo001.finprodId") + "("
                                    + entity.getFinprodId() + ")"
                                    + this.getMessage("MstCoinFundQuotationInfo001.valDate") + "("
                                    + DateUtil.format(entity.getValDate()) + ")");
        processParameter.setWorkFlowParameter(params);// 设置工作流注解
        String msg = mstCoinFundQuotationInfo001Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new MstCoinFundQuotationInfo001Dto[dto.getSelectedItem().size()]));// 调用工作流事件
        return new AjaxResult(msg);// 返回工作流错误信息
    }

    /**
     * 获取基金代码的联想文本框的值
     * 
     * @return List<SuggestDto>联想文本框实体类的列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCodeName")
    public List<SuggestDto> getSuggestDataByName()
        throws Exception {
        // 画面的输入值param前台的输入值，为了进行模糊查询
        Map<String, Object> param = RequestUtil.getPageParam();
        // 获取根据输入值检索到的结果
        List<SuggestDto> temp = mstCoinFundQuotationInfo001Service.selectForCode(param);// 获取基金代码
        return temp;// 返回基金代码
    }

    /**
     * 获取基金管理人联想文本框的值
     * 
     * @return List<SuggestDto>联想文本框的列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getManagement") // 获取基金管理人的名称
    public List<SuggestDto> getManagement()
        throws Exception {
        // 画面的输入值
        Map<String, Object> param = RequestUtil.getPageParam();
        // 获取根据输入值检索到的结果
        List<SuggestDto> temp = mstCoinFundQuotationInfo001Service.getManagement(param);// 获取基金管理人
        return temp;// 返回取得的值
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
        AjaxResult ajaxResult = mstCoinFundQuotationInfo001Service.checkConfirmMsgForImportData(fileName);
        return ajaxResult;
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
        return this.mstCoinFundQuotationInfo001Service.importData(fileName);
    }
}
