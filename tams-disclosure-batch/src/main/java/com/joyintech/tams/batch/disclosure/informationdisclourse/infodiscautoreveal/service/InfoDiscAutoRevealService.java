package com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.dto.InfoDiscAutoRevealReadResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.dto.InfoDiscAutoRevealResult;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.dto.FtpDetailDto;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.service.FtpDetailService;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;

@Service
@Scope("prototype")
public class InfoDiscAutoRevealService {
	
	@Autowired
    private FtpDetailService ftpDetailService;
	
	/**
     * 日志
     */
//    private Logger logger = LoggerFactory.getLogger(getClass());
//	@Autowired
//    private FtpDetailService ftpDetailService;
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	/**
     * process 数据处理
     * 
     * @param param1 传递参数
     * @return BookGenerateResult
     * @see
     * @since 1.0
     */
    public InfoDiscAutoRevealResult execute(Map<String, Object> param1) {
    	InfoDiscAutoRevealResult result = new InfoDiscAutoRevealResult();
    	// 获取reader返回值
    	InfoDiscAutoRevealReadResult infoDiscAutoRevealEntity = (InfoDiscAutoRevealReadResult)param1.get(
            ReportGlobalConstant.REP_READER_RESULT);
    	logger.info("process执行开始");
    	// 若reader传递的参数为空，不进行后续处理
        if (infoDiscAutoRevealEntity == null) {
            return null;
        }
        result.setId(infoDiscAutoRevealEntity.getId());
        //调用ftp参数
        FtpDetailDto ftpDetailDto = new FtpDetailDto();
		ftpDetailDto.setProdId(infoDiscAutoRevealEntity.getProdid());
		ftpDetailDto.setReportId(infoDiscAutoRevealEntity.getRepId());
		ftpDetailDto.setReportCategory(infoDiscAutoRevealEntity.getReportCategory()); // 获取ftp路径用
		ftpDetailDto.setOption(DictCodeListExt2.披露方式_自动.getValue()); //手工操作
		if(StringUtil.isNotEmpty(infoDiscAutoRevealEntity.getStartDate())) {
			ftpDetailDto.setStatrDate(infoDiscAutoRevealEntity.getStartDate());
		}
		if(StringUtil.isNotEmpty(infoDiscAutoRevealEntity.getEndDate())) {
			ftpDetailDto.setEndDate(infoDiscAutoRevealEntity.getEndDate());
		}
		ftpDetailDto.setRevealChannel(infoDiscAutoRevealEntity.getRevealChannel());
		ftpDetailDto.setRevealId(infoDiscAutoRevealEntity.getId());
		
    	
    	infoDiscAutoRevealEntity.setProdId(infoDiscAutoRevealEntity.getProdid());
    	BeanUtil.copyPropertiesIgnoreNull(ftpDetailDto, infoDiscAutoRevealEntity);    		
    	logger.info("process执行结束");
    	
    	result.setFtpDetailDto(ftpDetailDto);
    	
    	
    	
    	
		return result;
    }
}
