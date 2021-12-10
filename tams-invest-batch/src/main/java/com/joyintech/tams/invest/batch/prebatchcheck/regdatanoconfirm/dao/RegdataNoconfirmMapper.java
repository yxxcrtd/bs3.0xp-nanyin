package com.joyintech.tams.invest.batch.prebatchcheck.regdatanoconfirm.dao;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RegdataNoconfirmMapper {

    List<SysPrebatchResultDetail> listSysPrebatchResultDetail(Map<String, Object> param);

}
