package com.joyintech.service.disclosure.information.manage.workmanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.service.disclosure.information.manage.workmanage.dto.ReportWorkManage002Dto;
import com.joyintech.service.disclosure.information.manage.workmanage.dto.ReportWorkManage003Dto;
import com.joyintech.service.disclosure.information.manage.workmanage.dto.ReportWorkManage004Dto;
import com.joyintech.framework.select.SelectOptionDto;
/**
 * 
 * 作业任务管理-新建Mapper
 * @author wanghb
 * @version 1.0 2018年12月6日
 * @see ReportWorkManage002Mapper
 * @since 1.0
 */
@Repository(value="serviceReportWorkManage002Mapper")
public interface ReportWorkManage002Mapper {
	/**
	 * 根据作业编号查询报表作业范围数据
	 * @param jobId
	 * @return
	 */
	List<ReportWorkManage004Dto> findRulePage(String jobId);
	/**
	 * 根据作业编号查询报表作业屏蔽产品数据
	 * @param jobId
	 * @return
	 */
	List<ReportWorkManage003Dto> findProdPage(String jobId);
	/**
	 * 查询作业规则表
	 * @return
	 */
	List<SelectOptionDto> getRepRule();
	/**
     * 根据作业编号删除报表作业屏蔽产品
     * @param repId
     * @return
     * @see
     * @since 1.0
     */
    int deleteExcludeProInfo(String jobId);
    /**
     * 根据作业编号删除作业范围数据
     * @param repId
     * @return
     * @see
     * @since 1.0
     */
    int deleteRangeInfo(String jobId);
    /**
     * 查询是否已经存在该作业编号或名称
     * @param repId
     * @return
     * @see
     * @since 1.0
     */
    Integer selectJobByIdAndName(ReportWorkManage002Dto dto);
    /**
	 * 根据作业编号查询报表作业信息
	 * @param jobId
	 * @return
	 */
	ReportWorkManage002Dto getReportWorkManage002Dto(String jobId);
}
