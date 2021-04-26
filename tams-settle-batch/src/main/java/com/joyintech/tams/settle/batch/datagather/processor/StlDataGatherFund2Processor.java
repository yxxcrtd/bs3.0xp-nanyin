/*
 * 文件名：StlDataGatherFund2Processor.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年07月30日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.settle.batch.datagather.processor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.SettleTransTgMapping;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListStl;
import com.joyintech.tams.settle.batch.datadeal.constant.StlDataDealProcessConstant;
import com.joyintech.tams.settle.batch.datadeal.service.StlDataDealProcessCommonService;
import com.joyintech.tams.settle.batch.datagather.dto.InvestTradeReaderDto;
import com.joyintech.tams.settle.batch.datagather.dto.InvestTradeWriteDto;
import com.joyintech.tams.settle.batch.datagather.dto.SettleDelDealDetailDto;
import com.joyintech.tams.settle.batch.datagather.dto.SettleTransDataDto;
import com.joyintech.tams.settle.batch.datagather.dto.TotalTrdProductDealDto;
import com.joyintech.tams.settle.batch.datagather.dto.TrdProductDealDto;
import com.joyintech.tams.settle.common.service.SettleCommonService;

/**
 * 基金分红处理
 *
 * @author 王波
 * @date 2020年07月30日
 * @see StlDataGatherFund2Processor
 * @since 1.0
 */
@Service
@Lazy
public class StlDataGatherFund2Processor extends StlDataGatherProcessor {

    private static final String DEAL_REMARK = "数据清算自动处理";

    @Autowired
    private SettleCommonService settleCommonService;

    @Autowired
    private StlDataDealProcessCommonService stlDataDealProcessCommonService;

    @Override
    protected String getSecType() {
        return DictCodeListExt1.证券品种_基金.getValue();
    }

    @Override
    public InvestTradeWriteDto process(NeedOperateEntity operateEntity) throws Exception {
    	
        InvestTradeReaderDto item = (InvestTradeReaderDto) operateEntity;
        
        InvestTradeWriteDto investTradeWriteDto = new InvestTradeWriteDto();
        
        // 定义临时map存各个资产的除权交易
        Map<String, TotalTrdProductDealDto> tempTotleMap = new HashMap<>();
        
        
        // 第一步：先生成非货基现金分红的除权交易数据
        List<SettleDelDealDetailDto> delDtoList = item.getSettleTransDataDelDtoList();
        if (delDtoList != null && delDtoList.size() > 0) {
	        Map<String,List<SettleDelDealDetailDto>> tempMap 
			        = delDtoList.stream().collect(Collectors.groupingBy(x -> {
			    return x.getFinprodId() + x.getFinprodType2();
			}));
	        
	        Iterator<String> iterator = tempMap.keySet().iterator();
	        while (iterator.hasNext()) {
	        	String tempStr = iterator.next();
	        	List<SettleDelDealDetailDto> settleTransDataDelDtoList = tempMap.get(tempStr);
	        	if (settleTransDataDelDtoList != null && settleTransDataDelDtoList.size() > 0) {
					// 金融产品交易汇总信息
					TotalTrdProductDealDto dealTotal = new TotalTrdProductDealDto();
					TotalTrdProductDealDto totalTrdProductDealDto = setCqDealTotal(dealTotal, settleTransDataDelDtoList);
					investTradeWriteDto.getFinProductDealTotalList().add(totalTrdProductDealDto);
					
					// 金融产品交易明细
					TrdProductDealDto dealDto = generateCqProductDelDeal(totalTrdProductDealDto, settleTransDataDelDtoList);
					investTradeWriteDto.getProductDealList().add(dealDto);
					
					// 成交编号业务编号关联表（只针对除权登记数据）
					SettleTransTgMapping settleTransTgMapping = new SettleTransTgMapping();
					settleTransTgMapping.setTradeId(IDGenerator.generate(20));
					settleTransTgMapping.setTradeIdTg(dealDto.getTradeId());
					settleTransTgMapping.setTgDataStatus(DictCodeListStl.投管交易数据状态_处理结束.getValue());
					investTradeWriteDto.getSettleTransTgMappingList().add(settleTransTgMapping);
			
					// List<组合交易DTO>
					investTradeWriteDto.getPtlTradeList().add(generatePtlTrade(dealDto));
					
					// 默认一个资产只有一笔行权交易
					tempTotleMap.put(tempStr, totalTrdProductDealDto);
	        	}
	        }
        }
        
        List<SettleTransDataDto> settleTransDataDtoList = item.getSettleTransDataList();
        // 第二步：再生成非货基现金分红的现金分红数据
        List<SettleTransDataDto> settleTransDataDtoListFh = new ArrayList<SettleTransDataDto>();
        for (SettleTransDataDto settleTransDataDto : settleTransDataDtoList) {
        	if (StringUtil.equals(settleTransDataDto.getPs(), DictCodeList.交易方向_现金分红.getValue()) && 
        		StringUtil.equals(settleTransDataDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_基金.getValue()) &&
        		StringUtil.equals(settleTransDataDto.getBusiType(), DictCodeList.业务类型_非货币基金交易_非货基_非类货基资产交易.getValue())) {
        		settleTransDataDtoListFh.add(settleTransDataDto);
        	}
        }
        for (SettleTransDataDto settleTransDataDto : settleTransDataDtoListFh) {
            // 金融产品交易汇总信息
            TotalTrdProductDealDto dealTotal = generateFundDealTotal(settleTransDataDto, tempTotleMap.get(settleTransDataDto.getFinprodId() + settleTransDataDto.getFinprodType2()));
            investTradeWriteDto.getFinProductDealTotalList().add(dealTotal);
            // 金融产品交易信息
            TrdProductDealDto trdProductDealDto = generateProductDeal(settleTransDataDto, dealTotal);
            investTradeWriteDto.getProductDealList().add(trdProductDealDto);
            // 金融产品交易费用明细信息
            List<TrdTradeFeeDetail> trdTradeFeeDetails = generateTradeFeeDetail(trdProductDealDto, settleTransDataDto);
            if (investTradeWriteDto.getTradeFeeDetailList() != null || investTradeWriteDto.getTradeFeeDetailList().size() > 0) {
                investTradeWriteDto.getTradeFeeDetailList().addAll(trdTradeFeeDetails);
            }
            // 组合交易
            investTradeWriteDto.getPtlTradeList().add(generatePtlTrade(trdProductDealDto));
            // 划款指令明细
            StlPayOrders stlPayOrders = generateStlPayOrders(trdProductDealDto);
            investTradeWriteDto.getStlPayOrdersList().add(stlPayOrders);
        }
        
        // 第三步：最后生成货基红利再投的数据
        List<SettleTransDataDto> settleTransDataDtoListZt = new ArrayList<SettleTransDataDto>();
        for (SettleTransDataDto settleTransDataDto : settleTransDataDtoList) {
        	if (StringUtil.equals(settleTransDataDto.getPs(), DictCodeList.交易方向_红利再投.getValue()) && 
        		StringUtil.equals(settleTransDataDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_基金.getValue()) &&
        		StringUtil.equals(settleTransDataDto.getBusiType(), DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue())) {
        		settleTransDataDtoListZt.add(settleTransDataDto);
        	}
        }
        
        for (SettleTransDataDto settleTransDataDto : settleTransDataDtoListZt) {
    		// 金融产品交易汇总信息
            TotalTrdProductDealDto dealTotal = generateFundDealTotal(settleTransDataDto, null);
            investTradeWriteDto.getFinProductDealTotalList().add(dealTotal);
            // 金融产品交易信息
            TrdProductDealDto trdProductDealDto = generateProductDeal(settleTransDataDto, dealTotal);
            investTradeWriteDto.getProductDealList().add(trdProductDealDto);
            // 金融产品交易费用明细信息
            List<TrdTradeFeeDetail> trdTradeFeeDetails = generateTradeFeeDetail(trdProductDealDto, settleTransDataDto);
            if (investTradeWriteDto.getTradeFeeDetailList() != null || investTradeWriteDto.getTradeFeeDetailList().size() > 0) {
            	investTradeWriteDto.getTradeFeeDetailList().addAll(trdTradeFeeDetails);
            }
            // 组合交易
            investTradeWriteDto.getPtlTradeList().add(generatePtlTrade(trdProductDealDto));
        }
        
        investTradeWriteDto.setSecType(this.getSecType());
        
        return investTradeWriteDto;
    }
    
	/**
     * 生成现金分红和红利再投汇总交易
     *
     * @param settleTransDataDtos 成交明细
     * @return 汇总交易
     */
    private TotalTrdProductDealDto generateFundDealTotal(SettleTransDataDto settleTransDataDto, TotalTrdProductDealDto cqTotalTrdProductDealDto) {
        // 金融产品交易汇总信息
        TotalTrdProductDealDto dealTotal = new TotalTrdProductDealDto();
        //  汇总交易编号
        dealTotal.setTotleTradeId(settleCommonService.buildBussinessCodeExt(DictCodeList.共通业务类型_资产存续期交易));

        // 业务类型 TRD14
        dealTotal.setBusiType(DictCodeList.共通业务类型_基金类资产存续期.getValue());
        // 金融产品代码
        dealTotal.setFinprodId(settleTransDataDto.getFinprodId());
        // 金融产品类型（投管分类）
        dealTotal.setFinprodType2(settleTransDataDto.getFinprodType2());
        // 分支序号
        dealTotal.setBranch(settleTransDataDto.getBranch());
        // 交易日
        dealTotal.setTradeDate(settleTransDataDto.getTradeDate());
        // 交割日
        dealTotal.setSettleDate(settleTransDataDto.getSettleDate());
        // 交易币种
        dealTotal.setCcy(settleTransDataDto.getCcy());
        // 交易份额
        dealTotal.setShareAmt(settleTransDataDto.getShareAmt());
        // 交易本金
        dealTotal.setPrinAmt(settleTransDataDto.getPrinAmt());
        // 利息总额
        dealTotal.setIntAmt(settleTransDataDto.getIntAmt());
        // 随交易支付费用
        BigDecimal feeAmtPay = ArithmeticUtil.doAdd(settleTransDataDto.getJsfAmt(), settleTransDataDto.getGhfAmt(), settleTransDataDto.getZgfAmt(), 
				settleTransDataDto.getSxfAmt(), settleTransDataDto.getSettleFee(), settleTransDataDto.getStampFee());
        dealTotal.setFeeAmtPay(feeAmtPay);
        // 交易金额
        dealTotal.setTradeAmt(settleTransDataDto.getTradeAmt());
        // 未随交易支付费用
        dealTotal.setFeeAmtUnpay(BigDecimal.ZERO);
        // 总交易费用
        dealTotal.setFeeAmt(feeAmtPay);
        // 交易方向
        dealTotal.setPs(settleTransDataDto.getPs());
        // 交易状态
        dealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        // 是否已撤销
        dealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        //证券管理户代码
        dealTotal.setSecManageAcctId(settleTransDataDto.getSecAcctId());
        // 备注
        dealTotal.setRemark(DEAL_REMARK);
        if (cqTotalTrdProductDealDto != null) {
            // 前置交易编号
	        dealTotal.setbTradeId(cqTotalTrdProductDealDto.getTotleTradeId());
			// 权益登记日
			dealTotal.setRegDate(cqTotalTrdProductDealDto.getRegDate());
			// 单位净价
			dealTotal.setUnitCprice(cqTotalTrdProductDealDto.getUnitCprice());
			// 分红到账日
			dealTotal.setBonusCfmDate(cqTotalTrdProductDealDto.getBonusCfmDate());
        }
        else {
        	String calendarId = CacheUtil.getSysParam(StlDataDealProcessConstant.STL_DOWNDATA_DEAL_SHJYS_CALID);
        	// 权益登记日
        	dealTotal.setRegDate(stlDataDealProcessCommonService.getBeforeWorkDay(settleTransDataDto.getTradeDate(), calendarId));
        }
		// 分红类型
		if (StringUtil.equals(DictCodeList.交易方向_红利再投.getValue(), settleTransDataDto.getPs())) {
			dealTotal.setPayType(DictCodeList.分红方式_红利再投.getValue());
		}
		else {
			dealTotal.setPayType(DictCodeList.分红方式_现金分红.getValue());
		}
		
        return dealTotal;
    }

    /**
     * 生成 金融产品交易
     *
     * @param dealTotal 金融产品汇总交易DTO TotalTrdProductDealDto
     * @param settleTransDataDto 成交明细
     * @return TrdProductDealDto
     */
    private TrdProductDealDto generateProductDeal(SettleTransDataDto settleTransDataDto, TotalTrdProductDealDto dealTotal) {
    	
        TrdProductDealDto trdProductDealDto = new TrdProductDealDto();
     // 交易编号，按现有的交易编号生成规则，自动生成
        trdProductDealDto.setTradeId(settleCommonService.buildBussinessCodeExt(DictCodeList.共通业务类型_资产存续期交易));
        // 金融产品代码
        trdProductDealDto.setFinprodId(settleTransDataDto.getFinprodId());
        // 金融产品类型（投管分类）
        trdProductDealDto.setFinprodType2(settleTransDataDto.getFinprodType2());
        // 分支序号
        trdProductDealDto.setBranch(0L);
        // 交易日期
        trdProductDealDto.setTradeDate(settleTransDataDto.getTradeDate());
        // 交割日期
        trdProductDealDto.setSettleDate(settleTransDataDto.getSettleDate());
        // 清算速度
        trdProductDealDto.setSettleSpeed(settleTransDataDto.getSettleSpeed());
        if (StringUtil.equals(DictCodeList.交易方向_红利再投.getValue(), settleTransDataDto.getPs())) {
            // 交易份额
            trdProductDealDto.setShareAmt(settleTransDataDto.getShareAmt());
            // 交易本金
            trdProductDealDto.setPrinAmt(settleTransDataDto.getTradeAmt());
        }
        
    	// 交易币种
        trdProductDealDto.setCcy(settleTransDataDto.getCcy());
        // 利息总额
        trdProductDealDto.setIntAmt(settleTransDataDto.getTradeAmt());
        //全价总额
        trdProductDealDto.setFpriceAmt(settleTransDataDto.getTradeAmt());
        // 随交易支付费用
        BigDecimal feeAmtPay = ArithmeticUtil.doAdd(settleTransDataDto.getJsfAmt(), settleTransDataDto.getGhfAmt(), settleTransDataDto.getZgfAmt(), 
				settleTransDataDto.getSxfAmt(), settleTransDataDto.getSettleFee());
        trdProductDealDto.setFeeAmtPay(feeAmtPay);
        // 交易金额
        trdProductDealDto.setTradeAmt(settleTransDataDto.getTradeAmt());
        // 未随交易支付费用
        trdProductDealDto.setFeeAmtUnpay(BigDecimal.ZERO);
        //总交易费用
        trdProductDealDto.setFeeAmt(feeAmtPay);
        // 交易方向
        trdProductDealDto.setPs(settleTransDataDto.getPs());
        // 交易状态
        trdProductDealDto.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        // 设置 是否已撤销，默认“否”
        trdProductDealDto.setIsCancel(DictCodeList.是否_否.getValue());
        // 是否外部交易
        trdProductDealDto.setIsOutTrade(DictCodeList.是否_是.getValue());
        // 交易场所
        trdProductDealDto.setTradeMarket(settleTransDataDto.getTradeMarket());
        // 证券管理户代码
        trdProductDealDto.setSecManageAcctId(settleTransDataDto.getSecAcctId());

        // 登记托管机构
        trdProductDealDto.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());

        // 投资组合代码
        trdProductDealDto.setPortfolioId(settleTransDataDto.getPortfolioId());
        // 委托代码
        trdProductDealDto.setEntrustId(settleTransDataDto.getOrderId());
    	// 资金账户
        trdProductDealDto.setAcctNo(settleTransDataDto.getAcctNo());
        
        // 汇总交易编号
        trdProductDealDto.setTotleTradeId(dealTotal.getTotleTradeId());

		// 权益登记日份额
		trdProductDealDto.setRegDateAmt(settleTransDataDto.getShareAmt());

        // 成交编号
        trdProductDealDto.setDealId(settleTransDataDto.getTradeId());
        return trdProductDealDto;
    }

    /**
	 * 金融产品交易费用明细DTO
	 *
	 * @param deal                金融产品交易明细
	 * @param settleTransDataDto 成交数据明细
	 * @return 金融产品交易费用明细DTO
	 */
	private List<TrdTradeFeeDetail> generateTradeFeeDetail(TrdProductDealDto deal, SettleTransDataDto settleTransDataDto) {
	    List<TrdTradeFeeDetail> res = new ArrayList<>();
    	if (StringUtil.isNotEmpty(settleTransDataDto.getJsfAmt()) && settleTransDataDto.getJsfAmt().compareTo(new BigDecimal(0)) != 0) {
	        // 经手费
	        TrdTradeFeeDetail jsf = new TrdTradeFeeDetail();
	        // 交易编号
	        jsf.setTradeId(deal.getTradeId());
	
	        // 费用类型
	        jsf.setFeeType(DictCodeListExt1.一次性费用类型_经手费.getValue());
	        // 是否随交易支付
	        jsf.setIsPayWithTrade(DictCodeList.是否_是.getValue());
	        // 费用金额
	        jsf.setFeeAmt(settleTransDataDto.getJsfAmt());
            // 登记托管机构
	        jsf.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
	        res.add(jsf);
    	}

    	if (StringUtil.isNotEmpty(settleTransDataDto.getGhfAmt()) && settleTransDataDto.getGhfAmt().compareTo(new BigDecimal(0)) != 0) {
	        // 过户费
	        TrdTradeFeeDetail ghf = new TrdTradeFeeDetail();
	        // 交易编号
	        ghf.setTradeId(deal.getTradeId());
	
	        // 费用类型
	        ghf.setFeeType(DictCodeListExt1.一次性费用类型_过户费.getValue());
	        // 是否随交易支付
	        ghf.setIsPayWithTrade(DictCodeList.是否_是.getValue());
	        // 费用金额
	        ghf.setFeeAmt(settleTransDataDto.getGhfAmt());
            // 登记托管机构
	        ghf.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
	        res.add(ghf);
    	}

    	if (StringUtil.isNotEmpty(settleTransDataDto.getZgfAmt()) && settleTransDataDto.getZgfAmt().compareTo(new BigDecimal(0)) != 0) {
	        // 证管费
	        TrdTradeFeeDetail zgf = new TrdTradeFeeDetail();
	        // 交易编号
	        zgf.setTradeId(deal.getTradeId());
	
	        // 费用类型
	        zgf.setFeeType(DictCodeListExt1.一次性费用类型_证管费.getValue());
	        // 是否随交易支付
	        zgf.setIsPayWithTrade(DictCodeList.是否_是.getValue());
	        // 费用金额
	        zgf.setFeeAmt(settleTransDataDto.getZgfAmt());
            // 登记托管机构
	        zgf.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
	        res.add(zgf);
    	}

    	if (StringUtil.isNotEmpty(settleTransDataDto.getSxfAmt()) && settleTransDataDto.getSxfAmt().compareTo(new BigDecimal(0)) != 0) {
	        // 手续费
	        TrdTradeFeeDetail sxf = new TrdTradeFeeDetail();
	        // 交易编号
	        sxf.setTradeId(deal.getTradeId());
	
	        // 费用类型
	        sxf.setFeeType(DictCodeListExt1.一次性费用类型_交易手续费.getValue());
	        // 是否随交易支付
	        sxf.setIsPayWithTrade(DictCodeList.是否_是.getValue());
	        // 费用金额
	        sxf.setFeeAmt(settleTransDataDto.getSxfAmt());
            // 登记托管机构
	        sxf.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
	        res.add(sxf);
    	}


    	if (StringUtil.isNotEmpty(settleTransDataDto.getSettleFee()) && settleTransDataDto.getSettleFee().compareTo(new BigDecimal(0)) != 0) {
	        // 结算费
	        TrdTradeFeeDetail settleFee = new TrdTradeFeeDetail();
	        // 交易编号
	        settleFee.setTradeId(deal.getTradeId());
	
	        // 费用类型
	        settleFee.setFeeType(DictCodeListExt1.一次性费用类型_结算费.getValue());
	        // 是否随交易支付
	        settleFee.setIsPayWithTrade(DictCodeList.是否_是.getValue());
	        // 费用金额
	        settleFee.setFeeAmt(settleTransDataDto.getSettleFee());
            // 登记托管机构
	        settleFee.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
	        res.add(settleFee);
    	}

    	if (StringUtil.isNotEmpty(settleTransDataDto.getCommissionAmt()) && settleTransDataDto.getCommissionAmt().compareTo(new BigDecimal(0)) != 0) {
	        // 佣金
	        TrdTradeFeeDetail commision = new TrdTradeFeeDetail();
	        // 交易编号
	        commision.setTradeId(deal.getTradeId());
	
	        // 费用类型
	        commision.setFeeType(DictCodeListExt1.一次性费用类型_佣金.getValue());
	        // 是否随交易支付
	        commision.setIsPayWithTrade(settleTransDataDto.getIsPayWithTrade());
	        // 费用金额
	        commision.setFeeAmt(settleTransDataDto.getCommissionAmt());
            // 登记托管机构
	        commision.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
	        res.add(commision);
    	}
	    return res;
	}

    /**
     * 生成划款明细
     * @param deal
     * @param StlPayOrders
     * @return
     */
    protected StlPayOrders generateStlPayOrders(TrdProductDealDto deal) {
        StlPayOrders stlPayOrders = new StlPayOrders();
        // 指令编号
        stlPayOrders.setOrderId(settleCommonService.buildBussinessIdBySeqno("9998"));
        // 结算日期
        stlPayOrders.setSettleDate(deal.getSettleDate());
        //本方资金账户
        stlPayOrders.setMyAcctNo(deal.getAcctNo());
        // 收付方向
        stlPayOrders.setPs(DictCodeList.收付方向_收入.getValue());
        // 结算金额
        stlPayOrders.setSettleAmt(deal.getTradeAmt());
        // 币种
        stlPayOrders.setCcy(deal.getCcy());
        // 是否需要发送
        stlPayOrders.setIsNeedSend(DictCodeList.是否_否.getValue());
        // 是否汇总交易
        stlPayOrders.setIsTotalTrade(DictCodeList.是否_否.getValue());
        // 交易编号
        stlPayOrders.setTradeId(deal.getTradeId());
        // 指令状态
        stlPayOrders.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());
        // 投资组合代码
        stlPayOrders.setPortfolioId(deal.getPortfolioId());
        // 划款类型
        stlPayOrders.setPayType(DictCodeListExt1.平安划款类型_清算划款.getValue());
        
        return stlPayOrders;
    }

	/**
	 * 除权交易金融产品汇总交易DTO
	 *
	 * @param dealTotal 金融产品汇总交易
	 * @param settleTransDataDelDtos 除权交易
	 * @return 金融产品汇总交易DTO
	 */
	private TotalTrdProductDealDto setCqDealTotal(TotalTrdProductDealDto dealTotal, List<SettleDelDealDetailDto> settleTransDataDelDtos) {

		SettleDelDealDetailDto settleTransDataDelDto = settleTransDataDelDtos.get(0);
		// ∑除权交易明细.权益登记日份额
		BigDecimal registShareAll = BigDecimal.ZERO;

		// ∑除权交易明细.分红金额
		BigDecimal tradeAmtAll = BigDecimal.ZERO;

		for (SettleDelDealDetailDto transDataDelDto : settleTransDataDelDtos) {
			registShareAll = ArithmeticUtil.doAdd(registShareAll, transDataDelDto.getRedDateAmt());
			tradeAmtAll = ArithmeticUtil.doAdd(tradeAmtAll, transDataDelDto.getBonusAmt());
		}

		//  汇总交易编号
		dealTotal.setTotleTradeId(settleCommonService.buildBussinessCodeExt(DictCodeList.共通业务类型_资产存续期交易));

		// 业务类型 TRD14
		dealTotal.setBusiType(DictCodeList.共通业务类型_基金类资产存续期.getValue());
		// 金融产品代码
		dealTotal.setFinprodId(settleTransDataDelDto.getFinprodId());
		// 金融产品类型（投管分类）
		dealTotal.setFinprodType2(settleTransDataDelDto.getFinprodType2());
		// 分支序号
		dealTotal.setBranch(0L);
		// 交易日
		dealTotal.setTradeDate(settleTransDataDelDto.getDelDate());
		// 交割日
		dealTotal.setSettleDate(settleTransDataDelDto.getDelDate());
		// 交易币种
		dealTotal.setCcy(settleTransDataDelDto.getCcy());
		// 权益登记日
		dealTotal.setRegDate(settleTransDataDelDto.getRegDate());
		// 分红类型
		dealTotal.setPayType(DictCodeList.分红方式_现金分红.getValue());
		// 单位净价
		dealTotal.setUnitCprice(settleTransDataDelDto.getUnitBonusUnitValue());
		// 分红到账日
		dealTotal.setBonusCfmDate(settleTransDataDelDto.getDevidentDate());
		// 交易本金
		dealTotal.setPrinAmt(registShareAll);
		// 利息总额
		dealTotal.setIntAmt(tradeAmtAll);
		// 交易金额
		dealTotal.setTradeAmt(tradeAmtAll);
		// 交易方向
		dealTotal.setPs(DictCodeList.交易方向_除权登记.getValue());
		// 交易状态
		dealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
		// 是否已撤销
		dealTotal.setIsCancel(DictCodeList.是否_否.getValue());
		// 证券管理户代码
		dealTotal.setSecManageAcctId(settleTransDataDelDto.getSecAcctId());
		// 备注
		dealTotal.setRemark(DEAL_REMARK);

		return dealTotal;
	}

	/**
	 * 根据除权交易生成金融产品交易明细
	 * @param totalTrdProductDealDto 汇总交易明细
	 * @param list 除权交易明细
	 * @return 金融产品交易明细
	 */
	private TrdProductDealDto generateCqProductDelDeal(TotalTrdProductDealDto totalTrdProductDealDto, List<SettleDelDealDetailDto> list) {

		TrdProductDealDto trdProductDealDto = new TrdProductDealDto();
		setCqTrdProductDealDel(trdProductDealDto, totalTrdProductDealDto, list);
		return trdProductDealDto;
	}

	/**
	 * 除权交易 金融产品交易明细设置
	 * @param trdProductDealDto 金融产品交易明细
	 * @param settleTransDataDelDtoList 除权交易明细列表
	 */
	private void setCqTrdProductDealDel(TrdProductDealDto trdProductDealDto, TotalTrdProductDealDto totalTrdProductDealDto, List<SettleDelDealDetailDto> settleTransDataDelDtoList) {
		// 取第一条数据
		SettleDelDealDetailDto settleTransDataDelDto = settleTransDataDelDtoList.get(0);

		// 交易编号，按现有的交易编号生成规则，自动生成
		trdProductDealDto.setTradeId(settleCommonService.buildBussinessCodeExt(DictCodeList.共通业务类型_资产存续期交易));
		// 金融产品代码
		trdProductDealDto.setFinprodId(settleTransDataDelDto.getFinprodId());
		// 金融产品类型（投管分类）
		trdProductDealDto.setFinprodType2(settleTransDataDelDto.getFinprodType2());
		// 分支序号
		trdProductDealDto.setBranch(0L);
		// 交易日
		trdProductDealDto.setTradeDate(settleTransDataDelDto.getDelDate());
		// 交割日
		trdProductDealDto.setSettleDate(settleTransDataDelDto.getDelDate());
		//交易币种
		trdProductDealDto.setCcy(settleTransDataDelDto.getCcy());
		// 单位净价
		trdProductDealDto.setUnitCprice(settleTransDataDelDto.getUnitBonusUnitValue());
		// 交易本金
		trdProductDealDto.setPrinAmt(settleTransDataDelDto.getBonusAmt());
		// 利息总额
		trdProductDealDto.setIntAmt(settleTransDataDelDto.getBonusAmt());
		// 全价总额
		trdProductDealDto.setFpriceAmt(settleTransDataDelDto.getBonusAmt());
		// 交易金额
		trdProductDealDto.setTradeAmt(settleTransDataDelDto.getBonusAmt());
		// 交易方向
		trdProductDealDto.setPs(DictCodeList.交易方向_除权登记.getValue());
		// 交易状态
		trdProductDealDto.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
		// 是否已撤销
		trdProductDealDto.setIsCancel(DictCodeList.是否_否.getValue());
		// 汇总交易编号
		trdProductDealDto.setTotleTradeId(totalTrdProductDealDto.getTotleTradeId());
		// 是否外部交易
		trdProductDealDto.setIsOutTrade(DictCodeList.是否_是.getValue());
		// 交易场所
		trdProductDealDto.setTradeMarket(settleTransDataDelDto.getTradeMarket());
		// 证券管理户代码
		trdProductDealDto.setSecManageAcctId(settleTransDataDelDto.getSecAcctId());
		// 登记托管机构
		trdProductDealDto.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
		// 投资组合代码
		trdProductDealDto.setPortfolioId(settleTransDataDelDto.getPortfolioId());
		// 权益登记日
		trdProductDealDto.setRegDate(settleTransDataDelDto.getRegDate());
		// 权益登记日份额
		trdProductDealDto.setRegDateAmt(settleTransDataDelDto.getRedDateAmt());
	}

    /**
     * 生成投资组合交易
     *
     * @param dealList            交易明细
     * @param settleTransDataDtos 成交明细
     * @return 投资组合交易
     */
    protected PtlTrade generatePtlTrade(TrdProductDealDto deal) {
        PtlTrade ptlTrade = new PtlTrade();
        // 组合代码
        ptlTrade.setPortfolioId(deal.getPortfolioId());
        // 业务编号
        ptlTrade.setBusiNo(deal.getTradeId());
        // 业务表
        ptlTrade.setBusiTable(DictCodeListStl.业务表_金融产品交易表.getValue());
        // 业务明细代码
        ptlTrade.setBusiId(deal.getFinprodId());
        // 投资目的
        ptlTrade.setInvAim(deal.getInvAim());
        // 证券托管户代码
        ptlTrade.setSecManageAcctId(deal.getSecManageAcctId());
        // 实际发生日
        ptlTrade.setSettleDate(deal.getSettleDate());
        // 业务类型
        ptlTrade.setBusiType(DictCodeList.共通业务类型_基金类资产交易.getValue());
        return ptlTrade;
    }
}
