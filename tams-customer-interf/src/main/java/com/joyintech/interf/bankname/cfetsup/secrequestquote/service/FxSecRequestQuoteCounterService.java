package com.joyintech.interf.bankname.cfetsup.secrequestquote.service;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.base.BaseService;
import com.joyintech.interf.bankname.cfetsup.secrequestquote.dao.FxSecRequestQuoteMapper;
import com.joyintech.interf.bankname.cfetsup.secrequestquote.dto.FxSecRequestQuoteCounterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(value="fxSecRequestQuoteCounterService")
public class FxSecRequestQuoteCounterService extends BaseService {

    @Autowired
    private FxSecRequestQuoteMapper fxSecRequestQuoteMapper;

    public Page<FxSecRequestQuoteCounterDto> findCounterPage(Map<String, Object> pageParam) {
        return this.queryForPage(Integer.valueOf(pageParam.get("page").toString()), Integer.valueOf(pageParam.get("length").toString()),
                () -> fxSecRequestQuoteMapper.findCounterPage(pageParam));
    }
}
