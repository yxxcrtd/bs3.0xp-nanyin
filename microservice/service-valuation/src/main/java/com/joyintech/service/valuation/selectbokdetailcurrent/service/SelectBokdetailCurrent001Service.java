package com.joyintech.service.valuation.selectbokdetailcurrent.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.selectbokdetailcurrent.dao.SelectBokdetailCurrent001Mapper;
import com.joyintech.service.valuation.selectbokdetailcurrent.dto.SelectBokdetailCurrent001Dto;


/**
 * 账务明细当前表查询
 *
 * @author tanxuan
 * @createTime 2019-10-16
 */
@SuppressWarnings("rawtypes")
@Service("seriveSelectBokdetailCurrent001Service")
public class SelectBokdetailCurrent001Service extends BaseService {

    @Autowired
    private SelectBokdetailCurrent001Mapper selectBokdetailCurrent001Mapper;
    
    /**
     * 列表查询 - 分页
     * @param page
     * @param length
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
    public Page<SelectBokdetailCurrent001Dto> queryPageList(int page, int length, Map<String, Object> params) {
        return super.queryForPage(page,length,()->selectBokdetailCurrent001Mapper.selectList(params));
    }

    /**
     * 列表查询
     * @param page
     * @param length
     * @param params
     * @return
     */
    public List<SelectBokdetailCurrent001Dto> queryAllList(Map<String, Object> params) {
        return selectBokdetailCurrent001Mapper.selectList(params);
    }
}
