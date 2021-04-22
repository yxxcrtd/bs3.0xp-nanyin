package com.joyintech.tams.valuation.common.batch.bookcommon.dao;

import com.joyintech.tams.common.base.dao.BokValTableDataMapper;
import com.joyintech.tams.common.base.entity.BokValTableData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BokValTableDateWriterMapper extends BokValTableDataMapper {

    /**
     * 批量插入
     * @param bokValTableDatas
     * @return
     */
    int insertBatchBokValTableData(List<BokValTableData> bokValTableDatas);

}
