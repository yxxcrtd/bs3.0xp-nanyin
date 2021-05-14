package com.joyintech.tams.svreport.interf.chb.investtrade.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.dao.BaseMapper;
import com.joyintech.tams.common.base.entity.ChbInvestTrade;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.imp.ImportGenerator;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.svreport.interf.chb.assetinfo.dao.ChbAssetInfoInterfMapper;
import com.joyintech.tams.svreport.interf.chb.investtrade.dao.ChbInvestTradeInterfMapper;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 功能:投资交易文件解析方式入库
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年4月29日c
 * @修改记录：
 */
@Service
public class SvrInvestTradeFileImpService  extends FileImpService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 接口文件名称类型
     */
    private static final String[] FILENAME_LIST = {InterfDefine.投资交易信息接口数据.getCode()};
    
    /**
     * 业务主键
     */
    private static final String BUSSINESSKEY = "TRADE_CODE";
    
    /**
     * 重复性检查字段
     */
    private static final String[] DUPLICATIED_LIST = {"TRADE_CODE"};
	
	/**
	 * 使用sqlloader入库的条数限制
	 */
	private static final int SQLLOADER_MAX_SIZE = 1000;
    
    /**
     * mybatis的sqlsession管理核心
     */
    @Autowired
	private SqlSessionTemplate template;
    
    /**
     * 投资交易接入mapper
     */
    @Autowired
	private ChbInvestTradeInterfMapper chbInvestTradeInterfMapper;
    
    /**
     * 投资交易接入mapper
     */
    @Autowired
    private ChbAssetInfoInterfMapper chbAssetInfoInterfMapper;
    
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
     * 数据批量一次性insert条数
     */
    @Value("${application.reportInsertCount}")
    private int reportInsertCount;


    /**
     * 数据批量一次性insert条数(单条数据字段较多)
     */
    @Value("${application.reportInsertLongCount}")
    private int reportInsertLongCount;
    /**
     * 功能:从接口中获取投资交易信息
     * 描述：
     *      1.调用共通方法传入接口文件
     *      2.文件处理
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月29日
     * @param currentDate 解析文件日期
     * @param xmlFileList 下载文件list
     * @return 
     * String
     */
	@SuppressWarnings("unchecked")
    public String getAssetInfoByInterf(String currentDate) {
	    // 调用共通方法传入接口文件
	    List<InterfFile> xmlFileList = queryAndDownloadFiles(currentDate, Arrays.asList(FILENAME_LIST));
	    for (InterfFile interfFile : xmlFileList){
    	    if (interfFile != null
                    && StringUtil.isNotEmpty(interfFile.getFilename())
                    && interfFile.getFilename().
                    contains(DictCodeListExt2.上游接口文件命名规范_中债_投资交易信息.getValue())){
                List<Map<String, String>> investTradeList = new ArrayList<Map<String, String>>();
                try {
                    // 解析文件
                    Map<String, Object> map = parseFile(interfFile);
                    investTradeList = (List<Map<String, String>>) map.get("dataList");
                    String result = (String) map.get("remoteDir");
                    if ("fail".equals(result)){
                        return "fail";
                    }
                } catch (FileNotFoundException e) {
                    logger.error("投资交易信息解析错误！",e.getMessage(),e);
                    return "fail";
                }
                // 文件处理
                String saveResult = importDB(investTradeList,interfFile);
                if ("fail".equals(saveResult)){
                    return "fail";
                }
            } else {
                return "fail";
            }
	    }
        return "success";
	}

	/**
	 * 功能:文件处理方法
     * 描述： 
     *      1.parseFile --将文件转换为Map<String,String>
     *      2.checkData --校验数据
     *      3.saveData --入库
	 * @since 1.7.0
	 * @version 1.7.0
	 * @author：weiyuqiang 2019年5月5日
	 * @param fileLists 
	 * @see com.joyintech.tams.common.FileImpService#importDB(java.util.List)
	 */
    @Override
    public String importDB(List<Map<String, String>> investTradeList,InterfFile interfFile) {
        // 数据检查
        // 添加待处理的数据
//        checkData(investTradeList, interfFile.getFilename(),
//                InterfDefine.投资交易信息接口数据.getCode(), InterfDefine.投资交易信息接口数据.getCode(),BUSSINESSKEY, DUPLICATIED_LIST);
        // 待入库为空则直接返回
        if (CollectionUtils.isEmpty(investTradeList)){
            return "success";
        }
        // 检查通过数据入库
//        ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(InterfDefine.投资交易信息接口数据.getCode());
//        if (interfConfig == null){
//            logger.info("CHB_INTERF_CONFIG表中无配置项！");
//            return "fail";
//        }
//        String saveFlag = interfConfig.getContentType();
        String saveResult = saveData(investTradeList,null,interfFile.getDataSource());
        // 更新系统配置时间
        ChbInterfUtil.updateInterfTimeBySysdate( InterfDefine.投资交易信息接口数据.getCode());
        return saveResult;
    }

    /**
     * 功能:数据入库
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月5日 
     * @param listData 数据
     * @param saveFlag:F－全量，I－增量
     * @param  dataSource 数据来源 
     * @see com.joyintech.tams.common.FileImpService#saveData()
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
     * 功能:数据入库前删除数据
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月5日 
     * @param investTradeList 投资交易信息
     * @param saveFlag:F－全量，I－增量
     */
    public void deleteDataBatch(List<ChbInvestTrade> investTradeList,String saveFlag) {
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            logger.debug("投资交易删除当日数据开始--------------" + new Date());
            // 全量直接清表
            if (com.joyintech.tams.report.interf.common.constant.InterfConstants.SAVEFLAG_ALL.equals(saveFlag)) {
                // chbInvestTradeInterfMapper.deleteAllData();
                chbInvestTradeInterfMapper.deleteBySysTime(true);
                // 查询之前接入的资产交易
                List<String> tradeList = chbInvestTradeInterfMapper.selectTradeCodeBySysdate(false);
                Map<String,String> tradeMap = tradeList.stream().collect(Collectors.toMap(p -> p, p -> p,(key1,key2) -> key2));
                List<ChbInvestTrade> deleteTradeList = investTradeList.stream().filter(trade -> StringUtil.isNotEmpty(tradeMap.get(trade.getTradeCode()))).collect(Collectors.toList());
                logger.debug("资产要素删除昨日数据开始--------------");    
                int batchCount = Integer.parseInt( null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?"50" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));//每批commit的个数
                int batchLastIndex = batchCount;//每批最后一个的下标
                for(int index = 0; index < deleteTradeList.size();){
                    if(batchLastIndex > deleteTradeList.size()-1){
                        batchLastIndex = deleteTradeList.size();
                        chbInvestTradeInterfMapper.deleteByTradeCode(deleteTradeList.subList(index, batchLastIndex));
                        break;//数据插入完毕,退出循环
                    }else{
                        chbInvestTradeInterfMapper.deleteByTradeCode(deleteTradeList.subList(index, batchLastIndex));
                        index = batchLastIndex;//设置下一批下标
                        batchLastIndex = index + batchCount;                     
                    }
                }
                logger.debug("资产要素删除昨日数据结束--------------");    
            } else if (com.joyintech.tams.report.interf.common.constant.InterfConstants.SAVEFLAG_ADD.equals(saveFlag)) {// 增量
                // chbInvestTradeInterfMapper.deleteBySysTime( true);
                logger.info("增量类型无需删除！");
            } else {
                logger.error("无法识别全增量类型！");
            }
            logger.debug("资产要素删除当日数据结束--------------" + new Date());
            session.commit();
            // 清理缓存，防止溢出
            session.clearCache();
        }
        finally {
            session.close();
        }
    }

    /**
     * 功能:数据入库
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月5日 
     * @param investTradeList 投资交易信息
     * @param saveFlag:F－全量，I－增量
     */
    public void insertDataMerge(List<ChbInvestTrade> investTradeList,String saveFlag) {
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            int count = 0;
            logger.debug("投资交易插入开始--------------" + new Date());
            ChbInvestTradeInterfMapper batchMapper = session.getMapper(ChbInvestTradeInterfMapper.class);
            int batchCount = reportInsertLongCount==0?50:reportInsertLongCount;//每批commit的个数
            int batchLastIndex = batchCount;//每批最后一个的下标
            for(int index = 0; index < investTradeList.size();){
                if(batchLastIndex > investTradeList.size()-1){
                    batchLastIndex = investTradeList.size();
                    batchMapper.mergeInfoList(investTradeList.subList(index, batchLastIndex));
                    break;//数据插入完毕,退出循环
                }else{
                    batchMapper.mergeInfoList(investTradeList.subList(index, batchLastIndex));
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
            logger.debug("投资交易插入结束--------------" + new Date());
            session.commit();
            // 清理缓存，防止溢出
            session.clearCache();
        }
        finally {
            session.close();
        }
        logger.debug("投资交易插入结束--------------" + new Date());
    }
    
    /**
     * 功能:批量入库
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月29日
     * @param infoList 解析数据
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
        List<ChbInvestTrade> investTradeList = new ArrayList<>(infoList.size());
        List<String> listCloumn = baseMapper.getTableColumnList(InterfDefine.投资交易信息接口数据.getCode());
        investTradeList = this.convertMapToEntity(infoList, dataSource, listCloumn);
        if (investTradeList != null && investTradeList.size() > 0) {
            // 增量方式入库
            insertDataMerge(investTradeList,saveFlag);
        }
        return true;
    }
    
    /**
     * 将map转换为entity格式
     *
     * @param infoList 传入的listMap
     * @param entities 转换后的lis
     * @param listCloumn 表列名
     * @param dataSource 数据来源
     * @see
     * @since 1.0
     */
    private List<ChbInvestTrade> convertMapToEntity(List<Map<String, String>> infoList,String dataSource,
                                    List<String> listCloumn) {
        // 汇率信息放至最外层，每次计算折算人民币，先查rateMap
        Map<String, BigDecimal> rateMap = new HashMap<>();
        List<ChbInvestTrade> entities = new ArrayList<ChbInvestTrade>();
        // 根据资产代码和资产分类：资管计划去中债要素表中查询
        List<String> assetTypeList = new ArrayList<String>();
        assetTypeList.add(DictCodeListExt2.资产_负债类别_资产管理产品.getValue());
        assetTypeList.add(DictCodeListExt2.资产_负债类别_私募基金.getValue());
        assetTypeList.add(DictCodeListExt2.资产_负债类别_委外投资_协议方式.getValue());
        List<String> assetPlanLList = chbAssetInfoInterfMapper.selectAssetCodeByAssetType(assetTypeList, null);
        Map<String,String> assetPlanLMap = assetPlanLList.stream().collect(Collectors.toMap(p -> p,p -> p,(key1, key2) -> key2));
        // 获取产品表的列
        // List<String> listCloumn = prodFactorInfo001Mapper.getTableColumnList(CHB_PRD_BASEINFO);
        for (Map<String, String> map : infoList) {
            ChbInvestTrade chbInvestTrade = new ChbInvestTrade();
            for (String string : listCloumn) {
                String value = map.get(string);
                Field field = ReflectionUtils.findField(chbInvestTrade.getClass(),
//                  StringUtil.dealString(StringUtil.lowerCase(string), false));
                        this.subBlank(StringUtil.lowerCase(string)));
                if(field==null){
                  System.out.println(string);
                  continue;
                }
                field.setAccessible(true);
                Type type = field.getGenericType();
                if ("java.math.BigDecimal".equals(type.getTypeName())) {
                    try {
                        if (StringUtil.isNotEmpty(value)){
                            field.set(chbInvestTrade,new BigDecimal( value));
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
                            field.set(chbInvestTrade,Integer.valueOf(value));
                        }
                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return entities;
                    }
                }
                else if ("java.lang.Long".equals(type.getTypeName())) {
                    try {
                        if (StringUtil.isNotEmpty(value)){
                            field.set(chbInvestTrade,Long.valueOf(value));
                        }
                    } catch ( IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return entities;
                    }
                }
                else if ("java.lang.Short".equals(type.getTypeName())) {
                    try {
                        if (StringUtil.isNotEmpty(value)){
                            field.set(chbInvestTrade,Short.valueOf(value));
                        }
                    } catch ( IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return entities;
                    }
                }
                else if ("java.util.Date".equals(type.getTypeName())) {
                    if (value != null&&""!=value) {
                        try {
                            field.set(chbInvestTrade, DateUtil.parse(value));
                        } catch ( IllegalAccessException e) {
                            logger.error(e.getMessage(),e);
                            return entities;
                        }
                    }
                }
                else {
                    try {
                        field.set(chbInvestTrade, value);
                    } catch ( IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                        return entities;
                    }
                }
            }
            chbInvestTrade.setDataSource(SysDataUtil.getNameFromDataDict(DictCode.数据来源, dataSource));
            chbInvestTrade.setTradeType("01");
            // 以下是定制化的dto设置
            // 有效状态
//            chbInvestTrade.setStatus(SVReportDictCodeList.维护数据状态_有效.getValue());
            // 主键
            chbInvestTrade.setTradeSeqno(IDGenerator.generate(36));
            // 非人民币币种折算人民币金額
            if (!StringUtil.equals(chbInvestTrade.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue())) {
                chbInvestTrade.setCnyAmt(ChbCcyConvertUtil.changeCnyMoney(chbInvestTrade.getCcy(), chbInvestTrade.getAmt(), chbInvestTrade.getDealdate(), rateMap));
            } else {
                chbInvestTrade.setCnyAmt(chbInvestTrade.getAmt());
            }
            // 面额折算人民币
            if (chbInvestTrade.getFacevalue() != null){
                chbInvestTrade.setFacevalue(ChbCcyConvertUtil.changeCnyMoney(chbInvestTrade.getCcy(), chbInvestTrade.getFacevalue(), chbInvestTrade.getDealdate(), rateMap));
            }
            // TRADE_TYPE资产交易默认'01'
            if(DictCodeListExt2.投资端交易类型_资产交易.getValue().equals(chbInvestTrade.getTradeType())){
                // TRADE_TYPE资产交易默认'01'，判断是否为资管计划交易,即资产一级分类为资管计划
                if (CollectionUtils.isNotEmpty(assetPlanLList)
                        && assetPlanLMap.get(chbInvestTrade.getAssetCode()) != null){
                        chbInvestTrade.setTradeType(DictCodeListExt2.投资端交易类型_资管计划交易.getValue());
                }
            }
            // 是否底层交易流水 01 是
            chbInvestTrade.setIsUnderTrade(DictCodeList.是否_中债_否.getValue());
            // 利率为空
            chbInvestTrade.setRate(null);
            // 创建人
            chbInvestTrade.setCreateUser(CommonUtil.getUserName());
			//更新人
            chbInvestTrade.setUpdateUser(CommonUtil.getUserName());
            // 创建部门
            chbInvestTrade.setCreateDept(CommonUtil.getDeptCode());
            // 部门代码
            chbInvestTrade.setDeptCode(CommonUtil.getDeptCode());
            // 机构代码
            chbInvestTrade.setOrgCode(CommonUtil.getOrgCode());
            entities.add(chbInvestTrade);
        }
        return entities;
    }


}
