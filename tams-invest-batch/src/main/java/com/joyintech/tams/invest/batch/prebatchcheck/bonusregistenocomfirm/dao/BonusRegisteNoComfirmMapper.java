package com.joyintech.tams.invest.batch.prebatchcheck.bonusregistenocomfirm.dao;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BonusRegisteNoComfirmMapper {
    List<SysPrebatchResultDetail> selProductRegisteNoconfirm(Map<String, Object> param);
}
