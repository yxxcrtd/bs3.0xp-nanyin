package com.joyintech.service.valuation.lossmodel.impairmentprovision.dao;

import com.joyintech.service.valuation.lossmodel.impairmentprovision.dto.ImpairmentProvision001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 减值情况计提表
 * @author xutong
 * @createTime 2021-07-05
 * @see ImpairmentProvision001Mapper
 * @since 1.0
 */
@Repository(value="serviceImpairmentProvision001Mapper")
public interface ImpairmentProvision001Mapper {
    /**
     * 查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param params
     * @return
     */
    List<ImpairmentProvision001Dto> queryDate(Map<String, Object> params);

}
