package com.joyintech.tams.invest.product.productplanning.assetplannewsdefend.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.product.productplanning.assetplannewsdefend.dto.AssetPlanNewsDefend001Dto;
import com.joyintech.tams.invest.product.productplanning.assetplannewsdefend.dto.AssetPlanNewsDefend002Dto;
import com.joyintech.tams.invest.product.productplanning.assetplannewsdefend.service.AssetPlanNewsDefend001Service;
import com.joyintech.tams.invest.product.productplanning.assetplannewsdefend.service.AssetPlanNewsDefend002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 资产规划信息维护
 * @author guyunfei
 * 20191226
 */
@Controller
@PageId("AssetPlanNewsDefend001")
@RequestMapping("/invest/product/productplanning/assetplannewsdefend/AssetPlanNewsDefend001")
public class AssetPlanNewsDefend001Controller extends BaseController {

    @Autowired
    private AssetPlanNewsDefend001Service assetPlanNewsDefend001Service;

    @Autowired
    private AssetPlanNewsDefend002Service assetPlanNewsDefend002Service;

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model,DictCode.资产大类);
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
    public Page<AssetPlanNewsDefend001Dto> findPage(){
        return assetPlanNewsDefend001Service.selectForPage(
                RequestUtil.getParameters(getRequest()));
    }

    /**
     * 删除 走工作流
     * @param dto
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/delete")
    public AjaxResult deleteByPrimaryKey(@RequestBody AssetPlanNewsDefend001Dto dto)
            throws Exception{

        dto.setOperateType(GlobalConstant.OPERATE_TYPE_DELETE);
        ProcessParameter<AssetPlanNewsDefend002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<AssetPlanNewsDefend002Dto> params = new WorkFlowParameter<>();
        params.setElementId(dto.getElementId());
        params.setPageId("AssetPlanNewsDefend002");
        params.setBusinessCode(dto.getAssetPlanCode());
        params.setBusinessType(DictCodeList.共通业务类型_资产规划信息维护.getValue());
        // 设置工作流注解
        params.setComment(
                entity -> this.getMessage("AssetPlanNewsDefend")
                        + "(" + entity.getAssetPlanCode() + ")"
        );
        processParameter.setWorkFlowParameter(params);
        //根据资产规划编号查出详细数据
        AssetPlanNewsDefend002Dto dto1 = assetPlanNewsDefend002Service.queryData(dto.getAssetPlanCode());
        dto1.setOperateType(GlobalConstant.OPERATE_TYPE_DELETE);
        // 调用工作流事件，将详细数据存入工作流
        String msg = assetPlanNewsDefend002Service.start(processParameter, dto1);
        // 返回工作流错误信息
        return new AjaxResult(msg);
    }

    /**
     * 导出
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return assetPlanNewsDefend001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }

}
