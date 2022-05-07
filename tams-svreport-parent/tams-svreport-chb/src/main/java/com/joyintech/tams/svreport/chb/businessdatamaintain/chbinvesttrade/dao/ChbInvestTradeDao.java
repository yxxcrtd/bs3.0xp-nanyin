package com.joyintech.tams.svreport.chb.businessdatamaintain.chbinvesttrade.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbboksubject.dto.ChbBokSubjectDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbinvesttrade.dto.ChbInvestTradeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wubingbing
 * @date 2022-04-27 16:37
 */
@Repository
public interface ChbInvestTradeDao {
    List<SelectOptionDto> getProdName(Map<String, Object> param);

    List<SelectOptionDto> getRegCode(Map<String, Object> param);

    List<SelectOptionDto> getCustomerName(Map<String, Object> param);

    List<SelectOptionDto> getAssetCode(Map<String, Object> param);

    Map<String,String> selectProdIdAndRegCodeByProdName(String prodName);

    ChbInvestTradeDto selectByKey(@Param("tradeCode") String tradeCode,@Param("prodId") String prodId, @Param("registBankCode") String registBankCode, @Param("assetCode") String assetCode);

    Map<String,String> selectAssetNameByAssetCode(String assetCode);

    void update(ChbInvestTradeDto entity);

    void insert(ChbInvestTradeDto entity);

    List<ChbInvestTradeDto> selectAll(Map<String, Object> pageParam);
}
