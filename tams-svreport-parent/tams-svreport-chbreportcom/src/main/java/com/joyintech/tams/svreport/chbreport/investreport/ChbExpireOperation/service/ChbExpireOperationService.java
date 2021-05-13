package com.joyintech.tams.svreport.chbreport.investreport.ChbExpireOperation.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.svreport.chbreport.investreport.ChbExpireOperation.dao.ChbExpireOperationMapper;
import com.joyintech.tams.svreport.chbreport.investreport.ChbExpireOperation.dto.ChbExpirePrdInfo;
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
public class ChbExpireOperationService extends CommonExportService<ChbExpirePrdInfo> {

    @Autowired
    private ChbExpireOperationMapper chbExpireOperationMapper;

    /**
     * 针对weblogic部署无法获取模版问题
     */
    private static final String TMP_DIR = "/tpl/";

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(ChbExpireOperationService.class);

    /**
     * 查询成立公告中产品相关信息
     * @param params
     * @return
     * @throws Exception
     */
    public ChbExpirePrdInfo qryChbExpirePrdInfo(Map<String, Object> params){
        List<ChbExpirePrdInfo> list = chbExpireOperationMapper.getExpireinfo(params);
        ChbExpirePrdInfo chbExpirePrdInfo = new ChbExpirePrdInfo();
        if(!StringUtil.isEmptyList(list)) {
            chbExpirePrdInfo =  list.get(0);
        }

        return chbExpirePrdInfo;
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
            ChbExpirePrdInfo chbExpirePrdInfo = this.qryChbExpirePrdInfo(param);
            dataMap.put("prdInfo", chbExpirePrdInfo);//产品信息
            mapList.add(dataMap);
            titleList.add(ChbInvestReportUtil.generateFileNameForReport(DictCodeListExt3.中债披露报告_到期公告.getValue(),
                    chbExpirePrdInfo.getProdName(), chbExpirePrdInfo.getProdId(), chbExpirePrdInfo.getCustomerType(),
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
        String srcFilePath = FileNameUtil.getTplFile("chbreport/ChbExpireOperation");
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        List<String> titleList = new ArrayList<String>();
        this.createData(reportName, map, mapList, titleList);
        if(mapList.size() == 1) {
            return WordBatchUtil.exportMillCertificateWord(mapList.get(0), srcFilePath,ChbInvestReportConstant.到期公告模板文件, titleList.get(0));
        }
        else {
            String zipname = reportName + ChbInvestReportConstant.下划线 + DateUtil.format(new Date(), ChbInvestReportConstant.日期格式化yyyyMMddHHmmss);
            return WordBatchUtil.exportWordBatch(mapList, titleList, ChbInvestReportConstant.到期公告模板文件, srcFilePath, zipname);
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
        String srcFilePath = FileNameUtil.getTplFile("chbreport/ChbExpireOperation");
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        List<String> titleList = new ArrayList<String>();
        this.createData(reportName, map, mapList, titleList);
        return WordBatchUtil.createMillCertificateWord(mapList.get(0), srcFilePath,ChbInvestReportConstant.到期公告模板文件, titleList.get(0));
    }

}
