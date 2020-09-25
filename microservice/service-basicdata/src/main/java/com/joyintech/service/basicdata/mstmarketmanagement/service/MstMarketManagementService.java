package com.joyintech.service.basicdata.mstmarketmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.mstmarketmanagement.dao.MstMarketManagementMapper;
import com.joyintech.service.basicdata.mstmarketmanagement.dto.MstMarketManageDto;
@Service
public class MstMarketManagementService  extends BaseService<MstMarketManageDto>{
	@Autowired
	private MstMarketManagementMapper mstMarketManagementMapper;
	public Page<MstMarketManageDto> selectForPage(int page, int length, Map<String, Object> pageParam)
	        throws Exception {
 	        return super.queryForPage(page,length,() -> mstMarketManagementMapper.queryForPage(pageParam));
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
 		return mstMarketManagementMapper.selectFinprodName(param);
	}
	 
/*	public Page<SelectOptionDto> getGradeResult(int page, int length, Map<String, Object> param) {
 		  return queryForPageForSelectReturn(page, length, () -> mstMarketManagementMapper.getGradeResult(param));

			}*/
	 
 
}
