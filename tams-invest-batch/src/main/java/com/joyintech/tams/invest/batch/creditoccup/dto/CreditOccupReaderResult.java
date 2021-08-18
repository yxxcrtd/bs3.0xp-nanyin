package com.joyintech.tams.invest.batch.creditoccup.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesInputDto;

import java.util.List;

/**
 * read结果
 * @author zznn
 * @date 2021/8/10
 */
public class CreditOccupReaderResult extends NeedOperateEntity {

    private List<CreditUsingDto> creditUsings;

    List<CreditLinesInputDto> creditLinesInputs;

    public List<CreditUsingDto> getCreditUsings() {
        return creditUsings;
    }

    public void setCreditUsings(List<CreditUsingDto> creditUsings) {
        this.creditUsings = creditUsings;
    }

    public List<CreditLinesInputDto> getCreditLinesInputs() {
        return creditLinesInputs;
    }

    public void setCreditLinesInputs(List<CreditLinesInputDto> creditLinesInputs) {
        this.creditLinesInputs = creditLinesInputs;
    }
}

