/*
 * 文件名：ReportPageManage001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.information.config.prodpartner.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.dao.DisProdPartnerMapper;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * @author zhouqiang
 * @version 1.0 2022年7月12日
 * @see ProdPartner001Mapper
 * @since 1.0
 */
@Repository(value="ProdPartner001Mapper")
public interface ProdPartner001Mapper extends DisProdPartnerMapper {

    /**
     * 产品代码联想下拉框
     *
     * @param  map
     * @return 产品信息
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryProdIdInfo(Map<String, Object> map);

    /**
     *
     * 合作机构查询
     * <功能详细描述>
     * @param pageParam pageParam
     * @return List<MstMarketInstitutionsDto>
     * @see
     * @since 1.0
     */
    List<ProdPartnerDto> select(Map<String, Object> pageParam);

    /**
     * 导出选中的数据
     * @param investList
     * @return
     */
    List<ProdPartnerDto> selectForExport(@Param("list") List<ProdPartnerDto> investList);

    /**
     * 根据产品代码查询产品
     * @param prodId
     * @return
     */
    List<ProdPartnerDto> selectPartnerByProdId(String prodId);

    /**
     *
     * 导入（新建）
     * <功能详细描述>
     * @param listN 导入数据
     * @return 导入结果
     * @see
     * @since 1.0
     */
    int insertBtachEquityInfo(@Param("listN") List<ProdPartnerDto> listN);

    /**
     *
     * 导入（删除）
     * <功能详细描述>
     * @param listA 导入数据
     * @return 导入结果
     * @see
     * @since 1.0
     */
    int deleteBtachEquityInfo(@Param("listA") List<ProdPartnerDto> listA);

    int selectByProdId(String prodId);
}
