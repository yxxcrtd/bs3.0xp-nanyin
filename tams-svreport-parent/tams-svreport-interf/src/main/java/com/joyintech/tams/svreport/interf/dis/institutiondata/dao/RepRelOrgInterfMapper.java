package com.joyintech.tams.svreport.interf.dis.institutiondata.dao;

import com.joyintech.tams.common.base.entity.RepRelorg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RepRelOrgInterfMapper {

    /**
     * 查询截止上次接入时间的关联机构信息
     * @param batchTime
     * @return
     */
    List<RepRelorg> select(@Param("updatetime") Date updatetime);

}