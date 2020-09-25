package com.joyintech.service.risk.namelistmanage.assetnamelist.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.namelistmanage.assetnamelist.dto.RskAccessListDetailDto;
import com.joyintech.service.risk.namelistmanage.assetnamelist.dto.RskAccessListDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;



/**
 * 名单管理--资产名单管理
 * @author 张年
 * @version 1.0 2019年07月11日
 * @see AssetNameManagementMapper
 * @since 1.0
 */
@Repository
public interface AssetNameManagementMapper {

    List<RskAccessListDto> queryForPage(Map<String, Object> pageParam);

    List<RskAccessListDetailDto> queryDetailForPage(Map<String, Object> pageParam);

    List<SelectOptionDto> selectListId(Map<String, Object> param);
    
}
