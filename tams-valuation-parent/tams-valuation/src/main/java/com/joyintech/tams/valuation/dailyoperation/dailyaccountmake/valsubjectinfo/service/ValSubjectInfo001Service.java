package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valsubjectinfo.service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valsubjectinfo.dto.ValSubjectInfo001Dto;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.sztinterf.constant.DictCodeSztList;

@Service
public class ValSubjectInfo001Service extends CommonExportService<ValSubjectInfo001Dto> {

    /**
     * 科目信息表数据表格加载 <功能详细描述>
     * 
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<ValSubjectInfo001Dto> findPage(Map<String, Object> params)
        throws Exception {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValSubjectInfo001/findPage", params,
            ValSubjectInfo001Dto.class);
    }

    /**
     * 获取接口参数 <功能详细描述>
     * 
     * @param datas
     * @return Map<String, String>
     * @see
     * @since 1.0
     */
    public Map<String, String> getParm(String prodId, String cdate, String interType,
                                       String isSendDetailSubjectNo) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(DictCodeSztList.深圳通页面调用接口参数字段_产品代码.getValue(), prodId);
        urlParams.put(DictCodeSztList.深圳通页面调用接口参数字段_发送时间.getValue(), cdate);
        urlParams.put(DictCodeSztList.深圳通页面调用接口参数字段_接口类型.getValue(), interType);
        urlParams.put(DictCodeSztList.深圳通页面调用接口参数字段_是否发送四级科目号.getValue(), isSendDetailSubjectNo);
        return urlParams;
    }

    /**
     * 页面产品代码下拉框查询方法 <功能详细描述>
     * 
     * @param map
     * @return Page<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId(Map<String, Object> map) {
        map.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValSubjectInfo001/getFinprodId", map,
            SelectOptionDto.class);
    }
    
    /**
     * 报文输出格式转换<功能详细描述>
     * @param String
     * @return String
     * @see
     * @since 1.0
     */
    public  String formatXml(String str)
        throws Exception {
        Document document = null;
        document = DocumentHelper.parseText(str);
        // 格式化输出格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        StringWriter writer = new StringWriter();
        // 格式化输出流
        XMLWriter xmlWriter = new XMLWriter(writer, format);
        // 将document写入到输出流
        xmlWriter.write(document);
        xmlWriter.close();

        return writer.toString();
    }

}
