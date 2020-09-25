/*
 * 文件名：RskIsupTrialAndClsMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.batchList.dao;

import java.util.List;

/**
 * 批量存储Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月5日
 * @see BatchListMapper
 * @since 1.0
 */
public interface BatchListMapper<V> {

    void insertBatchList(List<V> infoList);
}
