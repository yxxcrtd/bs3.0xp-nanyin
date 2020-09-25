/*
 * 文件名：TrdStockBonus001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：panbin
 * 修改时间：2019年6月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.period.trdstockbonus.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.common.base.dao.WflTrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotalKey;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdstockbonus.dto.TrdStockBonus001Dto;
import com.joyintech.tams.invest.period.trdstockbonus.dto.TrdStockBonus002Dto;
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
import java.util.Map;

/**
 * 股票分红
 * @author panbin
 * @version 2019年6月18日
 * @see TrdStockBonus001Controller
 */
@PageId("TrdStockBonus001")
@RequestMapping("/invest/period/trdstockbonus/TrdStockBonus001")
@Controller
public class TrdStockBonus001Controller extends BaseController{

	/**
     * PeriodCommonService
     */
    @Autowired
    private PeriodCommonService periodCommonService;
    
    @Autowired
    private TrdStockBonus001Service trdStockBonus001Service;
    
    @Autowired
    private TrdStockBonus002Service trdStockBonus002Service;

    /**
     * 撤销时查询 是否有流程中数据
     */
    @Autowired
    private WflTrdFinProductDealTotalMapper wflTrdFinProductDealTotalMapper;
    /**
     * 画面初始化
     */
    @Override
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.交易流程状态,DictCode.通道类型
            ,DictCode.股票分红类型);
        model.addAttribute("TRADE_FLOW_STATUS_02", DictCodeListExt1.交易流程状态_有效.getValue());
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
    public Page<TrdStockBonus001Dto> findPage() {
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
        return trdStockBonus001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    
    
    /**
     * 撤销check
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) TrdStockBonus002Dto dto,
                                  BindingResult result) {
        //UPDATE BY XR 撤销时查询是否有流程中数据 start
        if(StringUtil.isNotEmpty(dto)&&dto.getOperateType().equals("revoke")){
            WflTrdFinProductDealTotalKey key = new WflTrdFinProductDealTotalKey();
            key.setTotleTradeId(dto.getBusiCode()+"R");
            key.setWorkflowStatus("P");
            WflTrdFinProductDealTotal trdFinProductDealTotal = wflTrdFinProductDealTotalMapper.selectByPrimaryKey(key);

            if (StringUtil.isNotEmpty(trdFinProductDealTotal)) {
                ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                errorList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0041")));
                AjaxResult ar = new AjaxResult();
                ar.setErrorList(errorList);
                return ar;
            }
        }
        //UPDATE BY XR 撤销时查询是否有流程中数据 end
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
            ValidateGroupDelete.class}) TrdStockBonus002Dto dto, BindingResult br)
            throws Exception {
    	if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("statusValid", dto.getStatusValid());
        paramMap.put("busiCode", dto.getBusiCode());
        //构建撤销dto
        TrdStockBonus002Dto dto002 = trdStockBonus002Service.findEffectiveInfo(paramMap);
        dto002.setTradeStatus(DictCodeListExt1.交易流程状态_撤销.getValue());
        dto002.setDividDetailInfoList(trdStockBonus002Service.findDividDetailInfo(dto.getBusiCode()));
        //构建流程参数
        ProcessParameter<TrdStockBonus002Dto> processParameter = new ProcessParameter<>();
	    WorkFlowParameter<TrdStockBonus002Dto> params = new WorkFlowParameter<>();
	    params.setPageId("TrdStockBonus002");
	    params.setElementId(dto.getElementId());
	    //模式为撤销
	    dto002.setMode(OperationType.REVOKE.toString());
	    dto002.setpTradeId(dto002.getBusiCode());
	    dto002.setBusiCode(dto002.getBusiCode()+'R');
	    dto002.setTotleTradeId(dto002.getBusiCode());
        dto002.setBusiType(DictCodeListExt2.共通业务类型_股票分红交易.getValue());
	    params.setBusinessCode(dto002.getBusiCode());
	    params.setBusinessType(DictCodeListExt2.共通业务类型_股票分红交易.getValue());
	    // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment(
        		this.getMessage("TrdStockBonus001.tradeId"),
                GlobalConstant.OPEN_PARENTHESIS + entity.getBusiCode()
                + GlobalConstant.CLOSE_PARENTHESIS,
        this.getMessage("TrdStockBonus001.finprodMarketName"),
        GlobalConstant.OPEN_PARENTHESIS + entity.getFinprodAbbr()
                + GlobalConstant.CLOSE_PARENTHESIS,
        this.getMessage("TrdStockBonus001.tradeDate"),
        GlobalConstant.OPEN_PARENTHESIS + entity.getTradeDateStr()
                + GlobalConstant.CLOSE_PARENTHESIS        ));
        processParameter.setWorkFlowParameter(params);
        String msg = trdStockBonus002Service.start(processParameter, dto002);
        
    	return new AjaxResult(msg);
    }
    
    
}
