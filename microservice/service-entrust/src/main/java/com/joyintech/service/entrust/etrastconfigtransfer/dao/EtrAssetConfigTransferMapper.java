package com.joyintech.service.entrust.etrastconfigtransfer.dao;

import com.joyintech.service.entrust.etrastconfigtransfer.dto.EtrAssetConfigDto;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * 资产配置快速转仓指令
 * 产品信息查询
 * @author 裴家俊
 * @version 2021年7月20日
 * @see EtrAssetConfigTransferMapper
 * @since 1.0
 */
@Component
public interface EtrAssetConfigTransferMapper {

    /**
     * 查询产品基本信息
      * @param param
     * @return
     */
    List<PtlPortfolio> selectForPage(Map<String, Object> param);

    /**
     * 查询产品持有(暂时查债券)的实际持仓
     * @param param
     * @return
     */
    List<EtrAssetConfigDto> getAstPosition(Map<String, Object> param);

}
