package com.joyintech.tams.svreport.interf.dis.boksubject.dao;

import com.joyintech.tams.common.base.entity.BokSubject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BokSubjectInterfMapper {

    /**
     * 查询截止上次接入时间的科目余额数据
     * @param batchTime
     * @return
     */
    List<BokSubject> select(@Param("updatetime") Date updatetime);

}