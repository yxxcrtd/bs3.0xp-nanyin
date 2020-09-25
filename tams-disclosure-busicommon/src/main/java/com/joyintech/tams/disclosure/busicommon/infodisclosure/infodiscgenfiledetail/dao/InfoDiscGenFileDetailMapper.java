package com.joyintech.tams.disclosure.busicommon.infodisclosure.infodiscgenfiledetail.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.base.entity.RepBatchDateStatus;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodiscgenfiledetail.entity.InfoDisclosureReadAndGenEntity;

public interface InfoDiscGenFileDetailMapper {
	/**
	 * 通过执行状态与执行标志查询所有有效的日志主表信息
	 * @param execStatus 执行状态
	 * @param execFlag   执行标志
	 * @return 满足条件的集合
	 */
	List<InfoDisclosureReadAndGenEntity> queryAllPreInstallLog(@Param("execStatus") String execStatus, @Param("execFlag") String execFlag);
	/**
	 * 通过sourceId获取最大id
	 * @param sourceId 适配器编号
	 * @return
	 */
	int queryMaxIdBySourceId(int sourceid);
	
	/**
     * 获取最大的数据日期
     * @return
     */
    String queryMaxIdByFromZG();
	
	/**
	 * 通过sourceId获取最大id
	 * @param sourceId 适配器编号
	 * @return
	 */
	RepBatchDateStatus queryStatusMaxIdBySourceId(String sourceid);
	
	/**
     * 通过sourceId获取最大id
     * @param sourceId 适配器编号
     * @return
     */
    int queryExecDateisReady(Date execDate);
    
    /**
     * 
     * 获取jixu确认数据
     * <功能详细描述>
     * @param execDate
     * @return
     * @see
     * @since 1.0
     */
    int queryAdptDateisReady(@Param("execDate")String execDate,@Param("execflag")String execflag);
	
    /**
     * 
     * 获取jixu确认数据 文件方式
     * <功能详细描述>
     * @param execDate
     * @return
     * @see
     * @since 1.0
     */
    int queryAdptDateisReadyFile(@Param("execDate")String execDate,@Param("execflag")String execflag);
}
