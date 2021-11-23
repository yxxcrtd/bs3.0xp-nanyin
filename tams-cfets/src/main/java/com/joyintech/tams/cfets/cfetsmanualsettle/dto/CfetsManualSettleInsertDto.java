package com.joyintech.tams.cfets.cfetsmanualsettle.dto;

import com.joyintech.tams.common.base.entity.TrdBankManualSetDetail;
import com.joyintech.tams.common.base.entity.TrdBankManualSettleFlow;
import com.joyintech.tams.common.base.entity.TrdBankManualSettleInfo;

import java.util.List;

public class CfetsManualSettleInsertDto {

    private TrdBankManualSettleInfo trdBankManualSettleInfo;

    private TrdBankManualSettleFlow trdBankManualSettleFlow;

    private List<TrdBankManualSetDetail> listTrdBnakSetDetails;

    public TrdBankManualSettleInfo getTrdBankManualSettleInfo() {
        return trdBankManualSettleInfo;
    }

    public void setTrdBankManualSettleInfo(TrdBankManualSettleInfo trdBankManualSettleInfo) {
        this.trdBankManualSettleInfo = trdBankManualSettleInfo;
    }

    public TrdBankManualSettleFlow getTrdBankManualSettleFlow() {
        return trdBankManualSettleFlow;
    }

    public void setTrdBankManualSettleFlow(TrdBankManualSettleFlow trdBankManualSettleFlow) {
        this.trdBankManualSettleFlow = trdBankManualSettleFlow;
    }

    public List<TrdBankManualSetDetail> getListTrdBnakSetDetails() {
        return listTrdBnakSetDetails;
    }

    public void setListTrdBnakSetDetails(List<TrdBankManualSetDetail> listTrdBnakSetDetails) {
        this.listTrdBnakSetDetails = listTrdBnakSetDetails;
    }
}
