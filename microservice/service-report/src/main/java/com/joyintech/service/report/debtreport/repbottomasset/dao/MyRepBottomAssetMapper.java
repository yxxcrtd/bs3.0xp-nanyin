/*
 * 文件名：RepBottomAssetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repbottomasset.dao;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.joyintech.tams.common.base.dao.RepBottomAssetMapper;
import com.joyintech.tams.common.base.entity.RepBottomAsset;
import com.joyintech.framework.select.SelectOptionDto;

/**
 * 底层资产/负债信息报送信息
 * @author fanshuanghu
 * @version 1.0 2018年12月27日
 * @see RepBottomAssetMapper
 * @since 1.0
 */
@Repository
public interface MyRepBottomAssetMapper {
    /**
     * 分页查询
     * @param map 分页查询参数
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    List<RepBottomAsset> findPage(Map<String,Object> map);
    /**
     * 获取对应资管及委外资产行内资产/负债编码（资管计划代码）
     * @return 对应资管及委外资产行内资产/负债编码（资管计划代码）集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getRepAssetCode(Map<String,Object> map);
    /**
     * 获取底层资产行内资产/负债编码（底层资产代码）
     * @return 底层资产行内资产/负债编码（底层资产代码）集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getRepBottomCode2(Map<String,Object> map);

}
