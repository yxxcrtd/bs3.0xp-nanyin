/*
 * 文件名：ValFinprodTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.basedata.productinfoquery.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.basedata.productinfoquery.dto.ValProductInfoAcctDto;
import com.joyintech.service.valuation.basedata.productinfoquery.dto.ValProductInfoFeeDto;
import com.joyintech.service.valuation.basedata.productinfoquery.dto.ValProductInfoQuery001Dto;
import com.joyintech.tams.common.base.entity.BokBooksetExt;

/**
 * 金融产品交易查询
 * 
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValProductInfoQuery001Mapper
 * @since 1.0
 */
@Repository(value="serviceValProductInfoQuery001Mapper")
public interface ValProductInfoQuery001Mapper {

    /**
     * 分页查询
     * 
     * @param params 参数
     * @return 金融产品交易结果集
     * @see
     * @since 1.0
     */
    List<ValProductInfoQuery001Dto> queryPageList(Map<String, Object> params);
    
    /**
     * 
     * @param params 参数
     * @return 金融产品交易结果集
     * @see
     * @since 1.0
     */
    List<ValProductInfoQuery001Dto> queryPageLists(Map<String, Object> params);

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodId(Map<String, Object> map);

    /**
     * 根据产品findprodid获取产品详细信息 <功能详细描述>
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    ValProductInfoQuery001Dto queryProductByfinprodId(Map<String, Object> map);

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<ValProductInfoFeeDto> queryFeeList(Map<String, Object> pageParam);

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<ValProductInfoAcctDto> getFinprodAcct(Map<String, Object> map);

    /**
     * 查看费用调节
     * 
     * @param pageParam
     * @return
     */
    List<ValProductInfoFeeDto> selectFeeAdjust(Map<String, Object> pageParam);

    /**
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * 
     * @param pageParam
     * @return
     */
    List<ValProductInfoFeeDto> queryfloatFeeList(Map<String, Object> pageParam);
   
    /**
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * 
     * @param pageParam
     * @return
     */
    List<ValProductInfoFeeDto> queryfloatFeeList1(Map<String, Object> pageParam);
    
    /**
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * 
     * @param pageParam
     * @return
     */
    List<ValProductInfoFeeDto> queryfloatFeeList2(Map<String, Object> pageParam);
    
    /**
     * 查询账套配置表
     * @param finprodId 金融产品代码
     * @param finprodType 金融产品类型
     * @return
     * @see
     * @since 1.0
     */
    List<BokBooksetExt> queryBooksetExt(Map<String, Object> map);
    /**
     * 查询账套配置表
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId 金融产品代码
     * @return
     */
    List<BokBooksetExt> queryBooksetExtByKey(Map<String, Object> map);
    
    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<ValProductInfoAcctDto> getSecAccount(Map<String, Object> map);
    
    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<ValProductInfoAcctDto> findBankdesc(Map<String, Object> map);    

}
