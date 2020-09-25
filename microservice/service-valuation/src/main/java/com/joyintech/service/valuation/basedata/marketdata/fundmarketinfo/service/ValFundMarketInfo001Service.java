package com.joyintech.service.valuation.basedata.marketdata.fundmarketinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.basedata.marketdata.fundmarketinfo.dao.ValFundMarketInfo001mapper;
import com.joyintech.service.valuation.basedata.marketdata.fundmarketinfo.dto.ValFundMarketInfo001dto;
/**
 * 每日基金行情业务层
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangqidong
 * @version 1.0 2018年7月23日
 * @see ValFundMarketInfo001Service
 * @since 1.0
 */
@Service(value="serviceValFundMarketInfo001Service")
public class ValFundMarketInfo001Service extends CommonExportService<ValFundMarketInfo001dto>{

    /**
     * ValFundMarketInfo001mapper 对象
     */
    @Autowired
    private ValFundMarketInfo001mapper valFundMarketInfo001mapper;

	/**
	 * 加载表格
	 * @param dto ValFundMarketInfo001dto
	 * @return  List<ValFundMarketInfo001dto>
	 */
    public List<ValFundMarketInfo001dto> tableLoad(Map<String, Object> params) {
        return valFundMarketInfo001mapper.tableLoad(params);
    }
	 
	 
	public Page<ValFundMarketInfo001dto> findPage(int page,int length,Map<String, Object> params) {
	        return super.queryForPage(page,length,()->valFundMarketInfo001mapper.tableLoad(params));
	}
	/**
	 * 
	 * 赋值条件类型转换
	 * <功能详细描述>
	 * @param map map
	 * @return ValFundMarketInfo001dto
	 */
//    public ValFundMarketInfo001dto dto(Map<String, Object> map){
//        ValFundMarketInfo001dto dto = new ValFundMarketInfo001dto();
//        dto.setFundName((String)map.get("fundName"));
//        dto.setFundCode((String)map.get("fundCode"));
//        dto.setValueSource((String)map.get("valueSource"));
//        dto.setFundType((String)map.get("fundType"));
//        dto.setStartDate((String)map.get("startDate"));
//        dto.setEndDate((String)map.get("endDate"));
//        dto.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
//        return dto;
//    }
    

}



