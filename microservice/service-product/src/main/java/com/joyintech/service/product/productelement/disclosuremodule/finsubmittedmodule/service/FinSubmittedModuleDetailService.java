/*
 * 文件名：FinSubmittedModule002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年8月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.productelement.disclosuremodule.finsubmittedmodule.service;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.product.productelement.disclosuremodule.finsubmittedmodule.dao.FinSubmittedModuleDetailMapper;
import com.joyintech.service.product.productelement.disclosuremodule.finsubmittedmodule.dto.FinSubmittedModuleDetailDto;

/**
 * 申报附件方案Service
 * 可以进行添加申报附件模板以及文件的上传和下载
 * @author liuwei
 * @version 1.0 2018年8月22日
 * @see FinSubmittedModuleDetailService
 * @since 1.0
 */
@Service("backFinSubmittedModuleDetailService")
@Transactional
public class FinSubmittedModuleDetailService extends BaseService<FinSubmittedModuleDetailDto> {
    
    /**
     * 申报附件方案管理Mapper
     */
    @Autowired
    private FinSubmittedModuleDetailMapper finSubmittedModule002Mapper;
    
    /**
     * 附件上传共通Service
     */
    @Autowired
    private MstFileCommonService mstFileCommonService;
    @Autowired
    private FinSubmittedModuleDetailService finSubmittedModule002Service;

    /**
     * 插入操作
     *
     * @param dto 选中参数
     * @see
     * @since 1.0
     */
    public int insert(FinSubmittedModuleDetailDto dto) {
        //将数据插入到申报方案附件表
        return finSubmittedModule002Mapper.insert(dto);
    }

    /**
     * 删除申报附件方案表信息
     * 
     * @param schemeId 申报附件方案代码
     * @return 结果
     * @see
     * @since 1.0
     */
    public int delete(String schemeId) {
        return finSubmittedModule002Mapper.delete(schemeId);
    }

    /**
     * 表格查询操作
     * 查询附件类型、模板类型和备注等数据（根据申报附件方案代码查询）
     * @param map 查询参数
     * @return 附件类型、模板类型和备注
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param map map
     * @param page 页码
     * @param length 页面长度
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public Page<FinSubmittedModuleDetailDto> selectForPage(int page, int length, Map<String, Object> map)
            throws Exception {
        Page<FinSubmittedModuleDetailDto> allPage = super.queryForPage(page, length, () -> finSubmittedModule002Mapper.select(map));
        List<FinSubmittedModuleDetailDto> list = allPage.getRoot();
        if(list != null && list.size()>0){
            for(FinSubmittedModuleDetailDto dto:list){
                FinSubmittedModuleDetailDto e;
                try {
                    DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
                    e = finSubmittedModule002Service.selectFile(dto.getSeqno());
                } finally {
                    DynamicDataSourceHolder.clearDataSource();
                }
                if(e != null){
                    dto.setFileName(e.getFileName());
                    dto.setFileId(e.getFileId());
                }
            }
        }
        allPage.setRoot(list);
        return allPage;
    }

    /**
     * 查询附件类型、模板类型和备注等数据（根据申报附件方案流水号查询）
     * 
     * @param seqno 流水号
     * @return 附件类型、模板类型和备注
     * @see
     * @since 1.0
     */
    public FinSubmittedModuleDetailDto selectDocTpl(String seqno) {
        return finSubmittedModule002Mapper.selectBySeqno(seqno);
    }
    /**
     * 根据点击行的附件、模板类型和申报方案代码查询对应的流水号
     * 
     * @param dto 查询参数
     * @return 流水号
     * @see
     * @since 1.0
     */
    public String selectSeqno(FinSubmittedModuleDetailDto dto) {
        return finSubmittedModule002Mapper.selectSeqno(dto);
    }

    /**
     * 附件明细表格查询操作
     * 
     * @param map 查询参数
     * @return 文件名和业务编码
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public FinSubmittedModuleDetailDto selectFile(String businessId)
            throws Exception {
        FinSubmittedModuleDetailDto fileInfo;
        try {
            DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
            fileInfo = finSubmittedModule002Mapper.selectFile(businessId);
        } finally {
            DynamicDataSourceHolder.clearDataSource();
        }
        return fileInfo;
    }

    /**
     * 根据业务编码删除文件信息
     * 
     * @param businessId 业务编码
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int deleteFile(String businessId) {
        int i;
        try {
            DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
            i = finSubmittedModule002Mapper.deleteFile(businessId);
        } finally {
            DynamicDataSourceHolder.clearDataSource();
        }
        return i;
    }

    /**
     * 查询上传文件信息流水号
     * 在上传时校验该模板是否已上传过文件
     * @param businessId 业务编码
     * @return 流水号
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String selectSeqNo(String businessId) {
        String seqNo;
        try {
            DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
            seqNo = finSubmittedModule002Mapper.selectSeqNo(businessId);
        } finally {
            DynamicDataSourceHolder.clearDataSource();
        }
        return seqNo;
    }

    /**
     * 查询同样的附件模板是否上传过
     *
     * @param dto 附件模板
     * @return 流水号
     * @see
     * @since 1.0
     */
    public String selectByDocAndTpl(FinSubmittedModuleDetailDto dto) {
        return finSubmittedModule002Mapper.selectByDocAndTpl(dto);
    }

    /**
     * 验证该附件模板是否被理财产品使用
     *
     * @param schemeId 附件模板
     * @return 流水号
     * @see
     * @since 1.0
     */
    public List<String> selectFinProdId(String schemeId) {
        return finSubmittedModule002Mapper.selectFinProdId(schemeId);
    }
}

