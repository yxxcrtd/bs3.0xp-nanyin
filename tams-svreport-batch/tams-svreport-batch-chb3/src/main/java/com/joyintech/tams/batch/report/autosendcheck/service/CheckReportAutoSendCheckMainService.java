/*
 * 文件名：CheckReportAutoSendCheckMainService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年7月23日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.batch.report.autosendcheck.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.submitmaintenance.equityinfo.service.EquityInfoDistinctService;
import com.joyintech.tams.report.submitmaintenance.equityinfo.validate.EquityInfoValid;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.service.InvestorsHoldInfoDistinctService;
import com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.report.submitmanagement.equityinforegist.common.EquityInfoRegistCommonUtil;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dao.EquityInfoRegist001Mapper;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dto.EquityInfoRegist001Dto;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dao.InvestorsHoldInfoRegist001Mapper;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dto.InvestorsHoldInfoRegist001Dto;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.validate.InvestorsHoldInfoRegist001Validate;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 报送前检查自动检查 业务service
 * @author zuoyinyi
 * @version 1.0 2018年7月23日
 * @see CheckReportAutoSendCheckMainService
 * @since 1.0
 */
@Service
public class CheckReportAutoSendCheckMainService extends AutoSendCBBaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * EquityInfoValid
     */
    @Autowired
    private EquityInfoValid equityInfoValid;

    /**
     * InvestorsHoldInfoRegist001Validate
     */
    @Autowired
    private InvestorsHoldInfoRegist001Validate investorsHoldInfoRegist001Validate;

    /**
     * CheckReport001Mapper
     */
    @Autowired
    private CheckReport001Mapper chbReportCheckMapper;
    
    @Autowired
    private InvestorsHoldInfoRegist001Mapper investorsHoldInfoRegist001Mapper;
    
    @Autowired
    private EquityInfoRegist001Mapper equityInfoRegist001Mapper;
    
    @Autowired
    private EquityInfoDistinctService equityInfoDistinctService;
    @Autowired
    private InvestorsHoldInfoDistinctService investorsHoldInfoDistinctService;
    

    /**
     * 自动检查报送
     *
     * @see
     * @since 1.0
     */
    public void autoSendCB() {

        setBatchBeanName(BatchBeanName.投资者报送前检查);

        Date batchStartTime = DateUtil.now();

        try {
            logInfoBatchStart();

            logInfoAutoCheckStart();
            //查询条件
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("status01", DictCodeListExt2.报送数据状态_新建.getValue());
            map.put("status02", DictCodeListExt2.报送数据状态_修改.getValue());
            map.put("sendStatus", DictCodeListExt2.报送状态_未报送.getValue());
            //投资者身份信息未报送数据信息
            List<EquityInfoRegist001Dto> equityRegistData = equityInfoRegist001Mapper.selectEquityRegistData(map);
            //投资者持有信息未报送数据信息
            List<InvestorsHoldInfoRegist001Dto> investRegistData = investorsHoldInfoRegist001Mapper.selectInvestRegistData(map);

            if (CollectionUtils.isEmpty(investRegistData) && CollectionUtils.isEmpty(equityRegistData)) {
                logInfoNoHandleData(batchStartTime);
                return;
            }

            //定义每次操作的数据条数（1000）
            Integer singleTime = 100;
/**--------------------------------------------------投资者身份信息处理------------------------------------------------------------*/
            //投资者身份信息分批处理
            Integer equityTotal = equityRegistData.size();
            if(equityTotal!=0){
                if(equityTotal < singleTime){
                    this.equityLogicDeal(equityRegistData);
                }
                else{
                    //超过1000条分批处理
                    for(int i = 0; i < equityTotal/singleTime ; i++){
                        this.equityLogicDeal(equityRegistData.subList(0, singleTime));
                        equityRegistData.removeAll(equityRegistData.subList(0, singleTime));
                    }
                    if(CollectionUtils.isNotEmpty(equityRegistData)){
                        this.equityLogicDeal(equityRegistData);
                    }
                }
            }
            /** 报送前检查增加 身份信息三要素重复检查处理 add by yuluan begin**/
            equityInfoDistinctService.checkBeforeSubmit();
            /** 报送前检查增加 身份信息三要素重复检查处理 add by yuluan end**/
/**--------------------------------------------------投资者持有信息处理------------------------------------------------------------*/
            // 删除检查表数据
            chbReportCheckMapper.delReportAllCheckDatas(DictCodeListExt2.报表名称_投资者持有信息登记.getValue());

           //投资者持有信息分批处理
            Integer investTotal = investRegistData.size();
            if(investTotal!=0){
                if(investTotal < singleTime){
                    this.investRegistLogicDeal(investRegistData);
                }
                else{
                    //超过1000条分批处理
                    for(int i = 0; i < investTotal/singleTime ; i++){
                        this.investRegistLogicDeal(investRegistData.subList(0, singleTime));
//                        equityRegistData.removeAll(investRegistData.subList(0, singleTime));
                        investRegistData.removeAll(investRegistData.subList(0, singleTime));
                    }
                    if(CollectionUtils.isNotEmpty(investRegistData)){
                        this.investRegistLogicDeal(investRegistData);
                    }
                }
            }
            /** 报送前检查增加 持有信息三要素重复检查处理 add by yuluan begin**/
            investorsHoldInfoDistinctService.checkBeforeSubmit();
            /** 报送前检查增加 持有信息三要素重复检查处理 add by yuluan end**/
            logInfoBatchEnd();
        }
        catch (Exception e) {
            logInfo(e.getMessage());
			logger.error("报送前检查自动检查Error",e);
            insertResultLogException(batchStartTime);
            throw e;
        }
    }

    /**
     *
     * 持有信息逻辑处理
     * @param equityInfoRegistRstList 数据源
     * @see
     * @since 1.0
     */
    public void equityLogicDeal(List<EquityInfoRegist001Dto> equityRegistData){
      //检查结果集
        List<CheckReport001Dto> resultList = new ArrayList<>();
      //投资者身份信息数据检查结果
//        EquityInfoRegistCommonUtil.format(equityRegistData, StringUtil.SPACE);
    	EquityInfoRegistCommonUtil.formatByType(equityRegistData,true);
        List<ExcelValidDto> equityInfoRegistRstList = equityInfoValid.checkDataEquity(equityRegistData,"2",null,null);      //数据重组
        //投资者身份信息
        List<EquityInfoRegist001Dto> equityInfoRegistUpdateList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(equityInfoRegistRstList)){
            equityInfoRegistRstList.forEach(item->{
                //检查结果
                CheckReport001Dto chbReportCheck = new CheckReport001Dto();
                BeanUtil.copyPropertiesIgnoreNull(chbReportCheck, item);
                chbReportCheck.setCheckSeqno(CommonUtil.getSequenceIndexData(DataIndexSequenceType.检查序列号));
                chbReportCheck.setRepCode(DictCodeListExt2.报表名称_投资者身份信息登记.getValue());
                chbReportCheck.setRepName(DictCodeListExt2.报表名称_投资者身份信息登记.getCodeName());
                chbReportCheck.setCheckResult(SysDataUtil.getCodeFromDataDictByName(
                    DictCode.检查结果, item.getCheckResult()));
                if(!chbReportCheck.getCheckResult().equals(DictCodeListExt2.检查结果_检查通过.getValue())){
                	resultList.add(chbReportCheck);
                }

                //更新检查序列号对应的数据
                EquityInfoRegist001Dto dto = new EquityInfoRegist001Dto();
                dto.setIndentifyMark(item.getValidKey());
                dto.setCheckSeqno(chbReportCheck.getCheckSeqno());
                if(!chbReportCheck.getCheckResult().equals(DictCodeListExt2.检查结果_检查通过.getValue())){
                	equityInfoRegistUpdateList.add(dto);
                }
            });
        }

        if(CollectionUtils.isNotEmpty(resultList)){
            //批量插入报送前检查表数据
            chbReportCheckMapper.batchInsert(resultList);
            //批量更新投资者身份信息检查序列号
            if(CollectionUtils.isNotEmpty(equityInfoRegistUpdateList)){
            	equityInfoRegist001Mapper.updateEquityRegistCheckSeqno(equityInfoRegistUpdateList);
            }
        }

    }

    /**
     *
     * 投资者持有信息逻辑处理
     * @param investRegistData 数据源
     * @see
     * @since 1.0
     */
    public void investRegistLogicDeal(List<InvestorsHoldInfoRegist001Dto> investRegistData){
        //检查结果集
        List<CheckReport001Dto> resultList = new ArrayList<>();

        //投资者持有信息数据检查结果
        List<ExcelValidDto> investRegistRstList = investorsHoldInfoRegist001Validate.checkData(investRegistData, "true");

        //投资者持有信息
        List<InvestorsHoldInfoRegist001Dto> investRegistUpdateList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(investRegistRstList)){
            //检查结果信息
            investRegistRstList.forEach(item->{
                CheckReport001Dto chbReportCheck = new CheckReport001Dto();
                BeanUtil.copyPropertiesIgnoreNull(chbReportCheck, item);
                chbReportCheck.setCheckSeqno(CommonUtil.getSequenceIndexData(DataIndexSequenceType.检查序列号));
                chbReportCheck.setRepCode(DictCodeListExt2.报表名称_投资者持有信息登记.getValue());
                chbReportCheck.setRepName(DictCodeListExt2.报表名称_投资者持有信息登记.getCodeName());
                chbReportCheck.setCheckResult(SysDataUtil.getCodeFromDataDictByName(
                    DictCode.检查结果, item.getCheckResult()));
                if(!chbReportCheck.getCheckResult().equals(DictCodeListExt2.检查结果_检查通过.getValue())){
                	resultList.add(chbReportCheck);
                }

                //更新检查序列号对应的数据
                InvestorsHoldInfoRegist001Dto dto = new InvestorsHoldInfoRegist001Dto();
                List<String> keyList = StringUtil.splitToList(GlobalConstant.COMMA,item.getValidKey());
                dto.setPrdRegistCode(keyList.get(0).substring(6).trim());
                dto.setIndentifyMark(keyList.get(1).substring(4).trim());
                dto.setHoldDate(DateUtil.parse(keyList.get(2).substring(4)));
                dto.setCcy(keyList.get(3).substring(2).trim());
                dto.setRegistBankCode(keyList.get(4).substring(6).trim());
                dto.setCheckSeqno(chbReportCheck.getCheckSeqno().trim());
                if(!chbReportCheck.getCheckResult().equals(DictCodeListExt2.检查结果_检查通过.getValue())){
                	investRegistUpdateList.add(dto);
                }
            });
        }

        //批量入库
        if(CollectionUtils.isNotEmpty(resultList)){
            //批量插入报送前检查表数据
            chbReportCheckMapper.batchInsert(resultList);

            //批量更新投资者持有信息检查序列号
            if(CollectionUtils.isNotEmpty(investRegistUpdateList)){
            	investorsHoldInfoRegist001Mapper.updateInvestRegistCheckSeqno(investRegistUpdateList);
            }
        }
    }
}
