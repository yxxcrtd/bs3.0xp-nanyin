package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixerrorinfo.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixerrorinfo.dao.FxImixErrorInfoMapper;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixerrorinfo.dto.FxImixErrorInfoDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmeninfo.dao.FxImixMenInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 本币银行间错误代码对照信息业务层
 *
 * @author zcf
 * @version 1.0
 * @date 2019-09-29
 */
@Service
public class FxImixErrorInfo001Service extends BaseService<FxImixErrorInfoDto> {

    @Autowired
    private FxImixErrorInfoMapper fxImixErrorInfoMapper;

    /**
     * 分页查询
     * @param para
     * @return
     */
    public Page<FxImixErrorInfoDto > selectPageData(Map<String, Object> para) {
        return this.queryForPage(Integer.valueOf(para.get("page").toString()),Integer.valueOf(para.get("length").toString()),
                ()->fxImixErrorInfoMapper.selectPageData(para));
    }

    /**
     * 查询组合代码
     * @param para
     * @return
     */
    public Page<SelectOptionDto> getApplerrorcode(Map para){
        return new Page(fxImixErrorInfoMapper.getApplerrorcode(para));
    }
}
