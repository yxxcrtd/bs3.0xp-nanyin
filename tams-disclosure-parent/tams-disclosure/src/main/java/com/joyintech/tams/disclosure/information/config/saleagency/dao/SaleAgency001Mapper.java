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

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.tams.common.base.dao.DisProdPartnerMapper;
import com.joyintech.tams.common.base.dao.DisSaleAgencyMapper;
import com.joyintech.tams.common.base.entity.DisSaleAgency;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import com.joyintech.tams.disclosure.information.config.saleagency.dto.ProdSaleAgencyDto;
import com.joyintech.tams.disclosure.information.config.saleagency.dto.SaleAgencyDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * @author zhouqiang
 * @version 1.0 2022年7月12日
 * @see SaleAgency001Mapper
 * @since 1.0
 */
@Repository(value="SaleAgency001Mapper")
public interface SaleAgency001Mapper extends DisSaleAgencyMapper {

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
     * 代销机构查询
     * <功能详细描述>
     * @param pageParam pageParam
     * @return List<MstMarketInstitutionsDto>
     * @see
     * @since 1.0
     */
    List<SaleAgencyDto> select(Map<String, Object> pageParam);


    /**
     * 根据代销机构id查询关联产品
     * @param sequenceNo
     * @return
     */
    List<ProdSaleAgencyDto> selectProdByAgencyId(String sequenceNo);

    /**
     * 构建产品树：根据条件查询符合条件的产品
     * @param
     * @return
     */
    List<TreeNode> selectProdInfo();

    /**
     * 查询系统中已经维护的产品系列
     * @param map
     * @return
     */
    List<SelectOptionDto> queryProdSeries(Map<String, Object> map);

    int insertEX(DisSaleAgency disSaleAgency);
}
