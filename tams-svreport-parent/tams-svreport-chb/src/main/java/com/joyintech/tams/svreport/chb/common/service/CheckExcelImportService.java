package com.joyintech.tams.svreport.chb.common.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.common.dao.UserTabColumnsMapper;
import com.joyintech.tams.svreport.chb.common.dto.TabColumnsDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
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

    /**
     * 校验列
     */
    public void checkTabColumns(TabColumnsDto dto, String value, int i, List<ExcelValidDto> checkResult) {
        //如果字段允许为空，值为空直接返回
        if (Objects.equals("Y", dto.getNullAble()) && StringUtil.isBlank(value)) {
            return;
        }
        if (StringUtil.isBlank(value)) {
            checkResult.add(new ExcelValidDto("0未导入", String.valueOf(i + 2), "货币对为空"));
        }
        //校验字段长度
        if (dto.getLength() != 0 && dto.getLength() < value.length()) {
            checkResult.add(new ExcelValidDto("0未导入", String.valueOf(i + 2), "字段长度不能超过" + dto.getLength()));
        }
        //日期类型校验
        if (Objects.equals("DATE", dto.getDataType())) {
            if (!this.checkDate(value)) {
                checkResult.add(new ExcelValidDto("0未导入", String.valueOf(i + 2), "不是日期格式"));
            }
        }
        //数字类型校验
        if (Objects.equals("NUMBER", dto.getDataType())) {
            if (!this.checkFigure(value)) {
                checkResult.add(new ExcelValidDto("0未导入", String.valueOf(i + 2), "不是数字格式"));
            }
        }
    }


    /**
     * 日期格式校验
     *
     * @param
     * @return 返回boolean
     */
    private boolean checkDate(String date) {
        boolean flag = true;
        if (!DateUtil.isDateStr(date, DateUtil.DEFAULT_OTHER_FORMAT,
                DateUtil.DEFAULT_ANOTHER_FORMAT, DateUtil.DEFAULT_DATE_FORMAT_EN)) {
            flag = false;
        } else {
            date = date.replace("-", "").replace("/", "");
            //年
            int year = Integer.valueOf(date.substring(0, 4));
            //月
            int month = Integer.valueOf(date.substring(4, 6));
            //日
            int day = Integer.valueOf(date.substring(6));
            if (month < 1 || month > 12) {
                flag = false;
            } else {
                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    if (day < 1 || day > 31) {
                        flag = false;
                    }
                } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                    if (day < 1 || day > 30) {
                        flag = false;
                    }
                } else if (month == 2) {
                    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                        if (day < 1 || day > 29) {
                            flag = false;
                        }
                    } else {
                        if (day < 1 || day > 28) {
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 判断是否为数字
     *
     * @param
     * @return 返回boolean
     */
    private boolean checkFigure(String figure) {
        try {
            new BigDecimal(figure);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<ExcelValidDto> checkImportDB(List<Map<String, String>> dataList, String tableName) {

        List<ExcelValidDto> checkResultAll = new LinkedList<>();
        Map<String, TabColumnsDto> tabColumnsMap = this.getTabColumns(tableName);
        Iterator<Map<String, String>> iterator = dataList.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            List<ExcelValidDto> checkResult = new LinkedList<>();
            Map<String, String> map = iterator.next();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (tabColumnsMap.containsKey(entry.getKey())) {
                    this.checkTabColumns(tabColumnsMap.get(entry.getKey()), entry.getValue(), i, checkResult);
                }
            }
            //如果校验不通过remove元素
            if (CollectionUtils.isNotEmpty(checkResult)) {
                iterator.remove();
                checkResultAll.addAll(checkResult);
            }
            i++;
        }
        return checkResultAll;

    }

}
