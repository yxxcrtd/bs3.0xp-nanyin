package com.joyintech.service.risk.valtblimport.dao;

import com.joyintech.service.risk.valtblimport.dto.RskValtblAssetSubDto;
import com.joyintech.service.risk.valtblimport.dto.RskValtblAssetSumDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RskValTableQueryMapper {

    List<RskValtblAssetSumDto> queryForPage(Map<String, Object> pageParam);

    List<RskValtblAssetSubDto> selectRskValTableDetaile(Map<String, Object> param);
}
