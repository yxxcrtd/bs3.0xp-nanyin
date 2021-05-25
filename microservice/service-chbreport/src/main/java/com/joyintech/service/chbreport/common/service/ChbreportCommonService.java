package com.joyintech.service.chbreport.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.svreport.chbreport.common.dao.ChbreportCommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author WanShiWei
 * @version 1.0 2021年04月19日
 * @see ChbreportCommonService
 * @since 1.0
 */
@Service
public class ChbreportCommonService extends BaseService {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(ChbreportCommonService.class);

    @Autowired
    private ChbreportCommonMapper chbreportCommonMapper;

    /**
     * 查询产品代码和产品名称
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPrdBaseinfo(Map<String, Object> map) {
        return chbreportCommonMapper.selectAllPrdBaseInfo(map);
    }
}
