/*
 * 文件名：DisProdPlanManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.repprodinfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdInfoDto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.service.ProdBasicChangeService;
import com.joyintech.tams.disclosure.information.config.repprodinfo.service.ProdInfoService;
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

/**
 * 报表模板管理新建
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author longhao
 * @version 1.0 2018年11月9日
 * @see ProdInfo002Controller
 * @since 1.0
 */
@Controller
@PageId("ProdInfo002")
@RequestMapping("/disclosure/information/config/repprodinfo/ProdInfo002")
public class ProdInfo002Controller extends BaseController {

    @Autowired
    ProdInfoService prodInfoService;

    @Autowired
    ProdBasicChangeService prodBasicChangeService;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ProdInfo002Controller.class);

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCodeExt1.信披_产品运作方式);
        this.setSelectOptionToModel(model, DictCodeExt1.信披_产品状态);
        this.setSelectOptionToModel(model, DictCodeExt1.信披_是否是分级产品);
        this.setSelectOptionToModel(model, DictCodeExt1.信披_募集方式);
        this.setSelectOptionToModel(model, DictCodeExt1.发行方式_信批);
        this.setSelectOptionToModel(model, DictCodeExt1.是否是指数型产品);
        this.setSelectOptionToModel(model, DictCodeExt1.是否是客户周期型产品);
        this.setSelectOptionToModel(model, DictCodeExt1.开放频率);
        this.setSelectOptionToModel(model, DictCodeExt1.信批产品类别);
        this.setSelectOptionToModel(model, DictCodeExt1.信披_产品类型);
        this.setSelectOptionToModel(model, DictCodeExt1.信披_投资性质);
        this.setSelectOptionToModel(model, DictCode.是或否);
        this.setSelectOptionToModel(model, DictCodeExt1.行情代码);


        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String id = this.getParam("prodId");
        String repProdType = this.getParam("repProdType");


        // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(id)) {
            // 根据主键获取业务实体
            if (StringUtils.equals(DictCodeListExt5.信批产品类别_普通产品.getValue(), repProdType)) {
                model.addAttribute("entity", prodInfoService.selectByCommonProdId(id));
            } else if (StringUtils.equals(DictCodeListExt5.信批产品类别_子产品.getValue(), repProdType)) {
                model.addAttribute("entity", prodInfoService.selectBySonProdId(id));
            } else {
                model.addAttribute("entity", prodInfoService.selectByProdId(id));
            }

            //业绩基准变更
            model.addAttribute("basicChangeList", prodBasicChangeService.selectListByProdId(id));

        }
        // 来自代办 现在是不走流程
        else if (StringUtil.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            ProdInfoDto entity = prodInfoService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", entity);
            model.addAttribute("workflowId", workflowId);
        }
        return super.init(model);
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
    public ProdInfoDto getPrdInfo(String prodId) {

        return prodInfoService.getPrdInfo(prodId);
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
    public AjaxResult checkSave(@RequestBody ProdInfoDto dto) {
        AjaxResult result = prodInfoService.check(dto);
        if (result.getErrorList().isEmpty()) {
            result.setData(this.getMessage("DIS_COMMON_C_0001", this.getMessage("SysCommon.save")));
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid ProdInfoDto dto, BindingResult bindingResult) {
        String operateType = dto.getOperateType();
        String pageModel = dto.getPageModel();
        if (StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {//新增
            ProdInfoDto existDto = prodInfoService.selectByProdStatus(dto.getpProdId());
            if (existDto != null) {
                return new AjaxResult("子产品代码已存在", MessageType.错误消息);
            }
        } else if (StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {//修改

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
        ProdInfoDto dto = JsonUtils.json2Object(datas, ProdInfoDto.class);
        try {
            if (this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                dto.setStatus(DictCodeListExt3.中债数据状态_有效.getValue());
                //	dto.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
                prodInfoService.insert(dto);
            }
            if (this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                prodInfoService.update(dto);
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
            ValidateGroupEdit.class}) ProdInfoDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 新建留痕表参数和工作流参数
        ProcessParameter<ProdInfoDto> processParameter = new ProcessParameter<ProdInfoDto>();
        WorkFlowParameter<ProdInfoDto> params = new WorkFlowParameter<ProdInfoDto>();
        params.setPageId("ProdInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getProdId());
        params.setBusinessType(DictCodeListExt4.共通业务类型_产品基本信息.getValue());
        params.setComment(entity -> "产品基本信息-产品代码" + "(" + entity.getProdId() + ")");
        // 塞入工作流参数
        processParameter.setWorkFlowParameter(params);

        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();// 获取平台日期
        dto.setCreateTime(sysDate);
        dto.setUpdateTime(sysDate);
        dto.setCreateUser(CommonUtil.getUserName());
        dto.setUpdateUser(CommonUtil.getUserName());

        // 流程开始
        String msg = prodInfoService.start(processParameter, dto);
        return new AjaxResult(msg);
    }


}
