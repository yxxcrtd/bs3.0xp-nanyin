/*
 * 文件名：FinPlanAssetCMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.basicdata.asset.plan.profitproject.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basicdata.asset.plan.plancur.dto.FinPlanAssetCDto;
import com.joyintech.service.valuation.basicdata.asset.plan.profitproject.dto.ValProfitprojectDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2019年1月7日
 * @see ValProfitprojecMapper
 * @since 1.0
 */
@Repository
public interface ValProfitprojecMapper {

    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<ValProfitprojectDto> selectForPage(Map<String, Object> pageParam);
    /*增加资产代码重复校验 add by zhuyongkang 20191021 end*/
    /**
     * 查询市场代码
     *
     * @param finprodMarketId 市场代码
     * @return 市场代码
     * @see
     * @since 1.0
     */
    int selectFinprodMarketId(String finprodMarketId);
    /*增加资产代码重复校验 add by zhuyongkang 20191021 end*/
    /**
     * 根据编号查询名称
     * <功能详细描述>
     * @param custormId
     * @return
     * @see
     * @since 1.0
     */
    String getName(String custormId);

    /**
     * 编辑页面初始化
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<ValProfitprojectDto> getBasicInfoByFinprodId(Map<String, Object> param);
    /* 增加快捷录入 add by zhuyongkang 2019/10/12 start*/
    String getFinprodId(String finprodMarketId);
    /* 增加快捷录入 add by zhuyongkang 2019/10/12 end*/

    List<SelectOptionDto> getCustomer(Map<String, Object> param);

    List<SuggestDto> getAssetId(Map<String, Object> param);
    /**
     * 查询日期参数是否节假日
     *
     * @param dateStr 日期字符串
     * @param calId 日历ID
     * @return 是否节假日
     */
    Integer selectDealCalendarHolidayCount(@Param("dateStr") String dateStr, @Param("calId") String calId);

    /**
     * 查询资产下拉框
     * @param param
     * @return
     */
    List<SelectOptionDto> getAssetSelect(Map<String, Object> param);

}
