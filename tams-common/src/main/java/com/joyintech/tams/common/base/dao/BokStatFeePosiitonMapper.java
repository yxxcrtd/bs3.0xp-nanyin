package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokStatFeePosiiton;
import com.joyintech.tams.common.base.entity.BokStatFeePosiitonKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BokStatFeePosiitonMapper {
    int deleteByPrimaryKey(BokStatFeePosiitonKey key);

    int insert(BokStatFeePosiiton record);

    int insertSelective(BokStatFeePosiiton record);

    int insertBasicByBasicInfo(BokStatFeePosiiton record);

    BokStatFeePosiiton selectByPrimaryKey(BokStatFeePosiitonKey key);

    int updateByPrimaryKeySelective(BokStatFeePosiiton record);

    int updateByPrimaryKey(BokStatFeePosiiton record);
    /**
     * @author  殷亚
     * @description mergeValBokStatFeePosition    批处理性能优化：新增批量合并方法
     * @date 2021-12-22 16:59
     * @param list:
     * @return int
     */
    int mergeValBokStatFeePosition(List<BokStatFeePosiiton> list);
}