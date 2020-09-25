/*
 * 文件名：FundAccountManagementController001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.mstcaptialaccount.controller;

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
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.basicdata.mstcaptialaccount.dto.MstCaptialAccountDto;
import com.joyintech.tams.invest.basicdata.mstcaptialaccount.service.MstCaptialAccountService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年7月23日
 * @see MstCaptialAccount001Controller
 * @since 1.0
 */
@Controller
@PageId("MstCaptialAccount001")
@RequestMapping("/invest/basicdata/mstcaptialaccount/MstCaptialAccount001")
public class MstCaptialAccount001Controller extends BaseController{
    /**
     * {@Autowired}
     */
    @Autowired
    private MstCaptialAccountService mstCaptialAccountService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        return super.init(model);
    }
    /**
     * 
     * 页面查询，表格分页
     * <功能详细描述>
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstCaptialAccountDto> selectAllMstCaptialAccount()
        throws Exception {
        Page<MstCaptialAccountDto> page = mstCaptialAccountService.selectForPage(RequestUtil.getPageParam());
        return page;
    }
    
    /**
     * 
     * 联想文本框
     * <功能详细描述>
     * @return List<SuggestDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getAcctName")
    public List<SuggestDto> getAcctName() throws Exception {
        String param = super.getRequest().getParameter("param");
        List<SuggestDto> temp = mstCaptialAccountService.getAcctName(param);
        return temp;
    }
    
    /**
     * 删除按钮点击时的check
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) MstCaptialAccountDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 行删除与删除按钮
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto 业务实体
     * @param br br
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) MstCaptialAccountDto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<MstCaptialAccountDto> processParameter = new ProcessParameter<MstCaptialAccountDto>();
        WorkFlowParameter<MstCaptialAccountDto> params = new WorkFlowParameter<MstCaptialAccountDto>();
        params.setPageId("MstCaptialAccount002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item->item.getAcctNo()+item.getCcy());
        params.setBusinessType(DictCodeList.共通业务类型_资金账户管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        //"网点账号"+ "(" + 网点号/大额支付系统号+账号+ ")" +  "-" + "账户名称"+ "(" + 账户名称 + ")"  +  "-" + "币种"+ "(" + 币种的数据字典名 + ")"
        params.setComment(
            entity -> "网点账号"+ "(" + entity.getLargePayno()+entity.getAcctNo()+ ")" +  "-" 
            + "账户名称"+ "(" + entity.getAcctName() + ")"  +  "-" 
                + "币种"+ "(" + SysDataUtil.getNameFromDataDict(DictCode.币种,entity.getCcy()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstCaptialAccountService.start(processParameter, 
            dto.getSelectedItem().toArray(new MstCaptialAccountDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    /**
     * 文件导出
     * <一句话功能简述>
     * <功能详细描述>
     * @return ResponseEntity
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() throws Exception {
        return mstCaptialAccountService.export(this.getPageId(),RequestUtil.getParameters(getRequest()));
    }
}
