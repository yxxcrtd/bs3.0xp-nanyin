package com.joyintech.service.risk.limitmanagement.rskSpecificAssetType.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.limitmanagement.rskSpecificAssetType.dto.RskSpecificAssetTypeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 特定资产类型设置
 *
 * @author zhangnian
 * @see RskSpecificAssetTypeMapper
 * @since 1.0
 */
@Repository("rskPageSpecificAssetTypeMapper")
public interface RskSpecificAssetTypeMapper {
	
	 /**
     * 查询界面查询事件
     *
     * @param pageParam 页面参数
     * @see
     * @since 1.0
     */
    List<RskSpecificAssetTypeDto> selectForPage(Map<String, Object> pageParam);

    RskSpecificAssetTypeDto getBasicInfo(@Param("specificId") String specificId);

    List<SelectOptionDto> getAssetIdOption(Map<String, Object> map);

    int getRepeatCount(Map<String,Object> param);
    

}
