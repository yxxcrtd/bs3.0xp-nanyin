package com.joyintech.service.product.nolimit.common.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.product.nolimit.common.dto.ProDesignAmtDto;


@Repository
public interface NolimitCommonMapper {
	
   /**
    * 可用额度查询
    * <功能详细描述>
    * @param param
    * @return
    * @see
    * @since 1.0
    */
    BigDecimal getShareAmt(Map<String,Object> map);

    /**
     * 非标资产额度查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<Map<String, Object>> queryLimitAmt(Map<String, Object> map);

    /**
     * 产品系列可用额度查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
	List<ProDesignAmtDto> getProDesignAmt(Map<String, Object> map);
}
