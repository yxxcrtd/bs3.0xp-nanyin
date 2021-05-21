package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbPrdBaseinfoDetail;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfoDetailWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChbPrdBaseinfoDetailMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ChbPrdBaseinfoDetailWithBLOBs record);

    int insertSelective(ChbPrdBaseinfoDetailWithBLOBs record);

    int insertBasicByBasicInfo(ChbPrdBaseinfoDetailWithBLOBs record);

    ChbPrdBaseinfoDetailWithBLOBs selectByPrimaryKey(String prodId);

    List<ChbPrdBaseinfoDetailWithBLOBs> selectByFprodid(@Param("fprodId")String fprodId);

    int updateByPrimaryKeySelective(ChbPrdBaseinfoDetailWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ChbPrdBaseinfoDetailWithBLOBs record);

    int updateByPrimaryKey(ChbPrdBaseinfoDetail record);
}