package com.joyintech.service.period.trdprojint.service;

import java.util.HashMap;
import java.util.Map;

import com.joyintech.service.period.trdprojint.dao.TrdProjectDurationInterestMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.period.trdprojbuyoff.TrdProjectBuyOffInterestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;

/**
 * @program: component-base
 * @description: 买断利率维护Service
 * @author: 强成西
 * @create: 2018-10-23 14:53
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class TrdProjectDurationInterestService extends BaseService<TrdProjectBuyOffInterestDto> {

    @Autowired
    private TrdProjectDurationInterestMapper trdProjectDurationInterestMapper;

    public Page<TrdProjectBuyOffInterestDto> findPage(int page, int length, Map<String, Object> param) {
        param.put("FINPROD_TYPE_TG_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        return queryForPageForSelectReturn(page, length, () -> trdProjectDurationInterestMapper.findPage(param));
    }
}