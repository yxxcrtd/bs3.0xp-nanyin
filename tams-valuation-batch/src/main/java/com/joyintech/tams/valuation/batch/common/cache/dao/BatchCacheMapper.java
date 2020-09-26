package com.joyintech.tams.valuation.batch.common.cache.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.common.cache.service.entity.SysParaDto;
import com.joyintech.tams.valuation.batch.common.cache.service.entity.ValBokBussDetsubMem;
import com.joyintech.tams.valuation.batch.common.cache.service.entity.ValBokSubjectDetailMem;

/**
 * 基础mapper
 * 
 * @author qiangchengxi
 * @version 1.0 2016年12月17日
 * @see BatchCacheMapper
 * @since 1.0
 */
@Repository(value = "valBatchCacheMapper")
public interface BatchCacheMapper {

    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<ValBokSubjectDetailMem> findAllDetailSubject();
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<ValBokBussDetsubMem> findAllBussDetsubKey();
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<SysParaDto> selectAllSysParam();
    
    
}