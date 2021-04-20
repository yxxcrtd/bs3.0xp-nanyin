/*
 * 文件名：CfetsBondDealQuotedPriceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.bankuplink.basic.cfetsportfolioacctrela.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.bankuplink.basic.cfetsportfolioacctrela.dao.CfetsPortfolioAcctRelaMapper;
import com.joyintech.service.bankuplink.basic.cfetsportfolioacctrela.dto.CfetsPortfolioAcctRela001Dto;
import com.joyintech.tams.common.base.dao.IbuPortfolioAcctRelaMapper;
import com.joyintech.tams.common.base.entity.IbuPortfolioAcctRela;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;

/**
 * CFETS账户关联
 * 
 * @author yufei
 * @version 1.0 2020年5月12日
 * @see CfetsPortfolioAcctRelaService
 * @since 1.0
 */
@Service("backCfetsPortfolioAcctRelaService")
public class CfetsPortfolioAcctRelaService extends BaseService {

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsPortfolioAcctRelaMapper cfetsPortfolioAcctRelaMapper;

    /**
     * {@linkplain}
     */
    @Autowired
    private IbuPortfolioAcctRelaMapper ibuPortfolioAcctRelaMapper;

    /**
     * 分页查询机构信息
     * 
     * @param page 页数
     * @param length 条数
     * @param param 查询参数
     * @return 查询集合
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> selectCommonSelect(int page, int length,
                                                    Map<String, Object> param) {
        Page<SelectOptionDto> pageList = super.queryForPage(page, length,
            () -> cfetsPortfolioAcctRelaMapper.selectMstCfetsCustomerInfo(param));
        return pageList;
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param dto 实体参数
     * @return 数量
     * @see
     * @since 1.0
     */
    public int delete(CfetsPortfolioAcctRela001Dto dto) {
        return ibuPortfolioAcctRelaMapper.deleteByPrimaryKey(dto);
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param dto 实体参数
     * @return 数量
     * @see
     * @since 1.0
     */
    public int insert(CfetsPortfolioAcctRela001Dto dto) {
        return ibuPortfolioAcctRelaMapper.insert(dto);
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param dto 实体参数
     * @return 数量
     * @see
     * @since 1.0
     */
    public int update(CfetsPortfolioAcctRela001Dto dto) {
        return cfetsPortfolioAcctRelaMapper.updateByPrimaryKey(dto);
    }

    /**
     * 流程后的db操作 删除
     * 
     * @param entity db对象
     * @throws Exception 异常信息
     */
    public void doDelete(CfetsPortfolioAcctRela001Dto entity) {
        this.delete(entity);
    }

    /**
     * 流程后的db操作 修改
     * 
     * @param entity db对象
     * @throws Exception 异常信息
     */
    public void doUpdate(CfetsPortfolioAcctRela001Dto entity)
        throws Exception {
        this.update(entity);

    }

    /**
     * 流程后的db操作 插入
     * 
     * @param entity db对象
     * @throws Exception 异常信息
     */
    public void doInsert(CfetsPortfolioAcctRela001Dto entity)
        throws Exception {
        this.insert(entity);
    }

    /**
     * <功能详细描述>
     * 
     * @param page 页数
     * @param length 条数
     * @param pageParam 查询参数
     * @return 分页结果
     * @see
     * @since 1.0
     */
    @Transactional(readOnly = true)
    public Page<CfetsPortfolioAcctRela001Dto> selectForPage(int page, int length,
                                                            Map<String, Object> pageParam) {
        pageParam.put("YESORNOY", BussinessDictCodeList.是否_是.getValue());
        pageParam.put("YESORNON", BussinessDictCodeList.是否_否.getValue());
        return super.queryForPage(page, length,
            () -> cfetsPortfolioAcctRelaMapper.selectForPage(pageParam));
    }

    /**
     * 根据主键查询CFETS账户关联 <功能详细描述>
     * 
     * @param param 入参
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public CfetsPortfolioAcctRela001Dto queryInfoByPrimaryKey(Map<String, Object> param) {
        return cfetsPortfolioAcctRelaMapper.queryInfoByPrimaryKey(param);
    }
    
    /**
     * -根据组合代码和账户类型查询记录是否存在
     * @param ibuPortfolioAcctRela cfets账户关联对象
     * @return 记录数量
     */
    public int checkPortfolioIdAcctTypeExists(IbuPortfolioAcctRela ibuPortfolioAcctRela) {
    	return cfetsPortfolioAcctRelaMapper.checkPortfolioIdAcctTypeExists(ibuPortfolioAcctRela);
    }
}
