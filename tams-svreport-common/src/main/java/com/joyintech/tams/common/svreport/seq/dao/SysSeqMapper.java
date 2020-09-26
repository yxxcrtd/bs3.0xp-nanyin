package com.joyintech.tams.common.svreport.seq.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.svreport.seq.dto.SysSeqModel;

/**
 * 系统序列
 * @description:
 * @version:1.0 2015年5月16日
 * @author:wang han-Dylan
 */
@Repository
public interface SysSeqMapper {

    /**
     * 插入序列
     * @param model
     * @return
     * @see
     * @since 1.0
     */
    int insertSelective(SysSeqModel model);

    /**
     * 根据主键序列查询序列
     * 
     * @param key
     * @return
     * @return SysSeqModel
     */
    SysSeqModel selectByPrimaryKey(SysSeqModel key);

    /**
     * 修改序列
     * 
     * @param model 序列model
     * @return
     * @return int
     */
    int updateByPrimaryKeySelective(SysSeqModel model);

}