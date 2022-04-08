/**
 * 
 */
package com.joyintech.tams.common.svreport.base.service;

import com.joyintech.framework.jdbc.datasource.DatabaseInfo;
import com.joyintech.framework.jdbc.datasource.DatabaseType;
import com.joyintech.framework.jdbc.datasource.DatabaseTypeHelper;
import com.joyintech.framework.jdbc.enums.DataSourceType;
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

import java.util.*;
import java.util.regex.Pattern;
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
    public List<BaseCommonCheckDto> checkBysql(List<Map<String,String>> list, String tableName) {
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
        List<BaseCommonCheckDto> aa = new ArrayList<>();
        //返回的数据结构
        List<ExcelValidDto> bb = new ArrayList<>();
        if(isMYSQLDataBase()) {
             aa = this.checkBysql(datas, TableName);
        }else{

        }
        for(BaseCommonCheckDto dto:aa){
            bb.add(dto.getExcelValidDto());
        }

        //当是接口接入时才入CHB_INTERF_ERRODATA表
        if(aa != null && aa.size() !=0 && StringUtil.equals("interf",checkFlag)) {
            //数据库check不通过的插入CHB_INTERF_ERRODATA
            dealErrBeforInsert(aa, FileName, interfType, bussinessKey, dataType, checkEuplicatedKeys);
        }
        return bb;
    }


    /**
     * 数据校验入口  业务
     *
     * @param datas 需要校验的数据
     * @param
     * @return 校验后的数据
     */
    public List<ExcelValidDto> checkDataYW(List<Map<String,String>> datas) {
        // 判断传入的数据是否为空
        if(CollectionUtils.isEmpty(datas)) {
            return null;
        }



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
