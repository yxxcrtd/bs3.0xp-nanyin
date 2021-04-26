package com.joyintech.tams.settle.batch.datagather.processor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.SettleTransTgMapping;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListStl;
import com.joyintech.tams.settle.batch.datagather.constant.StlDataGatherConstant;
import com.joyintech.tams.settle.batch.datagather.dto.InvestTradeReaderDto;
import com.joyintech.tams.settle.batch.datagather.dto.InvestTradeWriteDto;
import com.joyintech.tams.settle.batch.datagather.dto.SettleDelDealDetailDto;
import com.joyintech.tams.settle.batch.datagather.dto.SettleTransDataDto;
import com.joyintech.tams.settle.batch.datagather.dto.StlDataGatherShare2ProcDto;
import com.joyintech.tams.settle.batch.datagather.dto.StlDataGatherShare2ProcSubDto;
import com.joyintech.tams.settle.batch.datagather.dto.TotalTrdProductDealDto;
import com.joyintech.tams.settle.batch.datagather.dto.TrdProductDealDto;
import com.joyintech.tams.settle.common.service.SettleCommonService;

/**
 * 股票除权、现金分红、送转交易处理
 * @author chenzhuo
 * @date 2020/7/17
 * @see StlDataGatherShares2Processor
 * @since 1.0
 */
@Service
@Lazy
public class StlDataGatherShares2Processor extends StlDataGatherProcessor {

	@Autowired
	private SettleCommonService settleCommonService;

	@Override
	public InvestTradeWriteDto process(NeedOperateEntity operateEntity) throws Exception {
		InvestTradeReaderDto investTradeReaderDto = (InvestTradeReaderDto)operateEntity;
		if (CollectionUtils.isNotEmpty(investTradeReaderDto.getSettleTransDataDelDtoList())){
			InvestTradeWriteDto investTradeWriteDto = new InvestTradeWriteDto();
			List<SettleDelDealDetailDto> settleTransDataDelDtoList = investTradeReaderDto.getSettleTransDataDelDtoList();
			Map<String, List<SettleDelDealDetailDto>> collects = settleTransDataDelDtoList.stream().collect(Collectors.groupingBy(x -> {
				return x.getFinprodId() + x.getFinprodType2() + x.getDelDate();
			}));
			// 金融产品汇总交易List
			List<TotalTrdProductDealDto> totalTrdProductDealDtos = new ArrayList<>();
			
			// 金融产品汇总交易处理
			for (List<SettleDelDealDetailDto> list : collects.values()) {
				// 汇总交易
				TotalTrdProductDealDto totalTrdProductDealDto = generatorTotalTrdProductDealDto(list);
				totalTrdProductDealDtos.add(totalTrdProductDealDto);
			}
			investTradeWriteDto.setFinProductDealTotalList(totalTrdProductDealDtos);
			
			// 金融产品交易List-含除权交易、现金分红交易、送转交易
			List<TrdProductDealDto> dealDtoList = generatorTrdProductDealDtos(totalTrdProductDealDtos, collects.values(), investTradeReaderDto.getSettleTransDataList());
			investTradeWriteDto.setProductDealList(dealDtoList);
			
			for (TrdProductDealDto trdProductDealDto : investTradeWriteDto.getProductDealList()) {
				if (StringUtil.equals(trdProductDealDto.getPs(), DictCodeList.交易方向_除权登记.getValue())) {
					// 成交编号业务编号关联表（只针对除权登记数据）
					SettleTransTgMapping settleTransTgMapping = new SettleTransTgMapping();
					settleTransTgMapping.setTradeId(IDGenerator.generate(20));
					settleTransTgMapping.setTradeIdTg(trdProductDealDto.getTradeId());
					settleTransTgMapping.setTgDataStatus(DictCodeListStl.投管交易数据状态_处理结束.getValue());
					investTradeWriteDto.getSettleTransTgMappingList().add(settleTransTgMapping);
				}
			}
			// 组合品交易List-含除权交易、现金分红交易、送转交易
			List<PtlTrade> ptlTradeList = generatePtlTrade(dealDtoList);
			investTradeWriteDto.setPtlTradeList(ptlTradeList);
			
			// 金融产品交易费用明细
			List<TrdTradeFeeDetail> trdTradeFeeDetailList = generateTradeFeeDetail(dealDtoList);
			investTradeWriteDto.setTradeFeeDetailList(trdTradeFeeDetailList);
			
			// 划款指令明细
			List<StlPayOrders> stlPayOrdersList = generateStlPayOrdersList(totalTrdProductDealDtos, dealDtoList, investTradeReaderDto.getSettleTransDataList());
			investTradeWriteDto.setStlPayOrdersList(stlPayOrdersList);
			
			investTradeWriteDto.setSecType(this.getSecType());
			
			return investTradeWriteDto;
		}
		else {
			return null;
		}
	}

	@Override
	protected String getSecType() {
		return DictCodeListExt1.证券品种_股票.getValue();
	}

	/**
	 * 金融产品汇总交易生成
	 * @param list List<SettleDelDealDetailDto>
	 * @return 金融产品汇总交易
	 */
	private TotalTrdProductDealDto generatorTotalTrdProductDealDto(List<SettleDelDealDetailDto> list) {
		TotalTrdProductDealDto totalTrdProductDealDto = new TotalTrdProductDealDto();
		// 交易编号
		String totalTradeId = settleCommonService.buildBussinessCodeExt(DictCodeListExt2.共通业务类型_股票投资交易);;
		totalTrdProductDealDto.setTotleTradeId(totalTradeId);
		// 业务类型
		totalTrdProductDealDto.setBusiType(DictCodeListExt2.共通业务类型_股票分红交易.getValue());
		// 金融产品代码
		totalTrdProductDealDto.setFinprodId(list.get(0).getFinprodId());
		// 金融产品类型（投管分类）
		totalTrdProductDealDto.setFinprodType2(list.get(0).getFinprodType2());
		// 分支序号
		totalTrdProductDealDto.setBranch(0L);
		// 交易日
		totalTrdProductDealDto.setTradeDate(list.get(0).getDelDate());
		// 交易方向(每股送转、每股派息都有值，并且大于0，则为‘分红送转’。每股派息有值，并且大于0，则为‘现金分红’。每股送转有值，并且大于0，则为‘送转’)
		if (list.get(0).getPreDevidengCash() != null && list.get(0).getPreDevidengAmt() != null && list.get(0).getPreDevidengCash().compareTo(new BigDecimal(0)) > 0 &&
				list.get(0).getPreDevidengAmt().compareTo(new BigDecimal(0)) > 0) {
			totalTrdProductDealDto.setPs(DictCodeList.股票分红类型_分红送转.getValue());
		}
		else if (list.get(0).getPreDevidengCash() != null  && list.get(0).getPreDevidengCash().compareTo(new BigDecimal(0)) > 0 && list.get(0).getPreDevidengAmt() == null) {
			totalTrdProductDealDto.setPs(DictCodeList.股票分红类型_现金分红.getValue());
		}
		else if (list.get(0).getPreDevidengAmt() != null  && list.get(0).getPreDevidengAmt().compareTo(new BigDecimal(0)) > 0 && list.get(0).getPreDevidengCash() == null) {
			totalTrdProductDealDto.setPs(DictCodeList.股票分红类型_送转.getValue());
		}
		// 交易状态
		totalTrdProductDealDto.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
    	// 是否已撤销
		totalTrdProductDealDto.setIsCancel(DictCodeList.是否_否.getValue());
		// 权益登记日
		totalTrdProductDealDto.setRegDate(list.get(0).getRegDate());
		// 除权除息日
		totalTrdProductDealDto.setExDevidentDate(list.get(0).getDelDate());
		// 红股上市日
		totalTrdProductDealDto.setRedShareListingDate(list.get(0).getRedShareListingDate());
		// 派息日
		totalTrdProductDealDto.setDevidentDate(list.get(0).getDevidentDate());
		// 每股送转
		totalTrdProductDealDto.setPerDevidentAmt(list.get(0).getPreDevidengAmt());
		// 每股派息
		totalTrdProductDealDto.setPerDevidentCash(list.get(0).getPreDevidengCash());
		// 分红金额
		totalTrdProductDealDto.setDevidentCash(list.get(0).getBonusAmt());
		// 分红股本
		totalTrdProductDealDto.setDevidentAmt(list.get(0).getBonusQty());
		// 备注
		totalTrdProductDealDto.setRemark(StlDataGatherConstant.STL_DATA_GATHER_REMARK);
		// 投管业务类型
		totalTrdProductDealDto.setTgBusiType(DictCodeListExt2.平安业务类型_分红送转.getValue());
		// 交易场所
		totalTrdProductDealDto.setTradeMarket(list.get(0).getTradeMarket());
		
		return totalTrdProductDealDto;
	}
	
	/**
	 * 金融产品交易生成
	 * @param totalTrdProductDealDtos 汇总交易List
	 * @param settleDelDealDetailDtos 除权交易明细List Collection
	 * @param settleTransDataDtos 成交明细数据 
	 * @return 金融产品交易List
	 */
	private List<TrdProductDealDto> generatorTrdProductDealDtos(List<TotalTrdProductDealDto> totalTrdProductDealDtos, 
			Collection<List<SettleDelDealDetailDto>> settleDelDealDetailDtos, List<SettleTransDataDto> settleTransDataDtos) {
		List<TrdProductDealDto> result = new ArrayList<TrdProductDealDto>();
		// 分组
		List<StlDataGatherShare2ProcDto> list = new ArrayList<StlDataGatherShare2ProcDto>();
		for (List<SettleDelDealDetailDto> settleDelDealDetailDtoList : settleDelDealDetailDtos) {
			StlDataGatherShare2ProcDto stlDataGatherShare2ProcDto = new StlDataGatherShare2ProcDto();
			// 汇总交易
			for (TotalTrdProductDealDto totalTrdProductDealDto : totalTrdProductDealDtos) {
				if (StringUtil.equals(totalTrdProductDealDto.getFinprodId(), settleDelDealDetailDtoList.get(0).getFinprodId()) && 
					StringUtil.equals(totalTrdProductDealDto.getFinprodType2(), settleDelDealDetailDtoList.get(0).getFinprodType2()) && 
					(DateUtil.compareDate(totalTrdProductDealDto.getExDevidentDate(), settleDelDealDetailDtoList.get(0).getDelDate()) == 0)) {
					stlDataGatherShare2ProcDto.setTotalTrdProductDealDto(totalTrdProductDealDto);
					break;
				}
			}
			// StlDataGatherShare2ProcSubDto的LIST数据
			List<StlDataGatherShare2ProcSubDto> stlDataGatherShare2ProcSubDtoList = new ArrayList<StlDataGatherShare2ProcSubDto>();
			for (SettleDelDealDetailDto settleDelDealDetailDto : settleDelDealDetailDtoList) {
				StlDataGatherShare2ProcSubDto stlDataGatherShare2ProcSubDto = new StlDataGatherShare2ProcSubDto();
				// SettleDelDealDetailDto
				stlDataGatherShare2ProcSubDto.setSettleDelDealDetailDto(settleDelDealDetailDto);
				// 现金分红成交数据明细List
				List<SettleTransDataDto> settleTransDataDtoListFh = new ArrayList<SettleTransDataDto>();
				for (SettleTransDataDto settleTransDataDto : settleTransDataDtos) {
					if (StringUtil.equals(settleTransDataDto.getFinprodId(), settleDelDealDetailDto.getFinprodId()) &&
						StringUtil.equals(settleTransDataDto.getFinprodType2(), settleDelDealDetailDto.getFinprodType2()) &&
						StringUtil.equals(settleTransDataDto.getPs(), DictCodeList.交易方向_现金分红.getValue()) &&
						/*(DateUtil.compareDate(settleTransDataDto.getTradeDate(), settleDelDealDetailDto.getDevidentDate()) == 0) &&*/
						StringUtil.equals(settleTransDataDto.getPortfolioId(), settleDelDealDetailDto.getPortfolioId())) {
						settleTransDataDtoListFh.add(settleTransDataDto);
					}
				}
				stlDataGatherShare2ProcSubDto.setSettleTransDataDtoListFh(settleTransDataDtoListFh);
				// 送转成交数据明细List
				List<SettleTransDataDto> settleTransDataDtoListFSz = new ArrayList<SettleTransDataDto>();
				for (SettleTransDataDto settleTransDataDto : settleTransDataDtos) {
					if (StringUtil.equals(settleTransDataDto.getFinprodId(), settleDelDealDetailDto.getFinprodId()) &&
						StringUtil.equals(settleTransDataDto.getFinprodType2(), settleDelDealDetailDto.getFinprodType2()) &&
						StringUtil.equals(settleTransDataDto.getPs(), DictCodeListStl.交易方向_送股.getValue()) &&
						/*(DateUtil.compareDate(settleTransDataDto.getTradeDate(), settleDelDealDetailDto.getRedShareListingDate()) == 0) &&*/
						StringUtil.equals(settleTransDataDto.getPortfolioId(), settleDelDealDetailDto.getPortfolioId())) {
						settleTransDataDtoListFSz.add(settleTransDataDto);
					}
				}
				stlDataGatherShare2ProcSubDto.setSettleTransDataDtoListSz(settleTransDataDtoListFSz);
				stlDataGatherShare2ProcSubDtoList.add(stlDataGatherShare2ProcSubDto);
			}
			stlDataGatherShare2ProcDto.setStlDataGatherShare2ProcSubDtoList(stlDataGatherShare2ProcSubDtoList);
			list.add(stlDataGatherShare2ProcDto);
		}
		
		// 分组结束，开始生成金融产品交易（除权、现金分红、送转的一把处理）
		for (StlDataGatherShare2ProcDto stlDataGatherShare2ProcDto : list) {
			// 汇总交易
			TotalTrdProductDealDto totalTrdProductDealDto = stlDataGatherShare2ProcDto.getTotalTrdProductDealDto();
			for (StlDataGatherShare2ProcSubDto stlDataGatherShare2ProcSubDto : stlDataGatherShare2ProcDto.getStlDataGatherShare2ProcSubDtoList()) {
				// 除权交易生成
				TrdProductDealDto delTrdProductDealDto = new TrdProductDealDto();
				generatorDelTrdProductDealDto(delTrdProductDealDto, stlDataGatherShare2ProcSubDto.getSettleDelDealDetailDto(), totalTrdProductDealDto);
				result.add(delTrdProductDealDto);
				
				// 现金分红交易生成
				if (stlDataGatherShare2ProcSubDto.getSettleTransDataDtoListFh() != null && stlDataGatherShare2ProcSubDto.getSettleTransDataDtoListFh().size() > 0) {
					TrdProductDealDto fhTrdProductDealDto = new TrdProductDealDto();
					generatorTrdProductDealDto(fhTrdProductDealDto, delTrdProductDealDto, totalTrdProductDealDto, stlDataGatherShare2ProcSubDto.getSettleTransDataDtoListFh());
					// 股票现金分红的清算日期需要设置成汇总交易的清算日期（分红公告的派息日）
					fhTrdProductDealDto.setSettleDate(totalTrdProductDealDto.getDevidentDate());
					fhTrdProductDealDto.setTradeDate(totalTrdProductDealDto.getDevidentDate());
					// 股票现金分红交易的权益登记日需要设值
					fhTrdProductDealDto.setRegDate(delTrdProductDealDto.getRegDate());
					// 股票现金分红交易的单位利息需要设值
					fhTrdProductDealDto.setUnitInt(delTrdProductDealDto.getUnitInt());
					result.add(fhTrdProductDealDto);
				}
				
				// 送转分红交易生成
				if (stlDataGatherShare2ProcSubDto.getSettleTransDataDtoListSz() != null && stlDataGatherShare2ProcSubDto.getSettleTransDataDtoListSz().size() > 0) {
					TrdProductDealDto szTrdProductDealDto = new TrdProductDealDto();
					generatorTrdProductDealDto(szTrdProductDealDto, delTrdProductDealDto, totalTrdProductDealDto, stlDataGatherShare2ProcSubDto.getSettleTransDataDtoListSz());
					// 股票现金分红的清算日期需要设置成汇总交易的清算日期（分红公告的派息日）
					szTrdProductDealDto.setSettleDate(totalTrdProductDealDto.getRedShareListingDate());
					szTrdProductDealDto.setTradeDate(totalTrdProductDealDto.getRedShareListingDate());
					// 股票红利再投交易的权益登记日需要设值
					szTrdProductDealDto.setRegDate(delTrdProductDealDto.getRegDate());
					// 股票红利再投交易的单位送转需要设值
					szTrdProductDealDto.setUnitCprice(delTrdProductDealDto.getUnitCprice());
					result.add(szTrdProductDealDto);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 除权交易生成
	 * @param trdProductDeal 除权交易
	 * @param settleDelDealDetailDto 除权交易公告数据
	 * @param totalTrdProductDealDto 汇总交易数据
	 */
	private void generatorDelTrdProductDealDto(TrdProductDealDto trdProductDeal, SettleDelDealDetailDto settleDelDealDetailDto, TotalTrdProductDealDto totalTrdProductDealDto) {
		// 交易编号
		String tradeId = settleCommonService.buildBussinessCodeExt(DictCodeListExt2.共通业务类型_股票投资交易);;
    	trdProductDeal.setTradeId(tradeId);
    	// 金融产品代码
    	trdProductDeal.setFinprodId(settleDelDealDetailDto.getFinprodId());
    	// 金融产品类型（投管分类）
    	trdProductDeal.setFinprodType2(settleDelDealDetailDto.getFinprodType2());
    	// 分支序号
    	trdProductDeal.setBranch(0L);
    	// 交易日
    	trdProductDeal.setTradeDate(settleDelDealDetailDto.getDelDate());
        // 交割日
        trdProductDeal.setSettleDate(totalTrdProductDealDto.getDevidentDate());
    	// 交易币种
    	trdProductDeal.setCcy(settleDelDealDetailDto.getCcy());
    	// 单位净价
    	trdProductDeal.setUnitCprice(settleDelDealDetailDto.getPreDevidengAmt());
    	// 单位利息
    	trdProductDeal.setUnitInt(settleDelDealDetailDto.getPreDevidengCash());
    	// 交易份额
    	trdProductDeal.setShareAmt(settleDelDealDetailDto.getBonusQty());
    	// 全价总额
    	trdProductDeal.setFpriceAmt(settleDelDealDetailDto.getBonusAmt());
    	// 随交易支付费用
    	trdProductDeal.setFeeAmt(new BigDecimal(0));
    	// 交易金额
    	trdProductDeal.setTradeAmt(settleDelDealDetailDto.getBonusAmt());
    	// 未随交易支付费用
    	trdProductDeal.setFeeAmtUnpay(new BigDecimal(0));
    	// 总交易费用
    	trdProductDeal.setFeeAmt(new BigDecimal(0));
    	// 交易方向
    	trdProductDeal.setPs(DictCodeList.交易方向_除权登记.getValue());
    	// 交易状态
    	trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
    	// 是否已撤销
    	trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
    	// 汇总交易编号
    	trdProductDeal.setTotleTradeId(totalTrdProductDealDto.getTotleTradeId());
    	// 是否外部交易
    	trdProductDeal.setIsOutTrade(DictCodeList.是否_是.getValue());
    	// 证券管理户代码
    	trdProductDeal.setSecManageAcctId(settleDelDealDetailDto.getSecAcctId());
    	// 登记托管机构
    	trdProductDeal.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
    	// 投管业务类型
    	trdProductDeal.setTgBusiType(DictCodeListExt2.平安业务类型_分红送转.getValue());
    	// 投资组合代码
    	trdProductDeal.setPortfolioId(settleDelDealDetailDto.getPortfolioId());
    	// 权益登记日
    	trdProductDeal.setRegDate(settleDelDealDetailDto.getRegDate());
    	// 权益登记日份额
    	trdProductDeal.setRegDateAmt(settleDelDealDetailDto.getRedDateAmt());
	}
	
	/**
	 * 现金分红交易生成
	 * @param trdProductDeal 现金分红交易
	 * @param delTrdProductDealDto 除权交易数据
	 * @param totalTrdProductDealDto 汇总交易数据
	 * @param settleTransDataDtoList 现金分红成交明细数据List
	 */
	private void generatorTrdProductDealDto(TrdProductDealDto trdProductDeal, TrdProductDealDto delTrdProductDealDto, 
			TotalTrdProductDealDto totalTrdProductDealDto, List<SettleTransDataDto> settleTransDataDtos) {
		if (settleTransDataDtos != null && settleTransDataDtos.size() > 0) {
			// 交易金额_原始汇总
    		BigDecimal tradeAmtRawSum = BigDecimal.ZERO;
    		// ∑交易份额
    		BigDecimal shareAmtSum = BigDecimal.ZERO;
    		// ∑交易本金
    		BigDecimal prinAmtSum = BigDecimal.ZERO;
    		// ∑交易金额
    		BigDecimal tradeAmtSum = BigDecimal.ZERO;
    		// 随交易支付费用
    		BigDecimal feeAmtPay = BigDecimal.ZERO;
    		// 未随交易支付费用
    		BigDecimal feeAmtUnpay = BigDecimal.ZERO;
    		// 经手费
    		BigDecimal jsfAmt = BigDecimal.ZERO;
    		// 过户费
    		BigDecimal ghfAmt = BigDecimal.ZERO;
    		// 证管费
    		BigDecimal zgfAmt = BigDecimal.ZERO;
    		// 手续费
    		BigDecimal sxfAmt = BigDecimal.ZERO;
    		// 结算费
    		BigDecimal settleFee = BigDecimal.ZERO;
    		// 佣金
    		BigDecimal commissionAmt = BigDecimal.ZERO;
    		
    		for (SettleTransDataDto settleTransDataDto : settleTransDataDtos) {
    			tradeAmtRawSum = ArithmeticUtil.doAdd(tradeAmtRawSum, settleTransDataDto.getTradeAmtRaw());
    			shareAmtSum = ArithmeticUtil.doAdd(shareAmtSum, settleTransDataDto.getShareAmt());
    			prinAmtSum = ArithmeticUtil.doAdd(prinAmtSum, settleTransDataDto.getPrinAmt());
    			tradeAmtSum = ArithmeticUtil.doAdd(tradeAmtSum, settleTransDataDto.getTradeAmt());
    			if (StringUtil.equals(DictCodeList.是否_是.getValue(), settleTransDataDto.getIsPayWithTrade())) {
    				feeAmtPay = ArithmeticUtil.doAdd(feeAmtPay, settleTransDataDto.getJsfAmt(), settleTransDataDto.getGhfAmt(), settleTransDataDto.getZgfAmt(), 
    						settleTransDataDto.getSxfAmt(), settleTransDataDto.getSettleFee(), settleTransDataDto.getCommissionAmt());
    			}
    			else {
    				feeAmtPay = ArithmeticUtil.doAdd(feeAmtPay, settleTransDataDto.getJsfAmt(), settleTransDataDto.getGhfAmt(), settleTransDataDto.getZgfAmt(), 
    						settleTransDataDto.getSxfAmt(), settleTransDataDto.getSettleFee());
    				feeAmtUnpay = ArithmeticUtil.doAdd(feeAmtUnpay, settleTransDataDto.getCommissionAmt());
    			}
    			jsfAmt = ArithmeticUtil.doAdd(jsfAmt, settleTransDataDto.getJsfAmt());
    			ghfAmt = ArithmeticUtil.doAdd(ghfAmt, settleTransDataDto.getGhfAmt());
    			zgfAmt = ArithmeticUtil.doAdd(zgfAmt, settleTransDataDto.getZgfAmt());
    			sxfAmt = ArithmeticUtil.doAdd(sxfAmt, settleTransDataDto.getSxfAmt());
    			settleFee = ArithmeticUtil.doAdd(settleFee, settleTransDataDto.getSettleFee());
    			commissionAmt = ArithmeticUtil.doAdd(commissionAmt, settleTransDataDto.getCommissionAmt());
    		}
	    	// 交易编号
	    	String tradeId = settleCommonService.buildBussinessCodeExt(DictCodeListExt2.共通业务类型_股票投资交易);;
	    	trdProductDeal.setTradeId(tradeId);
	    	// 金融产品代码
	    	trdProductDeal.setFinprodId(settleTransDataDtos.get(0).getFinprodId());
	    	// 金融产品类型（投管分类）
	    	trdProductDeal.setFinprodType2(settleTransDataDtos.get(0).getFinprodType2());
	    	// 分支序号
	    	trdProductDeal.setBranch(0L);
	    	// 交易日
	    	trdProductDeal.setTradeDate(settleTransDataDtos.get(0).getTradeDate());
	    	// 交割日
	    	trdProductDeal.setSettleDate(settleTransDataDtos.get(0).getSettleDate());
	    	// 清算速度
	    	trdProductDeal.setSettleSpeed(settleTransDataDtos.get(0).getSettleSpeed());
	    	// 交易币种
	    	trdProductDeal.setCcy(settleTransDataDtos.get(0).getCcy());
	    	// 交易份额
	    	if (StringUtil.equals(DictCodeListStl.交易方向_送股.getValue(), settleTransDataDtos.get(0).getPs())) {
	    		trdProductDeal.setShareAmt(shareAmtSum);
	    	}
	    	// 全价总额
	    	if (StringUtil.equals(DictCodeList.交易方向_现金分红.getValue(), settleTransDataDtos.get(0).getPs())) {
	    		trdProductDeal.setFpriceAmt(tradeAmtSum);
	    	}
	    	// 随交易支付费用
    		trdProductDeal.setFeeAmtPay(feeAmtPay);
	    	// 交易金额
    		trdProductDeal.setTradeAmt(tradeAmtSum);
	    	// 未随交易支付费用
    		trdProductDeal.setFeeAmtUnpay(feeAmtUnpay);
	    	// 总交易费用
    		trdProductDeal.setFeeAmt(ArithmeticUtil.doAdd(feeAmtPay, feeAmtUnpay));
	    	// 交易方向
    		if (StringUtil.equals(DictCodeListStl.交易方向_送股.getValue(), settleTransDataDtos.get(0).getPs())) {
    			trdProductDeal.setPs(DictCodeList.交易方向_红利再投.getValue());
    		}
    		else {
    			trdProductDeal.setPs(settleTransDataDtos.get(0).getPs());
    		}
	    	// 交易状态
    		trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
	    	// 是否已撤销
    		trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
    		// 汇总交易编号
    		trdProductDeal.setTotleTradeId(totalTrdProductDealDto.getTotleTradeId());
    		// 前置交易编号
    		trdProductDeal.setbTradeId(delTrdProductDealDto.getTradeId());
	    	// 是否外部交易
	    	trdProductDeal.setIsOutTrade(DictCodeList.是否_是.getValue());
	    	// 交易场所
	    	trdProductDeal.setTradeMarket(settleTransDataDtos.get(0).getTradeMarket());
	    	// 交易平台
	    	trdProductDeal.setTradePlat(settleTransDataDtos.get(0).getTradePlat());
	    	// 交易市场
	    	trdProductDeal.setTradeMarketMode(settleTransDataDtos.get(0).getTradeMarketMode());
	    	// 证券管理户代码
	    	trdProductDeal.setSecManageAcctId(settleTransDataDtos.get(0).getSecAcctId());
	    	// 登记托管机构
	    	trdProductDeal.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
	    	// 投管业务类型
	    	trdProductDeal.setTgBusiType(DictCodeListExt2.平安业务类型_分红送转.getValue());
	    	// 席位号
	    	trdProductDeal.setSeatId(settleTransDataDtos.get(0).getSeatNumber());
	    	// 投资组合代码
	    	trdProductDeal.setPortfolioId(settleTransDataDtos.get(0).getPortfolioId());
	    	// 成交编号
	    	String dealId = settleTransDataDtos.stream().map(SettleTransDataDto::getTradeId).collect(Collectors.joining(GlobalConstant.COMMA));
	    	trdProductDeal.setDealId(dealId);
	    	// 经手费
	    	trdProductDeal.setJsfAmt(jsfAmt);
    		// 过户费
	    	trdProductDeal.setGhfAmt(ghfAmt);
    		// 证管费
	    	trdProductDeal.setZgfAmt(zgfAmt);
    		// 手续费
	    	trdProductDeal.setSxfAmt(sxfAmt);
    		// 结算费
	    	trdProductDeal.setSettleFee(settleFee);
    		// 佣金
	    	trdProductDeal.setCommissionAmt(commissionAmt);
	    	// 资金账户
	    	trdProductDeal.setAcctNo(settleTransDataDtos.get(0).getAcctNo());
	        // 委托代码
	    	trdProductDeal.setEntrustId(settleTransDataDtos.get(0).getOrderId());
		}
	}
	
	@Override
    protected List<PtlTrade> generatePtlTrade(List<TrdProductDealDto> dealList) {
		return dealList.stream().map(trdProductDealDto -> {
            PtlTrade ptlTrade = new PtlTrade();
            // 组合代码
            ptlTrade.setPortfolioId(trdProductDealDto.getPortfolioId());
            // 业务编号
            ptlTrade.setBusiNo(trdProductDealDto.getTradeId());
            // 业务表
            ptlTrade.setBusiTable(DictCodeListStl.业务表_金融产品交易表.getValue());
            // 业务明细代码
            ptlTrade.setBusiId(trdProductDealDto.getFinprodId());
            // 投资目的
            ptlTrade.setInvAim(trdProductDealDto.getInvAim());
            // 证券托管户代码
            ptlTrade.setSecManageAcctId(trdProductDealDto.getSecManageAcctId());
            // 实际发生日
            ptlTrade.setSettleDate(trdProductDealDto.getTradeDate());
            // 业务类型
            ptlTrade.setBusiType(DictCodeListExt2.共通业务类型_股票投资交易.getValue());
            return ptlTrade;
        }).collect(Collectors.toList());
    }
	
	@Override
    protected List<TrdTradeFeeDetail> generateTradeFeeDetail(List<TrdProductDealDto> dealList) {
    	List<TrdTradeFeeDetail> result = new ArrayList<TrdTradeFeeDetail>();
    	if (dealList != null && dealList.size() > 0) {
    		for (TrdProductDealDto trdProductDealDto : dealList) {
    			if (!StringUtil.equals(DictCodeList.交易方向_除权登记.getValue(), trdProductDealDto.getPs())) {
	    			// 经手费
	    	    	if (trdProductDealDto != null && trdProductDealDto.getJsfAmt().compareTo(new BigDecimal(0)) > 0) {
	    	    		TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
	    	    		// 交易编号
	    	    		trdTradeFeeDetail.setTradeId(trdProductDealDto.getTradeId());
	    	    		// 费用类型
	    	    		trdTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_经手费.getValue());
	    	    		// 是否随交易支付
	    	    		trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
	    	    		// 费用金额
	    	    		trdTradeFeeDetail.setFeeAmt(trdProductDealDto.getJsfAmt());
	    	            // 登记托管机构
	    	    		trdTradeFeeDetail.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
	    	    		result.add(trdTradeFeeDetail);
	    	    	}
	        		// 过户费
	    	    	if (trdProductDealDto != null && trdProductDealDto.getGhfAmt().compareTo(new BigDecimal(0)) > 0) {
	    	    		TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
	    	    		// 交易编号
	    	    		trdTradeFeeDetail.setTradeId(trdProductDealDto.getTradeId());
	    	    		// 费用类型
	    	    		trdTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_过户费.getValue());
	    	    		// 是否随交易支付
	    	    		trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
	    	    		// 费用金额
	    	    		trdTradeFeeDetail.setFeeAmt(trdProductDealDto.getGhfAmt());
	    	            // 登记托管机构
	    	    		trdTradeFeeDetail.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
	    	    		result.add(trdTradeFeeDetail);
	    	    	}
	        		// 证管费
	    	    	if (trdProductDealDto != null && trdProductDealDto.getZgfAmt().compareTo(new BigDecimal(0)) > 0) {
	    	    		TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
	    	    		// 交易编号
	    	    		trdTradeFeeDetail.setTradeId(trdProductDealDto.getTradeId());
	    	    		// 费用类型
	    	    		trdTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_证管费.getValue());
	    	    		// 是否随交易支付
	    	    		trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
	    	    		// 费用金额
	    	    		trdTradeFeeDetail.setFeeAmt(trdProductDealDto.getZgfAmt());
	    	            // 登记托管机构
	    	    		trdTradeFeeDetail.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
	    	    		result.add(trdTradeFeeDetail);
	    	    	}
	        		// 手续费
	    	    	if (trdProductDealDto != null && trdProductDealDto.getSxfAmt().compareTo(new BigDecimal(0)) > 0) {
	    	    		TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
	    	    		// 交易编号
	    	    		trdTradeFeeDetail.setTradeId(trdProductDealDto.getTradeId());
	    	    		// 费用类型
	    	    		trdTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_交易手续费.getValue());
	    	    		// 是否随交易支付
	    	    		trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
	    	    		// 费用金额
	    	    		trdTradeFeeDetail.setFeeAmt(trdProductDealDto.getSxfAmt());
	    	            // 登记托管机构
	    	    		trdTradeFeeDetail.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
	    	    		result.add(trdTradeFeeDetail);
	    	    	}
	        		// 结算费
	    	    	if (trdProductDealDto != null && trdProductDealDto.getSettleFee().compareTo(new BigDecimal(0)) > 0) {
	    	    		TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
	    	    		// 交易编号
	    	    		trdTradeFeeDetail.setTradeId(trdProductDealDto.getTradeId());
	    	    		// 费用类型
	    	    		trdTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_结算费.getValue());
	    	    		// 是否随交易支付
	    	    		trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
	    	    		// 费用金额
	    	    		trdTradeFeeDetail.setFeeAmt(trdProductDealDto.getSettleFee());
	    	            // 登记托管机构
	    	    		trdTradeFeeDetail.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
	    	    		result.add(trdTradeFeeDetail);
	    	    	}
	        		// 佣金
	    	    	if (trdProductDealDto != null && trdProductDealDto.getCommissionAmt().compareTo(new BigDecimal(0)) > 0) {
	    	    		TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
	    	    		// 交易编号
	    	    		trdTradeFeeDetail.setTradeId(trdProductDealDto.getTradeId());
	    	    		// 费用类型
	    	    		trdTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_佣金.getValue());
	    	    		// 是否随交易支付
	    	    		if (trdProductDealDto.getFeeAmtUnpay() != null && trdProductDealDto.getFeeAmtUnpay().compareTo(new BigDecimal(0)) > 0) {
	    	    			trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_否.getValue());
	    	    		}
	    	    		else {
	    	    			trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
	    	    		}
	    	    		// 费用金额
	    	    		trdTradeFeeDetail.setFeeAmt(trdProductDealDto.getCommissionAmt());
	    	            // 登记托管机构
	    	    		trdTradeFeeDetail.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
	    	    		result.add(trdTradeFeeDetail);
	    	    	}
    			}
    		}
    	}
    	return result;
    }
	
	@Override
    protected List<StlPayOrders> generateStlPayOrdersList(List<TotalTrdProductDealDto> totalTrdProductDealDtoList, List<TrdProductDealDto> dealList, List<SettleTransDataDto> settleTransDataDtos) {
		List<StlPayOrders> result = new ArrayList<StlPayOrders>();
		if (dealList != null && dealList.size() > 0) {
    		for (TrdProductDealDto trdProductDealDto : dealList) {
    			if (StringUtil.equals(DictCodeList.交易方向_现金分红.getValue(), trdProductDealDto.getPs())) {
    				StlPayOrders stlPayOrders = new StlPayOrders();
    				// 指令编号
    	    		stlPayOrders.setOrderId(settleCommonService.buildBussinessIdBySeqno("9998"));
    	    		// 结算日期
    	    		stlPayOrders.setSettleDate(trdProductDealDto.getSettleDate());
    	    		// 本方资金账户
    	    		stlPayOrders.setMyAcctNo(trdProductDealDto.getAcctNo());
    	    		// 收付方向
    	    		stlPayOrders.setPs(DictCodeList.收付方向_收入.getValue());
    	    		// 结算金额
    	    		stlPayOrders.setSettleAmt(trdProductDealDto.getTradeAmt());
    	    		// 币种
    	    		stlPayOrders.setCcy(trdProductDealDto.getCcy());
    	    		// 是否需要发送
    	    		stlPayOrders.setIsNeedSend(DictCodeList.是否_否.getValue());
    	    		// 是否汇总交易
    	    		stlPayOrders.setIsTotalTrade(DictCodeList.是否_否.getValue());
    	    		// 交易编号
    	    		stlPayOrders.setTradeId(trdProductDealDto.getTradeId());
    	    		// 指令状态
    	    		stlPayOrders.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());
    	    		// 投资组合代码
    	    		stlPayOrders.setPortfolioId(trdProductDealDto.getPortfolioId());
    		        // 划款类型
    		        stlPayOrders.setPayType(DictCodeListExt1.平安划款类型_清算划款.getValue());
    	    		result.add(stlPayOrders);
    			}
    		}
		}
		return result;
    }
}
