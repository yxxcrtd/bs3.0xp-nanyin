/*
 * 文件名：FinProductRevalDocumentService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.common.service;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.PrdFileMapper;
import com.joyintech.tams.common.base.entity.PrdFileKey;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.invest.common.dto.MstFileOutputParameter;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.common.dao.FinProductRevalDocumentMapper;
import com.joyintech.service.product.common.dto.FinProductFileInfoDto;

/**
 * 信息披露文档模板组件service
 * 
 * @author wangzehui
 * @version 1.0 2018年10月19日
 * @see FinProductRevalDocumentService
 * @since 1.0
 */
@Service("backFinProductRevalDocumentService")
public class FinProductRevalDocumentService {

    /**
     * finProductRevalDocumentMapper
     */
    @Autowired(required = false)
    private FinProductRevalDocumentMapper finProductRevalDocumentMapper;

    /**
     * 理财产品模板信息mapper
     */
    @Autowired
    private PrdFileMapper prdFileMapper;

    /**
     * 附件-基础组件
     */
    @Autowired(required = false)
    private MstFileCommonService fileCommonService;

    /**
     * @param key
     * @return
     */
    public int deleteByPrimaryKey(PrdFileKey key) {
        if (key != null) {
            return prdFileMapper.deleteByPrimaryKey(key);
        }
        return 0;
    }

    /**
     * 根据金融产品代码删除
     * 
     * @param finprodId 金融产品代码
     * @return 删除记录数
     */
    public int delete(String finprodId) {
        return finProductRevalDocumentMapper.delete(finprodId);
    }

    public List<FinProductFileInfoDto> queryForList(String finprodId) {
        List<FinProductFileInfoDto> fileList = finProductRevalDocumentMapper.selectFileList(
            finprodId);
        parseFileList(fileList);
        return fileList;
    }

    public List<SelectOptionDto> queryForListByFileType(String fileType, String initValue) {
        List<FinProductFileInfoDto> fileList = finProductRevalDocumentMapper.selectRevealList(
            fileType, initValue);
        parseFileList(fileList);
        if (fileList.isEmpty()) {
            return null;
        }
        List<SelectOptionDto> list = new ArrayList<SelectOptionDto>();
        for (FinProductFileInfoDto finProductFileInfoDto : fileList) {
            SelectOptionDto dto = new SelectOptionDto();
            dto.setId(finProductFileInfoDto.getRevealFileId());
            dto.setText(finProductFileInfoDto.getFileName());
            list.add(dto);
        }
        return list;
    }

    private void parseFileList(List<FinProductFileInfoDto> fileList) {
        if (null != fileCommonService && null != fileList) {
            fileList.forEach(file -> {
                MstFileOutputParameter outFile = fileCommonService.getFileInfoByBusiId(
                    file.getRevealFileId());
                List<SysUploadFileInfo> sysFileList = outFile.getUploadFileList();
                if (null != sysFileList && !sysFileList.isEmpty()) {
                    file.setFileName(sysFileList.get(0).getFileName());
                }
            });
        }
    }
}
