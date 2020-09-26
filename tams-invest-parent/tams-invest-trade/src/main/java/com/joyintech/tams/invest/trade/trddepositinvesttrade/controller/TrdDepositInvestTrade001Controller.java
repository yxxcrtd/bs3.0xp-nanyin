/*
 * 文件名：TrdDepositInvestTrade001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.trddepositinvesttrade.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.trade.service.TrdCommonBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.trade.trddepositinvesttrade.dto.TrdDepositInvestTrade001Dto;
import com.joyintech.tams.invest.trade.trddepositinvesttrade.service.TrdDepositInvestTrade001Service;
import com.joyintech.tams.invest.trade.trddepositinvesttrade.service.TrdDepositInvestTrade002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 存款交易查询
 * @author fanshuanghu
 * @version 1.0 2018年10月10日
 * @see TrdDepositInvestTrade001Controller
 * @since 1.0
 */
@Controller
@PageId("TrdDepositInvestTrade001")
@RequestMapping("/invest/trade/trddepositinvesttrade/TrdDepositInvestTrade001")
public class TrdDepositInvestTrade001Controller extends BaseController{
    
        /*
         * 注入service
         */
        @Autowired
        private TrdDepositInvestTrade001Service trdDepositInvestTrade001Service;
        
        @Autowired
        private TrdDepositInvestTrade002Service trdDepositInvestTrade002Service;
    /**
     * 通用Mapper共同代理方法
     */
    @Autowired(required = false)
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;


    /**
     * 清算指令共通Service
     */
    @Autowired(required = false)
    private BussSettleOrderCommService bussSettleOrderCommService;

    /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    
    @Autowired
    private TrdDepositInvestTrade002Controller trade002Controller;

    /**
     * 交易基础组件调用Service
     */
    @Autowired
    private TrdCommonBaseService trdCommonBaseService;

        /**
         * 初始化方法
         * {@inheritDoc}
         */
        @Override
        public String init(Model model) {
            this.setSelectOptionToModel(model, DictCode.交易方向,DictCode.交易流程状态,DictCode.存款类资产类型
            		,DictCode.行内资产二级分类,DictCode.行内资产三级分类,DictCode.行内资产四级分类,DictCode.行内资产五级分类);//新增资产二、三、四级分类 王贵奇 2019/08/01
            model.addAttribute("TRADE_STATUS", DictCodeListExt1.交易流程状态_有效.getValue());
            
            model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
            
            return super.init(model);
        }
        /**
         * 获取委托代码
         * @return 联想文本框实体类集合
         * @see
         * @since 1.0
         */
        @ResponseBody
        @RequestMapping("getEntrustId")
        public List<SuggestDto> getEntrustId(){
            String param = getParam("param");
            return trdDepositInvestTrade001Service.getEntrustId(param);
        }
        /**
         * 获取组合代码
         * @return 联想文本框实体类集合
         * @see
         * @since 1.0
         */
        @ResponseBody
        @RequestMapping("getPortfolioId")
        public Page<SelectOptionDto> getPortfolioId(){
            return trdDepositInvestTrade001Service.getPortfolioId(RequestUtil.getPageParam());
        }
        /**
         * 获取资金代码
         * @return 联想文本框实体类集合
         * @see
         * @since 1.0
         */
        @ResponseBody
        @RequestMapping("getFinprodMarketId")
        public List<SuggestDto> getFinprodMarketId(){
            String param = getParam("param");
            return trdDepositInvestTrade001Service.getFinprodMarketId(param);
        }
        /**
         * 分页查询
         * 
         * @return Page<TrdDepositInvestTrade001Dto>泛型的page实体类
         * @throws Exception 异常
         * @see
         * @since 1.0
         */
        @RequestMapping("findPage")
        @ResponseBody
        public Page<TrdDepositInvestTrade001Dto> findPage()
            throws Exception {
            return trdDepositInvestTrade001Service.findPage(RequestUtil.getPageParam());
        }
        
        /**
         * 画面导出功能实现
         * 
         * @return ResponseEntity 确认的实体类
         * @throws Exception 异常
         * @see
         * @since 1.0
         */
        @RequestMapping("export")
        public ResponseEntity<byte[]> export()
            throws Exception {
        	Map<String, Object> param = RequestUtil.getParameters(getRequest());
        	param.put("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE"));
        	
        	return trdDepositInvestTrade001Service.export(this.getPageId(),
            		param);
        }
        
        /**
         * 删除按钮点击时的check
         * @param dto 验证的实体类
         * @param result 验证的结果
         * @return 错误信息
         */
        @ResponseBody
        @RequestMapping("check")
        public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) TrdDepositInvestTrade001Dto dto, BindingResult result) {

           //已撤销的交易不允许撤销
            WflValidInputParameter parameter = new WflValidInputParameter();
            parameter.setPageId("TrdDepositInvestTrade002");
            parameter.setBusinessDataPk1(dto.getTradeId() + "R");
            parameter.setBusinessDataPk2("F07");
            if (mstWorkflowCommonService.isInWflProcess(parameter)) {
                ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                errorList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0041")));
                AjaxResult ar = new AjaxResult();
                ar.setErrorList(errorList);
                return ar;
            }

            //update by xr 撤销时查看查询日之后是否已有交易 ，已有交易则不允许撤销
            if(StringUtil.isNotEmpty(dto)&&dto.getOperateType().equals("delete")){
                TrdProductDeal deal=new TrdProductDeal();
                deal.setFinprodId(dto.getFinprodId());
                deal.setTradeDate(dto.getTradeDate());
                deal.setPortfolioId(dto.getPortfolioId());
                deal.setTradeStatus(DictCodeListExt1.有效状态_有效.getValue());
                deal.setIsCancel(DictCodeListExt1.是否_否.getValue());
                List<TrdProductDeal> list=trdDepositInvestTrade001Service.selectByTrade(deal);
                if(!StringUtil.isEmptyList(list)){
                    ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                    errorList.add(new ValidMessage(this.getMessage("该资产交易之后还有其他交易，不允许撤销")));
                    AjaxResult ar = new AjaxResult();
                    ar.setErrorList(errorList);
                    return ar;
                }
            }

            // 确认提醒 TISHKBS-1113【组合资金账户头寸查询】内转交易撤销提醒 add by hpp 2020-09-17 start
            if (result.getErrorCount() == 0) {
                AjaxResult ajaxResult = new AjaxResult();
                String confirmMsg = "";
                String changeLineMark = "<br/>";

                //根据交易编号查询另一笔未撤销的转仓交易信息
                String tradeId = dto.getSelectedItem().get(0).getTradeId();
                TrdProductDeal res = trdCommonBaseService.selectZCDeal(tradeId);
                if(StringUtil.isNotEmpty(res)){
                    confirmMsg = "此交易为转仓交易，撤销后需要将业务编号为["+
                            res.getTradeId()+"]的反向转仓交易也撤销掉，请确认是否继续？";
                }else{
                    confirmMsg = "请确认是否撤销？";
                }
                if (StringUtil.isNotBlank(confirmMsg)) {
                    ajaxResult.setData(confirmMsg);
                }
                if (ajaxResult != null) {
                    return ajaxResult;
                }
            }
            // 确认提醒 TISHKBS-1113【组合资金账户头寸查询】内转交易撤销提醒 add by hpp 2020-09-17 end

            return commonCheck(result, dto.getOperateType());
        }
        /**
         * 删除操作
         * @param dto 验证的实体类
         * @param br 错误结果
         * @return 错误信息
         * @throws Exception 异常
         */
        @ResponseBody
        @RequestMapping("delete")
        public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) TrdDepositInvestTrade001Dto dto, BindingResult br)
            throws Exception {
            if (br.hasErrors()) {
                return new AjaxResult(br);
            }
            dto = dto.getSelectedItem().get(0);
            //判断流程中是否存在,如果流程不存在则说明是生成的反向交易，组装撤销dto yjm start 20191015
            WflValidInputParameter parameter = new WflValidInputParameter();
            parameter.setPageId("TrdDepositInvestTrade002");
            parameter.setBusinessDataPk1(dto.getTradeId());
            if (!mstWorkflowCommonService.isProcessData(parameter)){
                TrdProductDeal trdProductDeal = new TrdProductDeal();
                TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
                String tradeId = dto.getTradeId();
                String portfolioId = dto.getPortfolioId();
                if(bussCommonMapperDelegateService != null){ 
                    trdProductDeal = bussCommonMapperDelegateService.selectBySinglePrimaryKey(TrdProductDealMapper.class, tradeId, TrdProductDeal.class);
                    trdProductDealAdd = bussCommonMapperDelegateService.selectBySinglePrimaryKey(TrdProductDealAddMapper.class, tradeId, TrdProductDealAdd.class);
                }
                String  finprodId = trdProductDeal.getFinprodId();
                dto = trdDepositInvestTrade002Service.getFinInfo(finprodId);
                dto.setTradeId(tradeId);
                dto.setPrinAmt(trdProductDeal.getPrinAmt());
                dto.setTradeDate(trdProductDeal.getTradeDate());
                dto.setIntAmt(trdProductDeal.getIntAmt());
                dto.setPs(trdProductDeal.getPs());
                dto.setRemark(trdProductDeal.getRemark());
                // 通道交易 新增 强成西 2019/03/26 start
                dto.setTradeAmtAndFee(trdProductDeal.getTradeAmt());
                dto.setChlId(trdProductDeal.getChlId());
                dto.setChannelFee(trdProductDeal.getFeeAmtPay());
                // 通道交易 新增 强成西 2019/03/26 end
                dto.setLoadRate("");
                FinProduct finProduct = bussCommonMapperDelegateService.selectBySinglePrimaryKey(FinProductMapper.class, finprodId, FinProduct.class);
                dto.setFinprodMarketId(finProduct.getFinprodMarketId());
                dto.setEntrustId(trdProductDealAdd.getEntrustId());
                TrdInvestPersonInfo002Dto trdInvestPersonInfo002Dto = new TrdInvestPersonInfo002Dto();
                dto.setTrdInvestPersonInfo002Dto(trdInvestPersonInfo002Dto);
                trade002Controller.createEntity(dto,portfolioId,trdProductDeal);
                StlPayOrdersDto stlPayOrdersList = null;
                if(trdDepositInvestTrade002Service.stlInfo(tradeId) != null){
                    stlPayOrdersList = trdDepositInvestTrade002Service.stlInfo(tradeId);
                    List<StlPayOrdersDto> stlPayOrdersLists = new ArrayList<>();
                    stlPayOrdersLists.add(stlPayOrdersList);
                    dto.setStlPayOrdersList(stlPayOrdersLists);
                };
                dto.setOperateType("delete");
            }
            //判断流程中是否存在,如果流程不存在则说明是生成的反向交易，组装撤销dto yjm end 20191015  
            ProcessParameter<TrdDepositInvestTrade001Dto> processParameter = new ProcessParameter<TrdDepositInvestTrade001Dto>();
            WorkFlowParameter<TrdDepositInvestTrade001Dto> params = new WorkFlowParameter<TrdDepositInvestTrade001Dto>();
            params.setPageId("TrdDepositInvestTrade002");
            params.setElementId(dto.getElementId());
            params.setBusinessCode(item -> item.getTradeId());
            params.setBusinessType(DictCodeList.共通业务类型_存款类资产交易.getValue());
            // 业务说明：“交易编号：” + 上述取得的交易编号+“资产名称：”+画面.资产名称+“交易日：”+画面.交易日 
            params.setComment(
                entity -> this.getMessage("TrdDepositInvestTrade002.tradeId") + "("
                          + entity.getTradeId() + ")" + "-"
                          + this.getMessage("TrdDepositInvestTrade001.finprodName") + "("
                          + entity.getFinprodName() + ")" 
                          + this.getMessage("TrdDepositInvestTrade001.tradeDate") + "("
                              + DateUtil.format(entity.getTradeDate()) + ")" );
            processParameter.setWorkFlowParameter(params);
            String msg = trdDepositInvestTrade002Service.start(processParameter, dto);
            return new AjaxResult(msg);
        }
}
