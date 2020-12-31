/*
 * 文件名：EtrPlanAssetR001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年9月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrplanassetr.controller;

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
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.entrust.etrplanassetr.dto.EtrPlanAssetRDto;
import com.joyintech.tams.invest.entrust.etrplanassetr.service.EtrPlanAssetRService;

/**
 * 计划类利率委托
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年9月25日
 * @see EtrPlanAssetR001Controller
 * @since 1.0
 */
@Controller
@PageId("EtrPlanAssetR001")
@RequestMapping("/invest/entrust/etrplanassetr/EtrPlanAssetR001")
public class EtrPlanAssetR001Controller extends BaseController  {
    
    @Autowired
    private EtrPlanAssetRService etrPlanAssetService;
    
    /**
     * 页面初始化
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model
                /*2,3,4数据字典、zhuyongkang 2019/7/29  start */
                ,DictCode.行内资产二级分类
                ,DictCode.行内资产三级分类
                ,DictCode.行内资产四级分类);
                /* 原因、zhuyongkang 2019/7/29 end*/
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.委托状态);
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return super.init(model);
    }
    /**
     * 
     * 计划类利率委托分页查询
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<EtrPlanAssetRDto> selectForPage(){
        Page<EtrPlanAssetRDto> page = etrPlanAssetService.selectForPage(
            RequestUtil.getPageParam());
        return page;
    }
    /**
     * 
     * 获取组合代码联想文本框信息
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getentrustId")
    public List<SuggestDto> getentrustId(String param)
        throws Exception {
        List<SuggestDto> temp = etrPlanAssetService.getentrustId(param);
        return temp;
    }
    /**
     * 
     * 获取资产代码联想文本框信息
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getfinprodId")
    public List<SuggestDto> getfinprodId(String param)
        throws Exception {
        List<SuggestDto> temp = etrPlanAssetService.getfinprodId(param);
        return temp;
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
        ValidateGroupDelete.class}) EtrPlanAssetRDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 撤回委托 <功能详细描述>
     * 
     * @param dto 实体类
     * @return boolean
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("recall")
    public AjaxResult recallDelegate(@RequestBody EtrPlanAssetRDto dto) {
        List<EtrPlanAssetRDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && dtolist.size()>0){
            for(EtrPlanAssetRDto entity : dtolist){
                etrPlanAssetService.recallDelegate(entity);
            }
            return new AjaxResult(this.getMessage("EtrPlanAssetR001.returnSuc"));
        }
        return new AjaxResult(this.getMessage("EtrPlanAssetR001.returnfail"));
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
    public AjaxResult closeDelegate(@RequestBody EtrPlanAssetRDto dto) {
        List<EtrPlanAssetRDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && dtolist.size()>0){
            for(EtrPlanAssetRDto entity : dtolist){
                etrPlanAssetService.closeDelegate(entity.getEntrustId(), entity.getCloseWay(), entity.getEntrustStatus());
            }
            return new AjaxResult(this.getMessage("ETR_X_003"));
        }
        return new AjaxResult(this.getMessage("ETR_X_004"));
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
    public AjaxResult deleteDelegate(@RequestBody EtrPlanAssetRDto dto) {
        List<EtrPlanAssetRDto> list = dto.getSelectedItem();
        if(list!=null && list.size()>0){
            for(EtrPlanAssetRDto entity : list){
                String entrustId = entity.getEntrustId();
                etrPlanAssetService.deleteDelegate(entrustId);
            }
            return new AjaxResult("删除成功");
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
    	Map<String, Object> param = RequestUtil.getParameters(getRequest());
    	param.put("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return etrPlanAssetService.export(this.getPageId(),
        		param);
    }
}
