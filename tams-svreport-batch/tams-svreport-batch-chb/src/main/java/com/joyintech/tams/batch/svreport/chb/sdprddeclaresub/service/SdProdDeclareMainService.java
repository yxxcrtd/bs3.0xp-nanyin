package com.joyintech.tams.batch.svreport.chb.sdprddeclaresub.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.svreport.chbsd.prddeclaresub.service.SdProDeclareBatchService;

/**
 * 功能:产品申报信息报送批处理入口
 * @since 
 * @version 
 * @作者：zhaoyongsheng
 * @创建时间：
 * @修改记录：
 */
@Service(value="sdProdDeclareMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SdProdDeclareMainService {

    @Autowired
	private SdProDeclareBatchService sdProDeclareBatchService;
	
    /**
     * @param bookDate 
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public void SendDurationRegMsg(Date batchDate,List<String> prodIdList) throws Exception {
    	sdProDeclareBatchService.sendDeclareInfoMsg(batchDate, prodIdList);
    }

}
