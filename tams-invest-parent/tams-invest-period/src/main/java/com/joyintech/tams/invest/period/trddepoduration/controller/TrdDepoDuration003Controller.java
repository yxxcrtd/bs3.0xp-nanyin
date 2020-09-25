/*
 * 文件名：TrdDepoDuration001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.period.trddepoduration.controller;

import static com.joyintech.tams.invest.period.common.service.PeriodCommonService.change现金流类型To交易方向;

import java.util.Map;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trddepoduration.dto.TrdDepoDuration001Dto;
import com.joyintech.tams.invest.period.trddepoduration.service.TrdDepoDuration001Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 存款类到期
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年10月11日
 * @see TrdDepoDuration003Controller
 * @since 1.0
 */
@Controller
@PageId("TrdDepoDuration003")
@RequestMapping("/invest/period/trddepoduration/TrdDepoDuration003")
public class TrdDepoDuration003Controller extends BaseController{
    /**
     * service
     */
    @Autowired
    private TrdDepoDuration001Service trdDepoDuration001Service;
    @Autowired
    private PeriodCommonService periodCommonService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        Map<String,Object>param = RequestUtil.getPageParam();
        String opType = (String)param.get("opType");
        model.addAttribute("DEPO_ASSET_TYPE_01",DictCodeList.存款类资产类型_存放同业.getValue());
        setSelectOptionToModel(model, DictCode.币种,DictCode.计息基础,DictCode.平安业务类型,DictCode.存款类资产类型
        		,DictCode.是否,DictCode.平安划款类型,DictCode.是否);
        String statusValid = (String)param.get("statusValid");
        TrdDepoDuration001Dto dto = null;
        String workflowId = (String)param.get(GlobalConstant.WORKFLOW_ID);
        if ((StringUtil.equals(DictCodeListExt1.画面数据状态_流程中.getValue(), statusValid)
             || !WorkFlowUtil.isNotFromToDoList())
            && StringUtil.isNotBlank(workflowId)) {
            dto = trdDepoDuration001Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("workflowId",workflowId);
            opType = dto.getOpType();
        }
        else if(StringUtil.isNotEmpty(statusValid)){
        	param.put("isFindEnd","true");
            dto = trdDepoDuration001Service.findEffectiveData(param);
            dto.setFinAssetIntInfoList(trdDepoDuration001Service.findAssetIntInfo(trdDepoDuration001Service.genParams(dto)));
            if(StringUtil.isNotEmpty(dto.getBusiCode())){
                dto.setBussTrdTransferOrder(periodCommonService.transformOrderDtoBack(periodCommonService.selectPayOrders(dto.getBusiCode())));
            }
            opType = "edit";
        }
        model.addAttribute("opType",opType);
        model.addAttribute(GlobalConstant.ENTITY,dto);
        model.addAttribute("FINPRODTYPE_DEFAULT",DictCodeListExt1.金融产品类型_投管用_存款.getValue());
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        // add by renping 2019-10-15 添加通道费 begin
        model.addAttribute("COLLECT_TYPE_01", DictCodeListExt2.收取方式_回款前扣除.getValue());
        model.addAttribute("IS_CAL_LOADRATE", CacheUtil.getSysParam(BussinessConstant.IS_CAL_LOADRATE));
        // add by renping 2019-10-15 添加通道费 end
        model.addAttribute("GEN_PAY_ORDERS_AUTO", CacheUtil.getSysParam("GEN_PAY_ORDERS_AUTO"));
        model.addAttribute("PS_DEFAULT",change现金流类型To交易方向(dto==null?null:dto.getCashType()));
        model.addAttribute("formTodoList", !WorkFlowUtil.isNotFromToDoList());
        model.addAttribute("acctNoList", JsonUtils.object2Json(periodCommonService.queryAcctList(null)));
        return super.init(model);
    }

}
