/*
 * 文件名：ValAssetsFundQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assets.fund.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.assets.fund.dto.ValAssetsFundQuery001Dto;
import com.joyintech.tams.valuation.assets.fund.dto.ValAssetsFundQuery002Dto;
import com.joyintech.tams.valuation.assets.fund.service.ValAssetsFundQuery001Service;
import com.joyintech.tams.valuation.assets.fund.service.ValAssetsFundQuery002Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 基金类资产列表页面
 * 
 * @author zhangwei
 * @version 1.0 2018年7月25日
 * @see ValAssetsFundQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("ValAssetsFundQuery001")
@RequestMapping("/valuation/assets/fund/ValAssetsFundQuery001")
public class ValAssetsFundQuery001Controller extends BaseController {

    @Autowired
    private ValAssetsFundQuery001Service valAssetsFundQuery001Service;

    @Autowired
    private ValAssetsFundQuery002Service valAssetsFundQuery002Service;
    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {

        this.setSelectOptionToModel(model, DictCode.基金类型);
        return super.init(model);
    }

    /**
     * 根据条件分页查询
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValAssetsFundQuery001Dto> findPage()
        throws Exception {
        return valAssetsFundQuery001Service.findPage(RequestUtil.getPageParam());
    }

    /**
     * 下拉框获取：资产
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("getAssets")
    @ResponseBody
    public List<SelectOptionDto> getAssets()
        throws Exception {
        return valAssetsFundQuery001Service.getAssets(RequestUtil.getPageParam());
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
        List<ValAssetsFundQuery001Dto> listdto;
        if (datas == null) {
            listdto = (List<ValAssetsFundQuery001Dto>)valAssetsFundQuery001Service.findAll(
                paraMap);
        }
        else {
            listdto = JsonUtils.json2List(datas, ValAssetsFundQuery001Dto.class);
        }
        File outFile = valAssetsFundQuery001Service.makeFile(
            "/com/joyintech/tams/valuation/assets/fund/conf/export_valassetsfundquery.xml",
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
     * @param dto
     * @param result
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("deleteCheck")
    public AjaxResult check(@RequestBody ValAssetsFundQuery001Dto dto, BindingResult result)
        throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        String finprodId = dto.getFinprodId();
        params.put("finprodId", finprodId);

        if (StringUtils.isNotEmpty(finprodId)) {// 删除基金类资产验证
            boolean flag = valAssetsFundQuery001Service.checkFindprodId(params);
            if (flag) {
                return new AjaxResult("存在资产交易，不能删除！", MessageType.错误消息);
            }
        }

        
        ValAssetsFundQuery002Dto valAssetsFundQuery002Dto = valAssetsFundQuery002Service.findByfinprodId(finprodId);
        if (valAssetsFundQuery002Service.checkBusinessDataInFlow(valAssetsFundQuery002Dto)) {
            
            return new AjaxResult("流程中，不能删除！", MessageType.错误消息);
        }
        return new AjaxResult();
    }


    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody ValAssetsFundQuery002Dto dto, BindingResult br)
        throws Exception {

        if (br.hasErrors()) {
            return new AjaxResult(br);
        }

        // 启动流程
        ProcessParameter<ValAssetsFundQuery002Dto> proParam = new ProcessParameter<>();
        WorkFlowParameter<ValAssetsFundQuery002Dto> params = new WorkFlowParameter<>();

        // 业务代码（凭证流水号）
        params.setBusinessCode(dto.getFinprodId());
        // 业务类型
        params.setBusinessType(DictCodeList.共通业务类型_基金类资产.getValue());
        // 页面id
        params.setPageId("ValAssetsFundQuery002");
        // 元素id
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment("基金类资产删除：", dto.getFinprodId(), // 金融产品代码
            dto.getFundType()));

        // 塞入流程参数
        proParam.setWorkFlowParameter(params);
        
        ValAssetsFundQuery002Dto dto1 = valAssetsFundQuery002Service.findByfinprodId(dto.getFinprodId());
        String msg = getMessage("COMMON_I_0001");
        try {
            msg = valAssetsFundQuery002Service.start(proParam, dto1);
        }
        catch (Exception e) {
            getLogger().error("启动流程失败：", e);
            return new AjaxResult(e.getMessage());
        }

        return new AjaxResult(msg);
    }

    /**
     * 修改前判断
     * 
     * @param dto
     * @param result
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("updateCheck")
    public AjaxResult updateCheck(@RequestBody ValAssetsFundQuery001Dto dto, BindingResult result)
        throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        String finprodId = dto.getFinprodId();
        params.put("finprodId", finprodId);

        if (StringUtils.isNotEmpty(finprodId)) {// 修改基金类资产验证
            boolean flag = valAssetsFundQuery001Service.checkFindprodId(params);
            if (flag) {
                return new AjaxResult("已存在交易数据，是否确定修改资产定义信息？", MessageType.确认消息);
            }
        }
        return new AjaxResult();
    }
}
