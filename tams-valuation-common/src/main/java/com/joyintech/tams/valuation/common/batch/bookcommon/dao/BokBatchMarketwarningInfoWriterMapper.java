package com.joyintech.tams.valuation.common.batch.bookcommon.dao;

import com.joyintech.tams.common.base.dao.BokBatchMarketwarningInfoMapper;
import com.joyintech.tams.common.base.entity.BokBatchMarketwarningInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BokBatchMarketwarningInfoWriterMapper extends BokBatchMarketwarningInfoMapper {

    int insertbatch(List<BokBatchMarketwarningInfo> list);

}
