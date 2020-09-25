package com.joyintech.tams.invest.batch.tradorSeq.service;

import com.joyintech.tams.invest.batch.tradorSeq.dao.TradorSeqForwardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 交易员序列生成批处理 (具体删除逻辑处理service)
 */
@Service
public class TradorSeqForwardDelService {
    @Autowired
    private TradorSeqForwardMapper tradorSeqMapper;
    
    /** @MethodName: 删除交易员序列
    * @Description: TODO
    * @Param: []
    * @Return: void
    * @Author: wangxuelei
    * @Date: 2020/1/6 0006
    **/
    public void deleteTradorSeq(){
        tradorSeqMapper.deleteTradorSeq();
    }
}
