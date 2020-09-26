package com.joyintech.tams.svreport.chb.proddurationreg.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.tams.batch.report.autosendcb.common.service.SvrCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;

/**
 * 功能:产品存续期登记自动生成批处理
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月30日
 * @修改记录：
 */
@Service
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class ProdDurationRegRegistBatchService extends SvrCBBaseService {
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
	private ProdDurationReg002Service prodDurationReg002Service;
	
    /**
     * @param bookDate 
     * @see
     * @since 1.0
     */
    public void generateDurationRegData(Date batchDate,List<String> prodIdList) {
    	// 设置批处理bean
    	setBatchBeanName(BatchBeanName.产品存续期交易登记报送);
    	logInfoBatchStart();
    	
    	//批处理前期检查：当天已登记的数据 批处理不通过
//    	batchCheck(batchDate,prodIdList);
    	prodDurationReg002Service.generateDurationRegData(batchDate, prodIdList);
		
    	logger.debug("产品存续期登记自动生成批处理");
		logInfoBatchEnd();
    }

    /**
     * 批处理检查，如有已登记的则抛出异常
     * @param prodIdList
     * @throws BatchException
     */
//	private void batchCheck(Date batchDate,List<String> prodIdList) throws BatchException {
//		ChbPrdDurationRegMapper chbPrdDurationRegMapper = SpringContextUtil.getBean(
//    			ChbPrdDurationRegMapper.class);
//    	List<ChbPrdDurationDeal> listBase = chbPrdDurationRegMapper.getSendedSubList(batchDate,prodIdList);
//    	if(!StringUtil.isEmptyList(listBase)){
//    		// 产品已报送列表
//    		StringBuffer msg = new StringBuffer("");
//    		int count = 1;//只展示50条
//    		for(ChbPrdDurationDeal sub : listBase){
//    			if(count >= 50){
//    				msg.append(sub.getProdId()).append(".....");
//    				break;
//    			}else{
//    				msg.append(sub.getProdId()).append(GlobalConstant.SEMICOLON);
//    			}
//    			count++;
//    		}
//    		
//    		if (!StringUtil.isEmpty(msg.toString())){
//    			throw new BatchException("存在已报送的产品代码:"+msg.toString()+"\n");
//    		}
//    	}
//	}
    
    /**
     * 功能:数据变更接口统一调度入口
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月9日
     * @param executeDate
     * @param prodList
     * @param assetList
     * @throws BusinessException 
     * @see com.joyintech.tams.batch.report.autosendcb.common.service.SvrCBBaseService#execute(java.util.Date, java.util.List, java.util.List)
     */
    @Override
    public void execute(Date executeDate, List<String> prodList, List<String> assetList,Map<String,List<String>> param) throws BusinessException {
        try {
            generateDurationRegData(executeDate,prodList);
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
            throw new BusinessException(e);
        }
    }
}