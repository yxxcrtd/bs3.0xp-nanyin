/*
 * 文件名：RskAuthorityManageService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.marketriskstresstestview.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskstresstestview.dto.MarketRiskStressTestView001Dto;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lifangchen
 * @version 1.0 2019年8月24日
 * @see MarketRiskStressTestView001Service
 * @since 1.0
 */
@Service
public class MarketRiskStressTestView001Service  extends CommonExportService<MarketRiskStressTestView001Dto>
     {
   
	public MarketRiskStressTestView001Dto initPage(@RequestBody Map<String, Object> param) {
		List<MarketRiskStressTestView001Dto> i = restTemplate.postForList(
				ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskStressTestView001/initPage", param,
				MarketRiskStressTestView001Dto.class);
		return i.get(0);
	}
    public Page<MarketRiskStressTestView001Dto> selectAll( @RequestBody Map<String, Object> param){
        

        return restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME +
            "/MarketRiskStressTestView001/findPage",param,
            MarketRiskStressTestView001Dto.class);
    }
    
    public List<SelectOptionDto> selectTestListMap(@RequestBody Map<String, Object> param){
        
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME +
            "/MarketRiskStressTestView001/selectTestListMap",param,
            SelectOptionDto.class);
    }
    @Override
    protected void buildExportParam(ExportParam exportParam) {
       
        exportParam.config("/com/joyintech/tams/invest/risk/stresstestmanage/marketriskstresstestview/conf/MarketRiskStressTestView001_config.xml");
    }


    @Override
    public Page<MarketRiskStressTestView001Dto> selectPageData(Map<String, Object> selectParam) {
        // TODO Auto-generated method stub
        Page<MarketRiskStressTestView001Dto> sss=selectAll(selectParam);
          return selectAll(selectParam);
    }
}
