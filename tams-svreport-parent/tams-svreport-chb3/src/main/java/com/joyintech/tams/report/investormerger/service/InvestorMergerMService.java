package com.joyintech.tams.report.investormerger.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.investormerger.dto.ChbEquHoldCheckDto;

/**
 * 产品份额统计
  * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see ProdShareCountMService
 * @since 1.0
 */
@Service
public class InvestorMergerMService extends CommonExportService<ChbEquHoldCheckDto>{

    /**
     * @Description 分页查询
     * @return Page<ChbEquHoldCheckDto>
     * @version 1.0 2019年3月18日
     */
	public Page<ChbEquHoldCheckDto> selectForPage(Map<String, Object> param) {
		return restTemplate.postForPage(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/InvestorMerger/findPage",param,
        		ChbEquHoldCheckDto.class);
	}
    
    /**
     * 业务画面导出 查询
     * @return
     * @since 1.0
     */
	public List<ChbEquHoldCheckDto> selectForExport(@RequestBody Map<String, Object> param)  {
    	return restTemplate.postForList(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/InvestorMerger/selectForExport",param,
        		ChbEquHoldCheckDto.class);
    }
	
	public void manualMerger(@RequestBody Map<String,Object> map) {
		restTemplate.postForObject(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/InvestorMerger/manual",map,
        		Integer.class);
	}
}
