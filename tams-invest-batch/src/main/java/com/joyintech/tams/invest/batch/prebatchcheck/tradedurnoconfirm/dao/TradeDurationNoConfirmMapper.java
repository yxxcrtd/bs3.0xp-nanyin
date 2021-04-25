package com.joyintech.tams.invest.batch.prebatchcheck.tradedurnoconfirm.dao;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TradeDurationNoConfirmMapper {

    List<SysPrebatchResultDetail> selectZQDurationList(Map<String, Object> param);


}
