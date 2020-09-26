/*
 * 文件名：ValVoucherQuery002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQuery002Dto;

/**
 * 凭证详情查看Mapper 〈功能详细描述〉
 * 
 * @author weipeng
 * @version 1.0 2018年5月5日
 * @see ValVoucherQuery002Mapper
 * @since 1.0
 */
@Repository
public interface ValVoucherQuery002Mapper {

    /**
     * 根据凭证编号bookId查询对应编号的所有凭证信息
     * 
     * @param bookId 凭证编号
     * @return List<ValVoucherQuery002Dto>
     * @see
     * @since 1.0
     */
    List<ValVoucherQuery002Dto> searchList(@Param("busVouchnum") String busVouchnum,
                                           @Param("tableId") String tableId,
                                           @Param("booksetId") String booksetId,
                                           @Param("bookDate") String bookDate,
                                           @Param("subjectNo") String subjectNo);

    /**
     * 根据凭证编号查询业务凭证编号
     * 
     * @param vouchNum
     * @return
     */
	String selectBusVouchNumByVouchNum(@Param("vouchNum") String vouchNum);
}
