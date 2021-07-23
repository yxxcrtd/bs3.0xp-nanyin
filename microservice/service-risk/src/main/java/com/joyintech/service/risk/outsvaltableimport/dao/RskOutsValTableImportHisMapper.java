package com.joyintech.service.risk.outsvaltableimport.dao;

import com.joyintech.service.risk.outsvaltableimport.dto.RskOutValtblImportHisDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface RskOutsValTableImportHisMapper {

    /**
     * <一句话功能简述> 页面查询
     *
     * @param param
     * @return 查询结果
     * @see
     * @since 1.0
     */
    List<RskOutValtblImportHisDto> selectForPage(Map<String, Object> param);

    Map<String,String> selectPortfolioId(Map<String, Object> param);

    Map<String,String> selectFinProductByFinprodMarketId(Map<String, Object> param);

    String selectGroupName(String ptlId);

    int queryorgCodeCount(@Param("orgCode") String orgCode);

    List<String> selectAssetSubMapId();

}