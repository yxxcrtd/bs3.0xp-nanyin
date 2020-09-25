package com.joyintech.service.entrust.etrstandardbond.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.workflow.engine.dto.ProcessEntity;

public class EtrStandardBondInDto extends EtrStandardBondSearchDto implements ProcessEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 934652225044201805L;
    
    /**
     * 质押券列表信息
     */
    List<EtrStandardBondInDto> pledgeBondList = new ArrayList<>();

    /**
     * 可质押券列表信息
     */
    List<EtrStandardBondInDto> avaPledgeBondList;

    
    public List<EtrStandardBondInDto> getPledgeBondList() {
        return pledgeBondList;
    }

    
    public void setPledgeBondList(List<EtrStandardBondInDto> pledgeBondList) {
        this.pledgeBondList = pledgeBondList;
    }

    
    public List<EtrStandardBondInDto> getAvaPledgeBondList() {
        return avaPledgeBondList;
    }

    
    public void setAvaPledgeBondList(List<EtrStandardBondInDto> avaPledgeBondList) {
        this.avaPledgeBondList = avaPledgeBondList;
    }
    
}
