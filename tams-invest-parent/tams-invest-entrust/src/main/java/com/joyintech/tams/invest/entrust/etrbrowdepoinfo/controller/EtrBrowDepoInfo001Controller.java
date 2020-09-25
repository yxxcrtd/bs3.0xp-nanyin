/*
 * 文件名：EtrBrowDepoInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrbrowdepoinfo.controller;

import java.util.ArrayList;
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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.entrust.etrbrowdepoinfo.dto.EtrBrowDepoInfoDto;
import com.joyintech.tams.invest.entrust.etrbrowdepoinfo.service.EtrBrowDepoInfoService;

/**
 * 存款委托
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月11日
 * @see EtrBrowDepoInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("EtrBrowDepoInfo001")
@RequestMapping("/invest/entrust/etrbrowdepoinfo/EtrBrowDepoInfo001")
public class EtrBrowDepoInfo001Controller extends BaseController {
    
    @Autowired
    private EtrBrowDepoInfoService etrBrowDepoInfoService;
    
    /**
     * 页面初始化
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.委托状态);
        this.setSelectOptionToModel(model, DictCode.存款类资产类型);
        // 新增资产二、三、四级分类 王贵奇 2019/8/1 开始
        this.setSelectOptionToModel(model, DictCode.行内资产二级分类);
        this.setSelectOptionToModel(model, DictCode.行内资产三级分类);
        this.setSelectOptionToModel(model, DictCode.行内资产四级分类);
        // 新增资产二、三、四级分类 王贵奇 2019/8/1 结束
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return super.init(model);
    }
    
    /**
     * 
     * 存款委托分页查询
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<EtrBrowDepoInfoDto> selectForPage(){
        Page<EtrBrowDepoInfoDto> page = etrBrowDepoInfoService.selectForPage(
            RequestUtil.getPageParam());
        return page;
    }
    
    /**
     * 组合代码联想文本框service层实现
     *
     * @param portfolioId 检索参数
     * @return SuggestDto
     */
    @ResponseBody
    @RequestMapping("/getPortfolioId")
    public List<SuggestDto> getPortfolioId() {
        String portfolioId = super.getRequest().getParameter("param");
        return etrBrowDepoInfoService.getPortfolioId(portfolioId);
    }
    
    /**
     * 资产代码联想文本框service层实现
     *
     * @param finprodId 检索参数
     * @return SuggestDto
     */
    @ResponseBody
    @RequestMapping("/getFinprodMarketId")
    public List<SuggestDto> getFinprodMarketId() {
        String finprodId = super.getRequest().getParameter("param");
        return etrBrowDepoInfoService.getFinprodMarketId(finprodId);
    }
    
    /**
     * 按钮点击时的check
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
        ValidateGroupDelete.class}) EtrBrowDepoInfoDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 撤回委托 
     * <功能详细描述>
     * @param dto 实体类
     * @return boolean
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("recall")
    public AjaxResult recallDelegate(@RequestBody EtrBrowDepoInfoDto dto) {
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        List<EtrBrowDepoInfoDto> list = dto.getSelectedItem();
        for(EtrBrowDepoInfoDto etrBrowDepoInfoDto:list){
            if(etrBrowDepoInfoService.recallDelegate(etrBrowDepoInfoDto)){
                ajaxResult.setMessage(this.getMessage("ETR_X_001"));
            }else{
                errorList.add(new ValidMessage(getMessage("委托代码:"+etrBrowDepoInfoDto.getPortfolioId())+"委托撤销失败"));
                ajaxResult.setErrorList(errorList);
            }
        }
        return ajaxResult;
    }
    /**
     * 关闭委托 
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("close")
    public AjaxResult closeDelegate(@RequestBody EtrBrowDepoInfoDto dto) {
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        List<EtrBrowDepoInfoDto> list = dto.getSelectedItem();
        for(EtrBrowDepoInfoDto etrBrowDepoInfoDto:list){
            if(etrBrowDepoInfoService.closeDelegate(etrBrowDepoInfoDto.getEntrustId(), etrBrowDepoInfoDto.getCloseWay())){
                ajaxResult.setMessage(this.getMessage("ETR_X_003"));
            }else{
                errorList.add(new ValidMessage(getMessage("组合代码:"+etrBrowDepoInfoDto.getPortfolioId())+"委托关闭失败"));
                ajaxResult.setErrorList(errorList);
            }
        }
        return ajaxResult;
    }
    
    /**
     * 
     * 删除投资委托
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult deleteDelegate(@RequestBody EtrBrowDepoInfoDto dto) {
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        List<EtrBrowDepoInfoDto> list = dto.getSelectedItem();
        for(EtrBrowDepoInfoDto etrBrowDepoInfoDto:list){
            if(etrBrowDepoInfoService.deleteDelegate(etrBrowDepoInfoDto.getEntrustId())){
                
            }else{
                errorList.add(new ValidMessage(getMessage("组合代码:"+etrBrowDepoInfoDto.getPortfolioId())+"委托删除失败"));
            }
        }
        ajaxResult.setErrorList(errorList);
        return ajaxResult;
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
    	Map<String, Object> param = RequestUtil.getParameters(getRequest());
    	param.put("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return etrBrowDepoInfoService.export(this.getPageId(),param);
    }
    
}
