/*
 * 文件名：TaxBalanceQuery001Service.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：leipeng 修改时间：2018年10月9日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.paythevatquery.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.PaTrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.PaTrdProductDealMapper;
import com.joyintech.tams.common.base.dao.StlPayOrdersMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.dao.TrdTradeFeeDetailMapper;
import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.product.common.service.ProductCommonService;
import com.joyintech.tams.invest.product.paythevatquery.dao.PayTheVatQuery003Mapper;
import com.joyintech.tams.invest.product.paythevatquery.dto.PayTheVatQuery001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 纳税增值税查询
 * 
 * @author chufengjie
 * @version 1.0 2019年02月23日
 * @see PayTheVatQuery001Dto
 * @since 1.0
 */
@Service("FinproductPayTheVatQuery003Service")
@Transactional(rollbackFor = Exception.class)
public class PayTheVatQuery003Service extends CommonExportService<PayTheVatQuery001Dto> implements BaseProcessService<PayTheVatQuery001Dto> {

    @Autowired
    private PayTheVatQuery003Mapper payTheVatQuery003Mapper;

    @Autowired
    private ProductCommonService productCommonService;

    /**
     * 根据条件分页查询
     *
     * @param booksetId
     * @return
     * @throws Exception
     */
    public List<PayTheVatQuery001Dto> findFinproduct(@Param("booksetId") String booksetId,
                                                     @Param("seitleDate") String seitleDate) {
        return payTheVatQuery003Mapper.findFinproduct(booksetId, seitleDate);
    }

    /**
     * 根据条件分页查询
     *
     * @param tradeId
     * @return
     * @throws Exception
     */
    public List<PayTheVatQuery001Dto> findPtradeid(@Param("tradeId") String tradeId) {
        return payTheVatQuery003Mapper.findPtradeid(tradeId);
    }

    /**
     * 根据汇总交易编号查询交易
     *
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    public List<PayTheVatQuery001Dto> queryTotleTradeId(@Param("tradeId") String tradeId) {
        Map<String, String> params = new HashMap<>();
        params.put("TAX0102", BussinessDictCodeList.税类型_利息收入.getValue());
        params.put("TAX0202", BussinessDictCodeList.税类型_红利收入.getValue());
        params.put("TAX0302", BussinessDictCodeList.税类型_价差收入.getValue());
        params.put("ALT0102", BussinessDictCodeList.税类型_城建税.getValue());
        params.put("ALT0202", BussinessDictCodeList.税类型_教育费.getValue());
        params.put("ALT0302", BussinessDictCodeList.税类型_地方教育费.getValue());
        params.put("ALT0402", BussinessDictCodeList.税类型_水利基金税.getValue());
        params.put("busiType16", BussinessDictCodeList.业务类型_增值税交易.getValue());
        params.put("finprodType", DictCodeListExt1.金融产品类型_税额.getValue());
        params.put("finprodType2", BussinessDictCodeList.金融产品类型_投管用_税额.getValue());
        params.put("ps01", BussinessDictCodeList.收支方向_收入.getValue());
        params.put("tradeStatus01", BussinessDictCodeList.有效状态_有效.getValue());
        params.put("tradeStatus03", BussinessDictCodeList.有效状态_撤销.getValue());
        params.put("tradeId", tradeId);
        return payTheVatQuery003Mapper.queryTotleTradeId(params);
    }

    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;

    @Autowired
    private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;

    @Autowired
    private TrdProductDealMapper trdProductDealMapper;

    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;

    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;

    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;

    @Autowired
    private TrdTradeFeeDetailMapper trdTradeFeeDetailMapper;

    
    /**
     * 撤销入库操作
     *
     * @param dto
     * @return
     * @throws Exception
     */
    public void doComplete(PayTheVatQuery001Dto dto, OperationType arg1) {
        // 根据汇总交易编号查询金融产品交易汇总
        TrdFinProductDealTotal trdFinProductDealTotal = this.genTrdFinProductDealTotalRevoke(dto.getTotleTradeId());
        if (trdFinProductDealTotal != null) {
            trdFinProductDealTotalMapper.insert(trdFinProductDealTotal);
        }

        // 根据汇总交易编号查询金融产品交易汇总扩展
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = productCommonService.genPaTrdFinProductDealTotalRevoke(
            dto.getTotleTradeId());
        if (paTrdFinProductDealTotal != null) {
            paTrdFinProductDealTotalMapper.insert(paTrdFinProductDealTotal);
        }

        // 根据汇总交易编号生成金融产品交易撤销数据
        List<TrdProductDeal> trdProductDealList = this.genTrdProductDealRevoke(dto.getTotleTradeId(), null);
        if (!StringUtil.isEmptyList(trdProductDealList)) {
            trdProductDealList.forEach(trdProductDeal -> trdProductDealMapper.insertSelective(trdProductDeal));
        }
        // 根据汇总交易编号查询金融产品交易扩展
        List<PaTrdProductDeal> paTrdProductDealList = this.genPaTrdProductDealRevoke(dto.getTotleTradeId());
        if (!StringUtil.isEmptyList(paTrdProductDealList)) {
            paTrdProductDealList.forEach(trdProductDeal -> paTrdProductDealMapper.insertSelective(trdProductDeal));
        }
        // 根据汇总交易编号生成金融产品交易附属撤销数据
        List<TrdProductDealAdd> trdProductDealAddList = this.genTrdProductDealAddRevoke(dto.getTotleTradeId());
        if (!StringUtil.isEmptyList(trdProductDealAddList)) {
            trdProductDealAddList.forEach(trdProductDeal -> trdProductDealAddMapper.insertSelective(trdProductDeal));
        }
        // 根据汇总交易编号查询划款指令
        List<StlPayOrders> stlPayOrdersList = this.genStlPayOrdersRevoke(dto.getTotleTradeId());
        if (!StringUtil.isEmptyList(stlPayOrdersList)) {
            stlPayOrdersList.forEach(trdProductDeal -> stlPayOrdersMapper.insertSelective(trdProductDeal));
        }
        // 根据汇总交易编号查获取撤销费用明细列表
        List<TrdTradeFeeDetail> trdtradefeedetails = this.genTrdTradeFeeDetailRevoke(dto.getTotleTradeId());
        if (!StringUtil.isEmptyList(trdtradefeedetails)) {
            trdtradefeedetails.forEach(trdTradeFeeDetail -> trdTradeFeeDetailMapper.insertSelective(trdTradeFeeDetail));
        }
        
        //通道增值税 还款方式为一次性划款时不需要改变组合现金流水
        if(!StringUtil.equals(dto.getTaxClass(), DictCodeListExt3.增值税品种_通道增值税.getValue())
            || !StringUtil.equals(dto.getPayType(), DictCodeListExt2.划款方式_一次性划款.getValue())) {
            // 撤销时 组合现金流水Entity
            List<PtlCashPositionFlowDto> ptlCashPositionFlows = new ArrayList<PtlCashPositionFlowDto>();
            PtlCashPositionFlowDto ptlCashPositionFlowDto = new PtlCashPositionFlowDto();
            ptlCashPositionFlowDto.setPortfolioId(dto.getPortfolioId());
            ptlCashPositionFlowDto.setAcctId(stlPayOrdersList.get(0).getMyAcctNo());
            ptlCashPositionFlowDto.setHodingType(BussinessDictCodeList.持仓类型_实际.getValue());
            ptlCashPositionFlowDto.setCdate(trdFinProductDealTotal.getSettleDate());
            ptlCashPositionFlowDto.setCcy(stlPayOrdersList.get(0).getCcy());
            ptlCashPositionFlowDto.setAmount(stlPayOrdersList.get(0).getSettleAmt());
            ptlCashPositionFlowDto.setOrderId(stlPayOrdersList.get(0).getOrderId());
            ptlCashPositionFlows.add(ptlCashPositionFlowDto);

            PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
            ptlPositionHoldDto.setPtlCashPositionFlows(ptlCashPositionFlows);
            restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/common/ptlposition/insertPosition",
                ptlPositionHoldDto, Boolean.class);
        }

        // 原始汇总交易的状态改为已撤销
        TrdFinProductDealTotal trdFinProductDealTotalRevoke = new TrdFinProductDealTotal();
        trdFinProductDealTotalRevoke.setTotleTradeId(dto.getTotleTradeId());
        trdFinProductDealTotalRevoke.setIsCancel(DictCodeList.是否_是.getValue());
//        bussCommonMapperDelegateService.updateByPrimaryKeySelective(
//            TrdFinProductDealTotalMapper.class, trdFinProductDealTotalRevoke);
        trdFinProductDealTotalMapper.updateByPrimaryKeySelective(trdFinProductDealTotalRevoke);
        
        // 原始交易的状态改为已撤销
        productCommonService.updateTrdProductDealByTotleTradeId(dto.getTotleTradeId());

        // 所有交易生效后调用的共通方法
        if (!StringUtil.isEmptyList(trdProductDealList)) {
            AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
            for (TrdProductDeal tradeDto : trdProductDealList) {

                inputDto.setProdid(tradeDto.getFinprodId());
                
                if(!StringUtil.equals(BussinessDictCodeList.收益类型_预期收益.getValue(), dto.getProfitType())){
                    inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
                } else {
                    inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                }
                
                if(StringUtil.equals(dto.getTaxClass(), DictCodeListExt3.增值税品种_通道增值税.getValue())) {
                    for(TrdProductDealAdd addDto :trdProductDealAddList) {
                        if(StringUtil.equals(addDto.getTradeId(), tradeDto.getTradeId())) {
                            inputDto.setTrdProductDealAdd(addDto);
                        }
                     }
                    
                    List<TrdTradeFeeDetail> feeDetails = new ArrayList<TrdTradeFeeDetail>();
                    for(TrdTradeFeeDetail feeDetail:trdtradefeedetails){
                        if(StringUtil.equals(feeDetail.getTradeId(), tradeDto.getTradeId())){
                            feeDetails.add(feeDetail);
                        }
                    }
                    inputDto.setListTrdTradeFeeDetail(feeDetails);
                }

                inputDto.setTrdProductDeal(tradeDto);
                AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
            }
        }
        
    }
//
//    @Autowired
//    private BussCommonMapperDelegateService bussCommonMapperDelegateService;

    /**
     * 生成金融产品交易汇总撤销数据
     * 
     * @param tradeId 交易编号
     * @return 金融产品交易汇总撤销数据
     * @see
     * @since 1.0
     */
    public TrdFinProductDealTotal genTrdFinProductDealTotalRevoke(String tradeId) {
        TrdFinProductDealTotal trdFinProductDealTotal = trdFinProductDealTotalMapper.selectByPrimaryKey(tradeId);
        if (trdFinProductDealTotal != null) {
            trdFinProductDealTotal.setpTradeId(trdFinProductDealTotal.getTotleTradeId());
            trdFinProductDealTotal.setTotleTradeId(StringUtil.defaultString(trdFinProductDealTotal.getTotleTradeId())
                                                   + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
            trdFinProductDealTotal.setTradeStatus(BussinessDictCodeList.交易流程状态_撤销.getValue());
        }
        return trdFinProductDealTotal;
    }

    /**
     * 生成划款指令撤销数据
     * 
     * @param tradeId 交易编号
     * @return 划款指令撤销数据
     * @see
     * @since 1.0
     */
    public List<StlPayOrders> genStlPayOrdersRevoke(String tradeId) {
        List<StlPayOrders> stlPayOrdersList = this.queryStlPayOrdersByTradeId(tradeId);
        if (!StringUtil.isEmptyList(stlPayOrdersList)) {
            for (StlPayOrders stlPayOrders : stlPayOrdersList) {
                stlPayOrders.setpTradeId(stlPayOrders.getOrderId());
                stlPayOrders.setOrderId(
                    StringUtil.defaultString(stlPayOrders.getOrderId()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                stlPayOrders.setTradeId(
                    StringUtil.defaultString(stlPayOrders.getTradeId()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                stlPayOrders.setOrderStatus(BussinessDictCodeList.指令状态_撤销.getValue());
            }
        }

        return stlPayOrdersList;
    }

    /**
     * 根据汇总交易编号划款指令信息
     * 
     * @param tradeId 汇总交易编号
     * @return 划款指令信息
     * @see
     * @since 1.0
     */
    private List<StlPayOrders> queryStlPayOrdersByTradeId(String tradeId) {
        return restTemplate.getForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/queryStlPayOrdersByTradeId?tradeId=" + tradeId,
            StlPayOrders.class);
    }

    /**
     * 根据汇总交易编号查询交易信息
     * 
     * @param tradeId 汇总交易编号
     * @return 交易信息
     * @see
     * @since 1.0
     */
    private List<TrdProductDeal> queryTrdProductDealByTotleTradeId(String tradeId) {
        return restTemplate.getForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/queryTrdProductDealByTotleTradeId?tradeId=" + tradeId,
            TrdProductDeal.class);
    }

    /**
     * 生成金融产品交易撤销数据
     * 
     * @param tradeId 交易编号
     * @param revokeReason 撤销原因
     * @return 金融产品交易撤销数据
     * @see
     * @since 1.0
     */
    private List<TrdProductDeal> genTrdProductDealRevoke(String tradeId, String revokeReason) {
        List<TrdProductDeal> trdProductDealList = this.queryTrdProductDealByTotleTradeId(tradeId);
        if (!StringUtil.isEmptyList(trdProductDealList)) {
            for (TrdProductDeal trdProductDeal : trdProductDealList) {
                trdProductDeal.setpTradeId(trdProductDeal.getTradeId());
                trdProductDeal.setTradeId(
                    StringUtil.defaultString(trdProductDeal.getTradeId()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                trdProductDeal.setTotleTradeId(StringUtil.defaultString(trdProductDeal.getTotleTradeId())
                                               + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                trdProductDeal.setTradeStatus(BussinessDictCodeList.交易流程状态_撤销.getValue());
                trdProductDeal.setCancelRemark(revokeReason);
            }
        }

        return trdProductDealList;
    }

    /**
     * 生成金融产品交易附属撤销数据
     * 
     * @param tradeId 交易编号
     * @return 金融产品交易附属撤销数据
     * @see
     * @since 1.0
     */
    private List<TrdProductDealAdd> genTrdProductDealAddRevoke(String tradeId) {
        List<TrdProductDealAdd> trdProductDealAddList = this.queryTrdProductDealAddByTotleTradeId(tradeId);
        if (!StringUtil.isEmptyList(trdProductDealAddList)) {
            for (TrdProductDealAdd trdProductDealAdd : trdProductDealAddList) {
                trdProductDealAdd.setTradeId(
                    StringUtil.defaultString(trdProductDealAdd.getTradeId()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
            }
        }

        return trdProductDealAddList;
    }

    /**
     * 根据汇总交易编号查询交易附属信息
     * 
     * @param tradeId 汇总交易编号
     * @return 交易附属信息
     * @see
     * @since 1.0
     */
    private List<TrdProductDealAdd> queryTrdProductDealAddByTotleTradeId(String tradeId) {
        return restTemplate.getForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/queryTrdProductDealAddByTotleTradeId?tradeId=" + tradeId,
            TrdProductDealAdd.class);
    }

    /**
     * 生成金融产品交易附属撤销数据
     * 
     * @param tradeId 交易编号
     * @return 金融产品交易附属撤销数据
     * @see
     * @since 1.0
     */
    private List<PaTrdProductDeal> genPaTrdProductDealRevoke(String tradeId) {
        List<PaTrdProductDeal> paTrdProductDealList = this.queryPaTrdProductDealByTotleTradeId(tradeId);
        if (!StringUtil.isEmptyList(paTrdProductDealList)) {
            for (PaTrdProductDeal paTrdProductDeal : paTrdProductDealList) {
                paTrdProductDeal.setTradeId(
                    StringUtil.defaultString(paTrdProductDeal.getTradeId()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
            }
        }

        return paTrdProductDealList;
    }

    /**
     * 根据交易编号查询金融产品交易扩展
     * 
     * @param tradeId 汇总交易编号
     * @return 金融产品交易扩展
     * @see
     * @since 1.0
     */
    private List<PaTrdProductDeal> queryPaTrdProductDealByTotleTradeId(String tradeId) {
        return restTemplate.getForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/queryPaTrdProductDealByTotleTradeId?tradeId=" + tradeId,
            PaTrdProductDeal.class);
    }

    /**
     * 获取撤销费用明细列表
     * 
     * @param tradeId 交易编号
     * @return 金融产品交易附属撤销数据
     * @see
     * @since 1.0
     */
    private List<TrdTradeFeeDetail> genTrdTradeFeeDetailRevoke(String tradeId) {
        List<TrdTradeFeeDetail> trdtradefeedetails = this.queryTrdTradeFeeDetailByTotleTradeId(tradeId);
        if (!StringUtil.isEmptyList(trdtradefeedetails)) {
            for (TrdTradeFeeDetail trdtradefeedetail : trdtradefeedetails) {
                trdtradefeedetail.setTradeId(
                    StringUtil.defaultString(trdtradefeedetail.getTradeId()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
            }
        }

        return trdtradefeedetails;
    }

    /**
     * 根据汇总交易编号查询费用明细列表
     * 
     * @param tradeId 汇总交易编号
     * @return 金融产品交易扩展
     * @see
     * @since 1.0
     */
    private List<TrdTradeFeeDetail> queryTrdTradeFeeDetailByTotleTradeId(String tradeId) {
        return restTemplate.getForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/queryTrdTradeFeeDetailByTotleTradeId?tradeId=" + tradeId,
            TrdTradeFeeDetail.class);
    }

}
