package com.joyintech.interf.bankname.cfetsup.secdialogquote.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.dao.FxSecdialogQuoteMapper;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.dto.FxSecdialogQuote001Dto;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.entity.FxSecdialogQuote;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class FxSecdialogQuote001Service extends BaseService<FxSecdialogQuote001Dto> implements BaseProcessService<FxSecdialogQuote001Dto> {

    @Autowired
    private FxSecdialogQuoteMapper fxSecdialogQuoteMapper;


    /**
     * 查询组合代码
     * @param para
     * @return
     */
    public Page<SelectOptionDto> getPortfolioId(Map para){

        return new Page(fxSecdialogQuoteMapper.getPortfolioId(para));

    }

    public Page<FxSecdialogQuote001Dto> selectForPage(Map<String,Object> pageParam){
        Object portfolioIdObj = pageParam.get("portfolioId");
        if(null != portfolioIdObj){
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,(String)portfolioIdObj));
        }else {
            pageParam.put("portfolioIdList", new ArrayList<>());
        }
        return super.queryForPage(()-> fxSecdialogQuoteMapper.selectForPage(pageParam));
    }


    @Override
    public void doComplete(FxSecdialogQuote001Dto fxSecdialogQuote001Dto, OperationType operationType) {
        fxSecdialogQuoteMapper.deleteByPrimaryKey(fxSecdialogQuote001Dto.getSeqno());
    }

    public FxSecdialogQuote selectByPrimaryKey(String seqno) {
        return fxSecdialogQuoteMapper.selectByPrimaryKey(seqno);
    }
}
