package com.joyintech.tams.invest.basicdata.futuresdepositpercent.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.basicdata.futuresdepositpercent.dto.FuturesDepositPercentDto;
import com.joyintech.tams.invest.basicdata.futuresdepositpercent.service.FuturesDepositPercentService;
import com.joyintech.tams.invest.basicdata.mstsecuritiesaccount.service.MstSecuritiesAccountService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname FuturesDepositPercent002Controller
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/12 14:30 
 * @version 1.0 
 */
@Controller
@PageId("FuturesDepositPercent002")
@RequestMapping("/invest/basicdata/futuresdepositpercent/FuturesDepositPercent002")
public class FuturesDepositPercent002Controller extends BaseController {

    @Autowired
    private FuturesDepositPercentService futuresDepositPercentService;
    
    @Autowired
    private MstSecuritiesAccountService mstSecuritiesAccountService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.合约标的,DictCode.期货类型);
        String workflowId = getParam("workflowId");
        String futuresType = getParam("futuresType");
        String futuresContractId = getParam("futuresContractId");
        String futuresCompany = getParam("futuresCompany");
        String effDate = getParam("effDate");
        //流程代办
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            FuturesDepositPercentDto  futuresDepositPercentDto = futuresDepositPercentService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", futuresDepositPercentDto);
        }
        //不来自代办,修改页面
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(futuresType)){
            FuturesDepositPercentDto futuresDepositPercentDto = new FuturesDepositPercentDto();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("futuresType",futuresType);
            map.put("futuresContractId",futuresContractId);
            map.put("futuresCompany",futuresCompany);
            map.put("effDate",effDate);
            futuresDepositPercentDto = futuresDepositPercentService.selectByPrimaryKey(map);
            model.addAttribute("entity", futuresDepositPercentDto);
        }
        //新建页面
        else {
            FuturesDepositPercentDto futuresDepositPercentDto = new FuturesDepositPercentDto();
            model.addAttribute("entity", futuresDepositPercentDto);
        }
        return super.init(model);
    }



    /**
     *
     * @Description //新建修改check
     * @Param [dto, result]
     * @return com.joyintech.framework.ajax.AjaxResult
     * @version 1.0
     * @see
    **/
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) FuturesDepositPercentDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     *
     * @Description //根据主键查询Dto
     * @Param [map]
     * @return com.joyintech.tams.invest.basicdata.futuresdepositpercent.dto.FuturesDepositPercentDto
     * @version 1.0
     * @see
    **/
    public FuturesDepositPercentDto selectByPrimaryKey(Map<String,Object> map){
        return futuresDepositPercentService.selectByPrimaryKey(map);
    }


    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody FuturesDepositPercentDto dto, BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        WorkFlowParameter<FuturesDepositPercentDto> params = new WorkFlowParameter<FuturesDepositPercentDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getFuturesCompany()+"-"+ DateUtil.format(dto.getEffDate()));
        params.setBusinessType(DictCodeListExt2.共通业务类型_期货保证金比例设置.getValue());
        FuturesDepositPercentDto futuresDto = setDescription(dto);
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> SpringContextUtil.getTextValue("FuturesDepositPercent001.futuresType")+"("+ futuresDto.getFuturesTypeName() +")"+SpringContextUtil.getTextValue("FuturesDepositPercent001.futuresCompany")+"("+futuresDto.getFuturesCompanyName()+")"
                        +SpringContextUtil.getTextValue("FuturesDepositPercent001.effDate")+"("+ DateUtil.format(entity.getEffDate())+")"+SpringContextUtil.getTextValue("FuturesDepositPercent001.futuresContractId")+"("+futuresDto.getFuturesContractIdName()+")"
        );
        //给参数变成ProcessParameter而非WorkFlowParameter
        ProcessParameter<FuturesDepositPercentDto> parameter = new ProcessParameter<FuturesDepositPercentDto>();
        parameter.setWorkFlowParameter(params);
        String msg = futuresDepositPercentService.start(parameter, dto);
        return new AjaxResult(msg);


    }

    public FuturesDepositPercentDto setDescription(FuturesDepositPercentDto futuresDepositPercentDto){
    	List<SelectOptionDto> temp = mstSecuritiesAccountService.getFuturesCompany(futuresDepositPercentDto.getFuturesCompany());
    	if(StringUtil.isNotEmpty(temp)){
    		for(SelectOptionDto selectOptionDto : temp){
    			if(StringUtil.equals(selectOptionDto.getId(),futuresDepositPercentDto.getFuturesCompany())){
    				futuresDepositPercentDto.setFuturesCompanyName(selectOptionDto.getText());
    			}
    		}
    	}
    	if(StringUtil.equals(futuresDepositPercentDto.getFuturesType(), DictCodeListExt3.期货类型_国债期货.getValue())){
    		futuresDepositPercentDto.setFuturesTypeName(DictCodeListExt3.期货类型_国债期货.getCodeName());
    	}else if(StringUtil.equals(futuresDepositPercentDto.getFuturesType(), DictCodeListExt3.期货类型_股指期货.getValue())){
    		futuresDepositPercentDto.setFuturesTypeName(DictCodeListExt3.期货类型_股指期货.getCodeName());
    	}
    	if(StringUtil.equals(futuresDepositPercentDto.getFuturesContractId(),DictCodeListExt3.合约标的_2年期国债.getValue())){
    		futuresDepositPercentDto.setFuturesContractIdName(DictCodeListExt3.合约标的_2年期国债.getCodeName());
    	}else if (StringUtil.equals(futuresDepositPercentDto.getFuturesContractId(),DictCodeListExt3.合约标的_5年期国债.getValue())){
    		futuresDepositPercentDto.setFuturesContractIdName(DictCodeListExt3.合约标的_5年期国债.getCodeName());
    	}else if (StringUtil.equals(futuresDepositPercentDto.getFuturesContractId(),DictCodeListExt3.合约标的_10年期国债.getValue())){
    		futuresDepositPercentDto.setFuturesContractIdName(DictCodeListExt3.合约标的_10年期国债.getCodeName());
    	}else if (StringUtil.equals(futuresDepositPercentDto.getFuturesContractId(),DictCodeListExt3.合约标的_沪深300指数.getValue())){
    		futuresDepositPercentDto.setFuturesContractIdName(DictCodeListExt3.合约标的_沪深300指数.getCodeName());
    	}else if (StringUtil.equals(futuresDepositPercentDto.getFuturesContractId(),DictCodeListExt3.合约标的_中证500指数.getValue())){
    		futuresDepositPercentDto.setFuturesContractIdName(DictCodeListExt3.合约标的_中证500指数.getCodeName());
    	}else if (StringUtil.equals(futuresDepositPercentDto.getFuturesContractId(),DictCodeListExt3.合约标的_上证50指数.getValue())){
    		futuresDepositPercentDto.setFuturesContractIdName(DictCodeListExt3.合约标的_上证50指数.getCodeName());
    	}
    	return futuresDepositPercentDto;
    }
}
