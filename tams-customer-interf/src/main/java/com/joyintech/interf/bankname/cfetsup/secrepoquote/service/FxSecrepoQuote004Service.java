package com.joyintech.interf.bankname.cfetsup.secrepoquote.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dao.FxSecrepoQuoteMapper;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuote003Dto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuote004Dto;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 回购对话报价业务层
 *
 * @author zcf
 * @version 1.0
 * @date 2020-01-08
 */
@Service
public class FxSecrepoQuote004Service extends BaseService<FxSecrepoQuote004Dto > {

    @Autowired
    private FxSecrepoQuoteMapper fxSecrepoQuoteMapper;

    /**
     * 分页查询
     * @param para
     * @return
     */
    public Page<FxSecrepoQuote004Dto > selectPageData(Map<String, Object> para) {
        para.put("ASSET_TYPE","F07");
        para.put("ENTRUST_STATUS","04");
        para.put("TRADER", LoginUserUtil.getUserName());
        return this.queryForPage(Integer.valueOf(para.get("page").toString()),Integer.valueOf(para.get("length").toString()),
                ()->fxSecrepoQuoteMapper.selectEntrustInfo(para));
    }

}
