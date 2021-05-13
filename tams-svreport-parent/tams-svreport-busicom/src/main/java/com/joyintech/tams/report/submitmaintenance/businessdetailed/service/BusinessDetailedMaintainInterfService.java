/*
 * 文件名：BusinessDetailedMaintainInterfService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zyf
 * 修改时间：2017年9月13日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.submitmaintenance.businessdetailed.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.XmlParseUtil;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.dao.IBDMapper;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.entity.InterestsBusinessDetailed;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;

/**
 * 
 * 权益人业务信息维护
 * 〈功能详细描述〉
 * @author zyf
 * @version 1.0 2017年9月13日
 * @see BusinessDetailedMaintainInterfService
 * @since 1.0
 */
@Service
public class BusinessDetailedMaintainInterfService {
    
    /**
     * 日志信息
     */
    private static final Log LOGGER = LogFactory.getLog(BusinessDetailedMaintainInterfService.class);
    
    @Autowired
    private IBDMapper ibdMapper;
    
    /**
     * 获取XML解析后的权益人业务明细数据
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
//    public void getBusinessDetailedData(){
//        //从缓存中获取开关项配置  01 开 02 闭
//        String isOpen = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, ReportGlobalConstant.DICT_INTERF_IMPORT_SWITCH);
//        List<Map<String, String>> result;
//        String xmlFilePath = "D:\\joyin\\workspace-zz\\report-parent\\report\\src\\test\\java\\com\\joyintech\\tams\\test\\demo.xml";
//        List <InterestsBusinessDetailed> ibdList = new ArrayList<InterestsBusinessDetailed>();
//        InterestsBusinessDetailed ibd = null;
//        try {
//            result = XmlParseUtil.parse(xmlFilePath);
////            result.forEach(System.out::println);
//            for(Map<String,String> map:result){
//                ibd = dataConversionEntity(map);
//                ibd.setRegCode(ibdMapper.selectRegCodeByProdId(ibd.getAccount()).getRegCode());
//                AjaxResult wr = ibd.validCheck();
//                if(wr.getStatuCode() == 500){
//                    ibd.setStatus("P");
//                }else{
//                    ibd.setStatus("E");
//                }
//                ibdList.add(ibd);
//            }
//            List<String> coverList = updateDataSwitch(); 
//            LOGGER.debug("********权益人业务明细接口数据插入开始********");
//            ibdMapper.saveIbdDataBatch(ibdList, isOpen, coverList);
//            LOGGER.debug("********权益人业务明细接口数据插入结束********");
//        }
//        catch (ParserConfigurationException | SAXException | IOException e) {
////            e.printStackTrace();
//        	LOGGER.error(e.getMessage(),e);
//        }
//    }
    
    @Value("${chbConfig.CHB_EQUDET}")
    private String chbEqudet;
    
    public List<String> updateDataSwitch(){
        List<String> coverList = null;
        if (StringUtil.isNotEmpty(chbEqudet)) {
            coverList = StringUtil.splitToList(GlobalConstant.COMMA,
            		chbEqudet);
        }
        return coverList;
    }
    
    @SuppressWarnings("deprecation")
    public InterestsBusinessDetailed dataConversionEntity(Map<String,String> map){
        InterestsBusinessDetailed ibd = new InterestsBusinessDetailed();
        ibd.setBusiSeqno(map.get("busiSeqno"));
        ibd.setAccount(map.get("account"));
        ibd.setAccountName(map.get("accountname"));
        ibd.setRegistBankCode(map.get("registBankCode"));
        ibd.setIndentifyMark(map.get("indentifyMark"));
        ibd.seteQuityConfDate(new Date(map.get("eQuityConfDate")));
        ibd.seteQuityConfDateStr(map.get("eQuityConfDate"));
        ibd.setBuyPlace(map.get("buyPlace"));
        ibd.setBusiType(map.get("busiType"));
        ibd.setCcy(map.get("ccy"));
        ibd.setAmount(Double.parseDouble(map.get("amount")));
        ibd.setShares(Double.parseDouble(map.get("shares")));
        ibd.setChannel(map.get("channel"));
        ibd.setFundAcc(map.get("fundAcc"));
        ibd.setFundAccBank(map.get("fundAccBank"));
        ibd.setIsSubmit(map.get("isSubmit"));
        ibd.setIsConsign(map.get("isConsign"));
        ibd.setRemark(map.get("remark"));
        ibd.setWorkflowId(map.get("workflowId"));
        ibd.setDataSource(map.get("dataSource"));
        ibd.setStatus(map.get("status"));
        ibd.setCreateUser(map.get("createUser"));
        ibd.setCreateDept(map.get("createDept"));
        ibd.setCreateTime(new Date(map.get("createTime")));
        ibd.setUpdateUser(map.get("updateUser"));
        ibd.setUpdateTime(new Date(map.get("updateTime")));
        return ibd;
    }
    
    
}
