/*
 * 文件名：TrdFundDuration002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.period.trdfundobversion.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdfundobversion.dto.TrdFundObversionDto;
import com.joyintech.tams.invest.period.trdfundobversion.service.TrdFundObversion001Service;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基金存续期折算新建画面
 * 
 * @author wangxuelei
 * @version 1.0 2019年10月15日
 * @see TrdFundObversion002Controller
 * @since 1.0
 */
@PageId("TrdFundObversion002")
@RequestMapping("/invest/period/trdfundduration/TrdFundObversion002")
@Controller
public class TrdFundObversion002Controller extends BaseController {
    /**
     * 基金存续期service
     */
    @Autowired
    private TrdFundObversion001Service trdFundDuration001Service;
    /**
     * 业务编号共通
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
    
    @Autowired
    private PeriodCommonService periodCommonService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
//        获取画面所有参数
        Map<String,Object> param = RequestUtil.getPageParam();
//        获取业务类型
        String type = (String)param.get("type");
//      交易人
        String trader = SecurityUtil.getLoginUser().getUserName();
        setSelectOptionToModel(model,DictCode.基金类资产类型
            ,DictCode.是否,DictCode.平安划款类型,DictCode.币种,DictCode.是否,DictCode.折算类型);
        model.addAttribute("FINPRODTYPE_DEFAULT",DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        model.addAttribute("FUND_ASSET_TYPE_F01", DictCodeList.基金类资产类型_货币基金.getValue());
        model.addAttribute("FUND_ASSET_TYPE_F02", DictCodeList.基金类资产类型_非货币基金.getValue());
        String statusValid = (String)param.get("statusValid");
        TrdFundObversionDto dto = null;
//        资金后台
        model.addAttribute("fromCheck", false);
        if(StringUtil.equals(DictCodeListExt1.画面数据状态_待处理.getValue(),statusValid ) 
                || (((StringUtil.equals(DictCodeListExt1.画面数据状态_流程中.getValue(),statusValid )
                ||!WorkFlowUtil.isNotFromToDoList())&& StringUtil.isEmpty((String)param.get(GlobalConstant.WORKFLOW_ID))))) {
            dto = trdFundDuration001Service.findCashDividend(param);
//            处理类型及交易员
            type=dto.getType();
            trader = dto.getTrader();
            model.addAttribute("fromCheck", true);
            Map<String,Object>pageParam = new HashMap<>();
            pageParam.put("busiCode", dto.getTotleTradeId());
            pageParam.put("statusValid", DictCodeListExt1.画面数据状态_流程中.getValue());
//          查询利息信息
            dto.setFinAssetIntInfoList(trdFundDuration001Service.findAssetIntInfo(pageParam));
        }
//        流程中数据获取
        else if(StringUtil.equals(DictCodeListExt1.画面数据状态_流程中.getValue(),statusValid )||!WorkFlowUtil.isNotFromToDoList()) {
            String workflowId = (String)param.get(GlobalConstant.WORKFLOW_ID);
            if(StringUtil.isNotEmpty(workflowId)){
                dto = trdFundDuration001Service.getBussinessEntityByWorkflowId(workflowId);
                model.addAttribute("workflowId",workflowId);
            }
            else if (StringUtils.isNotEmpty((String)param.get("totleTradeId"))){
//                流程中数据无工作流Id场景处理
                dto = trdFundDuration001Service.findCashDividend(param);
                Map<String,Object>pageParam = new HashMap<>();
                pageParam.put("busiCode", dto.getTotleTradeId());
                pageParam.put("statusValid", statusValid);
//              查询利息信息
                dto.setFinAssetIntInfoList(trdFundDuration001Service.findAssetIntInfo(pageParam));
            }
//            处理类型及交易员
            if (dto != null){
                type=dto==null?null:dto.getType();
                trader = dto.getTrader();
            }
        }
//        有效及撤销处理
        else if(StringUtil.isNotEmpty(statusValid)){
            dto = trdFundDuration001Service.findEffectiveData(param);
            Map<String,Object>pageParam = new HashMap<>();
            pageParam.put("busiCode", dto.getTotleTradeId());
            pageParam.put("statusValid", statusValid);
            pageParam.put("conversionTime",DateUtil.format(dto.getConversionTime()));
            pageParam.put("type","obversion");
            pageParam.put("finprodMarketId",dto.getFinprodMarketId());
            pageParam.put("POSITION_TYPE03",DictCodeList.持仓类型_实际.getValue());
            pageParam.put("totleTradeId",dto.getTotleTradeId());
//          查询利息信息
            List<FinAssetIntInfo> finAssetIntInfos = trdFundDuration001Service.selectObversionByTotleTradeId(pageParam);
            dto.setFinAssetIntInfoList(finAssetIntInfos);
            type = StringUtil.isNotEmpty(type)?type:dto.getType();
            trader = dto.getTrader();
        }
        else{
//            新建处理
            dto = new TrdFundObversionDto();
        }
        model.addAttribute("type",type);
        if (dto != null){
            model.addAttribute("busiCode", dto.getTotleTradeId());    
        }
        model.addAttribute("trader",trader);
//      画面的业务类型，用于判断画面处于何种模式
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
        ar.setData(trdFundDuration001Service.finprodMarketIdLink(RequestUtil.getPageParam()));
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
            ValidateGroupEdit.class}) TrdFundObversionDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 
     * 获取折算明细信息
     * <功能详细描述>
     * @return 查询到的结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findAssetIntInfo")
    public AjaxResult findAssetIntInfo() {
    	AjaxResult ar = new AjaxResult();
    	List<FinAssetIntInfo> assetIntInfo = trdFundDuration001Service.findAssetIntInfo(RequestUtil.getPageParam());
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
            ValidateGroupEdit.class}) TrdFundObversionDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<TrdFundObversionDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdFundObversionDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessExtType(DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_基金存续期折算));
        if(StringUtil.isEmpty(dto.getbTradeId())){
            dto.setTotleTradeId(baseGetBusinessId.buildBussinessCode(parameter));
        }
        else {
        	periodCommonService.deleteWflByTradeId(dto.getTotleTradeId());
        }
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessExtType(DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_基金存续期折算));
            dto.setTotleTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        } else {
            dto.setTotleTradeId(IDGenerator.generate());
        }
        params.setBusinessCode(dto.getTotleTradeId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_基金存续期折算.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment(
                this.getMessage("TrdDepoDuration001.finprodName"),//资产名称
                GlobalConstant.OPEN_PARENTHESIS + entity.getFinprodMarketName()
                        + GlobalConstant.CLOSE_PARENTHESIS,
                this.getMessage("TrdFundObversion001.conversionType"),//折算类型
                GlobalConstant.OPEN_PARENTHESIS +entity.getConversionTypeStr()+ GlobalConstant.CLOSE_PARENTHESIS,
                this.getMessage("TrdFundObversion001.conversionTime"),//折算日期
                GlobalConstant.OPEN_PARENTHESIS+DateUtil.format(entity.getConversionTime())+GlobalConstant.CLOSE_PARENTHESIS
              ));
        processParameter.setWorkFlowParameter(params);
        if (BussinessConstant.SUBMIT.equals(dto.getOperateType())) {
      	dto.setBaseTotalTradeInfoDto(trdFundDuration001Service.genFinProductTradeDto(dto));
      }
        String msg = trdFundDuration001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
