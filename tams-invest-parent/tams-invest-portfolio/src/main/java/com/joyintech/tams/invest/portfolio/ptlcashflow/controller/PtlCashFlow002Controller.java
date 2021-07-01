/*
 * 文件名：PtlCashFlow002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlcashflow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.dao.WflTrdProductDealMapper;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.portfolio.ptlcashflow.dto.PtlCashFlowDto;
import com.joyintech.tams.invest.portfolio.ptlcashflow.service.PtlCashFlowService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 自定义现金流新家
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月25日
 * @see PtlCashFlow002Controller
 * @since 1.0
 */
@Controller
@PageId("PtlCashFlow002")
@RequestMapping("/invest/portfolio/ptlcashflow/PtlCashFlow002")
public class PtlCashFlow002Controller extends BaseController{
    /**
     * ptlCashFlowService
     */
    @Autowired
    private PtlCashFlowService ptlCashFlowService;
    /**
     * baseGetBusinessId
     */
    @Autowired(required=false)
    private BaseGetBusinessId baseGetBusinessId;

    @Autowired(required = false)
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;

    /**
     * 清算指令共通Service
     */
    @Autowired(required = false)
    private BussSettleOrderCommService bussSettleOrderCommService;
    /**
     * mstWorkflowCommonService
     *//*
    @Autowired(required=false)
    private MstWorkflowCommonService mstWorkflowCommonService;*/
    /**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.平安资产类型);
        this.setSelectOptionToModel(model, DictCode.自定义现金流类型);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.是否);
        
        model.addAttribute("F23", DictCodeListExt1.金融产品类型_投管用_自定义现金流.getValue());
        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        model.addAttribute("PTL04", DictCodeList.共通业务类型_自定义现金流.getValue());
        model.addAttribute("CUSTOM_CASHFLOW_TYPE_04", DictCodeList.自定义现金流类型_逾期利息.getValue());
        String busiNo = getParam("busiNo");
        String finprodType2 = getParam("finprodType2");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (workflowId != null && !StringUtil.equals(workflowId, "")) {
            PtlCashFlowDto dto = ptlCashFlowService.getBussinessEntityByWorkflowId(
                workflowId);
            if(dto != null){
              //划款计划的值
                model.addAttribute("workflowId", workflowId);
                model.addAttribute("entity", dto);
                if (dto.getBussTrdTransferOrder() != null && !dto.getBussTrdTransferOrder().isEmpty()) {
                    model.addAttribute("entity_bussTrdTransferOrderDto", JsonUtils.object2Json(dto.getBussTrdTransferOrder().get(0)));
                }
            }
            
        }
        else if (StringUtil.isNotEmpty(busiNo)) {

            WflTrdProductDeal wflTrdProductDeal = new WflTrdProductDeal();
            if(bussCommonMapperDelegateService != null){
                wflTrdProductDeal.setWorkflowStatus(DictCodeList.数据状态_新建中.getValue());
                wflTrdProductDeal.setTradeId(busiNo);
                wflTrdProductDeal = bussCommonMapperDelegateService.selectByMultiPrimaryKey(WflTrdProductDealMapper.class, wflTrdProductDeal, WflTrdProductDeal.class);
            }
            WflTrdProductDeal wflTrdProductDeal1 = new WflTrdProductDeal();
            if(bussCommonMapperDelegateService != null){
                wflTrdProductDeal1.setWorkflowStatus(DictCodeList.数据状态_修改中.getValue());
                wflTrdProductDeal1.setTradeId(busiNo);
                wflTrdProductDeal1 = bussCommonMapperDelegateService.selectByMultiPrimaryKey(WflTrdProductDealMapper.class, wflTrdProductDeal1, WflTrdProductDeal.class);
            }

            if(wflTrdProductDeal != null){
                String workFlowId = wflTrdProductDeal.getWorkflowId();
                if(workFlowId != null && !workFlowId.isEmpty()){
                    PtlCashFlowDto entity = ptlCashFlowService.getBussinessEntityByWorkflowId(
                            workFlowId);

                    if (StringUtil.isEmpty(entity.getFinprodType2())) {
                        entity.setFinprodType2(finprodType2);
                    }

                    model.addAttribute("entity",entity);

                    if(entity.getBussTrdTransferOrder() != null && entity.getBussTrdTransferOrder().size()>0){
                        model.addAttribute("entity_bussTrdTransferOrderDto",JsonUtils.object2Json(entity.getBussTrdTransferOrder().get(0)));
                    }
                }

            }else if(wflTrdProductDeal1 != null){
                String workFlowId = wflTrdProductDeal1.getWorkflowId();
                if(workFlowId != null && !workFlowId.isEmpty()){
                    PtlCashFlowDto entity = ptlCashFlowService.getBussinessEntityByWorkflowId(
                            workFlowId);

                    if (StringUtil.isEmpty(entity.getFinprodType2())) {
                        entity.setFinprodType2(finprodType2);
                    }

                    model.addAttribute("entity",entity);

                    if(entity.getBussTrdTransferOrder() != null && entity.getBussTrdTransferOrder().size()>0){
                        model.addAttribute("entity_bussTrdTransferOrderDto",JsonUtils.object2Json(entity.getBussTrdTransferOrder().get(0)));
                    }
                }

            }
            else {

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("busiNo", busiNo);
                map.put("FINPROD_TYPE_TG_F23", DictCodeListExt1.金融产品类型_投管用_自定义现金流.getValue());
                // 根据估值日和业务编号进行查找
                PtlCashFlowDto dto = ptlCashFlowService.getEntity(map);
                if (null != bussSettleOrderCommService) {
                    List<BussTrdTransferOrderBase> list = bussSettleOrderCommService.selectOrdersByBizCode(dto.getBusiNo());
                    dto.setBussTrdTransferOrder(list);
                }
                if (StringUtil.isEmpty(dto.getFinprodType2())) {
                    dto.setFinprodType2(finprodType2);
                }
                model.addAttribute("entity", dto);
                if (dto.getBussTrdTransferOrder() != null && !dto.getBussTrdTransferOrder().isEmpty()) {
                    model.addAttribute("entity_bussTrdTransferOrderDto", JsonUtils.object2Json(dto.getBussTrdTransferOrder().get(0)));
                }
            }
        }
        //add by lizhi 是否限制划款指令开关 2020年2月26日  start
        model.addAttribute("GEN_PAY_ORDERS_AUTO",CacheUtil.getSysParam(BussinessConstant.GEN_PAY_ORDERS_AUTO));
        //add by lizhi 是否限制划款指令开关 2020年2月26日  end
        return super.init(model);
    }
    /**
     * 
     * 获得交易对手的联想文本框
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCounterId")
    public Page<SelectOptionDto> getCounterId(){
        return ptlCashFlowService.getCounterId(RequestUtil.getPageParam());
    }
    /**
     * 获取组合代码的下拉框
     * <功能详细描述>
     * @return SuggestDto
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getPortfolioIdToNew")
    public List<SelectOptionDto> getPortfolioIdToNew() {
        return ptlCashFlowService.getPortfolioIdToNew(RequestUtil.getPageParam());
    }
    /**
     * 
     * 资产代码的联想文本框
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFinprodMarketIdToNew")
    public Page<SelectOptionDto> getFinprodMarketIdToNew(){
        Map<String, Object> param = RequestUtil.getPageParam();
        return ptlCashFlowService.getFinprodIdToNew(param);
    }
    /**
     * 
     * 获得交易对手的联想文本框
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getPortfolioId")
    public Page<SelectOptionDto> getPortfolioId(){
        return ptlCashFlowService.getPortfolioId(getParam("search"));
    } 
    /**
     * 
     * 获得关联业务编号的联想文本框
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFTradeId")
    public List<SelectOptionDto> getFTradeId(){
        return ptlCashFlowService.getFTradeId(RequestUtil.getPageParam());
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
        ValidateGroupEdit.class}) PtlCashFlowDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 保存
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) PtlCashFlowDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            String branch = CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE);
            dto.setBranch(Long.parseLong(branch));
          //String parameter = DictCodeList.共通业务类型_自定义现金流.getValue();
            BussinessCodeParameter parameter = new BussinessCodeParameter();
            parameter.setCommonBusinessType(DictCodeList.共通业务类型_自定义现金流);
            String code = baseGetBusinessId !=null ? baseGetBusinessId.buildBussinessCode(parameter):IDGenerator.generate();
            dto.setBusiNo(code);
        }
        ProcessParameter<PtlCashFlowDto> processParameter = new ProcessParameter<PtlCashFlowDto>();
        WorkFlowParameter<PtlCashFlowDto> params = new WorkFlowParameter<PtlCashFlowDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getBusiNo());
        params.setBusinessType(DictCodeList.共通业务类型_自定义现金流.getValue());
        // "自定义现金流交易编号：" + 上述取得的交易编号 + "业务发生日期：" + 画面.业务发生日期 + "组合：" + 画面.组合代码对应的组合名称
        params.setComment(
            entity -> this.getMessage("PtlCashFlow001.busiNo") + "("
                      + entity.getBusiNo() + ")" + "-"
                      + this.getMessage("PtlCashFlow001.settleDate") + "("
                      + DateUtil.format(entity.getSettleDate()) + ")" + "-"
                      + this.getMessage("PtlCashFlow001.portfolioName") + "("
                      + entity.getPortfolioName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = ptlCashFlowService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
