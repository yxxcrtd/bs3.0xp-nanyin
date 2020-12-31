/*
 * 文件名：ChannelAssetQueryController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：yangjm 修改时间：2019年3月13日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.channel.feepay.controller;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeDto;
import com.joyintech.tams.invest.trade.channel.feepay.dto.ChannelFeePay002Dto;
import com.joyintech.tams.invest.trade.channel.feepay.service.ChannelFeePay002Service;
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

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;

import javax.validation.Valid;

/**
 * 通道费用支付新建
 * 
 * @author Johnnie
 * @version 1.0 2019年3月19日
 * @see ChannelFeePay002Controller
 * @since 1.0
 */
@Controller
@PageId("ChannelFeePay002")
@RequestMapping("/invest/trade/channel/feepay/ChannelFeePay002")
public class ChannelFeePay002Controller extends BaseController {

    @Autowired
    private BaseGetBusinessId baseGetBusinessIdImpl;

    /**
     * 通道资产查询-处理详细
     */
    @Autowired
    private ChannelFeePay002Service channelFeePay002Service;

    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.币种, DictCode.是否};

    /**
     * 界面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        model.addAttribute("feeName", DictCodeListExt1.一次性费用类型_通道费.getCodeName());
        model.addAttribute("YN_N", DictCodeList.是否_否.getValue());
        model.addAttribute("F28", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
        model.addAttribute("T10", DictCodeList.交易方向_付费.getValue());
        model.addAttribute("formTodoList", !WorkFlowUtil.isNotFromToDoList());
        ChannelFeePay002Dto dto = new ChannelFeePay002Dto();
        String tradeId = this.getParam("tradeId");
        String workflowStatus = this.getParam("workflowStatus");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);

        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId)) {
            dto = channelFeePay002Service.getBussinessEntityByWorkflowId(workflowId);
        }
        else if (StringUtil.isNotEmpty(tradeId)) {
            dto = channelFeePay002Service.getEffectiveData(tradeId);
            if (dto.getBussTrdTransferOrder() != null && !dto.getBussTrdTransferOrder().isEmpty()) {
                model.addAttribute("entity_bussTrdTransferOrderDto", JsonUtils.object2Json(dto.getOrders().get(0)));
            }
        }
        if (dto.getBussTrdTransferOrder() != null && !dto.getBussTrdTransferOrder().isEmpty()) {
            model.addAttribute("entity_bussTrdTransferOrderDto", JsonUtils.object2Json(dto.getBussTrdTransferOrder().get(0)));
        }
        //给币种赋个默认值CNY by zhaodongdong 20191210 start
        dto.setCcy(DictCodeList.币种_人民币_CNY.getValue());
        // 给币种赋个默认值CNY by zhaodongdong 20191210 end
        model.addAttribute(GlobalConstant.ENTITY, dto);
        return super.init(model);
    }

    /**
     * 通道代码模糊查询
     * 
     * @return 通道代码 金融产品代码
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("searchTheoryAmt")
    public ChannelFeePay002Dto searchTheoryAmt() {
        return channelFeePay002Service.searchTheoryAmt(RequestUtil.getPageParam());
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid ChannelFeePay002Dto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }

        return commonCheck(result, dto.getOperateType());
    }
    
    @ResponseBody
    @RequestMapping("getCollectType")
    public String getCollectType(String finprodMarketId) {      
    	 return channelFeePay002Service.getCollectType(finprodMarketId);       
    }

    /**
     * 保存
     *
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Valid ChannelFeePay002Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ChannelFeePay002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ChannelFeePay002Dto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        if (StringUtil.isEmpty(dto.getTradeId())) {
            BussinessCodeParameter parameter = new BussinessCodeParameter(DictCodeList.共通业务类型_通道费用支付);
            dto.setTradeId(baseGetBusinessIdImpl.buildBussinessCode(parameter));
        }
        params.setBusinessCode(dto.getTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_通道费用支付.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment("交易编号：" + dto.getTradeId(), "通道名称：" + entity.getChannelName(),
            "支付日：" + DateUtil.format(entity.getSettleDate())));
        processParameter.setWorkFlowParameter(params);
        String msg = channelFeePay002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
