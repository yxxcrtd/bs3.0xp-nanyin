package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.dto.ValElectReconciliation001Dto;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.sztinterf.constant.DictCodeSztList;

@Service
public class ValElectReconciliation001Service extends CommonExportService<ValElectReconciliation001Dto> {

    /**
     * 电子对账方法 <功能详细描述>
     * 
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<ValElectReconciliation001Dto> findPage(Map<String, Object> params)
        throws Exception {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValElectReconciliation001/findPage",
            params, ValElectReconciliation001Dto.class);
    }

    /**
     * 获取接口参数 <功能详细描述>
     * 
     * @param datas
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, String> getParm(String prodId, String cdate, String interType) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(DictCodeSztList.深圳通页面调用接口参数字段_产品代码.getValue(), prodId);
        urlParams.put(DictCodeSztList.深圳通页面调用接口参数字段_发送时间.getValue(), cdate);
        urlParams.put(DictCodeSztList.深圳通页面调用接口参数字段_接口类型.getValue(), interType);
        return urlParams;
    }

    /**
     * 页面产品代码下拉框查询方法 <功能详细描述>
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId(Map<String, Object> map) {
        map.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValElectReconciliation001/getFinprodId",
            map, SelectOptionDto.class);
    }

    /**
     * 调整 <功能详细描述>
     * 
     * @param List<ValElectReconciliation001Dto>
     * @return
     * @see
     * @since 1.0
     */
    public String adjustValue(List<ValElectReconciliation001Dto> listdto) {
        return restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValElectReconciliation001/adjustValue",
            listdto, String.class);
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
