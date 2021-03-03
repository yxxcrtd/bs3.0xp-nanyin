package com.joyintech.tams.common.svreport.datacheck.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.svreport.datacheck.dto.ChbInterfErrodata;
import com.joyintech.tams.common.svreport.datacheck.dto.ChbInterfErrodataWithBLOBs;

public interface ChbInterfErrodataMapper {
    int deleteByPrimaryKey(String dataid);

    int insert(ChbInterfErrodataWithBLOBs record);

    int insertSelective(ChbInterfErrodataWithBLOBs record);

    ChbInterfErrodataWithBLOBs selectByPrimaryKey(String dataid);

    int updateByPrimaryKeySelective(ChbInterfErrodataWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ChbInterfErrodataWithBLOBs record);

    int updateByPrimaryKey(ChbInterfErrodata record);
    int insertChbInterfErrodata(@Param("newDataList")List<ChbInterfErrodataWithBLOBs> newDataList);

	/** start  中债一二期添加         add  by  dongxiangyang  20201013 */

    int deleteByPrimaryKeyType(Map<String,String> map);
	/** end  中债一二期添加         add  by  dongxiangyang  20201013 */

}