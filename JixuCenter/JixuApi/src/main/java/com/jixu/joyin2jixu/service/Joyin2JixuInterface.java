package com.jixu.joyin2jixu.service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.ResultInterface;
import com.jixu.msg.dto.JJBkCashAcctDto;
import com.jixu.msg.dto.JJPortfolioDto;
import com.joyintech.framework.select.SelectOptionDto;

import java.util.List;

public interface Joyin2JixuInterface {
    ResultContainer executeJixuMessage(String jsonMsg);

    ResultContainer executeJixuMessageNoTx(String jsonMsg);

    ResultInterface createPortfolios(List<JJPortfolioDto> dtoList);

    ResultInterface updateBkCashAccts(List<JJBkCashAcctDto> dtoList);

    List<SelectOptionDto> getSecurityDtoByAssetType(String... assetType);
}
