/*
 * 文件名：TrdFundDurationNew002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.period.trdfunddurationnew.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.dao.WflTrdProductDealMapper;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdProductDealKey;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdfunddurationnew.dto.TrdFundDurationNew001Dto;
import com.joyintech.tams.invest.period.trdfunddurationnew.service.TrdFundDurationNew001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang.StringUtils;
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
import java.util.stream.Collectors;

/**
 * 基金存续期新建画面
 * 
 * @author dingzhipeng
 * @version 1.0 2018年10月15日
 * @see TrdFundDurationNew002Controller
 * @since 1.0
 */
@PageId("TrdFundDurationNew002")
@RequestMapping("/invest/period/trdfunddurationnew/TrdFundDurationNew002")
@Controller
public class TrdFundDurationNew002Controller extends BaseController {
    /**
     * 基金存续期service
     */
    @Autowired
    private TrdFundDurationNew001Service trdFundDurationNew001Service;
    /**
     * 业务编号共通
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
    
    @Autowired
    private PeriodCommonService periodCommonService;

    @Autowired
    private WflTrdProductDealMapper wflTrdProductDealMapper;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
//        获取画面所有参数
        Map<String,Object> param = RequestUtil.getPageParam();
        String workflowId = (String)param.get(GlobalConstant.WORKFLOW_ID);
        Date sysDate=DateUtil.parse(DateUtil.getSysDate()); //取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(sysDate);
        calendar.add(calendar.DATE,-1); //把日期往前一天
        sysDate=calendar.getTime();
        model.addAttribute("sysDate",DateUtil.format(sysDate, "yyyy-MM-dd"));
//      交易人
        String trader = SecurityUtil.getLoginUser().getUserName();
        setSelectOptionToModel(model,DictCode.基金类资产类型
            ,DictCode.分红方式,DictCode.是否,DictCode.平安划款类型,DictCode.币种,DictCode.是否);
        model.addAttribute("FINPRODTYPE_DEFAULT",DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
//      现金分红用
        model.addAttribute("PS_T11",DictCodeList.交易方向_现金分红.getValue());
        model.addAttribute("PS_T12",DictCodeList.交易方向_红利再投.getValue());
        model.addAttribute("PS_T13", DictCodeList.交易方向_除权登记.getValue());
        model.addAttribute("FUND_ASSET_TYPE_F01", DictCodeList.基金类资产类型_货币基金.getValue());
        model.addAttribute("FUND_ASSET_TYPE_F02", DictCodeList.基金类资产类型_非货币基金.getValue());
        model.addAttribute("BONUS_MODE_02", DictCodeList.分红方式_红利再投.getValue());
        model.addAttribute("BONUS_MODE_01", DictCodeList.分红方式_现金分红.getValue());
        String statusValid = (String)param.get("statusValid");
        TrdFundDurationNew001Dto dto = null;
//        资金后台
        model.addAttribute("fromCheck", false);
        if(StringUtil.equals(DictCodeListExt1.画面数据状态_待处理.getValue(),statusValid ) 
                || (((StringUtil.equals(DictCodeListExt1.画面数据状态_流程中.getValue(),statusValid )
                ||!WorkFlowUtil.isNotFromToDoList())&& StringUtil.isEmpty((String)param.get(GlobalConstant.WORKFLOW_ID))))) {
            dto = trdFundDurationNew001Service.findCashDividend(param);
//            处理类型及交易员
            trader = dto.getTrader();
            model.addAttribute("fromCheck", true);
            dto.setPstype(DictCodeList.分红方式_现金分红.getValue());
            Map<String,Object>pageParam = new HashMap<>();
            pageParam.put("busiCode", dto.getTotleTradeId());
            pageParam.put("statusValid", DictCodeListExt1.画面数据状态_流程中.getValue());
            pageParam.put("registDate", DateUtil.format(dto.getRegistDate()));
//          查询利息信息
            dto.setFinAssetIntInfoList(trdFundDurationNew001Service.findAssetIntInfo(pageParam));
            dto.setBussTrdTransferOrder(periodCommonService.selectPayOrders(dto.getTotleTradeId()));
            for(BussTrdTransferOrderDto bussTrdTransferOrderDto : dto.getBussTrdTransferOrder()){
                WflTrdProductDealKey wflTrdProductDealKey =new WflTrdProductDealKey();
                wflTrdProductDealKey.setTradeId(bussTrdTransferOrderDto.getTradeId());
                wflTrdProductDealKey.setWorkflowStatus(DictCodeList.数据状态_新建.getValue());
                WflTrdProductDeal wflTrdProductDeal = wflTrdProductDealMapper.selectByPrimaryKey(wflTrdProductDealKey);
                bussTrdTransferOrderDto.setSecAcctId(StringUtil.isNotEmpty(wflTrdProductDeal.getSecManageAcctId())?wflTrdProductDeal.getSecManageAcctId():wflTrdProductDeal.getChlId());
            }
        }
//        流程中数据获取
        else if(StringUtil.equals(DictCodeListExt1.画面数据状态_流程中.getValue(),statusValid )||StringUtil.isNotEmpty(workflowId)) {
            if(StringUtil.isNotEmpty(workflowId)){
                dto = trdFundDurationNew001Service.getBussinessEntityByWorkflowId(workflowId);
                model.addAttribute("workflowId",workflowId);
            }
            else if (StringUtils.isNotEmpty((String)param.get("totleTradeId"))){
//              流程中数据无工作流Id场景处理
                dto = trdFundDurationNew001Service.findCashDividend(param);
                Map<String,Object>pageParam = new HashMap<>();
                pageParam.put("busiCode", dto.getTotleTradeId());
                pageParam.put("statusValid", statusValid);
                pageParam.put("registDate", DateUtil.format(dto.getRegistDate()));
//              查询利息信息
                dto.setFinAssetIntInfoList(trdFundDurationNew001Service.findAssetIntInfo(pageParam));
            }
//            处理类型及交易员
            trader = dto.getTrader();
        }
//        有效及撤销处理
        else if(StringUtil.isNotEmpty(statusValid)){
            dto = trdFundDurationNew001Service.findEffectiveData(param);
            dto.setFinprodMarketName(dto.getFinprodAbbr());
            Map<String,Object>pageParam = new HashMap<>();
            pageParam.put("busiCode", dto.getTotleTradeId());
            pageParam.put("statusValid", statusValid);
            pageParam.put("registDate", DateUtil.format(dto.getRegistDate()));
//          查询利息信息
            dto.setFinAssetIntInfoList(trdFundDurationNew001Service.findAssetIntInfo(pageParam));
            if(StringUtil.equals("edit",param.get("mode"))){
                List<FinAssetIntInfo> listFinAssetIntInfotemp= dto.getFinAssetIntInfoList();
                listFinAssetIntInfotemp = listFinAssetIntInfotemp.stream().filter(finAssetIntInfo -> StringUtil.equals(finAssetIntInfo.getBonusMode(), DictCodeList.分红方式_红利再投.getValue())).collect(Collectors.toList());
                dto.setFinAssetIntInfoList(listFinAssetIntInfotemp);
                //                // 单位净值、分红份额重新设置
                BigDecimal unitPrice = periodCommonService.getLatestValuation(dto.getFinprodId(), DateUtil.format(dto.getExRightsDate()));
                BigDecimal tradeAmt = BigDecimal.ZERO;
                for(FinAssetIntInfo finAssetIntInfo : dto.getFinAssetIntInfoList()){
                    tradeAmt = finAssetIntInfo.getTradeAmt();
                }
                dto.setQty(ArithmeticUtil.divide(tradeAmt, unitPrice == null ? BigDecimal.ONE:unitPrice, 4));
                dto.setPstype(DictCodeList.分红方式_红利再投.getValue());
            }
            trader = dto.getTrader();
            if(StringUtil.equals(param.get("ps"),"T11")){
                dto.setPstype(DictCodeList.分红方式_现金分红.getValue());
            }
            dto.setBussTrdTransferOrder(periodCommonService.selectPayOrders(dto.getTotleTradeId()));
        }
        else{
//            新建处理
            dto = new TrdFundDurationNew001Dto();
        }
        model.addAttribute("acctNoList", JsonUtils.object2Json(periodCommonService.queryAcctList(null)));
        model.addAttribute("busiCode", dto.getTotleTradeId());
        model.addAttribute("trader",trader);
//      画面的业务类型，用于判断画面处于何种模式
        model.addAttribute("GEN_PAY_ORDERS_AUTO", CacheUtil.getSysParam("GEN_PAY_ORDERS_AUTO"));
        model.addAttribute("psType", dto.getPstype());
        model.addAttribute(GlobalConstant.ENTITY,dto);
        return super.init(model);
    }
    /**
     * 资产代码联动处理
     *
     * @return 联动结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("finprodMarketIdLink")
    public AjaxResult finprodMarketIdLink() {
        AjaxResult ar = new AjaxResult();
        ar.setData(trdFundDurationNew001Service.finprodMarketIdLink(RequestUtil.getPageParam()));
        return ar;
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
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) TrdFundDurationNew001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 
     * 获取利息明细信息
     * <功能详细描述>
     * @return 查询到的结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findAssetIntInfo")
    public AjaxResult findAssetIntInfo() {
    	AjaxResult ar = new AjaxResult();
    	List<FinAssetIntInfo> assetIntInfo = trdFundDurationNew001Service.findAssetIntInfo(RequestUtil.getPageParam());
    	ar.setData(assetIntInfo);
    	return ar;
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
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) TrdFundDurationNew001Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdFundDurationNew001Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdFundDurationNew001Dto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        if(StringUtil.isEmpty(dto.getbTradeId())){
            //为非货币现金分红及非货币红利再投时 设置前置交易编号
            if(StringUtil.equals(dto.getPstype(), DictCodeList.分红方式_现金分红.getValue())
                    ||StringUtil.equals(dto.getPstype(), DictCodeList.分红方式_红利再投.getValue())){
                dto.setbTradeId(dto.getTotleTradeId());
            }
            dto.setTotleTradeId(baseGetBusinessId.buildBussinessCode(parameter));
        }
        else {
        	periodCommonService.deleteWflByTradeId(dto.getTotleTradeId());
        }
        params.setBusinessCode(dto.getTotleTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_基金类资产存续期.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment(
                this.getMessage("TrdFundDurationNew001.finprodName"),
                GlobalConstant.OPEN_PARENTHESIS + entity.getFinprodMarketName()
                        + GlobalConstant.CLOSE_PARENTHESIS,
                this.getMessage("TrdFundDurationNew001.assetType"),
                GlobalConstant.OPEN_PARENTHESIS + SysDataUtil.getNameFromDataDict(
                        DictCode.基金类资产类型, entity.getAssetType()) + GlobalConstant.CLOSE_PARENTHESIS,
                this.getMessage("TrdFundDurationNew001.tradeId"),
                GlobalConstant.OPEN_PARENTHESIS + entity.getTotleTradeId()
                        + GlobalConstant.CLOSE_PARENTHESIS));
        processParameter.setWorkFlowParameter(params);
        if (BussinessConstant.SUBMIT.equals(dto.getOperateType())) {
      	    dto.setBaseTotalTradeInfoDto(trdFundDurationNew001Service.genFinProductTradeDto(dto));
        }
        String msg = trdFundDurationNew001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
