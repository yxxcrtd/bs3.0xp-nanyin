package com.joyintech.service.product.messageadjust.supplement.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.messageadjust.supplement.dao.FinSupplementPlanChgMapper;
import com.joyintech.service.product.messageadjust.supplement.dto.FinSupplementPlanChgDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;

@Service("backFinSupplementPlanChgService")
public class FinSupplementPlanChgService extends BaseService<FinSupplementPlanChgDto> {

	@Autowired
	private FinSupplementPlanChgMapper finSupplementPlanChgMapper;

	public Page<SelectOptionDto> getFinprodIds(Map<String, Object> param,int page,int length) {
	    param.put("ISSUE_STATUS_01", DictCodeListExt1.发行状态_发行成功.getValue());
		return super.queryForPageForSelectReturn(page,length,()->finSupplementPlanChgMapper.getFinprodIds(param));
	}

	public String getcycleTdayRule(Map<String, Object> param) {
		return finSupplementPlanChgMapper.getcycleTdayRule(param);
	}

	public Page<FinSupplementPlanChgDto> findPage(Map<String, Object> param,int page,int length) {
	    param.put("PS_C16", DictCodeList.交易方向_回补.getValue());
	    param.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
	    param.put("YES_OR_NO_NO", DictCodeList.是否_否.getValue());
	    param.put("YES_OR_NO_YES", DictCodeList.是否_是.getValue());
	    
	    param.put("SUPPLEMENT_STATUS_01", DictCodeListExt3.贴补状态_已贴补.getValue());
	    param.put("SUPPLEMENT_STATUS_02", DictCodeListExt3.贴补状态_未贴补.getValue());
		return super.queryForPage(page, length, () ->finSupplementPlanChgMapper.findPage(param));
	}

	public Page<FinSupplementPlanChgDto> selectForPage(int page, int length, Map<String, Object> param) throws Exception {
		return super.queryForPage(page, length, () -> finSupplementPlanChgMapper.findPage(param));
	}
	
	/**
	 * 删除旧的回补计划
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	public boolean deleteAccrualSche(Map<String, Object> param) {
	    
        finSupplementPlanChgMapper.deleteAccrualSche(param); 
        return true;
	}

}
