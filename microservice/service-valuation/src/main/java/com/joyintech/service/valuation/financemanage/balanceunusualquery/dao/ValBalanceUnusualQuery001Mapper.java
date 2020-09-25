/*
 * 文件名：ValBalanceUnusualQuery001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年1月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.financemanage.balanceunusualquery.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.financemanage.balanceunusualquery.dto.QueryDto;
import com.joyintech.service.valuation.financemanage.balanceunusualquery.dto.ValBalanceUnusualQuery001Dto;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author HuangJian
 * @version 1.0 2019年1月31日
 * @see ValBalanceUnusualQuery001
 * @since 1.0
 */
@Repository(value="serviceValBalanceUnusualQuery001Mapper")
public interface ValBalanceUnusualQuery001Mapper {
    
    
//    List<ValBalanceUnusualQuery001Dto> queryPageList(Map<String, Object> params);
    List<ValBalanceUnusualQuery001Dto> queryPageList(@Param("list")List<QueryDto> list,@Param("qrydate")String qrydate);
    
    
    List<ValBalanceUnusualQuery001Dto> queryPageListo(QueryDto list);
    
    
    List<ValBalanceUnusualQuery001Dto> getEveningUpAssetsBond(Map<String, Object> params);
    
    
    List<ValBalanceUnusualQuery001Dto> getEveningUpAssetsNoBond(Map<String, Object> params);
    
    
    List<String> getAssetNoZero(Map<String, Object> params);

}
