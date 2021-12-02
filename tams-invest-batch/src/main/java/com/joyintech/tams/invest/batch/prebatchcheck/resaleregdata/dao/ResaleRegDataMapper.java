package com.joyintech.tams.invest.batch.prebatchcheck.resaleregdata.dao;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ResaleRegDataMapper {
    /**
     * 回售登记撤销后理论行权日前一交易日未重新生成回售登记
     * @param param
     * @return
     */
    List<SysPrebatchResultDetail> listSysPrebatchResultDetail(Map<String, Object> param);

}
