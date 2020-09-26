/*
 * 文件名：DelayVerification001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月25日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.svreport.chb.datacheck.totalcheck.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.constant.CheckConfConstants;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.dao.ChbCheckRuleoutMapper;
import com.joyintech.tams.common.svreport.datacheck.dto.ChbCheckRuleout;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.report.interf.common.util.BigDecimalConverterUtil;
import com.joyintech.tams.svreport.chb.datacheck.totalcheck.dao.ChbTotalCheckCBMapper;
import com.joyintech.tams.svreport.chb.datacheck.totalcheck.dao.ChbTotalCheckDetailMapper;
import com.joyintech.tams.svreport.chb.datacheck.totalcheck.dao.ChbTotalCheckMapper;
import com.joyintech.tams.svreport.chb.datacheck.totalcheck.dto.ChbTotalCheckCBConstants;
import com.joyintech.tams.svreport.chb.datacheck.totalcheck.dto.ChbTotalCheckDetailDto;
import com.joyintech.tams.svreport.chb.datacheck.totalcheck.dto.ChbTotalCheckDto;
import com.joyintech.tams.svreport.chb.datacheck.totalcheck.util.ChbTotalCheckCBUtils;
import com.joyintech.tams.svreport.chb.monthreport.dao.ChbMonthReportMapper;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReport;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportDetail;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 
 * 功能:中体核查service
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年4月30日
 * @修改记录：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TotalCheck001Service extends BaseService<ChbTotalCheckDetailDto>{

    
    /**
     * 总体核查mapper
     */
    @Autowired
    private ChbTotalCheckMapper chbTotalCheckMapper;
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    
    /**
     * 延时报送检查的查询方法
     * @param param 存储了查询参数的Map
     * @return Map 存储了查询结果的Map
     * @see
     * @since 1.0
     */
    public Map<String,ChbTotalCheckDto> searchData(Map<String, Object> param) {
        Map<String,ChbTotalCheckDto> resultsMap= new HashMap<String, ChbTotalCheckDto>();
        dealSearchParam(param);
        List<ChbTotalCheckDto> dtoList = chbTotalCheckMapper.selectData(param);
        for(ChbTotalCheckDto dto : dtoList){
            addTips(dto);
            resultsMap.put(dto.getCheckType(),dto);
        }
        return resultsMap;
    }

    /**
     * 根据页面的参数查询Table的数据
     * <功能详细描述>
     * @param pageParam 存储了画面参数的MAP
     * @return 存储了查询结果
     * @see
     * @since 1.0
     */
    public Page<ChbTotalCheckDetailDto> searchTable(Map<String, Object> pageParam) {
        dealSearchParam(pageParam);
        Page<ChbTotalCheckDetailDto> resultPage = super.queryForPage(ChbTotalCheckDetailMapper.class, 
                "selectTotalCheckDetailByPage", pageParam);
        dealDataList(resultPage.getRoot(), false);
        return resultPage;
    }

    /**
     * 功能:处理传入参数
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月24日
     * @param param 
     * void
     */
    private void dealSearchParam(Map<String, Object> param) {
        if (null != param) {
            // 机构代码List
            String issuePartyIdListStr = (String) param.get("issuePartyIdList");
            if (!StringUtil.isBlank(issuePartyIdListStr)) {
                List<String> orgCodeList = StringUtil.splitToList(GlobalConstant.COMMA, issuePartyIdListStr);
                param.put("issuePartyIdList", orgCodeList);
            }
        }
    }
    /**
     * 功能:处理查询后结果
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月24日
     * @param list
     * @param isExport 
     * void
     */
    private void dealDataList(List<ChbTotalCheckDetailDto> list, boolean isExport) {
        list.forEach(item -> {
            if (StringUtil.isNotEmpty(item.getAssetType2())){
                item.setAssetType2(SvrDataUtil.getNameFromDataDict(DictCode.资产_负债二级类别,item.getAssetType2()));
            }
            if (StringUtil.isNotEmpty(item.getOperateMode())){
                item.setOperateMode(SvrDataUtil.getNameFromDataDict(DictCode.产品运作模式,item.getOperateMode()));
            }
            if (StringUtil.isNotEmpty(item.getRaiseIsDelay())){
                item.setRaiseIsDelay(SvrDataUtil.getNameFromDataDict(DictCode.是否_中债,item.getRaiseIsDelay()));
            }
            if (StringUtil.isNotEmpty(item.getAstHoldIsCount())){
                item.setAstHoldIsCount(SvrDataUtil.getNameFromDataDict(DictCode.是否_中债,item.getAstHoldIsCount()));
            }
            if (StringUtil.isNotEmpty(item.getIsReg())){
                item.setIsReg(SvrDataUtil.getNameFromDataDict(DictCode.是否_中债,item.getIsReg()));
            }
            if (StringUtil.isNotEmpty(item.getIsDeviation())){
                item.setIsDeviation(SvrDataUtil.getNameFromDataDict(DictCode.是否_中债,item.getIsDeviation()));
            }
            if (item.getDeviation() != null){
                item.setDeviationStr(BigDecimalConverterUtil.bigDecimalToStringWithPercent(item.getDeviation()));
            }
            if (item.getDeviationCny() != null){
                item.setDeviationCnyStr(BigDecimalConverterUtil.bigDecimalToStringWithPercent(item.getDeviationCny()));
            }
            if (item.getDeviationCny() != null){
                item.setDeviationCnyStr(BigDecimalConverterUtil.bigDecimalToStringWithPercent(item.getDeviationCny()));
            }
            if (item.getDetailYield() != null){
                item.setDetailYieldStr(BigDecimalConverterUtil.bigDecimalToStringWithPercent(item.getDetailYield()));
            }
        });
    }

    /**
     * 功能:读取系统中总体核查的对于颜色的配置参数
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月23日
     * @return 
     * Map<String,String[]>
     */
    public Map<String, String[]> readConfig() {
        Map<String, String[]> resultMap =new HashMap<String, String[]>();
        resultMap.put("mids", StringUtil.split(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_TOTALCHECK_COLOR_CONFIG_MID),
                GlobalConstant.COMMA));
        resultMap.put("tops", StringUtil.split(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_TOTALCHECK_COLOR_CONFIG_TOP),
                GlobalConstant.COMMA));
        return resultMap;
    }

    /**
     * 功能:添加提示信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月24日
     * @param totalCheck 
     * void
     */
    private void addTips(ChbTotalCheckDto totalCheck){
        if (ChbTotalCheckCBConstants.CHECKTYPE01.equals(totalCheck.getCheckType())){
            totalCheck.setTips(ChbTotalCheckCBConstants.CHECKTYPE01_TIPS);
        } else if (ChbTotalCheckCBConstants.CHECKTYPE02.equals(totalCheck.getCheckType())) {
            totalCheck.setTips(ChbTotalCheckCBConstants.CHECKTYPE02_TIPS);
        } else if (ChbTotalCheckCBConstants.CHECKTYPE03.equals(totalCheck.getCheckType())) {
            totalCheck.setTips(ChbTotalCheckCBConstants.CHECKTYPE03_TIPS);
        } else if (ChbTotalCheckCBConstants.CHECKTYPE04.equals(totalCheck.getCheckType())) {
            totalCheck.setTips(ChbTotalCheckCBConstants.CHECKTYPE04_TIPS);
        } else if (ChbTotalCheckCBConstants.CHECKTYPE05.equals(totalCheck.getCheckType())) {
            totalCheck.setTips(ChbTotalCheckCBConstants.CHECKTYPE05_TIPS);
        } else if (ChbTotalCheckCBConstants.CHECKTYPE06.equals(totalCheck.getCheckType())) {
            totalCheck.setTips(ChbTotalCheckCBConstants.CHECKTYPE06_TIPS);
        } else if (ChbTotalCheckCBConstants.CHECKTYPE07.equals(totalCheck.getCheckType())) {
            totalCheck.setTips(ChbTotalCheckCBConstants.CHECKTYPE07_TIPS);
        } else if (ChbTotalCheckCBConstants.CHECKTYPE08.equals(totalCheck.getCheckType())) {
            totalCheck.setTips(ChbTotalCheckCBConstants.CHECKTYPE08_TIPS);
        }
    }

}
