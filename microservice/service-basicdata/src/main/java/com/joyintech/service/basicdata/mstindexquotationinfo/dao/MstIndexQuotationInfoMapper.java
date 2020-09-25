/*
 * 文件名：MstIndexQuotationInfo001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.mstindexquotationinfo.dao;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.basicdata.mstindexquotationinfo.dto.MstIndexQuotationInfo001Dto;



/**
 * 指数行情信息mapper接口
 * @author fanshuanghu
 * @version 1.0 2018年8月14日
 * @see MstIndexQuotationInfoMapper
 * @since 1.0
 */
@Repository
public interface MstIndexQuotationInfoMapper {
    /**
     * 进行分页查询
     * @param map 参数集合
     * @return List<MstIndexQuotationInfo001Dto> 实体类集合
     * @see
     * @since 1.0
     */
    List<MstIndexQuotationInfo001Dto> queryForPage(Map<String,Object> map);
}
