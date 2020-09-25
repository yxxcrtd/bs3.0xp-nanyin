package com.joyintech.service.chb3.investormerger.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.investormerger.dto.ChbEquHoldCheckDto;
import com.joyintech.tams.report.investormerger.service.InvestorMerger001Service;

/**
 * 识别标识手工合并服务
 * 〈功能详细描述〉
 * @author zhagnzhongliang
 * @version 1.0 2019年04月19日
 * @see InvestorMergerController
 * @since 1.0
 */
@RestController
@RequestMapping("/InvestorMerger")
public class InvestorMergerController{
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private InvestorMerger001Service investorMerger001Service;
	
    /**
     * @Description 分页查询
     * @return Page<ChbEquHoldCheckDto>
     * @version 1.0 2019年3月18日
     */
    @PostMapping("findPage/{page}/{length}")
	public Page<ChbEquHoldCheckDto> selectAllData(@RequestBody Map<String, Object> param) {
    	Page<ChbEquHoldCheckDto> page = investorMerger001Service.selectForPage(param);
    	return page;
	}
    
    /**
     * 业务画面导出事件
     * @return
     * @since 1.0
     */
    @PostMapping("selectForExport")
	public List<ChbEquHoldCheckDto> selectForExport(@RequestBody Map<String, Object> param)  {
		return  investorMerger001Service.selectForExport(param);
    }
	
	@PostMapping("manual" )
	public void manualMerger(@RequestBody Map<String,Object> map) {
    	investorMerger001Service.manualMerger(map);
	}
}
