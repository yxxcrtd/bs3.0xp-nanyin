/*
 * 文件名：TrdStockBonus001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：panbin
 * 修改时间：2019年6月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.period.trdoptionduration.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.period.trdlendduration.dto.TrdLendDuration001Dto;
import com.joyintech.tams.invest.period.trdoptionduration.dto.OptionDurationDetailDto;
import com.joyintech.tams.invest.period.trdoptionduration.dto.TrdOptionDurationDto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdoptionduration.service.TrdOptionDuration001Service;
import com.joyintech.tams.invest.period.trdoptionduration.service.TrdOptionDuration002Service;
import com.joyintech.tams.invest.period.trdstockbonus.dto.TrdStockBonus001Dto;
import com.joyintech.tams.invest.period.trdstockbonus.service.TrdStockBonus001Service;
import com.joyintech.tams.invest.period.trdstockbonus.service.TrdStockBonus002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 期权存续期
 * @author panbin
 * @version 2019年12月25日
 * @see TrdOptionDuration001Controller
 */
@PageId("TrdOptionDuration001")
@RequestMapping("/invest/period/trdoptionduration/TrdOptionDuration001")
@Controller
public class TrdOptionDuration001Controller extends BaseController{

	/**
     * PeriodCommonService
     */
    @Autowired
    private PeriodCommonService periodCommonService;

    @Autowired
    private TrdOptionDuration002Service trdOptionDuration002Service;
    
    @Autowired
    private TrdStockBonus001Service trdStockBonus001Service;

    @Autowired
    private TrdOptionDuration001Service trdOptionDuration001Service;
    
    @Autowired
    private TrdStockBonus002Service trdStockBonus002Service;
    private Model model;
    
    @Autowired
    private IPlatformDate iPlatformDate;

    /**
     * 画面初始化
     */
    @Override
    public String init(Model model) {
        super.setSelectOptionToModel(model,DictCode.交易方向,DictCode.行权方式,DictCode.行权状态,DictCode.结构类型);
        List<SelectOptionDto> listtype = new ArrayList<SelectOptionDto>();
        SelectOptionDto dto1 = new SelectOptionDto();
        dto1.setText("未行权");
        dto1.setId("01");
        SelectOptionDto dto2 = new SelectOptionDto();
        dto2.setText("提前行权");
        dto2.setId("T59");
        SelectOptionDto dto3 = new SelectOptionDto();
        dto3.setText("到期行权");
        dto3.setId("T60");
        SelectOptionDto dto4 = new SelectOptionDto();
        dto4.setText("放弃行权");
        dto4.setId("T61");
        listtype.add(dto1);
        listtype.add(dto2);
        listtype.add(dto3);
        listtype.add(dto4);
        model.addAttribute("XY_STATUS", JsonUtils.objectArray2Json(listtype.toArray()));

        // 平台日期
        model.addAttribute("mDate", DateUtil.getSysDate());
        model.addAttribute("xqzt","01");
        return super.init(model);
    }
    
    /**
     * 业务编号
     *
     * @author panbin
     * @date 2019年6月21日
     * @return Page<SelectOptionDto>
     */
    @ResponseBody
    @RequestMapping("getBusiCode")
    public Page<SelectOptionDto> getBusiCode() {
        return trdStockBonus001Service.getBusiCode();
    }
    
    /**
     * 分页查询 证券账户代码、名称
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getSecManageAcctId")
    public Page<SelectOptionDto> getSecManageAcctId() {
        return trdStockBonus001Service.getSecManageAcctId();
    }
    
    /**
     * 分页查询 资产代码
     *
     * @return 下拉框集合
     */
    @ResponseBody
    @RequestMapping("getFinprodMarketId")
    public Page<SelectOptionDto> getFinprodMarketId() {
        return trdStockBonus001Service.getFinprodMarketId();
    }
    
    /**
     * 分页查询 股票存续期列表数据
     * @return
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdOptionDurationDto> findPage() {
        Page<TrdOptionDurationDto> page = trdOptionDuration001Service.findPage(RequestUtil.getPageParam());
        return page;
    }

    /**
     * 分页查询 股票存续期列表数据
     * @return
     */
    @ResponseBody
    @RequestMapping("findPage22")
    public Page<TrdStockBonus001Dto> findPage22() {
        return trdStockBonus001Service.findPage(RequestUtil.getPageParam());
    }
    /**
     * 导出
     *
     * @return 导出的文件流
     * @throws Exception 文件到处异常
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return trdOptionDuration001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    
    
    /**
     * 撤销check
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) TrdOptionDurationDto dto,
                                  BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 
     * TODO
     * @return AjaxResult
     * @param dto
     * @param br
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult revoke(@RequestBody @Validated({
            ValidateGroupDelete.class}) TrdOptionDurationDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("statusValidStr", dto.getStatusValidStr());
        paramMap.put("busiCode", dto.getpTradeId());
        paramMap.put("finprodId", dto.getFinprodId());
        OptionDurationDetailDto tempDto = trdOptionDuration002Service.getMyEntity(paramMap);
      //update zhangweiqi 2020-02-12 流程中显示原编号begin
        if(tempDto.getZfDate()==null)
        tempDto.setZfDate(tempDto.getXqDate());
        tempDto.setBussTrdTransferOrder(periodCommonService.selectPayOrders(dto.getpTradeId()));
        /*for( BussTrdTransferOrderDto ord: tempDto.getBussTrdTransferOrder()){
                 ord.setOrderId(ord.getOrderId()+"R");
        }*/
      //update zhangweiqi 2020-02-12 流程中显示原编号end
      
        tempDto.setMode(OperationType.REVOKE.toString());
        tempDto.setpTradeId(tempDto.getBusiCode());
        //tempDto.setBusiCode(tempDto.getBusiCode() + 'R');
        tempDto.setTotleTradeId(tempDto.getBusiCode());
        tempDto.setActPortfolioId(tempDto.getActPortfolioId());
        tempDto.setOperateType("revoke");
        ProcessParameter<OptionDurationDetailDto> processParameter = new ProcessParameter<OptionDurationDetailDto>();
        WorkFlowParameter<OptionDurationDetailDto> params = new WorkFlowParameter<OptionDurationDetailDto>();
        params.setBusinessCode(tempDto.getTotleTradeId());
      /*  if("FQXQ".equals(dto.getXqType())){
            params.setPageId("TrdOptionDuration003");
        }else{
            params.setPageId("TrdOptionDuration002");
        }*/
        //params.setPageId("TrdLendDuration002");
        params.setElementId(dto.getElementId());
        if(dto.getBusiCode().startsWith("QQDQ")){
            params.setPageId("TrdOptionDuration002");
            params.setBusinessType(DictCodeListExt2.共通业务类型_期权存续期到期交易.getValue());
            params.setComment(entity -> WorkFlowUtil.concatComment(
                    "交易编号:" + entity.getBusiCode() + "(" + SysDataUtil.getNameFromDataDict(DictCode.共通业务类型,
                            DictCodeListExt2.共通业务类型_期权存续期到期交易.getValue()) + ")"));
        }else if(dto.getBusiCode().startsWith("TQXQ")){
            params.setPageId("TrdOptionDuration002");
            params.setBusinessType(DictCodeListExt2.共通业务类型_期权存续期提前行权交易.getValue());
            params.setComment(entity -> WorkFlowUtil.concatComment(
                    "交易编号:" + entity.getBusiCode() + "(" + SysDataUtil.getNameFromDataDict(DictCode.共通业务类型,
                            DictCodeListExt2.共通业务类型_期权存续期提前行权交易.getValue()) + ")"));
        }else{
            params.setPageId("TrdOptionDuration003");
            params.setBusinessType(DictCodeListExt2.共通业务类型_期权存续期放弃行权交易.getValue());
            params.setComment(entity -> WorkFlowUtil.concatComment(
                    "交易编号:" + entity.getBusiCode() + "(" + SysDataUtil.getNameFromDataDict(DictCode.共通业务类型,
                            DictCodeListExt2.共通业务类型_期权存续期放弃行权交易.getValue()) + ")"));
        }
        //params.setBusinessType(DictCodeList.共通业务类型_拆借存续期交易.getValue());
        // 设置流程业务说明, entity即传入的dto
       /* params.setComment(item -> WorkFlowUtil.concatComment(getMessage("TrdLendDuration001.tradeId") + GlobalConstant.COLON
                        + item.getTotleTradeId() + GlobalConstant.OPEN_PARENTHESIS,
                DictCodeList.共通业务类型_拆借存续期交易.getCodeName() + GlobalConstant.CLOSE_PARENTHESIS));*/
        processParameter.setWorkFlowParameter(params);
//        params.setIsNeedWorkflow(item -> StringUtil.equals(item.getCapiIncomeFeature(), DictCodeListExt1.本金收益特征_保本.getValue()));
        String msg = trdOptionDuration002Service.start(processParameter, tempDto);
        return new AjaxResult(msg);
    }
    
    
}
