package com.joyintech.service.riskrds.limitmanagement.medianmanagement.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.riskrds.limitmanagement.medianmanagement.dao.PltMedianManagementMapper;
import com.joyintech.service.riskrds.limitmanagement.medianmanagement.dto.PltMedianManagementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * 指标参数中间值管理
 * @author zhengjunping
 * @version 1.0 2019年11月19日
 * @see PltMedianManagementService
 * @since 1.0
 */
@Service
public class PltMedianManagementService extends BaseService<PltMedianManagementDto> {

    /**
     * 指标参数中间值管理Mapper
     */
    @Autowired
    private PltMedianManagementMapper pltMedianManagementMapper;

    /**
     * 分页查询
     * 
     * @param length 长度
     * @param page 页数
     * @param map 分页查询参数
     * @return 指标参数中间值管理集合
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<PltMedianManagementDto> selectForPage(int page, int length, Map<String, Object> map)
        throws Exception {
        return super.queryForPage(page, length,
            () -> pltMedianManagementMapper.selectForPage(map));
    }
    
    /**
     * 使用给定的sqlID为联想框检索分页数据。返回类型是Page<SuggestDto>。
     * 
     * @param pageParam pageParam
     * @param page 页码
     * @param length 页面长度
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<SelectOptionDto> selectMedianManagement(int page, int length,
                                                        Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPageForSelectReturn(page, length,
            () -> pltMedianManagementMapper.selectMedianManagement(pageParam));
    }

}
