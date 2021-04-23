package com.joyintech.tams.invest.basicdata.standardcouponreconversionrate.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.basicdata.standardcouponreconversionrate.dto.StandardCouponReConversionRateDto;
import com.joyintech.tams.invest.basicdata.standardcouponreconversionrate.service.StandardCouponReConversionRateService;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PageId("StandardCouponReConversionRate002")
@RequestMapping("/invest/basicdata/standardcouponreconversionrate/StandardCouponReConversionRate002")
public class StandardCouponReConversionRate002Controller extends BaseController {

    @Autowired
    private StandardCouponReConversionRateService standardCouponReConversionRateService;

    /**
     *
     * 查询页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, new DictCode[] {DictCode.资金账户类型,DictCode.申报单位,DictCode.特殊处理业务,
                DictCode.柜台接口类型,DictCode.是或否});
        List<SelectOptionDto> brokerIds = new ArrayList<SelectOptionDto> ();
        List<SelectOptionDto> accountTypeList = new ArrayList<SelectOptionDto> ();

//        SelectOptionDto aop = new SelectOptionDto ();
//        aop.setId ("00000000000000000");
//        aop.setText ("所有");
        try {
            brokerIds = getMstCustomerInfo();
//            brokerIds.add (0,aop);
            model.addAttribute("brokerIds", JsonUtils.objectArray2Json(brokerIds.toArray()));

            accountTypeList = getPtlSecAcctInfo();
//            accountTypeList.add (0,aop);
            model.addAttribute("accountTypeList", JsonUtils.objectArray2Json(accountTypeList.toArray()));

        } catch (Exception e) {
            e.printStackTrace();
        }

        String brokerId = getParam("brokerId");
        String accountType = getParam("accountType");
        String workflowId = getParam("workflowId");

        // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(brokerId) && StringUtil.isNotEmpty(accountType)) {
            // 根据主键获取业务实体
            StandardCouponReConversionRateDto dto = new StandardCouponReConversionRateDto();
            dto.setBrokerId (brokerId);
            dto.setAccountType (accountType);
            StandardCouponReConversionRateDto entity = standardCouponReConversionRateService.selectByPrimaryKey(dto);
            model.addAttribute("entity", entity);
        }
        // 来自代办
        else if (StringUtil.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            StandardCouponReConversionRateDto entity = standardCouponReConversionRateService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", entity);
            model.addAttribute("workflowId", workflowId);
        }

        return super.init(model);
    }


    /**
     * 券商下拉框
     * @return
     * @throws Exception
     */
    public List<SelectOptionDto> getMstCustomerInfo()
            throws Exception {
        String param = super.getRequest().getParameter("param");
        Map<String, Object> map = new HashMap<String, Object> ();
        if(StringUtils.isNotEmpty(param)){
            map.put("brokerId", param);
        }

        List<SelectOptionDto> temp = new ArrayList<SelectOptionDto> ();
        SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.所有, null);
        for (SelectOptionDto dto : selectDto1.getOptions()) {
            temp.add (dto);
        }
        List<SelectOptionDto> tempList = standardCouponReConversionRateService.getMstCustomerInfo(map);
        for (int i = 0; i < tempList.size (); i++){
            temp.add (tempList.get (i));
        }
        return temp;
    }

    public List<SelectOptionDto> getPtlSecAcctInfo(){
        String param = super.getRequest().getParameter("param");
        Map<String, Object> map = new HashMap<String, Object> ();
        if(StringUtils.isNotEmpty(param)){
            map.put("accountType", param);
        }

        List<SelectOptionDto> temp = new ArrayList<SelectOptionDto> ();
        SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.所有, null);
        for (SelectOptionDto dto : selectDto1.getOptions()) {
            temp.add (dto);
        }
        List<SelectOptionDto> tempList = standardCouponReConversionRateService.getPtlSecAcctInfo(map);
        for (int i = 0; i < tempList.size (); i++){
            temp.add (tempList.get (i));
        }

        return temp;
    }


    /**
     * 保存按钮点击
     *
     * @param dto dto
     * @param result BindingResult
     * @throws Exception Exception
     * @return check结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings("deprecation")
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) StandardCouponReConversionRateDto dto, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult();
        }

        ProcessParameter<StandardCouponReConversionRateDto> processParameter =
                new ProcessParameter<StandardCouponReConversionRateDto>();
        WorkFlowParameter<StandardCouponReConversionRateDto> params =
                new WorkFlowParameter<StandardCouponReConversionRateDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getAccountType ()+ dto.getBrokerId ());
        params.setBusinessType(DictCodeListExt3.共通业务类型_标准券再折算率.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> SpringContextUtil.getTextValue("资金账户类型") + "("+ entity.getAccountType () + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = standardCouponReConversionRateService.start (processParameter,dto);
        return new AjaxResult(msg);

    }




    /**
     * 保存按钮点击时的check
     *
     * @param dto
     *            dto
     * @param result
     *            BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) StandardCouponReConversionRateDto dto,
                            BindingResult result) {
        if (result.getAllErrors().isEmpty()) {
            AjaxResult ar = commonCheck(result, dto.getOperateType());
            return ar;
        }
        return commonCheck(result, dto.getOperateType());
    }


    @ResponseBody
    @RequestMapping("/brokerIdChange")
    public List<SelectOptionDto> brokerIdChange()
            throws Exception {
        return standardCouponReConversionRateService.brokerIdChange(RequestUtil.getParameters(getRequest()));
    }


}
