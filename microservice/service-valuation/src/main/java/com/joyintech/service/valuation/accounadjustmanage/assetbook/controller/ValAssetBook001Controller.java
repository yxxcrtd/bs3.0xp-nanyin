/*
 * 文件名：ValAssetBook001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.assetbook.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.dto.ValAssetbook001Dto;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
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
//@RestController(value="serviceValAssetBook001Controller")
@RestController("valAssetBook001Ctl_dtx")
@RequestMapping("ValAssetBook001")
public class ValAssetBook001Controller extends BaseController {

    /**
     * ValAssetBook001Service
     */
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;

    /**
     * {@inheritDoc}
     */
//    @Override
//    public String init(Model model) {
//        this.setSelectOptionToModel(model, DictCode.账套类别);
//        this.setSelectOptionToModel(model, DictCode.金融产品类型);
//        model.addAttribute("defaultType", DictCodeList.账套类别_账套模板.getValue());
//        return super.init(model);
//    }
    
    /**
     * 检索账套代码
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */

    @PostMapping("/getBooksetId")
    public Page<SelectOptionDto> getBooksetId(@RequestBody Map<String, Object> param)
        throws Exception {
        String params = (String) param.get("param");
        return valAssetBook001Service.getBooksetId(param);
    }
    
    /**
     * 检索账套代码(只取理财账套)
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getBooksetId2")
    public List<SuggestDto> getBooksetId2(@RequestBody Map<String, Object> params)
        throws Exception {
        String param =(String) params.get("param");
        return valAssetBook001Service.getBooksetId2(param);
    }
    
    /**
     * 检索账套代码(只取理财账套)
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getBooksetIdAll")
    public List<SuggestDto> getBooksetIdAll(@RequestBody Map<String, Object> params)
        throws Exception {
        String param =(String) params.get("param");
        return valAssetBook001Service.getBooksetIdAll(param);
    }
    
    /**
     * 根据账套组中账套ID查询账套 add by yanya 20190929
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getBooksetId3")
    public List<SuggestDto> getBooksetId3(@RequestBody Map<String, Object> params)
        throws Exception {
        return valAssetBook001Service.getBooksetId3(params);
    }
    /**
     * 根据套帐名称 套帐类型查询数据 <功能详细描述>
     * 
     * @return List<ValAssetbook001SearchDto>
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @PostMapping("/getAllAssetbookPage/{page}/{length}")
    public Page<ValAssetbook001Dto> getAllAssetbookPage(@RequestBody Map<String,Object> params,
                                                        @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        String name = (String)params.get("name");
        String type = (String)params.get("type");
        return valAssetBook001Service.getAllAssetbookPage(page,length,type, name);
    }
    

    /**
     * 根据套帐类型与套帐名称获取数据
     * 
     * @param type 套帐类型
     * @param name 套帐名称
     * @return List<ValAssetbook001SearchDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/getAllAssetbook")
    public List<ValAssetbook001Dto> getAllAssetbook(@RequestBody Map<String,Object> params) {
        String name = (String)params.get("name");
        String type = (String)params.get("type");
        return valAssetBook001Service.getAllAssetbook(type, name);
    }
    
    /**
     * 检索账套代码列表
     * 
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    @GetMapping("/getBooksetIdForSelect")
    public List<SelectOptionDto> getBooksetIdForSelect() {
        return valAssetBook001Service.getBooksetIdForSelect();
    }

//    /**
//     * 导出数据
//     * 
//     * @return ResponseEntity<byte[]>
//     * @throws Exception ExportUtil.export
//     * @see
//     * @since 1.0
//     */
//    @RequestMapping(ACTION_EXPORT)
//    public ResponseEntity<byte[]> export()
//        throws Exception {
//        String datas = this.getParam("datas");
//        String name = this.getParam("name");
//        String type = this.getParam("type");
//        List<ValAssetbook001Dto> listdto;
//        if (datas == null) {
//            listdto = valAssetBook001Service.getAllAssetbook(type, name);
//        }
//        else {
//            listdto = JsonUtils.json2List(datas, ValAssetbook001Dto.class);
//        }
//        File outFile = valAssetBook001Service.makeFile(
//            "/com/joyintech/tams/valuation/accounadjustmanage/assetbook/config/export_assetbook.xml",
//            listdto);
//        return ExportUtil.export(
//            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
//                                 + DateUtil.format(new Date())
//                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
//                                 + SystemGlobalConstant.POINT
//                                 + FileNameUtil.getFileExtName(outFile),
//            outFile, true);
//    }
//    
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
    @PostMapping("/doDeleteCheck")
    public AjaxResult doDeleteCheck(@RequestBody Map<String,Object> params) throws Exception {
        ValAssetbook001Dto dto = (ValAssetbook001Dto)params.get("valAssetbook001Dto");
        BindingResult result = (BindingResult)params.get("bindingResult");
    	AjaxResult wr = new AjaxResult();
    	int res = valAssetBook001Service.workflowCheck(dto);
        if (res > 0) {
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
    @Transactional
    @DTXTransaction
    @PostMapping("/delete")
    public boolean delete(@RequestBody String datas)
        throws IOException {
        List<ValAssetbook001Dto> listDto = JsonUtils.json2List(datas, ValAssetbook001Dto.class);
        valAssetBook001Service.delete(listDto);
        return true;
    }

    @PostMapping("/getDataInWorklow")
    public int getDataInWorklow(@RequestBody ProcessMark processMark) {
        return valAssetBook001Service.getDataInWorklow(processMark);
    }
    
    
    
    /**
     * 检索账套代码列表(分页下拉框)
     * @param param
     * @return
     */
    @PostMapping("/findBooksetIdByPage/{page}/{length}")
    public Page<SelectOptionDto> findBooksetIdByPage(@RequestBody Map<String, Object> pageParam,
                                                     @PathVariable("page") int page, @PathVariable("length") int length) {
        return valAssetBook001Service.findBooksetIdByPage(page,length,pageParam);
    }
    
    /**
     * 检索账套代码列表(分页下拉框)
     * @param param
     * @return
     */
    @PostMapping("/findBooksetIdByType/{page}/{length}")
    public Page<SelectOptionDto> findBooksetIdByType(@RequestBody Map<String, Object> pageParam,
                                                        @PathVariable("page") int page, @PathVariable("length") int length) {
        return valAssetBook001Service.findBooksetIdByType(page,length,pageParam);
    }
    
    /**
     * 理财账套删除-带流程
     * 
     * @return boolean
     * @throws IOException 转换
     * @see
     * @since 1.0
     */
//    @ResponseBody
//    @RequestMapping("deleteWithFlow")
//    public AjaxResult deleteWithFlow(@RequestBody @Valid ValAssetbook001Dto dto, BindingResult br) throws IOException {
//    	if(br.hasErrors()){
//            return new AjaxResult(br);
//        }
//    	
//    	ProcessParameter<ValAssetbook001Dto> proParam = new ProcessParameter<>();
//        WorkFlowParameter<ValAssetbook001Dto> params = new WorkFlowParameter<>();
//        // 业务代码
//        params.setBusinessCode(dto.getBooksetId());
//        // 业务类型
//        params.setBusinessType(DictCodeList.共通业务类型_账套管理.getValue());
//        // 页面id
//        params.setPageId("ValAssetBook005");
//        // 元素id
//        params.setElementId("ValAssetBook001_btn_delete");
//        // 流程业务说明
//        params.setComment(entity -> WorkFlowUtil.concatComment("账套管理-删除",
//            entity.getBooksetId()//账套代码
//        ));
//        proParam.setWorkFlowParameter(params);
//        
//        String msg = getMessage("COMMON_I_0001");
//        try {
//            msg = valAssetBook001Service.start(proParam, dto);
//        }
//        catch (Exception e) {
//            getLogger().error("启动流程失败：", e);
//            return new AjaxResult(e.getMessage());
//        }
//        return new AjaxResult(msg);
//    }
    
    
    
    
    @GetMapping("queryVoucherCount")
    public int queryVoucherCount(String booksetId){
    	return valAssetBook001Service.queryVoucherCount(booksetId);
    }
    
    @Transactional
    @DTXTransaction
    @GetMapping("doComplete")
    public int doComplete(String booksetId){
    	return valAssetBook001Service.doComplete(booksetId);
    }



    /**
     * 获取产品组下的账套
     * @param object
     * @return
     */
    @PostMapping("/findAllProduct")
    public List<SysBatchGrpPrdlink> findAllProduct(@RequestBody Map<String, Object> params) {
       return valAssetBook001Service.findAllProduct(params);
    }
    /**
     * 产品组
     * @param pageParam
     * @return
     */
    @PostMapping("/findBooksetGroup/{page}/{length}")
    public Page<SelectOptionDto> findBooksetGroup(@RequestBody Map<String, Object> pageParam,
    @PathVariable("page") int page, @PathVariable("length") int length) {
        return valAssetBook001Service.findBooksetGroup(page,length,pageParam);
    }
    /**
     * 账套代码
     * @param pageParam
     * @return
     */
    @PostMapping("/getBooksetIdNew/{page}/{length}")
    public Page<SelectOptionDto> getBooksetIdNew(@RequestBody Map<String, Object> pageParam,
    @PathVariable("page") int page, @PathVariable("length") int length) {
        return valAssetBook001Service.getBooksetIdNew(page,length,pageParam);
    }

    /**
     * 根据配置产品组查询对应的产品
     *
     * @return 产品代码
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findProductGrp")
    public List<SuggestDto> findProductGrp(@RequestBody Map<String, Object> params)
            throws Exception {
        String param =(String) params.get("param");
        return valAssetBook001Service.findProductGrp(params);
    }
}
