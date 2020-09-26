package com.joyintech.tams.report.system.subinfobatchlog.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbSysUser;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;

@Repository
public interface ChbSubinfoAutoBatchLogMapper {
    int deleteByPrimaryKey(String batchExeId);

    int insert(ChbSubinfoBatchLog record);

    int insertSelective(ChbSubinfoBatchLog record);

    int insertBasicByBasicInfo(ChbSubinfoBatchLog record);

    ChbSubinfoBatchLog selectByPrimaryKey(String batchExeId);

    int updateByPrimaryKeySelective(ChbSubinfoBatchLog record);

    int updateByPrimaryKey(ChbSubinfoBatchLog record);
    
    /**
     * 查询日志结果列表
     */
    List<ChbSubinfoBatchLog> queryBatchResult(Map<String,Object> map);

    /**
     * 查询用户列表
     */
	List<ChbSysUser> selectUserList();

	/**
	 * 根据处理状态查询日志
	 */
	List<ChbSubinfoBatchLog> selectByDealStatus(ChbSubinfoBatchLog status);
}