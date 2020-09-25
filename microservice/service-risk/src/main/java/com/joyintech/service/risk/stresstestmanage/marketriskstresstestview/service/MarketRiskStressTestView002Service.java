/*
 * 文件名：RskValtblPrtBotassetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.service;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.dao.MarketRiskStressTestView002Mapper;
import com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.dto.MarketRiskStressTestView002Dto;
import com.joyintech.service.risk.conf.DataSourceType;

/**
 * 
 * @author lifangchen
 * @version 1.0 2019年8月24日
 * @since 1.0
 */
@Service
public class MarketRiskStressTestView002Service extends BaseService<MarketRiskStressTestView002Dto>{

    @Autowired
    private MarketRiskStressTestView002Mapper marketRiskStressTestView002Mapper;
  
    private Predicate<MarketRiskStressTestView002Dto> getFilter(Map<String, Object> pageParam) {
        return (item -> {
            String assetId = (String)pageParam.get("assetId");
            if (StringUtil.isNotEmpty(assetId)) {
                if (!StringUtil.equals(item.getAssetId(), assetId)) {
                    return false;
                }
            }
            return true;
        });
    }
    /**
     * 
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @throws Exception
     */
    public Page<MarketRiskStressTestView002Dto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        //Page<MarketRiskStressTestView002Dto> PAGE= super.queryForPage(page, length, () -> marketRiskStressTestView002Mapper.queryForPage(pageParam));
        String parentId = (String)pageParam.get("parentId");
        pageParam.put("parentId", "");
        Page<MarketRiskStressTestView002Dto> PAGE= super.queryForTree(() -> marketRiskStressTestView002Mapper.queryForPageTree(pageParam),
//        		MarketRiskStressTestView002Dto::getAssetId,
        		MarketRiskStressTestView002Dto::getIdTree,
        		MarketRiskStressTestView002Dto::getParentId,
        		MarketRiskStressTestView002Dto::getAssetName, getFilter(pageParam),Integer.MAX_VALUE,parentId.equals("root")?null:parentId);
        
        
        DynamicDataSourceHolder.clearDataSource();
        return PAGE;
    }
    /**
     * 
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @throws Exception
     */
    public Page<MarketRiskStressTestView002Dto> selectForPageExport(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        String parentId = (String)pageParam.get("parentId");
        pageParam.put("parentId", "");
//        Page<MarketRiskStressTestView002Dto> PAGE= super.queryForPage(page, length, () -> marketRiskStressTestView002Mapper.queryForPageTree(pageParam));
        
        List<MarketRiskStressTestView002Dto> list = marketRiskStressTestView002Mapper.queryForPageTree(pageParam);
        Page<MarketRiskStressTestView002Dto> pag = new Page<>();
        pag.setRoot(list);
        DynamicDataSourceHolder.clearDataSource();
        return pag;
    }
 
}
