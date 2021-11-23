package com.joyintech.tams.cfets.trdconfirm.dao;


import com.joyintech.tams.business.common.dto.CfetsOprHisInfoRequest;
import com.joyintech.tams.cfets.trdconfirm.dto.*;
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
    /**
     * 更新操作记录
     * @param record
     * @return
     */
    int insertTrdQutoMatchInfo(CfetsTrdConfirmMatchDto record);
    /**
     * 查询所有Cstp下行信息
     * @param pageParam
     * @return
     */
    List<CfetsTrdConfirmCstpInfoListResponse> findAllCstpInfoList(Map<String, Object> pageParam);
    /**
     * 查询所有匹配确认数据
     * @param pageParam
     * @return
     */
    List<CfetsTrdConfirmTrdCstpMatchResponse> findAllTrdCstpInfoList(Map<String, Object> pageParam);
    /**
     * 查询所有匹配确认-执行债券数据
     * @param pageParam
     * @return
     */
    List<CfetsTrdConfirmExecBondInfoResponse> findAllTrdBondList(Map<String, Object> pageParam);
    /**
     * 查询所有匹配确认-执行质押式数据
     * @param pageParam
     * @return
     */
    List<CfetsTrdConfirmExecRepoInfoResponse> findAllTrdRepoList(Map<String, Object> pageParam);
    /**
     * 匹配确认列表-cstp成交债券数据分页查询
     * @param pageParam
     * @return
     */
    List<CfetsTrdConfirmCstpBondInfoResponse> findAllCstpBondList(Map<String, Object> pageParam);
    /**
     * 匹配确认列表-cstp成交质押式分页查询
     * @param pageParam
     * @return
     */
    List<CfetsTrdConfirmCstpRepoInfoResponse> findAllCstpRepoList(Map<String, Object> pageParam);

    /**
     * 根据主键删除匹配关系表
     * @param seqno
     * @return
     */
    int deleteByPrimaryKey(String seqno);

}
