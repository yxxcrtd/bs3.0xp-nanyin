package com.joyintech.tams.svreport.interf.chb.mstexchangequote.service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.dao.BaseMapper;
import com.joyintech.tams.common.base.entity.MstExchargeQuote;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.svreport.interf.chb.mstexchangequote.dao.ChbMstExchangeQuoteMapper;
import com.joyintech.tams.systemtools.util.CommonUtil;
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

@Service
public class SvrMstExchangeQuoteFileImpService extends FileImpService {
    /**
     * 接口文件名称类型
     */
    private static final String[] FILENAME_LIST = {InterfDefine.汇率信息数据.getCode()};

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 重复性检查字段
     */
    private static final String[] DUPLICATIED_LIST = {"ASSET_CODE"};

    /**
     * 业务主键
     */
    private static final String BUSSINESSKEY = "ASSET_CODE";

    /**
     * 数据批量一次性insert条数(单条数据字段较多)
     */
    @Value("${application.reportInsertLongCount}")
    private int reportInsertLongCount;

    /**
     * 上游接口文件编码格式
     */
    @Value("${application.reportInterfFileEncoding}")
    public String reportInterfFileEncoding;

    /**
     * 上游接口解析分割符
     */
    @Value("${application.reportInterfSplitCharacter}")
    private String reportInterfSplitCharacter;

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
     * mybatis的sqlsession管理核心
     */
    @Autowired
    private SqlSessionTemplate template;

    /**
     * 基础共通mapper
     */
    @Autowired
    private BaseMapper baseMapper;

    @Autowired
    private ChbMstExchangeQuoteMapper chbMstExchangeQuoteMapper;

    /**
     * 执行步骤
     * @param infoList
     * @param tmpPath
     * @param table
     * @param subTable
     * @param saveFlag
     * @param dataSource
     * @return
     */
    private boolean excuteInfoByStep(List<Map<String,String>> infoList, String tmpPath, ImpTable table,
                                     ImpTable subTable, String saveFlag, String dataSource) {

        List<MstExchargeQuote> mstExchargeQuoteList;
        List<String> listColumn = baseMapper.getTableColumnList(InterfDefine.汇率信息数据.getCode());
        mstExchargeQuoteList = this.convertMapToEntity(infoList,dataSource,listColumn);
        if (mstExchargeQuoteList != null && mstExchargeQuoteList.size() > 0) {
            insertDataMerge(mstExchargeQuoteList, saveFlag);
        }
        return true;
    }

    private void insertDataMerge(List<MstExchargeQuote> mstExchargeQuoteList, String saveFlag) {
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try{
            int count = 0;
            logger.debug("汇率信息数据插入开始--------------" + new Date());
            ChbMstExchangeQuoteMapper batchMapper = session.getMapper(ChbMstExchangeQuoteMapper.class);
            int batchCount = reportInsertLongCount==0?50:reportInsertLongCount;
            int batchLastIndex = batchCount;
            for(int index = 0; index < mstExchargeQuoteList.size();){
                if(batchLastIndex > mstExchargeQuoteList.size()-1){
                    batchLastIndex = mstExchargeQuoteList.size();
                    batchMapper.mergeInfoList(mstExchargeQuoteList.subList(index,batchLastIndex));
                    break;
                }else{
                    batchMapper.mergeInfoList(mstExchargeQuoteList.subList(index,batchLastIndex));
                    index = batchLastIndex;
                    batchLastIndex = index + batchCount;
                }
                count ++;
                if (count % 20 ==0 ){
                    session.commit();
                    session.clearCache();
                }
            }
            logger.debug("汇率信息数据插入结束--------------" + new Date());
            session.commit();
            session.clearCache();
        }finally {
            session.close();
        }
        logger.debug("汇率信息数据插入结束--------------" + new Date());
    }

    private List<MstExchargeQuote> convertMapToEntity(List<Map<String, String>> infoList, String dataSource, List<String> listColumn) {
        List<MstExchargeQuote> entities = new ArrayList<>();
        for (Map<String, String> map : infoList) {
            MstExchargeQuote mstExchargeQuote = new MstExchargeQuote();
            for (String key : listColumn) {
                String value = map.get(key);
                Field field = ReflectionUtils.findField(mstExchargeQuote.getClass(),
                        this.subBlank(StringUtil.lowerCase(key)));
                field.setAccessible(true);
                Type type = field.getGenericType();
                if ("java.math.BigDecimal".equals(type.getTypeName())) {
                    try {
                        field.set(mstExchargeQuote,(value == null || "".equals(value)) ? BigDecimal.ZERO : new BigDecimal(value));
                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return entities;
                    }
                } else if ("java.lang.Integer".equals(type.getTypeName())
                        || "int".equals(type.getTypeName())){
                    try {
                        field.set(mstExchargeQuote,(value == null || "".equals(value)) ? 0 : Integer.valueOf(value));
                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return entities;
                    }
                } else if ("java.lang.Long".equals(type.getTypeName())) {
                    try {
                        field.set(mstExchargeQuote, (value == null || "".equals(value)) ? 0 : Long.valueOf(value));
                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return entities;
                    }
                } else if ("java.lang.Short".equals(type.getTypeName())) {
                    try {
                        field.set(mstExchargeQuote, (value == null || "".equals(value)) ? 0 : Short.valueOf(value));
                    } catch ( IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return entities;
                    }
                } else if("java.util.Date".equals(type.getTypeName())) {
                    try {
                        if (value != null&&""!=value) {
                            field.set(mstExchargeQuote, DateUtil.parse(value));
                        }
                    } catch ( IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return entities;
                    }

                } else {
                    try {
                        field.set(mstExchargeQuote,( value == null || "".equals(value)) ? "" : value);
                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return entities;
                    }
                }
            }
            //创建人
            mstExchargeQuote.setCreateUser(CommonUtil.getUserName());
            //更新人
            mstExchargeQuote.setUpdateUser(CommonUtil.getUserName());

            entities.add(mstExchargeQuote);
        }
        return entities;
    }


    @Override
    public String saveData(List<Map<String, String>> mstExchargeQuoteList, String saveFlag, String dateSource) {
        //解析成功或失败的标识
        boolean success = excuteInfoByStep(mstExchargeQuoteList, dateSource, null, null, null, saveFlag);
        if (success) {
            return "success";
        }
        return "fail";
    }

    @Override
    public String importDB(List<Map<String, String>> mstExchargeQuoteList, InterfFile interfFile) {

        //待入库为空则直接返回
        if (CollectionUtils.isEmpty(mstExchargeQuoteList)){
            return "success";
        }

        String saveResult = saveData(mstExchargeQuoteList, null , interfFile.getDataSource());

        // 更新系统配置时间
        ChbInterfUtil.updateInterfTimeBySysdate(InterfDefine.汇率信息数据.getCode());

        return saveResult;
    }
}
