/*
 * 文件名：CeAssetGroupManagementService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditrisklimitmanagement.objectlimitoccupation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditrisklimitmanagement.objectlimitoccupation.dao.ObjectLimitOccupationMapper;
import com.joyintech.service.risk.creditrisklimitmanagement.objectlimitoccupation.dto.ObjectLimitOccupationDto;
import com.joyintech.tams.common.base.dao.CreditUsingCustomerAjustMapper;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.CreditUsingCustomerAjust;

/**
 * 
 * 主体额度占用调整
 * @author chengzhong
 * @version 1.0 2020年02月26日
 * @see ObjectLimitOccupationMapper
 * @since 1.0
 */
@Service
public class ObjectLimitOccupationService extends BaseService {
    
    /**
     * ObjectLimitOccupationMapper
     */
    @Autowired
    private ObjectLimitOccupationMapper objectLimitOccupationMapper;

    
    /**
     * CreditUsingCustomerAjustMapper
     */
    @Autowired
    private CreditUsingCustomerAjustMapper creditUsingCustomerAjustMapper;
    
    
    /**
     * <一句话功能简述>
     * 查询系列明细导出数据
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
	@SuppressWarnings("unchecked")
	public Page<ObjectLimitOccupationDto> selectForPage(int page, int length,
                                                            Map<String, Object> param) throws Exception {
        return super.queryForPage(page, length, () -> objectLimitOccupationMapper.selectForPage(param));
    }

    /**
     * 主体名称：下拉框
     *
     * @param
     * @return 资产代码：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public Page<SelectOptionDto> getObject(int page, int length, Map<String, Object> param) throws Exception {
        return this.queryForPage(page, length,
                () -> objectLimitOccupationMapper.getObject(param));
    }
    
    /**
     * 查询操作人员
     * @param pageParam
     * @param page
     * @param length
     * @return
     */
    public Page<SelectOptionDto> selectUserName(Map<String, Object> pageParam,int page,int length) {
        return this.queryForPageForSelectReturn(page, length, ()-> objectLimitOccupationMapper.selectByUserName(pageParam));
    }
    
    /**
     * 插入
     * @param entity
     * @return
     * @since 1.0
     */
    public void insert(CreditUsingCustomerAjust entity) {
    	creditUsingCustomerAjustMapper.insert(entity);
    }
    
    /**
     * 插入
     * @param entity
     * @return
     * @since 1.0
     */
    public void update(CreditUsingCustomerAjust entity) {
    	creditUsingCustomerAjustMapper.updateByPrimaryKey(entity);
    }
    
    /**
     * 插入
     * @param entity
     * @return
     * @since 1.0
     */
    public void delete(CreditUsingCustomerAjust entity) {
    	creditUsingCustomerAjustMapper.deleteByPrimaryKey(entity.getSeqno());
    }  

    /**
     * <一句话功能简述>
     * 查询主体调整历史数据
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
	@SuppressWarnings("unchecked")
	public Page<ObjectLimitOccupationDto> selectHistory(int page, int length,
                                                            Map<String, Object> param) throws Exception {
        return super.queryForPage(page, length, () -> objectLimitOccupationMapper.selectHistory(param));
    }
	
	public List<CreditLines> selectCreditLine(Map<String, Object> param) throws Exception {
		return objectLimitOccupationMapper.selectCreditLine(param);
	}
}
