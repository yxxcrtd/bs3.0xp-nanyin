/*
 * 文件名：ProjectAutoDurDealGenerateReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.project.reader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.invest.batch.autodurdealgenerate.project.dto.ProjectAutoDurDealGenerateReaderResult;
import com.joyintech.tams.invest.batch.autodurdealgenerate.project.service.ProjectAutoDurDealGenerateReaderService;

/**
 * 读入Reader
 *
 * @author etliu
 * @version 1.0 2018年12月17日
 * @see ProjectAutoDurDealGenerateReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProjectAutoDurDealGenerateReader extends BaseReader<ProjectAutoDurDealGenerateReaderResult> {

    /**
     * ProjectAutoDurDealGenerateReaderService
     */
    @Autowired
    private ProjectAutoDurDealGenerateReaderService readerService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProjectAutoDurDealGenerateReaderResult> createList() {
        return readerService.execute(getParams());
    }

}
