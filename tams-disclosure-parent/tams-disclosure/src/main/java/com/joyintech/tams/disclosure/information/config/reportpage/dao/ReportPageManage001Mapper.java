/*
 * 文件名：ReportPageManage001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.disclosure.information.config.reportpage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.common.base.entity.RepScreen;
import com.joyintech.tams.disclosure.information.config.reportpage.dto.RepJobRangeDto;
import com.joyintech.tams.disclosure.information.config.reportpage.dto.ReportPageManage001Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author jiangsucheng
 * @version 1.0 2018年11月7日
 * @see ReportPageManage001Mapper
 * @since 1.0
 */
@Repository
public interface ReportPageManage001Mapper {

    List<SuggestDto> getPageName(String param);

    List<ReportPageManage001Dto> findPage(Map<String, Object> param);
    List<RepScreen> getRepScreenList(String repId);

    void updateReportStatus(Map<String, Object> param);

    int checkSave(Map<String, Object> checkMap);

    void deleteRepScreen(Map<String, Object> param);
    //删除记录
  	void deleteFromRepScreen(String repId);

	List<ReportPageManage001Dto> getReportInfo();
    
	ReportPageManage001Dto getReportInputsNew(Map<String, Object> pageParam);
	/**
     * 根据报表编号查询规则范围数据
     * @param repId
     * @return
     */
    List<RepJobRangeDto> findRulePage(String repId);
    /**
     * 根据报表编号删除规则范围数据
     * @param repId
     * @return
     * @see
     * @since 1.0
     */
    int deleteRangeInfo(String jobId);
}
