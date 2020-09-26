package com.joyintech.tams.valuation.batch.unitfycaccountant.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.joyintech.tams.valuation.batch.common.base.dao.ValBatchCronJobMapper;
import com.joyintech.tams.valuation.batch.unitfycaccountant.dao.UnitfycaccountantInfoMapper;
import com.joyintech.tams.valuation.batch.unitfycaccountant.entity.SztInterfValuation;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.SysUnaccountantInfoDetailMapper;
import com.joyintech.tams.common.base.entity.SysUnaccountantInfoDetail;

@Component
public class ValBatchBalanceCheckThread implements Runnable{
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${batch.properties.sleeptime:20000}")
    private Integer sleepTime;
    
    private SysUnaccountantInfoDetail sysUcaccountantInfoDetail;

    public void setSysUcaccountantInfoDetail(SysUnaccountantInfoDetail sysUcaccountantInfoDetail) {
        this.sysUcaccountantInfoDetail = sysUcaccountantInfoDetail;
    }

//    @Autowired
//    private SysUnaccountantInfoDetailMapper sysUcaccountantInfoDetailMapper;
 // 任务组序列号
    private String seqno;

    // 执行顺序号
    private Long orderNo;
    
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }
    
    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }
    
    // 产品组ID
    private String prdGrpSeqNo;

    public void setPrdGrpSeqNo(String prdGrpSeqNo) {
        this.prdGrpSeqNo = prdGrpSeqNo;
    }
    @Autowired
    private UnitfycaccountantInfoMapper unitfycaccountantInfoMapper;
    @Autowired
    private SysUnaccountantInfoDetailMapper sysUnaccountantInfoDetailMapper;
    @Override
    public void run() {

        Map<String, Object> params = new HashMap<>();
        while (true) {
            List<SysUnaccountantInfoDetail> list = unitfycaccountantInfoMapper.findInfoFailList(seqno, orderNo);
            if (!StringUtil.isEmptyList(list)) {
                // 失败的任务不执行
                return;
            }

            list = unitfycaccountantInfoMapper.findOrderNoLessList(seqno, orderNo);
            if (StringUtil.isEmptyList(list)) {
                break;
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            	Thread.currentThread().interrupt();
            }
        }
        String execId = sysUcaccountantInfoDetail.getExectaskId();
        params.put("execId", execId);
        params.put("modelCode", sysUcaccountantInfoDetail.getModelCode());
        params.put("seqno", sysUcaccountantInfoDetail.getSeqNo());
        params.put("orderNo", orderNo);
        sysUcaccountantInfoDetail.setStartTime(DateUtil.now());
        sysUcaccountantInfoDetail.setExectaskId(execId);
        sysUcaccountantInfoDetail.setStatus("1");
        sysUnaccountantInfoDetailMapper.updateByPrimaryKey(sysUcaccountantInfoDetail);
        
//        Map<String, Object> map = new HashMap<>();
        //查询余额表对账的产品
        List<String> products = findAllProdbyGroup(prdGrpSeqNo);
        while (true) {
        //查询余额表产品的对账条数
        List<SztInterfValuation> list = this.selectValuation(DateUtil.format(sysUcaccountantInfoDetail.getBusiDate()), products);
        //判断是否全部对账
        // 所有的任务
        int taskCount = list.size();
        //失败任务
        List<SztInterfValuation> listFail = list.stream()
            .filter(x->"3".equals(x.getStatus()))
            .collect(Collectors.toList());
        //成功任务
        List<SztInterfValuation> listSuccess = list.stream()
            .filter(x->"2".equals(x.getStatus()))
            .collect(Collectors.toList());
        
        
        
        if(taskCount==listFail.size()+listSuccess.size()){
            if(listFail.size()>0){
                Date endTime = DateUtil.now();
                params.put("endTime", endTime);
//                params.put("lastTime", endTime.getTime() - sysUcaccountantInfoDetail.getStartTime().getTime());
                params.put("status", "3");
                unitfycaccountantInfoMapper.updateInfoFail(params);
                unitfycaccountantInfoMapper.updateInfoDetailFail(params);
                break;
                
            }
            else{
            // 全部执行成功
            sysUcaccountantInfoDetail.setStatus("2");
//            Date endTime = DateUtil.now();
            sysUcaccountantInfoDetail.setEndTime(DateUtil.now());
//            sysUcaccountantInfoDetail.setLastTime(endTime.getTime() - sysUcaccountantInfoDetail.getStartTime().getTime());
            sysUnaccountantInfoDetailMapper.updateByPrimaryKey(sysUcaccountantInfoDetail);
            break;
            
            
            }
            
        }

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
        	Thread.currentThread().interrupt();
        }
        }
        
    }
    
    /**
     *  查询产品组所有产品
     * @param prdGrpId 产品组ID
     * @return
     */
    private List<String> findAllProdbyGroup(String prdGrpId) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        ValBatchCronJobMapper valBatchCronJobMapper = SpringContextUtil.getBean(ValBatchCronJobMapper.class);
        List<String> res = valBatchCronJobMapper.findAllProdbyGroup(prdGrpId);
        DynamicDataSourceHolder.clearDataSource();
        return res;
    }
    
 private List<SztInterfValuation> selectValuation(String date,List<String> product){
        
        
        List<SztInterfValuation> list=  new ArrayList<SztInterfValuation>();
        Map<String,Object> map = new HashMap<>();
        map.put("finprodIds", product);
        //1 估值表  0 余额表
        map.put("interType", "0");
        map.put("cdate", date.replace("-", ""));
        list =unitfycaccountantInfoMapper.selectValuation(map);
        return list;
        
    }
    

}
