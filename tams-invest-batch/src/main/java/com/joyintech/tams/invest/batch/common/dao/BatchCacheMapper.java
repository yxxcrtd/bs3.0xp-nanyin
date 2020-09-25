package com.joyintech.tams.invest.batch.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

//import com.joyintech.batch.valuation.common.cache.service.entity.ValBokBussDetsubMem;
//import com.joyintech.batch.valuation.common.cache.service.entity.ValBokSubjectDetailMem;
import com.joyintech.tams.invest.batch.common.dto.SysParaDto;

/**
 * 基础mapper
 *
 * @author qiangchengxi
 * @version 1.0 2016年12月17日
 * @see BatchCacheMapper
 * @since 1.0
 */
@Repository
public interface BatchCacheMapper {

    /**
     * 查询系统参数
     *
     * @return 系统参数
     * @see
     * @since 1.0
     */
    List<SysParaDto> selectAllSysParam();

//    /**
//     *
//     * <一句话功能简述>
//     * <功能详细描述>
//     * @return
//     * @see
//     * @since 1.0
//     */
//    List<ValBokSubjectDetailMem> findAllDetailSubject();
//    /**
//     *
//     * <一句话功能简述>
//     * <功能详细描述>
//     * @return
//     * @see
//     * @since 1.0
//     */
//    List<ValBokBussDetsubMem> findAllBussDetsubKey();
}