/*
 * 文件名：ValChannelProdFeeDealTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年3月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.assetdeal.feedeal;

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
import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.common.base.entity.StlOrdersExt;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.FinalString;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.FeeDealCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.InterfProductDealBookCommonDto;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValChannelProdFeeDeal;

/**
 * 通道产品费用交易 模板处理Service(VAL_CHANNEL_PROD_FEE_DEAL)
 * 
 * @author zhangwei
 * @version 1.0 2019年3月12日
 * @see ValChannelProdFeeDealTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValChannelProdFeeDealTemplateRecService {

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

    /**
     * 全局变量：默认分支序号
     */
    private static final Long DEFAULT_BRANCH = 0L;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public FeeDealCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                  TemplateOrderInfoReaderResult acctEntity) {

    	FeeDealCommonResult result = new FeeDealCommonResult();
        List<ValChannelProdFeeDeal> listValChannelProdFeeDeal = new ArrayList<ValChannelProdFeeDeal>();
        Map<String,Object> params = acctEntity.getParams();
        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult<>();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValChannelProdFeeDeal = returnResult.getT(ValChannelProdFeeDeal.class);
        result.setDatanum(listValChannelProdFeeDeal == null ? 0 : listValChannelProdFeeDeal.size());
        if (listValChannelProdFeeDeal == null) {
            listValChannelProdFeeDeal = new ArrayList<ValChannelProdFeeDeal>();
        }

        // 3、根据有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = this.handleValChannelProdFeeDeal(listValChannelProdFeeDeal,
            acctEntity.getResovleDate(),params,returnResult.getValInterflog());
        if (map != null) {
            // 金融产品交易List
            List<TrdProductDeal> listValTrdProductDeal = (List<TrdProductDeal>)map.get("ValTrdProductDeal");
            result.setListValFinProductDeal(listValTrdProductDeal);
            // 估值核算交易扩展表List
            List<TrdProductDealAdd> listValTrdProductDealExt = (List<TrdProductDealAdd>)map.get("ValTrdProductDealExt");
            result.setListValFinProductDealExt(listValTrdProductDealExt);
            
            /**add by wujie on 20190321 start**/
            // 入账信息表List
            List<BokDealAccounting> listValDealAccounting = (List<BokDealAccounting>)map.get("ValDealAccounting");
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
            result.setListValOrders(listValOrders);
            
            // 结算指令扩展表
            List<StlOrdersExt> listValOrdersExt =(List<StlOrdersExt>)map.get("listValOrdersExt");
            result.setListValOrdersExt(listValOrdersExt);
            
            // 交易费用明细表List
            List<TrdTradeFeeDetail> listValTgTradeFeeDetail = (List<TrdTradeFeeDetail>)map.get(
                "ValTradeFeeDetail");
            result.setListValTradeFeeDetail(listValTgTradeFeeDetail);
            
            /**add by wujie on 20190321 end**/
            
            // 添加日志
            result.setValInterflog((List<ValInterflog>)map.get("valInterflogList"));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> handleValChannelProdFeeDeal(List<ValChannelProdFeeDeal> listValChannelProdFeeDeal,
                                                            Date resovleDate,Map<String,Object> params,List<ValInterflog> valInterflog) {
		List<String> bookSetIds = (List<String>)params.get(InterfCommonConstant.接口_运行接口账套ID);
		String valModel = (String)params.get(InterfCommonConstant.接口模式);
    	// 初始化返回结果
        Map<String, Object> resultMap = new HashMap<>();
        // 金融产品交易List
        List<TrdProductDeal> listValTrdProductDeal = new ArrayList<TrdProductDeal>();
        // 估值核算交易扩展表List
        List<TrdProductDealAdd> listValTrdProductDealExt = new ArrayList<TrdProductDealAdd>();
        // 交易费用明细表List
        List<TrdTradeFeeDetail> listValTradeFeeDetail = new ArrayList<TrdTradeFeeDetail>();

        // 入账信息表List
        List<BokDealAccounting> listValDealAccounting = new ArrayList<BokDealAccounting>();
        // 账务明细表List
        List<BokDealAccountingDetail> listValDealAccountingDetail = new ArrayList<BokDealAccountingDetail>();
        // 账务明细代码表
        List<BokDetailId> listValBokDetailId = new ArrayList<BokDetailId>();
        // 结算指令
        List<StlOrders> listValOrders = new ArrayList<StlOrders>();
        // 结算指令扩展表
        List<StlOrdersExt> listValOrdersExt = new ArrayList<StlOrdersExt>();

        List<ValInterflog> valInterflogList =  new ArrayList<>();
        
        for (ValChannelProdFeeDeal dto : listValChannelProdFeeDeal) {
        	if(StringUtil.equals(valModel, InterfCommonConstant.接口模式_指定账套) && !bookSetIds.contains(dto.getProdId())){
        		continue;
        	}
            dto.setCreateTime(resovleDate);// 用于去重操作
            
            /**1.通道申购交易**/
            // 金融产品交易 T12(通道申购)
            TrdProductDeal valTrdProductDeal3 = this.getValTrdProductDeal3(dto);
            listValTrdProductDeal.add(valTrdProductDeal3);

            // 估值核算交易扩展表 T22(通道申购)
            TrdProductDealAdd trdProductDealExt3 = this.getValTrdProductDealExt3(dto, valTrdProductDeal3);
            listValTrdProductDealExt.add(trdProductDealExt3);
            
            
            // 调用共通设计->【详细设计-接口共通方法设计.xlsx】->交易入账SHEET(通道申购)
            InterfProductDealBookCommonDto dealAccounting3 = interfCommonService.dealAccounting(valTrdProductDeal3,
                dto.getProdId());
            listValDealAccounting.add(dealAccounting3.getBokDealAccounting());
            listValDealAccountingDetail.add(dealAccounting3.getBokDealAccountingDetail());


            /**add by wujie on 20190625 start**/
            //四级科目尾号
            dealAccounting3.getBokDetailId().setDetailSubjectNoTail(valTrdProductDeal3.getFinprodId());
            //四级科目尾号名称
            dealAccounting3.getBokDetailId().setDetailSubnameTail(dto.getChannelName());
            /**add by wujie on 20190625 end**/


            listValBokDetailId.add(dealAccounting3.getBokDetailId());

            // 调用共通设计->【详细设计-接口共通方法设计.xlsx】->通过交易生成指令及指令入账SHEET(通道申购)
            Map<String, Object> map3 = interfCommonService.instructionGenerate(valTrdProductDeal3, dto.getProdId(),
            		dealAccounting3.getAcctSubjectType(), null);
            if (map3.get("ORDERS") != null) {
                listValOrders.addAll((List<StlOrders>)map3.get("ORDERS"));
            }
            if (map3.get("ORDERS_EXT") != null) {
                listValOrdersExt.addAll((List<StlOrdersExt>)map3.get("ORDERS_EXT"));
            }
            if (map3.get("ACCOUNTING") != null) {
                listValDealAccounting.addAll((List<BokDealAccounting>)map3.get("ACCOUNTING"));
            }
            if (map3.get("ACCOUNTING_DETAI") != null) {
                listValDealAccountingDetail.addAll((List<BokDealAccountingDetail>)map3.get("ACCOUNTING_DETAI"));
            }
            if (map3.get("BOK_DETAIL_ID") != null) {
                listValBokDetailId.addAll((List<BokDetailId>)map3.get("BOK_DETAIL_ID"));
            }
            
            
            /**2.金融产品交易(产品买资产)**/ 
            // 金融产品交易 T1(产品买资产)
            TrdProductDeal valTrdProductDeal1 = this.getValTrdProductDeal1(dto);
            //T1.汇总交易编号 = T12.交易编号
            valTrdProductDeal1.setTotleTradeId(valTrdProductDeal3.getTradeId());
            listValTrdProductDeal.add(valTrdProductDeal1);

            // 估值核算交易扩展表 T21(产品买资产)
            TrdProductDealAdd trdProductDealExt1 = this.getValTrdProductDealExt1(dto, valTrdProductDeal1);
            listValTrdProductDealExt.add(trdProductDealExt1);

//            // 交易费用明细 T3(产品费用支付)
//            TgTradeFeeDetail tgTradeFeeDetail1 = this.getValTradeFeeDetail1(dto, valTrdProductDeal1);
//            listValTradeFeeDetail.add(tgTradeFeeDetail1);
            
            
            // 调用共通设计->【详细设计-接口共通方法设计.xlsx】->交易入账SHEET(产品费用支付)
            InterfProductDealBookCommonDto dealAccounting1 = interfCommonService.dealAccounting(valTrdProductDeal1,
                dto.getProdId());
            listValDealAccounting.add(dealAccounting1.getBokDealAccounting());
            listValDealAccountingDetail.add(dealAccounting1.getBokDealAccountingDetail());

            /**add by wujie on 20190625 start**/
            //四级科目尾号
            dealAccounting1.getBokDetailId().setDetailSubjectNoTail(valTrdProductDeal1.getFinprodId());
            //四级科目尾号名称
            dealAccounting1.getBokDetailId().setDetailSubnameTail(dto.getChannelName());
            /**add by wujie on 20190625 end**/

            listValBokDetailId.add(dealAccounting1.getBokDetailId());

            // 调用共通设计->【详细设计-接口共通方法设计.xlsx】->通过交易生成指令及指令入账SHEET(产品费用支付)
            Map<String, Object> map1 = interfCommonService.instructionGenerate(valTrdProductDeal1, dto.getProdId(),
            		dealAccounting1.getAcctSubjectType(), null);
            if (map1.get("ORDERS") != null) {
                listValOrders.addAll((List<StlOrders>)map1.get("ORDERS"));
            }
            if (map1.get("ORDERS_EXT") != null) {
                listValOrdersExt.addAll((List<StlOrdersExt>)map1.get("ORDERS_EXT"));
            }
            if (map1.get("ACCOUNTING") != null) {
                listValDealAccounting.addAll((List<BokDealAccounting>)map1.get("ACCOUNTING"));
            }
            if (map1.get("ACCOUNTING_DETAI") != null) {
                listValDealAccountingDetail.addAll((List<BokDealAccountingDetail>)map1.get("ACCOUNTING_DETAI"));
            }
            if (map1.get("BOK_DETAIL_ID") != null) {
                listValBokDetailId.addAll((List<BokDetailId>)map1.get("BOK_DETAIL_ID"));
            }

            
            /**3.通道费用交易**/
            // 金融产品交易 T11(通道费用交易)
            TrdProductDeal valTrdProductDeal2 = this.getValTrdProductDeal2(dto);
            //T11.汇总交易编号 = T12.交易编号
            valTrdProductDeal2.setTotleTradeId(valTrdProductDeal3.getTradeId());
            listValTrdProductDeal.add(valTrdProductDeal2);

            // 估值核算交易扩展表 T21通道费用交易)
            TrdProductDealAdd trdProductDealExt2 = this.getValTrdProductDealExt2(dto, valTrdProductDeal2);
            listValTrdProductDealExt.add(trdProductDealExt2);

            // 交易费用明细 T31(通道费用交易)
            TrdTradeFeeDetail tgTradeFeeDetail2 = this.getValTradeFeeDetail2(dto, valTrdProductDeal2);
            listValTradeFeeDetail.add(tgTradeFeeDetail2);

            
            // 调用共通设计->【详细设计-接口共通方法设计.xlsx】->交易入账SHEET(通道费用交易)
            InterfProductDealBookCommonDto dealAccounting2 = interfCommonService.dealAccounting(valTrdProductDeal2,
                dto.getProdId());
            listValDealAccounting.add(dealAccounting2.getBokDealAccounting());
            listValDealAccountingDetail.add(dealAccounting2.getBokDealAccountingDetail());

            /**add by wujie on 20190625 start**/
            //四级科目尾号
            dealAccounting2.getBokDetailId().setDetailSubjectNoTail(valTrdProductDeal2.getFinprodId());
            //四级科目尾号名称
            dealAccounting2.getBokDetailId().setDetailSubnameTail(dto.getChannelName());
            /**add by wujie on 20190625 end**/

            listValBokDetailId.add(dealAccounting2.getBokDetailId());

            // 调用共通设计->【详细设计-接口共通方法设计.xlsx】->通过交易生成指令及指令入账SHEET(通道费用交易)
            Map<String, Object> map2 = interfCommonService.instructionGenerate(valTrdProductDeal2, dto.getProdId(),
            		dealAccounting2.getAcctSubjectType(), null);
            if (map2.get("ORDERS") != null) {
                listValOrders.addAll((List<StlOrders>)map2.get("ORDERS"));
            }
            if (map2.get("ORDERS_EXT") != null) {
                listValOrdersExt.addAll((List<StlOrdersExt>)map2.get("ORDERS_EXT"));
            }
            if (map2.get("ACCOUNTING") != null) {
                listValDealAccounting.addAll((List<BokDealAccounting>)map2.get("ACCOUNTING"));
            }
            if (map2.get("ACCOUNTING_DETAI") != null) {
                listValDealAccountingDetail.addAll((List<BokDealAccountingDetail>)map2.get("ACCOUNTING_DETAI"));
            }
            if (map2.get("BOK_DETAIL_ID") != null) {
                listValBokDetailId.addAll((List<BokDetailId>)map2.get("BOK_DETAIL_ID"));
            }
        }

        resultMap.put("ValTrdProductDeal", listValTrdProductDeal);
        resultMap.put("ValTrdProductDealExt", listValTrdProductDealExt);
        resultMap.put("ValDealAccounting", listValDealAccounting);
        resultMap.put("ValDealAccountingDetail", listValDealAccountingDetail);
        resultMap.put("ValBokDetailId", listValBokDetailId);

        resultMap.put("listValOrders", listValOrders);
        resultMap.put("listValOrdersExt", listValOrdersExt);
        
        /**add by wujie on 20190321 start**/
        resultMap.put("ValTradeFeeDetail", listValTradeFeeDetail);
        /**add by wujie on 20190321 end**/
        valInterflogList = interfCommonService.filterValInterflogList(valInterflog, bookSetIds, valModel);
    	resultMap.put("valInterflogList", valInterflogList);
        return resultMap;
    }

    /**
     * T22: 估值核算交易扩展表(通道费用交易)
     * 
     * @param dto
     * @param deal
     * @return
     */
    private TrdProductDealAdd getValTrdProductDealExt3(ValChannelProdFeeDeal dto, TrdProductDeal deal) {
    	TrdProductDealAdd trdProductDealExt = new TrdProductDealAdd();
        // 交易编号 = T1.交易编号
        trdProductDealExt.setTradeId(deal.getTradeId());

        // 投资组合代码 = B.产品代码+"_"+ B.通道代码
        trdProductDealExt.setPortfolioId(dto.getProdId() + FinalString.LAYERING_SPLIT+ dto.getChannelId());
        // 理财产品代码 = B.产品代码+"_"+ B.通道代码
//        trdProductDealExt.setProdId(dto.getProdId() + FinalString.LAYERING_SPLIT+ dto.getChannelId());

        // 是否有结算指令 = B.有无清算指令
//        trdProductDealExt.setHasOrder(dto.getIsClean());
        // 生成方式 = 固定值 生成方式_接口接入
        trdProductDealExt.setGenType(DictCodeListExt1.生成方式_接口接入.getValue());
        trdProductDealExt.setCreateTime(dto.getCreateTime());
        return trdProductDealExt;
    }

    /**
     * T12: 金融产品交易(通道申购)
     * 
     * @param dto
     * @return
     */
    private TrdProductDeal getValTrdProductDeal3(ValChannelProdFeeDeal dto) {

        // 初始化 金融产品交易 返回值
        TrdProductDeal trdProductDeal = new TrdProductDeal();

        // 业务类型 = 数据字典：业务类型.费用交易
        trdProductDeal.setBusiType(DictCodeList.业务类型_货币型TA交易.getValue());
        // 交易编号 = 调用共通方法生成
        trdProductDeal.setTradeId(
            interfCommonService.getTradeid(DictCodeList.交易方向_产品申购.getValue(), dto.getPayDate(), "A"));
        // 金融产品代码 = B.产品代码 +"_"+ B.通道代码
        trdProductDeal.setFinprodId(dto.getProdId() + FinalString.LAYERING_SPLIT+ dto.getChannelId());

        // 金融产品类型 = 数据字典：金融产品类型.通道
        trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_通道.getValue());

        // 分支序号 设置全局变量(默认为0）
        trdProductDeal.setBranch(DEFAULT_BRANCH);

        // 交易日 = B.支付日
        trdProductDeal.setTradeDate(dto.getPayDate());
        // 交割日 = B.支付日
        trdProductDeal.setSettleDate(dto.getPayDate());
        // 交易币种 = B.币种
        trdProductDeal.setCcy(dto.getCcy());
        // 交易份额 = B.实付费用
        trdProductDeal.setShareAmt(dto.getActPayAmt());
        // 交易本金 = B.实付费用
        trdProductDeal.setPrinAmt(dto.getActPayAmt());
        // 交易金额 = B.实付费用
        trdProductDeal.setTradeAmt(dto.getActPayAmt());
        // 交易方向 数据字典：交易方向.产品申购
        trdProductDeal.setPs(DictCodeList.交易方向_产品申购.getValue());

        // 交易状态
        if (DictCodeList.动作标志_新增.getValue().equals(dto.getDataActionSign())) {
            // "B.数据动作标志 eq 新增 --> 01:初始
            trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());

        }
        else if (DictCodeList.动作标志_删除.getValue().equals(dto.getDataActionSign())) {
            // B.数据动作标志 eq 撤销 --> 03:撤销"
            trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
        }

        // 汇总交易编号 T12.交易编号
        trdProductDeal.setTotleTradeId(trdProductDeal.getTradeId());
        
        // 通道代码 = B.通道代码
        trdProductDeal.setChlId(dto.getChannelId());

        // 关联系统交易编号 = B.成交编号
        trdProductDeal.setfTradeId(dto.getTradeId());
        
        trdProductDeal.setCreateTime(dto.getCreateTime());

        // 原交易编号 = 交易状态为撤销时： 原交易编号=调用共通设计->【详细设计-接口共通方法设计.xlsx】->撤销交易对应的原交易编号查询SHEET
        // 交易状态为非撤销时：原交易编号为空
        if (trdProductDeal.getTradeStatus().equals(DictCodeListExt1.交易状态_撤销.getValue())) {
            trdProductDeal.setpTradeId(interfCommonService.getFTradeId(trdProductDeal));
        }
        return trdProductDeal;
    }

    /**
     * T31: 交易费用明细(通道费用交易)
     * 
     * @param dto
     * @param valFinProductDeal
     * @return
     */
    private TrdTradeFeeDetail getValTradeFeeDetail2(ValChannelProdFeeDeal dto, TrdProductDeal trdProductDeal) {
    	TrdTradeFeeDetail valTradeFeeDetail = new TrdTradeFeeDetail();
        // 交易编号 = T1.交易编号
        valTradeFeeDetail.setTradeId(trdProductDeal.getTradeId());
        // 一次性费用类型 数据字典：一次性费用类型.通道费
        valTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_通道费.getValue());
        // 是否随交易支付 = 固定值：Y 是 默认随交易支付
        valTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
        // 费用金额 = B.实付费用
        valTradeFeeDetail.setFeeAmt(dto.getActPayAmt());
        valTradeFeeDetail.setFeeId(dto.getFeeId());
        // 创建时间 = 系统时间
        valTradeFeeDetail.setCreateTime(dto.getCreateTime());
        return valTradeFeeDetail;
    }

    /**
     * T21: 估值核算交易扩展表(通道费用交易)
     * 
     * @param dto
     * @param deal
     * @return
     */
    private TrdProductDealAdd getValTrdProductDealExt2(ValChannelProdFeeDeal dto, TrdProductDeal deal) {
    	TrdProductDealAdd trdProductDealExt = new TrdProductDealAdd();
        // 交易编号 = T1.交易编号
        trdProductDealExt.setTradeId(deal.getTradeId());

        // 投资组合代码 = B.产品代码+"_"+ B.通道代码
        trdProductDealExt.setPortfolioId(dto.getProdId() + FinalString.LAYERING_SPLIT+ dto.getChannelId());
        // 理财产品代码 = B.产品代码+"_"+ B.通道代码
//        trdProductDealExt.setProdId(dto.getProdId() + FinalString.LAYERING_SPLIT+ dto.getChannelId());

        // 是否有结算指令 = B.有无清算指令
//        trdProductDealExt.setHasOrder(dto.getIsClean());
        // 生成方式 = 固定值 生成方式_接口接入
        trdProductDealExt.setGenType(DictCodeListExt1.生成方式_接口接入.getValue());
        trdProductDealExt.setCreateTime(dto.getCreateTime());
        return trdProductDealExt;
    }

    /**
     * T11: 金融产品交易(通道费用交易)
     * 
     * @param dto
     * @return
     */
    private TrdProductDeal getValTrdProductDeal2(ValChannelProdFeeDeal dto) {

        // 初始化 金融产品交易 返回值
        TrdProductDeal trdProductDeal = new TrdProductDeal();

        // 业务类型 = 数据字典：业务类型.费用交易
        trdProductDeal.setBusiType(DictCodeList.业务类型_产品费用交易.getValue());
        // 交易编号 = 调用共通方法生成
        trdProductDeal.setTradeId(
            interfCommonService.getTradeid(DictCodeList.交易方向_付费.getValue(), dto.getPayDate(), "A"));
        // 金融产品代码 = B.产品代码 +"_"+ B.通道代码
        trdProductDeal.setFinprodId(dto.getProdId() + FinalString.LAYERING_SPLIT+ dto.getChannelId());

        // 金融产品类型 = 数据字典：金融产品类型.交易费用
        trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_通道费.getValue());

        // 分支序号 设置全局变量(默认为0）
        trdProductDeal.setBranch(DEFAULT_BRANCH);

        // 交易日 = B.支付日
        trdProductDeal.setTradeDate(dto.getPayDate());
        // 起息日 = B.计提起始日
        trdProductDeal.setVdate(dto.getCalStartDay());
        // 到期日 = B.计提结束日
        trdProductDeal.setMdate(dto.getCalEndDay());
        // 交割日 = B.支付日
        trdProductDeal.setSettleDate(dto.getPayDate());
        // 交易币种 = B.币种
        trdProductDeal.setCcy(dto.getCcy());
        // 交易金额 = B.实付费用
        trdProductDeal.setTradeAmt(dto.getActPayAmt());

        // 交易方向 数据字典：交易方向.付费
        trdProductDeal.setPs(DictCodeList.交易方向_付费.getValue());

        // 交易状态
        if (DictCodeList.动作标志_新增.getValue().equals(dto.getDataActionSign())) {
            // "B.数据动作标志 eq 新增 --> 01:初始
            trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());

        }
        else if (DictCodeList.动作标志_删除.getValue().equals(dto.getDataActionSign())) {
            // B.数据动作标志 eq 撤销 --> 03:撤销"
            trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
        }

        // 是否结清 B.是否结算
        trdProductDeal.setIsClean(dto.getIsClean2());
        // 汇总交易编号 T1.交易编号
        trdProductDeal.setTotleTradeId(trdProductDeal.getTradeId());
        // 通道代码 = B.通道代码
        trdProductDeal.setChlId(dto.getChannelId());

        // 关联系统交易编号 = B.成交编号
        trdProductDeal.setfTradeId(dto.getTradeId());

        // 原交易编号 = 交易状态为撤销时： 原交易编号=调用共通设计->【详细设计-接口共通方法设计.xlsx】->撤销交易对应的原交易编号查询SHEET
        // 交易状态为非撤销时：原交易编号为空
        if (trdProductDeal.getTradeStatus().equals(DictCodeListExt1.交易状态_撤销.getValue())) {
            trdProductDeal.setpTradeId(interfCommonService.getFTradeId(trdProductDeal));
        }
        trdProductDeal.setCreateTime(dto.getCreateTime());
        return trdProductDeal;
    }

//    /**
//     * T3: 交易费用明细(产品费用支付)
//     * 
//     * @param dto
//     * @param valFinProductDeal
//     * @return
//     */
//    private TgTradeFeeDetail getValTradeFeeDetail1(ValChannelProdFeeDeal dto, TrdProductDeal trdProductDeal) {
//        TgTradeFeeDetail valTradeFeeDetail = new TgTradeFeeDetail();
//        // 交易编号 = T1.交易编号
//        valTradeFeeDetail.setTradeId(trdProductDeal.getTradeId());
//        // 一次性费用类型 数据字典：一次性费用类型.通道费
//        valTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_通道费.getValue());
//        // 是否随交易支付 = 固定值：Y 是 默认随交易支付
//        valTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
//        // 费用金额 = B.实付费用
//        valTradeFeeDetail.setFeeAmt(dto.getActPayAmt());
//        valTradeFeeDetail.setFeeId(dto.getFeeId());
//
//        return valTradeFeeDetail;
//    }

    /**
     * T2: 估值核算交易扩展表(产品费用支付)
     * 
     * @param dto
     * @param deal
     * @return
     */
    private TrdProductDealAdd getValTrdProductDealExt1(ValChannelProdFeeDeal dto, TrdProductDeal deal) {
    	TrdProductDealAdd trdProductDealExt = new TrdProductDealAdd();
        // 交易编号 = T1.交易编号
        trdProductDealExt.setTradeId(deal.getTradeId());

        // 投资组合代码 = B.产品代码
        trdProductDealExt.setPortfolioId(dto.getProdId());
        // 理财产品代码 = B.产品代码
//        trdProductDealExt.setProdId(dto.getProdId());

        // 是否有结算指令 = B.有无清算指令
//        trdProductDealExt.setHasOrder(dto.getIsClean());
        // 生成方式 = 固定值 生成方式_接口接入
        trdProductDealExt.setGenType(DictCodeListExt1.生成方式_接口接入.getValue());
        // 创建时间 = 系统时间
        trdProductDealExt.setCreateTime(dto.getCreateTime());
        return trdProductDealExt;
    }

    /**
     * T1: 金融产品交易(产品买资产)
     * 
     * @param dto
     * @return
     */
    private TrdProductDeal getValTrdProductDeal1(ValChannelProdFeeDeal dto) {

        // 初始化 金融产品交易 返回值
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        // 业务类型 = 数据字典：业务类型.费用交易
        trdProductDeal.setBusiType(DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
        // 交易编号 = 调用共通方法生成
        trdProductDeal.setTradeId(
            interfCommonService.getTradeid(DictCodeList.交易方向_申购确认.getValue(), dto.getPayDate(), "A"));
        // 金融产品代码 = B.产品代码+"_"+B.通道代码
        trdProductDeal.setFinprodId(dto.getProdId()+FinalString.LAYERING_SPLIT+dto.getChannelId());
        // 金融产品类型 = 数据字典：金融产品类型.计划类资产
        trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        // 分支序号 设置全局变量(默认为0）
        trdProductDeal.setBranch(DEFAULT_BRANCH);
        // 交易日 = B.支付日
        trdProductDeal.setTradeDate(dto.getPayDate());
        // 交割日 = B.支付日
        trdProductDeal.setSettleDate(dto.getPayDate());
        // 交易币种 = B.币种
        trdProductDeal.setCcy(dto.getCcy());
        // 交易份额 = B.实付费用
        trdProductDeal.setShareAmt(dto.getActPayAmt());
        // 交易本金 = B.实付费用
        trdProductDeal.setPrinAmt(dto.getActPayAmt());
        // 交易金额 = B.实付费用
        trdProductDeal.setTradeAmt(dto.getActPayAmt());

        // 交易方向 数据字典：交易方向.付费
        trdProductDeal.setPs(DictCodeList.交易方向_申购确认.getValue());

        // 交易状态
        if (DictCodeList.动作标志_新增.getValue().equals(dto.getDataActionSign())) {
            // "B.数据动作标志 eq 新增 --> 01:初始
            trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());

        }
        else if (DictCodeList.动作标志_删除.getValue().equals(dto.getDataActionSign())) {
            // B.数据动作标志 eq 撤销 --> 03:撤销"
            trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
        }
        // 是否结清 B.是否结算
        trdProductDeal.setIsClean(dto.getIsClean2());
        // 关联系统交易编号 = B.成交编号
        trdProductDeal.setfTradeId(dto.getTradeId());
        // 原交易编号 = 交易状态为撤销时： 原交易编号=调用共通设计->【详细设计-接口共通方法设计.xlsx】->撤销交易对应的原交易编号查询SHEET
        // 交易状态为非撤销时：原交易编号为空
        if (trdProductDeal.getTradeStatus().equals(DictCodeListExt1.交易状态_撤销.getValue())) {
            trdProductDeal.setpTradeId(interfCommonService.getFTradeId(trdProductDeal));
        }
        // 创建时间 = 系统时间
        trdProductDeal.setCreateTime(dto.getCreateTime());
        return trdProductDeal;
    }

}
