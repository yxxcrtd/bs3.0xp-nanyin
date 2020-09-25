/**
 * 
 */
package com.joyintech.tams.invest.common.service.dao;

import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.springframework.stereotype.Repository;

/**
 * @author ex_kjkfzx_heyy
 *
 */
@Repository
public interface MapperDelegateMapper {

    @DeleteProvider(type = MapperDelegateSqlProvider.class, method = "deleteBySpecificKey")
    int deleteBySpecificKey(Map<String, Object> param);
}
