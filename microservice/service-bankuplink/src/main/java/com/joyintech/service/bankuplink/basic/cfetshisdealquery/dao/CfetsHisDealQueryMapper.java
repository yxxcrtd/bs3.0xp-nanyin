package com.joyintech.service.bankuplink.basic.cfetshisdealquery.dao;

import com.joyintech.framework.select.SuggestDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CfetsHisDealQueryMapper {

    /**
     * 查询报价编号list
     * @param map
     * @return
     */
    List<SuggestDto> queryQuoteIdList(Map<String, Object> map);

}
