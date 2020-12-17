/*
 * 文件名：ResetBaseRuleInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.mproddivreindealgen.dao;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.batch.mproddivreindealgen.dto.MProdDivReinDealDelDto;
import com.joyintech.tams.invest.batch.mproddivreindealgen.dto.MProdDivReinDealGenReaderResult;


/**
 * 业绩基准重置
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月16日
 * @see MProdDivReinDealGenMapper
 * @since 1.0
 */
@Repository
public interface MProdDivReinDealGenMapper {

    /**
     * 查询待删除的 红利再投交易
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    List<MProdDivReinDealDelDto> selectTradeInfoForDel(Map<String,Object> paramMap);
    

    /**
     * 查询需要处理 红利再投信息
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    List<MProdDivReinDealGenReaderResult> selectProdDivReinInfo(Map<String,Object> paramMap);
    /**
     * 重新跑批时删除组合持仓流水信息
     * @param paramMap 传入条件
     * @return
     */
    int deletePtlSecPositionFlow(Map<String,Object> paramMap);

    // add by mashukai TISHKBS-1640  高频开放货币型产品跑批过滤节假日 start
    /**
     * 查询非货币型高频开放产品
     * <功能详细描述>
     * @param productIdList
     * @return
     * @see
     * @since 1.0
     */
    List<String> selectProdList(List<String> productIdList);

    /**
     * 查询是否是中国法定节假日
     *
     * @param paraMap 参数
     * @return 节假日列表
     */
    List<String> getIsHoliday(Map<String, Object> paraMap);
    // add by mashukai TISHKBS-1640  高频开放货币型产品跑批过滤节假日 end

}