package com.joyintech.tams.valuation.common.batch.bookcommon.dao;

import com.joyintech.tams.common.base.dao.BokAssetMarketInfoMapper;
import com.joyintech.tams.common.base.dao.BokBatchMarketwarningInfoMapper;
import com.joyintech.tams.common.base.entity.BokAssetMarketInfo;
import com.joyintech.tams.common.base.entity.BokBatchMarketwarningInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BokAssetMarketInfoWriterMapper extends BokAssetMarketInfoMapper {

    int insertbatch(List<BokAssetMarketInfo> list);

}
