package com.joyintech.service.risk.creditmanagement.creditrightsmanagement.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.risk.creditmanagement.creditrightsmanagement.dto.CreditRightsManagementDto;

/**
 * 风险权数管理
 * @author huwenbin
 *
 */
@Repository
public interface CreditRightsManagementMapper {
	
	/**
	 * 
	 * 页面查询
	 * <功能详细描述>
	 * @param pageParam Map<String, Object>
	 * @return List<CreditRightsManagementDto>
	 * @see
	 * @since 1.0
	 */
    List<CreditRightsManagementDto> select(Map<String, Object> pageParam);
    
    /**
     * 
     * 双击、编辑查看详情
     * <功能详细描述>
     * @param seqno String
     * @return CreditRightsManagementDto
     * @see
     * @since 1.0
     */
    CreditRightsManagementDto selectDetail(String seqno);
    
    /**
     * 
     * 同一个资产二级类型、同一生效日只能有一条数据
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return List<CreditRightsManagementDto>
     * @see
     * @since 1.0
     */
    List<CreditRightsManagementDto> checkalreadyhave(Map<String, Object> pageParam);
	
}
