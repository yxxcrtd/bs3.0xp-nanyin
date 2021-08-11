package com.joyintech.tams.invest.batch.creditoccup.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

import java.util.List;

/**
 * write结果
 * @author zznn
 * @date 2021/8/10
 */
public class CreditOccupResult extends NeedToWriteEntity {

    private List<CreditUsingDto> CreditUsings;

    public List<CreditUsingDto> getCreditUsings() {
        return CreditUsings;
    }

    public void setCreditUsings(List<CreditUsingDto> creditUsings) {
        CreditUsings = creditUsings;
    }
}

