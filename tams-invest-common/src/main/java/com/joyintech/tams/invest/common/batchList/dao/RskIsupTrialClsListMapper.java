/*
 * 文件名：RskIsupTrialClsListMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.batchList.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskIsupTrialCls;

/**
 * 风险试算结论批量登陆Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月5日
 * @see RskIsupTrialClsListMapper
 * @since 1.0
 */
@Repository
public interface RskIsupTrialClsListMapper extends BatchListMapper<RskIsupTrialCls> {

    @Override
    void insertBatchList(List<RskIsupTrialCls> infoList);
}
