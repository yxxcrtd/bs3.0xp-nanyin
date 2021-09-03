/*
 * 文件名：BussinessCodeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.model.common.rateinfo.dao;

import com.joyintech.tams.valuation.model.common.rateinfo.dto.ViewRatingInfoDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 业务编号生成使用mapper
 * 〈功能详细描述〉
 * @author jinmaodong
 * @version 1.0 2021-09-01
 * @see RateInfoCommonMapper
 * @since 1.0
 */
@Repository
public interface RateInfoCommonMapper {

    List<ViewRatingInfoDto> getRateInfo(@Param("ocode") String ocode,@Param("otype") String otype,  @Param("gradeorgid")String gradeorgid, @Param("queryDate")Date queryDate);

}
