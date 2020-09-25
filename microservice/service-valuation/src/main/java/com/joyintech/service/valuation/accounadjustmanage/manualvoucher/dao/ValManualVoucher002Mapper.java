/*
 * 文件名：ValManualVoucher002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher002Dto;

/**
 * 手工凭证详情查看Mapper 〈功能详细描述〉
 * 
 * @author qiangchengxi
 * @version 1.0 2018年5月5日
 * @see ValManualVoucher002Mapper
 * @since 1.0
 */
@Repository(value="serviceValManualVoucher002Mapper")
public interface ValManualVoucher002Mapper {

    /**
     * 根据手工凭证编号bookId查询对应编号的所有手工凭证信息
     * 
     * @param bookId 手工凭证编号
     * @return List<ValManualVoucher002Dto>
     * @see
     * @since 1.0
     */
    List<ValManualVoucher002Dto> searchList(@Param("tradeId") String tradeId);

}
