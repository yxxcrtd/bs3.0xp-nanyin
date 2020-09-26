package com.joyintech.tams.valuation.report.common.infodisclosure.infodisclosuregenandsend.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepInstallLog;
import com.joyintech.tams.common.base.entity.RepScreen;
import com.joyintech.tams.valuation.report.common.infodisclosure.infodisclosuregenandsend.entity.InfoDisclosureGenAndSendEntity;


/**
 * 信息披露生成及发送核心批处理mappermapper
 * @author wangmin
 * @version 1.0 2018年11月28日
 * @see InfoDisclosureGenAndSendMapper
 * @since 1.0
 */
@Repository("valuationInfoDisclosureGenAndSendMapper")
public interface InfoDisclosureGenAndSendMapper {
	/**
	 * 获取所有未执行与未生成状态数据
	 * @return list
	 */
	List<InfoDisclosureGenAndSendEntity> getAllRepInstallFileList();
	/**
     * 
     * 获取指定报表的查询条件
     * <功能详细描述>
     * @param repId
     * @return
     * @see
     * @since 1.0
     */
    List<RepScreen> getRepScreenList(String repId);
    /**
     * 更新批处理组装信息表状态
     * @param infoDisclosureGenAndSendEntity 条件
     * @return
     */
    int updateRepInstallStatus(InfoDisclosureGenAndSendEntity infoDisclosureGenAndSendEntity);
    /**
     * 通过jobSeq更新日志表
     * @param repInstallLog 日志dto
     * @return 
     */
    int updateByJobSeqSelective(RepInstallLog repInstallLog);
}
