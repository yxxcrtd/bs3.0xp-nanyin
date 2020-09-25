/*
 * 文件名：FinPlanAssetCMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.plan.plancur.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.asset.plan.plancur.dto.FinPlanAssetCDto;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2019年1月7日
 * @see FinPlanAssetCMapper
 * @since 1.0
 */
@Repository
public interface FinPlanAssetCMapper {

    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<FinPlanAssetCDto> selectForPage(Map<String, Object> pageParam);
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
    List<FinPlanAssetCDto> getBasicInfoByFinprodId(Map<String, Object> param);
    /* 增加快捷录入 add by zhuyongkang 2019/10/12 start*/
String getFinprodId(String finprodMarketId);
    /* 增加快捷录入 add by zhuyongkang 2019/10/12 end*/
    /**
     * 删除标的类金融产品扩展表
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    int deleteByFinprodId(String finprodId);
    
    /**
     * 将资产投资性质字段 FIN_PRODUCT_ADD里存值
     * <功能详细描述>
     * @param finprodId
     * @param investNature
     * @return
     * @see
     * @since 1.0
     */
    int updateInvestNatureByFinprodId(@Param("finprodId")String finprodId,@Param("investNature") String investNature);

}
