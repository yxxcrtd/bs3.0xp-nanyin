/*
 * 文件名：CfetsPortfolioAcctRelaMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.bankuplink.basic.cfetsportfolioacctrela.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.bankuplink.basic.cfetsportfolioacctrela.dto.CfetsPortfolioAcctRela001Dto;
import com.joyintech.tams.common.base.entity.IbuPortfolioAcctRela;

/**
 * CFETS账户关联
 * 
 * @author yufei
 * @version 1.0 2020年5月12日
 * @see CfetsPortfolioAcctRelaMapper
 * @since 1.0
 */
@Repository
public interface CfetsPortfolioAcctRelaMapper {

    /**
     * CFETS账户关联查询
     * 
     * @param pageParam 查询参数
     * @return 查询结果
     */
    List<CfetsPortfolioAcctRela001Dto> selectForPage(Map<String, Object> pageParam);

    /**
     * 根据主键查询CFETS账户关联 <功能详细描述>
     * 
     * @param param 入参
     * @return 查询结果
     * @see
     * @since 1.0
     */
    CfetsPortfolioAcctRela001Dto queryInfoByPrimaryKey(Map<String, Object> param);

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param param 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> selectMstCfetsCustomerInfo(Map<String, Object> param);

    int updateByPrimaryKey(CfetsPortfolioAcctRela001Dto record);
    
    /**
     * -根据组合代码和账户类型查询记录是否存在
     * @param ibuPortfolioAcctRela cfets账户关联对象
     * @return 记录数量
     */
    int checkPortfolioIdAcctTypeExists(IbuPortfolioAcctRela ibuPortfolioAcctRela);
}
