/*
 * 文件名：DisProdPlanManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.dto.ProdBonusplanDto;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.service.ProdBonusplanManager001Service;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.service.ProdBonusplanManager002Service;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdInfoDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 报表模板管理新建
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author longhao
 * @version 1.0 2018年11月9日
 * @see ProdBonusplanManager002Controller
 * @since 1.0
 */
@Controller
@PageId("ProdBonusplanManager002")
@RequestMapping("/disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager002")
public class ProdBonusplanManager002Controller extends BaseController {

    @Autowired
    ProdBonusplanManager002Service prodBonusplanManager002Service;


    @Autowired
    ProdBonusplanManager001Service prodBonusplanManager001Service;

    @Autowired
    private GetPrdInfoSelectService getPrdInfoSelectService;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ProdBonusplanManager002Controller.class);

    @Override
    public String init(Model model) {

        this.setSelectOptionToModel(model, DictCode.信披_数据状态ED);
        this.setSelectOptionToModel(model, DictCode.信披_分红方式);
        String workflowId = getParam("workflowId");

        String id = this.getParam("bonusPlanId");

        String editFlag = getParam("editFlag");
        model.addAttribute("editFlag", editFlag);
        // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(id)) {
            // 根据主键获取业务实体
            model.addAttribute("entity", prodBonusplanManager002Service.getProdBonusplanDto(id));
        }
        // 来自代办 现在是不走流程
        else if (StringUtil.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            ProdBonusplanDto entity = prodBonusplanManager002Service.getBussinessEntityByWorkflowId(workflowId);
            if (StringUtil.equals(entity.getOperateType(),"submit")){
                String prodId = entity.getProdId();
                String prodName =entity.getProdName();
                entity.setProdName(prodId);
                entity.setProdId(prodName);
            }
            model.addAttribute("entity", entity);
            model.addAttribute("workflowId", workflowId);
        }

        return super.init(model);
    }

    /**
     * 分页查询产品下拉框
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("getPrdInfoSelect")
    @ResponseBody
    public Page<SelectOptionDto> getPrdInfoSelect() {

        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("sonFlag", "2");
        Page<SelectOptionDto> prdInfoSelect = getPrdInfoSelectService.getAllPrdInfoSelect(param);
        return prdInfoSelect;
    }

    /**
     * 分页查询产品下拉框
     *
     * @return
     * @throws Exception
     */
//    @RequestMapping("getPrdInfoSelect2")
//    @ResponseBody
//    public Page<SelectOptionDto> getPrdInfoSelect2() {
//
//        Map<String, Object> param = RequestUtil.getPageParam();
//        param.put("sonFlag", "2");
//        Page<SelectOptionDto> prdInfoSelect = getPrdInfoSelectService.getAllPrdInfoSelect2(param);
//        return prdInfoSelect;
//    }

    @RequestMapping("getPrdInfoSelect2")
    @ResponseBody
    public List<SelectOptionDto> getPrdInfoSelect2() {

        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("sonFlag", "2");
        return prodBonusplanManager002Service.getAllPrdInfoSelect2(param);
    }
    /**
     * check
     *
     * @param prodId 查询产品明细信息
     * @return 结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getPrdInfo")
    public RepProdInfo getPrdInfo(String prodId) {

        return prodBonusplanManager002Service.getPrdInfo(prodId);
    }


    /**
     * check
     *
     * @param dto 待校验数据
     * @return 结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/checkSave")
    public AjaxResult checkSave(@RequestBody ProdBonusplanDto dto) {
        AjaxResult result = prodBonusplanManager002Service.check(dto);
        if (result.getErrorList().isEmpty()) {
            result.setData(this.getMessage("DIS_COMMON_C_0001", this.getMessage("SysCommon.save")));
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid ProdBonusplanDto dto, BindingResult bindingResult) {
        String operateType = dto.getOperateType();
        String pageModel = dto.getPageModel();
        if (StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {//新增
            ProdBonusplanDto existDto = prodBonusplanManager001Service.selectByProdId(dto.getProdName());
            if(StringUtils.equals(DictCodeListExt3.信披_分红方式_到期兑付.getValue(),dto.getBonusMode())
                && existDto !=null ){
                return new AjaxResult("当分红方式选择到期兑付时，一个子产品代码只能有一条数据", MessageType.错误消息);
            }
            dto.setProdId(dto.getProdName());
            dto.setBonusPlanId(dto.getBonusMode());
            ProdBonusplanDto existDto2 = prodBonusplanManager002Service.selectByCondition(dto);
            if(existDto2 != null){
                return new AjaxResult("“子产品代码+收益分配基准日”不得同时与已有数据重复", MessageType.错误消息);
            }
        }
        if(StringUtil.isNotEmpty(dto.getEditFlag())){
            if(prodBonusplanManager001Service.isInWorkflow(dto.getProdId(), DateUtil.format(dto.getSettDate(),"yyyy/MM/dd"))){
                return new AjaxResult("所选对象中存在流程中的数据！", MessageType.错误消息);
            }
        }else {
            dto.setSettDateStr(DateUtil.format(dto.getSettDate(),"yyyy/MM/dd"));
        }
        return commonCheck(bindingResult, operateType);
    }


    /**
     * 保存 <一句话功能简述> <功能详细描述>
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("insert")
    @ResponseBody
    public AjaxResult insert() throws IOException {
        AjaxResult result = new AjaxResult();
        List<ValidMessage> checkList = new ArrayList<ValidMessage>();
        String datas = this.getParam("object");
        String addParam = this.getParam("addParam");
        ProdBonusplanDto dto = JsonUtils.json2Object(datas, ProdBonusplanDto.class);
        try {
            if (this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                dto.setDataStatus(DictCodeListExt3.信披_数据状态_有效E.getValue());
                dto.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
                prodBonusplanManager002Service.insert(dto);
            }
            if (this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                prodBonusplanManager002Service.update(dto);
            }
        } catch (Exception e) {
            logger.error("error:", e);
            result = new AjaxResult("保存失败", MessageType.错误消息);
            return result;
        }
        if (null != checkList && checkList.size() != 0) {
            result.setErrorList(checkList);
        } else {
            result = new AjaxResult("保存成功", MessageType.提示消息);
        }
        return result;
    }


    /**
     * 数据保存操作
     *
     * @param dto 请求实体类
     */
    @ResponseBody
    @RequestMapping(OPERATE_TYPE_SAVE)
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) ProdBonusplanDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 新建留痕表参数和工作流参数
        dto.setSettDateStr(DateUtil.format(dto.getSettDate(),"yyyy/MM/dd"));
        ProcessParameter<ProdBonusplanDto> processParameter = new ProcessParameter<ProdBonusplanDto>();
        WorkFlowParameter<ProdBonusplanDto> params = new WorkFlowParameter<ProdBonusplanDto>();
        params.setPageId("ProdBonusplanManager002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getProdName());
        params.setBusinessType(DictCodeListExt4.共通业务类型_分红表单维护.getValue());
        params.setComment(entity -> "分红计划编号-产品代码" + "(" + entity.getProdName() + ")");
        // 塞入工作流参数
        processParameter.setWorkFlowParameter(params);

        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();// 获取平台日期
        dto.setCreateTime(sysDate);
        dto.setUpdateTime(sysDate);
        dto.setCreateUser(CommonUtil.getUserName());
        dto.setUpdateUser(CommonUtil.getUserName());

        // 流程开始
        String msg = prodBonusplanManager002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }


}
