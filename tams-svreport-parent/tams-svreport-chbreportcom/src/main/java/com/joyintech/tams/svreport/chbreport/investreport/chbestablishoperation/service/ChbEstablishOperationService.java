package com.joyintech.tams.svreport.chbreport.investreport.chbestablishoperation.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.svreport.chbreport.investreport.chbestablishoperation.dao.ChbEstablishOperationMapper;
import com.joyintech.tams.svreport.chbreport.investreport.chbestablishoperation.dto.ChbEstablishPrdInfo;
import com.joyintech.tams.svreport.chbreport.investreport.utils.ChbInvestReportConstant;
import com.joyintech.tams.svreport.chbreport.investreport.utils.ChbInvestReportUtil;
import com.joyintech.tams.svreport.chbreport.investreport.utils.WordBatchUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

/**
 * @ClassName ChbEstablishOperationService
 * @Description: 成立公告查询service
 * @Author wangyong
 * @Date 2021-05-11
 * @Version V1.0
 **/
@Service
public class ChbEstablishOperationService extends CommonExportService<ChbEstablishPrdInfo> {

    @Autowired
    private ChbEstablishOperationMapper chbEstablishOperationMapper;

    /**
     * 针对weblogic部署无法获取模版问题
     */
    private static final String TMP_DIR = "/tpl/";

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(ChbEstablishOperationService.class);

    /**
     * 查询成立公告中产品相关信息
     * @param params
     * @return
     * @throws Exception
     */
    public ChbEstablishPrdInfo qryChbEstablishPrdInfo(Map<String, Object> params){
        List<ChbEstablishPrdInfo> list = chbEstablishOperationMapper.getEstablishinfo(params);
        ChbEstablishPrdInfo chbEstablishPrdInfo = new ChbEstablishPrdInfo();
        if(!StringUtil.isEmptyList(list)) {
            chbEstablishPrdInfo =  list.get(0);
        }
        this.dealDictOrSpecialObj(chbEstablishPrdInfo);
        return chbEstablishPrdInfo;
    }

    /**
     * 处理返回的数据中需要处理数据字典及特殊字段的
     * @param chbEstablishPrdInfo
     */
    private void dealDictOrSpecialObj(ChbEstablishPrdInfo chbEstablishPrdInfo) {
        //理财信息系统登记编码
        if (StringUtil.isEmpty(chbEstablishPrdInfo.getRegCode())){
            chbEstablishPrdInfo.setRegCode(ChbInvestReportConstant.空字符);
        }
        //兑付本金币种显示名称
        String showName = ChbInvestReportUtil.getDictCodeAndName(DictCode.币种_中债, chbEstablishPrdInfo.getCcy());
        if ("人民币".equals(showName)){
            showName = "元" + showName;
        }
        if (StringUtil.isNotEmpty(showName)){
            chbEstablishPrdInfo.setCcy(showName);
            chbEstablishPrdInfo.setTradeCost(chbEstablishPrdInfo.getTradeCost() + showName);
        }
        else {
            chbEstablishPrdInfo.setCcy(ChbInvestReportConstant.空字符);
        }
    }

    /**
     * 组装数据
     * @param reportName
     * @param param
     * @param mapList
     * @param titleList
     */
    public void createData(String reportName, Map<String, Object> param ,List<Map<String, Object>> mapList, List<String> titleList) {
        if(StringUtil.isNotEmpty(param.get("endDate")) && StringUtil.isNotEmpty(param.get("prodId"))) {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            ChbEstablishPrdInfo chbEstablishPrdInfo = this.qryChbEstablishPrdInfo(param);
            dataMap.put("prdInfo", chbEstablishPrdInfo);//产品信息
            mapList.add(dataMap);
            titleList.add(ChbInvestReportUtil.generateFileNameForReport(DictCodeListExt3.中债披露报告_成立公告.getValue(),
                    chbEstablishPrdInfo.getProdName(), chbEstablishPrdInfo.getProdId(), chbEstablishPrdInfo.getCustomerType(),
                    param.get("endDate").toString()));
        }
    }

    /**
     * 导出公告
     * @param map
     * @param reportName
     * @return
     * @throws Exception
     */
    public ResponseEntity<byte[]> exportDoc(Map<String, Object> map, String reportName) throws Exception {
        String srcFilePath = FileNameUtil.getTplFile("chbreport/ChbEstablishOperation");
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        List<String> titleList = new ArrayList<String>();
        this.createData(reportName, map, mapList, titleList);
        if(mapList.size() == 1) {
            return WordBatchUtil.exportMillCertificateWord(mapList.get(0), srcFilePath,ChbInvestReportConstant.成立公告模板文件, titleList.get(0));
        }
        else {
            String zipname = reportName + ChbInvestReportConstant.下划线 + DateUtil.format(new Date(), ChbInvestReportConstant.日期格式化yyyyMMddHHmmss);
            return WordBatchUtil.exportWordBatch(mapList, titleList, ChbInvestReportConstant.成立公告模板文件, srcFilePath, zipname);
        }
    }

    /**
     * 创建成立报告
     * @param map
     * @param reportName
     * @return
     * @throws Exception
     */
    public File createDoc(Map<String, Object> map, String reportName) throws Exception {
        String srcFilePath = FileNameUtil.getTplFile("chbreport/ChbEstablishOperation");
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        List<String> titleList = new ArrayList<String>();
        this.createData(reportName, map, mapList, titleList);
        return WordBatchUtil.createMillCertificateWord(mapList.get(0), srcFilePath,ChbInvestReportConstant.成立公告模板文件, titleList.get(0));
    }

}
