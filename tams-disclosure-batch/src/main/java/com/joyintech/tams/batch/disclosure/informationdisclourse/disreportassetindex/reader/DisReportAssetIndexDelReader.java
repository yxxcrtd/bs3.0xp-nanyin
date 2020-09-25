/*
 * 文件名：AssetIntdateCarryoverReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dao.DisReportAssetIndexMapper;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dto.DisReportAssetIndexResult;

/**
 * 资产理论结息日结转 - 读入
 * 
 * @author chengzhong
 * @version 1.0 2018年5月7日
 * @see DisReportAssetIndexDelReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DisReportAssetIndexDelReader extends BaseReader<DisReportAssetIndexResult> {

	@Autowired
	private DisReportAssetIndexMapper disReportAssetIndexMapper;
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public List<DisReportAssetIndexResult> createList() {
		
		List<DisReportAssetIndexResult> revealList = new ArrayList<DisReportAssetIndexResult>();
		logger.info("删除数据read开始执行");
		DisReportAssetIndexResult result = new DisReportAssetIndexResult();
		revealList.add(result);
		return revealList;
	}

}
