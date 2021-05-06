
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
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.interf.base.constant.FinalString;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.FeeDealCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.InterfProductDealBookCommonDto;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFeeDrawDeal;
/**
 * 费用计提交易 模板处理Service(VAL_FEE_DRAW_DEAL)
 * @author chenlei1
 * @version 1.0 2019年11月25日
 * @see ValFeeDrawDealTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValFeeDrawDealTemplateRecService {
    
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
        List<ValFeeDrawDeal> listValFeeDrawDeal = new ArrayList<ValFeeDrawDeal>();
        Map<String,Object> params = acctEntity.getParams();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValFeeDrawDeal = returnResult.getT(ValFeeDrawDeal.class);
        result.setDatanum(listValFeeDrawDeal==null?0:listValFeeDrawDeal.size());

        /**add by wujie on 20210506 start**/
        if(returnResult.isReadFile()){
            result.getParamMap().put(InterfCommonConstant.读取文件状态,DictCodeList.是否_是.getValue());
        }
        /**add by wujie on 20210506 end**/

        // 3、根据有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = this.handleValPrdExpDeal(listValFeeDrawDeal,
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
	private Map<String, Object> handleValPrdExpDeal(List<ValFeeDrawDeal> listValFeeDrawDeal,
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
        
        for (ValFeeDrawDeal dto : listValFeeDrawDeal) {
        	if(StringUtil.equals(InterfCommonConstant.接口模式_指定账套,valModel) && !bookSetIds.contains(dto.getProdId())){
        		continue;
        	}
            dto.setCreateTime(resovleDate);
            
        	// 金融产品交易
            TrdProductDeal valFinProductDeal = this.getValFinProductDeal(dto);
            listValFinProductDeal.add(valFinProductDeal);

            // 估值核算交易扩展表
            TrdProductDealAdd valFinProductDealExt = this.getValFinProductDealExt(dto, valFinProductDeal);
            listValFinProductDealExt.add(valFinProductDealExt);
            
            // 交易费用明细表
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
            
            //valBokDetailId.setDetailSubnameTail(dto.getProdName());
            valBokDetailId.setBokDetailId(detailId);
            listValBokDetailId.add(valBokDetailId);
            
            
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
     * 金融产品交易
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private TrdProductDeal getValFinProductDeal(ValFeeDrawDeal dto) {
        // 初始化 金融产品交易 返回值
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        // 交易编号 = 调用共通方法生成
        trdProductDeal.setTradeId(interfCommonService.getTradeid(DictCodeListExt3.交易方向_费用计提.getValue(),dto.getDrawDate(), "A"));
        // 业务类型 = 数据字典：业务类型.费用交易
        /**modify by wujie no 20210502 start
        trdProductDeal.setBusiType(DictCodeListExt3.业务类型_费用计提交易.getValue());
         **/
        trdProductDeal.setBusiType(dto.getBusiType());
        /**modify by wujie on 20210502 end**/


        //金融产品代码 = A.产品代码  
        trdProductDeal.setFinprodId(dto.getProdId());        
        //金融产品类型=A.产品类型 
        trdProductDeal.setFinprodType(dto.getProdType());
        // 分支序号=A.分支序号
        trdProductDeal.setBranch(dto.getBranch());       
        // 交易日 = A.计提日
        trdProductDeal.setTradeDate(dto.getDrawDate());
        // 交割日 =  A.计提日
        trdProductDeal.setSettleDate(dto.getDrawDate());
        // 交易币种 = A.交易币种
        trdProductDeal.setCcy(dto.getCcy());
        // 全价总额 = A.计提金额
        trdProductDeal.setTradeAmt(dto.getFeeAmt());
        // 交易方向 = A.交易方向
        trdProductDeal.setPs(DictCodeListExt3.交易方向_费用计提.getValue());
        // 交易状态 = A.数据动作标志--> 新增 --> 01:初始    撤销 --> 03:撤销
        trdProductDeal.setTradeStatus(dto.getDataActionSign());
        // 通道代码 = A.通道代码
        trdProductDeal.setChlId(dto.getChannelId());
        // 关联系统交易编号 = A.成交编号
        trdProductDeal.setfTradeId(dto.getTradeId());
        //登记托管机构= A.登记托管机构
        trdProductDeal.setRegistOrg(dto.getRegistOrg());
        // 创建时间 = 系统时间
        trdProductDeal.setCreateTime(dto.getCreateTime());
        //投管业务类型=A.数据来源
        trdProductDeal.setTgBusiType(dto.getDataSource());
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
    private TrdProductDealAdd getValFinProductDealExt(ValFeeDrawDeal dto, TrdProductDeal deal) {
    	TrdProductDealAdd trdProductDealExt = new TrdProductDealAdd();
        // 交易编号 = T1.交易编号
        trdProductDealExt.setTradeId(deal.getTradeId());
        // 投资组合代码 = A.产品代码
        trdProductDealExt.setPortfolioId(dto.getProdId());
        // 是否有结算指令 = 数据字典：是否.是
        //trdProductDealExt.setHasOrder();
        // 生成方式 = 固定值 接口接入
        trdProductDealExt.setGenType(DictCodeListExt1.生成方式_接口接入.getValue());
        // 创建时间 = 固定值 系统时间
        trdProductDealExt.setCreateTime(dto.getCreateTime());
        return trdProductDealExt;

    }
    
    /**
     * T3.交易费用明细表
     * 
     * @param dto ValFundMnfRedAffirm
     * @return ValTradeFeeDetail
     * @see
     * @since 1.0
     */
    private TrdTradeFeeDetail getValTradeFeeDetail(ValFeeDrawDeal dto,TrdProductDeal deal) {
    	TrdTradeFeeDetail result = new TrdTradeFeeDetail();
        // 交易编号 = T1.交易编号
        result.setTradeId(deal.getTradeId());
        // 一次性费用类型 = A.费用类型
        result.setFeeType(dto.getFeeType());
        // 是否随交易支付 =Y 否
        result.setIsPayWithTrade(DictCodeList.是否_否.getValue());
        // 费用金额 = A.实付费用
        result.setFeeAmt(dto.getFeeAmt());
        // 费用代码 =A.费用类型
        result.setFeeId(dto.getFeeType());
        // 创建时间 = 系统时间
        result.setCreateTime(dto.getCreateTime());
        // 更新时间 = 系统时间
        result.setUpdateTime(dto.getCreateTime());
        return result;
    }
    
    
  
    
}
