/*
 * 文件名：ChannelAssetQueryController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：yangjm 修改时间：2019年3月13日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.channel.feepay.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.trade.channel.feepay.dto.ChannelFeePay001Dto;
import com.joyintech.tams.invest.trade.channel.feepay.dto.ChannelFeePay002Dto;
import com.joyintech.tams.invest.trade.channel.feepay.service.ChannelFeePay001Service;
import com.joyintech.tams.invest.trade.channel.feepay.service.ChannelFeePay002Service;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.service.LeaveMarkProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;

/**
 * 通道费用支付查询 〈功能详细描述〉
 * 
 * @author Johnnie
 * @version 1.0 2019年3月19日
 * @see ChannelFeePay001Controller
 * @since 1.0
 */
@Controller
@PageId("ChannelFeePay001")
@RequestMapping("/invest/trade/channel/feepay/ChannelFeePay001")
public class ChannelFeePay001Controller extends BaseController {

    /**
     * 通道资产查询-处理详细
     */
    @Autowired
    private ChannelFeePay001Service channelFeePay001Service;

    /**
     * 通道资产新建service
     */
    @Autowired
    private ChannelFeePay002Service channelFeePay002Service;
    /**
     * mstWorkflowCommonService工作流组件
     */
    @Autowired(required = false)
    private LeaveMarkProcessService leaveMarkProcessService;
    /**
     * 界面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return super.init(model);

    }

    /**
     * 分页查询
     * 
     * @return 通道资产分页查询
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ChannelFeePay001Dto> findPage()
        throws Exception {
        return channelFeePay001Service.selectForPage(RequestUtil.getPageParam());
    }

    /**
     * 通道代码模糊查询
     * 
     * @return 通道代码 金融产品代码
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findChannelId")
    public Page<SelectOptionDto> findChannelId() {
        return channelFeePay001Service.findChannelId();
    }

    /**
     * 导出
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        return channelFeePay001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
    }

    /**
     * 撤销
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("doRevoke")
    public AjaxResult revoke(@RequestBody @Validated({ValidateGroupDelete.class}) ChannelFeePay002Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ChannelFeePay002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ChannelFeePay002Dto> params = new WorkFlowParameter<>();
        params.setPageId("ChannelFeePay002");
        params.setElementId("ChannelFeePay001_btn_revoke");

        params.setBusinessCode(dto.getTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_通道费用支付.getValue());
        ChannelFeePay002Dto entity = channelFeePay002Service.getEffectiveData(dto.getTradeId());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(channelFeePay002Dto -> WorkFlowUtil.concatComment("交易编号：" + channelFeePay002Dto.getTradeId(),
            "通道名称：" + channelFeePay002Dto.getChannelName(), "实付费用：" + channelFeePay002Dto.getTradeAmt()));
        processParameter.setWorkFlowParameter(params);
        entity.setRevokeReason(dto.getRevokeReason());
        String msg = channelFeePay002Service.start(processParameter, entity);
        return new AjaxResult(msg);
    }

    /**
     * 撤销check
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("checkRevoke")
    public AjaxResult revokeCheck(@RequestBody @Validated({ValidateGroupDelete.class}) ChannelFeePay002Dto dto, BindingResult result) {
    	AjaxResult ar = new AjaxResult();
	     List<ValidMessage> errorList = new ArrayList<ValidMessage>();
    	if(dto!=null&&!StringUtil.equals(dto.getEffectStatus(), DictCodeListExt1.有效状态_有效.getValue())){
    	     errorList.add(new ValidMessage(this.getMessage("只能撤销有效状态的数据")));
    	}
    	 if (WorkFlowUtil.isNotFromToDoList()) {
 	        ProcessMark processMark = new ProcessMark();
            processMark.setBusinessDataPk1(dto==null?null:dto.getChlId());
            processMark.setBusinessDataPk2(dto==null?null:DateUtil.format(dto.getMdate()));
            processMark.setBusinessDataPk3(dto==null?null:dto.getPortfolioId());
            processMark.setBusinessDataPk4(dto==null?null:dto.getTradeId()); 
             
             List<String> resultList =leaveMarkProcessService.getBusinessDataInFlow(processMark);
             if(!StringUtil.isEmptyList(resultList)){
             	errorList.add(new ValidMessage("该数据已在撤销流程中！"));
             }
         }
    	 if(!StringUtil.isEmptyList(errorList)){
    	     ar.setErrorList(errorList);
    	     return ar;
    	 }
    	return commonCheck(result, dto==null?null:dto.getOperateType());
    }
}
