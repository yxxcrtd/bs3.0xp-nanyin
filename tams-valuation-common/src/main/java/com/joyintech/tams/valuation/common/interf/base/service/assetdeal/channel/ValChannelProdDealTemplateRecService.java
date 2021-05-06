/*
 * 文件名：ValChannelProdDealTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年3月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.assetdeal.channel;

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
import com.joyintech.tams.common.base.entity.JJBkCashAcct;
import com.joyintech.tams.common.base.entity.JJPortfolio;
import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.common.base.entity.StlOrdersExt;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.FinalString;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.InterfProductDealBookCommonDto;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.ValChannelProdDealDto;
import com.joyintech.tams.valuation.common.interf.base.dto.ValChannelTradeValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValChannelProdDeal;

/**
 * 理财产品借通道投资交易 模板处理Service(VAL_CHANNEL_PROD_DEAL)
 * 
 * @author zhangwei
 * @version 1.0 2019年3月12日
 * @see ValChannelProdDealTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValChannelProdDealTemplateRecService {

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
    public ValChannelTradeValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                  TemplateOrderInfoReaderResult acctEntity) {

        ValChannelTradeValCommonResult result = new ValChannelTradeValCommonResult();
        List<ValChannelProdDeal> listValChannelProdDeal = new ArrayList<ValChannelProdDeal>();
        Map<String,Object> params = acctEntity.getParams();
        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult<>();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValChannelProdDeal = returnResult.getT(ValChannelProdDeal.class);
        result.setDatanum(listValChannelProdDeal == null ? 0 : listValChannelProdDeal.size());
        if (listValChannelProdDeal == null) {
            listValChannelProdDeal = new ArrayList<ValChannelProdDeal>();
        }

        /**add by wujie on 20210506 start**/
        if(returnResult.isReadFile()){
            result.getParamMap().put(InterfCommonConstant.读取文件状态,DictCodeList.是否_是.getValue());
        }
        /**add by wujie on 20210506 end**/


        if(listValChannelProdDeal!= null && listValChannelProdDeal.size()>0){
        	// 3、根据有效数据，进行数据的编辑，并生成进入业务表的数据结构
            Map<String, Object> map = this.handleValChannelProdDeal(listValChannelProdDeal, acctEntity.getResovleDate(),params,returnResult.getValInterflog());
            if (map != null) {
                // 金融产品交易List
                List<TrdProductDeal> listValTrdProductDeal = (List<TrdProductDeal>)map.get("ValTrdProductDeal");
                result.setListValTrdProductDeal(listValTrdProductDeal);
                // 估值核算交易扩展表List
                List<TrdProductDealAdd> listValTrdProductDealExt = (List<TrdProductDealAdd>)map.get("ValTrdProductDealExt");
                result.setListValTrdProductDealExt(listValTrdProductDealExt);
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
                
                /**add by wujie on 20190321 start**/
                // 结算指令
                List<StlOrders> listValOrders = (List<StlOrders>)map.get("listValOrders");
                result.setListValOrders(listValOrders);
                
                // 结算指令扩展表
                List<StlOrdersExt> listValOrdersExt =(List<StlOrdersExt>)map.get("listValOrdersExt");
                result.setListValOrdersExt(listValOrdersExt);
                
                /**
                 * add by zhangquan 20200107 end
                 */
                //变量.接口模板处理返回共通结果DTO.几许产品信息List.addAll(变量.解析结果Map.get("JJPortfolio_List"))
                List<JJPortfolio> jJPortfolioList = (List<JJPortfolio>)map.get("JJPortfolio_List");
                result.setjJPortfolioList(jJPortfolioList);
                //变量.接口模板处理返回共通结果DTO.几许产品资金账户信息List.addAll(变量.解析结果Map.get("JJBkCashAcct_List"))
                List<JJBkCashAcct> jJBkCashAcctList = (List<JJBkCashAcct>)map.get("JJBkCashAcct_List");
                result.setjJBkCashAcctList(jJBkCashAcctList);
                
                /**add by wujie on 20190321 end**/

                /** modify by wujie on 20190321
                // 提供 理财产品借通道投资交易 调用共通设计->【详细设计-接口共通方法设计.xlsx】->初始化通道核算信息SHEET
                List<ValChannelProdDealDto> listValChannelProdDealDto = (List<ValChannelProdDealDto>)map.get(
                    "ValChannelProdDealDto");
                result.setListValChannelProdDealDto(listValChannelProdDealDto);

                // 提供 接口日期 调用共通设计->【详细设计-接口共通方法设计.xlsx】->初始化通道核算信息SHEET
                result.setResovleDate(acctEntity.getResovleDate());
                **/
                // 添加日志
                result.setValInterflog((List<ValInterflog>)map.get("valInterflogList"));//add by dongjun on 20190225
            }
        }else{
            result.setValInterflog(returnResult.getValInterflog());
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> handleValChannelProdDeal(List<ValChannelProdDeal> listValChannelProdDeal,
                                                         Date resovleDate,Map<String,Object> params,List<ValInterflog> valInterflog) {
		List<String> bookSetIds = (List<String>)params.get(InterfCommonConstant.接口_运行接口账套ID);
		String valModel = (String)params.get(InterfCommonConstant.接口模式);
        // 初始化返回结果
        Map<String, Object> resultMap = new HashMap<>();
        // 金融产品交易List
        List<TrdProductDeal> listValTrdProductDeal = new ArrayList<TrdProductDeal>();
        // 估值核算交易扩展表List
        List<TrdProductDealAdd> listValTrdProductDealExt = new ArrayList<TrdProductDealAdd>();
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

        /**modify by wujie on 20190321
        // 理财产品借通道投资交易
        List<ValChannelProdDealDto> listValChannelProdDealDto = new ArrayList<>();
        **/
        
        /**add by wujie on 20190320 start**/
        
        List<ValInterflog> valInterflogList =  new ArrayList<>();
        List<ValChannelProdDealDto> initChannelAccountInfoList = new ArrayList<ValChannelProdDealDto>();
        for (ValChannelProdDeal dto : listValChannelProdDeal) {
        	
        	if(StringUtil.equals(valModel, InterfCommonConstant.接口模式_指定账套) && !bookSetIds.contains(dto.getProdId())){
        		continue;
        	}
            dto.setCreateTime(resovleDate);
            
        	ValChannelProdDealDto newDto = new ValChannelProdDealDto();
        	//通道代码
        	newDto.setChlId(dto.getChannelId());
        	//产品代码
        	newDto.setProdId(dto.getProdId());
        	//交易状态
            if (DictCodeList.动作标志_新增.getValue().equals(dto.getDataActionSign())) {
                // "A.数据动作标志 eq 新增 --> 01:初始
            	newDto.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());

            }
            else if (DictCodeList.动作标志_删除.getValue().equals(dto.getDataActionSign())) {
                // A.数据动作标志 eq 撤销 --> 03:撤销"
            	newDto.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
            }
            //交易日
            newDto.setTradeDate(dto.getDealDate());
            //交易场所
            newDto.setTradeMarket(dto.getTradeMarket());
            //币种
            newDto.setCcy(dto.getCcy());
            
            
            newDto.setCreateTime(dto.getCreateTime());
            
        	initChannelAccountInfoList.add(newDto);
        }
        Map<String,Object> initResultMap = interfCommonService.initChanneAaccounting(initChannelAccountInfoList,resovleDate);
        
        /**add by zhangquan 20200107 end**/
        if(initResultMap.get("JJPortfolio_List") !=null) {
        	resultMap.put("JJPortfolio_List", initResultMap.get("JJPortfolio_List"));
        }
        if(initResultMap.get("JJBkCashAcct_List") !=null) {
        	resultMap.put("JJBkCashAcct_List", initResultMap.get("JJBkCashAcct_List"));
        }
        /**add by wujie on 20190320 end**/

        for (ValChannelProdDeal dto : listValChannelProdDeal) {
        	
        	if(StringUtil.equals(valModel, InterfCommonConstant.接口模式_指定账套) && !bookSetIds.contains(dto.getProdId())){
        		continue;
        	}
        	
            dto.setCreateTime(resovleDate);// 用于去重操作
            
            /**1.理财产品交易**/
            // T12 金融产品交易（理财产品交易）
            TrdProductDeal valTrdProductDeal2 = this.getValTrdProductDeal2(dto);
            listValTrdProductDeal.add(valTrdProductDeal2);
            
            // T14 估值核算交易扩展表（理财产品交易）
            TrdProductDealAdd trdProductDealExt2 = this.getValTrdProductDealExt2(dto, valTrdProductDeal2);
            listValTrdProductDealExt.add(trdProductDealExt2);
            
            
            // 【详细设计-接口共通方法设计.xlsx】->交易入账SHEET（理财产品交易）
            InterfProductDealBookCommonDto dealAccounting2 = interfCommonService.dealAccounting(valTrdProductDeal2,
                dto.getProdId());


            /**add by wujie on 20190625 start**/
            //四级科目尾号
            dealAccounting2.getBokDetailId().setDetailSubjectNoTail(valTrdProductDeal2.getFinprodId());
            //四级科目尾号名称
            dealAccounting2.getBokDetailId().setDetailSubnameTail(dto.getChannelName());
            /**add by wujie on 20190625 end**/


            listValDealAccounting.add(dealAccounting2.getBokDealAccounting());
            listValDealAccountingDetail.add(dealAccounting2.getBokDealAccountingDetail());
            listValBokDetailId.add(dealAccounting2.getBokDetailId());
            
            // 调用共通设计->【详细设计-接口共通方法设计.xlsx】->通过交易生成指令及指令入账SHEET（理财产品交易）
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
            
            
            
            /**2.通道产品交易**/
            // T11 金融产品交易(通道产品交易）
            TrdProductDeal valTrdProductDeal1 = this.getValTrdProductDeal1(dto);
            //T11.汇总交易编号 = T12.交易编号
            valTrdProductDeal1.setTotleTradeId(valTrdProductDeal2.getTradeId());
            listValTrdProductDeal.add(valTrdProductDeal1);

            // T13 估值核算交易扩展表(通道产品交易）
            TrdProductDealAdd trdProductDealExt1 = this.getValTrdProductDealExt1(dto, valTrdProductDeal1);
            listValTrdProductDealExt.add(trdProductDealExt1);

            // 【详细设计-接口共通方法设计.xlsx】->交易入账SHEET(通道产品交易）
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


            // 调用共通设计->【详细设计-接口共通方法设计.xlsx】->通过交易生成指令及指令入账SHEET(通道产品交易）
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

            /**modify by wujie on 20190320
            // 理财产品借通道投资交易(只针对通道产品交易）
            ValChannelProdDealDto valChannelProdDealDto = this.getValChannelProdDealDto(valTrdProductDeal1);
            if (valChannelProdDealDto != null) {
                listValChannelProdDealDto.add(valChannelProdDealDto);
            }
			**/
        }

        resultMap.put("ValTrdProductDeal", listValTrdProductDeal);
        resultMap.put("ValTrdProductDealExt", listValTrdProductDealExt);

        resultMap.put("ValDealAccounting", listValDealAccounting);
        resultMap.put("ValDealAccountingDetail", listValDealAccountingDetail);
        resultMap.put("ValBokDetailId", listValBokDetailId);
        
        
        resultMap.put("listValOrders", listValOrders);
        resultMap.put("listValOrdersExt", listValOrdersExt);

        /**modify by wujie on 20190321
        resultMap.put("listValChannelProdDealDto", listValChannelProdDealDto);
        **/
        valInterflogList = interfCommonService.filterValInterflogList(valInterflog, bookSetIds, valModel);
    	resultMap.put("valInterflogList", valInterflogList);
        return resultMap;
    }
//modify by wujie on 20190320
//    /**
//     * 理财产品借通道投资交易(只针对通道产品交易）
//     * 
//     * @param valTrdProductDeal
//     * @return
//     */
//    private ValChannelProdDealDto getValChannelProdDealDto(TrdProductDeal valTrdProductDeal) {
//        // 通道申购、赎回交易
//        if (DictCodeList.交易方向_产品申购.getValue().equals(valTrdProductDeal.getPs())
//            || DictCodeList.交易方向_产品赎回.getValue().equals(valTrdProductDeal.getPs())) {
//            // 初始化 金融产品交易 返回值
//            ValChannelProdDealDto valChannelProdDealDto = new ValChannelProdDealDto();
//
//            // 交易编号
//            valChannelProdDealDto.setTradeId(valTrdProductDeal.getTradeId());
//            // 产品代码 = A.产品代码
//            valChannelProdDealDto.setProdId(valTrdProductDeal.getFinprodId().split(FinalString.LAYERING_SPLIT)[0]);
//            // 通道代码
//            valChannelProdDealDto.setChlId(valTrdProductDeal.getChlId());
//            // 交易状态
//            valChannelProdDealDto.setTradeStatus(valTrdProductDeal.getTradeStatus());
//            // 交易方向
//            valChannelProdDealDto.setPs(valTrdProductDeal.getPs());
//            // 交易日
//            valChannelProdDealDto.setTradeDate(valTrdProductDeal.getTradeDate());
//            // 交易场所
//            valChannelProdDealDto.setTradeMarket(valTrdProductDeal.getTradeMarket());
//            // 交易币种 = A.币种
//            valChannelProdDealDto.setCcy(valTrdProductDeal.getCcy());
//            // 原交易编号
//            valChannelProdDealDto.setpTradeId(valTrdProductDeal.getpTradeId());
//            // 金融产品代码 = A.产品代码+'_'+ A.通道代码
//            valChannelProdDealDto.setFinprodId(valTrdProductDeal.getFinprodId());
//            return valChannelProdDealDto;
//        }
//        return null;
//    }

    /**
     * T13: 估值核算交易扩展表（通道产品交易）
     * 
     * @param dto
     * @param deal
     * @return
     */
    private TrdProductDealAdd getValTrdProductDealExt1(ValChannelProdDeal dto, TrdProductDeal deal) {
    	TrdProductDealAdd trdProductDealExt = new TrdProductDealAdd();
        // 交易编号 = T11.交易编号
        trdProductDealExt.setTradeId(deal.getTradeId());

        /**modify by wujie on 20190321 start
        // 投资组合代码 = A.产品代码
        trdProductDealExt.setPortfolioId(dto.getProdId());
        // 理财产品代码 = A.产品代码
        trdProductDealExt.setProdId(dto.getProdId());**/
        
        // 投资组合代码 = A.产品代码+'_'+A.通道代码
        trdProductDealExt.setPortfolioId(dto.getProdId() + FinalString.LAYERING_SPLIT + dto.getChannelId());
//        // 理财产品代码 = A.产品代码+'_'+A.通道代码
//        trdProductDealExt.setProdId(dto.getProdId() + FinalString.LAYERING_SPLIT + dto.getChannelId());//一体化无对应表字段
        
        /**modify by wujie on 20190321 end**/

        // 是否有结算指令 = A.有无清算指令
//        trdProductDealExt.setHasOrder(dto.getIsClean());//一体化表无此字段
        // 生成方式 = 固定值 录入方式.接口导入
        trdProductDealExt.setGenType(DictCodeListExt1.录入方式_接口导入.getValue());
        return trdProductDealExt;
    }

    /**
     * T14: 估值核算交易扩展表（理财产品交易）
     * 
     * @param dto
     * @param deal
     * @return
     */
    private TrdProductDealAdd getValTrdProductDealExt2(ValChannelProdDeal dto, TrdProductDeal deal) {
    	TrdProductDealAdd trdProductDealExt = new TrdProductDealAdd();
        // 交易编号 = T11.交易编号
        trdProductDealExt.setTradeId(deal.getTradeId());

        /**modify by wujie on 20190321 start
        // 投资组合代码 = A.产品代码+'_'+A.通道代码
        trdProductDealExt.setPortfolioId(dto.getProdId() + FinalString.LAYERING_SPLIT + dto.getChannelId());
        // 理财产品代码 = A.产品代码+'_'+A.通道代码
        trdProductDealExt.setProdId(dto.getProdId() + FinalString.LAYERING_SPLIT + dto.getChannelId());
        **/
        trdProductDealExt.setPortfolioId(dto.getProdId());
//        trdProductDealExt.setProdId(dto.getProdId()); 一体化无对应字段
        /**modify by wujie on 20190321 end**/
        // 是否有结算指令 = A.有无清算指令
//        trdProductDealExt.setHasOrder(dto.getIsClean()); 一体化无对应字段
        // 生成方式 = 固定值 录入方式.接口导入
        trdProductDealExt.setGenType(DictCodeListExt1.录入方式_接口导入.getValue());
        return trdProductDealExt;
    }

    /**
     * T11: 金融产品交易 （通道产品交易）
     * 
     * @param dto
     * @return
     */
    private TrdProductDeal getValTrdProductDeal1(ValChannelProdDeal dto) {

        // 初始化 金融产品交易 返回值
        TrdProductDeal trdProductDeal = new TrdProductDeal();

        // 业务类型 = 数据字典：业务类型.货币型TA交易
        trdProductDeal.setBusiType(DictCodeList.业务类型_货币型TA交易.getValue());
        
        // 金融产品代码 = A.产品代码+'_'+A.通道代码
        trdProductDeal.setFinprodId(dto.getProdId() + FinalString.LAYERING_SPLIT + dto.getChannelId());
        // 金融产品类型 = 数据字典：金融产品类型.通道
        trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_通道.getValue());
        // 分支序号 = 设置全局变量(默认为0）
        trdProductDeal.setBranch(InterfCommonConstant.分支序号_默认);
        // 交易日 = A.交易日
        trdProductDeal.setTradeDate(dto.getDealDate());
        // 交割日 = A.交割日
        trdProductDeal.setSettleDate(dto.getBusiDate());
        // 交易币种 = A.币种
        trdProductDeal.setCcy(dto.getCcy());
        // 交易份额 = A.交易份额
        trdProductDeal.setShareAmt(dto.getTradeQty());
        // 交易本金 = A.交易份额
        trdProductDeal.setPrinAmt(dto.getTradeQty());
        // 交易金额 = A.交易金额
        trdProductDeal.setTradeAmt(dto.getDealAmt());

        // "IF 数据字典：是否.否 eq A.通道费是否随交易支付
        if (DictCodeList.是否_否.getValue().equals(dto.getIsChlFeeAmt())) {
            // 未随交易支付费用 = A.通道费
            trdProductDeal.setFeeAmtUnpay(dto.getChlFeeAmt());
            // 总交易费用 = A.通道费
            trdProductDeal.setFeeAmt(dto.getChlFeeAmt());
        }

        // "IF A.交易方向 eq 申购、买入
        if (DictCodeList.交易方向_申购.getValue().equals(dto.getPs())
            || DictCodeList.交易方向_买入.getValue().equals(dto.getPs())) {
            // 交易方向 = 数据字典：交易方向.产品申购
            trdProductDeal.setPs(DictCodeList.交易方向_产品申购.getValue());
        }
        else if (DictCodeList.交易方向_赎回.getValue().equals(dto.getPs())
                 || DictCodeList.交易方向_卖出.getValue().equals(dto.getPs())) {
            // ELSE A.交易方向 eq 赎回、卖出
            // 交易方向 = 数据字典：交易方向.产品赎回
            trdProductDeal.setPs(DictCodeList.交易方向_产品赎回.getValue());
        }

        // 交易编号 = 调用共通方法生成
        trdProductDeal.setTradeId(interfCommonService.getTradeid(trdProductDeal.getPs(), dto.getBusiDate(), "A"));
        
        // 交易状态
        if (DictCodeList.动作标志_新增.getValue().equals(dto.getDataActionSign())) {
            // "A.数据动作标志 eq 新增 --> 01:初始
            trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());

        }
        else if (DictCodeList.动作标志_删除.getValue().equals(dto.getDataActionSign())) {
            // A.数据动作标志 eq 撤销 --> 03:撤销"
            trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
        }

        // 通道代码 = A.通道代码
        trdProductDeal.setChlId(dto.getChannelId());
        // 交易场所 = A.交易场所
        trdProductDeal.setTradeMarket(dto.getTradeMarket());
        // 关联系统交易编号 = A.成交编号
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
     * T12: 金融产品交易（理财产品交易）
     * 
     * @param dto
     * @return
     */
    private TrdProductDeal getValTrdProductDeal2(ValChannelProdDeal dto) {

        // 初始化 金融产品交易 返回值
        TrdProductDeal trdProductDeal = new TrdProductDeal();

        // 业务类型 = 数据字典：业务类型.货币基金交易
        trdProductDeal.setBusiType(DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
        
        // 金融产品代码 = A.产品代码+'_'+A.通道代码
        trdProductDeal.setFinprodId(dto.getProdId() + FinalString.LAYERING_SPLIT + dto.getChannelId());
        // 金融产品类型 = 数据字典：金融产品类型.计划类资产
        trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        // 分支序号 = 设置全局变量(默认为0）
        trdProductDeal.setBranch(InterfCommonConstant.分支序号_默认);
        // 交易日 = A.交易日
        trdProductDeal.setTradeDate(dto.getDealDate());
        // 交割日 = A.交割日
        trdProductDeal.setSettleDate(dto.getBusiDate());
        // 交易币种 = A.币种
        trdProductDeal.setCcy(dto.getCcy());
        // 交易份额 = A.交易份额
        trdProductDeal.setShareAmt(dto.getTradeQty());
        // 交易本金 = A.交易份额
        trdProductDeal.setPrinAmt(dto.getTradeQty());
        // 交易金额 = A.交易金额
        trdProductDeal.setTradeAmt(dto.getDealAmt());

        // "IF A.交易方向 eq 申购
        if (DictCodeList.交易方向_申购.getValue().equals(dto.getPs())) {
            // 交易方向 = 数据字典：交易方向.申购确认
            trdProductDeal.setPs(DictCodeList.交易方向_申购确认.getValue());
        }
        else if (DictCodeList.交易方向_赎回.getValue().equals(dto.getPs())) {
            // ELSE A.交易方向 eq 赎回
            // 交易方向 = 数据字典：交易方向.赎回确认
            trdProductDeal.setPs(DictCodeList.交易方向_赎回确认.getValue());
        }
        if (DictCodeList.交易方向_买入.getValue().equals(dto.getPs())
            || DictCodeList.交易方向_卖出.getValue().equals(dto.getPs())) {
            // ELSE A.交易方向 eq 买入、卖出
            // 交易方向 = A.交易方向
            trdProductDeal.setPs(dto.getPs());
        }
        
        // 交易编号 = 调用共通方法生成
        trdProductDeal.setTradeId(interfCommonService.getTradeid(trdProductDeal.getPs(), dto.getBusiDate(), "A"));

        // 交易状态
        if (DictCodeList.动作标志_新增.getValue().equals(dto.getDataActionSign())) {
            // "A.数据动作标志 eq 新增 --> 01:初始
            trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());

        }
        else if (DictCodeList.动作标志_删除.getValue().equals(dto.getDataActionSign())) {
            // A.数据动作标志 eq 撤销 --> 03:撤销"
            trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
        }

        // 交易场所 = A.交易场所
        trdProductDeal.setTradeMarket(dto.getTradeMarket());
        // 关联系统交易编号 = A.成交编号
        trdProductDeal.setfTradeId(dto.getTradeId());
        //汇总交易编号 = T12.交易编号
        trdProductDeal.setTotleTradeId(trdProductDeal.getTradeId());
        
        trdProductDeal.setCreateTime(dto.getCreateTime());
        
        // 原交易编号 = 交易状态为撤销时： 原交易编号=调用共通设计->【详细设计-接口共通方法设计.xlsx】->撤销交易对应的原交易编号查询SHEET
        // 交易状态为非撤销时：原交易编号为空
        if (trdProductDeal.getTradeStatus().equals(DictCodeListExt1.交易状态_撤销.getValue())) {
            trdProductDeal.setpTradeId(interfCommonService.getFTradeId(trdProductDeal));
        }
        return trdProductDeal;
    }
}
