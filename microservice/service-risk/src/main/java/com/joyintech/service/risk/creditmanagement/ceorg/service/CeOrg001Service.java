package com.joyintech.service.risk.creditmanagement.ceorg.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditmanagement.ceorg.dao.CeOrgMapper;
import com.joyintech.service.risk.creditmanagement.ceorg.dto.CreditLinesOrgDto;



/**
 * 监控指标
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see CeOrg001Service
 * @since 1.0
 */
@Service("backCeOrg001Service")
public class CeOrg001Service extends BaseService<CreditLinesOrgDto> {
	
	@Autowired
	private CeOrgMapper ceOrgMapper;
	
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
    public Page<CreditLinesOrgDto> selectForPage(int page, int length,Map<String, Object> param) 
        throws Exception {
        return super.queryForPage(page, length, () -> ceOrgMapper.selectForPage(param));
    }

    /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
    public Page<SelectOptionDto> getCustomerId(Map<String, Object> map, int page, int length) {
    	return this.queryForPageForSelectReturn(page, length,()->ceOrgMapper.getCustomerId(map));
    }
    
}
