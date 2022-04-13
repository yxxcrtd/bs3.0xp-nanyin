package com.joyintech.tams.common.svreport.base.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.base.dao.BaseCheckMysqlMapper;
import com.joyintech.tams.common.svreport.base.dto.BaseCheckMysqlDto;
import com.joyintech.tams.common.svreport.base.dto.BaseCommonCheckDto;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wubingbing
 * @date 2022-03-31 14:52
 */
@Component
public class BaseCheckMysqlService {
    private String rule = "^-?[1-9]\\d*$";
    private String floatRule = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
    @Autowired
    private BaseCheckMysqlMapper baseCheckMysqlMapper;

    public List<BaseCheckMysqlDto> getColumns(String table){
        return baseCheckMysqlMapper.select(table);
    }

    public List<BaseCommonCheckDto> check4Mysql(List<Map<String,String>> list, String tableName) {
        //给map添加编号
        for(int i=0;i<list.size();i++){
            list.get(i).put("index",i+2+"");
        }

        List<BaseCheckMysqlDto> columns = getColumns(tableName);
        List<BaseCommonCheckDto> result = new ArrayList<>();
        List<Map<String,String>> errList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            boolean errFlag = false;//检查结果 true为有错误
            Set<Map.Entry<String, String>> set = list.get(i).entrySet();
            for (Map.Entry<String, String> entry : set) {
                for (BaseCheckMysqlDto c : columns) {
                    if (entry.getKey().equals(c.getCOLUMN_NAME())) {
                        // 非空校验
                        if (c.getIS_NULLABLE().equals("NO")) {
                            if (!StringUtils.hasText(entry.getValue())) {
                                ExcelValidDto dto = new ExcelValidDto("0未导入", String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "为空");
                                BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                                commonCheckDto.setErrMap(list.get(i));
                                commonCheckDto.setExcelValidDto(dto);
                                result.add(commonCheckDto);
                                errList.add(list.get(i));
                                // 跳出 for (Column c : columns)循环
                                errFlag = true;
                                break;
                            }
                        }
                        //表字段是字符类型
                        if (c.getDATA_TYPE().equals("varchar")||
                            c.getDATA_TYPE().equals("tinyblob")||
                            c.getDATA_TYPE().equals("tinytext")||
                            c.getDATA_TYPE().equals("longblob")||
                            c.getDATA_TYPE().equals("longtext")) {
                            // 长度校验
                            if (entry.getValue().length() > Integer.valueOf(c.getCHARACTER_MAXIMUM_LENGTH())) {
                                ExcelValidDto dto = new ExcelValidDto("0未导入", String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "内容太长了");
                                BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                                commonCheckDto.setErrMap(list.get(i));
                                commonCheckDto.setExcelValidDto(dto);
                                result.add(commonCheckDto);
                                errList.add(list.get(i));
                                errFlag = true;
                            }
                            break;
                        } else if (c.getDATA_TYPE().equals("int")||c.getDATA_TYPE().equals("bigint")) {
                            // 长度校验
                            if (entry.getValue().length() > Integer.valueOf(c.getNUMERIC_PRECISION())) {
                                ExcelValidDto dto = new ExcelValidDto("0未导入", String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "内容太长了");
                                BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                                commonCheckDto.setErrMap(list.get(i));
                                commonCheckDto.setExcelValidDto(dto);
                                result.add(commonCheckDto);
                                errList.add(list.get(i));
                                errFlag = true;
                                break;
                            }else if(!Pattern.matches(rule,entry.getValue())){
                                //整数校验
                                ExcelValidDto dto = new ExcelValidDto("0未导入", String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "不是整数");
                                BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                                commonCheckDto.setErrMap(list.get(i));
                                commonCheckDto.setExcelValidDto(dto);
                                result.add(commonCheckDto);
                                errList.add(list.get(i));
                                errFlag = true;
                                break;
                            }
                            break;
                        }else if(c.getDATA_TYPE().equals("float")||c.getDATA_TYPE().equals("double")||c.getDATA_TYPE().equals("decimal")){
                            // 长度校验
                            if (entry.getValue().length() > Integer.valueOf(c.getNUMERIC_PRECISION())) {
                                ExcelValidDto dto = new ExcelValidDto("0未导入", String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "内容太长了");
                                BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                                commonCheckDto.setErrMap(list.get(i));
                                commonCheckDto.setExcelValidDto(dto);
                                result.add(commonCheckDto);
                                errList.add(list.get(i));
                                errFlag = true;
                                break;
                            }else if(!Pattern.matches(floatRule,entry.getValue())){
                                ExcelValidDto dto = new ExcelValidDto("0未导入", String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "内容太长了");
                                BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                                commonCheckDto.setErrMap(list.get(i));
                                commonCheckDto.setExcelValidDto(dto);
                                result.add(commonCheckDto);
                                errList.add(list.get(i));
                                errFlag = true;
                                break;
                            }
                            break;
                        }else if(c.getDATA_TYPE().equals("date")){
                            if(!DateUtil.isDateStr(entry.getValue())){
                                ExcelValidDto dto = new ExcelValidDto("0未导入", String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "日期格式不正确，请使用yyyy-MM-dd格式");
                                BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                                commonCheckDto.setErrMap(list.get(i));
                                commonCheckDto.setExcelValidDto(dto);
                                result.add(commonCheckDto);
                                errList.add(list.get(i));
                                errFlag = true;
                            }
                            break;
                        }else if(c.getDATA_TYPE().equals("datetime")||c.getDATA_TYPE().equals("timestamp")){
                            if(!DateUtil.isDateStr(entry.getValue(),DateUtil.DEFAULT_DATETIME_FORMAT)){
                                ExcelValidDto dto = new ExcelValidDto("0未导入", String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "日期格式不正确，请使用yyyy-MM-dd HH:mm:ss格式");
                                BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                                commonCheckDto.setErrMap(list.get(i));
                                commonCheckDto.setExcelValidDto(dto);
                                result.add(commonCheckDto);
                                errList.add(list.get(i));
                                errFlag = true;
                            }
                            break;
                        }

                        break;
                    }
                }
            }
            if(errFlag){
                list.get(i).put("STATUS","R");
            }
        }
        List<Map<String,String>> uniList = new ArrayList<>();
        List<Map<String,String>> priList = new ArrayList<>();
        if(list.size()>1){
            //唯一索引校验
            for(BaseCheckMysqlDto c:columns){
                if(c.getCOLUMN_KEY().equals("UNI")){
                    Map<String, List<Map<String, String>>> uniMap = list.stream().collect(Collectors.groupingBy(ele -> ele.get(c.getCOLUMN_KEY())));
                        uniMap.values().stream().filter(ele->ele.size()>1).forEach(ele->ele.forEach(e->{
                        ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY,e.get("index"), "表中数据重复");
                        BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                        commonCheckDto.setErrMap(e);
                        commonCheckDto.setExcelValidDto(dto);
                        result.add(commonCheckDto);
                        uniList.add(e);
                        }));

                }
            }


            //主键组合索引列
            List<String> primaries = new ArrayList<>();
            for(BaseCheckMysqlDto c:columns){
                if(c.getCOLUMN_KEY().equals("PRI")){
                    primaries.add(c.getCOLUMN_NAME());
                }
            }

            Map<String, List<Map<String, String>>> priMap = list.stream().collect(Collectors.groupingBy(ele -> {
                StringBuilder sb = new StringBuilder();
                for(String s:primaries){
                    sb.append(ele.get(s));
                }
                return sb.toString();
            }));

                priMap.values().stream().filter(ele->ele.size()>1).forEach(ele->ele.forEach(e->{
                ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY,e.get("index"), "表中数据重复");
                BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                commonCheckDto.setErrMap(e);
                commonCheckDto.setExcelValidDto(dto);
                result.add(commonCheckDto);
                priList.add(e);
            }));
        }
//        // 移除校验失败的行数据
//        list.removeAll(errList);
//        // 移除唯一索引重复的数据
//        list.removeAll(uniList);
//        // 移除组合主键索引重复的数据
//        list.removeAll(priList);
        return result;
    }


}
