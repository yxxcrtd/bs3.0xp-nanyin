/*
 * 文件名：FinOpenPlanMaintenance001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.maintenance.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.*;
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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.messageadjust.maintenance.dto.FinOpenPlanMaintenanceDto;
import com.joyintech.tams.invest.product.messageadjust.maintenance.service.FinOpenPlanMaintenanceService;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 开放计划调整
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月22日
 * @see FinOpenPlanMaintenance001Controller
 * @since 1.0
 */
@Controller
@PageId("FinOpenPlanMaintenance001")
@RequestMapping("/invest/product/messageadjust/maintenance/FinOpenPlanMaintenance001")
public class FinOpenPlanMaintenance001Controller extends BaseController{
    /**
     * finOpenPlanMaintenanceService
     */
    @Autowired
    private FinOpenPlanMaintenanceService finOpenPlanMaintenanceService;
    /**
     * 业务标号生成共通
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
    @Override
    public String init(Model model) {
        // 下拉框初始化
        setSelectOptionToModel(model, DictCode.计划类型);
        setSelectOptionToModel(model, DictCode.发送状态);
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("DISPOSE_PATTERN_05", BussinessDictCodeList.处理模式_开放阶梯.getValue());
        model.addAttribute("SCHEDULE_TYPE_01", DictCodeListExt2.计划类型_申购计划.getValue());
        model.addAttribute("SCHEDULE_TYPE_02",DictCodeListExt2.计划类型_赎回计划.getValue());
        model.addAttribute("PROFIT_TYPE_01",DictCodeList.收益类型_净值型.getValue());
        model.addAttribute("RANSOM_ISSUE_MODE_03", DictCodeList.申赎开放类型_申赎开放.getValue());
        
        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            FinOpenPlanMaintenanceDto dto = finOpenPlanMaintenanceService.getBussinessEntityByWorkflowId(
                workflowId);
            dto.setWorkflowId(workflowId);
            String finprodMarketId = finOpenPlanMaintenanceService.getFinprodMarketIdByFinprodId(dto.getFinprodId());
            dto.setFinprodMarketId(finprodMarketId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("scheduletype", dto.getOpenType());
        }
        // 画面元素是否显示
        PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);

        model.addAttribute("isEleShow", isEleShow);
        //平台日
        model.addAttribute("SESSION_SYS_DATE",DateUtil.getSysDate());
        return super.init(model);
    }
    /**
     * 产品代码联想下拉框
     * @return List<SelectOptionDto>
     */
    @ResponseBody
    @RequestMapping("/getFinprodId")
    public List<SelectOptionDto> getProdId() {
        return finOpenPlanMaintenanceService.getFinprodIds(RequestUtil.getPageParam());
    }
    /**
     * 
     * 根据产品代码查询数据
     * <功能详细描述>
     * @return Page<FinPayPlanAdjustmentDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public List<FinOpenPlanMaintenanceDto> selectForPage() throws Exception{
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        return finOpenPlanMaintenanceService.selectForPage(param);
    }
    /**
     * 保存按钮点击时的check
     *
     * @param dto 业务实体
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) FinOpenPlanMaintenanceDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto 业务实体
     * @param br AjaxResult
     * @return AjaxResul
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) FinOpenPlanMaintenanceDto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<FinOpenPlanMaintenanceDto> processParameter = new ProcessParameter<FinOpenPlanMaintenanceDto>();
        WorkFlowParameter<FinOpenPlanMaintenanceDto> params = new WorkFlowParameter<FinOpenPlanMaintenanceDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        String branch = CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE);
        dto.setBranch(Long.parseLong(branch));
        // 业务编号
        params.setBusinessCode(entity -> {
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessType(DictCodeList.共通业务类型_开放计划调整);
            String code = baseGetBusinessId != null ? baseGetBusinessId.buildBussinessCode(
                param) : IDGenerator.generate();
            return code;
        });
        params.setBusinessType(DictCodeList.共通业务类型_开放计划调整.getValue());
        // 业务说明："产品代码" + "(" + 画面.产品代码 + ")" + "-" + "开放计划维护"
        params.setComment(entity -> this.getMessage("FinOpenPlanMaintenance001.finprodId") + "("
                                    + entity.getFinprodId() + ")"
                                    + this.getMessage("FinOpenPlanMaintenance001"));
        processParameter.setWorkFlowParameter(params);
        String msg = finOpenPlanMaintenanceService.start(processParameter, dto);
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
        return finOpenPlanMaintenanceService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
    /**
     * 发送销售接口状态
     *
     * @param FinMdateAdjustment001Dto
     * @return 发送结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("sendOpenPlanSale")
    public AjaxResult sendOpenPlanSale(@RequestBody FinOpenPlanMaintenanceDto finOpenPlanMaintenanceDto) {
        //或许选择推送的数据，循环调用推送销售系统方法
        List<FinOpenPlanMaintenanceDto> dtoList = finOpenPlanMaintenanceDto.getSelectedItem() ;
        AjaxResult ar = new AjaxResult() ;
        if(dtoList.size() > 0){
            List<ValidMessage> errorList = ar.getErrorList();
            boolean sendFlag = true ;// 默认状态为推送销售系统
            for (FinOpenPlanMaintenanceDto fin001Dto : dtoList) {
                // 查询是否已经推送销售系统
                if(DictCodeList.发送状态_发送成功.getValue().equals(fin001Dto.getSendStatus())){
                    errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032","未发送和发送失败")));
                    sendFlag = false ;
                    return ar ;
                }
            }
            if (sendFlag) {
                // 推送产品分红至销售系统
                Map<String, String> map = finOpenPlanMaintenanceService.sendOpenPlanSale(dtoList);
                errorList.add(new ValidMessage(map.get("msg")));
            }
        }
        return ar;
    }
}
