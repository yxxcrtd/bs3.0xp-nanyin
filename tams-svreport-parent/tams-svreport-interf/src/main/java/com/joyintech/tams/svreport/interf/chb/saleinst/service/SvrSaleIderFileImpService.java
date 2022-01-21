package com.joyintech.tams.svreport.interf.chb.saleinst.service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.dao.BaseMapper;
import com.joyintech.tams.common.base.entity.ChbSalerIdenInfo;
import com.joyintech.tams.common.base.entity.ChbSalerInstInfo;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dto.ChbCheckTableInfo;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.svreport.interf.chb.saleinst.dao.ChbSaleIderInterfMapper;
import com.joyintech.tams.svreport.interf.chb.saleinst.dao.ChbSaleInstInterfMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 功能:销售人员身份信息文件解析方式入库
 * @since
 * @version 3.0
 * @作者：
 * @创建时间：
 * @修改记录：
 */
 
@Service
public class SvrSaleIderFileImpService extends FileImpService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

	
    /**
     * mybatis的sqlsession管理核心
     */
    @Autowired
	private SqlSessionTemplate template;

    /**
     * 中债基础数据mapper
     */
    @Autowired
    private BaseMapper baseMapper;
    
    /**
     * 上游接口解析读取文件处理最大线程数
     */
    @Value("${application.interfMaxCorePoolSize}")
    private int interfMaxCorePoolSize;

    /**
     * 上游接口解析读取文件处理,单次sqlldr处理最大数据条数
     */
    @Value("${application.reportInterfOneTimeSqlLdrDataCount}")
    private int reportInterfOneTimeSqlLdrDataCount;

    /**
     * 上游接口解析读取文件处理,单次sqlldr处理最大数据条数
     */
    @Value("${application.reportInterfOneTimeSqlLdrCommitCount}")
    private int reportInterfOneTimeSqlLdrCommitCount;
    
    /**
     * sqlldr用数据库连接
     */
    @Value("${spring.datasource.reportSqlldrDatabaseName}")
    private String reportSqlldrDatabaseName;

    /**
     * 数据批量一次性insert条数(单条数据字段较多)
     */
    @Value("${application.reportInsertLongCount}")
    private int reportInsertLongCount;

	/**
	 * 功能:文件处理方法
     * 描述： 
     *      1.checkData --校验数据
     *      2.saveData --入库
	 * @since 3.0
	 * @version 3.0
	 * @author：
     * @param saleInstList 解析出的数据
     * @param interfFile 接口文件
	 * @see FileImpService#(List)
	 */
    @Override
    public String importDB(List<Map<String, String>> saleInstList,InterfFile interfFile, String date){
        // 数据检查
        checkDataCodeCheck(saleInstList, interfFile.getFilename(), ChbCheckTableInfo.销售人员身份信息表INFO);
        if (CollectionUtils.isEmpty(saleInstList)){
            return "success";
        }
        String saveResult = saveData(saleInstList,null,interfFile.getDataSource());
        // 更新系统配置时间
        ChbInterfUtil.updateInterfTimeBySysdate( InterfDefine.销售人员身份信息接口数据.getCode());
        return saveResult;
    }
    
    /**
     * 功能:数据入库
     * @since 3.0
     * @version 3.0
     * @author：
     * @param listData 数据
     * @param saveFlag:F－全量，I－增量
     * @param  dataSource 数据来源 
     * @see FileImpService#
     */
    @Override
    public String saveData(List<Map<String, String>> listData,String saveFlag,String dataSource) {
        // 解析成功或失败的标识
        boolean success = excuteInfoByStep(listData, dataSource, null, null, null,saveFlag);
        // 数据解析完成，并导入数据库成功后，将ftp文件修改名称
        if (success) {
            return "success";
        }
        return "fail";
    }
    
    /**
     * 功能:批量入库
     * @since 3.0
     * @version 3.0
     * @author：
     * @param infoList
     * @param dataSource 数据来源
     * @param tmpPath 临时文件路径
     * @param table 基础表
     * @param subTable 登记表
     * @param saveFlag:F－全量，I－增量
     * @return 
     * boolean
     */
    private boolean excuteInfoByStep(List<Map<String, String>> infoList, String dataSource,
            String tmpPath, ImpTable table, ImpTable subTable,String saveFlag) {
        // 将map转换成dto
        List<ChbSalerIdenInfo> saleInfoList = new ArrayList<>(infoList.size());
        List<String> listCloumn = baseMapper.getTableColumnList(InterfDefine.销售人员身份信息接口数据.getCode());
        saleInfoList = this.convertMapToEntity(infoList, dataSource, listCloumn);
        if (saleInfoList != null && saleInfoList.size() > 0) {
            // 增量方式入库
            insertDataMerge(saleInfoList);
        }
        return true;
    }

    /**
     * 将map转换为entity格式
     *
     * @param infoList 传入的listMap
     * @param
     * @param listCloumn 表列名
     * @param dataSource 数据来源
     * @see
     * @since 1.0
     */
    private List<ChbSalerIdenInfo> convertMapToEntity(List<Map<String, String>> infoList, String dataSource,
                                                      List<String> listCloumn) {
        List<ChbSalerIdenInfo> entities = new ArrayList<ChbSalerIdenInfo>();

        for (Map<String, String> map : infoList) {
            ChbSalerIdenInfo chbSalerIdenInfo = new ChbSalerIdenInfo();
            try{
            	
            
            for (String string : listCloumn) {
                String value = map.get(string);
                if(value==null){
                	continue;
                }
                Field field = ReflectionUtils.findField(chbSalerIdenInfo.getClass(),
                        this.subBlank(StringUtil.lowerCase(string)));
                if(field==null){
                    logger.info(string);
                    continue;
                  }
                field.setAccessible(true);
                Type type = field.getGenericType();
                if ("java.math.BigDecimal".equals(type.getTypeName())) {
                    try {
                        if (StringUtil.isNotEmpty(value)){
                            field.set(chbSalerIdenInfo,new BigDecimal(value));
                        }
                    } catch ( IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return entities;
                    }
                }
                else if ("java.lang.Integer".equals(type.getTypeName())
                        || "int".equals(type.getTypeName())) {
                    try {
                        if (StringUtil.isNotEmpty(value)){
                            field.set(chbSalerIdenInfo,Integer.valueOf(value));
                        }
                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return entities;
                    }
                }
                else if ("java.lang.Long".equals(type.getTypeName())) {
                    try {
                        if (StringUtil.isNotEmpty(value)){
                            field.set(chbSalerIdenInfo, Long.valueOf(value));
                        }
                    } catch ( IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return entities;
                    }
                }
                else if ("java.lang.Short".equals(type.getTypeName())) {
                    try {
                        if (StringUtil.isNotEmpty(value)){
                            field.set(chbSalerIdenInfo,Short.valueOf(value));
                        }
                    } catch ( IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return entities;
                    }
                }
                else if ("java.util.Date".equals(type.getTypeName())) {
                    if (value != null&&""!=value) {
                        try {
                            field.set(chbSalerIdenInfo, DateUtil.parse(value));
                        } catch ( IllegalAccessException e) {
                            logger.error(e.getMessage(),e);
                            return entities;
                        }
                    }
                }
                else {
                    try {
                        field.set(chbSalerIdenInfo, (value == null || "".equals(value)) ? "" : value);
                    } catch ( IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return entities;
                    }
                }
            }
            }catch(Exception e){
            	logger.debug(e.getMessage(),e);
            	logger.error(e.getMessage(), e);
            }
            chbSalerIdenInfo.setDataDate(new Date());
            entities.add(chbSalerIdenInfo);
        }
        return entities;
    }
    
    
    /**
     * 功能:数据入库
     * @since 3.0
     * @version 3.0
     * @author：
     * @param saleInfoList 总部或分支机构信息
     */
    public void insertDataMerge(List<ChbSalerIdenInfo> saleInfoList) {
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            int count = 0;
            logger.debug("销售人员身份信息插入开始--------------" + new Date());
            ChbSaleIderInterfMapper batchMapper = session.getMapper(ChbSaleIderInterfMapper.class);
            int batchCount = reportInsertLongCount==0?50:reportInsertLongCount;//每批commit的个数
            int batchLastIndex = batchCount;//每批最后一个的下标
            for(int index = 0; index < saleInfoList.size();){
                if(batchLastIndex > saleInfoList.size()-1){
                    batchLastIndex = saleInfoList.size();
                    batchMapper.mergeInfoList(saleInfoList.subList(index, batchLastIndex));
                    break;//数据插入完毕,退出循环
                }else{
                    batchMapper.mergeInfoList(saleInfoList.subList(index, batchLastIndex));
                    index = batchLastIndex;//设置下一批下标
                    batchLastIndex = index + batchCount;                     
                }
                count ++;
                // 每20次提交
                if (count % 20 == 0) {
                    session.commit();
                    // 清理缓存，防止溢出
                    session.clearCache();
                }
            }
            logger.debug("销售人员身份信息插入结束--------------" + new Date());
            session.commit();
            // 清理缓存，防止溢出
            session.clearCache();
        }
        finally {
            session.close();
        }
    }

}
