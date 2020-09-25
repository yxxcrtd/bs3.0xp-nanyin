package com.joyintech.interf.bankname.cfetsup.secdialogquote.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.dto.FxSecdialogQuote001Dto;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.dto.FxSecdialogQuote002Dto;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.entity.FxSecdialogQuote;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface FxSecdialogQuoteMapper {


    List<FxSecdialogQuote001Dto> selectForPage(Map<String,Object> map);

    List<SelectOptionDto> getPortfolioId(Map para);

    List<SelectOptionDto> getFinprodId(Map<String,Object> params);

    /**
     * 查询债券未来行权计划的条数
     *
     * @param param 参数
     * @return 债券未来行权计划的条数
     * @see
     * @since 1.0
     */
    int selectBondHasEexrPlanCount(Map<String, Object> param);

    /**
     *
     * 获取平台日
     * <功能详细描述>
     * @return 平台日
     * @see
     * @since 1.0
     */
    Date getSysDate();

    /**
     * 获取报价信息
     * @return
     */
    FxSecdialogQuote002Dto selectByEntrustId(Map<String,Object> map);

    String getSequence();

    int deleteByPrimaryKey(String seqno);

    int insert(FxSecdialogQuote record);

    int insertHis(FxSecdialogQuote record);

    int insertSelective(FxSecdialogQuote record);

    FxSecdialogQuote selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(FxSecdialogQuote record);

    int updateByPrimaryKey(FxSecdialogQuote record);

    int revoke(String seqno);

    int updateHis(FxSecdialogQuote entity);
}
