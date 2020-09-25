/*
 * 文件名：batchUtilServer.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.disclosure.busicommon.infodisclosure.batchUtil.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.batchUtil.dao.SysBatchStartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.dao.SysBatchGrpMapper;
import com.joyintech.framework.base.dao.SysBatchPrdGrpMapper;
import com.joyintech.framework.base.dao.SysBatchgrpExecInfoMapper;
import com.joyintech.framework.base.dto.Product;
import com.joyintech.framework.base.entity.SysBatchGrp;
import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.base.entity.SysBatchJob;
import com.joyintech.framework.base.entity.SysBatchPrdGrp;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.base.service.ProductService;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年12月29日
 * @see BatchUtilServer
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BatchUtilServer {

    @Autowired
    private SysBatchPrdGrpMapper batchProductGroupMapper;
    
    @Autowired
    private SysBatchGrpMapper batchGroupMapper;
    
    @Autowired
    private SysBatchStartMapper sysBatchStartMapper;
    
    @Autowired
    private SysBatchgrpExecInfoMapper batchExecuteDetailMapper;
    
    @Autowired(required = false)
    private ProductService productService;
    
    /**
     * 将定时任务扫描任务组插入springBatch中
     * @return
     */
    @DataSource(DataSourceType.OTHER)
    public boolean execBatchRefresh(){
        boolean flag = false;
        String code = CacheUtil.getSysParam(ReportGlobalConstant.START_BATCH_CODE);
         String selectedBatchGroup = null!=code?code:"10";
         String selectedProductGroup = "";
         String startDate = DateUtil.getCurrentDate();
         String endDate = DateUtil.getCurrentDate();
         String batchGroupType = "1";
         String prodGroupType = "0";

         String batchGroupSeqNo = selectedBatchGroup;
         String nowString = null;
         if (GlobalConstant.BATCH_EXECUTE_TYPE_SINGLE.equals(batchGroupType)) {
             batchGroupSeqNo = IDGenerator.generate();
             SysBatchGrp batchGroup = new SysBatchGrp();
             batchGroup.setSystemCode("SYS");
             nowString = DateUtil.getCurrentDateTime();
             batchGroup.setSeqNo(batchGroupSeqNo);
             batchGroup.setBatchName("startBatch创建于" + nowString);
             batchGroup.setDetail(batchGroup.getBatchName() + ";启动批处理时创建。");
             String[] batchGroupSeqNos = selectedBatchGroup.split(",");
             List<SysBatchJob> jobList = new ArrayList<>();
             for (String taskId : batchGroupSeqNos) {
                 SysBatchJob  job = new SysBatchJob();
                 job.setBatchSeqNo(batchGroupSeqNo);
                 job.setTaskId(taskId);
                 jobList.add(job);
             }
             batchGroupMapper.insert(batchGroup);
             sysBatchStartMapper.batchInsertBatchGroup(jobList);
         }

         String prodGroupSeqNo = selectedProductGroup;
         if (StringUtil.isNotEmpty(prodGroupSeqNo)
             && GlobalConstant.BATCH_EXECUTE_TYPE_SINGLE.equals(prodGroupType)) {
             prodGroupSeqNo = IDGenerator.generate();
             SysBatchPrdGrp  productGroup = new SysBatchPrdGrp();
             productGroup.setSeqNo(prodGroupSeqNo);
             productGroup.setGroupName("startBatch创建于"
                                       + (nowString == null ? DateUtil.getCurrentDateTime() : nowString));
             productGroup.setDetail(productGroup.getGroupName() + ";启动批处理时创建。");
             String[] prodGroupSeqNos = StringUtil.split(selectedProductGroup, ",");
             List<Product> productList = new ArrayList<Product>();
             if (productService != null) {
                productList = productService.findProductByIds(prodGroupSeqNos);
             }
             Map<String, Product> productMap = productList.parallelStream().collect(
                 Collectors.toMap(Product::getProdId, product -> product));
             List<SysBatchGrpPrdlink> products = new ArrayList<>();
             for (String prodId : prodGroupSeqNos) {
                 SysBatchGrpPrdlink  groupProducts = new SysBatchGrpPrdlink ();
                 groupProducts.setProductId(prodId);
                 groupProducts.setGroupSeq(prodGroupSeqNo);
                 groupProducts.setProductName(productMap.get(prodId).getProdName());
                 products.add(groupProducts);
             }
             batchProductGroupMapper.insert(productGroup);
             sysBatchStartMapper.batchInsertProdGroup(products);
         }

         SysBatchgrpExecInfo executeDetail = new SysBatchgrpExecInfo();
         executeDetail.setSystemCode("SYS");
         executeDetail.setSeqNo(IDGenerator.generate());
         executeDetail.setBatchSeqNo(batchGroupSeqNo);
         executeDetail.setPrdGroupSeqNo(prodGroupSeqNo);
         executeDetail.setStartDate(DateUtil.parse(startDate));
         executeDetail.setEndDate(DateUtil.parse(endDate));
         executeDetail.setStatus(BatchDictCodeList.批处理执行状态_未执行.getValue());
         executeDetail.setCreateUser("System");
         executeDetail.setUpdateUser("System");
         executeDetail.setDetailName("信息披露扫描作业情况"+DateUtil.getCurrentDateTime());
         batchExecuteDetailMapper.insert(executeDetail);

        flag =true;
        return flag;
    }
}
