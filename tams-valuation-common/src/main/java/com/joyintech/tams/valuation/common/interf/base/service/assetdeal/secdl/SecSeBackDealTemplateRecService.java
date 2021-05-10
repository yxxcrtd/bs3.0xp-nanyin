package com.joyintech.tams.valuation.common.interf.base.service.assetdeal.secdl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.ArithmeticUtil;
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
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.FinalString;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.AssetDealInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.ChannelFeeInfoDeal;
import com.joyintech.tams.valuation.common.interf.base.dto.InterfProductDealBookCommonDto;
import com.joyintech.tams.valuation.common.interf.base.dto.TargetFinProduct;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSeclendAssetDeal;

/**
 * 债券借贷标的劵卖出和回购 模板处理Service(VAL_SECLEND_ASSET_DEAL)
 * 
 * @author dongxiangyang
 * @version 1.0 2018年10月29日
 * @see SecSeBackDealTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SecSeBackDealTemplateRecService {
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
     * 债券借贷标的劵卖出和购回 接口模板处理
     * 
     * @param templateSourceFlgDTO 文件来源和类型
     * @param acctEntity 计提基础信息DTO
     * @return 债券买卖交易 接口模板处理返回共通结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public AssetDealInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
            TemplateOrderInfoReaderResult acctEntity){
    	// 定义返回变量
        AssetDealInfoValCommonResult result = new AssetDealInfoValCommonResult();
        List<ValSeclendAssetDeal> listValSeclendAssetDeal=new ArrayList<ValSeclendAssetDeal>(); 
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        Map<String,Object> params = acctEntity.getParams();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        listValSeclendAssetDeal=returnResult.getT(ValSeclendAssetDeal.class);
        //获取数据数量
        result.setDataNum(listValSeclendAssetDeal.size());

		/**add by wujie on 20210506 start**/
		if(returnResult.isReadFile()){
			result.getParamMap().put(InterfCommonConstant.读取文件状态,DictCodeList.是否_是.getValue());
		}
		/**add by wujie on 20210506 end**/

         // 2、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleValSeclendAssetDeal(listValSeclendAssetDeal,acctEntity.getResovleDate(),params,returnResult.getValInterflog());
        if(map != null){
            // 金融产品交易List
            List<TrdProductDeal> listValFinProductDeal=(List<TrdProductDeal>)map.get("listValFinProductDeal");
            result.setListValFinProductDeal(listValFinProductDeal);
            // 估值核算交易扩展表List
            List<TrdProductDealAdd> listValFinProductDealExt=(List<TrdProductDealAdd>)map.get("listValFinProductDealExt");
            result.setListValFinProductDealExt(listValFinProductDealExt);
            // 入账信息表List
            List<BokDealAccounting> listValDealAccounting=(List<BokDealAccounting>)map.get("listValDealAccounting");
            result.setListValDealAccounting(listValDealAccounting);
            //账务明细list
            List<BokDealAccountingDetail> listValDealAccountingDetail=(List<BokDealAccountingDetail>)map.get("listValDealAccountingDetail");
            result.setListValDealAccountingDetail(listValDealAccountingDetail);
            //账务明细代码表
            List<BokDetailId> listValBokDetailId=(List<BokDetailId>)map.get("listValBokDetailId");
            result.setListValBokDetailId(listValBokDetailId);
            //交易费用明细表
            List<TrdTradeFeeDetail> listValTradeFeeDetail = (List<TrdTradeFeeDetail>)map.get("listValTradeFeeDetail");
            result.setListValTradeFeeDetail(listValTradeFeeDetail);
            //结算指令
            List<StlOrders> valOrdersList=(List<StlOrders>)map.get("listValOrders");
            result.setListValOrders(valOrdersList);
            //结算指令拓展表list
            List<StlOrdersExt> valOrdersExtList=(List<StlOrdersExt>)map.get("listValOrdersExt");
            result.setListValOrdersExt(valOrdersExtList);

			// 添加日志
			result.setValInterflog((List<ValInterflog>)map.get("valInterflogList"));
        }


    	return  result;
    }
    
    /**
     * 02-债券借贷标的劵卖出与购回
     * 
     * @param listValSeclendAssetPeriodDeal 
     * @return 金融产品交易List + 估值核算交易扩展表List + 入账信息表List + 账务明细表List 
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
	private  Map<String, Object> handleValSeclendAssetDeal(List<ValSeclendAssetDeal> listValSeclendAssetDeal,Date resovleDate,Map<String,Object> params,
			List<ValInterflog> valInterflog){
        List<String> bookSetIds = (List<String>)params.get(InterfCommonConstant.接口_运行接口账套ID);
    	String valModel = (String)params.get(InterfCommonConstant.接口模式);
    	Map<String, Object> resultMap=new HashMap<>();
    	// 金融产品交易List
  	     List<TrdProductDeal> listValFinProductDeal = new ArrayList<TrdProductDeal>();
  	     // 估值核算交易扩展表List
  	     List<TrdProductDealAdd> listValFinProductDealExt = new ArrayList<TrdProductDealAdd>();
  	     // 入账信息表List
  	     List<BokDealAccounting> listValDealAccounting = new ArrayList<BokDealAccounting>();
  	     // 账务明细表List
  	     List<BokDealAccountingDetail> listValDealAccountingDetail = new ArrayList<BokDealAccountingDetail>();
  	     //账务明细代码表
  	     List<BokDetailId> listValBokDetailId=new ArrayList<BokDetailId>();
  	     // 交易费用明细表List
         List<TrdTradeFeeDetail> listValTradeFeeDetail = new ArrayList<TrdTradeFeeDetail>();
  	     //结算指令
  	     List<StlOrders> listValOrders = new ArrayList<StlOrders>();
  	     List<StlOrdersExt> listValOrdersExt = new ArrayList<StlOrdersExt>();
         List<ValInterflog> valInterflogList =  new ArrayList<>();
         
         /**add by wujie on 20190506 start remark:设置四级科目尾号**/
         Map<String,TargetFinProduct> assetMap = new HashMap<String,TargetFinProduct>();
         /**add by wujie on 20190506 end remark:设置四级科目尾号**/
         
    	 for(ValSeclendAssetDeal dto:listValSeclendAssetDeal){
         	if(StringUtil.equals(InterfCommonConstant.接口模式_指定账套,valModel) && !bookSetIds.contains(dto.getProdId())){
         		continue;
         	}
    		 dto.setCreateTime(resovleDate);
    		 //金融产品交易
  	    	  TrdProductDeal valFinProductDeal=getValFinProductDeal(dto);
  	    	  listValFinProductDeal.add(valFinProductDeal);
	    	 
	    	 // 交易费用明细表
	    	 List<TrdTradeFeeDetail> listfeeDetail = this.getValTradeFeeDetail(dto,valFinProductDeal);
	         listValTradeFeeDetail.addAll(listfeeDetail);
	    	 
	         // 入账信息表   账务明细表、账务明细代码表调用共通
   	         InterfProductDealBookCommonDto dealAccounting = interfCommonService.dealAccounting(valFinProductDeal, dto.getProdId());
   	         listValDealAccounting.add(dealAccounting.getBokDealAccounting());
   	         listValDealAccountingDetail.add(dealAccounting.getBokDealAccountingDetail());
   	         
   	         // 估值核算交易扩展表
   	      TrdProductDealAdd TrdProductDealExt=getProductDealExt(dto,valFinProductDeal,dealAccounting.getAcctSubjectType());
	    	 listValFinProductDealExt.add(TrdProductDealExt);
   	         
   	         BokDetailId valBokDetailId = dealAccounting.getBokDetailId();
   	         
   	         /**add by wujie on 20190506 start remark:设置四级科目尾号**/
   	         TargetFinProduct item  = assetMap.get(dto.getSecId());
   	         if(item ==null){
   	        	item = interfCommonService.getTargetFinProduct(dto.getSecId());
   	         }
   	         if(item != null){
   	        	 //四级科目尾号
   	             valBokDetailId.setDetailSubjectNoTail(item.getFinprodId());
   	             //四级科目尾号名称
   	             valBokDetailId.setDetailSubnameTail(item.getFinprodAbbr());
   	             assetMap.put(dto.getSecId(), item);
   	         }
             /**add by wujie on 20190506 end remark:设置四级科目尾号**/
   	         
   	         listValBokDetailId.add(valBokDetailId);
   	         
   	         //结算指令
   	         /**
   	         BokDealAccounting valDealAccounting = dealAccounting.getBokDealAccounting();
   	         Map<String, Object> map = this.getvalOrders(valFinProductDeal,dto.getProdId(),valDealAccounting.getBooksetId(),dealAccounting.getAcctSubjectType());
	   	     if(map.get("ORDERS") != null){
				List<StlOrders> ValOrders=(List<StlOrders>)map.get("ORDERS");
				listValOrders.addAll(ValOrders);
			  }
	          if(map.get("ORDERS_EXT")!=null){
	          	List<StlOrdersExt> ValOrdersExt=(List<StlOrdersExt>)map.get("ORDERS_EXT");
	          	listValOrdersExt.addAll(ValOrdersExt);
	          }
	          if(map.get("ACCOUNTING")!=null){
	          	listValDealAccounting.addAll((List<BokDealAccounting>)map.get("ACCOUNTING"));
	          }
	          if(map.get("ACCOUNTING_DETAI")!=null){
	          	listValDealAccountingDetail.addAll((List<BokDealAccountingDetail>)map.get("ACCOUNTING_DETAI"));
	          }
	          if(map.get("BOK_DETAIL_ID")!=null){
	          	listValBokDetailId.addAll((List<BokDetailId>)map.get("BOK_DETAIL_ID"));
	          }**/
	   	      StlOrders stlOrders = this.getStlOrders(dto,valFinProductDeal);
	          Map<String, Object> map = interfCommonService.instructionGenerate_Interf(valFinProductDeal,stlOrders, 
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
	          /**modify by wujie on 20190118 end**/
	          
	          /**add by wujie on 20190322 start reamrk:通道投资费用交易生成**/
	            ChannelFeeInfoDeal channelFeeInfoDeal = this.getChannelFeeInfoDeal(dto, dealAccounting.getAcctSubjectType());
	            if(channelFeeInfoDeal != null){
	                Map<String,Object> feedealMap = interfCommonService.assetChlFeeLend(valFinProductDeal, channelFeeInfoDeal);
	                
	                if(feedealMap.get("TrdProductDeal")!=null){
	                    listValFinProductDeal.add((TrdProductDeal)feedealMap.get("TrdProductDeal"));
	                }
	                
	                if(feedealMap.get("TrdProductDealExt")!=null){
	                    listValFinProductDealExt.add((TrdProductDealAdd)feedealMap.get("TrdProductDealExt"));
	                }
	                
	                if(feedealMap.get("TgTradeFeeDetail")!=null){
	                    listValTradeFeeDetail.add((TrdTradeFeeDetail)feedealMap.get("TgTradeFeeDetail"));
	                }
	                
	                if(feedealMap.get("BokDealAccounting_List")!=null){
	                    listValDealAccounting.addAll((List<BokDealAccounting>)feedealMap.get("BokDealAccounting_List"));
	                }
	                
	                if(feedealMap.get("BokDealAccountingDetail_List")!=null){
	                    listValDealAccountingDetail.addAll(( List<BokDealAccountingDetail>)feedealMap.get("BokDealAccountingDetail_List"));
	                }
	                
	                if(feedealMap.get("BokDetailId_list")!=null){
	                    listValBokDetailId.addAll((List<BokDetailId>)feedealMap.get("BokDetailId_list"));
	                }
	                
	                if(feedealMap.get("StlOrders_List")!=null){
	                    listValOrders.addAll((List<StlOrders>)feedealMap.get("StlOrders_List"));
	                }
	                if(feedealMap.get("StlOrdersExt_List")!=null){
	                    listValOrdersExt.addAll((List<StlOrdersExt>)feedealMap.get("StlOrdersExt_List"));
	                }
	            }
	            /**add by wujie on 20190322 end reamrk:通道投资费用交易生成**/
    	 }
    	 resultMap.put("listValFinProductDeal", listValFinProductDeal);
         resultMap.put("listValFinProductDealExt", listValFinProductDealExt);
         resultMap.put("listValTradeFeeDetail",listValTradeFeeDetail);
         resultMap.put("listValDealAccounting", listValDealAccounting);
         resultMap.put("listValDealAccountingDetail", listValDealAccountingDetail);
         resultMap.put("listValBokDetailId", listValBokDetailId);
         resultMap.put("listValOrders", listValOrders);
         resultMap.put("listValOrdersExt", listValOrdersExt);
         
         valInterflogList = interfCommonService.filterValInterflogList(valInterflog, bookSetIds, valModel);
     	 resultMap.put("valInterflogList", valInterflogList);
    	
    	return  resultMap;
    	
    }
	
    /**
     * 接收 结算指令信息
     * @param dto
     * @return
     */
    private StlOrders getStlOrders(ValSeclendAssetDeal dto, TrdProductDeal valFinProductDeal){
    	StlOrders result = new StlOrders();
		//modify by yanya on 20210204 判定为空包含空字符
		//if(dto.getMyAcctNo() == null && dto.getCounterAcctNo() == null){
		if(StringUtil.isEmpty(dto.getMyAcctNo()) && StringUtil.isEmpty(dto.getCounterAcctNo()) ){
    		return null;
    	}
    	result.setMyAcctNo(dto.getMyAcctNo());
    	result.setCounterAcctNo(dto.getCounterAcctNo());
    	result.setSettleDate(dto.getFundSettleDate());
    	result.setSettleAmt(valFinProductDeal.getTradeAmt());
    	return result;
    }
    
    /**
     * 通道投资资产交易拆分_通道费用
     * add by wujie on 20190322 start
     */
    private ChannelFeeInfoDeal getChannelFeeInfoDeal(ValSeclendAssetDeal dto,String acctSubjectType){
        //接口.通道代码为空
        if(dto.getChlId() == null){
            return null;
        }
        //接口.通道费用空
        if(dto.getChlFeeAmt()==null || dto.getChlFeeAmt().compareTo(BigDecimal.ZERO)==0){
            return null;
        }
        ChannelFeeInfoDeal deal = new ChannelFeeInfoDeal();
        //通道费用 = 接口.通道费用
        deal.setChlFeeAmt(dto.getChlFeeAmt());
        //是否随交易支付 = 接口.通道费是否随交易支付
        deal.setIsPayWithTrade(dto.getIsChlFeeAmt());
        //通道代码 = 接口.通道代码
        deal.setChannelId(dto.getChlId());
        //产品代码 = 接口.产品代码
        deal.setProdId(dto.getProdId());
        
        deal.setAcctSubjectId(dto.getProdId() + FinalString.LAYERING_SPLIT + dto.getChlId());
        
        deal.setAcctSubjectType(acctSubjectType);
        
        return deal;
    }
    
    /**
     * T1:金融产品交易表 接收
     * 
     * @param dto 01-借贷交易
     * @return 金融产品交易表
     * @see
     * @since 1.0
     */
    private  TrdProductDeal getValFinProductDeal(ValSeclendAssetDeal dto){
    	TrdProductDeal trdProductDeal=new TrdProductDeal();
    	//业务类型=业务类型.债券借贷交易
    	trdProductDeal.setBusiType(DictCodeList.业务类型_债券借贷标的债券交易.getValue());
    	//交易编号=调用共通
    	String tradeId=interfCommonService.getTradeid(dto.getPs(), dto.getDealDate(), "A");
    	trdProductDeal.setTradeId(tradeId);
    	
    	//金融产品代码=C.债券ID
    	trdProductDeal.setFinprodId(dto.getSecId());
    	//金融产品类型= 数据字典:金融产品类型.债券
    	trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
    	
    	// 分支序号 = 设置全局变量(默认为0）
    	trdProductDeal.setBranch(InterfCommonConstant.分支序号_默认);
    	//交易日=C.交易日
    	trdProductDeal.setTradeDate(dto.getDealDate());
    	//交割日=C.交割日
    	trdProductDeal.setSettleDate(dto.getSettdateDate());
    	//交易币种=C.币种
    	trdProductDeal.setCcy(dto.getCcy());
    	//单位净价=C.净价
    	trdProductDeal.setUnitCprice(dto.getCprice());
    	//单位利息=C.应收利息
    	trdProductDeal.setUnitInt(dto.getUnitInt());
    	//单位全价=C.全价
    	trdProductDeal.setUnitFprice(dto.getFprice());
    	//单位面值=C.单位面值
    	trdProductDeal.setParValue(dto.getParValue());
    	//净价总额=C.净价总额
    	trdProductDeal.setCpriceAmt(dto.getCpriceAmt());
    	/**add by wujie on 20210508 start**/
		trdProductDeal.setPrinAmt(dto.getCpriceAmt());
		/**add by wujie on 20210508 end**/
    	//交易份额=C.成交数量

        /**modify by wujie on 20190516 start remark:交易份额 = 成交数量/100
        trdProductDeal.setShareAmt(dto.getDealNum());
        **/
        trdProductDeal.setShareAmt(ArithmeticUtil.divide(dto.getDealNum(), new BigDecimal(100),2));
        /**modify by wujie on 20190516 end**/
        
    	
    	//利息总额=C.应收利息总额
    	trdProductDeal.setIntAmt(dto.getIntAmt());
    	//全价总额=C.全价总额
    	trdProductDeal.setFpriceAmt(dto.getFpriceAmt());
    	//交易方向 = C.交易方向
    	trdProductDeal.setPs(dto.getPs());
    	
//    	//随交易支付费用
//    	if(dto.getIsStampDuty().equals(DictCodeList.是否_是.getValue())){
//    		trdProductDeal.setFeeAmtPay(ArithmeticUtil.doAdd(2,null,dto.getStampDuty(),trdProductDeal.getFeeAmtPay()));
//    	}else if(dto.getIsBrokerage().equals(DictCodeList.是否_是.getValue())){
//    		trdProductDeal.setFeeAmtPay(ArithmeticUtil.doAdd(2,null,dto.getBrokerage(),trdProductDeal.getFeeAmtPay()));
//    	}else if(dto.getIsCertfFee().equals(DictCodeList.是否_是.getValue())){
//    		trdProductDeal.setFeeAmtPay(ArithmeticUtil.doAdd(2,null,dto.getCertfFee(),trdProductDeal.getFeeAmtPay()));
//    	}else if(dto.getIsStlAmt().equals(DictCodeList.是否_是.getValue())){
//    		trdProductDeal.setFeeAmtPay(ArithmeticUtil.doAdd(2,null,dto.getStlAmt(),trdProductDeal.getFeeAmtPay()));
//    	}else if(dto.getIsCommission().equals(DictCodeList.是否_是.getValue())){
//    		trdProductDeal.setFeeAmtPay(ArithmeticUtil.doAdd(2,null,dto.getCommission(),trdProductDeal.getFeeAmtPay()));
//    	}else if(dto.getIsDealFeeAmt().equals(DictCodeList.是否_是.getValue())){
//    		trdProductDeal.setFeeAmtPay(ArithmeticUtil.doAdd(2,null,dto.getDealFeeAmt(),trdProductDeal.getFeeAmtPay()));
//    	}else if(dto.getIsStlFeeAmt().equals(DictCodeList.是否_是.getValue())) {
//    	    trdProductDeal.setFeeAmtPay(ArithmeticUtil.doAdd(2,null,dto.getStlFeeAmt(),trdProductDeal.getFeeAmtPay()));
//    	} else if (dto.getIsChlFeeAmt().equals(DictCodeList.是否_是.getValue())) {
//    	    trdProductDeal.setFeeAmtPay(ArithmeticUtil.doAdd(2,null,dto.getChlFeeAmt(),trdProductDeal.getFeeAmtPay()));
//    	}
//    	
//    	//交易金额 = C.交易金额  标的债券购回：A.交易金额+随交易支付费用  标的债券卖出：A.交易金额-随交易支付费用
//    	if(DictCodeList.交易方向_融入债券卖出.getValue().equals(dto.getPs())) {
//    	    trdProductDeal.setTradeAmt(ArithmeticUtil.subtract(dto.getTradeAmt(), trdProductDeal.getFeeAmtPay(),2,null));
//    	} else {
//            trdProductDeal.setTradeAmt(ArithmeticUtil.doAdd(2, null, dto.getTradeAmt(),trdProductDeal.getFeeAmtPay())); 
//    	}
//
//    	//未随交易支付费用 = C.未随交易支付费用
//    	trdProductDeal.setFeeAmtUnpay(dto.getFeeAmtUnpay());
//    	//总交易费用 = C.印花税+C.经手费+C.证管费+C.结算费+C.佣金+C.交易手续费+C.结算手续费+C.通道费
//    	trdProductDeal.setFeeAmt(ArithmeticUtil.doAdd(2, null,dto.getStampDuty(),
//    	    dto.getBrokerage(),dto.getCertfFee(),dto.getStlAmt(),dto.getCommission(),
//    	    dto.getDealFeeAmt(),dto.getStlFeeAmt(),dto.getChlFeeAmt()));
    	
    	//随交易支付费用
        BigDecimal feeAmtPay = BigDecimal.ZERO;
        
        //未随交易支付费用
        BigDecimal feeAmtUnpay = BigDecimal.ZERO;
        
        //总交易费用
        BigDecimal feeAmt = BigDecimal.ZERO;
    	
        //印花税
        if(DictCodeList.是否_是.getValue().equals(dto.getIsStampDuty())){
        	feeAmtPay = feeAmtPay.add(dto.getStampDuty()==null?BigDecimal.ZERO:dto.getStampDuty());
        }else{
        	feeAmtUnpay = feeAmtUnpay.add(dto.getStampDuty()==null?BigDecimal.ZERO:dto.getStampDuty());
        }
        feeAmt = feeAmt.add(dto.getStampDuty()==null?BigDecimal.ZERO:dto.getStampDuty());
        
        //经手费
        if(DictCodeList.是否_是.getValue().equals(dto.getIsBrokerage())){
        	feeAmtPay = feeAmtPay.add(dto.getBrokerage()==null?BigDecimal.ZERO:dto.getBrokerage());
        }else{
        	feeAmtUnpay = feeAmtUnpay.add(dto.getBrokerage()==null?BigDecimal.ZERO:dto.getBrokerage());
        }
        feeAmt = feeAmt.add(dto.getBrokerage()==null?BigDecimal.ZERO:dto.getBrokerage());
        
        //证管费
        if(DictCodeList.是否_是.getValue().equals(dto.getIsCertfFee())){
        	feeAmtPay = feeAmtPay.add(dto.getCertfFee()==null?BigDecimal.ZERO:dto.getCertfFee());
        }else{
        	feeAmtUnpay = feeAmtUnpay.add(dto.getCertfFee()==null?BigDecimal.ZERO:dto.getCertfFee());
        }
        feeAmt = feeAmt.add(dto.getCertfFee()==null?BigDecimal.ZERO:dto.getCertfFee());
        
        //结算费
        if(DictCodeList.是否_是.getValue().equals(dto.getIsStlAmt())){
        	feeAmtPay = feeAmtPay.add(dto.getStlAmt()==null?BigDecimal.ZERO:dto.getStlAmt());
        }else{
        	feeAmtUnpay = feeAmtUnpay.add(dto.getStlAmt()==null?BigDecimal.ZERO:dto.getStlAmt());
        }
        feeAmt = feeAmt.add(dto.getStlAmt()==null?BigDecimal.ZERO:dto.getStlAmt());
        
        //佣金
        if(DictCodeList.是否_是.getValue().equals(dto.getIsCommission())){
        	feeAmtPay = feeAmtPay.add(dto.getCommission()==null?BigDecimal.ZERO:dto.getCommission());
        }else{
        	feeAmtUnpay = feeAmtUnpay.add(dto.getCommission()==null?BigDecimal.ZERO:dto.getCommission());
        }
        feeAmt = feeAmt.add(dto.getCommission()==null?BigDecimal.ZERO:dto.getCommission());
        
        //交易手续费
        if(DictCodeList.是否_是.getValue().equals(dto.getIsDealFeeAmt())){
        	feeAmtPay = feeAmtPay.add(dto.getDealFeeAmt()==null?BigDecimal.ZERO:dto.getDealFeeAmt());
        }else{
        	feeAmtUnpay = feeAmtUnpay.add(dto.getDealFeeAmt()==null?BigDecimal.ZERO:dto.getDealFeeAmt());
        }
        feeAmt = feeAmt.add(dto.getDealFeeAmt()==null?BigDecimal.ZERO:dto.getDealFeeAmt());
        
        //结算手续费
        if(DictCodeList.是否_是.getValue().equals(dto.getIsStlFeeAmt())){
        	feeAmtPay = feeAmtPay.add(dto.getStlFeeAmt()==null?BigDecimal.ZERO:dto.getStlFeeAmt());
        }else{
        	feeAmtUnpay = feeAmtUnpay.add(dto.getStlFeeAmt()==null?BigDecimal.ZERO:dto.getStlFeeAmt());
        }
        feeAmt = feeAmt.add(dto.getStlFeeAmt()==null?BigDecimal.ZERO:dto.getStlFeeAmt());
        
        /** add by wujie on 20190322 start **/
        if (dto.getChlId() == null) {
            /** add by wujie on 20190322 end **/
            // 通道费
            if (DictCodeList.是否_是.getValue().equals(dto.getIsChlFeeAmt())) {
                feeAmtPay = feeAmtPay.add(dto.getChlFeeAmt() == null ? BigDecimal.ZERO : dto.getChlFeeAmt());
            }
            else {
                feeAmtUnpay = feeAmtUnpay.add(dto.getChlFeeAmt() == null ? BigDecimal.ZERO : dto.getChlFeeAmt());
            }
            feeAmt = feeAmt.add(dto.getChlFeeAmt() == null ? BigDecimal.ZERO : dto.getChlFeeAmt());
            /** add by wujie on 20190322 start **/
        }
        /** add by wujie on 20190322 end **/
        
        //随交易支付费用
        trdProductDeal.setFeeAmtPay(feeAmtPay);
        
        //未随交易支付费用
        trdProductDeal.setFeeAmtUnpay(feeAmtUnpay);
        
        //总交易费用
        trdProductDeal.setFeeAmt(feeAmt);

		if(DictCodeList.交易方向_融入债券卖出.getValue().equals(dto.getPs())){
        	trdProductDeal.setTradeAmt(ArithmeticUtil.subtract(dto.getFpriceAmt(),feeAmtPay));
        }else{
        	trdProductDeal.setTradeAmt(ArithmeticUtil.doAdd(2, null,dto.getFpriceAmt(),feeAmtPay));
        }
        /**modify by chendijun start **/
      //投资目的 = C.投资目的
        trdProductDeal.setInvAim(DictCodeListExt1.投资目的_卖空.getValue());
        /**modify by chendijun end **/
    	//交易状态 = C.数据动作标志
    	trdProductDeal.setTradeStatus(dto.getDataActionSign());

    	//通道代码=C.通道代码
    	trdProductDeal.setChlId(dto.getChlId());
    	//交易对手=C.交易对手
    	trdProductDeal.setCounterId(dto.getCounterId());
    	//交易对手类型=C.交易对手类型
//    	trdProductDeal.setCounterType(dto.getCounterType());
    	//交易场所=C.交易场所
    	trdProductDeal.setTradeMarket(dto.getTradeMarket());
    	//关联系统交易编号=C.成交编号
    	trdProductDeal.setfTradeId(dto.getTradeId());
    	//登记托管机构=C.登记计算机构
    	trdProductDeal.setRegistOrg(dto.getRegistAgency());
    	
    	trdProductDeal.setCreateTime(dto.getCreateTime());
    	
        //原交易编号: 交易状态为撤销时：原交易编号=调用共通设计->【详细设计-接口共通方法设计.xlsx】->撤销交易对应的原交易编号查询SHEET
        if(DictCodeListExt1.交易状态_撤销.getValue().equals(dto.getDataActionSign())) {
            trdProductDeal.setpTradeId(interfCommonService.getFTradeId(trdProductDeal));
        }
    	return trdProductDeal;
    }
    /**
     * T2:估值核算交易扩展表 接收
     * 
     * @param dto 01-债券借贷交易
     * @return 估值核算交易扩展表
     * @see
     * @since 1.0
     */
    private TrdProductDealAdd getProductDealExt(ValSeclendAssetDeal dto,TrdProductDeal valFinProductDeal,String finprodType){
    	TrdProductDealAdd trdProductDealExt=new TrdProductDealAdd();
    	//交易编号=T1.交易编号
    	trdProductDealExt.setTradeId(valFinProductDeal.getTradeId());
    	//理财产品代码=B.产品代码
    	/** modify by wujie on 20181115 start remark:投资组合投资时应赋值[表.投资组合代码]字段
        trdProductDealExt.setProdId(dto.getProdId());
        **/
    	trdProductDealExt.setPortfolioId(dto.getProdId());
//        if(DictCodeListExt1.金融产品类型_投资组合.getValue().equals(finprodType)){
//        	trdProductDealExt.setPortfolioId(dto.getProdId());
//        }else{
//        	trdProductDealExt.setProdId(dto.getProdId());
//        }
        /**modify by wujie on 20181115 end remark:投资组合投资时应赋值[表.投资组合代码]字段**/
    	//是否有结算指令
//    	trdProductDealExt.setHasOrder(dto.getIsClean());
    	//生成方式=固定值：接口接入
    	trdProductDealExt.setGenType(DictCodeListExt1.生成方式_接口接入.getValue());
    	return trdProductDealExt;
    }
    /**
     * T5: 交易费用明细表 接收
     * 
     * @param dto 03-债券标的劵卖出与回购
     * @param ValSeclendAssetDeal 
     * @return 交易费用明细表
     * @see
     * @since 1.0
     */
    private List<TrdTradeFeeDetail> getValTradeFeeDetail(ValSeclendAssetDeal dto,TrdProductDeal valFinProductDeal){
    	List<TrdTradeFeeDetail> listValTradeFeeDetail = new ArrayList<TrdTradeFeeDetail>();
        Map<String,BigDecimal> map = new HashMap<String,BigDecimal>();
        Map<String,String> mapIsPay = new HashMap<String,String>();
        //经手费
        map.put(DictCodeListExt1.一次性费用类型_经手费.getValue(), dto.getBrokerage());
        mapIsPay.put(DictCodeListExt1.一次性费用类型_经手费.getValue(), dto.getIsBrokerage());
        
        //印花税
        map.put(DictCodeListExt1.一次性费用类型_印花税.getValue(), dto.getStampDuty());
        mapIsPay.put(DictCodeListExt1.一次性费用类型_印花税.getValue(), dto.getIsStampDuty());
        
        //证管费
        map.put(DictCodeListExt1.一次性费用类型_证管费.getValue(), dto.getCertfFee());
        mapIsPay.put(DictCodeListExt1.一次性费用类型_证管费.getValue(), dto.getIsCertfFee());
        
        //结算费
        map.put(DictCodeListExt1.一次性费用类型_结算费.getValue(), dto.getStlAmt());
        mapIsPay.put(DictCodeListExt1.一次性费用类型_结算费.getValue(), dto.getIsStlAmt());
        
        //佣金
        map.put(DictCodeListExt1.一次性费用类型_佣金.getValue(), dto.getCommission());
        mapIsPay.put(DictCodeListExt1.一次性费用类型_佣金.getValue(), dto.getIsCommission());
        
        //交易手续费
        map.put(DictCodeListExt1.一次性费用类型_交易手续费.getValue(), dto.getDealFeeAmt());
        mapIsPay.put(DictCodeListExt1.一次性费用类型_交易手续费.getValue(), dto.getIsDealFeeAmt());
        
        //结算手续费
        map.put(DictCodeListExt1.一次性费用类型_结算手续费.getValue(), dto.getStlFeeAmt());
        mapIsPay.put(DictCodeListExt1.一次性费用类型_结算手续费.getValue(), dto.getIsStlFeeAmt());
        
        /** add by wujie on 20190322 start **/
        if (dto.getChlId() == null) {
            /** add by wujie on 20190322 end **/
            // 通道费
            map.put(DictCodeListExt1.一次性费用类型_通道费.getValue(), dto.getChlFeeAmt());
            mapIsPay.put(DictCodeListExt1.一次性费用类型_通道费.getValue(), dto.getIsChlFeeAmt());
            /** add by wujie on 20190322 start **/
        }
        /** add by wujie on 20190322 end **/
        
        for (String key : map.keySet()){
        	BigDecimal amt = map.get(key);
        	if(amt==null){
        		continue;
        	} 
        	TrdTradeFeeDetail tgTradeFeeDetail=new TrdTradeFeeDetail();
        	//交易编号=T1.交易编号
        	tgTradeFeeDetail.setTradeId(valFinProductDeal.getTradeId());
        	//一次性费用类型=一次性费用类型
        	tgTradeFeeDetail.setFeeType(key);
        	tgTradeFeeDetail.setIsPayWithTrade(mapIsPay.get(key));
        	tgTradeFeeDetail.setFeeAmt(map.get(key));
        	tgTradeFeeDetail.setFeeId(key);
            // 创建时间 = 系统时间
        	tgTradeFeeDetail.setCreateTime(dto.getCreateTime());
        	listValTradeFeeDetail.add(tgTradeFeeDetail);
        }
    	
    	return listValTradeFeeDetail;
    }
//    /**
//     * 结算指令的生成
//     * @param valFinProductDeal
//     * @param valBooksetInfoDtoList
//     * @return
//     */
//    private Map<String, Object> getvalOrders(TrdProductDeal valFinProductDeal,String prodId,String bookSetId,String acctSubjectType) {
//    	 Map<String, Object> map = new HashMap<String,Object>();
//    	 List<StlOrders> listValOrders = new ArrayList<StlOrders>();
//         List<StlOrdersExt> listValOrdersExt = new ArrayList<StlOrdersExt>();
//         List<BokDealAccounting> listBokDealAccounting = new ArrayList<BokDealAccounting>();
//         List<BokDealAccountingDetail> listBokDealAccountingDetail = new ArrayList<BokDealAccountingDetail>();
//         List<BokDetailId> listBokDetailId = new ArrayList<BokDetailId>();
//         
//    	 ProductDealDto productDealDto=new ProductDealDto();
//    	 productDealDto.setTradeStatus(valFinProductDeal.getTradeStatus());  //* 交易状态
//         productDealDto.setTradeId(valFinProductDeal.getTradeId());    // * 交易编号
//         productDealDto.setTradeDate(valFinProductDeal.getTradeDate()); //  * 交易日
//         productDealDto.setEffDate(valFinProductDeal.getSettleDate());
//         productDealDto.setPs(valFinProductDeal.getPs());//  * 交易方向
//         productDealDto.setTardeAmt(valFinProductDeal.getTradeAmt()); //  * 交易金额
//         productDealDto.setFinprodId(prodId); // * 金融产品代码
//         /**modify by wujie on 20190107 start
//         productDealDto.setFinprodType(interfCommonService.queryProductType(prodId));
//         **/
//         productDealDto.setFinprodType(acctSubjectType);
//         /**modify by wujie on 20190107 end**/
//         productDealDto.setBusiType(valFinProductDeal.getBusiType());// * 业务类型
//         productDealDto.setProdId(prodId); //  * 理财产品代码
//         productDealDto.setCcy(valFinProductDeal.getCcy()); //  * 币种
//         productDealDto.setIsTrade(DictCodeList.是否_是.getValue());   //  * 是否随交易出账
//         productDealDto.setBookNo(bookSetId);
//         productDealDto.setCreateTime(valFinProductDeal.getCreateTime());
//         productDealDto.setpTradeId(valFinProductDeal.getpTradeId());
//         
//         String insType = "";
//    	 if(valFinProductDeal.getPs().equals(DictCodeList.交易方向_融入债券卖出.getValue())){
//    		 insType=DictCodeListExt1.指令类型_债券借贷_融入债券卖出.getValue();
//    		 map = interfCommonService.settlementInstructionGeneration(productDealDto, 
//                 insType,DictCodeList.是否_是.getValue()); 
//    		 StlOrders ValOrders=(StlOrders)map.get("ORDERS");
//             StlOrdersExt ValOrdersExt=(StlOrdersExt)map.get("ORDERS_EXT");
//             listValOrders.add(ValOrders);
//             listValOrdersExt.add(ValOrdersExt);
//             
//             insType=DictCodeListExt1.指令类型_资金交收_债券类资产卖出_资金到账.getValue();
//             map = interfCommonService.settlementInstructionGeneration(productDealDto, 
//                 insType,DictCodeList.是否_否.getValue());
//             ValOrders=(StlOrders)map.get("ORDERS");
//             ValOrdersExt=(StlOrdersExt)map.get("ORDERS_EXT");
//             listValOrders.add(ValOrders);
//             listValOrdersExt.add(ValOrdersExt);
//             
//             BokDealAccounting valDealAccounting = (BokDealAccounting)map.get("ACCOUNTING"); 
//             BokDealAccountingDetail valDealAccountingDetail = (BokDealAccountingDetail)map.get("ACCOUNTING_DETAI"); 
//             BokDetailId valBokDetailId = (BokDetailId)map.get("BOK_DETAIL_ID"); 
//             listBokDealAccounting.add(valDealAccounting);
//             listBokDealAccountingDetail.add(valDealAccountingDetail);
//             listBokDetailId.add(valBokDetailId);
//             
//             
//    	 }else if(valFinProductDeal.getPs().equals(DictCodeList.交易方向_融入债券购回.getValue())){
//    		 insType=DictCodeListExt1.指令类型_债券借贷_融入债券卖出后购回.getValue();
//    		 map = interfCommonService.settlementInstructionGeneration(productDealDto, 
//        			 insType,DictCodeList.是否_是.getValue()); 
//    		 StlOrders ValOrders=(StlOrders)map.get("ORDERS");
//             StlOrdersExt ValOrdersExt=(StlOrdersExt)map.get("ORDERS_EXT");
//             listValOrders.add(ValOrders);
//             listValOrdersExt.add(ValOrdersExt);
//             
//    		 insType=DictCodeListExt1.指令类型_资金交收_债券类资产买入_资金交收日.getValue();
//    		 map = interfCommonService.settlementInstructionGeneration(productDealDto, 
//                 insType,DictCodeList.是否_否.getValue()); 
//    		 
//    		 BokDealAccounting valDealAccounting = (BokDealAccounting)map.get("ACCOUNTING"); 
//             BokDealAccountingDetail valDealAccountingDetail = (BokDealAccountingDetail)map.get("ACCOUNTING_DETAI"); 
//             BokDetailId valBokDetailId = (BokDetailId)map.get("BOK_DETAIL_ID"); 
//             listBokDealAccounting.add(valDealAccounting);
//             listBokDealAccountingDetail.add(valDealAccountingDetail);
//             listBokDetailId.add(valBokDetailId);
//    	 }
//    	 map = new HashMap<String,Object>();
//         map.put("ORDERS", listValOrders);
//         map.put("ORDERS_EXT", listValOrdersExt);
//         map.put("ACCOUNTING", listBokDealAccounting);
//         map.put("ACCOUNTING_DETAI", listBokDealAccountingDetail);
//         map.put("BOK_DETAIL_ID", listBokDetailId);
//         return map;
//    }
}
