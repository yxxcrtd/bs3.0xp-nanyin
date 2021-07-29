package com.joyintech.tams.invest.entrust.etrautomanagementaccount.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.entrust.common.aop.EntrustRdsCommonAop;
import com.joyintech.tams.invest.entrust.etrautomanagementaccount.dto.EtrAutoManagementAccountDto;
import com.joyintech.tams.invest.entrust.etrautomanagementaccount.service.EtrAutoManagementAccountService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@PageId("EtrAutoManagementAccount002")
@RequestMapping("/invest/entrust/etrautomanagementaccount/EtrAutoManagementAccount002")
public class EtrAutoManagementAccount002Controller extends BaseController {
    @Autowired
    private EtrAutoManagementAccountService etrAutoManagementAccountService;
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向);
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate", sysDate);
        String workflowId = getParam("workflowId");
        String entrustId = getParam("entrustId");
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());

        if (!WorkFlowUtil.isNotFromToDoList()&&StringUtil.isNotEmpty(workflowId)) {
            model.addAttribute("workflowId", workflowId);
            EtrAutoManagementAccountDto etrAutoManagementAccountDto = etrAutoManagementAccountService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("type6Str", etrAutoManagementAccountDto.getType6Str ());
            model.addAttribute("type7Str", etrAutoManagementAccountDto.getType7Str ());
            model.addAttribute("portfolioIdChange", etrAutoManagementAccountDto.getPortfolioIdChange ());
            model.addAttribute("entity", etrAutoManagementAccountDto);
        }
        else if (StringUtil.isNotEmpty(entrustId) && WorkFlowUtil.isNotFromToDoList()) {
            EtrAutoManagementAccountDto dto = etrAutoManagementAccountService.selectByKey(entrustId);
//            model.addAttribute("portfolioIdChangeDb", dto.getPortfolioId ());
//            String portfolioId = dto.getPortfolioId ().substring(dto.getPortfolioId ().indexOf("_")+1,dto.getPortfolioId ().length ());
//            dto.setPortfolioId (portfolioId);金融产品类型_投管用_计划类资产
            model.addAttribute("portfolioIdChange",DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue()+GlobalConstant.UNDER_LINE+ dto.getPortfolioId ());
            model.addAttribute(GlobalConstant.ENTITY, dto);

        }
        else {
            EtrAutoManagementAccountDto entity = new EtrAutoManagementAccountDto();
            entity.setEntrustDate (sysDate);
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }


        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) EtrAutoManagementAccountDto dto, BindingResult result ) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody EtrAutoManagementAccountDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }

        List<EtrAutoManagementAccountDto> dtoList = dto.getSelectedItem();

        if(!StringUtil.isNotEmpty(dto.getEntrustId())){
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessType(DictCodeListExt3.共通业务类型_自主管理专户投资指令);
            String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator.generate();
            dto.setEntrustId(code);
            dto.getSelectedItem ().forEach (item -> {
                item.setEntrustId (code+item.getPortfolioId ());
                item.setPs (dto.getPs ());
                item.setMdate(DateUtil.addYear(dto.getEntrustDate(),1));//add by zhangweiqi 添加指令到期日 默认指令日期 加一年
            });
        }else{
        	 String code=dto.getEntrustId();
        	 dto.getSelectedItem ().forEach (item -> {
                 item.setEntrustId (code+item.getPortfolioId ());
                 item.setPs (dto.getPs ());
                 item.setMdate(DateUtil.addYear(dto.getEntrustDate(),1));//add by zhangweiqi 添加指令到期日 默认指令日期 加一年
             });
        }



        // ----------add by yushilong 20200721 预占功能 start----------
        if (StringUtil.equals(BussinessConstant.SUBMIT, dto.getOperateType())||StringUtil.equals("update", dto.getOperateType())) {
            AjaxResult ar = EntrustRdsCommonAop.doListPreOccupy(dto.getSelectedItem(),
                    dto.getAssetType(),
                    BussinessDictCodeListRds.接口系统ID_预占批量接口.getValue(),
                    false, null);
            if (StringUtil.isNotEmpty(ar)) {
                return ar;
            }
          }
        
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeListExt3.共通业务类型_自主管理专户投资指令);
        ProcessParameter<EtrAutoManagementAccountDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<EtrAutoManagementAccountDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 授权代码作为业务编号
        String businessCode = dto.getPortfolioId ();
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeListExt3.共通业务类型_自主管理专户投资指令.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("EtrAutoManagementAccount002.portfolioId"),
                    GlobalConstant.OPEN_PARENTHESIS, businessCode, GlobalConstant.CLOSE_PARENTHESIS,
                    getMessage("EtrAutoManagementAccount002.finprodId"), GlobalConstant.OPEN_PARENTHESIS,
                    entity.getFinprodMarketId (), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = etrAutoManagementAccountService.start(processParameter, dto);
        return new AjaxResult(msg);
    }



    @ResponseBody
    @RequestMapping("/getSelectFinprodMarketId")
    public Page<SelectOptionDto> getSelectFinprodMarketId()
            throws Exception {
        Page<SelectOptionDto> temp = etrAutoManagementAccountService.getSelectFinprodMarketId(RequestUtil.getPageParam());
        return temp;
    }


    @ResponseBody
    @RequestMapping("/portfolioIdChange")
    public List<EtrAutoManagementAccountDto> selectForPage(){
        List<EtrAutoManagementAccountDto> page = etrAutoManagementAccountService.portfolioIdChange(
                RequestUtil.getPageParam());

        return page;
    }

    @ResponseBody
    @RequestMapping("findNewPage")
    public Page<EtrAutoManagementAccountDto> selectNewForPage(){
        Page<EtrAutoManagementAccountDto> page = etrAutoManagementAccountService.selectNewForPage(
                RequestUtil.getPageParam());
        return page;
    }


    @ResponseBody
    @RequestMapping("/finprodMarketIdChange")
    public Map<String, Object> finprodMarketIdChange(@RequestBody Map<String,Object> params)
            throws Exception {

        String finprodType2key = StringUtil.getOrElse(params.get("finprodType2"),"");

        String type6key = StringUtil.getOrElse(params.get("type6"),"");

        String type7key = StringUtil.getOrElse(params.get("type7"),"");


        Map<String, Object> param = new HashMap<> ();
        List<SelectOptionDto> resultList = new ArrayList<SelectOptionDto> ();

        Map<String, Object> result = new HashMap<> ();

        SelectOptionDto selectOptionDto = new SelectOptionDto();


        param.put ("finprodType2",finprodType2key);

        List<SelectOptionDto> tempType6 =
                etrAutoManagementAccountService.finprodMarketIdChange(param);
        if(tempType6 != null){
            List<SelectOptionDto> listType6 = tempType6;
            for(int i = 0; i < listType6.size (); i++){
                if(type6key.equals (listType6.get (i).getId ())){
                    selectOptionDto.setId("type6");
                    selectOptionDto.setText(listType6.get (i).getText ());
                    resultList.add (selectOptionDto);
                    result.put ("type6",listType6.get (i).getText ());
                }
            }
        }

        List<SelectOptionDto> tempType7 =
                etrAutoManagementAccountService.finprodMarketIdType7Change(param);
        if(tempType7 != null){
            List<SelectOptionDto> listType7 = tempType7;
            for(int i = 0; i < listType7.size (); i++){
                if(type7key.equals (listType7.get (i).getId ())){
                    selectOptionDto.setId("type7");
                    selectOptionDto.setText(listType7.get (i).getText ());
                    resultList.add (selectOptionDto);
                    result.put ("type7",listType7.get (i).getText ());
                }
            }
        }

        return result;
    }

}
