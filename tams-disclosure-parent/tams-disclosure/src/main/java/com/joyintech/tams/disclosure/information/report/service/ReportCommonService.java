
package com.joyintech.tams.disclosure.information.report.service;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.information.report.dao.ReportDlxsjgmdggMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 信披报表共通处理
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenggaoling
 * @version 1.0 2022年07月10日
 * @see ReportCommonService
 * @since 1.0
 */
@Service("reportCommonService")
public class ReportCommonService {

    private static final String PUBTYPE_GONGGAO = "公告";
    private static final String PUBTYPE_BAOGAO = "报告";
    private static final String PUBSTRING_GONGGAO = "特此公告。";
    private static final String SALE_STRING_1 = "本理财产品代销机构或";
    private static final String SALE_STRING_2 = "代销机构及";
    private static final String RAISETYPE_GM = "公募";
    private static final String RAISETYPE_SM = "私募";
    @Autowired
    private ReportDlxsjgmdggMapper reportDlxsjgmdggMapper;

    /**
     * 根据募集方式设置公告、报告字样
     * @param raiseType 募集方式
     * @param dataMap 数据集
     * @return 数据集
     */
    public void setPubString(String raiseType, Map<String, Object> dataMap) {
        if (StringUtil.equals(raiseType, DictCodeListExt2.募集方式_中债_公募.getValue())) {
            dataMap.put("pubType", PUBTYPE_GONGGAO);
            dataMap.put("pubString", PUBSTRING_GONGGAO);
            dataMap.put("raiseType", RAISETYPE_GM);
        } else if (StringUtil.equals(raiseType, DictCodeListExt2.募集方式_中债_私募.getValue())) {
            dataMap.put("pubType", PUBTYPE_BAOGAO);
            dataMap.put("pubString", "");
            dataMap.put("raiseType", RAISETYPE_SM);
        }
    }

    /**
     * 根据产品代码查询关联代销机构设置是否有代销相关字样
     * @param prodIds 产品代码集合
     * @param dataMap 数据集
     * @return 数据集
     */
    public void setSaleString(List<String> prodIds, Map<String, Object> dataMap) {
        if (null == prodIds || prodIds.size() <= 0) {
            dataMap.put("saleString1", "");
            dataMap.put("saleString2", "");
            return;
        }
        //根据产品代码查询是否存在关联代销机构
        Map<String,Object> param = new HashMap<>();
        param.put("prodlist", prodIds);
        Integer count = reportDlxsjgmdggMapper.queryProdAgency(param);
        if (count != 0) {
            dataMap.put("saleString1", SALE_STRING_1);
            dataMap.put("saleString2", SALE_STRING_2);
        } else {
            dataMap.put("saleString1", "");
            dataMap.put("saleString2", "");
        }
    }
}
