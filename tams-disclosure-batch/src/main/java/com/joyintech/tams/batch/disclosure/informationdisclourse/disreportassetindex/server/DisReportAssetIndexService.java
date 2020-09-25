package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.server;

import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.batch.disclosure.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.disclosure.common.db.DBUtil;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dao.DisReportAssetIndexMapper;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dto.DisReportAssetIndexIntoResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dto.DisReportAssetIndexResult;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;

@Service
public class DisReportAssetIndexService  extends AutoSendCBBaseService{
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
	private DBUtil dBUtil;
    
    @Autowired
    private DisReportAssetIndexMapper DisReportAssetIndexMapper;
    
    @Autowired
    private DisReportAssetIndexInterfService disReportAssetIndexInterService;
    
    
	/**
     * process 数据处理
     * 
     * @param param1 传递参数
     * @return BookGenerateResult
	 * @throws Exception 
     * @see
     * @since 1.0
     */
    public DisReportAssetIndexIntoResult execute(Map<String, Object> param1) throws Exception {
    	// 获取reader返回值
    	DisReportAssetIndexResult DisReportAssetIndexEntity = (DisReportAssetIndexResult)param1.get(
            ReportGlobalConstant.REP_READER_RESULT);
    	logger.info("process执行开始");
    	// 若reader传递的参数为空，不进行后续处理
        if (DisReportAssetIndexEntity == null) {
            return null;
        }
        
        DisReportAssetIndexIntoResult result = new DisReportAssetIndexIntoResult();
        
//        String source = CacheUtil.getSysParam(ReportGlobalConstant.DIS_DATA_SOURCE);
//        if(source.equals(DisclosureDictCodeList.上游数据来源_接口.getValue())){
//        	result = disReportAssetIndexInterService.processDto(DisReportAssetIndexEntity);
//        }else if(source.equals(DisclosureDictCodeList.上游数据来源_一体化.getValue())){
//        	result = this.processDto(DisReportAssetIndexEntity);
//        }
        result = this.processDto(DisReportAssetIndexEntity);
        

    	logger.info("process执行结束");
		return result;
    }
    

    
    
    
    /**
     * 
     * @param acctEntity
     * @return
     * @throws Exception
     */
    public DisReportAssetIndexIntoResult processDto(DisReportAssetIndexResult acctEntity) throws Exception { 

    	// 若reader读取的值为空，直接返回空
    	if (acctEntity == null) {
    		return null;
    	}
		DisReportAssetIndexIntoResult returnDto = new DisReportAssetIndexIntoResult();

		//资产指标表.剩余存续期限	= 剩余存续期限 = 当日资产指标表DTO.到期日- DTO.估值日															
		// 如果到期日为空，剩余期限=1
		if(null == acctEntity.getMdate()) {
			acctEntity.setEndDays2(new BigDecimal(1));
		}else {
			acctEntity.setEndDays2(new BigDecimal(DateUtil.getBetweenDayNumber(acctEntity.getValueDate(),acctEntity.getMdate())));
		}
		
		// 定义返回值
    	returnDto.getRepProdIndexList().add(acctEntity);
    	
    	
    	returnDto.setProdId(acctEntity.getProdId());


    	return returnDto;
    }


}
