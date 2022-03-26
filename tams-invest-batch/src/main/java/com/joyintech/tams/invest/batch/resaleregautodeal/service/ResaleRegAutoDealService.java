package com.joyintech.tams.invest.batch.resaleregautodeal.service;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.batch.resaleregautodeal.dao.ResaleRegAutoDealMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 回售登记数据自动生成成交数据
 */
@Service
public class ResaleRegAutoDealService {

    /**
     * 是否单体服务
     */
    @Value("${application.micorservice.contextPath.single:false}")
    private boolean isSingle;

    @Autowired(required = false)
    private JoyinRestTemplate restTemplate;

    @Autowired(required = false)
    @Qualifier("simpleRestTemplate")
    protected JoyinRestTemplate simpleRestTemplate;

    @Value("${application.micorservice.contextPath.tamsServer:http://tams-server}")
    private String tamsSerever;

    @Autowired
    private ResaleRegAutoDealMapper resaleRegAutoDealMapper;

    /**
     * 回售登记数据自动生成成交数据
     * @param currentDate
     */
    public void run(Date currentDate) throws BatchException {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("cdate", DateUtil.format(currentDate));
        //先删除生成的数据应该
        List<String> totleTradeIds = resaleRegAutoDealMapper.listTotleTradeId(paramMap);
        if(CollectionUtils.isNotEmpty(totleTradeIds)){
            //删除匹配信息表中的数据
            totleTradeIds.forEach(e->{
                resaleRegAutoDealMapper.deletePadeal(e);
                resaleRegAutoDealMapper.deleteDealAdd(e);
                resaleRegAutoDealMapper.deleteDeal(e);
                resaleRegAutoDealMapper.deletePaDealTotal(e);
                resaleRegAutoDealMapper.deleteDealTotal(e);
                resaleRegAutoDealMapper.deleteMatchInfoByPreTradeId(e);
            });
            paramMap.put("totleTradeIds",totleTradeIds);
            //调用页面自动成交确认
            try{
                if(isSingle) {
                    simpleRestTemplate.postForObject(tamsSerever + "/cfets/common/CfetsIbuQuoteBatchCommon/resaleregautodeal", paramMap, Map.class);
                }
                else {
                    restTemplate.postForObject(tamsSerever + "/cfets/common/CfetsIbuQuoteBatchCommon/resaleregautodeal", paramMap, Map.class);
                }
            }catch (Exception e){
                throw new BatchException(e.getMessage());
            }
        }
    }
}
