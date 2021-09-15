package com.joyintech.tams.invest.batch.prebatchcheck.productduenoconfirm.dao;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductDueNoConfirmMapper {
    List<SysPrebatchResultDetail> selectProductListSY(Map<String, Object> param);

    List<SysPrebatchResultDetail> selectProductListJZ(Map<String, Object> param);
}
