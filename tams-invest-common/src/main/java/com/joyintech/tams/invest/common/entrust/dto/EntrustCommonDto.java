package com.joyintech.tams.invest.common.entrust.dto;

import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;

/**
 * 委托共通dto
 */
public class EntrustCommonDto {
    /**
     * 委托信息
     */
    private EtrEntrust etrEntrust;
    /**
     * 委托附加信息
     */
    private EtrEntrustAdd etrEntrustAdd;

    private BussRepoSecDto bussRepoSecDto;

    public BussRepoSecDto getBussRepoSecDto() {
        return bussRepoSecDto;
    }

    public void setBussRepoSecDto(BussRepoSecDto bussRepoSecDto) {
        this.bussRepoSecDto = bussRepoSecDto;
    }

    public EtrEntrust getEtrEntrust() {
        return etrEntrust;
    }

    public void setEtrEntrust(EtrEntrust etrEntrust) {
        this.etrEntrust = etrEntrust;
    }

    public EtrEntrustAdd getEtrEntrustAdd() {
        return etrEntrustAdd;
    }

    public void setEtrEntrustAdd(EtrEntrustAdd etrEntrustAdd) {
        this.etrEntrustAdd = etrEntrustAdd;
    }
}
