package com.joyintech.service.valuation.trdassetlossmanage.finproductlossstage.dao;


import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.trdassetlossmanage.finproductlossstage.dto.FinProductLossStage001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FinProductLossStage001Mapper {
    List<FinProductLossStage001Dto> queryPageList(Map<String, Object> params);
    List<SelectOptionDto> getFinprodName(Map<String, Object> params);
    List<FinProductLossStage001Dto> getPrevValidObj(FinProductLossStage001Dto dto);
    List<FinProductLossStage001Dto> getNextValidObj(FinProductLossStage001Dto dto);
    FinProductLossStage001Dto getBooksetDate(FinProductLossStage001Dto dto);
    int saveFinProLosSta(FinProductLossStage001Dto dto);
    int updateInvalidDate(FinProductLossStage001Dto dto);
    int deleteFinProLosSta(FinProductLossStage001Dto dto);
    int editFinProLosSta(FinProductLossStage001Dto dto);
}
