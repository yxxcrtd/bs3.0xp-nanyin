/*
 * 文件名：RepOutInvestAgrInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.asset;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepTrustInvestMode;
import com.joyintech.tams.component.base.report.dto.BaseRepTrustInvestModeInfoDto;

/**
 * 委外投资-协议方式报送信息
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年8月29日
 * @see RepOutInvestAgrInfo
 * @since 1.0
 */
public interface BaseRepTrustInvestModeInfoService {
    
    /**
     * 
     * 修改资产负债报送公共字段信息
     * <功能详细描述>
     * @param repTrustInvestMode
     * @see
     * @since 1.0
     */
    public boolean update(BaseRepTrustInvestModeInfoDto dto);
    /**
     * 
     * 将信息插入委外投资-协议方式报送信息（REP_TRUST_INVEST_MODE）表
     * <功能详细描述>
     * @param repTrustInvestMode
     * @see
     * @since 1.0
     */
    public boolean insert(BaseRepTrustInvestModeInfoDto dto);
    /**
     * 
     * 根据金融产品代码，删除委外投资-协议方式报送信息（REP_TRUST_INVEST_MODE）表数据
     * <功能详细描述>
     * @param repTrustInvestMode
     * @see
     * @since 1.0
     */
    public boolean delete(BaseRepTrustInvestModeInfoDto dto);
    /**
     * 
     * 根据金融产品代码，查询委外投资-协议方式报送信息（REP_TRUST_INVEST_MODE）表数据
     * <功能详细描述>
     * @param repTrustInvestMode
     * @return
     * @see
     * @since 1.0
     */
    public List<RepTrustInvestMode> search(BaseRepTrustInvestModeInfoDto dto);
}
