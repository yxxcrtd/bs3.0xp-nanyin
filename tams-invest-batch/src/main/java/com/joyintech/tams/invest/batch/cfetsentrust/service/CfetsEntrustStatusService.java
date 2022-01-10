/*
 * 文件名：EntrustStatusService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.cfetsentrust.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.batch.cfetsentrust.dao.CfetsEntrustStatusMapper;
import com.joyintech.tams.invest.batch.cfetsentrust.dto.CfetsEntrustClosePosEtrReqDto;
import com.joyintech.tams.invest.batch.cfetsentrust.dto.CfetsEntrustClosePositionReqDto;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  更新关闭委托状态
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月15日
 * @see EntrustStatusService
 * @since 1.0
 */
@Service
public class CfetsEntrustStatusService {

    @Autowired
    private CfetsEntrustStatusMapper entrustStatusMapper;


   /* @Autowired
    private PinganBatchPlatformDateImpl platformDateService;*/
    
    public void run(SysBatchExecInfo batchExecuteInfo){
        if(null!=batchExecuteInfo.getExecuteDate()){
            Map<String, String> params = new HashMap<>();
            String sysDate = DateUtil.format(batchExecuteInfo.getExecuteDate());
            params.put("sysDate", sysDate);
            params.put("ENTRUST_STATUS_01", DictCodeList.委托状态_新建.getValue());
            params.put("ENTRUST_STATUS_02", DictCodeList.委托状态_待接收.getValue());
            params.put("ENTRUST_STATUS_08", DictCodeListExt3.委托状态_待分发.getValue());
            params.put("ENTRUST_STATUS_03", DictCodeList.委托状态_已接收.getValue());
            params.put("ENTRUST_STATUS_05", DictCodeList.委托状态_已退回.getValue());
            params.put("ENTRUST_STATUS_07", DictCodeList.委托状态_已撤回.getValue());
            List<CfetsEntrustClosePosEtrReqDto> entrustIdList = entrustStatusMapper.selectEntrustIds(params);
          //  entrustIdList.forEach(s -> baseEtrCashPositionCommService.entrustCloseExecute(s));
            closeDelegatePositionMethod(entrustIdList);

            entrustStatusMapper.updateDistributeStatus(params);//add by yangwenjun 20201211 更新委托指令分发表中的接收状态
            entrustStatusMapper.updateEntrustStatus(params);
        }
    }
    private void closeDelegatePositionMethod(List<CfetsEntrustClosePosEtrReqDto> dtolist){
        CfetsEntrustClosePositionReqDto req=new CfetsEntrustClosePositionReqDto();
        List<CfetsEntrustClosePosEtrReqDto> etrList=new ArrayList<>();
        for(CfetsEntrustClosePosEtrReqDto t: dtolist){
            CfetsEntrustClosePosEtrReqDto closeItem=new CfetsEntrustClosePosEtrReqDto();
            String busiTypePosition=DictCodeListExt3.头寸业务类型_现券交易.getValue();
            if(StringUtil.isNotEmpty(t.getPs())
                    && StringUtil.equals(t.getPs(), BussinessDictCodeList.交易方向_行权回售.getValue())){
                busiTypePosition=DictCodeListExt3.头寸业务类型_债券行权交易.getValue(); // 业务类型：01债券一级投标，02现券交易，03债券行权交易，04债券存续期交易
            }

            closeItem.setBusiType(busiTypePosition);
            closeItem.setPs(t.getPs());
            closeItem.setCloseId(t.getCloseId());
            closeItem.setSystemAct(DictCodeListExt3.系统动作_关闭.getValue());
            closeItem.setBusiAct(DictCodeListExt3.业务动作_指令日终关闭.getValue());
            etrList.add(closeItem);
        }
        req.setData(etrList);
        req.setUserId("SYSTEM");
        req.setTotalTradeId(IDGenerator.generate());
        req.setModelType(DictCodeListExt1.子系统代码_委托.getValue());
        PositionHttpRequest.batchClose(req);
    }
}
