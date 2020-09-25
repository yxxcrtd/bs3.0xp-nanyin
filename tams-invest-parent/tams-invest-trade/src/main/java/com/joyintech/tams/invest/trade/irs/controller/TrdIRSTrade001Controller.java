/*
 * 文件名：TrdIRSTrade001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiaominghui
 * 修改时间：2019年7月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.irs.controller;

import javax.validation.Valid;

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
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.trade.irs.dto.TrdIRSTradeDto;
import com.joyintech.tams.invest.trade.irs.dto.TrdIRSTradeRevokeDto;
import com.joyintech.tams.invest.trade.irs.service.TrdIRSTrade001Service;
import com.joyintech.tams.invest.trade.irs.service.TrdIRSTrade002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 利率互换交易
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月10日
 * @see TrdIRSTrade001Controller
 * @since 1.0
 */
@Controller
@PageId("TrdIRSTrade001")
@RequestMapping("/invest/trade/irs/TrdIRSTrade001")
public class TrdIRSTrade001Controller extends BaseController {


	@Autowired
	private TrdIRSTrade001Service trdIRSTrade001Service;
	
	@Autowired
	private TrdIRSTrade002Service trdIRSTrade002Service;
	
	/**
	 * 初始化界面
	 * {@inheritDoc}
	 */
	@Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.互换方式, DictCode.交易流程状态,DictCode.利率行情);
        return super.init(model);
    }
	
	 /**
     * 分页查询
     *
     * @return Page<TrdIRSTradeDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<TrdIRSTradeDto> findPage()
        throws Exception {
        Page<TrdIRSTradeDto> pageRet = trdIRSTrade001Service.findPage(RequestUtil.getPageParam());
        return pageRet;
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     *
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        return trdIRSTrade001Service.export(this.getPageId(), RequestUtil.getPageParam());
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
    public AjaxResult check(@Valid @RequestBody TrdIRSTradeRevokeDto inputDto,
                            BindingResult result) {
        return commonCheck(result, inputDto.getOperateType());
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
    AjaxResult doRevoke(@RequestBody TrdIRSTradeDto inputDto)
        throws Exception {

        ProcessParameter<TrdIRSTradeDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdIRSTradeDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId("TrdIRSTrade002");
        processParameter.setOperationType(OperationType.REVOKE);

        workFlowParameter.setPageId("TrdIRSTrade002");
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode((entity) -> entity.getTradeId());
        workFlowParameter.setBusinessType(DictCodeListExt2.共通业务类型_利率互换交易.getValue());
        workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment("交易编号：" + inputDto.getTradeId(),
        		"：互换方式" + inputDto.getIrsTypeStr(),
            "交易日：" + inputDto.getTradeDateStr()));
        
        String msg = trdIRSTrade002Service.start(processParameter, inputDto);

        return new AjaxResult(msg);
    }
    
    
}
