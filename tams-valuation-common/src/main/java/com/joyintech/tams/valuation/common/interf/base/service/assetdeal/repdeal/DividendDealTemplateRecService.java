/*
 * 文件名：DepositeAssetBaseInfoTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年6月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.assetdeal.repdeal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.common.base.entity.StlOrdersExt;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.FinalString;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.ChannelFeeInfoDeal;
import com.joyintech.tams.valuation.common.interf.base.dto.InterfProductDealBookCommonDto;
import com.joyintech.tams.valuation.common.interf.base.dto.RepurchaseDealInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValBotPeriodFund;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValRepDividendDeal;

/**
 * 交易信息-买断式回购派息交易 模板处理Service(ValRepDividendDeal)
 * 
 * @author chufengjie
 * @version 1.0 2018年8月7日
 * @see DividendDealTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DividendDealTemplateRecService {

    /**
     * XMl文件解析Service
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @Autowired
    private InterfCommonService interfCommonService;

    /**
     * 交易信息-买断式回购派息交易 接口模板处理
     * 
     * @param templateSourceFlgDTO 文件来源和类型
     * @param acctEntity 计提基础信息DTO
     * @return 交易信息-买断式回购派息交易 接口模板处理返回共通结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public RepurchaseDealInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                     TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        RepurchaseDealInfoValCommonResult result = new RepurchaseDealInfoValCommonResult();
        List<ValRepDividendDeal> listValRepDividendDeal = new ArrayList<ValRepDividendDeal>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        Map<String,Object> params = acctEntity.getParams();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValRepDividendDeal = returnResult.getT(ValBotPeriodFund.class);
        result.setDatanum(listValRepDividendDeal==null?0:listValRepDividendDeal.size());
        if (listValRepDividendDeal == null) {
        	listValRepDividendDeal = new ArrayList<ValRepDividendDeal>();
        }
        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleValBotPeriodFund(listValRepDividendDeal,acctEntity.getResovleDate(),params,returnResult.getValInterflog());

        if(map != null){
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
            // 账务明细代码表
            List<BokDetailId> listValBokDetailId = (List<BokDetailId>)map.get("ValBokDetailId");
            result.setListValBokDetailId(listValBokDetailId);

            // 结算指令
            List<StlOrders> listValOrders = (List<StlOrders>)map.get("listValOrders");
            List<StlOrdersExt> listValOrdersExt = (List<StlOrdersExt>)map.get("listValOrdersExt");
            result.setListValOrders(listValOrders);
            result.setListValOrdersExt(listValOrdersExt);
            // 添加日志
            result.setValInterflog((List<ValInterflog>)map.get("valInterflogList"));//add by dongjun on 20190225
        }

        // 返回处理结果数据
        return result;
    }

    /**
     * 02-买断式回购派息交易
     * 
     * @param listValBotPeriodFund 净值型产品存续期交易
     * @return 金融产品交易List + 估值核算交易扩展表List + 入账信息表List + 账务明细表List + 交易费用明细表List
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked"})
    private Map<String, Object> handleValBotPeriodFund(List<ValRepDividendDeal> listValRepDividendDeal,Date resovleDate,Map<String,Object> params,
    		List<ValInterflog> valInterflog) {
        List<String> bookSetIds = (List<String>)params.get(InterfCommonConstant.接口_运行接口账套ID);
    	String valModel = (String)params.get(InterfCommonConstant.接口模式);
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
        List<StlOrdersExt> listValOrdersExt = new ArrayList<StlOrdersExt>();
        List<ValInterflog> valInterflogList =  new ArrayList<>();
        for (ValRepDividendDeal dto : listValRepDividendDeal) {
        	if(StringUtil.equals(InterfCommonConstant.接口模式_指定账套,valModel) && !bookSetIds.contains(dto.getProdId())){
        		continue;
        	}
            dto.setCreateTime(resovleDate);
            FinTradeProduct finTradeProduct = interfCommonService.queryFinTradeProduct(dto.getAssetId());
            // 金融产品交易
            TrdProductDeal valFinProductDeal = this.getValFinProductDeal(dto);
            listValFinProductDeal.add(valFinProductDeal);

            // 入账信息表 账务明细表 账务明细代码表 调用共通
            InterfProductDealBookCommonDto dealAccounting = interfCommonService.dealAccounting(
                valFinProductDeal, dto.getProdId());
            listValDealAccounting.add(dealAccounting.getBokDealAccounting());
            listValDealAccountingDetail.add(dealAccounting.getBokDealAccountingDetail());
            
            // 估值核算交易扩展表
            TrdProductDealAdd valFinProductDealExt = this.getValFinProductDealExt(dto,
                valFinProductDeal,dealAccounting.getAcctSubjectType());
            valFinProductDealExt.setTradeId(valFinProductDeal.getTradeId());
            listValFinProductDealExt.add(valFinProductDealExt);
            //四级科目尾号
            dealAccounting.getBokDetailId().setDetailSubjectNoTail(finTradeProduct==null?null:finTradeProduct.getTermType());
            //四级科目尾号名称 
			dealAccounting.getBokDetailId()
					.setDetailSubnameTail(finTradeProduct == null ? null
							: InterfHelper.getDictColdeList_ItemName(DictCode.期限品种_回购.getValue(),
									finTradeProduct.getTermType()));
            listValBokDetailId.add(dealAccounting.getBokDetailId());

            /** modify by wujie on 20190118 start
            ProductDealDto productDealDto = getProductDealDto(dto, valFinProductDeal,
                dealAccounting.getBokDetailId(),dealAccounting.getAcctSubjectType());
            // 结算指令
            if (DictCodeList.业务类型_债券买断式回购交易.getValue().equals(valFinProductDeal.getBusiType())) {
                if (DictCodeList.交易方向_收息.getValue().equals(valFinProductDeal.getPs())) {
                    String insType1 = DictCodeListExt1.指令类型_买断式逆回购_派息.getValue();
                    String insType2 = DictCodeListExt1.指令类型_资金交收_买断式逆回购派息_资金到账.getValue();
                    
                    Map<String, Object> map1 = interfCommonService.settlementInstructionGeneration(
                        productDealDto, insType1, DictCodeList.是否_是.getValue());
                    
                    //modify by wujie on 20180819 不随交易支付，生成自己的入账信息
                    Map<String, Object> map2 = interfCommonService.settlementInstructionGeneration(
                        productDealDto, insType2, DictCodeList.是否_否.getValue());
                    StlOrders ValOrders1 = (StlOrders)map1.get("ORDERS");
                    StlOrdersExt ValOrdersExt1 = (StlOrdersExt)map1.get("ORDERS_EXT");
                    listValOrders.add(ValOrders1);
                    listValOrdersExt.add(ValOrdersExt1);
                    
                    //add by wujie on 20180819 start remark:不随交易入账指令
                    if(map2.get("ACCOUNTING") != null){
                    	listValDealAccounting.add((BokDealAccounting)map2.get("ACCOUNTING"));
                    }
                    if(map2.get("ACCOUNTING_DETAI") != null){
                    	listValDealAccountingDetail.add((BokDealAccountingDetail)map2.get("ACCOUNTING_DETAI"));
                    }
                    if(map2.get("BOK_DETAIL_ID") != null){
                    	listValBokDetailId.add((BokDetailId)map2.get("BOK_DETAIL_ID"));
                    }
                    //add by wujie on 20180819 end
                    
                    
                    StlOrders ValOrders2 = (StlOrders)map2.get("ORDERS");
                    StlOrdersExt ValOrdersExt2 = (StlOrdersExt)map2.get("ORDERS_EXT");
                    listValOrders.add(ValOrders2);
                    listValOrdersExt.add(ValOrdersExt2);
                }
            }
            **/
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
        resultMap.put("ValFinProductDeal", listValFinProductDeal);
        resultMap.put("ValFinProductDealExt", listValFinProductDealExt);
        resultMap.put("ValDealAccounting", listValDealAccounting);
        resultMap.put("ValDealAccountingDetail", listValDealAccountingDetail);
        resultMap.put("ValTradeFeeDetail", listValTradeFeeDetail);
        resultMap.put("ValBokDetailId", listValBokDetailId);

        resultMap.put("listValOrders", listValOrders);
        resultMap.put("listValOrdersExt", listValOrdersExt);
        
        valInterflogList = interfCommonService.filterValInterflogList(valInterflog, bookSetIds, valModel);
    	resultMap.put("valInterflogList", valInterflogList);
        return resultMap;
    }

    /**
     * 通道投资资产交易拆分_通道费用
     * add by wujie on 20190322 start
     */
    private ChannelFeeInfoDeal getChannelFeeInfoDeal(ValRepDividendDeal dto,String acctSubjectType){
        //接口.通道代码为空
        if(dto.getChannelId() == null){
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
        deal.setChannelId(dto.getChannelId());
        //产品代码 = 接口.产品代码
        deal.setProdId(dto.getProdId());
        
        deal.setAcctSubjectId(dto.getProdId() + FinalString.LAYERING_SPLIT + dto.getChannelId());
        
        deal.setAcctSubjectType(acctSubjectType);
        
        return deal;
    }

    /**
     * T1:金融产品交易表 接收
     * 
     * @param dto 02-存续期交易
     * @return 金融产品交易表
     * @see
     * @since 1.0
     */
    private TrdProductDeal getValFinProductDeal(ValRepDividendDeal dto) {

        // 金融产品交易
        TrdProductDeal valFinProductDeal = new TrdProductDeal();
        // 交易编号 = 自动生成
        String tradeId = interfCommonService.getTradeid(DictCodeList.交易方向_收息.getValue(),
            dto.getTradeDate(), "A");
        valFinProductDeal.setTradeId(tradeId);
        // 业务类型 = 05 债券买断式回购交易
        valFinProductDeal.setBusiType(DictCodeList.业务类型_债券买断式回购交易.getValue());
        // 金融产品代码 = B.回购标的代码
        valFinProductDeal.setFinprodId(dto.getAssetId());
        // 金融产品类型 = B.回购标的类型
        valFinProductDeal.setFinprodType(dto.getAssetType());
        // 分支序号 = 设置全局变量(默认为0）
        valFinProductDeal.setBranch(InterfCommonConstant.分支序号_默认);
        // 交易日 = B.交易日
        valFinProductDeal.setTradeDate(dto.getTradeDate());
        // 起息日 = B.起息日
        valFinProductDeal.setSettleDate(dto.getVdate());
        // 到期日 = B.到期日
        valFinProductDeal.setSettleDate(dto.getMdate());
        // 交割日 = B.交易日
        valFinProductDeal.setSettleDate(dto.getTradeDate());
        // 交易币种 = B.币种
        valFinProductDeal.setCcy(dto.getCcy());
        // 利息总额 = B.派息金额
        valFinProductDeal.setIntAmt(dto.getIntAmt());
        // 交易金额 = B.交易金额
        valFinProductDeal.setTradeAmt(dto.getIntAmt());
        // 交易方向 = 数据字典：交易方向.债券派息
        valFinProductDeal.setPs(DictCodeList.交易方向_收息.getValue());
        // 交易状态 = B.数据动作标志
        valFinProductDeal.setTradeStatus(dto.getDataActionSign());
        // 是否结清 = 设置默认值 待确定
        valFinProductDeal.setIsClean(DictCodeList.是否_是.getValue());
        // 关联系统交易编号 = B.成交编号
        valFinProductDeal.setfTradeId(dto.getTradeId());
        // 交易场所 = B.交易场所
        valFinProductDeal.setTradeMarket(dto.getTradeMarket());
        // 创建人 = 固定值
        valFinProductDeal.setCreateUser(InterfCommonConstant.接口_默认用户);
        // 创建时间 = 系统时间
        valFinProductDeal.setCreateTime(dto.getCreateTime());
        // 更新人
        valFinProductDeal.setUpdateUser(InterfCommonConstant.接口_默认用户);
        //交易市场 = 数据字典：流通市场.二级市场
        valFinProductDeal.setTradeMarketMode(DictCodeListExt1.交易市场_二级市场.getValue());
        if(valFinProductDeal.getTradeStatus().equals(DictCodeListExt1.交易状态_撤销.getValue())){
            valFinProductDeal.setpTradeId(interfCommonService.getFTradeId(valFinProductDeal));
        }
      //通道代码 = a.通道代码
        valFinProductDeal.setChlId(dto.getChannelId());
        return valFinProductDeal;
    }

    /**
     * T2:估值核算交易扩展表 接收
     * 
     * @param dto 02-回购交易
     * @return 估值核算交易扩展表
     * @see
     * @since 1.0
     */
    private TrdProductDealAdd getValFinProductDealExt(ValRepDividendDeal dto, TrdProductDeal t1,String finprodType) {
        // 估值核算交易扩展表
    	TrdProductDealAdd valFinProductDealExt = new TrdProductDealAdd();
        // 交易编号 = T1.交易编号
        valFinProductDealExt.setTradeId(t1.getTradeId());
        // 理财产品代码 = B.产品代码
        /** modify by wujie on 20181115 start remark:投资组合投资时应赋值[表.投资组合代码]字段
        valFinProductDealExt.setProdId(dto.getProdId());
        **/
//        if(DictCodeListExt1.金融产品类型_投资组合.getValue().equals(finprodType)){
//        	valFinProductDealExt.setPortfolioId(dto.getProdId());
//        }else{
////        	valFinProductDealExt.setProdId(dto.getProdId());
//        }
        valFinProductDealExt.setPortfolioId(dto.getProdId());
        /**modify by wujie on 20181115 end remark:投资组合投资时应赋值[表.投资组合代码]字段**/
        // 是否有结算指令 = B.有无清算指令
//        valFinProductDealExt.setHasOrder(dto.getIsClean());
        // 创建时间 = 系统时间
        valFinProductDealExt.setCreateTime(dto.getCreateTime());
        // 生成方式
        valFinProductDealExt.setGenType(
            com.joyintech.tams.constant.DictCodeListExt1.生成方式_接口接入.getValue());
        return valFinProductDealExt;
    }

//    /**
//     * 结算指令 <一句话功能简述> <功能详细描述>
//     * 
//     * @param dto
//     * @param valFinProductDeal
//     * @param valBokDetailId
//     * @return
//     * @see
//     * @since 1.0
//     */
//    private ProductDealDto getProductDealDto(ValRepDividendDeal dto,
//                                             TrdProductDeal valFinProductDeal,
//                                             BokDetailId valBokDetailId,String acctSubjectType) {
//        ProductDealDto result = new ProductDealDto();
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
//        // 业务类型
//        result.setBusiType(DictCodeList.业务类型_债券买断式回购交易.getValue());
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
//        /**add by chendijun  2018/11/15 start**/
//        //原交易编号
//        result.setpTradeId(valFinProductDeal.getpTradeId());
//        /**add by chendijun  2018/11/15 end**/
//        /**add by chendijun  2018/11/20 start**/
//        //交易场所
//        result.setTradeMarket(valFinProductDeal.getTradeMarket());
//        //交易市场
//        result.setTradeMarketMode(valFinProductDeal.getTradeMarketMode());
//        /**add by chendijun  2018/11/20 end**/
//        return result;
//    }
}
