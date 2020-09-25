/*
 * 文件名：ChannelAssetQueryService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年3月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.channel.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.invest.asset.bond.dto.FinBondTargetAssetDto;
import com.joyintech.tams.invest.asset.channel.dto.ChannelAssetDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 通道资产查询-处理详细
 * 〈功能详细描述〉
 * @author yangjm
 * @version 1.0 2019年3月25日
 * @see ChannelAssetQueryService
 * @since 1.0
 */
@Service("ChannelAssetQueryService")
@Transactional(rollbackFor = Exception.class)
public class ChannelAssetQueryService  extends CommonExportService<ChannelAssetDto>{
    /**
     * 
     * 分页查询
     * <功能详细描述>
     * @param pageParam 查询参数
     * @return 通道资产查询结果列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<ChannelAssetDto> selectForPage(Map<String,Object> pageParam) 
        throws Exception{
        return restTemplate.postForPage(
            ServiceApplication.ASSET_SERVICE_NAME + "/channelmanageinfo/findPage", 
            pageParam, ChannelAssetDto.class);
        
    }
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/asset/channel/conf/export_conf_channelasset.xml");
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ChannelAssetDto> selectPageData(Map<String, Object> selectParam) {
        try {
            return selectForPage(selectParam);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
