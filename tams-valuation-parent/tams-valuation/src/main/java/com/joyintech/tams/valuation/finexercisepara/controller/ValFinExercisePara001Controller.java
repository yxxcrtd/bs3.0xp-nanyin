/*
 * 文件名：ValFinExercisePara001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年3月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.finexercisepara.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.finexercisepara.dto.ValFinExercisePara001Dto;
import com.joyintech.tams.valuation.finexercisepara.dto.ValFinExercisePara002Dto;
import com.joyintech.tams.valuation.finexercisepara.service.ValFinExercisePara001Service;
import com.joyintech.tams.valuation.finexercisepara.service.ValFinExercisePara002Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 含权债摊销参数设置
 * 
 * @author zhangwei
 * @version 1.0 2019年3月2日
 * @see ValFinExercisePara001Controller
 * @since 1.0
 */
@Controller
@PageId("ValFinExercisePara001")
@RequestMapping("/valuation/finexercisepara/ValFinExercisePara001")
public class ValFinExercisePara001Controller extends BaseController {

    @Autowired
    private ValFinExercisePara001Service valFinExercisePara001Service;

    @Autowired
    private ValFinExercisePara002Service valFinExercisePara002Service;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    @RequestMapping("/getBooksetId")
    @ResponseBody
    public Page<SelectOptionDto> getBooksetId() {
        return valFinExercisePara002Service.findBooksetIdByPage(RequestUtil.getPageParam());
    }

    /**
     * 下拉框 ： 产品代码
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findFinprod")
    public Page<SelectOptionDto> findFinprod()
        throws Exception {
        return valFinExercisePara002Service.findFinprod(RequestUtil.getPageParam());
    }
    
    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValFinExercisePara001Dto> findPage()
        throws Exception {
        return valFinExercisePara001Service.findPage(RequestUtil.getPageParam());
    }

    /**
     * 删除 check
     * 
     * @param dto
     * @param result
     * @param operateType
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) ValFinExercisePara002Dto dto,
                            BindingResult bindingResult)
        throws Exception {
//        String msg = StringUtil.EMPTY;
        String operateType = dto.getOperateType();

        return commonCheck(bindingResult, operateType);
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        List<ValFinExercisePara001Dto> listdto;
        if (datas == null) {
            listdto = valFinExercisePara001Service.findAll(paraMap);
        }
        else {
            listdto = JsonUtils.json2List(datas, ValFinExercisePara001Dto.class);
        }
        File outFile = valFinExercisePara001Service.makeFile(
            "/com/joyintech/tams/valuation/finexercisepara/conf/export_valfinexercisepara.xml", listdto);
        return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }

    /**
     * 删除方案 并启动流程
     * 
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = ACTION_DELETE, method = RequestMethod.POST)
    public AjaxResult deleteScheme(@RequestBody ValFinExercisePara002Dto dto) {
        String msg = StringUtils.EMPTY;
        ProcessParameter<ValFinExercisePara002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ValFinExercisePara002Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            dto = valFinExercisePara002Service.findByPrimaryKey(dto);// dto 为回显值

            workFlowParameter.setPageId("ValFinExercisePara002"); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getBooksetId() + "_" + dto.getFinprodId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_含权债摊销参数设置.getValue());// 业务类型
            workFlowParameter.setComment(
                entity -> WorkFlowUtil.concatComment("含权债摊销参数设置", entity.getBooksetId(), entity.getFinprodId()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数

            valFinExercisePara002Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        }
        catch (Exception e) {
            this.getLogger().error("启动估值方案流程失败：", e);
            msg = e.getMessage();
        }
        return new AjaxResult(msg);
    }

}
