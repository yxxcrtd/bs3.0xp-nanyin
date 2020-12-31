package com.joyintech.tams.valuation.accounadjustmanage.assetbook.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.dto.ValAssetBook006Dto;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook006Service;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * add by yangwenjun 20201130 账套参数修改功能
 */
@Controller
@PageId("ValAssetBook006")
@RequestMapping("/valuation/accounadjustmanage/assetbook/ValAssetBook006")
public class ValAssetBook006Controller extends BaseController {

    @Autowired
    private ValAssetBook006Service valAssetBook006Service;

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public String init(Model model) {
        model.addAttribute("modifyFlag", this.getParam("modifyFlag"));
        model.addAttribute("booksetId", this.getParam("booksetId"));
        String workflowId = getParam("workflowId");
        if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
            model.addAttribute("workflowId", workflowId);
        }
        return super.init(model);
    }

    /**
     * 参数代码联想下拉框
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getParamCode")
    public List<SuggestDto> getParamCode() {
        Map<String, Object> para = RequestUtil.getPageParam();
        Map<String, String> map = new HashMap<String, String>();
        map.put("param", (String)para.get("param"));
        map.put("booksetId",(String)para.get("booksetId"));
        return valAssetBook006Service.getParamCode(map);
    }

    @ResponseBody
    @RequestMapping("findPage")
    public Page<ValAssetBook006Dto> findPage() {
        return valAssetBook006Service.findPage(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) ValAssetBook006Dto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<ValAssetBook006Dto> processParameter = new ProcessParameter<ValAssetBook006Dto>();
        WorkFlowParameter<ValAssetBook006Dto> params = new WorkFlowParameter<ValAssetBook006Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getBooksetId());
        params.setBusinessType(DictCodeList.共通业务类型_账套管理.getValue());
        params.setComment(entity -> "账套代码"+ "(" + entity.getBooksetId()+")" +  "：参数修改" );
        processParameter.setWorkFlowParameter(params);
        String msg = valAssetBook006Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) ValAssetBook006Dto dto, BindingResult result) {
        if (result.getErrorCount() == 0) {
            if("02".equals(CacheUtil.getSysParam("AUTHORITY_TYPE"))&&!"01".equals(CommonUtil.getInsitutionTypeByUserName(LoginUserUtil.getUserName()))){
                return new AjaxResult("仅省级机构能修改账套模板！", MessageType.错误消息);
            }
            WflValidInputParameter parameter = new WflValidInputParameter();
            parameter.setPageId("ValAssetBook006");
            parameter.setBusinessDataPk1(dto.getBooksetId());
            if (mstWorkflowCommonService.isInWflProcess(parameter)) {
                ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();
                errorList.add(new ValidMessage(this.getMessage("所选对象存在流程中的数据")));
                AjaxResult ar = new AjaxResult();
                ar.setErrorList(errorList);
                return ar;
            }
        }

        return commonCheck(result, "edit");
    }

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return valAssetBook006Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
    }
}
