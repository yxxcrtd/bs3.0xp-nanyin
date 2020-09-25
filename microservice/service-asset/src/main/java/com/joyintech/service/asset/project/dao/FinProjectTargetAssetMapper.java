/*
 * 文件名：FinPorjectTargetAssetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.tams.common.base.entity.PaFinProductAssetAttach;
import com.joyintech.service.asset.project.dto.FinProjectTargetAssetCmsDto;
import com.joyintech.service.asset.project.dto.FinProjectTargetAssetDto;

/**
 * 项目类资产标的信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月24日
 * @see FinPorjectTargetAssetMapper
 * @since 1.0
 */
@Repository
public interface FinProjectTargetAssetMapper {

    List<FinProjectTargetAssetDto> queryForPage(Map<String, Object> pageParam);
    
    FinProjectTargetAssetDto getBasicInfo(Map<String,Object> param);
    
    FinProjectTargetAssetCmsDto getCmsInfoBycashOutNo(@RequestBody Map<String, Object> param);
    
    List<PaFinProductAssetAttach> getBusinessIdByFinprodId(@Param("finprodId")String finprodId);
    
    void deleteAssetAttachNoByFinprodId(@Param("finprodId")String finprodId);

    String checkMarketExists(Map<String, Object> map);
}
