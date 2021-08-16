package com.joyintech.service.riskrds.limitmanagement.bankasset.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.riskrds.limitmanagement.bankasset.dto.BankAssetDto;


/**
 * 商业银行净资产维护
 * @author libaisong
 * @version 1.0 2020年12月29日
 * @see BankAssetMapper
 * @since 1.0
 */
@Repository
public interface BankAssetMapper {

    /**
     * 查询界面查询事件
     *
     * @param pageParam 页面参数
     * @see
     * @since 1.0
     */
    List<BankAssetDto> selectForPage(Map<String, Object> pageParam);
    
    List<SuggestDto> selectCustomerId(Map<String, Object> pageParam);
    
    BankAssetDto selectBankAsset(Map<String, Object> pageParam);
    
    Date selectMaxBankAsset(Map<String, Object> pageParam);
    
    List<SuggestDto> getCustomerIdList(Map<String, Object> map);
    
    int updateBankAsset(BankAssetDto dto);
    
    int selectSeqno();
    
    List<BankAssetDto> selectBankAssetByInsitituteCode(String insitituteCode);
    List<BankAssetDto> selectBankAssetByInsitituteCodeTmp(String insitituteCode);
}
