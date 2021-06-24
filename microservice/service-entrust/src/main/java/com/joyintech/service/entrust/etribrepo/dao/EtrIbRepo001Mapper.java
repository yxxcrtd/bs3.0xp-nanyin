/*
 * 文件名：EtrBondFirstBiddingMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etribrepo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.entrust.etribrepo.dto.EtrIbRepo001Dto;
import com.joyintech.service.entrust.etribrepo.dto.EtrIbRepoRk004Dto;
import com.joyintech.service.entrust.etribrepo.dto.EtrIbRepoRk005Dto;
import com.joyintech.tams.common.base.entity.EtrEntrustPledge;
import com.joyintech.tams.common.base.entity.EtrQuoteFinishStatus;
import com.joyintech.tams.common.base.entity.EtrSecStockConfig;
import com.joyintech.tams.common.base.entity.PtlPortfolio;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 *  场内交易委托
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年11月14日
 * @see EtrIbRepo001Mapper
 * @since 1.0
 */
@Component
public interface EtrIbRepo001Mapper {
    /**
     * 指令管理分页查询
     * <功能详细描述>
     * @param pageParam 查询参数
     * @return 分页结果
     * @see
     * @since 1.0
     */
    List<EtrIbRepo001Dto> findZlglPage(Map<String, Object> pageParam);
    /**
     * 指令管理查询分页查询
     *
     * @param pageParam 查询参数
     * @return 分页结果
     * @see
     * @since 1.0
     */
    List<EtrIbRepo001Dto> findZlglPositionPage(Map<String, Object> pageParam);

    /**
     * 根据组合代码，查询证券托管户：联想框文本框
     *
     * @param param
     * @return
     */
    List<SelectOptionDto> getSecManageAcctId(Map<String, Object> param);
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<EtrIbRepoRk004Dto> getAssetName(Map<String, Object> param);
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<EtrIbRepoRk005Dto> getAssetDetailBySeqno(Map<String, Object> param);
    
    int deleteByPrimaryKey(String configId);
    
    int delete(String pledgeStockId);
    
    int checkIsExistByPledgeStockName(Map<String, Object> map);
    
    EtrIbRepoRk004Dto getBusinessInfoByPledgeStockId(@Param("pledgeStockId") String pledgeStockId);
    
    int checkIsExistDetail(@Param("pledgeStockId") String pledgeStockId);
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<PtlPortfolio> findAllTask(Map<String, Object> params);
    
    EtrIbRepoRk004Dto getName(@Param("pledgeStockId") String pledgeStockId);
    
    Long deleteEtrEntrustPledge(EtrEntrustPledge etrEntrustPledgeKey);
    
    List<EtrIbRepo001Dto> selectF01List(Map<String, Object> params);
    
    int deleteEtrEntrustPledgeByPledgeStockId(@Param("pledgeStockId") String pledgeStockId);
    
	List<EtrQuoteFinishStatus> selectEtrQuoteFinishStatusList(Map<String, Object> param);
	
	int checkDataStatus(Map<String, Object> param);
    
}
