/*
 * 文件名：ValManualVoucher001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.manualvoucher.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
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
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher001Dto;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher001SearchDto;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.service.ValManualVoucher001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.service.BaseCommonService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 模板示例<br>
 * 手工凭证查询
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValManualVoucher001Controller
 * @since 2.0
 */
//@RestController(value="serviceValManualVoucher001Controller")
@RestController("valManualVoucher001Ctl_dtx")
@RequestMapping("ValManualVoucher001")
public class ValManualVoucher001Controller extends BaseController {
    
    /**
     * ValManualVoucher001Service
     */
    @Autowired
    private ValManualVoucher001Service valManualVoucher001Service;
    
//    /**
//     * ValManualVoucher003Service
//     */
//    @Autowired
//    private ValManualVoucher003Service valManualVoucher003Service;
    
//    @Autowired
//    private ValAssetBook001Service valAssetBook001Service;
    
//    @Autowired
//    private BaseCommonService baseCommonService;

//    /**
//     * 重写init方法，加载页面 {@inheritDoc}
//     */
//    @Override
//    public String init(Model model) {
//        this.setSelectOptionToModel(model, DictCode.会计场景代码);
//        this.setSelectOptionToModel(model, DictCode.凭证类型);
//     // 记账日期默认为系统平台日
//        model.addAttribute("sysDate",
//            DateUtil.format(baseCommonService.getSysDate(), "yyyy-MM-dd"));
//        model.addAttribute("bookType", DictCodeList.凭证类型_手工.getValue());
//        this.setSelectOptionToModel(model, DictCode.金融产品类型);
//        this.setSelectOptionToModel(model, DictCode.是否);
//        
//        model.addAttribute("YES", DictCodeList.是否_是.getValue());
//        model.addAttribute("FINPRODID_LIST",JsonUtils.objectArray2Json(valAssetBook001Service.getBooksetIdForSelect().toArray()));
//        return super.init(model);
//    }

//    /**
//     * 所属账套下拉框获取
//     *
//     * @return 返回的数据
//     * @exception Exception
//     *                异常
//     * @since 1.0
//     */
//    @RequestMapping("/getBooksetId")
//    @ResponseBody
//    public Page<SelectOptionDto> getBooksetId() {
//        return valAssetBook001Service.findBooksetIdByPage(RequestUtil.getPageParam());
//    }
    
    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    
    @PostMapping("/getBooksetId")
    public List<SelectOptionDto> getBooksetId(){
        return valManualVoucher001Service.getBooksetId();
    }

    /**
     * 业务类别下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getBusiClass")
    public List<SelectOptionDto> getBusiClass() {
        return valManualVoucher001Service.getBusiClass();
    }

    /**
     * 查询前校验
     * 
     * @param dto 参数
     * @param result 校验结果
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkQuery")
    public AjaxResult checkQuery(@Valid @RequestBody ValManualVoucher001SearchDto dto,
            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }

    /**
     * 记账手工凭证数据取得
     *
     * @return 记账手工凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValManualVoucher001Dto> findPage(@RequestBody Map<String, ValManualVoucher001SearchDto> map,
                                                 @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        ValManualVoucher001SearchDto dto = map.get("dto");
//        Map<String, Object> params= this.getRequestMap();
//        ValManualVoucher001SearchDto dto = (ValManualVoucher001SearchDto)JsonUtils.map2entity(params, ValManualVoucher001SearchDto.class);
//        // 凭证类型固定为手工
//        dto.setBookType(DictCodeList.凭证类型_手工.getValue());
        return valManualVoucher001Service.findPage(page,length,dto);

    }
    
    /**
     * 
     * 根据条件查询全部信息
     * @param params 筛选条件
     * @return 全部信息
     * @see
     * @since 1.0
     */
    @PostMapping("/selectAll")
    public List<ValManualVoucher001Dto> selectAll(@RequestBody ValManualVoucher001SearchDto dto) {
        return valManualVoucher001Service.selectAll(dto);
    }
    
    @Transactional
    @DTXTransaction
    @PostMapping("/deleteList")
    public int deleteList(@RequestBody List<ValManualVoucher001Dto> listDto) {
        return valManualVoucher001Service.deleteList(listDto); 
    }

//    /**
//     * 导出信息excel
//     * 
//     * @return excel
//     * @throws Exception
//     *             异常
//     * @see
//     * @since 1.0
//     */
//    @RequestMapping(ACTION_EXPORT)
//    public ResponseEntity<byte[]> export() throws Exception {
//        Map<String, Object> params= this.getRequestMap();
//        String datas = this.getParam("datas");
//        ValManualVoucher001SearchDto dto = (ValManualVoucher001SearchDto)JsonUtils.map2entity(params, ValManualVoucher001SearchDto.class);
//        dto.setBookType(DictCodeList.凭证类型_手工.getValue());
//        List<ValManualVoucher001Dto> dtoList;
//        if (datas == null) {
//            dtoList = valManualVoucher001Service.selectAll(dto);
//        }
//        else {
//            dtoList = JsonUtils.json2List(datas, ValManualVoucher001Dto.class);
//        }
//        File outFile = valManualVoucher001Service.makeFile(
//                "/com/joyintech/tams/valuation/accounadjustmanage/manualvoucher/conf/export_manualvoucher.xml",
//                dtoList);
//        return ExportUtil.export(
//                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
//                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
//                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
//                outFile, true);
//    }
//
//    /**
//     * 
//     * 获取页面参数 页面中有时间参数，获取参数后将时间转换成Date类型
//     * 
//     * @return paraMap
//     * @throws ParseException 
//     * @see
//     * @since 1.0
//     */
//    public Map<String, Object> getRequestMap() throws ParseException {
//        Map<String, Object> paraMap = RequestUtil.getPageParam();
//        // 入账日期区间
//        String bookDateBegin = this.getParam("bookDateBegin");
//        String bookDateEnd = this.getParam("bookDateEnd");
//        if(StringUtil.isNotEmpty(bookDateBegin)){
//            paraMap.put("bookDateBegin", DateUtil.parseDate(bookDateBegin, "yyyy-MM-dd"));
//        }
//        if(StringUtil.isNotEmpty(bookDateEnd)){
//            paraMap.put("bookDateEnd", DateUtil.parseDate(bookDateEnd, "yyyy-MM-dd"));
//        }
//        // 发生日期区间
//        String happenDateBegin = this.getParam("happenDateBegin");
//        if(StringUtil.isNotEmpty(happenDateBegin)){
//            paraMap.put("happenDateBegin", DateUtil.parseDate(happenDateBegin, "yyyy-MM-dd"));
//        }
//        String happenDateEnd = this.getParam("happenDateEnd");
//        if(StringUtil.isNotEmpty(happenDateEnd)){
//            paraMap.put("happenDateEnd", DateUtil.parseDate(happenDateEnd, "yyyy-MM-dd"));
//        }
//        return paraMap;
//    }
//    

    
//    @ResponseBody
//    @RequestMapping("deleteList")
//    public boolean deleteList() throws IOException {
//        String datas = this.getParam("datas");
//        List<ValManualVoucher001Dto> listDto = JsonUtils.json2List(datas, ValManualVoucher001Dto.class);
//        int count = valManualVoucher001Service.deleteList(listDto);
//        if(count !=  listDto.size()){
//            return false;
//        }
//        return true;
//    }
    
//    @ResponseBody
//    @RequestMapping(ACTION_DELETE)
//    public AjaxResult deleteVouch(@RequestBody ValManualVoucher001Dto dto) throws IOException {
//        valManualVoucher003Service.deleteVouch(dto.getBooksetId(), dto.getBookId(), dto.getTradeId());
//        return new AjaxResult(this.getMessage("VAL_COMMON_I_0007"));
//    }
    
//    /**
//     * 手工凭证删除
//     * @param dto
//     * @param br
//     * @return
//     * @throws Exception
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping(ACTION_DELETE)
//    public AjaxResult delete(@RequestBody @Valid ValManualVoucher001Dto dto, BindingResult br) throws Exception{
//        
//        if(br.hasErrors()){
//            return new AjaxResult(br);
//        }
//        
//        // 启动流程
//        ProcessParameter<ValManualVoucher001Dto> proParam = new ProcessParameter<>();
//        WorkFlowParameter<ValManualVoucher001Dto> params = new WorkFlowParameter<>();
//        
//        // 业务代码（凭证流水号）
//        params.setBusinessCode(dto.getSeqno());
//        // 业务类型
//        params.setBusinessType(DictCodeList.共通业务类型_手工凭证.getValue());
//        // 页面id
//        params.setPageId("ValManualVoucher002");
//        // 元素id
//        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
//        
//        //设置流程业务说明, entity即传入的dto
//        params.setComment(entity -> WorkFlowUtil.concatComment("手工凭证删除：",
//            dto.getBooksetId(),//账套代码
//            dto.getBookId(),//凭证编号
//            dto.getSeqno()//凭证流水号，即交易编号
//            ));
//        
//        // 塞入流程参数
//        proParam.setWorkFlowParameter(params);
//        
//        String msg = getMessage("COMMON_I_0001");
//        try {
//            msg = valManualVoucher001Service.start(proParam, dto);
//        }
//        catch (Exception e) {
//            getLogger().error("启动流程失败：", e);
//            return new AjaxResult(e.getMessage());
//        }
//
//        return new AjaxResult(msg);
//    }
//    
//    
//    /**
//     * 删除时的check
//     *
//     * @param dto dto
//     * @param result BindingResult
//     * @param operateType 操作类型
//     * @return check结果
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping("check")
//   public AjaxResult check(@RequestBody @Valid ValManualVoucher001Dto dto, BindingResult result, String operateType) {
//        return commonCheck(result, operateType);
//    }
//    
    
}
