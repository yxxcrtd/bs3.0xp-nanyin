package com.joyintech.service.basicdata.stlsecreconpara.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.stlsecreconpara.dto.StlSecReconParaDto;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface StlSecReconParaPageMapper {

    /**
     * 画面分页查询
     * @param pageParam
     * @return
     */
    public List<StlSecReconParaDto> selectForPage(Map<String, Object> pageParam);

    /**
     * 查询券商ID的下拉列表
     * @param paramMap
     * @return
     */
    List<SuggestDto> findAllBrokerIds(Map<String, Object> paramMap);

    /**
     * 查询券商ID的下拉列表
     * @param paramMap
     * @return
     */
    List<SelectOptionDto> findAllBrokerIdsSelect(Map<String, Object> paramMap);

    Integer checkParaIsExist(Map<String, Object> para);
}
