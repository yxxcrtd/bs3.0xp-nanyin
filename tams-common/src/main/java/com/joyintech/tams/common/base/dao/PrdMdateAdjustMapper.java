package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdMdateAdjust;
import com.joyintech.tams.common.base.entity.PrdMdateAdjustKey;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;

@Repository
public interface PrdMdateAdjustMapper {
    int deleteByPrimaryKey(PrdMdateAdjustKey key);

    int insert(PrdMdateAdjust record);

    int insertSelective(PrdMdateAdjust record);

    int insertBasicByBasicInfo(PrdMdateAdjust record);

    PrdMdateAdjust selectByPrimaryKey(PrdMdateAdjustKey key);

    int updateByPrimaryKeySelective(PrdMdateAdjust record);

    int updateByPrimaryKey(PrdMdateAdjust record);

    /**
     * 查询金融产品到期日调整表中的调整后日期
     * @param param
     * @return
     */
    Date selectMdate(Map<String,Object> param);
}