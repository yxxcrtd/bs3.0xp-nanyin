package com.joyintech.tams.invest.product.productplanning.informationMaintenance.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.product.productplanning.common.service.PlanningCommonService;
import com.joyintech.tams.invest.product.productplanning.informationMaintenance.dto.FinProductPlanning001Dto;
import com.joyintech.tams.invest.product.productplanning.informationMaintenance.dto.FinProductPlanning002Dto;
import com.joyintech.tams.invest.product.productplanning.informationMaintenance.service.FinProductPlanningEstablish001Service;
import com.joyintech.tams.invest.product.productplanning.informationMaintenance.service.FinProductPlanningEstablish002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
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

/**
 * 产品规划信息维护
 *  * @author 代国富
 *  * @version 1.0 2019年12月12日
 */
@Controller
@PageId("FinProductPlanningEstablish001")
@RequestMapping("/invest/product/productplanning/informationMaintenance/FinProductPlanningEstablish001")
public class FinProductPlanningEstablish001Controller extends BaseController {
    @Autowired
    private FinProductPlanningEstablish001Service finProductPlanningEstablish001Service;
    @Autowired
    private FinProductPlanningEstablish002Service finProductPlanningEstablish002Service;
    @Override
    public String init(Model model) {
        /**
         * 不需要有效状态 dgf 20191228
         */
//        this.setSelectOptionToModel(model, DictCode.有效状态);
        return super.init(model);
    }

    /**
     * 查询页面查询
     *@return Page<FinProductPayInt001Dto>泛型的page实体类
     * @throws Exception 异常
     */
    @RequestMapping("findForPageAll")
    @ResponseBody
    public Page<FinProductPlanning001Dto> findForPageAll() throws Exception {
        return finProductPlanningEstablish001Service.findPageAll(RequestUtil.getPageParam());
    }

    /**
     * 产品系列名称下拉
     * @throws Exception 异常
     *  @return 下拉框列表
     */
    @RequestMapping("querySeriesName")
    @ResponseBody
    public Page<SelectOptionDto> querySeriesName()
            throws Exception {
        Page<SelectOptionDto> selectOptionDtoPage = finProductPlanningEstablish001Service.querySeriesName(RequestUtil.getPageParam());
        return   selectOptionDtoPage;
    }
    /**
     * 删除、修改按钮点击时的check
     *
     * @param dto 参数
     * @param result 校验结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) FinProductPlanning001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 導出
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return finProductPlanningEstablish001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }

    /**
     * 删除操作
     * @param dto
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/delete")
    public AjaxResult deleteByPrimaryKey(@RequestBody FinProductPlanning001Dto dto)
            throws Exception{
        dto.setOperateType(GlobalConstant.OPERATE_TYPE_DELETE);
        ProcessParameter<FinProductPlanning002Dto> processParameter = new ProcessParameter<FinProductPlanning002Dto>();
        WorkFlowParameter<FinProductPlanning002Dto> params = new WorkFlowParameter<FinProductPlanning002Dto>();
        params.setElementId(dto.getElementId());
        params.setPageId("FinProductPlanningEstablish002");
        params.setBusinessCode(dto.getPlanningNo());
        params.setBusinessType(DictCodeListExt3.共通业务类型_产品规划信息维护.getValue());
        // 设置工作流注解
        params.setComment(
                entity -> this.getMessage("AssetPlanNewsDefend")
                        + "(" + entity.getPlanningNo() + ")"
                + "产品规划编号" +"(" +dto.getPlanningSeriesNo() + ")"
        );
        processParameter.setWorkFlowParameter(params);
        FinProductPlanning002Dto dto1 = finProductPlanningEstablish002Service.getEntityByNo(dto.getPlanningNo());
        dto1.setOperateType(GlobalConstant.OPERATE_TYPE_DELETE);
        // 调用工作流事件
        String msg = finProductPlanningEstablish002Service.start(processParameter, dto1);
        // 返回工作流错误信息
        return new AjaxResult(msg);
    }
}
