/*
 * 文件名：ResetBaseRuleInfoReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：sjj
 * 修改时间：2021年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.cfetssettle.service;

import java.util.*;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.TrdBankManualSetDetailMapper;
import com.joyintech.tams.common.base.dao.TrdBankManualSettleFlowMapper;
import com.joyintech.tams.common.base.dao.TrdBankManualSettleInfoMapper;
import com.joyintech.tams.common.base.entity.TrdBankManualSetDetail;
import com.joyintech.tams.common.base.entity.TrdBankManualSettleFlow;
import com.joyintech.tams.common.base.entity.TrdBankManualSettleInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.batch.cfetssettle.dao.CfetsSettleMapper;
import com.joyintech.tams.invest.batch.cfetssettle.dto.CfetsSettleAllInfoDto;
import com.joyintech.tams.invest.batch.cfetssettle.dto.CfetsSettleDetailInfoDto;
import com.joyintech.tams.invest.batch.cfetssettle.dto.CfetsSettleReaderResult;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.service.MapperDelegateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;

/**
 * reader相关业务处理
 * 〈功能详细描述〉
 * @author sjj
 * @version 1.0 2021年11月19日
 * @see CfetsSettleService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class CfetsSettleService extends BaseService{
    private static final Logger logger = LoggerFactory.getLogger(CfetsSettleService.class);
    @Autowired
    private CfetsSettleMapper cfetsSettleMapper;

    @Autowired
    private TrdBankManualSettleFlowMapper trdBankManualSettleFlowMapper;

    @Autowired
    private TrdBankManualSetDetailMapper trdBankManualSetDetailMapper;

    /**
     * 银行间手工交收流水信息Entity
     * @param cfetsSettleAllInfoDto
     * @param executeDate 跑批日
     * @return
     */
    private TrdBankManualSettleFlow createTrdBankManualSettleFlow(CfetsSettleAllInfoDto cfetsSettleAllInfoDto , Date executeDate) {
        TrdBankManualSettleFlow trdBankManualSettleFlow = new TrdBankManualSettleFlow();
        //交收流水编号=调用【共通】生成
        trdBankManualSettleFlow.setSeqNo(IDGenerator.generate());
        //交收编号=银行间手工交收信息Entiy.交收编号
        trdBankManualSettleFlow.setSettleSeqNo(cfetsSettleAllInfoDto.getSettleSeqNo());
        //交收时间=当前时间
        trdBankManualSettleFlow.setSettleTime(new Date());
        //交收数量=画面.交收数量
        trdBankManualSettleFlow.setSettleNum(cfetsSettleAllInfoDto.getSettleNumT());
        //交收金额=画面.交收金额
        trdBankManualSettleFlow.setSettleAmt(cfetsSettleAllInfoDto.getSettleAmtT());
        //金融产品代码=画面.资产代码
        trdBankManualSettleFlow.setFinprodId(cfetsSettleAllInfoDto.getFinprodId());
        //金融产品代码=画面.资产简称
        trdBankManualSettleFlow.setFinprodAbbr(cfetsSettleAllInfoDto.getFinprodAbbr());
        //操作类型=交收
        trdBankManualSettleFlow.setOperType(DictCodeListExt3.交收操作类型_交收.getValue());
        trdBankManualSettleFlow.setCreateUser("SYSTEM");
        trdBankManualSettleFlow.setUpdateUser("SYSTEM");
        return trdBankManualSettleFlow;
    }

    /**
     * 构建银行间手工交收信息Entity
     * @param cfetsSettleAllInfoDto
     * * @param executeDate 跑批日
     * @return
     */
    private TrdBankManualSettleInfo createTrdBankManualSettleInfo(CfetsSettleAllInfoDto cfetsSettleAllInfoDto, Date executeDate) {
        TrdBankManualSettleInfo trdBankManualSettleInfo =new TrdBankManualSettleInfo();
        //交收编号
        trdBankManualSettleInfo.setSettleSeqNo(
                cfetsSettleAllInfoDto.getSettleSeqNo()==null? IDGenerator.generate():cfetsSettleAllInfoDto.getSettleSeqNo());
        //原业务编号
        trdBankManualSettleInfo.setTradeId(cfetsSettleAllInfoDto.getTotalTradeId());
        //金融产品代码
        trdBankManualSettleInfo.setFinprodId(cfetsSettleAllInfoDto.getFinprodId());
        //交收时间=平台日
        trdBankManualSettleInfo.setSettleDate(executeDate);
        //交收数量
        trdBankManualSettleInfo.setSettleNum(cfetsSettleAllInfoDto.getSettleNumT());
        //交收金额
        trdBankManualSettleInfo.setSettleAmt(cfetsSettleAllInfoDto.getSettleAmtT());
        //业务类别
        trdBankManualSettleInfo.setBusiType(cfetsSettleAllInfoDto.getTgBusiType());
        //交收状态
        trdBankManualSettleInfo.setSettleStatus(DictCodeListExt3.交收状态_已交收.getValue());
        trdBankManualSettleInfo.setCreateUser("SYSTEM");
        trdBankManualSettleInfo.setUpdateUser("SYSTEM");
        return trdBankManualSettleInfo;
    }

    /**
     * 交收自动生成
     *
     * @param batchDate 执行日
     * @param productIdList
     * @see
     * @since 1.0
     */
    public void autoSettle(Date batchDate, List<String> productIdList) {
        // 删除当日持仓数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("batchDate", batchDate);
        paramMap.put("createrUser","SYSTEM");

        //删除当日手工交收数据
        //删除交收流水表
        cfetsSettleMapper.deleteTodayCfetsSettleFlow(paramMap);
        //删除交收明细表
        cfetsSettleMapper.deleteTodayCfetsSettleDetail(paramMap);
        //删除交收信息表
        cfetsSettleMapper.deleteTodayCfetsSettleInfo(paramMap);

        //生成当日手工交收数据
        List<CfetsSettleReaderResult> insertDtoLists = this.createTodayTodoSettleData(paramMap);
        //插入银行间手工交收相关表
        for(CfetsSettleReaderResult cfetsManualSettleInsertDto : insertDtoLists){
            //存在交收编号，说明是做过交收后又回溯，则做更新动作
            cfetsSettleMapper.mergeInfo(cfetsManualSettleInsertDto.getTrdBankManualSettleInfo());
            //新增交收流水
            trdBankManualSettleFlowMapper.insertSelective(cfetsManualSettleInsertDto.getTrdBankManualSettleFlow());
            //新增交收明细
            for(TrdBankManualSetDetail trdBankManualSetDetail:cfetsManualSettleInsertDto.getListTrdBnakSetDetails()){
                trdBankManualSetDetailMapper.insertSelective(trdBankManualSetDetail);
            }
        }

        //发送头寸 TODO

    }

    private List<CfetsSettleReaderResult> createTodayTodoSettleData(Map<String, Object> param) {
        List<CfetsSettleReaderResult> listTemp = new ArrayList<CfetsSettleReaderResult>();
        //平安业务类型
        param.put("PINAN_BUSINESS_TYPE_09", DictCodeListExt1.平安业务类型_债券类资产买入.getValue());
        param.put("PINAN_BUSINESS_TYPE_10", DictCodeListExt1.平安业务类型_债券类资产卖出.getValue());
        param.put("PINAN_BUSINESS_TYPE_11", DictCodeListExt1.平安业务类型_债券类资产行权.getValue());
        param.put("PINAN_BUSINESS_TYPE_35", DictCodeListExt1.平安业务类型_债券中间收息.getValue());
        param.put("PINAN_BUSINESS_TYPE_36", DictCodeListExt1.平安业务类型_债券中间还本.getValue());
        param.put("PINAN_BUSINESS_TYPE_37", DictCodeListExt1.平安业务类型_债券到期.getValue());
        param.put("PINAN_BUSINESS_TYPE_20", DictCodeListExt1.平安业务类型_正回购.getValue());
        param.put("PINAN_BUSINESS_TYPE_51", DictCodeListExt1.平安业务类型_逆回购到期.getValue());
        param.put("PINAN_BUSINESS_TYPE_52", DictCodeListExt1.平安业务类型_正回购到期.getValue());
        param.put("PINAN_BUSINESS_TYPE_74", DictCodeListExt1.平安业务类型_买断式正回购.getValue());
        param.put("PINAN_BUSINESS_TYPE_75", DictCodeListExt1.平安业务类型_买断式逆回购.getValue());
        param.put("PINAN_BUSINESS_TYPE_76", DictCodeListExt1.平安业务类型_买断式正回购到期.getValue());
        param.put("PINAN_BUSINESS_TYPE_77", DictCodeListExt1.平安业务类型_买断式逆回购到期.getValue());
        param.put("PINAN_BUSINESS_TYPE_105", DictCodeListExt2.平安业务类型_债券借贷融入.getValue());
        param.put("PINAN_BUSINESS_TYPE_106", DictCodeListExt2.平安业务类型_债券借贷融出.getValue());
        param.put("PINAN_BUSINESS_TYPE_107", DictCodeListExt2.平安业务类型_债券借贷融入到期.getValue());
        param.put("PINAN_BUSINESS_TYPE_108", DictCodeListExt2.平安业务类型_债券借贷融出到期.getValue());

        //收付方向
        param.put("feePs02", DictCodeListExt1.收付方向_支出.getValue());
        //结算状态
        param.put("settlementStatus01", DictCodeList.结算状态_已结算.getValue());
        param.put("settlementStatus02", DictCodeList.结算状态_已结算.getValue());
        //指令处理状态
        param.put("commandDealStatus01", DictCodeList.指令处理状态_处理成功.getValue());
        param.put("commandDealStatus02", DictCodeList.指令处理状态_处理失败.getValue());
        //指令处理状态
        param.put("cleanStatus01", DictCodeListExt3.清算状态_清算成功.getValue());
        param.put("cleanStatus02", DictCodeListExt3.清算状态_清算失败.getValue());
        param.put("cleanStatus03", DictCodeListExt3.清算状态_清算中.getValue());

        //交收状态
        param.put("DELIVERY_STATUS_01", DictCodeListExt3.交收状态_未交收.getValue());

        param.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());

        param.put("isCalcY", BussinessDictCodeList.是否_是.getValue());

        param.put("batchDate", param.get("batchDate"));

        //查询待交收数据List
        List<CfetsSettleAllInfoDto> cfetsSettleDtoList = cfetsSettleMapper.selectAllSettleToDoList(param);

        if (!StringUtil.isEmptyList(cfetsSettleDtoList)) {
            for (CfetsSettleAllInfoDto cfetsSettleAllInfoDto : cfetsSettleDtoList) {
                CfetsSettleReaderResult cfetsSettleReaderResult = new CfetsSettleReaderResult();
                //银行间手工交收信息Entity
                TrdBankManualSettleInfo trdBankManualSettleInfo = this.createTrdBankManualSettleInfo(cfetsSettleAllInfoDto, DateUtil.parse(param.get("batchDate").toString()));
                //银行间手工交收流水信息Entity
                TrdBankManualSettleFlow trdBankManualSettleFlow = this.createTrdBankManualSettleFlow(cfetsSettleAllInfoDto, DateUtil.parse(param.get("batchDate").toString()));
                //银行间手工交收明细信息Entity
                List<TrdBankManualSetDetail> listTrdBnakSetDetails = new ArrayList<TrdBankManualSetDetail>();
                for (CfetsSettleDetailInfoDto CfetsSettleDetailInfoDto : cfetsSettleAllInfoDto.getCfetsSettleDetailInfoDtoList()) {

                    TrdBankManualSetDetail trdBankManualSetDetail = new TrdBankManualSetDetail();
                    //交收流水序号=调用【共通】生成
                    trdBankManualSetDetail.setSeqNo(trdBankManualSettleFlow.getSeqNo() != null ?
                            trdBankManualSettleFlow.getSeqNo() : IDGenerator.generate());
                    //交收编号=银行间手工交收信息Entiy.交收编号
                    trdBankManualSetDetail.setSettleSeqNo(trdBankManualSettleInfo.getSettleSeqNo());
                    //交收日期=平台日
                    trdBankManualSetDetail.setSettleDate(DateUtil.parse((String) param.get("batchDate")));
                    //原业务编号=成交确认明细.交易编号
                    trdBankManualSetDetail.setTradeId(CfetsSettleDetailInfoDto.getTradeId());
                    //金融产品代码=画面.资产代码
                    trdBankManualSetDetail.setBusiType(cfetsSettleAllInfoDto.getFinprodId());
                    //金融产品简称=画面.资产简称
                    trdBankManualSetDetail.setBusiType(cfetsSettleAllInfoDto.getFinprodAbbr());
                    //组合代码=成交确认明细.投资组合代码
                    trdBankManualSetDetail.setPortfolioId(CfetsSettleDetailInfoDto.getPortfolioId());
                    //交割数量=成交确认明细.交易份额
                    trdBankManualSetDetail.setSettleNum(CfetsSettleDetailInfoDto.getSettleNum());
                    //交割金额=成交确认明细.交易金额
                    trdBankManualSetDetail.setSettleAmt(CfetsSettleDetailInfoDto.getSettleAmt());
                    //业务类别=画面.投管业务类型
                    trdBankManualSetDetail.setBusiType(cfetsSettleAllInfoDto.getTgBusiType());

                    //交收状态=数据字典.交收状态.已交收
                    trdBankManualSetDetail.setSettleStatus(DictCodeListExt3.交收状态_已交收.getValue());
                    trdBankManualSetDetail.setCreateUser("SYSTEM");
                    trdBankManualSetDetail.setUpdateUser("SYSTEM");

                    listTrdBnakSetDetails.add(trdBankManualSetDetail);
                }

                cfetsSettleReaderResult.setTrdBankManualSettleInfo(trdBankManualSettleInfo);
                cfetsSettleReaderResult.setTrdBankManualSettleFlow(trdBankManualSettleFlow);
                cfetsSettleReaderResult.setListTrdBnakSetDetails(listTrdBnakSetDetails);
//
                listTemp.add(cfetsSettleReaderResult);
            }
        }

        return listTemp;
    }
}
