package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dto.DisReportAssetIndexIntoResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dao.DisReportProdIndexMapper;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto.DisReportProdIndexIntoResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto.DisReportProdIndexResult;
@Service
@Scope("prototype")
public class DisReportProdIndexDelWriter extends BaseWriter<NeedToWriteEntity> {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DisReportProdIndexMapper disReportProdIndexMapper;
	
    @SuppressWarnings("unchecked")
	@Override
	public synchronized void write(List<? extends NeedToWriteEntity> items) throws Exception {
    	List<DisReportProdIndexIntoResult> result = (List<DisReportProdIndexIntoResult>) items;
    	
//		if(result.size()>0){
//			for (int i = 0; i < result.size(); i++) {
//				//删除逻辑为全部修改.所以仅执行一次
//				disReportProdIndexMapper.updateRepoprodIndex(result.get(i).getParam());
//				break;
//			}
//		}
		disReportProdIndexMapper.updateRepoprodIndex(result.get(0).getParam());
	}

}
