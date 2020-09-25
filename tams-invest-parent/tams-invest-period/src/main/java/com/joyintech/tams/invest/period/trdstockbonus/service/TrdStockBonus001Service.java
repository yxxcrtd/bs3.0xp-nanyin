/**
 * 
 */
package com.joyintech.tams.invest.period.trdstockbonus.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.period.trdstockbonus.dto.TrdStockBonus001Dto;
import com.joyintech.tams.invest.period.trdstockbonus.dto.TrdStockBonus002Dto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author panbin
 * @version 2019年6月18日
 * @see TrdStockBonus001Service
 */
@Service
public class TrdStockBonus001Service extends CommonExportService<TrdStockBonus001Dto>{

	/**
     * 列表的name属性
     */
    public static final String TABLE_NAME = "TrdStockBonus001";

	/**
	 * 
	 *
	 * @author panbin
	 * @date 2019年6月21日
	 * @return Page<SelectOptionDto>
	 */
	public Page<SelectOptionDto> getBusiCode() {
		return restTemplate.postForPage(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockBonus/getBusiCode",
                RequestUtil.getPageParam(), SelectOptionDto.class);
	}
	
	/**
	 * 
	 * 
	 *
	 * @author panbin
	 * @date 2019年6月20日
	 * @return Page<SelectOptionDto>
	 */
	public Page<SelectOptionDto> getFinprodMarketId() {
		Map<String, Object> params = RequestUtil.getPageParam();
        params.put("finprodType2",
            DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        return restTemplate.postForPage(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockBonus/getFinprodMarketId",
            params, SelectOptionDto.class);
	}

	/**
	 * 
	 *
	 * @author panbin
	 * @date 2019年6月21日
	 * @return Page<SelectOptionDto>
	 */
	public Page<SelectOptionDto> getSecManageAcctId() {
		return restTemplate.postForPage(
	            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockBonus/getSecManageAcctId",
	            RequestUtil.getPageParam(), SelectOptionDto.class);
	}
	
	/**
	 * 股票存续期列表查询
	 *
	 * @author panbin
	 * @date 2019年6月19日
	 * @param pageParam
	 * @return
	 */
	public Page<TrdStockBonus001Dto> findPage(Map<String, Object> pageParam) {
		return restTemplate.postForPage(
	            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockBonus/findPage", pageParam,
	            TrdStockBonus001Dto.class);
	}

	/**
	 * 导出参数
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config(
	            "/com/joyintech/tams/invest/period/trdstockbonus/config/export_conf.xml").dataSource(
	                DataSourceType.MASERT);
	}
	
	@Override
    public Page<TrdStockBonus001Dto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }

	/**
	 * 撤销check
	 *
	 * @author panbin
	 * @date 2019年6月24日
	 * @param dto
	 * @return Collection<? extends ValidMessage>
	 */
	public List<ValidMessage> revokeCheck(TrdStockBonus002Dto dto) {
		List<ValidMessage> msg = new ArrayList<>();
		Map paramMap = new HashMap();
		paramMap.put("busiCode",dto.getBusiCode());
		//交易状态验证
		int count = restTemplate.postForObject(
	            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockBonus/revokeCheck",
				paramMap, Integer.class);
		if(count>0){
			msg.add(new ValidMessage(getMessage("TRD_W_0001", getMessage("TrdStockBonus001.del"),getMessage("TrdStockBonus001.revoke"))));
		}else{
			
		}
		return msg;
	}



}
