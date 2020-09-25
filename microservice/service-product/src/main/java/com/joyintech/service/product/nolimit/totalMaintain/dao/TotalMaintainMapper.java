package com.joyintech.service.product.nolimit.totalMaintain.dao;

import com.joyintech.service.product.nolimit.totalMaintain.dto.TotalMaintain001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TotalMaintainMapper {
    /**
     * 非标限额信息分页查询
     *
     * @param pageParam
     * @return List
     */
    List<TotalMaintain001Dto> findPage(Map<String, Object> pageParam);

    /**
     * 新建非标限额生效日校验
     *
     * @param effDate
     * @return int
     */
    int effDateCheck(String effDate);

    /**
     * 新建非标限额
     *
     * @param dto
     */
    void insertInfo(TotalMaintain001Dto dto);

    /**
     * 修改非标限额
     *
     * @param dto
     */
    void updateInfo(TotalMaintain001Dto dto);

    /**
     * 删除非标限额
     *
     * @param effDate
     */
    void deleteInfo(String effDate);
}
