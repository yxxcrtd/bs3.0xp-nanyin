package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmenmailinfo.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmenmailinfo.dao.FxImixMenMailInfoMapper;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmenmailinfo.dto.FxImixMenMailInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 银行间交易员信息查询
 *
 * @author zcf
 * @version 1.0
 * @date: 2020-01-15
 */
@Service
public class FxImixMenMailInfo001Service extends BaseService<FxImixMenMailInfoDto> {

    @Autowired
    private FxImixMenMailInfoMapper fxImixMenMailInfoMapper;

    /**
     * 分页查询
     * @param para
     * @return
     */
    public Page<FxImixMenMailInfoDto> selectPageData(Map<String, Object> para) {
        return this.queryForPage(Integer.valueOf(para.get("page").toString()),Integer.valueOf(para.get("length").toString()),
                ()->fxImixMenMailInfoMapper.selectPageData(para));
    }

}
