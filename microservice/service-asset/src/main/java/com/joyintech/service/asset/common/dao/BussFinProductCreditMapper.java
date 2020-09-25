package com.joyintech.service.asset.common.dao;

import com.joyintech.tams.common.base.entity.FinProductCredit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 增信信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年10月11日
 * @see BussFinProductCreditMapper
 * @since 1.0
 */
@Repository
public interface BussFinProductCreditMapper {

    List<FinProductCredit> getFinProductCreditByProductId(@Param("finprodId") String finprodId);

    void deleteFinProductCreditByProductId(@Param("finprodId") String finprodId);

}
