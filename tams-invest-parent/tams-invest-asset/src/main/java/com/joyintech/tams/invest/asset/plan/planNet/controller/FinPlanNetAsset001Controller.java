/*
 * 文件名：FinPlanNetAsset001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.plan.planNet.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
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
import com.joyintech.tams.invest.asset.plan.planNet.dto.FinPlanNetAssetDto;
import com.joyintech.tams.invest.asset.plan.planNet.service.FinPlanNetAssetService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 计划类资产（净值型）查询和删除controller
 * 
 * @author liuwei
 * @version 1.0 2018年9月7日
 * @see FinPlanNetAsset001Controller
 * @since 1.0
 */
@Controller
@PageId("FinPlanNetAsset001")
@RequestMapping("/invest/asset/plan/planNet/FinPlanNetAsset001")
public class FinPlanNetAsset001Controller extends BaseController{
    
    /**
     * 计划类资产（净值型）service
     */
    @Autowired
    private FinPlanNetAssetService finPlanNetAssetService;
    
    /**
     * 计划类资产（净值型）查询界面的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
    	// 新增行内五级分类 zhangpuyu 2019-7-22 start 
       this.setSelectOptionToModel(model,DictCode.计划类资产一级分类,DictCode.行内资产二级分类,DictCode.行内资产三级分类,DictCode.行内资产四级分类,
        		DictCode.行内资产五级分类, DictCode.计划类资产二级分类,DictCode.清算速度,DictCode.币种);
        // 新增行内五级分类 zhangpuyu 2019-7-22 end
        model.addAttribute("FINPROD_TYPE_TG_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        model.addAttribute("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return super.init(model);
    }
    
    /**
     * 资产代码的联想文本框事件
     * 
     * @param param 输入的参数
     * @return 查询的资产代码和资产名称列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFinProdMarketId")
    public List<SuggestDto> getFinProdMarketId(String param)
        throws Exception {
        List<SuggestDto> temp = finPlanNetAssetService.getFinProdMarketId(param);
        return temp;
    }
    
    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinPlanNetAssetDto> selectAll()
        throws Exception {
        Map<String,Object> map = RequestUtil.getPageParam();
        Page<FinPlanNetAssetDto> page = finPlanNetAssetService.selectForPage(map);
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
            ValidateGroupDelete.class}) FinPlanNetAssetDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 删除
     * @param dto 页面选中的数据
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) FinPlanNetAssetDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinPlanNetAssetDto> processParameter = new ProcessParameter<FinPlanNetAssetDto>();
        WorkFlowParameter<FinPlanNetAssetDto> params = new WorkFlowParameter<FinPlanNetAssetDto>();
        params.setPageId("FinPlanNetAsset002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodMarketId());
        params.setBusinessType(DictCodeList.共通业务类型_计划类资产_净值型_信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：资产代码 + "(" + 资产代码 + ")" + 资产名称 + "(" + 资产名称 + ")"
        params.setComment(entity -> getMessage("FinPlanNetAsset001.finProdMarketId") + "(" + entity.getFinprodMarketId() + ")"+"-"
                +getMessage("FinPlanNetAsset001.finProdName") + "(" + entity.getFinprodName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finPlanNetAssetService.start(processParameter,
            dto.getSelectedItem().toArray(
                new FinPlanNetAssetDto[dto.getSelectedItem().size()]));
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
    	Map<String, Object> param = RequestUtil.getParameters(getRequest());
    	param.put("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return finPlanNetAssetService.export(this.getPageId(),param);

    }
}
