/*
 * 文件名：PlannedReceicableTradeTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chendijun
 * 修改时间：2018年8月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.assetdeal.mngasset;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.common.base.entity.StlOrdersExt;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.interf.base.constant.FinalString;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.ChannelFeeInfoDeal;
import com.joyintech.tams.valuation.common.interf.base.dto.InterfProductDealBookCommonDto;
import com.joyintech.tams.valuation.common.interf.base.dto.PlannedAssetDealCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TargetFinProduct;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngReceicableTrade;
import com.joyintech.framework.util.StringUtil;

/**
 * 计划类资产利率型应收款项转让交易模板处理 〈功能详细描述〉
 * 
 * @author chendijun
 * @version 1.0 2018年8月9日
 * @see PlannedReceicableTradeTemplateRecService
 * @since 1.0
 */

@Service
@Scope("prototype")
public class PlannedReceicableTradeTemplateRecService {

    /**
     * 基本信息模板处理service
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    /**
     * 接口共通方法
     */
    @Autowired
    private InterfCommonService interfCommonService;

    /**
     * @param templateSourceFlgDTO 模板输出DTO
     * @param acctEntity 计提基础信息DTO
     * @return 计划类交易共同结果
     * @see
     * @since 1.0
     */

    @SuppressWarnings({"unchecked", "rawtypes"})
    public PlannedAssetDealCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回结果
        PlannedAssetDealCommonResult result = new PlannedAssetDealCommonResult();
        List<ValMngReceicableTrade> listValMngReceicableTrade = new ArrayList<ValMngReceicableTrade>();
        Map<String,Object> params = acctEntity.getParams();
        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult<>();
        // 1.文件解析读取
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValMngReceicableTrade = returnResult.getT(ValMngReceicableTrade.class);
        
        //获取数据数量
        result.setDataNum(listValMngReceicableTrade.size());

        /**add by wujie on 20210506 start**/
        if(returnResult.isReadFile()){
            result.getParamMap().put(InterfCommonConstant.读取文件状态,DictCodeList.是否_是.getValue());
        }
        /**add by wujie on 20210506 end**/

        // 3、根据有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleListValMngReceicableTrade(listValMngReceicableTrade,
            acctEntity.getResovleDate(),params,returnResult.getValInterflog());
        
        if(map != null) {
            result.setListValFinProductDeal((List<TrdProductDeal>)map.get("valFinProductDeal")); 
            result.setListValFinProductDealExt((List<TrdProductDealAdd>)map.get("valFinProductDealExt"));
            result.setListValTradeFinProduct((List<FinTradeProduct>)map.get("valFinTradeProduct"));
            result.setListValDealAccounting((List<BokDealAccounting>)map.get("ValDealAccounting"));
            result.setListValDealAccountingDetail((List<BokDealAccountingDetail>)map.get("ValDealAccountingDetail"));
            result.setListValBokDetailId((List<BokDetailId>)map.get("ValBokDetailId"));
            result.setValOrdersList((List<StlOrders>)map.get("valOrdersList"));
            result.setValOrdersExtList((List<StlOrdersExt>)map.get("valOrdersExtList"));
            result.setValInterflog((List<ValInterflog>)map.get("valInterflogList"));
        }
        return result;

    }

    @SuppressWarnings({"unchecked"})
	private Map<String, Object> handleListValMngReceicableTrade(List<ValMngReceicableTrade> listValMngReceicableTrade,
                                                                Date resovleDate,Map<String,Object> params,List<ValInterflog> valInterflog) {
        List<String> bookSetIds = (List<String>)params.get(InterfCommonConstant.接口_运行接口账套ID);
    	String valModel = (String)params.get(InterfCommonConstant.接口模式);
        // 初始化返回结果
        Map<String, Object> map = new HashMap<>();
        // 金融产品交易
        List<TrdProductDeal> valFinProductDealList = new ArrayList<TrdProductDeal>();
        // 估值核算交易扩展
        List<TrdProductDealAdd> valFinProductDealExtList = new ArrayList<TrdProductDealAdd>();

        // 交易类金融产品
        List<FinTradeProduct> valFinTradeProductList = new ArrayList<FinTradeProduct>();
        // 入账明细
        List<BokDealAccounting> listValDealAccounting = new ArrayList<BokDealAccounting>();
        // 账务明细表List
        List<BokDealAccountingDetail> listValDealAccountingDetail = new ArrayList<BokDealAccountingDetail>();
        // 账务明细代码表
        List<BokDetailId> listValBokDetailId = new ArrayList<BokDetailId>();
        List<StlOrders> listValOrders = new ArrayList<StlOrders>();
        List<StlOrdersExt> listValOrdersExt = new ArrayList<StlOrdersExt>();
        Map<String, Long> maxBranchMap = new HashMap<String, Long>();
        
        List<ValInterflog> valInterflogList =  new ArrayList<>();
        
        /**ADD BY WUJIE ON 20190812 START**/
        Map<String,TargetFinProduct> assetMap = new HashMap<String,TargetFinProduct>();
        /**ADD BY WUJIE ON 20190812 END**/
        
        for (ValMngReceicableTrade dto : listValMngReceicableTrade) {
        	if(StringUtil.equals(InterfCommonConstant.接口模式_指定账套,valModel) && !bookSetIds.contains(dto.getProdId())){
        		continue;
        	}
            dto.setCreateTime(resovleDate);// remark:用于去重复数据
            
            String acctSubjectType = null;
            
            TrdProductDeal valFinProductDeal = null;
            //本金
            if(dto.getCapital()!=null && BigDecimal.ZERO.compareTo(dto.getCapital())!=0){
            	
            	valFinProductDeal = this.getValFinProductDeal(dto,DictCodeList.分支类型_应收本金.getValue());
                
                FinTradeProduct valFinTradeProduct = this.getValFinTradeProduct(valFinProductDeal,DictCodeList.分支类型_应收本金.getValue());

                Long maxBranch = interfCommonService.getBranch(valFinProductDeal.getMdate(),DictCodeList.分支类型_应收本金.getValue());
                valFinProductDeal.setBranch(maxBranch);
                //20181012 leipeng 原交易编号 = 交易状态为撤销时： 原交易编号=调用共通设计->【详细设计-接口共通方法设计.xlsx】->撤销交易对应的原交易编号查询SHEET 交易状态为非撤销时：原交易编号为空
                if(valFinProductDeal.getTradeStatus().equals(DictCodeListExt1.交易状态_撤销.getValue())){
                	valFinProductDeal.setpTradeId(interfCommonService.getFTradeId(valFinProductDeal));
                }
                
                String mapKey = valFinProductDeal.getFinprodId() + valFinProductDeal.getBranch();
                //查询数据库中是否存在
                Long branch = maxBranchMap.get(mapKey);
                if(branch==null){
                	branch = interfCommonService.queryValFinProductDealBranchNow(
                            valFinProductDeal.getFinprodId(), valFinProductDeal.getFinprodType(),
                            DictCodeList.分支类型_应收本金.getValue(), valFinProductDeal.getMdate());
                }
                if(branch==null || (branch != null && branch.compareTo(maxBranch) != 0)){
                	// 往 【T9: 交易类金融产品】插入数据
                    valFinTradeProduct.setBranch(maxBranch);
                    valFinTradeProductList.add(valFinTradeProduct);  
                }
                maxBranchMap.put(mapKey, maxBranch);
                
                valFinProductDealList.add(valFinProductDeal);
                
                // 入账信息表 账务明细表调用共通
                InterfProductDealBookCommonDto dealAccounting = interfCommonService.dealAccounting(
                    valFinProductDeal, dto.getProdId());
                
                TrdProductDealAdd valtrdProductDealExt = this.getValFinProductDealExt(dto,
                        valFinProductDeal,dealAccounting.getAcctSubjectType());
                valFinProductDealExtList.add(valtrdProductDealExt);
                
                //入账信息表List
                listValDealAccounting.add(dealAccounting.getBokDealAccounting());
                // 账务明细表List
                listValDealAccountingDetail.add(dealAccounting.getBokDealAccountingDetail());
                
                //四级科目尾号
                dealAccounting.getBokDetailId().setDetailSubjectNoTail(dto.getAssetId());
                
                TargetFinProduct item = assetMap.get(dto.getAssetId());
                if(item==null){
                	item = interfCommonService.getTargetFinProduct(dto.getAssetId());
                	//四级科目尾号名称
                	if(item!=null){
                        dealAccounting.getBokDetailId().setDetailSubnameTail(item.getFinprodName());
                        assetMap.put(dto.getAssetId(), item);
                    }
                }
                
                // 账务明细代码表
                listValBokDetailId.add(dealAccounting.getBokDetailId()) ;
                
                
                Map<String, Object> maps = interfCommonService.instructionGenerate(valFinProductDeal, 
                		dto.getProdId(), dealAccounting.getAcctSubjectType(),null);
                if(maps.get("ORDERS")!=null){
                	listValOrders.addAll((List<StlOrders>)maps.get("ORDERS"));
                }
                if(maps.get("ORDERS_EXT")!=null){
                	listValOrdersExt.addAll((List<StlOrdersExt>)maps.get("ORDERS_EXT"));
                }
                if(maps.get("ACCOUNTING")!=null){
                	 listValDealAccounting.addAll((List<BokDealAccounting>)maps.get("ACCOUNTING"));
                }
                if(maps.get("ACCOUNTING_DETAI")!=null){
                	listValDealAccountingDetail.addAll(( List<BokDealAccountingDetail>)maps.get("ACCOUNTING_DETAI"));
                }
                if(maps.get("BOK_DETAIL_ID")!=null){
                	listValBokDetailId.addAll((List<BokDetailId>)maps.get("BOK_DETAIL_ID"));
                }
                
                acctSubjectType = dealAccounting.getAcctSubjectType();
            }
            
            if(dto.getIntAmt()!=null && BigDecimal.ZERO.compareTo(dto.getIntAmt())!=0){
            	
            	valFinProductDeal = this.getValFinProductDeal(dto,DictCodeList.分支类型_应收利息.getValue());
                
                FinTradeProduct valFinTradeProduct = this.getValFinTradeProduct(valFinProductDeal,DictCodeList.分支类型_应收利息.getValue());

                Long maxBranch = interfCommonService.getBranch(valFinProductDeal.getMdate(),
                    DictCodeList.分支类型_应收利息.getValue());
                valFinProductDeal.setBranch(maxBranch);
                //20181012 leipeng 原交易编号 = 交易状态为撤销时： 原交易编号=调用共通设计->【详细设计-接口共通方法设计.xlsx】->撤销交易对应的原交易编号查询SHEET 交易状态为非撤销时：原交易编号为空
                if(valFinProductDeal.getTradeStatus().equals(DictCodeListExt1.交易状态_撤销.getValue())){
                	valFinProductDeal.setpTradeId(interfCommonService.getFTradeId(valFinProductDeal));
                }
                
                String mapKey = valFinProductDeal.getFinprodId() + valFinProductDeal.getFinprodType()+new SimpleDateFormat("yyyyMMdd").format(valFinProductDeal.getMdate());
                //查询数据库中是否存在
                Long branch = maxBranchMap.get(mapKey);
                if(branch==null){
                	branch = interfCommonService.queryValFinProductDealBranchNow(
                            valFinProductDeal.getFinprodId(), valFinProductDeal.getFinprodType(),
                            DictCodeList.分支类型_应收利息.getValue(), valFinProductDeal.getMdate());
                }
                if(branch==null || (branch != null && branch.compareTo(maxBranch) != 0)){
                	// 往 【T9: 交易类金融产品】插入数据
                    valFinTradeProduct.setBranch(maxBranch);
                    valFinTradeProductList.add(valFinTradeProduct);  
                }
                maxBranchMap.put(mapKey, maxBranch);
                
                valFinProductDealList.add(valFinProductDeal);
                
                // 入账信息表 账务明细表调用共通
                InterfProductDealBookCommonDto dealAccounting = interfCommonService.dealAccounting(
                    valFinProductDeal, dto.getProdId());
                
                TrdProductDealAdd valtrdProductDealExt = this.getValFinProductDealExt(dto,
                        valFinProductDeal,dealAccounting.getAcctSubjectType());
                valFinProductDealExtList.add(valtrdProductDealExt);
                
                //入账信息表List
                listValDealAccounting.add(dealAccounting.getBokDealAccounting());
                // 账务明细表List
                listValDealAccountingDetail.add(dealAccounting.getBokDealAccountingDetail());
                
                //四级科目尾号
                dealAccounting.getBokDetailId().setDetailSubjectNoTail(dto.getAssetId());
                
                TargetFinProduct item = assetMap.get(dto.getAssetId());
                if(item==null){
                	item = interfCommonService.getTargetFinProduct(dto.getAssetId());
                	//四级科目尾号名称
                	if(item!=null){
                        dealAccounting.getBokDetailId().setDetailSubnameTail(item.getFinprodName());
                        assetMap.put(dto.getAssetId(), item);
                    }
                }
                
                // 账务明细代码表
                listValBokDetailId.add(dealAccounting.getBokDetailId()) ;
                
                
                Map<String, Object> maps = interfCommonService.instructionGenerate(valFinProductDeal, 
                		dto.getProdId(), dealAccounting.getAcctSubjectType(),null);
                if(maps.get("ORDERS")!=null){
                	listValOrders.addAll((List<StlOrders>)maps.get("ORDERS"));
                }
                if(maps.get("ORDERS_EXT")!=null){
                	listValOrdersExt.addAll((List<StlOrdersExt>)maps.get("ORDERS_EXT"));
                }
                if(maps.get("ACCOUNTING")!=null){
                	 listValDealAccounting.addAll((List<BokDealAccounting>)maps.get("ACCOUNTING"));
                }
                if(maps.get("ACCOUNTING_DETAI")!=null){
                	listValDealAccountingDetail.addAll(( List<BokDealAccountingDetail>)maps.get("ACCOUNTING_DETAI"));
                }
                if(maps.get("BOK_DETAIL_ID")!=null){
                	listValBokDetailId.addAll((List<BokDetailId>)maps.get("BOK_DETAIL_ID"));
                }
            	
                
                acctSubjectType = dealAccounting.getAcctSubjectType();
                
            }
            
            
            ChannelFeeInfoDeal channelFeeInfoDeal = this.getChannelFeeInfoDeal(dto, acctSubjectType);
            if(channelFeeInfoDeal != null){
                Map<String,Object> feedealMap = interfCommonService.assetChlFeeLend(valFinProductDeal, channelFeeInfoDeal);
                
                if(feedealMap.get("TrdProductDeal")!=null){
                    valFinProductDealList.add((TrdProductDeal)feedealMap.get("TrdProductDeal"));
                }
                
                if(feedealMap.get("TrdProductDealExt")!=null){
                    valFinProductDealExtList.add((TrdProductDealAdd)feedealMap.get("TrdProductDealExt"));
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
        }

        map.put("valFinProductDeal",valFinProductDealList);
        map.put("valFinProductDealExt",valFinProductDealExtList);
        map.put("valFinTradeProduct",valFinTradeProductList);
        map.put("ValDealAccounting",listValDealAccounting);
        map.put("ValDealAccountingDetail",listValDealAccountingDetail);
        map.put("ValBokDetailId",listValBokDetailId);
        map.put("valOrdersList", listValOrders);
        map.put("valOrdersExtList", listValOrdersExt); 
        
        valInterflogList = interfCommonService.filterValInterflogList(valInterflog, bookSetIds, valModel);
        map.put("valInterflogList", valInterflogList);
        
        return map;
    }
    
    /**
     * 通道投资资产交易拆分_通道费用
     * add by wujie on 20190322 start
     */
    private ChannelFeeInfoDeal getChannelFeeInfoDeal(ValMngReceicableTrade dto,String acctSubjectType){
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
     * T1: 金融产品交易 接收表
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private TrdProductDeal getValFinProductDeal(ValMngReceicableTrade dto,String branchType) {
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        // 交易编号 = 自动生成,调用共同方法生成
        trdProductDeal.setTradeId(
            interfCommonService.getTradeid(dto.getPs(), dto.getTradeDate(), "A"));
        // 业务类型 = 数据字典：业务类型.计划类资产交易
        trdProductDeal.setBusiType(DictCodeList.业务类型_计划类资产交易_类债券资产交易.getValue());
        // 金融产品代码 = C.资产代码
        trdProductDeal.setFinprodId(dto.getAssetId());
        // 金融产品类型 = 数据字典：金融产品类型.计划类资产
        trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        // 分支序号 = 调用共通设计->标的类共通方法:当前计划对应分支序号查询

        // 交易日 = C.交易日
        trdProductDeal.setTradeDate(dto.getTradeDate());
        // 起息日 = C.起息日
        trdProductDeal.setVdate(dto.getVdate());
        // 到期日 = C.到期日
        trdProductDeal.setMdate(dto.getMdate());
        // 交割日 = C.交割日
        trdProductDeal.setSettleDate(dto.getEffDate());
        // 交易币种 = C.币种
        trdProductDeal.setCcy(dto.getCcy());
        
//        // 交易本金 = C.本金金额
//        trdProductDeal.setPrinAmt(dto.getCapital());
//        // 利息总额 = C.应收利息总额
//        trdProductDeal.setIntAmt(dto.getIntAmt());
//        // 交易金额 = C.本金金额+C.应收利息总额
//        trdProductDeal.setTradeAmt(
//            ArithmeticUtil.doAdd(2, null, dto.getCapital(), dto.getIntAmt()));
        if(DictCodeList.分支类型_应收利息.getValue().equals(branchType)){
        	// 交易本金 = C.应收利息总额
            trdProductDeal.setPrinAmt(BigDecimal.ZERO);
            // 利息总额 = C.应收利息总额
            trdProductDeal.setIntAmt(dto.getIntAmt());
            // 交易金额 = C.应收利息总额
            trdProductDeal.setTradeAmt(dto.getIntAmt());
        }
        else{
        	// 交易本金 = C.本金金额
            trdProductDeal.setPrinAmt(dto.getCapital());
            // 利息总额 = C.应收利息总额
            trdProductDeal.setIntAmt(BigDecimal.ZERO);
            // 交易金额 = C.本金金额
            trdProductDeal.setTradeAmt( dto.getCapital());
        }
        
        // 交易方向 = C.交易方向
        trdProductDeal.setPs(dto.getPs());
        // 交易状态 = C.数据动作标志 新增 --> 01：初始 撤销 --> 03:撤销
        if (StringUtil.isNotEmpty(dto.getDataActionSign())) {
            // 新增
            if (dto.getDataActionSign().equals("01")) {
                trdProductDeal.setTradeStatus("01");
            }
            // 撤销
            else if (dto.getDataActionSign().equals("03")) {
                trdProductDeal.setTradeStatus("03");
            }
        }
        // 原交易编号 = C.原交易编号
        trdProductDeal.setpTradeId(dto.getFtradeId());
        // 是否结清 = C.是否结清
        trdProductDeal.setIsClean(dto.getIsClean2());
        // 关联系统交易编号 = C.成交编号
        trdProductDeal.setfTradeId(dto.getTradeId());
//        //20181012 leipeng 原交易编号 = 交易状态为撤销时： 原交易编号=调用共通设计->【详细设计-接口共通方法设计.xlsx】->撤销交易对应的原交易编号查询SHEET 交易状态为非撤销时：原交易编号为空
//        if(trdProductDeal.getTradeStatus().equals(DictCodeListExt1.交易状态_撤销.getValue())){
//        	trdProductDeal.setpTradeId(interfCommonService.getFTradeId(trdProductDeal));
//        }
        // 创建时间 = 系统时间
        trdProductDeal.setCreateTime(dto.getCreateTime());
        //通道代码 = a.通道代码
        trdProductDeal.setChlId(dto.getChannelId());
        return trdProductDeal;
    }

    /**
     * T2: 估值核算交易扩展表 接收表
     * 
     * @param dto
     * @param deal
     * @return
     * @see
     * @since 1.0
     */
    private TrdProductDealAdd getValFinProductDealExt(ValMngReceicableTrade dto,
                                                      TrdProductDeal deal,String finprodType) {
    	TrdProductDealAdd trdProductDealExt = new TrdProductDealAdd();
        // 交易编号 = T1.交易编号
        trdProductDealExt.setTradeId(deal.getTradeId());
        // 理财产品代码 = C.产品代码
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
        // 是否有结算指令 = C.有无清算指令
//        trdProductDealExt.setHasOrder(dto.getIsClean());
        // 生成方式 = 接口接入
        trdProductDealExt.setGenType(
            com.joyintech.tams.constant.DictCodeListExt1.生成方式_接口接入.getValue());
        // 创建时间 = 系统时间
        trdProductDealExt.setCreateTime(dto.getCreateTime());

        return trdProductDealExt;
    }

    /**
     * T9: 交易类金融产品 生成表
     * 
     * @param deal T1
     * @return
     * @see
     * @since 1.0
     */
    private FinTradeProduct getValFinTradeProduct(TrdProductDeal deal,String branchType) {
        FinTradeProduct finTradeProduct = new FinTradeProduct();
        // 金融产品代码 = T1.金融产品代码
        finTradeProduct.setFinprodId(deal.getFinprodId());
        // 金融产品类型 = T1.金融产品类型
        finTradeProduct.setFinprodType(deal.getFinprodType());
        // 分支序号 = T1.分支序号
        finTradeProduct.setBranch(deal.getBranch());
        // 分支类型 = 数据字典：分支类型.应收利息
        finTradeProduct.setBranchType(branchType);
        // 名义本金 = T1.交易本金
        finTradeProduct.setPrin(deal.getPrinAmt());
        // 本金币种 = T1.交易币种
        finTradeProduct.setCcy(deal.getCcy());
        // 起息日 = T1.起息日
        finTradeProduct.setVdate(deal.getVdate());
        // 到期日 = T1.到期日
        finTradeProduct.setMdate(deal.getMdate());
        // 到期利息 = T1.利息总额
        finTradeProduct.setmIntAmt(deal.getIntAmt());
        // 到期金额 = T1.交易金额
        finTradeProduct.setmTradeAmt(deal.getTradeAmt());
        // 原金融产品代码 = T1.金融产品代码
        finTradeProduct.setoFinprodId(deal.getFinprodId());
        // 原金融产品类型 = T1.金融产品类型
//        finTradeProduct.setoFinprodType(deal.getFinprodType());
        // 创建时间 = 系统时间
        finTradeProduct.setCreateTime(deal.getCreateTime());
        return finTradeProduct;
    }


//    /**
//     * <一句话功能简述>
//     * <功能详细描述>
//     * @param valFinProductDeal
//     * @return
//     * @see
//     * @since 1.0
//     * add by wujie on 20180828
//     */
//    private Map<String, Object> getvalOrders(TrdProductDeal valFinProductDeal,String prodId,String bookSetId,String acctSubjectType) {
//        //交易指令 调用共通 
//        ProductDealDto productDealDto=new ProductDealDto();
//        productDealDto.setTradeStatus(valFinProductDeal.getTradeStatus());  //* 交易状态
//        productDealDto.setTradeId(valFinProductDeal.getTradeId());    // * 交易编号
//        productDealDto.setTradeDate(valFinProductDeal.getTradeDate()); //  * 交易日
//        productDealDto.setEffDate(valFinProductDeal.getSettleDate());//交割日
//        productDealDto.setPs(valFinProductDeal.getPs());//  * 交易方向
//        productDealDto.setTardeAmt(valFinProductDeal.getTradeAmt()); //  * 交易金额
//        productDealDto.setFinprodId(prodId); // * 金融产品代码
//        /**modify by wujie on 20190107 start
//        productDealDto.setFinprodType(interfCommonService.queryProductType(prodId));
//        **/
//        productDealDto.setFinprodType(acctSubjectType);
//        /**modify by wujie on 20190107 end**/
//        productDealDto.setBusiType(valFinProductDeal.getBusiType());// * 业务类型
//        productDealDto.setProdId(prodId); //  * 理财产品代码
//        productDealDto.setCcy(valFinProductDeal.getCcy()); //  * 币种
//        productDealDto.setIsTrade(DictCodeList.是否_是.getValue());   //  * 是否随交易出账
//        productDealDto.setBookNo(bookSetId);
//        /**add by chendijun  2018/11/15 start**/
//        //原交易编号
//        productDealDto.setpTradeId(valFinProductDeal.getpTradeId());
//        /**add by chendijun  2018/11/15 end**/
//        String insType = null;
//        if(DictCodeList.交易方向_应收款项转入.getValue().equals(valFinProductDeal.getPs())){
//        	insType = DictCodeListExt1.指令类型_应收款项_转仓转入.getValue();
//        }
//        else if(DictCodeList.交易方向_应收款项转出.getValue().equals(valFinProductDeal.getPs())){
//        	insType = DictCodeListExt1.指令类型_应收款项_转仓转出.getValue();
//        }
//        return interfCommonService.settlementInstructionGeneration(productDealDto, insType,DictCodeList.是否_是.getValue());
//    }
}
