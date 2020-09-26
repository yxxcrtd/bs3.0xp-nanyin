/*
 * 文件名：taxInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huwei
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.taxinfo.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.taxinfo.dto.TaxInfoDto;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendReaderEntity;

/**
 * 增值税缴纳Mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年11月19日
 * @see TaxInfoMapper
 * @since 1.0
 */
@Repository
public interface TaxInfoMapper {
    /**
     * 查询方法
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<TaxInfoDto> queryTaxData(SendReaderEntity item);
}
