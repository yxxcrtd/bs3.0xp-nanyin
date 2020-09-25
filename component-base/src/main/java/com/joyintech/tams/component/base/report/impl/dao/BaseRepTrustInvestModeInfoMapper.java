/*
 * 文件名：BaseRepTrustInvestModeInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.impl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepTrustInvestMode;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年9月4日
 * @see BaseRepTrustInvestModeInfoMapper
 * @since 1.0
 */
@Repository
public interface BaseRepTrustInvestModeInfoMapper {

    /**
     * 查询委外投资-协议方式报送信息
     * <功能详细描述>
     * @param idList
     * @see
     * @since 1.0
     */
    List<RepTrustInvestMode> search(List<String> idList);

    /**
     * 批量新建委外投资-协议方式报送信息
     * <功能详细描述>
     * @param entityList
     * @return
     * @see
     * @since 1.0
     */
    int insert(List<RepTrustInvestMode> entityList);

    /**
     * 批量新建委外投资-协议方式报送信息
     * <功能详细描述>
     * @param repFinprodIdList
     * @return
     * @see
     * @since 1.0
     */
    int delete(List<String> idList);

}
