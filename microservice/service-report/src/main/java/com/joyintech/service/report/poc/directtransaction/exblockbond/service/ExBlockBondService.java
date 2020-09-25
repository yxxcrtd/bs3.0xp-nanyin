package com.joyintech.service.report.poc.directtransaction.exblockbond.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.poc.directtransaction.exblockbond.dao.ExBlockBondMapper;
import com.joyintech.service.report.poc.directtransaction.exblockbond.dto.ExBlockBondDto;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("backExBlockBondService")
public class ExBlockBondService extends BaseService<ExBlockBondDto> {

	@Autowired
	private ExBlockBondMapper exBlockBondMapper;
	
	public ExBlockBondDto search(String entrustId){
		Map<String, String> map = new HashMap<>();
		map.put("entrustId", entrustId);
		return exBlockBondMapper.search(map);
	}
	/**
     * 获取新建界面的资产代码联想下拉框框 <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getfinprodIdForAdd(Map<String, Object> map) {
        map.put("product", BussinessDictCodeList.金融产品类型_投管用_项目类资产.getValue());
        return exBlockBondMapper.getfinprodIdForAdd(map);
    }
}
