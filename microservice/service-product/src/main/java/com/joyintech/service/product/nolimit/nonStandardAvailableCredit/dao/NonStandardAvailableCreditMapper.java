package com.joyintech.service.product.nolimit.nonStandardAvailableCredit.dao;

import java.util.List;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.product.nolimit.nonStandardAvailableCredit.dto.NonStandardAvailableCreditDto;
/**
 * 非标可用额度查询
 * 〈功能详细描述〉
 * @author dongzhikang
 * @version 1.0 2018年10月11日
 * @see NonStandardAvailableCreditDto
 * @since 1.0
 */
@Repository
public interface NonStandardAvailableCreditMapper {
	  /**
     * 非标可用额度查询
     * @param pageParam
     * @return
     */
    List<NonStandardAvailableCreditDto> selectForPage(Map<String, Object> parameters)  ;
  

}
