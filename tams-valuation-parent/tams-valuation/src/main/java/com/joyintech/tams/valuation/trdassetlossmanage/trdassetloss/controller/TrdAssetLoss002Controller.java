/*
 * 文件名：ValSubjectSet001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.trdassetlossmanage.trdassetloss.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.trdassetlossmanage.trdassetloss.dto.TrdAssetLoss001Dto;
import com.joyintech.tams.valuation.trdassetlossmanage.trdassetloss.service.TrdAssetLossService;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 估值核算-财务管理-资产减值管理--新建
 *
 * @author lby
 * @version 1.0 2020年10月10日
 * @see TrdAssetLoss002Controller
 * @since 1.0
 */
@Controller
@PageId("TrdAssetLoss002")
@RequestMapping("/valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss002")
public class TrdAssetLoss002Controller extends BaseController {
    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private InterfCommonService interfCommonService;

    @Autowired
    private TrdAssetLossService trdAssetLossService;
    /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * 初始化001查询页面
     */
    @Override
    public String init(Model model) {
        Date sysDate = DateUtil.platDateFromCache();
        this.setSelectOptionToModel(model, DictCode.减值阶段);
        this.setSelectOptionToModel(model,DictCode.金融产品类型);
        this.setSelectOptionToModel(model, DictCode.减值测算方式);
        this.setSelectOptionToModel(model, DictCode.减值录入方式);
        model.addAttribute("outValue", DictCodeListExt3.减值测算方式_外部评估.getValue());
        model.addAttribute("expectedCreditLossModel", DictCodeListExt3.减值测算方式_预期信用损失模型.getValue());
        model.addAttribute("rateLossValue", DictCodeListExt3.减值录入方式_按比例减值.getValue());
        model.addAttribute("SYS_DATE", DateUtil.format(DateUtil.platDateFromCache()));

        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE") == null ? "01" : CacheUtil.getSysParam("IS_SHOW_TYPE"));
        String finprodId = getParam("finprodId");
        String tradeId = getParam("tradeId");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            TrdAssetLoss001Dto dto = trdAssetLossService.getBussinessEntityByWorkflowId(
                    workflowId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("businessId",workflowId);
        }else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(tradeId)) {
            TrdAssetLoss001Dto dto = trdAssetLossService.selectByKey(tradeId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("tradeId", dto.getTradeId());
            model.addAttribute("businessId",tradeId);
        }
        //新建初始化
        if (WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(tradeId)) {
            TrdAssetLoss001Dto entity = new TrdAssetLoss001Dto();
            entity.setvDate(sysDate);
            entity.setmDate(sysDate);
            entity.setValDate(sysDate);
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }
        return super.init(model);
    }

    /**
     * 保存按钮点击时的check
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
            ValidateGroupEdit.class}) TrdAssetLoss001Dto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        if (StringUtil.isNotEmpty(dto) && StringUtil.isNotEmpty(dto.getFinprodId())){
            WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
            wflValidInputParameter.setPageId("TrdAssetLoss002");
            wflValidInputParameter.setBusinessDataPk1(dto.getFinprodId());
            if (WorkFlowUtil.isNotFromToDoList() && mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
                ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                errorList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0041")));
                AjaxResult ar = new AjaxResult();
                ar.setErrorList(errorList);
                return ar;
            }
        }
        AjaxResult ajaxResult = new AjaxResult();
        if(StringUtil.equals(dto.getOperateType(), "save") || StringUtil.equals(dto.getOperateType(), "submit")){
            List<ValidMessage> errorList = new ArrayList<>();
            if (!StringUtil.isNotEmpty(dto.getFinprodId())){
                errorList.add(new ValidMessage("finprodId","资产名称不能为空"));
            }

            if (StringUtil.isNotEmpty(dto.getStrDate()) && StringUtil.isNotEmpty(dto.getEndDate())) {
                if (dto.getStrDate().compareTo(dto.getEndDate()) > 0) {
                    errorList.add(new ValidMessage("endDate", "初始确认起始日不能大于初始确认截止日"));
                } else {
                    if (!(dto.getStrDate().compareTo(dto.getvDate()) >= 0 && dto.getStrDate().compareTo(dto.getmDate()) <= 0)) {
                        errorList.add(new ValidMessage("strDate", "初始确认起始日 需大于等于资产起息日,小于等于资产到期日"));
                    }
                    if (!(dto.getEndDate().compareTo(dto.getvDate()) >= 0 && dto.getEndDate().compareTo(dto.getmDate()) <= 0)) {
                        errorList.add(new ValidMessage("endDate", "初始确认截止日 需大于等于资产起息日,小于等于资产到期日"));
                    }
                }
            }
            if (StringUtil.isNotEmpty(dto.getFinprodId())
                    && StringUtil.isNotEmpty(dto.getValDate())
                    && StringUtil.isNotEmpty(dto.getPeroid())
                    && StringUtil.isNotEmpty(dto.getStrDate())
                    && StringUtil.isNotEmpty(dto.getEndDate())){
                Map<String, Object> param = new HashMap<>();
                param.put("finprodId",dto.getFinprodId());
                param.put("valDate", DateUtil.format(dto.getValDate()));
                List<TrdAssetLoss001Dto> tmpLsit1 = trdAssetLossService.selectByFinprodIdAndValDate(param);
                if (!StringUtil.isEmptyList(tmpLsit1)){
                    TrdAssetLoss001Dto loss001Dto = tmpLsit1.get(0);
                    if (!StringUtil.equals(loss001Dto.getLossTestType(),dto.getLossTestType())){
                        errorList.add(new ValidMessage("finprodId","同一资产同一行情日不能维护两种减值测算方式"));
                    }
                }
                param.put("peroid", dto.getPeroid());
                param.put("strDate", DateUtil.format(dto.getStrDate()));
                List<TrdAssetLoss001Dto> tmpLsit2 = trdAssetLossService.selectByFinprodIdAndValDate(param);
                if (!StringUtil.isEmptyList(tmpLsit2)){
                    errorList.add(new ValidMessage("strDate","初始确认日不能出现在相同资产相同行情日相同代偿期的初始确认日到截止日区间中"));
                }
                param.remove("strDate");
                param.put("endDate", DateUtil.format(dto.getEndDate()));
                List<TrdAssetLoss001Dto> tmpLsit3 = trdAssetLossService.selectByFinprodIdAndValDate(param);
                if (!StringUtil.isEmptyList(tmpLsit3)){
                    errorList.add(new ValidMessage("endDate","初始确认截止日不能出现在相同资产相同行情日相同代偿期的初始确认日到截止日区间中"));
                }
                param.put("strDate", DateUtil.format(dto.getStrDate()));
                List<TrdAssetLoss001Dto> tmpLsit4 = trdAssetLossService.selectRepeatByFinprodIdAndValDate(param);
                if (!StringUtil.isEmptyList(tmpLsit4)) {
                    errorList.add(new ValidMessage("endDate","新建减值流水的初始确认日与初始确认截止日的区间，不能包含相同资产、相同行情日、相同代偿期的有效流水的初始确认日到截止日区间"));
                }
            }
            if (!StringUtil.isNotEmpty(dto.getAssetType1())){
                errorList.add(new ValidMessage("assetType1","资产类型不能为空"));
            }
            if (!StringUtil.isNotEmpty(dto.getvDate())){
                errorList.add(new ValidMessage("vDate","资产起息日不能为空"));
            }
            if (!StringUtil.isNotEmpty(dto.getmDate())){
                errorList.add(new ValidMessage("mDate","资产到期日不能为空"));
            }
            if (!StringUtil.isNotEmpty(dto.getValDate())){
                errorList.add(new ValidMessage("valDate","减值行情日不能为空"));
            }
            if (!StringUtil.isNotEmpty(dto.getStrDate())){
                errorList.add(new ValidMessage("strDate","初始确认起始日不能为空"));
            }
            if (!StringUtil.isNotEmpty(dto.getEndDate())){
                errorList.add(new ValidMessage("endDate","初始确认截止日不能为空"));
            }
            if (!StringUtil.isNotEmpty(dto.getStage())){
                errorList.add(new ValidMessage("stage","减值阶段不能为空"));
            }
            if (!StringUtil.isNotEmpty(dto.getPeroid())){
                errorList.add(new ValidMessage("peroid","待偿期（年）不能为空"));
            }
            if (StringUtil.isNotEmpty(dto.getValDate())) {
                if (dto.getValDate().compareTo(DateUtil.parse(DateUtil.getSysDate())) != 0) {
                    errorList.add(new ValidMessage("valDate", "减值行情日必须等于平台日"));
                }
            }
            if (!StringUtil.isNotEmpty(dto.getLossTestType())){
                errorList.add(new ValidMessage("lossTestType","减值测算方式不能为空"));
            }
            if (StringUtil.isNotEmpty(dto.getLossInputPattern()) && StringUtil.equals(dto.getLossInputPattern(),DictCodeListExt3.减值录入方式_按比例减值.getValue())){
                if (!StringUtil.isNotEmpty(dto.getLossRatio())){
                    errorList.add(new ValidMessage("lossRatio","减值比例%不能为空"));
                }else {
                    if (!(dto.getLossRatio().compareTo(BigDecimal.ZERO) >= 0 && dto.getLossRatio().compareTo(new BigDecimal(100)) < 0)) {
                        errorList.add(new ValidMessage("lossRatio","减值比例% 应该大于等于0,小于100"));
                    }
                    String lossRatio = String.valueOf(dto.getLossRatio());
                    if (!isNumber(lossRatio)){
                        errorList.add(new ValidMessage("lossRatio","减值比例% 不能超过12位小数"));
                    }
                }
            }
            if (StringUtil.isNotEmpty(dto.getLossTestType()) && StringUtil.equals(dto.getLossTestType(),DictCodeListExt3.减值测算方式_预期信用损失模型.getValue())){
                if (!StringUtil.isNotEmpty(dto.getPdValue())){
                    errorList.add(new ValidMessage("pdValue","PD违约概率% 不能为空"));
                }
                if (!StringUtil.isNotEmpty(dto.getLgdValue())){
                    errorList.add(new ValidMessage("lgdValue","LGD违约损失率% 不能为空"));
                }
                if (!StringUtil.isNotEmpty(dto.getEclVaule())){
                    errorList.add(new ValidMessage("eclVaule","ECL预期信用损失比例% 不能为空"));
                }
            }
            if (StringUtil.isNotEmpty(dto.getPdValue())) {
                if (!(dto.getPdValue().compareTo(BigDecimal.ZERO) >= 0 && dto.getPdValue().compareTo(new BigDecimal(100)) < 0)) {
                    errorList.add(new ValidMessage("pdValue","PD违约概率% 应该大于等于0,小于100"));
                }
                String pdValue = String.valueOf(dto.getPdValue());
                if (!isNumber(pdValue)){
                    errorList.add(new ValidMessage("pdValue","PD违约概率% 不能超过12位小数"));
                }
            }
            if (StringUtil.isNotEmpty(dto.getLgdValue())) {
                if (!(dto.getLgdValue().compareTo(BigDecimal.ZERO) >= 0 && dto.getLgdValue().compareTo(new BigDecimal(100)) < 0)) {
                    errorList.add(new ValidMessage("lgdValue","LGD违约损失率% 应该大于等于0,小于100"));
                }
                String lgdValue = String.valueOf(dto.getLgdValue());
                if (!isNumber(lgdValue)){
                    errorList.add(new ValidMessage("lgdValue","LGD违约损失率% 不能超过12位小数"));
                }
            }
            if (errorList.size() > 0) {
                ajaxResult.setErrorList(errorList);
                return ajaxResult;
            }
        }
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 保存/提交
     *
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody TrdAssetLoss001Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }

        if(!StringUtil.isNotEmpty(dto.getTradeId())){
            String tradeId = interfCommonService !=null ? interfCommonService.getTradeid("TEDLOSS", dto.getValDate(), "A"): IDGenerator.generate();
            dto.setTradeId(tradeId);
            //dto.setStatus();
        }

        ProcessParameter<TrdAssetLoss001Dto> processParameter = new ProcessParameter<TrdAssetLoss001Dto>();
        WorkFlowParameter<TrdAssetLoss001Dto> params = new WorkFlowParameter<TrdAssetLoss001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(TrdAssetLoss001Dto::getFinprodId);
        params.setBusinessType(DictCodeListExt3.共通业务类型_资产减值管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：“交易代码：” + 上述取得的交易代码+“业务类型”+“交易日：”+画面.平台日
        params.setComment(
                entity -> "交易代码："+entity.getTradeId()+"业务类型："+DictCodeListExt3.共通业务类型_资产减值管理.getCodeName()+"交易日："+DateUtil.format(entity.getValDate()));

        processParameter.setWorkFlowParameter(params);
        String msg = trdAssetLossService.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     *
     * 资产信息检索
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getAssetInfo")
    public TrdAssetLoss001Dto getAssetInfo(@RequestBody Map<String, Object> param){
        return trdAssetLossService.getAssetInfo(param);
    }

    /**
     *
     * 检索资产行情日
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getAssetExerciseDate")
    public TrdAssetLoss001Dto getAssetExerciseDate(@RequestBody Map<String, Object> param){
        return trdAssetLossService.getAssetExerciseDate(param);
    }

    private boolean isNumber(String str) {
        //首先判断是否为浮点数
        Matcher match = Pattern.compile("^(-?\\d+)(\\.\\d+)?$").matcher(str);
        if (!match.matches()){
            return match.matches();
        }
        // 判断小数点后12位的数字的正则表达式
        Matcher match2 = Pattern.compile("^(([1-9\\-]{1}\\d*)|([0]{1}))(\\.(\\d){0,12})?$").matcher(str);
        return match2.matches();
    }
}
