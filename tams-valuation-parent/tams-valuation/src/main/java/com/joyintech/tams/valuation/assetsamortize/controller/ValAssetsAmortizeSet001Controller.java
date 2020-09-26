/*
 * 文件名：ValIncomeStatisticsQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assetsamortize.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.assetsamortize.dto.ValAssetsAmortizeSet001Dto;
import com.joyintech.tams.valuation.assetsamortize.service.ValAssetsAmortizeSet001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 资产摊销设置
 * 
 * @author HuangJian
 * @version 1.0 2018年12月4日
 * @see ValAssetsAmortizeSet001Controller
 * @since 1.0
 */
@Controller
@PageId("ValAssetsAmortizeSet001")
@RequestMapping("/valuation/assetsamortize/ValAssetsAmortizeSet001")
public class ValAssetsAmortizeSet001Controller extends BaseController {

    /**
     * valAssetsAmortizeSet001Service
     */
    @Autowired
    private ValAssetsAmortizeSet001Service valAssetsAmortizeSet001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.重算方式);
        model.addAttribute("recalType", DictCodeList.重算方式_重算.getValue());
        /*model.addAttribute("FINPROD_ID",
            JsonUtils.objectArray2Json(valAssetsAmortizeSet001Service.getFinprodId().toArray()));*/
        //model.addAttribute("BOOKSET_ID",
        //JsonUtils.objectArray2Json(valAssetsAmortizeSet001Service.getBooksetId().toArray()));
        return super.init(model);
    }

    /**
     * 表格查询
     * 
     * @return Page<ValAssetsAmortizeSet001Dto>
     * @throws Exception $.trim($('#finprodId').val())
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValAssetsAmortizeSet001Dto> findPage()
        throws Exception {
        return valAssetsAmortizeSet001Service.findPage(RequestUtil.getPageParam());
    }

    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getBooksetId")
    @ResponseBody
    public Page<SelectOptionDto> getBooksetId() {
        return valAssetsAmortizeSet001Service.getBooksetId(RequestUtil.getPageParam());
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
        List<ValAssetsAmortizeSet001Dto> listdto;
        if (datas == null) {
            listdto = (List<ValAssetsAmortizeSet001Dto>)valAssetsAmortizeSet001Service.findAll(
                paraMap);
        }
        else {
            listdto = JsonUtils.json2List(datas, ValAssetsAmortizeSet001Dto.class);
        }
        File outFile = valAssetsAmortizeSet001Service.makeFile(
            "/com/joyintech/tams/valuation/assetsamortize/conf/export_valassetsamortizeset.xml",
            listdto);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date())
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }

    /**
     * 删除前判断
     * 
     * @param dto ValAssetsAmortizeSet001Dto
     * @param result BindingResult
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("deleteCheck")
    public AjaxResult check(@Valid @RequestBody ValAssetsAmortizeSet001Dto dto,
                            BindingResult result)
        throws Exception {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }

    /**
     * 删除功能
     * 
     * @param dto ValAssetsAmortizeSet001Dto
     * @param br BindingResult
     * @return AjaxResult
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody ValAssetsAmortizeSet001Dto dto, BindingResult br)
        throws Exception {

        // 启动流程
        ProcessParameter<ValAssetsAmortizeSet001Dto> proParam = new ProcessParameter<>();
        WorkFlowParameter<ValAssetsAmortizeSet001Dto> params = new WorkFlowParameter<>();
        dto.setRecalTypeVal(dto.getRecalType());
        // 业务代码（凭证流水号）
        params.setBusinessCode(dto.getBusiId() + dto.getBooksetId() + dto.getCdate());
        // 业务类型
        params.setBusinessType(DictCodeList.共通业务类型_资产摊销设置.getValue());
        // 页面id
        params.setPageId("ValAssetsAmortizeSet002");
        // 元素id
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment("资产摊销删除：",
            dto.getBusiId() + dto.getBooksetId() + dto.getCdate()));

        // 塞入流程参数
        proParam.setWorkFlowParameter(params);

        String msg = getMessage("COMMON_I_0001");
        try {
            msg = valAssetsAmortizeSet001Service.start(proParam, dto);
        }
        catch (Exception e) {
            getLogger().error("启动流程失败：", e);
            return new AjaxResult(e.getMessage());
        }

        return new AjaxResult(msg);
    }

    /**
     * 资产代码下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getFinprodId")
    @ResponseBody
    public Page<SelectOptionDto> getSubjectNo(HttpServletRequest request) {
        Map<String, Object> param =RequestUtil.getPageParam();
        return valAssetsAmortizeSet001Service.getFinprodId2(param);
    }


}
