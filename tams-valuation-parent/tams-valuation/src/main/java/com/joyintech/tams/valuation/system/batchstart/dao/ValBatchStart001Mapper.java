package com.joyintech.tams.valuation.system.batchstart.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.base.entity.SysBatchJob;
import com.joyintech.framework.base.entity.SysBatchPrdGrp;
import com.joyintech.framework.base.entity.SysBatchTask;
import com.joyintech.tams.valuation.system.batchstart.dto.BatchGrpPri;
import com.joyintech.tams.valuation.system.batchstart.dto.DimensionValue;
import com.joyintech.tams.valuation.system.batchstart.dto.Product;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokBooksetDate;

@Repository
public interface ValBatchStart001Mapper {
    List<SysBatchTask> findAllTask(Map<String,Object> pageParam);

    void batchInsertBatchGroup(List<SysBatchJob> jobList);

    void batchInsertProdGroup(List<SysBatchGrpPrdlink> products);

    void updateExeDetailToUnr(@Param("detailSeqNo") String detailSeqNo, @Param("userName")  String userName);

    void deleteAllBatchInfo(String detailSeqNo);
    
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
    List<BokBookset> selectValBokBookset(@Param("start") Date start, @Param("end")  Date end
                                            , @Param("prodList")  List<String> prodList);
    
    List<Product> findAllProd(Map<String,Object> map);
    
    List<String> findAllProdbyGroup(@Param("prodGroupSeqNos") String[] prodGroupSeqNos);

    List<String> findAllTaskbyGroup(@Param("batchGroupSeqNos") String[] batchGroupSeqNos);
    
    void insertDimValue(List<DimensionValue> dimValueList);
    
    void insertBatchGrpPri(List<BatchGrpPri> batchGrpPriList);
    
    void insertBatchJob(List<SysBatchJob> batchJobList);
    
    void updateByBookRe(BokBooksetDate bokBooksetDate);
    
    List<SysBatchPrdGrp> findAllPrdGroup(Map<String, Object> params);

	/**
	 * 批处理启动_估值：未选择产品，默认查询登录用户所属机构的所有产品
	 * @param createInst
	 * @return
	 */
	List<com.joyintech.tams.valuation.system.batchstart.dto.Product> findAllProdByInstitutionCode(@Param("institutionCode")String createInst);

    List<SysPrebatchResult> selectErrorPrebatchResults(Map<String, Object> param);
}
