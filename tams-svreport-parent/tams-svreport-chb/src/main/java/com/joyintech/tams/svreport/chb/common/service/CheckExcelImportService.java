package com.joyintech.tams.svreport.chb.common.service;

import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.common.dao.UserTabColumnsMapper;
import com.joyintech.tams.svreport.chb.common.dto.TabColumnsDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Excel导入功能校验
 *
 * @author zhoubin
 */
@Service
public class CheckExcelImportService {

    @Resource
    private UserTabColumnsMapper userTabColumnsMapper;

    /**
     * 获取表中每个字段的相应属性
     *
     * @return
     */
    public Map<String, TabColumnsDto> getTabColumns(String tableName) {
        List<TabColumnsDto> tabColumnsList = userTabColumnsMapper.selectTabColumns(tableName);
        Map<String, TabColumnsDto> map = tabColumnsList.stream().collect(Collectors.toMap(TabColumnsDto::getColumnName, Function.identity()));
        return map;
    }

    /**
     * 拼接错误信息
     *
     * @param checkResult
     * @return
     */
    public String getCheckErrMsg(List<ExcelValidDto> checkResult) {
        StringBuffer errMsg = new StringBuffer();
        checkResult.forEach(e -> {
            errMsg.append("第")
                    .append(e.getRowNum())
                    .append("行").append(e.getErrorInfo())
                    .append(";");
        });

        return errMsg.toString();
    }

    /**
     * 将多行错误凭借成一个行
     *
     * @param list
     * @return
     */
    public List<ExcelValidDto> getExcelValidList(List<ExcelValidDto> list) {

        List<ExcelValidDto> checkResult = new LinkedList<>();
        Map<String, List<ExcelValidDto>> map = list.stream().collect(Collectors.groupingBy(ExcelValidDto::getRowNum));
        map.forEach((rowNum, checkList) -> {
            ExcelValidDto dto = new ExcelValidDto();
            dto.setRowNum(rowNum);
            dto.setCheckResult("检查不通过");
            dto.setValidKey(checkList.stream().map(ExcelValidDto::getValidKey).collect(Collectors.joining(";")));
            dto.setErrorInfo(checkList.stream().map(ExcelValidDto::getErrorInfo).collect(Collectors.joining(";")));
            checkResult.add(dto);
        });

        return checkResult;
    }
}
