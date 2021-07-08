
package com.joyintech.tams.invest.period.trdstockconnectbonus.controller;

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
import com.joyintech.tams.invest.period.trdstockconnectbonus.dto.TrdStockConnectBonus001Dto;
import com.joyintech.tams.invest.period.trdstockconnectbonus.dto.TrdStockConnectBonus002Dto;
import com.joyintech.tams.invest.period.trdstockconnectbonus.service.TrdStockConnectBonus001Service;
import com.joyintech.tams.invest.period.trdstockconnectbonus.service.TrdStockConnectBonus002Service;
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
 * 港股通股票分红
 * @see TrdStockConnectBonus001Controller
 */
@PageId("TrdStockConnectBonus001")
@RequestMapping("/invest/period/trdstockconnectbonus/TrdStockConnectBonus001")
@Controller
public class TrdStockConnectBonus001Controller extends BaseController{

	/**
     * 存续期共通
     */
    @Autowired
    private PeriodCommonService periodCommonService;
    
    @Autowired
    private TrdStockConnectBonus001Service trdStockConnectBonus001Service;
    
    @Autowired
    private TrdStockConnectBonus002Service trdStockConnectBonus002Service;

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
        super.setSelectOptionToModel(model, DictCode.画面数据状态,DictCode.通道类型
            ,DictCode.股票分红类型);
        model.addAttribute("PAGE_DATA_STATUS01", DictCodeListExt1.画面数据状态_待处理.getValue());
        model.addAttribute("PAGE_DATA_STATUS05", DictCodeListExt1.画面数据状态_有效.getValue());
        return super.init(model);
    }
    
    /**
     * 业务编号
     */
    @ResponseBody
    @RequestMapping("getBusiCode")
    public Page<SelectOptionDto> getBusiCode() {
        return trdStockConnectBonus001Service.getBusiCode();
    }
    
    /**
     * 分页查询 证券账户代码、名称
     */
    @ResponseBody
    @RequestMapping("getSecManageAcctId")
    public Page<SelectOptionDto> getSecManageAcctId() {
        return trdStockConnectBonus001Service.getSecManageAcctId();
    }
    
    /**
     * 分页查询 资产代码
     */
    @ResponseBody
    @RequestMapping("getFinprodMarketId")
    public Page<SelectOptionDto> getFinprodMarketId() {
        return trdStockConnectBonus001Service.getFinprodMarketId();
    }
    
    /**
     * 分页查询 股票存续期列表数据
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdStockConnectBonus001Dto> findPage() {
        return trdStockConnectBonus001Service.findPage(RequestUtil.getPageParam());
    }
    
    
    /**
     * 导出
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return trdStockConnectBonus001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    
    
    /**
     * 撤销check
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) TrdStockConnectBonus002Dto dto,
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
     * 撤销
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult revoke(@RequestBody @Validated({
            ValidateGroupDelete.class}) TrdStockConnectBonus002Dto dto, BindingResult br)
            throws Exception {
    	if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("statusValid", dto.getStatusValid());
        paramMap.put("busiCode", dto.getBusiCode());
        //构建撤销dto
        TrdStockConnectBonus002Dto dto002 = trdStockConnectBonus002Service.findEffectiveInfo(paramMap);
        dto002.setTradeStatus(DictCodeListExt1.交易流程状态_撤销.getValue());
        dto002.setFinAssetIntInfoList(trdStockConnectBonus002Service.findDividDetailInfo(dto.getBusiCode()));
        //构建流程参数
        ProcessParameter<TrdStockConnectBonus002Dto> processParameter = new ProcessParameter<>();
	    WorkFlowParameter<TrdStockConnectBonus002Dto> params = new WorkFlowParameter<>();
	    params.setPageId("TrdStockConnectBonus002");
	    params.setElementId(dto.getElementId());
	    //模式为撤销
	    dto002.setMode(OperationType.REVOKE.toString());
	    dto002.setpTradeId(dto002.getBusiCode());
	    dto002.setBusiCode(dto002.getBusiCode()+'R');
	    dto002.setTotleTradeId(dto002.getBusiCode());
        dto002.setBusiType(DictCodeListExt2.共通业务类型_港股通股票分红交易.getValue());
	    params.setBusinessCode(dto002.getBusiCode());
	    params.setBusinessType(DictCodeListExt2.共通业务类型_港股通股票分红交易.getValue());
	    // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment(
        		this.getMessage("TrdStockConnectBonus001.tradeId"),
                GlobalConstant.OPEN_PARENTHESIS + entity.getBusiCode()
                + GlobalConstant.CLOSE_PARENTHESIS,
        this.getMessage("TrdStockConnectBonus001.finprodMarketName"),
        GlobalConstant.OPEN_PARENTHESIS + entity.getFinprodAbbr()
                + GlobalConstant.CLOSE_PARENTHESIS,
        this.getMessage("TrdStockConnectBonus001.tradeDate"),
        GlobalConstant.OPEN_PARENTHESIS + entity.getTradeDateStr()
                + GlobalConstant.CLOSE_PARENTHESIS        ));
        processParameter.setWorkFlowParameter(params);
        String msg = trdStockConnectBonus002Service.start(processParameter, dto002);
        
    	return new AjaxResult(msg);
    }
    
    
}
