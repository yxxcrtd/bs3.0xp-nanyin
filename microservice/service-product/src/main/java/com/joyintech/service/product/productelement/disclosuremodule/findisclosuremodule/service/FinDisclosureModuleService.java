/*
 * 文件名：FinDisclosureModuleService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.productelement.disclosuremodule.findisclosuremodule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.PrdFileTemplatMapper;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.invest.common.dto.MstFileOutputParameter;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.product.productelement.disclosuremodule.findisclosuremodule.dao.FinDisclosureModuleMapper;
import com.joyintech.service.product.productelement.disclosuremodule.findisclosuremodule.dto.FinDisclosureModuleDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年10月31日
 * @see FinDisclosureModuleService
 * @since 1.0
 */
@Service("backFinDisclosureModuleService")
public class FinDisclosureModuleService extends BaseService<FinDisclosureModuleDto>{
    /**
     * 定义mapper对象
     */
    @Autowired
    private FinDisclosureModuleMapper finDisclosureModuleMapper;

    /**
     * 定义PrdFileTemplatMapper
     */
    @Autowired
    private PrdFileTemplatMapper prdFileTemplatMapper;

    /**
     * MstFileCommonService
     */
    @Autowired
    private MstFileCommonService mstFileCommonService;
    
    /**
     * 根据文件类型查询文件名
     * 
     * @param param 页面参数
     * @return list集合
     */
    public List<SuggestDto> getFileNameOption(Map<String, Object> param) {
        List<String> revealFileIdList = finDisclosureModuleMapper.getRevealFileIdList(param);
        param.put("revealFileIdList", revealFileIdList);
        List<SuggestDto> resultList;
        try {
            DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
            resultList = finDisclosureModuleMapper.getFileNameByBase(param);
        } finally {
            DynamicDataSourceHolder.clearDataSource();
        }
        return resultList;
    }

    /**
     * 画面查询
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<FinDisclosureModuleDto> selectAll(int page, int length, Map<String, Object> pageParam)
        throws Exception{
        Page<FinDisclosureModuleDto> pageList = super.queryForPage(page, length, () -> {
            finDisclosureModuleMapper.getFileList(pageParam);
        });
        selectForPage(pageList.getRoot(), pageParam);
        return pageList;
    }
    /**
     * 画面查询方法
     * 
     * @param pageParam 页面参数
     * @return 查询结果
     * @throws Exception 异常
     */
    private List<FinDisclosureModuleDto> selectForPage(List<FinDisclosureModuleDto> fileList, Map<String, Object> pageParam)
        throws Exception {
    	if(null != mstFileCommonService) {
    		List<FinDisclosureModuleDto> newList = new ArrayList<>();
    		List<SysUploadFileInfo> revealFileList = new ArrayList<>();
    		if (fileList != null && !fileList.isEmpty()) {
    			for (FinDisclosureModuleDto dto : fileList) {
    				MstFileOutputParameter output = mstFileCommonService.getFileInfoByBusiId(dto.getRevealFileId());
    				revealFileList.addAll(output.getUploadFileList());
    			}
    		}

    		for (FinDisclosureModuleDto dto : fileList) {
    			for (SysUploadFileInfo file : revealFileList) {
    				if (dto.getRevealFileId().equals(file.getBusinessId())) {
    					dto.setFileId(file.getSeqNo());
    					dto.setFileName(file.getFileName());
    					newList.add(dto);
    				}
    			}
    		}
    	}
    	return fileList;
    }

    /**
     * 通过披露文档代码查询数据
     * 
     * @param map 披露文档代码
     * @return dto
     */
    public FinDisclosureModuleDto selectByRevealFileId(Map<String, Object> map) {
        return finDisclosureModuleMapper.getDataByRevealFileId(map);
    }

    /**
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int delete(FinDisclosureModuleDto dto) {
        return prdFileTemplatMapper.deleteByPrimaryKey(dto.getRevealFileId());
    }

    /**
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    public int insert(FinDisclosureModuleDto dto) {
//        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
//        mstFileInputParameter.setBussinessId(dto.getRevealFileId());
//        List<SysUploadFileInfo> fileList = new ArrayList<SysUploadFileInfo>();
//        SysUploadFileInfo entity = new SysUploadFileInfo();
//        entity.setSeqNo(dto.getFileId());
//        fileList.add(entity);
//        mstFileInputParameter.setFileList(fileList);
//        // 将附件和当前业务对象绑定（更新附件的业务代码）
//        if(null != mstFileCommonService) {
//        	mstFileCommonService.updateBusinessId(mstFileInputParameter);
//        }
        return prdFileTemplatMapper.insert(dto);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param revealFileId
     * @return
     * @see
     * @since 1.0
     */
    public String isUsed(String revealFileId) {
        return finDisclosureModuleMapper.isUsed(revealFileId);
    }

}
