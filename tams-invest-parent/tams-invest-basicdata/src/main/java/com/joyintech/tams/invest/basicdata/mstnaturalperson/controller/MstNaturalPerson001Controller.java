/*
 * 文件名：MstNaturalPerson001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstnaturalperson.controller;

import java.util.HashMap;
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
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.basicdata.mstnaturalperson.dto.MstNaturalPerson001Dto;
import com.joyintech.tams.invest.basicdata.mstnaturalperson.service.MstNaturalPerson001Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 自然人信息管理
 * 
 * @author xiexinting
 * @version 1.0 2018年8月10日
 * @see MstNaturalPerson001Controller
 * @since 1.0
 */
@Controller
@PageId("MstNaturalPerson001")
@RequestMapping("/invest/basicdata/mstnaturalperson/MstNaturalPerson001")
public class MstNaturalPerson001Controller extends BaseController {

    /**
     * 
     */
    @Autowired
    private MstNaturalPerson001Service mstNaturalPerson001Service;

    /**
     * 初始化页面参数
     */
    @Override
    public String init(Model model) {
        model.addAttribute("man", DictCodeList.性别_男.getValue());
        return super.init(model);
    }

    /**
     * 查询 <功能详细描述>
     * 
     * @return page分页参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstNaturalPerson001Dto> selectAllMstCaptialAccount()
        throws Exception {
        Page<MstNaturalPerson001Dto> page = mstNaturalPerson001Service.selectForPage(
            RequestUtil.getPageParam());
        return page;
    }

    /**
     * 用户名联想文本框 <功能详细描述>
     * 
     * @return temp 返回联想文本框参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getcName")
    public List<SuggestDto> getcName()
        throws Exception {
        String param = super.getRequest().getParameter("param");
        Map<String, String> map = new HashMap<String, String>();
        map.put("naturalp", DictCodeList.客户类型_自然人.getValue());
        map.put("param", param);
        List<SuggestDto> temp = mstNaturalPerson001Service.getcName(map);
        return temp;

    }

    /**
     * 身份证联想文本框 <功能详细描述>
     * 
     * @return temp 联想文本框参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getcID")
    public List<SuggestDto> getcID()
        throws Exception {
        String param = super.getRequest().getParameter("param");
        List<SuggestDto> temp = mstNaturalPerson001Service.getcID(param);
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
        ValidateGroupDelete.class}) MstNaturalPerson001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 
     * 删除
     * <功能详细描述>
     * @param dto 删除参数
     * @param br 删除参数
     * @return 删除返回参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) MstNaturalPerson001Dto dto, BindingResult br)
        throws Exception {
//        if (br.hasErrors()) {
//            return new AjaxResult(br);
//        }
//        ProcessParameter<MstNaturalPerson001Dto> processParameter = new ProcessParameter<MstNaturalPerson001Dto>();
//        WorkFlowParameter<MstNaturalPerson001Dto> params = new WorkFlowParameter<MstNaturalPerson001Dto>();
//        params.setPageId("MstNaturalPerson002");
//        params.setElementId(dto.getElementId());
//        params.setBusinessCode(item -> item.getCustomerId());
//        params.setBusinessType(DictCodeList.共通业务类型_自然人管理.getValue());
//        // 设置流程业务说明, entity即传入的dto
//        // 客户代码 + "(" + 客户代码 + ")" + "-" + "客户名称" + "-" + "客户名称"
//        params.setComment(entity -> "客户名称" + "-" + entity.getCustomerName());
//        processParameter.setWorkFlowParameter(params);
//        String msg = mstNaturalPerson001Service.start(processParameter,
//            dto.getSelectedItem().toArray(
//                new MstNaturalPerson001Dto[dto.getSelectedItem().size()]));
        if(dto!=null){
            if(mstNaturalPerson001Service.delete(dto)){
                return new AjaxResult("删除成功");
            }
        }
        return new AjaxResult("删除失败");
    }

    /**
     * 文件导出 <功能详细描述>
     * 
     * @return 导出返回参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return mstNaturalPerson001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}
