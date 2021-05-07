/*
 * 文件名：CurrentDepositDealIntTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chendijun
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.assetdeal.currentdeposit;

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
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.FinalString;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.CurrentDepositCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.InterfProductDealBookCommonDto;
import com.joyintech.tams.valuation.common.interf.base.dto.ProductDealDto;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValCurrentDepositDealInt;

/**
 * 活期存款收息交易 service(VAL_CURRENT_DEPOSIT_DEAL_INT) 〈功能详细描述〉
 * 
 * @author chendijun
 * @version 1.0 2018年8月21日
 * @see CurrentDepositDealIntTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class CurrentDepositDealIntTemplateRecService {

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
    public CurrentDepositCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                              TemplateOrderInfoReaderResult acctEntity) {
        // 初始化模板处理返回共通结果
        CurrentDepositCommonResult result = new CurrentDepositCommonResult();
        Map<String,Object> params = acctEntity.getParams();//modify by niuqianyu on 20190112
        List<ValCurrentDepositDealInt> listValCurrentDepositDealInt = new ArrayList<ValCurrentDepositDealInt>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValCurrentDepositDealInt = returnResult.getT(ValCurrentDepositDealInt.class);
        //设置读取数据数量
        result.setDataNum(listValCurrentDepositDealInt.size());

        /**add by wujie on 20210506 start**/
        if(returnResult.isReadFile()){
            result.getParamMap().put(InterfCommonConstant.读取文件状态,DictCodeList.是否_是.getValue());
        }
        /**add by wujie on 20210506 end**/

        // 3、根据有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleValCurrentDepositDealInt(listValCurrentDepositDealInt,
            acctEntity.getResovleDate(),params,returnResult.getValInterflog());

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
        result.setListValOrders(listValOrders);

        // 结算指令扩展表
        List<StlOrdersExt> listValOrdersExt = (List<StlOrdersExt>)map.get("listValOrdersExt");
        result.setListValOrdersExt(listValOrdersExt);

        // 添加日志
        result.setValInterflog((List<ValInterflog>)map.get("valInterflogList"));
        // 返回处理结果数据
        return result;
    }

    @SuppressWarnings("unchecked")
	private Map<String, Object> handleValCurrentDepositDealInt(List<ValCurrentDepositDealInt> listValCurrentDepositDealInt,
            Date resovleDate,Map<String,Object> params,List<ValInterflog> valInterflog) {
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
        // 账务明细代码表
        List<BokDetailId> listValBokDetailId = new ArrayList<BokDetailId>();
        // 结算指令
        List<StlOrders> listValOrders = new ArrayList<StlOrders>();
        // 结算指令扩展表
        List<StlOrdersExt> listValOrdersExt = new ArrayList<StlOrdersExt>();

        List<ValInterflog> valInterflogList =  new ArrayList<>();
        for (ValCurrentDepositDealInt dto : listValCurrentDepositDealInt) {
        	if(StringUtil.equals(InterfCommonConstant.接口模式_指定账套,valModel) && !bookSetIds.contains(dto.getProdId())){
        		continue;
        	}
            dto.setCreateTime(resovleDate);// add by chendijun on 20180821 remark:用于去重复数据
            
            //产品直投 
            if(StringUtil.isEmpty(dto.getChannelId())){
                //  金融产品交易(理财产品活期存款收息交易)
                TrdProductDeal trdProductDeal = this.getValFinProductDeal(dto);
                listValFinProductDeal.add(trdProductDeal);
                // 估值核算交易扩展表（理财产品活期存款收息交易）
                TrdProductDealAdd trdProductDealExt = this.getValFinProductDealExt(dto,trdProductDeal);
                listValFinProductDealExt.add(trdProductDealExt);

                // 入账信息表 ,账务明细表, 账务明细代码表调用共通
        		InterfProductDealBookCommonDto dealAccounting = interfCommonService.dealAccounting(
        		    trdProductDeal, dto.getProdId());
        		listValDealAccounting.add(dealAccounting.getBokDealAccounting());
        		listValDealAccountingDetail.add(dealAccounting.getBokDealAccountingDetail());
        		listValBokDetailId.add(dealAccounting.getBokDetailId());

        		// 结算指令表
        		// 结算指令扩展表
                /**modify by wujie on 20190515 start
                 BokDealAccounting valDealAccounting = dealAccounting.getBokDealAccounting();
                 ProductDealDto productDealDto = this.getValOrdersDto(trdProductDeal,valDealAccounting.getBooksetId(),dealAccounting.getAcctSubjectType());

                 String insType = DictCodeList.指令类型_活期存款利息_结息.getValue();
                 Map<String, Object> ordersmap = this.settlementInstructionGeneration(
                 productDealDto, insType, DictCodeList.是否_是.getValue(),dto.getAcctId());

                 StlOrders ValOrders = (StlOrders)ordersmap.get("ORDERS");
                 StlOrdersExt ValOrdersExt = (StlOrdersExt)ordersmap.get("ORDERS_EXT");
                 listValOrders.add(ValOrders);
                 listValOrdersExt.add(ValOrdersExt);
                 **/
                Map<String, Object> map = interfCommonService.instructionGenerate(trdProductDeal,
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
                /**modify by wujie on 20190515 end**/
        		
            }
            else{
            
            	/**1.通道产品活期存款收息交易**/
            	String chlProdId = dto.getProdId() + FinalString.LAYERING_SPLIT + dto.getChannelId();
            	// 金融产品交易(通道产品活期存款收息交易)
                TrdProductDeal chlTrdProductDeal = this.getValFinProductDeal(dto);
                chlTrdProductDeal.setChlId(dto.getChannelId());
                chlTrdProductDeal.setTotleTradeId(chlTrdProductDeal.getTradeId());
                listValFinProductDeal.add(chlTrdProductDeal);
                
            	// 估值核算交易扩展表（通道产品活期存款收息交易）
                TrdProductDealAdd chlTrdProductDealExt = this.getValFinProductDealExt(dto,chlTrdProductDeal);
//                chlTrdProductDealExt.setProdId(chlProdId);//一体化版本无此字段
                chlTrdProductDealExt.setPortfolioId(chlProdId);
                listValFinProductDealExt.add(chlTrdProductDealExt);
               
                // 入账信息表 ,账务明细表, 账务明细代码表调用共通
        		InterfProductDealBookCommonDto dealAccounting = interfCommonService.dealAccounting(
        				chlTrdProductDeal, dto.getProdId());
        		listValDealAccounting.add(dealAccounting.getBokDealAccounting());
        		listValDealAccountingDetail.add(dealAccounting.getBokDealAccountingDetail());
        		listValBokDetailId.add(dealAccounting.getBokDetailId());

                //结算指令
                /**modify by wujie on 20190515 start
                 BokDealAccounting valDealAccounting = dealAccounting.getBokDealAccounting();
                 ProductDealDto productDealDto = this.getValOrdersDto(chlTrdProductDeal,valDealAccounting.getBooksetId(),dealAccounting.getAcctSubjectType());

                 String insType = DictCodeList.指令类型_活期存款利息_结息.getValue();
                 Map<String, Object> ordersmap = this.settlementInstructionGeneration(
                 productDealDto, insType, DictCodeList.是否_是.getValue(),dto.getAcctId());

                 StlOrders ValOrders = (StlOrders)ordersmap.get("ORDERS");
                 StlOrdersExt ValOrdersExt = (StlOrdersExt)ordersmap.get("ORDERS_EXT");
                 listValOrders.add(ValOrders);
                 listValOrdersExt.add(ValOrdersExt);
                 **/
                Map<String, Object> map = interfCommonService.instructionGenerate(chlTrdProductDeal,
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
                /**modify by wujie on 20190515 end**/
                
                
                
                /**2.通道产品赎回**/
                String detailSubnameTail = null;
            	// 金融产品交易(通道产品赎回)
                TrdProductDeal chlRedTrdProductDeal = this.getValFinProductDealTD(dto);
                chlRedTrdProductDeal.setTotleTradeId(chlTrdProductDeal.getTradeId());
                listValFinProductDeal.add(chlRedTrdProductDeal);
            	// 估值核算交易扩展表（通道产品赎回）
                TrdProductDealAdd chlRedTrdProductDealExt = this.getValFinProductDealExt(dto,chlRedTrdProductDeal);
//                chlRedTrdProductDealExt.setProdId(chlProdId);//一体化版本无此字段
                chlRedTrdProductDealExt.setPortfolioId(chlProdId);
                listValFinProductDealExt.add(chlRedTrdProductDealExt);
                
                
                // 入账信息表   账务明细表、账务明细代码表调用共通
         		dealAccounting = interfCommonService.dealAccounting(chlRedTrdProductDeal, dto.getProdId());
         		listValDealAccounting.add(dealAccounting.getBokDealAccounting());
         		
         		BokDetailId bokDetailId = new BokDetailId();
         		bokDetailId.setBusiId(chlProdId);
         		bokDetailId.setBusiDetailType(dealAccounting.getAcctSubjectType());
         		String detailId =  interfCommonService.genMd5BokDetailId(bokDetailId);
         		
         		dealAccounting.getBokDealAccountingDetail().setBokDetailId(detailId);
         		listValDealAccountingDetail.add(dealAccounting.getBokDealAccountingDetail());
         		
         		dealAccounting.getBokDetailId().setBusiId(dto.getProdId());
         		dealAccounting.getBokDetailId().setBusiDetailType(dealAccounting.getAcctSubjectType());
         		dealAccounting.getBokDetailId().setBokDetailId(detailId);

                /**add by wujie on 20190625 start**/
                //四级科目尾号
                dealAccounting.getBokDetailId().setDetailSubjectNoTail(chlRedTrdProductDeal.getFinprodId());
                //四级科目尾号名称
                detailSubnameTail = dealAccounting.getBooksetName();
                dealAccounting.getBokDetailId().setDetailSubnameTail(detailSubnameTail);
                /**add by wujie on 20190625 end**/

                listValBokDetailId.add(dealAccounting.getBokDetailId());
         		        
         		//结算指令
         		map = interfCommonService.instructionGenerate(chlRedTrdProductDeal,
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
                
                
         		/**3.理财产品买通道虚拟资产**/
         	    // 金融产品交易(理财产品买虚拟通道资产)
            	TrdProductDeal prdTrdProductDeal = this.getValFinProductDealPRD(dto);
            	prdTrdProductDeal.setTotleTradeId(chlTrdProductDeal.getTradeId());
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
        resultMap.put("ValBokDetailId", listValBokDetailId);

        resultMap.put("listValOrders", listValOrders);
        resultMap.put("listValOrdersExt", listValOrdersExt);
        valInterflogList = interfCommonService.filterValInterflogList(valInterflog, bookSetIds, valModel);
    	resultMap.put("valInterflogList", valInterflogList);
        return resultMap;
    }

    /**
     * T1:金融产品交易表 接收
     * 
     * @param dto ValCurrentDepositDealInt
     * @return TrdProductDeal
     * @see
     * @since 1.0
     */
    private TrdProductDeal getValFinProductDeal(ValCurrentDepositDealInt dto) {
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        // 交易编号 = 自动生成,调用共同方法生成
        trdProductDeal.setTradeId(interfCommonService.getTradeid(DictCodeList.交易方向_收息.getValue(),
            dto.getDealDate(), "A"));
        // 业务类型 = 固定值: 数据字典 业务类型.活期存款交易
        trdProductDeal.setBusiType(DictCodeList.业务类型_活期存款交易.getValue());
        // 金融产品代码 = A.资金账户账号
        trdProductDeal.setFinprodId(dto.getAcctId());
        // 金融产品类型 = 固定值:数据字典 金融产品类型.资金账户
        if(StringUtil.isEmpty(dto.getChannelId())){
        	trdProductDeal.setFinprodType(interfCommonService.selectCapAcctType(dto.getAcctId(), dto.getProdId(),dto.getCcy()).get(0).getAcctType());
        }else{
        	String chlProdId = dto.getProdId() + FinalString.LAYERING_SPLIT + dto.getChannelId();
        	trdProductDeal.setFinprodType(interfCommonService.selectCapAcctType(dto.getAcctId(), chlProdId,dto.getCcy()).get(0).getAcctType());
        }
        // 分支序号 = 固定值 :设置全局变量(默认为0）
        trdProductDeal.setBranch(InterfCommonConstant.分支序号_默认);
        // 交易日 = A.本次收息日
        trdProductDeal.setTradeDate(dto.getDealDate());
        // 起息日 = A.上次收息日
        trdProductDeal.setVdate(dto.getVdate());
        // 到期日 = A.收息截止日
        trdProductDeal.setMdate(dto.getMdate());
        
        // 交割日 = A.本次收息日
        trdProductDeal.setSettleDate(dto.getDealDate());
        // 交易币种 = A.币种
        trdProductDeal.setCcy(dto.getCcy());
        // 交易金额 = A.实付利息
        trdProductDeal.setTradeAmt(dto.getDealAmt());
        // 交易方向 = 固定值:数据字典 交易方向.收息
        trdProductDeal.setPs(DictCodeList.交易方向_收息.getValue());
        // 交易状态 = A.数据动作标志 新增 --> 01:初始 撤销 --> 03:撤销
        trdProductDeal.setTradeStatus(dto.getDataActionSign());
        // 是否结清 = 固定值:设置默认值
        trdProductDeal.setIsClean(InterfCommonConstant.是否结清_默认);
        // 关联系统交易编号 = A.成交编号
        trdProductDeal.setfTradeId(dto.getTradeId());
        // 创建时间 = 系统时间
        trdProductDeal.setCreateTime(dto.getCreateTime());
        if(trdProductDeal.getTradeStatus().equals(DictCodeListExt1.交易状态_撤销.getValue())){
        	trdProductDeal.setpTradeId(interfCommonService.getFTradeId(trdProductDeal));
        }
        //汇总交易编号
        trdProductDeal.setTotleTradeId(trdProductDeal.getTradeId());
        return trdProductDeal;
    }

    /**
     * T2: 估值核算交易扩展表 
     */
    private TrdProductDealAdd getValFinProductDealExt(ValCurrentDepositDealInt dto,TrdProductDeal t1) {
    	TrdProductDealAdd trdProductDealExt = new TrdProductDealAdd();
        // 交易编号 = T1.交易编号
        trdProductDealExt.setTradeId(t1.getTradeId());
        // 理财产品代码 = A.产品代码
//        trdProductDealExt.setProdId(dto.getProdId());//一体化无此字段
        // 投资组合代码 = A.产品代码
        trdProductDealExt.setPortfolioId(dto.getProdId());
        // 是否有结算指令 = A.有无清算指令
//        trdProductDealExt.setHasOrder(dto.getIsClean());//一体化无此字段
        // 生成方式 = 固定值:接口接入
        trdProductDealExt.setGenType(DictCodeListExt1.生成方式_接口接入.getValue());
        // 创建时间 = 系统时间
        trdProductDealExt.setCreateTime(dto.getCreateTime());

        return trdProductDealExt;
    }

//    /**
//     * 结算指令 <功能详细描述>
//     *
//     * @param valFinProductDeal
//     * @param bookSetId
//     * @return
//     * @see
//     * @since 1.0
//     */
//    private ProductDealDto getValOrdersDto(TrdProductDeal valFinProductDeal, String bookSetId,String acctSubjectType) {
//
//        // 结算指令
//        ProductDealDto dto = new ProductDealDto();
//        //交易状态
//        dto.setTradeStatus(valFinProductDeal.getTradeStatus());
//        // 交易编号
//        dto.setTradeId(valFinProductDeal.getTradeId());
//        // 交易日
//        dto.setTradeDate(valFinProductDeal.getTradeDate());
//        // 交易方向
//        dto.setPs(valFinProductDeal.getPs());
//        // 交易金额
//        dto.setTardeAmt(valFinProductDeal.getTradeAmt());
//        // 金融产品代码
//        dto.setFinprodId(valFinProductDeal.getFinprodId());
//        // 金融产品类型
//        /**add by wujie on 20190107 start
//        dto.setFinprodType(valFinProductDeal.getFinprodType());
//        **/
//        dto.setFinprodType(acctSubjectType);
//        /**add by wujie on 20190107 end**/
//        // 业务类型
//        dto.setBusiType(valFinProductDeal.getBusiType());
//        // 理财产品代码
//        dto.setProdId(valFinProductDeal.getFinprodId());
//        // 币种
//        dto.setCcy(valFinProductDeal.getCcy());
//        // 是否随交易出账
//        dto.setIsTrade(DictCodeList.是否_是.getValue());
//        // 账套代码
//        dto.setBookNo(bookSetId);
//        // 交割日
//        dto.setEffDate(valFinProductDeal.getSettleDate());
//
//        /**add by chendijun  2018/11/15 start**/
//        //原交易编号
//        dto.setpTradeId(valFinProductDeal.getpTradeId());
//        /**add by chendijun  2018/11/15 end**/
//        return dto;
//    }

//    /**
//     * 结算指令生成逻辑
//     * @param productDealDto 结算指令所需参数
//     * @param insType 指令类型
//     * @param isBook 指令是否随交易生成
//     * @param acctId 资金账户号
//     * @return
//     */
//    public Map<String,Object> settlementInstructionGeneration(ProductDealDto productDealDto,
//            String insType, String isBook,String acctId){
//    	Map<String,Object> map = new HashMap<String,Object>();
//    	// 定义 变量.结算指令Dto
//        StlOrders valOrders = new StlOrders();
//        // 定义 变量.结算指令扩展Dto
//        StlOrdersExt valOrdersExt = new StlOrdersExt();
//        // 定义 变量.结算日期 =输入.交易标的Dto.交易日
//        Date dealDate = productDealDto.getTradeDate();
//        // 定义 变量.本方资金账户
//        String myAcctNo = acctId;
//        // 定义 变量.收付方向
//        String ps = "";
//        // 定义 变量.对方资金账户
//        String counterAcctNo = "";
//        // 2.1 期存款利息_结息、活期存款利息_卖出
//        if (DictCodeListExt1.指令类型_活期存款利息_结息.getValue().equals(insType)
//            || DictCodeListExt1.指令类型_活期存款利息_卖出_转仓.getValue().equals(insType)) {
//            counterAcctNo = null;
//            ps = DictCodeList.收付方向_收入.getValue();
//        }
//        // 2.2 活期存款利息_买入
//        else if (DictCodeListExt1.指令类型_活期存款利息_买入_转仓.getValue().equals(insType)) {
//            counterAcctNo = null;
//            ps = DictCodeListExt1.收付方向_支出.getValue();
//        }
//        String pOrderId = "";
//        if(DictCodeListExt1.交易状态_撤销.getValue().equals(productDealDto.getTradeStatus())) {
//            StlOrders valOrdersCX = new StlOrders();
//            //变量.结算指令Dto.交易编号 = 输入.交易标的Dto.原交易编号
//            valOrdersCX.setTradeId(productDealDto.getpTradeId());
//            //变量.结算指令Dto.指令类型 = 输入.指令类型
//            valOrdersCX.setOrderType(insType);
//            //变量.结算指令Dto.业务类型 = 输入.交易标的Dto.业务类型
//            valOrdersCX.setBusiType(productDealDto.getBusiType());
//            pOrderId = interfCommonService.queryPOrderId(valOrdersCX);
//        }
//        // 定义 变量.指令编号 = 自动生成
//        String fingerNo = interfCommonService.getTradeid(insType, dealDate, "B");
//        // 入库
//        // 结算指令表(STL_ORDERS)
//        valOrders.setOrderId(fingerNo);
//        valOrders.setBusiType(productDealDto.getBusiType());
//        valOrders.setOrderType(insType);
//        valOrders.setOrderNum(null);
//        valOrders.setSettleDate(dealDate);
//        valOrders.setTradeId(productDealDto.getTradeId());
//        valOrders.setMyAcctNo(myAcctNo);
//        valOrders.setPs(ps);
//        valOrders.setCounterAcctNo(counterAcctNo);
//        valOrders.setSettleAmt(productDealDto.getTardeAmt());
//        valOrders.setCcy(productDealDto.getCcy());
//        valOrders.setSettleStatus(DictCodeList.成功失败标志_成功.getValue());
//        valOrders.setOrderStatus(productDealDto.getTradeStatus());
//        valOrders.setpOrderId(pOrderId);
//        valOrders.setCreateTime(productDealDto.getCreateTime());
//        map.put("ORDERS", valOrders);
//
//        // 3.2 结算指令扩展表(VAL_ORDERS_EXT)
//        valOrdersExt.setOrderId(fingerNo);
//        valOrdersExt.setIsTrade(isBook);
//        valOrdersExt.setCreateTime(productDealDto.getCreateTime());
//        map.put("ORDERS_EXT", valOrdersExt);
//
//    	return map;
//    }


    /**
     * T1:金融产品交易表(通道产品申赎)
     * @param dto 增值税交易
     * @return 金融产品交易表
     */
    private TrdProductDeal getValFinProductDealTD(ValCurrentDepositDealInt dto) {
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
        valFinProductDeal.setTradeDate(dto.getDealDate());
        
        // 交割日= A.交割日
        valFinProductDeal.setSettleDate(dto.getDealDate());
        
        // 交易币种= A.交易币种
        valFinProductDeal.setCcy(dto.getCcy());
        
        // 交易金额= A.交易金额
        valFinProductDeal.setTradeAmt(dto.getDealAmt());
        
        // 交易份额 = A.实付费用
        valFinProductDeal.setShareAmt(dto.getDealAmt());
        
        // 交易本金 = A.实付费用
        valFinProductDeal.setPrinAmt(dto.getDealAmt());
        
        //交易方向
        valFinProductDeal.setPs(DictCodeList.交易方向_产品赎回.getValue());
        
        // 交易状态 = A.数据动作标志
        valFinProductDeal.setTradeStatus(dto.getDataActionSign());
        
        // 是否结清 = 固定值：设置默认值
        valFinProductDeal.setIsClean(InterfCommonConstant.是否结清_默认);
        
        // 关联系统交易编号 = A.成交编号
        valFinProductDeal.setfTradeId(dto.getTradeId());
        
         //定义编号
        String tradeId=interfCommonService.getTradeid(valFinProductDeal.getPs(),dto.getDealDate(),"A");
        
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
     */
    private TrdProductDeal getValFinProductDealPRD(ValCurrentDepositDealInt dto) {
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
        trdProductDeal.setTradeDate(dto.getDealDate());
        // 交割日 = A.交割日
        trdProductDeal.setSettleDate(dto.getDealDate());
        // 交易币种 = A.交易币种
        trdProductDeal.setCcy(dto.getCcy());
        // 交易金额 = A.实付费用
        trdProductDeal.setTradeAmt(dto.getDealAmt());
        // 交易份额 = A.实付费用
        trdProductDeal.setShareAmt(dto.getDealAmt());
        // 交易本金 = A.实付费用
        trdProductDeal.setPrinAmt(dto.getDealAmt());
        //交易方向
        trdProductDeal.setPs(DictCodeList.交易方向_赎回确认.getValue());
        
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
            dto.getDealDate(), "A"));
        if(trdProductDeal.getTradeStatus().equals(DictCodeListExt1.交易状态_撤销.getValue())){
            trdProductDeal.setpTradeId(interfCommonService.getFTradeId(trdProductDeal));
        }
        
        return trdProductDeal;
    }
}
