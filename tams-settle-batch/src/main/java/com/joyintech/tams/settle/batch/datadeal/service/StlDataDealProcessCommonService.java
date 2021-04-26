package com.joyintech.tams.settle.batch.datadeal.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.CommissionCaclProcess;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListStl;
import com.joyintech.tams.settle.batch.datadeal.constant.StlDataDealProcessConstant;
import com.joyintech.tams.settle.batch.datadeal.dao.StlDownDataDealMapper;
import com.joyintech.tams.settle.batch.datadeal.dto.StlDownDataDealOperatorDto;
import com.joyintech.tams.settle.batch.datadeal.dto.StlDownDataDealWriteDto;
import com.joyintech.tams.settle.batch.datadeal.dto.step2.StlDownDataDealWriteBaseDto;
import com.joyintech.tams.settle.common.dto.CommisionDetail;
import com.joyintech.tams.settle.common.dto.CommissionCalcDetail;
import com.joyintech.tams.settle.common.dto.CommissionCalcDto;
import com.joyintech.tams.settle.common.dto.TradeFeatureInformation;
import com.joyintech.tams.settle.common.dto.TrdDetailDto;
import com.joyintech.tams.settle.common.service.TrdFeeCalcService;

/**
 * 数据清算step2 processor 共通Service
 * 
 * @author 陈卓
 * @date 2020/7/9
 * @see StlSzdJsmxDataDealProcessService
 * @since 1.0
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StlDataDealProcessCommonService {
	
	@Autowired
    protected StlDownDataDealMapper stlDownDataDealMapper;
	
	@Autowired
    protected TrdFeeCalcService trdFeeCalcService;

	// 币种取得
	public String getCcy(String bz) {
		if (StringUtil.equals(StlDataDealProcessConstant.SZD_JSMX_BZ_HKD, bz)) {
			return DictCodeList.币种_港元_HKD.getValue();
		}
		if (StringUtil.equals(StlDataDealProcessConstant.SZD_JSMX_BZ_USD, bz)) {
			return DictCodeList.币种_美元_USD.getValue();
		}
		return DictCodeList.币种_人民币_CNY.getValue();
	}
	
	// 交易平台取得
	public String getTradePlat(String jsfs) {
		// 普通交易-撮合交易平台
		if (StringUtil.equals(StlDataDealProcessConstant.JYFS_001, jsfs)) {
			return DictCodeList.交易平台_竞价撮合平台.getValue();
		}
		// 大宗交易
		if (StringUtil.equals(StlDataDealProcessConstant.JYFS_002, jsfs)) {
			return DictCodeList.交易平台_大宗交易平台.getValue();
		}
		// 固定收益综合电子平台交易
		if (StringUtil.equals(StlDataDealProcessConstant.JYFS_101, jsfs)) {
			return DictCodeList.交易平台_固定收益平台.getValue();
		}
		// RTGS债券交易（非担保交收）
		if (StringUtil.equals(StlDataDealProcessConstant.JYFS_106, jsfs)) {
			return DictCodeList.交易平台_固定收益平台.getValue();
		}
		// 固定收益证券综合电子平台实施非担保交收的交易
		if (StringUtil.equals(StlDataDealProcessConstant.JYFS_105, jsfs)) {
			return DictCodeList.交易平台_固定收益平台.getValue();
		}
		// 综合业务平台业务
		if (StringUtil.equals(StlDataDealProcessConstant.JYFS_104, jsfs)) {
			return DictCodeList.交易平台_综合协议交易平台.getValue();
		}
		// 普通交易(经深交所)
		if (StringUtil.equals(StlDataDealProcessConstant.SJSMX_JYFS_01, jsfs)) {
			return DictCodeList.交易平台_竞价撮合平台.getValue();
		}
		// 综合金融服务平台交易(经深交所)
		if (StringUtil.equals(StlDataDealProcessConstant.SJSMX_JYFS_02, jsfs)) {
			return DictCodeList.交易平台_大宗交易平台.getValue();
		}
		return null;
	}
	
	// 交收方式取得
	public String getSettleMode(String jsfs) {
		if (StringUtil.equals(StlDataDealProcessConstant.JSFS_001, jsfs) || StringUtil.equals(StlDataDealProcessConstant.SJSMX_JSFS_A, jsfs) || StringUtil.equals(StlDataDealProcessConstant.SJSMX_JSFS_Y, jsfs)) {
			return DictCodeListStl.交收方式_担保.getValue();
		}
		if (StringUtil.equals(StlDataDealProcessConstant.JSFS_002, jsfs) || StringUtil.equals(StlDataDealProcessConstant.SJSMX_JSFS_N, jsfs) || StringUtil.equals(StlDataDealProcessConstant.SJSMX_JSFS_R, jsfs)) {
			return DictCodeListStl.交收方式_非担保.getValue();
		}
		return null;
	}
	
	// 获取下个工作日
	public Date getNextWorkDay(Date nowday, String calendarId) {
		return stlDownDataDealMapper.getNextWorkDay(nowday, calendarId);
	}
	
	// 获取上个工作日
	public Date getBeforeWorkDay(Date nowday, String calendarId) {
		return stlDownDataDealMapper.getBeforeWorkDay(nowday, calendarId);
	}
    
    // 删除【成交数据与投管交易关系】
    public int deleteSettleBatchPara(Date dealDate, List<String> portfolioIdList, List<String> secTypeList) {
    	return stlDownDataDealMapper.deleteSettleBatchPara(dealDate, portfolioIdList, secTypeList);
    }
	
	// 佣金计算
	public StlDownDataDealWriteDto calcCommsionAmt(StlDownDataDealOperatorDto stlDownDataDealOperatorDto, StlDownDataDealWriteDto stlDownDataDealWriteDto) {
		// 小数位数
		Integer retentionBit = new Integer(2);
		// 是否分配明细
		String isAlloc = DictCodeList.是否_是.getValue();
		// 交易品种
		String tradeType = stlDownDataDealOperatorDto.getFinprodType();
		
		StlDownDataDealWriteDto result = new StlDownDataDealWriteDto();
		
		// 按照交易场所、交易平台、交易方向、期限品种、证券账户代码、组合代码、金融产品代码、分支序号、交易日期进行分组处理
		Map<String, List<StlDownDataDealWriteBaseDto>> map = new HashMap<String, List<StlDownDataDealWriteBaseDto>>();
		if (stlDownDataDealWriteDto.getStlDownDataDealWriteBaseDtoList() != null && stlDownDataDealWriteDto.getStlDownDataDealWriteBaseDtoList().size() > 0) {
			for (StlDownDataDealWriteBaseDto stlDownDataDealWriteBaseDto : stlDownDataDealWriteDto.getStlDownDataDealWriteBaseDtoList()) {
				StringBuffer buffer = new StringBuffer();
				buffer.append(stlDownDataDealWriteBaseDto.getSettleTransData().getTradeMarket() + "//");
				buffer.append(stlDownDataDealWriteBaseDto.getSettleTransData().getTradePlat() + "//");
				buffer.append(stlDownDataDealWriteBaseDto.getSettleTransData().getPs() + "//");
				buffer.append(stlDownDataDealWriteBaseDto.getSettleTransData().getTermType() + "//");
				buffer.append(stlDownDataDealWriteBaseDto.getSettleTransData().getSecAcctId() + "//");
				buffer.append(stlDownDataDealWriteBaseDto.getSettleTransData().getPortfolioId() + "//");
				buffer.append(stlDownDataDealWriteBaseDto.getSettleTransData().getFinprodId() + "//");
				buffer.append(stlDownDataDealWriteBaseDto.getSettleTransData().getBranch() + "//");
				buffer.append(stlDownDataDealWriteBaseDto.getSettleTransData().getFinprodType2() + "//");
				buffer.append(DateUtil.format(stlDownDataDealWriteBaseDto.getSettleTransData().getTradeDate(), DateUtil.DEFAULT_OTHER_FORMAT));
				if (map.containsKey(buffer.toString())) {
					map.get(buffer.toString()).add(stlDownDataDealWriteBaseDto);
				}
				else {
					List<StlDownDataDealWriteBaseDto> list = new ArrayList<StlDownDataDealWriteBaseDto>();
					list.add(stlDownDataDealWriteBaseDto);
					map.put(buffer.toString(), list);
				}
			}
		}
		
		// 定义变量记录每笔交易承担的费用信息
		Map<String, List<String>> feeMap = new HashMap<>();
		
		// 按照交易场所、交易平台、交易方向、期限品种、证券账户代码、组合代码、金融产品代码、分支序号、交易日期进行分组结果调用共通处理-交易所费用计算，并且对入参StlDownDataDealWriteDto再次处理
		Iterator<String> iterator = map.keySet().iterator();
		CommissionCalcDto commissionCalcDto = null;
		while (iterator.hasNext()) {
		    // 批次号
	        String batch = IDGenerator.generate();
	        
			StlDownDataDealWriteDto temp = new StlDownDataDealWriteDto();
			String key = iterator.next();
			List<StlDownDataDealWriteBaseDto> list = map.get(key);
			if (list != null && list.size() > 0) {
				TradeFeatureInformation tradeFeatureInformation = new TradeFeatureInformation();
				String[] keyStrArray = key.split("//");
				tradeFeatureInformation.setTradeMarket(keyStrArray[0]);
				tradeFeatureInformation.setTradePlat(keyStrArray[1]);
				tradeFeatureInformation.setPs(keyStrArray[2]);
				if (!StringUtil.equals(keyStrArray[3], "null")) {
					tradeFeatureInformation.setTermType(keyStrArray[3]);
				}
				tradeFeatureInformation.setSecAcctId(keyStrArray[4]);
				tradeFeatureInformation.setPortfolioId(keyStrArray[5]);
				tradeFeatureInformation.setFinprodId(keyStrArray[6]);
				tradeFeatureInformation.setBranch(new Long(keyStrArray[7]));
				tradeFeatureInformation.setFinprodType2(keyStrArray[8]);
				tradeFeatureInformation.setDealDate(DateUtil.parse(keyStrArray[9]));
				List<TrdDetailDto> detailDtos = new ArrayList<TrdDetailDto>();
				for (StlDownDataDealWriteBaseDto stlDownDataDealWriteBaseDto : list) {
					TrdDetailDto trdDetailDto = new TrdDetailDto();
					// 交易日期
					trdDetailDto.setTradeDate(stlDownDataDealWriteBaseDto.getSettleTransData().getTradeDate());
					// 金融产品代码
					trdDetailDto.setFinprodId(stlDownDataDealWriteBaseDto.getSettleTransData().getFinprodId());
					// 证券账户代码
					trdDetailDto.setSecManageAcctId(stlDownDataDealWriteBaseDto.getSettleTransData().getSecAcctId());
					// 交易场所
					trdDetailDto.setTradeMarket(stlDownDataDealWriteBaseDto.getSettleTransData().getTradeMarket());
					// 交易平台
					trdDetailDto.setTradePlat(stlDownDataDealWriteBaseDto.getSettleTransData().getTradePlat());
					// 交易方向
					trdDetailDto.setPs(stlDownDataDealWriteBaseDto.getSettleTransData().getPs());
					// 期限品种
					trdDetailDto.setTermType(stlDownDataDealWriteBaseDto.getSettleTransData().getTermType());
					// 全价总额
					trdDetailDto.setFpriceAmt(stlDownDataDealWriteBaseDto.getSettleTransData().getFpriceAmt());
					// 交易份额
					trdDetailDto.setShareAmt(stlDownDataDealWriteBaseDto.getSettleTransData().getShareAmt());
					// 申请编号
					trdDetailDto.setApplyTradeId(stlDownDataDealWriteBaseDto.getSettleTransData().getApplyTradeId());
					// 成交编号
					trdDetailDto.setTradeId(stlDownDataDealWriteBaseDto.getSettleTransData().getTradeId());
					// 净价总额
					trdDetailDto.setCpriceAmt(stlDownDataDealWriteBaseDto.getSettleTransData().getCpriceAmt());
					// 利息总额
					trdDetailDto.setIntAmt(stlDownDataDealWriteBaseDto.getSettleTransData().getIntAmt());
					// 交易本金
					trdDetailDto.setPrinAmt(stlDownDataDealWriteBaseDto.getSettleTransData().getPrinAmt());
					// BRANCH
					trdDetailDto.setBranch(stlDownDataDealWriteBaseDto.getSettleTransData().getBranch());
					detailDtos.add(trdDetailDto);
				}
				// 设置成交数据List
				for (StlDownDataDealWriteBaseDto stlDownDataDealWriteBaseDto : map.get(key)) {
					result.getStlDownDataDealWriteBaseDtoList().add(stlDownDataDealWriteBaseDto);
				}
				commissionCalcDto = trdFeeCalcService.calcWayCommon(detailDtos, tradeFeatureInformation, retentionBit, batch, isAlloc, tradeType);
				// 设置佣金计算过程List
				if (commissionCalcDto != null && commissionCalcDto.getCommissionCalcDetailList() != null && commissionCalcDto.getCommissionCalcDetailList().size() > 0) {
					for (CommissionCalcDetail commissionCalcDetail : commissionCalcDto.getCommissionCalcDetailList()) {
						CommissionCaclProcess commissionCaclProcess = new CommissionCaclProcess();
						// 业务编号
						commissionCaclProcess.setBusiNo(commissionCalcDetail.getBussinessId());
						// 佣金计算方式
						commissionCaclProcess.setCalcWay(commissionCalcDetail.getCommCalMethod());
						// 计算基数
						if (commissionCalcDetail.getCalcBase() != null) {
							commissionCaclProcess.setCalcBase(commissionCalcDetail.getCalcBase().toString());
						}
						// 费率
						commissionCaclProcess.setYield(commissionCalcDetail.getFeeRate());
						// 佣金包含经手费金额
						commissionCaclProcess.setIncludeJsfAmt(commissionCalcDetail.getIncludeJsfAmt());
						// 佣金包含结算费金额
						commissionCaclProcess.setIncludeSettleFee(commissionCalcDetail.getIncludeSettleFee());
						// 佣金包含证管费金额
						commissionCaclProcess.setIncludeZgfAmt(commissionCalcDetail.getIncludeZgfAmt());
						// 佣金包含过户费金额
						commissionCaclProcess.setIncludeGhfAmt(commissionCalcDetail.getIncludeGhfAmt());
						// 佣金包含印花税金额
						commissionCaclProcess.setIncludeStampFee(commissionCalcDetail.getIncludeStampFee());
						// 限额最大值
						commissionCaclProcess.setMaxQuotaAmt(commissionCalcDetail.getMaxQuotaAmt());
						// 限额最小值
						commissionCaclProcess.setMinQuotaAmt(commissionCalcDetail.getMinQuotaAmt());
						// 佣金金额
						commissionCaclProcess.setCommissionAmt(commissionCalcDetail.getCommissionAmt());
						// 批次号
						commissionCaclProcess.setBatchNumber(commissionCalcDetail.getBatch().toString());
						result.getCommissionCaclProcessList().add(commissionCaclProcess);
					}
				}
				// 根据佣金计算的返回值对StlDownDataDealWriteBaseDto的佣金及相关数据重新赋值
				if (commissionCalcDto != null && commissionCalcDto.getCommisionDetailList() != null && commissionCalcDto.getCommisionDetailList().size() > 0) {
					for (StlDownDataDealWriteBaseDto stlDownDataDealWriteBaseDto : result.getStlDownDataDealWriteBaseDtoList()) {
						for (CommisionDetail commisionDetail : commissionCalcDto.getCommisionDetailList()) {
							if (StringUtil.equals(commisionDetail.getTradeId(), stlDownDataDealWriteBaseDto.getSettleTransData().getTradeId())) {
							    stlDownDataDealWriteBaseDto.getSettleTransData().setBatchNumber(batch);
							    if (StringUtil.equals(commisionDetail.getFeeType(), DictCodeListExt1.一次性费用类型_佣金.getValue())){
							        stlDownDataDealWriteBaseDto.getSettleTransData().setCommissionAmt(commisionDetail.getFeeAmt());
	                                stlDownDataDealWriteBaseDto.getSettleTransData().setCommissionAmtRaw(commisionDetail.getFeeAmt());
	                                stlDownDataDealWriteBaseDto.getSettleTransData().setIsPayWithTrade(commisionDetail.getIsPayWithTrade());
	                                // 佣金随交易支付时，交易金额需要加上佣金和费用
	                                if (StringUtil.equals(commisionDetail.getIsPayWithTrade(), DictCodeList.是否_是.getValue())){
	                                    if (StringUtil.equals(stlDownDataDealWriteBaseDto.getSettleTransData().getPs(), DictCodeList.交易方向_买入.getValue())){
	                                        stlDownDataDealWriteBaseDto.getSettleTransData().setTradeAmt(ArithmeticUtil.doAdd(stlDownDataDealWriteBaseDto.getSettleTransData().getTradeAmt(), commisionDetail.getFeeAmt()));
	                                    }
	                                    else if (StringUtil.equals(stlDownDataDealWriteBaseDto.getSettleTransData().getPs(), DictCodeList.交易方向_卖出.getValue())){
	                                        stlDownDataDealWriteBaseDto.getSettleTransData().setTradeAmt(ArithmeticUtil.subtract(stlDownDataDealWriteBaseDto.getSettleTransData().getTradeAmt(), commisionDetail.getFeeAmt()));
	                                    }
	                                }
							    }
							    else{
							        if (feeMap.get(commisionDetail.getTradeId()) != null && feeMap.get(commisionDetail.getTradeId()).size() > 0){
							            List<String> feeList = new ArrayList<>(feeMap.get(commisionDetail.getTradeId()));
							            feeList.add(commisionDetail.getFeeType());
							            feeMap.put(commisionDetail.getTradeId(), feeList);
							        }
							        else{
							            feeMap.put(commisionDetail.getTradeId(), Arrays.asList(commisionDetail.getFeeType()));
							        }
							        if (StringUtil.equals(commisionDetail.getFeeType(), DictCodeListExt1.一次性费用类型_经手费.getValue())){
	                                    stlDownDataDealWriteBaseDto.getSettleTransData().setJsfAmt(commisionDetail.getFeeAmt());
	                                }
	                                else if (StringUtil.equals(commisionDetail.getFeeType(), DictCodeListExt1.一次性费用类型_过户费.getValue())){
	                                    stlDownDataDealWriteBaseDto.getSettleTransData().setGhfAmt(commisionDetail.getFeeAmt());
	                                }
	                                else if (StringUtil.equals(commisionDetail.getFeeType(), DictCodeListExt1.一次性费用类型_证管费.getValue())){
	                                    stlDownDataDealWriteBaseDto.getSettleTransData().setZgfAmt(commisionDetail.getFeeAmt());
	                                }
	                                else if (StringUtil.equals(commisionDetail.getFeeType(), DictCodeListExt1.一次性费用类型_交易手续费.getValue())){
	                                    stlDownDataDealWriteBaseDto.getSettleTransData().setSxfAmt(commisionDetail.getFeeAmt());
	                                }
	                                else if (StringUtil.equals(commisionDetail.getFeeType(), DictCodeListExt1.一次性费用类型_结算费.getValue())){
	                                    stlDownDataDealWriteBaseDto.getSettleTransData().setSettleFee(commisionDetail.getFeeAmt());
	                                }
                                    else if (StringUtil.equals(commisionDetail.getFeeType(), DictCodeListExt1.一次性费用类型_印花税.getValue())){
                                        stlDownDataDealWriteBaseDto.getSettleTransData().setStampFee(commisionDetail.getFeeAmt());
                                    }
							    }
							}
						}
					}
				}
			}
		}
		
		for (StlDownDataDealWriteBaseDto stlDownDataDealWriteBaseDto : result.getStlDownDataDealWriteBaseDtoList()) {
            // 表示有券商承担的费用
            if (feeMap.containsKey(stlDownDataDealWriteBaseDto.getSettleTransData().getTradeId())){
                List<String> feeTypeList = feeMap.get(stlDownDataDealWriteBaseDto.getSettleTransData().getTradeId());
                
                if (!feeTypeList.contains(DictCodeListExt1.一次性费用类型_经手费.getValue())){
                    stlDownDataDealWriteBaseDto.getSettleTransData().setJsfAmt(null);
                }
                else if (!feeTypeList.contains(DictCodeListExt1.一次性费用类型_过户费.getValue())){
                    stlDownDataDealWriteBaseDto.getSettleTransData().setGhfAmt(null);
                }
                else if (!feeTypeList.contains(DictCodeListExt1.一次性费用类型_证管费.getValue())){
                    stlDownDataDealWriteBaseDto.getSettleTransData().setZgfAmt(null);
                }
                else if (!feeTypeList.contains(DictCodeListExt1.一次性费用类型_交易手续费.getValue())){
                    stlDownDataDealWriteBaseDto.getSettleTransData().setSxfAmt(null);
                }
                else if (!feeTypeList.contains(DictCodeListExt1.一次性费用类型_结算费.getValue())){
                    stlDownDataDealWriteBaseDto.getSettleTransData().setSettleFee(null);
                }
                else if (!feeTypeList.contains(DictCodeListExt1.一次性费用类型_印花税.getValue())){
                    stlDownDataDealWriteBaseDto.getSettleTransData().setStampFee(null);
                }
            }
            // 一次性费用都是随交易支付的
            if (!StringUtil.equals(DictCodeListExt1.证券品种_回购.getValue(), tradeType)) {
	            if (StringUtil.equals(stlDownDataDealWriteBaseDto.getSettleTransData().getPs(), DictCodeList.交易方向_买入.getValue())){
	                stlDownDataDealWriteBaseDto.getSettleTransData().setTradeAmt(ArithmeticUtil.doAdd(stlDownDataDealWriteBaseDto.getSettleTransData().getTradeAmt(), 
	                    stlDownDataDealWriteBaseDto.getSettleTransData().getJsfAmt(),
	                    stlDownDataDealWriteBaseDto.getSettleTransData().getGhfAmt(), stlDownDataDealWriteBaseDto.getSettleTransData().getZgfAmt(),
	                    stlDownDataDealWriteBaseDto.getSettleTransData().getSxfAmt(), stlDownDataDealWriteBaseDto.getSettleTransData().getSettleFee(),
	                    stlDownDataDealWriteBaseDto.getSettleTransData().getStampFee()));
	            }
	            else if (StringUtil.equals(stlDownDataDealWriteBaseDto.getSettleTransData().getPs(), DictCodeList.交易方向_卖出.getValue())){
	                stlDownDataDealWriteBaseDto.getSettleTransData().setTradeAmt(ArithmeticUtil.subtract(stlDownDataDealWriteBaseDto.getSettleTransData().getTradeAmt(), 
	                    ArithmeticUtil.doAdd(stlDownDataDealWriteBaseDto.getSettleTransData().getJsfAmt(),
	                        stlDownDataDealWriteBaseDto.getSettleTransData().getGhfAmt(), stlDownDataDealWriteBaseDto.getSettleTransData().getZgfAmt(),
	                        stlDownDataDealWriteBaseDto.getSettleTransData().getSxfAmt(), stlDownDataDealWriteBaseDto.getSettleTransData().getSettleFee(),
	                        stlDownDataDealWriteBaseDto.getSettleTransData().getStampFee())));
	            }
            }
            else {
            	if (StringUtil.equals(stlDownDataDealWriteBaseDto.getSettleTransData().getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())){
            		stlDownDataDealWriteBaseDto.getSettleTransData().setTradeAmt(ArithmeticUtil.doAdd(stlDownDataDealWriteBaseDto.getSettleTransData().getTradeAmt(), 
    	                    stlDownDataDealWriteBaseDto.getSettleTransData().getJsfAmt(),
    	                    stlDownDataDealWriteBaseDto.getSettleTransData().getGhfAmt(), stlDownDataDealWriteBaseDto.getSettleTransData().getZgfAmt(),
    	                    stlDownDataDealWriteBaseDto.getSettleTransData().getSxfAmt(), stlDownDataDealWriteBaseDto.getSettleTransData().getSettleFee(),
    	                    stlDownDataDealWriteBaseDto.getSettleTransData().getStampFee()));
            	}
            	else {
            		stlDownDataDealWriteBaseDto.getSettleTransData().setTradeAmt(ArithmeticUtil.subtract(stlDownDataDealWriteBaseDto.getSettleTransData().getTradeAmt(), 
    	                    ArithmeticUtil.doAdd(stlDownDataDealWriteBaseDto.getSettleTransData().getJsfAmt(),
    	                        stlDownDataDealWriteBaseDto.getSettleTransData().getGhfAmt(), stlDownDataDealWriteBaseDto.getSettleTransData().getZgfAmt(),
    	                        stlDownDataDealWriteBaseDto.getSettleTransData().getSxfAmt(), stlDownDataDealWriteBaseDto.getSettleTransData().getSettleFee(),
    	                        stlDownDataDealWriteBaseDto.getSettleTransData().getStampFee())));
            	}
            }
        }
		
		return result;
	}
	
	// 获取组合的收益类型
	public String getPortfolioProfitType(String portfolioId) {
		List<String> result = stlDownDataDealMapper.getPortfolioProfitType(portfolioId);
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}
}
