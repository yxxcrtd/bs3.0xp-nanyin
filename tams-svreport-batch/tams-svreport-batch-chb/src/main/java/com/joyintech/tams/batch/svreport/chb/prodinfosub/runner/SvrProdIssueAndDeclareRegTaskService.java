package com.joyintech.tams.batch.svreport.chb.prodinfosub.runner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.joyintech.tams.batch.report.common.db.SvrDBUtil;
import com.joyintech.tams.batch.svreport.chb.prodinfosub.service.ProdIssueAndDecalreCBMainService;
import com.joyintech.tams.common.svreport.chb.util.ToolsUtil;

/**
 * 发行登记
 * @author luminmin
 * @version 1.0 2019年01月29日
 * @see ProdIssueAndDecalreService
 * @since 1.0
 */
@Service(value = "svrProdIssueAndDeclareRegTaskService")
@Scope("prototype")
public class SvrProdIssueAndDeclareRegTaskService extends BatchBaseService{
    

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 数据归档service
     */
    @Autowired
    private ProdIssueAndDecalreCBMainService prodIssueSubMainService;
    @Autowired
	private SvrDBUtil dbUtil;
    
      @Override
        public JobExecution execute() throws BatchException {
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
            logger.info("产品发行申报登记报送批处理启动:"+sdf.format(bdate));
            SysBatchgrpExecInfo batchExecuteDetail = this.getBatchExecuteDetail();
            List<String> prodList = new ArrayList<String>();
            List<String> prodListex = new ArrayList<String>();
            if (StringUtil.isNotEmpty(batchExecuteDetail.getPrdGroupSeqNo())) {
            	Map<String,String> prodMap=dbUtil.getAllProdMapWithNoSameId();
            	prodListex = this.getProductIdList();
                if(!prodListex.isEmpty()) {
                	for(String prodString : prodListex) {
                		//净值型产品过滤“F16_”前缀
                		prodString = prodString.replaceFirst("F16_", "");
                		if(prodMap.containsKey(prodString)){
                			prodList.add(prodMap.get(prodString));
                		}else{
                			prodList.add(prodString);
                		}
                	}
                }
            }
          String  InstitutionCode = null;

          if(CommonUtil.isBAmodel()){
              //判断当前是否是总行机构
              if(!DictCodeListExt3.联社机构类型_省级机构.getValue().equals(CommonUtil.getInsitutionTypeByUserName(batchExecuteInfo.getCreateUser()))){
                  //如果是非总行则将批处理启动用户所属的机构传入后台调用
                  InstitutionCode=CommonUtil.getInsitutionCodeByUserName(batchExecuteInfo.getCreateUser());
              }
          }
            prodIssueSubMainService.generateIssueData(ToolsUtil.getCurrentTime(batchExecuteInfo.getExecuteDate()), prodList,false,InstitutionCode);
            result.setStatus(BatchStatus.COMPLETED);
            Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
            logger.info("产品发行申报登记报送批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");    
            return result;
        }

}
