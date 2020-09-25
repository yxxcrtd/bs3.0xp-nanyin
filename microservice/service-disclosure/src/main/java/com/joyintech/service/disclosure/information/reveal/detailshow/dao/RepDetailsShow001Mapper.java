/*
 * 文件名：ValFinprodTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.reveal.detailshow.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.disclosure.information.reveal.detailshow.dto.ProdInfoDto;
import com.joyintech.service.disclosure.information.reveal.detailshow.dto.RepDetailsShow001Dto;
import com.joyintech.tams.common.base.entity.RepJobRange;
import com.joyintech.tams.common.base.entity.RepScreen;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.ProdInfoListEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.RepRuleEntity;

/**
 * 报表查询
 * @author leipeng
 * @version 1.0 2018年8月2日
 * @see RepDetailsShow001Mapper
 * @since 1.0
 */
@Repository(value="serviceRepDetailsShow001Mapper")
public interface RepDetailsShow001Mapper {
    
    
    /**
     * 
     * 获取指定报表的查询条件
     * <功能详细描述>
     * @param repId
     * @return
     * @see
     * @since 1.0
     */
    List<RepScreen> getRepScreenList(String repId);
    
    
    
    RepDetailsShow001Dto getInfoByRepId(String repId);
    
    
    
    /**
     * 
     * 获取指定报表的查询条件
     * <功能详细描述>
     * @param repId
     * @return
     * @see
     * @since 1.0
     */
    List<RepDetailsShow001Dto> getRepInfoListByPid(String pid);
    
    /**
     * 
     * 获取产品信息
     * <功能详细描述>
     * @return ProdInfoDto
     * @see
     * @since 1.0
     */
    List<ProdInfoDto>getProdInfo();
    
    /**
     * 
     * 系统生成时查询相同的报表
     * @return count
     * @see
     * @since 1.0
     */
    int querySameCount(Map<String, Object>params);
    
    /**
     *
     * 删除相同的报表
     * @return count
     * @see
     * @since 1.0
     */
    int updateDataStatus(Map<String, Object>params);
    
    /**
     * 
     * 获取产品信息
     * @return ProdInfoListEntity
     * @see
     * @since 1.0
     */
    ProdInfoListEntity getProdList(@Param("prodId")String prodId);
    
    /**
     * 
     * 获取规则信息
     * @return RepRule
     * @see
     * @since 1.0
     */
    List<RepRuleEntity> getRuleInfo(@Param("repId")String repId);
    
    /**
     * 
     * 获取校验详细信息
     * @return errormsg
     * @see
     * @since 1.0
     */
    List<String> getErrorMsg(@Param("rep_id")String rep_id);
    /**
     * 
     * 删除校验详细信息
     * @return count
     * @see
     * @since 1.0
     */
    int deleteErrorMsg(@Param("rep_id")String rep_id);
    /**
     * 获取产品名称的全称
     */
    String getProdName(@Param("prod_id")String prod_id);
    /**
     * 
     * 获取产品规则信息
     * @return RepRule
     * @see
     * @since 1.0
     */
    List<RepJobRange> getRepJobRangeInfo(@Param("repId")String repId);
}
