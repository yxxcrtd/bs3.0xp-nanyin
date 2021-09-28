package com.joyintech.service.valuation.common.lossmodcommon.dao;

import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author tanxuan
 * @createTime 2019-10-10
 */
@Repository("serviceLossModCommonMapper")
public interface LossModCommonMapper {

    /**
     * 查询资产代码下拉框
     * @param params
     * @return
     */
    List<SelectOptionDto> getAssetInfoOption(Map<String, Object> params);
    /**
     * 减值模型普华方案查询资产代码下拉框
     * @param params
     * @return
     */
    List<SelectOptionDto> getModAssetInfoOptionPH(Map<String, Object> params);

    /**
     * 减值模型毕马威方案查询资产代码下拉框
     * @param params
     * @return
     */
    List<SelectOptionDto> getModAssetInfoOptionBMW(Map<String, Object> params);

    /**
     * 减值模型产品信息下拉框
     * @param params
     * @return
     */
    List<SelectOptionDto> getModProInfoPage(Map<String, Object> params);

    /**
     * 减值模型业务编号下拉框
     * @param params
     * @return
     */
    List<SelectOptionDto> getBusiNumPage(Map<String, Object> params);



}
