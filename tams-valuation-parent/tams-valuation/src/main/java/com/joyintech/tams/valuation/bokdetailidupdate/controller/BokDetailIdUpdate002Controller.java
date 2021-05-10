package com.joyintech.tams.valuation.bokdetailidupdate.controller;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import com.joyintech.framework.util.*;
import com.joyintech.tams.valuation.common.auxiliary.dto.ChangeSubjectNoResult;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
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
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.bokdetailidupdate.dto.BokDetailIdUpdate002CheckValidatorDto;
import com.joyintech.tams.valuation.bokdetailidupdate.dto.BokDetailIdUpdate003CheckValidatorDto;
import com.joyintech.tams.valuation.bokdetailidupdate.dto.BokDetailIdUpdate003Dto;
import com.joyintech.tams.valuation.bokdetailidupdate.dto.ResultData;
import com.joyintech.tams.valuation.bokdetailidupdate.service.BokDetailIdUpdate002Service;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;


/**
 * 账务明细代码更新
 *
 * @author 吴洁
 * @createTime 2020-12-29
 */
@Controller
@PageId("BokDetailIdUpdate002")
@RequestMapping("/valuation/bokdetailidupdate/BokDetailIdUpdate002")
public class BokDetailIdUpdate002Controller extends BaseController {
	
	@Autowired
	private ValAccountHandle001Service valAccountHandle001Service;
	
	@Autowired
	private BokDetailIdUpdate002Service bokDetailIdUpdate002Service;
	
    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model,DictCode.收益类型,DictCode.投资目的,DictCode.币种);
        
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.金融产品类型, null);
        List<SelectOptionDto> options = selectDto.getOptions();
        if(options==null){
        	options = new ArrayList<SelectOptionDto>();
        }
        selectDto = this.getSelectDataFromDataDic(DictCode.资金账户类型, null);
        if(selectDto.getOptions()!=null && selectDto.getOptions().size()>0){
        	options.addAll(selectDto.getOptions());
        }
        model.addAttribute(DictCode.金融产品类型.getValue(), JsonUtils.objectArray2Json(options.toArray()));
        
        return super.init(model);
    }
    
    
    /**
     * 账套代码下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getBooksetId")
    @ResponseBody
    public Page<SelectOptionDto> getBooksetId() {
        return valAccountHandle001Service.getBookSetId2(RequestUtil.getPageParam()); 
    }
    
    /**
     * 参数调整check
     * @param dto
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping("getBokdetailId")
    public AjaxResult getBokdetailId(@Valid @RequestBody BokDetailIdUpdate002CheckValidatorDto dto,
                            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult ajaxResult = new AjaxResult();
        BokDetailIdUpdate003Dto bokDetailIdUpdate003Dto = new BokDetailIdUpdate003Dto();
        BeanUtil.copyPropertiesIgnoreNull(bokDetailIdUpdate003Dto,dto);
        ajaxResult.setData(bokDetailIdUpdate002Service.getBokdetailId(bokDetailIdUpdate003Dto));
        return ajaxResult;
    }
    
    /**
     * 参数调整check
     * @param dto
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping("doSumit")
    public AjaxResult doSumit(@Valid @RequestBody BokDetailIdUpdate003CheckValidatorDto dto,
                            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        BokDetailIdUpdate003Dto bokDetailIdUpdate003Dto = new BokDetailIdUpdate003Dto();
        BeanUtil.copyPropertiesIgnoreNull(bokDetailIdUpdate003Dto,dto);
        AjaxResult ajaxResult = new AjaxResult();
        try{
        	ajaxResult.setData(bokDetailIdUpdate002Service.doSubmit(bokDetailIdUpdate003Dto));
        }catch(Exception e){
        	ResultData resultData = new ResultData();
        	resultData.setRstCode("2222");
        	resultData.setRstDesc("调整失败！失败原因："+e.getMessage());
        	ajaxResult.setData(resultData);
        }
        return ajaxResult;
    }
    /**
     * check
     * @param dto
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody BokDetailIdUpdate003CheckValidatorDto dto,
                              BindingResult result) {
            return new AjaxResult(result);
    }
    /**
     * scriptGeneration
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("scriptGeneration")
    public ResponseEntity<byte[]> scriptGeneration() throws IOException {
        BokDetailIdUpdate003CheckValidatorDto dto = new BokDetailIdUpdate003CheckValidatorDto();
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        dto.setBusiId(paraMap.get("busiId") == null ? "" : paraMap.get("busiId").toString());
        dto.setBusiDetailType(paraMap.get("busiDetailType") == null ? "" : paraMap.get("busiDetailType").toString());
        dto.setVdate(paraMap.get("vdate") == null ? null : DateUtil.parse(paraMap.get("vdate").toString()));
        dto.setProfitType(paraMap.get("profitType") == null ? "" : paraMap.get("profitType").toString());
        dto.setInvAim(paraMap.get("invAim") == null ? "" : paraMap.get("invAim").toString());
        dto.setCcy(paraMap.get("ccy") == null ? "" : paraMap.get("ccy").toString());
        dto.setAcctNoOld(paraMap.get("acctNoOld") == null ? "" : paraMap.get("acctNoOld").toString());
        dto.setBokdetailIdOld(paraMap.get("bokdetailIdOld") == null ? "" : paraMap.get("bokdetailIdOld").toString());
        dto.setBooksetId(paraMap.get("booksetId") == null ? "" : paraMap.get("booksetId").toString());

        String sqlName = "账务明细代码调整执行脚本";
        sqlName = sqlName + DateUtil.getSysDate();
        BokDetailIdUpdate003Dto bokDetailIdUpdate003Dto = new BokDetailIdUpdate003Dto();
        BeanUtil.copyPropertiesIgnoreNull(bokDetailIdUpdate003Dto,dto);
        ChangeSubjectNoResult resultData = bokDetailIdUpdate002Service.scriptGeneration(bokDetailIdUpdate003Dto);
        return bokDetailIdUpdate002Service.downloadSqlFile(resultData.getExecSql(),sqlName);
    }

    
    /**
     * 检索资产代码
     * 
     * @return 资产代码下拉框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getBusiId")
    public List<SuggestDto> getBusiId() {
        // 业务类型
        String finprodType = this.getParam("busiDetailType");
        // 帐套代码
        String booksetId = this.getParam("booksetId");
        String param = this.getParam("param");
        return bokDetailIdUpdate002Service.getBusiId(finprodType, booksetId, param);
    }
    
}
