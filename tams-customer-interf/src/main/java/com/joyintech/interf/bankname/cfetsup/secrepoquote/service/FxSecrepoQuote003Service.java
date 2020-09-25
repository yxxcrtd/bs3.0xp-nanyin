package com.joyintech.interf.bankname.cfetsup.secrepoquote.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dao.FxSecrepoQuoteMapper;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuote001Dto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuote003Dto;
import com.joyintech.tams.constant.DictCodeList;
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
public class FxSecrepoQuote003Service extends BaseService<FxSecrepoQuote003Dto > {

    @Autowired
    private FxSecrepoQuoteMapper fxSecrepoQuoteMapper;

    /**
     * 分页查询
     * @param para
     * @return
     */
    public Page<FxSecrepoQuote003Dto > selectPageData(Map<String, Object> para) {
        //系统日期
        para.put("cdate", fxSecrepoQuoteMapper.getSysDate());
        return this.queryForPage(Integer.valueOf(para.get("page").toString()),Integer.valueOf(para.get("length").toString()),
                ()->fxSecrepoQuoteMapper.selectByFinprodInfo(para));
    }

    /**
     * 查询债券代码
     * @param para
     * @return
     */
    public Page<SelectOptionDto> getFinprodId(Map para){
        para.put("FINPROD_TYPE", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        return new Page(fxSecrepoQuoteMapper.getFinprodId(para));
    }

    /**
     * 获取系统日期
     * @return
     */
    public String getSysDate() {
        // 加载平台日期
        Date dt = fxSecrepoQuoteMapper.getSysDate();
        if (dt != null) {
            return DateUtil.format(dt, DateUtil.DEFAULT_DATE_FORMAT_EN);
        }
        return StringUtil.EMPTY;
    }
}
