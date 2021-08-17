package com.joyintech.service.risk.valtblimport.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.service.risk.valtblimport.dto.PreOccupActualDetailDto;
import com.joyintech.service.risk.valtblimport.dto.PreOccupationChangeDto;
import com.joyintech.service.risk.valtblimport.dto.ValTableImpRiskDto;

/**
 * 估值表导入-预占实占变更Mapper
 * @author zhangpei
 * 20201222
 */
public interface PreOccupationChangeMapper {
	/**
	 * 获取估值日期自主管理专户的汇总值
	 * @param paramMap
	 * @return
	 */
	BigDecimal getSelfManagerTotal(Map<String,Object> paramMap);
	/**
	 * 根据估值日期查询各个组合对应额自主管理专户的持仓
	 * @param paramMap
	 * @return
	 */
	List<PreOccupationChangeDto> getSelfManagerByOne(Map<String,Object> paramMap);
	/**
	 * 查询估值表里相应自主管理组合所对应的资产持仓
	 * @param paramMap
	 * @return
	 */
	List<PreOccupActualDetailDto> getValtblAllotAmout(Map<String,Object> paramMap);
	/**
	 *  查询估值表里相应自主管理组合所对应的市值
	 * @param paramMap
	 * @return
	 */
	List<PreOccupActualDetailDto> getValtblAllotAmoutForSmv(Map<String,Object> paramMap);
	/**
	 * 查询需要释放的指令信息
	 * @param paramMap
	 * @return
	 */
	List<PreOccupationChangeDto> queryNeedFreedInfo(Map<String,Object> paramMap);
	
	/**
	 * 根据委托代码，更新指令状态为‘已完成’
	 * @param dto
	 * @return
	 */
	int updateEntrustStatus (PreOccupationChangeDto dto);
	/**
	 * 插入估值表导入风险实占留痕表数据
	 * @param dto
	 * @return
	 */
	int insertValImpRisk(ValTableImpRiskDto dto);
	/**
	 * 删除估值表导入风险实占留痕表数据
	 * @param tradeId
	 * @return
	 */
	int deleteByPrimaryKeyRisk (String tradeId);
	/**
	 * 根据委外组合和导入时间查询导入的留痕表数据
	 * @param paramMap
	 * @return
	 */
	List<ValTableImpRiskDto> queryImpRiskInfo(Map<String,Object> paramMap);
 
}
