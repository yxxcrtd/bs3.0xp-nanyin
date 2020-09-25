package com.joyintech.service.valuation.basedata.marketdata.externaloptions.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.basedata.marketdata.externaloptions.dao.ExternalOptionsDao;
import com.joyintech.service.valuation.basedata.marketdata.externaloptions.dto.ExternalOptions001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 姚轶文
 * @create 2020- 01-06 16:07
 */
@Service
public class ExternalOptionsService001 extends BaseService {

    @Autowired
    ExternalOptionsDao externalOptionsDao;

    /**
     * 列表查询 - 分页
     *
     * @param page
     * @param length
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
    public Page<ExternalOptions001Dto> queryPageList(int page, int length, Map<String, Object> params) {
        return super.queryForPage(page, length, () -> externalOptionsDao.selectList(params));
    }
}
