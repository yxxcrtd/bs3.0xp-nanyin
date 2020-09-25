package com.joyintech.service.report.poc.directtransaction.exblockbond.dao;

import org.springframework.stereotype.Component;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.poc.directtransaction.exblockbond.dto.ExBlockBondDto;

import java.util.List;
import java.util.Map;

@Component
public interface ExBlockBondMapper {
	
	ExBlockBondDto search(Map<String, String> map);
	/**
     * 
     * 获取新建界面的资产代码联想下拉框框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getfinprodIdForAdd(Map<String, Object> map);
}
