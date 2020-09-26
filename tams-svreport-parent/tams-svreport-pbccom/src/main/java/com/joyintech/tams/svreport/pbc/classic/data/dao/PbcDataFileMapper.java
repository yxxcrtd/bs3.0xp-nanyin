package com.joyintech.tams.svreport.pbc.classic.data.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.svreport.pbc.classic.data.dto.PbcDataFile;

public interface PbcDataFileMapper {
    int deleteByPrimaryKey(String fileid);

    int insert(PbcDataFile record);

    int insertSelective(PbcDataFile record);

    PbcDataFile selectByPrimaryKey(String fileid);

    int updateByPrimaryKeySelective(PbcDataFile record);

    int updateByPrimaryKey(PbcDataFile record);
    
    /**
     * 分页查询
     * @param param 页面参数
     * @return 查询结果
     */
    List<PbcDataFile> queryPage(Map<String, Object> param);
}