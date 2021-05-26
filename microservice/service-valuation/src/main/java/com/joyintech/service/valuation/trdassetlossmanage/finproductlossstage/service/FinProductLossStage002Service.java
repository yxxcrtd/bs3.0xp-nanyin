package com.joyintech.service.valuation.trdassetlossmanage.finproductlossstage.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.trdassetlossmanage.finproductlossstage.dao.FinProductLossStage001Mapper;
import com.joyintech.service.valuation.trdassetlossmanage.finproductlossstage.dto.FinProductLossStage001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="serviceFinProductLossStage002Service")
public class FinProductLossStage002Service extends CommonExportService<FinProductLossStage001Dto> {

    @Autowired
    private FinProductLossStage001Mapper finProductLossStage001Mapper;

    /**
     * 插入数据
     */
    public int saveFinProLosSta(FinProductLossStage001Dto dto){
        return finProductLossStage001Mapper.saveFinProLosSta(dto);
    }

    /**
     * 更新失效日期
     */
    public int updateInvalidDate(FinProductLossStage001Dto dto){
        return finProductLossStage001Mapper.updateInvalidDate(dto);
    }

    /**
     * 编辑
     */
    public int editFinProLosSta(FinProductLossStage001Dto dto){
        return finProductLossStage001Mapper.editFinProLosSta(dto);
    }
}
