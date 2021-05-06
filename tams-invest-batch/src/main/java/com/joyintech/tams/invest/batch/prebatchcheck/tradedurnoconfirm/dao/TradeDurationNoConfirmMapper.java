package com.joyintech.tams.invest.batch.prebatchcheck.tradedurnoconfirm.dao;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TradeDurationNoConfirmMapper {

    List<SysPrebatchResultDetail> selectZQDurationList(Map<String, Object> param);

    List<SysPrebatchResultDetail> selectZYSHGDurationList(Map<String, Object> param);

    List<SysPrebatchResultDetail> selectCKDurationList(Map<String, Object> param);

    List<SysPrebatchResultDetail> selectCJDurationList(Map<String, Object> param);

    List<SysPrebatchResultDetail> selectJHLLLXDurationList(Map<String, Object> param);

    List<SysPrebatchResultDetail> selectMDSHGDurationList(Map<String, Object> param);

    List<SysPrebatchResultDetail> selectXMLDurationList(Map<String, Object> param);

    List<SysPrebatchResultDetail> selectZQJDDurationList(Map<String, Object> param);

    List<SysPrebatchResultDetail> selectJZXJHJJTradeList(Map<String, Object> param);
}
