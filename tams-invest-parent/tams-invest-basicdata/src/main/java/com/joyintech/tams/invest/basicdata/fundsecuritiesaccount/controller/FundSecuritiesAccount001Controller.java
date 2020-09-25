/*
 * 文件名：FundSecuritiesAccount001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.fundsecuritiesaccount.controller;

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
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.basicdata.fundsecuritiesaccount.service.FundSecuritiesAccountService;
import com.joyintech.tams.invest.basicdata.fundsecuritiesaccount.dto.FundSecuritiesAccountDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;


/**
 * 基金账户管理
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年9月11日
 * @see FundSecuritiesAccount001Controller
 * @since 1.0
 */
@Controller
@PageId("FundSecuritiesAccount001")
@RequestMapping("/invest/basicdata/fundsecuritiesaccount/FundSecuritiesAccount001")
public class FundSecuritiesAccount001Controller extends BaseController{
    /**
     * 基金管理Service
     */
    @Autowired
    private FundSecuritiesAccountService fundSecuritiesAccountService;
    
    /**
     * 页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        //单选框枚举值设置
        this.setSelectOptionToModel(model, DictCode.登记托管机构);
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
    public Page<FundSecuritiesAccountDto> selectAll()
        throws Exception {
        Page<FundSecuritiesAccountDto> page = fundSecuritiesAccountService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }

    /**
     * 基金账户账号联想文本框 <功能详细描述>
     * @param param String
     * @return List
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getSecAcctId")
    public List<SuggestDto> getSecAcctId(String param)
        throws Exception {
        List<SuggestDto> temp = fundSecuritiesAccountService.getSecAcctId(param);
        return temp;
    }
    
    /**
     * 基金管理人联想文本框 <功能详细描述>
     * @param param String
     * @return List
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getSecManager")
    public List<SuggestDto> getSecManager(String param)
        throws Exception {
        List<SuggestDto> temp = fundSecuritiesAccountService.getSecManager(param);
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
        ValidateGroupDelete.class}) FundSecuritiesAccountDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 行删除及删除按钮点击
     * @return AjaxResult
     * @param dto FundSecuritiesAccountDto
     * @throws Exception Exception
     * @param  br BindingResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) FundSecuritiesAccountDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FundSecuritiesAccountDto> processParameter = new ProcessParameter<FundSecuritiesAccountDto>();
        WorkFlowParameter<FundSecuritiesAccountDto> params = new WorkFlowParameter<FundSecuritiesAccountDto>();
        params.setPageId("FundSecuritiesAccount002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getSecAcctId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_基金账户管理.getValue());
        params.setComment(entity -> SpringContextUtil.getTextValue("FundSecuritiesAccount001.secAcctId") + "(" + entity.getSecAcctId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = fundSecuritiesAccountService.start(processParameter,
            dto.getSelectedItem().toArray(
                new FundSecuritiesAccountDto[dto.getSelectedItem().size()]));
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
        return fundSecuritiesAccountService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }

}
