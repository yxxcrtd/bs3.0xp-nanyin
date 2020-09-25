package com.joyintech.service.portfolio.ptlsubfundsmange.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.ptlsubfundsmange.dto.PtlSubFundsMangeDto;
/**
 * 
 * @author pancz
 *  注解
 */
@Repository
public interface PtlSubFundsMangeMapper {
	
	

    /**
	 * 产品名称联想下拉框
	 *
	 * @param param 参数map
	 * @return 产品信息
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto> queryProdNameInfo(Map<String, Object> map);
	
	 /**
		 * 产品名称联想下拉框
		 *
		 * @param param 参数map
		 * @return 产品信息
		 * @see
		 * @since 1.0
		 */
		List<SelectOptionDto> queryProdIdInfo(Map<String, Object> map);
		
		/**
		 * 流程中查询
		 * @param params
		 * @return
		 */
	    List<PtlSubFundsMangeDto> findPage01(Map<String, Object> params);
		/**
		 * 有效时查询
		 * @param params
		 * @return
		 */
	    List<PtlSubFundsMangeDto> findPage02(Map<String, Object> params);
	    
	    /**
	     * 待处理查询汇总
	     * @param params
	     * @return
	     */
	    List<PtlSubFundsMangeDto> findPage03(Map<String, Object> params);
	    
	    
	    /**
	     * 分配状态为空时
	     * @param params
	     * @return
	     */
	    List<PtlSubFundsMangeDto> findPage04(Map<String, Object> params);
	    
	    /**
	     * 待处理流水List查询
	     */
	    List<String> findPage03List(Map<String, Object> params);
	    
	    /**
	     * 待处理查询明细
	     * @param params
	     * @return
	     */
	    List<PtlSubFundsMangeDto> findPage03mx(Map<String, Object> params);
	    
	    
}
