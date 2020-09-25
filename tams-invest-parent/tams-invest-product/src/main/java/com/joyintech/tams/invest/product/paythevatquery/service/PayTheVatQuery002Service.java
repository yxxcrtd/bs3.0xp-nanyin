/*
 * 文件名：PayTheVatQuery002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年2月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.invest.product.paythevatquery.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.component.business.settle.dto.BussSettleOrderParameterDto;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.PaTrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.PaTrdProductDealMapper;
import com.joyintech.tams.common.base.dao.PtlTradeMapper;
import com.joyintech.tams.common.base.dao.StlPayOrdersMapper;
import com.joyintech.tams.common.base.dao.TrdAsynHandleStatusMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.dao.TrdTradeFeeDetailMapper;
import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.common.base.entity.StlOrdersExt;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdAsynHandleStatus;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.component.business.trade.impl.BussTrdTransferOrderImplService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.product.paythevatquery.dao.PayTheVatQuery002Mapper;
import com.joyintech.tams.invest.product.paythevatquery.dto.PayTheVatQuery002Dto;
import com.joyintech.tams.invest.product.paythevatquery.dto.PayTheVatQuery002TableDto;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 纳税增值税新建service
 * @author wangqidong
 * @version 1.0 2019年2月25日
 * @see PayTheVatQuery002Service
 * @since 1.0
 */
@Service("FinproductPayTheVatQuery002Service")
public class PayTheVatQuery002Service extends CommonExportService<PayTheVatQuery002Dto> implements 
            BaseProcessService<PayTheVatQuery002Dto>{
    
    @Autowired
    private PayTheVatQuery002Mapper payTheVatQuery002Mapper;
    /**
     * 金融产品交易扩展表
     */
    /**
     * 接口共通
     */
    @Autowired
    private InterfCommonService interfCommonService;
    
    @Autowired
    private BussTrdTransferOrderImplService bussTrdTransferOrderImplService;
    
    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;
    
    @Autowired
    private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;
    
    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;
    
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;
    
    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;
    
    @Autowired
    private TrdTradeFeeDetailMapper trdTradeFeeDetailMapper;
    
    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;
    
    @Autowired
    private PtlTradeMapper ptlTradeMapper;
    
    @Autowired
    private TrdAsynHandleStatusMapper trdAsynHandleStatusMapper;
    
    /**
     * 查询
     * @param pageParam
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<PayTheVatQuery002Dto> search(Map<String, Object> pageParam) throws Exception {
        //产品多选
        if (StringUtil.isNotEmpty(pageParam.get("portfolioId"))) {
            String portfolioId = (String) pageParam.get("portfolioId");
            String[] portfolioIdArr = null;
            portfolioIdArr = portfolioId.split(",");
            pageParam.put("portfolioIdArr", portfolioIdArr);
        }
        
        pageParam.put("FINPROD_TYPE_F16", DictCodeListExt1.金融产品类型_理财产品.getValue());
        pageParam.put("FINPROD_TYPE_F18", DictCodeListExt1.金融产品类型_投资组合.getValue());
        pageParam.put("payment1",DateUtil.parse((String) pageParam.get("payment1")));
        pageParam.put("TAX0102", BussinessDictCodeList.税类型_利息收入.getValue());
        pageParam.put("TAX0202", BussinessDictCodeList.税类型_红利收入.getValue());
        pageParam.put("TAX0302", BussinessDictCodeList.税类型_价差收入.getValue());
        pageParam.put("ALT0102", BussinessDictCodeList.税类型_城建税.getValue());
        pageParam.put("ALT0202", BussinessDictCodeList.税类型_教育费.getValue());
        pageParam.put("ALT0302", BussinessDictCodeList.税类型_地方教育费.getValue());
        pageParam.put("ALT0402", BussinessDictCodeList.税类型_水利基金税.getValue());
        pageParam.put("BUSI_TYPE_16", BussinessDictCodeList.业务类型_增值税交易.getValue());
        pageParam.put("TRADE_STATUS_01", BussinessDictCodeList.有效状态_有效.getValue());
        pageParam.put("TRADE_STATUS_03", BussinessDictCodeList.有效状态_撤销.getValue());
        
        pageParam.put("taxidenType", pageParam.get("taxidenType"));
        pageParam.put("tradeStatus", BussinessDictCodeList.有效状态_有效.getValue());
        
        pageParam.put("finprodType", DictCodeListExt1.金融产品类型_税额.getValue());
        pageParam.put("finprodType2", BussinessDictCodeList.金融产品类型_投管用_税额.getValue());
        
        pageParam.put("YES_NO_NO", DictCodeList.是否_否.getValue());
        
        String taxClass = (String)pageParam.get("taxClass");
        Page<PayTheVatQuery002Dto> page  = new Page<PayTheVatQuery002Dto>();
        if(StringUtil.equals(taxClass, DictCodeListExt3.增值税品种_产品增值税.getValue())) {
            
            pageParam.put("profitType_SY", BussinessDictCodeList.收益类型_预期收益.getValue());
            page = super.queryForPage(() -> payTheVatQuery002Mapper.searchProductVat(pageParam)); 
            
        } else if (StringUtil.equals(taxClass, DictCodeListExt3.增值税品种_通道增值税.getValue())) {
            
            page = super.queryForPage(() -> payTheVatQuery002Mapper.searchChannelVat(pageParam));
            //计算增值税和附加税
            if(page != null && !StringUtil.isEmptyList(page.getRoot())){
                page.getRoot().forEach(dto ->{
                    dto.setAppreciationTax(ArithmeticUtil.doAdd(dto.getInterestIncome(),dto.getDividendIncome(),dto.getDifbetIncome())); 
                    dto.setAdditionalTax(ArithmeticUtil.doAdd(dto.getConstructionTax(),dto.getEducationTax(),dto.getPlaceeducationTax(),dto.getWaterTrafficTax()));
                });
            }
        }
        
        
        if(page != null&&!StringUtil.isEmptyList(page.getRoot())){
            PayTheVatQuery002Dto sumDto = new PayTheVatQuery002Dto();
            //组合代码list
            List<String> listProdId = new ArrayList<String>();
            sumDto.setPortfolioName("合计");
            sumDto.setSumLine(BigDecimal.ONE);
            sumDto.setInterestIncome(BigDecimal.ZERO);
            sumDto.setDividendIncome(BigDecimal.ZERO);
            sumDto.setDifbetIncome(BigDecimal.ZERO);
            sumDto.setAppreciationTax(BigDecimal.ZERO);
            sumDto.setConstructionTax(BigDecimal.ZERO);
            sumDto.setEducationTax(BigDecimal.ZERO);
            sumDto.setPlaceeducationTax(BigDecimal.ZERO);
            sumDto.setAdditionalTax(BigDecimal.ZERO);
            sumDto.setWaterTrafficTax(BigDecimal.ZERO);
            sumDto.setTaxTotal(BigDecimal.ZERO);
            page.getRoot().forEach(dto ->{
                listProdId.add(dto.getProdId());
                sumDto.setInterestIncome(sumDto.getInterestIncome().add(dto.getInterestIncome()));
                sumDto.setDividendIncome(sumDto.getDividendIncome().add(dto.getDividendIncome()));
                sumDto.setDifbetIncome(sumDto.getDifbetIncome().add(dto.getDifbetIncome()));
                sumDto.setAppreciationTax(sumDto.getAppreciationTax().add(dto.getAppreciationTax()));
                sumDto.setConstructionTax(sumDto.getConstructionTax().add(dto.getConstructionTax()));
                sumDto.setEducationTax(sumDto.getEducationTax().add(dto.getEducationTax()));
                sumDto.setPlaceeducationTax(sumDto.getPlaceeducationTax().add(dto.getPlaceeducationTax()));
                sumDto.setAdditionalTax(sumDto.getAdditionalTax().add(dto.getAdditionalTax()));
                sumDto.setWaterTrafficTax(sumDto.getWaterTrafficTax().add(dto.getWaterTrafficTax()));
                sumDto.setTaxTotal(sumDto.getTaxTotal().add(dto.getTaxTotal()));
            });
            
            /**调用估值接口获取是否显示水利基金附加税 add by chendijun 2019/10/14 start**/
            String queryDate = (String)pageParam.get("payTaxVDate");
            //OPEX-650【产品缴纳】缴纳查询界面，选择纳税日期查询条件进行查询，报数据接口异常 guyunfei 20191227 start
            List<String> addTaxTypeList= getAddTaxType(listProdId,
                    BussinessDictCodeList.金融产品类型_投资组合.getValue(), queryDate,null,null);
            //OPEX-650【产品缴纳】缴纳查询界面，选择纳税日期查询条件进行查询，报数据接口异常 guyunfei 20191227 end
            for(PayTheVatQuery002Dto dto:page.getRoot()) {
                dto.setShowWaterTrafficTax(!StringUtil.isEmptyList(addTaxTypeList)
                    && addTaxTypeList.contains(BussinessDictCodeList.税类型_水利基金税.getValue())); 
            }
            /**调用估值接口获取是否显示水利基金附加税  add by chendijun 2019/10/14 end**/
            page.getRoot().add(sumDto);
        }
        return page;
    }
    
    /**
     * 校验缴税/退税日期
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<PayTheVatQuery002Dto> checkdate(Map<String, Object> pageParam){
      //产品多选
        if (StringUtil.isNotEmpty(pageParam.get("booksetId"))) {
            String booksetId = (String) pageParam.get("booksetId");
            String[] booksetIdArr = null;
            booksetIdArr = booksetId.split(",");
            pageParam.put("booksetIdArr", booksetIdArr);
        }
        if(StringUtil.isNotEmpty(pageParam.get("portfolioId"))){
            String portfolioId = (String) pageParam.get("portfolioId");
            String [] portfolioIdArr = portfolioId.split(",");
            pageParam.put("portfolioIdArr", portfolioIdArr);
        }
        List<PayTheVatQuery002Dto> payTheVatQuery002Dto = payTheVatQuery002Mapper.checkdate(pageParam);
        return payTheVatQuery002Dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(PayTheVatQuery002Dto dto, OperationType type) {
        this.docommitEx(dto);
    }
    
    private void docommitEx(PayTheVatQuery002Dto dto) {
        PayTheVatQuery002TableDto listDto = dto.getListpayTheVatQuery002TableDto().get(0);
        Date tradedate = DateUtil.parse(dto.getPayment2());
        Date vdate = DateUtil.parse(listDto.getTaxStartDate());
        Date mdate = DateUtil.parse(dto.getPayment1());
        TrdFinProductDealTotal trdfinproductdealtotal = this.genTrdFinProductDealTotal(dto,tradedate,vdate,mdate);
        trdFinProductDealTotalMapper.insertSelective(trdfinproductdealtotal);
        
        //pa金融产品汇总表
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
        paTrdFinProductDealTotal.setPaBussType(BussinessDictCodeList.平安业务类型_增值税缴纳.getValue());
        paTrdFinProductDealTotal.setTotleTradeId(trdfinproductdealtotal.getTotleTradeId());
        paTrdFinProductDealTotalMapper.insertSelective(paTrdFinProductDealTotal);
        
        // 金融产品交易
        TrdProductDeal trdproductdeal = new TrdProductDeal();
        trdproductdeal.setTradeId(interfCommonService.getTradeid(dto.getTaxidenType(), dto.getCreateTime(), "A"));
        trdproductdeal.setTotleTradeId(dto.getTotleTradeId());
        trdproductdeal.setBusiType(BussinessDictCodeList.业务类型_增值税交易.getValue());
        trdproductdeal.setFinprodId(listDto.getFinprodId());
        trdproductdeal.setFinprodType(DictCodeListExt1.金融产品类型_税额.getValue());
        trdproductdeal.setFinprodType2(BussinessDictCodeList.金融产品类型_投管用_税额.getValue());
        trdproductdeal.setCcy(dto.getCcy());
        trdproductdeal.setPs(dto.getTaxidenType());
        trdproductdeal.setTradeDate(tradedate);
        trdproductdeal.setVdate(vdate);
        trdproductdeal.setMdate(mdate);
        trdproductdeal.setSettleDate(tradedate);
        trdproductdeal.setTradeAmt(listDto.getTaxTotal());
        trdproductdeal.setTradeStatus(BussinessDictCodeList.有效状态_有效.getValue());
        trdproductdeal.setIsCancel(BussinessDictCodeList.是否_否.getValue());
        trdproductdeal.setWorkflowId(dto.getWorkflowId());
        trdproductdeal.setRemark(dto.getRemark());
        
       
        //通道增值税需要传通道代码
        if(StringUtil.equals(dto.getTaxClass(),DictCodeListExt3.增值税品种_通道增值税.getValue())) {
            trdproductdeal.setChlId(DictCodeListExt1.金融产品类型_投管用_通道.getValue()+GlobalConstant.UNDER_LINE+dto.getChannelId());
        }
        trdProductDealMapper.insertSelective(trdproductdeal);
        // pa金融产品交易
        PaTrdProductDeal patrdproductdeal = new PaTrdProductDeal();
        patrdproductdeal.setTradeId(trdproductdeal.getTradeId());
        patrdproductdeal.setPaBussType(BussinessDictCodeList.平安业务类型_增值税缴纳.getValue());
        patrdproductdeal.setOrderBussType(DictCodeListExt1.指令业务类型_产品税费支付.getValue());
        paTrdProductDealMapper.insertSelective(patrdproductdeal);
        
        // 金融产品交易附属表
        TrdProductDealAdd trdProductDealExt = new TrdProductDealAdd();
        trdProductDealExt.setTradeId(trdproductdeal.getTradeId());
        trdProductDealExt.setPortfolioId(listDto.getPortfolioId());
        trdProductDealExt.setGenType(BussinessDictCodeList.生成方式_画面录入.getValue());
        trdProductDealExt.setWorkflowId(dto.getWorkflowId());
        trdProductDealAddMapper.insertSelective(trdProductDealExt);

        // 入交易费用明细表表(TAX_TYPE数据字典,分6条数据分别存储金额数据)
        List<TrdTradeFeeDetail> trdTradeFeeDetails  = new ArrayList<>();
        // TAX0102利息收入
        trdTradeFeeDetails.add(tgTradeFeeDetailEx(trdproductdeal,
                BussinessDictCodeList.税类型_利息收入.getValue(), listDto.getInterestIncome()));
        // TAX0202红利收入
        trdTradeFeeDetails.add(tgTradeFeeDetailEx(trdproductdeal,
                BussinessDictCodeList.税类型_红利收入.getValue(), listDto.getDividendIncome()));
        // TAX0302价差收入
        trdTradeFeeDetails.add(tgTradeFeeDetailEx(trdproductdeal,
                BussinessDictCodeList.税类型_价差收入.getValue(), listDto.getDifbetIncome()));
        // ALT0102城建税附加
        trdTradeFeeDetails.add(tgTradeFeeDetailEx(trdproductdeal,
                BussinessDictCodeList.税类型_城建税.getValue(), listDto.getConstructionTax()));
        // ALT0202教育税附加
        trdTradeFeeDetails.add(tgTradeFeeDetailEx(trdproductdeal,
                BussinessDictCodeList.税类型_教育费.getValue(), listDto.getEducationTax()));
        // ALT0302地方教育费附加
        trdTradeFeeDetails.add(tgTradeFeeDetailEx(trdproductdeal,
                BussinessDictCodeList.税类型_地方教育费.getValue(), listDto.getPlaceeducationTax()));
        
        // ALT0402水利基金附加
        trdTradeFeeDetails.add(tgTradeFeeDetailEx(trdproductdeal,
                BussinessDictCodeList.税类型_水利基金税.getValue(), listDto.getWaterTrafficTax()));
        
        for(TrdTradeFeeDetail trdTradeFeeDetail:trdTradeFeeDetails) {
            trdTradeFeeDetailMapper.insertSelective(trdTradeFeeDetail);
        }
        
//        //批量插入
//        TrdCommonParam trdCommonParam = new TrdCommonParam();
//        trdCommonParam.setTrdFinProductDealTotal(trdfinproductdealtotal);
//        trdCommonParam.setTrdProductDeals(Arrays.asList(trdproductdeal));
//        trdCommonParam.setPaDeals(Arrays.asList(patrdproductdeal));
//        trdCommonParam.setDealAdds(Arrays.asList(trdProductDealExt));
//        trdCommonParam.setTrdTradeFeeDetails(trdTradeFeeDetails);
//        trdCommonParam.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);
        
//        if(!StringUtil.equals(dto.getTaxClass(), DictCodeListExt3.增值税品种_通道增值税.getValue())
//            || !StringUtil.equals(dto.getPayType(), DictCodeListExt2.划款方式_一次性划款.getValue())) {
            StlPayOrders stlpayorder = this.getOrder(dto);
            if(stlpayorder!=null) {
                stlPayOrdersMapper.insertSelective(stlpayorder);

                // 组合现金流水Entity
                List<PtlCashPositionFlowDto> ptlCashPositionFlows = new ArrayList<PtlCashPositionFlowDto>();
                PtlCashPositionFlowDto ptlCashPositionFlowDto = new PtlCashPositionFlowDto();
                ptlCashPositionFlowDto.setPortfolioId(listDto.getPortfolioId());
                ptlCashPositionFlowDto.setAcctId(stlpayorder == null ? null : stlpayorder.getMyAcctNo());
                ptlCashPositionFlowDto.setHodingType(BussinessDictCodeList.持仓类型_实际.getValue());
                ptlCashPositionFlowDto.setCdate(tradedate);
                ptlCashPositionFlowDto.setCcy(dto.getCcy());
                ptlCashPositionFlowDto.setAmount(stlpayorder.getSettleAmt().negate());
                ptlCashPositionFlowDto.setOrderId(stlpayorder.getOrderId());
                ptlCashPositionFlows.add(ptlCashPositionFlowDto);

                PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
                ptlPositionHoldDto.setPtlCashPositionFlows(ptlCashPositionFlows);
                restTemplate.postForObject(
                        ServiceApplication.PORTFOLIO_SERVICE_NAME + "/common/ptlposition/insertPosition",
                        ptlPositionHoldDto, Boolean.class);
            }
//        }
        /*del by xr 中债交易登记缺少增值税交易 修复*/
		
//        if(StringUtil.equals(dto.getTaxClass(), DictCodeListExt3.增值税品种_通道增值税.getValue())) {
            PtlTrade ptlTrade =  genPtlTrdea(dto,trdproductdeal);
            ptlTradeMapper.insert(ptlTrade);
//        }
		/*del by xr 中债交易登记缺少增值税交易 修复*/

//        restTemplate.postForObject(
//                ServiceApplication.PRODUCT_SERVICE_NAME + "/PayTheVatQueryController/doCommitEx", trdCommonParam, String.class);


        AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();

//        if(!StringUtil.equals(BussinessDictCodeList.收益类型_预期收益.getValue(), listDto.getProfitType())){
//            inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
//            inputDto.setProdid(trdproductdeal.getFinprodId());
//        } else {
//            inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
//            inputDto.setProdid(trdProductDealExt.getPortfolioId());
//        }
        inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
        inputDto.setProdid(trdProductDealExt.getPortfolioId());
        inputDto.setTrdProductDeal(trdproductdeal);

        if(StringUtil.equals(dto.getTaxClass(), DictCodeListExt3.增值税品种_通道增值税.getValue())) {
            inputDto.setListTrdTradeFeeDetail(trdTradeFeeDetails);
            inputDto.setTrdProductDealAdd(trdProductDealExt);
        }
        AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
        
        //20191225新增 
    	TrdAsynHandleStatus trdAsynHandleStatus = new TrdAsynHandleStatus();
        trdAsynHandleStatus.setTradeId(dto.getTotleTradeId());		//交易汇总id
        trdAsynHandleStatus.setTrdTableType("01");		// 交易表类型(01：金融产品交易汇总表，02：金融产品交易表)
        trdAsynHandleStatus.setHandleStatus("01");		//01	待处理
        trdAsynHandleStatusMapper.insertSelective(trdAsynHandleStatus);
    }
    
    
    /**
     * 生成金融产品交易汇总
     * 
     * @param entity 产品费用支付Dto
     * @return 金融产品交易汇总
     * @see
     * @since 1.0
     */
    private TrdFinProductDealTotal genTrdFinProductDealTotal(PayTheVatQuery002Dto entity,
                                                             Date tradedate, Date vdate, Date mdate) {
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(entity.getTotleTradeId());
        trdFinProductDealTotal.setBusiType(BussinessDictCodeList.共同业务类型_产品费用支付管理.getValue());
        trdFinProductDealTotal.setFinprodId(entity.getFinprodId());
        trdFinProductDealTotal.setVdate(vdate);
        trdFinProductDealTotal.setMdate(mdate);
        trdFinProductDealTotal.setSettleDate(tradedate);
        trdFinProductDealTotal.setTradeAmt(entity.getTradeAmt());
        trdFinProductDealTotal.setCashId(entity.getCashId());
        trdFinProductDealTotal.setPs(entity.getTaxidenType());
        trdFinProductDealTotal.setTradeStatus(BussinessDictCodeList.有效状态_有效.getValue());
        trdFinProductDealTotal.setIsCancel(BussinessDictCodeList.是否_否.getValue());
        trdFinProductDealTotal.setUpdateUser(entity.getUpdateUser());
        trdFinProductDealTotal.setCreateUser(entity.getCreateUser());
        trdFinProductDealTotal.setCreateDept(entity.getCreateDept());
        trdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
        trdFinProductDealTotal.setPortfolioId(entity.getPortfolioId());
        
        if(StringUtil.equals(entity.getTaxClass(),DictCodeListExt3.增值税品种_通道增值税.getValue())) {
            trdFinProductDealTotal.setChlId(DictCodeListExt1.金融产品类型_投管用_通道.getValue()+GlobalConstant.UNDER_LINE+entity.getChannelId());
        }

        return trdFinProductDealTotal;
    }

    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    public static TrdProductDeal getTrdProductDeal(PayTheVatQuery002Dto entity,PayTheVatQuery002TableDto temp) {
        TrdProductDeal trdProductDeal = new TrdProductDeal();

        trdProductDeal.setPs(entity.getTaxidenType());
//        金融产品类型
        trdProductDeal.setFinprodType2("16");
        try {
            trdProductDeal.setTradeDate(DateUtil.parseDate(entity.getPayTaxVDate(),"yyyy-MM-dd"));
        } catch (ParseException e) {

        }
        trdProductDeal.setTradeAmt(temp.getTaxTotal());
//        币种
        trdProductDeal.setCcy(entity.getCcy());
        trdProductDeal.setPortfolioId(entity.getPortfolioId());

        return trdProductDeal;
    }

    /**
     * 获取指令
     * @param dto
     * @return
     */
    private StlPayOrders getOrder(PayTheVatQuery002Dto dto) {
        StlPayOrders orders = null;

        if(StringUtil.equals(dto.getTaxClass(), DictCodeListExt3.增值税品种_通道增值税.getValue())
            && StringUtil.equals(dto.getPayType(), DictCodeListExt2.划款方式_一次性划款.getValue())) {
            // add by wj

            List<BussTrdTransferOrderDto> stlPayOrdersList = new ArrayList<>();

            List<PayTheVatQuery002TableDto> listpayTheVatQuery002TableList = dto.getListpayTheVatQuery002TableDto();
            if(!StringUtil.isEmptyList(listpayTheVatQuery002TableList)){
                for(PayTheVatQuery002TableDto temp : listpayTheVatQuery002TableList) {
                    TrdProductDeal deal = getTrdProductDeal(dto,temp);
                    BussSettleOrderParameterDto param = new BussSettleOrderParameterDto();
                    param.setIsTotalTrade(DictCodeList.是否_否.getValue());
                    param.setPortfolioId(dto.getPortfolioId());
                    param.setDeal(deal);
                    BussTrdTransferOrderDto ret = bussTrdTransferOrderService.generateOrder(param);

                    stlPayOrdersList.add(ret);
                }
            }
            if (!StringUtil.isEmptyList(stlPayOrdersList)) {
                stlPayOrdersList = bussTrdTransferOrderImplService.save(stlPayOrdersList);
                for (BussTrdTransferOrderDto stlPayOrders : stlPayOrdersList) {
                    if (StringUtil.equals(stlPayOrders.getPortfolioId(), dto.getPortfolioId())) {
                        orders = new StlPayOrders();
                        BeanUtil.copyPropertiesIgnoreNull(orders, stlPayOrders);
                        orders.setTradeId(dto.getTotleTradeId());
                        orders.setOrderStatus(BussinessDictCodeList.指令状态_新建.getValue());
                        orders.setWorkflowId(dto.getWorkflowId());
                    }
                }
            }


            return orders;
        }else {

            List<BussTrdTransferOrderDto> stlPayOrdersList = bussTrdTransferOrderImplService.save(dto.getBussTrdTransferOrder());
            if (!StringUtil.isEmptyList(stlPayOrdersList)) {
                for (BussTrdTransferOrderDto stlPayOrders : stlPayOrdersList) {
                    if (StringUtil.equals(stlPayOrders.getPortfolioId(), dto.getPortfolioId())) {
                        orders = new StlPayOrders();
                        BeanUtil.copyPropertiesIgnoreNull(orders, stlPayOrders);
                        orders.setTradeId(dto.getTotleTradeId());
                        orders.setOrderStatus(BussinessDictCodeList.指令状态_新建.getValue());
                        orders.setWorkflowId(dto.getWorkflowId());
                    }
                }
            }
        }
        return orders;
    }
    /**
     * 
     * 金融产品交易扩展表
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public TrdProductDealAdd trdProductDealExt(PayTheVatQuery002Dto dto){
        TrdProductDealAdd trdProductDealExt = new TrdProductDealAdd();
        trdProductDealExt.setTradeId(dto.getTradeId());        
        trdProductDealExt.setPortfolioId(dto.getPortfolioId());
        // trdProductDealExt.setProdId(dto.getProdId());   TODO    
        trdProductDealExt.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        trdProductDealExt.setCreateTime(new Date());
        trdProductDealExt.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        trdProductDealExt.setUpdateTime(new Date());
        return trdProductDealExt;
        
    }
    /**
     *金融产品交易表
     * @param dto
     * @return
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    public TrdProductDeal trdProductDeal(PayTheVatQuery002Dto dto) throws ParseException{
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        trdProductDeal.setTradeId(dto.getTradeId());
        trdProductDeal.setFinprodId(dto.getFinprodId());
        trdProductDeal.setBusiType("16");
        trdProductDeal.setFinprodType("C07");
        trdProductDeal.setBranch(InterfCommonConstant.分支序号_默认);
        trdProductDeal.setTradeAmt(dto.getTradeAmt());
        trdProductDeal.setMdate(DateUtil.parseDate(dto.getPayment2(), "yyyy-MM-dd"));
        if(StringUtil.isNotEmpty(dto.getPaymentStart())){
            trdProductDeal.setVdate(DateUtil.parseDate(dto.getPaymentStart(), "yyyy-MM-dd"));
        }else{
            trdProductDeal.setVdate(null);
        }
        trdProductDeal.setSettleDate(DateUtil.parseDate(dto.getPayment1(), "yyyy-MM-dd"));
        trdProductDeal.setTradeDate(DateUtil.parseDate(dto.getPayment2(), "yyyy-MM-dd"));
        trdProductDeal.setCcy(dto.getStandardCcy());
        trdProductDeal.setPs(dto.getTaxidenType());
        trdProductDeal.setTradeStatus(dto.getTradeStatus());
        trdProductDeal.setIsClean("Y");
        trdProductDeal.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        trdProductDeal.setCreateTime(new Date());
        trdProductDeal.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        trdProductDeal.setUpdateTime(new Date());
        return trdProductDeal;
    }
    /**
     * 交易费用明细表
     * @param trdproductdeal
     * @return
     * @see
     * @since 1.0
     */
    public TrdTradeFeeDetail tgTradeFeeDetailEx(TrdProductDeal trdproductdeal,String feeType,BigDecimal feeamt){
        TrdTradeFeeDetail tgTradeFeeDetail = new TrdTradeFeeDetail();
        tgTradeFeeDetail.setTradeId(trdproductdeal.getTradeId());
        tgTradeFeeDetail.setFeeType(feeType);
        tgTradeFeeDetail.setIsPayWithTrade(BussinessDictCodeList.是否_是.getValue());
        tgTradeFeeDetail.setFeeAmt(feeamt);
        tgTradeFeeDetail.setWorkflowId(trdproductdeal.getWorkflowId());
        //如果是通道增值税，设置通道代码
        if(StringUtil.isNotEmpty(trdproductdeal.getChlId())) {
            tgTradeFeeDetail.setChlFinprodId(trdproductdeal.getChlId());  
        }
        return tgTradeFeeDetail;
    }
    /**
     * 交易费用明细表
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public TrdTradeFeeDetail tgTradeFeeDetail(PayTheVatQuery002Dto dto){
        TrdTradeFeeDetail tgTradeFeeDetail = new TrdTradeFeeDetail();
        tgTradeFeeDetail.setTradeId(dto.getTradeId());
        tgTradeFeeDetail.setFeeType(dto.getFeeType());
        tgTradeFeeDetail.setIsPayWithTrade("Y");
        tgTradeFeeDetail.setFeeAmt(dto.getFeeAmt());
        tgTradeFeeDetail.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        tgTradeFeeDetail.setCreateTime(new Date());
        tgTradeFeeDetail.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        tgTradeFeeDetail.setUpdateTime(new Date());
        return tgTradeFeeDetail;
    }
    /**
     * 结算指令表
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public StlOrders stlOrders (PayTheVatQuery002Dto dto){
        StlOrders stlOrders = new StlOrders();
        stlOrders.setOrderId(dto.getOrderId());
        stlOrders.setBusiType("16");
        if("C12".equals(dto.getTaxidenType())){
            stlOrders.setOrderType("N2615");
        }else{
            stlOrders.setOrderType("N2616");
        }
        stlOrders.setMyAcctNo(dto.getAcctNo());
        stlOrders.setPs("02");
        stlOrders.setTradeId(dto.getTradeId());
        stlOrders.setOrderStatus("01");
        stlOrders.setSettleStatus("01");
        stlOrders.setSettleAmt(dto.getTradeAmt());
        stlOrders.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        stlOrders.setCreateTime(new Date());
        stlOrders.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        stlOrders.setUpdateTime(new Date());
        return stlOrders;
    }
    /**
     * 结算指令扩展表
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public StlOrdersExt stlOrdersExt(PayTheVatQuery002Dto dto){
        StlOrdersExt stlOrdersExt = new StlOrdersExt();
        stlOrdersExt.setOrderId(dto.getOrderId());
        stlOrdersExt.setIsTrade("Y");
        stlOrdersExt.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        stlOrdersExt.setCreateTime(new Date());
        stlOrdersExt.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        stlOrdersExt.setUpdateTime(new Date());
        return stlOrdersExt;
    }
    /**
     * 入账信息表
     * @param dto
     * @return
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    public BokDealAccounting bokDealAccounting(PayTheVatQuery002Dto dto) throws ParseException{
        BokDealAccounting bokDealAccounting = new BokDealAccounting();
        bokDealAccounting.setBooksetId(dto.getBooksetId());
        bokDealAccounting.setBusiNo(dto.getTradeId());
        bokDealAccounting.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        bokDealAccounting.setIsAccounted("N");
        bokDealAccounting.getBooksetDate();
        bokDealAccounting.setBookDate(DateUtil.parseDate(dto.getPayment2(), "yyyy-MM-dd"));
        bokDealAccounting.setHappenDate(DateUtil.parseDate(dto.getPayment2(), "yyyy-MM-dd"));
        bokDealAccounting.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        bokDealAccounting.setCreateTime(new Date());
        bokDealAccounting.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        bokDealAccounting.setUpdateTime(new Date());
        return bokDealAccounting;
    }
    /**
     * 入账账务明细表
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public BokDealAccountingDetail bokDealAccountingDetail(PayTheVatQuery002Dto dto){
        BokDealAccountingDetail bokDealAccountingDetail = new BokDealAccountingDetail();
        bokDealAccountingDetail.setBooksetId(dto.getBooksetId());
        bokDealAccountingDetail.setBusiNo(dto.getTradeId());
        bokDealAccountingDetail.setBokDetailId(dto.getBokDetailid());
        bokDealAccountingDetail.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        bokDealAccountingDetail.setBusiType("16");
        bokDealAccountingDetail.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        bokDealAccountingDetail.setCreateTime(new Date());
        bokDealAccountingDetail.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        bokDealAccountingDetail.setUpdateTime(new Date());
        return bokDealAccountingDetail;
    }
    
    /**
     * 列表行数据删除
     * @param booksetId
     * @see
     * @since 1.0
     */
    public int delete(String booksetId) {       
        
      return payTheVatQuery002Mapper.deleteByBooksetId(booksetId);
      
    }
    
    public List<PayTheVatQuery002Dto> getLastPayTheVatInfoList(Map<String, Object> params) {
        params.put("BUSI_TYPE_16", BussinessDictCodeList.业务类型_增值税交易.getValue());
        params.put("finprodType", DictCodeListExt1.金融产品类型_税额.getValue());
        params.put("finprodType2", BussinessDictCodeList.金融产品类型_投管用_税额.getValue());
        params.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        params.put("IS_CANCEL", DictCodeList.是否_否.getValue());
        return payTheVatQuery002Mapper.getLastPayTheVatInfoList(params);
    }
    
    public List<PayTheVatQuery002Dto> getLastcdate(Map<String, Object> params) {
        params.put("TAX0102", BussinessDictCodeList.税类型_利息收入.getValue());
        params.put("TAX0202", BussinessDictCodeList.税类型_红利收入.getValue());
        params.put("TAX0302", BussinessDictCodeList.税类型_价差收入.getValue());
        params.put("ALT0102", BussinessDictCodeList.税类型_城建税.getValue());
        params.put("ALT0202", BussinessDictCodeList.税类型_教育费.getValue());
        params.put("ALT0302", BussinessDictCodeList.税类型_地方教育费.getValue());
        params.put("ALT0402", BussinessDictCodeList.税类型_水利基金税.getValue());
        return payTheVatQuery002Mapper.getLastcdate(params);
    }
    
    /**
     * 
     * 获取通道最新缴纳日期查询
     * <功能详细描述>
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    public Date getLastChannelSettleDate(Map<String, Object> params) {
       params.put("BUSI_TYPE_16", DictCodeList.业务类型_增值税交易.getValue());
       params.put("PS_C12", DictCodeList.交易方向_税费缴税.getValue());
       params.put("YES_NO_NO", DictCodeList.是否_否.getValue());
       params.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
       return  payTheVatQuery002Mapper.getLastChannelSettleDate(params);
    }
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param listProdId 组合代码集合
     * @param prodType 校验的税类型
     * @param prodType 类型
     * @param queryDate 查询日期
     * @return
     * @see
     * @since 1.0
     */
    public List<String> getAddTaxType(List<String> listProdId,String prodType, String queryDate, String queryDateBegin,String queryDateEnd) {
        if(!StringUtil.isEmptyList(listProdId)) {
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("listProdId", listProdId);
            paramMap.put("prodType", prodType);
            //OPEX-650【产品缴纳】缴纳查询界面，选择纳税日期查询条件进行查询，报数据接口异常 guyunfei 20191227 start
            if(StringUtil.isNotEmpty(queryDate)){
                paramMap.put("queryDate", queryDate);
            }
            if(StringUtil.isNotEmpty(queryDateBegin)){
                paramMap.put("queryDateBegin", queryDateBegin);
            }
            if(StringUtil.isNotEmpty(queryDateEnd)){
                paramMap.put("queryDateEnd", queryDateEnd);
            }
            //OPEX-650【产品缴纳】缴纳查询界面，选择纳税日期查询条件进行查询，报数据接口异常 guyunfei 20191227 end
            List<String> addTaxTypeList =  restTemplate.postForObject(
                com.joyintech.tams.valuation.common.constants.ServiceApplication.VALUATION_SERVICE_NAME + "/valApiController/getAddTaxType",
                paramMap, List.class);
            return addTaxTypeList;
        }
        else {
            return new ArrayList<String>();
        }
    }
    
    /**
     * 
     *  获取通道下的组合
     * <功能详细描述>
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> selectPortfolioIdByChannel(Map<String, Object> params){
        params.put("TAX0102", BussinessDictCodeList.税类型_利息收入.getValue());
        params.put("TAX0202", BussinessDictCodeList.税类型_红利收入.getValue());
        params.put("TAX0302", BussinessDictCodeList.税类型_价差收入.getValue());
        params.put("ALT0102", BussinessDictCodeList.税类型_城建税.getValue());
        params.put("ALT0202", BussinessDictCodeList.税类型_教育费.getValue());
        params.put("ALT0302", BussinessDictCodeList.税类型_地方教育费.getValue());
        params.put("ALT0402", BussinessDictCodeList.税类型_水利基金税.getValue());
        
        
        params.put("FINPROD_TYPE_F18", DictCodeListExt1.金融产品类型_投资组合.getValue());
        params.put("FINPROD_TYPE_F16", DictCodeListExt1.金融产品类型_理财产品.getValue());
        return super.queryForPageForSelectReturn(() -> payTheVatQuery002Mapper.selectPortfolioIdByChannel(params)) ; 
    }
    
    
    /**
     * 生成组合交易表信息
     *
     * @param entity 产品费用支付Dto
     * @return 组合交易表信息
     * @see
     * @since 1.0
     */
    private PtlTrade genPtlTrdea(PayTheVatQuery002Dto entity,TrdProductDeal deal) {
        PtlTrade ptlTrade = new PtlTrade();
        ptlTrade.setPortfolioId(entity.getPortfolioId());
        ptlTrade.setBusiNo(deal.getTradeId());
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        ptlTrade.setBusiId(entity.getFinprodId());
        ptlTrade.setBusiType(DictCodeList.业务类型_增值税交易.getValue());
        ptlTrade.setSettleDate(deal.getTradeDate());
        ptlTrade.setUpdateUser(entity.getUpdateUser());
        ptlTrade.setCreateUser(entity.getCreateUser());
        ptlTrade.setCreateDept(entity.getCreateDept());

        return ptlTrade;
    }
    
    /**
     * 查询币种
     */
    public List<String> searchCurrency(Map<String, Object> pageParam) throws Exception {
    	pageParam.put("finprodType2_F28", BussinessDictCodeList.金融产品类型_投管用_通道.getValue());
        return payTheVatQuery002Mapper.searchCurrency(pageParam);
    }
}




