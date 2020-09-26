/*
 * 文件名：ExpectedprddealFeeDealTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.assetdeal.feedeal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.FinTradeProductAdd;
import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.common.base.entity.StlOrdersExt;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.FinalString;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.FeeDealCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.InterfProductDealBookCommonDto;
import com.joyintech.tams.valuation.common.interf.base.dto.TargetFinProduct;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFeeDeal;
/**
 * 费用交易 模板处理Service(VAL_FEE_DEAL)
 * @author huwei
 * @version 1.0 2018年10月16日
 * @see FeeDealTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class FeeDealTemplateRecService {
    
    /**
     * XMl文件解析Service
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    /**
     * 接口共通方法
     */
    @Autowired
    private InterfCommonService interfCommonService;
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public FeeDealCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                TemplateOrderInfoReaderResult acctEntity) {
        
        // 初始化返回对象
    	FeeDealCommonResult result = new FeeDealCommonResult();
        List<ValFeeDeal> listValFeeDeal = new ArrayList<ValFeeDeal>();
        Map<String,Object> params = acctEntity.getParams();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValFeeDeal = returnResult.getT(ValFeeDeal.class);
        result.setDatanum(listValFeeDeal==null?0:listValFeeDeal.size());
        // 3、根据有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = this.handleValPrdExpDeal(listValFeeDeal,
            acctEntity.getResovleDate(),params,returnResult.getValInterflog());
        if(map != null){
            // 金融产品交易List
            List<TrdProductDeal> listValFinProductDeal = (List<TrdProductDeal>)map.get(
                "ValFinProductDeal");
            result.setListValFinProductDeal(listValFinProductDeal);
            // 估值核算交易扩展表List
            List<TrdProductDealAdd> listValFinProductDealExt = (List<TrdProductDealAdd>)map.get(
                "ValFinProductDealExt");
            result.setListValFinProductDealExt(listValFinProductDealExt);
            // 交易费用明细表List
            List<TrdTradeFeeDetail> listValTgTradeFeeDetail = (List<TrdTradeFeeDetail>)map.get(
                "ValTradeFeeDetail");
            result.setListValTradeFeeDetail(listValTgTradeFeeDetail);
            // 入账信息表List
            List<BokDealAccounting> listValDealAccounting = (List<BokDealAccounting>)map.get(
                "ValDealAccounting");
            result.setListValDealAccounting(listValDealAccounting);
            // 账务明细表List
            List<BokDealAccountingDetail> listValDealAccountingDetail = (List<BokDealAccountingDetail>)map.get(
                "ValDealAccountingDetail");
            result.setListValDealAccountingDetail(listValDealAccountingDetail);
            // 账务明细代码表
            List<BokDetailId> listValBokDetailId = (List<BokDetailId>)map.get("ValBokDetailId");
            result.setListValBokDetailId(listValBokDetailId);

            // 结算指令
            List<StlOrders> listValOrders = (List<StlOrders>)map.get("listValOrders");
            List<StlOrdersExt> listValOrdersExt = (List<StlOrdersExt>)map.get("listValOrdersExt");
            result.setListValOrders(listValOrders);
            result.setListValOrdersExt(listValOrdersExt);

            // 交易类金融产品表
            List<FinTradeProduct> listFinTradeProduct =  (List<FinTradeProduct>)map.get("listFinTradeProduct");
            result.setListFinTradeProduct(listFinTradeProduct);
            
            // 交易类金融产品附属表
            List<FinTradeProductAdd> listFinTradeProductAdd = (List<FinTradeProductAdd>)map.get("listFinTradeProductAdd");
            result.setListFinTradeProductAdd(listFinTradeProductAdd);
            
            // 定义 金融产品分类List
            List<FinProductType> listFinProductType = (List<FinProductType>)map.get("listFinProductType");
            result.setListFinProductType(listFinProductType);
            
            // 添加日志
            result.setValInterflog((List<ValInterflog>)map.get("valInterflogList")); 
        }
        // 返回处理结果数据
        return result;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param listValPrdFeeDeal
     * @param resovleDate
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked"})
	private Map<String, Object> handleValPrdExpDeal(List<ValFeeDeal> listValFeeDeal,
                                                    Date resovleDate,Map<String,Object> params,List<ValInterflog> valInterflog) {
        List<String> bookSetIds = (List<String>)params.get(InterfCommonConstant.接口_运行接口账套ID);
    	String valModel = (String)params.get(InterfCommonConstant.接口模式);
        // 初始化返回map
        Map<String, Object> resultMap = new HashMap<>();
        
        // 金融产品交易List
        List<TrdProductDeal> listValFinProductDeal = new ArrayList<TrdProductDeal>();
        // 估值核算交易扩展表List
        List<TrdProductDealAdd> listValFinProductDealExt = new ArrayList<TrdProductDealAdd>();
        // 入账信息表List
        List<BokDealAccounting> listValDealAccounting = new ArrayList<BokDealAccounting>();
        // 账务明细表List
        List<BokDealAccountingDetail> listValDealAccountingDetail = new ArrayList<BokDealAccountingDetail>();
        // 交易费用明细表List
        List<TrdTradeFeeDetail> listValTradeFeeDetail = new ArrayList<TrdTradeFeeDetail>();
        // 账务明细代码表
        List<BokDetailId> listValBokDetailId = new ArrayList<BokDetailId>();
        // 结算指令
        List<StlOrders> listValOrders = new ArrayList<StlOrders>();
        // 结算指令扩展表
        List<StlOrdersExt> listValOrdersExt = new ArrayList<StlOrdersExt>(); 
        // 交易类金融产品表
        List<FinTradeProduct> listFinTradeProduct = new ArrayList<FinTradeProduct>();
        // 交易类金融产品附属表
        List<FinTradeProductAdd> listFinTradeProductAdd = new ArrayList<FinTradeProductAdd>();
        // 定义 金融产品分类List
        List<FinProductType> listFinProductType = new ArrayList<FinProductType>();
        
        for (ValFeeDeal dto : listValFeeDeal) {
        	if(StringUtil.equals(InterfCommonConstant.接口模式_指定账套,valModel) && !bookSetIds.contains(dto.getProdId())){
        		continue;
        	}
            dto.setCreateTime(resovleDate);
            
            //费用支付(产品直投)
            if(StringUtil.isEmpty(dto.getChannelId())){
            	// 金融产品交易(理财产品费用支付)
                TrdProductDeal valFinProductDeal = this.getValFinProductDeal(dto);
                listValFinProductDeal.add(valFinProductDeal);

                // 估值核算交易扩展表(理财产品费用支付)
                TrdProductDealAdd valFinProductDealExt = this.getValFinProductDealExt(dto, valFinProductDeal);
                listValFinProductDealExt.add(valFinProductDealExt);
                // 交易费用明细表(理财产品费用支付)
                TrdTradeFeeDetail ValTgTradeFeeDetail = this.getValTradeFeeDetail(dto,valFinProductDeal);
                listValTradeFeeDetail.add(ValTgTradeFeeDetail);
            	
                // 入账信息表 账务明细表 账务明细代码表 调用共通
                InterfProductDealBookCommonDto dealAccounting = interfCommonService.dealAccounting(
                		valFinProductDeal, dto.getProdId());
                
                listValDealAccounting.add(dealAccounting.getBokDealAccounting());
                
                String finprodType = dealAccounting.getAcctSubjectType();
                BokDetailId bokDetailId = new BokDetailId();
                bokDetailId.setBusiId(dto.getProdId());
                bokDetailId.setBusiDetailType(finprodType);
                String detailId =  interfCommonService.genMd5BokDetailId(bokDetailId);
                
                BokDealAccountingDetail bokDealAccountingDetail = dealAccounting.getBokDealAccountingDetail();
                bokDealAccountingDetail.setBokDetailId(detailId);
                listValDealAccountingDetail.add(bokDealAccountingDetail);
                
                BokDetailId valBokDetailId =  dealAccounting.getBokDetailId();
                valBokDetailId.setBusiId(dto.getProdId());
                valBokDetailId.setBusiDetailType(finprodType);
                valBokDetailId.setDetailSubjectNoTail(dto.getProdId()+
                		FinalString.LAYERING_SPLIT+
                		finprodType);
                
                valBokDetailId.setDetailSubnameTail(dto.getProdName());
                valBokDetailId.setBokDetailId(detailId);
                listValBokDetailId.add(valBokDetailId);
                
                if(DictCodeList.是否_是.getValue().equals(dto.getIsPrepaid())){
                    // T9.交易类金融产品
                    FinTradeProduct finTradeProduct = this.getFinTradeProduct(dto, valFinProductDeal);
                    listFinTradeProduct.add(finTradeProduct);
                    // T10.交易类金融产品附属表
                    FinTradeProductAdd finTradeProductAdd = this.getFinTradeProductAdd(dto, finTradeProduct);
                    listFinTradeProductAdd.add(finTradeProductAdd);
                    // 交易类金融产品
                    FinProductType finProductType = this.getFinProductType(dto, finTradeProduct);
                    listFinProductType.add(finProductType);
                    
                    // T12.账务明细代码表
                    BokDetailId valBokDetailId2 = interfCommonService.getBokDetailId(finTradeProduct.getFinprodId(), null, finTradeProduct.getFinprodType(), null, finTradeProduct.getBranch(), finTradeProduct.getCcy());
                    // 账套代码 = 入账信息.账套代码
                    valBokDetailId2.setBooksetId(dealAccounting.getBokDealAccounting().getBooksetId());
                    // 交易编号
                    valBokDetailId2.setTradeId(valFinProductDeal.getTradeId());
                    valBokDetailId2.setDetailSubjectNoTail(finTradeProduct.getFinprodId());
                    valBokDetailId2.setDetailSubnameTail(finTradeProduct.getFinprodId());
                    listValBokDetailId.add(valBokDetailId2);
                }
                
                Map<String, Object> map = interfCommonService.instructionGenerate(valFinProductDeal, 
                		dto.getProdId(), dealAccounting.getAcctSubjectType(),null);
                if(map.get("ORDERS")!=null){
                	listValOrders.addAll((List<StlOrders>)map.get("ORDERS"));
                }
                if(map.get("ORDERS_EXT")!=null){
                	listValOrdersExt.addAll((List<StlOrdersExt>)map.get("ORDERS_EXT"));
                }
                if(map.get("ACCOUNTING")!=null){
                	 listValDealAccounting.addAll((List<BokDealAccounting>)map.get("ACCOUNTING"));
                }
                if(map.get("ACCOUNTING_DETAI")!=null){
                	listValDealAccountingDetail.addAll(( List<BokDealAccountingDetail>)map.get("ACCOUNTING_DETAI"));
                }
                if(map.get("BOK_DETAIL_ID")!=null){
                	listValBokDetailId.addAll((List<BokDetailId>)map.get("BOK_DETAIL_ID"));
                }
            	
            }else{
            	
            	/**1.通道产品费用支付**/
            	// 金融产品交易(通道产品费用支付)
            	String chlProdId= dto.getProdId() + FinalString.LAYERING_SPLIT + dto.getChannelId();
            	
            	//通道基本信息查询
            	TargetFinProduct targetFinProduct = interfCommonService.getTargetFinProduct(dto.getChannelId());
            	String td_finprodAbbr = targetFinProduct==null?null:targetFinProduct.getFinprodAbbr();
            	
            	
            	TrdProductDeal chlTrdProductDeal = this.getValFinProductDeal(dto);
            	chlTrdProductDeal.setFinprodId(chlProdId);
            	chlTrdProductDeal.setChlId(dto.getChannelId());
            	chlTrdProductDeal.setTotleTradeId(chlTrdProductDeal.getTradeId());
            	listValFinProductDeal.add(chlTrdProductDeal);
            	
            	// 估值核算交易扩展表(通道产品费用支付)
                TrdProductDealAdd chlFinProductDealExt = this.getValFinProductDealExt(dto, chlTrdProductDeal);
                chlFinProductDealExt.setPortfolioId(chlProdId);
//                chlFinProductDealExt.setProdId(chlProdId);//一体化无此字段
                listValFinProductDealExt.add(chlFinProductDealExt);
                
                // 交易费用明细表(通道产品费用支付)
                TrdTradeFeeDetail chlTgTradeFeeDetail = this.getValTradeFeeDetail(dto,chlTrdProductDeal);
                listValTradeFeeDetail.add(chlTgTradeFeeDetail);
                
                // 入账信息表 账务明细表 账务明细代码表 调用共通
                InterfProductDealBookCommonDto dealAccounting = interfCommonService.dealAccounting(
                		chlTrdProductDeal, dto.getProdId());
                listValDealAccounting.add(dealAccounting.getBokDealAccounting());
                
                String finprodType = dealAccounting.getAcctSubjectType();
                BokDetailId bokDetailId = new BokDetailId();
                bokDetailId.setBusiId(chlProdId);
                bokDetailId.setBusiDetailType(finprodType);
                String detailId =  interfCommonService.genMd5BokDetailId(bokDetailId);
                
                BokDealAccountingDetail bokDealAccountingDetail = dealAccounting.getBokDealAccountingDetail();
                bokDealAccountingDetail.setBokDetailId(detailId);
                listValDealAccountingDetail.add(bokDealAccountingDetail);
                
                BokDetailId valBokDetailId =  dealAccounting.getBokDetailId();
                valBokDetailId.setBusiId(chlProdId);
                valBokDetailId.setBusiDetailType(finprodType);
                valBokDetailId.setDetailSubjectNoTail(chlProdId+FinalString.LAYERING_SPLIT+finprodType);
                valBokDetailId.setDetailSubnameTail(td_finprodAbbr);
                valBokDetailId.setBokDetailId(detailId);
                listValBokDetailId.add(valBokDetailId);
                
                Map<String, Object> map = interfCommonService.instructionGenerate(chlTrdProductDeal,dto.getProdId(), dealAccounting.getAcctSubjectType(),null);
                if(map.get("ORDERS")!=null){
                	listValOrders.addAll((List<StlOrders>)map.get("ORDERS"));
                }
                if(map.get("ORDERS_EXT")!=null){
                	listValOrdersExt.addAll((List<StlOrdersExt>)map.get("ORDERS_EXT"));
                }
                if(map.get("ACCOUNTING")!=null){
                	 listValDealAccounting.addAll((List<BokDealAccounting>)map.get("ACCOUNTING"));
                }
                if(map.get("ACCOUNTING_DETAI")!=null){
                	listValDealAccountingDetail.addAll(( List<BokDealAccountingDetail>)map.get("ACCOUNTING_DETAI"));
                }
                if(map.get("BOK_DETAIL_ID")!=null){
                	listValBokDetailId.addAll((List<BokDetailId>)map.get("BOK_DETAIL_ID"));
                }
                
                /**2.通道产品申购**/
                // 金融产品交易(通道产品申购)
            	TrdProductDeal chlPurTrdProductDeal = this.getValFinProductDealTD(dto);
            	chlPurTrdProductDeal.setTotleTradeId(chlTrdProductDeal.getTradeId());
            	listValFinProductDeal.add(chlPurTrdProductDeal);
            	
            	// 估值核算交易扩展表(通道产品申购)
                TrdProductDealAdd chlPurFinProductDealExt = this.getValFinProductDealExt(dto, chlPurTrdProductDeal);
                chlPurFinProductDealExt.setPortfolioId(chlProdId);
//                chlPurFinProductDealExt.setProdId(chlProdId);//一体化无此字段
                listValFinProductDealExt.add(chlPurFinProductDealExt);
                
                // 入账信息表 账务明细表 账务明细代码表 调用共通
                dealAccounting = interfCommonService.dealAccounting(chlPurTrdProductDeal, dto.getProdId());
                
                listValDealAccounting.add(dealAccounting.getBokDealAccounting());
                
                finprodType = dealAccounting.getAcctSubjectType();
                bokDetailId = new BokDetailId();
                bokDetailId.setBusiId(chlProdId);
                bokDetailId.setBusiDetailType(finprodType);
                detailId =  interfCommonService.genMd5BokDetailId(bokDetailId);
                
                bokDealAccountingDetail = dealAccounting.getBokDealAccountingDetail();
                bokDealAccountingDetail.setBokDetailId(detailId);
                listValDealAccountingDetail.add(bokDealAccountingDetail);
                
                valBokDetailId =  dealAccounting.getBokDetailId();
                valBokDetailId.setBusiId(chlProdId);
                valBokDetailId.setBusiDetailType(finprodType);
                valBokDetailId.setDetailSubjectNoTail(chlProdId+FinalString.LAYERING_SPLIT+finprodType);
                
                valBokDetailId.setDetailSubnameTail(td_finprodAbbr);
                valBokDetailId.setBokDetailId(detailId);
                listValBokDetailId.add(valBokDetailId);
                
                map = interfCommonService.instructionGenerate(chlPurTrdProductDeal,dto.getProdId(), 
                		dealAccounting.getAcctSubjectType(),null);
                if(map.get("ORDERS")!=null){
                	listValOrders.addAll((List<StlOrders>)map.get("ORDERS"));
                }
                if(map.get("ORDERS_EXT")!=null){
                	listValOrdersExt.addAll((List<StlOrdersExt>)map.get("ORDERS_EXT"));
                }
                if(map.get("ACCOUNTING")!=null){
                	 listValDealAccounting.addAll((List<BokDealAccounting>)map.get("ACCOUNTING"));
                }
                if(map.get("ACCOUNTING_DETAI")!=null){
                	listValDealAccountingDetail.addAll(( List<BokDealAccountingDetail>)map.get("ACCOUNTING_DETAI"));
                }
                if(map.get("BOK_DETAIL_ID")!=null){
                	listValBokDetailId.addAll((List<BokDetailId>)map.get("BOK_DETAIL_ID"));
                }
                
                /**3.理财产品买通道虚拟资产**/
                // 金融产品交易(理财产品买虚拟通道资产)
            	TrdProductDeal prdTrdProductDeal = this.getValFinProductDealPRD(dto);
            	prdTrdProductDeal.setTotleTradeId(chlTrdProductDeal.getTradeId());
            	listValFinProductDeal.add(prdTrdProductDeal);
                
            	// 估值核算交易扩展表(理财产品买虚拟通道资产)
                TrdProductDealAdd prdFinProductDealExt = this.getValFinProductDealExt(dto, prdTrdProductDeal);
                listValFinProductDealExt.add(prdFinProductDealExt);
                
                // 入账信息表 账务明细表 账务明细代码表 调用共通
                dealAccounting = interfCommonService.dealAccounting(prdTrdProductDeal, dto.getProdId());
                listValDealAccounting.add(dealAccounting.getBokDealAccounting());
                listValDealAccountingDetail.add(dealAccounting.getBokDealAccountingDetail());
                listValBokDetailId.add(valBokDetailId);
                
                map = interfCommonService.instructionGenerate(prdTrdProductDeal,dto.getProdId(), dealAccounting.getAcctSubjectType(),null);
                if(map.get("ORDERS")!=null){
                	listValOrders.addAll((List<StlOrders>)map.get("ORDERS"));
                }
                if(map.get("ORDERS_EXT")!=null){
                	listValOrdersExt.addAll((List<StlOrdersExt>)map.get("ORDERS_EXT"));
                }
                if(map.get("ACCOUNTING")!=null){
                	 listValDealAccounting.addAll((List<BokDealAccounting>)map.get("ACCOUNTING"));
                }
                if(map.get("ACCOUNTING_DETAI")!=null){
                	listValDealAccountingDetail.addAll(( List<BokDealAccountingDetail>)map.get("ACCOUNTING_DETAI"));
                }
                if(map.get("BOK_DETAIL_ID")!=null){
                	listValBokDetailId.addAll((List<BokDetailId>)map.get("BOK_DETAIL_ID"));
                }
            }
        }
        resultMap.put("ValFinProductDeal", listValFinProductDeal);
        resultMap.put("ValFinProductDealExt", listValFinProductDealExt);
        resultMap.put("ValDealAccounting", listValDealAccounting);
        resultMap.put("ValDealAccountingDetail", listValDealAccountingDetail);
        resultMap.put("ValTradeFeeDetail", listValTradeFeeDetail);
        resultMap.put("ValBokDetailId", listValBokDetailId);

        resultMap.put("listValOrders", listValOrders);
        resultMap.put("listValOrdersExt", listValOrdersExt);  
        resultMap.put("listFinTradeProduct", listFinTradeProduct);  
        resultMap.put("listFinTradeProductAdd", listFinTradeProductAdd);  
        resultMap.put("listFinProductType", listFinProductType);  
        List<ValInterflog> valInterflogList= new ArrayList<>();
        valInterflogList = interfCommonService.filterValInterflogList(valInterflog, bookSetIds, valModel);
    	resultMap.put("valInterflogList", valInterflogList);
        
        return resultMap;
    }
    
    /**
     * 金融产品交易(产品直投/产品借通道-费用支付)
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private TrdProductDeal getValFinProductDeal(ValFeeDeal dto) {
        // 初始化 金融产品交易 返回值
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        // 交易编号 = 调用共通方法生成
        trdProductDeal.setTradeId(interfCommonService.getTradeid(dto.getPs(),
            dto.getTradeDate(), "A"));
        // 业务类型 = 数据字典：业务类型.费用交易
        trdProductDeal.setBusiType(DictCodeList.业务类型_产品费用交易.getValue()); 
        //金融产品代码 = A.产品代码  
        trdProductDeal.setFinprodId(dto.getProdId());        
        //金融产品类型 = 数据字典：金融产品类型.理财产品     
        trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_交易费用.getValue());
        // 分支序号 = 设置全局变量(默认为0）
        trdProductDeal.setBranch(InterfCommonConstant.分支序号_默认);       
        // 交易日 = A.交易日
        trdProductDeal.setTradeDate(dto.getTradeDate());
        // 交割日 = A.交割日
        trdProductDeal.setSettleDate(dto.getSettdateDate());
        // 到期日 = A.付费截止日
        trdProductDeal.setMdate(dto.getMdate());
        // 交易币种 = A.交易币种
        trdProductDeal.setCcy(dto.getCcy());
        // 交易金额 = A.实付费用
        trdProductDeal.setTradeAmt(dto.getDealAmt());
        // 交易方向 = A.交易方向
        trdProductDeal.setPs(dto.getPs());
        // 交易状态 = A.数据动作标志--> 新增 --> 01:初始    撤销 --> 03:撤销
        trdProductDeal.setTradeStatus(dto.getDataActionSign());
        // 是否结清 = 固定值 (设置默认值)
        trdProductDeal.setIsClean(InterfCommonConstant.是否结清_默认);
        // 关联系统交易编号 = A.成交编号
        trdProductDeal.setfTradeId(dto.getTradeId());
        //登记托管机构= A.登记托管机构
        trdProductDeal.setRegistOrg(dto.getRegistOrg());
        // 创建时间 = 系统时间
        trdProductDeal.setCreateTime(dto.getCreateTime());
        
        // 原交易编号 = 交易状态为撤销时： 原交易编号=调用共通设计->【详细设计-接口共通方法设计.xlsx】->撤销交易对应的原交易编号查询SHEET 交易状态为非撤销时：原交易编号为空
        if(trdProductDeal.getTradeStatus().equals(DictCodeListExt1.交易状态_撤销.getValue())){
            trdProductDeal.setpTradeId(interfCommonService.getFTradeId(trdProductDeal));
        }
        return trdProductDeal;
    }

    /**
     * T2: 估值核算交易扩展表
     * 
     * @param dto
     * @param deal 
     * @return 估值核算交易扩展表
     * @see
     * @since 1.0
     */
    private TrdProductDealAdd getValFinProductDealExt(ValFeeDeal dto, TrdProductDeal deal) {
    	TrdProductDealAdd trdProductDealExt = new TrdProductDealAdd();
        // 交易编号 = T1.交易编号
        trdProductDealExt.setTradeId(deal.getTradeId());
        // 理财产品代码 = A.产品代码
        /** modify by wujie on 20181115 start remark:投资组合投资时应赋值[表.投资组合代码]字段
        trdProductDealExt.setProdId(dto.getProdId());
        **/
//        String finprodType = interfCommonService.queryProductType(dto.getProdId());
        trdProductDealExt.setPortfolioId(dto.getProdId());
//        if(DictCodeListExt1.金融产品类型_投资组合.getValue().equals(finprodType)){
//        	trdProductDealExt.setPortfolioId(dto.getProdId());
//        }else{
//        	trdProductDealExt.setProdId(dto.getProdId());
//        }
        /**modify by wujie on 20181115 end remark:投资组合投资时应赋值[表.投资组合代码]字段**/
        // 是否有结算指令 = A.有无清算指令
//        trdProductDealExt.setHasOrder(dto.getIsClean());
        // 生成方式 = 固定值 接口接入
        trdProductDealExt.setGenType(DictCodeListExt1.生成方式_接口接入.getValue());
        // 创建时间 = 固定值 系统时间
        trdProductDealExt.setCreateTime(dto.getCreateTime());
        return trdProductDealExt;

    }
    
    /**
     * T3.交易费用明细表(产品直投/产品借通道-费用支付)
     * 
     * @param dto ValFundMnfRedAffirm
     * @return ValTradeFeeDetail
     * @see
     * @since 1.0
     */
    private TrdTradeFeeDetail getValTradeFeeDetail(ValFeeDeal dto,TrdProductDeal deal) {
    	TrdTradeFeeDetail result = new TrdTradeFeeDetail();
        // 交易编号 = T1.交易编号
        result.setTradeId(deal.getTradeId());
        // 一次性费用类型 = A.费用类型
        result.setFeeType(dto.getFeeType());
        // 是否随交易支付 =Y 否
        result.setIsPayWithTrade(DictCodeList.是否_否.getValue());
        // 费用金额 = A.实付费用
        result.setFeeAmt(dto.getDealAmt());
        // 费用代码 =A.费用类型
        result.setFeeId(dto.getFeeType());
        // 是否作为待摊= A.是否作为待摊
        result.setIsPrepaid(dto.getIsPrepaid());
        // 创建时间 = 系统时间
        result.setCreateTime(dto.getCreateTime());
        // 更新时间 = 系统时间
        result.setUpdateTime(dto.getCreateTime());
        return result;
    }
    
    
    
    /**
     * 金融产品交易(通道产品申购)
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private TrdProductDeal getValFinProductDealTD(ValFeeDeal dto) {
        // 初始化 金融产品交易 返回值
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        // 业务类型 = 数据字典：业务类型.货币型TA交易
        trdProductDeal.setBusiType(DictCodeList.业务类型_货币型TA交易.getValue()); 
        //金融产品代码 = A.产品代码  
        trdProductDeal.setFinprodId(dto.getProdId()+FinalString.LAYERING_SPLIT+dto.getChannelId());        
        //金融产品类型 = 数据字典：金融产品类型.通道     
        trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_通道.getValue());
        // 分支序号 = 设置全局变量(默认为0）
        trdProductDeal.setBranch(InterfCommonConstant.分支序号_默认);       
        // 交易日 = A.交易日
        trdProductDeal.setTradeDate(dto.getTradeDate());
        // 交割日 = A.交割日
        trdProductDeal.setSettleDate(dto.getSettdateDate());
        // 交易币种 = A.交易币种
        trdProductDeal.setCcy(dto.getCcy());
        // 交易金额 = A.实付费用
        trdProductDeal.setTradeAmt(dto.getDealAmt());
        // 交易份额 = A.实付费用
        trdProductDeal.setShareAmt(dto.getDealAmt());
        // 交易本金 = A.实付费用
        trdProductDeal.setPrinAmt(dto.getDealAmt());
        // 交易方向 = A.交易方向
        trdProductDeal.setPs(DictCodeList.交易方向_产品申购.getValue());
        // 交易状态 = A.数据动作标志--> 新增 --> 01:初始    撤销 --> 03:撤销
        trdProductDeal.setTradeStatus(dto.getDataActionSign());
        // 是否结清 = 固定值 (设置默认值)
        trdProductDeal.setIsClean(InterfCommonConstant.是否结清_默认);
        // 关联系统交易编号 = A.成交编号
        trdProductDeal.setfTradeId(dto.getTradeId());
        //登记托管机构= A.登记托管机构
        trdProductDeal.setRegistOrg(dto.getRegistOrg());
        // 创建时间 = 系统时间
        trdProductDeal.setCreateTime(dto.getCreateTime()); 
        // 交易编号 = 调用共通方法生成
        trdProductDeal.setTradeId(interfCommonService.getTradeid(trdProductDeal.getPs(),
            dto.getTradeDate(), "A"));
        // 通道代码 = A.通道代码
        trdProductDeal.setChlId(dto.getChannelId());
        
        // 原交易编号 = 交易状态为撤销时： 原交易编号=调用共通设计->【详细设计-接口共通方法设计.xlsx】->撤销交易对应的原交易编号查询SHEET 交易状态为非撤销时：原交易编号为空
        if(trdProductDeal.getTradeStatus().equals(DictCodeListExt1.交易状态_撤销.getValue())){
            trdProductDeal.setpTradeId(interfCommonService.getFTradeId(trdProductDeal));
        }
        return trdProductDeal;
    }
    
    
    /**
     * 金融产品交易(理财产品买虚拟通道资产)
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private TrdProductDeal getValFinProductDealPRD(ValFeeDeal dto) {
        // 初始化 金融产品交易 返回值
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        // 业务类型 = 数据字典：业务类型.货币基金交易
        trdProductDeal.setBusiType(DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue()); 
        //金融产品代码 = A.产品代码  
        trdProductDeal.setFinprodId(dto.getProdId()+FinalString.LAYERING_SPLIT+dto.getChannelId());        
        //金融产品类型 = 数据字典：金融产品类型.计划类资产     
        trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        // 分支序号 = 设置全局变量(默认为0）
        trdProductDeal.setBranch(InterfCommonConstant.分支序号_默认);       
        // 交易日 = A.交易日
        trdProductDeal.setTradeDate(dto.getTradeDate());
        // 交割日 = A.交割日
        trdProductDeal.setSettleDate(dto.getSettdateDate());
        // 交易币种 = A.交易币种
        trdProductDeal.setCcy(dto.getCcy());
        // 交易金额 = A.实付费用
        trdProductDeal.setTradeAmt(dto.getDealAmt());
        // 交易份额 = A.实付费用
        trdProductDeal.setShareAmt(dto.getDealAmt());
        // 交易本金 = A.实付费用
        trdProductDeal.setPrinAmt(dto.getDealAmt());
        // 交易方向 = A.交易方向
        trdProductDeal.setPs(DictCodeList.交易方向_申购确认.getValue());
        // 交易状态 = A.数据动作标志--> 新增 --> 01:初始    撤销 --> 03:撤销
        trdProductDeal.setTradeStatus(dto.getDataActionSign());
        // 是否结清 = 固定值 (设置默认值)
        trdProductDeal.setIsClean(InterfCommonConstant.是否结清_默认);
        // 关联系统交易编号 = A.成交编号
        trdProductDeal.setfTradeId(dto.getTradeId());
        //登记托管机构= A.登记托管机构
        trdProductDeal.setRegistOrg(dto.getRegistOrg());
        // 创建时间 = 系统时间
        trdProductDeal.setCreateTime(dto.getCreateTime()); 
        // 交易编号 = 调用共通方法生成
        trdProductDeal.setTradeId(interfCommonService.getTradeid(trdProductDeal.getPs(),
            dto.getTradeDate(), "A"));
        
        // 原交易编号 = 交易状态为撤销时： 原交易编号=调用共通设计->【详细设计-接口共通方法设计.xlsx】->撤销交易对应的原交易编号查询SHEET 交易状态为非撤销时：原交易编号为空
        if(trdProductDeal.getTradeStatus().equals(DictCodeListExt1.交易状态_撤销.getValue())){
            trdProductDeal.setpTradeId(interfCommonService.getFTradeId(trdProductDeal));
        }
        return trdProductDeal;
    }
    
    /**
     * T9.交易类金融产品初始化
     * @param dto 模板信息
     * @param deal 交易表数据
     * @return 交易类金融产品初始化
     * @see
     * @since 1.0
     */
    private FinTradeProduct getFinTradeProduct(ValFeeDeal dto, TrdProductDeal deal){//add by niuqianyu on 20190117
        FinTradeProduct finTradeProduct = new FinTradeProduct();
        // 金融产品代码  = A.产品代码
        finTradeProduct.setFinprodId(deal.getTradeId());
        // 金融产品类型 = A.金融产品类型
        finTradeProduct.setFinprodType(DictCodeListExt1.金融产品类型_待摊费用.getValue());
        // 分支序号 = 固定值 设置全局变量(默认为0）
        finTradeProduct.setBranch(0L);//modify by dongjun on 20181029
        // 名义本金 = A.实付费用
        finTradeProduct.setPrin(dto.getDealAmt());
        // 本金币种 = A.认购币种
        finTradeProduct.setCcy(dto.getCcy());
        // 起息日 = A.待摊开始日
        finTradeProduct.setVdate(dto.getPrepaidVdate());
        // 到期日 = A.待摊结束日
        finTradeProduct.setMdate(dto.getPrepaidMdate());
        finTradeProduct.setCreateTime(dto.getCreateTime()); 
        return finTradeProduct;
    }
    
    /**
     * T11.交易类金融产品附属表
     * @param dto A
     * @param finTradeProduct T9.交易类金融产品初始化
     * @return
     * @see
     * @since 1.0
     */
    private FinTradeProductAdd getFinTradeProductAdd(ValFeeDeal dto, FinTradeProduct finTradeProduct) {
        // 标的类金融产品扩展表
        FinTradeProductAdd finTradeProductAdd = new FinTradeProductAdd();       
        //金融产品代码 = T9.金融产品代码
        finTradeProductAdd.setFinprodId(finTradeProduct.getFinprodId());
        // 金融产品类型 = T9.金融产品类型
        finTradeProductAdd.setFinprodType(finTradeProduct.getFinprodType());
        // 分支序号 = T9.分支序号
        finTradeProductAdd.setBranch(finTradeProduct.getBranch());
        // 登记托管机构 = A.登记托管机构
        finTradeProductAdd.setRegistOrg(dto.getRegistOrg());
        finTradeProductAdd.setCreateTime(dto.getCreateTime()); 
        return finTradeProductAdd;
    }
    
    /**
     * T11.交易类金融产品附属表
     * @param dto A
     * @param finTradeProduct T9.交易类金融产品初始化
     * @return
     * @see
     * @since 1.0
     */
    private FinProductType getFinProductType(ValFeeDeal dto, FinTradeProduct finTradeProduct) {
        // 标的类金融产品扩展表
        FinProductType finProductType = new FinProductType();       
        //金融产品代码 = T9.金融产品代码
        finProductType.setFinprodId(finTradeProduct.getFinprodId());
        // 分支序号 = T9.分支序号
        finProductType.setBranch(finTradeProduct.getBranch());
        // Type1 = A.费用类型
        finProductType.setType1(dto.getFeeType());
        finProductType.setCreateTime(dto.getCreateTime()); 
        return finProductType;
    }
    

//    @SuppressWarnings("unchecked")
//	private void dataEncapsulation(List<BokDealAccounting> listValDealAccounting,
//			List<BokDealAccountingDetail> listValDealAccountingDetail, List<BokDetailId> listValBokDetailId,
//			List<StlOrders> listValOrders, List<StlOrdersExt> listValOrdersExt, Map<String, Object> prodResultMap) {
//		if((List<BokDealAccounting>)prodResultMap.get("listValDealAccounting") != null){
//			listValDealAccounting.addAll((List<BokDealAccounting>)prodResultMap.get("listValDealAccounting"));
//		}
//		if((List<BokDealAccountingDetail>)prodResultMap.get("listValDealAccountingDetail") != null){
//			listValDealAccountingDetail.addAll((List<BokDealAccountingDetail>)prodResultMap.get("listValDealAccountingDetail"));
//		}
//		if((List<BokDetailId>)prodResultMap.get("listValBokDetailId") != null){
//			listValBokDetailId.addAll((List<BokDetailId>)prodResultMap.get("listValBokDetailId"));
//		}
//		if((List<StlOrders>)prodResultMap.get("listValOrders") != null){
//			listValOrders.addAll((List<StlOrders>)prodResultMap.get("listValOrders"));
//		}
//		if((List<StlOrdersExt>)prodResultMap.get("listValOrdersExt") != null){
//			listValOrdersExt.addAll((List<StlOrdersExt>)prodResultMap.get("listValOrdersExt"));
//		}
//	}
//
//        //交易状态
//        result.setTradeStatus(valFinProductDeal.getTradeStatus());
//        // 交易编号
//        result.setTradeId(valFinProductDeal.getTradeId());
//        // 交易日
//        result.setTradeDate(valFinProductDeal.getSettleDate());
//        // 交易方向
//        result.setPs(valFinProductDeal.getPs());
//        // 交易金额
//        result.setTardeAmt(valFinProductDeal.getTradeAmt());
//        // 金融产品代码
//        result.setFinprodId(dto.getProdId());
//        // 金融产品类型
//        /**modify by wujie on 20190107 start
//        result.setFinprodType(interfCommonService.queryProductType(prodId));
//        **/
//        result.setFinprodType(acctSubjectType);
//        /**modify by wujie on 20190107 end**/
//        
//        // 业务类型
//        result.setBusiType(dto.getFeeType());
//        // 理财产品代码
//        result.setProdId(dto.getProdId());
//        // 币种
//        result.setCcy(valFinProductDeal.getCcy());
//        // 是否随交易出账
//        result.setIsTrade(DictCodeList.是否_是.getValue());
//        // 账套代码
//        result.setBookNo(valBokDetailId.getBooksetId());
//        // 交割日
//        result.setEffDate(valFinProductDeal.getSettleDate());
//        // 创建时间
//        result.setCreateTime(dto.getCreateTime());
//        
//        /**add by chendijun  2018/11/15 start**/
//        //原交易编号
//        result.setpTradeId(valFinProductDeal.getpTradeId());
//        /**add by chendijun  2018/11/15 end**/
//        return result;
//    }
}
