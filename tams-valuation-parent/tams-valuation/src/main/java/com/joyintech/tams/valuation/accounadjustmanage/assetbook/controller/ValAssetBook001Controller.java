/*
 * 文件名：ValAssetBook001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.assetbook.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.dto.ValAssetbook001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 模板示例<br>
 * 账套管理
 * 
 * @author 黄健
 * @version 1.0 2018年5月10日
 * @see ValAssetBook001Controller
 * @since 1.0
 */
@Controller
@PageId("ValAssetBook001")
@RequestMapping("/valuation/accounadjustmanage/assetbook/ValAssetBook001")
public class ValAssetBook001Controller extends BaseController {

    /**
     * ValAssetBook001Service
     */
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.账套类别);
        this.setSelectOptionToModel(model, DictCode.金融产品类型);
        model.addAttribute("defaultType", DictCodeList.账套类别_账套模板.getValue());
        Date sysDate = DateUtil.platDateFromCache();
        String morenDate = DateUtil.format(sysDate);
        model.addAttribute("sysDate", morenDate);
        return super.init(model);
    }
    
    /**
     * 检索账套代码
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
//    @ResponseBody
//    @RequestMapping("/getBooksetId")
//    public List<SuggestDto> getBooksetId()
//        throws Exception {
////        String param = super.getRequest().getParameter("param");
//        return valAssetBook001Service.getBooksetId(RequestUtil.getPageParam());
//    }
    
    /**
     * 检索账套代码(只取理财账套)
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getBooksetId2")
    public List<SuggestDto> getBooksetId2()
        throws Exception {
//        String param = super.getRequest().getParameter("param");
        return valAssetBook001Service.getBooksetId2(RequestUtil.getPageParam());
    }
    /**
     * 根据套帐名称 套帐类型查询数据 <功能详细描述>
     * 
     * @return List<ValAssetbook001SearchDto>
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @RequestMapping("ValAssetBook001search")
    @ResponseBody
    public Page<ValAssetbook001Dto> search() throws Exception {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("name",this.getParam("name"));
        params.put("type",this.getParam("type"));
        return valAssetBook001Service.getAllAssetbookPage(params);
    }
    
    /**
     * 导出数据
     * 
     * @return ResponseEntity<byte[]>
     * @throws Exception ExportUtil.export
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        String datas = this.getParam("datas");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", this.getParam("name"));
        params.put("type", this.getParam("type"));
        List<ValAssetbook001Dto> listdto;
        if (datas == null) {
            listdto = valAssetBook001Service.getAllAssetbook(params);
        }
        else {
            listdto = JsonUtils.json2List(datas, ValAssetbook001Dto.class);
        }
        File outFile = valAssetBook001Service.makeFile(
            "/com/joyintech/tams/valuation/accounadjustmanage/assetbook/config/export_assetbook.xml",
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
     * 删除check
     * 
     * @param dto 行数据
     * @param result check结果
     * @return WebResult
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @RequestMapping("doDeleteCheck")
    @ResponseBody
    public AjaxResult doDeleteCheck(@RequestBody @Valid ValAssetbook001Dto dto,
                                 BindingResult result) throws Exception {
    	AjaxResult wr = new AjaxResult();
//    	int res = valAssetBook001Service.workflowCheck(dto);
//    	valAssetBook001Service.checkBusinessDataInFlow(dto);
        if (valAssetBook001Service.checkBusinessDataInFlow(dto)) {
            return new AjaxResult("存在流程中数据，不能删除！", MessageType.错误消息);
        }
    	// 该账套已经有记账凭证产生，删除时提示“已有记账凭证产生，是否确认删除”
    	int voucherCount = valAssetBook001Service.queryVoucherCount(dto.getBooksetId());
    	if (voucherCount > 0) {
    		wr.setData("已有记账凭证产生，是否确认删除?");
    		return wr;
    	}
    	wr.setData(this.getMessage("COMMON_C_0001", "删除"));
        return wr;
    }
    
    /**
     * 模板账套删除-不带流程
     * 
     * @return boolean
     * @throws IOException 转换
     * @see
     * @since 1.0
     */
    @RequestMapping("delete")
    @ResponseBody
    public boolean delete()
        throws IOException {
        String datas = this.getParam("datas");
//        List<ValAssetbook001Dto> listDto = JsonUtils.json2List(datas, ValAssetbook001Dto.class);
//        valAssetBook001Service.delete(listDto);
        valAssetBook001Service.delete(datas);
        return true;
    }

    /**
     * 理财账套删除-带流程
     * 
     * @return boolean
     * @throws IOException 转换
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("deleteWithFlow")
    public AjaxResult deleteWithFlow(@RequestBody @Valid ValAssetbook001Dto dto, BindingResult br) throws IOException {
    	if(br.hasErrors()){
            return new AjaxResult(br);
        }
    	
    	ProcessParameter<ValAssetbook001Dto> proParam = new ProcessParameter<>();
        WorkFlowParameter<ValAssetbook001Dto> params = new WorkFlowParameter<>();
        // 业务代码
        params.setBusinessCode(dto.getBooksetId());
        // 业务类型
        params.setBusinessType(DictCodeList.共通业务类型_账套管理.getValue());
        // 页面id
        params.setPageId("ValAssetBook005");
        // 元素id
        params.setElementId("ValAssetBook001_btn_delete");
        // 流程业务说明
        params.setComment(entity -> WorkFlowUtil.concatComment("账套管理-删除",
            entity.getBooksetId()//账套代码
        ));
        proParam.setWorkFlowParameter(params);
        
        String msg = getMessage("COMMON_I_0001");
        try {
            msg = valAssetBook001Service.start(proParam, dto);
        }
        catch (Exception e) {
            getLogger().error("启动流程失败：", e);
            return new AjaxResult(e.getMessage());
        }
        return new AjaxResult(msg);
    }




    /**
     /**
     * 检索账套代码(只取理财账套)
     *
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getBooksetId")
    public Page<SelectOptionDto> getBooksetId() throws Exception {
        return valAssetBook001Service.getBooksetIdNew(RequestUtil.getPageParam());
    }

    /**
     * 查询账套组
     * @return 套账日期
     * @throws Exception
     * @see
     */
    @RequestMapping("findBooksetGroup")
    @ResponseBody
    public Page<SelectOptionDto> findBooksetGroup() throws Exception {
        return valAssetBook001Service.findBooksetGroup(RequestUtil.getPageParam());
    }

}
