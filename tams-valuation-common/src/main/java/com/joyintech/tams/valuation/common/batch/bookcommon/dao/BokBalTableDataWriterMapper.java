package com.joyintech.tams.valuation.common.batch.bookcommon.dao;

import com.joyintech.tams.common.base.dao.BokBalTableDataMapper;
import com.joyintech.tams.common.base.entity.BokBalTableData;
import com.joyintech.tams.common.base.entity.BokValTableData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BokBalTableDataWriterMapper extends BokBalTableDataMapper {


    /**
     * 批量插入
     * @param bokBalTableDatas
     * @return
     */
    int insertBatchBokBalTableData(List<BokBalTableData> bokBalTableDatas);

}
