package com.joyintech.tams.disclosure.infoCompliance.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.system.main.dao.MainMapper;
import com.joyintech.tams.disclosure.busicommon.common.constants.DictCode;
import com.joyintech.tams.disclosure.infoCompliance.dto.InfoComplianceDto;
import com.joyintech.tams.disclosure.infoCompliance.service.InfoCompliance001Service;
import com.joyintech.tams.disclosure.infoCompliance.service.InfoCompliance002Service;
import com.joyintech.workflow.engine.controller.WorkFlowController;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PageId("InfoCompliance001")
@RequestMapping("/disclosure/infoCompliance/infoCompliance001")
public class InfoCompliance001Controller extends BaseController {

    @Autowired
    private InfoCompliance001Service infoCompliance001Service;

    @Autowired
    private InfoCompliance002Service infoCompliance002Service;


    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model,DictCode.信披类型);
        this.setSelectOptionToModel(model,DictCode.信批状态);
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("findPage")
    private Page<InfoComplianceDto> findPage(){
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        int page = Integer.parseInt(pageParam.get("page").toString());
        int length = Integer.parseInt(pageParam.get("length").toString());
        return infoCompliance001Service.findPage(page,length,pageParam);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody InfoComplianceDto dto, BindingResult result){
        String userName = LoginUserUtil.getUserName();

        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("seqno",dto.getSeqno());
        dto = infoCompliance002Service.queryInfoCompliance(paramMap);
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        if(!dto.getCreateUser().equals(userName)){
            errorList.add(new ValidMessage("只有创建人能删除!"));
        }
        if(StringUtil.isNotEmpty(dto.getInfoStatus())){
            errorList.add(new ValidMessage("只能删除未发起审批流程的数据!"));
        }
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setErrorList(errorList);
        if(errorList.isEmpty()){
            ajaxResult.setData("确认是否删除？");
        }
        return ajaxResult;
    }

    /***
     *  通过列表界面删除数据
     * */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody InfoComplianceDto dto, BindingResult result){
        AjaxResult ajaxResult = new AjaxResult();
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("seqno",dto.getSeqno());
        dto = infoCompliance002Service.queryInfoCompliance(paramMap);

        String procInstIid = infoCompliance002Service.selectProcInstIid(dto.getWorkflowId());
        WorkFlowUtil.deleteProcess(dto.getWorkflowId(), procInstIid);
        ajaxResult.setData("删除成功!");
        return ajaxResult;
    }

}
