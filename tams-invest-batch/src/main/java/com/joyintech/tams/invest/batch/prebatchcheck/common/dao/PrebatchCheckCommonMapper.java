package com.joyintech.tams.invest.batch.prebatchcheck.common.dao;

import com.joyintech.tams.invest.batch.prebatchcheck.common.dto.SysPrebatchResultDto;
import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrebatchCheckCommonMapper {

    void updateSysPrebatchResult(SysPrebatchResultDto dto);

    SysPrebatchResultDto selectSysPrebatchResult(SysPrebatchResultDto paramdto);

    void insertBatchResultDetail(@Param("insertDataList")List<SysPrebatchResultDetail> details);
}
