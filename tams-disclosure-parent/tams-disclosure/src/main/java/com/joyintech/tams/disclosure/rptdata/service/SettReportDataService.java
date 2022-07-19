package com.joyintech.tams.disclosure.rptdata.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RepProdInfoMapper;
import com.joyintech.tams.common.base.entity.BokBalance;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.disclosure.common.CollectionUtils;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;
import com.joyintech.tams.disclosure.rptdata.dao.SettReportMapper;
import com.joyintech.tams.disclosure.rptdata.dto.SettReportDto;

@Service(value = "settReportDataService")
public class SettReportDataService implements IReportDataService {

	@Autowired
	private RepProdInfoMapper repProdInfoMapper;

	@Autowired
	private SettReportMapper settReportMapper;	

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(SettReportDataService.class);

	@Override
	public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {

		List<Map<String, Object>> listResult = new ArrayList<>();

		SettReportDto settReportDto = new SettReportDto();
		try {
			String prodId = (paraMap.get("portnum") == null ? null : paraMap.get("portnum").toString());
			Date genDate = (paraMap.get("endDate") == null ? new Date()
					: DateUtil.parse(paraMap.get("endDate").toString()));

			// 查找产品
			RepProdInfo prodInfo = null;
			Map<String, BigDecimal> mapBalance = new HashMap<String, BigDecimal>();

			if (prodId != null) {
				prodInfo = repProdInfoMapper.selectByPrimaryKey(prodId);
				BokBalance balance = new BokBalance();
				balance.setBooksetId(prodId);
				balance.setBalanceDate(genDate);
				List<BokBalance> listBalance = settReportMapper.select(balance);
				
				// 将list转诚map,空值统一转化为0
				mapBalance = listBalance.stream().map(x->{
					if ( x.getbAmt()==null ) 
						x.setbAmt(BigDecimal.ZERO);
					return x;
				}).collect(Collectors.toMap(BokBalance::getSubjectNo, BokBalance::getbAmt,(key1,key2)->key2));
			}

			settReportDto.setProdName(prodInfo.getProdName());
			settReportDto.setProdRe(prodInfo.getProdRe());
			settReportDto.seteDate(prodInfo.getSetDate());
			settReportDto.setTrustName(prodInfo.getAcctChnname());
			settReportDto.setmDate(prodInfo.getMdate());

			settReportDto.setSumAssetAmt(mapBalance.get("").add(mapBalance.get("")).add(mapBalance.get("")));// 资产合计
			settReportDto.setDeposit(mapBalance.get("")); // 银行存款
			settReportDto.setGuarantyAmt(mapBalance.get("")); // 保证金
			settReportDto.setIntAmt(mapBalance.get("")); // 应计利息
			settReportDto.setSumDebtAmt(mapBalance.get("").add(mapBalance.get("")).add(mapBalance.get(""))
					.add(mapBalance.get("")).add(mapBalance.get("")).add(mapBalance.get("")).add(mapBalance.get(""))
					.add(mapBalance.get(""))); // 负债合计
			settReportDto.setMgrFee(mapBalance.get("")); // 管理费
			settReportDto.setFloatFee(mapBalance.get("")); // 应付管理人业绩报酬
			settReportDto.setTrustFee(mapBalance.get("")); // 托管费
			settReportDto.setSaleFee(mapBalance.get("")); // 销售费
			settReportDto.setRedemAmt(mapBalance.get("")); // 赎回紧额
			settReportDto.setTaxFee(mapBalance.get("")); // 税费
			settReportDto.setProfit(mapBalance.get("")); // 应付利润
			settReportDto.setOtherPayAmt(mapBalance.get("")); // 其他应付紧额
			settReportDto.setAssetValue(BigDecimal.ZERO); // 资产价值，固定值0
        	listResult.add(CollectionUtils.bean2Map(settReportDto));
		} catch (Exception e) {
			logger.error("清算报表数据错误", e);
			e.printStackTrace();
		}

		return listResult;
	}

}
