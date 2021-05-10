/*
 * 文件名：FinBondTargetAsset001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basicdata.asset.bond.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.basicdata.asset.bond.dto.FinBondTargetAssetDto;
import com.joyintech.tams.valuation.basicdata.asset.bond.service.FinBondTargetAssetService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 债券类标的信息管理
 * @author zhangnian
 * @version 1.0 2018年9月20日
 * @see FinBondTargetAsset002Controller
 * @since 1.0
 */
@Controller
@PageId("FinBondTargetAsset001")
@RequestMapping("/valuation/basicdata/asset/bond/FinBondTargetAsset001")
public class FinBondTargetAsset001Controller extends BaseController{
   
    @Autowired
    private FinBondTargetAssetService finBondTargetAssetService;
    
    /**
     * 初始化数据字典下拉框
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {
            DictCode.息票品种,
            DictCode.债券类型,
            DictCode.债券核算分类,
            DictCode.是否,
            DictCode.币种
    };
    
    @Override
    public String init(Model model){
        setSelectOptionToModel(model, DICT_CODES);
        model.addAttribute("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_债券.getValue());
        model.addAttribute("YES",DictCodeList.是否_是.getValue());
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
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
    public Page<FinBondTargetAssetDto> findPage()
        throws Exception {
        Page<FinBondTargetAssetDto> page = finBondTargetAssetService.selectForPage(
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
        ValidateGroupDelete.class}) FinBondTargetAssetDto dto, BindingResult result) {
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
        ValidateGroupDelete.class}) FinBondTargetAssetDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinBondTargetAssetDto> processParameter = new ProcessParameter<FinBondTargetAssetDto>();
        WorkFlowParameter<FinBondTargetAssetDto> params = new WorkFlowParameter<FinBondTargetAssetDto>();
        params.setPageId("FinBondTargetAsset002");
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
        String msg = finBondTargetAssetService.start(processParameter,
            dto.getSelectedItem().toArray(
                new FinBondTargetAssetDto[dto.getSelectedItem().size()]));
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
        String datas = this.getParam("datas");
        Map<String, Object> params= RequestUtil.getPageParam();

        List<FinBondTargetAssetDto> list = null;
        if (StringUtil.isNotEmpty(datas)) {
            list = JsonUtils.json2List(datas, FinBondTargetAssetDto.class);
        }
        else {
            list = finBondTargetAssetService.selectAllList(params);
        }
        File outFile = finBondTargetAssetService.makeFile(
                "/com/joyintech/tams/valuation/basicdata/asset/bond/conf/export_conf.xml",
                list);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date())
                        + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT
                        + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
}
