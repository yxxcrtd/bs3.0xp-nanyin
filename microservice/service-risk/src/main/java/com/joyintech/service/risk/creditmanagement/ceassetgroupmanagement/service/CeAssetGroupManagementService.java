/*
 * 文件名：CeAssetGroupManagementService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.CreditAssetpoolMapper;
import com.joyintech.tams.common.base.entity.CreditAssetpool;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.dao.CeAssetGroupManagementMapper;
import com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.dto.CeAssetGroupManagement001Dto;
import com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.dto.CeAssetGroupManagement002Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2019年1月11日
 * @see CeAssetGroupManagementService
 * @since 1.0
 */
@Service
public class CeAssetGroupManagementService extends BaseService<CeAssetGroupManagement001Dto>{
    
    /**
     * CreditAssetpoolMapper
     */
    @Autowired
    private CreditAssetpoolMapper creditAssetpoolMapper;
    
    /**
     * 
     */
    @Autowired CeAssetGroupManagementMapper ceAssetGroupManagementMapper;

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
    public Page<CeAssetGroupManagement001Dto> selectForPage(int page, int length,
                                                            Map<String, Object> param) throws Exception {
        return super.queryForPage(page, length, () -> ceAssetGroupManagementMapper.selectForPage(param));
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void insert(CeAssetGroupManagement001Dto dto) {
        CreditAssetpool record = new CreditAssetpool();
        record.setSeqno(dto.getSeqno());
        record.setAssetpoolName(dto.getAssetpoolName());
		//多级法人改造 add by jingqiongqiong 20200616
		//当前用户所属机构代码
		String createInst =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
		record.setInstitutionCode(createInst);
		//多级法人改造 add by jingqiongqiong 20200616
        creditAssetpoolMapper.insert(record);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void update(CeAssetGroupManagement001Dto dto) {
        CreditAssetpool record = new CreditAssetpool();
        record.setSeqno(dto.getSeqno());
        record.setAssetpoolName(dto.getAssetpoolName());
		//多级法人改造 add by jingqiongqiong 20200616
		//当前用户所属机构代码
		String createInst =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
		record.setInstitutionCode(createInst);
		//多级法人改造 add by jingqiongqiong 20200616
        creditAssetpoolMapper.updateByPrimaryKey(record);
        
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int delete(CeAssetGroupManagement001Dto dto) {
        return creditAssetpoolMapper.deleteByPrimaryKey(dto.getSeqno());
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param seqno
     * @return
     * @see
     * @since 1.0
     */
    public CeAssetGroupManagement001Dto getBusinessInfoBySeqno(String seqno) {
        return ceAssetGroupManagementMapper.getBusinessInfoBySeqno(seqno);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<CeAssetGroupManagement001Dto> getAssetName(Map<String, Object> param) {
        return ceAssetGroupManagementMapper.getAssetName(param);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryAssetInfo(Map<String, Object> map) { 
        List<SelectOptionDto> assetInfoList = ceAssetGroupManagementMapper.queryAssetInfo(map);
//        // wangyang1 二级法人改造 2020年7月18日 start
//        assetInfoList.addAll(ceAssetGroupManagementMapper.queryAssetInfoPub(map));
//        // wangyang1 二级法人改造 2020年7月18日 start
        return assetInfoList;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public List<CeAssetGroupManagement002Dto> selectAssetInfo(Map<String, Object> param)
        throws Exception {
        return ceAssetGroupManagementMapper.selectAssetInfo(param);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void insertAssetPool(CeAssetGroupManagement002Dto dto) {
        List<CeAssetGroupManagement002Dto> selectedItem = dto.getSelectedItem();
        for (CeAssetGroupManagement002Dto ceAssetGroupManagement002Dto : selectedItem) {
            ceAssetGroupManagement002Dto.setSeqno(dto.getSeqno());
        }
        if (selectedItem.size() > 0) {
            ceAssetGroupManagementMapper.insertAssetPool(selectedItem);
        }
        
        
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int deleteAssetPool(CeAssetGroupManagement002Dto dto) {
        return ceAssetGroupManagementMapper.deleteAssetPool(dto.getSeqno(),dto.getFinprodId());
    }

    /**
     * <一句话功能简述>
     * 查询某系列下的资产明细
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<CeAssetGroupManagement001Dto> getAssetDetailBySeqno(int page, int length,
                                                                    Map<String, Object> param) throws Exception {
        return super.queryForPage(page, length, () ->ceAssetGroupManagementMapper.getAssetDetailBySeqno(param));
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<CeAssetGroupManagement002Dto> getExistAssetBySeqno(Map<String, Object> param) {
        return ceAssetGroupManagementMapper.getExistAssetBySeqno(param);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param seqno
     * @param assetpoolName
     * @return
     * @see
     * @since 1.0
     */
    public int checkIsExistByassetpoolName(Map<String, Object> map) {
        return ceAssetGroupManagementMapper.checkIsExistByassetpoolName(map);
    }

    /**
     * <一句话功能简述>
     * 校验添加的资产明细是否已被添加
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public int checkIsExistAssetPool(String finprodId) {
        return ceAssetGroupManagementMapper.checkIsExistAssetPool(finprodId);
    }
    
    /**
     * <一句话功能简述>
     * 校验添加的资产明细是否已被添加到其他系列中
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public String checkIsExistAssetPoolNew(String finprodId) {
        return ceAssetGroupManagementMapper.checkIsExistAssetPoolNew
        		(finprodId, MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName()));
    }

    /**
     * <一句话功能简述>
     * 判断是否关联授信客户编号
     * @param seqno
     * @return
     * @see
     * @since 1.0
     */
    public int checkisExistId(String seqno) {
        return ceAssetGroupManagementMapper.checkisExistId(seqno);
    }

    public int checkisExistAssetByCredit(String finprodId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", finprodId);
        if (MultilevelDataAuthorityUtil.isEnabled()) {
            param.put("institutionCode", MultilevelDataAuthorityUtil.getOrganizationCode(LoginUserUtil.getUserName()));
        }
        return ceAssetGroupManagementMapper.checkisExistAssetByCredit(param);
    }
    
    /**
     * <一句话功能简述>
     * 判断该资产系列是否存在明细
     * @param seqno
     * @return
     * @see
     * @since 1.0
     */
    public int checkIsExistDetail(String seqno) {
        return ceAssetGroupManagementMapper.checkIsExistDetail(seqno);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageId
     * @return
     * @see
     * @since 1.0
     */
    public List<CeAssetGroupManagement002Dto> getBusinessWorkflowId(String pageId) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        List<CeAssetGroupManagement002Dto> workflowId = ceAssetGroupManagementMapper.getBusinessWorkflowId(pageId);
        DynamicDataSourceHolder.clearDataSource();
        return workflowId;
    }


}
