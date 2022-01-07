package com.joyintech.tams.common.svreport.select.service;


import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.common.base.entity.MstCustomerInfoAdd;
import com.joyintech.tams.common.svreport.select.dao.SelectorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author yxj
 * @date 2022/1/5
 */
@Service
public class SelectorService extends BaseService {
    @Autowired
    private SelectorMapper selectorMapper;

    public Page<SelectOptionDto> getCustomerId(Map<String, Object> param){
        return this.queryForPage(() -> selectorMapper.getCustomerId(param));
    }

    public MstCustomerInfoAdd getCustomerInfo(Map<String, Object> param) {
        return selectorMapper.getCustomerInfo(param);
    }
}
