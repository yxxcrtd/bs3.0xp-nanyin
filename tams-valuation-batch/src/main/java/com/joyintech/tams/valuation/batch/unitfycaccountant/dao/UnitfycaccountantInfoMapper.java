package com.joyintech.tams.valuation.batch.unitfycaccountant.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.unitfycaccountant.entity.SztInterfValuation;
import com.joyintech.tams.valuation.batch.unitfycaccountant.entity.ValAccountInfoDto;
import com.joyintech.tams.valuation.batch.unitfycaccountant.entity.ValDataBackhaulLogDto;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.system.batch.batchprocess.dto.BatchTaskDto;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.SysUnaccountantInfo;
import com.joyintech.tams.common.base.entity.SysUnaccountantInfoDetail;
import com.joyintech.tams.common.base.entity.ValInterfActionlog;

@Repository
public interface UnitfycaccountantInfoMapper {
    /**
     * 查询未执行的任务
     * @return 任务列表
     */
    public List<SysUnaccountantInfo> findUnExecUnitfycaccountantInfo();

    /**
     * 通过Seqno 查询所有明细
     * @return
     */
    public List<SysUnaccountantInfoDetail> findSysUcaccountantInfoDetailBySeqno(Map<String, Object> params);


    /**
     * 查询在执行中或未执行的任务
     * @return 任务列表
     */
    public List<SysUnaccountantInfo> findInExecUnitfycaccountantInfo();

    /**
     * 将未执行的任务更新成执行状态
     */
    public void updateUnitfycaccountantInfoRun(Map<String, Object> params);

    /**
     * 查询在执行顺序低的执行中数据
     * @return 任务列表
     */
    public List<SysUnaccountantInfoDetail> findOrderNoLessList(@Param(value = "seqno")String seqno, @Param(value = "orderNo")Long orderNo);

    /**
     * 查询任务失败的数据
     * @return 任务列表
     */
    public List<SysUnaccountantInfoDetail> findInfoFailList(@Param(value = "seqno")String seqno, @Param(value = "orderNo")Long orderNo);


    /**
     * 查询接口列表
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    public List<ValAccountInfoDto> findAllTask(Map<String, Object> params);


    /**
     * 获取界面任务组所需日志信息
     * @param params
     * @return
     */
    public List<ValInterfActionlog> getInterActionLog(Map<String, Object> params);


    /**
     * 获取界面任务组所需日志信息
     * @param params
     * @return
     */
    public List<SysBatchExecInfo> getBatchTaskLogInfo(Map<String, Object> params);



    /**
     * 后续任务置失败
     * @param params
     * @return
     */
    public int updateInfoDetailFail(Map<String, Object> params);

    /**
     * 任务置失败
     * @param params
     * @return
     */
    public int updateInfoFail(Map<String, Object> params);

    List<String> findAllProdbyGroup(@Param("prodGroupSeqNos") String[] prodGroupSeqNos);

    /**
     *
     * 查询账套
     * <功能详细描述>
     * @param start
     * @param end
     * @return
     * @see
     * @since 1.0
     */
    List<BokBookset> selectValBokBookset(@Param("start") Date start, @Param("end") Date end
            , @Param("prodList")  List<String> prodList);
    
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<BatchTaskDto> getBatchTaskid(Map<String, Object> map);
    
    
    List<SztInterfValuation> selectValuation( Map<String, Object> map);
    
    
    List<ValDataBackhaulLogDto> getInterDataBackLog(Map<String, Object> map);
}
