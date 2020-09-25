/*
 * 文件名：ChannelAssetQueryService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年3月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.channel.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.asset.channel.dao.ChannelAssetMapper;
import com.joyintech.service.asset.channel.dto.ChannelAssetDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author yangjm
 * @version 1.0 2019年3月25日
 * @see ChannelAssetQueryService
 * @since 1.0
 */
@Service
public class ChannelAssetQueryService  extends BaseService<ChannelAssetDto> {

    
    /**
     * 通道代码
     */
    private static final String SECACCTID = "secAcctId";
    
    /**
     * 通道名称
     */
    private static final String SECACCTNAME = "secAcctName";
    
    /**
     * 通道资产查询sql
     */
    @Autowired
    private ChannelAssetMapper channelAssetMapper;
    /**
     * 分页查询
     * 
     * @param length 长度
     * @param page 页数
     * @param pageParam 分页查询参数
     * @return 组合管理集合
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<ChannelAssetDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("PINAN_ASSET_TYPE_F01", DictCodeList.平安资产类型_债券.getValue());
        if (StringUtil.isNotEmpty(pageParam.get(SECACCTID))) {
            pageParam.put(SECACCTID, StringUtil.splitToList(null,  pageParam.get(SECACCTID).toString())); 
        }
        else {
            pageParam.remove(SECACCTID);
        }
        if (StringUtil.isNotEmpty(pageParam.get(SECACCTNAME))) {
            pageParam.put(SECACCTNAME, StringUtil.splitToList(null,  pageParam.get(SECACCTNAME).toString())); 
        }
        else {
            pageParam.remove(SECACCTNAME);
        }
        
        return super.queryForPage(page, length,
            () -> channelAssetMapper.selectChannelAssetForPage(pageParam));
    }
}
