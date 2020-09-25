package com.joyintech.tams.component.base.report.asset;

import com.joyintech.tams.common.base.entity.RepAssetOther;
import com.joyintech.tams.component.base.report.dto.BaseRepOtherDto;

import java.util.List;

public interface BaseRepOtherInfoService {

    /**
     * 新建
     * <功能详细描述>
     * @param list
     * @return
     * @see
     * @since 1.0
     */
    boolean insert(List<RepAssetOther> list);

    /**
     * 删除
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    boolean delete(BaseRepOtherDto dto);

    /**
     * 更新
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    boolean update(BaseRepOtherDto dto);

    /**
     * 查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetOther> select(BaseRepOtherDto dto);

}
