/*
 * 文件名：AddValueTaxDealTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangqidong
 * 修改时间：2018年8月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.assetdeal.vatax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.common.base.entity.StlOrdersExt;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.interf.base.constant.FinalString;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValAddValueTaxDeal;


/**
 * 增值税交易模板处理Service(VAL_ADD_VALUE_TAX_DEAL)
 * 
 * @author wangqidong
 * @version 1.0 2018年8月16日
 * @see AddValueTaxDealTemplateRecService
 * @since 1.0
 */

@Service
@Scope("prototype")
public class AddValueTaxDealTemplateRecService {

    /**
     * XMl文件解析Service
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
    
    /**
     * 接口共通Service
     */
    @Autowired
    private InterfCommonService interfCommonService;
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param templateSourceFlgDTO 文件来源和类型
     * @param acctEntity 计提基础信息DTO
     * @return 增值税交易 接口模板处理返回共通结果
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public AssetDealInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        AssetDealInfoValCommonResult result = new AssetDealInfoValCommonResult();
        List<ValAddValueTaxDeal> listValAddValueTaxDeal = new ArrayList<ValAddValueTaxDeal>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        Map<String,Object> params = acctEntity.getParams();

        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        listValAddValueTaxDeal = returnResult.getT(ValAddValueTaxDeal.class);
        //获取数据数量
        result.setDataNum(listValAddValueTaxDeal.size());

        /**add by wujie on 20210506 start**/
        if(returnResult.isReadFile()){
            result.getParamMap().put(InterfCommonConstant.读取文件状态,DictCodeList.是否_是.getValue());
        }
        /**add by wujie on 20210506 end**/

        // 2、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleValAddValueTaxDeal(listValAddValueTaxDeal,acctEntity.getResovleDate(),params,returnResult.getValInterflog());

        // 金融产品交易List
        List<TrdProductDeal> listValFinProductDeal = (List<TrdProductDeal>)map.get(
            "ValFinProductDeal");
        result.setListValFinProductDeal(listValFinProductDeal);
        // 估值核算交易扩展表List
        List<TrdProductDealAdd> listValFinProductDealExt = (List<TrdProductDealAdd>)map.get(
            "ValFinProductDealExt");
        result.setListValFinProductDealExt(listValFinProductDealExt);
        // 入账信息表List
        List<BokDealAccounting> listValDealAccounting = (List<BokDealAccounting>)map.get(
            "ValDealAccounting");
        result.setListValDealAccounting(listValDealAccounting);
        // 账务明细表List
        List<BokDealAccountingDetail> listValDealAccountingDetail = (List<BokDealAccountingDetail>)map.get(
            "ValDealAccountingDetail");
        result.setListValDealAccountingDetail(listValDealAccountingDetail);
        
        // 账务明细代码表List
        List<BokDetailId> listValBokDetailId = (List<BokDetailId>)map.get(
            "ValBokDetailId");
        result.setListValBokDetailId(listValBokDetailId);
              
        // 交易费用明细表List
        List<TrdTradeFeeDetail> listValTradeFeeDetail = (List<TrdTradeFeeDetail>)map.get(
            "ValTradeFeeDetail");
        result.setListValTradeFeeDetail(listValTradeFeeDetail);
        //结算指令表List
        List<StlOrders> valOrdersList=(List<StlOrders>)map.get("valOrdersList");
        result.setListValOrders(valOrdersList);
        //结算指令拓展表list
        List<StlOrdersExt> valOrdersExtList=(List<StlOrdersExt>)map.get("valOrdersExtList");
        result.setListValOrdersExt(valOrdersExtList);
        // 添加日志
        result.setValInterflog((List<ValInterflog>)map.get("valInterflogList"));
        // 返回处理结果数据
        return result;
    }

    /**
     * 增值税交易
     * 
     * @param listValAddValueTaxDeal listValAddValueTaxDeal
     * @param resovleDate resovleDate
     * @return 金融产品交易List + 估值核算交易扩展表List +  交易费用明细表List
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> handleValAddValueTaxDeal(List<ValAddValueTaxDeal> listValAddValueTaxDeal,
            Date resovleDate,Map<String,Object> params,List<ValInterflog> valInterflog) {
		List<String> bookSetIds = (List<String>)params.get(InterfCommonConstant.接口_运行接口账套ID);
		String valModel = (String)params.get(InterfCommonConstant.接口模式);
		Map<String, Object> resultMap = new HashMap<>();
        // 金融产品交易List
        List<TrdProductDeal> listValFinProductDeal = new ArrayList<TrdProductDeal>();
        // 估值核算交易扩展表List
        List<TrdProductDealAdd> listValFinProductDealExt = new ArrayList<TrdProductDealAdd>();
        // 交易费用明细表List
        List<TrdTradeFeeDetail> listValTradeFeeDetail = new ArrayList<TrdTradeFeeDetail>();
        // 入账信息表List
        List<BokDealAccounting> listValDealAccounting = new ArrayList<BokDealAccounting>();
        // 账务明细表List
        List<BokDealAccountingDetail> listValDealAccountingDetail = new ArrayList<BokDealAccountingDetail>();
         //结算指令list
        List<StlOrders> valOrdersList = new ArrayList<StlOrders>();
        //结算指令拓展list
        List<StlOrdersExt> valOrdersExtList = new ArrayList<StlOrdersExt>();
        // 账务明细代码表List
        List<BokDetailId> listValBokDetailId = new ArrayList<BokDetailId>();
        
        List<ValInterflog> valInterflogList =  new ArrayList<>();
        for (ValAddValueTaxDeal dto : listValAddValueTaxDeal) {
        	if(StringUtil.equals(InterfCommonConstant.接口模式_指定账套,valModel) && !bookSetIds.contains(dto.getProdId())){
        		continue;
			}
            dto.setCreateTime(resovleDate);     //数据去重
            
            
            //增值税(产品直投)
            if(StringUtil.isEmpty(dto.getChannelId())){
            	
            	 String finprodType = DictCodeListExt1.金融产品类型_理财产品.getValue();
                 ValBooksetInfoDto valBooksetInfoDto = interfCommonService.getBooksetInfoByProdId(dto.getProdId());
                 if(valBooksetInfoDto != null){
                 	finprodType = valBooksetInfoDto.getAcctSubjectType();
                 }
                 
                 // 金融产品交易(理财产品增值税交易)
                 TrdProductDeal valFinProductDeal = this.getValFinProductDeal(dto,finprodType);
                 listValFinProductDeal.add(valFinProductDeal);
                 
                 // 估值核算交易扩展表 (理财产品增值税交易)
                 TrdProductDealAdd valFinProductDealExt = this.getValFinProductDealExt(dto,valFinProductDeal);
                 listValFinProductDealExt.add(valFinProductDealExt);
                 
                 // 交易费用明细表(理财产品增值税交易)
                 List<TrdTradeFeeDetail> listfeeDetail = this.getValTradeFeeDetail(dto,valFinProductDeal);
                 listValTradeFeeDetail.addAll(listfeeDetail);
                 
                 
                // 入账信息表   账务明细表、账务明细代码表调用共通
         		InterfProductDealBookCommonDto dealAccounting = interfCommonService.dealAccounting(valFinProductDeal, dto.getProdId());
         		listValDealAccounting.add(dealAccounting.getBokDealAccounting());
         		
         		BokDetailId bokDetailId = new BokDetailId();
         		bokDetailId.setBusiId(dto.getProdId());
         		bokDetailId.setBusiDetailType(finprodType);
         		String detailId =  interfCommonService.genMd5BokDetailId(bokDetailId);
         		
         		dealAccounting.getBokDealAccountingDetail().setBokDetailId(detailId);
         		listValDealAccountingDetail.add(dealAccounting.getBokDealAccountingDetail());
         		
         		dealAccounting.getBokDetailId().setBusiId(dto.getProdId());
         		dealAccounting.getBokDetailId().setBusiDetailType(finprodType);
         		dealAccounting.getBokDetailId().setBokDetailId(detailId);

                /**
                 * modify by weipeng on 20190104 start--增值税交易重新生成账务明细代码缺少四级科目号及名称
                 **/
                dealAccounting.getBokDetailId().setDetailSubjectNoTail(dto.getProdId()+
                        FinalString.LAYERING_SPLIT+
                        finprodType);
                // 获取金融产品名称
                TargetFinProduct targetFinProduct = interfCommonService.getTargetFinProductByCondition(dto.getProdId(),finprodType);
                if(targetFinProduct != null){
                    dealAccounting.getBokDetailId().setDetailSubnameTail(targetFinProduct.getFinprodName());
                }
                /**modify by weipeng on 20190104 end**/

         		listValBokDetailId.add(dealAccounting.getBokDetailId());
         		        
         		//结算指令
         		Map<String, Object> map = interfCommonService.instructionGenerate(valFinProductDeal, 
         				dto.getProdId(), dealAccounting.getAcctSubjectType(),null);
         		if(map.get("ORDERS")!=null){
         			valOrdersList.addAll((List<StlOrders>)map.get("ORDERS"));
         		}
         		if(map.get("ORDERS_EXT")!=null){
         			valOrdersExtList.addAll((List<StlOrdersExt>)map.get("ORDERS_EXT"));
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
            else{
            	
            	/**1.通道产品增值税交易**/
            	String chlProdId = dto.getProdId() + FinalString.LAYERING_SPLIT + dto.getChannelId();
            	String finprodType = DictCodeListExt1.金融产品类型_通道.getValue();
                ValBooksetInfoDto valBooksetInfoDto = interfCommonService.getBooksetInfoByProdId(chlProdId);
                if(valBooksetInfoDto != null){
                	finprodType = valBooksetInfoDto.getAcctSubjectType();
                }
            	
                String chlFinprodId = InterfHelper.getMd5CashId_(chlProdId,
                		DictCodeListExt1.金融产品类型_通道.getValue(),DictCodeListExt1.现金流二级类型_税额.getValue());
                // 金融产品交易(通道产品增值税交易)
                TrdProductDeal chlFinProductDeal = this.getValFinProductDeal(dto,finprodType);
                chlFinProductDeal.setFinprodId(chlFinprodId);
                chlFinProductDeal.setChlId(dto.getChannelId());
                chlFinProductDeal.setTotleTradeId(chlFinProductDeal.getTradeId());
                listValFinProductDeal.add(chlFinProductDeal);
                
                // 估值核算交易扩展表 (通道产品增值税交易)
                TrdProductDealAdd chlFinProductDealExt = this.getValFinProductDealExt(dto,chlFinProductDeal);
//                chlFinProductDealExt.setProdId(chlProdId);
                chlFinProductDealExt.setPortfolioId(chlProdId);
                listValFinProductDealExt.add(chlFinProductDealExt);
                
                // 交易费用明细表(通道产品增值税交易)
                List<TrdTradeFeeDetail> chlListfeeDetail = this.getValTradeFeeDetail(dto,chlFinProductDeal);
                listValTradeFeeDetail.addAll(chlListfeeDetail);
                
                // 入账信息表   账务明细表、账务明细代码表调用共通
         		InterfProductDealBookCommonDto dealAccounting = interfCommonService.dealAccounting(chlFinProductDeal, dto.getProdId());
         		listValDealAccounting.add(dealAccounting.getBokDealAccounting());
         		
         		BokDetailId bokDetailId = new BokDetailId();
         		bokDetailId.setBusiId(chlProdId);
         		bokDetailId.setBusiDetailType(dealAccounting.getAcctSubjectType());
         		String detailId =  interfCommonService.genMd5BokDetailId(bokDetailId);
         		
         		dealAccounting.getBokDealAccountingDetail().setBokDetailId(detailId);
         		listValDealAccountingDetail.add(dealAccounting.getBokDealAccountingDetail());
         		
         		dealAccounting.getBokDetailId().setBusiId(chlProdId);
         		dealAccounting.getBokDetailId().setBusiDetailType(finprodType);
         		dealAccounting.getBokDetailId().setBokDetailId(detailId);

                /**
                 * modify by weipeng on 20190104 start--增值税交易重新生成账务明细代码缺少四级科目号及名称
                 **/
                dealAccounting.getBokDetailId().setDetailSubjectNoTail(chlProdId);
                dealAccounting.getBokDetailId().setDetailSubnameTail(dealAccounting.getBooksetName());
                /**modify by weipeng on 20190104 end**/

         		listValBokDetailId.add(dealAccounting.getBokDetailId());
         		        
         		//结算指令
         		Map<String, Object> map = interfCommonService.instructionGenerate(chlFinProductDeal, 
         				dto.getProdId(), dealAccounting.getAcctSubjectType(),null);
         		if(map.get("ORDERS")!=null){
         			valOrdersList.addAll((List<StlOrders>)map.get("ORDERS"));
         		}
         		if(map.get("ORDERS_EXT")!=null){
         			valOrdersExtList.addAll((List<StlOrdersExt>)map.get("ORDERS_EXT"));
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
                
         		/**2.通道产品申赎交易**/
                String detailSubnameTail = null;
         	    // 金融产品交易(通道产品申赎)
         		TrdProductDeal chlRedFinProductDeal = this.getValFinProductDealTD(dto); 
         		chlRedFinProductDeal.setTotleTradeId(chlFinProductDeal.getTradeId());
                listValFinProductDeal.add(chlRedFinProductDeal);
                
                // 估值核算交易扩展表(通道产品申赎)
                TrdProductDealAdd chlRedFinProductDealExt = this.getValFinProductDealExt(dto,chlRedFinProductDeal);
//                chlRedFinProductDealExt.setProdId(chlProdId);
                chlRedFinProductDealExt.setPortfolioId(chlProdId);
                listValFinProductDealExt.add(chlRedFinProductDealExt);
                           
                // 入账信息表   账务明细表、账务明细代码表调用共通
         		dealAccounting = interfCommonService.dealAccounting(chlRedFinProductDeal, dto.getProdId());
         		listValDealAccounting.add(dealAccounting.getBokDealAccounting());
         		
         		bokDetailId = new BokDetailId();
         		bokDetailId.setBusiId(chlProdId);
         		bokDetailId.setBusiDetailType(dealAccounting.getAcctSubjectType());
         		detailId =  interfCommonService.genMd5BokDetailId(bokDetailId);
         		
         		dealAccounting.getBokDealAccountingDetail().setBokDetailId(detailId);
         		listValDealAccountingDetail.add(dealAccounting.getBokDealAccountingDetail());
         		
         		dealAccounting.getBokDetailId().setBusiId(chlProdId);
         		dealAccounting.getBokDetailId().setBusiDetailType(dealAccounting.getAcctSubjectType());
         		dealAccounting.getBokDetailId().setBokDetailId(detailId);

                /**add by wujie on 20190625 start**/
                //四级科目尾号
                dealAccounting.getBokDetailId().setDetailSubjectNoTail(chlRedFinProductDeal.getFinprodId());
                //四级科目尾号名称
                detailSubnameTail = dealAccounting.getBooksetName();
                dealAccounting.getBokDetailId().setDetailSubnameTail(detailSubnameTail);
                /**add by wujie on 20190625 end**/

                listValBokDetailId.add(dealAccounting.getBokDetailId());
         		        
         		//结算指令
         		map = interfCommonService.instructionGenerate(chlRedFinProductDeal, 
         				dto.getProdId(), dealAccounting.getAcctSubjectType(),null);
         		if(map.get("ORDERS")!=null){
         			valOrdersList.addAll((List<StlOrders>)map.get("ORDERS"));
         		}
         		if(map.get("ORDERS_EXT")!=null){
         			valOrdersExtList.addAll((List<StlOrdersExt>)map.get("ORDERS_EXT"));
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
            	prdTrdProductDeal.setTotleTradeId(chlFinProductDeal.getTradeId());
            	listValFinProductDeal.add(prdTrdProductDeal);
                
            	// 估值核算交易扩展表(理财产品买虚拟通道资产)
                TrdProductDealAdd prdFinProductDealExt = this.getValFinProductDealExt(dto, prdTrdProductDeal);
                listValFinProductDealExt.add(prdFinProductDealExt);
                
                // 入账信息表   账务明细表、账务明细代码表调用共通
         		dealAccounting = interfCommonService.dealAccounting(prdTrdProductDeal, dto.getProdId());
         		listValDealAccounting.add(dealAccounting.getBokDealAccounting());
         		listValDealAccountingDetail.add(dealAccounting.getBokDealAccountingDetail());

                /**add by wujie on 20190625 start**/
                //四级科目尾号
                dealAccounting.getBokDetailId().setDetailSubjectNoTail(prdTrdProductDeal.getFinprodId());
                //四级科目尾号名称
                dealAccounting.getBokDetailId().setDetailSubnameTail(detailSubnameTail);
                /**add by wujie on 20190625 end**/

                listValBokDetailId.add(dealAccounting.getBokDetailId());
         		        
         		//结算指令
         		map = interfCommonService.instructionGenerate(prdTrdProductDeal,dto.getProdId(), dealAccounting.getAcctSubjectType(),null);
         		if(map.get("ORDERS")!=null){
         			valOrdersList.addAll((List<StlOrders>)map.get("ORDERS"));
         		}
         		if(map.get("ORDERS_EXT")!=null){
         			valOrdersExtList.addAll((List<StlOrdersExt>)map.get("ORDERS_EXT"));
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
        resultMap.put("valOrdersList", valOrdersList);
        resultMap.put("valOrdersExtList", valOrdersExtList);
        resultMap.put("ValFinProductDeal", listValFinProductDeal);
        resultMap.put("ValFinProductDealExt", listValFinProductDealExt);
        resultMap.put("ValDealAccounting", listValDealAccounting);
        resultMap.put("ValDealAccountingDetail", listValDealAccountingDetail);
        resultMap.put("ValTradeFeeDetail", listValTradeFeeDetail);             
        resultMap.put("ValBokDetailId", listValBokDetailId);
        
        valInterflogList = interfCommonService.filterValInterflogList(valInterflog, bookSetIds, valModel);
    	resultMap.put("valInterflogList", valInterflogList);
        
        return resultMap;
    }

    /**
     * T1:金融产品交易表(通道产品增值税交易)
     * @param dto 增值税交易
     * @return 金融产品交易表
     */
    private TrdProductDeal getValFinProductDeal(ValAddValueTaxDeal dto,String finProdType) {
        // 金融产品交易
        TrdProductDeal valFinProductDeal = new TrdProductDeal();
        //定义编号
        String tradeId=interfCommonService.getTradeid(dto.getPs(),dto.getTradeDate(),"A");
        // 交易编号 = 共通生成
        valFinProductDeal.setTradeId(tradeId);
        // 业务类型 = A.业务类型
        valFinProductDeal.setBusiType(dto.getBusiType());
        
        // 金融产品代码 = 调用共通方法设计，现金流代码查询
        String cashId = InterfHelper.getMd5CashId_(dto.getProdId(),finProdType, DictCodeListExt1.现金流二级类型_税额.getValue());
        valFinProductDeal.setFinprodId(cashId);
        
        // 金融产品类型 = 固定值（数据字典 金融产品类型.税额）
        valFinProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_税额.getValue());
        
        // 分支序号 = 设置全局变量(默认为0）
        valFinProductDeal.setBranch(InterfCommonConstant.分支序号_默认);
        
        // 交易日 = A.交易日
        valFinProductDeal.setTradeDate(dto.getTradeDate());
        
        // 起息日 = A.起息日
        valFinProductDeal.setVdate(dto.getVdate());
        
        // 到期日= A.到期日
        valFinProductDeal.setMdate(dto.getMdate());
        
        // 交割日= A.交割日
        valFinProductDeal.setSettleDate(dto.getSettdateDate());
        
        // 交易币种= A.交易币种
        valFinProductDeal.setCcy(dto.getCcy());
        
        // 交易金额= A.交易金额
        valFinProductDeal.setTradeAmt(dto.getDealAmt());
        
        // 交易方向= A.交易方向
        valFinProductDeal.setPs(dto.getPs());
        
        // 交易状态 = A.数据动作标志
        valFinProductDeal.setTradeStatus(dto.getDataActionSign());
        
        // 是否结清 = 固定值：设置默认值
        valFinProductDeal.setIsClean(InterfCommonConstant.是否结清_默认);
        
        // 关联系统交易编号 = A.成交编号
        valFinProductDeal.setfTradeId(dto.getTradeId());
        if(valFinProductDeal.getTradeStatus().equals(DictCodeListExt1.交易状态_撤销.getValue())){
        	valFinProductDeal.setpTradeId(interfCommonService.getFTradeId(valFinProductDeal));
        }
        // 创建时间 = 系统时间
        valFinProductDeal.setCreateTime(dto.getCreateTime());
        
        // 更新时间= 系统时间
        valFinProductDeal.setUpdateTime(dto.getCreateTime());
        
        return valFinProductDeal;
    }
    
    /**
     * T2:估值核算交易扩展表 接收
     * 
     * @param dto 增值税交易
     * @param t1 t1 
     * @return 估值核算交易扩展表
     */
    private TrdProductDealAdd getValFinProductDealExt(ValAddValueTaxDeal dto,TrdProductDeal t1) {
        // 估值核算交易扩展表
    	TrdProductDealAdd valFinProductDealExt = new TrdProductDealAdd();
        
        // 交易编号 = T1.交易编号
        valFinProductDealExt.setTradeId(t1.getTradeId());
        
        //理财产品代码=A.产品代码
        /** modify by wujie on 20181115 start remark:投资组合投资时应赋值[表.投资组合代码]字段
        valFinProductDealExt.setProdId(dto.getProdId());
        **/
//        if(DictCodeListExt1.金融产品类型_投资组合.getValue().equals(finprodType)){
        	valFinProductDealExt.setPortfolioId(dto.getProdId());
//        }else{
////        	valFinProductDealExt.setProdId(dto.getProdId());
//        }
        /**modify by wujie on 20181115 end remark:投资组合投资时应赋值[表.投资组合代码]字段**/
        
        
        // 是否有结算指令 = A.有无清算指令
//        valFinProductDealExt.setHasOrder(dto.getIsClean());
        
        //生成方式= 接口接入
        valFinProductDealExt.setGenType(com.joyintech.tams.constant.DictCodeListExt1.生成方式_接口接入.getValue());
        
        // 创建时间 = 系统时间
        valFinProductDealExt.setCreateTime(dto.getCreateTime());
        
        // 更新时间= 系统时间
        valFinProductDealExt.setUpdateTime(dto.getCreateTime());
        
        return valFinProductDealExt;
    }

    /**
     * T3:交易费用明细表 接收
     * 
     * @param dto 增值税交易
     * @param t1 t1
     * @return 交易费用明细表
     */
    private List<TrdTradeFeeDetail> getValTradeFeeDetail(ValAddValueTaxDeal dto,
                                                        TrdProductDeal t1) {
        List<TrdTradeFeeDetail> listValTradeFeeDetail = new ArrayList<TrdTradeFeeDetail>();

        Map<String,BigDecimal> map = new HashMap<String,BigDecimal>();        
        //利息收入税
        map.put(DictCodeList.税类型_利息收入.getValue(), dto.getIntTaxAmt());
        //红利收入税
        map.put(DictCodeList.税类型_红利收入.getValue(), dto.getDivTaxAmt());
        //价差收入税
        map.put(DictCodeList.税类型_价差收入.getValue(), dto.getSprTaxAmt());
        //城建税附加
        map.put(DictCodeList.税类型_城建税.getValue(), dto.getAddTaxAmt());
        //教育费附加
        map.put(DictCodeList.税类型_教育费.getValue(), dto.getEduTaxAmt());
        //地方教育费附加
        map.put(DictCodeList.税类型_地方教育费.getValue(), dto.getAleTaxAmt());
        //水利基金税
        map.put(DictCodeList.税类型_水利基金税.getValue(), dto.getWcpTaxAmt());
        
        for (String key : map.keySet()) {
            BigDecimal amt = map.get(key);
            if(amt==null){
                continue;
            }      
            TrdTradeFeeDetail valTradeFeeDetail = new TrdTradeFeeDetail();
            // 交易编号 = T1.交易编号
            valTradeFeeDetail.setTradeId(t1.getTradeId());
            
            // 一次性费用类型= 固定值 数据字典税类型
            valTradeFeeDetail.setFeeType(key); 
            
            // 是否随交易支付 = 固定值：Y 是 默认随交易支付    
            valTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_是.getValue());  
            
            // 费用代码= 固定值 数据字典税类型
            valTradeFeeDetail.setFeeId(key);
            
            //费用金额
            valTradeFeeDetail.setFeeAmt(amt);
            
                       
            // 创建时间 = 系统时间
            valTradeFeeDetail.setCreateTime(dto.getCreateTime()); 
            
            // 更新时间= 系统时间
            valTradeFeeDetail.setUpdateTime(dto.getCreateTime());
            
            listValTradeFeeDetail.add(valTradeFeeDetail);
        }
        
        return listValTradeFeeDetail;
    }
    

    /**
     * T1:金融产品交易表(通道产品申赎)
     * @param dto 增值税交易
     * @return 金融产品交易表
     */
    private TrdProductDeal getValFinProductDealTD(ValAddValueTaxDeal dto) {
        // 金融产品交易
        TrdProductDeal valFinProductDeal = new TrdProductDeal();
        
        // 业务类型 = A.业务类型
        valFinProductDeal.setBusiType(DictCodeList.业务类型_货币型TA交易.getValue());
        
        // 金融产品代码 = A.产品代码+"_"+A.通道代码
        valFinProductDeal.setFinprodId(dto.getProdId()+FinalString.LAYERING_SPLIT+dto.getChannelId());
        
        // 金融产品类型 = 固定值（数据字典 金融产品类型.通道）
        valFinProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_通道.getValue());
        
        // 分支序号 = 设置全局变量(默认为0）
        valFinProductDeal.setBranch(InterfCommonConstant.分支序号_默认);
        
        // 交易日 = A.交易日
        valFinProductDeal.setTradeDate(dto.getTradeDate());
        
        // 交割日= A.交割日
        valFinProductDeal.setSettleDate(dto.getSettdateDate());
        
        // 交易币种= A.交易币种
        valFinProductDeal.setCcy(dto.getCcy());
        
        // 交易金额= A.交易金额
        valFinProductDeal.setTradeAmt(dto.getDealAmt());
        
        // 交易份额 = A.实付费用
        valFinProductDeal.setShareAmt(dto.getDealAmt());
        
        // 交易本金 = A.实付费用
        valFinProductDeal.setPrinAmt(dto.getDealAmt());
        
        //交易方向
        if(DictCodeList.交易方向_税费缴税.getValue().equals(dto.getPs())){
        	valFinProductDeal.setPs(DictCodeList.交易方向_产品申购.getValue());
        } else if(DictCodeList.交易方向_税费退税.getValue().equals(dto.getPs())) {
        	valFinProductDeal.setPs(DictCodeList.交易方向_产品赎回.getValue());
        }
        
        // 交易状态 = A.数据动作标志
        valFinProductDeal.setTradeStatus(dto.getDataActionSign());
        
        // 是否结清 = 固定值：设置默认值
        valFinProductDeal.setIsClean(InterfCommonConstant.是否结清_默认);
        
        // 关联系统交易编号 = A.成交编号
        valFinProductDeal.setfTradeId(dto.getTradeId());
        
         //定义编号
        String tradeId=interfCommonService.getTradeid(valFinProductDeal.getPs(),dto.getTradeDate(),"A");
        
        // 交易编号 = 共通生成
        valFinProductDeal.setTradeId(tradeId);
        
        if(valFinProductDeal.getTradeStatus().equals(DictCodeListExt1.交易状态_撤销.getValue())){
        	valFinProductDeal.setpTradeId(interfCommonService.getFTradeId(valFinProductDeal));
        }
        // 创建时间 = 系统时间
        valFinProductDeal.setCreateTime(dto.getCreateTime());
        
        //通道代码
        valFinProductDeal.setChlId(dto.getChannelId());
        
        return valFinProductDeal;
    }

    
    /**
     * 金融产品交易(理财产品买虚拟通道资产)
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private TrdProductDeal getValFinProductDealPRD(ValAddValueTaxDeal dto) {
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
        //交易方向
        if(DictCodeList.交易方向_税费缴税.getValue().equals(dto.getPs())){
        	trdProductDeal.setPs(DictCodeList.交易方向_申购确认.getValue());
        } else if(DictCodeList.交易方向_税费退税.getValue().equals(dto.getPs())) {
        	trdProductDeal.setPs(DictCodeList.交易方向_赎回确认.getValue());
        }
        // 交易状态 = A.数据动作标志--> 新增 --> 01:初始    撤销 --> 03:撤销
        trdProductDeal.setTradeStatus(dto.getDataActionSign());
        
        // 是否结清 = 固定值 (设置默认值)
        trdProductDeal.setIsClean(InterfCommonConstant.是否结清_默认);
        
        // 关联系统交易编号 = A.成交编号
        trdProductDeal.setfTradeId(dto.getTradeId());
        
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
}




