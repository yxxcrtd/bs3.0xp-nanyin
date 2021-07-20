
package com.joyintech.tams.invest.period.trdstockconnectbonus.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.period.trdstockconnectbonus.dto.TrdStockConnectBonus001Dto;
import com.joyintech.tams.invest.period.trdstockconnectbonus.dto.TrdStockConnectBonus002Dto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see TrdStockConnectBonus001Service
 */
@Service
public class TrdStockConnectBonus001Service extends CommonExportService<TrdStockConnectBonus001Dto>{

	/**
     * 列表的name属性
     */
    public static final String TABLE_NAME = "TrdStockConnectBonus001";

	/**
	 * 业务编号
	 */
	public Page<SelectOptionDto> getBusiCode() {
		return restTemplate.postForPage(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/getBusiCode",
                RequestUtil.getPageParam(), SelectOptionDto.class);
	}
	
	/**
	 * 分页查询 资产代码
	 */
	public Page<SelectOptionDto> getFinprodMarketId() {
		Map<String, Object> params = RequestUtil.getPageParam();
        params.put("finprodType2",
            DictCodeListExt1.金融产品类型_投管用_股票.getValue());
		params.put("tradeMarket",
				DictCodeListExt1.交易场所_香港联合交易所.getValue());
        return restTemplate.postForPage(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/getFinprodMarketId",
            params, SelectOptionDto.class);
	}

	/**
	 * 分页查询 证券账户代码、名称
	 */
	public Page<SelectOptionDto> getSecManageAcctId() {
		return restTemplate.postForPage(
	            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/getSecManageAcctId",
	            RequestUtil.getPageParam(), SelectOptionDto.class);
	}
	
	/**
	 * 股票存续期列表查询
	 */
	public Page<TrdStockConnectBonus001Dto> findPage(Map<String, Object> pageParam) {
		return restTemplate.postForPage(
	            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/findPage", pageParam,
	            TrdStockConnectBonus001Dto.class);
	}

	/**
	 * 导出参数
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config(
	            "/com/joyintech/tams/invest/period/trdstockconnectbonus/config/export_conf.xml").dataSource(
	                DataSourceType.MASERT);
	}
	
	@Override
    public Page<TrdStockConnectBonus001Dto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }

	/**
	 * 撤销check
	 */
	public List<ValidMessage> revokeCheck(TrdStockConnectBonus002Dto dto) {
		List<ValidMessage> msg = new ArrayList<>();
		Map paramMap = new HashMap();
		paramMap.put("busiCode",dto.getBusiCode());
		//交易状态验证
		int count = restTemplate.postForObject(
	            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/revokeCheck",
				paramMap, Integer.class);
		if(count>0){
			msg.add(new ValidMessage(getMessage("TRD_W_0001", getMessage("TrdStockConnectBonus001.del"),getMessage("TrdStockConnectBonus001.revoke"))));
		}else{
			
		}
		return msg;
	}



}
