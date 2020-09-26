package com.joyintech.tams.valuation.batch.tafloatfeeaccounts.dao;

import com.joyintech.tams.valuation.batch.tafloatfeeaccounts.entity.TaFloadFeeAccountsDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yanya
 * @create 2019-12-12 10:51
 */
@Repository
public interface TaFloadFeeAccountsMapper {

    /**
     * 获取存款类资产计提账务待处理交易数据
     * @param param
     * @return
     */
    public List<TaFloadFeeAccountsDto> selectBokBookdetailCurrent(Map<String, Object> param);

}
