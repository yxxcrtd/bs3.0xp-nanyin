package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.MstBondValuationInfo;

@Repository
public interface ValBondValuationInfoBatchMapper {
    int insertBatch(List<MstBondValuationInfo> list);
    void deleteData(@Param("list") List<MstBondValuationInfo> list);
}