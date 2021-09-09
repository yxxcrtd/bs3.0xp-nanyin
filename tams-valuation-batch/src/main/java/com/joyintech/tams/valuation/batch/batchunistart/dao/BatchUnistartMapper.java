package com.joyintech.tams.valuation.batch.batchunistart.dao;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.base.entity.SysBatchJob;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.SysBatchUnistart;
import com.joyintech.tams.common.base.entity.SysBatchUnistartDetail;
import com.joyintech.tams.valuation.batch.batchunistart.entity.BatchUnistartGrpconfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Repository
public interface BatchUnistartMapper {

    /**
     * 查询未执行的任务
     *
     * @return 任务列表
     */
    public List<SysBatchUnistart> findUnistartUnr();

    /**
     * 通过Seqno 查询所有明细
     *
     * @return
     */
    public List<SysBatchUnistartDetail> findUnistartDetailBySeqNo(Map<String, Object> params);

    /**
     * 查询在执行中或未执行的任务
     *
     * @return 任务列表
     */
    public List<SysBatchUnistart> findUnistartUnrOrRun();

//    /**
//     * 将未执行的任务更新成执行状态
//     */
//    public void updateSysUnistartStatusBySeqNo(Map<String, Object> params);

    public void updateSysUnistartBreak();

    public void updateSysUnistartDetailBreak();
    /**
     * 查询在执行顺序低的执行中数据
     *
     * @return 任务列表
     */
    public List<SysBatchUnistartDetail> findUnistartOrderNoLessList(Map<String, Object> params);

    /**
     * 获取界面任务组所需日志信息
     *
     * @param params
     * @return
     */
    public List<SysBatchExecInfo> getBatchTaskLogInfo(Map<String, Object> params);

    /**
     * 获取界面任务组所需日志信息
     *
     * @param params
     * @return
     */
    public List<SysBatchExecInfo> getBatchTaskDetailLogInfo(Map<String, Object> params);

    /**
     * 后续任务置失败
     *
     * @param params
     * @return
     */
    public int updateUnistartDetailToEnd(Map<String, Object> params);

    /**
     * 任务置失败
     *
     * @param params
     * @return
     */
    public int updateUnistartToEnd(Map<String, Object> params);

    /**
     * 查询账套
     * <功能详细描述>
     *
     * @param start
     * @param end
     * @return
     * @see
     * @since 1.0
     */
    public List<BokBookset> selectValBokBookset( @Param("start") Date start, @Param("end") Date end,
                                                 @Param("prodList") List<String> prodList);

    /**
     * 查询批处理任务组明细
     * @param batchSeqNos
     * @return
     */
    public List<SysBatchJob> findAllJobByGroup(@Param("batchSeqNos") String batchSeqNos);


    /**
     * 查询未执行的任务
     *
     * @return 任务列表
     */
    public List<BatchUnistartGrpconfig> findGrpconfigById(@Param("configId") String configId);

    List<String> selectErrorPrebatchResult(Map<String, Object> param);
}
