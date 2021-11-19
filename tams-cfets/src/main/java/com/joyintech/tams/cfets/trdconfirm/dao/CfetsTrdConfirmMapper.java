package com.joyintech.tams.cfets.trdconfirm.dao;


import com.joyintech.tams.cfets.trdconfirm.dto.CfetsRegFinProductDealDto;
import com.joyintech.tams.cfets.trdconfirm.dto.CfetsTrdConfirmRegFeeInfoDto;
import com.joyintech.tams.cfets.trdconfirm.dto.CfetsTrdConfirmRegInfoListResponse;
import com.joyintech.tams.common.base.entity.RegFinProductDeal;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 银行间交易成交确认
 * @author
 * @version 1.0 2021年10月26日
 * @see CfetsTrdConfirmMapper
 * @since 1.0
 */
@Repository
public interface CfetsTrdConfirmMapper {
    /**
     * 查询所有登记信息
     * @param pageParam
     * @return
     */
    List<CfetsTrdConfirmRegInfoListResponse> findAllRegInfoList(Map<String, Object> pageParam);

    /**
     * 根据主键查询登记信息
     * @param pageParam
     * @return
     */
    CfetsTrdConfirmRegInfoListResponse selectRegInfoByKey(String totleTradeId);
    /**
     * 查询所有登记费用信息
     * @param pageParam
     * @return
     */
    List<CfetsTrdConfirmRegFeeInfoDto> findAllRegFeeInfoList(String tradeId);

    /**
     * 查询所有登记明细数据
     * @param pageParam
     * @return
     */

    List<CfetsRegFinProductDealDto> findAllRegDealList(String totleTradeId);

    /**
     * 查询所有登记明细费用信息
     * @param pageParam
     * @return
     */
    List<CfetsTrdConfirmRegFeeInfoDto> findAllRegFeeDetailInfoList(String tradeId);

    /**
     * 检索账户来源
     * @param param
     * @return
     */
    String selectSecAcctSource(Map<String, Object> param);

}
