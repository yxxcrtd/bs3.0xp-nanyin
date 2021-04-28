package com.joyintech.tams.invest.trade.bondmarkettrade.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.trade.bondmarkettrade.dto.TrdBondMarketTradeDto;
import com.joyintech.tams.invest.trade.bondmarkettrade.service.TrdBondMarketTrade001Service;
import com.joyintech.tams.invest.trade.bondmarkettrade.service.TrdBondMarketTrade002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import com.mchange.lang.IntegerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 债券上市流通
 *
 * @author 马数凯
 * @version 1.0 2021年3月29日
 * @see com.joyintech.tams.invest.trade.bondmarkettrade.controller.TrdBondMarketTrade001Controller
 * @since 1.0
 */
@Controller
@PageId("TrdBondMarketTrade001")
@RequestMapping("/invest/trade/bondmarkettrade/TrdBondMarketTrade001")
public class TrdBondMarketTrade001Controller extends BaseController {

    @Autowired
    TrdBondMarketTrade001Service trdBondMarketTrade001Service;

    @Autowired
    TrdBondMarketTrade002Service trdBondMarketTrade002Service;
    
    @Autowired
    private IPlatformDate iPlatformDate;
    
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model,  DictCode.交易场所);
        return super.init(model);
    }

    /**
     * 发行代码：下拉框
     *
     * @param params 画面参数
     * @return 发行代码：下拉框
     * @see
     * @since 1.0
     */
    @RequestMapping("getFinprodId")
    @ResponseBody
    public Page<SelectOptionDto> getFinprodId(@RequestParam Map<String, Object> params) {
        return trdBondMarketTrade001Service.getFinprodId(params);
    }

    /**
     * 发行代码：下拉框
     *
     * @param params 画面参数
     * @return 发行代码：下拉框
     * @see
     * @since 1.0
     */
    @RequestMapping("getAppearMarketId")
    @ResponseBody
    public Page<SelectOptionDto> getAppearMarketId(@RequestParam Map<String, Object> params) {
        return trdBondMarketTrade001Service.getAppearMarketId(params);
    }
    
    /**
     * 分页查询
     *
     * @return Page<TrdBondInvestTradeDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<TrdBondMarketTradeDto> findPage()
            throws Exception {
        Page<TrdBondMarketTradeDto> page = trdBondMarketTrade001Service.findPage(RequestUtil.getPageParam());
        List<TrdBondMarketTradeDto> root = page.getRoot();
        int i = 0;
        for (TrdBondMarketTradeDto dto : root) {
            i++;
            dto.setRowNum(StringUtil.numberToString(i));
        }
        return page;
    }

    /**
     * 撤销
     * @param inputDto 画面输入参数dto
     * @return 撤销结果
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("doRevoke")
    @ResponseBody
    AjaxResult doRevoke(@RequestBody TrdBondMarketTradeDto inputDto)
            throws Exception {
        String operateType = inputDto.getOperateType();
        String totleTradeId = inputDto.getTotleTradeId();

        inputDto.setTotleTradeId(totleTradeId + "R");
        inputDto.setFinprodType2("F01");
        inputDto.setOperateType(operateType);
        //判断流程中是否存在,如果流程不存在则说明是生成的反向交易，组装撤销dto yjm end 20191015
        ProcessParameter<TrdBondMarketTradeDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdBondMarketTradeDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId("TrdBondMarketTrade002");
        processParameter.setOperationType(OperationType.REVOKE);

        workFlowParameter.setPageId("TrdBondMarketTrade002");
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getTotleTradeId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_债券类资产交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
                getMessage("TrdBondInvestTrade002.tradeId") + GlobalConstant.COLON + inputDto.getTotleTradeId(),
                getMessage("TrdBondInvestTrade002.finprodName") + GlobalConstant.COLON + inputDto.getFinprodAbbr(),
                getMessage("TrdBondInvestTrade002.tradeDate") + GlobalConstant.COLON + DateUtil.format(inputDto.getTradeDate())));

        trdBondMarketTrade002Service.setRevokeEntity(inputDto);
        String msg = trdBondMarketTrade002Service.start(processParameter, inputDto);

        return new AjaxResult(msg);
    }

    /**
     * 撤销前check
     *
     * @param inputDto 画面输入参数dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @RequestMapping("checkRevoke")
    @ResponseBody
    public AjaxResult check(@Valid @RequestBody TrdBondMarketTradeDto inputDto,
                            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult res= new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        
        if(!StringUtil.equals(DateUtil.format(inputDto.getTradeDate()),iPlatformDate.getSysDateStr())){
            ValidMessage validMessage = new ValidMessage(
                    "只可撤销生效日等于系统平台日的数据！");
            errorList.add(validMessage);
            res.setErrorList(errorList);
            res.setMessageType(MessageType.错误消息);
            return res;
        }

        int count  = trdBondMarketTrade002Service.selectDealNum(inputDto);
        if (count > 0){
            ValidMessage validMessage = new ValidMessage("发行标的当天存在流程中或已生效的交易，请先撤销交易再做上市流通！");
            errorList.add(validMessage);
            res.setErrorList(errorList);
            res.setMessageType(MessageType.错误消息);
            return res;
        }
        
        count = trdBondMarketTrade002Service.selectRevokeDeal(inputDto);
        if (count > 0){
            ValidMessage validMessage = new ValidMessage("已存在流程中的撤销交易");
            errorList.add(validMessage);
            res.setErrorList(errorList);
            res.setMessageType(MessageType.错误消息);
            return res;
        }
        return commonCheck(result, inputDto.getOperateType());
    }
}
