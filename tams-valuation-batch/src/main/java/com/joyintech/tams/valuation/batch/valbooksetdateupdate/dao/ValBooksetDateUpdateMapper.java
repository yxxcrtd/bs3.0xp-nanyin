/*
 * 文件名：ValBooksetDateUpdateMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.valbooksetdateupdate.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 账套日期Mapper
 * @author wangzhiang  
 * @version 1.0 2018年7月11日
 * @see ValBooksetDateUpdateMapper
 * @since 1.0
 */
@Repository
public interface ValBooksetDateUpdateMapper {
    /**
     * 
     * 账套日期日切
     * @param booksets
     * @see
     * @since 1.0
     */
    void updateBooksetDate(@Param(value = "booksetIds") List<String> booksetIds);

    /**
     * 
     * 更新入账信息表 是否入账为是
     * @param booksets
     * @see
     * @since 1.0
     */
	void updateBokDealAccounting(@Param(value = "booksetIds") List<String> booksetIds,@Param("bookDate") Date bookDate);
    
}
