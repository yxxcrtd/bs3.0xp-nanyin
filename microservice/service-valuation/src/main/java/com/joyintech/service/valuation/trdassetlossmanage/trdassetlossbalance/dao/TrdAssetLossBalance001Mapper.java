package com.joyintech.service.valuation.trdassetlossmanage.trdassetlossbalance.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.trdassetlossmanage.trdassetlossbalance.dto.TrdAssetLossBalance001Dto;
import com.joyintech.service.valuation.trdassetlossmanage.trdassetlossbalance.dto.TrdAssetLossBalance002Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TrdAssetLossBalance001Mapper {
    List<TrdAssetLossBalance001Dto> queryPageList(Map<String, Object> params);
    List<TrdAssetLossBalance002Dto> queryDetailList(Map<String, Object> params);
    List<SelectOptionDto> getFinprodId(Map<String, Object> params);
    List<SelectOptionDto> getBooksetId(Map<String, Object> params);
}
