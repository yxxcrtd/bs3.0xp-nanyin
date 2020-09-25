/*
 * 文件名：ChannelAssetQuery001Service.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：yangjm 修改时间：2019年3月13日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.channel.feepay.service;

import java.util.Map;

import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.channel.feepay.dto.ChannelFeePay001Dto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;

/**
 * 通道费用支付查询
 * 
 * @author Johnnie
 * @version 1.0 2019年3月13日
 * @see ChannelFeePay001Service
 * @since 1.0
 */
@Service("ChannelFeePay001Service")
@Transactional(rollbackFor = Exception.class)
public class ChannelFeePay001Service extends CommonExportService<ChannelFeePay001Dto> {

    /**
     * 分页查询 <功能详细描述>
     * 
     * @param pageParam 查询参数
     * @return 通道资产查询结果列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<ChannelFeePay001Dto> selectForPage(Map<String, Object> pageParam) {
        pageParam.put("loginUserName", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/ChannelFeePay/findPage", pageParam,
            ChannelFeePay001Dto.class);
    }

    public Page<SelectOptionDto> findChannelId() {
        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/ChannelFeePay/findChannelId", RequestUtil.getPageParam(),
                SelectOptionDto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/trade/channel/feepay/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ChannelFeePay001Dto> selectPageData(Map<String, Object> selectParam) {
        try {
            return selectForPage(selectParam);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
