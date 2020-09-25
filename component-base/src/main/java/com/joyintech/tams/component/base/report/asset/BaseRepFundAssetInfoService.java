/*
 * 文件名：RepFundAssetInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.asset;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepFund;
import com.joyintech.tams.component.base.report.dto.BaseRepFundAssetInfoDto;

/**
 * 基金资产信息报送
 * 对债券标的资产新建时的报送信息进行处理，提供新建、修改、删除和查询接口
 * @author wangzehui
 * @version 1.0 2018年8月29日
 * @see RepFundAssetInfo
 * @since 1.0
 */
public interface BaseRepFundAssetInfoService {
    
    /**
     * 
     * 修改资产负债报送公共字段信息
     * <功能详细描述>
     * @param repTrustInvestMode
     * @see
     * @since 1.0
     */
    public boolean update(BaseRepFundAssetInfoDto dto);
    /**
     * 
     * 将信息插入委外投资-协议方式报送信息（REP_TRUST_INVEST_MODE）表
     * <功能详细描述>
     * @param repTrustInvestMode
     * @see
     * @since 1.0
     */
    public boolean insert(BaseRepFundAssetInfoDto dto);
    /**
     * 
     * 根据金融产品代码，表数据
     * <功能详细描述>
     * @param repTrustInvestMode
     * @see
     * @since 1.0
     */
    public boolean delete(BaseRepFundAssetInfoDto dto);
    /**
     * 
     * 根据金融产品代码，表数据
     * <功能详细描述>
     * @param repTrustInvestMode
     * @return
     * @see
     * @since 1.0
     */
    public List<RepFund> search(BaseRepFundAssetInfoDto dto);
}
