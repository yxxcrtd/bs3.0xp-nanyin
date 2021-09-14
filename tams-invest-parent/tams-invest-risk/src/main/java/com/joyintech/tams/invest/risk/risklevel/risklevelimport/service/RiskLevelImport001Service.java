package com.joyintech.tams.invest.risk.risklevel.risklevelimport.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.web.PlatformDate;
import com.joyintech.tams.invest.risk.risklevel.risklevelimport.dto.RiskLevelImportDto;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.ExcelUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;

@Service
public class RiskLevelImport001Service extends CommonExportService<RiskLevelImportDto> {

    @Autowired
    private IPlatformDate iPlatformDate;

    /**
     * 查询界面询事件
     *
     * @param map
     *            页面参数
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public Page<RiskLevelImportDto> selectForPage(Map<String, Object> map) {
        Page<RiskLevelImportDto> page = restTemplate.postForPage(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/RiskLevelImport/selectForPage", map,
                RiskLevelImportDto.class);
        return page;
    }

   

    /**
     * excel导入功能
     *
     * @param flieName
     *            文件名称
     * @return 检验list
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    public List<ExcelValidDto> importData(String flieName) throws Exception {
        // 检查文件名是否为空
        if (StringUtil.isNotEmpty(flieName)) {
            
             if (flieName.indexOf("风险五级分类") != -1) {
                ExcelUtil excelUtil = ExcelUtil.build(FileNameUtil.getUploadFilePath(flieName), null);
                List<List<String>> dataList = excelUtil.readToList(0, 1, 0);
                // 检查读出信息是否为空
                if (null != dataList && dataList.size() > 0) {

                    // 将excel表格中的数据转化为dto
                    List<ExcelValidDto> checkResult = new ArrayList<>();
                    List<RiskLevelImportDto> equityList = new ArrayList<>();
                    if (checkResult.isEmpty()) {
                          equityList = converToBean(dataList);
                     }
                    //数据入库
                    Map<String, Object> map = new HashMap<>();
                    map.put("insertList",equityList);
                    restTemplate.postForObject(
                    ServiceApplication.RISK_SERVICE_NAME + "/RiskLevelImport/save", equityList,
                    RiskLevelImportDto.class);
                    
                    return checkResult;
                } else {
                    List<ExcelValidDto> checkResult = new ArrayList<>();
                    checkResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,getMessage("MST_ASSET_DECLARATION_CODE_E_0015")));
                    return checkResult;
                }
            } else {
                List<ExcelValidDto> checkResult = new ArrayList<>();
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,getMessage("BUSS_COM_E_0002", "风险五级分类导入")));
                return checkResult;
            }
        }
        return null;
    }


    /**
     * list转换为Bean
     *
     * @param dataList excel解析后的list
     * @return List<TrdProjectBuyOffInterest001Dto>
     * @throws ParseException 抛出异常
     * @see
     * @since 1.0
     */
    private List<RiskLevelImportDto> converToBean(List<List<String>> dataList) {
        // 返回的list定义
        List<RiskLevelImportDto> returnInfoList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list = dataList.get(i);
            RiskLevelImportDto dto = new RiskLevelImportDto();
            dto.setAssetsType(list.get(0).split(" ")[0]);
            dto.setBondRiskType(list.get(2).split(" ")[0]);
            dto.setFinprodName(list.get(1));
            dto.setUpdateTime(iPlatformDate.getSysDate());
            Map<String,Object> map = new HashMap<>();
            map.put("finprodName",dto.getFinprodName());
            map.put("assetsType",dto.getAssetsType());
            //查询资产代码
            String str = "";
            str = restTemplate.postForObject(
                    ServiceApplication.RISK_SERVICE_NAME + "/RiskLevelImport/queryFinprodMarketId", map,
                    String.class);
            dto.setFinprodMarketId(new BigDecimal(i).toString());
            //dto.setFinprodMarketId();
            returnInfoList.add(dto);
        }
        return returnInfoList;
    }
 
    /**
     * 文件导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/risk/risklevel/risklevelimport/conf/export_conf.xml")
                .dataSource(DataSourceType.MASERT);
    }


    @Override
    public Page<RiskLevelImportDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    public void delete(RiskLevelImportDto dto) {
        restTemplate.postForObject(
                ServiceApplication.RISK_SERVICE_NAME + "/RiskLevelImport/delete", dto.getSelectedItem(),
                String.class);
     }
}
