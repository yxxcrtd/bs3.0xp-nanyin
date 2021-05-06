package com.joyintech.tams.invest.batch.prebatchcheck.processproduct.dao;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProcessProductMapper {

    List<SysPrebatchResultDetail> selectProcessProductList(Map<String,Object> map);

}
