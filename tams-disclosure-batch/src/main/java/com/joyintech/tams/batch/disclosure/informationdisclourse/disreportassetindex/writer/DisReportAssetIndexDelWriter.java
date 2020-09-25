/*
 * 文件名：BookGenerateResultCommonWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.writer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dao.DisReportAssetIndexMapper;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dto.DisReportAssetIndexIntoResult;

/**
 * 资产理论结息日结转-STEP1
 * 
 * @author chengzhong
 * @version 1.0 2018年5月9日
 * @see DisReportAssetIndexDelWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DisReportAssetIndexDelWriter extends BaseWriter<NeedToWriteEntity> {

    
	@Autowired
	private DisReportAssetIndexMapper disReportAssetIndexMapper;

    /**
     * 删除writer
     */
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
    	
    	List<DisReportAssetIndexIntoResult> result = (List<DisReportAssetIndexIntoResult>) items;
    	
		if(result.size()>0){
//			for (int i = 0; i < result.size(); i++) {
//				//删除逻辑为全部修改.所以仅执行一次
//				disReportAssetIndexMapper.updateRepoAssetIndex(result.get(i).getParam());
//				break;
//			}
			disReportAssetIndexMapper.updateRepoAssetIndex(result.get(0).getParam());
		}
		
    }

}
