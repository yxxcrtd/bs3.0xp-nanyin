package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.server;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dto.DisReportAssetIndexIntoResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dto.DisReportAssetIndexResult;

@Service
public class DisReportAssetIndexInterfService{
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

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

		BigDecimal days2=BigDecimal.ZERO;
		if(null!=acctEntity.getMdate()){
			days2 = new BigDecimal(DateUtil.getBetweenDayNumber(acctEntity.getValueDate(), acctEntity.getMdate()));
		}
		acctEntity.setEndDays2(days2);
		
		// 定义返回值
    	returnDto.getRepProdIndexList().add(acctEntity);


    	return returnDto;
    }


}
