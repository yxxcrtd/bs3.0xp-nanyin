package com.joyintech.service.report.poc.directtransaction.exblockbond.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.poc.directtransaction.exblockbond.dto.ExBlockBondDto;
import com.joyintech.service.report.poc.directtransaction.exblockbond.service.ExBlockBondService;

@RestController("backExBlockBondController")
@RequestMapping("/ExBlockBond")
public class ExBlockBondController {

	@Autowired
	private ExBlockBondService exBlockBondService;
	
	@GetMapping("/search")
	@ResponseBody
	public ExBlockBondDto search(String entrustId){
		return exBlockBondService.search(entrustId);
	}
	/**
     * 
     * 获取新建界面的资产代码联想下拉框框
     * <功能详细描述>
     * @param finprodId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getfinprodIdForAdd")
    @ResponseBody
    public List<SelectOptionDto> getfinprodIdForAdd(@RequestBody Map<String, Object> map)
        throws Exception {
        List<SelectOptionDto> temp = exBlockBondService.getfinprodIdForAdd(map);
        return temp;
    }
}
