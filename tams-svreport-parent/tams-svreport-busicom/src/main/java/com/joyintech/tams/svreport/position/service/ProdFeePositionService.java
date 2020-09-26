package com.joyintech.tams.svreport.position.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.SvrCBBaseService;
import com.joyintech.tams.common.base.entity.ChbPrdFeeDeal;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.position.dao.ChbPrdFeePositionMapper;
import com.joyintech.tams.svreport.position.dto.ChbPrdFeePosition;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 产品费用头寸批处理操作
 * @author chenxc
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProdFeePositionService  extends SvrCBBaseService{

    private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    private ChbPrdFeePositionMapper chbPrdFeePositionMapper;
	
	@Autowired
	private SqlSessionTemplate template;
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
	/**
	 * 头寸处理
	 * @param batchDate
	 * @param prodIdList
	 */
	public void generatePositionData(Date batchDate,List<String> prodIdList) {
		//1、查询倒起息交易信息
		logger.debug("产品费用头寸数据批处理:----开始------" + df.format(new Date()));
		HashMap<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("batchDate", batchDate);
		paraMap.put("prodIdList", prodIdList);
//		List<ChbPrdDeal> minSettdateList = new ArrayList<ChbPrdDeal>();
//		template.select("com.joyintech.tams.svreport.position.dao.ChbPrdFeePositionMapper.queryMinSettdate",paraMap,
//                new ResultHandler() {
//            @Override
//            public void handleResult(ResultContext resultContext) {
//            	minSettdateList.add((ChbPrdDeal) resultContext.getResultObject());
//            }
//        });
		
	      //查询最小跑批日
        Date minSettdate = chbPrdFeePositionMapper.queryMinSettdate(batchDate, prodIdList);
        
    	//2、删除跑批日头寸重新生成
		for (Date d = minSettdate; d.compareTo(batchDate) <= 0;) {
			delPosition(d,prodIdList);
            //生成头寸
    		calPosition(d,prodIdList);
			d = DateUtil.addDay(d, 1);
		}
		logger.debug("产品费用头寸数据批处理:----结束------" + df.format(new Date()));
	}
	
	/**
	 * 批量删除
	 * @param batchDate
	 * @param prodIdList
	 */
	private void delPosition(Date batchDate, List<String> prodIdList) {
		logger.debug("产品费用头寸数据批量删除:----开始------" + df.format(new Date()));
		int singalCount = Integer.parseInt( null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?
										   "100" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));
		int totalCount = null == prodIdList ? 0 : prodIdList.size();
		if(totalCount > 0){
			if(totalCount<=singalCount){
				chbPrdFeePositionMapper.deletePosition(batchDate,prodIdList);
			}
			else{
				for(int i = 0; i < totalCount/singalCount ; i++){
				    chbPrdFeePositionMapper.deletePosition(batchDate,prodIdList.subList(i*singalCount, (i+1)*singalCount));
//					prodIdList.removeAll(prodIdList.subList(0, singalCount));
				}
	            if(CollectionUtils.isNotEmpty(prodIdList)){
	                chbPrdFeePositionMapper.deletePosition(batchDate,prodIdList.subList((totalCount/singalCount)*singalCount, totalCount));
	            }
			}
		}else{
		    chbPrdFeePositionMapper.deletePosition(batchDate,null);
		}
		logger.debug("产品头寸数据批量删除:----结束------" + df.format(new Date()));
	}

	/**
	 * 头寸数据前期处理
	 * @param bookDate
	 * @param prodIdList
	 */
	private void calPosition(Date bookDate,List<String> prodIdList) { 
	   
	    //查询昨日头寸
        List<ChbPrdFeePosition> listYstPos = chbPrdFeePositionMapper.selectByDate(DateUtil.addDay(bookDate, -1), prodIdList);
/*
	    List<ChbPrdFeePosition> listYstPos =new ArrayList<ChbPrdFeePosition> ();
        HashMap<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("cdate", DateUtil.addDay(bookDate, -1));
        paraMap.put("prodIdList", prodIdList);
        template.select(
            "com.joyintech.tams.svreport.position.dao.ChbPrdFeePositionMapper.selectByDate",
            paraMap, new ResultHandler() {
                @Override
                public void handleResult(ResultContext resultContext) {
                    listYstPos.add((ChbPrdFeePosition)resultContext.getResultObject());
                }
            });
           
*/	    
	    
	    //定义头寸累计记录Map<费用头寸表业务主键,ChbPrdFeePosition对象>，费用一级类型＋费用二级类型＋ccy+机构代码＋产品代码，为key
	    Map<String,ChbPrdFeePosition> mapPos = new HashMap<String,ChbPrdFeePosition>();
	    
	    for ( ChbPrdFeePosition m:listYstPos ) {
	        String key = m.getFeeType()+"|"+m.getFeeSectype()+"|"+m.getCcy()+"|"+m.getOrgCode()+"|"+m.getProdId();
	        m.setCdate(bookDate);
	        mapPos.put(key,m);
	    }
	    
        // 查询费用信息
        List<ChbPrdFeeDeal> feeList = chbPrdFeePositionMapper.selectPrdFeeDeal(bookDate,prodIdList);
        
        
        for (ChbPrdFeeDeal fee : feeList) {

            String key = fee.getFeeType()+"|"+fee.getFeeSectype()+"|"+fee.getCcy()+"|"+fee.getOrgCode()+"|"+fee.getProdId();
            ChbPrdFeePosition pos = mapPos.get(key);
            if ( pos==null) {
                pos = new ChbPrdFeePosition();
                pos.setCdate(bookDate);
                pos.setCcy(fee.getCcy());
                pos.setProdId(fee.getProdId());
                pos.setFeeType(fee.getFeeType());
                pos.setFeeSectype(fee.getFeeSectype());
                pos.setAmt(fee.getAmt());
                pos.setOrgCode(fee.getOrgCode());
                pos.setDeptCode(fee.getDeptCode());
                mapPos.put(key, pos);
            } else {
                pos.setAmt(pos.getAmt().add(fee.getAmt()));
            }
        }
        
        //List<ChbPrdFeePosition> listPos = Arrays.asList(mapPos.values().toArray());
        if(mapPos.values()!=null && mapPos.values().size()>0){
            logger.debug("产品费用头寸数据入库:----开始------" + df.format(new Date()));
            SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            //批量插入mapper
            ChbPrdFeePositionMapper batchMapper =  session.getMapper(ChbPrdFeePositionMapper.class);
            try{
                int count = 0;
                for(Entry<String,ChbPrdFeePosition> e:mapPos.entrySet()){
                    ChbPrdFeePosition pos = e.getValue();
                    pos.setCreateUser(CommonUtil.getUserName());
                    batchMapper.insertPos(pos);
                    count++;
                    if(count % 1000==0){
                        session.commit();
                        session.clearCache();
                    }
                }   
                session.commit();
                session.clearCache();
//            } catch (Exception e) {
//              logger.debug("批量插入产品费用头寸数据出错:" + e.getMessage());
//              session.rollback();
          } 
            finally {
                session.close();
            }
            logger.debug("产品费用头寸数据入库:----结束------" + df.format(new Date()));
        }        
	}
    
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
        // 设置批处理bean
        setBatchBeanName(BatchBeanName.产品费用头寸批处理);
        logInfoBatchStart();
        generatePositionData(executeDate, prodList);
        logger.debug("产品费用头寸批处理");
        logInfoBatchEnd();
    }
	

}
