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

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskstresstestview.dto.MarketRiskStressTestView002Dto;
import com.joyintech.framework.grid.Page;
/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenyunlong
 * @version 1.0 2019年8月24日
 * @see MarketRiskStressTestView002Service
 * @since 1.0
 */
@Service
public class MarketRiskStressTestView002Service  extends CommonExportService<MarketRiskStressTestView002Dto>{
   
    /**
     * 查找所有数据
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<MarketRiskStressTestView002Dto> selectAll( @RequestBody Map<String, Object> param){
        return restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME +
            "/MarketRiskStressTestView002/findPage",param,
            MarketRiskStressTestView002Dto.class);
    }
    
    /**
     * 查找所有数据-导出
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<MarketRiskStressTestView002Dto> selectForPageExport( @RequestBody Map<String, Object> param){
        return restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME +
            "/MarketRiskStressTestView002/selectForPageExport",param,
            MarketRiskStressTestView002Dto.class);
    }
    @Override
    protected void buildExportParam(ExportParam exportParam) {
       
        exportParam.config("/com/joyintech/tams/invest/risk/stresstestmanage/marketriskstresstestview/conf/MarketRiskStressTestView002_config.xml");
    }


    @Override
    public Page<MarketRiskStressTestView002Dto> selectPageData(Map<String, Object> selectParam) {
        // TODO Auto-generated method stub
          return selectForPageExport(selectParam);
    }  
   
}
