package com.joyintech.service.entrust.etrbondbiddingresult.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.entrust.etrbondbiddingresult.dao.EtrbondbiddingresultMapper;
import com.joyintech.service.entrust.etrbondbiddingresult.dto.EtrbondbiddingresultDto;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("backEtrbondbiddingresultService")
public class EtrbondbiddingresultService extends BaseService<EtrbondbiddingresultDto> {

	@Autowired
	private EtrbondbiddingresultMapper etrbondbiddingresultMapper;

	/**
	 * 使用给定的检索分页数据。返回类型是Service的泛型。
	 *
	 * @param length int
	 * @param page int
	 * @param pageParam 查询方法
	 * @return Page类
	 * @throws Exception 异常信息
	 */
	public Page<EtrbondbiddingresultDto> selectForPage(int page, int length, Map<String, Object> pageParam) throws Exception {
		String portfolioId = (String)pageParam.get("portfolioId");
		if(StringUtil.isNotEmpty(portfolioId)){
			pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
		}
		Page<EtrbondbiddingresultDto> etrbondbiddingresultDtoPage = super.queryForPage(page, length, () -> etrbondbiddingresultMapper.select(pageParam));
		if (!etrbondbiddingresultDtoPage.getRoot().isEmpty()){
			List<EtrbondbiddingresultDto> resultList = getResultList(etrbondbiddingresultDtoPage.getRoot());
			etrbondbiddingresultDtoPage.setRoot(resultList);
			etrbondbiddingresultDtoPage.setRecords(resultList.size());
		}
		return etrbondbiddingresultDtoPage;
	}

	/**
	 * 封装查询结果解决合并单标位问题
	 * @param select
	 * @return
	 */
	private List<EtrbondbiddingresultDto> getResultList(List<EtrbondbiddingresultDto> select) {
		Map<String, List<EtrbondbiddingresultDto>> entrustIdMap = select.stream().collect(JoyinCollectors.groupingBy(EtrbondbiddingresultDto::getEntrustId));
		List<EtrbondbiddingresultDto> resultList = new ArrayList<>();
		for (String entrustId : entrustIdMap.keySet()) {
			//根据中标标位分组
			Map<BigDecimal, List<EtrbondbiddingresultDto>> winPrices = entrustIdMap.get(entrustId).stream().collect(JoyinCollectors.groupingBy(EtrbondbiddingresultDto::getWinPrice));
			//根据投标标位分组
			Map<BigDecimal, List<EtrbondbiddingresultDto>> bidPrices = entrustIdMap.get(entrustId).stream().collect(JoyinCollectors.groupingBy(EtrbondbiddingresultDto::getBidPrice));
			//如果两个map的size不一致，则该委托是单标位
			if (winPrices.size()!=bidPrices.size()){
				EtrbondbiddingresultDto etrbondbiddingresultDto = entrustIdMap.get(entrustId).get(0);
				BigDecimal bidPriceAmt = BigDecimal.ZERO;
				String bidPriceStr = "";
				for (EtrbondbiddingresultDto dto : entrustIdMap.get(entrustId)) {
					bidPriceAmt = ArithmeticUtil.doAdd(dto.getBidPrinAmt(),bidPriceAmt);
					bidPriceStr += dto.getBidPrice().toString() + ",";
				}
				etrbondbiddingresultDto.setBidPriceStr(bidPriceStr.substring(0,bidPriceStr.length()-1));
				etrbondbiddingresultDto.setBidPrinAmt(bidPriceAmt);
				resultList.add(etrbondbiddingresultDto);
			}else {
				for (EtrbondbiddingresultDto etrbondbiddingresultDto : entrustIdMap.get(entrustId)) {
					etrbondbiddingresultDto.setBidPriceStr(etrbondbiddingresultDto.getBidPrice().toString());
					resultList.add(etrbondbiddingresultDto);
				}
			}

		}
		return resultList;
	}

	public List<FinProduct> findFinProductByOFinprodId(Map<String,Object> map){
		return etrbondbiddingresultMapper.findFinProductByOFinprodId(map);
	}

    public void updateTradeId(Map<String, Object> map) {
        if (map.get("tradeId") == null) {
            return;
        }
        etrbondbiddingresultMapper.updateTradeId(map);
    }
}
