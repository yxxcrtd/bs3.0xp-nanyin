/*
 * 文件名：RskParamReCalcMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：jjpei
 * 修改时间：2020年4月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.riskparamcalc.dao;

import com.joyintech.tams.common.base.entity.RskIndicatorParaCalcdetail;
import com.joyintech.tams.common.base.entity.RskOccupyInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 * 批处理-指标参数重新更新-Mapper
 * 
 * @author jjpei
 * @version 1.0 2020年4月17日
 * @see RskParamReCalcMapper
 * @since 1.0
 */
@Repository
public interface RskParamReCalcMapper {

    /**
     * 取得 所有预占下的实占信息
     * @param prodIdList 产品代码
     * @return 所有预占下的实占信息
     * @see
     * @since 1.0
     */
    List<RskOccupyInfo> queryOcpInfo(@Param("prodIdList") List<String> prodIdList,@Param("sysDate") Date sysDate);

    /**
     * 删除 参数计算流水
     * @param infoList 参数计算流水信息
     * @return 结果
     */
    int deleteRskIndParaDtl(@Param("infoList") List<RskIndicatorParaCalcdetail> infoList);
    
    /**
     * 删除 参数计算流水
     * @param map 参数
     * @return 结果
     */
    int deleteRskIndParaDtlTwo(Map<String, Object> map);
    
    /**
     * 从RSK_INDICATOR_PARA_CALCDETAIL表中获取交易时的tradeAmtReal
     * @param eventId
     * @return
     */
    String getTradeAmtByEventId(String eventId);
    
}
