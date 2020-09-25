package com.joyintech.service.basicdata.mstcommissionset.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.mstcommissionset.dto.MstCommissionSetDto;

/**
 * 佣金设置
 * @author 裴家俊
 * @version 1.0 2019年3月22日
 * @see MstCommissionSetMapper
 * @since 1.0
 */
@Repository
public interface MstCommissionSetMapper {

	/**
	 * 根据条件查找数据
	 * @param pageParam
	 * @return 返回数据库里符合条件的数据
	 */
	List<MstCommissionSetDto> selectForPage(Map<String, Object> pageParam);
	
	MstCommissionSetDto getBussinessEntityBySeqNo(Map<String, Object> pageParam);

	/**
     * 证券账户联想下拉框
     * @return List<SuggestDto>
     */
    List<SelectOptionDto> getSecAcctName(Map<String, Object> map);
    
    int searchRepeatBySecPs(Map<String, Object> map);
    
    List<String> searchRepeatBySecPs1(Map<String, Object> map);
    
    String getSecRegistOrg(String secAcctId);
    /**
     * 删除佣金设置
     * @return int
     */ 
    int deleteByBatchSeq(String batchSeq);
    /**
     * 删除佣金设置明细
     * @return int
     */ 
    int deleteDtlByBatchSeq(String batchSeq);
}
