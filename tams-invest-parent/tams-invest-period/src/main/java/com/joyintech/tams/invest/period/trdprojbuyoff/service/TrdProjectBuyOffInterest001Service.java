package com.joyintech.tams.invest.period.trdprojbuyoff.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.period.trdprojbuyoff.dto.TrdProjectBuyOffInterest001Dto;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.service
 * @Description: 债券存续期管理-查询
 * @date Date : 2018年09月21日 14:32
 */
@Service
public class TrdProjectBuyOffInterest001Service extends CommonExportService<TrdProjectBuyOffInterest001Dto> {

    /**
     * 查询
     * 
     * @param selectParam 参数
     * @return
     */
    public Page<TrdProjectBuyOffInterest001Dto> findPage(Map<String, Object> selectParam) {
        if (selectParam.get("assetType") != null) {
            selectParam.put("assetType", StringUtil.splitToList(",", (String)selectParam.get("assetType")));
        }
        if (selectParam.get("assetId") != null) {
            selectParam.put("assetId", StringUtil.splitToList(",", (String)selectParam.get("assetId")));
        }
        return restTemplate.postForPage(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectBuyOffInterest/findPage",
            selectParam, TrdProjectBuyOffInterest001Dto.class);
    }

    /**
     * 分页查询项目类资产代码
     * 
     * @return 候选dto
     */
    public Page<SelectOptionDto> getProjAssetId() {
        return restTemplate.postForPage(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectBuyOffInterest/getProjAssetId",
            RequestUtil.getPageParam(), SelectOptionDto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/period/trdprojbuyoff/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdProjectBuyOffInterest001Dto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }

    public void save(TrdProjectBuyOffInterest001Dto dto) {
        restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectBuyOffInterest/save", dto, String.class);
    }

    public int selectCountForMatch(TrdProjectBuyOffInterest001Dto dto) {
        return restTemplate.postForObject(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectBuyOffInterest/selectCountForMatch", dto, Integer.class);
    }

    /**
     * excel导入功能
     *
     * @param flieName 文件名称
     * @return 检验list
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    public List<ExcelValidDto> importData(String flieName)
        throws Exception {
        // 检查文件名是否为空
        if (StringUtil.isNotEmpty(flieName)) {
            // 检查文件名是否包含项目类资产买断利率
            if (flieName.indexOf("项目类资产买断利率") != -1) {
                ExcelUtil excelUtil = ExcelUtil.build(FileNameUtil.getUploadFilePath(flieName), null);
                List<List<String>> dataList = excelUtil.readToList(0, 1, 0);
                // 检查读出信息是否为空
                if (null != dataList) {
                    // 将excel表格中的数据转化为dto
                    List<ExcelValidDto> checkResult = checkExcelData(dataList);
                    if (checkResult.isEmpty()) {
                        List<TrdProjectBuyOffInterest001Dto> equityList = converToBean(dataList);
                        dealAfterCheck(equityList);
                    }
                    return checkResult;
                }
            }
            else {
                List<ExcelValidDto> checkResult = new ArrayList<>();
                checkResult.add(
                    new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY, getMessage("BUSS_COM_E_0002", "项目类资产买断利率")));
                return checkResult;
            }
        }
        return null;
    }

    /**
     * check后的保存 <功能详细描述>
     *
     * @param equityList 需要保存的数据
     * @see
     * @since 1.0
     */
    public void dealAfterCheck(List<TrdProjectBuyOffInterest001Dto> equityList) {
        if (equityList != null && equityList.size() > 0) {
            this.saveList(equityList);
        }
    }

    private String saveList(List<TrdProjectBuyOffInterest001Dto> listN) {
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectBuyOffInterest/saveList", listN,
            String.class);
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
    private List<TrdProjectBuyOffInterest001Dto> converToBean(List<List<String>> dataList) {
        // 返回的list定义
        List<TrdProjectBuyOffInterest001Dto> returnInfoList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list = dataList.get(i);
            TrdProjectBuyOffInterest001Dto dto = new TrdProjectBuyOffInterest001Dto();
            dto.setAssetId(list.get(1));
            try {
                dto.setRate(new BigDecimal(list.get(3)));
            }
            catch (Exception e) {
                dto.setRate(null);
            }
            dto.setEffDate(list.get(4));
            returnInfoList.add(dto);
        }
        return returnInfoList;
    }

    /**
     * 导入文件内容检查 <功能详细描述>
     *
     * @param dataList 导入数据
     * @return 返回检查结果
     * @throws ParseException 抛出异常
     * @see
     * @since 1.0
     */
    private List<ExcelValidDto> checkExcelData(List<List<String>> dataList) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        List<TrdProjectBuyOffInterest001Dto> equityList = converToBean(dataList);
        TrdProjectBuyOffInterest001Dto dto;
        // 检查表中是否有数据
        if (equityList.size() >= 1) {
            // 判断项目类资产代码是否存在
            String notExistsAssetId = checkIdExists(equityList);
            if (StringUtil.isNotEmpty(notExistsAssetId)) {
                checkResult.add(
                    new ExcelValidDto(notExistsAssetId, StringUtil.EMPTY, getMessage("TrdProjectBuyOffInterest001.notExists")));
            }
            for (int i = 0; i < equityList.size(); i++ ) {
                dto = equityList.get(i);
                // 检查表内数据每行是否合法
                List<ExcelValidDto> checkResult1 = checkExcelCow(i, dataList.get(i));
                if (checkResult1 != null) {
                    checkResult.addAll(checkResult1);
                }
                // 检查表内数据是否有主键重复
                if (equityList.size() >= 2) {
                    for (int k = i + 1; k < equityList.size(); k++ ) {
                        if (equityList.get(k).getAssetId().equals(dto.getAssetId())
                            && equityList.get(k).getEffDate().equals(dto.getEffDate())) {
                            checkResult.add(new ExcelValidDto(dataList.get(i).get(1), String.valueOf(i + 2),
                                getMessage("MstMarketCalendarInfo001.repeat")));
                        }
                    }
                }
            }
        }
        return checkResult;
    }

    private String checkIdExists(List<TrdProjectBuyOffInterest001Dto> equityList) {
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectBuyOffInterest/checkIdExists",
            equityList, String.class);
    }

    /**
     * 检查excel是否为空或者传入列是否正确
     *
     * @param i 第几行
     * @param list 每一列解析后的List<String>
     * @return 检查结构
     * @see
     * @since 1.0
     */
    private List<ExcelValidDto> checkExcelCow(int i, List<String> list) {
        List<ExcelValidDto> excelValidDtolist = new ArrayList<>();
        // 资产代码、买断利率、生效日不能为空
        if (StringUtil.isEmpty(list.get(1))) {
            excelValidDtolist.add(new ExcelValidDto(list.get(1), String.valueOf(i),
                this.getMessage("TrdProjectBuyOffInterest001.error.notEmpty", "资产代码")));
        }
        if (StringUtil.isEmpty(list.get(3))) {
            excelValidDtolist.add(new ExcelValidDto(list.get(3), String.valueOf(i),
                this.getMessage("TrdProjectBuyOffInterest001.error.notEmpty", "买断利率")));
        }
        if (StringUtil.isEmpty(list.get(4))) {
            excelValidDtolist.add(new ExcelValidDto(list.get(4), String.valueOf(i),
                this.getMessage("TrdProjectBuyOffInterest001.error.notEmpty", "生效日")));
        }
        // 使用日期格式化捕获异常来判断日期格式是否合法
        try {
            Date date = DateUtil.parse(list.get(4));
            // // 获取平台日期
            // Date sysDate = DateUtil.parse(
            // (String)SecurityUtil.getFromSession(GlobalConstant.SESSION_SYS_DATE));
            // if (date.before(sysDate)) {
            // // 验证导入数据日期是否小于平台日期
            // ExcelValidDto dto = new ExcelValidDto(list.get(0), String.valueOf(i),
            // this.getMessage("BUSS_COM_E_0003", "平台日期", "导入数据的日期"));
            // excelValidDtolist.add(dto);
            // }
        }
        catch (Exception e) {
            ExcelValidDto dto = new ExcelValidDto(list.get(4), String.valueOf(i),
                this.getMessage("TrdProjectBuyOffInterest001.error.notDate"));
            excelValidDtolist.add(dto);
        }
        return excelValidDtolist;
    }

    public void deleteData(List<TrdProjectBuyOffInterest001Dto> selectedData) {
        restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectBuyOffInterest/deleteData", selectedData,
            String.class);
    }

    public TrdProjectBuyOffInterest001Dto findBuyOffInfo(String finprodId, String effDate) {
        Map<String, String> params = new HashMap<>();
        params.put("finprodId", finprodId);
        params.put("effDate", effDate);
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectBuyOffInterest/findBuyOffInfo",
                params, TrdProjectBuyOffInterest001Dto.class);
    }
}
