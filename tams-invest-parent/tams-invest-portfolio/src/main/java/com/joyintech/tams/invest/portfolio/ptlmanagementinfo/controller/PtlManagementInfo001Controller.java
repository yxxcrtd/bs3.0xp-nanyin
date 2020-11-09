/*
 * 文件名：PtlManagementInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.portfolio.ptlmanagementinfo.controller;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PtlValParam;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.PtlManagementInfo004Dto;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.service.PtlManagementInfo004Service;
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
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.PtlManagementInfoDto;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.service.PtlManagementInfo001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
/**
 * 组合信息管理
 * @author fanshuanghu
 * @version 1.0 2018年9月6日
 * @see PtlManagementInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("PtlManagementInfo001")
@RequestMapping("/invest/portfolio/ptlmanagementinfo/PtlManagementInfo001")
public class PtlManagementInfo001Controller extends BaseController{
    @Autowired
    private PtlManagementInfo001Service ptlManagementInfo001Service;
    @Autowired
    private PtlManagementInfo004Service ptlManagementInfo004Service;
    /**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.组合类型,DictCode.收益类型);
        model.addAttribute("isShowButtonChild", CacheUtil.getSysParam("IS_SHOW_BUTTON_CHILD"));
        return super.init(model);
    }
   /* *//**
     * 分页查询
     * @return Page<MstInterestRateQuotationInfoDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PtlManagementInfoDto> findPage()
        throws Exception {
        Page<PtlManagementInfoDto> page = ptlManagementInfo001Service.selectForPage(
            RequestUtil.getParameters(getRequest()));
        if (StringUtil.isNotEmpty(page)&&StringUtil.isNotEmpty(page.getRoot())){
            page.getRoot().stream().forEach(item->{
                if (StringUtil.equals(item.getPortfolioType(),DictCodeListExt1.组合类型_委外组合.getValue())){
                     item.setUpdateFlg(DictCodeListExt1.是或否_否.getValue());
                }else{
                    item.setUpdateFlg(DictCodeListExt1.是或否_是.getValue());
                }
            });
        }
        return page;
    }
    /**
     * 删除按钮点击时的check
     * @param dto 验证的实体类
     * @param result 验证的结果
     * @return 错误信息
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) PtlManagementInfoDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 创建子组合按钮点击时的check
     * @param dto 验证的实体类
     * @return 错误信息
     */
    @ResponseBody
    @RequestMapping("checkAddChild")
    public AjaxResult checkAddChild(@RequestBody PtlManagementInfoDto dto) {
        return ptlManagementInfo001Service.checkAddChild(dto);
    }
    /**
     * 删除操作
     * @param dto 验证的实体类
     * @param br 错误结果
     * @return 错误信息
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) PtlManagementInfoDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<PtlManagementInfoDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<PtlManagementInfoDto> params = new WorkFlowParameter<>();
        params.setPageId("PtlManagementInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getPortfolioId());
        params.setBusinessType(DictCodeList.共通业务类型_组合管理.getValue());
        // 设置流程业务说明, 组合名称 + "(" + 组合名称 + ")" + "-"  + "组合代码" + "("  + "组合代码"+")"
        params.setComment(
            entity -> this.getMessage("PtlManagementInfo001.portfolioName") + "("
                      + entity.getPortfolioName() + ")" + "-"
                      + this.getMessage("PtlManagementInfo001.portfolioId") + "("
                      + entity.getPortfolioId() + ")" );
        processParameter.setWorkFlowParameter(params);
        List<PtlManagementInfoDto> ptlManagementInfoDtos = ptlManagementInfo001Service.setPtlManagementInfoDto(dto);
        List<PtlManagementInfo004Dto> childList = new ArrayList<>();
        List<PtlManagementInfoDto> parentList = new ArrayList<>();
        for (PtlManagementInfoDto ptlManagementInfoDto : ptlManagementInfoDtos) {
            if (!StringUtil.isEmpty(ptlManagementInfoDto.getParentPortfolioId())){
                PtlManagementInfo004Dto ptlManagementInfo004Dto = new PtlManagementInfo004Dto();
                BeanUtil.copyProperties(ptlManagementInfo004Dto, ptlManagementInfoDto);
                childList.add(ptlManagementInfo004Dto);
            }else {
                parentList.add(ptlManagementInfoDto);
            }
        }
        String msg = null;
        if (!childList.isEmpty()){
            ProcessParameter<PtlManagementInfo004Dto> dtoProcessParameter = new ProcessParameter<>();
            WorkFlowParameter<PtlManagementInfo004Dto> dtoParams = new WorkFlowParameter<>();
            dtoParams.setPageId("PtlManagementInfo004");
            dtoParams.setElementId("PtlManagementInfo004_btn_delete");
            dtoParams.setBusinessCode(item -> item.getPortfolioId());
            dtoParams.setBusinessType(DictCodeList.共通业务类型_组合管理.getValue());
            // 设置流程业务说明, 组合名称 + "(" + 组合名称 + ")" + "-"  + "组合代码" + "("  + "组合代码"+")"
            dtoParams.setComment(
                    entity -> this.getMessage("PtlManagementInfo001.portfolioName") + "("
                            + entity.getPortfolioName() + ")" + "-"
                            + this.getMessage("PtlManagementInfo001.portfolioId") + "("
                            + entity.getPortfolioId() + ")"
                            + this.getMessage("PtlManagementInfo004.childPortfolioId") + "("
                            + entity.getChildPortfolioId() + ")" + "-"
                            + this.getMessage("PtlManagementInfo004.childPortfolioName") + "("
                            +  entity.getChildPortfolioName() + ")");
            dtoProcessParameter.setWorkFlowParameter(dtoParams);
            msg = ptlManagementInfo004Service.start(dtoProcessParameter,
                    childList.toArray(
                            new PtlManagementInfo004Dto[childList.size()]));
        }
        if (!parentList.isEmpty()){
            msg = ptlManagementInfo001Service.start(processParameter,
                    parentList.toArray(
                            new PtlManagementInfoDto[parentList.size()]));
        }
        ArrayList<String> list = new ArrayList<String>();
        for(PtlManagementInfoDto dt : dto.getSelectedItem()){
            list.add(dt.getPortfolioId());
        }
//        msg = this.getMessage("COMMON_I_0030",this.getMessage("PtlManagementInfo001.portfolioId") , list.toString());
        msg = msg+this.getMessage("PtlManagementInfo001.portfolioId")+list.toString();
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
        return ptlManagementInfo001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }

    /**
     * 获取账号
     * @return 账号下拉框集合
     * @see
     * @since 1.0
     */
    @RequestMapping("/getStrategy")
    @ResponseBody
    public List<SuggestDto> getStrategy(){
        return ptlManagementInfo001Service.getStrategy(RequestUtil.getPageParam());
    }

    /**
     * 获取账号
     * @return 账号下拉框集合
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectPortfolioId")
    @ResponseBody
    public List<SelectOptionDto> selectPortfolioId(){
        return ptlManagementInfo001Service.selectPortfolioId(RequestUtil.getPageParam());
    }
}
