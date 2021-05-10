/*
 * 文件名：ValManualVoucher001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dao;

import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete001Dto;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete001SearchDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 手工凭证查询Mapper
 * @author sunshuaishuai
 * @version 1.0 2021年2月8日
 * @see ValManualVoucherTemplete001Mapper
 * @since 1.0
 */
@Repository(value="serviceValManualVoucherTemplete001Mapper")
public interface ValManualVoucherTemplete001Mapper {
    
    /**
     * 
     * 页面初始化查询列表
     * @param dto 参数
     * @return List<ValManualVoucherTemplete001Dto>
     * @see
     * @since 1.0
     */
    List<ValManualVoucherTemplete001Dto> queryPageList(ValManualVoucherTemplete001SearchDto dto);
}
