/*
 * 文件名：ProdShareCount001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年6月29日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.report.queryanalysis.prodsharecount.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.queryanalysis.prodsharecount.dto.ProdShareCount001Dto;


/**
 * 产品份额统计
 * @author zuoyinyi
 * @version 1.0 2018年6月29日
 * @see ProdShareCount001Mapper
 * @since 1.0
 */
@Repository
public interface ProdShareCount001Mapper {
    /**
     * 
     * 页面结果查询
     * @param map 查询条件
     * @return 结果列表
     * @see
     * @since 1.0
     */
    List<ProdShareCount001Dto> searchPage(Map<String,Object> map);
}
