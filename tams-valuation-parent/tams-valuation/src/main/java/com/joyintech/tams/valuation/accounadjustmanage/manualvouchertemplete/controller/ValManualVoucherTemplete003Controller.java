/*
 * 文件名：ValManualVoucher001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher003AddDto;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto.*;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.service.ValManualVoucherTemplete003Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.service.BaseCommonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 新建-手工凭证模板
 * 
 * @author sunshuaishuai
 * @version 2.0 2021年2月9日
 * @see ValManualVoucherTemplete003Controller
 * @since 2.0
 */
@Controller
@PageId("ValManualVoucherTemplete003")
@RequestMapping("/valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete003")
public class ValManualVoucherTemplete003Controller extends BaseController {

    @Autowired
    private ValManualVoucherTemplete003Service valManualVoucherTemplete003Service;

    @Autowired
    private InterfCommonService interfCommonService;

    @Autowired
    private BaseCommonService baseCommonService;

    private List<ValManualVoucherTemplete003AddDto> listAddDto = new ArrayList<ValManualVoucherTemplete003AddDto>();

    private String flag;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.币种, DictCode.借贷方向, DictCode.分录类型, DictCode.金融产品类型);
        // 获取业务类型
        // 获取 金融产品类型 数据字典
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.金融产品类型, null);
        List<SelectOptionDto> options = selectDto.getOptions();
        
        // 工作流ID
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);

        String operateType = this.getParam("operateType");
        
        // 判断是修改操作还是新建操作
        String tmpCode = getParam("tmpCode");
        if (StringUtil.isNotEmpty(tmpCode)) {
            ValManualVoucherTemplete001Dto entity = valManualVoucherTemplete003Service.getByTmpCode(tmpCode);
            model.addAttribute("entity", entity);
            // 借贷方向设一个默认值
            model.addAttribute("cdFlag", entity.getCdFlag());
        }
        else if(StringUtil.isNotEmpty(workflowId)){
            ValManualVoucherTemplete003Dto entity= valManualVoucherTemplete003Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", entity);
            model.addAttribute("tmpName", entity.getTmpName());
            model.addAttribute("workflowId", workflowId);
        }else if("save".equals(operateType)){
            String datas = this.getParam("datas");
            List<ValManualVoucher003AddDto> listdto = null;
            try {
                listdto = JsonUtils.json2List(datas, ValManualVoucher003AddDto.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<ValManualVoucherTemplete003AddDto> addDto = new ArrayList<ValManualVoucherTemplete003AddDto>();
            for(ValManualVoucher003AddDto dto : listdto){
                ValManualVoucherTemplete003AddDto valManualVoucherTemplete003Dto=new ValManualVoucherTemplete003AddDto();
                valManualVoucherTemplete003Dto.setSubjectNo(dto.getSubjectNo());
                valManualVoucherTemplete003Dto.setSubjectName(dto.getSubjectName());
                valManualVoucherTemplete003Dto.setCcy(dto.getCcy());
                valManualVoucherTemplete003Dto.setcAmt(dto.getcAmt());
                valManualVoucherTemplete003Dto.setdAmt(dto.getdAmt());
                valManualVoucherTemplete003Dto.setCdFlag(dto.getCdFlag());
                valManualVoucherTemplete003Dto.setHappenNumber(dto.getHappenNumber());
                addDto.add(valManualVoucherTemplete003Dto);
            }
            setListAddDto(addDto);
            setFlag("saveTmp");
            String obj =this.getParam("objParams");
            Object c= JsonUtils.json2Object(obj,ValManualVoucher003Dto.class);
            ValManualVoucher003Dto dto=(ValManualVoucher003Dto)c;
            ValManualVoucherTemplete003Dto entity =new ValManualVoucherTemplete003Dto();
            entity.setTmpCode("");
            entity.setTmpName("");
            entity.setRemark(dto.getBookRemark());
            entity.setRecordType(dto.getRecordType());
            entity.setBusiType(dto.getFinprodType());
            entity.setOperateType("new");

            model.addAttribute("entity",entity);
        }
        else {
            // 借贷方向设一个默认值
            model.addAttribute("cdFlag", DictCodeList.借贷方向_贷.getValue());
        }
        
        return super.init(model);
    }

    /**
     * 根据模板编号tmpCode获取对应相同编号的所有数据
     *
     * @return
     * @throws Exception 异常
     * @see
     */
    @RequestMapping("searchList")
    @ResponseBody
    public List<ValManualVoucherTemplete003AddDto> searchList() {
        // 模板编号
        String tmpCode = this.getParam("tmpCode");
        return valManualVoucherTemplete003Service.searchList(tmpCode);
    }

    /**
     * 点击新增一个空的分录行
     * 
     * @param dto 参数
     * @param result 校验结果
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("doAdd")
    public AjaxResult doAdd(@Valid @RequestBody ValManualVoucherTemplete003AddValidatorDto dto,
                            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setData(valManualVoucherTemplete003Service.initTableDto(dto));
        return ajaxResult;
    }
    
    /**
     * 确认校验
     * 
     * @param valManualVoucherTemplete003CheckValidatorDto 参数
     * @param result 校验结果
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("doCheck")
    public AjaxResult doCheck(@Valid @RequestBody  ValManualVoucherTemplete003CheckValidatorDto valManualVoucherTemplete003CheckValidatorDto,
                              BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult ajaxResult = new AjaxResult();
        return ajaxResult;
    }

    /**
     * 获取科目名称
     * 
     * @return 账套科目代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getSubjectNo")
    public AjaxResult getSubjectNo(@RequestBody ValManualVoucherTemplete003SubjectDto dto)
        throws Exception {
        String subjectNo = dto.getSubjectNo();
        if ( StringUtil.isNotEmpty(subjectNo)) {
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.setData(
                    valManualVoucherTemplete003Service.getSubjectNo(dto));
            return ajaxResult;
        }
        else {
            return new AjaxResult();
        }
    }

    /**
     * 手工凭证 - 新建 - 走流程
     * 
     * @param dto 参数
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "doSave", method= RequestMethod.POST)
    public AjaxResult doSave(@RequestBody @Valid ValManualVoucherTemplete003Dto dto, BindingResult br) {
        
        if(br.hasErrors()){
            return new AjaxResult(br);
        }

        //设置主键
        if(!StringUtil.isNotEmpty(dto.getTmpCode())){
            Date sysDate= DateUtil.platDateFromCache();
            dto.setTmpCode(getTmpCode("MB", sysDate, "A"));
        }

        if(dto!=null){
            if( valManualVoucherTemplete003Service.save(dto)){
                if("0".equals(dto.getPageModel())){
                    return new AjaxResult("新建成功");
                }else {
                    return new AjaxResult("修改成功");
                }
            }
        }
        if("0".equals(dto.getPageModel())){
            return new AjaxResult("新建失败");
        }else {
            return new AjaxResult("修改失败");
        }
    }
    
    
    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @param operateType 操作类型
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
   public AjaxResult check(@RequestBody @Valid ValManualVoucherTemplete003Dto dto, BindingResult result, String operateType) {
        return commonCheck(result, operateType);
    }
    
    /**
     * 根据流程id，获取对应的凭证新建时的分录信息
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("/getValManualVoucherTemplete003AddDtoList")
    @ResponseBody
    public List<ValManualVoucherTemplete003AddDto> getValManualVoucherTemplete003AddDtoList() {
        
        List<ValManualVoucherTemplete003AddDto> returnList = new ArrayList<>();
        
        Map<String,Object> map= RequestUtil.getPageParam();
        
        String workflowId = (String)map.get("workflowId");

        String tmpCode = String.valueOf(map.get("tmpCode"));

        String flag=getFlag();
        
        //根据流程id获取对应的凭证新建时的分录信息
        if(workflowId!=null && workflowId.length()>0){
            ValManualVoucherTemplete003Dto entity= valManualVoucherTemplete003Service.getBussinessEntityByWorkflowId(workflowId);
            returnList = entity.getListAddDto();
        }else if(!StringUtil.isNotEmpty(workflowId) && tmpCode!=null && tmpCode.length()>0){
            List<ValManualVoucherTemplete003AddDto> entity = valManualVoucherTemplete003Service.searchList(tmpCode);
            returnList = entity;
        }else if(flag!="" &&"saveTmp".equals(flag)){
            returnList=getListAddDto();
            setFlag("");
        }
        else{
            // 若为空给予一个空list，不能为null，否则前台报错。
            returnList = new ArrayList<ValManualVoucherTemplete003AddDto>();
        }
        return returnList;
    }

    /**
     * 
     * 科目号下拉框
     * 
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getAllSubjectNo")
    public List<SelectOptionDto> getAllSubjectNo(){
        Map<String,Object> para = RequestUtil.getPageParam();
        para.put("level3", DictCodeListExt1.科目级别_3级.getValue());
        List<SelectOptionDto> dtolist = valManualVoucherTemplete003Service.querySubctNo(para);
        return dtolist;
    }

    /**
     * 编号生成
     */
    public String getTmpCode(String str, Date date, String flag) {
        int  sequences=0;
        // 查询数据库sequences
        if ("A".equals(flag)) {
            sequences = valManualVoucherTemplete003Service.queryPdSequences();
        }
        // 组装8位序列号 不足补0
        String sequence = StringUtils.leftPad(String.valueOf(sequences), 6, '0');
        // 日期格式化yyymmdd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String formatDate = sdf.format(date);

        String tmpcode = str + formatDate + sequence;
        return tmpcode;
    }

    public List<ValManualVoucherTemplete003AddDto> getListAddDto() {
        return listAddDto;
    }

    public void setListAddDto(List<ValManualVoucherTemplete003AddDto> listAddDto) {
        this.listAddDto = listAddDto;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
