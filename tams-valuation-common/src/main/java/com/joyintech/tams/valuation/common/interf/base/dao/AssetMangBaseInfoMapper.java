/*
 * 文件名：AssetMangBaseInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年6月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.valuation.common.interf.base.dto.TargetFinProduct;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateColMatchDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangwei1
 * @version 1.0 2018年6月21日
 * @see AssetMangBaseInfoMapper
 * @since 1.0
 */
@Repository
public interface AssetMangBaseInfoMapper {

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param tempList 
     * @param string
     * @return
     * @see
     * @since 1.0
     */
    List<TemplateOrderInfoReaderResult> queryTempletDetail(@Param("orderNo") String orderNo, @Param("tempList")List<String> tempList);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param tmpEName
     * @return
     * @see
     * @since 1.0
     */
    List<TemplateOrderInfoReaderDTO> queryTempletCheckDetail(@Param("tmpEName")String tmpEName);

    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param tmpEName
     * @param dtoName
     * @return
     * @see
     * @since 1.0
     */
    List<TemplateColMatchDTO> queryTempletDtoMatchInfo(@Param("tmpEName") String tmpEName,
                                                       @Param("dtoName") String dtoName);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<FinProduct> queryProduct();   
    
    /**
     * 查询交易类标的信息
     * add by wujie on 20181229 remark:查询回购、拆借、债券借贷标的信息
     */
    List<TargetFinProduct> queryTargetFinProduct();
    
}
