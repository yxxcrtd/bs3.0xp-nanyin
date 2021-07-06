package com.jixu.joyin2jixu.service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.ResultInterface;
import com.jixu.msg.dto.JJBkCashAcctDto;
import com.jixu.msg.dto.JJPortfolioDto;

import java.util.List;

public interface Joyin2JixuInterface {

    ResultContainer executeJixuMessageNoTx(String jsonMsg);

    ResultInterface createPortfolios(List<JJPortfolioDto> dtoList);

    ResultInterface updateBkCashAccts(List<JJBkCashAcctDto> dtoList);
}
