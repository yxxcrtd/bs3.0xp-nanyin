/*
 * 文件名：BookAccountGenerateMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.bookaccountgenerate.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.bookaccountgenerate.dto.BookAccountDetail;

@Repository
public interface BookAccountGenerateMapper {

    int deleteOldDataDetail(Map<String, Object> params);

    int deleteOldDataRel(Map<String, Object> params);

    int deleteOldData(Map<String, Object> params);

    List<BookAccountDetail> selectData(Map<String, Object> params);
    void updateHistory(@Param("vouchYear") String vouchYear,@Param("vouchMonth") String vouchMonth);
}
