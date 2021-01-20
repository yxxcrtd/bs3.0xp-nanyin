package com.joyintech.tams.valuation.batch.common.base.dao;

 import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokBookdetailCurrentExt;
import com.joyintech.tams.common.base.entity.BokBussDetsub;
import com.joyintech.tams.common.base.entity.BokSubjectDetail;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;

/**
 * 基础mapper
 * 
 * @author qiangchengxi
 * @version 1.0 2016年12月17日
 * @see BaseAccountWriterMapper
 * @since 1.0
 */
@Repository
public interface BaseAccountWriterMapper {
	/**
	 * 插入明细科目表
	 * @param valBokSubjectDetail 账务明细代码列表
	 * @see
	 * @since 1.0
	 */
    void insertValBokSubjectDetail(List<BokSubjectDetail> valBokSubjectDetail);
    
    /**
     * 插入业务明细科目表
     * @param valBokSubjectDetail 账务明细代码列表
     * @see
     * @since 1.0
     */
    void insertValBokBussDetsub(List<BokBussDetsub> valBokBussDetsub);
    
    /**
     * 插入账务明细（当前表）
     * @param valBokSubjectDetail 账务明细代码列表
     * @see
     * @since 1.0
     */
    void insertValBokBookdetailCurrent(List<ValBokBookdetailBatch> valBokSubjectDetail);
    
    /**
     * 插入账务明细（历史表）
     * @param valBokSubjectDetail 批处理用账务明细
     * @see
     * @since 1.0
     */
    void insertValBokBookdetailAll(List<ValBokBookdetailBatch> valBokSubjectDetail);
    
    /**
     * 更新账务明细（历史表）
     * @param vo 批处理用账务明细
     * @see
     * @since 1.0
     */
    void updateValBokBookDetailAll(ValBokBookdetailBatch vo);
    
    /**
     * 更新账务明细（当前表）
     * @param vo 批处理用账务明细
     * @see
     * @since 1.0
     */
    void updateValBokBookDetailCurrent(ValBokBookdetailBatch vo);
    
    /**
     * 插入账务明细辅助表
     * 
     * @param BokBookdetailCurrentExt 账务明细辅助表列表
     * @see
     * @since 1.0
     */
    void insertBokBookdetailCurrentExt(List<BokBookdetailCurrentExt> BokBookdetailCurrentExt);

    int mergeValBokBussDetsub(List<BokBussDetsub> list);
}