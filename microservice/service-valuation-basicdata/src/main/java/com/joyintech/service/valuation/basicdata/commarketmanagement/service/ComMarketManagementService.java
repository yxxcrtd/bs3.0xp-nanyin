package com.joyintech.service.valuation.basicdata.commarketmanagement.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basicdata.commarketmanagement.dao.ComMarketManagementMapper;
import com.joyintech.service.valuation.basicdata.commarketmanagement.dto.ComMarketManageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ComMarketManagementService extends BaseService<ComMarketManageDto>{
	@Autowired
	private ComMarketManagementMapper comMarketManagementMapper;
	public Page<ComMarketManageDto> selectForPage(int page, int length, Map<String, Object> pageParam)
	        throws Exception {
 	        return super.queryForPage(page,length,() -> comMarketManagementMapper.queryForPage(pageParam));
	    }
	  /**
     * 
     * 客户名称联想文本框service层实现
     * <功能详细描述>
     * @param map 联想文本框查询信息
     * @return 查询结果
     * @see
     * @since 1.0
     */
 
	public List<SuggestDto> selectFinprodName(Map<String, Object> param) {
 		return comMarketManagementMapper.selectFinprodName(param);
	}
	 
/*	public Page<SelectOptionDto> getGradeResult(int page, int length, Map<String, Object> param) {
 		  return queryForPageForSelectReturn(page, length, () -> mstMarketManagementMapper.getGradeResult(param));

			}*/


	/**
	 * 根据资产代码获取债券
	 * @param param
	 * @return
	 */
	public List<ComMarketManageDto> getFinprodLists(Map<String, Object> param) {
		return comMarketManagementMapper.getFinprodLists(param);
	}

	/**
	 * 根据评级结构获取机构
	 * @param param
	 * @return
	 */
	public List<ComMarketManageDto> getCustomerLists(Map<String, Object> param) {
		return comMarketManagementMapper.getCustomerLists(param);
	}

	/**
	 * 导入数据
	 * @param list
	 * @return
	 */
	public int importDatas(List<ComMarketManageDto> list) {
		comMarketManagementMapper.deleteBatch(list);
		return comMarketManagementMapper.insertBatch(list);
	}

 
}
