package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.joyintech.tams.common.base.entity.MstFundMarketInfo;

@Repository
public interface ValFundMarketInfoBatchMapper {
    int insertBatch(List<MstFundMarketInfo> list);
    void deleteData(@Param("list") List<MstFundMarketInfo> list);
}