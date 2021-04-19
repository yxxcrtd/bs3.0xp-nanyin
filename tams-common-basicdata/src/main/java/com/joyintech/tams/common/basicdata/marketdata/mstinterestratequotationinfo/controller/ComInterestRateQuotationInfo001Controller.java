/*
 * 文件名：MstInterestRateQuotationInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年8月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.common.basicdata.marketdata.mstinterestratequotationinfo.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.basicdata.marketdata.mstinterestratequotationinfo.dto.ComInterestRateQuotationInfoDto;
import com.joyintech.tams.common.basicdata.marketdata.mstinterestratequotationinfo.service.ComInterestRateQuotationInfoService;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
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
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 利率行情信息查询
 * 
 * @author fanshuanghu
 * @version 1.0 2018年8月17日
 * @see ComInterestRateQuotationInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("ComInterestRateQuotationInfo001")
@RequestMapping("/common/basicdata/marketdata/mstinterestratequotationinfo/ComInterestRateQuotationInfo001")
public class ComInterestRateQuotationInfo001Controller extends BaseController {

    /**
     * 注入service类
     */
    @Autowired
    private ComInterestRateQuotationInfoService comInterestRateQuotationInfoService;

    /**
     * 界面初始化 {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.利率行情);
        return super.init(model);
    }

    /**
     * 分页查询
     * 
     * @return Page<MstInterestRateQuotationInfoDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ComInterestRateQuotationInfoDto> selectAllMstInterestRate()
        throws Exception {
        Map<String, Object> pageParam = RequestUtil.getParameters(getRequest());
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.利率行情, null);
        List<String> intRateIdList = new ArrayList<String>();
        if (selectDto != null && !StringUtil.isEmptyList(selectDto.getOptions())) {
            List<SelectOptionDto> options = selectDto.getOptions();
            for(SelectOptionDto selectOptionDto :options){
                intRateIdList.add(selectOptionDto.getId());
            }
        }
        pageParam.put("intRateIdList", intRateIdList);
        Page<ComInterestRateQuotationInfoDto> page = comInterestRateQuotationInfoService.selectForPage(
            pageParam);
        return page;
    }

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
        return comInterestRateQuotationInfoService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }

    /**
     * 删除按钮点击时的check
     * 
     * @param dto 验证的实体类
     * @param result 验证的结果
     * @return 错误信息
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) ComInterestRateQuotationInfoDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 删除操作
     * 
     * @param dto 验证的实体类
     * @param br 错误结果
     * @return 错误信息
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) ComInterestRateQuotationInfoDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ComInterestRateQuotationInfoDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ComInterestRateQuotationInfoDto> params = new WorkFlowParameter<>();
        params.setPageId("ComInterestRateQuotationInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getIntRateId());
        params.setBusinessType(DictCodeList.共通业务类型_基准利率行情.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> this.getMessage("ComInterestRateQuotationInfo002.intRateId") + "("
                      + entity.getIntRateId() + ")" + "-"
                      + this.getMessage("ComInterestRateQuotationInfo002.intRateName") + "("
                      + entity.getIntRateName() + ")" + "-"
                      + this.getMessage("ComInterestRateQuotationInfo002.effDate") + "("
                      + DateUtil.format(entity.getEffDate()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = comInterestRateQuotationInfoService.start(processParameter,
            dto.getSelectedItem().toArray(
                new ComInterestRateQuotationInfoDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
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
        return comInterestRateQuotationInfoService.checkConfirmMsgForImportData(fileName);
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
        return this.comInterestRateQuotationInfoService.importData(fileName);

    }

}
