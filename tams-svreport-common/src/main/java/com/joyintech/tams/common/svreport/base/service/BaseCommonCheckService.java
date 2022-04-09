/**
 * 
 */
package com.joyintech.tams.common.svreport.base.service;

import com.joyintech.framework.jdbc.datasource.DatabaseInfo;
import com.joyintech.framework.jdbc.datasource.DatabaseType;
import com.joyintech.framework.jdbc.datasource.DatabaseTypeHelper;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.common.base.dao.BaseMapper;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dto.ChbCheckTableInfo;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.util.SvrMaptoEntityUtil;
import com.joyintech.tams.common.svreport.datacheck.dto.CheckRule;
import com.joyintech.tams.common.svreport.datacheck.service.DataCheckService;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BaseCommonCheckDto;
import com.joyintech.tams.common.base.entity.ColumnDto;
import com.joyintech.tams.common.svreport.base.dao.BaseCommonCheckMapper;
import com.joyintech.tams.common.svreport.datacheck.dto.ChbInterfErrodataWithBLOBs;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;



@Service
public class BaseCommonCheckService {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    @Autowired
    private BaseCommonCheckMapper baseCommonCheckMapper;

    @Autowired
    private SqlSessionTemplate template;

    /**
     * 中债基础数据mapper
     */
    @Autowired
    private BaseMapper baseMapper;

    private String rule = "^-?[1-9]\\d*$";
    private String floatRule = "^(\\-|\\+)?\\d+(\\.\\d+)?$";

    public List<ColumnDto> getColumns(String table){
        return baseCommonCheckMapper.selectBySql(table);
    }

    /**
     * sql check
     * @param list 被check数据
     * @param tableName 表名
     * @return
     * @author wubingbing
     * @since 2022-04-08
     */
    public List<BaseCommonCheckDto> check4Mysql(List<Map<String,String>> list, String tableName) {
        List<ColumnDto> columns = getColumns(tableName);
        List<BaseCommonCheckDto> result = new ArrayList<>();
        BaseCommonCheckDto resultDto = new BaseCommonCheckDto();
        List<Map<String,String>> errList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Set<Map.Entry<String, String>> set = list.get(i).entrySet();
            for (Map.Entry<String, String> entry : set) {
                for (ColumnDto c : columns) {
                    if (entry.getKey().equals(c.getCOLUMN_NAME())) {
                        // 非空校验
                        if (c.getIS_NULLABLE().equals("YES")) {
                            if (!StringUtils.hasText(entry.getValue())) {
                                ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "为空");
                                resultDto.setExcelValidDto(dto);
                                resultDto.setErrMap(list.get(i));
                                result.add(resultDto);
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
                                resultDto.setExcelValidDto(dto);
                                resultDto.setErrMap(list.get(i));
                                result.add(resultDto);
                                errList.add(list.get(i));
                            }
                            break;
                        } else if (c.getDATA_TYPE().equals("int")||c.getDATA_TYPE().equals("bigint")) {
                            // 长度校验
                            if (entry.getValue().length() > Integer.valueOf(c.getNUMERIC_PRECISION())) {
                                ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "内容太长了");
                                resultDto.setExcelValidDto(dto);
                                resultDto.setErrMap(list.get(i));
                                result.add(resultDto);
                                errList.add(list.get(i));
                                break;
                            }else if(!Pattern.matches(rule,entry.getValue())){
                                //整数校验
                                ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "不是整数");
                                resultDto.setExcelValidDto(dto);
                                resultDto.setErrMap(list.get(i));
                                result.add(resultDto);
                                errList.add(list.get(i));
                                break;
                            }
                            break;
                        }else if(c.getDATA_TYPE().equals("float")||c.getDATA_TYPE().equals("double")||c.getDATA_TYPE().equals("decimal")){
                            // 长度校验
                            if (entry.getValue().length() > Integer.valueOf(c.getNUMERIC_PRECISION())) {
                                ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "内容太长了");
                                resultDto.setExcelValidDto(dto);
                                resultDto.setErrMap(list.get(i));
                                result.add(resultDto);
                                errList.add(list.get(i));
                                break;
                            }else if(!Pattern.matches(floatRule,entry.getValue())){
                                ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "内容太长了");
                                resultDto.setExcelValidDto(dto);
                                resultDto.setErrMap(list.get(i));
                                result.add(resultDto);
                                errList.add(list.get(i));
                                break;
                            }
                            break;
                        }else if(c.getDATA_TYPE().equals("date")){
                            if(!DateUtil.isDateStr(entry.getValue())){
                                ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "日期格式不正确，请使用yyyy-MM-dd格式");
                                resultDto.setExcelValidDto(dto);
                                resultDto.setErrMap(list.get(i));
                                result.add(resultDto);
                                errList.add(list.get(i));
                            }
                            break;
                        }else if(c.getDATA_TYPE().equals("datetime")||c.getDATA_TYPE().equals("timestamp")){
                            if(!DateUtil.isDateStr(entry.getValue(),DateUtil.DEFAULT_DATETIME_FORMAT)){
                                ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2), c.getCOLUMN_COMMENT() + "日期格式不正确，请使用yyyy-MM-dd HH:mm:ss格式");
                                resultDto.setExcelValidDto(dto);
                                resultDto.setErrMap(list.get(i));
                                result.add(resultDto);
                                errList.add(list.get(i));
                            }
                            break;
                        }
                        break;
                    }
                }
            }
        }
        // 移除校验失败的行数据
        list.removeAll(errList);
        return result;
    }


//
//    /**
//     * orcle check
//     * @param dataList 被check数据
//     * @param tableName 表名
//     * @return
//     * @author zhoubin
//     * @since 2022-04-08
//     */
//    public List<com.joyintech.tams.report.common.dto.ExcelValidDto> check4Oracle(List<Map<String, String>> dataList, String tableName) {
//        List<com.joyintech.tams.report.common.dto.ExcelValidDto> checkResult = new LinkedList<>();
//        Map<String, TabColumnsDto> tabColumnsMap = this.getTabColumns(tableName);
//        for (int i = 0; i < dataList.size(); i++) {
//            Map<String, String> map = dataList.get(i);
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                if (tabColumnsMap.containsKey(entry.getKey())) {
//                    this.checkTabColumns(tabColumnsMap.get(entry.getKey()), entry.getValue(), i, checkResult);
//                }
//            }
//
//        }
//        return checkResult;
//    }
//
//
//    /**
//     * 获取表中每个字段的相应属性
//     *
//     * @return
//     */
//    public Map<String, TabColumnsDto> getTabColumns(String tableName) {
//        List<TabColumnsDto> tabColumnsList = baseCommonCheckMapper.selectTabColumns(tableName);
//        Map<String, TabColumnsDto> map = tabColumnsList.stream().collect(Collectors.toMap(TabColumnsDto::getColumnName, Function.identity()));
//        return map;
//    }
//
//    /**
//     * 校验列
//     */
//    public void checkTabColumns(TabColumnsDto dto, String value, int i, List<com.joyintech.tams.report.common.dto.ExcelValidDto> checkResult) {
//        //如果字段允许为空，值为空直接返回
//        if (Objects.equals("Y", dto.getNullAble()) && StringUtil.isBlank(value)) {
//            return;
//        }
//        if (StringUtil.isBlank(value)) {
//            checkResult.add(new com.joyintech.tams.report.common.dto.ExcelValidDto("0未导入", String.valueOf(i + 2), "货币对为空"));
//        }
//        //校验字段长度
//        if (dto.getLength() != 0 && dto.getLength() < value.length()) {
//            checkResult.add(new com.joyintech.tams.report.common.dto.ExcelValidDto("0未导入", String.valueOf(i + 2), "字段长度不能超过" + dto.getLength()));
//        }
//        //日期类型校验
//        if (Objects.equals("DATE", dto.getDataType())) {
//            if (!this.checkDate(value)) {
//                checkResult.add(new com.joyintech.tams.report.common.dto.ExcelValidDto("0未导入", String.valueOf(i + 2), "不是日期格式"));
//            }
//        }
//        //数字类型校验
//        if (Objects.equals("NUMBER", dto.getDataType())) {
//            if (!this.checkFigure(value)) {
//                checkResult.add(new com.joyintech.tams.report.common.dto.ExcelValidDto("0未导入", String.valueOf(i + 2), "不是数字格式"));
//            }
//        }
//    }

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


    /**
     *基础数据库check
     * @param checkFlag 是接口check还是导入check  接口是interf  导入是import
     * @param datas 被check数据
     * @param TableName  表名
     * @param FileName  文件名
     * @param interfType 接口类型
     * @param dataType
     * @param bussinessKey 业务主键
     * @param checkEuplicatedKeys 联合主键
     * @return
     */
    public  List<ExcelValidDto> checkDataDB(String checkFlag,List<Map<String,String>> datas, String TableName, String FileName,String interfType,String dataType,String bussinessKey,String... checkEuplicatedKeys) {
        // 判断传入的数据是否为空
        if(CollectionUtils.isEmpty(datas)) {
            return null;
        }

        //使用sql或者orcle?
        List<BaseCommonCheckDto> erroList = new ArrayList<>();
        //返回的数据结构
        List<ExcelValidDto> erroListValid = new ArrayList<>();
        if(isMYSQLDataBase()) {
            erroList = this.check4Mysql(datas, TableName);
        }else{
//            erroList = this.check4Oracle(datas, TableName);
        }

        //如果检查数据中存在错误数据就进行下一步处理 若无就返回空的检查结果
        if(erroList != null && erroList.size() != 0) {
            for (BaseCommonCheckDto dto : erroList) {
                erroListValid.add(dto.getExcelValidDto());
            }

            //当是接口接入时才入CHB_INTERF_ERRODATA表
            if (erroList != null && erroList.size() != 0 && StringUtil.equals("interf", checkFlag)) {
                //数据库check不通过的插入CHB_INTERF_ERRODATA
                dealErrBeforInsert(erroList, FileName, interfType, bussinessKey, dataType, checkEuplicatedKeys);
            }
            return erroListValid;
        }

        return erroListValid;
    }


    /**
     * 数据校验入口  业务
     * @param checkFlag 是接口check还是导入check  接口是interf  导入是import
     * @param datas 被check数据
     * @param TableName  表名
     * @param FileName  文件名
     * @param interfType 接口类型
     * @param dataType
     * @param bussinessKey 业务主键
     * @param checkEuplicatedKeys 联合主键
     * @return 校验后的数据
     */
    public <T> List<BaseCommonCheckDto> checkDataYW(String checkFlag,List<T> datas, String TableName, String FileName,String interfType,String dataType,String bussinessKey,String... checkEuplicatedKeys) {
        // 判断传入的数据是否为空
        if(CollectionUtils.isEmpty(datas)) {
            return null;
        }
        //查询数据库表字段
        List<String> listCloumn = baseMapper.getTableColumnList(TableName);
        //将list<接口表DTO>转换成List<Map<Key, Object>>格式
        List<Map<String, String>> listMap = SvrMaptoEntityUtil.convertEntityToMap(datas, listCloumn);


        return null;
    }


    /**
     * 入CHB_INTERF_ERRODATA表前的处理
     * @param list
     * @param FileName
     * @param interfType
     * @param dataType
     * @param bussinessKey
     * @param checkEuplicatedKeys
     */
    public void dealErrBeforInsert(List<BaseCommonCheckDto> list,String FileName,String interfType,String dataType,String bussinessKey,String... checkEuplicatedKeys){
        List<Map<String,String>> erroDatas = new ArrayList<>();
        ChbInterfErrodataWithBLOBs data=null;
        List<ChbInterfErrodataWithBLOBs> erroList=new ArrayList<ChbInterfErrodataWithBLOBs>();
        for (BaseCommonCheckDto dto:list){
            dto.getErrMap().put("ERRMSG",dto.getExcelValidDto().getErrorInfo());
            erroDatas.add(dto.getErrMap());
        }
        for(Map<String, String> erro:erroDatas){
            //生成入CHB_INTERF_ERRODATA表的数据结构
            data = createChbInterfErrdata(FileName, interfType, erro, bussinessKey, checkEuplicatedKeys);
            if(data != null) {
                erroList.add(data);
            }
        }
        if(!erroList.isEmpty()){
            //入CHB_INTERF_ERRODATA表
            insertErroData(erroList);
        }
    }

    /**
     * 生成入CHB_INTERF_ERRODATA表的数据结构
     * @param FileName
     * @param interfType
     * @param erro
     * @param bussinessKey
     * @param checkEuplicatedKeys
     * @return
     */
    public ChbInterfErrodataWithBLOBs createChbInterfErrdata(String FileName,String interfType,Map<String, String> erro,String bussinessKey,String... checkEuplicatedKeys){
        String msg = erro.get("ERRMSG");
        if (StringUtil.isEmpty(msg)) {
            return null;
        }

        // 拼装错误信息
        ChbInterfErrodataWithBLOBs errodata = new ChbInterfErrodataWithBLOBs();
        // 主键uuid
        errodata.setDatauuid(IDGenerator.generate(36));
        // 文件名
        errodata.setFireName(FileName);
        // 业务主键
        errodata.setDataid("dataId");
        // 接口类型
        errodata.setInterfType(interfType);
        // 错误提示信息
        errodata.setErrMsg(msg);
        // 数据状态
        errodata.setStatus("E");
        errodata.setCreateUser("system");
        errodata.setUpdateUser("system");
        errodata.setCreateTime(new Date());
        errodata.setUpdateTime(new Date());
        erro.remove("ERRMSG");
        errodata.setOrgData(JsonUtils.object2Json(erro));
        return errodata;
    }

    /**
     * 入CHB_INTERF_ERRODATA表
     * @param erroList
     */
    public void insertErroData(List<ChbInterfErrodataWithBLOBs> erroList){
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            int count = 0;
            BaseCommonCheckMapper batchMapper = session.getMapper(BaseCommonCheckMapper.class);
            for(ChbInterfErrodataWithBLOBs single: erroList){
                batchMapper.insertIntoErro(single);
                count ++;
                // 每20次提交
                if (count % 20 == 0) {
                    session.commit();
                    // 清理缓存，防止溢出
                    session.clearCache();
                }
            }
            session.commit();
            // 清理缓存，防止溢出
            session.clearCache();
        }
        catch (Exception e) {
            session.rollback();
            logger.error("SQL执行异常：", e);
        }
        finally {
            session.close();
        }
    }


    /**
     * 判断系统使用的数据库是否是mysql
     *
     * @return 系统使用的数据库是否是mysql
     * @see
     * @since 1.0
     */
    public static boolean isMYSQLDataBase() {
        try {
            Map<String, List<DatabaseInfo>> databaseType = DatabaseTypeHelper.getDatabaseType();

            List<DatabaseInfo> dsList = databaseType.get(DataSourceType.MASERT);
            if (dsList != null && !dsList.isEmpty()) {
                for (int i = 0; i < dsList.size(); i++) {
                    DatabaseInfo dbInfo = dsList.get(i);
                    if (StringUtil.equalsIgnoreCase(DatabaseType.DATABASE_TYPE_MYSQL.getValue(),
                            dbInfo.getDbType().getValue())) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        return false;
    }
}
