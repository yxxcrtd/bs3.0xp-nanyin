/*
 * 文件名：ReportPageManage001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.information.config.saleagency.dao;

import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.dao.DisProdAgencyMapper;
import com.joyintech.tams.common.base.dao.DisSaleAgencyMapper;
import com.joyintech.tams.common.base.entity.DisProdAgency;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import com.joyintech.tams.disclosure.information.config.saleagency.dto.ProdSaleAgencyDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * @author zhouqiang
 * @version 1.0 2022年7月12日
 * @see SaleAgency002Mapper
 * @since 1.0
 */
@Repository(value="SaleAgency002Mapper")
public interface SaleAgency002Mapper extends DisProdAgencyMapper {

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
     * 代销机构下拉框
     *
     * @param  map
     * @return 代销机构
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> querySaleAgency(Map<String, Object> map);

    /**
     *
     * 合作机构查询
     * <功能详细描述>
     * @param pageParam pageParam
     * @return List<MstMarketInstitutionsDto>
     * @see
     * @since 1.0
     */
    List<ProdSaleAgencyDto> select(Map<String, Object> pageParam);


    int selectByProdId(String prodId);

    List<ProdSaleAgencyDto> selectProdByAgencyId(String agencyId);

    /**
     * 根据代销机构名称查询代销机构是否重复
     * @param agencyName
     * @return
     */
    int selectSaleAgencyByName(String agencyName);

    int insertBtachProdAgency(List<DisProdAgency> list);

    /**
     * 根据代销机构id删除关联产品
     * @param sequenceNo
     * @return
     */
    int deleteByAgencyId(String sequenceNo);

    /**
     * 查询所有的产品系类
     * @return
     */
    List<String> selectAllProdSeries(Map<String,Object> param);

    /**
     * 根据产品代码查询关联的代销机构
     * @param prodId
     * @return
     */
    List<ProdSaleAgencyDto> selectAgencyByProdId(String prodId);
}
