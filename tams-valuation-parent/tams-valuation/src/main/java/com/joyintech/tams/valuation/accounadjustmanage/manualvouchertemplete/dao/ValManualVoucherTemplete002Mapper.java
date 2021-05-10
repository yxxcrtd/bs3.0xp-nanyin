/*
 * 文件名：ValManualVoucher002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dao;

import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete002Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 手工凭证模板详情查看Mapper 〈功能详细描述〉
 * 
 * @author sunshuaishuai
 * @version 1.0 2021年2月8日
 * @see ValManualVoucherTemplete002Mapper
 * @since 1.0
 */
@Repository
public interface ValManualVoucherTemplete002Mapper {

    /**
     * 根据模板编号tmpCode查询对应编号的所有手工凭证模板信息
     * 
     * @param tmpCode 手工凭证编号
     * @return List<ValManualVoucherTemplete002Dto>
     * @see
     * @since 1.0
     */
    List<ValManualVoucherTemplete002Dto> searchList(@Param("tmpCode") String tmpCode);

}
