package com.joyintech.tams.settle.batch.datagather.processor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.h2.table.Plan;
import org.springframework.beans.BeanUtils;
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

	private static final String DEAL_REMARK = "数据清算自动处理";

	@Autowired
	private SettleCommonService settleCommonService;

	@Override
	public InvestTradeWriteDto process(NeedOperateEntity operateEntity) throws Exception {
		//接收参数
		InvestTradeReaderDto investTradeReaderDto = (InvestTradeReaderDto)operateEntity;
		//返回值
		InvestTradeWriteDto investTradeWriteDto = new InvestTradeWriteDto();

		List<SettleDelDealDetailDto> list = investTradeReaderDto.getSettleTransDataDelDtoList();
		if (CollectionUtils.isNotEmpty(list)){
			Map<String, List<SettleDelDealDetailDto>> collects = list.stream().collect(Collectors.groupingBy(x -> {
				return x.getFinprodId() +","+ x.getSecAcctId();
			}));
			Iterator<String> iterator = collects.keySet().iterator();
			while (iterator.hasNext()) {
				String tempStr = iterator.next();

				//保证批可重复执行 （先删除）
				String[] para = tempStr.split(",");
				Map<String ,Object> map = new HashMap<>();
				map.put("finprodId",para[0]);
				map.put("secAcctId",para[1]);
				map.put("dealDate", getBatchExecuteInfo().getExecuteDate());
				map.put("busiType",DictCodeListExt2.共通业务类型_股票分红交易.getValue());
				List<String> totleTradeIdList = stlDataGatherMapper.selectCqTotleTradeId(map);
				if(CollectionUtils.isNotEmpty(totleTradeIdList)){
					for (String totleTradeId:totleTradeIdList){
						List<String> tradeIdList = stlDataGatherMapper.selectCqtradeId(totleTradeId);
						if(CollectionUtils.isNotEmpty(tradeIdList)){
							for (String tradeId : tradeIdList){
								stlDataGatherMapper.deleteCqTrdProductDealAdd(tradeId);
								stlDataGatherMapper.deleteCqPaTrdProductDeal(tradeId);
								stlDataGatherMapper.deleteCqPtlTrade(tradeId);
							}
						}
						stlDataGatherMapper.deleteCqTrdFinProductDealTotal(totleTradeId);
						stlDataGatherMapper.deleteCqTrdProductDeal(totleTradeId);
						stlDataGatherMapper.deleteCqPaTrdFinProductDealTotal(totleTradeId);
						stlDataGatherMapper.deleteCqTrdDealTotalAdd(totleTradeId);
					}
				}

				List<SettleDelDealDetailDto> settleTransDataDelDtoList = collects.get(tempStr);
				if (settleTransDataDelDtoList != null && settleTransDataDelDtoList.size() > 0) {
					// 金融产品交易汇总信息
					TotalTrdProductDealDto totalTrdProductDealDto = setCqTotalTrdProductDealDto(settleTransDataDelDtoList);
					investTradeWriteDto.getFinProductDealTotalList().add(totalTrdProductDealDto);

					for (SettleDelDealDetailDto settleDelDealDetailDto : settleTransDataDelDtoList) {
						// 金融产品交易明细
						TrdProductDealDto dealDto = setCqTrdProductDealDto(totalTrdProductDealDto, settleDelDealDetailDto);
						investTradeWriteDto.getProductDealList().add(dealDto);

						// 成交编号业务编号关联表（只针对除权登记数据）
						SettleTransTgMapping settleTransTgMapping = new SettleTransTgMapping();
						settleTransTgMapping.setTradeId(IDGenerator.generate(20));
						settleTransTgMapping.setTradeIdTg(dealDto.getTradeId());
						settleTransTgMapping.setTgDataStatus(DictCodeListStl.投管交易数据状态_处理结束.getValue());
						investTradeWriteDto.getSettleTransTgMappingList().add(settleTransTgMapping);

						// 组合交易
						investTradeWriteDto.getPtlTradeList().add(setPtlTrade(dealDto));
					}
				}
			}
		}

		//第二步 ： 生成现金分红、送转和申购确认的交易
		List<SettleTransDataDto> dataList = investTradeReaderDto.getSettleTransDataList();
		if(CollectionUtils.isNotEmpty(dataList)){
			Map<String, List<SettleTransDataDto>> collect = dataList.stream().collect(Collectors.groupingBy(x -> {
				return x.getFinprodId() + "," + x.getSecAcctId();
			}));
			Iterator<String> iterator = collect.keySet().iterator();
			while (iterator.hasNext()){
				String next = iterator.next();
				List<SettleTransDataDto> settleTransDataDtos = collect.get(next);
				//现金分红 和 深交所送转的交易（要先登一笔除权登记，再登交易）
				if(StringUtil.equals(settleTransDataDtos.get(0).getPs(),DictCodeList.交易方向_现金分红.getValue())||
						StringUtil.equals(settleTransDataDtos.get(0).getPs(),DictCodeList.交易方向_红利再投.getValue())
						&&StringUtil.equals(settleTransDataDtos.get(0).getTradeMarket(),DictCodeListExt1.交易场所_深圳证券交易所.getValue())){
					TotalTrdProductDealDto totalCqTrdProductDealDto = setTotalTrdProductDealDto(settleTransDataDtos,true);
					investTradeWriteDto.getFinProductDealTotalList().add(totalCqTrdProductDealDto);
					TotalTrdProductDealDto totalTrdProductDealDto = setTotalTrdProductDealDto(settleTransDataDtos,false);
					//前置交易编号
					totalTrdProductDealDto.setbTradeId(totalCqTrdProductDealDto.getTotleTradeId());
					investTradeWriteDto.getFinProductDealTotalList().add(totalTrdProductDealDto);
					for (SettleTransDataDto settleTransDataDto : settleTransDataDtos) {
						TrdProductDealDto dealCqDto = setTrdProductDealDto(totalCqTrdProductDealDto, settleTransDataDto,true);
						investTradeWriteDto.getProductDealList().add(dealCqDto);
						TrdProductDealDto dealDto = setTrdProductDealDto(totalTrdProductDealDto, settleTransDataDto,false);
						dealDto.setbTradeId(dealCqDto.getTradeId());
						investTradeWriteDto.getProductDealList().add(dealDto);
						SettleTransTgMapping settleTransTgMapping = new SettleTransTgMapping();
						settleTransTgMapping.setTradeId(IDGenerator.generate(20));
						settleTransTgMapping.setTradeIdTg(dealCqDto.getTradeId());
						settleTransTgMapping.setTgDataStatus(DictCodeListStl.投管交易数据状态_处理结束.getValue());
						investTradeWriteDto.getSettleTransTgMappingList().add(settleTransTgMapping);
						PtlTrade ptlCqTrade = setPtlTrade(dealCqDto);
						ptlCqTrade.setSettleDate(dealCqDto.getSettleDate());
						investTradeWriteDto.getPtlTradeList().add(ptlCqTrade);
						PtlTrade ptlTrade = setPtlTrade(dealDto);
						ptlTrade.setSettleDate(dealDto.getSettleDate());
						investTradeWriteDto.getPtlTradeList().add(ptlTrade);

						if(StringUtil.equals(settleTransDataDto.getPs(), DictCodeList.交易方向_现金分红.getValue())) {
							// 划款指令明细
							StlPayOrders stlPayOrders = generateStlPayOrders(dealDto);
							investTradeWriteDto.getStlPayOrdersList().add(stlPayOrders);
						}
					}
				}else if(StringUtil.equals(settleTransDataDtos.get(0).getPs(),DictCodeList.交易方向_红利再投.getValue())
						&&StringUtil.equals(settleTransDataDtos.get(0).getTradeMarket(),DictCodeListExt1.交易场所_上海证券交易所.getValue())){
					String bTradeId = stlDataGatherMapper.selectCqBtradeId(settleTransDataDtos.get(0).getSecAcctId(),settleTransDataDtos.get(0).getFinprodId(),getBatchExecuteInfo().getExecuteDate());
					TotalTrdProductDealDto totalTrdProductDealDto = setTotalTrdProductDealDto(settleTransDataDtos,false);
					//前置交易编号
					totalTrdProductDealDto.setbTradeId(bTradeId);
					investTradeWriteDto.getFinProductDealTotalList().add(totalTrdProductDealDto);
					for (SettleTransDataDto settleTransDataDto : settleTransDataDtos) {
						TrdProductDealDto dealDto = setTrdProductDealDto(totalTrdProductDealDto, settleTransDataDto,false);
						investTradeWriteDto.getProductDealList().add(dealDto);
						PtlTrade ptlTrade = setPtlTrade(dealDto);
						ptlTrade.setSettleDate(dealDto.getSettleDate());
						investTradeWriteDto.getPtlTradeList().add(ptlTrade);
					}
				}
			}
		}

		//第三步 ： 生成申购确认的交易
		List<SettleTransDataDto> dataSqqrList = investTradeReaderDto.getSettleTransDataSgqrList();
		if(CollectionUtils.isNotEmpty(dataSqqrList)){
			Map<String, List<SettleTransDataDto>> collect = dataSqqrList.stream().collect(Collectors.groupingBy(x -> {
				return x.getSecAcctId() + x.getFinprodId() + x.getSeatNumber() + x.getPs();
			}));
			Iterator<String> iterator = collect.keySet().iterator();
			while (iterator.hasNext()){
				String next = iterator.next();
				List<SettleTransDataDto> settleTransDataDtos = collect.get(next);
				settleTransDataDtos.sort((a,b)->{
					return a.getShareAmtSg().compareTo(b.getShareAmtSg());
				});
				BigDecimal shareAmtSum = settleTransDataDtos.stream().map(SettleTransDataDto::getShareAmtSg).reduce(BigDecimal.ZERO,BigDecimal::add);
				List<BigDecimal> l = new ArrayList<>();
				for (int i = 0; i < 5; i++) {
					l.add(new BigDecimal(0));
				}
				for (int i = 0; i < settleTransDataDtos.size(); i++) {
					SettleTransDataDto settleTransDataDto = settleTransDataDtos.get(i);
					TrdProductDealDto dealDto;
					if(i==settleTransDataDtos.size()-1){
						dealDto = setTrdProductDealSgDto(settleTransDataDto,l,shareAmtSum,true);
					}else {
						dealDto = setTrdProductDealSgDto(settleTransDataDto,l,shareAmtSum,false);
					}

					// GZYHTZJY-1087:股票申购确认需登录SettleTransTgMapping modify by fuyongsheng 20220117 start
					SettleTransTgMapping settleTransTgMapping = new SettleTransTgMapping();
					settleTransTgMapping.setTradeId(IDGenerator.generate(20));
					settleTransTgMapping.setTradeIdTg(dealDto.getTradeId());
					settleTransTgMapping.setDealDate(dealDto.getTradeDate());
					settleTransTgMapping.setTgDataStatus(DictCodeListStl.投管交易数据状态_处理结束.getValue());
					investTradeWriteDto.getSettleTransTgMappingList().add(settleTransTgMapping);
					// GZYHTZJY-1087:股票申购确认需登录SettleTransTgMapping modify by fuyongsheng 20220117 end

					PtlTrade ptlTrade = setPtlTrade(dealDto);
					ptlTrade.setSettleDate(dealDto.getSettleDate());
					investTradeWriteDto.getPtlTradeList().add(ptlTrade);
					investTradeWriteDto.getProductDealList().add(dealDto);
				}
			}
		}
		// GZYHTZJY-1050:赋值sectype modify fuyongsheng 20220106 start
		investTradeWriteDto.setSecType(getSecType());
		// GZYHTZJY-1050:赋值sectype modify fuyongsheng 20220106 end
		return investTradeWriteDto;
	}

	@Override
	protected String getSecType() {
		return DictCodeListExt1.证券品种_股票.getValue();
	}

	@Override
	protected List<String> getFinprodType() {return Arrays.asList(DictCodeListExt1.金融产品类型_投管用_股票.getValue());}

	/**
	 * 除权交易（送转上交所） 金融产品汇总交易DTO
	 *
	 * @param settleTransDataDelDtos 除权交易
	 * @return 金融产品汇总交易DTO
	 */
	private TotalTrdProductDealDto setCqTotalTrdProductDealDto(List<SettleDelDealDetailDto> settleTransDataDelDtos) {
		//返回值
		TotalTrdProductDealDto dealTotal = new TotalTrdProductDealDto();

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
		// 业务类型
		dealTotal.setBusiType(DictCodeListExt2.共通业务类型_股票分红交易.getValue());
		// 金融产品代码
		dealTotal.setFinprodId(settleTransDataDelDto.getFinprodId());
		// 金融产品类型（估值核算）
		dealTotal.setFinprodType(settleTransDataDelDto.getFinprodType2());
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
		// 单位利息
		dealTotal.setUnitInt(settleTransDataDelDto.getPreDevidengAmt());
		// 交易本金
		dealTotal.setPrinAmt(registShareAll);
		// 利息总额
		dealTotal.setIntAmt(tradeAmtAll);
		//全价总额
		dealTotal.setFpriceAmt(tradeAmtAll);
		// 交易方向
		dealTotal.setPs(DictCodeList.交易方向_除权登记.getValue());
		// 交易状态
		dealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
		// 是否已撤销
		dealTotal.setIsCancel(DictCodeList.是否_否.getValue());
		// 证券管理户代码
		dealTotal.setSecManageAcctId(settleTransDataDelDto.getSecAcctId());
		//交易场所
		dealTotal.setTradeMarket(settleTransDataDelDto.getTradeMarket());
		// 权益登记日
		dealTotal.setRegDate(settleTransDataDelDto.getRegDate());
		// 备注
		dealTotal.setRemark(DEAL_REMARK);
		//预计分红日
		dealTotal.setBonusCfmDate(settleTransDataDelDto.getDevidentDate());
		 //分红类型
		dealTotal.setPayType(DictCodeList.股票分红类型_送转.getValue());

		return dealTotal;
	}

	/**
	 * 除权交易 金融产品交易明细设置
	 * @param totalTrdProductDealDto 金融产品汇总交易
	 * @param settleDelDealDetailDto 交易明细列表
	 */
	private TrdProductDealDto setCqTrdProductDealDto(TotalTrdProductDealDto totalTrdProductDealDto,SettleDelDealDetailDto settleDelDealDetailDto) {
		//返回值
		TrdProductDealDto trdProductDealDto = new TrdProductDealDto();
		// 交易编号，按现有的交易编号生成规则，自动生成
		trdProductDealDto.setTradeId(settleCommonService.buildBussinessCodeExt(DictCodeList.共通业务类型_资产存续期交易));
		// 金融产品代码
		trdProductDealDto.setFinprodId(settleDelDealDetailDto.getFinprodId());
		// 金融产品类型（估值核算）
		trdProductDealDto.setFinprodType(settleDelDealDetailDto.getFinprodType2());
		// 金融产品类型（投管分类）
		trdProductDealDto.setFinprodType2(settleDelDealDetailDto.getFinprodType2());
		// 分支序号
		trdProductDealDto.setBranch(0L);
		// 交易日
		trdProductDealDto.setTradeDate(settleDelDealDetailDto.getDelDate());
		// 交割日
		trdProductDealDto.setSettleDate(settleDelDealDetailDto.getDelDate());
		//交易币种
		trdProductDealDto.setCcy(settleDelDealDetailDto.getCcy());
		// 单位利息
		trdProductDealDto.setUnitInt(settleDelDealDetailDto.getPreDevidengAmt());
		//交易份额
		trdProductDealDto.setShareAmt(settleDelDealDetailDto.getRedDateAmt());
		// 全价总额
		trdProductDealDto.setFpriceAmt(settleDelDealDetailDto.getBonusAmt());
		// 交易方向
		trdProductDealDto.setPs(DictCodeList.交易方向_除权登记.getValue());
		// 交易状态
		trdProductDealDto.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
		// 是否已撤销
		trdProductDealDto.setIsCancel(DictCodeList.是否_否.getValue());
		// 汇总交易编号
		trdProductDealDto.setTotleTradeId(totalTrdProductDealDto.getTotleTradeId());
		// 是否外部交易
		trdProductDealDto.setIsOutTrade(DictCodeList.是否_否.getValue());
		// 交易场所
		trdProductDealDto.setTradeMarket(settleDelDealDetailDto.getTradeMarket());
		// 证券管理户代码
		trdProductDealDto.setSecManageAcctId(settleDelDealDetailDto.getSecAcctId());
		//备注
		trdProductDealDto.setRemark(DEAL_REMARK);

		// 投资组合代码
		trdProductDealDto.setPortfolioId(settleDelDealDetailDto.getPortfolioId());
		// 权益登记日
		trdProductDealDto.setRegDate(settleDelDealDetailDto.getRegDate());
		//预计分红日
		trdProductDealDto.setBonusCfmDate(settleDelDealDetailDto.getDevidentDate());
		//分红类型
		trdProductDealDto.setPayType(DictCodeList.股票分红类型_送转.getValue());

		return trdProductDealDto;
	}

	/**
	 * 生成投资组合交易
	 *
	 * @param deal            交易明细
	 * @return 投资组合交易
	 */
	protected PtlTrade setPtlTrade(TrdProductDealDto deal) {
		PtlTrade ptlTrade = new PtlTrade();
		// 组合代码
		ptlTrade.setPortfolioId(deal.getPortfolioId());
		// 业务编号
		ptlTrade.setBusiNo(deal.getTradeId());
		// 业务表
		ptlTrade.setBusiTable(DictCodeListStl.业务表_金融产品交易表.getValue());
		// 业务明细代码
		ptlTrade.setBusiId(deal.getFinprodId());
		//业务类型
		ptlTrade.setBusiType("TRD59");
		// 证券托管户代码
		ptlTrade.setSecManageAcctId(deal.getSecManageAcctId());
		// 实际发生日
		ptlTrade.setSettleDate(deal.getTradeDate());
		return ptlTrade;
	}

	/**
	 * 除权交易和交易 金融产品汇总交易DTO
	 *
	 * @param isCq 是否除权交易
	 * @return 金融产品汇总交易DTO
	 */
	private TotalTrdProductDealDto setTotalTrdProductDealDto(List<SettleTransDataDto> settleTransDataDtos,boolean isCq) {
		//返回值
		TotalTrdProductDealDto dealTotal = new TotalTrdProductDealDto();

		SettleTransDataDto settleTransDataDto = settleTransDataDtos.get(0);

		// 总持仓数量
		BigDecimal amountAll = BigDecimal.ZERO;
		for (SettleTransDataDto dto : settleTransDataDtos) {
			amountAll = ArithmeticUtil.doAdd(amountAll, dto.getAmount());
		}
		dealTotal.setAmount(amountAll);

		//  汇总交易编号
		dealTotal.setTotleTradeId(settleCommonService.buildBussinessCodeExt(DictCodeList.共通业务类型_资产存续期交易));
		// 业务类型
		dealTotal.setBusiType(DictCodeListExt2.共通业务类型_股票分红交易.getValue());
		// 金融产品代码
		dealTotal.setFinprodId(settleTransDataDto.getFinprodId());
		// 金融产品类型（估值核算）
		dealTotal.setFinprodType(settleTransDataDto.getFinprodType2());
		// 金融产品类型（投管分类）
		dealTotal.setFinprodType2(settleTransDataDto.getFinprodType2());
		// 分支序号
		dealTotal.setBranch(0L);
		// 交易日
		dealTotal.setTradeDate(settleTransDataDto.getSettleDate());
		// 交割日
		dealTotal.setSettleDate(settleTransDataDto.getSettleDate());
		// 交易币种
		dealTotal.setCcy(settleTransDataDto.getCcy());
		if(StringUtil.equals(settleTransDataDto.getPs(),DictCodeList.交易方向_现金分红.getValue())){
			//单位净价
			dealTotal.setUnitCprice(settleTransDataDto.getUnitCprice());
			//分红类型
			dealTotal.setPayType(DictCodeList.股票分红类型_现金分红.getValue());
		}else {
			dealTotal.setUnitCprice(settleTransDataDto.getShareAmt().divide(amountAll));
			//分红类型
			dealTotal.setPayType(DictCodeList.股票分红类型_送转.getValue());
		}
		// 单位利息
		dealTotal.setUnitInt(settleTransDataDto.getUnitInt());
		//交易份额
		dealTotal.setShareAmt(amountAll);
		//全价总额
		dealTotal.setFpriceAmt(settleTransDataDto.getFpriceAmt());
		if(isCq){
			// 交易方向
			dealTotal.setPs(DictCodeList.交易方向_除权登记.getValue());
		}else {
			// 交易方向
			dealTotal.setPs(settleTransDataDto.getPs());
			if(StringUtil.equals(settleTransDataDto.getPs(),DictCodeList.交易方向_现金分红.getValue())){
				//交易金额
				dealTotal.setTradeAmt(settleTransDataDto.getTradeAmt());
			}
		}

		// 交易状态
		dealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
		// 是否已撤销
		dealTotal.setIsCancel(DictCodeList.是否_否.getValue());
		// 证券管理户代码
		dealTotal.setSecManageAcctId(settleTransDataDto.getSecAcctId());
		//交易场所
		dealTotal.setTradeMarket(settleTransDataDto.getTradeMarket());
		// 权益登记日
		dealTotal.setRegDate(settleTransDataDto.getTradeDate());
		// 备注
		dealTotal.setRemark(DEAL_REMARK);
		//预计分红日
		dealTotal.setBonusCfmDate(settleTransDataDto.getSettleDate());

		return dealTotal;
	}

	/**
	 * 除权交易和交易（现金分红，送转深交所） 金融产品交易明细设置
	 * @param totalTrdProductDealDto 金融产品交易明细
	 * @param settleTransDataDto 除权交易明细列表
	 */
	private TrdProductDealDto setTrdProductDealDto(TotalTrdProductDealDto totalTrdProductDealDto,SettleTransDataDto settleTransDataDto,boolean isCq) {
		//返回值
		TrdProductDealDto trdProductDealDto = new TrdProductDealDto();
		// 交易编号，按现有的交易编号生成规则，自动生成
		trdProductDealDto.setTradeId(settleCommonService.buildBussinessCodeExt(DictCodeList.共通业务类型_资产存续期交易));
		// 金融产品代码
		trdProductDealDto.setFinprodId(settleTransDataDto.getFinprodId());
		// 金融产品类型（估值核算）
		trdProductDealDto.setFinprodType(settleTransDataDto.getFinprodType2());
		// 金融产品类型（投管分类）
		trdProductDealDto.setFinprodType2(settleTransDataDto.getFinprodType2());
		// 分支序号
		trdProductDealDto.setBranch(0L);
		// 交易日
		trdProductDealDto.setTradeDate(settleTransDataDto.getSettleDate());
		// 交割日
		trdProductDealDto.setSettleDate(settleTransDataDto.getSettleDate());
		//交易币种
		trdProductDealDto.setCcy(settleTransDataDto.getCcy());
		//单位净价
		trdProductDealDto.setUnitCprice(totalTrdProductDealDto.getUnitCprice());
		// 单位利息
		trdProductDealDto.setUnitInt(totalTrdProductDealDto.getUnitInt());
		//交易份额
		trdProductDealDto.setShareAmt(settleTransDataDto.getAmount());
		// 全价总额
		trdProductDealDto.setFpriceAmt(settleTransDataDto.getFpriceAmt().multiply(settleTransDataDto.getAmount().divide(totalTrdProductDealDto.getAmount())));
		if(isCq) {
			// 交易方向
			trdProductDealDto.setPs(DictCodeList.交易方向_除权登记.getValue());
		}else {
			// 交易方向
			trdProductDealDto.setPs(settleTransDataDto.getPs());
			if(StringUtil.equals(settleTransDataDto.getPs(),DictCodeList.交易方向_现金分红.getValue())){
				trdProductDealDto.setTradeAmt(settleTransDataDto.getTradeAmt().multiply(settleTransDataDto.getAmount().divide(totalTrdProductDealDto.getAmount())));
			}
		}
		// 交易状态
		trdProductDealDto.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
		// 是否已撤销
		trdProductDealDto.setIsCancel(DictCodeList.是否_否.getValue());
		// 汇总交易编号
		trdProductDealDto.setTotleTradeId(totalTrdProductDealDto.getTotleTradeId());
		// 是否外部交易
		trdProductDealDto.setIsOutTrade(DictCodeList.是否_否.getValue());
		// 交易场所
		trdProductDealDto.setTradeMarket(settleTransDataDto.getTradeMarket());
		// 证券管理户代码
		trdProductDealDto.setSecManageAcctId(settleTransDataDto.getSecAcctId());
		//备注
		trdProductDealDto.setRemark(DEAL_REMARK);

		// 投资组合代码
		trdProductDealDto.setPortfolioId(settleTransDataDto.getPortfolioId());
		// 权益登记日
		trdProductDealDto.setRegDate(settleTransDataDto.getTradeDate());
		//预计分红日
		trdProductDealDto.setBonusCfmDate(settleTransDataDto.getSettleDate());
		//权益登记日份额
		trdProductDealDto.setRegDateAmt(settleTransDataDto.getAmount());

		return trdProductDealDto;
	}

	/**
	 * 生成划款明细
	 * @param deal
	 * @return
	 */
	protected StlPayOrders generateStlPayOrders(TrdProductDealDto deal) {
		StlPayOrders stlPayOrders = new StlPayOrders();
		// 指令编号
		stlPayOrders.setOrderId(settleCommonService.buildBussinessIdBySeqno("9998"));
		// 划款类型
		stlPayOrders.setPayType(DictCodeListExt1.平安划款类型_托管划款.getValue());
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
		stlPayOrders.setIsTotalTrade(DictCodeList.是否_是.getValue());
		// 交易编号
		stlPayOrders.setTradeId(deal.getTradeId());
		// 指令状态
		stlPayOrders.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());
		// 投资组合代码
		stlPayOrders.setPortfolioId(deal.getPortfolioId());
		stlPayOrders.setRemark(DEAL_REMARK);

		return stlPayOrders;
	}

	/**
	 * 申购确认交易 金融产品交易明细设置
	 * @param settleTransDataDto 金融产品交易明细
	 * @param list 除权交易明细列表
	 */
	private TrdProductDealDto setTrdProductDealSgDto(SettleTransDataDto settleTransDataDto,List<BigDecimal> list,BigDecimal shareAmtSum,boolean last) {
		//返回值
		TrdProductDealDto trdProductDealDto = new TrdProductDealDto();
		// 交易编号，按现有的交易编号生成规则，自动生成
		trdProductDealDto.setTradeId(settleCommonService.buildBussinessCodeExt(DictCodeList.共通业务类型_资产存续期交易));
		// 金融产品代码
		trdProductDealDto.setFinprodId(settleTransDataDto.getFinprodId());
		// 金融产品类型（估值核算）
		trdProductDealDto.setFinprodType(settleTransDataDto.getFinprodType2());
		// 金融产品类型（投管分类）
		trdProductDealDto.setFinprodType2(settleTransDataDto.getFinprodType2());
		// 分支序号
		trdProductDealDto.setBranch(0L);
		// 交易日
		trdProductDealDto.setTradeDate(settleTransDataDto.getTradeDate());
		// 交割日
		trdProductDealDto.setSettleDate(settleTransDataDto.getSettleDate());
		//交易币种
		trdProductDealDto.setCcy(settleTransDataDto.getCcy());
		//单位净价
		trdProductDealDto.setUnitCprice(settleTransDataDto.getUnitCprice());
		// 单位利息
		trdProductDealDto.setUnitInt(settleTransDataDto.getUnitInt());
		//单位全价
		trdProductDealDto.setUnitFprice(settleTransDataDto.getUnitFprice());
		//单位面值
		trdProductDealDto.setParValue(settleTransDataDto.getParValue());
		if(last){
			//交易份额
			trdProductDealDto.setShareAmt(null == settleTransDataDto.getShareAmt() ? BigDecimal.ZERO : settleTransDataDto.getShareAmt().subtract(list.get(0)));
			//净价总额
			trdProductDealDto.setCpriceAmt(null == settleTransDataDto.getCpriceAmt() ? BigDecimal.ZERO : settleTransDataDto.getCpriceAmt().subtract(list.get(1)));
			//交易本金
			trdProductDealDto.setPrinAmt(null == settleTransDataDto.getPrinAmt() ? BigDecimal.ZERO : settleTransDataDto.getPrinAmt().subtract(list.get(2)));
			// 全价总额
			trdProductDealDto.setFpriceAmt(null == settleTransDataDto.getFpriceAmt() ? BigDecimal.ZERO : settleTransDataDto.getFpriceAmt().subtract(list.get(3)));
			//交易金额
			trdProductDealDto.setTradeAmt(null == settleTransDataDto.getTradeAmt() ? BigDecimal.ZERO : settleTransDataDto.getTradeAmt().subtract(list.get(4)));
		}else {
			//交易份额
			trdProductDealDto.setShareAmt(null == settleTransDataDto.getShareAmt() ? BigDecimal.ZERO : settleTransDataDto.getShareAmt().multiply(settleTransDataDto.getShareAmtSg()).divide(shareAmtSum, 4, RoundingMode.HALF_UP));
			list.set(0,list.get(0).add(trdProductDealDto.getShareAmt()));
			//净价总额
			trdProductDealDto.setCpriceAmt(null == settleTransDataDto.getCpriceAmt() ? BigDecimal.ZERO : settleTransDataDto.getCpriceAmt().multiply(settleTransDataDto.getShareAmtSg()).divide(shareAmtSum, 4, RoundingMode.HALF_UP));
			list.set(1, list.get(1).add(trdProductDealDto.getCpriceAmt()));
			//交易本金
			trdProductDealDto.setPrinAmt(null == settleTransDataDto.getPrinAmt() ? BigDecimal.ZERO : settleTransDataDto.getPrinAmt().multiply(settleTransDataDto.getShareAmtSg()).divide(shareAmtSum, 4, RoundingMode.HALF_UP));
			list.set(2, list.get(2).add(trdProductDealDto.getPrinAmt()));
			// 全价总额
			trdProductDealDto.setFpriceAmt(null == settleTransDataDto.getFpriceAmt() ? BigDecimal.ZERO : settleTransDataDto.getFpriceAmt().multiply(settleTransDataDto.getShareAmtSg()).divide(shareAmtSum, 4, RoundingMode.HALF_UP));
			list.set(3, list.get(3).add(trdProductDealDto.getFpriceAmt()));
			//交易金额
			trdProductDealDto.setTradeAmt(null == settleTransDataDto.getTradeAmt() ? BigDecimal.ZERO : settleTransDataDto.getTradeAmt().multiply(settleTransDataDto.getShareAmtSg()).divide(shareAmtSum, 4, RoundingMode.HALF_UP));
			list.set(4, list.get(4).add(trdProductDealDto.getTradeAmt()));
		}
		// 交易方向
		trdProductDealDto.setPs(settleTransDataDto.getPs());
		//投资目的
		trdProductDealDto.setInvAim(settleTransDataDto.getInvAim());
		//结算方式
		trdProductDealDto.setSettMode("03");
		// 交易状态
		trdProductDealDto.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
		// 是否已撤销
		trdProductDealDto.setIsCancel(DictCodeList.是否_否.getValue());
		//是否结清
		trdProductDealDto.setIsClean("Y");
		//前置交易编号
		trdProductDealDto.setbTradeId(settleTransDataDto.getbTradeId());
		// 是否外部交易
		trdProductDealDto.setIsOutTrade(DictCodeList.是否_是.getValue());
		// 交易场所
		trdProductDealDto.setTradeMarket(settleTransDataDto.getTradeMarket());
		//交易平台
		trdProductDealDto.setTradePlat(settleTransDataDto.getTradePlat());
		//交易市场
		trdProductDealDto.setTradeMarketMode(settleTransDataDto.getTradeMarketMode());
		//关联系统交易编号
		trdProductDealDto.setfTradeId(settleTransDataDto.getSeqNo());
		//登记托管机构
		trdProductDealDto.setRegistOrg(DictCodeList.登记托管机构_中债_中国证券登记结算有限责任公司.getValue());
		// 证券管理户代码
		trdProductDealDto.setSecManageAcctId(settleTransDataDto.getSecAcctId());
		//备注
		trdProductDealDto.setRemark(DEAL_REMARK);
		//交收方式
		trdProductDealDto.setSettlemenType(settleTransDataDto.getSettleMode());
		//席位号
		trdProductDealDto.setSeatId(settleTransDataDto.getSeatNumber());
		//委托代码
		trdProductDealDto.setEntrustId(settleTransDataDto.getEntrustId());
		// 投资组合代码
		trdProductDealDto.setPortfolioId(settleTransDataDto.getPortfolioId());

		return trdProductDealDto;
	}

}
