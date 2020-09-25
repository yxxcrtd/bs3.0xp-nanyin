package com.joyintech.interf.bankname.cfetsup.secrequestquote.dao;


import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixaccountcfets.dto.FxImixAccountInfo001Dto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixtraderassociation.dto.FxTraderManagerDto;
import com.joyintech.interf.bankname.cfetsup.secrequestquote.dto.FxSecRequestQuoteCounterDto;
import com.joyintech.interf.bankname.cfetsup.secrequestquote.dto.FxSecRequestQuoteDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface FxSecRequestQuoteMapper {

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    public List<FxSecRequestQuoteDto> findPage(Map<String, Object> pageParam);

    /**
     * 查询交易对手
     * @param pageParam
     * @return
     */
    public List<FxSecRequestQuoteDto> findCounterPage(Map<String, Object> pageParam);

    /**
     *
     * @param pseqno
     * @return
     */
    public List<FxSecRequestQuoteCounterDto> findCounterDtoByPseqno(String pseqno);

    /**
     *
     * @param seqno
     * @return
     */
    public FxSecRequestQuoteDto findRequestQuoteDtoBySeqno(String seqno);

    public List<SelectOptionDto> getJycs(Map para);

    public List<SelectOptionDto> getProductid(Map para);

    public List<SelectOptionDto> getSeccode(Map para);

    public List<SelectOptionDto> getCcy(Map para);

    public String findSequence();

    public void saveQuote(FxSecRequestQuoteDto dto);

    public void saveCounterList(List<FxSecRequestQuoteCounterDto> counterList);

    public FxSecRequestQuoteDto setQuoteInfoFromTrust(String trustid);

    public List<SelectOptionDto> getDataDict(Map<String, Object> pageParam);

    public void updateProcstatus (FxSecRequestQuoteDto dto);

    public void deleteQuoteBySeqno(String seqno);

    public void deleteQuoteCounterByPseqno(String pseqno);

    public void insertQuoteCounterHist(List<FxSecRequestQuoteCounterDto> counterList);

    public void insertQuoteHist(FxSecRequestQuoteDto dto);

    public FxTraderManagerDto findHomeTrader(String userName);

    public FxImixAccountInfo001Dto findHomeCstp(String accountid);

    public void updateStatus(FxSecRequestQuoteDto dto);
}
