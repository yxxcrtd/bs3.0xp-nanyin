package com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParam003Dto;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParam005Dto;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParamDto;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.dto.SqlDto;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dto.RskSupervisionIndicatorDto;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 指标参数管理
 *
 * @author 刘伟
 * @version 1.0 2018年10月23日
 * @see RskSupervisionIndicatorParamMapper
 * @since 1.0
 */
@Repository
public interface RskSupervisionIndicatorParamMapper {
    /**
     * 查询界面查询事件
     *
     * @param pageParam 页面参数
     * @see
     * @since 1.0
     */
    List<RskSupervisionIndicatorParamDto> select(Map<String, Object> pageParam);

    /**
     * 修改或查看界面
     *
     * @param paraCode 参数代码
     * @return 结果
     */
    List<RskSupervisionIndicatorParamDto> selectInfo(String paraCode);

    /**
     * 查询参数代码
     *
     * @param paraCode 参数代码
     * @return 结果
     */
    int selectParaCode(String paraCode);

    /**
     * 监督指标明细表删除
     *
     * @param paraCode 参数代码
     * @return 结果
     */
    int delete(String paraCode);

    /**
     * 删除参数与事件的关系
     *
     * @param paraCode 参数代码
     * @return 结果
     */
    int deleteEventRelPara(String paraCode);

    /**
     * 是否在监督指标里配置
     *
     * @param paraCode 参数代码
     * @return 结果
     */
    int isConfigure(String paraCode);
    
    /**
     * 查看已配指标的资产类型
     * @param paraCode 参数代码
     * @return 结果
     */
    List<RskSupervisionIndicatorDto> selectIndHavedType(String paraCode);

	int deleteFixRecode(Map<String, Object> param);

	List<RskSupervisionIndicatorParam003Dto> selectFixRecode(Map<String, Object> param);
	
	List<RskSupervisionIndicatorParam003Dto> selectFixRecod2(Map<String, Object> param);
	
	Integer isExistParaName(Map<String, Object> param);

	List<RskSupervisionIndicatorParam005Dto> selectCustomParam(String paraCode);

	List<SqlDto> getSql(String paraCode);

	List<String> getModelValue(String modelCode);
	
	List<SelectOptionDto> getInstitutionId(Map<String, Object> param);
}