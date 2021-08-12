package com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dao;

import com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dto.FinProductDto;
import com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dto.ValBalanceStatementQuery002Dto;
import com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dto.ValBalanceStatementQuery003Dto;
import com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dto.ValBalanceStatementQuery004Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository(value="serviceValBalanceStatementQuery002Mapper")
public interface ValBalanceStatementQuery002Mapper {
    /**
     * 查询余额流水信息
     *
     * @param pageParam
     * @return
     */
    List<ValBalanceStatementQuery002Dto> selectAll(Map<String, Object> pageParam);

    /**
     * 查询凭证信息
     *
     * @param params
     * @return
     */
    List<ValBalanceStatementQuery003Dto> selectForVouch(Map<String, Object> params);

    /**
     * 查询交易信息
     *
     * @param params
     * @return
     */
    List<ValBalanceStatementQuery004Dto> selectForTrade(Map<String, Object> params);

    /**
     * 根据账套查询对应信息
     *
     * @param params
     * @return
     */
    FinProductDto getFinprodByBoksetId(Map<String, Object> params);
}
