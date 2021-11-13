/*
 * 文件名：PtlActivityPeriod002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlactivityperiod.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
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
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.portfolio.ptlactivityperiod.dto.PtlActivityPeriod001Dto;
import com.joyintech.tams.invest.portfolio.ptlactivityperiod.service.PtlActivityPeriod001Service;
import com.joyintech.tams.invest.portfolio.ptlactivityperiod.service.PtlActivityPeriod002Service;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

/**
 * 活期结息查询
 * @author fanshuanghu
 * @version 1.0 2018年9月19日
 * @see PtlActivityPeriod004Controller
 * @since 1.0
 */
@Controller
@PageId("PtlActivityPeriod004")
@RequestMapping("/invest/portfolio/ptlactivityperiod/PtlActivityPeriod004")
public class PtlActivityPeriod004Controller extends BaseController{
    /**
     * 注入活期结息Service
     */
    @Autowired
    public PtlActivityPeriod002Service ptlActivityPeriod002Service;
    /**
     * 注入活期结息001Service
     */
    @Autowired
    public PtlActivityPeriod001Service ptlActivityPeriod001Service;
    /**
     * 注入业务主键生成工具类
     */
    @Autowired(required =false)
    public BaseGetBusinessId baseGetBussinessId;
    
    @Autowired
    private IPlatformDate platformDateImpl;
    
    /**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.币种, DictCode.交易所基金类型, DictCode.交易方向,
                DictCode.交易场所, DictCode.基金类资产类型);
        String operateType = getParam("operateType");
        if (StringUtil.equals(operateType, "import")) {
            String tempList = getParam("tempList");
            List<PtlActivityPeriod001Dto> PtlActivityPeriod001DtoList = ptlActivityPeriod002Service.getData(tempList);
            model.addAttribute("PtlActivityPeriod001DtoList", StringUtil.isEmptyList(
                    PtlActivityPeriod001DtoList) ? null : JsonUtils.object2Json(PtlActivityPeriod001DtoList));
        }
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate", DateUtil.format(sysDate));
        return super.init(model);
    }

    /**
     * 画面点击保存按钮时check
     * 
     * @param dto 实体类
     * @param br
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) PtlActivityPeriod001Dto dto, BindingResult br) {
        if (br != null && br.hasErrors()) {
            return new AjaxResult(br);
        }
        
        String str = "";
        if(StringUtil.isNotEmpty(dto.getBusiAcctNo())) {
            for(String busiAcctNo:StringUtil.splitToList(",", dto.getBusiAcctNo())) {
                List<PtlActivityPeriod001Dto> newDetailsList = new ArrayList<PtlActivityPeriod001Dto>();
                for(PtlActivityPeriod001Dto detailsDto:dto.getDetailsList()) {
                    if(StringUtil.equals(busiAcctNo, detailsDto.getBusiAcctNo())) {
                        newDetailsList.add(detailsDto);
                    }   
                }
                if(StringUtil.isEmptyList(newDetailsList)) {
                    if(StringUtil.isNotEmpty(str)) {
                        str = str +"," +busiAcctNo;
                    } else {
                        str = busiAcctNo;
                    }
                    
                }
            }

        }

        if(StringUtil.isNotEmpty(str)) {
            return new AjaxResult(str + getMessage("PTL_COM_E_0030"));
        }
        return commonCheck(br, dto.getOperateType());  
    }
    
    /**
     * 保存
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) PtlActivityPeriod001Dto dto, BindingResult br) throws WorkFlowException {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        
        String msg = null;
        if(StringUtil.isNotEmpty(dto.getBusiAcctNo())) {
            for(String busiAcctNo:StringUtil.splitToList(",", dto.getBusiAcctNo())) {
                List<PtlActivityPeriod001Dto> newDetailsList = new ArrayList<PtlActivityPeriod001Dto>();
                BigDecimal sumActualTradeAmt = BigDecimal.ZERO;
                for(PtlActivityPeriod001Dto detailsDto:dto.getDetailsList()) {
                    if(StringUtil.equals(busiAcctNo, detailsDto.getBusiAcctNo())) {
                        newDetailsList.add(detailsDto); 
                        //如果含有子组合，将子组合从json数据转化成DTO，塞入ChildrenDetailsList，如果JSON数据为空，那么查询一下子组合数据，默认查询数据为实付数据
                        if(StringUtil.equals(detailsDto.getIsParentPortfolio(),DictCodeList.是否_是.getValue())) {
                            detailsDto.setChildrenDetailsList(new ArrayList<PtlActivityPeriod001Dto>());
                            if(StringUtil.isNotEmpty(detailsDto.getChildrenDetailsJson())) {
                                PtlActivityPeriod001Dto childrenDetailsDtoArr[] = (PtlActivityPeriod001Dto[])JsonUtils.json2Object(detailsDto.getChildrenDetailsJson(),
                                    PtlActivityPeriod001Dto[].class);  
                                for(PtlActivityPeriod001Dto childrenDetailsDto:childrenDetailsDtoArr){
                                    detailsDto.getChildrenDetailsList().add(childrenDetailsDto);
                                }
                            } else {
                                Map<String,Object> map = new HashMap<String,Object>();
                                map.put("portfolioId", detailsDto.getPortfolioId());
                                map.put("mdate", dto.getMdateStr());
                                map.put("acctNo", detailsDto.getAcctNo());
                                map.put("ccy", detailsDto.getCcy());
                                map.put("busiAcctNo", detailsDto.getBusiAcctNo());
                                map.put("acctName", detailsDto.getAcctName());
                                detailsDto.setChildrenDetailsList(ptlActivityPeriod002Service.findChildrenPortfolioDetails(map));
                            }  
                        }

                        sumActualTradeAmt = ArithmeticUtil.doAdd(sumActualTradeAmt,detailsDto.getActualTradeAmt());
                    }
                }
                

                if(!StringUtil.isEmptyList(newDetailsList)) {
                    PtlActivityPeriod001Dto newDto = new PtlActivityPeriod001Dto();
                    newDto.setBusiAcctNo(busiAcctNo);
                    newDto.setAcctName(newDetailsList.get(0).getAcctName());
                    newDto.setAcctNo(newDetailsList.get(0).getAcctNo());
                    newDto.setSettleDate(dto.getSettleDate());
                    newDto.setBankDep(dto.getBankDep());
                    newDto.setMdate(dto.getMdate());
                    newDto.setTradeAmt(sumActualTradeAmt);
                    newDto.setActualTradeAmt(sumActualTradeAmt);
                    newDto.setDetailsList(newDetailsList);
                    newDto.setCcy(newDetailsList.get(0).getCcy());
                    newDto.setElementId(dto.getElementId());
                    
                    List<BussTrdTransferOrderDto> orderList = new ArrayList<BussTrdTransferOrderDto>();
                    for(BussTrdTransferOrderDto bussTrdTransferOrderDto:dto.getBussTrdTransferOrderList()) {
                        if(StringUtil.equals(bussTrdTransferOrderDto.getRecAcctNo(),newDto.getAcctNo())){
                            orderList.add(bussTrdTransferOrderDto);
                        }
                    }
                    newDto.setBussTrdTransferOrderList(orderList);
                    BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter(); 
                    bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_活期结息);
                    newDto.setBusiNo(baseGetBussinessId !=null ? baseGetBussinessId.buildBussinessCode(bussinessCodeParameter):IDGenerator.generate());
                    ProcessParameter<PtlActivityPeriod001Dto> processParameter = new ProcessParameter<PtlActivityPeriod001Dto>();
                    WorkFlowParameter<PtlActivityPeriod001Dto> params = new WorkFlowParameter<PtlActivityPeriod001Dto>();
                    params.setPageId("PtlActivityPeriod002");
                    params.setElementId(newDto.getElementId());
                    params.setBusinessCode(item -> item.getBusiNo());
                    params.setBusinessType(DictCodeList.共通业务类型_活期结息.getValue());
                    // 设置流程业务说明, 账户名称 +"(" + 账户名称 + ")"+"-"+"账号"+"（"+"账号"+")"+"-"+"币种"+"("+"币种"+")"
                    params.setComment(
                        entity -> this.getMessage("PtlActivityPeriod001.acctName1") + "("
                            + entity.getAcctName() + ")" + "-"
                            + this.getMessage("PtlActivityPeriod001.acctNo") + "("
                            + entity.getBusiAcctNo() + ")" + "-"
                            + this.getMessage("PtlActivityPeriod001.ccy") + "("
                            + entity.getCcyStr() + ")" );
                    processParameter.setWorkFlowParameter(params);
                    msg = ptlActivityPeriod002Service.start(processParameter, newDto);
                }
            }
        }
        
        return new AjaxResult(msg);
    }

    /**
     * 导入excel数据时check
     *
     * @param fileName fileName
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkImportData")
    public AjaxResult checkImportData(String fileName) {
        return ptlActivityPeriod002Service.checkImportData(fileName);
    }


}
