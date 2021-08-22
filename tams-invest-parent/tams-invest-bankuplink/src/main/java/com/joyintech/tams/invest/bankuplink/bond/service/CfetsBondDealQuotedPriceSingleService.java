/*
 * 文件名：EtrIbBondinfo002Service.java
 */

package com.joyintech.tams.invest.bankuplink.bond.service;


import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.bankuplink.bond.dto.BondQuoteInfoDto;
import com.joyintech.tams.invest.bankuplink.bond.dto.CfetsBondDealDialogueQuoteDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 债券投资委托画面端service 〈功能详细描述〉
 */
@Service
public class CfetsBondDealQuotedPriceSingleService extends CommonExportService<BondQuoteInfoDto> implements BaseProcessService<CfetsBondDealDialogueQuoteDto> {


    @Override
    public void doComplete(CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuoteDto, OperationType operationType) {

    }

    /**
     * 获取新建界面的资产代码联想下拉框框
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getfinprodIdForAdd(Map<String, Object> param) {
        return restTemplate.postForPage(ServiceApplication.BANKUPLINK_SERVICE_NAME
                        + "/CfetsBondDealQuotedPrice/getfinprodIdForAdd"
                , param,
                SelectOptionDto.class);
    }


}
