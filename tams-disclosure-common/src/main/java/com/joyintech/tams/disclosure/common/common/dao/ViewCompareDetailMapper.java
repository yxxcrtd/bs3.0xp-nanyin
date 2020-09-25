/*
 * 文件名：ViewCompareDetailMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年12月12日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.disclosure.common.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author 何阳阳
 * @version 1.0 2017年12月12日
 * @see ViewCompareDetailMapper
 * @since 1.0
 */
@Repository(value="disViewCompareDetailMapper")
public interface ViewCompareDetailMapper {
    /**
     * 查询详细
     * 
     * @param param 参数
     * @return 详细信息
     */
    List<Map<String, Object>> selectDetail(Map<String, Object> param);
}
