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

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.BokDetailIdDto;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher001Dto;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher003AddDto;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher003CheckValidatorDto;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher003Dto;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher003SubjectDto;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.service.ValManualVoucher003Service;
import com.joyintech.tams.common.base.dao.BokBooksetDateMapper;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 新建-手工凭证
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValManualVoucher003Controller
 * @since 2.0
 */
//@RestController(value="serviceValManualVoucher003Controller")
@RestController("valManualVoucher003Ctl_dtx")
@RequestMapping("/ValManualVoucher003")
public class ValManualVoucher003Controller extends BaseController {

    @Autowired
    private ValManualVoucher003Service valManualVoucher003Service;

//    @Autowired
//    private BaseCommonService baseCommonService;
    
//    @Autowired
//    private InterfCommonService interfCommonService;
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;
    @Autowired
    private BokBooksetDateMapper bokBooksetDateMapper;

//    @Override
//    public String init(Model model) {
//        this.setSelectOptionToModel(model, DictCode.会计场景代码, DictCode.币种, DictCode.冲正标识,
//            DictCode.借贷方向, DictCode.投资目的, DictCode.分录类型, DictCode.金融产品类型);
//        // 获取业务类型
//        // 获取 金融产品类型 数据字典
//        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.金融产品类型, null);
//        List<SelectOptionDto> options = selectDto.getOptions();
//        // 获取 现金流二级类型 数据字典
//        selectDto = this.getSelectDataFromDataDic(DictCode.现金流二级类型, null);
//        options.addAll(selectDto.getOptions());
//        // 获取 资金账户类型 数据字典
//        selectDto = this.getSelectDataFromDataDic(DictCode.资金账户类型, null);
//        options.addAll(selectDto.getOptions());
//        // 获取 一次性费用类型 数据字典
//        selectDto = this.getSelectDataFromDataDic(DictCode.一次性费用类型, null);
//        options.addAll(selectDto.getOptions());
//        // 4个数据字典的全部放入一个下拉框中
//        model.addAttribute("busiType", JsonUtils.objectArray2Json(options.toArray()));
//        model.addAttribute("QTYSK_BJ", DictCodeBaseSubjectNatrue.其他应收款_本金.getValue());
//        model.addAttribute("QTYSK_LX", DictCodeBaseSubjectNatrue.其他应收款_利息.getValue());
//        
//        // 工作流ID
//        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
//        
//        // 判断是修改操作还是新建操作
//        String seqno = getParam("seqno");
//        if (StringUtil.isNotEmpty(seqno)) {
//            ValManualVoucher001Dto entity = valManualVoucher003Service.getBySeqno(seqno);
//            model.addAttribute("entity", entity);
//            // 冲销标志默认值
//            model.addAttribute("offsetFlag", entity.getOffsetFlag());
//            // 借贷方向设一个默认值
//            model.addAttribute("cdFlag", entity.getCdFlag());
//            // 入账日期默认为系统平台日
//            model.addAttribute("sysDate", entity.getBookDate());
//        }
//        else if(StringUtil.isNotEmpty(workflowId)){
//            ValManualVoucher003Dto entity= valManualVoucher003Service.getBussinessEntityByWorkflowId(workflowId);
//
//            model.addAttribute("entity", entity);
//            model.addAttribute("offsetFlag", entity.getOffsetFlag());
//            model.addAttribute("workflowId", workflowId);
//            
//            // 入账日期默认为系统平台日
//            model.addAttribute("sysDate",DateUtil.format(entity.getBookDate(), "yyyy-MM-dd"));
//        }
//        else {
//            // 冲销标志默认值
//            model.addAttribute("offsetFlag", DictCodeList.冲正标识_正常交易.getValue());
//            // 借贷方向设一个默认值
//            model.addAttribute("cdFlag", DictCodeList.借贷方向_贷.getValue());
//            // 入账日期默认为系统平台日
//            model.addAttribute("sysDate",
//                DateUtil.format(baseCommonService.getSysDate(), "yyyy-MM-dd"));
//        }
//        
//        return super.init(model);
//    }
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param seqno
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/getBySeqno")
    public ValManualVoucher001Dto getBySeqno(@RequestBody String seqno) {
        return valManualVoucher003Service.getBySeqno(seqno);
    }

    /**
     * 根据手工凭证编号bookId获取对应相同编号的所有数据
     *
     * @return 记账手工凭证数据
     * @throws Exception 异常
     * @see
     */
    @PostMapping("/searchList")
    public List<ValManualVoucher003AddDto> searchList(@RequestBody String bookId) {
        // 手工凭证编号
//        String bookId = this.getParam("bookId");
        if (StringUtil.isNotEmpty(bookId)) {
            return valManualVoucher003Service.searchList(bookId);
        }
        else {
            return null;
        }
    }

//    /**
//     * 修改凭证摘要
//     * 
//     * @param dto 参数
//     * @param result 校验结果
//     * @return AjaxResult
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping("doAdd")
//    public AjaxResult doAdd(@Valid @RequestBody ValManualVoucher003AddValidatorDto dto,
//                            BindingResult result) {
//        if (result != null && result.hasErrors()) {
//            return new AjaxResult(result);
//        }
//        AjaxResult ajaxResult = new AjaxResult();
////        dto = valManualVoucher003Service.initTableDto(dto);
//        ajaxResult.setData(valManualVoucher003Service.initTableDto(dto));
//        return ajaxResult;
//    }
    
    /**
     * 确认校验
     * 
     * @param dto 参数
     * @param result 校验结果
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("doCheck")
    public AjaxResult doCheck(@Valid @RequestBody ValManualVoucher003CheckValidatorDto dto,
                            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult ajaxResult = new AjaxResult();
        return ajaxResult;
    }

    /**
     * 检索科目代码
     * 
     * @return 账套科目代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getSubjectNo")
    public ValManualVoucher003SubjectDto getSubjectNo(@RequestBody ValManualVoucher003SubjectDto dto)
        throws Exception {
        String booksetId = dto.getBooksetId();
        String subjectNo = dto.getSubjectNo();
        String busiId = dto.getBusiId();
        String finprodType = dto.getFinprodType();
        String ccy = dto.getCcy();//modify by dongjun on 20181211
//        if (StringUtil.isNotEmpty(booksetId) || StringUtil.isNotEmpty(subjectNo)) {
//            AjaxResult ajaxResult = new AjaxResult();
//            ajaxResult.setData(
             return valManualVoucher003Service.getSubjectNo(booksetId, subjectNo, busiId, finprodType,ccy);//modify by dongjun on 20181211
//            return ajaxResult;
//        }
//        else {
//            return new AjaxResult();
//        }
    }
    
    /**
     * 账套变更，获取对应的账套日期
     * 2018年12月17日  魏鹏新增
     * 
     * @return 记账日
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getBookDate")
    public AjaxResult getBookDate(@RequestBody ValManualVoucher003SubjectDto dto)
        throws Exception {
        String booksetId = dto.getBooksetId();
        if (StringUtil.isNotEmpty(booksetId) ) {
            AjaxResult ajaxResult = new AjaxResult();
            BokBooksetDate bokBooksetDate = bokBooksetDateMapper.selectByPrimaryKey(booksetId);
            if(bokBooksetDate != null){
                ajaxResult.setData(DateUtil.format(bokBooksetDate.getBooksetDate()));
            }
            return ajaxResult;
        }
        else {
            return new AjaxResult();
        }
        
    }
    
    /**
     * 检索科目代码
     * 
     * @return 账套科目代码下拉框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getSubjectNoPullDw")
    public List<SelectOptionDto> getSubjectNoPullDw(@RequestBody String booksetId)
        throws Exception {
        if (StringUtil.isNotEmpty(booksetId)) {
            return valManualVoucher003Service.getSubjectNoPullDw(booksetId);
        }
        else {
            return null;
        }
    }
    
    /**
     * 获取资产代码下拉框
     * 
     * @return 资产代码代码下拉框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getAcctId")
    public List<SelectOptionDto> getAcctId(@RequestBody Map<String, Object> params)
        throws Exception {
        String param = (String)params.get("param");
        String booksetId = (String)params.get("booksetId");
        if (StringUtil.isNotEmpty(booksetId)) {
            return valManualVoucher003Service.getAcctId(booksetId, param);
        }
        else {
            return null;
        }
    }
    
    /**
     * 获取分支序号下拉框
     * 
     * @return 分支序号下拉框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getBranch")
    public List<SelectOptionDto> getBranch(@RequestBody Map<String, Object> params)
        throws Exception {
        String param = (String)params.get("param");
        String booksetId = (String)params.get("booksetId");
        String finprodType = (String)params.get("finprodType");
        String subjectNo = (String)params.get("subjectNo");
        String finprodId = (String)params.get("finprodId");
        if (StringUtil.isNotEmpty(booksetId) || StringUtil.isNotEmpty(finprodType)
            || StringUtil.isNotEmpty(subjectNo) || StringUtil.isNotEmpty(finprodId)) {
            return valManualVoucher003Service.getBranch(booksetId,finprodType, subjectNo, finprodId, param);
        }
        else {
            return null;
        }
    }

    /**
     * 检索资产代码
     * 
     * @return 资产代码下拉框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getBusiId")
    public List<SuggestDto> getBusiId(@RequestBody Map<String, Object> params) {
        // 业务类型
        String finprodType = (String)params.get("finprodType");
        // 帐套代码
        String booksetId = (String)params.get("booksetId");
        String param = (String)params.get("param");
        return valManualVoucher003Service.getBusiId(finprodType, booksetId, param);
    }

//    /**
//     * 修改凭证摘要
//     * 
//     * @param dto 参数
//     * @param result 校验结果
//     * @return AjaxResult
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping("doSubmit")
//    public AjaxResult doSubmit(@Valid @RequestBody ValManualVoucher003Dto dto,
//                               BindingResult result) {
//        if (result != null && result.hasErrors()) {
//            return new AjaxResult(result);
//        }
//        AjaxResult ajaxResult = new AjaxResult(getMessage("COMMON_I_0001"));
//        valManualVoucher003Service.doSubmit(dto);
//        return ajaxResult;
//    }

//    /**
//     * 手工凭证 - 新建 - 走流程
//     * 
//     * @param dto 参数
//     * @return
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping(value = "doSave", method=RequestMethod.POST)
//    public AjaxResult doSave(@RequestBody @Valid ValManualVoucher003Dto dto, BindingResult br) {
//        
//        if(br.hasErrors()){
//            return new AjaxResult(br);
//        }
//        
//        // 启动流程
//        ProcessParameter<ValManualVoucher003Dto> proParam = new ProcessParameter<>();
//        WorkFlowParameter<ValManualVoucher003Dto> params = new WorkFlowParameter<>();
//        
//        //设置主键，即【即TRD_VOUCH_DEAL（手工凭证交易表）中的TRADE_ID（交易编号）】
//        dto.setSeqno(interfCommonService.getTradeid("T28", dto.getBookDate(), "A"));
//        
//        // 业务代码（凭证流水号）
//        params.setBusinessCode(dto.getSeqno());
//        // 业务类型
//        params.setBusinessType(DictCodeList.共通业务类型_手工凭证.getValue());
//        // 页面id
//        params.setPageId(this.getPageId());
//        // 元素id
//        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
//        
//        //设置流程业务说明, entity即传入的dto
//        params.setComment(entity -> WorkFlowUtil.concatComment("手工凭证新建：",
//            entity.getSeqno(),//凭证流水号
//            DateUtil.format(entity.getBookDate()),//记账日期
//            entity.getBooksetId()//账套代码
//            ));
//        
//        // 塞入流程参数
//        proParam.setWorkFlowParameter(params);
//        
//        String msg = getMessage("COMMON_I_0001");
//        try {
//            msg = valManualVoucher003Service.start(proParam, dto);
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
//     * 保存按钮点击时的check
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
//   public AjaxResult check(@RequestBody @Valid ValManualVoucher003Dto dto, BindingResult result, String operateType) {
//        return commonCheck(result, operateType);
//    }
//    
//    /**
//     * 根据流程id，获取对应的凭证新建时的分录信息
//     * @return
//     * @see
//     * @since 1.0
//     */
//    @RequestMapping("/getValManualVoucher003AddDtoList")
//    @ResponseBody
//    public List<ValManualVoucher003AddDto> getValManualVoucher003AddDtoList() {
//        
//        List<ValManualVoucher003AddDto> returnList = null;
//        
//        Map<String,Object> map=RequestUtil.getPageParam();
//        
//        String workflowId = (String)map.get("workflowId");
//        
//        //根据流程id获取对应的凭证新建时的分录信息
//        if(workflowId!=null && workflowId.length()>0){
//            ValManualVoucher003Dto entity= valManualVoucher003Service.getBussinessEntityByWorkflowId(workflowId);
//            returnList = entity.getListAddDto();
//        }
//        
//        // 因为分录信息中的【分支序号】实体类中设置的类型为【Long】,当不传递值的时候，会默认给一个0。
//        // 故判断当前分支序号若为0，显示为空
//        if (returnList != null) {
//	        for(ValManualVoucher003AddDto dto:returnList){
//	            if(new Long(0).equals(dto.getBranch())){
//	                dto.setBranch(null);
//	            }   
//	        }
//        }
//        return returnList;
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
        String param = super.getRequest().getParameter("param");
        return valAssetBook001Service.getBooksetId2(param);
    }
    
    /**modify by dongjun on 20181214 start **/
    /**
     * 
     * 手工凭证科目号联想
     * 
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/querySubctNo")
    public List<SelectOptionDto> querySubctNo(@RequestBody Map<String,Object> para){
//        Map<String,Object> para = RequestUtil.getPageParam();
        para.put("level3", DictCodeListExt1.科目级别_3级.getValue());
        List<SelectOptionDto> dtolist = valManualVoucher003Service.querySubctNo(para);
        return dtolist;
    }
    /**modify by dongjun on 20181214 end **/
    
    @Transactional
    @DTXTransaction
    @PostMapping("deleteVouch")
    public int deleteVouch(@RequestBody ValManualVoucher001Dto dto){
    	return valManualVoucher003Service.deleteVouch(dto);
    	
    }
    
    @Transactional
    @DTXTransaction
    @PostMapping("doSubmit")
    public int doSubmit(@RequestBody ValManualVoucher003Dto dto){
    	valManualVoucher003Service.doSubmit(dto);
    	return 1;
    	
    }
    
    /**
     * 获取投资目的
     * 
     * @return 投资目的下拉框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getInvAim")
    public BokDetailIdDto getInvAim(@RequestBody Map<String, Object> params)
        throws Exception {
       return valManualVoucher003Service.getInvAim(params);
    }
}
