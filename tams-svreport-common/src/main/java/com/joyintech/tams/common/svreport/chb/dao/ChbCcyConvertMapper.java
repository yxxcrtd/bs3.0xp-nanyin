/*
 * 文件名：ChbDeadLineMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：Administrator
 * 修改时间：2017年12月16日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.common.svreport.chb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbMstExchangeInfo;

/**
 * 汇率换算
 * @author lingxq
 * @version 1.0 2019年02月10日
 * @see ChbCcyConvertMapper
 * @since 1.0
 */
@Repository
public interface ChbCcyConvertMapper {

    /**
     * 
     * 根据日期和折算币种获取汇率行情
     * @param ccy  传入币种
     * @param date 传入日期
     * @return 汇率行情
     * @see
     * @since 1.0
     */
	List<ChbMstExchangeInfo> getExchangerate(@Param("ccy")String ccy, @Param("date")Date date);
 
}
