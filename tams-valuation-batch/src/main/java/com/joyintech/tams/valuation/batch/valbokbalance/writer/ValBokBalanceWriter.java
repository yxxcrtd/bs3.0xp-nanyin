/*
 * 文件名：ValBokBalanceWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V1.0
 * 修改人：ranhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.valbokbalance.writer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.BokBalTableDataMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.batch.common.accounting.entity.ValBokBalanceDto;
import com.joyintech.tams.valuation.batch.common.util.ExcelTools;
import com.joyintech.tams.valuation.batch.valbokbalance.dto.ValBokBalanceGenerateResult;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.valuation.common.utils.DBUtil;
import com.joyintech.tams.valuation.common.utils.ExcelNamedUtil;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author weipeng
 * @version 1.0 2018年5月28日
 * @see ValBokBalanceWriter
 * @since 1.0
 */
@Service
public class ValBokBalanceWriter extends BaseWriter<NeedToWriteEntity>{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
//TODO 一体化问题8：表删除（文件生成信息:BOK_FILE_GEN） 
//    @Autowired
//    private BokFileGenMapper valFileGenMapper;

    @Autowired
    private BokBalTableDataMapper bokBalTableDataMapper;
    @Autowired
    private DBUtil dBUtil;
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends NeedToWriteEntity> items) throws Exception {
        //获取process返回组合的list值 - 记账凭证list
        List<ValBokBalanceGenerateResult> resultList = (List<ValBokBalanceGenerateResult>) items;
        //是否使用基金估值表开关
        String schemeCode = CacheUtil.getSysParam("SCHEME_MODE");
        //循环科目余额list，对每个记账凭证进行操作
        for(ValBokBalanceGenerateResult result : resultList){
        	List<ValBokBalanceDto> list = result.getValBokBalanceBatch();
//        	valBoKBanlanceMapper.deleteBokBalTableData(result.getBookDate(), result.getProdId());
        	list.forEach(item->{
        	    bokBalTableDataMapper.insert(item.toBokBalTableData(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
        	        result.getProdId(), result.getBooksetName(), result.getBookDate()));
        	});
            if(StringUtil.equals(CacheUtil.getSysParam("EXPORT_VALBOKBANLANCE_SWITCH"), "ON")) {
                String fileName = null;
                if (StringUtils.isEmpty(schemeCode) || DictCodeList.估值模板_基金模板.getValue().equals(schemeCode)) {
                    // 基金模板名称
                    fileName = result.getProdId() + "_" + DateUtil.format(result.getBookDate(), "yyyy_MM_dd") + "_余额表" + ".xls";
                } else {
                    // 理财产品模板名称
                    fileName = ExcelNamedUtil.generalExcelName(null, DateUtil.format(result.getBookDate(), "yyyy_MM_dd"), result.getProdId(), "02", DictCodeList.估值模板_理财产品模板.getValue());
                }
                String outPath = CacheUtil.getSysParam("EXPORT_VALBOKBANLANCE") + File.separator + fileName;
                String tplPath = CacheUtil.getSysParam("BATCH_TPL_OUT") + File.separator + "ValBokBalance.xls";
                String tempName = "ValBokBalance.xls";
                InputStream stream = null;
                Map<String, Object> paraMap = new HashMap<>();
                paraMap.put("prodTitle", String.format("%s（%s）余额表", result.getFinprodName(), result.getProdId()));
                Calendar cal = Calendar.getInstance();
                cal.setTime(result.getBookDate());
                paraMap.put("dateStr", String.format("%d/%d/%d", cal.get(Calendar.YEAR),cal.get(Calendar.MONTH) + 1,cal.get(Calendar.DAY_OF_MONTH)));
                paraMap.put("list", list);
                try {
                    stream = new FileInputStream(tplPath);
                    ExcelTools.writeExcel(stream, tempName, paraMap, outPath);
                    // 上传文件到文件服务器上
//                    SftpTools sftpTools = new SftpTools();
//                    String ip = CacheUtil.getSysParam("FILESERVER_HOST");// 主机IP
//                    Integer port = Integer.parseInt(CacheUtil.getSysParam("FILESERVER_PORT"));// 主机端口
//                    String username = CacheUtil.getSysParam("FILESERVER_USERNAME");// 主机用户名
//                    String password = CacheUtil.getSysParam("FILESERVER_PASSWORD");// 主机密码或秘钥地址
//                    String sftpLoginType = CacheUtil.getSysParam("SFTP_LOGIN_TYPE");// SFTP登录方式
//                    String remotePath = CacheUtil.getSysParam("FILESERVER_REMOTE_PATH");
//                    sftpTools.login(username, password, ip, port, sftpLoginType );
//                    
//                    
//                    if (StringUtils.isEmpty(schemeCode) || DictCodeList.估值模板_基金模板.getValue().equals(schemeCode)) {
//                        sftpTools.putFile(outPath, remotePath);
//                    } else {
//                        sftpTools.putFileDate(outPath, remotePath,DateUtil.format(result.getBookDate(), "yyyyMMdd"));
//                    }
//                    sftpTools.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                } finally {
                    if (stream != null) {
                        try {
                            stream.close();
                        } catch (IOException e) {
                            logger.error(e.getMessage(), e);
                        }
                    }
                }
//                BokFileGen valFileGen = new BokFileGen();
//                valFileGen.setBooksetId(result.getProdId());
//                valFileGen.setGenDate(result.getBookDate());
//                valFileGen.setFileName(fileName);
//                valFileGen.setFilePath(outPath);
//                valFileGen.setFileType(DictCodeList.文件类型_余额表.getValue());
//                valFileGenMapper.deleteByPrimaryKey(valFileGen);
//                // 上传状态设为 02：未上传
//                valFileGen.setUploadStatus(GlobalConstant.TYPE_02);
//                valFileGenMapper.insert(valFileGen);
            }
        }
    }
}
