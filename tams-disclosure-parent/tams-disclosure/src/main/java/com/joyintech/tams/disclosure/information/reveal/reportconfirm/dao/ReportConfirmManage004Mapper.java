package com.joyintech.tams.disclosure.information.reveal.reportconfirm.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ReportConfirmManage004Mapper {
	/**
     * 
     * 系统生成时查询相同的报表
     * @return count
     * @see
     * @since 1.0
     */
    int querySameCount(Map<String, Object>params);
    
    /**
     *
     * 删除相同的报表
     * @return count
     * @see
     * @since 1.0
     */
    int updateDataStatus(Map<String, Object>params);
}
