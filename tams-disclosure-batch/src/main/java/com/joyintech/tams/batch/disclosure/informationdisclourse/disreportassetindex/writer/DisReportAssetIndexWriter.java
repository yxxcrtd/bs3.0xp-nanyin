package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.writer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dao.DisReportAssetIndexMapper;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dto.DisReportAssetIndexIntoResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dto.DisReportAssetIndexResult;
@Service
@Scope("prototype")
public class DisReportAssetIndexWriter extends BaseWriter<NeedToWriteEntity> {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DisReportAssetIndexMapper disReportAssetIndexMapper;
    @SuppressWarnings("unchecked")
	@Override
	public synchronized void write(List<? extends NeedToWriteEntity> items) throws Exception {
		// 获取process返回组合的list值 - 披露成功list
		List<DisReportAssetIndexIntoResult> result = (List<DisReportAssetIndexIntoResult>) items;
		logger.info("wirte执行开始");
		
		//批量导入数据
		for (DisReportAssetIndexIntoResult disReportAssetIndexResult : result) {
			//遍历实体类并入库
			for (DisReportAssetIndexResult disRep : disReportAssetIndexResult.getRepProdIndexList()) {
				disReportAssetIndexMapper.updateRepoAssetIndexAll(disRep);
			}
			
		}

	}

}
