package com.joyintech.service.risk.creditmanagement.creditlinemanage.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditmanagement.creditlinemanage.dao.CreditTagInfoDaoMapper;
import com.joyintech.service.risk.creditmanagement.creditlinemanage.dto.CreditTagInfoDto;


/**
 * 额度标签管理
 * @author chenlei1
 * @version 1.0 2019年02月15日
 * @see CeLine001Service
 * @since 1.0
 */
@Service
public class CeLine001Service extends BaseService<CreditTagInfoDto> {
	
	@Autowired
	private CreditTagInfoDaoMapper creditTagInfoMapper;
	
    /**
     * 画面查询方法
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<CreditTagInfoDto> selectForPage(int page, int length,Map<String, Object> param) 
        throws Exception {
        return super.queryForPage(page, length, () -> creditTagInfoMapper.selectForPage(param));
    }

    /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
    public Page<SelectOptionDto> getLimitLabel(Map<String, Object> map, int page, int length) {
    	//map.put("param", map);
    	return this.queryForPageForSelectReturn(page, length,()->creditTagInfoMapper.getLimitLabel(map));
    }
    
}
