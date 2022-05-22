package com.joyintech.tams.svreport.chb.businessdatamaintain.chbprdbaseinfo.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.svreport.base.service.BatchInsertService;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dto.ChbCheckTableInfo;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.report.interf.common.imp.ImportGenerator;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbinvesttrade.dao.ChbInvestTradeDao;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbinvesttrade.dto.ChbInvestTradeDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbprdbaseinfo.dao.ChbPrdBaseInfoDao;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbprdbaseinfo.dto.ChbPrdBaseInfoDto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author wubingbing
 * @date 2022-05-19 10:48
 */
@Service
public class ChbPrdBaseInfoFileImpService extends FileImpService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 数据批量一次查询或者删除条数
     */
    @Value("${application.reportInsertLongCount}")
    private int reportInsertLongCount;

    @Autowired
    private ChbPrdBaseInfoDao chbPrdBaseInfoDao;

    @Autowired
    private SqlSessionTemplate template;

    @Autowired
    private BatchInsertService batchInsertService;
    @Override
    public String saveData(List<Map<String, String>> listData, String saveFlag, String dateSource) {
        boolean success = true;
        String type = "";
        String tmpPath =  SvrDataUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        //转换实体类
        List<ChbPrdBaseInfoDto> chbPrdBaseInfoDtoList = (List<ChbPrdBaseInfoDto>)convertMapToEntity(listData, ChbPrdBaseInfoDto.class);

        if(InterfConstants.SAVEFLAG_ADD.equals(saveFlag)){
            //增量
            mergeData(chbPrdBaseInfoDtoList);

        }
        //增量插入 小于100000正常入库 超过100000条使用sqlldr进行入库
        try{
            //先根据更新日删除当日插入的数据
            chbPrdBaseInfoDao.deleteData();
            insertData(chbPrdBaseInfoDtoList);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            success = false;
        }
        return success?"success":"fail";
    }

    private void insertData(List<ChbPrdBaseInfoDto> list) {
        logger.debug("批量插入产品基本信息----开始-----:" + DateUtil.format(new Date()));
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        //批量插入mapper
        ChbPrdBaseInfoDao batchMapper =  session.getMapper(ChbPrdBaseInfoDao.class);
        try {
            int count = 0;
            for (ChbPrdBaseInfoDto dto : list) {

                batchMapper.insert(dto);
                count++;
                if (count % 1000 == 0) {
                    session.commit();
                    session.clearCache();
                }
            }
            session.commit();
            session.clearCache();
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
        } finally {
            session.close();
        }
        logger.debug("批量插入产品信息----结束-----:" + DateUtil.format(new Date()));
    }

    private void mergeData(List<ChbPrdBaseInfoDto> listNE) {
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            int count = 0;
            logger.debug("产品基本插入开始--------------" + new Date());
            ChbPrdBaseInfoDao batchMapper = session.getMapper(ChbPrdBaseInfoDao.class);
            int batchCount = reportInsertLongCount==0?500:reportInsertLongCount;//每批commit的个数
            int batchLastIndex = batchCount;//每批最后一个的下标
            for(int index = 0; index < listNE.size();){
                if(batchLastIndex > listNE.size()-1){
                    batchLastIndex = listNE.size();
                    batchMapper.mergeData(listNE.subList(index, batchLastIndex));
                    break;//数据插入完毕,退出循环
                }else{
                    batchMapper.mergeData(listNE.subList(index, batchLastIndex));
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
            session.commit();
            // 清理缓存，防止溢出
            session.clearCache();
        } finally {
            session.close();
        }
    }

    @Override
    public String importDB(List<Map<String, String>> listData, InterfFile interfFile, String date) {
        // 产品基本信息
        ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.产品基本信息接口数据.getCode());
        String coverFlag = "Y";
        if (interfConfig == null){
            logger.info("CHB_INTERF_CONFIG表中无配置项！");
        }else{
            coverFlag = interfConfig.getCoverFlag()==null?"Y":interfConfig.getCoverFlag();
        }
        checkDataCodeCheck(listData, interfFile.getFilename(), ChbCheckTableInfo.个人发行登记表INFO,coverFlag);
        if (CollectionUtils.isEmpty(listData)){
            return "success";
        }

        String saveResult = saveData(listData,null,interfFile.getDataSource());
        return saveResult;
    }
}
