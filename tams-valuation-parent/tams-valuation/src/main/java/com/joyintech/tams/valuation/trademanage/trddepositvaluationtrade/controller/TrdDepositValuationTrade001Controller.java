/*
 * 文件名：TrdDepositInvestTrade001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.trademanage.trddepositvaluationtrade.controller;

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
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.valuation.trademanage.trddepositvaluationtrade.dto.TrdDepositValuationTrade001Dto;
import com.joyintech.tams.valuation.trademanage.trddepositvaluationtrade.service.TrdDepositValuationTrade001Service;
import com.joyintech.tams.valuation.trademanage.trddepositvaluationtrade.service.TrdDepositValuationTrade002Service;
import com.joyintech.tams.valuation.trademanage.trdplanratevaluationtrade.dto.TrdPlanRateValuationTradeDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 存款交易查询
 * @author fanshuanghu
 * @version 1.0 2018年10月10日
 * @see TrdDepositValuationTrade001Controller
 * @since 1.0
 */
@Controller
@PageId("TrdDepositValuationTrade001")
@RequestMapping("/valuation/trademanage/trddepositvaluationtrade/TrdDepositValuationTrade001")
public class TrdDepositValuationTrade001Controller extends BaseController {
    
        /*
         * 注入service
         */
        @Autowired
        private TrdDepositValuationTrade001Service trdDepositValuationTrade001Service;
        
        @Autowired
        private TrdDepositValuationTrade002Service trdDepositValuationTrade002Service;
//    /**
//     * 通用Mapper共同代理方法
//     */
//    @Autowired(required = false)
//    private BussCommonMapperDelegateService bussCommonMapperDelegateService;



    /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    
    @Autowired
    private TrdDepositValuationTrade002Controller trade002Controller;
        /**
         * 初始化方法
         * {@inheritDoc}
         */
        @Override
        public String init(Model model) {
            this.setSelectOptionToModel(model, DictCode.交易方向, DictCode.交易流程状态, DictCode.存款类资产类型
            		, DictCode.行内资产二级分类, DictCode.行内资产三级分类, DictCode.行内资产四级分类, DictCode.行内资产五级分类);//新增资产二、三、四级分类 王贵奇 2019/08/01
            model.addAttribute("TRADE_STATUS", DictCodeListExt1.交易流程状态_有效.getValue());
            
            model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01": CacheUtil.getSysParam("IS_SHOW_TYPE"));
            
            return super.init(model);
        }


    /**
     * @Description:页面查询，表格分页
     * @return:
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdDepositValuationTrade001Dto> findPage()
            throws Exception {
        Page<TrdDepositValuationTrade001Dto> page = trdDepositValuationTrade001Service.selectForPage(
                RequestUtil.getParameters(getRequest()));
        return page;
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
        	
        	return trdDepositValuationTrade001Service.export(this.getPageId(),
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
            ValidateGroupDelete.class}) TrdDepositValuationTrade001Dto dto, BindingResult result) {
            return commonCheck(result, dto.getOperateType());
        }
//        /**
//         * 删除操作
//         * @param dto 验证的实体类
//         * @param br 错误结果
//         * @return 错误信息
//         * @throws Exception 异常
//         */
//        @ResponseBody
//        @RequestMapping("delete")
//        public AjaxResult delete(@RequestBody @Validated({
//            ValidateGroupDelete.class}) TrdDepositInvestTrade001Dto dto, BindingResult br)
//            throws Exception {
//            if (br.hasErrors()) {
//                return new AjaxResult(br);
//            }
//            dto = dto.getSelectedItem().get(0);
//            //判断流程中是否存在,如果流程不存在则说明是生成的反向交易，组装撤销dto yjm start 20191015
//            WflValidInputParameter parameter = new WflValidInputParameter();
//            parameter.setPageId("TrdDepositInvestTrade002");
//            parameter.setBusinessDataPk1(dto.getTradeId());
//            if (!mstWorkflowCommonService.isProcessData(parameter)){
//                TrdProductDeal trdProductDeal = new TrdProductDeal();
//                TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
//                String tradeId = dto.getTradeId();
//                String portfolioId = dto.getPortfolioId();
//                if(bussCommonMapperDelegateService != null){
//                    trdProductDeal = bussCommonMapperDelegateService.selectBySinglePrimaryKey(TrdProductDealMapper.class, tradeId, TrdProductDeal.class);
//                    trdProductDealAdd = bussCommonMapperDelegateService.selectBySinglePrimaryKey(TrdProductDealAddMapper.class, tradeId, TrdProductDealAdd.class);
//                }
//                String  finprodId = trdProductDeal.getFinprodId();
//                dto = trdDepositInvestTrade002Service.getFinInfo(finprodId);
//                dto.setTradeId(tradeId);
//                dto.setPrinAmt(trdProductDeal.getPrinAmt());
//                dto.setTradeDate(trdProductDeal.getTradeDate());
//                dto.setIntAmt(trdProductDeal.getIntAmt());
//                dto.setPs(trdProductDeal.getPs());
//                dto.setRemark(trdProductDeal.getRemark());
//                // 通道交易 新增 强成西 2019/03/26 start
//                dto.setTradeAmtAndFee(trdProductDeal.getTradeAmt());
//                dto.setChlId(trdProductDeal.getChlId());
//                dto.setChannelFee(trdProductDeal.getFeeAmtPay());
//                // 通道交易 新增 强成西 2019/03/26 end
//                dto.setLoadRate("");
//                FinProduct finProduct = bussCommonMapperDelegateService.selectBySinglePrimaryKey(FinProductMapper.class, finprodId, FinProduct.class);
//                dto.setFinprodMarketId(finProduct.getFinprodMarketId());
//                dto.setEntrustId(trdProductDealAdd.getEntrustId());
//                TrdInvestPersonInfo002Dto trdInvestPersonInfo002Dto = new TrdInvestPersonInfo002Dto();
//                dto.setTrdInvestPersonInfo002Dto(trdInvestPersonInfo002Dto);
//                trade002Controller.createEntity(dto,portfolioId,trdProductDeal);
//                StlPayOrdersDto stlPayOrdersList = null;
//                if(trdDepositInvestTrade002Service.stlInfo(tradeId) != null){
//                    stlPayOrdersList = trdDepositInvestTrade002Service.stlInfo(tradeId);
//                    List<StlPayOrdersDto> stlPayOrdersLists = new ArrayList<>();
//                    stlPayOrdersLists.add(stlPayOrdersList);
//                    dto.setStlPayOrdersList(stlPayOrdersLists);
//                };
//                dto.setOperateType("delete");
//            }
//            //判断流程中是否存在,如果流程不存在则说明是生成的反向交易，组装撤销dto yjm end 20191015
//            ProcessParameter<TrdDepositInvestTrade001Dto> processParameter = new ProcessParameter<TrdDepositInvestTrade001Dto>();
//            WorkFlowParameter<TrdDepositInvestTrade001Dto> params = new WorkFlowParameter<TrdDepositInvestTrade001Dto>();
//            params.setPageId("TrdDepositInvestTrade002");
//            params.setElementId(dto.getElementId());
//            params.setBusinessCode(item -> item.getTradeId());
//            params.setBusinessType(DictCodeList.共通业务类型_存款类资产交易.getValue());
//            // 业务说明：“交易编号：” + 上述取得的交易编号+“资产名称：”+画面.资产名称+“交易日：”+画面.交易日
//            params.setComment(
//                entity -> this.getMessage("TrdDepositInvestTrade002.tradeId") + "("
//                          + entity.getTradeId() + ")" + "-"
//                          + this.getMessage("TrdDepositInvestTrade001.finprodName") + "("
//                          + entity.getFinprodName() + ")"
//                          + this.getMessage("TrdDepositInvestTrade001.tradeDate") + "("
//                              + DateUtil.format(entity.getTradeDate()) + ")" );
//            processParameter.setWorkFlowParameter(params);
//            String msg = trdDepositInvestTrade002Service.start(processParameter, dto);
//            return new AjaxResult(msg);
//        }
}
