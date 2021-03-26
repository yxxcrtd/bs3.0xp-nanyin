package com.joyintech.tams.svreport.chb.common.dao;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.ChbPrdCustNumIfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbCustomerNumCheckMapper {


    /**
     * 查询一二期的产品代码和客户数
     * @param checkDate
     * @return
     */
    List<ChbPrdCustNumIfo>    getraiseCustNum(@Param("checkDate")Date checkDate, @Param("prodId")String prodId);


    /**
     * 查询三期的产品代码和客户数
     * @return
     */
    List<ChbPrdCustNumIfo>   getraiseCustNumThree(@Param("prodIdList")List<String> prodIdList, @Param("checkDate")Date checkDate);
}