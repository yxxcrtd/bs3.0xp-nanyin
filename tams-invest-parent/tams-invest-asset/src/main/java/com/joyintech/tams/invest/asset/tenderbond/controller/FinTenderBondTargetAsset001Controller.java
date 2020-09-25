/*
 * 文件名：FinTenderBondTargetAsset001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.tenderbond.controller;

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
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.asset.tenderbond.dto.FinTenderBondTargetAssetDto;
import com.joyintech.tams.invest.asset.tenderbond.service.FinTenderBondTargetAssetService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 一级投标债券资料预定义
 * @author wangpeng
 * @version 1.0 2019年7月2日
 * @see FinTenderBondTargetAsset002Controller
 * @since 1.0
 */
@Controller
@PageId("FinTenderBondTargetAsset001")
@RequestMapping("/invest/asset/tenderbond/FinTenderBondTargetAsset001")
public class FinTenderBondTargetAsset001Controller extends BaseController{
   
    @Autowired
    private FinTenderBondTargetAssetService finTenderBondTargetAssetService;
    
    /**
     * 初始化数据字典下拉框
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.息票品种,DictCode.利率类型,
    	DictCode.行内资产二级分类,DictCode.行内资产三级分类,DictCode.行内资产四级分类,	//新增资产二、三、四级分类 王贵奇 2019/7/31
        DictCode.WIND一级分类_含债券一级分类等,DictCode.WIND二级分类_含债券二级分类等,DictCode.是否,DictCode.币种};
    
    @Override
    public String init(Model model){
        setSelectOptionToModel(model, DICT_CODES);
        model.addAttribute("FINPROD_TYPE_TG_F01", BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return super.init(model);
    }
    
    
    /**
     * 初始化查询
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinTenderBondTargetAssetDto> selectAll()
        throws Exception {
        Page<FinTenderBondTargetAssetDto> page = finTenderBondTargetAssetService.selectForPage(
            RequestUtil.getParameters(getRequest()));
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
        ValidateGroupDelete.class}) FinTenderBondTargetAssetDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    
    /**
     * 删除
     * @param dto
     * @param br
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) FinTenderBondTargetAssetDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinTenderBondTargetAssetDto> processParameter = new ProcessParameter<FinTenderBondTargetAssetDto>();
        WorkFlowParameter<FinTenderBondTargetAssetDto> params = new WorkFlowParameter<FinTenderBondTargetAssetDto>();
        params.setPageId("FinTenderBondTargetAsset002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodMarketId());
        params.setBusinessType(DictCodeList.共通业务类型_债券类资产信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("FinBondTargetAsset002.finprodId")+
                GlobalConstant.OPEN_PARENTHESIS+entity.getFinprodMarketId()+GlobalConstant.CLOSE_PARENTHESIS+
                getMessage("FinBondTargetAsset002.finprodName")+GlobalConstant.OPEN_PARENTHESIS+
                entity.getFinprodAbbr()+GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = finTenderBondTargetAssetService.start(processParameter,
            dto.getSelectedItem().toArray(
                new FinTenderBondTargetAssetDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    
    /**
     * 导出
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
    	Map<String, Object> param = RequestUtil.getParameters(getRequest());
		param.put("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return finTenderBondTargetAssetService.export(this.getPageId(),param);
    }
}
