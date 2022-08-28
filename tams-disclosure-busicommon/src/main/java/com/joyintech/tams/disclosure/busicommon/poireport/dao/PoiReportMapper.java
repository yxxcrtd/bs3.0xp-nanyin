/*
 * 文件名：ValFinprodTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.busicommon.poireport.dao;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepDataCheck;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.RepReportCheck;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.RepRuleEntity;

/**
 * 报表查询
 * @author leipeng
 * @version 1.0 2018年8月2日
 * @see PoiReportMapper
 * @since 1.0
 */
@Repository
public interface PoiReportMapper {
    
    /**
     * 
     * 获取规则信息
     * @return RepRule
     * @see
     * @since 1.0
     */
    List<RepRuleEntity> getRuleInfo(@Param("repId")String repId);

    int checkBaseDate(@Param("prodId")String prodId,@Param("baseDate")String baseDate);

    int deleteCheckData(RepReportCheck repReportCheck);

    List<RepDataCheck> getRepDataCheckList();

    RepProdInfo getProdInfo(@Param("prodId")String prodId);

    RepProdInfo getVProdInfo(@Param("prodId")String prodId);
}
