package com.joyintech.tams.batch.svreport.assetinfo.runner;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.svreport.assetinfo.service.AssetInfoImpMainService;

/**
 * 功能:资产要素批处理
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月29日
 * @修改记录：
 */
@Service(value = "svrAssetInfoInterfTaskService")
@Scope("prototype")
public class SvrAssetInfoInterfTaskService extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
	private AssetInfoImpMainService assetInfoImpMainService;
	
	@Override
	public JobExecution execute() throws BatchException {
		// TODO Auto-generated method stub
		JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bdate=new Date();
        Long bt=bdate.getTime();
        logger.info("资产要素基础表数据导入批处理启动:"+sdf.format(bdate));
        try {
            SysBatchgrpExecInfo batchExecuteDetail = this.getBatchExecuteDetail();
            List<String> prodList = new ArrayList<String>();
            // 数据接入不再支持单产品接入
//            if (StringUtil.isNotEmpty(batchExecuteDetail.getPrdGroupSeqNo())) {
//                prodList = this.getProductIdList();
//            }
            String  institutionCode = null;
            if(CommonUtil.isBAmodel()){
                //判断当前是否是总行机构
                if(!DictCodeListExt3.联社机构类型_省级机构.getValue().equals(CommonUtil.getInsitutionTypeByUserName(batchExecuteInfo.getCreateUser()))){
                    //如果是非总行则将批处理启动用户所属的机构传入后台调用
                    institutionCode=CommonUtil.getInsitutionCodeByUserName(batchExecuteInfo.getCreateUser());
                }
            }
        	assetInfoImpMainService.generateAssetInfoData(batchExecuteInfo.getExecuteDate(),prodList,false,institutionCode);
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
            logger.error("资产要素基础表数据导入批处理Error",e);
        }finally{
        	Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
        	logger.info("资产要素基础表数据导入批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");	
        }
        return result;
	}


}
