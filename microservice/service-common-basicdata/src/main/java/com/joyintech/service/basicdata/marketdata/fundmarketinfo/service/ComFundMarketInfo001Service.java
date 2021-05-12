package com.joyintech.service.basicdata.marketdata.fundmarketinfo.service;

import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.fundmarketinfo.dto.ComFundMarketInfo001dto;
import com.joyintech.tams.constant.DictCodeListExt1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.marketdata.fundmarketinfo.dao.ComFundMarketInfo001mapper;

/**
 * 每日基金行情业务层
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangqidong
 * @version 1.0 2018年7月23日
 * @see ComFundMarketInfo001Service
 * @since 1.0
 */
@Service("serviceComFundMarketInfo001Service")
public class ComFundMarketInfo001Service extends CommonExportService<ComFundMarketInfo001dto>{

    /**
     * ValFundMarketInfo001mapper 对象
     */
    @Autowired
    private ComFundMarketInfo001mapper comFundMarketInfo001Mapper;

	/**
	 * 加载表格
	 * @param dto ComFundMarketInfo001dto
	 * @return  List<ComFundMarketInfo001dto>
	 */
    public List<ComFundMarketInfo001dto> tableLoad(Map<String, Object> params) {
        return comFundMarketInfo001Mapper.tableLoad(params);
    }
	 
	 
	public Page<ComFundMarketInfo001dto> findPage(int page, int length, Map<String, Object> params) {
	        return super.queryForPage(page,length,()-> comFundMarketInfo001Mapper.tableLoad(params));
	}
	/**
	 * 
	 * 赋值条件类型转换
	 * <功能详细描述>
	 * @param map map
	 * @return ComFundMarketInfo001dto
	 */
//    public ComFundMarketInfo001dto dto(Map<String, Object> map){
//        ComFundMarketInfo001dto dto = new ComFundMarketInfo001dto();
//        dto.setFundName((String)map.get("fundName"));
//        dto.setFundCode((String)map.get("fundCode"));
//        dto.setValueSource((String)map.get("valueSource"));
//        dto.setFundType((String)map.get("fundType"));
//        dto.setStartDate((String)map.get("startDate"));
//        dto.setEndDate((String)map.get("endDate"));
//        dto.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
//        return dto;
//    }


	/**
	 * 删除数据
	 */
	public int deleteByPrimaryKey(ComFundMarketInfo001dto dto){
		return comFundMarketInfo001Mapper.deleteByPrimaryKey(dto);
	}

	/**
	 * 新建数据
	 */
	public int saveNewDto(ComFundMarketInfo001dto dto){
		return comFundMarketInfo001Mapper.saveNewDto(dto);
	}

	/**
	 * 更新数据
	 */
	public int updateByPrimaryKey(ComFundMarketInfo001dto dto){
		return comFundMarketInfo001Mapper.updateByPrimaryKey(dto);
	}

	/**
	 * 查询符合条件的dto数据
	 */
	public List<ComFundMarketInfo001dto> queryDtoByKey(Map<String,Object> map){
		return comFundMarketInfo001Mapper.queryDtoByKey(map);
	}

	/**
	 * 查询基金代码
	 */
	public Page<SelectOptionDto> getFinProductList(Map<String,Object> map,int page, int length){
		return this.queryForPageForSelectReturn(page, length,() -> comFundMarketInfo001Mapper.getFinProductList(map));
	}

	public List<SelectOptionDto> getFinprodMarketId(Map<String, Object> map) {
		map.put("finprodType", DictCodeListExt1.金融产品类型_基金.getValue());
		return comFundMarketInfo001Mapper.getFinprodMarketId(map);
	}

	/**
	 * 根据产品代码查找市场代码
	 * @param finprodId
	 * @return
	 */
	public String queryFinprodMarketIdByFinprodId(String finprodId) {
		return comFundMarketInfo001Mapper.queryFinprodMarketIdByFinprodId(finprodId);
	}

	public List<ComFundMarketInfo001dto> getAssetInfo(Map<String, Object> param) {
		return comFundMarketInfo001Mapper.getAssetInfo(param);
	}
}



