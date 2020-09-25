/*
 * 文件名：TrdPbRelation001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.1.2
 * 修改人：caoxue
 * 修改时间：2019年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.1.2
 */

package com.joyintech.service.portfolio.trdpbrelation.dao;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.trdpbrelation.dto.TrdPbRelation001Dto;


/**
 * PB编号管理Mapper
 * 〈功能详细描述〉
 * @author caoxue
 * @version 1.0 2019年12月25日
 * @see TrdPbRelation001Mapper
 * @since 1.0
 *
 */
@Repository
public interface TrdPbRelation001Mapper {
	/**
	 * 画面查询方法
	 * 
	 * @param page
	 * @param length
	 * @param pageParam 
	 * @return
	 */
	List<TrdPbRelation001Dto> selectForPage(Map<String, Object> pageParam);
	
	/**
	 * 查询PB编号关联信息
	 * @param strategyId 策略代码
	 * @return
	 */
	List<TrdPbRelation001Dto> getTrdPbRelation(TrdPbRelation001Dto entity);
	/**
	 * 导出Excel方法
	 * 
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 */
	List<TrdPbRelation001Dto> selectForExport(Map<String, Object> pageParam);

	/**
	 * 根据PB编号删除信息
	 * @param pbId
	 * @return
	 */
    int delete(@Param("seqno") String seqno);
    
    /**
     * 新建
     * @param pbId
     * @return
     */
    int insert(TrdPbRelation001Dto entity);
    
    /**
     * 存续的产品代码
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryPrdId(Map<String, Object> pageParam);
   
}
