/*
 * 文件名：TrdDepositInvestTrade002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.trademanage.trddepositvaluationtrade.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;

import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

import com.joyintech.tams.valuation.basicdata.business.dto.BussinessCodeParameter;
import com.joyintech.tams.valuation.component.business.service.CommonBusinessService;
import com.joyintech.tams.valuation.component.common.constant.BussinessConstant;
import com.joyintech.tams.valuation.trademanage.trddepositvaluationtrade.dto.TrdDepositValuationTrade001Dto;
import com.joyintech.tams.valuation.trademanage.trddepositvaluationtrade.service.TrdDepositValuationTrade002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 存款交易新建
 * @author fanshuanghu
 * @version 1.0 2018年10月10日
 * @see TrdDepositValuationTrade002Controller
 * @since 1.0
 */
@Controller
@PageId("TrdDepositValuationTrade002")
@RequestMapping("/valuation/trademanage/trddepositvaluationtrade/TrdDepositValuationTrade002")
public class TrdDepositValuationTrade002Controller extends BaseController {
//	@Autowired
//	private TradeCommonService tradeCommonService;
//    /**
//     * 注入业务主键生产类
//     */
//    @Autowired(required=false)
//    private BaseGetBusinessId baseGetBusinessId;

    /**
     * 注入存款类交易Service
     */
    @Autowired
    private TrdDepositValuationTrade002Service trdDepositValuationTrade002Service;
//    /**
//     * 通用Mapper共同代理方法
//     */
//    @Autowired(required = false)
//    private BussCommonMapperDelegateService bussCommonMapperDelegateService;

    @Autowired(required = false)
    private CommonBusinessService commonBusinessService;
    /**
     * 附件上传类
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;
    

    /**
     * 初始化方法
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向, DictCode.计息基础, DictCode.是否, DictCode.存款类资产类型, DictCode.币种
        		, DictCode.行内资产二级分类, DictCode.行内资产三级分类, DictCode.行内资产四级分类);// 新增资产二、三、四级分类 王贵奇 2019/8/1
//        model.addAttribute("buy", DictCodeList.交易方向_买入.getValue());
//        model.addAttribute("YES", DictCodeList.是否_是.getValue());
//        model.addAttribute("YES_OR_NO_N", DictCodeList.是否_否.getValue());
//        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_交易监控.getValue());
//        model.addAttribute("FINPROD_TYPE_F07", DictCodeListExt1.金融产品类型_投管用_存款.getValue());
//        model.addAttribute("PS_T01", DictCodeList.交易方向_买入.getValue());
//        model.addAttribute("PS_T02", DictCodeList.交易方向_卖出.getValue());
//        model.addAttribute("collectType_01", DictCodeListExt2.收取方式_回款前扣除.getValue());
//        model.addAttribute("IS_CAL_LOADRATE", CacheUtil.getSysParam(BussinessConstant.IS_CAL_LOADRATE));
//
//        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01": CacheUtil.getSysParam("IS_SHOW_TYPE"));
//
//        TrdInvestPersonInfo002Dto dto = new TrdInvestPersonInfo002Dto();
//        model.addAttribute("TrdInvestPersonInfo002Dto", JsonUtils.object2Json(dto));
//        BussTrdTransferOrderDto bussTrdTransferOrderDto = new BussTrdTransferOrderDto();
//        model.addAttribute("TrdInvestPersonInfo002Dto", JsonUtils.object2Json(bussTrdTransferOrderDto));
//        String portfolioId = getParam("portfolioId");
//        String entrustId = getParam("entrustId");
//        String finprodId = getParam("finprodId");
//        String tradeId = getParam("tradeId");
//        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
//        if (!WorkFlowUtil.isNotFromToDoList() && workflowId != null && !workflowId.isEmpty()) {
//            TrdDepositInvestTrade001Dto entity = trdDepositInvestTrade002Service.getBussinessEntityByWorkflowId(
//                workflowId);
//            model.addAttribute("finprodId",entity.getFinprodId());
//            model.addAttribute("entity",entity);
//            model.addAttribute("interfSendOpicsList",entity.getInterfSendOpicsList());
//            if(entity.getBussTrdTransferOrder() != null && entity.getBussTrdTransferOrder().size()>0){
//                model.addAttribute("stlPayOrdersList", JsonUtils.object2Json(entity.getBussTrdTransferOrder().get(0)));
//            }
//            model.addAttribute("workflowId",workflowId);
//            model.addAttribute("trdInvestPersonInfo002Dto", JsonUtils.object2Json(entity.getTrdInvestPersonInfo002Dto()));
//            model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(entity.getRiskTrialRetDto()));
//            model.addAttribute("tradeId", entity.getTradeId());
//            // 判断逐渐是否为空
//        }
//        else if (StringUtil.isNotEmpty(tradeId)) {
//            WflTrdProductDeal wflTrdProductDeal = new WflTrdProductDeal();
//            if(bussCommonMapperDelegateService != null){
//                wflTrdProductDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
//                wflTrdProductDeal.setTradeId(tradeId);
//                wflTrdProductDeal = bussCommonMapperDelegateService.selectByMultiPrimaryKey(WflTrdProductDealMapper.class, wflTrdProductDeal, WflTrdProductDeal.class);
//            }
//            if(wflTrdProductDeal != null){
//                String workFlowId = wflTrdProductDeal.getWorkflowId();
//                if(workFlowId != null && !workFlowId.isEmpty()){
//                    TrdDepositInvestTrade001Dto entity = trdDepositInvestTrade002Service.getBussinessEntityByWorkflowId(
//                        workFlowId);
//                    model.addAttribute("finprodId",entity.getFinprodId());
//                    model.addAttribute("entity",entity);
//                    model.addAttribute("workflowId",workflowId);
//                    model.addAttribute("interfSendOpicsList",entity.getInterfSendOpicsList());
//                    model.addAttribute("riskTrialRetDto", JsonUtils.object2Json(entity.getRiskTrialRetDto()));
//                    if(entity.getBussTrdTransferOrder() != null && entity.getBussTrdTransferOrder().size()>0){
//                        model.addAttribute("stlPayOrdersList", JsonUtils.object2Json(entity.getBussTrdTransferOrder().get(0)));
//                    }
//                    model.addAttribute("trdInvestPersonInfo002Dto", JsonUtils.object2Json(entity.getTrdInvestPersonInfo002Dto()));
//                    model.addAttribute("tradeId", workflowId);
//                }
//            }
//            else {
//                TrdProductDeal trdProductDeal = new TrdProductDeal();
//                TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
//                if(bussCommonMapperDelegateService != null){
//                    trdProductDeal = bussCommonMapperDelegateService.selectBySinglePrimaryKey(TrdProductDealMapper.class, tradeId, TrdProductDeal.class);
//                    trdProductDealAdd = bussCommonMapperDelegateService.selectBySinglePrimaryKey(TrdProductDealAddMapper.class, tradeId, TrdProductDealAdd.class);
//                }
//                finprodId = trdProductDeal.getFinprodId();
//                TrdDepositInvestTrade001Dto entity = trdDepositInvestTrade002Service.getFinInfo(finprodId);
//                entity.setPrinAmt(trdProductDeal.getPrinAmt());
//                entity.setTradeDate(trdProductDeal.getTradeDate());
//                entity.setIntAmt(trdProductDeal.getIntAmt());
//                entity.setPs(trdProductDeal.getPs());
//                entity.setRemark(trdProductDeal.getRemark());
//                // 通道交易 新增 强成西 2019/03/26 start
//                entity.setTradeAmtAndFee(trdProductDeal.getTradeAmt());
//                entity.setChlId(trdProductDeal.getChlId());
//
//                TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
//                trdTradeFeeDetail.setTradeId(trdProductDeal.getTradeId());
//                trdTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_通道费.getValue());
//                if(bussCommonMapperDelegateService != null){
//                	trdTradeFeeDetail = bussCommonMapperDelegateService.selectByMultiPrimaryKey(TrdTradeFeeDetailMapper.class, trdTradeFeeDetail, TrdTradeFeeDetail.class);
//                }
//                if(StringUtil.isNotEmpty(trdTradeFeeDetail)){
//                	entity.setChannelFee(trdTradeFeeDetail.getFeeAmt());
//                }
//                // 通道交易 新增 强成西 2019/03/26 end
//                entity.setLoadRate("");
//                //entity.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(trdProductDealAdd.getPortfolioId()));
//                FinProduct finProduct = bussCommonMapperDelegateService.selectBySinglePrimaryKey(FinProductMapper.class, finprodId, FinProduct.class);
//                entity.setFinprodMarketId(finProduct.getFinprodMarketId());
//                entity.setEntrustId(trdProductDealAdd.getEntrustId());
//                TrdInvestPersonInfo002Dto trdInvestPersonInfo002Dto = new TrdInvestPersonInfo002Dto();
//                entity.setTrdInvestPersonInfo002Dto(trdInvestPersonInfo002Dto);
//                createEntity(entity,portfolioId,trdProductDeal);
//                StlPayOrdersDto stlPayOrdersList = null;
//                if(trdDepositInvestTrade002Service.stlInfo(tradeId) != null){
//                    stlPayOrdersList = trdDepositInvestTrade002Service.stlInfo(tradeId);
//                    List<StlPayOrdersDto> stlPayOrdersLists = new ArrayList<>();
//                    stlPayOrdersLists.add(stlPayOrdersList);
//                    entity.setStlPayOrdersList(stlPayOrdersLists);
//                };
//                List<InterfSendOpics> interfSendOpicsList = trdDepositInvestTrade002Service.getOpics(tradeId.substring(0,18));
//                if(interfSendOpicsList != null && interfSendOpicsList.size() > 1){
//                    for(int i = 0; i<2; i++){
//                        if(!interfSendOpicsList.get(i).getVdate().isEmpty() && interfSendOpicsList.get(i).getVdate().length()==8){
//                            String vDate = interfSendOpicsList.get(i).getVdate();
//                            String temp = vDate.substring(0, 4) + "-" +vDate.substring(4,6) + "-" + vDate.substring(6,8);
//                            interfSendOpicsList.get(i).setVdate(temp);
//                        }
//                        if(!interfSendOpicsList.get(i).getMdate().isEmpty() && interfSendOpicsList.get(i).getMdate().length()==8){
//                            String mDate = interfSendOpicsList.get(i).getMdate();
//                            String temp = mDate.substring(0, 4) + "-" +mDate.substring(4,6) + "-" + mDate.substring(6,8);
//                            interfSendOpicsList.get(i).setMdate(temp);
//                        }
//                        if(!interfSendOpicsList.get(i).getDealdate().isEmpty() && interfSendOpicsList.get(i).getDealdate().length()==8){
//                            String dealdate = interfSendOpicsList.get(i).getDealdate();
//                            String temp = dealdate.substring(0, 4) + "-" +dealdate.substring(4,6) + "-" + dealdate.substring(6,8);
//                            interfSendOpicsList.get(i).setDealdate(temp);
//                        }
//                    }
//                    model.addAttribute("interfSendOpicsList",interfSendOpicsList);
//                }
//                model.addAttribute("finprodId",entity.getFinprodId());
//                entity.setTradeId(tradeId.substring(0,18));
//                model.addAttribute("entity",entity);
////                model.addAttribute("stlPayOrdersList",JsonUtils.object2Json(stlPayOrdersList));
//                model.addAttribute("trdInvestPersonInfo002Dto", JsonUtils.object2Json(entity.getTrdInvestPersonInfo002Dto()));
//                model.addAttribute("tradeId", tradeId);
//            }
//        }else if(entrustId != null){
//            FinProduct finProduct = null;
//            String trader = "";
//            EtrEntrust etrEntrust = trdDepositInvestTrade002Service.getEtrInfo(entrustId);
//            TrdInvestPersonInfo002Dto trdInvestPersonInfo002Dto = new TrdInvestPersonInfo002Dto();
//            portfolioId = etrEntrust.getPortfolioId();
//            finprodId = etrEntrust.getFinprodId();
//            TrdDepositInvestTrade001Dto entity = trdDepositInvestTrade002Service.getFinInfo(finprodId);
//            if(bussCommonMapperDelegateService != null){
//                finProduct = bussCommonMapperDelegateService.selectBySinglePrimaryKey(FinProductMapper.class, finprodId, FinProduct.class);
//                trader= trdDepositInvestTrade002Service.getTrader(entrustId);
//            }
//            if(entity != null){
//                entity.setPrinAmt(etrEntrust.getEntrustAmt());
//                entity.setPortfolioId(portfolioId);
//                entity.setFinprodId(finprodId);
//                if(finProduct != null){
//                    entity.setFinprodMarketId(finProduct.getFinprodMarketId());
//                }
//                if(trader != null && !"".equals(trader)){
//                    trdInvestPersonInfo002Dto.setTrader(trader);
//                }
//                entity.setEntrustId(entrustId);
//                entity.setTrdInvestPersonInfo002Dto(trdInvestPersonInfo002Dto);
//                trdInvestPersonInfo002Dto.setCounterId(etrEntrust.getCounterId());
//                trdInvestPersonInfo002Dto.setCounterTrader(etrEntrust.getCounterTrader());
//                trdInvestPersonInfo002Dto.setIsOutTrade(etrEntrust.getIsOutTrade());
//                trdInvestPersonInfo002Dto.setContactInfo(etrEntrust.getContactInfo());
//                entity.setTrdInvestPersonInfo002Dto(trdInvestPersonInfo002Dto);
//                entity.setPs(etrEntrust.getPs());
//                model.addAttribute("trdInvestPersonInfo002Dto", JsonUtils.object2Json(entity.getTrdInvestPersonInfo002Dto()));
//            }
//            model.addAttribute("finprodId", finprodId);
//            model.addAttribute("entity", entity);
//            model.addAttribute("tradeId", entrustId);
//        }
        TrdDepositValuationTrade001Dto entity=new TrdDepositValuationTrade001Dto();
//        model.addAttribute("entity", entity);
        return super.init(model);
    }

    /**
     * 自定义产品代码下拉框
     * @param request
     * @return
     */
    @RequestMapping("/findPortfolioId")
    @ResponseBody
    public Page<SelectOptionDto> findPortfolioId(HttpServletRequest request) {
        Map<String, Object> param =RequestUtil.getPageParam();
        //param.put("finprodType", BussinessDictCodeList.金融产品类型_计划类资产.getValue()); //F17
        param.put("finprodType", "F01"); //测试
        return trdDepositValuationTrade002Service.findPortfolioId(param);
    }


    /**
     * 自定义产品代码下拉框
     * @param request
     * @return
     */
    @RequestMapping("/findFinprodId")
    @ResponseBody
    public Page<SelectOptionDto> findFinprodId(HttpServletRequest request) {
        Map<String, Object> param =RequestUtil.getPageParam();
        //param.put("finprodType", BussinessDictCodeList.金融产品类型_计划类资产.getValue()); //F17
        param.put("finprodType", "F01"); //测试
        return trdDepositValuationTrade002Service.findFinprodId(param);
    }

    @ResponseBody
    @RequestMapping("/getCounterId")
    public Page<SelectOptionDto> getCounterId(){
        return trdDepositValuationTrade002Service.getCounterId(RequestUtil.getPageParam());
    }

    /**
     * 获取产品代码
     * @return 联想下拉框实体类集合
     */
    @ResponseBody
    @RequestMapping("getProdId")
    public List<SelectOptionDto> getProdId(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return commonBusinessService.getProdId(map);
    }

    /**
     * 获取资产代码
     * @return 联想下拉框实体类集合
     */
    @ResponseBody
    @RequestMapping("getAssetId")
    public List<SelectOptionDto> getAssetId(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return commonBusinessService.getAssetId(map);
    }
    /**
     * 获取资金账户
     * @return 联想下拉框实体类集合
     */
    @ResponseBody
    @RequestMapping("getTrdAcctId")
    public List<SelectOptionDto> getTrdAcctId(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return commonBusinessService.getTrdAcctId(map);
    }



    /**
     * 保存按钮点击时的check
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) TrdDepositValuationTrade001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }



//    /**

//     *
//     * 保存提交工作流
//     * <功能详细描述>
//     * @param dto 保存参数
//     * @param br 保存参数
//     * @return 保存返回信息
//     * @throws Exception 抛出异常
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping("save")
//    public AjaxResult save(@RequestBody @Validated({
//            ValidateGroupEdit.class}) MstNaturalPerson001Dto dto, BindingResult br)
//            throws Exception {
//        if (br.hasErrors()) {
//            return new AjaxResult(br);
//        }
//        if(StringUtil.isEmpty(dto.getCustomerId())){
//            BussinessCodeParameter param = new BussinessCodeParameter();
//            param.setCommonBusinessType(DictCodeList.共通业务类型_自然人管理);
//            String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator.generate();
//            dto.setCustomerId(code);
//        }
//        ProcessParameter<MstNaturalPerson001Dto> processParameter = new ProcessParameter<MstNaturalPerson001Dto>();
//        WorkFlowParameter<MstNaturalPerson001Dto> params = new WorkFlowParameter<MstNaturalPerson001Dto>();
//        params.setPageId(this.getPageId());
//        params.setElementId(dto.getElementId());
//        params.setBusinessCode(item -> item.getCustomerId());
//        params.setBusinessType(DictCodeList.共通业务类型_自然人管理.getValue());
//
//        // 设置流程业务说明, entity即传入的dto
//        params.setComment(entity -> "客户名称" + "-" + entity.getCustomerName());
//        processParameter.setWorkFlowParameter(params);
//        String msg = mstNaturalPerson001Service.start(processParameter, dto);
//        return new AjaxResult(msg);
//    }

    /**
     * 保存
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
//    @ResponseBody
//    @RequestMapping("save")
//    public AjaxResult save(@RequestBody @Validated({
//            ValidateGroupEdit.class}) TrdDepositValuationTrade001Dto dto, BindingResult br)
//            throws Exception {
//        if (br.hasErrors()) {
//            return new AjaxResult(br);
//        }
//        ProcessParameter<TrdDepositValuationTrade001Dto> processParameter = new ProcessParameter<TrdDepositValuationTrade001Dto>();
//        WorkFlowParameter<TrdDepositValuationTrade001Dto> params = new WorkFlowParameter<TrdDepositValuationTrade001Dto>();
//        params.setPageId("TrdDepositInvestTrade002");
//        if(dto.getTradeId() == null || dto.getTradeId().isEmpty() || dto.getTradeId().length() > 25){
//            BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
//            bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_存款录入);
//            if(DictCodeList.存款类资产类型_同业借款.getValue().equals(dto.getFinprodType())){
//                bussinessCodeParameter.setType1(DictCodeList.业务编号一级分类_同业借款);
//            }else{
//                bussinessCodeParameter.setType1(DictCodeList.业务编号一级分类_存放同业);
//            }
//            dto.setTradeId((baseGetBusinessId != null && baseGetBusinessId.buildBussinessCode(bussinessCodeParameter) != null)? baseGetBusinessId.buildBussinessCode(bussinessCodeParameter):IDGenerator.generate().substring(0, 30));
//            if(dto.getTradeId().length()>31){
//                dto.setTradeId(dto.getTradeId().substring(0,29));
//            }
////            dto.setTradeId(IDGenerator.generate().substring(0, 31));
//        }
//        if(dto.getBussTrdTransferOrder() != null && dto.getBussTrdTransferOrder().size() > 0 && dto.getBussTrdTransferOrder().get(0) != null && !dto.getBussTrdTransferOrder().get(0).toString().isEmpty()){
//            dto.setBussTrdTransferOrder(bussTrdTransferOrderService.save(dto.getBussTrdTransferOrder()));
//            StlPayOrdersDto stlPayOrdersDto = new StlPayOrdersDto();
//            BeanUtil.copyPropertiesIgnoreNull(stlPayOrdersDto, dto.getBussTrdTransferOrder().get(0));
//            List<StlPayOrdersDto> list = new ArrayList<StlPayOrdersDto>();
//            list.add(stlPayOrdersDto);
//            dto.setStlPayOrdersList(list);
//        }
//        if (CollectionUtils.isNotEmpty(dto.getFileArray())
//                && StringUtil.isNotEmpty(dto.getTradeId())) {
//            // 将附件和当前业务对象绑定（更新附件的业务代码）
//            fileAttachmentProcess.updateBussinessNo(
//                    dto.getFileArray().stream().map(UploadFile::getFileNo).collect(
//                            Collectors.toList()),
//                    dto.getTradeId());
//        }
//        params.setElementId(dto.getElementId());
//        params.setBusinessCode(item -> item.getTradeId());
//        params.setBusinessType(DictCodeList.共通业务类型_存款类资产交易.getValue());
//        // 业务说明：“交易编号：” + 上述取得的交易编号+“资产名称：”+画面.资产名称+“交易日：”+画面.交易日
//        params.setComment(
//                entity -> this.getMessage("TrdPlanRateInvestTrade001.tradeId") + "("
//                        + entity.getTradeId() + ")" + "-"
//                        + this.getMessage("TrdPlanRateInvestTrade001.finprodName") + "("
//                        + entity.getFinprodName() + ")"
//                        + this.getMessage("TrdPlanRateInvestTrade001.tradeDate") + "("
//                        + DateUtil.format(entity.getTradeDate()) + ")" );
//        processParameter.setWorkFlowParameter(params);
//        String msg = trdDepositValuationTrade002Service.start(processParameter, dto);
//        return new AjaxResult(msg);
//    }


}
