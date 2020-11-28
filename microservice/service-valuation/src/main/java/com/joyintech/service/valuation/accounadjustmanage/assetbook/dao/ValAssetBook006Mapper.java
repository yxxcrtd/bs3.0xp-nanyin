package com.joyintech.service.valuation.accounadjustmanage.assetbook.dao;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.dto.ValAssetBook006Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository(value="serviceValAssetBook006Mapper")
public interface ValAssetBook006Mapper {

    List<SuggestDto> getParamCode(Map<String, String> pageParam);

    List<ValAssetBook006Dto> findPage(Map<String, Object> map);

    void updateParamValue(ValAssetBook006Dto dto);
}
