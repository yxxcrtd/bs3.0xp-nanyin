/*
 * 文件名：ValAssetBookParameter001Mapper.java
 * 描述：账套参数管理
 * 修改人：tanxuan
 * 修改时间：2019年11月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.assetbookparameter.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.accounadjustmanage.assetbookparameter.dto.ValAssetBookParameter002Dto;

@Repository(value="serviceValAssetBookParameter001Mapper")
public interface ValAssetBookParameter001Mapper {
    
    /**
     * 获取查询结果
     * @param booksetId
     * @return
     */
    List<ValAssetBookParameter002Dto> getBookSetExt(Map<String,Object> params);

    /**
     * 账套代码
     * @param pageParam
     * @return
     */
    List<SelectOptionDto> getBooksetIdForSelect(Map<String, Object> pageParam);
}
