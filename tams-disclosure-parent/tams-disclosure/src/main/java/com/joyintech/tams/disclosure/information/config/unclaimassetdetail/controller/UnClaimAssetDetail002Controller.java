package com.joyintech.tams.disclosure.information.config.unclaimassetdetail.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.information.config.hugeinfopro.dto.HugeInfoPro001Dto;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dto.UnClaimAssetDetailDto;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.service.UnClaimAssetDetail001Service;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.service.UnClaimAssetDetail002Service;
import com.joyintech.tams.systemtools.util.CommonUtil;
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

import java.text.ParseException;
import java.util.*;

@Controller
@PageId("UnClaimAssetDetail002")
@RequestMapping("/disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail002")
public class UnClaimAssetDetail002Controller extends BaseController {

    @Autowired
    private UnClaimAssetDetail001Service unClaimAssetDetail001Service;

    @Autowired
    private UnClaimAssetDetail002Service unClaimAssetDetail002Service;



    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, new DictCode[] {DictCode.是否});
        String workflowId = getParam("workflowId");
        String prodId = getParam("prodId");
        String effDate = getParam("effDateStr");
        String effEndDate = getParam("effEndDateStr");
        String dataStatus = getParam("dataStatus");
        String editFlag = getParam("editFlag");
        Map<String,Object> params = new HashMap<>();
        params.put("prodId",prodId);
        params.put("effDate",effDate);
        params.put("effEndDate",effEndDate);
        params.put("dataStatus",dataStatus);
        model.addAttribute("editFlag", editFlag);
        // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() &&  StringUtil.isNotEmpty(prodId)
                && StringUtil.isNotEmpty(effDate) && StringUtil.isNotEmpty(effEndDate)) {
            // 根据主产品代码prodId，生效日effDate，生效截止日期effEndDate，
            // 获取该产品在生效区间内所投非标资产明细列表
             List<UnClaimAssetDetailDto> unclaimList = unClaimAssetDetail002Service.selectDetailList(params);
            //新建画面DTO
             UnClaimAssetDetailDto dto = new UnClaimAssetDetailDto();
            //赋基本信息的初始值，这与所投非标资产的基本信息一致
            dto.setNostdAssetId(unclaimList.get(0).getNostdAssetId());
            dto.setProdId(unclaimList.get(0).getProdId());
            dto.setEffDate(unclaimList.get(0).getEffDate());
            dto.setEffEndDate(unclaimList.get(0).getEffEndDate());
            dto.setProdName(unclaimList.get(0).getProdName());
            dto.setDataStatus(unclaimList.get(0).getDataStatus());
            dto.setUnclaimList(unclaimList);
            dto.setEffDateO(unclaimList.get(0).getEffDate());
            dto.setEffEndDateO(unclaimList.get(0).getEffEndDate());
            model.addAttribute("unclaimList",unclaimList);
            model.addAttribute("entity", dto);
        }
        else if( !WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            UnClaimAssetDetailDto dto = unClaimAssetDetail002Service.getBussinessEntityByWorkflowId(workflowId);
            List<UnClaimAssetDetailDto> list = dto.getUnclaimList();
            model.addAttribute("unclaimList",list);
            model.addAttribute("entity", dto);

        }
        return super.init(model);
    }


    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) UnClaimAssetDetailDto dto, BindingResult result) {
        if(StringUtil.equals(dto.getEditFlag(),"01")){
            if(unClaimAssetDetail001Service.checkExistData(dto)){
                return new AjaxResult("系统已存在相同产品主代码+生效日+生效截止日的数据，不允许新建！", MessageType.错误消息);
            }
        }
        if(StringUtil.isNotEmpty(dto.getEditFlag())){
            if(unClaimAssetDetail001Service.isInWorkflow(dto)){
                return new AjaxResult("系统已存在相同产品主代码+生效日+生效截止日的流程中的数据！", MessageType.错误消息);
            }
        }

        if (unClaimAssetDetail001Service.checkOverlappingData(dto)){
            return new AjaxResult("生效区间重叠!", MessageType.错误消息);
		}

        if (result.hasErrors()) {
            return new AjaxResult(result);
        }
        return commonCheck(result, dto.getOperateType());
    }


    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) UnClaimAssetDetailDto dto, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult();
        }
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        ProcessParameter<UnClaimAssetDetailDto> processParameter = new ProcessParameter<UnClaimAssetDetailDto>();
        WorkFlowParameter<UnClaimAssetDetailDto> params = new WorkFlowParameter<UnClaimAssetDetailDto>();
//        String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator .generate();
       //
        if (StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW, (dto.getPageModel()))) {
            dto.setCreateTime(sysDate);
            dto.setUpdateTime(sysDate);
            dto.setCreateUser(CommonUtil.getUserName());
            dto.setUpdateUser(CommonUtil.getUserName());
            for (UnClaimAssetDetailDto unclaimDto:dto.getUnclaimList()) {
                if (unclaimDto.getNostdAssetId() == null || unclaimDto.getNostdAssetId().isEmpty()) {
                    unclaimDto.setNostdAssetId(IDGenerator.generate(36));
                }
                unclaimDto.setCreateTime(dto.getCreateTime());
//                DateUtil.parseDate(DateUtil.formatDateTime(dto.getUpdateTime()),"yyyy-MM-dd HH:mm:ss")
                unclaimDto.setUpdateTime(dto.getUpdateTime());
                unclaimDto.setCreateUser(CommonUtil.getUserName());
                unclaimDto.setUpdateUser(CommonUtil.getUserName());
                unclaimDto.setProdId(dto.getProdId());
                unclaimDto.setEffDate(dto.getEffDate());
                unclaimDto.setEffEndDate(dto.getEffEndDate());
                unclaimDto.setDataStatus(DictCodeListExt1.维护数据状态_有效.getValue());
                //数据来源
                unclaimDto.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
            }

        }
        //如果是编辑页面，只需要更新非标资产列表的更新时间和更新人
        if (StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT, (dto.getPageModel()))) {
            dto.setUpdateTime(sysDate);
            dto.setUpdateUser(CommonUtil.getUserName());
            for (UnClaimAssetDetailDto unclaimDto:dto.getUnclaimList()){
                unclaimDto.setProdId(dto.getProdId());
                unclaimDto.setEffDate(dto.getEffDate());
                unclaimDto.setEffEndDate(dto.getEffEndDate());
                unclaimDto.setUpdateTime(dto.getUpdateTime());
                unclaimDto.setUpdateUser(CommonUtil.getUserName());
            }
        }

        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getProdId());
        params.setBusinessType(DictCodeListExt4.共通业务类型_非标债权类资产明细维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> SpringContextUtil.getTextValue("产品主代码") + "(" + entity.getProdId() + ")" + "-"
                + SpringContextUtil.getTextValue("生效日") + "("+DateUtil.format(entity.getEffDate())+")" + "-"
                + SpringContextUtil.getTextValue("生效截止日期") + "("+DateUtil.format(entity.getEffEndDate())+")" );
        processParameter.setWorkFlowParameter(params);
        String msg = unClaimAssetDetail002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

//    @RequestMapping("selectDetail")
//    @ResponseBody
//    public UnClaimAssetDetailDto selectDetail() {
//        String nostdAssetId = this.getParam("nostdAssetId");
//        return unClaimAssetDetail002Service.selectDetail(nostdAssetId);
//    }



//      @RequestMapping("selectCount")
//      @ResponseBody
//      public Map<String, Integer>  selectCount(){
//        HashMap map = new HashMap();
//        map.put("count",unClaimAssetDetail002Service.selectCount());
//          return map;
//      }
}
