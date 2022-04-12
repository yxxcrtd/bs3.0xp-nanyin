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
        List<BaseCheckMysqlDto> columns = getColumns(tableName);
        List<BaseCommonCheckDto> result = new ArrayList<>();
        List<Map<String,String>> errList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Set<Map.Entry<String, String>> set = list.get(i).entrySet();
            for (Map.Entry<String, String> entry : set) {
                for (BaseCheckMysqlDto c : columns) {
                    if (entry.getKey().equals(c.getCOLUMN_NAME())) {
                        // 非空校验
                        if (c.getIS_NULLABLE().equals("YES")) {
                            if (!StringUtils.hasText(entry.getValue())) {
                                ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "为空");
                                BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                                list.get(i).put("STATUS","R");
                                commonCheckDto.setErrMap(list.get(i));
                                commonCheckDto.setExcelValidDto(dto);
                                result.add(commonCheckDto);
                                errList.add(list.get(i));
                                // 跳出 for (Column c : columns)循环
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
                                ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "内容太长了");
                                BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                                list.get(i).put("STATUS","R");
                                commonCheckDto.setErrMap(list.get(i));
                                commonCheckDto.setExcelValidDto(dto);
                                result.add(commonCheckDto);
                                errList.add(list.get(i));
                            }
                            break;
                        } else if (c.getDATA_TYPE().equals("int")||c.getDATA_TYPE().equals("bigint")) {
                            // 长度校验
                            if (entry.getValue().length() > Integer.valueOf(c.getNUMERIC_PRECISION())) {
                                ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "内容太长了");
                                BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                                list.get(i).put("STATUS","R");
                                commonCheckDto.setErrMap(list.get(i));
                                commonCheckDto.setExcelValidDto(dto);
                                result.add(commonCheckDto);
                                errList.add(list.get(i));
                                break;
                            }else if(!Pattern.matches(rule,entry.getValue())){
                                //整数校验
                                ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "不是整数");
                                BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                                list.get(i).put("STATUS","R");
                                commonCheckDto.setErrMap(list.get(i));
                                commonCheckDto.setExcelValidDto(dto);
                                result.add(commonCheckDto);
                                errList.add(list.get(i));
                                break;
                            }
                            break;
                        }else if(c.getDATA_TYPE().equals("float")||c.getDATA_TYPE().equals("double")||c.getDATA_TYPE().equals("decimal")){
                            // 长度校验
                            if (entry.getValue().length() > Integer.valueOf(c.getNUMERIC_PRECISION())) {
                                ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "内容太长了");
                                BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                                list.get(i).put("STATUS","R");
                                commonCheckDto.setErrMap(list.get(i));
                                commonCheckDto.setExcelValidDto(dto);
                                result.add(commonCheckDto);
                                errList.add(list.get(i));
                                break;
                            }else if(!Pattern.matches(floatRule,entry.getValue())){
                                ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "内容太长了");
                                BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                                list.get(i).put("STATUS","R");
                                commonCheckDto.setErrMap(list.get(i));
                                commonCheckDto.setExcelValidDto(dto);
                                result.add(commonCheckDto);
                                errList.add(list.get(i));
                                break;
                            }
                            break;
                        }else if(c.getDATA_TYPE().equals("date")){
                            if(!DateUtil.isDateStr(entry.getValue())){
                                ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "日期格式不正确，请使用yyyy-MM-dd格式");
                                BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                                list.get(i).put("STATUS","R");
                                commonCheckDto.setErrMap(list.get(i));
                                commonCheckDto.setExcelValidDto(dto);
                                result.add(commonCheckDto);
                                errList.add(list.get(i));
                            }
                            break;
                        }else if(c.getDATA_TYPE().equals("datetime")||c.getDATA_TYPE().equals("timestamp")){
                            if(!DateUtil.isDateStr(entry.getValue(),DateUtil.DEFAULT_DATETIME_FORMAT)){
                                ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "日期格式不正确，请使用yyyy-MM-dd HH:mm:ss格式");
                                BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                                list.get(i).put("STATUS","R");
                                commonCheckDto.setErrMap(list.get(i));
                                commonCheckDto.setExcelValidDto(dto);
                                result.add(commonCheckDto);
                                errList.add(list.get(i));
                            }
                            break;
                        }

                        break;
                    }
                }
            }
        }
        List<Map<String,String>> uniList = new ArrayList<>();
        List<Map<String,String>> priList = new ArrayList<>();
        if(list.size()>1){
            Map<String,String> map = null;
            //唯一索引校验
            for(BaseCheckMysqlDto c:columns){
                if(c.getCOLUMN_KEY().equals("UNI")){
                    for(int i=0;i<list.size();i++){
                        map = list.get(i);
                        for(int j=i+1;j<list.size();j++){
                            if(list.get(j).get(c.getCOLUMN_NAME()).equals(map.get(c.getCOLUMN_NAME()))){
                                ExcelValidDto dto = new ExcelValidDto(list.get(j).get(1),String.valueOf(j + 2), "表中数据重复");
                                BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                                map.put("STATUS","R");
                                commonCheckDto.setErrMap(map);
                                commonCheckDto.setExcelValidDto(dto);
                                result.add(commonCheckDto);
                                uniList.add(list.get(j));
                            }
                        }
                    }
                }
            }


            //主键组合索引列
            List<String> primaries = new ArrayList<>();
            for(BaseCheckMysqlDto c:columns){
                if(c.getCOLUMN_KEY().equals("PRI")){
                    primaries.add(c.getCOLUMN_NAME());
                }
            }
            Map<String,String> var1 = null;
            Map<String,String> var2 = null;
            if(primaries.size()>0){
                for(int i=0;i<list.size();i++){
                    var1 = list.get(i);
                    for(int j=i+1;j<list.size();j++){
                        var2 = list.get(j);
                        int num=0;
                        for(String s: primaries){
                            if(!var1.get(s).equals(var2.get(s))){
                                break;
                            }
                            num ++;
                        }
                        if(num == primaries.size()){
                            ExcelValidDto dto = new ExcelValidDto(list.get(j).get(1),String.valueOf(j + 2), "表中数据重复");
                            BaseCommonCheckDto commonCheckDto = new BaseCommonCheckDto();
                            list.get(j).put("STATUS","R");
                            commonCheckDto.setErrMap(list.get(j));
                            commonCheckDto.setExcelValidDto(dto);
                            result.add(commonCheckDto);
                            priList.add(list.get(j));
                        }
                    }
                }
            }
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
