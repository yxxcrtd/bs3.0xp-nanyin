/*
 * 文件名：MstSecuritiesAccount001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.mstsecuritiesaccount.controller;

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
import com.joyintech.tams.invest.basicdata.mstsecuritiesaccount.dto.MstSecuritiesAccountDto;
import com.joyintech.tams.invest.basicdata.mstsecuritiesaccount.service.MstSecuritiesAccountService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;


/**
 * 证券账户管理
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年9月11日
 * @see MstSecuritiesAccount001Controller
 * @since 1.0
 */
@Controller
@PageId("MstSecuritiesAccount001")
@RequestMapping("/invest/basicdata/mstsecuritiesaccount/MstSecuritiesAccount001")
public class MstSecuritiesAccount001Controller extends BaseController{
    /**
     * 证券管理Service
     */
    @Autowired
    private MstSecuritiesAccountService mstSecuritiesAccountService;
    
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
    public Page<MstSecuritiesAccountDto> selectAll()
        throws Exception {
        Page<MstSecuritiesAccountDto> page = mstSecuritiesAccountService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }

    /**
     * 证券账户账号联想文本框 <功能详细描述>
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
        List<SuggestDto> temp = mstSecuritiesAccountService.getSecAcctId(param);
        return temp;
    }
    
    /**
     * 组合代码联想文本框service层实现
     *
     * @return SuggestDto
     */
    @ResponseBody
    @RequestMapping("/getPortfolioId")
    public List<SuggestDto> getPortfolioId() {
        String portfolioId = super.getRequest().getParameter("param");
        return mstSecuritiesAccountService.getPortfolioId(portfolioId);
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
        ValidateGroupDelete.class}) MstSecuritiesAccountDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 行删除及删除按钮点击
     * @return AjaxResult
     * @param dto MstSecuritiesAccountDto
     * @throws Exception Exception
     * @param  br BindingResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) MstSecuritiesAccountDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MstSecuritiesAccountDto> processParameter = new ProcessParameter<MstSecuritiesAccountDto>();
        WorkFlowParameter<MstSecuritiesAccountDto> params = new WorkFlowParameter<MstSecuritiesAccountDto>();
        params.setPageId("MstSecuritiesAccount002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getSecAcctId());
        params.setBusinessType(DictCodeList.共通业务类型_证券账户管理.getValue());
        // 设置流程业务说明, entity即传入的dtoSpringContextUtil.getTextValue("MstBondQuotationInfo001.secId")
        params.setComment(entity -> SpringContextUtil.getTextValue("MstSecuritiesAccount001.secAcctId") + "(" + entity.getSecAcctId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstSecuritiesAccountService.start(processParameter,
            dto.getSelectedItem().toArray(
                new MstSecuritiesAccountDto[dto.getSelectedItem().size()]));
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
        return mstSecuritiesAccountService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }

}
