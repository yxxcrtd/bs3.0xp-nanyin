/*
 * 文件名：ValRiftMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.batch.valbatchimport.dao;
import java.util.List;
import java.util.Map;


import com.joyintech.tams.common.base.entity.RskValtblSubject;
import com.joyintech.tams.invest.batch.valbatchimport.dto.RskValStructureDto;
import com.joyintech.tams.invest.batch.valbatchimport.dto.RskValSumnameDto;
import com.joyintech.tams.invest.batch.valbatchimport.dto.RskValtblAssetSubDto;
import com.joyintech.tams.invest.batch.valbatchimport.dto.RskValtblAssetSubMapDtlDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *  估值穿透
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月25日
 * @see ValBatchImportMapper
 * @since 1.0
 */
@Repository
public interface ValBatchImportMapper {

    List<String> selectPortfolioId(Map<String, Object> param);

    Map<String,String> selectFinProductByFinprodMarketId(Map<String, Object> param);

    String selectGroupName(String ptlId);

    List<RskValStructureDto> selectForList(String structId);

    List<RskValtblAssetSubMapDtlDto> selectDtlList(@Param("id") String id);

    List<RskValSumnameDto> selectSumForList(@Param("sumId") String sumId);

    List<RskValtblSubject> selecctRskValtblSubjectCodeList(@Param("subType") String subType);

    void deleteValTable(RskValtblAssetSubDto dto);

    void deleteSumValTable(RskValtblAssetSubDto dto);

    List<String> queryorgCodeCount(@Param("orgCode") String orgCode);
}
